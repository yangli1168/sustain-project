package net.xinqushi.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.xinqushi.orm.entity.AuditLog;

public interface AuditLogMapper extends SqlMapper {
	
	int insertSelective(AuditLog record);
	
	/** 测试方法日志列表*/
	List<AuditLog> listTestMethodLogs(@Param("reservId")long reservId, 
			@Param("start")Integer start, @Param("pageSize")Integer pageSize);
	
	/** 测试方法日志列表计数*/
	int countTestMethodLogs(@Param("reservId")long reservId);
}