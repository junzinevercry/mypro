<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<style>

.nav2 {
	width: 100%;
	/*
	background: #57cfed;
	*/
	height: 30px;
}

.nav2 ul {
	list-style: none;
}

.nav2 li {
	float: left;
	color: #000000;
	font-size: 12px;
	line-height: 30px;
	padding: 0 10px;
}

.nav2 li a {
	color: #000000;
	text-decoration: none;
}

.nav2 li a:hover {
	color: #990099;
	text-decoration: none;
}

</style>
<%--
<c:choose>
	<c:when test="${fn:length(subMenuList) > 0}">
		<div class="nav2">
			<ul>
				<li><a href="${ctx }/${parentMenu.url }">${parentMenu.name }</a> >> </li> 
				<c:forEach var="i" begin="0" end="${fn:length(subMenuList)-1}" step="1">
					<c:set var="menu" value="${subMenuList[i]}" />
					<li><a href="${ctx }/${menu.url }">${menu.name }</a><c:if test="${i < fn:length(subMenuList)-1}">-</c:if></li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
--%>
<div class="boss" >
	&nbsp;&nbsp;${parentMenu.name }——当前位置：${currentMenu.name }
</div>