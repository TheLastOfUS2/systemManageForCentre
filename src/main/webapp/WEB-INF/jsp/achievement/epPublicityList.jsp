<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String publicityType=(String)request.getAttribute("publicityType");
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
<script type="text/javascript" src="resources/js/achievement/epPublicityList.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 导出 cjk编码转换 -->
<script type="text/javascript" src="resources/js/cjkEncode.js"></script>
<!-- 上传-->
<script type="text/javascript" src="resources/js/jquery-form.js"></script>
<!-- 时间戳日期格式化-->
<script type="text/javascript" src="resources/js/formatTime.js"></script>
 <!--选择作者-->
<script type="text/javascript" src="resources/js/searchName.js"></script>
</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
	<!-- scrollbarSize:0:去掉空白滚动条 -->
	<!-- 基本信息列表 -->
	<table id="dg" 
	   data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0" toolbar="#dlg-toolbar"  style="overflow: hidden">
		  <thead>
		  <!-- 用户角色 -->
			<input type="hidden" id="role" value="${accountInfo.roleName}" />
			<input type="hidden" id="baseInfoId" value="${accountInfo.baseInfoId}" />
			<input type="hidden" id="baseInfoName2" value="${accountInfo.baseInfoName}" />
		  	<input type="hidden" id="publicityType1" value="<%=publicityType%>"/>
		  	<!-- 信息删除状态(当刚进列表时:baseInfoDel=1,点击"删除"按钮,改变数据库状态;当点击回收站后:baseInfoDel=2,点击"删除"按钮,彻底删除数据) -->
		 	<input type="hidden" id="publicityDel"/>
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
            			<!-- 报纸 -->
						<div data-options="name:'publicityTheme'">宣传主题</div>
						<div id="tId1" data-options="name:'publicityTitle'">报纸名称</div>
						<div id="iId1" data-options="name:'publicityInterviewee'">受访人</div>
						<div id="cId1" data-options="name:'publicityCompany'">受访单位</div>
						<div id="pId1" data-options="name:'publicityPosition'">版面</div>
						<div id="tiId1" data-options="name:'publicityTime'">发表时间</div>
						<!-- 期刊 -->
						<div id="tId2" data-options="name:'publicityTitle'">期刊名称</div>
						<div id="iId3" data-options="name:'publicityInterviewee'">被宣传人</div>
						<div id="vId" data-options="name:'publicityVolume'">卷/期</div>
						<div id="pId2" data-options="name:'publicityPosition'">位置</div>
						<div id="tiId2" data-options="name:'publicityTime'">发表时间</div>
						<!-- 电视台 -->
						<div id="cId" data-options="name:'publicityChannel'">电视频道名称</div>
						<div id="tId3" data-options="name:'publicityTitle'">栏目名称</div>
						<div id="pId3" data-options="name:'publicityPosition'">视频网址</div>
						<div id="iId2" data-options="name:'publicityInterviewee'">受访人</div>
						<div id="tiId3" data-options="name:'publicityTime'">时间</div>
						
						<!-- 网络 -->
						<div id="tId4" data-options="name:'publicityTitle'">网站名称</div>
						<div id="pId4" data-options="name:'publicityPosition'">宣传页网址</div>
						<div id="iId4" data-options="name:'publicityInterviewee'">被宣传人</div>
						<div id="tiId4" data-options="name:'publicityTime'">发表时间</div>
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
         	<span style="display: none">
         		<input type="text" id="publicityId" name="publicityId">
         		<!-- 存放选择人员的文本框名称 -->
	         	<input type="text" id="name">
         	</span>
         	<span style="display: none"><input type="text" id="publicityType" name="publicityType"></span>
         	<!-- 用来判断是弹窗是新增还是编辑 -->
         	<span style="display: none"><input type="text" id="baseInfoPath"></span>
	         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
	         		<!-- 通讯录信息 -->
	         		<div id="Staff" class="Staff" style="width:100%">
	         			<!-- 报纸 -->
	         			<table id="Staff1" class="StaffInfo" style="display:none">
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>宣传主题:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTheme' name='publicityTheme' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>报纸名称:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTitle' name='publicityTitle' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>受访人:</label></td>
									<td width='180' colspan='3'>
										<input type='text' id='publicityInterviewee' name='publicityInterviewee' class='easyui-textbox' style='width:410px'>
										<a id="a_publicityInterviewee" href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='searchByType(0,"publicityInterviewee","")'>选择</a>
										<input type='hidden' id='publicityIntervieweeId' name='publicityIntervieweeId'/>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>受访单位:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityCompany' name='publicityCompany' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>发表时间:</label></td>
									<td width='180'>
									<input type='text' id='publicityTime' name='publicityTime' class='easyui-datebox'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>版面:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityPosition' name='publicityPosition' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='publicityRemarks' name='publicityRemarks'
						         	class='easyui-textbox' style='width:448px;height: 50px'></td>
						         </tr>
	         				</tbody>
	         			</table>
	         			<!-- 期刊 -->
	         			<table id="Staff2" class="StaffInfo" style="display:none">
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>宣传主题:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTheme' name='publicityTheme' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>期刊名称:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTitle' name='publicityTitle' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>被宣传人:</label></td>
									<td width='180' colspan='3'>
										<input type='text' id='publicityInterviewee' name='publicityInterviewee' class='easyui-textbox' style='width:410px'>
										<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='searchByType(0,"publicityInterviewee","")'>选择</a>
										<input type='hidden' id='publicityIntervieweeId' name='publicityIntervieweeId'/>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>发表时间:</label></td>
									<td width='180'>
									<input type='text' id='publicityTime' name='publicityTime' class='easyui-datebox'></td>
									<td width='120' style='text-align:right;'><label class='in_label'>卷/期:</label></td>
									<td width='180'>
									<input type='text' id='publicityVolume' name='publicityVolume' class='easyui-textbox'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>位置:</label></td>
									<td width='180' colspan='3'>
									<select id='publicityPosition' class='easyui-combobox' name='publicityPosition' panelMaxHeight='100px'>
						         	<option value=''>请选择</option><option value='封1'>封1</option><option value='封2'>封2</option>
						         	<option value='封3'>封3</option><option value='封4'>封4</option>
						         	<option value='书评'>书评</option><option value='其他'>其他</option></select></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='publicityRemarks' name='publicityRemarks'
						         	class='easyui-textbox' style='width:448px;height: 50px'></td>
						         </tr>
	         				</tbody>
	         			</table>
	         			<!-- 电视台 -->
	         			<table id="Staff3" class="StaffInfo" style="display:none">
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>宣传主题:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTheme' name='publicityTheme' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>电视频道名称:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityChannel' name='publicityChannel' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>栏目名称:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTitle' name='publicityTitle' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>受访人:</label></td>
									<td width='180'  colspan='3'>
										<input type='text' id='publicityInterviewee' name='publicityInterviewee' class='easyui-textbox' style='width:410px'>
										<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='searchByType(0,"publicityInterviewee","")'>选择</a>
										<input type='hidden' id='publicityIntervieweeId' name='publicityIntervieweeId'/>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>时间:</label></td>
									<td width='180'>
									<input type='text' id='publicityTime' name='publicityTime' class='easyui-datebox'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>视频网址:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityPosition' name='publicityPosition' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='publicityRemarks' name='publicityRemarks'
						         	class='easyui-textbox' style='width:448px;height: 50px'></td>
						         </tr>
	         				</tbody>
	         			</table>
	         			<!-- 网络 -->
	         			<table id="Staff4" class="StaffInfo" style="display:none">
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>宣传主题:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTheme' name='publicityTheme' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>网站名称:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityTitle' name='publicityTitle' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>被宣传人:</label></td>
									<td width='180'  colspan='3'>
										<input type='text' id='publicityInterviewee' name='publicityInterviewee' class='easyui-textbox' style='width:410px'>
										<a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' onclick='searchByType(0,"publicityInterviewee","")'>选择</a>
										<input type='hidden' id='publicityIntervieweeId' name='publicityIntervieweeId'/>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>发表时间:</label></td>
									<td width='180'>
									<input type='text' id='publicityTime' name='publicityTime' class='easyui-datebox'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>宣传页网址:</label></td>
									<td width='180' colspan='3'>
									<input type='text' id='publicityPosition' name='publicityPosition' class='easyui-textbox' style='width:448px'></td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>
						         	<td width='180' colspan='3'>
						         	<input type='text' data-options='multiline:true' id='publicityRemarks' name='publicityRemarks'
						         	class='easyui-textbox' style='width:448px;height: 50px'></td>
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
    
    <!-- 人员列表提示框 -->
	<div id="plg" class="easyui-dialog" title="请选择人员"  data-options="closed:true" >
		 <div style="margin: 5px;">
		 	<!-- 判断是从作者进入选择人员列表,还是从合作者进入选择人员列表 -->
		 	<input type="hidden" id="typeNum"/>
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
									<td width="30%"><span style="width: 5px;color:red">*</span>文件名称</td>
									<td>
										<input type="text" id="fileFullName" name="fileFullName" required="true" class='easyui-textbox' style="padding: 3px;border: 1px solid #BDC7D8;"/>
									</td>
								</tr>
								<tr>
									<td width="20%">文件类别</td>
									<td id="typePublicity0" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">报纸文章页</option>
											<option value="1">其他</option>
										</select>
									</td>
									<td id="typePublicity1" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">期刊首页及刊登文章页</option>
											<option value="1">其他</option>
										</select>
									</td>
									<td id="typePublicity2" style="display: none">
										<select id="fileType" name="fileType" style="padding: 3px;border: 1px solid #BDC7D8;">
										    <option value="">请选择文件类别</option>
											<option value="0">其他</option>
										</select>
									</td>
									<td id="typePublicity3" style="display: none">
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
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="delUploadFile()" style="width:90px">取消</a>
    </div>
    <!-- 底部按钮 -->
	<!-- 选择文件后弹框 -->
</body>
</html>