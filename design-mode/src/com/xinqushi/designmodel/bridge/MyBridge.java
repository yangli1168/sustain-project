package com.xinqushi.designmodel.bridge;

public class MyBridge extends Bridge {

	@Override
	public void method() {
		Sourceable source = getSource();
		source.method();
	}

}
