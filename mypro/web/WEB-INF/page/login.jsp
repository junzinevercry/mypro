<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>web_demo</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link rel="stylesheet" href="${ctx}/resource/css/login.css" type="text/css" media="all" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="SHORTCUT ICON" href="${ctx }/resource/images/logo.ico"/>
	<script type="text/javascript" src="${ctx }/resource/lib/jquery/jquery-1.6.2.js"></script>
	<script type="text/javascript">
		function doLogin() {
			if($("#j_username").val()==''){
				alert('请输入用户名');
				return;
			}
			if($("#j_password").val()==''){
				alert('请输入密码');
				return;
			}
			
			if(!$("#yanzheng").val()){
				alert('请输入验证码');
				return;
			}
			
			$.ajax({
				url:'${ctx}/PubMsgServlet',
				type:'post',
				data:{verifyCode:$("#yanzheng").val()},
				success: function(result) {
					if(result=="false"){
						$("#yanzheng").val('');
						$("#errorMsg").html("验证码不正确,请重新输入!");
						changeVerifyCode();
						return;
					}
					else if(result=="true"){
						$("#errorMsg").html("");
						$("#loginForm").submit();
					}
				}
			});
			
			
			
		}
		function resertLogin(){
			$("#j_username").val('');
			$("#j_password").val('');
		}
		function initFoucus() {
		   var username  = document.forms(0).elements["j_username"];
		   username.select();
		   username.focus(); 
		}
		
		function changeVerifyCode(){
			t=new Date().getTime();
			    $("#yanZhengPic").attr('src',"${ctx}/VerifyCodeServlet?t="+t);
		}
		
		
	</script>
</head>

<body onload="initFoucus();" <%-- background="${ctx}/resource/images/indexbj.jpg" --%> onkeydown="if(event.keyCode=='13') doLogin();">
<form name="loginForm" id="loginForm" action="${ctx}/j_spring_security_check" method="post" >
   <div class="container">
       <div class="top">
           <div id="top">
               <div class="top_logo"></div>
           </div>
       </div>
       <!-- top end-->
       <div class="content">
           <div id="content">
               <div class="login_l">
                   <div class="login_user">
                         <table width="388px" border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td class="font1">用户名：</td>
                              </tr>
                              <tr>
                                <td>
                                <input type="text" id="j_username" name="j_username" value="" size="50" style="height:27px"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="font1">密码：</td>
                              </tr>
                              <tr>
                                <td>
                                <input type="password" id="j_password" name="j_password" value="" size="50" style="height:27px"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="font1">验证码：</td>
                              </tr>
                              <tr>
                                <td>
                                	<input name="yanzheng" id="yanzheng" maxlength="5" size="18"  style="height:26px"/><img id="yanZhengPic" src="${ctx}/VerifyCodeServlet" /><a href="javascript:changeVerifyCode()"><img src="${ctx}/resource/images/login/icon1.png"></a><span><a href="javascript:changeVerifyCode()">看不清？换一张</a></span>
                               	</td>
                              </tr>
                              <tr>
		                         <td>
		                        	<div id="errorMsg" style="color:red">
		                         	<c:if test="${param.error_code==1 }">
		                        		 用户名或密码无效
		                        	</c:if>
		                        	</div>
		                         </td>
		                       </tr>
                              <tr>
                                <td ><img src="${ctx }/resource/images/login/login_button.png" style="margin-left:100px;" onclick="javascript:doLogin()" /></td>
                              </tr>
                        </table>

                   </div>
               </div>
               <div class="login_r">
                   <div class="r_text">
                       <p>description</p>
                      
                   </div>
               </div>
           </div>
           <div class="clearer"></div>
       </div>
       <!-- content end-->
       <div id="footer">
           <div class="footer">
              <ul>
                  <li>Copyright © </li>
                  <li>information</li>
              </ul>
           </div>
       </div>
       <!-- footer end -->
   </div>
</form>
</body>
</html>