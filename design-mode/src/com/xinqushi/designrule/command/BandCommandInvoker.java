package com.xinqushi.designrule.command;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:06:41
 * @Description: 模拟atm机进行操作
 */
public class BandCommandInvoker {
	
	public void doComand(Command command){
		command.execute();
	}
}
