package com.xinqushi.designrule.command;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:09:11
 * @Description: 模拟atm取钱操作
 */
public class WithDrawCommand implements Command{
	
	private int amount;
	
	public WithDrawCommand(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		System.out.println("执行取款操作：取钱" + amount + "元");
	}
	
}
