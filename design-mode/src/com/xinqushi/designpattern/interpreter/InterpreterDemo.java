package com.xinqushi.designpattern.interpreter;
/**
 * 解释器模式demo
 * <p>定义：给定一个语言，定义它的文法的一种表示，并定义一个解释器，
 * 该解释器使用该表示来解释语言中的句子</p>
 * <p>使用：当有一个语言需要解释执行，并且你可将该语言中的句子表示
 * 为一个抽象语法树时</p>
 * <p>优点：已用于正则表达式，浏览器等应用；</p>
 * @author yangli
 */
public class InterpreterDemo {
	
	public static void main(String[] args) {
		
		PlayContext context = new PlayContext();
		
		System.out.println("上海滩：");
		
		context.setText("0 2 E 0.5 G 0.5 A 3 E 0.5 G 0.5"
				+ " D 3 E 0.5 G 0.5 A 0.5 0 3 C 1 0 2 A"
				+ " 0.5 G 1 C 0.5 E 0.5 D 3");
		
		Expression expression = null;
		try {
			while (context.getText().length() > 0) {
				String str = context.getText().substring(0, 1);
				switch (str) {
				case "0":
					expression = new ScaleExpression();
					break;
				case "C":
				case "D":
				case "E":
				case "F":
				case "G":
				case "A":
				case "B":
				case "P":
					expression = new TerminalExpression();
					break;
				}
				expression.interpret(context);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
