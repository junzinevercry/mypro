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
	function gotoRoleList(){
		$("#searchForm").submit();
	}
	function selectcheck(box){
		var tbox = $(box);
		var id = tbox.attr("id");
		var name = $("#"+id+"name").text();
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
	function saveRole(){
		if($('#idJson').val()==''){
			alert('请选择用户角色！');
			return;
		}
		callBackFunc(roleArray,openerName,'addRole');
		closeIframe();
	}
</script>
</head>
<body bgcolor="#e1f4ff">
	<div class="basecontent">
		<div class="menu">
			<form action="${ctx }/role/gotoRoleListForCheckBox.action?openerName=${openerName}" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="idJson" name="idJson"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                    <li>角色名称：<input type="text" name="name" id="name" size="20" value="${queryRole.name }"/></li>
		                </ul>
		                <div class="clearer"></div>
		            </div>
		        </li>
		         <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="javascript:gotoRoleList()" /></li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td width="10%" class="font6">&nbsp;</td>
						<td width="45%" class="font6">角色名称</td>
						<td width="45%" class="font6">角色Code</td>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${fn:length(pageObject.pageList) == 0}">
						<tr>
							<td colspan="3" align="center">没有检索到任何数据</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="0" end="${fn:length(pageObject.pageList)-1}" step="1">
							
							<c:set var="roleDto" value="${pageObject.pageList[i]}" />
							<tr>
								<td><input type="checkbox" id="${roleDto.id }" onclick="selectcheck(this)"  <c:if test='${roleDto.selectType==1 }'>checked="checked"</c:if> ></td>
						       	<td id="${roleDto.id }name">${roleDto.name }</td>
						       	<td>${roleDto.code }</td>
							</tr>
						</c:forEach>
						<tr>
					        <td colspan="3" style="background: #bee3f9;" align="center" valign="middle">	
					        	<!--  <div class="button">
					            	<ul>
					                	<li onclick="saveRole()">确定</li>
					                    <li onclick="closeIframe()">取消</li>
					                </ul>
					            </div>-->
					            <img src="${ctx }/resource/images/button/oa_icon_button_yes.png" onclick="saveRole()">
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