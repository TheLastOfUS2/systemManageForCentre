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
			// 禁用性别combobox
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
		}else{
			//显示保存按钮
			$("#dlg-buttons").show();
			// 删除所有textbox的禁用
			$("#Staff1").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			// 删除性别combobox的禁用
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
			$('#baseInfoIfMasterTutor').datebox('enable');
			// 取消教育经历下所有textbox的禁用状态
			$("#educationalId").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			$("#educationalId").find(".textbox-icon,.combo-arrow").removeAttr("disabled").removeClass('textbox-icon-disabled');
			// 取消工作经历下所有textbox的禁用状态
			$("#workId").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			$("#workId").find(".textbox-icon,.combo-arrow").removeAttr("disabled").removeClass('textbox-icon-disabled');
			// 取消社会兼职下所有textbox的禁用状态
			$("#jobId").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			$("#jobId").find(".textbox-icon,.combo-arrow").removeAttr("disabled").removeClass('textbox-icon-disabled');
			// 取消出国经历下所有textbox的禁用状态
			$("#abroadId").find(".textbox-text,.textbox-value").removeAttr("disabled").removeClass('textbox-disabled');
			$("#abroadId").find(".textbox-icon,.combo-arrow").removeAttr("disabled").removeClass('textbox-icon-disabled');
			$("#educationalId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#workId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#jobId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#abroadId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#educationalId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#workId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#jobId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
			$("#abroadId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").show();
		}
	}
	
	// 查询该用户的基本信息
	function searchBaseinfo(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		// 获取角色
		var role=$("#role").val();
		// 获取用户id
		var baseInfoId=$("#baseInfoId").val();
		if(baseInfoId!=""){
			// 根据基本信息id,查询基本信息
			$.ajax({
				url:"baseInfo/baseInfoView?baseInfoId="+baseInfoId + urls,
				type:"post",
				success:function (data){
					if(data!=null){
						//将列表中的类型和选择数据的ID放到新增信息form中
						$('#fmv').form('load', data); 
						//每次进来设置educationalNum为0
						educationalNum=0;
	   					workNum=0;
	   					jobNum=0;
	   					abroadNum=0;
	   					//加载教育经历数据
						if(data.biEducationals!=null && data.biEducationals.length>0){
		   					editEducational(data);
						}else {
							$("#educationalId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
						}
						//加载工作经历数据
						if(data.biWorks!=null && data.biWorks.length>0){
		   					editWork(data);
						}else {
							$("#workId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
						}
						//加载工作经历数据
						if(data.biJobs!=null && data.biJobs.length>0){
							editJob(data);
						}else {
							$("#jobId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
						}
						//加载出国经历数据
						if(data.biAbroads!=null && data.biAbroads.length>0){
							editAbroad(data);
						}else {
							$("#adroadId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
						}
					}
				}
			});
		}
		//加载对应基本信息和经历信息
		if(role==3){
			pageDisplay("",0);
		}else{
			pageDisplay("",5);
		}
		
	}
	
	// 编辑基本信息
	function editBaseInfo(){
		// 0：是禁用 1.删除禁用
		ifDisabled(1);
		// 隐藏编辑按钮
		$("#editBtn").hide();
	}
	
	//保存基本信息
    function saveUser(){
    	// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
    	var accountId=$("#accountId").val();
    	var baseInfoType=$("#baseInfoType").val();
       $('#fmv').form('submit',{
           url: "baseInfo/saveOrUpBaseInfo?accountId="+accountId+"&baseInfoType="+baseInfoType + urls,
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
            	   // 保存基本信息id到隐藏域
            	   $("#baseInfoId").val(result.baseInfoId);
            	   ifDisabled(0);
            	   searchBaseinfo();
	               // 显示编辑按钮
	           	   $("#editBtn").show();
	           	   //隐藏保存按钮
		   		   $("#dlg-buttons").hide();
               }
           }
       });
    }
    
	//点击新增和编辑按钮时，动态显示文本内容
	function pageDisplay(data,baseInfoType){
		
		//append前清空教育经历
		$('#educationalExperience').empty();	
		//append前清空工作经历
		$('#workExperience').empty();	
		//append前清空社会兼职
		$('#socialAppointments').empty();	
		//append前清空出国经历
		$('#abroadExperience').empty();	
		
		//拼接页面(科研人员)
		if(baseInfoType==0){
			//显示出生日期
			$("#birthDate1").show();
			$("#birthDate2").show();
			//显示是否博导
			$("#doctorTutor1").show();
			$("#doctorTutor2").show();
			//拼接页面(教育经历)
			var educational=$("#educationalExperience").append("<div id='eId' style='width: 100%;'  class='base_info_head'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>教育经历</h2></td>" +
					"<td width='60px'><a  href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addEducational(event)' >添加</a></td>" +
					"</tr></tbody></table>" +
					"</div>"+
					"<div class='listBody'>" +
					"<table width='100%' border='0' id='educationalId'>" +
					"<thead>" +
					"<tr>" +
					"<th style='width: 7%;'>国家</th><th style='width: 13%;'>学校</th><th style='width: 29%;'>就读时间</th><th style='width: 13%;'>院系</th><th style='width: 13%;'>专业</th><th style='width: 10%;'>学历</th><th style='width: 10%;'>学位</th><th style='width: 5%;'>操作</th>" +
					"</tr>" +
					"</thead>" +
					"</table>" +
					"</div>");
			//拼接页面(工作经历)
			var work=$("#workExperience").append("<div id='wId'  style='width: 100%;' class='base_info_head'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>工作经历</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addWork(event)'>添加</a></td>" +
					"</tr></tbody></table>" +
					"</div>"+
					"<div class='listBody'>" +
					"<table width='100%' border='0' id='workId'>" +
					"<thead>" +
					"<tr>" +
					"<th style='width: 40%;'>时间</th><th style='width: 25%;'>工作单位</th><th style='width: 30%;'>职务</th><th style='width: 5%;'>操作</th>" +
					"</tr>" +
					"</thead>" +
					"</table>" +
					"</div>");
			
			//拼接页面(社会兼职)
			var social=$("#socialAppointments").append("<div id='sId' style='width: 100%;' class='base_info_head'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>社会兼职</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addJob(event)' >添加</a></td>" +
					"</tr></tbody></table>" +
					"</div>"+
					"<div class='listBody'>" +
					"<table width='100%' border='0' id='jobId'>" +
					"<thead>" +
					"<tr>" +
					"<th style='width: 10%;'>兼职类型</th><th style='width: 10%;'>机构类别</th><th style='width: 35%;'>时间</th><th style='width: 20%;'>兼职机构</th><th style='width: 20%;'>职务名称</th><th style='width: 5%;'>操作</th>" +
					"</tr>" +
					"</thead>" +
					"</table>" +
					"</div>");
			//拼接页面(出国经历)
			var abroad=$("#abroadExperience").append("<div id='aId' style='width: 100%;' class='base_info_head'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>出国经历</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addAbroad(event)' >添加</a></td>" +
					"</tr></tbody></table>" +
					"</div>"+
					"<div class='listBody'>" +
					"<table width='100%' border='0' id='abroadId'>" +
					"<thead>" +
					"<tr>" +
					"<th style='width: 20%;'>国家</th><th style='width: 35%;'>时间</th><th style='width: 25%;'>机构</th><th style='width: 15%;'>目的</th><th style='width: 5%;'>操作</th>" +
					"</tr>" +
					"</thead>" +
					"</table>" +
					"</div>");
			
			
			$.parser.parse(educational );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			$.parser.parse(work );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			$.parser.parse(social );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			$.parser.parse(abroad );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			
			$("#nationalityId1").remove();
			$("#nationalityId2").remove();
			
			$("#payrollCardId1").remove();
			$("#payrollCardId2").remove();
			
			$("#trId7").remove();
			$("#trId8").remove();
			$("#trId9").remove();
		}
		
		//拼接页面(其他人员)
		if(baseInfoType==5){
			// 更改身份证号为身份证号/护照号
			$("#baseInfoIdNumberLable").text("身份证号/护照号");
			//显示国籍
			$("#nationalityId1").show();
			$("#nationalityId2").show();
			//隐藏籍贯、民族、政治面貌、学历、学位、专业、是否博导、是否硕导、参加工作时间、工行卡号、大连银行卡号
			$("#trId1").remove();
			$("#trId2").remove();
			$("#trId3").remove();
			$("#trId4").remove();
			$("#trId5").remove();
			$("#trId6").remove();
			
			//显示银行卡号
			$("#payrollCardId1").show();
			$("#payrollCardId2").show();
			
			$("#trId7").show();
			$("#trId8").show();
			$("#trId9").show();
		}
	}
	
	//教育经历====================================================================================================
	//教育经历索引
	var educationalNum=0;
	//添加教育经历
	function addEducational(event){
		//获取已有条数
		var tbodys = $("#educationalId").find("tbody");
		educationalNum=tbodys.length;
		var targetObj =$("#educationalId").append("<tbody id='tbodyE"+educationalNum+"'>" +
		"<tr class='item-row'>" +
		"<td>" +
		"<input type='text' id='educationalCountry"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalCountry' class='easyui-textbox' style='width:45px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' id='educationalSchool"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalSchool' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' id='educationalStartTime"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalStartTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"~"+
		"<input type='text' id='educationalEndTime"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalEndTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' id='educationalCollege"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalCollege' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' id='educationalMajor"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalMajor' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>" +
		"<select class='easyui-combobox' id='educationalEducation"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalEducation' panelMaxHeight='70px' style='width:65px'>"+
		"<option value=''>请选择</option>" +
		"<option value='0'>博士</option>" +
		"<option value='1'>硕士</option>" +
		"<option value='2'>本科</option>" +
		"</select>"+
		"</td>"+
		"<td>" +
		"<select class='easyui-combobox' id='educationalDegree"+educationalNum+"' name='biEducationals[" + educationalNum + "].educationalDegree' panelMaxHeight='70px' style='width:65px'>"+
		"<option value=''>请选择</option>" +
		"<option value='0'>博士</option>" +
		"<option value='1'>硕士</option>" +
		"<option value='2'>学士</option>" +
		"</td>"+
		"<td>"+
		"<a id='"+educationalNum+"' href='javascript:void(0)'  class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delEducational(this.id)' style='border: 1px solid transparent;padding: 0;padding: 0;'></a>"+
		"</td>"+
		"</tr>" +
		"</tbody>");
		//渲染整个tbody(一行)
		$.parser.parse($("#tbodyE"+educationalNum));
		//单个渲染
		//$.parser.parse($("#educationalCountry"+educationalNum).parent());//单个渲染
		//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
		//$.parser.parse(targetObj );
		
		// 修改表格样式
		updateDomCss(2, $($($(event.currentTarget).parents(".base_info_head")[0]).next().children()[0]), educationalNum);
		
		educationalNum++;
	}
	
	//编辑教育经历
	function editEducational(data){
		for(var i=0;i<data.biEducationals.length;i++){
			//将教育经历id放到form中
			var educationalIds=$("#educationalIds").val();
			if(educationalIds!=""){
				$("#educationalIds").val(educationalIds+","+data.biEducationals[i].educationalId)
			}else{
				$("#educationalIds").val(data.biEducationals[i].educationalId);
			}
			var targetObj =$("#educationalId").append("<tbody id='tbodyE"+i+"'>" +
					"<tr class='item-row'>" +
					"<td>" +
					"<input type='text' id='educationalCountry"+i+"' name='biEducationals[" + i + "].educationalCountry' value='"+data.biEducationals[i].educationalCountry+"'  class='easyui-textbox' style='width:45px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' id='educationalSchool"+i+"' name='biEducationals[" + i + "].educationalSchool' value='"+data.biEducationals[i].educationalSchool+"' class='easyui-textbox' style='width:86px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' id='educationalStartTime"+i+"' name='biEducationals[" + i + "].educationalStartTime' value='"+data.biEducationals[i].educationalStartTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"~"+
					"<input type='text' id='educationalEndTime"+i+"' name='biEducationals[" + i + "].educationalEndTime' value='"+data.biEducationals[i].educationalEndTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' id='educationalCollege"+i+"' name='biEducationals[" + i + "].educationalCollege' value='"+data.biEducationals[i].educationalCollege+"' class='easyui-textbox' style='width:86px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' id='educationalMajor"+i+"' name='biEducationals[" + i + "].educationalMajor' value='"+data.biEducationals[i].educationalMajor+"' class='easyui-textbox' style='width:86px'>"+
					"</td>"+
					"<td>" +
					"<select class='easyui-combobox' id='educationalEducation"+i+"' name='biEducationals[" + i + "].educationalEducation' value='"+data.biEducationals[i].educationalEducation+"' panelMaxHeight='70px' style='width:65px'>"+
					"<option value=''>请选择</option>" +
					"<option value='0'>博士</option>" +
					"<option value='1' text='pxx'>硕士</option>" +
					"<option value='2'>本科</option>" +
					"</select>"+
					"</td>"+
					"<td>" +
					"<select class='easyui-combobox' id='educationalDegree"+i+"' name='biEducationals[" + i + "].educationalDegree' value='"+data.biEducationals[i].educationalDegree+"' panelMaxHeight='70px' style='width:65px'>"+
					"<option value=''>请选择</option>" +
					"<option value='0'>博士</option>" +
					"<option value='1'>硕士</option>" +
					"<option value='2'>学士</option>" +
					"</td>"+
					"<td>"+
					"<span style='display: none'><input hidden='hidden' type='text' id='educationalId"+i+"' name='biEducationals[" + i + "].educationalId' value='"+data.biEducationals[i].educationalId+"' class='easyui-textbox' style='width:45px'></span>"+
					"<a href='javascript:void(0)' id='"+i+"' class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delEducational(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
					"</td>"+
					"</tr>" +
					"</tbody>");
			//设置被选中的学历
	 		$("#educationalEducation"+i).val(data.biEducationals[i].educationalEducation);
	 		//设置被选中的学位
	 		$("#educationalDegree"+i).val(data.biEducationals[i].educationalDegree);
	 		//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了
			$.parser.parse($("#tbodyE"+i));
			
			// 修改表格样式
			updateDomCss(2, $("#educationalId"), i);
		}
		// 禁用所有的textbox
		$("#educationalId").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');
		$("#educationalId").find(".textbox-icon,.combo-arrow").attr("disabled", "disabled").addClass('textbox-icon-disabled');
		$("#educationalId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
		$("#educationalId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
	}
	
	//删除教育经历
	function delEducational(obj){
		var tbody=$("#tbodyE"+obj);
		tbody.remove(); 
		//获取删除后的tbody的长度
		var tbodys = $("#educationalId").find("tbody");
		educationalNum=tbodys.length;
		//遍历tbodys
		for(var i=0;i<tbodys.length;i++) { 
			tbodys[i].id="tbodyE"+i;
			$(tbodys.eq(i).find("input")[0]).attr("textboxname", "biEducationals[" + i + "].educationalCountry");
			tbodys.eq(i).find("input")[2].name="biEducationals[" + i + "].educationalCountry";
			$(tbodys.eq(i).find("input")[3]).attr("textboxname", "biEducationals[" + i + "].educationalSchool"); 
			tbodys.eq(i).find("input")[5].name="biEducationals[" + i + "].educationalSchool";
			$(tbodys.eq(i).find("input")[6]).attr("textboxname", "biEducationals[" + i + "].educationalStartTime"); 
			tbodys.eq(i).find("input")[8].name="biEducationals[" + i + "].educationalStartTime";
			$(tbodys.eq(i).find("input")[9]).attr("textboxname", "biEducationals[" + i + "].educationalEndTime");
			tbodys.eq(i).find("input")[11].name="biEducationals[" + i + "].educationalEndTime";
			$(tbodys.eq(i).find("input")[12]).attr("textboxname", "biEducationals[" + i + "].educationalCollege");
			tbodys.eq(i).find("input")[14].name="biEducationals[" + i + "].educationalCollege";
			$(tbodys.eq(i).find("input")[15]).attr("textboxname", "biEducationals[" + i + "].educationalMajor"); 
			tbodys.eq(i).find("input")[17].name="biEducationals[" + i + "].educationalMajor";
			$(tbodys.eq(i).find("select")[0]).attr("textboxname", "biEducationals[" + i + "].educationalEducation");
			tbodys.eq(i).find("input")[19].name="biEducationals[" + i + "].educationalEducation";
			$(tbodys.eq(i).find("select")[1]).attr("textboxname", "biEducationals[" + i + "].educationalDegree");
			tbodys.eq(i).find("input")[21].name="biEducationals[" + i + "].educationalDegree"; 
			if(tbodys.eq(i).find("input")[24]!=undefined){
				$(tbodys.eq(i).find("input")[22]).attr("textboxname", "biEducationals[" + i + "].educationalId"); 
				tbodys.eq(i).find("input")[24].name="biEducationals[" + i + "].educationalId"; 
			}
			tbodys.eq(i).find("a")[4].id=""+i+"";
		} 
	}
	
	//工作经历====================================================================================================
	//工作经历索引
	var workNum=0;
	//添加工作经历
	function addWork(event){
		//获取已有条数
		var tbodys = $("#workId").find("tbody");
		workNum=tbodys.length;
		var targetObj =$("#workId").append("<tbody id='tbodyW"+workNum+"'>" +
		"<tr class='item-row'>" +
		"<td>" +
		"<input type='text' name='biWorks[" + workNum + "].workStartTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"~"+
		"<input type='text' name='biWorks[" + workNum + "].workEndTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biWorks[" + workNum + "].workCompany' class='easyui-textbox' style='width:45px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biWorks[" + workNum + "].workPost' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>"+
		"<a id='"+workNum+"' href='javascript:void(0)'  class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delWork(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
		"</td>"+
		"</tr>" +
		"</tbody>");
		//渲染整个tbody(一行)
		$.parser.parse($("#tbodyW"+workNum));
		//单个渲染
		//$.parser.parse($("#educationalCountry"+educationalNum).parent());//单个渲染
		//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
		//$.parser.parse(targetObj );
		
		// 修改表格样式
		updateDomCss(1, $($($(event.currentTarget).parents(".base_info_head")[0]).next().children()[0]), workNum);
		
		workNum++;
	}
	
	//编辑工作经历
	function editWork(data){
		for(var i=0;i<data.biWorks.length;i++){
			//将教育经历id放到form中
			var workIds=$("#workIds").val();
			if(workIds!=""){
				$("#workIds").val(workIds+","+data.biWorks[i].workId)
			}else{
				$("#workIds").val(data.biWorks[i].workId);
			}
			var targetObj =$("#workId").append("<tbody id='tbodyW"+i+"'>" +
					"<tr class='item-row'>" +
					"<td>" +
					"<input type='text' name='biWorks[" + i + "].workStartTime' value='"+data.biWorks[i].workStartTime+"' class='easyui-datebox EdiInputText' style='width:88px' >"+
					"~"+
					"<input type='text' name='biWorks[" + i + "].workEndTime' value='"+data.biWorks[i].workEndTime+"' class='easyui-datebox EdiInputText' style='width:88px' >"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biWorks[" + i + "].workCompany' value='"+data.biWorks[i].workCompany+"'  class='easyui-textbox' style='width:45px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biWorks[" + i + "].workPost' value='"+data.biWorks[i].workPost+"' class='easyui-textbox' style='width:86px' >"+
					"</td>"+
					"<td>"+
					"<span style='display: none'><input hidden='text' type='text' name='biWorks[" + i + "].workId' value='"+data.biWorks[i].workId+"' class='easyui-textbox' style='width:45px'></span>"+
					"<a href='javascript:void(0)' id='"+i+"' class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delWork(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
					"</td>"+
					"</tr>" +
					"</tbody>");
			$.parser.parse($("#tbodyW"+i));
			
			// 修改表格样式
			updateDomCss(1, $("#workId"), i);
		}
		// 禁用所有的textbox
		$("#workId").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');
		$("#workId").find(".textbox-icon,.combo-arrow").attr("disabled", "disabled").addClass('textbox-icon-disabled');
		$("#workId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
		$("#workId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
	}
	
	//删除工作经历
	function delWork(obj){
		var tbody=$("#tbodyW"+obj);
		tbody.remove(); 
		//获取删除后的tbody的长度
		var tbodys = $("#workId").find("tbody");
		workNum=tbodys.length;
		//遍历tbodys
		for(var i=0;i<tbodys.length;i++) { 
			tbodys[i].id="tbodyW"+i;
			$(tbodys.eq(i).find("input")[0]).attr("textboxname", "biWorks[" + i + "].workStartTime");
			tbodys.eq(i).find("input")[2].name="biWorks[" + i + "].workStartTime";
			$(tbodys.eq(i).find("input")[3]).attr("textboxname", "biWorks[" + i + "].workEndTime");
			tbodys.eq(i).find("input")[5].name="biWorks[" + i + "].workEndTime";
			$(tbodys.eq(i).find("input")[6]).attr("textboxname", "biWorks[" + i + "].workCompany");
			tbodys.eq(i).find("input")[8].name="biWorks[" + i + "].workCompany";
			$(tbodys.eq(i).find("input")[9]).attr("textboxname", "biWorks[" + i + "].workPost");
			tbodys.eq(i).find("input")[11].name="biWorks[" + i + "].workPost"; 
			if(tbodys.eq(i).find("input")[14]!=undefined){
				$(tbodys.eq(i).find("input")[12]).attr("textboxname", "biWorks[" + i + "].workId");
				tbodys.eq(i).find("input")[14].name="biWorks[" + i + "].workId"; 
			}
			tbodys.eq(i).find("a")[2].id=""+i+"";
		} 
	}
	
	//社会兼职====================================================================================================
	//社会兼职索引
	var jobNum=0; 
	//添加社会兼职
	function addJob(event){
		//获取已有条数
		var tbodys = $("#jobId").find("tbody");
		jobNum=tbodys.length;
		var targetObj =$("#jobId").append("<tbody id='tbodyJ"+jobNum+"'>" +
		"<tr class='item-row'>" +
		"<td>" +
		"<select class='easyui-combobox' id='jobType"+jobNum+"' name='biJobs[" + jobNum + "].jobType' panelMaxHeight='70px' style='width:65px'>"+
		"<option value=''>请选择</option>" +
		"<option value='0'>政府兼职</option>" +
		"<option value='1'>企业兼职</option>" +
		"<option value='2'>学术兼职</option>" +
		"</select>"+
		"</td>"+
		"<td>" +
		"<select class='easyui-combobox' id='jobMechanismType"+jobNum+"' name='biJobs[" + jobNum + "].jobMechanismType' panelMaxHeight='70px' style='width:65px'>"+
		"<option value=''>请选择</option>" +
		"<option value='0'>国内兼职</option>" +
		"<option value='1'>国外兼职</option>" +
		"</select>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biJobs[" + jobNum + "].jobStartTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"~"+
		"<input type='text' name='biJobs[" + jobNum + "].jobEndTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biJobs[" + jobNum + "].jobMechanism' class='easyui-textbox' style='width:45px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biJobs[" + jobNum + "].jobPost' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>"+
		"<a id='"+jobNum+"' href='javascript:void(0)'  class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delJob(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
		"</td>"+
		"</tr>" +
		"</tbody>");
		//渲染整个tbody(一行)
		$.parser.parse($("#tbodyJ"+jobNum));
		
		// 修改表格样式
		updateDomCss(3, $($($(event.currentTarget).parents(".base_info_head")[0]).next().children()[0]), jobNum);
		
		jobNum++;
	}
	
	//编辑社会兼职
	function editJob(data){
		for(var i=0;i<data.biJobs.length;i++){
			//将教育经历id放到form中
			var jobIds=$("#jobIds").val();
			if(jobIds!=""){
				$("#jobIds").val(jobIds+","+data.biJobs[i].jobId)
			}else{
				$("#jobIds").val(data.biJobs[i].jobId);
			}
			var targetObj =$("#jobId").append("<tbody id='tbodyJ"+i+"'>" +
					"<tr class='item-row'>" +
					"<td>" +
					"<select class='easyui-combobox' id='jobType"+i+"' name='biJobs[" + i + "].jobType' value='"+data.biJobs[i].jobType+"'  panelMaxHeight='70px' style='width:65px'>"+
					"<option value=''>请选择</option>" +
					"<option value='0'>政府兼职</option>" +
					"<option value='1'>企业兼职</option>" +
					"<option value='2'>学术兼职</option>" +
					"</select>"+
					"</td>"+
					"<td>" +
					"<select class='easyui-combobox' id='jobMechanismType"+i+"' name='biJobs[" + i + "].jobMechanismType' value='"+data.biJobs[i].jobMechanismType+"' panelMaxHeight='70px' style='width:65px' >"+
					"<option value=''>请选择</option>" +
					"<option value='0'>国内兼职</option>" +
					"<option value='1'>国外兼职</option>" +
					"</select>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobStartTime' value='"+data.biJobs[i].jobStartTime+"' class='easyui-datebox EdiInputText' style='width:88px' >"+
					"~"+
					"<input type='text' name='biJobs[" + i + "].jobEndTime' value='"+data.biJobs[i].jobEndTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobMechanism' value='"+data.biJobs[i].jobMechanism+"' class='easyui-textbox' style='width:45px' >"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobPost' value='"+data.biJobs[i].jobPost+"' class='easyui-textbox' style='width:86px' >"+
					"</td>"+
					"<td>"+
					"<span style='display: none'><input hidden='text' type='text' name='biJobs[" + i + "].jobId' value='"+data.biJobs[i].jobId+"' class='easyui-textbox' style='width:45px'></span>"+
					"<a href='javascript:void(0)' id='"+i+"' class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delJob(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
					"</td>"+
					"</tr>" +
					"</tbody>");
			
			//设置被选中的兼职类型
	 		$("#jobType"+i).val(data.biJobs[i].jobType);
	 		//设置被选中的机构类别
	 		$("#jobMechanismType"+i).val(data.biJobs[i].jobMechanismType);
			
			$.parser.parse($("#tbodyJ"+i));
			
			// 修改表格样式
			updateDomCss(3, $("#jobId"), i);
		}
		// 禁用所有的textbox
		$("#jobId").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');
		$("#jobId").find(".textbox-icon,.combo-arrow").attr("disabled", "disabled").addClass('textbox-icon-disabled');
		$("#jobId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
		$("#jobId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
	}
	
	//删除社会兼职
	function delJob(obj){
		var tbody=$("#tbodyJ"+obj);
		tbody.remove(); 
		//获取删除后的tbody的长度
		var tbodys = $("#jobId").find("tbody");
		jobNum=tbodys.length;
		//遍历tbodys
		for(var i=0;i<tbodys.length;i++) { 
			tbodys[i].id="tbodyJ"+i;
			$(tbodys.eq(i).find("select")[0]).attr("textboxname", "biJobs[" + i + "].jobType");
			tbodys.eq(i).find("input")[1].name="biJobs[" + i + "].jobType";
			$(tbodys.eq(i).find("select")[1]).attr("textboxname", "biJobs[" + i + "].jobMechanismType");
			tbodys.eq(i).find("input")[3].name="biJobs[" + i + "].jobMechanismType";
			$(tbodys.eq(i).find("input")[4]).attr("textboxname", "biJobs[" + i + "].jobStartTime");
			tbodys.eq(i).find("input")[6].name="biJobs[" + i + "].jobStartTime";
			$(tbodys.eq(i).find("input")[7]).attr("textboxname", "biJobs[" + i + "].jobEndTime");
			tbodys.eq(i).find("input")[9].name="biJobs[" + i + "].jobEndTime";
			$(tbodys.eq(i).find("input")[10]).attr("textboxname", "biJobs[" + i + "].jobMechanism");
			tbodys.eq(i).find("input")[12].name="biJobs[" + i + "].jobMechanism";
			$(tbodys.eq(i).find("input")[13]).attr("textboxname", "biJobs[" + i + "].jobPost");
			tbodys.eq(i).find("input")[15].name="biJobs[" + i + "].jobPost";
			if(tbodys.eq(i).find("input")[18]!=undefined){
				$(tbodys.eq(i).find("input")[16]).attr("textboxname", "biJobs[" + i + "].workId");
				tbodys.eq(i).find("input")[18].name="biJobs[" + i + "].workId"; 
			}
			tbodys.eq(i).find("a")[4].id=""+i+"";
		} 
	}
	
	//出国经历====================================================================================================
	//出国经历索引
	var abroadNum=0;  
	//添加出国经历
	function addAbroad(event){
		//获取已有条数
		var tbodys = $("#abroadId").find("tbody");
		abroadNum=tbodys.length;
		var targetObj =$("#abroadId").append("<tbody id='tbodyA"+abroadNum+"'>" +
		"<tr class='item-row'>" +
		"<td>" +
		"<input type='text' name='biAbroads[" + abroadNum + "].abroadCountry' class='easyui-textbox' style='width:86px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biAbroads[" + abroadNum + "].abroadStartTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"~"+
		"<input type='text' name='biAbroads[" + abroadNum + "].abroadEndTime' class='easyui-datebox EdiInputText' style='width:88px'>"+
		"</td>"+
		"<td>" +
		"<input type='text' name='biAbroads[" + abroadNum + "].abroadMechanism' class='easyui-textbox' style='width:45px'>"+
		"<td>" +
		"<select class='easyui-combobox' id='biAbroads"+abroadNum+"' name='biAbroads[" + abroadNum + "].abroadObjective' panelMaxHeight='70px' style='width:65px'>"+
		"<option value=''>请选择</option>" +
		"<option value='0'>高访</option>" +
		"<option value='1'>培训</option>" +
		"<option value='2'>项目合作</option>" +
		"<option value='3'>学习</option>" +
		"<option value='4'>留学</option>" +
		"<option value='5'>其他</option>" +
		"</select>"+
		"</td>"+
		"<td>" +
		"<a id='"+abroadNum+"' href='javascript:void(0)'  class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delAbroad(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
		"</td>"+
		"</tr>" +
		"</tbody>");
		//渲染整个tbody(一行)
		$.parser.parse($("#tbodyA"+abroadNum));
		
		// 修改表格样式
		updateDomCss(4, $($($(event.currentTarget).parents(".base_info_head")[0]).next().children()[0]), abroadNum);
		
		abroadNum++;
	}
	
	//编辑出国经历
	function editAbroad(data){
		for(var i=0;i<data.biAbroads.length;i++){
			//将教育经历id放到form中
			var abroadIds=$("#abroadIds").val();
			if(abroadIds!=""){
				$("#abroadIds").val(abroadIds+","+data.biAbroads[i].abroadId)
			}else{
				$("#abroadIds").val(data.biAbroads[i].abroadId);
			}
			var targetObj =$("#abroadId").append("<tbody id='tbodyA"+i+"'>" +
					"<tr class='item-row'>" +
					"<td>" +
					"<input type='text' name='biAbroads[" + i + "].abroadCountry' value='"+data.biAbroads[i].abroadCountry+"' class='easyui-textbox' style='width:86px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biAbroads[" + i + "].abroadStartTime' value='"+data.biAbroads[i].abroadStartTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"~"+
					"<input type='text' name='biAbroads[" + i + "].abroadEndTime' value='"+data.biAbroads[i].abroadEndTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biAbroads[" + i + "].abroadMechanism' value='"+data.biAbroads[i].abroadMechanism+"' class='easyui-textbox' style='width:45px'>"+
					"<td>" +
					"<select class='easyui-combobox' id='biAbroads"+i+"' name='biAbroads[" + i + "].abroadObjective' value='"+data.biAbroads[i].abroadObjective+"' panelMaxHeight='70px' style='width:65px'>"+
					"<option value=''>请选择</option>" +
					"<option value='0'>高访</option>" +
					"<option value='1'>培训</option>" +
					"<option value='2'>项目合作</option>" +
					"<option value='3'>学习</option>" +
					"<option value='4'>留学</option>" +
					"<option value='5'>其他</option>" +
					"</select>"+
					"</td>"+
					"<td>"+
					"<span style='display: none'><input hidden='text' type='text' name='biAbroads[" + i + "].abroadId' value='"+data.biAbroads[i].abroadId+"' class='easyui-textbox' style='width:45px'></span>"+
					"<a href='javascript:void(0)' id='"+i+"' class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delAbroad(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
					"</td>"+
					"</tr>" +
					"</tbody>");
			//设置被选中的目的
	 		$("#biAbroads"+i).val(data.biAbroads[i].abroadObjective);
	 		
			$.parser.parse($("#tbodyA"+i));
			
			// 修改表格样式
			updateDomCss(4, $("#abroadId"), i);
		}
		// 禁用所有的textbox
		$("#abroadId").find(".textbox-text,.textbox-value").attr("disabled", "disabled").addClass('textbox-disabled');
		$("#abroadId").find(".textbox-icon,.combo-arrow").attr("disabled", "disabled").addClass('textbox-icon-disabled');
		$("#abroadId").find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
		$("#abroadId").parent().prev().find(".easyui-linkbutton,.l-btn,.l-btn-small,.l-btn-plain").hide();
	}
	
	//删除出国经历
	function delAbroad(obj){
		var tbody=$("#tbodyA"+obj);
		tbody.remove(); 
		//获取删除后的tbody的长度
		var tbodys = $("#abroadId").find("tbody");
		abroadNum=tbodys.length;
		//遍历tbodys
		for(var i=0;i<tbodys.length;i++) { 
			tbodys[i].id="tbodyA"+i;
			$(tbodys.eq(i).find("input")[0]).attr("textboxname", "biAbroads[" + i + "].abroadCountry");
			tbodys.eq(i).find("input")[2].name="biAbroads[" + i + "].abroadCountry";
			$(tbodys.eq(i).find("input")[3]).attr("textboxname", "biAbroads[" + i + "].abroadStartTime");
			tbodys.eq(i).find("input")[5].name="biAbroads[" + i + "].abroadStartTime";
			$(tbodys.eq(i).find("input")[6]).attr("textboxname", "biAbroads[" + i + "].abroadEndTime");
			tbodys.eq(i).find("input")[8].name="biAbroads[" + i + "].abroadEndTime";
			$(tbodys.eq(i).find("input")[9]).attr("textboxname", "biAbroads[" + i + "].abroadMechanism");
			tbodys.eq(i).find("input")[11].name="biAbroads[" + i + "].abroadMechanism";
			$(tbodys.eq(i).find("select")[0]).attr("textboxname", "biAbroads[" + i + "].abroadObjective");
			tbodys.eq(i).find("input")[13].name="biAbroads[" + i + "].abroadObjective";
			if(tbodys.eq(i).find("input")[16]!=undefined){
				$(tbodys.eq(i).find("input")[14]).attr("textboxname", "biAbroads[" + i + "].abroadId");
				tbodys.eq(i).find("input")[16].name="biAbroads[" + i + "].abroadId"; 
			}
			tbodys.eq(i).find("a")[3].id=""+i+"";
		} 
	}
	
	/**
	 * 修改表格样式
	 * @param type 表格类型（教育经历-2、工作经历-1、社会兼职-3、出国经历-4）
	 * @param dom 节点对象
	 * @param workNum 行号
	 */
	function updateDomCss(type, dom, workNum) {
		var tds = null;
		var tbodys = dom.find("tbody");
		if(type == 1) {
			var nums = "tbodyW"+workNum;
			for(var i = 0; i < tbodys.length; i ++) {
				if(nums == $(tbodys[i]).attr("id")) {
					tds = $($(tbodys[i]).children()[0]).children();
					break;
				}
			}
			$($(tds[0]).children()[1]).width("130");
			$($($(tds[0]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 104px;");
			$($(tds[0]).children()[3]).width("130");
			$($($(tds[0]).children()[3]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 104px;");
			$($(tds[1]).children()[1]).width("167");
			$($($(tds[1]).children()[1]).children()[0]).width("159");
			$($(tds[2]).children()[1]).width("203");
			$($($(tds[2]).children()[1]).children()[0]).width("195");
		}else if(type == 2) {
			var nums = "tbodyE"+workNum;
			for(var i = 0; i < tbodys.length; i ++) {
				if(nums == $(tbodys[i]).attr("id")) {
					tds = $($(tbodys[i]).children()[0]).children();
					break;
				}
			}
			$($(tds[2]).children()[1]).width("93");
			$($($(tds[2]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 67px;");
			$($(tds[0]).children()[3]).width("93");
			$($($(tds[0]).children()[3]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 67px;");
		}else if(type == 3) {
			var nums = "tbodyJ"+workNum;
			for(var i = 0; i < tbodys.length; i ++) {
				if(nums == $(tbodys[i]).attr("id")) {
					tds = $($(tbodys[i]).children()[0]).children();
					break;
				}
			}
			$($(tds[0]).children()[1]).width("75");
			$($($(tds[0]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 49px;");
			$($(tds[1]).children()[1]).width("75");
			$($($(tds[1]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 49px;");
			$($(tds[2]).children()[1]).width("102");
			$($($(tds[2]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 75px;");
			$($(tds[2]).children()[3]).width("102");
			$($($(tds[2]).children()[3]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 75px;");
			$($(tds[3]).children()[1]).width("132");
			$($($(tds[3]).children()[1]).children()[0]).width("124");
			$($(tds[4]).children()[1]).width("132");
			$($($(tds[4]).children()[1]).children()[0]).width("124");
		}else {
			var nums = "tbodyA"+workNum;
			for(var i = 0; i < tbodys.length; i ++) {
				if(nums == $(tbodys[i]).attr("id")) {
					tds = $($(tbodys[i]).children()[0]).children();
					break;
				}
			}
			$($(tds[0]).children()[1]).width("133");
			$($($(tds[0]).children()[1]).children()[0]).width("125");
			$($(tds[1]).children()[1]).width("113");
			$($($(tds[1]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 86px;");
			$($(tds[1]).children()[3]).width("113");
			$($($(tds[1]).children()[3]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 86px;");
			$($(tds[2]).children()[1]).width("168");
			$($($(tds[2]).children()[1]).children()[0]).width("160");
			$($(tds[3]).children()[1]).width("97");
			$($($(tds[3]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 70px;");
		}
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