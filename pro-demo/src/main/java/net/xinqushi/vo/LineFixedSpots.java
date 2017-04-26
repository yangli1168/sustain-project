package net.xinqushi.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinqushi.util.Pair;

public class LineFixedSpots {

	private int lineId;

	private int srcCityId;

	private int dstCityId;

	private List<Spot> srcSpots = new ArrayList<Spot>();

	private List<Spot> dstSpots = new ArrayList<Spot>();

	private Map<Spot, List<Pair<Long, Long>>> timedSrcSpots = new HashMap<Spot, List<Pair<Long, Long>>>();

	private Map<Spot, List<Pair<Long, Long>>> timedDstcSpots = new HashMap<Spot, List<Pair<Long, Long>>>();

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getLineId() {
		return lineId;
	}

	public int getSrcCityId() {
		return srcCityId;
	}

	public void setSrcCityId(int srcCityId) {
		this.srcCityId = srcCityId;
	}

	public int getDstCityId() {
		return dstCityId;
	}

	public void setDstCityId(int dstCityId) {
		this.dstCityId = dstCityId;
	}

	public List<Spot> getSrcSpots() {
		return srcSpots;
	}

	public void setSrcSpots(List<Spot> srcSpots) {
		this.srcSpots = srcSpots;
	}

	public List<Spot> getDstSpots() {
		return dstSpots;
	}

	public void setDstSpots(List<Spot> dstSpots) {
		this.dstSpots = dstSpots;
	}

	public Map<Spot, List<Pair<Long, Long>>> getTimedSrcSpots() {
		return timedSrcSpots;
	}

	public void setTimedSrcSpots(Map<Spot, List<Pair<Long, Long>>> timedSrcSpots) {
		this.timedSrcSpots = timedSrcSpots;
	}

	public Map<Spot, List<Pair<Long, Long>>> getTimedDstcSpots() {
		return timedDstcSpots;
	}

	public void setTimedDstcSpots(Map<Spot, List<Pair<Long, Long>>> timedDstcSpots) {
		this.timedDstcSpots = timedDstcSpots;
	}

}
