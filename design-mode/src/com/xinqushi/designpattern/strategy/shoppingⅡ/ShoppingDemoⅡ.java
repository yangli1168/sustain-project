package com.xinqushi.designpattern.strategy.shoppingⅡ;

public class ShoppingDemoⅡ {
	
	public static void main(String[] args) {
		
		showPrice("满300返100", 800);
		showPrice("打8折", 800);
		showPrice("正常收费", 800);
	}

	private static void showPrice(String type, double money) {
		CashContext cs = new CashContext(type);
		double result = cs.getResult(money);
		System.out.println("策略模式：总价[ " + money + " ]的商品，折算后需付款：" + result + " 元");
	}
}
