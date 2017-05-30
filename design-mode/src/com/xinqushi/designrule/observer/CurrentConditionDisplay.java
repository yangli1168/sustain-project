package com.xinqushi.designrule.observer;
/**
 * 布告板具体实现类-当前观测值类
 * @author yangli
 */
public class CurrentConditionDisplay implements DisplayElement, Observer{
	
	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	@Override
	public void display() {
		// 显示当前观测值
		System.out.println("当前观测值：" + temperature +
				"度，湿度" + humidity + "%");
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}

}
