package com.xinqushi.designpattern.flyweight;
/**
 * 享元模式：具体网站类
 * @author yangli
 */
public class ConcreteWebSite implements WebSite{

	private String name = "";
	
	public ConcreteWebSite(String name) {
		this.name = name;
	}
	
	@Override
	public void use(User user) {
		System.out.println("网站分类：" + name + 
				" 用户：" + user.getName());
	}
	
}
