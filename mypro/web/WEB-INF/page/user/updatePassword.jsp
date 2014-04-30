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
		if($('#oldpassword').val()==''){
			alert('请填写旧密码！');
			return;
		}
		if($('#newpassword').val()==''){
			alert('请填写新密码！');
			return;
		}
		if($('#newpassword2').val()==''){
			alert('请填写确认密码！');
			return;
		}
		if($('#newpassword').val()!=$('#newpassword2').val()){
			alert('两次密码不相同！');
			return;
		}
		$.ajax({
			type:'post',
			url:'${ctx}/user/doUpdatePassword.action',
			dataType:"json",
			data:$("#saveform").serialize(),
			async:false,
			cache:false,
			success:function(data){
				if(data.type=='success'){
					alert("操作成功！");
					closeIframe()
				}else if(data.type == 'noequals'){
					alert("密码输入错误！");
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="basecontent">
		<div class="boss" >当前位置：修改密码</div>
		<div class="tablebox">
			<form action="" method="post" id="saveform" name="saveform">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td colspan="2"  class="font1">修改密码</td>
						</tr>
					</thead>
					<tr>
				      	<td class="font6">旧密码</td>
						<td class="font3"><input type="password" id="oldpassword" name="oldpassword" maxlength="10"></td>
			     	</tr>
			     	<tr>
				      	<td class="font6">新密码</td>
						<td class="font3"><input type="password" id="newpassword" name="newpassword" maxlength="10"></td>
			     	</tr>
			     	<tr>
				      	<td class="font6">确认密码</td>
						<td class="font3"><input type="password" id="newpassword2" name="newpassword2" maxlength="10"></td>
			     	</tr>
			     	<tr>
				        <td colspan="2" style="background: #bee3f9;" align="center" valign="middle">	
				        	<!--  <div class="button2">
				            	<ul>
				                	<li onclick="saveUser()">确定</li>
				                    <li onclick="closeIframe()">取消</li>
				                </ul>
				            </div>-->
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