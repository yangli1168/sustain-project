package com.xinqushi.designrule.factory;
/**
 * 
 * @author yangli
 * @date 2017年5月30日 - 下午9:36:35
 * @Description: 安卓生产工厂
 */
public class AndroidFactory implements Factory{

	@Override
	public Product createProduct() {
		return new Android();
	}
	
}
