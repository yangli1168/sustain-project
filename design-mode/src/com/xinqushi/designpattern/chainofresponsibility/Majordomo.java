package com.xinqushi.designpattern.chainofresponsibility;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:42:22
 * @Description: 总监类
 */
public class Majordomo extends Manager{

	public Majordomo(String name) {
		super(name);
	}

	@Override
	public void requestApplication(Request request) {
		//总监的权限可以处理五天内的假期
		if (request.getRequestType().equals("请假") &&
				request.getNumber() <= 5) {
			System.out.println(name + " : " + request.getRequestContent() + 
					request.getNumber() + "天，被批准");
		} else {
			if (null != superior) {
				superior.requestApplication(request);
			}
		}
	}
	
}
