package net.xinqushi.common.response;

/**
 * 响应类型封装
 * @author yangli
 */
public class RestResponse {

	/** 响应业务状态 */
	private int code = 200;

	/** 响应中的数据 */
	private Object data;

	/** 响应消息 */
	private String msg;
	
	/** 一般用于返回列表计数*/
	private Object appendix;
	
	/** 可用于返回另外的响应数据*/
	private Object results;

	/** 构造函数：空 */
	public RestResponse() {

	}

	/** 构造函数：携带数据对象 */
	public RestResponse(Object data) {
		this.msg = "OK";
		this.data = data;
	}

	/** 构造函数：携带状态、消息、数据对象 */
	public RestResponse(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 公共静态方法：携带状态(200)、消息(ok)，无数据对象
	 * 
	 * @return
	 */
	public static RestResponse ok() {
		return new RestResponse(null);
	}

	/**
	 * 公共静态方法：携带状态(200)、消息(ok)、数据对象
	 * 
	 * @param data
	 * @return
	 */
	public static RestResponse ok(Object data) {
		return new RestResponse(data);
	}

	/**
	 * 公共静态方法：携带状态、消息；数据对象为null
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static RestResponse build(int code, String msg) {
		return new RestResponse(code, msg, null);
	}

	/**
	 * 公共静态方法：携带状态、消息、数据对象
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static RestResponse build(int code, String msg, Object data) {
		return new RestResponse(code, msg, data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getAppendix() {
		return appendix;
	}
	
	/** 一般用于设置列表计数*/
	public void setAppendix(Object appendix) {
		this.appendix = appendix;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

}

