package com.xinqushi.designpattern.state;
/**
 * 下午工作状态类
 * @author yangli
 */
public class AfternoonState extends State{

	@Override
	public void writeProgram(Work work) {
		if (work.getHour() < 17) {
			System.out.println("当前时间：" + work.getHour() + "点  -> 下午状态不错，继续努力");
		} else {
			work.setState(new EveningState());
			work.writeProgram();
		}
	}
	
}
