package com.demo.mode.factory;

public class SendFactoryMany {

	public Sender produceMail() {
		return new MailSender();
	}

	public Sender produceSms() {
		return new SmsSender();
	}

}
