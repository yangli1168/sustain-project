package net.xinqushi.common.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户类
 * 
 * @author yangli 2017年6月28日-下午2:21:38
 */
public class UserElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111051151608856580L;

	private long userId;

	private String deviceId;

	private String token;

	private Long carId;

	private String platform;

	private String pushUserId;

	private String pushChannelId;

	private String phone;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPushUserId() {
		return pushUserId;
	}

	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
	}

	public String getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(String pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("deviceId", this.deviceId);
		map.put("platform", this.platform);
		map.put("userId", this.userId + "");
		map.put("token", token);
		map.put("phone", phone);
		if (this.carId != null) {
			map.put("carId", this.carId + "");
		}
		if (this.pushUserId != null) {
			map.put("pushUserId", this.pushUserId);
		}
		if (this.pushChannelId != null) {
			map.put("pushChannelId", this.pushChannelId);
		}
		return map;
	}

	public static UserElement fromMap(Map<String, String> map) {
		UserElement ue = new UserElement();
		ue.setDeviceId(map.get("deviceId"));
		ue.setPlatform(map.get("platform"));
		ue.setToken(map.get("token"));
		ue.setPhone(map.get("phone"));
		try {
			ue.setUserId(Long.parseLong(map.get("userId")));
		} catch (Exception e) {
		}
		try {
			ue.setCarId(Long.parseLong(map.get("carId")));
		} catch (Exception e) {
		}
		ue.setPushUserId(map.get("pushUserId"));
		ue.setPushChannelId(map.get("pushChannelId"));
		return ue;
	}

}
