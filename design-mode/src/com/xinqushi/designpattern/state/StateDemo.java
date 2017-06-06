package com.xinqushi.designpattern.state;
/**
 * 状态模式demo
 * <p>定义：当控制一个对象状态转换的条件表达式过于复杂时的情况，
 * 将状态的判断逻辑转移到表示不同状态的一系列类中，把复杂判断逻辑简化</p>
 * <p>优点：将与特定状态相关的行为局部化，并且将不同状态的行为分割开来</p>
 * <p>使用：当一个对象的行为取决于它的状态，并且必须在运行时间根据状态
 * 改变它的行为时</p>
 * @author yangli
 */
public class StateDemo {
	
	public static void main(String[] args) {
		//紧急项目
		Work emergecyProject = new Work();
		emergecyProject.setHour(9);
		emergecyProject.writeProgram();
		emergecyProject.setHour(10);
		emergecyProject.writeProgram();
		emergecyProject.setHour(12);
		emergecyProject.writeProgram();
		emergecyProject.setHour(13);
		emergecyProject.writeProgram();
		emergecyProject.setHour(14);
		emergecyProject.writeProgram();
		emergecyProject.setHour(17);
		
		//工作完成与否
//		emergecyProject.setFinish(true);
		emergecyProject.setFinish(false);
		
		emergecyProject.writeProgram();
		emergecyProject.setHour(19);
		emergecyProject.writeProgram();
		emergecyProject.setHour(22);
		emergecyProject.writeProgram();
		emergecyProject.setHour(23);
		emergecyProject.writeProgram();
		
	}
}
