<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<script>
	var openerName = '${openerName}';
	var roleArray = new Array();
	var str;
	function gotoUserListForCheckBox(){
		$("#searchForm").submit();
	}
	function selectcheck(box){
		var tbox = $(box);
		var id = tbox.attr("id");
		var name = $.trim($("#"+id+"name").text());
		if(tbox.attr("checked")=="checked"){
			var role = new Object();
			role.id = id;
			role.name = name;
			roleArray.push(role);
		}else{
			for(var i in roleArray){
				if(roleArray[i].id==id){
					roleArray.splice(i, 1);
				}
			}
		}
		if(roleArray.length>0){
			addIdJson();
		}else{
			cleanIdJson();
		}
	}
	function addIdJson(){
		str = '[';
		for(var i in roleArray){
			var obj = roleArray[i];
			str = str + '{"id":"' + obj.id + '","name":"' + obj.name + '"}';
			if(i < roleArray.length-1){
				str = str + ',';
			}
		}
		str = str + ']';
		$("#idJson").val(str);
	}
	function cleanIdJson(){
		$("#idJson").val("");
	}
	$(document).ready(function(){
		if('${idJson}'!=''){
			roleArray = eval('('+'${idJson}'+')');
			$('#idJson').val('${idJson}');
		}
	}); 
	function saveUser(){
		if($('#idJson').val()==''){
			alert('请选择用户！');
			return;
		}
		callBackFunc(roleArray,openerName,'addUser');
		closeIframe();
	}
</script>
</head>
<body bgcolor="e7f3fc"> 
	<div class="basecontent">
		<div class="menu">
			<form action="${ctx }/user/gotoUserListForCheckBox.action?openerName=${openerName}" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<input type="hidden" id="idJson" name="idJson"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                    <li>部门名称：<input type="text" name="deptName" id="deptName"  value="${queryMap.deptName}"/></li>
		                    <li>职务名称：<input type="text" name="jobName" id="jobName"  value="${queryMap.jobName}"/></li>
		                    <li>人员名称：<input type="text" name="userName" id="userName"  value="${queryMap.userName}"/></li>
		                </ul><br>
		                <div class="clearer"></div>
		            </div> 
		        </li>
		        <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="gotoUserListForCheckBox()" /></li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td class="font6">&nbsp;&nbsp;</td>
						<td class="font6">编号</td>
						<td class="font6">名称</td>
						<td class="font6">部门</td>
						<td class="font6">职位</td>
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
								<td><input type="checkbox" id="${user.id }" onclick="selectcheck(this)"  <c:if test='${user.selectType==1 }'>checked="checked"</c:if> ></td>
						       	<td>&nbsp;
						       		${user.num}
						       		&nbsp;
					       		</td>
					       		<td id="${user.id }name">&nbsp;
						       		${elf:setStrongInterceptString(user.name,queryMap.userName,color)}
						       		&nbsp;
					       		</td>
								<td>&nbsp;
						       		${elf:setStrongInterceptString(user.deptName,queryMap.deptName,color)}
						       		&nbsp;
					       		</td>
					       		<td>&nbsp;
						       		${elf:setStrongInterceptString(user.jobName,queryMap.jobName,color)}
						       		&nbsp;
					       		</td>
							</tr>
						</c:forEach>
						<tr>
					        <td colspan="5"  style="background: #bee3f9;" align="center" valign="middle">	
					        	<!--  <div class="button">
					            	<ul>
					                	<li onclick="saveUser()">确定</li>
					                    <li onclick="closeIframe()">取消</li>
					                </ul>
					            </div>-->
					            <img src="${ctx }/resource/images/button/oa_icon_button_yes.png" onclick="saveUser()">			        
					            <img src="${ctx }/resource/images/button/oa_icon_button_qx.png" onclick="closeIframe()">
			                    
					        </td>
			      		</tr>
					</c:otherwise>
				</c:choose>
		    </table>
			<jsp:include page="/common/paginationFooter.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>