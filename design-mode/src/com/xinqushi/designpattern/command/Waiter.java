package com.xinqushi.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yangli
 * @date 2017年6月5日 - 下午8:00:52
 * @Description: 服务员类
 */
public class Waiter {
	
	private Command command;
	private List<Command> orders = new ArrayList<Command>();
	
	/** 设置订单*/
	public void setOrder(Command command){
		//判断客户的请求
		if (command.toString().equals("烤鸡翅")) {
			System.out.println("服务员： 鸡翅没有了，请点别的烧烤！");
		} else {
			orders.add(command);
			System.out.println("增加订单：" + command.toString() +
					"；时间： " + System.currentTimeMillis());
		}
	}
	
	/** 取消订单*/
	public void cancelOrder(Command command){
		orders.remove(command);
		System.out.println("取消订单：" + command.toString() +
				"；时间： " + System.currentTimeMillis());
	}
	
	/** 通知执行*/
	public void notifyBarbecuer(){
		for (Command command : orders) {
			command.excuteCommand();
		}
	}
}
