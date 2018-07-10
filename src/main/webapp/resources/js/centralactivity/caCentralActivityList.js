	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#activityDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide();
		showOrHide();
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
	//根据类型,显示隐藏查询条件
	function showOrHide(){
		//获取信息类型
		var activityType=$("#activityType1").val();
		if(activityType==0 || activityType==1){ 
			$("#hId2").hide(); // 组织者
		}
		if(activityType!=0 && activityType!=1){ 
			$("#hId1").hide(); // 主持人
			$("#sId").hide(); // 主讲人
		}
	}
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	
	var showTitle="";
	function showTitleType(activityType){
		if(activityType==0){
			showTitle="科研"
		}else if(activityType==1){
			showTitle="工会"
		}else if(activityType==2){
			showTitle="党员"
		}else if(activityType==3){
			showTitle="其他"
		}
		
	}
	
	//调用后台方法
	function queryAction(activityDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var activityType=$("#activityType1").val();
		showTitleType(activityType);
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'centralActivity/centralActivityList?activityType='+activityType+"&activityDel="+activityDel+"&getName="+getName+"&getValue="+getValue + urls,
			title: showTitle+"列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'consultId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'activityTheme',
                title : '主题',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'activityTime',
                title : '时间',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'activityAddress',
                title : '地点',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            },{
                field : 'activitySpeaker',
                title : '主讲人',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'activityHost',
                title : '主持人',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }] ],
            //加载后设置
			onLoadSuccess:function(data){
					if($("#activityType1").val()==2 || $("#activityType1").val()==3){
						//更改列名(将主讲人更改为组织者)
						var option = $("#dg").datagrid("getColumnOption", "activitySpeaker");
						if(option.title!="组织者"){
							option.title = "组织者";
							$('#dg').datagrid();
						}
						//将主持人隐藏
						$("#dg").datagrid("hideColumn", "activityHost");
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
		
		//$('#fm').form('load', 'opus/opusView'); 
    	$("#dlg").dialog({  
    		title: "中心各项活动-"+showTitle+"-信息申报",
				iconCls : "icon-add",
				collapsible: true,
              /* minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					var activityType=$("#activityType1").val();
					$("#activityType").val($("#activityType1").val());
					//加载对应通讯信息
					pageDisplay(activityType);
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "centralActivity/saveOrUpCentralActivity?" + urls;
	}
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#fm').form('load', 'centralActivity/centralActivityView?path=editCentralActivity&activityId='+row.activityId + urls); 
        	$("#dlg").dialog({  
        		title: "中心各项活动-"+showTitle+"-信息编辑",
					iconCls : "icon-edit",
					collapsible: true,
	               /* minimizable: true,
	                maximizable: true,*/
	                resizable: true,
					onClose: function(){
					   win.window('destroy');//关闭即销毁
					},
					onOpen:function(){
   					//将列表中的类型放到新增信息form中
					$('activityId').val(row.activityId);
					var activityType=$("#activityType1").val();
   					$("#activityType").val($("#activityType1").val());
   					//加载对应通讯信息
   					pageDisplay(activityType);
   				},
        	});
        	$("#dlg").dialog("open");
        	// 弹框居中
        	dlgCenter();
        	//点击保存按钮后跳转路径
        	url = "centralActivity/saveOrUpCentralActivity?" + urls;
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var activityDel=$("#activityDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].activityId;  
                 } else {  
                     temID = selRow[i].activityId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(activityDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('centralActivity/deleteCentralActivity?activityDel='+activityDel + urls,{activityId:temID},function(data){
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
		$("#activityDel").val(2);
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
                     temID = selRow[i].activityId;  
                 } else {  
                     temID = selRow[i].activityId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('centralActivity/deleteCentralActivity?activityDel=0' + urls,{activityId:temID},function(data){
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
		var activityType=$("#activityType1").val();
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		if(activityType==0){
			var url = "ReportServer?reportlet=activityType0.cpt&activityType=" + activityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("科研各项活动");
		}else if(activityType==1){
			var url = "ReportServer?reportlet=activityType1.cpt&activityType=" + activityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("工会各项活动");
		}else if(activityType==2){
			var url = "ReportServer?reportlet=activityType2.cpt&activityType=" + activityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("党员各项活动");
		}else{
			var url = "ReportServer?reportlet=activityType3.cpt&activityType=" + activityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("其他各项活动");
		}
		window.open(url);
	}
	/*//基本信息列表==操作功能
	var toolbar = [{
		text:'新增',
		iconCls:'icon-add',
		handler:function(){
				//根据登录账号id查询角色,如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
				//$('#fm').form('load', 'opus/opusView'); 
            	$("#dlg").dialog({  
            		title: "基本信息-科研人员-通讯录",
   					iconCls : "icon-add",
   					collapsible: true,
   	                minimizable: true,
   	                maximizable: true,
   	                resizable: true,
   					width: 795,
   					height:410, 
   					top: 2,
   					left: 141,
   					onClose: function(){
   					   win.window('destroy');//关闭即销毁
   					},
	   				onOpen:function(){
	   					var activityType=$("#activityType1").val();
	   					$("#activityType").val($("#activityType1").val());
	   					//加载对应通讯信息
	   					pageDisplay(activityType);
	   				},
            	});
            	$('#fm').form('clear');
            	$("#dlg").dialog("open");
            	//点击保存按钮后跳转路径
            	url = "centralActivity/saveOrUpCentralActivity";
		}
	} ,{
		text:'编辑',
		iconCls:'icon-edit',
		handler:function(){
			var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#fm').form('load', 'centralActivity/centralActivityView?path=editCentralActivity&activityId='+row.activityId); 
            	$("#dlg").dialog({  
            		title: "基本信息-科研人员-信息申报",
   					iconCls : "icon-edit",
   					collapsible: true,
   	                minimizable: true,
   	                maximizable: true,
   	                resizable: true,
   					width: 795,
   					height:410, 
   					top: 2,
   					//modal:true,遮罩层
   					left: 141,
   					onClose: function(){
   					   win.window('destroy');//关闭即销毁
   					},
   					onOpen:function(){
	   					//将列表中的类型放到新增信息form中
						$('activityId').val(row.activityId);
						var activityType=$("#activityType1").val();
	   					$("#activityType").val($("#activityType1").val());
	   					//加载对应通讯信息
	   					pageDisplay(activityType);
	   				},
            	});
            	$("#dlg").dialog("open");
            	//点击保存按钮后跳转路径
            	url = "centralActivity/saveOrUpCentralActivity";
            }else{ 
	            $.messager.alert('提示信息','未选中任何记录!','info'); 
            }
		}
	},'-',{
		text:'删除',
		iconCls:'icon-cancel',
		handler:function(){
			//获取是在未被删除数据的列表还是已被删除数据的列表
			var activityDel=$("#activityDel").val();
			//返回选中多行
			var selRow = $('#dg').datagrid('getSelections');
            if (selRow!=""){
            	 var temID="";  
                 //批量获取选中行的评估模板ID  
                 for (i = 0; i < selRow.length;i++) {  
                     if (temID =="") {  
                         temID = selRow[i].activityId;  
                     } else {  
                         temID = selRow[i].activityId + "," + temID;  
                     }                 
                 }
                $.messager.confirm('确认','确定要删除吗?',function(r){
                    if (r){
                        $.post('centralActivity/deleteCentralActivity?activityDel='+activityDel,{activityId:temID},function(data){
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
	},{
		text:'回收站',
		iconCls:'icon-trash',
		handler:function(){
			//查询已删除数据列表
			queryAction(1);
			//彻底删除数据
			$("#activityDel").val(2);
			//显示恢复按钮
			$("div.datagrid-toolbar [id ='recovery']").eq(0).show();  
		}
	},{
		id : 'recovery',  
		text:'恢复',
		iconCls:'icon-undo',
		handler:function(){
			//将选中的数据恢复成未删除状态
			//返回选中多行
			var selRow = $('#dg').datagrid('getSelections');
            if (selRow!=""){
            	 var temID="";  
                 //批量获取选中行的评估模板ID  
                 for (i = 0; i < selRow.length;i++) {  
                     if (temID =="") {  
                         temID = selRow[i].activityId;  
                     } else {  
                         temID = selRow[i].activityId + "," + temID;  
                     }                 
                 }
                $.messager.confirm('确认','确定要恢复吗?',function(r){
                    if (r){
                        $.post('centralActivity/deleteCentralActivity?activityDel=0',{activityId:temID},function(data){
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
	},'-',{//'_'是页面操作功能之间显示的'|'
		text:'导出',
		iconCls:'icon-xls',
		handler:function(){alert('save')}
	},'-',{
		text:'导出',
		iconCls:'icon-xls',
		handler:function(){alert('save')}
	}];*/
	
	// 上传材料
	function upload(){
		//获取选择上传数据id
		var row = $('#dg').datagrid('getSelected');
		//表名
		var tableName="bi_base_info"
		$("#relevanceId").val(row.activityId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.activityId);
	}
	
	function uploadList(tableName,activityId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取信息类型
		var activityType=$("#activityType1").val();
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+activityId+"&tableName="+tableName + urls,
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
						"<td id ='typelist0"+i+"' style='display:none'>" +
						"<select id='selector"+i+"'  value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist1"+i+"' style='display:none'>" +
						"<select id='selector"+i+"'  value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist2"+i+"' style='display:none'>" +
						"<select id='selector"+i+"'  value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>存档终稿</option>" +
						"<option value='1'>下发通知原文</option>" +
						"<option value='2'>相关参考资料</option>" +
						"<option value='3'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist3"+i+"' style='display:none'>" +
						"<select id='selector"+i+"'  value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>其他</option>" +
						"</select>"+
						"</td>"+
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
						$("#typelist"+activityType+i).show();
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
		var activityType=$("#activityType1").val();
		$("#typeActivity"+activityType).show();
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
		var activityType=$("#activityType1").val();//活动类型
		
		if(fileFullName!=""){
			$("#fmUpload").ajaxSubmit({
				type: "POST",
				url:"common/uploadFile?pathWay=centralActivity/sr"+activityType+"&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
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
	function pageDisplay(activityType){
		if(activityType==2 || activityType==3){
			//删除主持人
			$("#activityHostTr").remove();
			//将主讲人变成组织者
			$("#activitySpeakerId").text("组织者:");
		}
    }
