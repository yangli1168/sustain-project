package com.xinqushi.designpattern.command;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午7:59:09
 * @Description: 烤羊肉命令类
 */
public class BakeChickenWingCommand extends Command{

	public BakeChickenWingCommand(Barbecuer receiver) {
		super(receiver);
	}

	@Override
	public void excuteCommand() {
		receiver.bakeChickenWing();
	}

	@Override
	public String toString() {
		return "烤鸡翅";
	}
	
}
