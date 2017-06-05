package com.xinqushi.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yangli
 * @date 2017年6月4日 - 下午6:22:58
 * @Description: 观察者模式：秘书类
 */
public class Secretary implements Subject{
	
	private String action;
	
	//观察者列表
	private List<Observer> observers = new ArrayList<Observer>();
	
	
	@Override
	//增加观察者
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	@Override
	//减少观察者
	public void delObserver(Observer observer){
		observers.remove(observer);
	}
	
	@Override
	//通知观察者
	public void notifyObserver(){
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
