package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:41:33
 * @Description: 服饰超类
 */
public abstract class Finery extends Person{

	Person person;
	
	public void decorator(Person person) {
		this.person = person;
	}
	
	@Override
	public void show() {
		if (null != person) {
			person.show();
		}
	}
	
}
