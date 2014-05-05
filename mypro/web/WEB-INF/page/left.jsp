<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--框架必需start-->
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/framework.js"></script>
<script type="text/javascript" src="${ctx }/resource/script/popupClient.js"></script>
<link media="all" href="${ctx }/resource/lib/qui/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link media="all" href="${ctx }/resource/css/style.css" rel="stylesheet" type="text/css" />
<link media="all" href="${ctx }/resource/lib/qui/skins/blue/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function updateLeft(){
		/* $.ajax({
			type:'post',
			url:'${ctx}/updateLeft.action',
			dataType:"json",
			async:false,
			cache:false,
			success:function(data){
				$('#topic').text('未读消息：'+data.topic);
			}
		}); */
		refresh();
	}
	function gotoUserDetail(id){
		ShowIframe('员工信息','userDetail','${ctx}/user/gotoUserDetail.action',400,250,true);
	}
	function refresh(){
		window.location.reload();
	}
</script>
<!--框架必需end-->
 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>web_demo</title>
</head>

<body leftFrame="true" style="background-color:#fff">
<div style=" width:210px; float:left">
	
    <div class="user">
        <div class="user_title"></div>
    </div>
    <div class="left_t">
       <div class="list_user">
           <ul>
              <li><img src="${ctx }/resource/images/home/admin.png" align="middle" /> <span style="color:red;cursor:pointer" onclick="javascript:gotoUserDetail('${loginUser.id}')">${loginUser.realName }</span>，您好！<a href="javascript:updateLeft()"><img src="${ctx}/resource/images/login/icon.png" align="middle"  title=""></a></li>
           </ul>
       </div>
    </div>  
</div>
<div id="scrollContent" >
	<div class="listbox" style="bottom:0px;left:0px;overflow:hidden;position:fixed;margin-bottom:25px;">
       <div class="list">
           <ul>
           </ul>
       </div>
    </div> 	
</div>			
</body>
</html>