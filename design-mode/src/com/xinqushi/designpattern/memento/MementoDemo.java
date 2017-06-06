package com.xinqushi.designpattern.memento;
/**
 * 备忘录模式demo
 * <p>定义：在不破坏封装性的前提下，捕获对象内部状态，并在对象之外保存，
 * 以后可以将该对象恢复到原先保存的状态</p>
 * <p>使用：适用于功能比较复杂，但需要维护或记录属性历史的类；或需要保存的
 * 属性只是众多属性中的一小部分时，可以根据保存的信息还原到前一状态</p>
 * <p>优点：可以将复杂的对象内部信息对其他的对象疲敝起来；在当状态改变的
 * 时候，若这个状态无效，可使用暂时存储起来的备忘录将状态恢复</p>
 * @author yangli
 */
public class MementoDemo {
	
	public static void main(String[] args) {
		
		//大战boss前
		GameRole tianhe = new GameRole();
		tianhe.getInitState();
		System.out.println("*****大战boss前*****");
		tianhe.displayState();
		
		//保存进度
		RoleStateCaretaker stateCaretaker = new RoleStateCaretaker();
		stateCaretaker.setMemento(tianhe.saveState());
		
		//大战boss失败
		System.out.println("*****大战boss失败*****");
		tianhe.combat_lose();
		tianhe.displayState();
		
		//恢复到boss战前
		System.out.println("*****恢复到boss战前*****");
		tianhe.recoveryState(stateCaretaker.getMemento());
		tianhe.displayState();
		
		//大战boss成功
		System.out.println("*****大战boss成功*****");
		tianhe.combat_win();
		tianhe.displayState();
	}
}
