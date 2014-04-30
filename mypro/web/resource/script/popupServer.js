var pop_z_index = 1;

var dialog = null;
var cDG;
var api = null;
var W = null;
var thisTop=frameElement;

var isIE=false;

var isFire=false;

if(document.all){
	isIE=true;
}else{
	isFire=true;
}

function ShowIframe(title, popName, contentUrl, width, height, isModal,openerName){
		//var parentApi=(document.frames[openerName])?document.frames[openerName].frameElement.api:null;
	
	dialog=$.dialog({title: title,
				content: 'url:'+contentUrl, 
				width:width+'px',
		        height:height,  
		        max:true,
			    min:true,
			    id:popName,
			    lock:true
			//	parent:parentApi,
			//	close:onCloseIframe
			});
}

//关闭窗口
function closeIframe(popName){
	var list=$.dialog.list;
    list[popName].close();
}



//刷新窗口
function refreshIframe(popName, obj){
    var iframe_name;
    if (popName == null || popName == "" || popName=="home") {
        iframe_name = "frmright";
    }
    else {
        iframe_name = popName;
    }
    if (obj == "" || typeof(obj) == "undefined" || obj == null) {
        obj = $(document);
    }
    var $indexIframes = obj.find("iframe");
    $indexIframes.each(function(){
        if (this.name != "" && this.name != null && typeof(this.name) != "undefined" && this.name.length > 0) {
            if (this.name == iframe_name) {
				var target=obj.find("iframe[name=" + this.name + "]") || obj.find("iframe[id=" + this.name + "]") ; 
				
				target[0].contentWindow.location.href=target[0].contentWindow.location.href		

                return;
            }
            else {
				 var newObj = obj.find("iframe[name=" + this.name + "]") || obj.find("iframe[id=" + this.name + "]") ;
                
				if(newObj.size()>0)
				refreshIframe(popName, newObj);
				
            }
        }
    });
}

/**
 * 功能说明:弹出窗口,返回JSON结果并调用子页面中的回调函数
 * param: resultObj  传给主页面Iframe页面的json对象
 *        funcName   需要回调的iframe中的方法名,可为空
 */
function callBackFunc(resultObj, iframeName, funcName, obj){
    var iframe_name;
    if (iframeName == null || iframeName == "" || iframeName=="home") {
        iframe_name = "frmright";
    }
    else {
        iframe_name = iframeName;
    }
    if (obj == "" || typeof(obj) == "undefined" || obj == null) {
        obj = $(document);
    }
    var $indexIframes = obj.find("iframe");
    $indexIframes.each(function(){
        if (this.name != "" && this.name != null && typeof(this.name) != "undefined" && this.name.length > 0) {
            if (this.name == iframe_name) {
				
				var target=obj.find("iframe[name=" + this.name + "]") || obj.find("iframe[id=" + this.name + "]") ;
				target=target[0].contentWindow;
				
				if(!target[funcName] || typeof(target[funcName]) != "function"){
					alert(funcName + " \u4e0d\u662f" + iframe_name + " \u4e2d\u7684\u4e00\u4e2a\u65b9\u6cd5");
				}
				
				else{
					target[funcName](resultObj);
					//alert(target[funcName]);
					//target['test']();
					//eval("target."+funcName+"("+resultObj+")");
				}
						
                return;
            }
            else {
				var newObj =obj.find("iframe[name=" + this.name + "]") || obj.find("iframe[id=" + this.name + "]") ;
				
				if(newObj.size()>0)
              	  callBackFunc(resultObj, iframeName, funcName, newObj);
            }
        }
    });
}


function forwordIndex(num,url,winName){
	top.gotoPage(num,url,winName);
}
