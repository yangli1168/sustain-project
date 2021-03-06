package net.xinqushi.common.constants;

/**
 * 缓存key常量，用于标记
 * 
 * @author yangli
 */
public class CacheConstants {

	/** 缓存标志：用于定时任务刷新缓存 */
	public static final String CITY_PARAM_UPDATED = "city:param:update";

	/** 城市列表key:缓存所有非删除状态城市 */
	public static final String CITY_KEY = "city:list";

	/** 线路规定上下车地点 */
	public static final String LINE_FIXED_SPOT_KEY = "line:fixed:spots";

	/** 线路设置key */
	public static final String LINE_SETTINGS_KEY = "line:settings";

}
