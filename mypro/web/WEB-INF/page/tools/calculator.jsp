<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<style type="text/css">
		        body,form,ul,li{margin:0; padding:0;}
		        body,p,th,td,span,div,a,input{color:#000000;font-size:9pt;font-family:Arial}
		        a{color:#000000;text-decoration:none}
		        a:hover{color:#ff0000;text-decoration:underline}
	
				.alink{float:left;display:block;padding:5px 10px;background:#def}
				.alink_hover{background:#fff}
	
				table{ border:solid 1px #def}
				table tr th{background:#def}
		</style>
	
		<script>
			function setttt(v){
				if('ca'==v){
					document.getElementById("mframe").src="${ctx }/calculator/common.action";
					document.getElementById("ca").className="alink alink_hover";
					document.getElementById("cb").className="alink";
				}else{
					document.getElementById("mframe").src="${ctx }/calculator/science.action";
					document.getElementById("ca").className="alink";
					document.getElementById("cb").className="alink alink_hover";
				}
			}
		</script>
	</head>
	<body>
		<br/>
		<h2 align="center">实用计算器</h2>
		<br/>
		<table align="center" cellpadding="0" cellspacing="0">
			<tr>
				<th>
					<a href="javascript:;" onclick="setttt('ca')" id="ca" class="alink">普通型</a>
					<a href="javascript:;" onclick="setttt('cb')" id="cb" class="alink alink_hover">科学型</a>
				</th>
			</tr>
			<tr>
				<td>
					<iframe frameBorder="0" id="mframe" scrolling="no" src="${ctx }/calculator/science.action" style="width:600px; height:500px;"></iframe>
				</td>
			</tr>
		</table>
	</body>
</html>