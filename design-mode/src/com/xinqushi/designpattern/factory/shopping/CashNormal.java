package com.xinqushi.designpattern.factory.shopping;
/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午9:55:20
 * @Description: 正常收费实现类
 */
public class CashNormal extends CashSuper{
	
	/** 正常收费时按原价返回*/
	@Override
	public double acceptCash(double money) {
		return money;
	}
	
}
