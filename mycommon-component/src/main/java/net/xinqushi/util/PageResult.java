package net.xinqushi.util;

import java.util.List;
/**
 * 分页VO
 * 2017年7月26日-上午9:03:53
 * @param <T>
 */
public class PageResult <T>{

	/**
	 * 分页查询的结果
	 */
	public List<T> queryList ;
	
	/**
	 * 同一查询条件下数据集总数
	 */
	public Integer totalCount;
	
	/** 构造函数*/
	public PageResult(List<T> queryList, Integer totalCount) {
		this.queryList = queryList;
		this.totalCount = totalCount;
	}
	
	public PageResult() {
        // TODO Auto-generated constructor stub
    }
}
