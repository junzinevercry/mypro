/**
 * 鉴于没有好的textarea文本框字数统计工具，尤其是不支持中文输入，以及复制粘贴，
 * 特开发一个，很简单，相信不用加注释了！无视keyup/keypress/change...等事件...(因为不支持中文)
 * Debian下开发，windows用户请用比较高级的编辑器(editplus/notepad++/vim...)打开，以防乱码
 * max参数作为限定用，如果只想显示长度，可不用加此参数，如果想像twitter (
 * 国内微博实现的字数显示技术没有twitter牛，总是反应慢一拍 )   等那样，显示剩余字数，请加参数
 * callback函数在文本框内的字符数发生改变时调用，可以用来决定button按钮是否可用等
 * 
 * Licensed under The MIT License
 *
 * @version     1.0
 * @since       2010-11-20 15:47
 * @author     Jesse <microji@126.com>
 */

/**
 * Usage: $('#txa').jTextCount('#jcounter', {max:140, jCountBarId : '#jcount-bar', callback:function(s){
 *      $('#submitId').attr('disabled',  !s) ;
 * }});
 *
 * @param jCountId 你想动态显示字符数或剩余字符数的标签ID，注意要家#号，如：'#counter'
 * @param options 一些附加参数
 *
 * Valid options:
 * ---------------------------------------
 *       max:     大于0的数值
 *       jCountBarId : 显示一个进度条的id！，注意要加#号，如：'#count-bar'
 *       callback: 回调函数，在文本框内字符长度发生改变时，调用，并传递是否超过max的限定的bool参数！
 */
var longOfChineseChar = 3;
var maxChineseChar = 4000 / longOfChineseChar;

//textarea字符串长度验证并提示功能
function checkTextarea(textareaId, maxLength){
	 $('#'+textareaId).jTextCount('#'+textareaId+'Div', {max:maxLength, jCountBarId : '', callback:function(s){}});
}

// 返回内容长度(英文长度为1,中文为2)
$.fn.valueLength = function() {

	var matchCount = $(this).val().match(/[^\x00-\xff]/ig);

	return ($(this).val().length + (matchCount == null ? 0 : matchCount.length
			* (longOfChineseChar - 1)));
}
 
$.fn.jTextCount = function(jCountId, options){
    var defaults = {
        max : 0,  // define max length for the texarea
        jCountBarId : '', // use jquery id type ! like '#count-bar'
        callback:function(){} // callback function ,when length changed
    };
    if(options) {
        $.extend(defaults, options);
    };
    var editor = this;
    var counter = $(jCountId) ;
    var countBar = defaults.jCountBarId !='' && $(defaults.jCountBarId).length>0 ? $(defaults.jCountBarId) : null ;
    var timer = null ;
	
    editor.defaultLength = editor.val().length ; // get original length
    var processChange = function(){
        //var newLength = editor.val().length ;
		var newLength;
		if (defaults.max >= maxChineseChar) {
			newLength = editor.valueLength();
		} else {
			newLength = editor.val().length;
		}
		var strValue = editor.val();
		newLength = newLength + ((strValue.split("\n")).length-1)*2;
		//newLength = editor.val().length;
        if(defaults.max>0){
            var num = defaults.max - newLength ;
            counter.text(num);
            if(countBar){
                var percent = Math.min( (100*newLength / defaults.max).toFixed(1), 100) ;
                countBar.width(percent+'%');
            }
            if(num>=0 && num <=10){
                defaults.callback(true);
                counter.removeClass('jTextCount-error').addClass('jTextCount-warn');
                if(countBar){
                    countBar.removeClass('jTextCount-bar-error').addClass('jTextCount-bar-warn');
                }
            }else if(num<0){
                counter.removeClass('jTextCount-warn').addClass('jTextCount-error');
                if(countBar){
                    countBar.removeClass('jTextCount-bar-warn').addClass('jTextCount-bar-error');
                }
				alert("已经超出规定长度,多余部分会被删除!");
				
				editor.val(editor.val().substring(0,
						editor.val().length + num));
				
                defaults.callback(false);
            }else{
                defaults.callback(true);
                counter.removeClass('jTextCount-warn').removeClass('jTextCount-error');
                if(countBar){
                    countBar.removeClass('jTextCount-bar-warn').removeClass('jTextCount-bar-error');
                }
            }
        }else{
            counter.text(newLength);
        }
        editor.defaultLength = newLength ;
    };

    processChange();
    /*
    editor.bind('input',function(){
        var newLength = editor.val().length ;
                if( newLength != editor.defaultLength ){
                    processChange();
                }
    }); */

    editor.bind('focus',function(){
        if(!timer){
            timer = window.setInterval(function(){
                var newLength = editor.val().length ;
                if( newLength != editor.defaultLength ){
                    processChange();
                }
            },10);
        }
    });
    editor.bind('blur', function(){
        window.clearInterval(timer) ;
        timer = null;
    });
};

