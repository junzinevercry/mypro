﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>web_demo</title>
<script type="text/javascript" src="${ctx }/resource/lib/jquery/jquery-1.6.2.js"></script>
<link rel="SHORTCUT ICON" href="${ctx }/resource/images/logo.ico"/>
<!--框架必需start-->
<link media="all" href="${ctx }/resource/lib/qui/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link media="all" href="${ctx }/resource/lib/qui/skins/blue/style.css" rel="stylesheet" type="text/css" />
<link media="all" href="${ctx }/resource/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/bsFormat.js"></script>
<!--框架必需end-->

<!--引入弹窗组件star-->
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/popup/drag.js"></script>
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->

<!--分隔条star-->
<script type="text/javascript" src="${ctx }/resource/lib/qui/js/nav/spliter.js"></script>
<!--分隔条end-->

<script type="text/javascript" src="${ctx}/resource/lib/lhgdialog/lhgdialog.js?skin=chrome&self=true"></script>
<script type="text/javascript" src="${ctx }/resource/script/popupServer.js"></script>

<!--菜单star-->
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/menu.css">
<script type="text/javascript" src="${ctx }/resource/script/menu.js"></script>
<!--菜单end-->

<script>
document.onkeydown = killesc;

function   killesc()

{   
	if(window.event.keyCode==27)   
	{   
		window.event.keyCode=0;   
		window.event.returnValue=false;   
	}   
} 

function bookmarksite(title, url){
    if (window.sidebar) // firefox
        window.sidebar.addPanel(title, url, "");
    else 
        if (window.opera && window.print) { // opera
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }
        else 
            if (document.all)// ie
                window.external.AddFavorite(url, title);
}
function exitHandler(){

	top.Dialog.confirm("确定要退出系统吗",function(){});
}
var initNum = 0;
function gotoPage(num,url,winName){
	if(url==''){
		return;
	}
	$("#"+initNum).attr("style","background:url(${ctx }/resource/images/home/banner.png);color:#1d6f97 ");
	$("#"+num).attr("style","background:url(${ctx }/resource/images/home/banner_bg_hover.png) no-repeat;color:#fff");
	initNum = num;
	var linkStr = '?';
	if(url.indexOf('?')!=-1){
		linkStr = '&';
	}
	$("iframe[name=frmright]").attr("src",url+linkStr+"winName="+winName);
}
function gotoUpdatePassword(){
	ShowIframe('修改密码','updatePassword','${ctx}/user/gotoUpdatePassword.action',400,250,true);
}
function gotoUpdateSignPassword(){
	ShowIframe('修改签名密码','updateSignPassword','${ctx}/user/gotoUpdateSignPassword.action',400,250,true);
}
function gotoTools(){
	ShowIframe('常用工具','tools','${ctx}/tools.action',400,250,true);
}

