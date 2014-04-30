//屏蔽IE的部分按键
/*
	* 注意：eval中控件名称不能包含下划线，不能以数字开始。
	* File name: util.js
	* Author: 马现福
	* Date: 08-03-2011
	* 目录创建日期：08-03-2011
	* 以下为页面焦点控制用到的方法
	*1. setSubmitFunction 设置需要提交的方法 
	*2. firstFocus 第一个焦点选中控件
	*3. keyDown 按健后的处理方法
	*4. executeSubmitFunction 执行需要提交的方法
 */
var gnIsSelectCtrl=0;
var gnIsShowSending=0;//没有ShowSending
var nextfield="";
var gSubmitFunction=""; 
var gFormName = "";
//第一个获得焦点的控件名称，作为在调用executeSubmitFunction时转移焦点，防止重复回车多次保存记录
var gFirstFocusCtrlName="";

/**
 * 设置需要提交的方法
 * @param strFunction 方法
 */
function setSubmitFunction(strFunction)
{
	gSubmitFunction=strFunction;
}

function setFormName(strFormName)
{
	gFormName=strFormName;
}

/**
 * 第一个控件选中
 * @param obj 控件
 */
function firstFocus(obj)
{
	//设置第一个焦点时，把控件名称保存到全局变量中。
	gFirstFocusCtrlName=obj;
	
	if (gFirstFocusCtrlName != "")
	{
		obj.focus();
	}
}

document.onkeydown = keyDown; // work together to analyze keystrokes

/**
 * 按健后的处理方法
 */
function keyDown(DnEvents)
{ 
	if(gnIsShowSending==0)//如果没有显示执行，才处理keydown
	{
		// handles keypress
		// determines whether Netscape or Internet Explorer
		var k = window.event.keyCode;
		var oElement = window.event.srcElement;
		if (k == 13 && oElement.tagName != "TEXTAREA")
		{
			// enter key pressed
			if(gnIsSelectCtrl==0)
			{
				if (nextfield == 'submitfunction')
			 	{
			 		executeSubmitFunction();
					return false;
			    } // submit, we finished all fields
				else
				{ // we're not done yet, send focus to next box
					if(nextfield=="")
					{
				 		executeSubmitFunction();
						return false;
					}
					else
					{
						eval('document.' + gFormName + '.' + nextfield + '.focus()');
						//文本框选中
						if(!isNaN(eval('document.' + gFormName + '.' + nextfield + '.maxLength')))
						{
							eval('document.' + gFormName + '.' + nextfield + '.select()');
						}
						return false;
					}
	     		}
		    }
		    else
		    {
		    	gnIsSelectCtrl=0;
		    	return false;
		    }
	  	}
	}
}


/**
 * 执行需要提交的方法
 */
function executeSubmitFunction()
{
	//gFirstFocusCtrlName为页面上第一个获得焦点的空件名称，在调用 firstFocus时负值，为全局变量
	if (gFirstFocusCtrlName != "" )
	   gFirstFocusCtrlName.focus();
	eval(gSubmitFunction);

}

//配置是否使用屏蔽鼠标右键(true为使用，false为不使用)
var bContextMenu = false;
//配置是否使用屏蔽鼠标左键拖动(true为使用，false为不使用)
var bSelectStart = false;
//配置是否使用屏蔽F1帮助(true为使用，false为不使用)
var bHelp = false;
//配置是否使用屏蔽部分IE快捷键(true为使用，false为不使用)
var bKeydown = true;
	
if(bContextMenu){
	//屏蔽  鼠标右键(不包含Input type="text"和textarea)
	window.document.oncontextmenu = function(){
		var oElement = window.event.srcElement;
		if(oElement.tagName == "INPUT"){
			if(oElement.type == "text" ){
				if(!oElement.disabled){
					return true;
				}
			}
		}
		if(oElement.tagName == "TEXTAREA") {
			return true;
		}
		return false;
	};
}

if(bSelectStart){
	//屏蔽  鼠标左键拖动(不包含Input type="text"和textarea)
	window.document.onselectstart = function(){
		var oElement = window.event.srcElement;
		if(oElement.tagName == "INPUT"){
			if(oElement.type == "text"){
				return true;
			}
		}
		if(oElement.tagName == "TEXTAREA") {
			return true;
		}
		return false;
	};
}

if(bHelp){
	//屏蔽  F1帮助  
	window.onhelp = function(){
		window.event.keyCode = 505;
		return false;
	};
}

