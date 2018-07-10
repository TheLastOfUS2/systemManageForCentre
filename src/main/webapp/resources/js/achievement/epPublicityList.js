	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		
		//获取信息类型
		var publicityType=$("#publicityType1").val();
		pageDisplay(publicityType);
		
		// 当手动填写受访人时,清空id
		$("input",$("#publicityInterviewee").next("span")).keydown(function(){
			$("#publicityIntervieweeId").val("");
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#publicityDel").val(1);
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
	
	//点击新增和编辑按钮时，动态显示文本内容
	function pageDisplay(publicityType){
		if(publicityType==0){
			//报纸
			$("#Staff1").show();
			$("#Staff2").empty();
			$("#Staff3").empty();
			$("#Staff4").empty();
		}else if(publicityType==1){
			//期刊
			$("#Staff2").show();
			$("#Staff1").empty();
			$("#Staff3").empty();
			$("#Staff4").empty();
		}else if(publicityType==2){
			//电视台
			$("#Staff3").show();
			$("#Staff1").empty();
			$("#Staff2").empty();
			$("#Staff4").empty();
		}else if(publicityType==3){
			//网络
			$("#Staff4").show();
			$("#Staff1").empty();
			$("#Staff2").empty();
			$("#Staff3").empty();
		}
    }
	
	//根据类型,显示隐藏查询条件
	function showOrHide(){
		//获取信息类型
		var publicityType=$("#publicityType1").val();
		if(publicityType!=0){ 
			//报纸
			$("#tId1").hide(); // 报纸名称
			$("#iId1").hide(); // 受访人
			$("#cId1").hide(); // 受访单位
			$("#tiId1").hide(); // 发表时间
			$("#pId1").hide(); // 发表时间
		}
		if(publicityType!=1){ 
			//期刊
			$("#tId2").hide(); // 期刊名称
			$("#vId").hide(); // 卷/期
			$("#tiId2").hide(); // 发表时间
			$("#pId2").hide(); // 位置
			$("#iId3").hide(); // 被宣传人
		}
		if(publicityType!=2){ 
			//电视台
			$("#cId").hide(); // 电视频道名称
			$("#tId3").hide(); // 栏目名称
			$("#pId3").hide(); // 视频网址
			$("#iId2").hide(); // 受访人
			$("#tiId3").hide(); // 时间
		}
		if(publicityType!=3){ 
			//网络
			$("#tId4").hide(); // 网络名称
			$("#pId4").hide(); // 网址
			$("#tiId4").hide(); // 发表时间
			$("#iId4").hide(); // 被宣传人
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
	function showTitleType(publicityType){
		if(publicityType==0){
			showTitle="报纸"
		}else if(publicityType==1){
			showTitle="期刊"
		}else if(publicityType==2){
			showTitle="电视台"
		}else if(publicityType==3){
			showTitle="网络"
		}
	}
	//调用后台方法
	function queryAction(publicityDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var publicityType=$("#publicityType1").val();
		showTitleType(publicityType);
		// 根据用户角色和和用户账号查询列表
		var baseInfoId=$("#baseInfoId").val();
		var role=$("#role").val();
		var queryParam="";
		if(role==3 || role==4){
			queryParam="&baseInfoId="+baseInfoId;
		}
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'publicity/publicityList?publicityType='+publicityType+"&publicityDel="+publicityDel+"&getName="+getName+"&getValue="+getValue+queryParam + urls,
			title: showTitle+"列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'publicityId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'publicityTheme',
                title : '宣传主题',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityChannel',
                title : '电视频道名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityTitle',
                title : '报纸名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityInterviewee',
                title : '受访人',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityCompany',
                title : '受访单位',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityVolume',
                title : '卷/期',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            },{
                field : 'publicityPosition',
                title : '版面',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'publicityTime',
                title : '发表时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            }] ],
            //加载后设置
			onLoadSuccess:function(data){
				
				//当为"报纸"时,隐藏的列
				if(publicityType==0){
					$("#dg").datagrid("hideColumn", "publicityVolume");//卷/期
					$("#dg").datagrid("hideColumn", "publicityChannel");//电视频道名称
				}else if(publicityType==1){
					//更改列名(判断当标题不为"入学时间"时更改列名)
					var option = $("#dg").datagrid("getColumnOption", "publicityTitle");
					if(option.title!="期刊名称"){
						option.title = "期刊名称";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityPosition");
					if(option.title!="位置"){
						option.title = "位置";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityInterviewee");
					if(option.title!="被宣传人"){
						option.title = "被宣传人";
						$('#dg').datagrid();
					}
					$("#dg").datagrid("hideColumn", "publicityCompany");//受访单位
					$("#dg").datagrid("hideColumn", "publicityChannel");//电视频道名称
				}else if(publicityType==2){
					//更改列名(判断当标题不为"入学时间"时更改列名)
					var option = $("#dg").datagrid("getColumnOption", "publicityTitle");
					if(option.title!="栏目名称"){
						option.title = "栏目名称";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityInterviewee");
					if(option.title!="受访人"){
						option.title = "受访人";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityTime");
					if(option.title!="时间"){
						option.title = "时间";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityPosition");
					if(option.title!="视频网址"){
						option.title = "视频网址";
						$('#dg').datagrid();
					}
					$("#dg").datagrid("hideColumn", "publicityVolume");//卷/期
					$("#dg").datagrid("hideColumn", "publicityCompany");//受访单位
				}else if(publicityType==3){
					//更改列名(判断当标题不为"入学时间"时更改列名)
					var option = $("#dg").datagrid("getColumnOption", "publicityTitle");
					if(option.title!="网站名称"){
						option.title = "网站名称";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityPosition");
					if(option.title!="宣传页网址"){
						option.title = "宣传页网址";
						$('#dg').datagrid();
					}
					var option = $("#dg").datagrid("getColumnOption", "publicityInterviewee");
					if(option.title!="被宣传人"){
						option.title = "被宣传人";
						$('#dg').datagrid();
					}
					$("#dg").datagrid("hideColumn", "publicityVolume");//卷/期
					$("#dg").datagrid("hideColumn", "publicityCompany");//受访单位
					$("#dg").datagrid("hideColumn", "publicityChannel");//电视频道名称
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
    		title: "对外宣传-"+showTitle+"-信息申报",
				iconCls : "icon-add",
				collapsible: true,
              /* minimizable: true,
               maximizable: true,*/
               resizable: true,
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					var publicityType=$("#publicityType1").val();
					$('#publicityType').val(publicityType);
					//负责人赋值
					setBaseInfoName();
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "publicity/saveOrUpPublicity?" + urls;
	}
	function setBaseInfoName(){
		var baseInfoId=$("#baseInfoId").val();
		var baseInfoName=$("#baseInfoName2").val();
		var role=$("#role").val();
		if(baseInfoId!="" && baseInfoName!="" && (role=="3" || role=="4")){
			$("#publicityInterviewee").textbox('setValue',baseInfoName);
			$("#publicityIntervieweeId").val(baseInfoId);
			$("#publicityInterviewee").textbox("disable");
			$("#a_publicityInterviewee").hide();
		}
	}
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#fm').form('load', 'publicity/publicityView?path=editPublicity&publicityId='+row.publicityId + urls); 
        	$("#dlg").dialog({  
        		title: "对外宣传-"+showTitle+"-信息编辑",
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
					$('#publicityId').val(row.publicityId);
					//负责人赋值
					setBaseInfoName();
   				},
        	});
        	$("#dlg").dialog("open");
        	// 弹框居中
        	dlgCenter();
        	//点击保存按钮后跳转路径
        	url = "publicity/saveOrUpPublicity?" + urls;
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var publicityDel=$("#publicityDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].publicityId;  
                 } else {  
                     temID = selRow[i].publicityId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(publicityDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('publicity/deletePublicity?publicityDel='+publicityDel + urls,{publicityId:temID},function(data){
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
		$("#publicityDel").val(2);
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
                     temID = selRow[i].publicityId;  
                 } else {  
                     temID = selRow[i].publicityId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('publicity/deletePublicity?publicityDel=0' + urls,{publicityId:temID},function(data){
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
		var publicityType=$("#publicityType1").val();
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		// 报纸
		if(publicityType==0){
			var url = "ReportServer?reportlet=publicityType0.cpt&publicityType=" + publicityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("报纸对外宣传");
		// 期刊
		}else if(publicityType==1){
			var url = "ReportServer?reportlet=publicityType1.cpt&publicityType=" + publicityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("期刊对外宣传");
		// 电视台
		}else if(publicityType==2){
			var url = "ReportServer?reportlet=publicityType2.cpt&publicityType=" + publicityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("电视台对外宣传");
		// 网络
		}else if(publicityType==3){
			var url = "ReportServer?reportlet=publicityType3.cpt&publicityType=" + publicityType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("网络对外宣传");
		}
		window.open(url);
	}
	
	// 上传材料
	function upload(){
		//获取选择上传数据id
		var row = $('#dg').datagrid('getSelected');
		//表名
		var tableName="ep_publicity"
		$("#relevanceId").val(row.publicityId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.publicityId);
	}
	
	function uploadList(tableName,publicityId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取类型
		var publicityType=$("#publicityType1").val();
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+publicityId+"&tableName="+tableName + urls,
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
						"<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>报纸文章页</option>" +
						"<option value='1'>其他</option>" +
						"</select>"+
						"</td>" +
						"<td id ='typelist1"+i+"' style='display:none'>" +
						"<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>期刊首页及登文章页</option>" +
						"<option value='1'>其他</option>" +
						"</select>"+
						"</td>" +
						"<td id ='typelist2"+i+"' style='display:none'>" +
						"<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>其他</option>" +
						"</select>"+
						"</td>" +
						"<td id ='typelist3"+i+"' style='display:none'>" +
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
						$("#typelist"+publicityType+i).show();
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
		var publicityType=$("#publicityType1").val();
		$("#typePublicity"+publicityType).show();
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
		var publicityType=$("#publicityType1").val();//宣传类型
		
		if(fileFullName!=""){
			$("#fmUpload").ajaxSubmit({
				type: "POST",
				url:"common/uploadFile?pathWay=publicity/sr"+publicityType+"&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent + urls,
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
    	if($("#publicityInterviewee").prop('disabled')){
    		$("#publicityInterviewee").textbox("enable");
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
