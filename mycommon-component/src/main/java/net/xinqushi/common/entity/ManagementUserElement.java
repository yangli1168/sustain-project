package net.xinqushi.common.entity;

import java.util.List;
/**
 * 管理员用户类
 * @author yangli
 * 2017年6月28日-下午2:20:55
 */
public class ManagementUserElement {

	private int userId;

	private long deparmentId;

	private String token;

	private String userName;

	private String loginName;

	private List<Integer> roles;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public long getDeparmentId() {
		return deparmentId;
	}

	public void setDeparmentId(long deparmentId) {
		this.deparmentId = deparmentId;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

}
