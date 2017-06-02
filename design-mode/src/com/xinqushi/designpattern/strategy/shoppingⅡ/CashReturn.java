package com.xinqushi.designpattern.strategy.shoppingⅡ;
/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午9:55:20
 * @Description: 返利收费实现类
 */
public class CashReturn extends CashSuper{
	
	/** 返利门槛条件*/
	private double moneyCondition = 0.0d;
	
	/** 返利数目*/
	private double moneyReturn = 0.0d;
	
	public CashReturn(String moneyCondition, String moneyReturn) {
		this.moneyCondition = Double.parseDouble(moneyCondition);
		this.moneyReturn = Double.parseDouble(moneyReturn);
	}
	
	/** 打折收费时按折扣率返回*/
	@Override
	public double acceptCash(double money) {
		double result = money;
		
		if (money >= moneyCondition) {
			result = money - Math.floor(money / moneyCondition) * moneyReturn;
		}
		
		return result;
	}
	
}
