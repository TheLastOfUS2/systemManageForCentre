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
<script type="text/javascript" src="resources/js/common/accountList.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 时间戳日期格式化-->
<script type="text/javascript" src="resources/js/formatTime.js"></script>
<!-- 导出 cjk编码转换 -->
<script type="text/javascript" src="resources/js/cjkEncode.js"></script>
<!-- 上传-->
<script type="text/javascript" src="resources/js/jquery-form.js"></script>
<!-- 密码加密-->
<script type="text/javascript" src="resources/js/md5.js"></script>
<!-- 选择作者 和 弹框居中样式-->
<script type="text/javascript" src="resources/js/searchName.js"></script>
</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
	<!-- scrollbarSize:0:去掉空白滚动条 -->
	<!-- 基本信息列表 -->
	<table id="dg" title="账户管理列表"
	   data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0" toolbar="#dlg-toolbar"   style="overflow: hidden">
		  <thead>
		  <input type="hidden" id="accountDel"/>
		  </thead> 
	</table>
	<!-- 基本信息列表 -->
	
	<div id="dlg-toolbar" style="padding:5px;height:auto;display: none">
		<table cellpadding="0" cellspacing="0">  
			<tr>  
				<td>  
                	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>  
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
						<div data-options="name:'accountName'">用户名</div>
						<div data-options="name:'accountPassWord'">用户密码</div>
						<div data-options="name:'roleName'">用户角色</div>
						<div data-options="name:'accountStatus'">使用状态</div>
						<div data-options="name:'baseInfoName'">绑定用户姓名</div>
					</div>
            	</td>
			</tr>
		</table>
	</div>
	
	<!-- 新增、编辑基本信息 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" style='display: none'>
		 <div style="margin: 13px;">
		 	<div ><h2 style="border-bottom:1px solid #999">基本信息</h2></div>
		        <form id="fm" method="post">
			        <!-- 基本信息id -->
		         	<span style="display: none"><input type="text" id="accountId" name="accountId"></span>
		         	<!-- 用来判断是弹窗是新增还是编辑 -->
	         		<span style="display: none"><input type="text" id="accountPath"></span>
		         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
		         		<!-- 著作基本信息 -->
		         		<div id="Staff" class="Staff" style="width:100%">
		         			<table id='Staff1' class='StaffInfo'>
		         				<tbody>
		         					<tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'><span style="width: 5px;color:red">*</span>用户名:</label>
										</td>
										<td width='180' colspan='3'>
											<input type='text' id='accountName' name='accountName' class='easyui-textbox' style='width:447px' required="true">
										</td>
									</tr>
									<tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'><span style="width: 5px;color:red">*</span>登录密码:</label>
										</td>
										<td width='180' colspan='3'>
											<input type='text' id='accountPassWord1' class='easyui-textbox' style='width:447px' required="true">
											<span style="display: none">
												<!-- 加密后密码 -->
												<input type='text' id='accountPassWordMd5' name='accountPassWord' class='easyui-textbox' style='width:447px'>
												<!-- 原密码 -->
												<input type='text' id='accountPassWordOld' class='easyui-textbox' style='width:447px'>
											</span>
										</td>
									</tr>
									<tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'><span style="width: 5px;color:red">*</span>用户角色:</label>
										</td>
										<td width='180' colspan='3'>
											<select id='roleName' class='easyui-combobox' name='roleId' panelMaxHeight='140px' data-options="value:'',editable:true,valueField:'id',textField:'text'"  required="true"></select>
							         	</td>
									</tr>
		         				</tbody>
		         			</table>
		         		</div>
	        	</form>
		 </div>
	</div>
	<!-- 底部按钮 -->
    <div id="dlg-buttons">
       	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveAccount()" style="width:90px">保存</a>
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
    
    <!-- 导出提示框 -->	
    <div id="dlgExcel" class="easyui-dialog" title="系统提示" data-options="iconCls:'icon-save',closed:true" style="width:260px;height:130px;padding:20px;text-align: center">
		文件生成成功,
		<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' style="width:70px;height:30px;" onclick="exportExcel()">点此下载</a>
	</div>
    <!-- 导出提示框 -->
	 <!-- datagrid绑定右键菜单 -->
    <div id="menu" class="easyui-menu" style="width: 30px; display: none;"> 
		<!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
		<div id="btn_Modify" data-options="iconCls:'icon-edit'" onclick="edit()">编辑</div> 
		<!--放置一个隐藏的菜单Div-->
		<div id="btn_Delete" data-options="iconCls:'icon-cancel'" onclick="del()">移入回收站</div> 
	</div>
 	<!-- datagrid绑定右键菜单 -->
 	
 	<!-- 上传材料弹框 -->
    <div id="dlgUpload" class="easyui-dialog" title="上传附件材料" data-options="iconCls:'icon-attach',closed:true" style="width:840px;height:240px;padding:10px;">
		<div style="margin: 13px;">
			<form id="fmUpload" method="post" enctype="multipart/form-data">
				<!-- 上传材料数据id -->
				<input type="text" name="relevanceId" id="relevanceId"/>
				<!-- 上传材料表名 -->
				<input type="hidden" name="tableName" id="tableName"/>
 				<div>
					<h2 style="border-bottom:1px solid #999">
						<span style="display:block;float: left">附件管理</span>
						<span style="display:block;text-align: right;"><!-- iconCls:'icon-attach', -->
							<a href='javascript:void(0)' class='easyui-linkbutton' data-options="plain:true,iconCls:'icon-attach'">下载全部</a>
							<!-- accept:easyui filebox限制文件上传的类型 -->
							<input class="easyui-filebox" name="file" id="uploadFileid" data-options="prompt:'选择文件',buttonText:'添加文件 ',accept:'image/gif,image/jp2,image/jpeg,image/png,application/pdf,audio/mpeg,audio/mp4,video/mp4,application/msword' " style="width:55px;border:1px #ff0000 solid;">
						</span>
					</h2>
				</div>
				<div style="border: 1px dotted #ccc;padding: 5px;margin-bottom: 12px; " data-field="files">
					<div>
						<table class="f-table">
							<thead>
								<tr>
									<th width="30%">文件名称</th>
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
										<input type="text" id="fileFullName" name="fileFullName" style="padding: 3px;border: 1px solid #BDC7D8;"/>
									</td>
								</tr>
								<tr>
									<td width="20%">文件类别</td>
									<td>
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">其他</option>
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
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 底部按钮 -->
	<!-- 选择文件后弹框 -->
</body>
</html>