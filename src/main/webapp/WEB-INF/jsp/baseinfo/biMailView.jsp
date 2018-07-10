<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/_include.jsp" /> 
<!--页面功能块动作事件-->
<script type="text/javascript" src="resources/js/jquery-1.7.1.min.js"></script>
<!--easyUI布局插件-->
<script type="text/javascript" src="resources/App_Addins/easyUI/jquery.easyui.min.js"></script>
<!-- 本页js -->
<script type="text/javascript" src="resources/js/baseinfo/biMailView.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 导出 cjk编码转换 -->
<script type="text/javascript" src="resources/js/cjkEncode.js"></script>

<!-- 上传-->
<script type="text/javascript" src="resources/js/jquery-form.js"></script>

<!-- 时间戳日期格式化-->
<script type="text/javascript" src="resources/js/formatTime.js"></script>
<!-- 选择作者-->
<script type="text/javascript" src="resources/js/searchName.js"></script>

</head>
<body style="overflow-x : hidden;">

 	<!-- 基本信息Id -->
 	<input type="hidden" id="baseInfoId" value="${accountInfo.baseInfoId}" />
	<!-- 用户角色 -->
	<input type="hidden" id="role" value="${accountInfo.roleName}" />
	<input type="hidden" id="accountId" value="${accountInfo.accountId}" />
	<!-- 基本信息Id -->
 	<input type="hidden" id="baseInfoType" value="${baseInfoType}" />
	<!-- 基本信息明细 -->
	<div id="dlgView" class="panel"  style="width: auto; height: auto;" data-options="closed:true,buttons:'#dlg-buttons'">
		<div class="panel-body panel-body-noheader panel-body-noborder">
		<center>
			<div class="base_information" style="text-align:left;">
			<div id="content">
				<div class="base_info_head">
					<table width="100%">
						<tbody>
							<tr>
								<td width="90%">
									<h2> 通讯录 </h2>
								</td>
								<td width="10%" id="editBtn">
									<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" plain="true">
										<span class="l-btn-left">
											<span class="l-btn-text icon-edit" style="padding-left: 20px;margin: 0 0px;" onclick="editBaseInfo()">编辑</span>
										</span>
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="height:5px;"></div>
				<form id="fmv" method="post">
					<div id="Staff" class="Staff" style="width:100%;">
						<table id='Staff1' class='StaffInfo'>
							<tbody>
								<tr>
									<td width='180' style="display: none"> <input type='text'name='mailListId' class='easyui-textbox'></td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">姓名:</label>
	         						</td>
	         						<td width="180">
	         							<input type="text" id="baseInfoName" name="baseInfoName"  class="easyui-textbox">
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">手机号码:</label>
	         						</td>
	         						<td width='180'> 
	         							<input type='text' name='mailListPhone' class='easyui-textbox'>
	         						</td>
	         					</tr>
	         					<tr id="tr1">
									<td width='120' style='text-align:right;'>
									<label class='in_label'>小号:</label></td>
									<td width='180'> 
										<input type='text' name='mailListPhone1' class='easyui-textbox'>
									</td>
									<td id="tdstudio1" width='120' style='text-align:right;'>
										<label class='in_label'>工作室号:</label></td>
									<td id="tdstudio2" width='180' id="">
										<input type='text' name='mailListStudio' class='easyui-textbox'>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>办公电话:</label>
									</td>
									<td width='180'> 
										<input type='text' name='mailListOph' class='easyui-textbox'>
									</td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>电子邮箱:</label>
									</td>
									<td width='180'> 
										<input type='text' name='mailListEmail' class='easyui-textbox'>
									</td>
								</tr>
								<tr id="tr2" style="display: none">
									<td width='120' style='text-align:right;'>
									<label class='in_label'>职称:</label></td>
									<td width='180'> 
										<select id='baseInfoPositionalTitles' class='easyui-combobox' name='baseInfoPositionalTitles' panelMaxHeight='100px'>
								        	<option value=''>请选择</option><option value='0'>研究员</option><option value='1'>副研究员</option><option value='2'>助理研究员</option>
								         	<option value='3'>教授</option><option value='4'>副教授</option><option value='5'>讲师</option><option value='6'>助教</option></select>
									</td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>职务:</label>
									</td>
									<td width='180'> 
										<input type='text' id='baseInfoPost' name='baseInfoPost' class='easyui-textbox'>
									</td>
								</tr>
								<tr id="tr3" style="display: none">
									<td width='120' style='text-align:right;'>
										<label class='in_label'>单位:</label>
									</td>
									<td width='180' colspan='3'>
										<input type='text' id='mailListCompany' name='mailListCompany' class='easyui-textbox' style="width: 448px">
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>通讯地址:</label>
									</td>
									<td width='180' colspan='3'>
										<input type='text' name='mailListAddress' class='easyui-textbox' style="width: 448px">
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>备注:</label>
									</td>
						         	<td width='180' colspan='3'>
						         		<input type='text' data-options='multiline:true' id='baseInfoRemarks' name='mailListRemarks' class='easyui-textbox' style='width:448px;height: 50px'>
						         	</td>
					         	</tr>
							</tbody>
						</table>
					</div>
					<div id="Staff2">
						<!-- 教育经历-->
		         		<div class="model_list" id="educationalExperience" ></div>
		         		<!-- 工作经历 -->
		         		<div class="model_list" id="workExperience" ></div>
		         		<!-- 社会兼职 -->
		         		<div class="model_list" id="socialAppointments" ></div>
		         		<!-- 出国经历 -->
		         		<div class="model_list" id="abroadExperience" ></div>
					</div>
				</form>
			</div>
			
			<!-- 底部按钮 -->
		    <div id="dlg-buttons" style="text-align: center;display: none">
		       	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
		       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancle()" style="width:90px">取消</a>
		    </div>
		    <!-- 底部按钮 -->
			</div>
		</center>
		</div>
	</div>
    
    <!-- 人员列表提示框 -->
	<div id="plg" class="easyui-dialog" title="请选择人员"  data-options="closed:true,buttons:[{text:'确定',handler:function(){alert(222)}},
	{text:'取消',handler:function(){$('#plg').dialog('close'); funClose()}}]" >
		 <div style="margin: 5px;">
		 	<!-- 判断是从作者进入选择人员列表,还是从合作者进入选择人员列表 -->
		 	<input type="text" id="typeNum"/>
		 	<!-- 合作者:选择人员的索引 -->
		 	<input type="hidden" id="controllerNum"/>
		 	<div style="padding-bottom: 13px">已选择:</div>
		 	<div class="baseInfoNameDiv1">
		 		<span class="cata-item selected" id="type0" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(0,"","")'>科研人员</span>
		 		<span class="cata-item " id="type1" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(1,"","")'>行政人员</span>
		 		<span class="cata-item " id="type2" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(2,"","")'>博士后</span>
		 		<span class="cata-item " id="type3" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(3,"","")'>博士</span>
		 		<span class="cata-item " id="type4" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(4,"","")'>硕士</span>
		 		<span class="cata-item " id="type5" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(5,"","")'>其他人员</span>
		 	 </div>
		 	<div class="baseInfoNameDiv2">
		 		<table id="personId"  width='100%'></table>
		 	</div>
		 </div>
	</div>
	<!-- 人员列表提示框 -->
</body>
</html>