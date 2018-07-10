<!-- 用这个根标签，可以去掉tabs最外侧的滚动条 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<head>
	<title>东北财经大学 产业组织与企业组织研究中心</title>
	
	<jsp:include page="/WEB-INF/jsp/common/_include.jsp" /> 
	<!--indexManage.html页面样式-->
	<link rel="stylesheet" type="text/css" href="resources/css/indexManage.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/baseinfo/baseInfoList.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/windowDialog.css"/>
	
	<script type="text/javascript" src="resources/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="resources/App_Addins/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resources/js/UIAction.js?_=57"></script>
	<script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="resources/js/pageHandler.js"></script>
	<script type="text/javascript" src="resources/js/baseinfo/baseInfoList.js"></script>
	<script type="text/javascript" src="resources/js/system/indexManage.js"></script>
	<!-- 密码加密-->
	<script type="text/javascript" src="resources/js/md5.js"></script>
	
	<style type="text/css">
		/* 给顶部功能按钮添加选中样式 */
		#TopNavBar li.liid{
			background-image: url("resources/App_Addins/easyUI/themes/default/images/tabs_active.png") !important;
		    background-repeat: repeat-x;
		    font-weight: bold !important;
		    font-family: "微软雅黑";
		    padding-top: 6px !important;
		}
	</style>
