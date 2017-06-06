package com.xinqushi.designpattern.interpreter;
/**
 *  解释器模式：表达式超类
 * @author yangli
 */
public abstract class Expression {
	
	/** 解释器*/
	public void interpret(PlayContext context){
		if (context.getText().length() == 0) {
			return;
		} else {
			String playKey = context.getText().substring(0, 1);
			context.setText(context.getText().substring(2));
			double playValue = Double.parseDouble(context.getText().substring(0, context.getText().indexOf(" ")));
			context.setText(context.getText().substring(context.getText().indexOf(" ") + 1));
			
			this.excute(playKey, playValue);
		}
	}
	
	/** 执行*/
	public abstract void excute(String key, double value);
}