function reloadIndex(){
	window.location.href=window.location.href;
}
function refreshLeft(){
	$("#frmleft").attr("src",$("#frmleft").attr("src"));
}
window.setInterval(function(){
	
	$basecontent=$($("#frmright")[0].contentWindow.document).find(".basecontent");
	
	if($basecontent.size()>0){
		if($basecontent.height()!=$("#bs_right").height()){
		$basecontent.height($("#bs_right").height());
		}
	}
	
	
}, 100);
</script>
</head>
<body>
	<div id="mainFrame">
	<!--头部与导航start-->
		<div id="hbox">
			<div id="bs_bannercenter">
				<div class="bs_logo"></div>
				<div class="bs_right">
					<ul>
						<li style="padding-top:15px;"><a href="javascript:void(0)" onclick="gotoTools()"><img src="${ctx }/resource/images/home/cook.png" /></a></li>
						<li style="padding-top:15px;"><a href="javascript:void(0)" onclick="gotoUpdatePassword()"><img src="${ctx }/resource/images/home/edit.png" /></a></li>
						<c:if test="${not empty signPassword}">
							<li style="padding-top:15px;"><a href="javascript:void(0)" onclick="gotoUpdateSignPassword()"><img src="${ctx }/resource/images/home/edit_name.png" /></a></li>
						</c:if>
						<li style="padding-top:13px;"><a href="${ctx }/logout.action"><img src="${ctx }/resource/images/home/exit.png" /></a></li>
					</ul>
				</div>
			</div>
			

    
    <div class="top">
        <ul class="menu">
		<c:if test="${fn:length(menuList) > 0}">
			<c:forEach var="i" begin="0" end="${fn:length(menuList)-1}" step="1">
				<c:set var="menuDto" value="${menuList[i]}" />
				<c:if test="${i==0 }">
					<li id="${i }" style="background:url(${ctx }/resource/images/home/banner_bg_hover.png) no-repeat; color:#fff">
				</c:if>
				<c:if test="${i > 0 }">
					<li style="background:url(${ctx }/resource/images/home/banner.png);color:#1d6f97" id="${i }" >
				</c:if>
					<c:if test="${fn:length(menuDto.subMenu) == 0}">
						<a class="tablink nosub" onclick="javascript:gotoPage('${i }','${menuDto.url }','${menuDto.winName }')">${menuDto.name }</a>
					</c:if>
					<c:if test="${fn:length(menuDto.subMenu) > 0}">
						<a class="tablink ">${menuDto.name }</a>
						<ul>
							<c:forEach var="j" begin="0" end="${fn:length(menuDto.subMenu)-1}" step="1">
							<c:set var="menu" value="${menuDto.subMenu[j]}" />
								<li>
									<a onclick="javascript:gotoPage('${i }','${menu.url }','${menu.winName }')">${menu.name }</a>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</li>
			</c:forEach>
		</c:if>
        </ul>
    </div>
<!--代码结束-->


			
			</div>
		</div>
	<!--头部与导航end-->
		<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
			<tr>
			<!--左侧区域start-->
				<td id="hideCon" class="ver01 ali01">
					<div id="lbox">
						<div id="lbox_topcenter">
							<div id="lbox_topleft">
								<div id="lbox_topright">
								</div>
							</div>
						</div>
						<div id="lbox_middlecenter">
							<div id="lbox_middleleft">
								<div id="lbox_middleright">
									<div id="bs_left">
										<IFRAME scrolling="no" width="100%"  frameBorder=0 id=frmleft name=frmleft src="${ctx }/left.action"  allowTransparency="true"></IFRAME>
									</div>
									<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
								</div>
							</div>
						</div>
						<div id="lbox_bottomcenter">
							<div id="lbox_bottomleft">
								<div id="lbox_bottomright">
									<div class="lbox_foot"></div>
								</div>
							</div>
						</div>
					</div>
				</td>
			<!--左侧区域end-->
			
			<!--分隔栏区域start-->
				<td class="spliter main_shutiao" targetId="hideCon" beforeClickTip="" afterClickTip="" beforeClickClass="bs_leftArr" afterClickClass="bs_rightArr">
		        </td>

			<!--分隔栏区域end-->
			
			<!--右侧区域start-->
				<td class="ali01 ver01"  width="100%">
					<div id="rbox">
						<div id="rbox_topcenter">
							<div id="rbox_topleft">
								<div id="rbox_topright">
								</div>
							</div>
						</div>
						<div id="rbox_middlecenter">
							<div id="rbox_middleleft">
								<div id="rbox_middleright">
									<div id="bs_right">
								       <IFRAME scrolling="no" width="100%" frameBorder=0 id=frmright name=frmright src="${ctx }/home.action"  allowTransparency="true"></IFRAME>
									</div>
								</div>
							</div>
						</div>
						<div id="rbox_bottomcenter" >
							<div id="rbox_bottomleft">
								<div id="rbox_bottomright">
								</div>
							</div>
						</div>
					</div>
				</td>
			<!--右侧区域end-->
			</tr>
		</table>
	
	<!--尾部区域start-->
		<div id="fbox">
			<div id="bs_footcenter">
	    		<div class="footer">
	        		<ul>
	           			<li>Copyright © </li>
	           			<li>information</li>
	        		</ul>
	    		</div>
			</div>
		</div>
	</div>
<!--尾部区域end-->

<!--浏览器resize事件修正start-->
	<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
	<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->
</body>
</html>
