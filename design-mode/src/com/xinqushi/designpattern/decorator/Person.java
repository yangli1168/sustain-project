package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:38:50
 * @Description: 需要装饰的对象
 */
public class Person {
	
	private String name;
	
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}
	
	public void show(){
		System.out.println("装扮的" + name);
	}
}
