package com.xinqushi.designpattern.flyweight;
/**
 * 享元模式demo
 * <p>定义：运用共享计数有效的支持大量细粒度的对象</p>
 * <p>使用：①大量相似的对象造成很大的存储开销时；②
 * 对象的大多数状态可以使用外部状态，若删除对象的外部状态，可以用相对较少的
 * 共享对象来取代很多组对象时</p>
 * <p>优点：可以避免大量非常相似的类的开销；将类的参数移到实例外，在方法调用
 * 时传递进去，就可以通过共享大幅度减少需要实例化的类的数量</p>
 * @author yangli
 */
public class FlyWeightDemo {
	
	public static void main(String[] args) {
		
		WebSiteFactory f = new WebSiteFactory();
		
		//实例化产品展示的网站对象
		WebSite fx = f.getWebSiteCategory("产品展示");
		fx.use(new User("天河"));
		
		//共享上方生成的对象，不再实例化
		WebSite fy = f.getWebSiteCategory("产品展示");
		fy.use(new User("紫英"));
		WebSite fz = f.getWebSiteCategory("产品展示");
		fz.use(new User("梦璃"));
		
		//实例化博客的网站对象
		WebSite fl = f.getWebSiteCategory("博客");
		fl.use(new User("菱纱"));
		
		//共享上方生成的对象，不再实例化
		WebSite fm = f.getWebSiteCategory("博客");
		fm.use(new User("玄霄"));
		WebSite fn = f.getWebSiteCategory("博客");
		fn.use(new User("长老"));
		
		System.out.println("网站分类总数为：" + f.getWebSiteCount());
		
	}
}
