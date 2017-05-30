package com.xinqushi.designrule;
/**
 * 设计原则1：将会变化的部分取出封装起来，让其余部分不受影响 
 * 设计原则2：针对接口编程，而不是针对实现编程
 * @author yangli
 * @date 2017年5月26日 - 下午11:52:52
 * @Description:
 */
public class MiniDuckSimulator {
	
	public static void main(String[] args) {
		
		Duck duck = new MallardDuck();
		
		System.out.println("*****展示MallardDuck****");
		show(duck);
		
		duck = new RedheadDuck();
		System.out.println("*****展示RedheadDuck****");
		show(duck);
		
		duck = new RubberDuck();
		System.out.println("*****展示RubberDuck****");
		show(duck);
		
		duck = new ModuleDuck();
		System.out.println("*****展示RubberDuck【改变行为之前】****");
		show(duck);
		duck.setFlyBehavior(new FlyRocketPowered());
		duck.setQuackBehavior(new Quack());
		System.out.println("*****展示RubberDuck【改变行为之后】****");
		show(duck);
		
	}

	private static void show(Duck duck) {
		duck.display();
		duck.swim();
		duck.performFly();
		duck.performQuack();
	}
}
