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
		var major = $.trim($("#"+id+"major").text());
		var jobTable = $.trim($("#"+id+"jobTable").text());
		var office = $.trim($("#"+id+"office").text());
		if(tbox.attr("checked")=="checked"){
			var role = new Object();
			role.id = id;
			role.name = name;
			role.major = major;
			role.jobTable = jobTable;
			role.office = office;
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
			str = str + '{"id":"' + obj.id + '","name":"' + obj.name + '","major":"' + obj.major + '","jobTable":"' + obj.jobTable + '","office":"' + obj.office + '"}';
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
		callBackFunc(roleArray,openerName,'addExpert');
		closeIframe();
	}
</script>
</head>
<body bgcolor="e7f3fc"> 
	<div class="basecontent">
		<div class="menu">
			<form action="${ctx }/user/listExpertForCheckBox.action?openerName=${openerName}" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<input type="hidden" id="idJson" name="idJson"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
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
						<td class="font6">姓名</td>
						<td class="font6">专业</td>
						<td class="font6">职称</td>
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
							<c:set var="expert" value="${pageObject.pageList[i]}" />
							<tr class="${i % 2 == 0 ? 'table_tr' : 'table_tr_bg'}">
								<td><input type="checkbox" id="${expert.id }" onclick="selectcheck(this)"  <c:if test='${expert.selectType==1 }'>checked="checked"</c:if> ></td>
					       		<td id="${expert.id }name">&nbsp;
						       		${elf:setStrongInterceptString(expert.userName,queryMap.userName,color)}
						       		&nbsp;
					       		</td>
								<td id="${expert.id }major">&nbsp;
						       		${expert.major}
						       		&nbsp;
					       		</td>
					       		<td id="${expert.id }jobTable">&nbsp;
						       		${expert.jobTable}
						       		&nbsp;
					       		</td>
					       		<td id="${expert.id }office">&nbsp;
						       		${expert.office}
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