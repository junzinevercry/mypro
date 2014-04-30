package com.abs.entity.base;

import java.util.Date;

import com.abs.util.annotation.Columns;

public class GenericEntity extends SortableEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1488434651082345223L;
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;

	@Columns("update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Columns("update_by")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Columns("create_by")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Columns("create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
