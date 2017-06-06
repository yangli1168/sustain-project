package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：失败状态类
 * @author yangli
 */
public class ActionFailing implements Action{

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.toString() + this.toString() + 
				"时， 闷头喝酒，谁也不用劝。");
	}

	@Override
	public void getWomenConclusion(Woman woman) {
		System.out.println(woman.toString() + this.toString() + 
				"时， 眼泪汪汪， 谁也劝不了。");
	}

	@Override
	public String toString() {
		return "失败";
	}

}
