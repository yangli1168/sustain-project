package com.xinqushi.designpattern.mediator;
/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午9:17:30
 * @Description 中介者模式demo
 * <p>定义：用一个中介对象来封装一系列的对象交互；使得各对象之间不需要
 * 显示的相互引用，从而使其耦合松散，且可独立的改变它们之间的交互</p>
 * <p>使用：一般用于一组对象以定义好但是复杂的方式进行通信的场合；
 * 以及想定制一个分布在多个类中的行为，而又不想生成太多的子类的场合</p>
 * <p>优点：中介者减少了对象之间的耦合，使得可以独立的改版和复用中介者
 * 类和独立对象</p>
 */
public class MediatorDemo {
	
	public static void main(String[] args) {
		//初始化中介者
		ConcreteMediator mediator = new ConcreteMediator();
		
		//让两个具体同事认识中介者
		ConcreteColleagueA ca = new ConcreteColleagueA(mediator);
		ConcreteColleagueB cb = new ConcreteColleagueB(mediator);
		mediator.setCa(ca);
		mediator.setCb(cb);
		
		//具体同事类对象发送的信息都通过中介者转发
		ca.send("吃过饭了吗？");
		cb.send("没有呢，你打算请客？");
	}
}
