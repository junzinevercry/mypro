<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function doCreateMenu() {
		if (confirm('确认创建菜单?')) {
			$.ajax({
				url: '${ctx}/menu/doCreateMenu.action',
				type:'post',
				data:$("#saveForm").serialize(),
				dataType:'json',
				success: function(data) {
					var result = data.info;
					if(result=="success"){
						callBackFunc('','frmright','gotoMenuList');
						alert('处理成功!');
						closeIframe('createMenu');
					} else if(result=="nameEmpty"){
						alert('菜单名称不能为空!');
						$("#name").focus();
					} else if(result=="urlEmpty"){
						alert('菜单地址不能为空!');
						$("#url").focus();
					} else if(result=="winNameEmpty"){
						alert('菜单窗口名称不能为空!');
						$("#winName").focus();
					} else if(result=="hasName"){
						alert('菜单名称已存在!');
						$("#name").focus();
					} else if(result=="hasUrl"){
						alert('菜单地址已存在!');
						$("#url").focus();
					} else if(result=="hasWinName"){
						alert('菜单窗口名称已存在!');
						$("#winName").focus();
					} else {
						alert('处理失败!');
					}
				}
			});
		}
	}
	function changeParent() {
		var pId = $("#pId").val();
		if(pId=='0'){
			$("#picTR").show();
		} else {
			$("#picTR").hide();
		}
	}
	function changePic(){
		$("#picDiv").html("<img src=\"${ctx }/resource/images/desktop/"+$("#picture").val()+"\"></img>");
	}
</script>
</head>
<body bgcolor="#e1f4ff">
	<form id="saveForm" name="saveForm" method="post" action="${ctx}/menu/doCreateMenu.action">
	<div class="tablebox">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #c8d6f1;">
			<thead>
		  	<tr>
		    	<td colspan="4" class="font1">菜单信息创建</td>
		    </tr>
			</thead>
			<tr>
				<td width="25%" class="font4" >菜单名称</td>
				<td width="75%" class="font10">
					<input type="text" id="name" name="name" maxlength="50"/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="25%" class="font4">菜单地址</td>
				<td width="75%" class="font10">
					<input type="text" id="url" name="url" maxlength="200"/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="25%" class="font4">菜单窗口名称</td>
				<td width="75%" class="font10">
					<input type="text" id="winName" name="winName" maxlength="200"/><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td width="25%" class="font4">上级菜单</td>
				<td width="75%" class="font10">
					<select id="pId" name="pId" onchange="javascript:changeParent()">
						<option value="0">无</option>
						<c:if test="${fn:length(menuList) > 0}">
							<c:forEach var="i" begin="0" end="${fn:length(menuList)-1}" step="1">
								<c:set var="menu" value="${menuList[i]}" />
								<option value="${menu.id }">${menu.name }</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="background: #bee3f9;" align="center" valign="middle">
					<!-- <div class="button">
						<ul>
			            	<li onclick="javascript:doCreateMenu()">确定</li>
			                <li onclick="javascript:closeIframe('createMenu')">关闭</li>
						</ul>
					</div>-->
					<img src="${ctx }/resource/images/button/oa_icon_button_yes.png" onclick="javascript:doCreateMenu()">			        
				    <img src="${ctx }/resource/images/button/oa_icon_button_close.png" onclick="javascript:closeIframe('createMenu')">
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>