package com.xinqushi.designpattern.composite;
/**
 * 抽象公司超类
 * @author yangli
 */
public abstract class Company {
	
	String name;
	
	public Company(String name) {
		this.name = name;
	}
	
	public abstract void add(Company company);
	public abstract void del(Company company);
	public abstract void display(int depth);
	
	/** 不同部门履行不同职责*/
	public abstract void lineOfDuty();  
}
