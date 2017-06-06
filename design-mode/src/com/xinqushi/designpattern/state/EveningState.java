package com.xinqushi.designpattern.state;
/**
 * 晚间工作状态类
 * @author yangli
 */
public class EveningState extends State{

	@Override
	public void writeProgram(Work work) {
		if (work.isFinish()) {
			work.setState(new RestState());
			work.writeProgram();
		} else {
			
			if (work.getHour() < 21) {
				System.out.println("当前时间：" + work.getHour() + "点  -> 加班哦，疲累之极");
			} else {
				//超过21点，转入睡眠工作状态
				work.setState(new SleepingState());
				work.writeProgram();
			}
		}
	}
	
}
