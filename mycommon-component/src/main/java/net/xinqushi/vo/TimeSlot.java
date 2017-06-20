package net.xinqushi.vo;

public class TimeSlot {

	private long slotBegin;
	
	private long slotEnd;
	
	private int maxSeats;
	
	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public TimeSlot() {
    }

	public TimeSlot(long slotBegin, long slotEnd) {
	    this.slotBegin = slotBegin;
	    this.slotEnd = slotEnd;
    }

	public long getSlotBegin() {
		return slotBegin;
	}

	public void setSlotBegin(long slotBegin) {
		this.slotBegin = slotBegin;
	}

	public long getSlotEnd() {
		return slotEnd;
	}

	public void setSlotEnd(long slotEnd) {
		this.slotEnd = slotEnd;
	}
	
}
