package com.abs.entity.common;

import java.util.List;

public class PageObject<T> {
	private int currentPage = 1;
	private int dataCount = 0;
	private int pageSize = 10;
	private int maxPage;
	private List<T> pageList;
	private int startPoint;
	private int endPoint;
	private PaginationStrategy paginationStrategy;

	public PageObject() {

	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
		maxPage = dataCount % pageSize == 0 ? dataCount / pageSize : dataCount
				/ pageSize + 1;
		paginationStrategy =new PaginationStrategyForMySQL();
		setDataPoint(paginationStrategy);
	}

	/**
	 * @param dataCount
	 *            总数据数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            页面大小 注: 默认分页逻辑 startPoint & endPoint 是oracle实现</br>
	 *            如果需要使用到其他数据库请实现PaginationStrategy接口</br> 使用该类的
	 *            setPaginationStrategy 方法获得相应的实现</br>
	 */
	public PageObject(int dataCount, int currentPage, int pageSize) {
		if (pageSize == 0)
			throw new IllegalArgumentException(
					" 单页数据设置 [pageSize]不能为小于等于 0  当前[pageSize]的值是 : "
							+ pageSize);
		this.pageSize = pageSize;
		this.dataCount = dataCount;
		this.currentPage = currentPage;
		maxPage = dataCount % pageSize == 0 ? dataCount / pageSize : dataCount
				/ pageSize + 1;
		paginationStrategy = new PaginationStrategyForMySQL();
		setDataPoint(paginationStrategy);
	}

	private void setDataPoint(PaginationStrategy paginationStrategy) {
		paginationStrategy.setDataPoint(currentPage, pageSize);
		startPoint = paginationStrategy.getStartPoint();
		endPoint = paginationStrategy.getEndPoint();
	}

	/**
	 * 默认的实现是 PaginationStrategyForOracle 如果需要实现其他数据库的分页逻辑，请继承
	 * PaginationStrategy，传入当前页面和页面大小设置不同数据库的分页
	 * 
	 * @param paginationStrategy
	 */
	public void setPaginationStrategy(PaginationStrategy paginationStrategy) {
		this.paginationStrategy = paginationStrategy;
		setDataPoint(paginationStrategy);
	}

	/**
	 * 获得当前页码
	 * 
	 * @return 当前页码
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 获得单页数据数
	 * 
	 * @return 单页数据数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获得一共有多少页
	 * 
	 * @return 一共有多少页
	 */
	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * 获得总数据数
	 * 
	 * @return 总数据数
	 */
	public int getDataCount() {
		return dataCount;
	}

	/**
	 * 获得分页起始点
	 * 
	 * @return 分页起始点
	 */
	public int getStartPoint() {
		return startPoint;
	}

	/**
	 * 获得分页结束点
	 * 
	 * @return 分页结束点
	 */
	public int getEndPoint() {
		return endPoint;
	}

	/**
	 * 设置分页对象集合
	 * 
	 * @return 分页对象集合
	 */
	public List<T> getPageList() {
		return pageList;
	}

	/**
	 * 获得分页对象集合
	 * 
	 * @param pageList
	 *            分页对象集合
	 */
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	@Override
	public String toString() {
		StringBuilder pageObjectPrintBuilder = new StringBuilder();
		pageObjectPrintBuilder.append("  当前页码 ").append(currentPage)
				.append("  总数据数 ").append(dataCount);
		pageObjectPrintBuilder.append("  起始点 ").append(startPoint)
				.append("     结束点 ").append(endPoint);
		pageObjectPrintBuilder.append("  总页数 ").append(maxPage)
				.append("        单页数据数 ").append(pageSize);
		return pageObjectPrintBuilder.toString();
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
