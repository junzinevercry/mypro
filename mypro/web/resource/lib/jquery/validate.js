$.validate = {
	settings : {},
	messages : {},
	message_info : "",
	obj : "",
	aCity : {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	},
	initValidate : function(config_options, message_options, obj) {
		// 初始化配置选项
		$.validate.init(config_options, message_options, obj);
		var valEvent = $.validate.settings.valEvent;

		if(valEvent.indexOf(",") > 0) {
			$.each(valEvent.split(","), function(index, value) {
				$.validate.obj.bind(value, function() {
					$.validate.doValidate(config_options, message_options, obj);
				});
			});
		} else {
			$.validate.obj.bind(valEvent, function() {
				$.validate.doValidate(config_options, message_options, obj);
			});
		}
		if(valEvent.indexOf("nosubmit") < 0) {
			$.validate.obj.closest("form").bind("submit", function() {
				if($.validate.doValidate(config_options, message_options, obj)) {
					return false;
				}
			});
		}
	},
	doValidate : function(config_options, message_options, obj) {
		// 初始化配置选项
		$.validate.init(config_options, message_options, obj);
		if(!$.validate.settings.nullable && $.validate.settings.valType != "notNull" && $.validate.settings.valType != "select") {
			if(!$.validate.isNull()) {
				$.validate.messages.errorMsg = messageConfig.nullMsg;
				$.validate.setFocus(true);
				$.validate.showMsg(true);
				return true;
			}
		}

		if(!$.validate.lengthValidate()) {
			$.validate.messages.errorMsg = messageConfig.lengthMsg;
			$.validate.showMsg(true);
			$.validate.setFocus(true);
			return true;
		}

		var result = false;
		if($.validate.isTypeOf("notNull")) {
			result = $.validate.isNull();
		} else if($.validate.isTypeOf("char")) {
			result = $.validate.isChar();
		} else if($.validate.isTypeOf("num")) {
			result = $.validate.isNum();
		} else if($.validate.isTypeOf("float")) {
			result = $.validate.isFloat();
		} else if($.validate.isTypeOf("chinese")) {
			result = $.validate.isChinese();
		} else if($.validate.isTypeOf("date")) {
			result = $.validate.isDate();
		} else if($.validate.isTypeOf("email")) {
			result = $.validate.isEmail();
		} else if($.validate.isTypeOf("money")) {
			result = $.validate.isMoney();
		} else if($.validate.isTypeOf("zip")) {
			result = $.validate.isZip();
		} else if($.validate.isTypeOf("idCard")) {
			result = $.validate.isIdCard();
		} else if($.validate.isTypeOf("regex")) {
			result = $.validate.isRegex();
		} else if($.validate.isTypeOf("func")) {
			result = $.validate.funcValidate($.validate.settings.func);
		} else if($.validate.isTypeOf("check")) {
			result = $.validate.checkValidate();
		} else if($.validate.isTypeOf("select")) {
			result = $.validate.selectValidate();
		} else if($.validate.isTypeOf("password")) {
			result = $.validate.passwordValidate();
		} else if($.validate.isTypeOf("any")) {
			result = true;
		}
		$.validate.setFocus(!result);
		$.validate.showMsg(!result);
		return !result;
	},
	init : function(config_options, message_options, obj) {
		$.extend(true, $.validate.settings, initConfig, config_options || {});
		$.extend(true, $.validate.messages, messageConfig, message_options || {});
		$.validate.message_info = '';
		$.validate.obj = obj;
	},
	isNull : function() {
		var result = true;
		//$.validate.reg(regexEnum.notempty);
		if($.validate.obj.val().length == 0) {
			result = false;
		}
		if(!result) {
			$.validate.setNullMsg();
		}
		return result;
	},
	isChar : function() {
		return $.validate.commonValicate(regexEnum.letter);
	},
	isNum : function() {
		return $.validate.commonValicate("^[1-9]\\d*|0$");
	},
	isFloat : function() {
		var numLength = $.validate.settings.numlength;
		var floatLength = $.validate.settings.floatlength;
		if(numLength < 0 && floatLength < 0) {
			return false;
		}
		var regu = "";
		if(numLength > 1 && floatLength > 1) {
			regu = "^([+-]?)\\d{1," + numLength + "}\\.\\d{1," + floatLength + "}$";
		} else if(numLength < 1 && floatLength > 1) {
			regu = "^([+-]?)\\d+\\.\\d{1," + floatLength + "}$";
		} else if(numLength > 1 && floatLength < 1) {
			regu = "^([+-]?)\\d{1," + numLength + "}\\.\\d+$";
		} else {
			regu = "^([+-]?)\\d+\\.\\d+$";
		}
		return $.validate.commonValicate(regu);
	},
	isMobile : function() {
		var regu = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$";
		return $.validate.commonValicate(regu);
	},
	isEmail : function() {
		var regu = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		return $.validate.commonValicate(regu);
	},
	isMoney : function() {
		var regu = "^\\d*(\\.\\d{1,2})?$";
		return $.validate.commonValicate(regu);
	},
	isTel : function() {
		return true;
	},
	isDecimal : function() {
		var regu = "^[0-9]+|\\d*\\.\\d+$";
		return $.validate.commonValicate(regu);
	},
	isChinese : function() {
		return $.validate.commonValicate(regexEnum.chinese);
	},
	isZip : function() {
		return $.validate.commonValicate(regexEnum.zipcode);
	},
	isDate : function() {
		var regu = "";
		if($.validate.settings.datepattern == initConfig.datepattern) {
			regu = "^(\\d{4})(-)(\\d{1,2})(-)(\\d{1,2})$";
		} else if($.validate.settings.datepattern == "yyyy-MM") {
			regu = "^(\\d{4})(-)(\\d{1,2})$";
		} else if($.validate.settings.datepattern == "yyyy-MM-dd hh:mm:ss") {
			regu = "^(\\d{4})(-)(\\d{1,2})(-)(\\d{1,2}) (\\d{1,2})(:)(\\d{1,2})(:)(\\d{1,2})$";
		} else if($.validate.settings.datepattern == "yyyy-MM-dd hh:mm") {
			regu = "^(\\d{4})(-)(\\d{1,2})(-)(\\d{1,2}) (\\d{1,2})(:)(\\d{1,2})$";
		}
		return $.validate.commonValicate(regu);
	},
	isRegex : function() {
		var regu = $.validate.settings.valRegex;
		return $.validate.commonValicate(regu);
	},
	// ****************** tools *********************
	commonValicate : function(regex) {
		var result = true;
		if($.validate.settings.nullable && $.validate.obj.val().length == 0) {
			return true;
		}
		result = $.validate.reg(regex);
		if(!result) {
			$.validate.message_info += $.validate.messages.errorMsg;
		}
		return result;
	},
	isTypeOf : function(type) {
		return $.validate.settings.valType == type;
	},
	isNullStr : function(str) {
		if($.trim(str) == '') {
			return true;
		} else {
			return false;
		}
	},
	reg : function(regu) {
		var elementVal = $.validate.obj.val();
		if( typeof (elementVal) != 'undefined') {
			var re = new RegExp(regu);
			if(re.test(elementVal)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	},
	isIdCard : function() {
		var sId = $.validate.obj.val();
		var iSum = 0;
		var info = "";
		if(!/^\d{17}(\d|x)$/i.test(sId)) {
			$.validate.setCommonErrorMsg("你输入的身份证长度或格式错误");
			return false;
		}
		sId = sId.replace(/x$/i, "a");
		if($.validate.aCity[parseInt(sId.substr(0, 2))] == null) {
			$.validate.setCommonErrorMsg("你的身份证地区非法");
			return false;
		}
		sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if(sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
			$.validate.setCommonErrorMsg("身份证上的出生日期非法");
			return false;
		}
		for(var i = 17; i >= 0; i--) {
			iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
		}
		if(iSum % 11 != 1) {
			$.validate.setCommonErrorMsg("你输入的身份证号非法");
			return false;
		}
		return true;
		//$.validate.aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
	},
	// *******************************************************************
	lengthValidate : function() {
		if($.validate.settings.maxlength < 0 && $.validate.settings.minlength < 0) {
			return true;
		}
		var element = $.validate.obj;
		var currentLength = element.val().length;
		if($.validate.settings.nullable && currentLength == 0) {
			return true;
		}
		if(($.validate.settings.maxlength < currentLength && $.validate.settings.maxlength >= 0) || ($.validate.settings.minlength > currentLength && $.validate.settings.minlength >= 0)) {
			$.validate.setLengthMsg();
			return false;
		} else {
			return true;
		}
	},
	funcValidate : function(f) {
		if(f != '') {
			var result = (f($.validate.obj.val()));
			if(!result) {
				$.validate.setCommonErrorMsg($.validate.messages.errorMsg);
			}
			return result;
		}
	},
	checkValidate : function() {
		var result = false;
		$.validate.obj.each(function(index) {
			if($(this).attr("checked") == 'checked') {
				result = true;
				return;
			}
		});
		if(!result && $.validate.obj.length > 0) {
			$.validate.setNullMsg();
		}
		return result;
	},
	selectValidate : function() {
		var result = $.validate.obj.val() == $.validate.settings.selectDefValue && !$.validate.settings.nullable;
		if(result) {
			$.validate.setNullMsg();
		}
		return !result;
	},
	passwordValidate : function() {
		var result = false;
		var elements = $.validate.obj;
		if(elements.length == 2) {
			if(elements.eq(0).val() == elements.eq(1).val()) {
				result = true;
			} else {
				$.validate.setCommonErrorMsg($.validate.messages.errorMsg);
			}
		}
		return result;
	},
	setCommonErrorMsg : function(msg) {
		if($.validate.messages.errorMsg == messageConfig.errorMsg) {
			$.validate.message_info = msg;
		} else {
			$.validate.message_info = $.validate.messages.errorMsg;
		}
	},
	setCommonCorrectMsg : function(msg) {
		if($.validate.messages.correctMsg == messageConfig.correctMsg) {
			$.validate.message_info = msg;
		} else {
			$.validate.message_info = $.validate.messages.correctMsg;
		}
	},
	setNullMsg : function() {
		$.validate.setCommonErrorMsg($.validate.messages.nullMsg);
	},
	setLengthMsg : function() {
		$.validate.setCommonErrorMsg($.validate.messages.lengthMsg);
	},
	setFocus : function(result) {
		if(result && $.validate.settings.valEvent == "") {
			if($.validate.isTypeOf("check")) {
				$.validate.obj.eq(0).focus();
			} else {
				$.validate.obj.focus();
				$.validate.obj.select();
			}
		}
	},
	showMsg : function(result) {
		if(result && messageConfig.showType != "none") {
			if($.validate.messages.showType == 'alert') {
				if($.validate.message_info != "") {
					alert($.validate.message_info);
				}
			} else if($.validate.messages.showType == 'div') {
				if($.validate.message_info != "") {
					$.validate.setCommonErrorMsg($.validate.messages.errorMsg);
					$("#" + $.validate.obj.attr("id") + "Msg").html("<span style='color:red'>" + $.validate.message_info + "</span>")
				}
			}
		} else {
			if($.validate.messages.showType == 'div') {
				$.validate.setCommonCorrectMsg($.validate.messages.correctMsg);
				$("#" + $.validate.obj.attr("id") + "Msg").html("<span style='color:green'>" + $.validate.message_info + "</span>")
			}
		}
	}
}
$.fn.doValidate = function(initConfig, messageConfig) {
	return $.validate.doValidate(initConfig, messageConfig, $(this));
};
$.fn.initValidate = function(initConfig, messageConfig) {
	return $.validate.initValidate(initConfig, messageConfig, $(this));
};
var initConfig = {
	maxlength : -1,
	minlength : -1,
	numlength : 0,
	floatlength : 0,
	datepattern : "yyyy-MM-dd",
	nullable : true,
	valType : "",
	valRegex : "",
	func : "",
	selectDefValue : "",
	valEvent : ""
}
var messageConfig = {
	enable : false,
	showType : "alert",
	errorMsg : "您输入的内容有误。\n",
	nullMsg : "您输入的内容为空。\n",
	lengthMsg : "您输入的内容长度有误。\n",
	correctMsg : "输入正确。\n"
}
var regexEnum = {
	//整数
	intege : "^-?[1-9]\\d*$",
	intege1 : "^[1-9]\\d*$", //正整数
	intege2 : "^-[1-9]\\d*$", //负整数
	num : "^([+-]?)\\d*\\.?\\d+$", //数字
	num1 : "^[1-9]\\d*|0$", //正数（正整数 + 0）
	num2 : "^-[1-9]\\d*|0$", //负数（负整数 + 0）
	decmal : "^([+-]?)\\d*\\.\\d+$", //浮点数
	decmal1 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
	decmal2 : "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
	decmal3 : "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
	decmal4 : "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
	decmal5 : "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
	//邮件
	email : "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
	color : "^[a-fA-F0-9]{6}$", //颜色
	url : "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
	//仅中文
	chinese : "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",
	ascii : "^[\\x00-\\xFF]+$", //仅ACSII字符
	//邮编
	zipcode : "^\\d{6}$",
	mobile : "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$", //手机
	ip4 : "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
	//非空
	notempty : "^\\S+$",
	picture : "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
	rar : "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
	//日期
	date : "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
	qq : "^[1-9]*[1-9][0-9]*$", //QQ号码
	tel : "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
	username : "^\\w+$", //用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	//字母
	letter : "^[A-Za-z]+$",
	letter_u : "^[A-Z]+$", //大写字母
	letter_l : "^[a-z]+$", //小写字母
	idcard : "^[1-9]([0-9]{14}|[0-9]{17})$"	//身份证
}