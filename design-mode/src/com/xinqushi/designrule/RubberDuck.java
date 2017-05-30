package com.xinqushi.designrule;

/**
 * 
 * @author yangli
 * @date 2017年5月26日 - 下午10:47:46
 * @Description: 橡皮鸭
 */
public class RubberDuck extends Duck{
	
	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new MuteQuack();
	}
	
	@Override
	public void display() {
		System.out.println("这是一只橡皮鸭");
	}
	
	
}
