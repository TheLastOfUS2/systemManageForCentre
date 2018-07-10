<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
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
</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
	<!-- scrollbarSize:0:去掉空白滚动条 -->
	<!-- 基本信息列表 -->
	<table id="dg" title="科研人员列表"
	   data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0"   style="overflow: hidden">
		  <thead>
		  <!-- 信息类型 -->
		   <input type="hidden" id="baseInfoType1" value="<%=baseInfoType%>"/>
			<!-- <tr>hidden="hidden":隐藏th
				<!-- <th id="baseInfoId" hidden="hidden" data-options="field:'baseInfoId'"></th>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'baseInfoName',width:'80',formatter : function(value,row,index){  
	            				 if(value!=null && value!=''){
	            				    return value
	            				 } else{return '--'}
                                }">姓名</th>
	            <th data-options="field:'baseInfoSex',width:'80', formatter : function(value,row,index){  
	            				 if(value!=null && value!=''){
	            				   if(value=='1'){return '男'} else if(value=='0'){return '女'}
	            				 } else{return '--'}
                                }">性别
                </th>
	            <th data-options="field:'baseInfoBirthDate',width:'80',formatter : function(value){
			            		if(value!=null && value!=''){
				            		var date = new Date(value);
			                        var y = date.getFullYear();
			                        var m = date.getMonth() + 1;
			                        var d = date.getDate();
			                        return y + '-' +m + '-' + d;
		                        }else{return '--'}
		                        }">出生日期
                </th>
                <th data-options="field:'baseInfoEducation',width:'80', formatter : function(value,row,index){  
	                            if(value!=null && value!=''){
	                            	if(value=='0'){return '博士'} else if(value=='1'){return '硕士'} else if(value=='2'){return '本科'} 
	                            }else{return '--'}
	                            }">学历
                </th>
                <th data-options="field:'baseInfoPositionalTitles',width:'80', formatter : function(value,row,index){  
	                            if(value!=null && value!=''){
	                            	if(value=='0'){return '研究员'} else if(value=='1'){return '副研究员'} else if(value=='2'){return '助理研究员'} 
		                            else if(value=='3'){return '教授'} else if(value=='4'){return '副教授'} else if(value=='5'){return '讲师'}
		                            else if(value=='6'){return '助教'}
		                            }else{return '--'}
	                            }">职称
                </th>
                <th data-options="field:'baseInfoLevel',width:'80', formatter : function(value,row,index){  
	                             if(value!=null && value!=''){
	                             	 if(value=='0'){return '高级'} else if(value=='1'){return '副高'} else if(value=='2'){return '中级'} 
		                             else if(value=='3'){return '初级'}
		                             }else{return '--'}
	                            }">级别
                </th>
                <th data-options="field:'baseInfoIfDoctorTutor',width:'80', formatter : function(value,row,index){  
                               if(value!=null && value!=''){
	                             	if(value=='0'){return '否'} else if(value=='1'){return '是'}  
	                             }else{return '--'}
                                }">是否博导
                </th>
                <th data-options="field:'baseInfoIfMasterTutor',width:'80', formatter : function(value,row,index){  
                                if(value!=null && value!=''){
	                             	if(value=='0'){return '否'} else if(value=='1'){return '是'}   
	                             }else{return '--'}
                                }">是否硕导
                </th>
                 <th data-options="field:'baseInfoStartWorkTime',width:'80',formatter : function(value){
		                       if(value!=null && value!=''){
	                             	var date = new Date(value);
			                        var y = date.getFullYear();
			                        var m = date.getMonth() + 1;
			                        var d = date.getDate();
			                        return y + '-' +m + '-' + d;
	                             }else{return '--'}
		                        }">参加工作时间
                </th> 
			</tr>-->
		</thead> 
	</table>
	<!-- 基本信息列表 -->
	<!-- 新增、编辑基本信息 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" >
		 <div style="margin: 13px;">
		 	<div ><h2 style="border-bottom:1px solid #999">基本信息</h2></div>
	         <form id="fm" method="post">
	         	<!-- 基本信息id -->
	         	<span style="display: none"><input type="text" id="baseInfoId" name="baseInfoId"></span>
	         	<!-- 基本信息类型 -->
	         	<span style="display: none"><input type="text" name="baseInfoType" id="baseInfoType"></span>
	         	<!-- 用来判断是弹窗是新增还是编辑 -->
	         	<span style="display: none"><input type="text" id="baseInfoPath"></span>
	         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
	         		<!-- 人员基本信息 -->
	         		<div id="Staff" class="Staff" style="width:100%">
	         			<!-- <table id="Staff1" class="StaffInfo">
	         				<tbody>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">姓名:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoName"  class="easyui-textbox">
	         						</td>
	         						
	         						
	         						
	         						
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">性别:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoSex" panelMaxHeight="70px">
	         								<option value="">请选择</option>
		         							<option value="1">男</option>
											<option value="0">女</option>
										</select>
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">身份证号/护照号:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name=baseInfoIdNumber  class="easyui-textbox">
	         						</td>
         							<td id="baseInfoBirthDateTd1" width="120" style="text-align:right;">
         								<label class="in_label">出生日期:</label>
	         						</td>
	         						<td id="baseInfoBirthDateTd2" width="180">
	         						  <input id="bs"  type="text" name="baseInfoBirthDate"  class="easyui-datebox EdiInputText">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">籍贯:</label>
	         						</td>
	         						<td width="180">
	         							  <input id="bs" type="text" name="baseInfoNativePlace"  class="easyui-textbox">
	         						</td>
	         						
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">民族:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoNation"  class="easyui-textbox">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">政治面貌:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoPoliticalOutlook" panelMaxHeight="100px">
	         								<option value="">请选择</option>
		         							<option value="中共党员">中共党员</option>
											<option value="民盟">民盟</option>
											<option value="民建">民建</option>
											<option value="九三">九三</option>
											<option value="致公">致公</option>
											<option value="农公">农公</option>
											<option value="民进">民进</option>
											<option value="台盟">台盟</option>
											<option value="民革">民革</option>
											<option value="团员">团员</option>
											<option value="非党群众">非党群众</option>
											<option value="无党派人士">无党派人士</option>
										</select>
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">学历:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoEducation" panelMaxHeight="90px">
	         								<option value="">请选择</option>
		         							<option value="0">博士</option>
											<option value="1">硕士</option>
											<option value="2">本科</option>
										</select>
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">学位:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoDegree" panelMaxHeight="90px">
	         								<option value="">请选择</option>
		         							<option value="0">博士</option>
											<option value="1">硕士</option>
											<option value="2">学士</option>
										</select>
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">专业:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoSpecialty"  class="easyui-textbox">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">职称:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoPositionalTitles" panelMaxHeight="100px">
	         								<option value="">请选择</option>
		         							<option value="0">研究员</option>
											<option value="1">副研究员</option>
											<option value="2">助理研究员</option>
											<option value="3">教授</option>
											<option value="4">副教授</option>
											<option value="5">讲师</option>
											<option value="6">助教</option>
										</select>
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">级别:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoLevel" panelMaxHeight="90px">
	         								<option value="">请选择</option>
		         							<option value="0">高级</option>
											<option value="1">副高</option>
											<option value="2">中级</option>
											<option value="3">初级</option>
										</select>
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">职务:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoPost"  class="easyui-textbox">
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">是否博导:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoIfDoctorTutor" panelMaxHeight="70px">
	         								<option value="">请选择</option>
		         							<option value="0">否</option>
											<option value="1">是</option>
											</select>
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">是否硕导:</label>
	         						</td>
	         						<td width="180">
	         							<select class="easyui-combobox" name="baseInfoIfMasterTutor" panelMaxHeight="70px">
	         								<option value="">请选择</option>
		         							<option value="0">否</option>
											<option value="1">是</option>
										</select>
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">参加工作时间:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoStartWorkTime"  class="easyui-datebox">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">工行卡号(校园卡):</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoCampusCard"  class="easyui-textbox">
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">大连银行卡号(工资卡):</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" name="baseInfoPayrollCard"  class="easyui-textbox">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">研究方向:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" data-options="multiline:true" name="baseInfoResearchDirection"  class="easyui-textbox" style="width:180px;height: 50px">
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">研究专长:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" data-options="multiline:true" name="baseInfoResearchExpertise"  class="easyui-textbox" style="width:180px;height: 50px">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">人才计划:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" data-options="multiline:true" name="baseInfoTalentPlan"  class="easyui-textbox" style="width:180px;height: 50px">
	         						</td>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">荣誉称号:</label>
	         						</td>
	         						<td width="180">
	         							  <input type="text" data-options="multiline:true" name="baseInfoHonoraryTitle"  class="easyui-textbox" style="width:180px;height: 50px">
	         						</td>
	         					</tr>
	         					<tr>
	         						<td width="120" style="text-align:right;">
	         							<label class="in_label">备注:</label>
	         						</td>
	         						<td width="180" colspan="3">
	         							  <input type="text" data-options="multiline:true" name="baseInfoRemarks"  class="easyui-textbox" style="width:488px;height: 50px">
	         						</td>
	         					</tr>
	         				</tbody>
	         			</table> -->
	         		</div>
	         		<!-- 人员基本信息 -->
	         		<!-- 教育经历-->
	         		<div class="model_list" id="educationalExperience" >
	         			<!-- <div class="headCaption">
	         				<table width="100%">
	         					<tbody>
	         						<tr>
	         							<td>
	         								<h2>教育经历</h2>
	         							</td>
	         							<td width="60px">
	         								<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" data-options="plain:true" onclick="addEducational()">添加</a>
	         							</td>
	         						</tr>
	         					</tbody>
	         				</table>
	         			</div>
	         			<div class="listBody">
	         				<table width="100%" border="0" id="educationalId">
	         					<thead>
	         						<tr>
	         							<th>国家</th>
	         							<th>学校</th>
	         							<th>就读时间</th>
	         							<th>院系</th>
	         							<th>专业</th>
	         							<th>学历</th>
	         							<th>学位</th>
	         							<th>操作</th>
	         						</tr>
	         					</thead>
	         				</table>
	         			</div> -->
	         		</div>
	         		<!-- 教育经历 -->
	         		<!-- 工作经历 -->
	         		<div class="model_list" id="workExperience" >
	         			<!-- <div class="headCaption">
	         				<table width="100%">
	         					<tbody>
	         						<tr>
	         							<td>
	         								<h2>工作经历</h2>
	         							</td>
	         							<td width="60px">
	         								<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" data-options="plain:true">添加</a>
	         							</td>
	         						</tr>
	         					</tbody>
	         				</table>
	         			</div>
	         			<div class="listBody">
	         				<table width="100%" border="0">
	         					<thead>
	         						<tr>
	         							<th>时间</th>
	         							<th>工作单位</th>
	         							<th>职务</th>
	         							<th>操作</th>
	         						</tr>
	         					</thead>
	         				</table>
	         			</div> -->
	         		</div>
	         		<!-- 工作经历 -->
	         		<!-- 社会兼职 -->
	         		<div class="model_list" id="socialAppointments" >
	         			<!-- <div class="headCaption">
	         				<table width="100%">
	         					<tbody>
	         						<tr>
	         							<td>
	         								<h2>社会兼职</h2>
	         							</td>
	         							<td width="60px">
	         								<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" data-options="plain:true">添加</a>
	         							</td>
	         						</tr>
	         					</tbody>
	         				</table>
	         			</div>
	         			<div class="listBody">
	         				<table width="100%" border="0">
	         					<thead>
	         						<tr>
	         							<th>兼职类型</th>
	         							<th>机构类别</th>
	         							<th>兼职机构</th>
	         							<th>职务名称</th>
	         							<th>操作</th>
	         						</tr>
	         					</thead>
	         				</table>
	         			</div> -->
	         		</div>
	         		<!-- 社会兼职 -->
	         		<!-- 出国经历 -->
	         		<div class="model_list" id="abroadExperience" >
	         			<!-- <div class="headCaption">
	         				<table width="100%">
	         					<tbody>
	         						<tr>
	         							<td>
	         								<h2>出国经历</h2>
	         							</td>
	         							<td width="60px">
	         								<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" data-options="plain:true">添加</a>
	         							</td>
	         						</tr>
	         					</tbody>
	         				</table>
	         			</div>
	         			<div class="listBody">
	         				<table width="100%" border="0">
	         					<thead>
	         						<tr>
	         							<th>国家</th>
	         							<th>时间</th>
	         							<th>机构</th>
	         							<th>目的</th>
	         							<th>操作</th>
	         						</tr>
	         					</thead>
	         				</table>
	         			</div> -->
	         		</div>
	         		<!-- 出国经历-->
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
    <!-- 新增、编辑基本信息 -->
    <!-- 删除提示框 -->
    <div id="dlgClose" class="easyui-dialog"  data-options="closed:true" >
     	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 删除提示框 -->
</body>
</html>