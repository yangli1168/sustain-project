package net.xinqushi.entry.vo;
/**
 * 状态码常量类
 * @author yangli
 */
public class StatusCodeConstants {
	
	/** 请求参数：user-token*/
	public static final String REQUEST_TOKEN_KEY = "user-token";
	
	/** 请求参数：version*/
	public static final String REQUEST_VERSION_KEY = "version";
	
	/** 请求参数：platform*/
	public static final String REQUEST_PLATFORM_KEY = "platform";
	
	/** 请求参数：type*/
	public static final String REQUEST_TYPE_KEY = "type";
	
	/** 成功：200*/
	public static final int RESP_STATUS_OK = 200;
	
	/** 错误：无授权401*/
	public static final int RESP_STATUS_NOAUTH = 401;
	
	/** 服务器错误：500*/
	public static final int RESP_STATUS_INTERNAL = 500;
	
	/** 错误：超时550*/
	public static final int RESP_STATUS_SHARE_EXPIRED = 550;
	
	/** 错误：用户已存在560*/
	public static final int RESP_USER_EXISTS = 560;
	
	/** 错误：券已使用570*/
	public static final int RESP_COUPON_TAKEN = 570;
	
	/** 错误：请求失败400*/
	public static final int RESP_STATUS_BADREQUEST = 400;
	
	/** 错误：状态激活失败501*/
	public static final int RESP_STATUS_ACTIVATE_ERROR = 501;
	
}
