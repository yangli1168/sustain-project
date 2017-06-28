package net.xinqushi.common.enums;
/**
 * 状态枚举
 * @author yangli
 */
public enum CommonStatus {
	/** 有效*/
	VALID("有效"), 
	/** 无效*/
	INVALID("无效"), 
	/** 删除*/
	DELETED("删除");
	
	private final String status;
	
	private CommonStatus(String status) {
		this.status = status;
	}
	
	/** 返回文字说明*/
	public String getExplain(){
		return status;
	}
	
	public static void main(String[] args) {
		
		System.out.println(CommonStatus.VALID);
		System.out.println(CommonStatus.VALID.ordinal());
		System.out.println(CommonStatus.VALID.getExplain());
	}
}
