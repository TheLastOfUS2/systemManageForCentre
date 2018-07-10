	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#fundDel").val(1);
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
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	//调用后台方法
	function queryAction(fundDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'fund/fundList?fundDel='+fundDel+"&getName="+getName+"&getValue="+getValue + urls,
			title:"资助经费列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'fundId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'fundTitle',
                title : '经费名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'fundAmounts',
                title : '金额（万）',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'fundTime',
                title : '划拨时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            }, {
                field : 'fundSource',
                title : '经费来源',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
      				 if(value!=null && value!=''){
      				   if(value=='0'){return '教育部'} else if(value=='1'){return '其他部委'}
      				else if(value=='2'){return '省市政府'}else if(value=='3'){return '学校'}
      				else if(value=='4'){return '企事业单位'}else if(value=='5'){return '社会捐助'}
      				 } else{return '--'}
                   }
            },{
                field : 'fundType',
                title : '经费类别',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
     				 if(value!=null && value!=''){
     				   if(value=='0'){return '设备'} else if(value=='1'){return '会议'}
     				else if(value=='2'){return '图书'}else if(value=='3'){return '项目'}
     				else if(value=='4'){return '办公'}else if(value=='5'){return '其他'}
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
	
	var url;
	function add(){
		//根据登录账号id查询角色,如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//$('#fm').form('load', 'opus/opusView'); 
    	$("#dlg").dialog({  
    		title: "管理工作-资助经费-信息申报",
				iconCls : "icon-add",
				collapsible: true,
            /*   minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					//加载对应通讯信息
					pageDisplay();
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "fund/saveOrUpFund?" + urls;
	}
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#fm').form('load', 'fund/fundView?path=editFund&fundId='+row.fundId + urls); 
        	$("#dlg").dialog({  
        		title: "管理工作-资助经费-信息编辑",
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
					$('#fundId').val(row.fundId);
   					//加载对应通讯信息
   					pageDisplay();
   				},
        	});
        	$("#dlg").dialog("open");
        	// 弹框居中
        	dlgCenter();
        	//点击保存按钮后跳转路径
        	url = "fund/saveOrUpFund?" + urls;
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var fundDel=$("#fundDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].fundId;  
                 } else {  
                     temID = selRow[i].fundId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(fundDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('fund/deleteFund?fundDel='+fundDel + urls,{fundId:temID},function(data){
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
		$("#fundDel").val(2);
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
                     temID = selRow[i].fundId;  
                 } else {  
                     temID = selRow[i].fundId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('fund/deleteFund?fundDel=0' + urls,{fundId:temID},function(data){
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
		var url = "ReportServer?reportlet=fund.cpt&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("资助经费");
		window.open(url);
	}
	function getValueFunction(name, value){
		//经费类别
        if(name=="fundType"){
            if(value=="设备"){
               value=0; 
            }else if(value=="会议"){
               value=1;
            }else if(value=="图书"){
               value=2;
            }else if(value=="项目"){
               value=3;
            }else if(value=="办公"){
               value=4;
            }else if(value=="其他"){
               value=5;
            }else{
               value=99;
            }
        }
        //经费来源
        if(name=="fundSource"){
            if(value=="教育部"){
               value=0; 
            }else if(value=="其他部委"){
               value=1;
            }else if(value=="省市政府"){
               value=2;
            }else if(value=="学校"){
               value=3;
            }else if(value=="企事业单位"){
               value=4;
            }else if(value=="社会捐助"){
               value=5;
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
		var tableName="mw_fund"
		$("#relevanceId").val(row.fundId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.fundId);
	}
	
	function uploadList(tableName,fundId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+fundId+"&tableName="+tableName + urls,
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
						"<option value='0'>其他</option>" +
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
				url:"common/uploadFile?pathWay=fund/sr0&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
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
	function pageDisplay(){
		//append前清空通讯信息数据
		$('#Staff').empty();
		var mailList =$("#Staff").append("<table id='Staff1' class='StaffInfo'>" +
				"<tbody>"+
				"<tr>"+
				"<td width='120' style='text-align:right;'><label class='in_label'>经费名称:</label></td>" +
				"<td width='180' colspan='3'>" +
				"<input type='text' id='fundTitle' name='fundTitle' class='easyui-textbox' style='width:448px'></td>" +
				"</tr>"+
				"<tr>"+
				"<td width='120' style='text-align:right;'><label class='in_label'>金额(万):</label></td>" +
				"<td width='180'>" +
				"<input class='easyui-numberspinner' id='fundAmounts' name='fundAmounts' style='width:139px' precision='2' data-options='min:0.00,max:10000'/></td>" +
				"<td width='120' style='text-align:right;'><label class='in_label'>划拨时间:</label></td>" +
				"<td width='180'>" +
				"<input type='text' id='fundTime' name='fundTime' class='easyui-datebox'></td>" +
				"</tr>"+
				"<tr>" +
				"<td width='120' style='text-align:right;'><label class='in_label'>经费类别:</label></td>" +
				"<td width='180'>" +
				"<select id='fundType' class='easyui-combobox' name='fundType' panelMaxHeight='100px'>" +
	         	"<option value=''>请选择</option><option value='0'>设备</option><option value='1'>会议</option><option value='2'>图书</option>" +
	         	"<option value='3'>项目</option><option value='4'>办公</option><option value='5'>其他</option></select></td>" +
	         	"<td width='120' style='text-align:right;'><label class='in_label'>经费来源:</label></td>" +
				"<td width='180'>" +
				"<select id='fundSource' class='easyui-combobox' name='fundSource' panelMaxHeight='100px'>" +
	         	"<option value=''>请选择</option><option value='0'>教育部</option><option value='1'>其他部委</option><option value='2'>省市政府</option>" +
	         	"<option value='3'>学校</option><option value='4'>企事业单位</option><option value='5'>社会捐助</option></select></td>" +
	         	"</tr>"+
				"<tr>" +
				"<td width='120' style='text-align:right;'><label class='in_label'>备注:</label></td>" +
	         	"<td width='180' colspan='3'>" +
	         	"<input type='text' data-options='multiline:true' id='fundRemarks' name='fundRemarks'" +
	         	"class='easyui-textbox' style='width:448px;height: 50px'></td>" +
	         	"</tr>"+
				"</tbody>");
		$.parser.parse(mailList );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
    }
