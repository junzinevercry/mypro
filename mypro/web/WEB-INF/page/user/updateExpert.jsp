<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>web_demo</title>
<script>
function doCreateExpert(){
		$.ajax({
			type:'post',
			url:'${ctx}/user/doUpdateExpert.action',
			dataType:"json",
			data:$("#expert").serialize(),
			async:false,
			cache:false,
			success:function(data){
				if(data.type == 'success'){
					alert('操作成功');
					callBackFunc('','home','gotoExpertList');
					closeIframe();
				}
			}
		});
	}
</script >
</head>
<body bgcolor="#e1f4ff">
<div class="basecontent">
	<div class="tablebox">
		<input type="hidden" id="idJson" name="idJson">
		<form action="${ctx}/job/doUpdateCreateUser.action" id="expert" name="expert" method="post">
			<input type="hidden" id="id" name="id" value="${expertDto.id }">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #c8d6f1;">
				<thead>
					<tr>
						<td colspan="2"  class="font1">编辑专家信息</td>
					</tr>
				</thead>
				<tr>
			      	<td class="font4">专家姓名</td>
					<td class="font3" >
						<input type="text" size="60" value="${expertDto.userName }" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td class="font4">专业</td>
					<td class="font3">
						<input type="text" size="60" id="major" name="major" maxlength="30" value="${expertDto.major }"/>
					</td>
				</tr>
				<tr>
					<td class="font4">职称</td>
					<td class="font3">
						<input type="text" size="60" id="jobTable" name="jobTable" maxlength="30" value="${expertDto.jobTable }"/>
					</td>
				</tr>
				<tr>
					<td class="font4">职位</td>
					<td class="font3">
						<input type="text" size="60" id="office" name="office" maxlength="30" value="${expertDto.office }"/>
					</td>
				</tr>
				<tr>
	
		     		<td colspan="2" style="background: #bee3f9;" align="center" valign="middle"   >	
			             <img src="${ctx }/resource/images/button/oa_icon_button_open.png" onclick="doCreateExpert()">
			             <img src="${ctx }/resource/images/button/oa_icon_button_close.png" onclick="closeIframe()">
			        </td>
		     	</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>