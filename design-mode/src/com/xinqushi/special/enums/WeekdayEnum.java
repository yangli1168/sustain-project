package com.xinqushi.special.enums;
/**
 * 星期枚举
 * @author yangli
 */
public enum WeekdayEnum {
	
	MONDAY("星期一"),	
	TUESDAY("星期二"), 
	WEDNESDAY("星期三"),	
	THURSDAY("星期四"), 
	FRIDAY("星期五"), 
	SATURDAY("星期六"),	
	SUNDAY("星期日");
	
	private final String weekDay;
	
	private WeekdayEnum(String weekDay) {
		this.weekDay = weekDay;
	}
	
	/** 返回文字说明*/
	public String getExplain(){
		return weekDay;
	}
	
	public static void main(String[] args) {
		
		System.out.println(WeekdayEnum.TUESDAY.toString());
		System.out.println(WeekdayEnum.TUESDAY.getExplain());
		System.out.println(WeekdayEnum.TUESDAY.ordinal());
	}
	
}
