package com.xinqushi.designrule;
/**
 * 
 * @author yangli
 * @date 2017年5月26日 - 下午11:23:59
 * @Description:
 */
public class MuteQuack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("不会叫");
	}

}
