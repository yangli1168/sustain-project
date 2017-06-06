package com.xinqushi.designpattern.visitor;
/**
 * 访问者模式demo
 * <p>定义：表示一个作用于某对象结构中的各元素的操作；使你可在不改变各
 * 元素类的前提下定义作用于这些元素的新操作</p>
 * <p>使用：适用于数据结构相对稳定的系统</p>
 * <p>优点：增加新的操作很容易</p>
 * @author yangli
 */
public class VisitorDemo {
	
	public static void main(String[] args) {
		
		ObjectStructure o = new ObjectStructure();
		o.add(new Man());
		o.add(new Woman());
		
		//成功时的反应
		ActionSuccess v1 = new ActionSuccess();
		o.display(v1);
		
		//失败时的反应
		ActionFailing v2 = new ActionFailing();
		o.display(v2);
		
		//恋爱时的反应
		ActionLoving v3 = new ActionLoving();
		o.display(v3);
		
		//恋爱时的反应
		ActionMarriage v4 = new ActionMarriage();
		o.display(v4);
		
	}
}
