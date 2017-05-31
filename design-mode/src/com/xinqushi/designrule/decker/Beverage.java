package com.xinqushi.designrule.decker;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午10:50:28
 * @Description: 饮料超类
 */
public abstract class Beverage {
	
	String description = "超类饮料";
	
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}