if(bKeydown){
	window.document.onkeydown = function(){  
		//屏蔽  Alt + 方向键 ←  
		//屏蔽  Alt + 方向键 →
		if((window.event.altKey) && ((window.event.keyCode==37) || (window.event.keyCode==39))){
			window.event.keyCode = 505;
			return false;
		}
	
		//屏蔽  BackSpace(不包含Input type="text"和textarea)
		if(window.event.keyCode==8){
			var oElement = window.event.srcElement;
			/*alert(oElement.type);*/
			if(oElement.tagName == "INPUT"){
				if(oElement.type == "text"){
					return true;
				}
			}
			if(oElement.tagName == "INPUT"){
				if(oElement.type == "password"){
					return true;
				}
			}
			if(oElement.tagName == "TEXTAREA") {
				return true;
			}
			window.event.keyCode = 505;
			return false;
		}
		
		//屏蔽  F2 - F12 键
		//if((window.event.keyCode>=113) && (window.event.keyCode<=123)){
		//	window.event.keyCode = 505;
	   //     return false; 
		//}
		
		//屏蔽  Ctrl + (a - z (不包含c、v、a、x))键
		if(window.event.ctrlKey && ((window.event.keyCode>=65) && (window.event.keyCode<=90))){
			if((window.event.keyCode == 67) || (window.event.keyCode == 86)){
				return true;
			}
			if((window.event.keyCode == 65) || (window.event.keyCode == 88)){
				return true;
			}
			window.event.keyCode = 505;
	        return false; 
		}
		
		//屏蔽  Ctrl + (F1 - F12)键
		//if(window.event.ctrlKey && ((window.event.keyCode>=112) && (window.event.keyCode<=123))){
		//	window.event.keyCode = 505;
	     //   return false; 
		//}
	};
}

//当(window)窗口改变大小时
window.onresize = function(){
	try{
		var oBody = window.document.body;
		var oDivSending = window.document.getElementById("sending");
		var oDivBackground = window.document.getElementById("sendingBackground");
		var oDivSendingCurrency = window.document.getElementById("sendingCurrency");
	
		if((oBody != null) && (oDivSending != null) && (oDivBackground != null)){
			//网页正文全文高
			var bodyHeigth = oBody.scrollHeight;
			//网页正文全文宽
			var bodyWidth = oBody.scrollWidth;
			//网页可见区域高
			var displayHeight = oBody.clientHeight;
			//网页可见区域宽
			var displayWidth = oBody.clientWidth;
			//DivSending高
			var divSendingHeight = $("#sending").height();
	
			//获取oBody的位置
			var bbPosition = $("body").offset();
			
			//设置oDivBackground
			oDivBackground.style.height = bodyHeigth;
			oDivBackground.style.width = bodyWidth;
	
			//设置DivSending位置
			var divSendingLeft = bbPosition.left;
			//var divSendingTop = displayHeight/2 - divSendingHeight + oBody.scrollTop;
			var divSendingTop = 0;
			if(displayHeight <= divSendingHeight){
				divSendingTop = 0;
			}
			else {
				divSendingTop = displayHeight/2 - divSendingHeight/2;
			}
			oDivSending.style.left =divSendingLeft;
			oDivSending.style.top = divSendingTop;
		}
		
		if((oBody != null) && (oDivSendingCurrency != null) && (oDivBackground != null)){
			//网页正文全文高
			var bodyHeigth = oBody.scrollHeight;
			//网页正文全文宽
			var bodyWidth = oBody.scrollWidth;
			//网页可见区域高
			var displayHeight = oBody.clientHeight;
			//网页可见区域宽
			var displayWidth = oBody.clientWidth;
			//DivSending高
			var divSendingHeight = $("#sendingCurrency").height();
	
			//获取oBody的位置
			var bbPosition =  $("body").offset();
			
			//设置oDivBackground
			oDivBackground.style.height = bodyHeigth;
			oDivBackground.style.width = bodyWidth;
	
			//设置DivSending位置
			var divSendingLeft = bbPosition.left;
			//var divSendingTop = displayHeight/2 - divSendingHeight + oBody.scrollTop;
			var divSendingTop = 0;
			if(displayHeight <= divSendingHeight){
				divSendingTop = 0;
			}
			else {
				divSendingTop = displayHeight/2 - divSendingHeight/2;
			}
			oDivSendingCurrency.style.left = divSendingLeft;
			oDivSendingCurrency.style.top = divSendingTop;
		}
	}
	catch(ex){
		/*var debug = "";
		for(var i in ex){
			debug += i + "  = " + ex[i] + "\n";
		}
		alert(debug);*/
		return;
	}
};

