package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:47:29
 * @Description: 吊带裙装饰类
 */
public class BracesSkirt extends Finery{

	@Override
	public void show() {
		System.out.print(" 吊带裙 ");
		super.show();
	}

}
