package net.xinqushi.common.constants;

/**
 * ElasticSearchz用常量类
 * 
 * @author yangli 2017年4月19日-下午12:31:20
 */
public class ESConstants {
	
	/** es服务器默认host*/
	public static final String DEFAULT_HOST = "127.0.0.1";
	
	/** es服务器默认clustername*/
	public static final String DEFAULT_CLUSTER_NAME = "Yang";
	
	/** 城市区域索引名 */
	public static final String CITY_AREA_INDEX_NAME = "city-area-yang";

	/** 城市区域新增索引样式 */
	public static final String UPDATE_CITY_AREA_PERCOLATOR_TEMPLATE = "{\"query\":{\"filtered\":{\"query\":{\"match_all\":{}},\"filter\":{\"geo_polygon\":{\"location\":{\"points\":[]}}}}}}";

	/** 城市区域查询样式 */
	public static final String CITY_AREA_QUERY_TEMPLATE = "{\"doc\":{\"location\":{\"lat\":%f,\"lon\":%f}}}";

}
