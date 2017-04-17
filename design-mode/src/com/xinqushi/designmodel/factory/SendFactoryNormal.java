package com.demo.mode.factory;

public class SendFactoryNormal {
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("please use correct type");
			return null;
		}
	}
}
