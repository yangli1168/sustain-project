package net.xinqushi.orm.entity;

import net.xinqushi.common.enums.CommonStatus;
import net.xinqushi.common.enums.SpecialRuleType;
import net.xinqushi.common.enums.TripMode;

public class LineSpecialPrice {
    private Integer id;

    private Integer lineId;
    
    private Integer cityId;
    
    private Integer areaId;

    private SpecialRuleType ruleType;

    private Float adjust;

    private TripMode tripMode;

    private Long startTime;

    private Long endTime;

    private CommonStatus status;

    private Long createTime;

    private Integer level;
    /** 新增区域名称*/
    private String areaName;
	/** 新增城市名称*/
	private String cityName;

    public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public SpecialRuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(SpecialRuleType ruleType) {
        this.ruleType = ruleType;
    }

    public Float getAdjust() {
        return adjust;
    }

    public void setAdjust(Float adjust) {
        this.adjust = adjust;
    }

    public TripMode getTripMode() {
        return tripMode;
    }

    public void setTripMode(TripMode tripMode) {
        this.tripMode = tripMode;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
    
}