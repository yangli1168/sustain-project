package com.xinqushi.designrule.command;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:12:52
 * @Description:
 */
public class CommandDemo {
	
	public static void main(String[] args) {
		
		BandCommandInvoker bc = new BandCommandInvoker();
		//存钱
		bc.doComand(new DepositCommand(8888));
		
		//取钱
		bc.doComand(new WithDrawCommand(6666));
		
		
	}
}
