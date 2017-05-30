package com.xinqushi.designrule;

/**
 * 
 * @author yangli
 * @date 2017年5月26日 - 下午11:21:27
 * @Description:
 */
public class FlyNoWay implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("无法飞行");
	}
	
}
