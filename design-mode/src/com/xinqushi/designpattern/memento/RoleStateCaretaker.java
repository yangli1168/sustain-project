package com.xinqushi.designpattern.memento;
/**
 * 角色状态管理者类
 * @author yangli
 */
public class RoleStateCaretaker {
	
	private RoleStateMemento memento;

	public RoleStateMemento getMemento() {
		return memento;
	}

	public void setMemento(RoleStateMemento memento) {
		this.memento = memento;
	}
	
}
