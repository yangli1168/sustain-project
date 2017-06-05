package com.xinqushi.designpattern.observer;
/**
 * 
 * @author yangli
 * @date 2017年6月4日 - 下午6:25:25
 * @Description: 看股票的观察者
 */
public class StockOberserver extends Observer{


	public StockOberserver(String name, Subject subject) {
		super(name, subject);
	}

	@Override
	public void update() {
		System.out.println(subject.getAction() + name + 
				"！ 关闭股票行情，继续工作！");
	}

}
