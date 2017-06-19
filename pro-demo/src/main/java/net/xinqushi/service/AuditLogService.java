package net.xinqushi.service;

public interface AuditLogService {
	
	/** 测试方法
	 * @param reservId 参数1
	 * @param userId 参数2
	 * @param remark 参数3
	 */
	void testMethod(Long reservId, int userId, String remark);
	
}