package net.xinqushi.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
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
		ElasticSearchUtil esUtil = new ElasticSearchUtil("Yang", "localhost");
		// esUtil.putDoc(cities);
//		System.out.println(esUtil.test());
		esUtil.getInfo();
	}

	public String test() {
		City city = new City();
		city.setId(888);
		city.setName("青云");
		city.setCreateTime(System.currentTimeMillis());
		String sourceStr = JSON.toJSONString(city);
		return indexInfo("a","b",sourceStr,30000);
	}
	
	/**
	 * 
	 * @param indexName 索引名
	 * @param typeName	类型名
	 * @param sourceStr 需要索引的内容【json格式】
	 * @param time 有效时间
	 * @return id
	 */
	public String indexInfo(String indexName, String typeName, String sourceStr, long time) {
		IndexRequestBuilder builder = client.prepareIndex().setIndex(indexName).setType(typeName)
				.setSource(sourceStr.getBytes()).setTTL(time);
//		builder.setId("special");
		IndexResponse response = builder.execute().actionGet();
		String id = response.getId();
		return id;
	}
	
	public String getInfo(){
		String query = "{\"name\":\"测\"}";
		SearchResponse response = client.prepareSearch("city")
				.setQuery("name").setFrom(0).setSize(5).setExplain(true)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit searchHit : hits) {
			System.out.println(searchHit.getId()
					+ " -> " + searchHit.getIndex()
					+ " -> " + searchHit.getFields());
		}
		return null;
	}
}
