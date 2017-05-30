package com.xinqushi.designrule.factory;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午9:37:35
 * @Description: 苹果手机产品
 */
public class WindowsPhone implements Product{
	
	private String brand;
	private Integer price;
	
	public WindowsPhone() {
		this.brand = "微软";
		this.price = 5000;
	}
	
	public WindowsPhone(Integer price) {
		this.brand = "微软";
		this.price = price;
	}

	@Override
	public String toString() {
		return "WindowsPhone [brand=" + brand + ", price=" + price + "]";
	}

}
