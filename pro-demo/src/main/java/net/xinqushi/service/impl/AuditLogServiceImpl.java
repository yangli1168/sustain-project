package net.xinqushi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.common.enums.OperationType;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.jms.AuditLogPublisher;
import net.xinqushi.orm.entity.AuditLog;
import net.xinqushi.orm.mapper.AuditLogMapper;
import net.xinqushi.service.AuditLogService;
import net.xinqushi.util.PageUtils;
import net.xinqushi.util.Pair;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private AuditLogPublisher auditLogPublisher;
	
	@Autowired
	private AuditLogMapper auditLogMapper;
	
	private PageUtils pageUtils;

	private void pushAuditLogToQueue(AuditLog log) {
		auditLogPublisher.sendAuditLogsToQueue(log);
	}

	@Override
	public void testMethod(Long reservId, int userId, String remark) {
		AuditLog log = new AuditLog();
		log.setOpType(OperationType.TEST_OPERATION);
		log.setOpTypeStr(OperationType.TEST_OPERATION);
		log.setReservId(reservId);
		log.setManagementUserId(userId);
		log.setTime(System.currentTimeMillis());
		this.pushAuditLogToQueue(log);
	}

	@Override
	public Pair<List<AuditLog>, Integer> listAuditLogsByReservId(Long reservId, Integer pageNum, Integer pageSize)
			throws CommonException {
		
		int nums = this.auditLogMapper.countTestMethodLogs(reservId);
		List<AuditLog> list = new ArrayList<AuditLog>();
		if (nums > 0) {
			list = this.auditLogMapper.listTestMethodLogs(reservId, this.pageUtils.calcPageStart(pageNum, pageSize), pageSize);
		}
		
		return new Pair<List<AuditLog>, Integer>(list, nums);
	}
	
	

}
