package com.demo.mode.decorator2proxy;
/**
 * 装饰模式就是给一个对象增加一些新的功能，而且是动态的，要求装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例
 * @author Administrator
 *
 */
public class Decorator2ProxyTest {
	public static void main(String[] args) {
		System.out.println("******装饰模式******");
		System.out.println("被装饰类方法 -->");
		Sourceable source = new Source();
		source.method();
		
		System.out.println();
		System.out.println("装饰类方法 -->");
		Sourceable decorator = new Decorator(source);
		decorator.method();
		System.out.println("******装饰模式******");
		System.out.println();
		
		System.out.println("******代理模式******");
		Sourceable proxy = new Proxy(new Source());
		proxy.method();
		System.out.println("******代理模式******");
		
		
	}
}
