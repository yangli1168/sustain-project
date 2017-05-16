package net.xinqushi.vo;

public class PriceRule {

	private float price;
	
	private int startMileage;
	
	private int endMileage;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(int startMileage) {
		this.startMileage = startMileage;
	}

	public int getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(int endMileage) {
		this.endMileage = endMileage;
	}

	@Override
	public String toString() {
		return "PriceRule [price=" + price + ", startMileage=" + startMileage + ", endMileage=" + endMileage + "]";
	}
	
}
