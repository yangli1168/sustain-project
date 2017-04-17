package com.demo.mode.factory;

public class SendFactoryStatic {

	public static Sender produceMail() {
		return new MailSender();
	}

	public static Sender produceSms() {
		return new SmsSender();
	}

}
