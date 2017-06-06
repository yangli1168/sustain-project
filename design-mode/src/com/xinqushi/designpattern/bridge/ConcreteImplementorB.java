package com.xinqushi.designpattern.bridge;
/**
 * 具体实现类A
 * @author yangli
 */
public class ConcreteImplementorB extends Implementor{

	@Override
	public void operation() {
		System.out.println("执行具体实现类B的方法");
	}
	
}
