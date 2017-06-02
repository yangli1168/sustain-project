package com.xinqushi.designpattern.factory.shopping;

public class ShppingDemo {
	
	public static void main(String[] args) {
		
		showPrice("满300减100", 800);
		showPrice("打8折", 800);
		showPrice("正常收费", 800);
		
	}

	private static void showPrice(String condition, double money) {
		CashSuper cs = CashFactory.createCashAccept(condition);
		double acceptCash = cs.acceptCash(money);
		System.out.println("工厂模式：" + condition + " 下，总价" + money + "的商品应付：" + acceptCash);
	}
}
