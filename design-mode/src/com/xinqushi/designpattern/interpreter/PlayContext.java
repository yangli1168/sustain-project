package com.xinqushi.designpattern.interpreter;
/**
 * 解释器模式：演奏内容类
 * @author yangli
 */
public class PlayContext {
	
	/** 演奏文本*/
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
