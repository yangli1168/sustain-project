package com.xinqushi.designpattern.chainofresponsibility;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:42:22
 * @Description: 经理类
 */
public class CommonManager extends Manager{

	public CommonManager(String name) {
		super(name);
	}

	@Override
	public void requestApplication(Request request) {
		//经理的权限可以处理两天内的假期
		if (request.getRequestType().equals("请假") &&
				request.getNumber() <= 2) {
			System.out.println(name + " : " + request.getRequestContent() + 
					request.getNumber() + "天，被批准");
		} else {
			if (null != superior) {
				superior.requestApplication(request);
			}
		}
	}
	
}
