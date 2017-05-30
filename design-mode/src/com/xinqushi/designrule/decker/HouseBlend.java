package com.xinqushi.designrule.decker;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:57:03
 * @Description: 混合饮料实现类
 */
public class HouseBlend extends Beverage{
	
	public HouseBlend() {
		this.description = "House Blend Coffee";
	}
	
	@Override
	public double cost() {
		return 0.99;
	}

}
