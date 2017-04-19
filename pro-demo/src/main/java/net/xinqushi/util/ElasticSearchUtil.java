package net.xinqushi.util;

import java.util.List;

import org.elasticsearch.action.admin.indices.refresh.RefreshAction;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.percolate.PercolateRequest;
import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.percolate.PercolateResponse.Match;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import net.xinqushi.common.constants.ESConstants;
import net.xinqushi.orm.entity.City;
import net.xinqushi.vo.Coordinate;

/**
 * 
 * @author yangli
 * @Description ElasticSearch工具类
 */
public class ElasticSearchUtil {

	// 初始化
	private static Client client = null;
	
	private ElasticSearchUtil() {
		super();
	}
	
	/**
	 * 静态方法：返回client
	 * @param clusterName null时采用默认值
	 * @param host null时采用默认值
	 * @return
	 */
	@SuppressWarnings("resource")
	public static Client getESClient(String clusterName, String host) {
		if (null == client) {
			// 配置client
			Settings settings = ImmutableSettings.settingsBuilder()
					.put("cluster.name", clusterName == null ? ESConstants.DEFAULT_CLUSTER_NAME : clusterName)
					.put("client.transport.ping_timeout", "10s").build();
			client = new TransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(host == null ? ESConstants.DEFAULT_HOST : host, 9300));
		}
		return client;
	}
	
	/**
	 * 静态方法：存入索引信息
	 * @param clusterName null时采用默认值
	 * @param host null时采用默认值
	 * @param indexName 索引名
	 * @param queryCondition 自定义索引条件
	 * @param dataSource 需要索引的数据源
	 */
	public static void saveOrUpdateIndex(String clusterName, String host, String indexName, String queryCondition, byte[] dataSource ){
		Client esClient = ElasticSearchUtil.getESClient(clusterName, host);
		RefreshAction.INSTANCE.newRequestBuilder(esClient.admin().indices()).setIndices(indexName).execute()
		.actionGet();
		try {
			GetResponse response = esClient.prepareGet(indexName, ".percolator", queryCondition).execute().actionGet();
			if (response.isExists()) {
				esClient.prepareUpdate(indexName, ".percolator", queryCondition).setDoc(dataSource)
				.execute().actionGet();
			} else {
				esClient.prepareIndex(indexName, ".percolator", queryCondition).setSource(dataSource)
				.execute().actionGet();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			RefreshAction.INSTANCE.newRequestBuilder(esClient.admin().indices()).setIndices(indexName).execute()
			.actionGet();
			esClient.close();
		}
	}
	
	/**
	 * 
	 * @param clusterName null时采用默认值
	 * @param host null时采用默认值
	 * @param indexName 索引名
	 * @param queryCondition 自定义索引条件
	 */
	public static void deleteIndex(String clusterName, String host, String indexName, String queryCondition){
		Client esClient = ElasticSearchUtil.getESClient(clusterName, host);
		try {
			esClient.prepareDelete(indexName, ".percolator", queryCondition).execute().actionGet();
			RefreshAction.INSTANCE.newRequestBuilder(esClient.admin().indices()).setIndices(indexName).execute()
			.actionGet();
			
			GetResponse response = esClient.prepareGet(indexName, ".percolator", queryCondition).execute().actionGet();
			if (response.isExists()) {
				esClient.prepareDelete(indexName, ".percolator", queryCondition).execute().actionGet();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			esClient.close();
		}
	}
	
	private static final String CITY_AREA_INDEX_NAME = "uto-city-area";
	private static final TypeReference<List<Coordinate>> coordinatesTypeRef = new TypeReference<List<Coordinate>>() {
	}; 
	
	public static void main(String[] args) {

//		ElasticSearchUtil.getAreaInfo(30.665037, 104.073084);
//		esUtil.getResvInfo(30.665037, 104.073084);
		
		
		String param = "[{\"lat\":28.96593,\"lon\":105.44397},{\"lat\":28.964866542710855,\"lon\":105.45612537243667},{\"lat\":28.961708483455013,\"lon\":105.46791141003278},{\"lat\":28.95655177826491,\"lon\":105.47896999999999},{\"lat\":28.94955311101833,\"lon\":105.48896513267805},{\"lat\":28.940925132678057,\"lon\":105.49759311101832},{\"lat\":28.93093,\"lon\":105.50459177826491},{\"lat\":28.919871410032798,\"lon\":105.50974848345501},{\"lat\":28.908085372436684,\"lon\":105.51290654271085},{\"lat\":28.89593,\"lon\":105.51396999999999},{\"lat\":28.883774627563316,\"lon\":105.51290654271085},{\"lat\":28.871988589967202,\"lon\":105.50974848345501},{\"lat\":28.86093,\"lon\":105.50459177826491},{\"lat\":28.850934867321943,\"lon\":105.49759311101832},{\"lat\":28.84230688898167,\"lon\":105.48896513267805},{\"lat\":28.83530822173509,\"lon\":105.47896999999999},{\"lat\":28.830151516544987,\"lon\":105.46791141003278},{\"lat\":28.826993457289145,\"lon\":105.45612537243667},{\"lat\":28.82593,\"lon\":105.44397},{\"lat\":28.826993457289145,\"lon\":105.43181462756331},{\"lat\":28.830151516544987,\"lon\":105.4200285899672},{\"lat\":28.83530822173509,\"lon\":105.40897},{\"lat\":28.84230688898167,\"lon\":105.39897486732194},{\"lat\":28.850934867321943,\"lon\":105.39034688898167},{\"lat\":28.86093,\"lon\":105.38334822173508},{\"lat\":28.871988589967202,\"lon\":105.37819151654497},{\"lat\":28.883774627563316,\"lon\":105.37503345728913},{\"lat\":28.89593,\"lon\":105.37397},{\"lat\":28.908085372436684,\"lon\":105.37503345728913},{\"lat\":28.919871410032798,\"lon\":105.37819151654497},{\"lat\":28.93093,\"lon\":105.38334822173508},{\"lat\":28.940925132678057,\"lon\":105.39034688898167},{\"lat\":28.94955311101833,\"lon\":105.39897486732194},{\"lat\":28.95655177826491,\"lon\":105.40897},{\"lat\":28.961708483455013,\"lon\":105.4200285899672},{\"lat\":28.964866542710855,\"lon\":105.43181462756331}]";
		List<Coordinate> source = JSON.parseObject(param, coordinatesTypeRef);
		
		ElasticSearchUtil.saveOrUpdateIndex(null, null, "city-area-yang", new StringBuilder().append("qinyun").append(".").append("888").toString(), 
				ElasticSearchUtil.createSource(source));
	}
	
	public static byte[] createSource(List<Coordinate> source){
		
		JSONObject jo = JSON.parseObject(ESConstants.UPDATE_CITY_AREA_PERCOLATOR_TEMPLATE);

		JSONArray points = jo.getJSONObject("query").getJSONObject("filtered").getJSONObject("filter")
				.getJSONObject("geo_polygon").getJSONObject("location").getJSONArray("points");
		Coordinate coordinate = null;
		for (Coordinate range : source) {
			if (coordinate == null) {
				coordinate = range;
			}
			JSONArray ja = new JSONArray();
			ja.add(range.getLon());
			ja.add(range.getLat());
			points.add(ja);
		}
		if (coordinate != null) {
			JSONArray ja = new JSONArray();
			ja.add(coordinate.getLon());
			ja.add(coordinate.getLat());
			points.add(ja);
		}
		
		return JSON.toJSONString(jo).getBytes();
	}
	
	public static String createQC(String pinyin, int cityId){
		StringBuilder sb = new StringBuilder();
		sb.append(pinyin).append(".").append(cityId);
		return 	sb.toString();
	}
	
	
	public static void putDoc(List<City> cities) {
		client = ElasticSearchUtil.getESClient(null, null);
		
		for (int i = 0; i < cities.size(); i++) {
			Object just = cities.get(i);
			IndexResponse response = client.prepareIndex().setIndex("city").setType("class").setId(i + 1 + "")
					.setSource(JSON.toJSONString(just)).execute().actionGet();
			System.out.println("索引创建成功,版本号：" + response.getVersion());
		}
		client.close();
	}
	
	public static void getAreaInfo(double lat, double lon){
		client = ElasticSearchUtil.getESClient("utoo-es-test", "112.74.133.11");
		String source = String.format(ESConstants.CITY_AREA_QUERY_TEMPLATE, lat, lon);
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
		client = ElasticSearchUtil.getESClient("utoo-es-test", "112.74.133.11");
		
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
}
