package com.demo.mode.strategy;

public class StrategyTest {
	public static void main(String[] args) {
		String exp = "2+8";
		ICalculator cal = new Plus();
		System.out.println("加法 -->");
		System.out.println(exp + " = " + cal.calculate(exp));
		System.out.println();
		
		exp = "2-8";
		cal = new Minus();
		System.out.println("减法 -->");
		System.out.println(exp + " = " + cal.calculate(exp));
		System.out.println();
		
		exp = "2*8";
		cal = new Multiply();
		System.out.println("乘法 -->");
		System.out.println(exp + " = " + cal.calculate(exp));
		System.out.println();
	}
}
