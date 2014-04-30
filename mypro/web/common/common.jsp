<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="elf" uri="/WEB-INF/elfunc.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx }/resource/script/pagination.js"></script>
<script type="text/javascript" src="${ctx }/resource/lib/jquery/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ctx }/resource/lib/jquery/jquery.jtextcount.js"></script>
<script type="text/javascript" src="${ctx }/resource/script/popupClient.js"></script>
<script type="text/javascript" src="${ctx }/resource/script/validate.js"></script>
<script type="text/javascript" src="${ctx }/resource/script/sending.js"></script>
<link media="all" href="${ctx }/resource/css/base.css" type="text/css" rel="stylesheet">
<link media="all" href="${ctx }/resource/css/append.css" type="text/css" rel="stylesheet">
<%
	request.setAttribute("serverUrl",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/");
%>
<c:set var="color" value="FFBA12"/>
<link rel="SHORTCUT ICON" href="${ctx }/resource/images/logo.ico"/>