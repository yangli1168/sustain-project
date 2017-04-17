package com.demo.mode.factory;

public class SendSmsFactory implements Provider{

	@Override
	public Sender produce() {
		return new SmsSender();
	}

}
