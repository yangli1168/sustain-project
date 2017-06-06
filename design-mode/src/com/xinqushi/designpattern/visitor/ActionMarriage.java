package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：结婚状态类
 * @author yangli
 */
public class ActionMarriage implements Action{

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.toString() + this.toString() + 
				"时， 感慨道：恋爱游戏终结时，'有妻徒刑'遥无期。");
	}

	@Override
	public void getWomenConclusion(Woman woman) {
		System.out.println(woman.toString() + this.toString() + 
				"时，欣慰曰：爱情长跑路漫漫，婚姻保险保平安。");
	}

	@Override
	public String toString() {
		return "结婚";
	}

}
