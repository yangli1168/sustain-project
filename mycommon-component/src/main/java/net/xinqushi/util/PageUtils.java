package net.xinqushi.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 分页工具类
 * @author yangli
 */
public class PageUtils {
	
	/** 返回分页查询记录首地址*/
	public static int calcPageStart(int pageNum, int pageSize) {
		if (pageNum <= 0) {
			pageNum = 1;
		}
		return (pageNum - 1) * pageSize;
	}
	
	/** 返回分页查询参数*/
	public static Pair<Integer, Integer> getPageRequestInfo(JSONObject jo){
		Integer pageNum = jo.getInteger("pageNum");
		Integer pageSize = jo.getInteger("pageSize");
		
		if (null == pageNum || pageNum <= 0) {
			pageNum = 1;
		}
		if (null == pageSize || pageSize <= 0) {
			pageSize = 10;
		}
		
		return new Pair<Integer, Integer>(pageNum, pageSize);
	}
}
