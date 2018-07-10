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
<script type="text/javascript" src="resources/js/baseinfo/baseInfoView.js"></script>
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
									<h2> 基本信息 </h2>
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
					<span style="display: none"><input type="text" id="educationalIds" name="educationalIds"></span>
		         	<!-- 刚进入编辑页面时,存放工作经历id -->
		         	<span style="display: none"><input type="text" id="workIds" name="workIds"></span>
		         	<!-- 刚进入编辑页面时,存放社会兼职id -->
		         	<span style="display: none"><input type="text" id="jobIds" name="jobIds"></span>
		         	<!-- 刚进入编辑页面时,存放出国经历id -->
		         	<span style="display: none"><input type="text" id="abroadIds" name="abroadIds"></span>
					<div id="Staff" class="Staff" style="width:100%;">
						<table id='Staff1' class='StaffInfo'>
							<tbody>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='180' style="display: none"> <input type='text'name='baseInfoId' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'>
										<select class='easyui-combobox' id='baseInfoSex' name='baseInfoSex' panelMaxHeight='70px'>
											<option value='1'>男</option>
											<option value='0'>女</option>
										</select>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label id="baseInfoIdNumberLable" class='in_label'>身份证号:</label></td>
									<td>
										<input type='text' id='baseInfoIdNumber' name=baseInfoIdNumber class='easyui-textbox' >
									</td>
									<td id="birthDate1" style="display: none"><label class='in_label'>出生日期:</label></td>
									<td id="birthDate2" style="display: none">
										<input type='text' id='baseInfoBirthDate' name='baseInfoBirthDate' class='easyui-datebox EdiInputText' >
									</td>
										<td width='120' style='text-align:right;display: none' id="nationalityId1"><label class='in_label'>国籍:</label></td>
		         						<td width='180' style="display: none" id="nationalityId2"><input type='text' id='baseInfoNationality' name='baseInfoNationality' class='easyui-textbox'></td>
									
								</tr>
								<tr id="trId1">
									<td width='120' style='text-align:right;'><label  class='in_label'>籍贯:</label></td>
						         	<td><input id='baseInfoNativePlace' type='text' name='baseInfoNativePlace' class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>民族:</label></td>
						         	<td><input type='text' id='baseInfoNation' name='baseInfoNation' class='easyui-textbox' ></td>
						        </tr>
						        <tr id="trId2">
						         	<td width='120' style='text-align:right;'><label class='in_label'>政治面貌:</label></td>
						         	<td><select id='baseInfoPoliticalOutlook' class='easyui-combobox' name='baseInfoPoliticalOutlook' panelMaxHeight='100px'>
						         	<option value='中共党员'>中共党员</option><option value='民盟'>民盟</option>
						         	<option value='民建'>民建</option><option value='九三'>九三</option><option value='致公'>致公</option><option value='农公'>农公</option>
						         	<option value='民进'>民进</option><option value='台盟'>台盟</option><option value='民革'>民革</option><option value='团员'>团员</option>
						         	<option value='非党群众'>非党群众</option><option value='无党派人士'>无党派人士</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>学历:</label></td><td width='180'>
						         		<select id='baseInfoEducation' class='easyui-combobox' name='baseInfoEducation' panelMaxHeight='90px'>
						         		<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>本科</option></select>
						         	</td>
						         </tr>
						         <tr id="trId3">
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>学位:</label></td><td width='180'>
						         	<select id='baseInfoDegree' class='easyui-combobox' name='baseInfoDegree' panelMaxHeight='90px'>
						         	<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>学士</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>专业:</label></td><td width='180' >
						         	<input type='text' id='baseInfoSpecialty' name='baseInfoSpecialty' class='easyui-textbox'></td>
						        </tr>
						        <tr>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>职称:</label></td><td width='180'>
						         	<select id='baseInfoPositionalTitles' class='easyui-combobox' name='baseInfoPositionalTitles' panelMaxHeight='100px' >
						         	<option value='0'>研究员</option><option value='1'>副研究员</option><option value='2'>助理研究员</option>
						         	<option value='3'>教授</option><option value='4'>副教授</option><option value='5'>讲师</option><option value='6'>助教</option>
						         	</select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>级别:</label>
						         	</td><td width='180'><select id='baseInfoLevel' class='easyui-combobox' name='baseInfoLevel' panelMaxHeight='90px' >
						         	<option value='0'>高级</option><option value='1'>副高</option>
						         	<option value='2'>中级</option><option value='3'>初级</option></select></td></tr>
						         <tr><td width='120' style='text-align:right;'><label class='in_label'>职务:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPost' name='baseInfoPost'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;display: none' id="doctorTutor1"><label class='in_label'>是否博导:</label></td>
						         	<td width='180' style="display: none" id="doctorTutor2"><select id='baseInfoIfDoctorTutor' class='easyui-combobox' name='baseInfoIfDoctorTutor' panelMaxHeight='70px'>
						         	<option value='0'>否</option><option value='1'>是</option></select>
						         	<td width='120' style='text-align:right;display: none' id="payrollCardId1"><label class='in_label'>银行卡号:</label></td>
		         					<td width='180' style="display: none" id="payrollCardId2"><input type='text' id='baseInfoPayrollCard' name='baseInfoPayrollCard' class='easyui-textbox' ></td>
						        </tr>
						        <tr id="trId4">
						         	<td width='120' style='text-align:right;'><label class='in_label'>是否硕导:</label></td>
						         	<td width='180'><select id='baseInfoIfMasterTutor' class='easyui-combobox' name='baseInfoIfMasterTutor' panelMaxHeight='70px' >
						         	<option value=''>请选择</option><option value='0'>否</option><option value='1'>是</option>
						         	</select></td><td width='120' style='text-align:right;'><label class='in_label'>参加工作时间:</label></td>
						         	<td width='180'><input type='text' id='baseInfoStartWorkTime' name='baseInfoStartWorkTime' class='easyui-datebox'></td>
						         </tr>
						         <tr id="trId5">
						         	<td width='120' style='text-align:right;'><label class='in_label'>工行卡号(校园卡):</label></td>
						         	<td width='180'><input type='text' id='baseInfoCampusCard' name='baseInfoCampusCard'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>大连银行卡号(工资卡):</label></td>
						         	<td width='180'><input type='text' id='baseInfoPayrollCard' name='baseInfoPayrollCard' class='easyui-textbox'></td>
						         </tr>
						         <tr id="trId6">
						         	<td width='120' style='text-align:right;'><label class='in_label'>人才计划:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoTalentPlan' name='baseInfoTalentPlan'
						         	 class='easyui-textbox' style='width:488px;height: 50px' ></td>
						         </tr>
						         <tr>
						         	 <td width='120' style='text-align:right;'>
						         	<label class='in_label'>荣誉称号:</label></td><td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true'
						         	 id='baseInfoHonoraryTitle' name='baseInfoHonoraryTitle' class='easyui-textbox' style='width:488px;;height: 50px'>
						         	 </td>
						         </tr>
					         	<tr id="trId7" style="display: none">
						         	<td width='120' style='text-align:right;'><label class='in_label'>原工作单位:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPrimaryWorkUnit' name='baseInfoPrimaryWorkUnit'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>外聘称谓:</label></td>
						         	<td width='180'><input type='text' id='baseInfoExternalAppellation' name='baseInfoExternalAppellation' class='easyui-textbox'></td>
						         </tr>
						         <tr id="trId8" style="display: none">
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>聘用开始日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEngageStartTime' name='baseInfoEngageStartTime' class='easyui-datebox EdiInputText'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>聘用截止日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEngageEndTime' name='baseInfoEngageEndTime' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr id="trId9" style="display: none">
						         	<td width='120' style='text-align:right;'><label class='in_label'>个人简介:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoProfile' name='baseInfoProfile'
						         	class='easyui-textbox' style='width:488px;height: 50px' ></td>
						         </tr>
						         <tr >
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'
						         	 class='easyui-textbox' style='width:488px;height: 50px' ></td>
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
		 		<span class="cata-item selected" id="type0" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(0,"","")'>科研人员</span>
		 		<span class="cata-item " id="type1" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(1,"","")'>行政人员</span>
		 		<span class="cata-item " id="type2" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(2,"","")'>博士后</span>
		 		<span class="cata-item " id="type3" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(3,"","")'>博士</span>
		 		<span class="cata-item " id="type4" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(4,"","")'>硕士</span>
		 		<span class="cata-item " id="type5" style="width:56px;height: 23px;padding: 4px" onclick='searchByType(5,"","")'>其他人员</span>
		 	 </div>
		 	<div class="baseInfoNameDiv2">
		 		<table id="personId"  width='100%'></table>
		 	</div>
		 </div>
	</div>
	<!-- 人员列表提示框 -->
</body>
</html>