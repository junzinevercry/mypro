<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<script type="text/javascript">
	var openerName = '${openerName}';
	function saveUser(){
		var password = $('#password').val();
		if(password==''){
			alert('请填写密码。');
			return;
		}
		if(password.length<6){
			alert("密码长度最少6位。");
			return;
		}
		$.ajax({
			type:'post',
			url:'${ctx}/user/doCheckPassword.action',
			dataType:"json",
			data:{password:password},
			async:false,
			cache:false,
			success:function(data){
				if(data.type=='ok'){
					alert("密码验证通过！");
					var str = "<img src='"+data.filePath+"'/>";
					callBackFunc(str,openerName,'addSignFile');
					closeIframe();
				}else if(data.type == 'error'){
					alert("密码输入错误！");
				}
			}
		});
	}
	$(document).ready(function(){
		if('${passwordType}'=='no'){
			alert('用户无电子签名密码，如需设置请联系管理员！');
			callBackFunc('',openerName,'addSignFile');
			closeIframe();
		}
	});
</script>
</head>
<body>
	<div class="basecontent">
		<div class="boss" >当前位置：核对电子签章密码</div>
		<div class="tablebox">
			<form action="" method="post" id="saveform" name="saveform">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td colspan="2"  class="font1">核对电子签章密码</td>
						</tr>
					</thead>
					<tr>
				      	<td class="font6">请输入密码</td>
						<td class="font3"><input type="password" id="password" name="password"></td>
			     	</tr>
			     	<tr>
				        <td colspan="2" style="background: #bee3f9;" align="center" valign="middle">	
				            <img src="${ctx }/resource/images/button/oa_icon_button_yes.png" onclick="saveUser()">			        
				            <img src="${ctx }/resource/images/button/oa_icon_button_qx.png" onclick="closeIframe()">
				        </td>
			      	</tr>
			    </table>
		    </form>
		</div>
	</div>
</body>
</html>