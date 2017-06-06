package com.xinqushi.designpattern.interpreter;
/**
 * 解释器模式：中、低、高音类
 * @author yangli
 */
public class ScaleExpression extends Expression{

	@Override
	public void excute(String key, double value) {
		String scale = "";
		switch (Integer.parseInt(key)) {
		case 1:
			scale = "低音";
			break;
		case 2:
			scale = "中音";
			break;
		case 3:
			scale = "高音";
			break;
		}
		
		System.out.print(" " + scale);
	}
	
}
