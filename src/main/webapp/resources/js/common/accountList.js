	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#accountDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide(); 
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
	function queryAction(accountDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'account/accountList?accountDel='+accountDel+"&getName="+getName+"&getValue="+getValue + urls,
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'accountId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'accountName',
                title : '用户名',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'roleName',
                title : '用户角色',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
   				 if(value!=null && value!=''){
   				   if(value=='0'){return '管理员'} else if(value=='1'){return '领导'}
   				else if(value=='2'){return '行政'}else if(value=='3'){return '科研人员'}else if(value=='4'){return '其他人员'}
   				 } else{return '--'}
                }
            }, {
                field : 'accountStatus',
                title : '账号使用状态',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
   				 if(value!=null && value!=''){
   				   if(value=='0'){return '未使用'} else if(value=='1'){return '已使用'}
   				 } else{return '--'}
                }
            }, {
                field : 'baseInfoName',
                title : '账号绑定用户姓名',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
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
	
	//向角色中添加内容
	function addRole(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		$.ajax({
			url:"role/roleTypeList?" +urls,
			type:"post",
			success:function (data){
				if(data!=null && data.length>0){
					var themecombo=[];
					for(var i=0;i<data.length;i++){
						var title=""
						if(data[i].roleName==0){
							title="管理员";
						}else if(data[i].roleName==1){
							title="领导";
						}else if(data[i].roleName==2){
							title="行政";
						}else if(data[i].roleName==3){
							title="科研人员";
						}else{
							title="其他人员";
						}
						themecombo.push({"text":title,"id":data[i].roleId});
					}
					$("#roleName").combobox("loadData",themecombo);
				}
			}
		});
	}
	
	var num=0;
	var url;
	function add(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		addRole();
		num=0;
    	$("#dlg").dialog({  
    		title: "权限管理-账户管理-信息申报",
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
	   					$('#accountPath').val(0);
					}
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "account/saveOrUpAccount?" +  urls;
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		addRole();
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$.ajax({
				url:'account/accountView?accountId='+row.accountId   + urls,
				type:"post",
				success:function (data){
					if(data!=null){
						$('#fm').form('load', data); 
						if(data.accountPassWord!=null && data.accountPassWord!=""){
							$("#accountPassWord1").textbox("setValue",data.accountPassWord);
				        	// 记录原密码用户之后比较
				    		$("#accountPassWordOld").textbox("setValue",data.accountPassWord);
						}
						$("#dlg").dialog({  
		            		title: "权限管理-账户管理-信息编辑",
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
		            	//点击保存按钮后跳转路径
		            	url = "account/saveOrUpAccount?" + urls;
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
		var accountDel=$("#accountDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
		if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].accountId;  
                 } else {  
                     temID = selRow[i].accountId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(accountDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('account/deleteAccount?accountDel='+accountDel + urls,{accountId:temID},function(data){
                    	if(data.status == 1){
                    		if(data.count!=0){
                    			alert("有"+data.count+"个已使用的账号,已使用的账号不能删除!");
                    		}
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
		$("#accountDel").val(2);
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
                     temID = selRow[i].accountId;  
                 } else {  
                     temID = selRow[i].accountId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('account/deleteAccount?accountDel=0'  +urls,{accountId:temID},function(data){
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
		
		var url = "ReportServer?reportlet=account.cpt&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("账号管理列表");
		window.open(url);
}
	function getValueFunction(name, value){
		//著作类型
        if(name=="roleName"){
            if(value=="管理员"){
               value=0; 
            }else if(value=="领导"){
               value=1; 
            }else if(value=="行政人员"){
               value=2; 
            }else if(value=="科研人员"){
               value=3; 
            }else if(value=="其他人员"){
               value=4; 
            }else{
            	value=99; 
            }
        }else if(name=="accountStatus"){
        	if(value=="未使用"){
                value=0; 
             }else if(value=="已使用"){
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
		var tableName="common_account"
		$("#relevanceId").val(row.accountId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.accountId);
	}
	
	function uploadList(tableName,accountId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+accountId+"&tableName="+tableName  + urls,
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
						"<input type='hidden' value='"+data[i].fileId+"'>"+
						"<input type='text' value='"+data[i].fileName+"' style='border: 1px solid #BDC7D8;' readonly>"+
						"</td>" +
						"<td>" +
						"<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:65px;border: 1px solid #BDC7D8;' readonly>"+
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
						"<a href='javascript:void(0)' onclick='downUploadFile(\""+data[i].fileId+"\")'>下载</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='downUploadFile(\""+data[i].fileId+"\")'>查看</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='delFile(\""+data[i].fileId+"\")'>删除</a>"+
						"</td>" +
						"</tr>");
						  $("#selector"+i).val(data[i].fileType);
					}
				}
            	$("#dlgUpload").dialog("open");
            	
			}
		});
	}
	
	// 选择完文件后,触发事件
	function upload1(){
		$('#dlgUpload1').dialog('open');
		//获取上传文件路径
		var fileUrl=$("#uploadFileid").filebox("getValue");
		//获取文件名
		var strFileName=fileUrl.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
		//获取文件后缀
		var FileExt=fileUrl.replace(/.+\./,"");   //正则表达式获取后缀
		$("#fileFullName").val(strFileName+"."+FileExt);
	}
	
	//上传文件
	function saveUploadFile(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var fileType=$("#fileType").val();//选择的类别
		var fileContent=$("#fileContent").val();//文件描述
		var fileFullName=$("#fileFullName").val()// 获取文件名称
		$("#fmUpload").ajaxSubmit({
			type: "POST",
			url:"common/uploadFile?pathWay=account/sr0&fileFullName="+fileFullName+"&tableName=common_account&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent  + "&" + urls,
			dataType: "json",
		    success: function(data){
		    	if(data.status == 1){
		    		// 关闭附件信息弹框
		    		$('#dlgUpload1').dialog('close');
		    		var tableName=$("#tableName").val();
		    		var relevanceId=$("#relevanceId").val();
		    		uploadList(tableName,relevanceId);
                } else {
                	$.messager.show({
                        title: '提示',
                        msg: data.text
                    });
                }
			}
		});
	}
	
	//下载文件
	function downUploadFile(fileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		window.open("common/downloadFile?fileId="+fileId  +urls);
	}
	//删除文件
	function delFile(fileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		 $.messager.confirm('确认',"确认要删除吗?",function(r){
             if (r){
                 $.post('common/delFile?fileId='+fileId   +urls,function(data){
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
    function saveAccount(){
       // 将登录密码md5加密
       var accountPassWord=$("#accountPassWord1").val();
       // 获取原密码
       var accountPassWordOld=$("#accountPassWordOld").val();
       if(accountPassWord!="" && accountPassWord!=accountPassWordOld){
    	   $("#accountPassWordMd5").textbox("setValue", $.md5(accountPassWord));
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
