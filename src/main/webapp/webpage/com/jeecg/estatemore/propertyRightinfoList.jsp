<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="propertyRightinfoList" checkbox="true" pagination="true" fitColumns="true" title="房间产权信息表" actionUrl="propertyRightinfoController.do?datagrid" idField="id" sortName="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="房间id"  field="roomId"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="房屋所有权人 现"  field="nowRight"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件名称 现"  field="nowCertificate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件号码 现"  field="nowCardnum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="房屋所有权人 原"  field="oldRight"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件名称 原"  field="oldCertificate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="证件号码 原"  field="oldCardnum"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="变更人"  field="changeBy"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="变更日期"  field="changeDate"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="note"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="propertyRightinfoController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="propertyRightinfoController.do?goAdd" funname="add"  width="768"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="propertyRightinfoController.do?goUpdate" funname="update"  width="768"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="propertyRightinfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="propertyRightinfoController.do?goUpdate" funname="detail"  width="768"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'propertyRightinfoController.do?upload', "propertyRightinfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("propertyRightinfoController.do?exportXls","propertyRightinfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("propertyRightinfoController.do?exportXlsByT","propertyRightinfoList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

</script>
