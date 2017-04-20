package com.xinqushi.designmodel.build;

import java.util.ArrayList;
import java.util.List;

public class Builder {

	private static List<Sender> list = new ArrayList<Sender>();

	public static List<Sender> produceMailSender(int count) {
		listCheck();
		for (int i = 0; i < count; i++) {
			list.add(new MailSender());
		}
		return list;
	}

	public static List<Sender> produceSmsSender(int count) {
		listCheck();
		for (int i = 0; i < count; i++) {
			list.add(new SmsSender());
		}
		return list;
	}

	private static void listCheck() {
		if (null != list) {
			list.removeAll(list);
		}
	}
}
