package com.xinqushi.designpattern.factory.caculate;
/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午8:38:12
 * @Description: 运算工厂类
 */
public class OperationFactory {
	
	public static Operation createOperation(String operate){
		Operation operation = null;
		
		switch (operate) {
		case "+":
			operation = new OperationAdd();
			break;
		case "-":
			operation = new OperationDel();
			break;
		case "*":
			operation = new OperationMul();
			break;
		case "/":
			operation = new OperationDiv();
			break;
		}
		
		return operation;
	}
}
