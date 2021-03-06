<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>公司表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="companyController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="form-group">
			<label for="comName" class="col-sm-3 control-label">物业公司名称：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="comName" name="comName" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入物业公司名称"  datatype="*"  ignore="checked" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="comContact" class="col-sm-3 control-label">公司负责人：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="comContact" name="comContact" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入公司负责人"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="comPhone" class="col-sm-3 control-label">联系电话：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="comPhone" name="comPhone" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入联系电话"  datatype="m" ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="createBy" class="col-sm-3 control-label">创建人：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="createBy" name="createBy" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入创建人"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="createDate" class="col-sm-3 control-label">创建时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="createDate" type="text" class="form-control laydate-datetime"  ignore="ignore"  />
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
			var win = frameElement.api.opener;
			if (data.success == true) {
				frameElement.api.close();
			    win.reloadTable();
			    win.tip(data.msg);
			} else {
			    if (data.responseText == '' || data.responseText == undefined) {
			        $.messager.alert('错误', data.msg);
			        $.Hidemsg();
			    } else {
			        try {
			            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
			            $.messager.alert('错误', emsg);
			            $.Hidemsg();
			        } catch (ex) {
			            $.messager.alert('错误', data.responseText + "");
			            $.Hidemsg();
			        }
			    }
			    return false;
			}
		}
	});
		
});
</script>
</body>
</html>