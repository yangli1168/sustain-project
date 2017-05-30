package com.xinqushi.designrule.factory;

public class FactoryDemo {
	
	public static void main(String[] args) {
		//安卓
		Factory factory = new AndroidFactory();
		showPhone(factory);
		
		//苹果
		factory = new IPhoneFactory();
		showPhone(factory);
		
		//使用反射方式传入工厂名[包、类名]
		try {
			Class<?> c = Class.forName("com.xinqushi.designrule.factory.WindowsPhoneFactory");
			factory = (Factory) c.newInstance();
			showPhone(factory);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	private static void showPhone(Factory factory) {
		Product product = factory.createProduct();
		System.out.println(product);
	}
	
	
}
