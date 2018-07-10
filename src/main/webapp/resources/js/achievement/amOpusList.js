	var p=0;
	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//当"姓名"文本框被触发时,去后台获取人员列表
		$("input",$("#baseInfoName").next("span")).focus(function(){
			if(p==0){
				searchByType(0,"baseInfoName","");
			}
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#opusDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide(); 
		
		// 批量下载方法
		$("#downloadAllFiles").click(function(){
			// 获取操作对应的菜单ID
			urls = getPowerMenuId();
			
			// 获取fileList下的tr个数
			var fileLen=$("#fileList").find("tr").length;
			if(fileLen>0){
				// 获取所有上传文件id
				var fileids="";
				for(var i=0;i<fileLen;i++){
					if(fileids==""){
						fileids=$("#fileIds"+i).val();
					}else{
						fileids=fileids+","+$("#fileIds"+i).val();
					}
				}
				window.open("common/downloadAllFiles?fileIds="+fileids+urls);
			}
		});
	});
	
	//plg 弹出框点击取消按钮,将p设置为0
	function funClose(){
		p=0;
	}
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	
	//调用后台方法
	function queryAction(opusDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var queryParam="";
		// 获取全文检索
		//责任人id
		var qBaseInfoName=$("#qBaseInfoName").val();
		if(qBaseInfoName!="" && qBaseInfoName!="null"){
			queryParam="&qBaseInfoName="+qBaseInfoName;
		}
		// 责任人类别
		var baseInfoType=$("#baseInfoType").val();
		if(baseInfoType!="" && baseInfoType!="null"){
			queryParam=queryParam+"&baseInfoType="+baseInfoType;
		}
		
		// 责任人职称
		var baseInfoPositionalTitles=$("#baseInfoPositionalTitles").val();
		if(baseInfoPositionalTitles!="" && baseInfoPositionalTitles!="null"){
			queryParam=queryParam+"&baseInfoPositionalTitles="+baseInfoPositionalTitles;
		}
		
		// 责任人级别
		var baseInfoLevel=$("#baseInfoLevel").val();
		if(baseInfoLevel!="" && baseInfoLevel!="null"){
			queryParam=queryParam+"&baseInfoLevel="+baseInfoLevel;
		}
		
		// 责任人区间
		var baseInfoStartAge=$("#baseInfoStartAge").val();
		if(baseInfoStartAge!="" && baseInfoStartAge!="null"){
			queryParam=queryParam+"&baseInfoStartAge="+baseInfoStartAge;
		}
		var baseInfoEndAge=$("#baseInfoEndAge").val();
		if(baseInfoEndAge!="" && baseInfoEndAge!="null"){
			queryParam=queryParam+"&baseInfoEndAge="+baseInfoEndAge;
		}

		// 成果/项目名称
		var projectName=$("#projectName").val();
		if(projectName!="" && projectName!="null"){
			queryParam=queryParam+"&projectName="+projectName;
		}
		
		// 成果时间区间
		var projectStartTime=$("#projectStartTime").val();
		if(projectStartTime!="" && projectStartTime!="null"){
			queryParam=queryParam+"&projectStartTime="+projectStartTime;
		}
		var projectEndTime=$("#projectEndTime").val();
		if(projectEndTime!="" && projectEndTime!="null"){
			queryParam=queryParam+"&projectEndTime="+projectEndTime;
		}
		
		// 根据用户角色和和用户账号查询列表
		var baseInfoId=$("#baseInfoId").val();
		var role=$("#role").val();
		if(role==3 || role==4){
			queryParam="&baseInfoId="+baseInfoId;
		}
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'opus/opusList?opusDel='+opusDel+"&getName="+getName+"&getValue="+getValue+queryParam + urls,
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'opusId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'opusName',
                title : '著作名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'baseInfoName',
                title : '作者',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'opusPublishTime',
                title : '出版时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            }, {
                field : 'opusPublishCompany',
                title : '出版单位',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                	 if(value!=null && value!=''){
     				    return value
     				 } else{return '--'}
                }
            }, {
                field : 'opusSupportTopic',
                title : '支持课题',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            }, {
                field : 'opusLanguages',
                title : '语种',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
   				 if(value!=null && value!=''){
   				   if(value=='0'){return '中文'} else if(value=='1'){return '外文'}
   				 } else{return '--'}
                }
            }, {
                field : 'opusType',
                title : '著作类型',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
   				 if(value!=null && value!=''){
   				   if(value=='0'){return '专著'} else if(value=='1'){return '编著'}
   				   else if(value=='2'){return '译著'} else if(value=='3'){return '教材'}
   				   else if(value=='4'){return '工具书'}
   				   else if(value=='5'){return '普及性出版物'}
   				 } else{return '--'}
                }
            }] ],
            /*datagrid绑定右键菜单*/
            onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件 
            	 //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据 
            	 e.preventDefault(); //阻止浏览器捕获右键事件 
            	 $(this).datagrid("clearSelections"); //取消所有选中项 
            	 $(this).datagrid("selectRow", rowIndex); //根据索引选中该行 
            	 $('#menu').menu('show', { 
            	  //显示右键菜单 
            	  left: e.pageX,//在鼠标点击处显示菜单 
            	  top: e.pageY 
            	 }); 
            	 e.preventDefault(); //阻止浏览器自带的右键菜单弹出 
            	 } 
            /*datagrid绑定右键菜单*/
		});
	}
	var num=0;
	var url;
	function add(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		num=0;
		//根据登录账号id查询角色,如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
		//$('#fm').form('load', 'opus/opusView'); 
    	$("#dlg").dialog({  
    		title: "学术成果-著作-信息申报",
				iconCls : "icon-add",
				collapsible: true,
              /* minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					if(num==0){//防止点击编辑按钮后,再走一遍onOpen
						//将列表中的类型放到新增信息form中
	   					$('#baseInfoPath').val(0);
	   					//每次进来设置num为0
	   					collaboratorNum=0;
	   					pageDisplay("");
	   				    //负责人赋值
						setBaseInfoName();
					}
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "opus/saveOrUpOpus?collaboratorType=0" + urls;
	}
	function setBaseInfoName(){
		var baseInfoId=$("#baseInfoId").val();
		var baseInfoName=$("#baseInfoName2").val();
		var role=$("#role").val();
		if(baseInfoId!="" && baseInfoName!="" && (role=="3" || role=="4")){
			$("#baseInfoName").textbox('setValue',baseInfoName);
			$("#baseInfoNameId").val(baseInfoId);
			$("#baseInfoName").textbox("disable");
		}
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	num=1;
        	$.ajax({
				url:"opus/opusView?opusId="+row.opusId + urls,
				type:"post",
				success:function (data){
					if(data!=null){
						$('#fm').form('load', data); 
						//将列表中的类型放到新增信息form中
						$('#opusId').val(row.opusId);
						$('#baseInfoPath').val(1);
						//每次进来设置collaboratorNum为0
						collaboratorNum=0;
						//加载对应基本信息和经历信息
	   					pageDisplay(data);
	   					
	   					//负责人赋值
						setBaseInfoName();
						
	   					//加载合作者数据
						if(data.amCollaborators!=null && data.amCollaborators.length>0){
							editCollaborator(data);
						}
						$("#dlg").dialog({  
							title: "学术成果-著作-信息编辑",
		   					iconCls : "icon-edit",
		   					collapsible: true,
		   	              /*  minimizable: true,
		   	                maximizable: true,*/
		   	                resizable: true,
		   					onClose: function(){
		   					   win.window('destroy');//关闭即销毁
		   					},
		            	});
		            	$("#dlg").dialog("open");
		            	// 弹框居中
		            	dlgCenter();
		            	//点击保存按钮后跳转路径;collaboratorType:著作
		            	url = "opus/saveOrUpOpus?collaboratorType=0" + urls;
					}
				}
			});
        	//$('#fm').form('load', 'opus/opusView?path=editOpus&opusId='+row.opusId); 
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function del(){
		//获取是在未被删除数据的列表还是已被删除数据的列表
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var opusDel=$("#opusDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
		if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].opusId;  
                 } else {  
                     temID = selRow[i].opusId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(opusDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('opus/deleteOpus?opusDel='+opusDel + urls,{opusId:temID},function(data){
                    	if(data.status == 1){
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: '提示',
                                msg: data.text
                            });
                        } else {
                        	$.messager.show({
                                title: '提示',
                                msg: data.text
                            });
                        }
                    },'json');
                }
            });
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function trash(){
		//查询已删除数据列表
		queryAction(1);
		//彻底删除数据
		$("#opusDel").val(2);
		//显示恢复按钮
		 $("div.datagrid-toolbar [id ='recovery']").eq(0).show();   
	}
	
	function recovery(){
		//将选中的数据恢复成未删除状态
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].opusId;  
                 } else {  
                     temID = selRow[i].opusId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('opus/deleteOpus?opusDel=0' + urls,{opusId:temID},function(data){
                    	if(data.status == 1){
                            $('#dg').datagrid('reload');
                            $.messager.show({
                                title: '提示',
                                msg: data.text
                            });
                        } else {
                        	$.messager.show({
                                title: '提示',
                                msg: data.text
                            });
                        }
                    },'json');
                }
            });
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function excel(){
		$("#dlgExcel").dialog("open");
	}
	
	//导出excle
	function exportExcel(){
		$("#dlgExcel").dialog("close");
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		getValue=getValueFunction(getName, getValue);
		
		var url = "ReportServer?reportlet=opus.cpt&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("著作");
		window.open(url);
}
	function getValueFunction(name, value){
		//著作类型
        if(name=="opusType"){
            if(value=="专著"){
               value=0; 
            }else if(value=="编著"){
               value=1; 
            }else if(value=="译著"){
               value=2; 
            }else if(value=="教材"){
               value=3; 
            }else if(value=="工具书"){
               value=4; 
            }else if(value=="普及行出版物"){
               value=5; 
            }else{
            	value=99; 
            }
            //语种
        }else if(name=="opusLanguages"){
            if(value=="中文"){
                value=0; 
            }else if(value=="外文"){
                value=1; 
            }else{
                value=99;
            }
        }
        return value;
    }
	
	// 上传材料
	function upload(){
		//获取选择上传数据id
		var row = $('#dg').datagrid('getSelected');
		//表名
		var tableName="am_opus"
		$("#relevanceId").val(row.opusId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.opusId);
	}
	
	function uploadList(tableName,opusId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+opusId+"&tableName="+tableName + urls,
			type:"post",
			success:function (data){
				$("#dlgUpload").dialog({  
   					//modal:true,遮罩层
   					left: 141,
   					onClose: function(){
   					   win.window('destroy');//关闭即销毁
   					},
            	});
				if(data!=null && data.length>0){
					for(var i=0;i<data.length;i++){
						var t=format(data[i].fileCreateTime);
						$("#fileList").append(
						"<tr>" +
						"<td>" +
						"<input type='hidden' id='fileIds"+i+"' value='"+data[i].fileId+"'>"+
						"<input type='text' value='"+data[i].fileName+"' style='border: 1px solid #BDC7D8;' readonly>"+
						"</td>" +
						"<td>" +
						"<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>首页、版权页及目录页</option>" +
						"<option value='1'>其他</option>" +
						"</select>"+
						"</td>" +
						"<td>" +
						"<input type='text' value='"+data[i].fileContent+"'style='border: 1px solid #BDC7D8;' readonly>"+
						"</td>" +
						"<td>" +
						""+t+""+
						"</td>" +
						"<td width='30px'>" +
						/*"<a href='javascript:downFile("+data[i].fileId+");' title='下载此附件'>" +
						"</a>"+
						"<a href='javascript:viewFile("+data[i].fileId+");' title='查看'></a>"+
						"<a href='javascript:delFile("+data[i].fileId+");' title='删除'></a>"+*/
						"<a href='javascript:void(0)' onclick='downUploadFile(\""+data[i].fileId+"\")'>下载</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='viewFile(\""+data[i].filePath+"\")'>查看</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='delFile(\""+data[i].fileId+"\")'>删除</a>"+
						"</td>" +
						"</tr>");
						  $("#selector"+i).val(data[i].fileType);
					}
				}
            	$("#dlgUpload").dialog("open");
            	
            	// 修改按钮样式
            	$(window.parent.document).find("div[id='tabs']").find("div:first").next().children().each(function(){
            		var styles= $(this).attr("style");
            		if(styles.indexOf("block") >= 0){
            			var documents = $($($(this).find("iframe"))[0].contentDocument).find("span[class='fileManage']");
            			$($($($(documents).next().children()[2]).children()[0]).children()[0]).attr("style", "margin-top: 0;");
            			$($($(documents).next().children()[0]).children()[0]).attr("style", "margin-top: 0;");
            		}
            	});
			}
		});
	}
	
	// 选择完文件后,触发事件
	function upload1(){
		$($($("#dlgUpload1").prev().children()[1]).find("a")).bind("click",function(){
			$($("input",$("#uploadFileid").next("span"))[1]).val("");
		});
		$('#dlgUpload1').dialog('open');
		//获取上传文件路径
		var fileUrl=$("#uploadFileid").filebox("getValue");
		//获取文件名
		var strFileName=fileUrl.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
		//获取文件后缀
		var FileExt=fileUrl.replace(/.+\./,"");   //正则表达式获取后缀
		$("#fileFullName").textbox("setValue",strFileName+"."+FileExt);
	}
	
	function delUploadFile(){
		$('#dlgUpload1').dialog('close');
		$($("input",$("#uploadFileid").next("span"))[1]).val("");
	}
	
	
	//上传文件
	function saveUploadFile(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var fileType=$("#fileType").val();//选择的类别
		var fileContent=$("#fileContent").val();//文件描述
		var fileFullName=$("#fileFullName").val()// 获取文件名称
		
		if(fileFullName!=""){
			$("#fmUpload").ajaxSubmit({
				type: "POST",
				url:"common/uploadFile?pathWay=opus/sr0&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
				dataType: "json",
			    success: function(data){
			    	if(data.status == 1){
			    		// 关闭附件信息弹框
			    		$('#dlgUpload1').dialog('close');
			    		var tableName=$("#tableName").val();
			    		var baseInfoId=$("#relevanceId").val();
			    		uploadList(tableName,baseInfoId);
	                   // $('#dg').datagrid('reload');
	                   /* $.messager.show({
	                        title: '提示',
	                    });*/
			    		$($("input",$("#uploadFileid").next("span"))[1]).val("");
	                } else {
	                	$.messager.show({
	                        title: '提示',
	                        msg: data.text
	                    });
	                }
				}
			});
		}
	}
	
	//下载文件
	function downUploadFile(fileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		window.open("common/downloadFile?fileId="+fileId + urls);
	}
	//删除文件
	function delFile(fileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		 $.messager.confirm('确认',"确认要删除吗?",function(r){
             if (r){
                 $.post('common/delFile?fileId='+fileId + urls,function(data){
                 	if(data.status == 1){
                         $('#dg').datagrid('reload');
                         var tableName=$("#tableName").val();
     		    		 var baseInfoId=$("#relevanceId").val();
     		    		 uploadList(tableName,baseInfoId);
                         $.messager.show({
                             title: '提示',
                             msg: data.text
                         });
                     } else {
                     	$.messager.show({
                             title: '提示',
                             msg: data.text
                         });
                     }
                 },'json');
             }
         });
	}
	//保存信息
    function saveUser(){
    	if($("#baseInfoName").prop('disabled')){
    		$("#baseInfoName").textbox("enable");
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
                   $('#dg').datagrid('reload');    // reload the user data
                   $('#dlg').dialog('close');        // close the dialog
                  
                 
               }
           }
       });
    }
    
    //当进入编辑页面时,需要手动赋值
	function textHtml(data){
		//著作名称
		$("#opusName").val(data.opusName);
		//著作类型（0.专著1.编著2.译著3.教材4.工具书5.普及行出版物）
 		$("#opusType").val(data.opusType);
 		//出版时间
 		$("#opusPublishTime").val(data.opusPublishTime);
 		//语种（0.中文1.外文）
 		$("#opusLanguages").val(data.opusLanguages);
 		//出版单位
 		$("#opusPublishCompany").val(data.opusPublishCompany);
 		//支持课题
 		$("#opusSupportTopic").val(data.opusSupportTopic);
 		//内容摘要
 		$("#opusContent").val(data.opusContent);
 		//备注
 		$("#opusRemarks").val(data.opusRemarks);
	}
    
    //点击新增和编辑按钮时，动态显示文本内容
	function pageDisplay(data){
		//append前清空合作者
		$('#collaborator').empty();
		//拼接页面(合作者)
		var collaborator=$("#collaborator").append("<div class='headCaption'>" +
				"<table width='100%'><tbody>" +
				"<tr>" +
				"<td><h2>合作者</h2></td>" +
				"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
				" data-options='plain:true' onclick='addCollaborator()'>添加</a></td>" +
				"</tr></tbody></table>" +
				"</div>"+
				"<div class='listBody'>" +
				"<table width='100%' border='0' id='collaboratorId'>" +
				"<thead>" +
				"<tr>" +
				"<th>姓名</th><th>主要工作</th><th>操作</th>" +
				"</tr>" +
				"</thead>" +
				"</table>" +
				"</div>");
		$.parser.parse(collaborator );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
    }
	
	
	//合作者====================================================================================================
	//合作者索引
	var collaboratorNum=0;
	//添加合作者
	function addCollaborator(){
		//判断是从新增页 还是从编辑页添加
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoPath==1){
			//获取已有条数
			var tbodys = $("#collaboratorId").find("tbody");
			collaboratorNum=tbodys.length;
		}
		var targetObj =$("#collaboratorId").append("<tbody id='tbody"+collaboratorNum+"'>" +
		"<tr class='item-row'>" +
		"<td>" +
		"<input style='border:1px solid #bdc7d8;height:22px'  type='text' name='amCollaborators[" + collaboratorNum + "].baseInfoName' id='baseInName"+collaboratorNum+"'  style='width:45px' onfocus='searchByType(0,1,this.id)'>"+
		"<input type='hidden' name='amCollaborators[" + collaboratorNum + "].baseInfoId' id='baseInId"+collaboratorNum+"'>"+
		"</td>"+
		"<td>" +
		"<input style='border:1px solid #bdc7d8;height:22px' type='text' name='amCollaborators[" + collaboratorNum + "].collaboratorContent' >"+
		"</td>"+
		"<td>"+
		"<a id='"+collaboratorNum+"' href='javascript:void(0)'  class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delCollaborator(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
		"</td>"+
		"</tr>" +
		"</tbody>");
		//渲染整个tbody(一行)
		$.parser.parse($("#tbody"+collaboratorNum));
		collaboratorNum++;
	}
	
	//编辑合作者
	function editCollaborator(data){
		for(var i=0;i<data.amCollaborators.length;i++){
			//将教育经历id放到form中
			var collaboratorIds=$("#collaboratorIds").val();
			if(collaboratorIds!=""){
				$("#collaboratorIds").val(collaboratorIds+","+data.amCollaborators[i].collaboratorId)
			}else{
				$("#collaboratorIds").val(data.amCollaborators[i].collaboratorId);
			}
			var targetObj =$("#collaboratorId").append("<tbody id='tbody"+i+"'>" +
					"<tr class='item-row'>" +
					"<td>" +
					"<input style='border:1px solid #bdc7d8;height:22px' type='text' name='amCollaborators[" + i + "].baseInfoName' value='"+data.amCollaborators[i].baseInfoName+"' id='baseInName"+i+"' onfocus='searchByType(0,1,this.id)'>"+
					"<input type='hidden' name='amCollaborators[" + i + "].baseInfoId' value='"+data.amCollaborators[i].baseInfoId+"' id='baseInId"+i+"'>"+
					"</td>"+
					"<td>" +
					"<input style='border:1px solid #bdc7d8;height:22px' type='text' name='amCollaborators[" + i + "].collaboratorContent' value='"+data.amCollaborators[i].collaboratorContent+"'>"+
					"</td>"+
					"<td>"+
					"<span style='display: block'><input hidden='text' type='text' name='amCollaborators[" + i + "].collaboratorId' value='"+data.amCollaborators[i].collaboratorId+"'></span>"+
					"<a href='javascript:void(0)' id='"+i+"' class='easyui-linkbutton' iconcls='icon-cancel' data-options='plain:true' onclick='delCollaborator(this.id)' style='border: 1px solid transparent;padding: 0;'></a>"+
					"</td>"+
					"</tr>" +
					"</tbody>");
					$.parser.parse(targetObj );
		}
	}
	
	//删除合作者
	function delCollaborator(obj){
		var tbody=$("#tbody"+obj);
		tbody.remove(); 
		//获取删除后的tbody的长度
		var tbodys = $("#collaboratorId").find("tbody");
		collaboratorNum=tbodys.length;
		//遍历tbodys
		for(var i=0;i<tbodys.length;i++) { 
			tbodys[i].id="tbody"+i;
			tbodys.eq(i).find("input")[0].name="amCollaborators[" + i + "].baseInfoName";
			tbodys.eq(i).find("input")[0].id="baseInName"+ i;
			tbodys.eq(i).find("input")[1].name="amCollaborators[" + i + "].baseInfoId";
			tbodys.eq(i).find("input")[1].id="baseInId"+ i;
			tbodys.eq(i).find("input")[2].name="amCollaborators[" + i + "].collaboratorContent"; 
			if(tbodys.eq(i).find("input")[3]!=undefined){
				tbodys.eq(i).find("input")[3].name="amCollaborators[" + i + "].collaboratorId"; 
			}
			tbodys.eq(i).find("a")[0].id=""+i+"";
		} 
	}
