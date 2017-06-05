package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:22:31
 * @Description: 抽象同事超类
 */
public class Colleague {
	
	Mediator mediator;
	
	public Colleague(Mediator mediator) {
		this.mediator = mediator;
	}
}
