package com.xinqushi.designrule.decker;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午11:05:57
 * @Description: 调料实现类
 */
public class Whip extends CondimentDecorator{
	
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.40;
	}

}
