//***********************************************************************
//控制显示"正在执行中, 请稍候..."提示信息的方法
function showSending(){
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
			var subHTML = "<table bgcolor=\"#ff9900\" style=\"width:160px\" height=\"70\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\">";
			subHTML += "<tr><td bgcolor=\"#eeeeee\" align='center'><p align=\"center\">正在执行中, 请稍候...</p></td></tr></table>";
			
			oDivSending.innerHTML = subHTML;
			oDivSending.id = "sending";
			oDivSending.style.position = "absolute";
			oDivSending.style.zIndex = 501;
			oDivSending.style.border="3px solid #ffcc00";
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