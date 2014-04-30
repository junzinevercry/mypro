<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" media="all" href="${ctx }/resource/css/login.css" />
</head>
<body background="${ctx }/resource/images/404_bg.png" >
   <div class="center">
        <div class="centerbody">
           <div class="centerbody_r">
                <div class="c_r">
                    <ul>
                      <!--  <li>您要查看的网址可能已被删除或者暂时不可用。</li>
                       <li>点击以下链接继续浏览网站</li>
                       <li>>> <a href="">返回上一级页面</a></li>
                       <li>>> <a href="">返回首页</a></li>
                        -->
                    </ul>
                </div>
           </div>
        </div>
   </div>
</body>
</html>