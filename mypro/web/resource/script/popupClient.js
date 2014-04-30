

function ShowIframe(title, popName, contentUrl, width, height, isModal) {
	top.ShowIframe(title, popName, contentUrl, width, height, isModal);
}

function callBackFunc(resultObj, iframeName, funcName, obj) {
	top.callBackFunc(resultObj, iframeName, funcName, obj);
}

function closeIframe(popName) {
	if(!popName) popName=window.name;
	top.closeIframe(popName);
}

function refreshIframe(popName) {
	if(!popName) popName=window.name;
	top.refreshIframe(popName);
}


function forwordIndex(num,url,winName){
	top.forwordIndex(num,url,winName);
}