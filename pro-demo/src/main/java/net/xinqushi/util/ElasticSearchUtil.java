package net.xinqushi.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.percolate.PercolateRequest;
import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.percolate.PercolateResponse.Match;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.sf.json.util.JSONBuilder;
import net.xinqushi.orm.entity.City;

/**
 * 
 * @author yangli
 * @date 2017年4月13日 - 下午9:45:11
 * @Description ElasticSearch工具类
 */
public class ElasticSearchUtil {

	// 初始化
	private Client client = null;

	/**
	 * 私有构造方法
	 * 
	 * @param clusterName
	 *            不传值时默认"Yang"
	 * @param host
	 *            不传值时默认"127.0.0.1"
	 * @return
	 */
	public ElasticSearchUtil(String clusterName, String host) {
		if (null == client) {
			client = this.getESClient(clusterName, host);
		}
	}

	@SuppressWarnings("resource")
	private Client getESClient(String clusterName, String host) {
		if (null == client) {
			// 配置client
			Settings settings = ImmutableSettings.settingsBuilder()
					.put("cluster.name", clusterName == null ? "Yang" : clusterName).build();
			client = new TransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(host == null ? "127.0.0.1" : host, 9300));
		}
		return client;
	}

	public void putDoc(List<City> cities) {

		for (int i = 0; i < cities.size(); i++) {
			Object just = cities.get(i);
			IndexResponse response = client.prepareIndex().setIndex("city").setType("class").setId(i + 1 + "")
					.setSource(JSON.toJSONString(just)).execute().actionGet();
			System.out.println("索引创建成功,版本号：" + response.getVersion());
		}
		client.close();
	}

	private static final String CITY_AREA_QUERY_TEMPLATE = "{\"doc\":{\"location\":{\"lat\":%f,\"lon\":%f}}}";
	private static final String CITY_AREA_INDEX_NAME = "uto-city-area";
	
	public static void main(String[] args) {
		// List<City> cities = new ArrayList<City>();
		//
		// City city = new City();
		// city.setId(888);
		// city.setName("青云");
		// city.setCreateTime(System.currentTimeMillis());
		// cities.add(city);
		//
		// String jsondata = JSON.toJSONString(cities);
		// System.out.println("封装的json:" + jsondata);
		//
		// ElasticSearchUtil esUtil = new ElasticSearchUtil("Yang",
		// "localhost");
		// esUtil.putDoc(cities);
		// System.out.println(esUtil.test());
		// esUtil.getInfo();

		ElasticSearchUtil esUtil = new ElasticSearchUtil("utoo-es-test", "112.74.133.11");
		esUtil.getAreaInfo(30.665037, 104.073084);
//		esUtil.getResvInfo(30.665037, 104.073084);

		
	}
	
	public void getAreaInfo(double lat, double lon){
		String source = String.format(CITY_AREA_QUERY_TEMPLATE, lat, lon);
		int result = 0;
		
		PercolateRequest percolateRequest = new PercolateRequest();
		percolateRequest.indices(CITY_AREA_INDEX_NAME);
		percolateRequest.documentType("position-validate");
		percolateRequest.source(source);
		percolateRequest.listenerThreaded(false);
		PercolateResponse percolateResponse = client.percolate(percolateRequest).actionGet();
		if (percolateResponse.getCount() > 0) {
			Match[] matches = percolateResponse.getMatches();
			if (matches != null && matches.length > 0) {
				Match match = matches[0];
				String type = match.getId().string();
				if (type != null) {
					String[] array = type.split("\\.");
					String val = array[array.length - 1];
					result = Integer.parseInt(val);
				}
			}
		}

		System.out.println("result = " + result);
	}
	
	public void getResvInfo(double lat, double lon){
		String area = "";
		
		JSONObject coord = new JSONObject();
        coord.put("lat", lat);
        coord.put("lon", lon);
        JSONObject loc = new JSONObject();
        loc.put("location", coord);
        JSONObject request = new JSONObject();
        request.put("doc", loc);
        String source = request.toJSONString();

        PercolateRequest percolateRequest = new PercolateRequest();
        percolateRequest.indices("utoo-reservation-validate");
        percolateRequest.documentType("reservation-validate");
        percolateRequest.source(source);
        percolateRequest.listenerThreaded(false);
        PercolateResponse percolateResponse = client.percolate(percolateRequest).actionGet();
        if (percolateResponse.getCount() > 0) {
             Match[] matches = percolateResponse.getMatches();
             if (matches != null && matches.length > 0) {
             Match match = matches[0];
             String type = match.getId().string();
             if (type != null) {
            	 area = type;
             }
        }
        }
		
		System.out.println("area = " + area);
	}

	public String test() {
		City city = new City();
		city.setId(888);
		city.setName("青云");
		city.setCreateTime(System.currentTimeMillis());
		String sourceStr = JSON.toJSONString(city);
		return indexInfo("a", "b", sourceStr, 30000);
	}

	/**
	 * 
	 * @param indexName
	 *            索引名
	 * @param typeName
	 *            类型名
	 * @param sourceStr
	 *            需要索引的内容【json格式】
	 * @param time
	 *            有效时间
	 * @return id
	 */
	public String indexInfo(String indexName, String typeName, String sourceStr, long time) {
		IndexRequestBuilder builder = client.prepareIndex().setIndex(indexName).setType(typeName)
				.setSource(sourceStr.getBytes()).setTTL(time);
		// builder.setId("special");
		IndexResponse response = builder.execute().actionGet();
		String id = response.getId();
		return id;
	}

	public String getInfo() {
		String query = "{\"name\":\"测\"}";
		SearchResponse response = client.prepareSearch("city").setQuery("name").setFrom(0).setSize(5).setExplain(true)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit searchHit : hits) {
			System.out.println(searchHit.getId() + " -> " + searchHit.getIndex() + " -> " + searchHit.getFields());
		}
		return null;
	}
}
