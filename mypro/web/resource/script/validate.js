var message1 = "请输入正确的";
var point = ".";
var single = "'";
/*
function isNull(elementName,cnName) {
	var regu = "^\\S+$";
	var message = single + cnName + single + "不能为空" + point;
	return !reg(elementName,message,regu);
}*/
function isNull(elementName,cnName) {
	var element = $("#" + elementName);
	var elementVal = element.val();
	if(typeof(elementVal) != 'undefined') {
		if (isNullStr(elementVal)) {
			alert("请输入'" + cnName + "'.");
			element.focus();
			return true;
		}else{
			return false;
		}
	} else {
		return false;
	}
}
function isMobile(elementName,cnName) {
	var regu = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$";
	var message = message1 + single + cnName + single + point;
	return reg(elementName,message,regu);
}
function isEmail(elementName,cnName) {
	var regu = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
	var message = getMessage1(cnName);
	return reg(elementName,message,regu);
}
function isTel(elementName,cnName) {
//	var regu = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
//	var message = getMessage1(cnName);
//	return reg(elementName,message,regu);
	return true;
}
function isFloat(elementName,cnName) {
	var regu = "^([+-]?)\\d*\\.\\d+$";
	var message = single + cnName + single + "必须为小数" + point;
	return reg(elementName,message,regu);
}
function isNum(elementName,cnName) {
	var regu = "^[0-9]+$";
	var message = single + cnName + single + "必须为正整数" + point; 
	return reg(elementName,message,regu);
}
function isDecimal(elementName,cnName) {
	var regu = "^[0-9]+|\\d*\\.\\d+$";
	var message = single + cnName + single + "必须为正数" + point; 
	return reg(elementName,message,regu);
}
function isChinese(elementName,cnName) {
	var regu = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
	var message = single + cnName + single + "必须为汉字" + point;
	return reg(elementName,message,regu);
}
function isZip(elementName,cnName){
	var regu = "^\\d{6}$";
	var message = getMessage1(cnName);
	return reg(elementName,message,regu);
}
function isYMD(elementName,cnName){
	var regu = "^(\\d{4})(-)(\\d{2})(-)(\\d{2})$";
	var message = single + cnName + single + "格式必须为'YYYY-MM-DD'." + point;
	return reg(elementName,message,regu);
}
function isMoney(elementName,cnName){
	var regu = "^([1-9]\\d{0,8}|0)(\\.\\d+)?$";
	var message = single + cnName + single + "应该为小于十位的整数或小数" + point;
	var firstResult = reg(elementName,message,regu);
	return firstResult;
}
function isUrl(elementName,cnName){
	var regu = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
	var message = single + cnName + single + "格式不正确" + point;
	var firstResult = reg(elementName,message,regu);
	return firstResult;
}
/********************************华丽的分割线*********************************/

function getMessage1(cnName){
	return message1 + single + cnName + single + point;
}

function isNullStr(str) {
	if ($.trim(str)=='') {
		return true;
	}else{
		return false;
	}
}
function reg(elementName,message,regu){
	var element = $("#"+elementName);
	var elementVal = element.val();
	if(typeof(elementVal) != 'undefined') {
		var re = new RegExp(regu);
		if (re.test(elementVal)) {
			return true;
		}else{
			alert(message);
			element.focus();
			element.select();
			return false;
		}
	} else {
		return true;
	}
}
function initLimitChars(textArea) {
	  if(textArea.value.length>600)
	    textArea.value=textArea.value.substr(0,600)
	}	