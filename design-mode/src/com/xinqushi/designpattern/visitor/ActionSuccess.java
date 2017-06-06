package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：成功状态类
 * @author yangli
 */
public class ActionSuccess implements Action{

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.toString() + this.toString() + 
				"时， 背后大多有一个伟大的女人。");
	}

	@Override
	public void getWomenConclusion(Woman woman) {
		System.out.println(woman.toString() + this.toString() + 
				"时， 背后大多有一个不成功的男人。");
	}

	@Override
	public String toString() {
		return "成功";
	}

}
