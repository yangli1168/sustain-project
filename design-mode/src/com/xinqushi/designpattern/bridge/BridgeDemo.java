package com.xinqushi.designpattern.bridge;
/**
 * 桥接模式demo
 * </p>定义：将抽象部分与它的实现部分分离，使它们都可以独立变化；
 * 实现指的是抽象类和它的派生类用来实现自己的对象</p>
 * </p>使用：实现系统可能有多角度分类，每一种分类都有可能变化，
 * 此时把这种多角度分离出来让它们独立变化</p>
 * </p></p>
 * @author yangli
 */
public class BridgeDemo {
	
	public static void main(String[] args) {
		
		Abstracion ab = new RefindAbstraction();
		
		ab.setImplementor(new ConcreteImplementorA());
		ab.operation();
		
		ab.setImplementor(new ConcreteImplementorB());
		ab.operation();
		
	}
}
