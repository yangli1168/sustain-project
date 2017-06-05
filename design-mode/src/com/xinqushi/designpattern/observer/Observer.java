package com.xinqushi.designpattern.observer;
/**
 * 
 * @author yangli
 * @date 2017年6月4日 - 下午6:21:27
 * @Description: 抽象观察者超类
 */
public abstract class Observer {
	
	String name;
	Subject subject;
	
	public Observer(String name, Subject subject) {
		this.name = name;
		this.subject = subject;
	}
	
	public abstract void update();
}
