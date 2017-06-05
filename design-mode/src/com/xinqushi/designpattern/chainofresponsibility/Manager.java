package com.xinqushi.designpattern.chainofresponsibility;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:38:20
 * @Description: 抽象管理者超类
 */
public abstract class Manager {
	
	String name;
	
	//管理者的上级
	Manager superior;
	
	public Manager(String name) {
		this.name = name;
	}
	
	/** 设置管理者的上级*/
	public void setSuperior(Manager superior){
		this.superior = superior;
	}
	
	/** 申请请求*/
	public abstract void requestApplication(Request request);
}
