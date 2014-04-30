package com.abs.entity.common;

/**
 * 
 * @author suyi
 * 
 */
public interface PaginationStrategy {
	/**
	 * 获取数据结束�?
	 * 
	 * @return
	 */
	public int getStartPoint();

	/**
	 * 获取数据起始�?
	 * 
	 * @return
	 */
	public int getEndPoint();

	/**
	 * 设置起分页始点和结束�?
	 * 
	 * @param currentPage
	 * @param pageSize
	 */
	public void setDataPoint(int currentPage, int pageSize);
}
