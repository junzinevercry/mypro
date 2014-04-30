<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp"%>
<input type="hidden" id="maxPage" name="maxPage" value="${pageObject.maxPage}"/>

<div class="numpeople">
	共有<span class="font17">${pageObject.dataCount}</span>条记录</div>
<div class="page">
	<ul>
		<li style="vertical-align:middle" >
		<c:if test="${pageObject.currentPage != 1}">
			<a href="javascript:gotoFirstPage('1','${pageObject.currentPage}')">
				<img src="${ctx}/resource/images/page/page_backleft.png" />
			</a>
			<a href="javascript:gotoPage('${pageObject.currentPage-1}')">
				<img src="${ctx}/resource/images/page/page_left.png" />
			</a>
		</c:if>
			
		<c:if test="${pageObject.currentPage == 1}">
				<img src="${ctx}/resource/images/page/page_backleft_not.png" />&nbsp;<img src="${ctx}/resource/images/page/page_left_not.png" />
		</c:if>
		<c:if test="${pageObject.currentPage != pageObject.maxPage and pageObject.maxPage > 0 }">
			<a href="javascript:goNext('${pageObject.currentPage+1}',${pageObject.maxPage})">
				<img src="${ctx}/resource/images/page/page_right.png" />
			</a>
			<a href="javascript:gotoEndPage('${pageObject.maxPage}','${pageObject.currentPage}')">
				<img src="${ctx}/resource/images/page/page_goright.png" />
			</a>
		</c:if>
		<c:if test="${pageObject.currentPage == pageObject.maxPage or pageObject.maxPage == 0 }">
				<img src="${ctx}/resource/images/page/page_right_not.png" />&nbsp;<img src="${ctx}/resource/images/page/page_goright_not.png" />
		</c:if>
		</li>
		<li style="margin-top:2px;" >共有${pageObject.maxPage }页  转到第<input name="pageNumber" id="pageNumber" size="3" maxlength="3" value="${pageObject.currentPage}"/>页<img src="${ctx}/resource/images/page/page_go.png" onclick="javascript:choiceTextPage('${pageObject.maxPage}','${pageObject.currentPage}')"/></li>
	</ul>
</div>
