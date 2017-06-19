package net.xinqushi.service;

import java.util.List;

import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.AuditLog;
import net.xinqushi.util.Pair;

public interface AuditLogService {
	
	/** 测试方法
	 * @param reservId 参数1
	 * @param userId 参数2
	 * @param remark 参数3
	 */
	void testMethod(Long reservId, int userId, String remark);
	
	/** 日志列表查询*/
	Pair<List<AuditLog>, Integer> listAuditLogsByReservId(Long reservId,
			Integer pageNum, Integer pageSize) throws CommonException;
}