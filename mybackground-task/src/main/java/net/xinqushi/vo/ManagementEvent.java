package net.xinqushi.vo;

import net.xinqushi.common.enums.ManagementEventType;

/**
 * 事件类
 * @author yangli
 */
public class ManagementEvent {
	
	private ManagementEventType type;

	private String json;

	private String classname;

	public ManagementEventType getType() {
		return type;
	}

	public void setType(ManagementEventType type) {
		this.type = type;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
