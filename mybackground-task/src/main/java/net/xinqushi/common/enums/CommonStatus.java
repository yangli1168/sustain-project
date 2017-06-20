package net.xinqushi.common.enums;
/**
 * 状态枚举
 * @author yangli
 */
public enum CommonStatus {
	/** 合法*/
	VALID, 
	/** 非法*/
	INVALID, 
	/** 删除*/
	DELETED;
	
	public static String toString(CommonStatus status){
		String result = null;
		switch (status) {
		case VALID:
			result = "有效";
			break;
		case INVALID:
			result = "无效";
			break;
		case DELETED:
			result = "删除";
			break;
		}
		return result;
	}
}
