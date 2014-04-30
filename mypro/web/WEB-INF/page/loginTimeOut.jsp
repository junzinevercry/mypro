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
$(document).ready(function(){
	window.top.location.href='${ctx}/login.action';
});
</script>
</head>

<body>
</body>
</html>