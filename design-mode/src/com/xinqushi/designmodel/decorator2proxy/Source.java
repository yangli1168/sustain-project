package com.demo.mode.decorator2proxy;

public class Source implements Sourceable {

	@Override
	public void method() {
		System.out.println("this is original method!");
	}

}
