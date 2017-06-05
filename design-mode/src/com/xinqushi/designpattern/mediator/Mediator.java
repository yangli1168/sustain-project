package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:21:19
 * @Description: 抽象中介者超类
 */
public abstract class Mediator {
	
	public abstract void send(String message, Colleague colleague);
}
