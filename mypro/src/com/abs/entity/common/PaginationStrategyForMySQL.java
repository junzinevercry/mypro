package com.abs.entity.common;

/**
 * 
 * @author su yi
 * 
 */
public class PaginationStrategyForMySQL implements PaginationStrategy {
	private int _startPoint;

	private int _endPoint;

	public int getEndPoint() {
		return _endPoint;
	}

	public int getStartPoint() {
		return _startPoint;
	}

	public void setDataPoint(int currentPage, int pageSize) {
		_startPoint = (currentPage - 1) * pageSize;
		_endPoint = pageSize;
	}
}
