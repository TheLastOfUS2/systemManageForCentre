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
<!-- 分页 -->
<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
<!-- 本页js -->
<script type="text/javascript" src="resources/js/common/toQuery.js"></script> 
<!-- 选择作者-->
<script type="text/javascript" src="resources/js/searchName.js"></script>
<script type="text/javascript" src="resources/js/pageHandler.js"></script>

</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
	<div style="margin: 5px;"> 
		<div class="panel" style="display: block; width: auto;">
			<div class="panel-header" style="width: auto;">
				<div class="panel-title panel-with-icon" data-options="iconCls: 'icon-search'">查询条件</div>
				<div class="panel-icon icon-search"></div>
				<div class="panel-tool"></div>
			</div>
			<div id="search-layer" class="easyui-panel panel-body" style="width: auto; height: auto;">
				<form id="fm" method="post">
					<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
						<div id="Staff" class="Staff" style="width:100%">
							<table id='Staff1' class='StaffInfo'>
			         			<tbody>
			         				<tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>责任人姓名:</label>
										</td>
							         	<td width='180' colspan='5'>
							         		<input type='text' id='baseInfoName' name='baseInfoName' class='easyui-textbox' style='width:447px'>
							         		<input type='hidden' id='baseInfoNameId' name='baseInfoNameId' style='width:447px'>
							         	 </td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>责任人类别:</label>
										</td>
							         	<td width='180'>
							         		<input type='checkbox' name='baseInfoType' value="0">科研人员
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='baseInfoType' value="1">行政人员
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='baseInfoType' value="2">博士后
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='baseInfoType' value="3">博士
							         	</td>
							        </tr>
							        <tr>
							        	<td width='120' style='text-align:right;'></td>
							        	<td width='180'>
							        		<input type='checkbox' name='baseInfoType' value="4">博士
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='baseInfoType' value="5">其他人员
							         	</td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>责任人职称:</label>
										</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="0">研究员
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="1">副研究员
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="2">助理研究员
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="3">教授
							         	</td>
							        </tr>
							        <tr>
										<td width='120' style='text-align:right;'></td>
										<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="4">副教授
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="5">讲师
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='positionalTitleType' value="6">助教
							         	</td>
									</tr>
									<tr style="height: 5px"></tr>
									<tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>责任人级别:</label>
										</td>
							         	<td width='180'>
							         		<input type='checkbox' name='levelType' value="0">高级
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='levelType' value="1">副高
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='levelType' value="2">中级
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='levelType' value="3">初级
							         	</td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>责任人年龄区间:</label>
										</td>
							         	<td width='180'>
											<input id="baseInfoStartAge" class='easyui-numberspinner' name='baseInfoStartAge' data-options='min:18,max:120' style='width:55px;'></input>
											~
											<input id="baseInfoEndAge" class='easyui-numberspinner' name='baseInfoEndAge' data-options='min:18,max:120' style='width:55px;'></input>
										</td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>成果时间区间:</label>
										</td>
							         	<td width='180' colspan='5'>
											<input type='text' id='projectStartTime' name='projectStartTime' class='easyui-datebox'>
											至
											<input type='text' id='projectEndTime' name='projectEndTime' class='easyui-datebox'>
										</td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>成果/项目名称:</label>
										</td>
							         	<td width='180' colspan='5'>
							         		<input type='text' id='projectName' name='projectName' class='easyui-textbox' style='width:447px'>
							         	 </td>
							        </tr>
							        <tr style="height: 5px"></tr>
							        <tr>
										<td width='120' style='text-align:right;'>
											<label class='in_label'>数据查找范围:</label>
										</td>
							         	<td width='180'>
							         		<input type='checkbox' name='fanweiType' value="项目">项目
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='fanweiType' value="著作">著作
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='fanweiType' value="论文">论文
							         	</td>
							        </tr>
							        <tr>
										<td width='120' style='text-align:right;'></td>
										 	<td width='180'>
							         		<input type='checkbox' name='fanweiType' value="获奖">获奖
							         	</td>
							         	<td width='180'>
							         		<input type='checkbox' name='fanweiType' value="采纳批示">采纳批示
							         	</td>
									</tr>
									<tr>
									 	<td width='120' style='text-align:right;'></td>
									 	<td width='180' colspan='3'>
									 		<hr>
									 	</td>
									 </tr>
									 <tr>
									 	<td width='120' style='text-align:right;'></td>
									 	<td width='180' colspan='3'>
									 		<div class="BtnDiv">
									 			<a href="javascript:void(0);" onclick="query()"><span>搜索</span></a>
									 		</div>
									 		<div style="padding-top: 5px">
									 			<a href='javascript:void(0)' class='easyui-linkbutton' style="height: 23px;width: 55px;" data-options='plain:true' onclick='clearQuery()'>重置</a>
									 		</div>
									 	</td>
									 </tr>
			         			</tbody>
			         		</table>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- 人员列表提示框 -->
	<div id="plg" class="easyui-dialog" title="请选择人员"  data-options="closed:true" >
		 <div style="margin: 5px;">
			<!-- 判断是从作者进入选择人员列表,还是从合作者进入选择人员列表 -->
		 	<input type="hidden" id="typeNum"/>
		 	<!-- 合作者:选择人员的索引 -->
		 	<input type="hidden" id="controllerNum"/>
		 	<div style="padding-bottom: 13px">已选择:</div>
		 	<div class="baseInfoNameDiv1">
		 		<span class="cata-item selected" id="type0" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(0,"baseInfoName","")'>科研人员</span>
		 		<span class="cata-item " id="type1" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(1,"baseInfoName","")'>行政人员</span>
		 		<span class="cata-item " id="type2" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(2,"baseInfoName","")'>博士后</span>
		 		<span class="cata-item " id="type3" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(3,"baseInfoName","")'>博士</span>
		 		<span class="cata-item " id="type4" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(4,"baseInfoName","")'>硕士</span>
		 		<span class="cata-item " id="type5" style="width:56px;height: 23px;padding: 10px;font-size: 14px" onclick='searchByType(5,"baseInfoName","")'>其他人员</span>
		 	 </div>
		 	<div class="baseInfoNameDiv2">
		 		<table id="personId"  width='100%'></table>
		 	</div>
		 </div>
	</div>
	<!-- 人员列表提示框 -->
	
	<div id="dlgQuery" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" >
	    <div id="tabsQuery" class="easyui-tabs" data-options="fit:true,border:false">
	    
	    </div>    
	</div>
</body>
</html>