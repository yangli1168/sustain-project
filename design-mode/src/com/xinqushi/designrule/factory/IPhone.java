package com.xinqushi.designrule.factory;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午9:37:35
 * @Description: 苹果手机产品
 */
public class IPhone implements Product{
	
	private String brand;
	private Integer price;
	
	public IPhone() {
		this.brand = "苹果";
		this.price = 6000;
	}
	
	public IPhone(Integer price) {
		this.brand = "苹果";
		this.price = price;
	}

	@Override
	public String toString() {
		return "IPhone [brand=" + brand + ", price=" + price + "]";
	}
	
}
