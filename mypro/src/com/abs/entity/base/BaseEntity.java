package com.abs.entity.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.abs.util.annotation.Columns;
import com.abs.util.annotation.PK;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8190411543033236108L;
	private String id;

	@Columns("id")
	@PK
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public final String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
