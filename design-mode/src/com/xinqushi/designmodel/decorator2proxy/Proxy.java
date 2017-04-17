package com.demo.mode.decorator2proxy;

public class Proxy implements Sourceable {
	private Sourceable source;

	public Proxy(Sourceable source) {
		super();
		this.source = source;
	}

	@Override
	public void method() {
		before();
		source.method();
		atfer();
	}

	private void atfer() {
		System.out.println("after proxy!");
	}

	private void before() {
		System.out.println("before proxy!");
	}

}
