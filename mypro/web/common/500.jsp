<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx }/resource/style/error/error.css" type="text/css" media="all" />
	<script src="${ctx }/resource/lib/jquery/jquery-1.6.2.js" type="text/javascript"></script>
	<link rel="SHORTCUT ICON" href="${ctx }/resource/images/logo.ico"/>
</head>
<body> 
	<div class="basecontent">
		<div class="errorbox500">
			<p style="position:absolute;top:260px;left:480px;"><img src="${ctx }/resource/images/error/404_dump.gif" /></p>
	        <p class="backred"><a href="${ctx }/login.action">点击这里返回</a></p>
	    </div>
	</div>
</body>
</html>