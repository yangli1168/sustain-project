package com.xinqushi.designpattern.state;

/**
 * 强制下班状态类
 * 
 * @author yangli
 */
public class ForceState extends State {

	@Override
	public void writeProgram(Work work) {
		System.out.println("当前时间：" + work.getHour() + "点  -> 必须在22点前离开公司，再见");
	}

}
