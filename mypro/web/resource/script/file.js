function gotoCreateFile(basePath, winName, fileType){
	ShowIframe('上传附件','createFile',basePath + '/file/gotoCreateFile.action?winName='+winName+'&fileType='+fileType,900,300,true);
}
function doDownloadFile(basePath,filePath,realName){
	window.location.href=basePath + '/file/doDownloadFile.action?filePath=' + filePath + "&realName=" + realName;
}
