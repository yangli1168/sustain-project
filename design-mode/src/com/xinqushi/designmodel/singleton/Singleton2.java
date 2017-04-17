package com.demo.mode.singleton;

public class Singleton2 {
	/* 私有构造方法，防止被实例化 */
	private Singleton2() {
	}

	/* 获取实例 */
	public static Singleton2 getInstance() {
		return SingletonFactory.instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}

	/* 此处使用一个内部类来维护单例 */
	private static class SingletonFactory {
		private static Singleton2 instance = new Singleton2();
	}
}