//当(window)滚动条滚动时
window.onscroll = function(){
	try{
		var oBody = window.document.body;
		var oDivSending = window.document.getElementById("sending");
		var oDivBackground = window.document.getElementById("sendingBackground");
		var oDivSendingCurrency = window.document.getElementById("sendingCurrency");
			
		if((oBody != null) && (oDivSending != null) && (oDivBackground != null)){
			//网页正文全文高
			var bodyHeigth = oBody.scrollHeight;
			//网页正文全文宽
			var bodyWidth = oBody.scrollWidth;
			//网页可见区域高
			var displayHeight = oBody.clientHeight;
			//网页可见区域宽
			var displayWidth = oBody.clientWidth;
			
			//DivSending高
			var divSendingHeight = $("#sending").height();
	
			//获取oBody的位置
			var bbPosition = $("body").offset();
			
			//设置oDivBackground
			oDivBackground.style.height = bodyHeigth;
			oDivBackground.style.width = bodyWidth;
	
			//设置DivSending位置
			var divSendingLeft = bbPosition.left;
			//var divSendingTop = displayHeight/2 - divSendingHeight/2 + oBody.scrollTop;
			var divSendingTop = 0;
			if(displayHeight <= divSendingHeight){
				divSendingTop = 0;
			}
			else {
				divSendingTop = displayHeight/2 - divSendingHeight/2;
			}
			oDivSending.style.left =divSendingLeft;
			oDivSending.style.top = divSendingTop;
		}
		
		if((oBody != null) && (oDivSendingCurrency != null) && (oDivBackground != null)){
			//网页正文全文高
			var bodyHeigth = oBody.scrollHeight;
			//网页正文全文宽
			var bodyWidth = oBody.scrollWidth;
			//网页可见区域高
			var displayHeight = oBody.clientHeight;
			//网页可见区域宽
			var displayWidth = oBody.clientWidth;
			//DivSending高
			var divSendingHeight = $("#sendingCurrency").height();
	
			//获取oBody的位置
			var bbPosition = $("body").offset();
			
			//设置oDivBackground
			oDivBackground.style.height = bodyHeigth;
			oDivBackground.style.width = bodyWidth;
	
			//设置DivSending位置
			var divSendingLeft = bbPosition.left;
			//var divSendingTop = displayHeight/2 - divSendingHeight + oBody.scrollTop;
			var divSendingTop = 0;
			if(displayHeight <= divSendingHeight){
				divSendingTop = 0;
			}
			else {
				divSendingTop = displayHeight/2 - divSendingHeight/2;
			}
			oDivSendingCurrency.style.left =divSendingLeft;
			oDivSendingCurrency.style.top = divSendingTop;
		}
	}
	catch(ex){
		/*var debug = "";
		for(var i in ex){
			debug += i + "  = " + ex[i] + "\n";
		}
		alert(debug);*/
		return;
	}
};

