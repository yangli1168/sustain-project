package com.xinqushi.designmodel.factory;

public class FactoryTest {

	private static Sender sender;

	public static void main(String[] args) {

		printNormalFactory();

		printManyFactory();

		printStaticMethod();

		printAbstractFactory();
	}

	/**
	 * 抽象工厂模式，创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码 拓展性较好
	 */
	private static void printAbstractFactory() {

		// 抽象工厂模式
		System.out.println("******抽象工厂模式******");
		Provider factoryProvider = new SendMailFactory();
		sender = factoryProvider.produce();
		sender.send();

		factoryProvider = new SendSmsFactory();
		sender = factoryProvider.produce();
		sender.send();
		System.out.println("******抽象工厂模式******");
	}

	/**
	 * 将多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
	 */
	private static void printStaticMethod() {

		// 静态工厂模式
		System.out.println("******静态工厂模式******");
		// SendFactoryStatic staticFactory = new SendFactoryStatic();
		// //由于是静态方法无须初始化

		sender = SendFactoryStatic.produceMail();
		sender.send();

		sender = SendFactoryStatic.produceSms();
		sender.send();
		System.out.println("******静态工厂模式******");
		System.out.println();
	}

	/**
	 * 多个工厂方法模式是提供多个工厂方法，分别创建对象，改进普通模式。
	 * 
	 */
	private static void printManyFactory() {

		// 多个工厂模式
		System.out.println("******多个工厂模式******");
		SendFactoryMany manyFactory = new SendFactoryMany();

		sender = manyFactory.produceMail();
		sender.send();

		sender = manyFactory.produceSms();
		sender.send();
		System.out.println("******多个工厂模式******");
		System.out.println();
	}

	/**
	 * 普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象 本例中normalFactory.produce("")产生了null空对象
	 */
	private static void printNormalFactory() {
		// 普通工厂模式
		System.out.println("******普通工厂模式******");
		SendFactoryNormal normalFactory = new SendFactoryNormal();

		sender = normalFactory.produce("mail");
		sender.send();

		sender = normalFactory.produce("sms");
		sender.send();

		try {
			sender = normalFactory.produce("");
			sender.send();
		} catch (Exception e) {
			System.out.println("发生异常如下：" + e);

		}
		System.out.println("******普通工厂模式******");
		System.out.println();
	}

}
