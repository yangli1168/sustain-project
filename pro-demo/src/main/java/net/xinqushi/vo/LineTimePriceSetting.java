package net.xinqushi.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineTimePriceSetting {

	private int beginTime;

	private int endTime;

	private int intervalMin;

	private int minReservMin;

	private int maxReservDay;

	private Map<Integer, List<PriceRule>> carModelPriceRules = new HashMap<Integer, List<PriceRule>>();

	public int getBeginTime() {
		return beginTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setBeginTime(int beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getIntervalMin() {
		return intervalMin;
	}

	public void setIntervalMin(int intervalMin) {
		this.intervalMin = intervalMin;
	}

	public int getMinReservMin() {
		return minReservMin;
	}

	public void setMinReservMin(int minReservMin) {
		this.minReservMin = minReservMin;
	}

	public int getMaxReservDay() {
		return maxReservDay;
	}

	public void setMaxReservDay(int maxReservDay) {
		this.maxReservDay = maxReservDay;
	}

	public Map<Integer, List<PriceRule>> getCarModelPriceRules() {
		return carModelPriceRules;
	}

	public void setCarModelPriceRules(Map<Integer, List<PriceRule>> carModelPriceRules) {
		this.carModelPriceRules = carModelPriceRules;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beginTime;
		result = prime * result + endTime;
		result = prime * result + intervalMin;
		result = prime * result + maxReservDay;
		result = prime * result + minReservMin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineTimePriceSetting other = (LineTimePriceSetting) obj;
		if (beginTime != other.beginTime)
			return false;
		if (endTime != other.endTime)
			return false;
		if (intervalMin != other.intervalMin)
			return false;
		if (maxReservDay != other.maxReservDay)
			return false;
		if (minReservMin != other.minReservMin)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineTimePriceSetting [beginTime=" + beginTime + ", endTime=" + endTime + ", intervalMin=" + intervalMin
				+ ", minReservMin=" + minReservMin + ", maxReservDay=" + maxReservDay + ", carModelPriceRules="
				+ carModelPriceRules + "]";
	}

}
