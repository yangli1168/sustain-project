package com.xinqushi.designpattern.strategy.shoppingⅡ;
/**
 * 上下文，维护付款方式
 * @author yangli
 */
public class CashContext {
	
	private CashSuper cashSuper;
	
	/** 构造方法1*/
	public CashContext(CashSuper cashSuper) {
		this.cashSuper = cashSuper;
	}
	
	/** 构造方法2:与策略模式结合*/
	public CashContext(String type) {
		switch (type) {
		case "正常收费":
			cashSuper = new CashNormal();
			break;
		case "满300返100":
			cashSuper = new CashReturn("300", "100");
			break;
		case "打8折":
			cashSuper = new CashRebate("0.8");
			break;
		}
	}
	
	/** 根据付款方式不同获得不同结果*/
	public double getResult(double money){
		return cashSuper.acceptCash(money);
	}
}
