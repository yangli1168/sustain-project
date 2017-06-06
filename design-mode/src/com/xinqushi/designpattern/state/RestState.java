package com.xinqushi.designpattern.state;

/**
 * 下班休息状态类
 * 
 * @author yangli
 */
public class RestState extends State {

	@Override
	public void writeProgram(Work work) {
		System.out.println("当前时间：" + work.getHour() + "点  -> 下班回家了");
	}

}
