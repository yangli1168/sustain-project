package net.xinqushi.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.xinqushi.orm.entity.Activity;

public interface ActivityMapper extends SqlMapper{
	
    int deleteByPrimaryKey(Long activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    /** 活动列表*/
    List<Activity> listActivities(@Param("subject") String subject, @Param("status") Integer status, 
    		@Param("type") String type, @Param("start")Integer start, @Param("pageSize")Integer pageSize);
    
    /** 活动列表计数*/
    int countListActivities(@Param("subject") String subject, @Param("status") Integer status, @Param("type") String type);
    
}