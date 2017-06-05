package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:26:34
 * @Description: 具体同事类A
 */
public class ConcreteColleagueA extends Colleague{

	public ConcreteColleagueA(Mediator mediator) {
		super(mediator);
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public void notify(String message){
		System.out.println("同事A得到信息：" + message);
	}
}
