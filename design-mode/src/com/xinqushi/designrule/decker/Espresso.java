package com.xinqushi.designrule.decker;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:57:03
 * @Description: 浓缩饮料实现类
 */
public class Espresso extends Beverage{
	
	public Espresso() {
		this.description = "Espresso";
	}
	
	@Override
	public double cost() {
		return 1.99;
	}

}
