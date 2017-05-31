package com.xinqushi.designpattern.factory.caculate;
/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午7:43:29
 * @Description: 运算类
 */
public class Operation {
	
	private double numberA = 0;
	private double numberB = 0;
	
	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}
	
	public double getResult(){
		return 0;
	}
	

	public double getNumberA() {
		return numberA;
	}

	public double getNumberB() {
		return numberB;
	}

	/**
	 * 
	 * @param numberA
	 * @param numberB
	 * @param operate 运算方式
	 * @return
	 */
	public static double getResult(double numberA, double numberB, String operate){
		double result = 0;
		switch (operate) {
		case "+":
			result = numberA + numberB;
			break;
		case "-":
			result = numberA - numberB;
			break;
		case "*":
			result = numberA * numberB;
			break;
		case "/":
			result = numberA / numberB;
			break;
		}
		return result;
	}
	
	/**
	 * 
	 * @param express 表达式
	 * @return
	 */
	public static double getResult(String express){
		String[] str = express.split("");
		double numberA = Double.parseDouble(str[0]);
		String operate = str[2];
		double numberB = Double.parseDouble(str[4]);
		
		return getResult(numberA, numberB, operate);
	}
}
