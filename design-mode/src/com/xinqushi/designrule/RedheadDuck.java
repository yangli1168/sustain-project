package com.xinqushi.designrule;

/**
 * 
 * @author yangli
 * @date 2017年5月26日 - 下午10:47:46
 * @Description: 红头鸭
 */
public class RedheadDuck extends Duck{
	
	public RedheadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Squack();
	}
	
	@Override
	public void display() {
		System.out.println("这是一只红头鸭");
	}
	
	
}
