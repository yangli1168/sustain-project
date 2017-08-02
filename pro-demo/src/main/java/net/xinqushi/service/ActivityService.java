package net.xinqushi.service;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.Activity;
import net.xinqushi.util.PageResult;

/**
 * 活动服务
 * @author yangli
 * 2017年8月2日-上午9:56:38
 */
public interface ActivityService {
	
	/** 新增活动 */
	Long addActivity(Activity record) throws CommonException;
	
	/** 活动列表查询 
	 * @param subject 主题
	 * @param status 状态
	 * @param type 类型
	 * @param pageNum
	 * @param pageSize
	 */
	PageResult<Activity> searchActivities(String subject, Integer status, String type,
			Integer pageNum, Integer pageSize) throws CommonException;
}
