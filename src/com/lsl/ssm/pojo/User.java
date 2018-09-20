package com.lsl.ssm.pojo;

import java.util.Date;

public class User {
	private Integer id; // 用户id
	private String ip; // 用户IP地址
	private String nickName; // 用户昵称
	private String address; // 用户地址
	private Integer count; // 挑战次数
	private String best; // 最好成绩
	private Integer suCount; // 成功次数
	private Date creationDate; // 第一次访问时间
	private Date modifyDate; // 最后一次访问时间
	private String userKey;// 加密值

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getBest() {
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}

	public Integer getSuCount() {
		return suCount;
	}

	public void setSuCount(Integer suCount) {
		this.suCount = suCount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User(Integer id, String ip, String nickName, String address,
			Integer count, String best, Integer suCount, Date creationDate,
			Date modifyDate, String userKey) {
		super();
		this.id = id;
		this.ip = ip;
		this.nickName = nickName;
		this.address = address;
		this.count = count;
		this.best = best;
		this.suCount = suCount;
		this.creationDate = creationDate;
		this.modifyDate = modifyDate;
		this.userKey = userKey;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", best=" + best + ", count="
				+ count + ", creationDate=" + creationDate + ", id=" + id
				+ ", ip=" + ip + ", modifyDate=" + modifyDate + ", nickName="
				+ nickName + ", suCount=" + suCount + ", userKey=" + userKey
				+ "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
