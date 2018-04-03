package com.xinqushi.designpattern.factory.shopping;

public class ShppingDemo {
	
	public static void main(String[] args) {
		
		showPrice("满300减100", 800);
		showPrice("打8折", 800);
		showPrice("正常收费", 800);
	    
	    showPriceⅡ(CashType.CashNormal, 600);
	    showPriceⅡ(CashType.CashRebate, 600);
	    showPriceⅡ(CashType.CashReturn, 600);
		
	}

	private static void showPrice(String condition, double money) {
		CashSuper cs = CashFactory.createCashAccept(condition);
		double acceptCash = cs.acceptCash(money);
		System.out.println("工厂模式：" + condition + " 下，总价" + money + "的商品应付：" + acceptCash);
	}
	
	private static void showPriceⅡ(CashType cashType, double money){
	  //使用反射方式传入工厂名[包、类名]
        try {
            Class<?> c = Class.forName("com.xinqushi.designpattern.factory.shopping." + cashType);
            CashSuper cashSuper = (CashSuper) c.newInstance();
            
            double acceptCash = cashSuper.acceptCash(money);
            System.out.println("工厂模式：" + cashType.name() + " 下，总价" + money + "的商品应付：" + acceptCash);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
	}
	
}
