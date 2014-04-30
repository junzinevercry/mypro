<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>web_demo</title>
<script type="text/javascript" src="${ctx }/resource/script/file.js"></script>
<script>
function doCreateJob(isPublish){
		if($.trim($("#userType").val())==''){
			alert('请选择人员类型。');
			return;
		}
		if($.trim($("#num").val())==''){
			alert('请输入人员编号。');
			return;
		}
		if($.trim($("#name").val())==''){
			alert('请输入人员名称。');
			return;
		}
		if($.trim($("#roleIds").val())==''){
			alert('请选择角色。');
				return;
		}
		<c:choose>
			<c:when test="${empty orgId }">
				if($.trim($("#orgId").val())==''){
					alert('请选择组织机构。');
						return;
				}
			</c:when>
			<c:otherwise>
				if($.trim($("#deptId").val())==''){
					alert('请选择部门。');
					return;
				}
				if($.trim($("#jobId").val())==''){
					alert('请选择职位。');
					return;
				}
			</c:otherwise>
		</c:choose>
		$.ajax({
			type:'post',
			url:'${ctx}/user/countByUserName.action',
			dataType:"json",
			data:{userNum:$.trim($('#num').val()),id:'${user.id}'},
			async:false,
			cache:false,
			success:function(data){
				if(data.type == 'success'){
					$.ajax({
						type:'post',
						url:'${ctx}/user/doUpdateUser.action',
						dataType:"json",
						data:$("#userInfo").serialize(),
						async:false,
						cache:false,
						success:function(data){
							if(data.type == 'success'){
								alert('操作成功');
								callBackFunc('','home','gotoUserList');
								closeIframe();
							}
						}
					});
				}else{
					alert('人员编号重复！');
					$('#num').focus();
					return;
				}
			}
		});
	}
	var oriDeptId = '';
	function selectDept(){
		oriDeptId = $("#deptId").val();
		ShowIframe('选择所属部门','selectDept','${ctx}/department/gotoDepartmentTreeForRadio.action?openName='+window.name,990,350,true);
	}
	function selectJob(){
		if($.trim($("#deptId").val())==''){
			alert('请先选择所属部门。');
				return;
		}
		ShowIframe('选择职位','selectJob','${ctx}/job/gotoJobTreeForRadio.action?openName='+window.name+"&deptId="+$("#deptId").val(),990,350,true);
	}
	function gotoRoleList(){
		createRoleJson();
		var url = encodeURI(encodeURI('${ctx}/role/gotoRoleListForCheckBox.action?idJson='+$('#idJson').val()+'&openerName='+window.name));
		ShowIframe('选择角色','selectRole',url,990,600,true);
	}
	function addRole(roleArray){
		var ids = "";
		var names = "";
		for(var i in roleArray){    
			var obj = roleArray[i];
			ids = ids + obj.id + ",";
			names = names + obj.name + ",";
		}
		$('#roleIds').val(ids.substring(0, ids.length-1));
		$('#roleNames').val(names.substring(0, names.length-1));
	}
	function createRoleJson(){
		var ids = $('#roleIds').val();
		var names = $('#roleNames').val();
		if(ids!=""){
			var str = "[";
			var idArray = ids.split(",");
			var nameArray = names.split(",");
			for(var i = 0 ;i < idArray.length ;i++){
				str = str + '{"id":"' + idArray[i] + '","name":"' + nameArray[i] + '"}';
				if(i < idArray.length-1){
					str = str + ',';
				}
			}
			str = str + ']';
			$('#idJson').val(str);
		}else{
			$('#idJson').val("");
		}
	}
	function setSelDept(json){
		var deptId = json.aid;
		if(oriDeptId != deptId){
			$('#jobId').val("");
			$('#jobName').val("");
		}
		$('#deptId').val(deptId);
		$('#deptName').val(json.aname);
	}
	function setSelJob(json){
		$('#jobId').val(json.aid);
		$('#jobName').val(json.aname);
	}
	$(function($) {
		$("#nozero").hide();
		if($('#signy').attr('checked')=='checked'){
			$('#signFilePath').show();
			$('#filea').hide();
		}else{
			$('#fileDiv').hide();
			$('#signFilePath').hide();
		}
	});
	function checkedYse(){
		if($('#signy').attr('checked')=='checked'){
			$('#signFilePath').show();
		}else{
			$('#signFilePath').hide();
		}
	}
	function selectFile(){
		gotoCreateFile('${ctx}','userUpdate','bmp,jpg,png');
	}
	function setUpload(file){
		$("#filea").hide();
		$("#fileDiv").html(file.html);
	}
	
