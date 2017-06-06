package com.xinqushi.designpattern.bridge;
/**
 * 
 * @author yangli
 */
public class Abstracion {
	
	Implementor implementor;

	public void setImplementor(Implementor implementor) {
		this.implementor = implementor;
	}
	
	public void operation(){
		implementor.operation();
	}
}
