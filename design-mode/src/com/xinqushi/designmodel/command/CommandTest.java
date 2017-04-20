package com.xinqushi.designmodel.command;

public class CommandTest {
	public static void main(String[] args) {
		Command myCommand = new MyCommand(new Receiver());
		Invoker invoker = new Invoker(myCommand);
		invoker.action();
	}
}
