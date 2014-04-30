<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<title>web_demo</title>
<script>
	function gotoUserList(){
		$("#searchForm").submit();
	}
	function gotoUpdateUser(id){
		ShowIframe('编辑人员','userUpdate','${ctx}/user/gotoUpdateUser.action?userId='+id,500,400,true);
	}
	function gotoCreateUser(){
		ShowIframe('创建人员','userCreate','${ctx}/user/gotoCreateUser.action',500,400,true);
	}
	function updataUserState(id,username,state){
		if(state=='0'){
			var msg = "您确认"+username+"已离职吗？\n\n请确认 ！";
		    
		}else{
			var msg = "您确认启用"+username+"吗？\n\n请确认 ！";
		}
		if (confirm(msg)==false){   
			  return;
		}
		$.ajax({
			type:'post',
			url:'${ctx}/user/doUpdateUserState.action',
			dataType:"json",
			data:{id:id,state:state},
			async:false,
			cache:false,
			success:function(data){
				if(data.type=='success'){
					alert('操作成功');
					gotoUserList();
				}
			}
		});
	}
</script>
</head>
<body> 
	<div class="basecontent">
		<%@ include file="/common/subMenuList.jsp"%>
		<div class="menu">
			<form action="${ctx }/user/gotoUserList.action" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                	<c:if test="${empty orgId }">
			                    <li>
			                    	组织机构名称：
			                    	<select name="orgName" id="orgName">
			                    		<option value="">请选择</option>
			                    		<c:forEach var="org" items="${orgList}">
		                    				<option value="${org.id }">${org.name }</option>
		                    			</c:forEach>
			                    	</select>
			                    </li>
		                    </c:if>
		                    <li>部门名称：<input type="text" name="deptName" id="deptName" value="${queryMap.deptName}"/></li>
		                    <li>职务名称：<input type="text" name="jobName" id="jobName" value="${queryMap.jobName}"/></li>
		                    <li>人员名称：<input type="text" name="userName" id="userName" value="${queryMap.userName}"/></li>
		                    <li>
		                    	状态：
		                    	<select name="state" id="state">
		                    		<option value="">请选择</option>
		                    		<option value="1" <c:if test="${'1'==queryMap.state }">selected="selected"</c:if>>在职</option>
		                    		<option value="0" <c:if test="${'0'==queryMap.state }">selected="selected"</c:if>>离职</option>
		                    	</select>
		                    </li>
		                </ul><br>
		                <div class="clearer"></div>
		            </div> 
		        </li>
		        <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="gotoUserList()" /></li>
		        <li><img src="${ctx }/resource/images/button/oa_button_add.png" onclick="gotoCreateUser()"/></li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="font1">编号</td>
						<td class="font1">名称</td>
						<c:if test="${empty orgId }">
							<td class="font1">组织机构</td>
						</c:if>
						<td class="font1">部门</td>
						<td class="font1">职位</td>
						<td width="10%" class="font1">操作</td>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${fn:length(pageObject.pageList) == 0}">
						<tr>
							<td colspan="6" align="center">没有检索到任何数据</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="0" end="${fn:length(pageObject.pageList)-1}" step="1">
							<c:set var="user" value="${pageObject.pageList[i]}" />
							<tr class="${i % 2 == 0 ? 'table_tr' : 'table_tr_bg'}">
						       	<td>&nbsp;
						       		${user.num}
						       		&nbsp;
					       		</td>
					       		<td>&nbsp;
						       		${elf:setStrongInterceptString(user.name,queryMap.userName,color)}
						       		&nbsp;
					       		</td>
					       		<c:if test="${empty orgId }">
									<td>&nbsp;
							       		${elf:setStrongInterceptString(user.orgName,queryMap.orgName,color)}
							       		&nbsp;
						       		</td>
								</c:if>
								<td>&nbsp;
						       		${elf:setStrongInterceptString(user.deptName,queryMap.deptName,color)}
						       		&nbsp;
					       		</td>
					       		<td>&nbsp;
						       		${elf:setStrongInterceptString(user.jobName,queryMap.jobName,color)}
						       		&nbsp;
					       		</td>
						       	<td>
						       		<img src="${ctx }/resource/images/control/edit.png" width="20" height="20" onclick="gotoUpdateUser('${user.id}')" title="编辑"/>
						       		<c:choose>
						       			<c:when test="${user.state == '1' }">
						       				<img src="${ctx }/resource/images/control/stop.png" width="20" height="20" title="离职" onclick="updataUserState('${user.id}','${user.name}','0')"/>
						       			</c:when>
						       			<c:otherwise>
						       				<img src="${ctx }/resource/images/control/begin.png" width="20" height="20" title="启用" onclick="updataUserState('${user.id}','${user.name}','1')"/>
						       			</c:otherwise>
						       		</c:choose>
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