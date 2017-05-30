package com.xinqushi.designrule.command;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:09:11
 * @Description: 模拟atm存钱操作
 */
public class DepositCommand implements Command{
	
	private int amount;
	
	public DepositCommand(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		System.out.println("执行存款操作：存款" + amount + "元");
	}
	
}
