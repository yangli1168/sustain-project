package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:47:29
 * @Description: T恤装饰类
 */
public class TShirts extends Finery{

	@Override
	public void show() {
		System.out.print(" T恤 ");
		super.show();
	}

}
