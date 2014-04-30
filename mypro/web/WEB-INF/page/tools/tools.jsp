<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common.jsp"%>
<title>web_demo</title>
<link rel="stylesheet" href="${ctx}/resource/css/login.css" type="text/css" media="all" />
<script>
	function calendar() {
		ShowIframe('万年历','calendar','${ctx}/calendar.action',900,600,true);
	}
	function calculator() {
		ShowIframe('计算器','calculator','${ctx}/calculator.action',900,600,true);
	}
</script>
</head>
<body bgcolor="#e1f4ff">
<div class="tools">
   <ul>
      <li><a class="pointer" onclick="calculator()"><img title="计算器" src="${ctx }/resource/images/login/jisuanqi.png"></a></li>
      <li><a class="pointer" onclick="calendar()"><img title="万年历" src="${ctx }/resource/images/login/rili.png"></a></li>
   </ul>
</div>  
</body>
</html>