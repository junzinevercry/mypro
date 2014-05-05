function choiceTextPage(pageSize, currentPage) {
	var pageNumber = $("#pageNumber").val();
	if (Number(currentPage) == Number(pageNumber)) {
		return;
	}
	if (pageNumber.length == 0) {
		alert("请输入需要查询的页码");
		return;
	}
	if (Number(pageNumber) <= 0 || Number(pageNumber) > Number(pageSize)) {
		if(Number(pageSize) > 1){
			alert("请输入1-" + (Number(pageSize)) + "之间的整数");
		} else if(Number(pageSize) == 0){
			alert("当前列表没有数据");
		} else {
			alert("当前列表只有一页");
		}
		return;
	}
	gotoPage(pageNumber);
}
function gotoFirstPage(pageNumber, currentPage) {
	if (Number(pageNumber) == Number(currentPage)) {
		if (pageNumber == 1) {
			alert("已经是第一页，不能操作[首页]");
		}
		return;
	}
	gotoPage(pageNumber);
}
function gotoEndPage(pageNumber, currentPage) {
	if (Number(pageNumber) == Number(currentPage)) {
		if (pageNumber == currentPage) {
			alert("已经是最后一页，不能操作[末页]");
		}
		return;
	}
	gotoPage(pageNumber);
}
function gotoPage(pageNumber) {
	if (!isNumTmd(pageNumber)) {
		alert("请输入整数");
		return;
	}
	if (pageNumber <= 0) {
		alert("已经是第一页，不能操作[上一页]");
		return;
	}
	$("#currentPage").val(pageNumber);
	$("#searchForm").submit();
}
function goNext(pageNumber, pageSize) {
	if (pageNumber > pageSize) {
		alert("已经是最后一页，不能操作[下一页]");
		return;
	}
	gotoPage(pageNumber);
}
function isNumTmd(value) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (re.test(value)) {
		return true;
	} else {
		return false;
	}
}
