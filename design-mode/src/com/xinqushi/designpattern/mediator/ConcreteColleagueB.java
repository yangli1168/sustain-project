package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:26:34
 * @Description: 具体同事类B
 */
public class ConcreteColleagueB extends Colleague{

	public ConcreteColleagueB(Mediator mediator) {
		super(mediator);
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public void notify(String message){
		System.out.println("同事B得到信息：" + message);
	}
}
