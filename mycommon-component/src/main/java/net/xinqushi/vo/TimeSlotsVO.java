package net.xinqushi.vo;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotsVO {

	private String date;
	
	private List<TimeSlot> slots = new ArrayList<TimeSlot>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TimeSlot> getSlots() {
		return slots;
	}

	public void setSlots(List<TimeSlot> slots) {
		this.slots = slots;
	}
	
}
