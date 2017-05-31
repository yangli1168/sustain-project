package com.xinqushi.designpattern.factory.caculate;

public class OperationDiv extends Operation{

	@Override
	public double getResult() {
		double result = 0;
		if (this.getNumberB() == 0) {
			try {
				throw new Exception("除数不能为0");
			} catch (Exception e) {
				System.err.println("错误：" + e.getMessage());
			}
		}
		result = this.getNumberA() / this.getNumberB();
		
		return result;
	}
	
}
