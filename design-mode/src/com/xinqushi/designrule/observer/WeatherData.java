package com.xinqushi.designrule.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体的主题实现类-天气信息
 * @author yangli
 */
public class WeatherData implements Subject{
	
	/** 温度*/
	private float temperature;
	
	/** 湿度*/
	private float humidity;
	
	/** 气压*/
	private float pressure;
	
	@SuppressWarnings("rawtypes")
	private List<Observer> observers;
	
	public WeatherData() {
		observers = new ArrayList<Observer>();
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(temperature, humidity, pressure);
		}
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		measurementsChanged();
	}
	
	public void measurementsChanged(){
		this.notifyObservers();
	}
	
}
