package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：抽象人的超类
 * @author yangli
 */
public abstract class Person {
	
//	String action;
//	
//	/** 得到结论或反应*/
//	public abstract void getConclusion(Action action);
//
//	public String getAction() {
//		return action;
//	}
//
//	public void setAction(String action) {
//		this.action = action;
//	}
	
	/** 获取状态对象*/
	public abstract void accept(Action visitor);
	
}
