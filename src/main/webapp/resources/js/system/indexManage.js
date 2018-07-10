	var urls = "";
	$(document).ready(function() { 
		// 闭浏览器时 避免弹出提示框
		$(window).unbind('beforeunload');
		window.onbeforeunload = null;
		
		// 登录后跳转到该页面,根据角色判断显示页面功能及显示样式
		// ; 科研人员和其他人员看明细
		// 默认选中第一项菜单 根据权限判断
		// 获取隐藏域中用户的权限菜单数据
		$(".accordion-header-selected").next().find("a:first").click();
		
		//判断是否显示数据查询（0.管理员1.领导2.行政能看到；3.科研人员.4其他人员 看不到）
		var role=$("#role").val();
		if(role==3 || role==4){
			$("#user-search").hide();
		}
		
		// 领导角色区分
		var optext=""
		var accountRoleType=$("#accountRoleType").val();
		var role =$("#role").val();
		// 根据角色判断是否显示可切换功能
		if(role=="1" || (role=="3" && accountRoleType=="1")){
			$("#roleChange").show();
		}else{
			$("#roleChange").hide();
		}
		
		if(accountRoleType==0){
			optext="领导";
		}else{
			optext="科研人员";
		}
		var select = document.getElementById("roleChange");  
		for(var i=0; i<select.options.length; i++){  
		    if(select.options[i].innerHTML == optext){  
		        select.options[i].selected = true;  
		        break;  
		    }  
		} 
	});
	
	/**
	 * 修改密码弹出框居中显示
	**/
	function dlgCenter(){
		//修改父级元素样式
		$("#editPassdlg").parent().css({
			"top":"50%",
			"left":"50%",
			"width":"630px",
			"height":"240px",
			"margin-top":"-120px",
			"margin-left":"-315px",
			"position":"absolute"
		});
		//修改父级元素的下一个兄弟元素样式
		$("#editPassdlg").parent().next().css({
			"top":"50%",
			"left":"50%",
			"width":"630px",
			"height":"240px",
			"margin-top":"-120px",
			"margin-left":"-315px",
			"position":"absolute"
		});
		//修改本身元素样式
		$("#editPassdlg").css({
			"top":"50%",
			"left":"50%",
			"width":"628px",
			"height":"187px"
		});
		//修改上一个兄弟元素样式
		$("#editPassdlg").prev().css({
			"width":"630px"
		});
		//修改下一个兄弟元素素样式
		$("#editPassdlg").next().css({
			"width":"618px"
		});
	}
	
	// 修改密码
	function editPassword(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		$("#editPassdlg").dialog({  
    		title: "修改密码",
			collapsible: true,
            resizable: true,
          /*  width: 630,
			height:240, */
			modal:true,//遮罩层
			/*top: 367,
			left: 645,*/
			onClose: function(){
				win.window('destroy');//关闭即销毁
			}
    	});
		$('#fm').form('clear');
    	$("#editPassdlg").dialog("open");
    	dlgCenter();
    	// 获取登录账号id
    	var accountId=$("#accountId").val();
    	url = "account/updatePassWord?accountId="+accountId + urls;
	}
	
	//保存基本信息
    function savePassWord(){
    	// 新密码
    	//判断两次输入密码是否一致
    	var accountPassword=   $("#accountPassword").val();
    	var accountPasswordq=   $("#accountPasswordq").val();
    	if(accountPassword!="" && accountPasswordq!="" && accountPassword!=accountPasswordq){
    		$("#accountPasswordq2span").text("两次输入密码不一致");
    		return;
    	}else{
    		// 判断密码的长度
    		if(accountPassword.length<6 || accountPassword.length>18){
    			$("#accountPassword2span").text("密码应为6~18位");
    			return;
    		}else{
    			// 原密码
            	var oldPassWord=   $("#oldPassWord").val();
            	if(oldPassWord!=""){
            		 $("#oldPassWord2").textbox("setValue", $.md5(oldPassWord));
            	}
        		var accountPassword=$.md5(accountPassword)
        		$("#accountPassword2").textbox("setValue", accountPassword);
        		$("#accountPasswordq2").textbox("setValue", accountPassword);
        		$("#accountPasswordq2span").text("");
        		$("#accountPassword2span").text("");
    		}
    	}
       $('#fm').form('submit',{
           url: url,
           onSubmit: function(){
               return $(this).form('validate');
           },
           success: function(data){
               var result = eval('('+data+')');
               if (result.status==0){
                   $.messager.show({
                       title: '提示',
                       msg: result.text
                   });
               } else {
            	   $.messager.show({
                       title: '提示',
                       msg: result.text,
                       timeout:1
                   });
                   $('#editPassdlg').dialog('close');        // close the dialog
               }
           }
       });
    }
    
    //领导角色转换
    function roleChange(value){
    	if(value!=""){
    		// 获取操作对应的菜单ID
    		urls = getPowerMenuId();
    		//改变用户角色
    		var accountId=$("#accountId").val();
    		var accountRoleType=0;
    		if(value=="3"){
    			accountRoleType="1";
    		}
    		$.ajax({
				url:'account/roleChange?accountId='+accountId+"&accountRoleType="+accountRoleType+urls,
				type:"post",
				success:function (data){
					if(data!=null){
						if (data.status==0){
			                   $.messager.show({
			                       title: '提示',
			                       msg: data.text
			                   });
		               } else {
		            	   
		            	   $.messager.show({
		                       title: '提示',
		                       msg: data.text,
		                       timeout:1
		                   });
		            	   location.reload();
		            	   //重定向
		            	   //window.location.href="http://localhost:8081/systemManage/";
		               }
					}  //公司执照  找票 扫描件  管理员身份证  andorid 平台
				}
			});
    	}
    	
    }
    
