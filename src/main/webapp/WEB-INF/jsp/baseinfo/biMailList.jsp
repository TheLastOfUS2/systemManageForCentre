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
<script type="text/javascript" src="resources/js/baseinfo/biMailList.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 导出 cjk编码转换 -->
<script type="text/javascript" src="resources/js/cjkEncode.js"></script>
<!-- 选择作者 和 弹框居中样式-->
<script type="text/javascript" src="resources/js/searchName.js"></script>
</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<input type="hidden" id="baseInfoNameS" value="${accountInfo.baseInfoName}" />
	<input type="hidden" id="baseInfoIdS" value="${accountInfo.baseInfoId}" />
	<input type="hidden" id="role" value="${accountInfo.roleName}" />
	<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
	<!-- scrollbarSize:0:去掉空白滚动条 -->
	<!-- 基本信息列表 -->
	<table id="dg"
	   data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0" toolbar="#dlg-toolbar" style="overflow: hidden" >
		  <thead>
		  <!-- 信息类型 -->
		   <input type="hidden" id="baseInfoType1" value="<%=baseInfoType%>"/>
		   <!-- 信息删除状态(当刚进列表时:baseInfoDel=1,点击"删除"按钮,改变数据库状态;当点击回收站后:baseInfoDel=2,点击"删除"按钮,彻底删除数据) -->
		   <input type="hidden" id="mailListDel"/>
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
						<div data-options="name:'baseInfoName'">姓名</div>
						<div data-options="name:'mailListPhone'">手机</div>
						<div id="pId" data-options="name:'mailListPhone1'">小号</div>
						<div id="oId" data-options="name:'mailListOph'">办公电话</div>
						<div id="sId" data-options="name:'mailListStudio'">工作室号</div>
						<div id="eId" data-options="name:'mailListEmail'">电子邮箱</div>
						<div id="aId" data-options="name:'mailListAddress'">通讯地址</div>
						
						
						<div id="cId" data-options="name:'mailListCompany'">单位</div>
						<div id="ptId" data-options="name:'baseInfoPositionalTitles'">职称</div>
						<div id="poId" data-options="name:'baseInfoPost'">职务</div>
						<div id="stId" data-options="name:'baseInfoStartTime'">入站时间</div>
						<div id="etId" data-options="name:'baseInfoEndTime'">出站时间</div>
						<div id="satId" data-options="name:'baseInfoStartTime'">入学时间</div>
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
         	<span style="display: none"><input type="text" id="mailListId" name="mailListId"></span>
         	<!-- 基本信息类型 -->
         	<span style="display: none"><input type="text" name="baseInfoType" id="baseInfoType"></span>
         	<!-- 选择人员的id -->
         	<span style="display: none"><input type="text" id="baseInfoId" name="baseInfoId"></span>
         	<!-- 用来判断是弹窗是新增还是编辑 -->
         	<span style="display: none"><input type="text" id="baseInfoPath"></span>
	         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
	         		<!-- 通讯录信息 -->
	         		<div id="Staff" class="Staff" style="width:100%">
	         			<table id="Staff1" class="StaffInfo">
	         				<tbody>
		         				<tr>
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
	         					<tr id="tr3">
									<td width='120' style='text-align:right;'>
									<label class='in_label'>小号:</label></td>
									<td width='180'> 
										<input type='text' name='mailListPhone1' class='easyui-textbox'>
									</td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>办公电话:</label>
									</td>
									<td width='180'> 
										<input type='text' name='mailListOph' class='easyui-textbox'>
									</td>
								</tr>
								<tr id="tr4">
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
								<tr id="tr5">
									<td width='120' style='text-align:right;'>
										<label class='in_label'>单位:</label>
									</td>
									<td width='180' colspan='3'>
										<input type='text' id='mailListCompany' name='mailListCompany' class='easyui-textbox' style="width: 448px">
									</td>
								</tr>
								<tr id="tr6">
									<td width='120' style='text-align:right;'>
										<label class='in_label'>入站时间:</label>
									</td>
									<td width='180'> 
										<input type='text' id='baseInfoStartTime' name='baseInfoStartTime' class='easyui-datebox'>
									</td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>出站时间:</label>
									</td>
									<td width='180'> 
										<input type='text' id='baseInfoEndTime' name='baseInfoEndTime' class='easyui-datebox'>
									</td>
								</tr>
								<tr>
									<td id="tdstudio1" width='120' style='text-align:right;'>
										<label class='in_label'>工作室号:</label></td>
									<td id="tdstudio2" width='180' id="">
										<input type='text' name='mailListStudio' class='easyui-textbox'>
									</td>
									<td id="tdstartTime1" width='120' style='text-align:right;'>
										<label class='in_label'>入学时间:</label>
									</td>
									<td id="tdstartTime2" width='180'> 
										<input type='text' id='baseInfoStartTime' name='baseInfoStartTime' class='easyui-datebox'>
									</td>
									<td id="tdoph1" width='120' style='text-align:right;'>
										<label class='in_label'>办公电话:</label>
									</td>
									<td id="tdoph2" width='180'> 
										<input type='text' name='mailListOph' class='easyui-textbox'>
									</td>
									<td width='120' style='text-align:right;'>
										<label class='in_label'>电子邮箱:</label>
									</td>
									<td width='180'> 
										<input type='text' name='mailListEmail' class='easyui-textbox'>
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
	         		<!-- 通讯录信息 -->
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
    
    <!-- 导出提示框 -->	
    <div id="dlgExcel" class="easyui-dialog" title="系统提示" data-options="iconCls:'icon-save',closed:true" style="width:260px;height:130px;padding:20px;text-align: center">
		文件生成成功,
		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' style="width:70px;height:30px;" onclick="exportExcel()">点此下载</a>
	</div>
    <!-- 导出提示框 -->
    
    <!-- 人员列表提示框 -->
	<div id="plg" class="easyui-dialog" title="请选择人员"  data-options="closed:true" >
		 <div style="margin: 5px;">
		 	<div style="padding-bottom: 13px">已选择:</div>
		 	<div class="baseInfoNameDiv1">
		 		<span id="type0" style="display:none;">
		 			<span class="cata-item selected"  style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(0)'>科研人员</span>
		 		</span>
		 		<span id="type1" style="display:none;">
		 			<span class="cata-item selected" id="type1" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(1)'>行政人员</span>
		 		</span>
		 		<span id="type2" style="display:none;">
		 			<span class="cata-item selected" id="type2" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(2)'>博士后</span>
		 		</span>
		 		<span id="type3" style="display:none;">
		 			<span class="cata-item selected" id="type3" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(3)'>博士</span>
		 		</span>
		 		<span id="type4" style="display:none;">
		 			<span class="cata-item selected" id="type4" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(4)'>硕士</span>
		 		</span>
		 		<span id="type5" style="display:none;">
		 			<span class="cata-item selected" id="type5" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(5)'>其他人员</span>
		 		</span>
		 		</div>
		 	<div class="baseInfoNameDiv2">
		 		<table id="personId"  width='100%'></table>
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
	</div>
 	<!-- datagrid绑定右键菜单 -->
</body>
</html>



<!-- 
demo
<div id="dl1" class="easyui-dialog" title="窗口"
style="width:400px;height:200px;"data-options="
toolbar:[{text:'添加',iconCls:'icon-add',handler:function(){alert('点击了添加按钮')}},
{text:'保存',iconCls:'icon-save'}],
buttons:[{text:'确定'},

{text:'取消',handler:function(){$('#dl1').dialog('close')}}]
">
这是对话窗口
</div> -->