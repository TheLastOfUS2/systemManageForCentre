	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#shareFileDel").val(1);
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
	
	function ss(date) {  
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours();  
	    var minute = date.getMinutes();  
	    var second = date.getSeconds();  
	    minute = minute < 10 ? ('0' + minute) : minute;  
	    second = second < 10 ? ('0' + second) : second;  
	    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
	}; 
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	
	var showTitle="";
	function showTitleType(shareFileType){
		if(shareFileType==0){
			showTitle="工作论文"
		}else if(shareFileType==1){
			showTitle="研讨会讲稿"
		}else if(shareFileType==2){
			showTitle="参考文献"
		}else if(shareFileType==3){
			showTitle="会议通讯录"
		}else if(shareFileType==4){
			showTitle="其他"
		}
	}
	
	//调用后台方法
	function queryAction(shareFileDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取信息类型
		var shareFileType=$("#shareFileType1").val();
		showTitleType(shareFileType);
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'shareFile/shareFileList?shareFileType='+shareFileType+'&shareFileDel='+shareFileDel+"&getName="+getName+"&getValue="+getValue + urls,
			title: showTitle+"列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'shareFileId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'shareFileName',
                title : '文件名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'shareFileSubmitter',
                title : '提交人',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'shareFileCreateTime',
                title : '上传时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
                 		var d=new Date(value); 
      				    return ss(d);
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
    	$("#dlg").dialog({  
    		title: "共享文件-"+showTitle+"-信息申报",
				iconCls : "icon-add",
				collapsible: true,
            /*   minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					if(num==0){//防止点击编辑按钮后,再走一遍onOpen
						//将列表中的类型放到新增信息form中
	   					$('#shareFilePath').val(0);
	   					var shareFileType=$("#shareFileType1").val();
	   					$('#shareFileType').val(shareFileType);
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
    	url = "shareFile/saveOrUpShareFile?" + urls;
	}
	
	//负责人赋值
	function setBaseInfoName(){
		var baseInfoId=$("#baseInfoId").val();
		var baseInfoName=$("#baseInfoName").val();
		if(baseInfoId!="" && baseInfoName!=""){
			$("#shareFileSubmitter1").textbox('setValue',baseInfoName);
			$("#shareFileSubmitterId").val(baseInfoId);
			$("#shareFileSubmitter1").textbox("disable");
		}
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	num=1;
        	$.ajax({
				url:"shareFile/shareFileView?shareFileId="+row.shareFileId + urls,
				type:"post",
				success:function (data){
					if(data!=null){
						$('#fm').form('load', data); 
						//将列表中的类型放到新增信息form中
						$('#shareFileId').val(row.shareFileId);
						$('#shareFilePath').val(1);
						
						//负责人赋值
						setBaseInfoName();
						
						$("#dlg").dialog({  
							title: "共享文件-"+showTitle+"-信息编辑",
		   					iconCls : "icon-edit",
		   					collapsible: true,
		   	               /* minimizable: true,
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
		            	url = "shareFile/saveOrUpShareFile?" + urls;
					}
				}
			});
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var shareFileDel=$("#shareFileDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
		if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].shareFileId;  
                 } else {  
                     temID = selRow[i].shareFileId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(shareFileDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('shareFile/deleteShareFile?shareFileDel='+shareFileDel + urls,{shareFileId:temID},function(data){
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
		$("#shareFileDel").val(2);
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
                     temID = selRow[i].shareFileId;  
                 } else {  
                     temID = selRow[i].shareFileId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('shareFile/deleteShareFile?shareFileDel=0' + urls,{shareFileId:temID},function(data){
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
		//类型
		var shareFileType = $("#shareFileType1").val();
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		
		// 工作论文
		if(shareFileType==0){//&extype=simple  原样导出excel 不分页
			var url = "ReportServer?reportlet=shareFile0.cpt&shareFileType=" + shareFileType + "&" + getName + "=" + getValue +"&format=excel&extype=simple&__filename__=" + cjkEncode("工作论文共享文件");
		// 研讨会讲稿
		}else if(shareFileType==1){
			var url = "ReportServer?reportlet=shareFile1.cpt&shareFileType=" + shareFileType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("研讨会讲稿共享文件");
		// 参考文献
		}else if(shareFileType==2){
			var url = "ReportServer?reportlet=shareFile2.cpt&shareFileType=" + shareFileType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("参考文献共享文件");
		// 会议通讯录
		}else if(shareFileType==3){
			var url = "ReportServer?reportlet=shareFile3.cpt&shareFileType=" + shareFileType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("会议通讯录共享文件");
		// 其他
		}else {
			var url = "ReportServer?reportlet=shareFile4.cpt&shareFileType=" + shareFileType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("其他共享文件");
		}
		window.open(url);
}
	
	// 上传材料
	function upload(){
		//获取选择上传数据id
		var row = $('#dg').datagrid('getSelected');
		//表名
		var tableName="sf_share_file"
		$("#relevanceId").val(row.shareFileId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.shareFileId);
	}
	
	function uploadList(tableName,shareFileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+shareFileId+"&tableName="+tableName + urls,
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
		//$("#fileFullName").val(strFileName+"."+FileExt);
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
				url:"common/uploadFile?pathWay=shareFile/sr0&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
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
    	if($("#shareFileSubmitter1").prop('disabled')){
    		$("#shareFileSubmitter1").textbox("enable");
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