//***********************************************************************
//控制显示"正在执行中, 请稍候..."提示信息的方法
function showSending(disabledFlag){
	gnIsShowSending = 1;
	
	try{
		var oBody = window.document.body;
		if(oBody != null ){
			//网页正文全文高
			var bodyHeigth = oBody.scrollHeight;
			//网页正文全文宽
			var bodyWidth = oBody.scrollWidth;
			//网页可见区域高
			var displayHeight = oBody.clientHeight;
			//网页可见区域宽
			var displayWidth = oBody.clientWidth;
			//获取oBody的位置
			var bbPosition = $("body").offset();
			
			//在内存中创建一个oDivSending对象
			var oDivSending = document.createElement("DIV");
			var subHTML = "<table bgcolor=\"#ff9900\" width=\"380\" height=\"70\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\">";
			subHTML += "<tr><td bgcolor=\"#eeeeee\" align='center'><p align=\"center\">正在执行中, 请稍候...</p></td></tr></table>";
			
			oDivSending.innerHTML = subHTML;
			oDivSending.id = "sending";
			oDivSending.style.position = "absolute";
			oDivSending.style.zIndex = 501;
			oBody.appendChild(oDivSending);
			
			//获取DivSending高度
			var divSendingHeight = $(oDivSending).height();
			//获取DivSending宽度
			var divSendingWidth = $(oDivSending).width();
			
			
			//在内存中创建一个oDivBackground对象
			var oDivBackground = document.createElement("DIV");
			//oDivBackground = window.document.createElement("IFRAME");
			oDivBackground.id = "sendingBackground";
			oDivBackground.style.position = "absolute";
			oDivBackground.style.left = bbPosition.left;
			oDivBackground.style.top = bbPosition.top;
			oDivBackground.style.zIndex = 500;
			//oDivBackground.style.backgroundColor = "beige";
			oDivBackground.style.backgroundColor = "white";
			oDivBackground.style.filter = "progid:DXImageTransform.Microsoft.Alpha(Opacity=60, FinishOpacity=30, Style=0, StartX=0,  FinishX=0, StartY=0, FinishY=0)";
			oDivBackground.style.height = bodyHeigth;
			oDivBackground.style.width = bodyWidth;
			oDivBackground.style.display = "block";
			oBody.appendChild(oDivBackground);
			
			
			//disabled所有的<SELECT>标签
			if (!disabledFlag) {
				var oSelectColl = window.document.getElementsByTagName("SELECT");
				for (var i = 0; i < oSelectColl.length; i++) {
					var oSelect = oSelectColl[i];
					var oParent = oSelect.parentNode;
					
					var selectId = oSelect.id
					var selectName = oSelect.name;
					var selectValue = "";
					
					if (oSelect.multiple == true) {
						//有select标签有multiple属性
						for (var j = 0; j < oSelect.options.length; j++) {
							var oOption = oSelect.options(j);
							//if(oOption.selected == true)
							selectValue = oOption.value;
							
							var oHidden = window.document.createElement("INPUT");
							oHidden.type = "hidden";
							if (selectId != "") {
								oHidden.id = selectId;
							}
							if (selectName != "") {
								oHidden.name = selectName;
							}
							oHidden.value = selectValue;
							oParent.appendChild(oHidden);
						}
					}
					else {
						//有select标签为单选
						if (oSelect.options.length > 0 && oSelect.selectedIndex >= 0) {
							selectValue = oSelect.options(oSelect.selectedIndex).value;
						}
						
						var oHidden = window.document.createElement("INPUT");
						oHidden.type = "hidden";
						if (selectId != "") {
							oHidden.id = selectId;
						}
						if (selectName != "") {
							oHidden.name = selectName;
						}
						oHidden.value = selectValue;
						oParent.appendChild(oHidden);
					}
					
					oSelect.disabled = "true";
				}
			}
			//设置DivSending位置
			var divSendingLeft =(oBody.scrollLeft + oBody.clientWidth/2 - divSendingWidth/2) + "px";
			//var divSendingTop = displayHeight/2 - divSendingHeight + oBody.scrollTop;
			var divSendingTop = 0;
			
			if(displayHeight <= divSendingHeight){
				divSendingTop = 0;
			}
			else {
				divSendingTop = (oBody.scrollTop + oBody.clientHeight/2 - divSendingHeight/2) + "px";
			}
			oDivSending.style.left = divSendingLeft;
			oDivSending.style.top = divSendingTop;
			oDivSending.style.display = "block";
			//oIsShowSending.value = "false";
			
			oBody.focus(); 
		}
	}
	catch(ex){
		//var debug = "";
		//for(var i in ex){
		//	debug += i + "  = " + ex[i] + "\n";
		//}
		//alert(debug);
		return;
	}
}
  function removeSending(){
		$("#sending").remove();
		$("#sendingBackground").remove();
		$("#sendingCurrency").remove();
  }

//iframe onLoad
function iframeOnLoad(){
	var oDivBackground = window.document.getElementById("sendingBackground");
	var oDivSending = window.document.getElementById("sending");
	var oIsShowSending =  window.document.getElementById("mainIsShowSending");
	if((oDivSending != null) && (oDivBackground != null)){
		window.document.body.removeChild(oDivBackground);
		window.document.body.removeChild(oDivSending);
		
	}
	if(oIsShowSending != null){
		oIsShowSending.value = "true";
	}
}

$(document).keydown(function(event){
    var inputs = $('input[type=text]');	  
    var pwds = $('input[type=password]');	
    var textAreas = $('textarea');
    for(var i=0 ;i<pwds.length;i++){
		if (document.activeElement.readOnly == false){
			return true;
		}
    }	
    for(var i=0 ;i<inputs.length;i++){
		if (document.activeElement.readOnly == false){
			return true;
		}
    }	
    for(var i=0 ;i<textAreas.length;i++){
		if (document.activeElement.readOnly == false){
			return true;
		}
    }	
    //if(event.ctrlKey)return false;
    if(event.keyCode==8){
       return false;
    }
});
//-->