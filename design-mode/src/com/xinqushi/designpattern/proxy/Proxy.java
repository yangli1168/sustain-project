package com.xinqushi.designpattern.proxy;
/**
 * 
 * @author yangli
 * @date 2017年6月1日 - 下午9:41:29
 * @Description: 代理类
 */
public class Proxy extends Subject{

	private RealSubject realSubject;
	
	@Override
	public void request() {
		if (null == realSubject) {
			realSubject = new RealSubject();
		}
		
		realSubject.request();
	}

}
