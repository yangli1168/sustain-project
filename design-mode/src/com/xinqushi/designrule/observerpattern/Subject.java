package com.xinqushi.designrule.observerpattern;
/**
 * 观察者模式-主题接口
 * @author yangli
 */
public interface Subject {
	
	/** 注册观察者*/
	void registerObserver(Observer o);
	
	/** 注销观察者*/
	void removeObserver(Observer o);
	
	/** 通知观察者*/
	void notifyObservers();
}
