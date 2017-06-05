package com.xinqushi.designpattern.observer;

public class ObserverDemo {
	
	public static void main(String[] args) {
		
		//前台小姐
		Subject mengli = new Secretary();
		
		//看股票的同事
		StockOberserver tianhe = new StockOberserver("天河", mengli);
		StockOberserver ziying = new StockOberserver("紫英", mengli);
		
		//看NBA的同事
		NBAOberserver linsha = new NBAOberserver("菱纱", mengli);
		
		//前台记下看股票的同事
		mengli.addObserver(tianhe);
		mengli.addObserver(ziying);
		//
		mengli.addObserver(linsha);
		
		//发现老板回来
		mengli.setAction("老板回来了!");
		
		//通知观察者同事
		mengli.notifyObserver();
	}
}
