package com.xinqushi.designrule;

/**
 * @author yangli
 * @date 2017年5月26日 - 下午10:40:25
 * @Description: 鸭子的超类
 */
public abstract class Duck {

	/** 模拟鸭子叫[接口方式] */
	QuackBehavior quackBehavior;

	/** 模拟鸭子飞行[接口方式] */
	FlyBehavior flyBehavior;

	/** 模拟鸭子叫[具体实现类型] */
	void performQuack() {
		quackBehavior.quack();
	}

	/** 模拟鸭子飞行[具体实现类型] */
	void performFly() {
		flyBehavior.fly();;
	}

	/** 设置飞行行为*/
	void setFlyBehavior(FlyBehavior fb){
		flyBehavior = fb;
	}
	
	/** 设置叫声行为*/
	void setQuackBehavior(QuackBehavior qb){
		quackBehavior = qb;
	}
	
	/** 模拟鸭子游泳 */
	public void swim() {
		System.out.println("所有鸭子都会游泳，甚至是鸭仔");
	}

	/** 模拟鸭子外观 */
	public void display() {}

}
