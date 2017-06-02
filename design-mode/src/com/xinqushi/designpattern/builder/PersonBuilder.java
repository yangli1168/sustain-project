package com.xinqushi.designpattern.builder;

import java.awt.Graphics;

/**
 * 抽象超类
 * @author yangli
 */
public abstract class PersonBuilder {
	
	Graphics g;
	
	public PersonBuilder(Graphics g) {
		this.g = g;
	}
	
	//构造身体方法
	public abstract void buildHead();
	public abstract void buildBody();
	public abstract void buildArmLeft();
	public abstract void buildArmRight();
	public abstract void buildLegLeft();
	public abstract void buildLegRight();
	
}
