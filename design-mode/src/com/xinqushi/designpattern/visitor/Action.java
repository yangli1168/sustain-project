package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式：状态接口
 * @author yangli
 */
public interface Action {
	
	/** 获取男人结论或反应*/
	public void getManConclusion(Man man);
	
	/** 获取女人结论或反应*/
	public void getWomenConclusion(Woman woman);
}
