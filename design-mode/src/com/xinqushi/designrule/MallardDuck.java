package com.xinqushi.designrule;

/**
 * 
 * @author yangli
 * @date 2017年5月26日 - 下午10:47:46
 * @Description: 绿头鸭
 */
public class MallardDuck extends Duck{
	
	public MallardDuck() {
		quackBehavior = new Quack();
		flyBehavior = new FlyWithWings();
	}
	
	@Override
	public void display() {
		System.out.println("这是一只绿头鸭");
	}
	
}
