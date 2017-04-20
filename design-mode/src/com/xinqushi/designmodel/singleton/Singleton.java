package com.xinqushi.designmodel.singleton;

public class Singleton {

	/* 私有构造方法，防止被实例化 */
	private Singleton() {
	}
	
	/* 私有一个空实例，用于延迟加载*/
	private static Singleton instance = null;
	
	/* 控制多线程下唯一性*/
	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new Singleton();
		}
	}

	/* 获取实例 */
	public static Singleton getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}
}