package com.xinqushi.designpattern.chainofresponsibility;

/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:44:13
 * @Description: 请求类
 */
public class Request {

	/** 申请类别 */
	private String requestType;
	/** 申请内容 */
	private String requestContent;
	/** 数量 */
	private int number;
	
	public Request(String requestType, String requestContent, int number ) {
		this.requestType = requestType;
		this.requestContent = requestContent;
		this.number = number;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
