package com.xinqushi.designpattern.state;

/**
 * 下班休息状态类
 * 
 * @author yangli
 */
public class SleepingState extends State {

	@Override
	public void writeProgram(Work work) {
		if (work.getHour() < 23) {
			System.out.println("当前时间：" + work.getHour() + "点  -> 不行了，睡着了");
		} else {
			work.setState(new ForceState());
			work.writeProgram();
		}
	}

}
