package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：恋爱状态类
 * @author yangli
 */
public class ActionLoving implements Action{

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.toString() + this.toString() + 
				"时， 凡事不懂也要装懂。");
	}

	@Override
	public void getWomenConclusion(Woman woman) {
		System.out.println(woman.toString() + this.toString() + 
				"时，遇事懂也装作不懂。");
	}

	@Override
	public String toString() {
		return "恋爱";
	}

}
