package com.xinqushi.designpattern.factory.caculate;

public class OperationDel extends Operation{

	@Override
	public double getResult() {
		return this.getNumberA() - this.getNumberB();
	}
	
}
