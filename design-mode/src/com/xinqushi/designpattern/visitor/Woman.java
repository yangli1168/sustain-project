package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：女人类
 * @author yangli
 */
public class Woman extends Person{

	@Override
	public String toString() {
		return "女人";
	}

	@Override
	public void accept(Action visitor) {
		visitor.getWomenConclusion(this);
	}

}
