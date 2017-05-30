package com.xinqushi.designrule.factory;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午9:37:35
 * @Description: 安卓手机产品
 */
public class Android implements Product{
	
	private String brand;
	private Integer price;
	
	public Android() {
		this.brand = "安卓";
		this.price = 3000;
	}
	
	public Android(Integer price) {
		this.brand = "安卓";
		this.price = price;
	}
	

	@Override
	public String toString() {
		return "Android [brand=" + brand + ", price=" + price + "]";
	}
	
}
