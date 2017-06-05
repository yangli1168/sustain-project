package com.xinqushi.designpattern.observer;
/**
 * 
 * @author yangli
 * @date 2017年6月4日 - 下午6:45:58
 * @Description: 通知者接口
 */
public interface Subject {
	
	void addObserver(Observer observer);
	
	void delObserver(Observer observer);
	
	void notifyObserver();

	String getAction();
	
	void setAction(String action);
}
