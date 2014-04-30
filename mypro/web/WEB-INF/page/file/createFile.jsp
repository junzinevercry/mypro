<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${ctx }/resource/lib/jquery/validate.js"></script>
<title></title>
<script>
	var fileType = '${fileType}';
	function doCreateFile() {
		if (checkBefore()) {
			if (confirm('确认上传文件?')) {
				$("#saveForm").submit();
			}
		}
	}
	function checkBefore() {
		if ($("#file1").doValidate({
			valType : "notNull"
		})) {
			return false;
		}
		if (fileType != '') {
			var test = false;
			var fileName = $("#file1").val();
			var type = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length);
			var fileTypes = fileType.split(",");
			for ( var i = 0; i < fileTypes.length; i++) {
				if (type == fileTypes[i]) {
					test = true;
					break;
				}
			}
			if (!test) {
				alert("上传文件类型应为" + fileType);
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body>
	<c:if test="${not empty result }">
		<script>
			var html = "<div>"
					+ "<span onclick=\"doDownloadFile('"
					+ '${ctx}'
					+ "','"
					+ '${filePath}'
					+ "','"
					+ '${realName}'
					+ "')\">"
					+ '${realName}'
					+ "</span>"
					+ "<input type='hidden' name='fileId' id='fileId' value='"+'${fileId}'+"'/>"
					+ "<input type='hidden' name='realName' id='realName' value='"+'${realName}'+"'/>"
					+ "<input type='hidden' name='fileName' id='fileName' value='"+'${fileName}'+"'/>"
					+ "<input type='hidden' name='filePath' id='filePath' value='"+'${filePath}'+"'/>"
					+ "<span class='pointer;color:red' onclick='gotoCreateFile(\""
					+ '${ctx}' + "\",\"" + '${winName}' ;
					'<c:if test="${not empty fileType }">'
					html += "\",\"" + '${fileType}';
					'</c:if>'
				html += "\")'> 重新上传 </span>"
					+ "</div>";
			var result = {
				"fileId" : '${fileId}',
				"realName" : '${realName}',
				"fileName" : '${fileName}',
				"filePath" : '${filePath}',
				"html" : html
			};
			alert('处理成功!');
			callBackFunc(result, '${winName}', 'setUpload');
			closeIframe('createFile');
		</script>
	</c:if>
	<c:if test="${empty result }">
		<div class="basecontent">
			<div class="tablebox">
				<form id="saveForm" name="saveForm" method="post"
					action="${uploadURL};jsessionid=${sessionId}"
					enctype="multipart/form-data">
					<input type="hidden" id="fileType" name="fileType" value="${fileType}" />
					<input type="hidden" id="winName" name="winName" value="${winName}" />
					<input type="hidden" id="year" name="year" value="${year}y" /> <input
						type="hidden" id="month" name="month" value="${month}m" /> <input
						type="hidden" id="day" name="day" value="${day}d" /> <input
						type="hidden" name="pathArray"
						value="/${year}y/${month}m/${day}d/"> <input type="hidden"
						name="clientURL" value="${basePath}/file/doCreateFile.action" /> <input
						type="hidden" name="jsessionid" value="${sessionId}" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="border: 1px solid #c8d6f1;">
						<thead>
							<tr>
								<td colspan="2" class="font1">文件上传</td>
							</tr>
						</thead>
						<tr>
							<td width="25%" class="font4">支持的附件类型</td>
							<td width="75%" class="font3">
							<c:if test="${not empty fileType }">${fileType}</c:if>
							<c:if test="${empty fileType }">任意类型</c:if>&nbsp;
							</td>
						</tr>
						<tr>
							<td width="25%" class="font4">请选择文件</td>
							<td width="75%" class="font3"><input type="file" id="file1"
								name="file1" /><font color="red">*</font></td>
						</tr>
						<tr>
							<td colspan="2" style="background: #bee3f9;" align="center"
								valign="middle"><img style="cursor: pointer"
								src="${ctx }/resource/images/button/oa_icon_button_yes.png"
								onclick="javascript:doCreateFile()"> <img
								style="cursor: pointer"
								src="${ctx }/resource/images/button/oa_icon_button_close.png"
								onclick="javascript:closeIframe('createFile')"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</c:if>
</body>
</html>