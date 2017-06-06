package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：男人类
 * @author yangli
 */
public class Man extends Person{

	@Override
	public String toString() {
		return "男人";
	}

	@Override
	public void accept(Action visitor) {
		visitor.getManConclusion(this);
	}
	
}
