package com.xinqushi.designmodel.build;

import java.util.List;
/**
 * 建造者模式将很多功能集成到一个类里，这个类可以创造出比较复杂的东西。
 * 所以与工程模式的区别就是：工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象，多个部分。
 * 因此，是选择工厂模式还是建造者模式，依实际情况而定。
 * @author Administrator
 *
 */
public class BuilderTest {
	public static void main(String[] args) {
		//建造邮件
		List<Sender> mailSenderList = Builder.produceMailSender(3);
		printBuilderList(mailSenderList);
		
		//建造短信
		List<Sender> smsSenderList = Builder.produceSmsSender(3);
		printBuilderList(smsSenderList);
		
	}

	private static void printBuilderList(List<Sender> senderList) {
		System.out.println("******print start******");
		for (int i=0; i<senderList.size(); i++) {
			Sender sender = senderList.get(i);
			System.out.print(i + 1 + " --> ");
			sender.send();
		}
		System.out.println("******print end  ******");
		System.out.println();
	}
}
