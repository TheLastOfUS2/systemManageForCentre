	var p=0;
	var urls = "";
	$(document).ready(function() { 
		// 当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		
		// 当手动填写姓名时,清空id
		$("input",$("#conferenceName").next("span")).keydown(function(){
			$("#conferenceNameId").val("");
		});
		
		// 当手动填写主席时,清空id
		$("input",$("#conferenceImplementChairman").next("span")).keydown(function(){
			$("#conferenceImplementChairmanId").val("");
		});
		
		
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#conferenceDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide();  
		
		$("#conferenceNature").combobox({
			 stopFirstChangeEvent: true, 
			 onChange:function(n,o){
				var typeValue= $('#conferenceNature').combobox('getValue');// 获取当前选中的值
				// 根据当前选中的类型显示对应内容
				pageDisplay(typeValue);
	    	}
    	});
		
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
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	
	//调用后台方法
	function queryAction(conferenceDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		// 根据用户角色和和用户账号查询列表
		var baseInfoId=$("#baseInfoId").val();
		var role=$("#role").val();
		var queryParam="";
		if(role==3 || role==4){
			queryParam="&baseInfoId="+baseInfoId;
		}
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'conference/conferenceList?conferenceDel='+conferenceDel+"&getName="+getName+"&getValue="+getValue+queryParam + urls,
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'conferenceId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'conferenceTitle',
                title : '会议名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'conferenceNature',
                title : '会议性质',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
	  				 if(value!=null && value!=''){
	  				   if(value=='0'){return '自办'} else if(value=='1'){return '参会'}
	  				 } else{return '--'}
               }
            },{
                field : 'conferenceChairman',
                title : '主席',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
                   	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'conferenceImplementChairman',
                title : '执行主席',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
                   	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'conferenceName',
                title : '姓名',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
                   	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'conferenceAddress',
                title : '举办地点',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'conferenceStartTime',
                title : '会议开始时间',
                width : 80,
                sortable :true,
                formatter : function(value){
            		if(value!=null && value!=''){
            		var date = new Date(value);
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + '-' +m + '-' + d;
            		}else{return '--'}
                }
            }, {
                field : 'conferenceEndTime',
                title : '会议结束时间',
                width : 80,
                sortable :true,
                formatter : function(value){
            		if(value!=null && value!=''){
            		var date = new Date(value);
                    var y = date.getFullYear();
                    var m = date.getMonth() + 1;
                    var d = date.getDate();
                    return y + '-' +m + '-' + d;
            		}else{return '--'}
                }
            }, {
                field : 'conferenceType',
                title : '会议类型',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
	  				 if(value!=null && value!=''){
	  				   if(value=='0'){return '国内'} else if(value=='1'){return '国际'}
	  				 } else{return '--'}
              }
            }] ],
            //加载后设置
			onLoadSuccess:function(data){
				// 根据角色判断，如果是领导或者行政人员，判断其是否已添加过基本信息，如果已添加过基本信息，页面新增按钮不让点击
				var role=$("#role").val();
				if(role==3 || role==4){
					// 列表看不到主席和执行主席
					$("#dg").datagrid("hideColumn", "conferenceChairman"); // 主席
					$("#dg").datagrid("hideColumn", "conferenceImplementChairman"); // 执行主席
				}
            },
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
	
	var url;
	function add(){
		//根据登录账号id查询角色,如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//$('#fm').form('load', 'paper/paperView'); 
    	$("#dlg").dialog({  
    		title: "学术交流-学术会议-信息申报",
				iconCls : "icon-add",
				collapsible: true,
             /*  minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
					$('#tbody1').remove();
				    win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
	   				pageDisplay(0);
	   				//负责人赋值
					setBaseInfoName();
					// 如果是科研人员和其他人员，看不到自办会议
					var role=$("#role").val();
					if(role==3 || role==4){
						 $('#conferenceNature').combobox('select', 1);
						 $('#conferenceNature').combobox('disable');
					}
				}
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "conference/saveOrUpConference?" + urls;
	}
	
	function setBaseInfoName(){
		var baseInfoId=$("#baseInfoId").val();
		var baseInfoName=$("#baseInfoName2").val();
		var role=$("#role").val();
		
		if(baseInfoId!="" && baseInfoName!="" && (role=="3" || role=="4")){
			$("#conferenceName").textbox('setValue',baseInfoName);
			$("#conferenceNameId").val(baseInfoId);
			$("#conferenceName").textbox("disable");
			//隐藏选择按钮
			$("#a_conferenceName").hide();
			
		}
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	num=1;
        	$.ajax({
				url:"conference/conferenceView?path=editConference&conferenceId="+row.conferenceId + urls,
				type:"post",
				success:function (data){
					if(data!=null){
						$('#fm').form('load', data); 
						$("#dlg").dialog({  
							title: "学术交流-学术会议-信息编辑",
		   					iconCls : "icon-edit",
		   					collapsible: true,
		   	              /*  minimizable: true,
		   	                maximizable: true,*/
		   	                resizable: true,
		   					onClose: function(){
		   						$('#tbody1').remove();
		 					   win.window('destroy');//关闭即销毁
		   					},
							onOpen:function(){
								//将列表中的类型放到新增信息form中
								$('#conferenceId').val(row.conferenceId);
			   					//加载对应通讯信息
			   					pageDisplay(row.conferenceNature);
			   					//负责人赋值
								setBaseInfoName();
			   				},
		            	});
		            	$("#dlg").dialog("open");
		            	// 弹框居中
		            	dlgCenter();
		            	//点击保存按钮后跳转路径;collaboratorType:著作
		            	url = "conference/saveOrUpConference?" + urls;
					}
				}
			});
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	function del(){
		//获取是在未被删除数据的列表还是已被删除数据的列表
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var conferenceDel=$("#conferenceDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].conferenceId;  
                 } else {  
                     temID = selRow[i].conferenceId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(conferenceDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('conference/deleteConference?conferenceDel='+conferenceDel + urls,{conferenceId:temID},function(data){
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
		$("#conferenceDel").val(2);
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
                     temID = selRow[i].conferenceId;  
                 } else {  
                     temID = selRow[i].conferenceId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('conference/deleteConference?conferenceDel=0' + urls,{conferenceId:temID},function(data){
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
		var url = "ReportServer?reportlet=conference.cpt&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("学术会议");
		window.open(url);
	}
	function getValueFunction(name, value){
		//会议性质
        if(name=="conferenceNature"){
            if(value=="自办"){
               value=0; 
            }else if(value=="参会"){
               value=1;
            }else{
               value=99;
            }
        }
        //会议类型（0.国内1.国际）
        if(name=="conferenceType"){
            if(value=="国内"){
               value=0; 
            }else if(value=="国际"){
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
		var tableName="am_conference"
		$("#relevanceId").val(row.conferenceId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.conferenceId);
	}
	
	function uploadList(tableName,conferenceId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取类型
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+conferenceId+"&tableName="+tableName + urls,
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
						"<option value='0'>会议邀请函</option>" +
						"<option value='1'>论文集封面及目录页</option>" +
						"<option value='2'>通讯录</option>" +
						"<option value='3'>音像资料</option>" +
						"<option value='4'>其他</option>" +
						"</select>"+
						"</td>" +
						"<td>" +
						"<input type='text' value='"+data[i].fileContent+"'style='border: 1px solid #BDC7D8;' readonly>"+
						"</td>" +
						"<td>" +
						""+t+""+
						"</td>" +
						"<td width='30px'>" +
					/*	"<a href='javascript:downFile("+data[i].fileId+");' title='下载此附件'>" +
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
				url:"common/uploadFile?pathWay=conference/sr0&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
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
    	if($("#conferenceName").prop('disabled')){
    		$("#conferenceName").textbox("enable");
    	}
    	if($("#conferenceNature").prop('disabled')){
    		$("#conferenceNature").textbox("enable");
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
    
    //点击新增和编辑按钮时，动态显示文本内容
	function pageDisplay(typeValue){
		//首次进来时,设置select默认选中值
		$("#conferenceNature").combobox('setValue',typeValue);
		if(typeValue==0){
			// 自办
			$("#conferenceContentId").hide();//主要活动内容
			$("#conferencePaperNameId").hide();//交流论文名称
			$("#conferenceIfReadId1").hide();//是否大会宣读
			$("#conferenceIfReadId2").hide();//是否大会宣读
			$("#conferenceIfIncludeId").hide();//是否收录会议论文集
			$("#conferenceSynopsisId").text("会议综述或简介");// 会议简介
			
			$("#conferenceChairman1").show();//主席
			$("#conferenceChairman2").show();//主席
			$("#conferenceImplementChairman1").show();//执行主席
			$("#conferenceInternationCountId").show();//国际参会人员
		}else if(typeValue==1){
			// 参会
			$("#conferenceChairman1").hide();//主席
			$("#conferenceChairman2").hide();//主席
			$("#conferenceImplementChairman1").hide();//执行主席
			$("#conferenceInternationCountId").hide();//国际参会人员
			$("#conferenceSynopsisId").text("会议简介(描述或新闻网址)");// 会议简介
			
			$("#conferenceContentId").show();//主要活动内容
			$("#conferencePaperNameId").show();//交流论文名称
			$("#conferenceIfReadId1").show();//是否大会宣读
			$("#conferenceIfReadId2").show();//是否大会宣读
			$("#conferenceIfIncludeId").show();//是否收录会议论文集
		}
    }
	