</head>
<body id="body" class="easyui-layout" style="overflow:hidden; margin:0px; padding:0px;height: 100%;border: none">

	<!-- 用户权限 -->
	<input type="hidden" id="power" value="${accountInfo.rolePower}" />
	<!-- 用户角色 -->
	<input type="hidden" id="role" value="${accountInfo.roleName}" />
	<!-- 用户基本信息id-->
	<input type="hidden" id="accountId" value="${accountInfo.accountId}" />
	<!-- 领导角色区分-->
	<input type="hidden" id="accountRoleType" value="${accountInfo.accountRoleType}" />
	<!--顶部—及功能导航、工具栏-->
	<div id="topFunction" data-options="region:'north',split:true,disabled:true" style="height:127px;padding:0px 0px 20px 0px; border:none; background-image:url(resources/images/banner_back.jpg) ; background-position:left top;background-repeat:repeat-x; color:#fff; overflow:hidden">
	    <div style="height:89px; padding:0px 0px 0px 0px">
	       	<!-- 顶部图片 -->
	        <div style="width:460px; float:left; padding:0px 0px 0px 6px"><img src="resources/images/cmips-header_new.png" /></div>
	        <div style="width:160px; float:right; padding:0px 0px 0px 0px"><img src="resources/images/banner_right_new.png" /></div><!-- width="100px" -->
	        <div style="clear:both"></div>
	        <!-- 顶部功能栏 -->
	        <div class="panel-header" style="width:100%; height:30px; color:#000; font-size:16px; padding:0px 0px 0px; color:#15428b; overflow:hidden">
	        	<!-- 顶部功能 -->
	        	<ul class="ULmenu" id="TopNavBar">
	           		<li id="a" class="liid"><a name="basic" href="javascript:;" title="基本信息" style="text-decoration: none;color: #15428b;">基本信息</a></li>
					<li id="b"><a name="point1" href="javascript:;" title="成果申报" style="text-decoration: none;color: #15428b;">成果管理</a></li>
					<li id="c"><a name="point2" href="javascript:;" title="管理工作" style="text-decoration: none;color: #15428b;">管理工作</a></li>
					<li id="d"><a name="point3" href="javascript:;" title="中心各项活动" style="text-decoration: none;color: #15428b;">中心各项活动</a></li>
					<li id="e"><a name="point4" href="javascript:;" title="共享文件" style="text-decoration: none;color: #15428b;">共享文件</a></li>
					<li id="f"><a name="point5" href="javascript:;" title="权限管理" style="text-decoration: none;color: #15428b;">权限管理</a></li>
	           </ul>
	           <!-- 右侧功能 -->
	           <div class="headRight">
	           		<!-- 角色转换==当领导登录时,默认角色是领导;领导可以切换到科研人员 -->
	           		<select id="roleChange" name="role" style="padding: 1px;border: 1px solid #BDC7D8;top: 2px;position: relative;background-color: #e4edfe;" onchange="roleChange(this.value)">
					    <option value="1" id="op1">领导</option>
						<option value="3" id="op2">科研人员</option>
					</select>
	                <a id="user-search" class="easyui-linkbutton GeneralSearch" plain="true" iconCls="icon-search" data-inspect="0" onclick="InitLeftMenu1('common/toQuery?')">数据查询</a>
		            <!--  <a id="changpass" class="easyui-linkbutton" plain="true" iconCls="icon-sys">修改密码</a> -->
		            <a id="changpass" class="easyui-linkbutton" plain="true" iconCls="icon-sys" onclick="editPassword()"> 修改密码  </a>
		            <a id="quitlog" class="easyui-linkbutton" plain="true" iconCls="icon-quit" onclick="$('#dlg').dialog('open')">退出登录</a>
		       </div>
	        </div>
	    </div>
	</div>
	<!--顶部—及功能导航、工具栏-->
	
	<!--左侧导航-->
	<div data-options="region:'west',split:true,disabled:true" iconCls="icon-sys" title="导航"  style="width:223px;padding:0px;overflow:hidden;" id="SubNavRegion" >
	    <div class="easyui-accordion" fit="true" border="false" id="wnav">
	    </div> 
	</div>
	<!--左侧导航-->
	
	<!--中部主窗体-->
	<div data-options="region:'center',iconCls:'icon-home',disabled:true"  title="主操作区"  style="overflow:hidden;">
	    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false"><!-- plain:true:加了个这个,tab的背景颜色没有了 -->
	     <!--   <div id="main-regions"  title="系统首页" style="padding:5px 5px;">
	        </div> -->
	    </div>
	</div>
	<!--中部主窗体-->
	
	<!--底部版权信息split:true:是有分割线，可以拖拽 disabled:true:是禁止拖拽-->
	<div id="d" data-options="region:'south',split:true,disabled:true"  style="height:35px;padding:8px 0px 12px;background:#efefef; text-align:center; overflow:hidden">
	    Copyright &copy; 中心信息管理系统
	</div>
	<!--底部版权信息-->
	
	<!--右侧说明栏-->
	<!-- <div data-options="region:'east',split:true,disabled:true" iconCls="icon-reload"  title="填写说明" split="true" style="width:200px;">
	    <div  style="height:10px;"></div>
	</div> -->
	
	<!-- <div id="global-cache-box" style="display:none;"></div> -->
	<!-- 退出对话框  closed="true"：是进页面时先隐藏对话框，等点击按钮时才显示;;modal="true" :easyui 的 dialog 是继承自 window的，而 window中有modal这样的属性（见参考资料），就是用于打开模态的窗口的，也就是你说的有遮罩层的窗口。所以不需要额外的代码，仅需在dialog中设置这样的属性就可以了。我想这也是兼容性最好的解决方案-->
	<div id="dlg" class="easyui-dialog" closed="true" modal="true" title="退出确认" style="width:300px;height:114px;padding:10px;overflow: hidden">
		<span class="quitPicCss"></span><span>确定退出本系统吗?</span>
		<div style="padding-top: 14px;">
			<span class="quitBtnCss" onclick="location='toLogin'">确认</span>
			<span class="quitBtnCss" onclick="$('#dlg').dialog('close')">取消</span>
			<!--$('#dlg').dialog('open'):显示弹出框；；$('#dlg').dialog('close')：隐藏弹出框  -->
		</div>
	</div>
	
	<div id="editPassdlg" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" >
		 <div style="margin: 13px;">
		 	<div ><h2 style="border-bottom:1px solid #999">修改密码</h2></div>
	        <form id="fm" method="post">
	         	<div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
	         		<div id="Staff" class="Staff" style="width:100%">
	         			<table id='Staff3' class='StaffInfo'>
	         				<tbody>
	         					<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>原密码:</label></td>
									<td width='220'>
										<input type='text' id='oldPassWord' class='easyui-textbox' style="width: 220px">
										<span style="display: none"><input type='text' id='oldPassWord2' name='oldPassWord' class='easyui-textbox' style="width: 220px"></span>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>新密码:</label></td>
									<td width='220'> 
										<input type='text' id='accountPassword' class='easyui-textbox' style="width: 220px">
										<span style="display: none"><input type='text' id='accountPassword2' name='accountPassword' class='easyui-textbox' style="width: 220px"></span>
										<span id="accountPassword2span"></span>
									</td>
								</tr>
								<tr>
									<td width='120' style='text-align:right;'><label class='in_label'>确认新密码:</label></td>
									<td width='350'>
										<input type='text' id='accountPasswordq' class='easyui-textbox' style="width: 220px">
										<span style="display: none"><input type='text' id='accountPasswordq2' name='accountPassword2' class='easyui-textbox' style="width: 220px"></span>
										<span id="accountPasswordq2span"></span>
									</td>
								</tr>
	         				</tbody>
		         		</table>
	         		</div>
	         	</div>
	        </form>
		 </div>
	</div>
	<!-- 底部按钮 -->
    <div id="dlg-buttons">
       	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePassWord()" style="width:90px">确认</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editPassdlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 右键菜单 -->
	<div id="mmTab" class="easyui-menu" style="width:150px;">
         <div id="mm-tabclose">关闭</div>
         <div id="mm-tabcloseall">全部关闭</div>
         <div id="mm-tabcloseother">关闭其他</div>
         <div class="menu-sep"></div>
         <div id="mm-tabcloseright">当前页右侧全部关闭</div>
         <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
         
 </div>
</body>
</html>