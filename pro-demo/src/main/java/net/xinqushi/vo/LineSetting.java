package net.xinqushi.vo;

import java.util.ArrayList;
import java.util.List;

public class LineSetting {

	private int lineId;
	
	private int shareMaxSeats = 0;
	
	private int chartorMaxSeats = 0;

	private List<LineTimePriceSetting> shareTimeSettings = new ArrayList<LineTimePriceSetting>();

	private List<LineTimePriceSetting> chartorTimeSettings = new ArrayList<LineTimePriceSetting>();

	private List<PriceRule> defaultSharePrice = new ArrayList<PriceRule>();

	private List<PriceRule> defaultCharterPrice = new ArrayList<PriceRule>();

	public int getLineId() {
		return lineId;
	}

	@Override
	public String toString() {
		return "LineSetting [lineId=" + lineId + ", shareMaxSeats=" + shareMaxSeats + ", chartorMaxSeats="
				+ chartorMaxSeats + ", shareTimeSettings=" + shareTimeSettings + ", chartorTimeSettings="
				+ chartorTimeSettings + ", defaultSharePrice=" + defaultSharePrice + ", defaultCharterPrice="
				+ defaultCharterPrice + "]";
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public List<LineTimePriceSetting> getShareTimeSettings() {
		return shareTimeSettings;
	}

	public void setShareTimeSettings(List<LineTimePriceSetting> shareTimeSettings) {
		this.shareTimeSettings = shareTimeSettings;
	}

	public List<LineTimePriceSetting> getChartorTimeSettings() {
		return chartorTimeSettings;
	}

	public void setChartorTimeSettings(List<LineTimePriceSetting> chartorTimeSettings) {
		this.chartorTimeSettings = chartorTimeSettings;
	}

	public List<PriceRule> getDefaultSharePrice() {
		return defaultSharePrice;
	}

	public void setDefaultSharePrice(List<PriceRule> defaultSharePrice) {
		this.defaultSharePrice = defaultSharePrice;
	}

	public List<PriceRule> getDefaultCharterPrice() {
		return defaultCharterPrice;
	}

	public void setDefaultCharterPrice(List<PriceRule> defaultCharterPricee) {
		this.defaultCharterPrice = defaultCharterPricee;
	}

	public int getShareMaxSeats() {
		return shareMaxSeats;
	}

	public void setShareMaxSeats(int shareMaxSeats) {
		this.shareMaxSeats = shareMaxSeats;
	}

	public int getChartorMaxSeats() {
		return chartorMaxSeats;
	}

	public void setChartorMaxSeats(int chartorMaxSeats) {
		this.chartorMaxSeats = chartorMaxSeats;
	}

}
