<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<title>web_demo</title>
<script>
	function gotoExpertList(){
		$("#searchForm").submit();
	}
	function gotoUpdateExpert(id){
		ShowIframe('编辑专家信息','expertUpdate','${ctx}/user/gotoUpdateExpert.action?id='+id,500,400,true);
	}
	
</script>
</head>
<body> 
	<div class="basecontent">
		<%@ include file="/common/subMenuList.jsp"%>
		<div class="menu">
			<form action="${ctx }/user/gotoExpertList.action" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                    <li>用户姓名：<input type="text" name="userName" id="userName" value="${queryMap.userName}"/></li>
		                </ul><br>
		                <div class="clearer"></div>
		            </div> 
		        </li>
		        <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="gotoExpertList()" /></li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="font1">姓名</td>
						<td class="font1">专业</td>
						<td class="font1">职位</td>
						<td class="font1">职称</td>
						<td class="font1">操作</td>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${fn:length(pageObject.pageList) == 0}">
						<tr>
							<td colspan="5" align="center">没有检索到任何数据</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="0" end="${fn:length(pageObject.pageList)-1}" step="1">
							<c:set var="expertDto" value="${pageObject.pageList[i]}" />
							<tr class="${i % 2 == 0 ? 'table_tr' : 'table_tr_bg'}">
					       		<td>&nbsp;
						       		${elf:setStrongInterceptString(expertDto.userName,queryMap.userName,color)}
						       		&nbsp;
					       		</td>
								<td>&nbsp;
						       		${expertDto.major}
						       		&nbsp;
					       		</td>
					       		<td>&nbsp;
						       		${expertDto.jobTable}
						       		&nbsp;
					       		</td>
					       		<td>&nbsp;
						       		${expertDto.office}
						       		&nbsp;
					       		</td>
						       	<td>
						       		<img src="${ctx }/resource/images/control/edit.png" width="20" height="20" onclick="gotoUpdateExpert('${expertDto.id}')" title="编辑"/>
					       		</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		    </table>
			<jsp:include page="/common/paginationFooter.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>