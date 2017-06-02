package com.xinqushi.designpattern.builder;
/**
 * 指挥者类
 * @author yangli
 */
public class PersonDirector {
	
	private PersonBuilder pb;
	
	public PersonDirector(PersonBuilder pb) {
		this.pb = pb;
	}
	
	public void createPerson(){
		pb.buildHead();
		pb.buildBody();
		pb.buildArmLeft();
		pb.buildArmRight();
		pb.buildLegLeft();
		pb.buildLegRight();
	}
}
