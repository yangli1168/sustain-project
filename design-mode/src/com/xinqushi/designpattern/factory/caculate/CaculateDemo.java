package com.xinqushi.designpattern.factory.caculate;

import java.io.Console;
import java.util.Scanner;

/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午7:47:51
 * @Description: 模拟计算器
 */
public class CaculateDemo {
	
	public static void main(String[] args) {
		
//		caculate();
		
//		caculateByExpress();
		
		Operation op = OperationFactory.createOperation("/");
		op.setNumberA(8);
		op.setNumberB(0);
		double result = op.getResult();
		System.out.println("result = " + result);
	}

	private static void caculateByExpress() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("请输入算术表达式：");
		String express = scanner.nextLine();
		
		double result = Operation.getResult(express);
		System.out.println(express + " = " + result);
	}

	private static void caculate() {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.println("请输入数字A：");
			double numberA = scanner.nextDouble();
			System.out.println("请选择运算符号[+、-、*、/]：");
			String operate = scanner.next();
			System.out.println("请输入数字B：");
			double numberB = scanner.nextDouble();
			
			double result = Operation.getResult(numberA, numberB, operate);
			System.out.println(numberA + " " + operate + " " + numberB + " = " + result);
			
			
		} catch (Exception e) {
			System.err.println("您的输入有误：" + e.getMessage());
		}
	}
}
