package com.xinqushi.designmodel.state;

public class StateTest {
	public static void main(String[] args) {
		State state = new State();
		Context context = new Context(state);
		// state1
		state.setValue("state1");
		context.method();

		// state2
		state.setValue("state2");
		context.method();
	}
}
