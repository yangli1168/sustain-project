package com.xinqushi.designpattern.chainofresponsibility;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:31:52
 * @Description 责任链模式demo
 * <p>定义：使多个对象都有机会处理请求，从而避免请求的发送者和接受者之间的耦合关系。
 * 将这个对象连成一条链，并沿着该链传递该请求，知道有对象处理它为止</p>
 * <p>使用：</p>
 * <p>优点：当客户提交一个请求时，请求沿着链条直至有一个concreteHandler对象负责处理它</p>
 */
public class ChainOfResponsibilityDemo {
	
	public static void main(String[] args) {
		
		CommonManager jinli = new CommonManager("经理");
		Majordomo zongjian = new Majordomo("总监");
		GeneralManager zongjinli = new GeneralManager("总经理");
		//设置上级[可根据需求来更改]
		jinli.setSuperior(zongjian);
		zongjian.setSuperior(zongjinli);
		
		//请求
		Request request1 = new Request("请假", "梦璃请假", 1);
		jinli.requestApplication(request1);
		
		Request request2 = new Request("请假", "梦璃请假", 4);
		jinli.requestApplication(request2);
		
		Request request3 = new Request("加薪", "梦璃请求加薪", 500);
		jinli.requestApplication(request3);
		
		Request request4 = new Request("加薪", "梦璃请求加薪", 1000);
		jinli.requestApplication(request4);
		
	}
}
