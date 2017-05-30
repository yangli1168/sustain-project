package com.xinqushi.designrule;
/**
 * 
 * @author yangli
 * @date 2017年5月27日 - 上午12:02:35
 * @Description:
 */
public class ModuleDuck extends Duck{
	
	public ModuleDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new MuteQuack();
	}

	@Override
	public void display() {
		System.out.println("这是一只模型鸭");
	}
	
	
}
