<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<script>
	function gotoRoleList(){
		$("#searchForm").submit();
	}
	function doDeleteRole(id){
		var msg = "您真的要删除该条记录吗？\n\n请确认 ！";
	    if (confirm(msg)==false){   
		  return;
		}
	    var currentPage = $("#currentPage").val();
	    $.ajax({
			  type: 'get',
			  url : '${ctx}/role/doDeleteRole.action',
			  dataType : "json",
			  data:{id:id,currentPage:currentPage},
			  async : false,
			  success:function(result){
			  	if(result.type=='success'){
			  		alert('操作成功');
			  		gotoRoleList();
			  	} else if(result.type=='nozero') {
			  		alert('有使用此角色的用户，不可进行删除！');
			  	}else{
			  		alert('操作失败');
			  	}
			  }
			});
	}
	function gotoUpdateRole(id){
		ShowIframe('角色信息修改','updateRole','${ctx}/role/gotoUpdateRole.action?id=' + id,990,450,true);
	}
	function gotoCreateRole(){
		ShowIframe('角色信息创建','createRole','${ctx}/role/gotoCreateRole.action',990,450,true);
	}
	
	
	
	function refresh(){
		refreshIframe('roleList');
	}
	
</script>
</head>
<body bgcolor="#e1f4ff">
	<div class="basecontent">
		<%@ include file="/common/subMenuList.jsp"%>
		<div class="menu">
			<form action="${ctx }/role/gotoRoleList.action" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                    <li>角色名称：<input type="text" name="name" id="name" size="20" value="${queryRole.name }"/></li>
					        <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="javascript:gotoRoleList()" /></li>
					        <li><img src="${ctx }/resource/images/button/oa_button_add.png" onclick="javascript:gotoCreateRole()"/></li>
		                </ul>
		            </div>
		        </li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<td width="45%" class="font1">角色名称</td>
						<td width="45%" class="font1">角色Code</td>
						<td width="10%" class="font1">操作</td>
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
							<c:set var="role" value="${pageObject.pageList[i]}" />
							<tr class="${i % 2 == 0 ? 'table_tr' : 'table_tr_bg'}">
						       	<td>&nbsp;
						       		${elf:setStrongInterceptString(role.name,queryRole.name,color)}
						       		&nbsp;</td>
						       	<td>&nbsp;${role.code }&nbsp;</td>
						       	<td>
						       		<img title="修改" src="${ctx }/resource/images/control/edit.png" width="20" height="20" onclick="javascript:gotoUpdateRole('${role.id}')"/>
						       		<img title="删除" src="${ctx }/resource/images/control/delete.png" width="20" height="20" onclick="javascript:doDeleteRole('${role.id}')"/>
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