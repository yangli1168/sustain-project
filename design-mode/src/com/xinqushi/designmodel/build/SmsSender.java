package com.demo.mode.build;

public class SmsSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is sms sender");
	}

}