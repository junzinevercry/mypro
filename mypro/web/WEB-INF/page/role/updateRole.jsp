<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="${ctx }/resource/lib/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/resource/lib/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/resource/lib/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx }/resource/lib/zTree/js/jquery.ztree.excheck-3.5.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function doUpdateRole() {
		if (confirm('确认修改角色?')) {
			getTreeElement();
			$.ajax({
				url: '${ctx}/role/doUpdateRole.action',
				type:'post',
				data:$("#saveForm").serialize(),
				//dataType:'json',
				success: function(result) {
					if(result=='success'){
						callBackFunc('','home','gotoRoleList');
						alert('处理成功!');
						closeIframe('updateRole');
					} else if (result=='nameEmpty'){
						alert('角色名称不能为空!');
						$("#name").focus();
					} else if (result=='codeEmpty'){
						alert('角色Code不能为空!');
						$("#code").focus();
					} else if (result=='hasName'){
						alert('角色名称已存在!');
						$("#name").focus();
					} else if (result=='hasCode'){
						alert('角色Code已存在!');
						$("#code").focus();
					} else if (result=='menuEmpty'){
						alert('请选择该角色能看到的菜单!');
					} else {
						alert('处理失败!');
					}
				}
			});
		}
	}

	var setting = {
			view: {
				showIcon: showIconForTree
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes = eval('(' + '${menuJSON }' + ')');
		
		var code;

		function showIconForTree(treeId, treeNode) {
			return !treeNode.isParent;
		};

		function getTreeElement(){
			var zTree = $.fn.zTree.getZTreeObj("menu"),
			nodes = zTree.getCheckedNodes(true);
			v = "";
			for (var i = 0, l = nodes.length; i<l; i++){
				if(i > 0){
					v += ',';
				}
				v += nodes[i].id;
			}
			$("#menuIds").val(v);
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#menu"), setting, zNodes);
		});
</script>
</head>
<body bgcolor="#e1f4ff">
<form id="saveForm" name="saveForm" method="post" action="${ctx}/role/doUpdateRole.action">
	<input id="menuIds" name="menuIds" type="hidden" value="" />
	<input id="id" name="id" value="${role.id}" type="hidden"/>
	<div class="tablebox">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #c8d6f1;">
			<thead>
		  	<tr>
		    	<td colspan="4" class="font1">角色信息修改</td>
		    </tr>
			</thead>
			<tr>
				<td width="25%" class="font4">角色名称</td>
				<td width="75%" class="font3">
					<input type="text" id="name" name="name" value="${role.name }" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="font4">角色Code</td>
				<td width="75%" class="font3">
					<input type="text" id="code" name="code" value="${role.code }" maxlength="200"/>
				</td>
			</tr>
			<tr>
				<td width="25%" class="font4">角色菜单</td>
				<td width="75%" class="font3">
				    <div class="content_wrap">
						<div>
							<ul id="menu" class="ztree"></ul>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="background: #bee3f9;">
					<!--  <div class="button">
						<ul>
			            	<li onclick="javascript:doUpdateRole()">确定</li>
			                <li onclick="javascript:closeIframe('updateRole')">取消</li>
						</ul>
					</div>-->
					<img src="${ctx }/resource/images/button/oa_icon_button_yes.png" onclick="javascript:doUpdateRole()">			        
				    <img src="${ctx }/resource/images/button/oa_icon_button_qx.png" onclick="javascript:closeIframe('updateRole')">
				</td>
			</tr>
		</table>
		
	</div>
	
</form>
</body>
</html>