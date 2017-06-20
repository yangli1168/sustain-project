package net.xinqushi.vo;

public class TimeSlotCast {

	private String slotBegin;
	
	private String slotEnd;
	
	private int maxSeats;
	
	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public TimeSlotCast() {
    }

	public TimeSlotCast(String slotBegin, String slotEnd) {
	    this.slotBegin = slotBegin;
	    this.slotEnd = slotEnd;
    }

	public String getSlotBegin() {
		return slotBegin;
	}

	public void setSlotBegin(String slotBegin) {
		this.slotBegin = slotBegin;
	}

	public String getSlotEnd() {
		return slotEnd;
	}

	public void setSlotEnd(String slotEnd) {
		this.slotEnd = slotEnd;
	}

}
