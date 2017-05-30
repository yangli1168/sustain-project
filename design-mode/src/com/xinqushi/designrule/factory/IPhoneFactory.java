package com.xinqushi.designrule.factory;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午9:36:35
 * @Description: 苹果手机生产工厂
 */
public class IPhoneFactory implements Factory{

	@Override
	public Product createProduct() {
		return new IPhone();
	}
	
}
