package com.abs.dto.statistic;

import java.math.BigDecimal;
import java.util.Date;

public class ProfoundsStatisticDto {
	private String itemId;
	private String itemName;
	private String itemNum;
	private String foundsType;
	private BigDecimal founds;
	private String userName;
	private String detail;
	private Date paymentDate;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getFoundsType() {
		return foundsType;
	}

	public void setFoundsType(String foundsType) {
		this.foundsType = foundsType;
	}

	public BigDecimal getFounds() {
		return founds;
	}

	public void setFounds(BigDecimal founds) {
		this.founds = founds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
