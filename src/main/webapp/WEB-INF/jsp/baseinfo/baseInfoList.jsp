<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%	
	//获取基本信息类型(0.科研人员1.行政人员2.博士后3.博士4.硕士5.其他人员)
	String baseInfoType=(String)request.getAttribute("baseInfoType");
 %>

<html>

<head>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/_include.jsp" /> 
<!--页面功能块动作事件-->
<script type="text/javascript" src="resources/js/jquery-1.7.1.min.js"></script>
<!--easyUI布局插件-->
<script type="text/javascript" src="resources/App_Addins/easyUI/jquery.easyui.min.js"></script>
<!-- 本页js -->
<script type="text/javascript" src="resources/js/baseinfo/baseInfoList.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 导出 cjk编码转换 -->
<script type="text/javascript" src="resources/js/cjkEncode.js"></script>

<!-- 上传-->
<script type="text/javascript" src="resources/js/jquery-form.js"></script>

<!-- 时间戳日期格式化-->
<script type="text/javascript" src="resources/js/formatTime.js"></script>
<!-- 选择作者 和 弹框居中样式-->
<script type="text/javascript" src="resources/js/searchName.js"></script>

</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<!-- 用户角色 -->
	<input type="hidden" id="role" value="${accountInfo.roleName}" />
	<!-- 用户账号id -->
	<input type="hidden" id="accountId" value="${accountInfo.accountId}" />
	<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
	<!-- scrollbarSize:0:去掉空白滚动条 -->
	<!-- 基本信息列表 -->
	<table id="dg"  data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0"
	    toolbar="#dlg-toolbar" style="overflow: hidden">
		  <thead>
		  <!-- 信息类型 -->
		  <input type="hidden" id="baseInfoType1" value="<%=baseInfoType%>"/>
		  <!-- 信息删除状态(当刚进列表时:baseInfoDel=1,点击"删除"按钮,改变数据库状态;当点击回收站后:baseInfoDel=2,点击"删除"按钮,彻底删除数据) -->
		  <input type="hidden" id="baseInfoDel"/>
		</thead> 
	</table>
			
	<div id="dlg-toolbar" style="padding:5px;height:auto;display: none">
		<table cellpadding="0" cellspacing="0">  
			<tr>  
				<td>  
                	<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>  
            	</td>  
            	<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">编辑</a>  
            	</td>
            	<td><div class="datagrid-btn-separator"></div></td>
            	<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="del()">删除</a>  
            	</td>  
            	<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-trash',plain:true" onclick="trash()">回收站</a>  
            	</td>
            	<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" id="recovery" onclick="recovery()">恢复</a>  
            	</td>
            	<td><div class="datagrid-btn-separator"></div></td>
            	<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-xls',plain:true" onclick="excel()">导出</a>  
            	</td>
            	<td><!-- prompt:'请输入检索关键字', -->
            		<input id="searchboxId" class="easyui-searchbox" data-options="menu:'#mm',searcher:doSearch" style="width:200px"></input>
            		<div id="mm">
						<div id="nId" data-options="name:'baseInfoName'">姓名</div>
						<div data-options="name:'baseInfoSex'">性别</div>
						<div id="bId" data-options="name:'baseInfoBirthDate'">出生日期</div>
						<div id="eId" data-options="name:'baseInfoEducation'">学历</div>
						<div id="dId" data-options="name:'baseInfoDegree'">学位</div>
						<div id="sId" data-options="name:'baseInfoSpecialty'">专业</div>
						<div id="tId" data-options="name:'baseInfoPositionalTitles'">职称</div>
						<div id="lId" data-options="name:'baseInfoLevel'">级别</div>
						<div id="pId" data-options="name:'baseInfoPost'">职务</div>
						<div id="wId" data-options="name:'baseInfoStartWorkTime'">参加工作时间</div>
						<div id="aetId" data-options="name:'abroadEndTime'">出国日期</div>
						
						<div id="mId" data-options="name:'baseInfoMentor'">合作导师</div>
						<div id="stId" data-options="name:'baseInfoStartTime'">进站时间</div>
						<div id="etId" data-options="name:'baseInfoEndTime'">出站时间</div>
						
						<div id="m1Id" data-options="name:'baseInfoMentor'">导师姓名</div>
						<div id="st1Id" data-options="name:'baseInfoStartTime'">入学时间</div>
						<div id="et1Id" data-options="name:'baseInfoEndTime'">毕业时间</div>
						
						<div id="aId" data-options="name:'baseInfoExternalAppellation'">外聘称谓</div>
						<!-- <div id="estId" data-options="name:'baseInfoEngageStartTime'">聘用开始日期</div>
						<div id="eetId" data-options="name:'baseInfoEngageEndTime'">聘用截止日期</div> -->
					</div>
            	</td>
			</tr>
		</table>
	</div>
	
	<!-- 基本信息列表 -->
	<!-- 新增、编辑基本信息 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" style="display: none">
		 <div style="margin: 13px;">
		 	<div ><h2 style="border-bottom:1px solid #999">基本信息</h2></div>
	        <form id="fm" method="post">
	         	<!-- 基本信息id -->
	         	<span style="display: none">
	         		<input type="text" id="baseInfoId" name="baseInfoId">
	         		<!-- 存放选择人员的文本框名称 -->
	         		<input type="text" id="name">
	         	</span>
	         	<!-- 基本信息类型 -->
	         	<span style="display: none"><input type="text" name="baseInfoType" id="baseInfoType"></span>
	         	<!-- 用来判断是弹窗是新增还是编辑 -->
	         	<span style="display: none"><input type="text" id="baseInfoPath"></span>
	         	<!-- 刚进入编辑页面时,存放教育经历id -->
	         	<span style="display: none"><input type="text" id="educationalIds" name="educationalIds"></span>
	         	<!-- 刚进入编辑页面时,存放工作经历id -->
	         	<span style="display: none"><input type="text" id="workIds" name="workIds"></span>
	         	<!-- 刚进入编辑页面时,存放社会兼职id -->
	         	<span style="display: none"><input type="text" id="jobIds" name="jobIds"></span>
	         	<!-- 刚进入编辑页面时,存放出国经历id -->
	         	<span style="display: none"><input type="text" id="abroadIds" name="abroadIds"></span>
	         	
	         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
	         		<!-- 人员基本信息 -->
	         		<div id="Staff" class="Staff" style="width:100%">
	         			<!-- 科研人员 -->
	         			<table id='Staff3' class='StaffInfo' style="display: none">
	         				<tbody>
	         					<!-- 管理员、领导、行政添加科研人员、行政人员和其他人员时,需先选择登录账号 -->
	         					<tr id="atr0" style="display: none">
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">请先选择账号:</label>
	         						</td>
	         						<td width="180">
	         							<input type="text" id="accountName0" name="accountName"  class="easyui-textbox">
	         							<input type="hidden" id="accountName0Id" name="accountId">
	         						</td>
	         					</tr>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'> <select id='baseInfoSex' class='easyui-combobox' name='baseInfoSex' panelMaxHeight='70px'>
									<option value=''>请选择</option><option value='1'>男</option><option value='0'>女</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>身份证号:</label></td>
									<td width='180'><input type='text' id='baseInfoIdNumber' name='baseInfoIdNumber' class='easyui-textbox'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>出生日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input id='baseInfoBirthDate' type='text' name='baseInfoBirthDate' class='easyui-datebox EdiInputText'></td>
								</tr>
						        <tr><td width='120' style='text-align:right;'><label class='in_label'>籍贯:</label></td>
						         	<td width='180'><input id='baseInfoNativePlace' type='text' name='baseInfoNativePlace' class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>民族:</label></td>
						         	<td width='180'><input type='text' id='baseInfoNation' name='baseInfoNation' class='easyui-textbox'></td>
						         </tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>政治面貌:</label></td>
						         	<td width='180'><select id='baseInfoPoliticalOutlook' class='easyui-combobox' name='baseInfoPoliticalOutlook' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='中共党员'>中共党员</option><option value='民盟'>民盟</option>
						         	<option value='民建'>民建</option><option value='九三'>九三</option><option value='致公'>致公</option><option value='农公'>农公</option>
						         	<option value='民进'>民进</option><option value='台盟'>台盟</option><option value='民革'>民革</option><option value='团员'>团员</option>
						         	<option value='非党群众'>非党群众</option><option value='无党派人士'>无党派人士</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>学历:</label></td><td width='180'>
						         	<select id='baseInfoEducation' class='easyui-combobox' name='baseInfoEducation' panelMaxHeight='90px'><option value=''>请选择</option>
						         	<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>本科</option></select></td>
					         	</tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>学位:</label></td><td width='180'>
						         	<select id='baseInfoDegree' class='easyui-combobox' name='baseInfoDegree' panelMaxHeight='90px'><option value=''>请选择</option>
						         	<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>学士</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>专业:</label></td><td width='180'>
						         	<input type='text' id='baseInfoSpecialty' name='baseInfoSpecialty' class='easyui-textbox'></td>
					         	</tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>职称:</label></td><td width='180'>
						         	<select id='baseInfoPositionalTitles' class='easyui-combobox' name='baseInfoPositionalTitles' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='0'>研究员</option><option value='1'>副研究员</option><option value='2'>助理研究员</option>
						         	<option value='3'>教授</option><option value='4'>副教授</option><option value='5'>讲师</option><option value='6'>助教</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>级别:</label>
						         	</td><td width='180'><select id='baseInfoLevel' class='easyui-combobox' name='baseInfoLevel' panelMaxHeight='90px'>
						         	<option value=''>请选择</option><option value='0'>高级</option><option value='1'>副高</option>
						         	<option value='2'>中级</option><option value='3'>初级</option></select></td></tr>
						         	<tr><td width='120' style='text-align:right;'><label class='in_label'>职务:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPost' name='baseInfoPost'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>是否博导:</label></td>
						         	<td width='180'><select id='baseInfoIfDoctorTutor' class='easyui-combobox' name='baseInfoIfDoctorTutor' panelMaxHeight='70px'>
						         	<option value=''>请选择</option><option value='0'>否</option><option value='1'>是</option></select></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>是否硕导:</label></td>
						         	<td width='180'><select id='baseInfoIfMasterTutor' class='easyui-combobox' name='baseInfoIfMasterTutor' panelMaxHeight='70px'>
						         	<option value=''>请选择</option><option value='0'>否</option><option value='1'>是</option>
						         	</select></td><td width='120' style='text-align:right;'><label class='in_label'>参加工作时间:</label></td>
						         	<td width='180'><input type='text' id='baseInfoStartWorkTime' name='baseInfoStartWorkTime' class='easyui-datebox'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>工行卡号(校园卡):</label></td>
						         	<td width='180'><input type='text' id='baseInfoCampusCard' name='baseInfoCampusCard'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>大连银行卡号(工资卡):</label></td>
						         	<td width='180'><input type='text' id='baseInfoPayrollCard' name='baseInfoPayrollCard' class='easyui-textbox'></td>
						         </tr>
						          <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>研究方向:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='baseInfoResearchDirection' name='baseInfoResearchDirection'
						         	class='easyui-textbox' style='width:488px;;height: 50px'></td>
						         </tr>
						         <tr>
						         <td width='120' style='text-align:right;'>
						         	<label class='in_label'>研究专长:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true'
						            id='baseInfoResearchExpertise' name='baseInfoResearchExpertise' class='easyui-textbox' style='width:488px;;height: 50px'></td>
								</tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>人才计划:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='baseInfoTalentPlan' name='baseInfoTalentPlan'
						         	class='easyui-textbox' style='width:488px;;height: 50px'></td>
						         </tr>
						         <tr>
						         <td width='120' style='text-align:right;'>
						         	<label class='in_label'>荣誉称号:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true'
						            id='baseInfoHonoraryTitle' name='baseInfoHonoraryTitle' class='easyui-textbox' style='width:488px;;height: 50px'></td>
								</tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
	         				</tbody>
		         		</table>
		         		
		         		<!-- 行政人员 -->
	         			<table id='Staff4' class='StaffInfo' style="display: none">
	         				<tbody>
	         					<!-- 管理员、领导、行政添加科研人员、行政人员和其他人员时,需先选择登录账号 -->
	         					<tr id="atr1" style="display: none">
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">请先选择账号:</label>
	         						</td>
	         						<td width="180">
	         							<input type="text" id="accountName1" name="accountName"  class="easyui-textbox">
	         							<input type="hidden" id="accountName1Id" name="accountId">
	         						</td>
	         					</tr>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'> <select class='easyui-combobox' id='baseInfoSex' name='baseInfoSex' panelMaxHeight='70px'>
									<option value=''>请选择</option><option value='1'>男</option><option value='0'>女</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>身份证号:</label></td>
									<td width='180'><input type='text' id='baseInfoIdNumber' name=baseInfoIdNumber class='easyui-textbox'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>出生日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input id='baseInfoBirthDate' type='text' name='baseInfoBirthDate' class='easyui-datebox EdiInputText'></td>
								</tr>
						         <tr><td width='120' style='text-align:right;'><label class='in_label'>籍贯:</label></td>
						         	<td width='180'><input id='baseInfoNativePlace' type='text' name='baseInfoNativePlace' class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>民族:</label></td>
						         	<td width='180'><input type='text' id='baseInfoNation' name='baseInfoNation'  class='easyui-textbox'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>政治面貌:</label></td>
						         	<td width='180'><select class='easyui-combobox' id='baseInfoPoliticalOutlook' name='baseInfoPoliticalOutlook' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='中共党员'>中共党员</option><option value='民盟'>民盟</option>
						         	<option value='民建'>民建</option><option value='九三'>九三</option><option value='致公'>致公</option><option value='农公'>农公</option>
						         	<option value='民进'>民进</option><option value='台盟'>台盟</option><option value='民革'>民革</option><option value='团员'>团员</option>
						         	<option value='非党群众'>非党群众</option><option value='无党派人士'>无党派人士</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>入党时间:</label></td>
						         	<td width='180'><input type='text' id='baseInfoMemberTime' name='baseInfoMemberTime' class='easyui-datebox'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>学历:</label></td><td width='180'>
						         	<select class='easyui-combobox' id='baseInfoEducation' name='baseInfoEducation' panelMaxHeight='90px'><option value=''>请选择</option>
						         	<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>本科</option></select></td>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>学位:</label></td><td width='180'>
						         	<select class='easyui-combobox' id='baseInfoDegree' name='baseInfoDegree' panelMaxHeight='90px'><option value=''>请选择</option>
						         	<option value='0'>博士</option><option value='1'>硕士</option><option value='2'>学士</option></select></td>
						         </tr>
						         <tr>
						        	<td width='120' style='text-align:right;'><label class='in_label'>专业:</label></td><td width='180'>
						         	<input type='text' id='baseInfoSpecialty' name='baseInfoSpecialty' class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>职称:</label></td><td width='180'>
						         	<select class='easyui-combobox' id='baseInfoPositionalTitles' name='baseInfoPositionalTitles' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='0'>研究员</option><option value='1'>副研究员</option><option value='2'>助理研究员</option>
						         	<option value='3'>教授</option><option value='4'>副教授</option><option value='5'>讲师</option><option value='6'>助教</option></select></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>级别:</label>
						         	<td width='180'><select class='easyui-combobox' id='baseInfoLevel' name='baseInfoLevel' panelMaxHeight='90px'>
						         	<option value=''>请选择</option><option value='0'>高级</option><option value='1'>副高</option>
						         	<option value='2'>中级</option><option value='3'>初级</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>职务:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPost' name='baseInfoPost'  class='easyui-textbox'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>参加工作时间:</label></td>
						         	<td width='180'><input type='text' id='baseInfoStartWorkTime' name='baseInfoStartWorkTime' class='easyui-datebox'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
	         				</tbody>
		         		</table>
		         		
		         		<!-- 博士、硕士 -->
	         			<table id='Staff1' class='StaffInfo' style="display: none">
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'> <select class='easyui-combobox' id='baseInfoSex' name='baseInfoSex' panelMaxHeight='70px'>
									<option value=''>请选择</option><option value='1'>男</option><option value='0'>女</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>身份证号:</label></td>
									<td width='180'><input type='text' id='baseInfoIdNumber' name=baseInfoIdNumber class='easyui-textbox'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>出生日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoBirthDate' name='baseInfoBirthDate' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>年级:</label></td>
									<td width='180'><input type='text' name='baseInfoGrade' class='easyui-combobox' id='yearChoose'></td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>导师姓名:</label>
									</td>
									<td width='180'>
										<input type='text' id='baseInfoMentor' name='baseInfoMentor' class='easyui-textbox'>
										<input type='hidden' id='baseInfoMentorId' name='baseInfoMentorId'>
									</td>
								</tr>
								<tr>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>入学时间:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoStartTime' name='baseInfoStartTime' class='easyui-datebox EdiInputText'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>毕业时间:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEndTime' name='baseInfoEndTime' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>学位论文题目:</label></td>
									<td width='180' colspan='3'><input type='text' id='baseInfoContactInformation' name='baseInfoContactInformation' class='easyui-textbox' style='width:488px;'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>就业单位:</label></td>
									<td width='180' colspan='3'><input type='text' id='baseInfoEmploymentCompany' name='baseInfoEmploymentCompany' class='easyui-textbox' style='width:488px;'></td>
								</tr>
								<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>在读期间获得奖项或荣誉:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoHonor' name='baseInfoHonor'
						         	class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
						         <tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>署名中心成果:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoAchievement' name='baseInfoAchievement'
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
								<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
	         				</tbody>
		         		</table>
		         		
		         		<!-- 博士后 -->
		         		<table id='Staff2' class='StaffInfo' style="display: none">
	         				<tbody>
         					 <!-- 博士后 -->
					         	<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'> <select class='easyui-combobox' id='baseInfoSex' name='baseInfoSex' panelMaxHeight='70px'>
									<option value=''>请选择</option><option value='1'>男</option><option value='0'>女</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>身份证号:</label></td>
									<td width='180'><input type='text' id='baseInfoIdNumber' name=baseInfoIdNumber class='easyui-textbox'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>出生日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoBirthDate' name='baseInfoBirthDate' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>合作导师:</label></td>
									<td width='180'>
										<input type='text' id='baseInfoMentor' name='baseInfoMentor' class='easyui-textbox'>
										<input type='hidden' id='baseInfoMentorId' name='baseInfoMentorId'>
									</td>
									<td width='120' style='text-align:right;'><label class='in_label'>出站报告:</label></td>
									<td width='180'><input type='text' id='baseInfoDepartureReport' name='baseInfoDepartureReport' class='easyui-textbox'></td>
								</tr>
								<tr>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>进站时间:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input  type='text' id='baseInfoStartTime' name='baseInfoStartTime' class='easyui-datebox EdiInputText'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>出站时间:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEndTime' name='baseInfoEndTime' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>就业单位:</label></td>
									<td width='180' colspan='3'><input type='text' id='baseInfoEmploymentCompany' name='baseInfoEmploymentCompany' class='easyui-textbox' style='width:488px;'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>研究方向:</label></td>
									<td width='180' colspan='3'><input type='text' id='baseInfoResearchDirection' name='baseInfoResearchDirection' class='easyui-textbox' style='width:488px;height: 50px'></td>
								</tr>
								<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'
						         	class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
	         				</tbody>
	         			</table>
	         			
	         			<!-- 其他人员 -->
	         			<table id='Staff5' class='StaffInfo' style="display: none">
	         				<tbody>
	         					<!-- 管理员、领导、行政添加科研人员、行政人员和其他人员时,需先选择登录账号 -->
	         					<tr id="atr2" style="display: none">
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">请先选择账号:</label>
	         						</td>
	         						<td width="180">
	         							<input type="text" id="accountName2" name="accountName"  class="easyui-textbox">
	         							<input type="hidden" id="accountName2Id" name="accountId">
	         						</td>
	         					</tr>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>姓名:</label></td>
									<td width='180'> <input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>性别:</label></td>
									<td width='180'> <select class='easyui-combobox' id='baseInfoSex' name='baseInfoSex' panelMaxHeight='70px'>
									<option value=''>请选择</option><option value='1'>男</option><option value='0'>女</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>身份证号/护照号:</label></td>
									<td width='180'><input type='text' id='baseInfoIdNumber' name=baseInfoIdNumber class='easyui-textbox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>国籍:</label></td>
						         	<td width='180'><input type='text' id='baseInfoNationality' name='baseInfoNationality' class='easyui-textbox'></td>
								</tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'>
						         	<label class='in_label'>职称:</label></td><td width='180'>
						         	<select class='easyui-combobox' id='baseInfoPositionalTitles' name='baseInfoPositionalTitles' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='0'>研究员</option><option value='1'>副研究员</option><option value='2'>助理研究员</option>
						         	<option value='3'>教授</option><option value='4'>副教授</option><option value='5'>讲师</option><option value='6'>助教</option></select></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>级别:</label>
						         	</td><td width='180'><select class='easyui-combobox' id='baseInfoLevel' name='baseInfoLevel' panelMaxHeight='90px'>
						         	<option value=''>请选择</option><option value='0'>高级</option><option value='1'>副高</option>
						         	<option value='2'>中级</option><option value='3'>初级</option></select></td>
						        </tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>职务:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPost' name='baseInfoPost'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>银行卡号:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPayrollCard' name='baseInfoPayrollCard' class='easyui-textbox'></td>
					         	</tr>
					         	<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>原工作单位:</label></td>
						         	<td width='180'><input type='text' id='baseInfoPrimaryWorkUnit' name='baseInfoPrimaryWorkUnit'  class='easyui-textbox'></td>
						         	<td width='120' style='text-align:right;'><label class='in_label'>外聘称谓:</label></td>
						         	<td width='180'><input type='text' id='baseInfoExternalAppellation' name='baseInfoExternalAppellation' class='easyui-textbox'></td>
					         	</tr>
					         	<tr>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>聘用开始日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEngageStartTime' name='baseInfoEngageStartTime' class='easyui-datebox EdiInputText'></td>
									<td id='baseInfoBirthDateTd1' width='120' style='text-align:right;'><label class='in_label'>聘用截止日期:</label></td>
									<td id='baseInfoBirthDateTd2' width='180'><input type='text' id='baseInfoEngageEndTime' name='baseInfoEngageEndTime' class='easyui-datebox EdiInputText'></td>
								</tr>
								<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>个人简介:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoProfile' name='baseInfoProfile'
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
					         	</tr>
								<tr>
						         	<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'><input type='text' data-options='multiline:true' id='baseInfoRemarks' name='baseInfoRemarks'"
						         	 class='easyui-textbox' style='width:488px;height: 50px'></td>
						         </tr>
	         				</tbody>
		         		</table>
	         		</div>
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
	</div>
	<!-- 底部按钮 -->
    <div id="dlg-buttons">
       	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 底部按钮 -->
    
    <!-- 删除提示框 -->
    <div id="dlgClose" class="easyui-dialog"  data-options="closed:true" >
     	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 删除提示框 -->
    
    <!-- 导出提示框 -->	
    <div id="dlgExcel" class="easyui-dialog" title="系统提示" data-options="iconCls:'icon-save',closed:true" style="width:260px;height:130px;padding:20px;text-align: center">
		文件生成成功,
		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' style="width:70px;height:30px;" onclick="exportExcel()">点此下载</a>
	</div>
    <!-- 导出提示框 -->
    
    <!-- 人员列表提示框 -->
	<div id="plg" class="easyui-dialog" title="请选择人员"  data-options="closed:true,buttons:[{text:'确定',handler:function(){alert(222)}},
	{text:'取消',handler:function(){$('#plg').dialog('close'); funClose()}}]" >
		 <div style="margin: 5px;">
		 	<!-- 判断是从作者进入选择人员列表,还是从合作者进入选择人员列表 -->
		 	<input type="hidden" id="typeNum"/>
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
	
	
	<div id="alg" class="easyui-dialog" title="请选择账号"  data-options="closed:true" >
		 <div style="margin: 5px;">
		 	<!-- 选择账号文本的名称 -->
		 	<input type="hidden" id="aName"/>
		 	<div style="padding-bottom: 13px">已选择:</div>
		 	<div class="baseInfoNameDiv1">
		 		<span class="cata-item " id="atype3" style="display:none;width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='getAccount(3,"")'>科研人员</span>
		 		<span class="cata-item " id="atype1" style="display:none;width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='getAccount(1,"")'>领导</span>
		 		<span class="cata-item " id="atype2" style="display:none;width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(2,"")'>行政人员</span>
		 		<span class="cata-item " id="atype4" style="display:none;width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(4,"")'>其他人员</span>
		 	 </div>
		 	<div class="baseInfoNameDiv2">
		 		<table id="accoutIdt"  width='100%'></table>
		 	</div>
		 </div>
	</div>
	<!-- 人员列表提示框 -->
	
    <!-- datagrid绑定右键菜单 -->
    <div id="menu" class="easyui-menu" style="width: 30px; display: none;"> 
		<!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
		<div id="btn_Modify" data-options="iconCls:'icon-edit'" onclick="edit()">编辑</div> 
		<!--放置一个隐藏的菜单Div-->
		<div id="btn_Delete" data-options="iconCls:'icon-cancel'" onclick="del()">移入回收站</div> 
		<!--放置一个隐藏的菜单Div-->
		<div id="btn_Delete" data-options="iconCls:'icon-attach'" onclick="upload()">上传材料</div> 
	</div>
 	<!-- datagrid绑定右键菜单 -->
 	
 	<!-- 上传材料弹框 -->
    <div id="dlgUpload" class="easyui-dialog" title="上传附件材料" data-options="iconCls:'icon-attach',closed:true" style="width:840px;height:240px;padding:10px;">
		<div style="margin: 13px;">
			<form id="fmUpload" method="post" enctype="multipart/form-data">
				<!-- 上传材料数据id -->
				<input type="hidden" name="relevanceId" id="relevanceId"/>
				<!-- 上传材料表名 -->
				<input type="hidden" name="tableName" id="tableName"/>
 				<div>
					<h2 style="border-bottom:1px solid #999">
						<span class="fileManage" style="display:block;float: left">附件管理</span>
						<span style="display:block;text-align: right;"><!-- iconCls:'icon-attach', -->
							<a href='javascript:void(0)' class='easyui-linkbutton' id="downloadAllFiles" style="width:60px;height:20px;border:1px  #bbb solid;border-radius:1px 1px 1px 1px;margin-top: 0">下载全部</a>
							<!-- accept:easyui filebox限制文件上传的类型 -->
							<input class="easyui-filebox" name="file" id="uploadFileid" data-options="prompt:'选择文件',buttonText:'添加文件 ',accept:'image/gif,image/jp2,image/jpeg,image/png,application/pdf,audio/mpeg,audio/mp4,video/mp4,application/msword' " style="width:55px;height:20px;border:1px #ff0000 solid;">
						</span>
					</h2>
				</div>
				<div style="border: 1px dotted #ccc;padding: 5px;margin-bottom: 12px; " data-field="files">
					<div>
						<table class="f-table">
							<thead>
								<tr>
									<th width="25%">文件名称</th>
									<th width="15%">文件类别</th>
									<th width="20%">描述说明</th>
									<th width="20%">上传时间</th>
									<th width="25%">操作</th>
								</tr>
							</thead>
							<tbody id="fileList"></tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
    <!-- 上传材料弹框 -->
    
    <!-- 选择文件后弹框 -->
    <div id="dlgUpload1" class="easyui-dialog" title="附件信息" data-options="closed:true,buttons:'#dlgUpload1-buttons'" style="width:840px;height:362px;padding:10px;">
		<div style="margin: 13px;">
			<form id="" method="post">
				<div>
					<h2 style="border-bottom:1px solid #999">文件信息</h2>
				</div>
				<div style="border: 1px dotted #ccc;padding: 5px;margin-bottom: 12px; " data-field="files">
					<div>
						<table class="f-table">
							<thead>
								<tr>
									<td width="30%">文件名称</td>
									<td>
										<input type="text" id="fileFullName" name="fileFullName" required="true" class='easyui-textbox' style="padding: 3px;border: 1px solid #BDC7D8;"/>
									</td>
								</tr>
								<tr>
									<td width="20%">文件类别</td>
									<td id="typeBaseInfo0" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">身份证</option>
											<option value="1">毕业证</option>
											<option value="2">学位证</option>
											<option value="3">兼职证明</option>
											<option value="4">人才计划证明</option>
											<option value="5">荣誉称号证明</option>
											<option value="6">其他</option>
										</select>
									</td>
									<td id="typeBaseInfo1" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">身份证</option>
											<option value="1">毕业证</option>
											<option value="2">学位证</option>
											<option value="3">其他</option>
										</select>
									</td>
									<td id="typeBaseInfo2" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">博士后证书</option>
											<option value="1">其他成果证明</option>
											<option value="2">其他</option>
										</select>
									</td>
									<td id="typeBaseInfo3" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">获奖证书</option>
											<option value="1">其他成果</option>
											<option value="2">其他</option>
										</select>
									</td>
									<td id="typeBaseInfo4" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">获奖证书</option>
											<option value="1">其他成果</option>
											<option value="2">其他</option>
										</select>
									</td>
									<td id="typeBaseInfo5" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">身份证</option>
											<option value="1">护照</option>
											<option value="2">聘书</option>
											<option value="3">合作协议</option>
											<option value="4">其他</option>
										</select>
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div style="border: 1px dotted #ccc;padding: 5px;margin-bottom: 12px; " data-field="files">
					<div>
						<h3 style="border-bottom:1px solid #999">文件说明</h3>
					</div>
					<div>
						<textarea id="fileContent" rows="4" cols="106" placeholder="关于该文件内容的简要说明(选填)" style="padding: 3px;border: 1px solid #BDC7D8;"></textarea>
					</div>
				</div> 
			</form>
		</div>
	</div>
	
	<!-- 底部按钮 -->
    <div id="dlgUpload1-buttons">
       	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUploadFile()" style="width:90px">开始上传</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="delUploadFile()" style="width:90px">取消</a>
    </div>
    <!-- 底部按钮 -->
	<!-- 选择文件后弹框 -->
	
	 <!-- 预览弹出框 -->	
    <div id="dlgViewFile" class="easyui-dialog" title="系统提示" data-options="iconCls:'icon-save',closed:true" style="width:260px;height:130px;padding:20px;text-align: center">
	</div>
    <!-- 预览弹出框 -->
</body>
</html>