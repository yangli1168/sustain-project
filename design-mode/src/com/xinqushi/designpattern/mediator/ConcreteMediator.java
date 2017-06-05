package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:24:23
 * @Description: 具体中介者类
 */
public class ConcreteMediator extends Mediator{
	
	private ConcreteColleagueA ca;
	private ConcreteColleagueB cb;
	
	public void setCa(ConcreteColleagueA ca) {
		this.ca = ca;
	}

	public void setCb(ConcreteColleagueB cb) {
		this.cb = cb;
	}

	@Override
	public void send(String message, Colleague colleague) {
		if (colleague == ca) {
			cb.notify(message);
		} else {
			ca.notify(message);
		}
	}
}
