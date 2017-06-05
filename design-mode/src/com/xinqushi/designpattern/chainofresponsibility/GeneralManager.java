package com.xinqushi.designpattern.chainofresponsibility;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:42:22
 * @Description: 总经理类
 */
public class GeneralManager extends Manager{

	public GeneralManager(String name) {
		super(name);
	}

	@Override
	public void requestApplication(Request request) {
		//总经理的权限可以处理任意天内的假期
		if (request.getRequestType().equals("请假")) {
			System.out.println(name + " : " + request.getRequestContent() + 
					request.getNumber() + "天，被批准");
		} else if (request.getRequestType().equals("加薪") &&
				request.getNumber() <= 500) {
			System.out.println(name + " : " + request.getRequestContent() + 
					request.getNumber() + "元，被批准");
		} else if (request.getRequestType().equals("加薪") &&
				request.getNumber() > 500) {
			System.out.println(name + " : " + request.getRequestContent() + 
					request.getNumber() + "元，再说吧");
		} 
	}
	
}
