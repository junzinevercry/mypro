package com.abs.dto.statistic;

import java.math.BigDecimal;
import java.util.Date;

public class ManageFoundsStatisticDto {
	private String foundsType;
	private BigDecimal founds;
	private Date paymentDate;
	private String detail;
	private String userName;

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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
