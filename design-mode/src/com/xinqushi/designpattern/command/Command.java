package com.xinqushi.designpattern.command;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午7:54:19
 * @Description: 抽象命令超类
 */
public abstract class Command {
	
	Barbecuer receiver;
	
	public Command(Barbecuer receiver) {
		this.receiver = receiver;
	}
	
	/** 执行命令*/
	public abstract void excuteCommand();
}
