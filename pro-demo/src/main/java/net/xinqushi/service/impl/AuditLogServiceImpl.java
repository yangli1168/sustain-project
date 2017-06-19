package net.xinqushi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.common.enums.OperationType;
import net.xinqushi.jms.AuditLogPublisher;
import net.xinqushi.orm.entity.AuditLog;
import net.xinqushi.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private AuditLogPublisher auditLogPublisher;

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

}
