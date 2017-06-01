package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:47:29
 * @Description: 跨裤装饰类
 */
public class BigTrouser extends Finery{

	@Override
	public void show() {
		System.out.print(" 跨裤 ");
		super.show();
	}

}
