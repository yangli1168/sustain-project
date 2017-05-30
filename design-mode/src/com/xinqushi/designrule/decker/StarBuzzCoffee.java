package com.xinqushi.designrule.decker;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午11:11:58
 * @Description: 装饰者模式测试类
 */
public class StarBuzzCoffee {
	
	public static void main(String[] args) {
		//不含调料
		Beverage beverage = new Espresso();
		show(beverage);
		
		//混合[加调料]
		beverage = new HouseBlend();
		beverage = new Mocha(beverage); //加mocha
		beverage = new Soy(beverage); //加soy
		beverage = new Whip(beverage); //加whip
		show(beverage);
	}

	private static void show(Beverage beverage) {
		System.out.println(beverage.getDescription() + " -> $" + beverage.cost());
	}
}
