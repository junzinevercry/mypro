<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<script>
	function gotoMenuList(){
		$("#searchForm").submit();
	}
	function doDeleteMenu(id){
		var msg = "您真的要删除该条记录吗？\n\n请确认 ！";
	    if (confirm(msg)==false){   
		  return;
		}
	    var currentPage = $("#currentPage").val();
	    $.ajax({
			  type: 'get',
			  url : '${ctx}/menu/doDeleteMenu.action',
			  dataType : "json",
			  data:{id:id,currentPage:currentPage},
			  async : false,
			  success: function(data) {
				var result = data.info;
			  	if(result=="success"){
			  		alert('操作成功');
			  		gotoMenuList();
			  	} else {
			  		alert('操作失败');
			  	}
			  }
			});
	}
	function gotoUpdateMenu(id){
		ShowIframe('菜单信息修改','updateMenu','${ctx}/menu/gotoUpdateMenu.action?id=' + id,600,300,true);
	}
	function gotoCreateMenu(){
		ShowIframe('菜单信息创建','createMenu','${ctx}/menu/gotoCreateMenu.action',600,300,true);
	}
	
	
	
	function refresh(){
		$("#winName").val($("#winName").val());
		top.reloadHome();
		refreshIframe('menuList');
	}
	
</script>
</head>
<body bgcolor="e7f3fc"> 
	<div class="basecontent">
		<%@ include file="/common/subMenuList.jsp"%>
		<div class="menu">
			<form action="${ctx }/menu/gotoMenuList.action" method="post" id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
			<input type="hidden" id="winName" name="winName" value="${winName}"/>
			<ul>
		    	<li>
		        	<div class="search">
		            	<ul>
		                	<li><img src="${ctx }/resource/images/page/icon_search.png" />查询条件 </li>
		                    <li>菜单名称：<input type="text" name="name" id="name" size="20" value="${queryMenu.name }"/></li>
					        <li><img src="${ctx }/resource/images/button/oa_button_search.png" onclick="javascript:gotoMenuList()" /></li>
					        <li><img src="${ctx }/resource/images/button/oa_button_add.png" onclick="javascript:gotoCreateMenu()"/></li>
		                </ul>
		            </div>
		        </li>
		    </ul>
		    </form>
		    <div class="clearer"></div>
		</div>
		<div class="tablebox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border:1px solid #c8d6f1">
				<thead>
					<tr>
						<td width="30%" class="font1">菜单名称</td>
						<td width="30%" class="font1">菜单地址</td>
						<td width="30%" class="font1">上级菜单</td>
						<td width="10%" class="font1">操作</td>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${fn:length(pageObject.pageList) == 0}">
						<tr>
							<td colspan="4" align="center">没有检索到任何数据</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="0" end="${fn:length(pageObject.pageList)-1}" step="1">
							<c:set var="menu" value="${pageObject.pageList[i]}" />
							<tr class="${i % 2 == 0 ? 'table_tr' : 'table_tr_bg'}">
						       	<td>&nbsp;
						       		${elf:setStrongInterceptString(menu.name,queryMenu.name,color)}
						       		&nbsp;
					       		</td>
						       	<td>&nbsp;${menu.url }&nbsp;</td>
						       	<td>&nbsp;
						       		<c:if test="${fn:length(menuList) > 0}">
										<c:forEach var="i" begin="0" end="${fn:length(menuList)-1}" step="1">
											<c:set var="menuParent" value="${menuList[i]}" />
											<c:choose>
												<c:when test="${menu.pId==menuParent.id }">
													${menuParent.name }
												</c:when>
											</c:choose>
										</c:forEach>
									</c:if>&nbsp;
					       		</td>
						       	<td>
						       		<img title="修改" src="${ctx }/resource/images/control/edit.png" width="20" height="20" onclick="javascript:gotoUpdateMenu('${menu.id}')"/>
						       		<img title="删除" src="${ctx }/resource/images/control/delete.png" width="20" height="20" onclick="javascript:doDeleteMenu('${menu.id}')"/>
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