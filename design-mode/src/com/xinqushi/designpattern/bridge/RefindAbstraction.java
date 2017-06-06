package com.xinqushi.designpattern.bridge;
/**
 * 
 * @author yangli
 */
public class RefindAbstraction extends Abstracion{

	@Override
	public void operation() {
		this.implementor.operation();
	}
	
}
