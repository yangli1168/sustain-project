package com.xinqushi.designpattern.decorator;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午8:47:29
 * @Description: 高跟鞋装饰类
 */
public class HighHeeledShoes extends Finery{

	@Override
	public void show() {
		System.out.print(" 高跟鞋 ");
		super.show();
	}

}
