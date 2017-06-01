package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:52:45
 * @Description:
 */
public class DecoratorDemo {
	
	public static void main(String[] args) {
		
		Person person = new Person("天河");
		
		TShirts t = new TShirts();
		BigTrouser b = new BigTrouser();
		//装饰过程
		t.decorator(person);
		b.decorator(t);
		//展示
		b.show();
		
		person = new Person("梦璃");
		BracesSkirt s = new BracesSkirt();
		HighHeeledShoes h = new HighHeeledShoes();
		//装饰
		s.decorator(person);
		h.decorator(s);
		//展示
		h.show();
	}
}
