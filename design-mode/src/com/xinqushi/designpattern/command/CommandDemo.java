package com.xinqushi.designpattern.command;
/**
 * @author yangli
 * @date 2017年6月5日 - 下午8:03:11
 * @Description 命令模式demo
 * <p>定义： 将请求封装为一个对象，使得可用不同的请求对客户进行参数化；
 * 对请求排队火记录请求日志，以及支持可以撤销的操作</p>
 * <p>使用：在真正需要如撤销、恢复操作等功能时，可将原来的代码
 * 重构为命令模式</p>
 * <p>优点：把请求一个操作的对象和知道怎么执行一个操作的对象分割开</p>
 */
public class CommandDemo {
	
	public static void main(String[] args) {
		
		//开店前的准备
		Barbecuer barbecuer = new Barbecuer();
		Command bakeMuttonCommand1 = new BakeMuttonCommand(barbecuer);
		Command bakeMuttonCommand2 = new BakeMuttonCommand(barbecuer);
		Command bakeChickenWingCommand1 = new BakeChickenWingCommand(barbecuer);
		Waiter waiter = new Waiter();
		
		//开门营业,顾客点菜
		waiter.setOrder(bakeMuttonCommand1);
		waiter.setOrder(bakeMuttonCommand2);
		waiter.setOrder(bakeChickenWingCommand1);
		
		//点菜完成，通知厨房
		waiter.notifyBarbecuer();
		
	}
}
