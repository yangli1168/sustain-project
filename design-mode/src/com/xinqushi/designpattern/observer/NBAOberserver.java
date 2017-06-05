package com.xinqushi.designpattern.observer;
/**
 * 
 * @author yangli
 * @date 2017年6月4日 - 下午6:25:25
 * @Description: 看股票的观察者
 */
public class NBAOberserver extends Observer{


	public NBAOberserver(String name, Subject subject) {
		super(name, subject);
	}

	@Override
	public void update() {
		System.out.println(subject.getAction() + name + 
				"！ 关闭NBA直播，继续工作！");
	}

}