</script >
</head>
<body bgcolor="#e1f4ff">
<div class="basecontent">
	<div class="tablebox">
		<input type="hidden" id="idJson" name="idJson">
		<form action="${ctx}/job/doUpdateCreateUser.action" id="userInfo" name="userInfo" method="post">
			<input type="hidden" id="id" name="id" value="${user.id }">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #c8d6f1;">
				<thead>
					<tr>
						<td colspan="2"  class="font1">编辑人员</td>
					</tr>
				</thead>
				<tr>
			      	<td class="font4" width="20%">员工类型</td>
					<td class="font3" >
						<select id="userType" name="userType">
							<option value="">请选择</option>
							<c:forEach var="userType" items="${userTypeList }">
								<option value="${userType.num }" <c:if test="${userType.num==user.userType }">selected="selected"</c:if>>${userType.name }</option>
							</c:forEach>
						</select>
						<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
					</td>
				</tr>
				<tr>
			      	<td class="font4">人员编号</td>
					<td class="font3" >
						<input type="text" size="50" id="num" name="num" maxlength="30" value="${user.num }"/>
						<span id="nozero" style="color: red;">&nbsp;用户名重复&nbsp;</span>
						<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td class="font4">人员名称</td>
					<td class="font3">
						<input type="text" size="50" id="name" name="name" maxlength="30" value="${user.name }"/>
						<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td class="font4">角色</td>
					<td class="font3">
						<input type="text" size="50" id="roleNames" name="roleNames" maxlength="30" onclick="gotoRoleList()" value="${userRole.names }"/>
						<input type="hidden" id="roleIds" name="roleIds" value="${userRole.ids }">
						<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty orgId }">
						<tr>
							<td class="font4">组织机构</td>
							<td class="font3">
								<select id="orgId" name="orgName">
									<option value="">请选择</option>
									<c:forEach var="org" items="${orgList }">
										<option value="${org.id }" <c:if test="${org.id==user.orgId }">selected="selected"</c:if>>${org.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="font4">部门</td>
							<td class="font3" >
								<input type="text" size="50" id="deptName" name="deptName" maxlength="30" readonly="readonly" onclick="selectDept()" value="${user.deptName }"/>
								<input type="hidden" id="deptId" name="deptId" value="${user.deptId }">
								<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
							</td>
						</tr>
						<tr>
							<td class="font4" >职位</td>
							<td class="font3">
								<input type="text" size="50" id="jobName" name="jobName" maxlength="30" readonly="readonly" onclick="selectJob()" value="${user.jobName }"/>
								<input type="hidden" id="jobId" name="jobId" value="${user.jobId }">
								<span style='color: red; font-size: 18px;'>&nbsp;*&nbsp;</span>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td class="font4">电子印章</td>
					<td class="font3">
						<input type="radio" name="sign" id="signy" value="1" <c:if test="${not empty user.signPassword }">checked="checked"</c:if> onclick="checkedYse();">有</input>
						<input type="radio" name="sign" id="signn" value="0" <c:if test="${empty user.signPassword }">checked="checked"</c:if> onclick="checkedYse();">无</input>
					</td>
				</tr>
				<tr id = "signFilePath">
					<td class="font4">印章文件</td>
					<td class="font3">
						<a href="#" onclick="selectFile()" id="filea">选择文件</a>
						<div id="fileDiv">
							<span class="pointer" onclick="doDownloadFile('${ctx}','${file.filePath}','${file.fileName}')">${file.fileName}</span>
							<input type='hidden' name='fileId' id='fileId' value='${file.id }'/>
							<input type='hidden' name='realName' id='realName' value='${file.fileName }'/>
							<input type='hidden' name='fileName' id='fileName' value='${file.fileName }'/>
							<input type='hidden' name='filePath' id='filePath' value='${file.filePath }'/>
							<span style='cursor:pointer;color:red' onclick="gotoCreateFile('${ctx}','userUpdate','bmp,jpg,png')"> 重新上传 </span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="font4">性别</td>
					<td class="font3">
						<select id="sex" name="sex">
							<option value="">请选择</option>
							<c:forEach var="sex" items="${sexList }">
								<option value="${sex.num }" <c:if test="${sex.num==user.sex }">selected="selected"</c:if>>${sex.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="font4" >年龄</td>
					<td class="font3">
						<input type="text" size="50" id="age" name="age" maxlength="3" value="${user.age }"/>
					</td>
				</tr>
				<tr>
					<td class="font4">电话</td>
					<td class="font3">
						<input type="text" size="50" id="phone" name="phone" maxlength="15" value="${user.phone }"/>
					</td>
				</tr>
				<tr>
					<td class="font4">地址</td>
					<td class="font3">
						<input type="text" size="50" id="address" name="address" maxlength="100" value="${user.address }"/>
					</td>
				</tr>
				<tr>
	
		     		<td colspan="2" style="background: #bee3f9;" align="center" valign="middle"   >	
			        	<!--  <div class="button">
			            	<ul>
			                	<li onclick="doCreateJob()">提交</li>
			                    <li onclick="closeIframe()">关闭</li>
			                </ul>
			            </div>-->
			             <img src="${ctx }/resource/images/button/oa_icon_button_open.png" onclick="doCreateJob()">
			             <img src="${ctx }/resource/images/button/oa_icon_button_close.png" onclick="closeIframe()">
			        </td>
		     	</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>