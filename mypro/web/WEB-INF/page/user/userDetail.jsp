<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.Job/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>web_demo</title>
<script>
</script >
</head>
<body>
<div class="basecontent">
	<div class="tablebox">
		<input type="hidden" id="idJson" name="idJson">
		<form action="${ctx}/j2ob/doCreateUser.action" id="userInfo" name="userInfo" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #c8d6f1;">
				<thead>
					<tr >
						<td colspan="2" style=" border-bottom:1px solid #c8d6f1;"><div align="left" style=" margin-left:20px;">用户信息</div></td>
					</tr>
				</thead>
				<tr>
					<td class="font3" style=" background:#eef9ff;">人员名称</td>
					<td class="font3" align="left">
						${loginUser.name }
					</td>
				</tr>
				<tr>
					<td class="font3" style=" background:#eef9ff;">部门</td>
					<td class="font3" align="left">
						${loginUser.deptName }
					</td>
				</tr>
				<tr>
					<td class="font3" style=" background:#eef9ff;">职位</td>
					<td class="font3" align="left">
						${loginUser.jobName }
					</td>
				</tr>
				<tr>
		     		<td colspan="2" style="background: #bee3f9;" align="center" valign="middle">	
			        	
			             <img src="${ctx }/resource/images/button/oa_icon_button_close.png" onclick="closeIframe()">
			        </td>
		     	</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>