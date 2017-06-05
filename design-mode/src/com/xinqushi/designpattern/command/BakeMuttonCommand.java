package com.xinqushi.designpattern.command;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午7:59:09
 * @Description: 烤羊肉命令类
 */
public class BakeMuttonCommand extends Command{

	public BakeMuttonCommand(Barbecuer receiver) {
		super(receiver);
	}

	@Override
	public void excuteCommand() {
		receiver.bakeMutton();
	}
	
	@Override
	public String toString() {
		return "烤羊肉串";
	}
}
