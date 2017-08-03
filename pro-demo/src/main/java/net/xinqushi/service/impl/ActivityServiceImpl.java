package net.xinqushi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.api.cache.CommonCacheManager;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.Activity;
import net.xinqushi.orm.mapper.ActivityMapper;
import net.xinqushi.service.ActivityService;
import net.xinqushi.util.PageResult;
import net.xinqushi.util.PageUtils;

@Service
public class ActivityServiceImpl implements ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private CommonCacheManager commonCacheManager;

	@Override
	public Long addActivity(Activity record) throws CommonException {
		try {
			//1、新增
			this.activityMapper.insertSelective(record);
			//2、向redis缓存写入标志检测时间用以更新状态[生效]
			Long timeout = ((record.getStartTime() - System.currentTimeMillis())/1000);
			this.commonCacheManager.cacheForExpiry("activity." + record.getActivityId() + ".created", "", timeout.intValue());
			//2、向redis缓存写入标志检测时间用以更新状态[失效]
			if (5 == record.getJumpType()) {
				//生效时间持续1天
				timeout = timeout + 24*60*60;
				this.commonCacheManager.cacheForExpiry("activity." + record.getActivityId() + ".destory", "", timeout.intValue());
			}
		} catch (Exception e) {
			logger.error("fail to creat activity: {}", e.getMessage());
			throw new CommonException("新增活动失败");
		}
		return record.getActivityId();
	}

	@Override
	public PageResult<Activity> searchActivities(String subject, Integer status, String type, Integer pageNum,
			Integer pageSize) throws CommonException {
		int nums = this.activityMapper.countListActivities(subject, status, type);
		List<Activity> list = new ArrayList<Activity>();
		if (nums > 0) {
			list = this.activityMapper.listActivities(subject, status, type, PageUtils.calcPageStart(pageNum, pageSize),
					pageSize);
		}

		return new PageResult<>(list, nums);
	}

}
