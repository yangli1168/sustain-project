package com.xinqushi.designmodel.visitor;

public interface Subject {
	public void accept(Visitor visitor);
	
	public String getSubject();
}
