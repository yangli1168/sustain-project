package com.xinqushi.designrule.observerpattern;
/**
 * 布告板具体实现类-第三方实现类[用于扩展]
 * @author yangli
 */
public class ThirdPartyDisplay implements DisplayElement, Observer{

	@Override
	public void display() {
		//显示基于观测值的其它内容
		
	}


	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Auto-generated method stub
		
	}

}
