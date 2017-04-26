package net.xinqushi.orm.entity;

import net.xinqushi.common.enums.CommonStatus;

public class LineFixedSpot {

	private Integer id;
	
	private Integer lineId;
	
	private String name;
	
	private String ruleType;
	
	private Integer cityId;
	
	private Double latitude;
	
	private Double longitude;
	
	private Long startTime;
	
	private Long endTime;
	
	private CommonStatus status;
	
	private Long createTime;
	
	private String src;
	
	private String dst;
	
	private String cityName;
	
	private Integer srcCityId;
	
	private Integer dstCityId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public CommonStatus getStatus() {
		return status;
	}

	public void setStatus(CommonStatus status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getSrcCityId() {
		return srcCityId;
	}

	public void setSrcCityId(Integer srcCityId) {
		this.srcCityId = srcCityId;
	}

	public Integer getDstCityId() {
		return dstCityId;
	}

	public void setDstCityId(Integer dstCityId) {
		this.dstCityId = dstCityId;
	}
	
}
