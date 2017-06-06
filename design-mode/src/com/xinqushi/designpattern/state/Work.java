package com.xinqushi.designpattern.state;
/**
 * 
 * @author yangli
 */
public class Work {
	
	private State state;
	private double hour;
	//工作完成属性：默认未完成
	private boolean finish = false;
	
	public Work() {
		//初始化为上午工作状态
		state = new ForenoonState();
	}
	
	/** 工作方法*/
	public void writeProgram(){
		state.writeProgram(this);
	}
	
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
