package com.xinqushi.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式：对象结构类
 * @author yangli
 */
public class ObjectStructure {
	
	private List<Person> elements = new ArrayList<>();
	
	/** 增加*/
	public void add(Person element){
		elements.add(element);
	}
	
	/** 移除*/
	public void del(Person element){
		elements.remove(element);
	}
	
	/** 查看显示*/
	public void display(Action visitor){
		for (Person person : elements) {
			person.accept(visitor);
		}
	}
}
