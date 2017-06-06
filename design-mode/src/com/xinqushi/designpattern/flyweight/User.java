package com.xinqushi.designpattern.flyweight;
/**
 * 享元模式：用户类
 * @author yangli
 */
public class User {
	
	private String name;
	
	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
