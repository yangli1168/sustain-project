package com.xinqushi.designrule.observerpattern;
/**
 * 观察者模式-观察者接口
 * @author yangli
 */
public interface Observer {
	
	/** 更新操作
	 * @param temp 温度
	 * @param humidity 湿度
	 * @param pressure 气压
	 */
	void update(float temp, float humidity, float pressure);
}
