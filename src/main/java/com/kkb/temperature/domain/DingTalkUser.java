package com.kkb.temperature.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DingTalkUser {
	private String userId;
	private String name;
	private String title;
	private String mobile;
	private String department;

	private Boolean isAdmin;

	private Boolean isPromised;

	private String avatar;

	@JsonProperty("isPromised")
	public Boolean getPromised() {
		return isPromised;
	}

	public void setPromised(Boolean promised) {
		isPromised = promised;
	}

	@JsonProperty("isAdmin")
	public Boolean getAdmin() {
		return isAdmin;
	}


	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


}
