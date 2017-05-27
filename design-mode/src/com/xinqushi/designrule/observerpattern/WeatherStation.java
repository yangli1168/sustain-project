package com.xinqushi.designrule.observerpattern;
/**
 * 气象站类
 * @author yangli
 */
public class WeatherStation {
	
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionDisplay conditionDisplay = new CurrentConditionDisplay(weatherData);
//		StatisticsDisplay statisticsDisplay = new sta
		
		weatherData.setMeasurements(80, 60, 30);
	}
}
