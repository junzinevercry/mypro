<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<title>万年历</title>
<style>
body,td,.p1,.p2,.i{font-family:arial;margin:0;padding:0}
body{background-color:#fff;color:#000;position:relative}
input{padding-top:0;padding-bottom:0;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box;}
table{border:0}
TD{FONT-SIZE:9pt;LINE-HEIGHT:18px;}
em{font-style:normal;color:#cc0000}
a em{text-decoration:underline;}

#cal{width:434px;border:1px solid #c3d9ff;font-size:12px;margin:auto}
#cal #top{height:29px;line-height:29px;background:#e7eef8;color:#003784;padding-left:70px;}
#cal #top select{font-size:12px;}
#cal #top input{padding:0;}
#cal ul#wk{margin:0;padding:0;height:25px;}
#cal ul#wk li{float:left;width:60px;text-align:center;line-height:25px;list-style:none;}
#cal ul#wk li b{font-weight:normal;color:#c60b02;}
#cal #cm{clear:left;border-top:1px solid #ddd;border-bottom:1px dotted #ddd;position:relative;}
#cal #cm .cell{position:absolute;width:42px;height:36px;text-align:center;margin:0 0 0 9px;}
#cal #cm .cell .so{font:bold 16px arial;}
#cal #bm{text-align:right;height:24px;line-height:24px;padding:0 13px 0 0;}
#cal #bm a{color:7977ce;}
#cal #fd{display:none;position:absolute;border:1px solid #dddddf;background:#feffcd;padding:10px;line-height:21px;width:150px;}
#cal #fd b{font-weight:normal;color:#c60a00;}
</style>
</head>
<body>
<table cellpadding="0" cellspacing="0" align="center">
<tr><td height="60">
	<font style="font-size:14px; line-height:2em;font-weight:bold;">万年历</font>
</td></tr>
<tr><td>
<div id="cal"><div id="top">公元&nbsp;<select></select>&nbsp;年&nbsp;<select></select>&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;农历<span></span>年&nbsp;[&nbsp;<span></span>年&nbsp;]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="回到今天" title="点击后跳转回今天" style="padding:0px"></div><ul id="wk"><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li><b>六</b></li><li><b>日</b></li></ul><div id="cm"></div><div id="bm"><a target="_blank" onMouseDown="return c({'fm':'alop','title':this.innerHTML,'url':this.href,'p1':al_c(this),'p2':1})" href="javascript:void(0)"></a></div></div>
<script src="${ctx }/resource/script/bdcalendar.js"></script>
</td></tr></table>
</body>
</html>