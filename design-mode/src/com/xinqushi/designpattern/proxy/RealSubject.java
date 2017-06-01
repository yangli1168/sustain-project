package com.xinqushi.designpattern.proxy;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午9:40:15
 * @Description: 具体实现类
 */
public class RealSubject extends Subject{

	@Override
	public void request() {
		System.out.println("真实的请求");
	}
	
}
