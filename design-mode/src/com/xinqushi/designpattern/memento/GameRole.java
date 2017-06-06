package com.xinqushi.designpattern.memento;
/**
 * 游戏角色类
 * @author yangli
 */
public class GameRole {
	
	private int vit;
	private int atk;
	private int def;
	
	//显示状态
	public void displayState(){
		System.out.println("角色当前状态：");
		System.out.println("体力：" + this.vit + " 攻击力：" + this.atk +
				" 防御力：" + this.def);
	}
	
	//获取初始状态
	public void getInitState(){
		this.vit = 100;
		this.atk = 100;
		this.def = 100;
	}
	
	//保存状态
	public RoleStateMemento saveState(){
		return new RoleStateMemento(this.vit, this.atk, this.def);
	}
	
	//恢复状态
	public void recoveryState(RoleStateMemento memento){
		this.vit = memento.getVit();
		this.atk = memento.getAtk();
		this.def = memento.getDef();
	}
	
	//战斗失败
	public void combat_lose(){
		this.vit = 0;
		this.atk = 0;
		this.def = 0;
	}
	
	//战斗胜利
	public void combat_win(){
		this.vit = 80;
		this.atk = 90;
		this.def = 70;
	}
	
	public int getVit() {
		return vit;
	}
	public void setVit(int vit) {
		this.vit = vit;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}
	
}
