package com.abs.dto.checkin;

import java.util.Date;

public class CheckInDto {

	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户所在地
	 */
	private String userArea;
	/**
	 * 考勤日期
	 */
	private Date checkInDate;
	/**
	 * 签到时间
	 */
	private String inTime;
	/**
	 * 签退时间
	 */
	private String outTime;
	/**
	 * 请假时长
	 */
	private String leaveLong;
	/**
	 * 请假类型
	 */
	private String leaveType;
	/**
	 * 是否出差
	 */
	private String isTravel;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 请假开始时间
	 */
	private Date leaveStartTime;
	/**
	 * 请假结束时间
	 */
	private Date leaveEndTime;
	/**
	 * 外出开始时间
	 */
	private String goOutStartTime;
	/**
	 * 外出结束时间
	 */
	private String goOutEndTime;
	/**
	 * 是否异常数据（默认：0非异常）
	 */
	private String isException;
	private String overtimeLong;
	private String goOutLong;

	public String getGoOutLong() {
		return goOutLong;
	}

	public void setGoOutLong(String goOutLong) {
		this.goOutLong = goOutLong;
	}

	public String getOvertimeLong() {
		return overtimeLong;
	}

	public void setOvertimeLong(String overtimeLong) {
		this.overtimeLong = overtimeLong;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getLeaveLong() {
		return leaveLong;
	}

	public void setLeaveLong(String leaveLong) {
		this.leaveLong = leaveLong;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getIsTravel() {
		return isTravel;
	}

	public void setIsTravel(String isTravel) {
		this.isTravel = isTravel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(Date leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public Date getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public String getGoOutStartTime() {
		return goOutStartTime;
	}

	public void setGoOutStartTime(String goOutStartTime) {
		this.goOutStartTime = goOutStartTime;
	}

	public String getGoOutEndTime() {
		return goOutEndTime;
	}

	public void setGoOutEndTime(String goOutEndTime) {
		this.goOutEndTime = goOutEndTime;
	}

	public String getIsException() {
		return isException;
	}

	public void setIsException(String isException) {
		this.isException = isException;
	}

}
