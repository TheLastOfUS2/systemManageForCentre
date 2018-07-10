	var p=0;
	var urls = "";
	$(document).ready(function() { 
		// 0：是禁用 1.删除禁用
		ifDisabled(0);
		searchBaseinfo();
	});
	
	// 禁用或删除禁用
	function ifDisabled(status){
		if(status==0){
			$('#fmv').form('clear');
			// 禁用所有的textbox
			$("#Staff1").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');
			/*// 禁用性别combobox
			$('#baseInfoSex').combobox('disable');
			// 禁用出生日期datebox
			$('#baseInfoBirthDate').datebox('disable');
			// 禁用参加工作时间datebox
			$('#baseInfoStartWorkTime').datebox('disable');
			// 禁用政治面貌combobox
			$('#baseInfoPoliticalOutlook').datebox('disable');
			// 禁用学历combobox
			$('#baseInfoEducation').datebox('disable');
			// 禁用学位combobox
			$('#baseInfoDegree').datebox('disable');
			// 禁用职称combobox
			$('#baseInfoPositionalTitles').datebox('disable');
			// 禁用级别combobox
			$('#baseInfoLevel').datebox('disable');
			// 禁用是否博导combobox
			$('#baseInfoIfDoctorTutor').datebox('disable');
			// 禁用是否硕导combobox
			$('#baseInfoIfMasterTutor').datebox('disable');
			
			// 禁用所有的textbox
			$("#educationalId").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');*/
			
		}else{
			//显示保存按钮
			$("#dlg-buttons").show();
			// 删除所有textbox的禁用
			$("#Staff1").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			/*// 删除性别combobox的禁用
			$('#baseInfoSex').combobox('enable');
			// 删除出生日期datebox的禁用
			$('#baseInfoBirthDate').datebox('enable');
			// 删除参加工作时间datebox禁用
			$('#baseInfoStartWorkTime').datebox('enable');
			// 删除政治面貌combobox禁用
			$('#baseInfoPoliticalOutlook').datebox('enable');
			// 删除学历combobox禁用
			$('#baseInfoEducation').datebox('enable');
			// 删除学位combobox禁用
			$('#baseInfoDegree').datebox('enable');
			// 删除职称combobox禁用
			$('#baseInfoPositionalTitles').datebox('enable');
			// 删除级别combobox禁用
			$('#baseInfoLevel').datebox('enable');
			// 删除是否博导combobox禁用
			$('#baseInfoIfDoctorTutor').datebox('enable');
			// 删除是否硕导combobox禁用
			$('#baseInfoIfMasterTutor').datebox('enable');*/
			
		}
	}
	
	// 查询该用户的基本信息
	function searchBaseinfo(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		// 获取角色
		var role=$("#role").val();
		// 获取用户id
		var accountId=$("#accountId").val();
		if(baseInfoId!=0){
			$('#fmv').form('load', 'baseInfo/biMailListView?path=mailListView&accountId='+accountId + urls); 
		}
		if(role==4){
			// 隐藏小号和工作室号
			$("#tr1").hide();
			$("#tr2").show();
			$("#tr3").show();
		}
	}
	
	// 编辑基本信息
	function editBaseInfo(){
		// 判断是否已填写完基本信息,如果没有填写基本信息,提示先填写基本信息
		var baseInfoId=$("#baseInfoId").val();
		if(baseInfoId=="" || baseInfoId==0){
			alert("请先填写个人基本信息");
			return;
		}else{
			// 0：是禁用 1.删除禁用
			ifDisabled(1);
			$("#baseInfoName").textbox('disable');
			$("#baseInfoPositionalTitles").combobox('disable');
			$("#baseInfoPost").textbox('disable');
			// 隐藏编辑按钮
			$("#editBtn").hide();
		}
	}
	
	//保存基本信息
    function saveUser(){
    	// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		if($("#baseInfoName").prop('disabled')){
    		$("#baseInfoName").textbox("enable");
    	}
    	var baseInfoId=$("#baseInfoId").val();
    	var baseInfoType=$("#baseInfoType").val();
       $('#fmv').form('submit',{
           url: "baseInfo/saveOrUpMailList?baseInfoId="+baseInfoId+"&baseInfoType="+baseInfoType + urls,
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
            	   ifDisabled(0);
            	   searchBaseinfo();
	               // 显示编辑按钮
	           	   $("#editBtn").show();
	           	 // 显示编辑按钮
	           	   $("#editBtn").show();
	           	   //隐藏保存按钮
		   		   $("#dlg-buttons").hide();
               }
           }
       });
    }
    
	/**
	 * 取消编辑，页面刷新
	 */
	function cancle() {
		searchBaseinfo();
		// 显示编辑按钮
	    $("#editBtn").show();
        // 隐藏保存按钮
		$("#dlg-buttons").hide();
		// 禁用页面标签
		ifDisabled(0);
	}
    
