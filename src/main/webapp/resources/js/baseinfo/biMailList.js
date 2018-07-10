	var p=0;
	var urls = "";
	$(document).ready(function() { 
		//当"姓名"文本框被触发时,去后台获取人员列表
		$("input",$("#baseInfoName").next("span")).focus(function(){
			if(p==0){
				//获取信息类型
				var baseInfoType=$("#baseInfoType1").val();
				$("#type"+baseInfoType).show();
				$("#type"+baseInfoType).css('display','inline-block');
				searchByType1(baseInfoType);
			}
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#mailListDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide();
		showOrHide();
	});
	
	
	//plg 弹出框点击取消按钮,将p设置为0
	function funClose(){
		p=0;
	}
	// 根据选中人员类型,查询该类型下的人员
	function searchByType1(type){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		$.ajax({
			url:"baseInfo/baseInfoNames?baseInfoType="+type + urls,
			type:"post",
			success:function (data){
				if(data!=null && p==0){
	            	$("#plg").dialog({  
	    	    		title: "请选择则人员",
    					iconCls : "icon-search",
    					collapsible: true,
    	                resizable: true,
    					width: 795,
    					height:410, 
    					top: 2,
    					left: 141,
    					onClose: function(){
    						p=0;
    						win.window('destroy');//关闭即销毁
    					},
	    	    	});
	            	var n=0;
	            	$("#personId").empty();
	            	for(var i=0;i<data.length;i++){
	            		if(i%10==0){
	            			n++;
	            			var social=$("#personId").append("<tr id='ptr"+n+"'>" +
		        					"<td id='tdname"+i+"' class='baseInfoNameTd' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>" +
		        					"<span id='spanname"+i+"' class='baseInfoNameSpan' onclick='subName1(this.id)'>"+data[i].baseInfoName+ "</span>"+
		        					"</td>" +
		        					"<td id='tdid"+i+"' style='display:none'>"+data[i].baseInfoId+"</td>" +
		        					"</tr>");
	            			
	            		}else{
	            			var social=$("#ptr"+n).append("<td id='tdname"+i+"' class='baseInfoNameTd' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>"+
	            					"<span id='spanname"+i+"' class='baseInfoNameSpan' onclick='subName1(this.id)'>"+data[i].baseInfoName+ "</span>"+
	            					"</td>"+
	            					"<td id='tdid"+i+"' style='display:none'>"+data[i].baseInfoId+"</td>");
	            		}
	            	}
	            	
	    	    	$("#plg").dialog("open");
	    	    	p=1;
				}
			}
		});
	}
	
	//选择某一人员后,获取选中人员的姓名和id,放到新增页面的文本框中
	function subName1(id){
		var baseInfoName=$("#"+id).text();
		var baseInfoId=$("#"+id).parent().next().text();
		$("#baseInfoName").textbox("setValue", baseInfoName);
		$("#baseInfoId").val(baseInfoId);
		$("#plg").dialog("close");
		$("#personId").empty();
		p=0;
	}
	
	// 当鼠标放到td上时,td显示背景颜色
	function moveName(id){
		$("#"+id).css('background-color','#99BBE8');
	}
	// 当鼠标放到td上时,td取消背景颜色
	function outName(id){
		$("#"+id).css('background-color','#FFFFFF');
	}
	
	//根据类型,显示隐藏查询条件
	function showOrHide(){
		//获取信息类型
		var baseInfoType=$("#baseInfoType1").val();
		if(baseInfoType!=2 && baseInfoType!=5){
			$("#cId").hide(); // 单位
			$("#ptId").hide(); // 职称
			$("#poId").hide(); // 职务
		}
		
		if(baseInfoType!=2){
			$("#stId").hide(); // 入站时间
			$("#etId").hide(); // 出站时间
		}
		
		if(baseInfoType!=3 && baseInfoType!=4){
			$("#satId").hide(); // 入学时间
		}
		
		if(baseInfoType!=0 && baseInfoType!=1){
			$("#pId").hide(); // 小号
			$("#sId").hide(); // 工作室号
		}
		
		if(baseInfoType==2 || baseInfoType==3 || baseInfoType==4){
			$("#oId").hide(); // 办公电话
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
	function showTitleType(baseInfoType){
		if(baseInfoType==0){
			showTitle="科研人员"
		}else if(baseInfoType==1){
			showTitle="行政人员"
		}else if(baseInfoType==2){
			showTitle="博士后"
		}else if(baseInfoType==3){
			showTitle="博士"
		}else if(baseInfoType==4){
			showTitle="硕士"
		}else if(baseInfoType==5){
			showTitle="其他人员"
		}
	}
	//调用后台方法
	function queryAction(mailListDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取信息类型
		var baseInfoType=$("#baseInfoType1").val();
		showTitleType(baseInfoType);
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'baseInfo/biMailList?baseInfoType='+baseInfoType+"&mailListDel="+mailListDel+"&getName="+getName+"&getValue="+getValue + urls,
			title: showTitle+"列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                field : 'mailListId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'baseInfoName',
                title : '姓名',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                }
            }, {
                field : 'mailListPhone',
                title : '手机号码',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'mailListPhone1',
                title : '小号',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'mailListOph',
                title : '办公电话',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'mailListStudio',
                title : '工作室号',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            },{
                field : 'baseInfoPositionalTitles',
                title : '职称',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                    if(value!=null && value!=''){
                    	if(value=='0'){return '研究员'} else if(value=='1'){return '副研究员'} else if(value=='2'){return '助理研究员'} 
	                         else if(value=='3'){return '教授'} else if(value=='4'){return '副教授'} else if(value=='5'){return '讲师'}
	                         else if(value=='6'){return '助教'}
                       }else{return '--'}
                }
            }, {
                field : 'baseInfoPost',
                title : '职务',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'baseInfoStartTime',
                title : '入站时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'baseInfoEndTime',
                title : '出站时间',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                  	 if(value!=null && value!=''){
       				    return value
       				 } else{return '--'}
                 }
            }, {
                field : 'mailListEmail',
                title : '电子邮箱',
                width : 80,
                sortable :true,
                formatter : function(value,row,index){  
                 	 if(value!=null && value!=''){
      				    return value
      				 } else{return '--'}
                }
            }, {
                field : 'mailListAddress',
                title : '通讯地址',
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
				//获取信息类型
				var baseInfoType=$("#baseInfoType1").val();
				var role=$("#role").val();
				var baseInfoNameS=$("#baseInfoNameS").val();
				var baseInfoIdS=$("#baseInfoIdS").val();
				// 领导和行政人员时，判断是否已经填写了基本信息，如果填写了，则可以新增通讯录，否则置灰
				if(role==1){
					if(baseInfoNameS=="" || (baseInfoType!=0 && baseInfoType!=2 && baseInfoType!=3 && baseInfoType!=4)){
						$('#addBtn').linkbutton('disable');
					}
				}else if(role==2){
					if(baseInfoNameS=="" || (baseInfoType!=1 && baseInfoType!=2 && baseInfoType!=3 && baseInfoType!=4)){
						$('#addBtn').linkbutton('disable');
					}
				}
				if(role==1 || role==2){
					if(data.total!=0 && data.rows[0].baseInfoId==baseInfoIdS){
						// 判断当列表总条数大于0时，并且id等于登录时，新增按钮置灰
						$('#addBtn').linkbutton('disable');
					}
				}
				if(baseInfoType==5){
					$("#dg").datagrid("hideColumn", "baseInfoStartTime"); 
					$("#dg").datagrid("hideColumn", "baseInfoEndTime"); 
				}
				// 科研人员、行政人员、博士、硕士隐藏
				if(baseInfoType!=2 && baseInfoType!=5){
					$("#dg").datagrid("hideColumn", "baseInfoPositionalTitles"); 
					$("#dg").datagrid("hideColumn", "baseInfoPost"); 
					if(baseInfoType!=3 && baseInfoType!=4){
						$("#dg").datagrid("hideColumn", "baseInfoStartTime"); 
					}
					$("#dg").datagrid("hideColumn", "baseInfoEndTime"); 
				}
				// 博士后、博士、硕士隐藏：小号、办公电话、工作室号、
				if(baseInfoType!=0 && baseInfoType!=1){
					$("#dg").datagrid("hideColumn", "mailListPhone1"); 
					$("#dg").datagrid("hideColumn", "mailListStudio"); 
					if(baseInfoType!=5){
						$("#dg").datagrid("hideColumn", "mailListOph");
					}
				}
				if(baseInfoType==3 || baseInfoType==4){
					var option = $("#dg").datagrid("getColumnOption", "baseInfoStartTime");
					if(option.title!="入学时间"){
						option.title = "入学时间";
						$('#dg').datagrid();
					}
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
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		//$('#fm').form('load', 'baseInfo/biMailListView?path=addMailList' + urls); 
    	$("#dlg").dialog({  
    		title: "通讯录-"+showTitle+"-信息申报",
				iconCls : "icon-add",
				collapsible: true,
              /* minimizable: true,
               maximizable: true,*/
               resizable: true,
				/*width: 795,
				height:410, 
				top: 2,
				left: 141,*/
				onClose: function(){
				   win.window('destroy');//关闭即销毁
				},
				onOpen:function(){
					//将列表中的类型放到新增信息form中
					var baseInfoType=$("#baseInfoType1").val();
					$('#baseInfoType').val(baseInfoType);
					$('#baseInfoPath').val(0);
					
					//加载对应通讯信息
					
					//博士后
					if(baseInfoType==0 || baseInfoType==1){
						//隐藏"职称"和"职务"
						$("#tr4").remove();
						//隐藏"单位"
						$("#tr5").remove();
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==2){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").hide();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==3 || baseInfoType==4){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").remove();
						
						//隐藏"职称"和"职务"
						$("#tr4").remove();
						//隐藏"单位"
						$("#tr5").remove();
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==5){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").remove();
						
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
					}
					//pageDisplay('',baseInfoType);
					// 判断 如果是领导、行政人员登录时，姓名赋值
					var role=$("#role").val();
					var baseInfoNameS=$("#baseInfoNameS").val();
					var baseInfoIdS=$("#baseInfoIdS").val();
					if(role==1 || role ==2){
						if(baseInfoType==0 || baseInfoType==1 || baseInfoType==5){
							$("#baseInfoName").textbox("setValue", baseInfoNameS);
							$("#baseInfoId").val(baseInfoIdS);
							$("#baseInfoName").textbox('disable');
						}
					}
				},
    	});
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	// 弹框居中
    	dlgCenter();
    	//点击保存按钮后跳转路径
    	url = "baseInfo/saveOrUpMailList?" + urls;
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#fm').form('load', 'baseInfo/biMailListView?path=editMailList&mailListId='+row.mailListId + urls); 
        	$("#dlg").dialog({  
        		title: "通讯录-"+showTitle+"-信息编辑",
					iconCls : "icon-edit",	
					collapsible: true,
	               /* minimizable: true,
	                maximizable: true,*/
	                resizable: true,
					/*width: 795,
					height:410, 
					top: 2,
					left: 141,*/
					onClose: function(){
					   win.window('destroy');//关闭即销毁
					},
					onOpen:function(){
   					//将列表中的类型放到新增信息form中
						var baseInfoType=$("#baseInfoType1").val();
   					$('#baseInfoType').val(baseInfoType);
					$('#mailListId').val(row.mailListId);
					//博士后
					if(baseInfoType==0 || baseInfoType==1){
						//隐藏"职称"和"职务"
						$("#tr4").remove();
						//隐藏"单位"
						$("#tr5").remove();
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==2){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").hide();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==3 || baseInfoType==4){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").remove();
						
						//隐藏"职称"和"职务"
						$("#tr4").remove();
						//隐藏"单位"
						$("#tr5").remove();
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"办公电话"
						$("#tdoph1").remove();
						$("#tdoph2").remove();
					}
					if(baseInfoType==5){
						//隐藏"小号"和"办公电话"
						$("#tr3").remove();
						
						//隐藏"工作室号"
						$("#tdstudio1").remove();
						$("#tdstudio2").remove();
						
						//隐藏"入站时间"和"出站时间"
						$("#tr6").remove();
						//隐藏"入学时间"
						$("#tdstartTime1").remove();
						$("#tdstartTime2").remove();
					}
   					//加载对应通讯信息
   					//pageDisplay('',baseInfoType);
   				},
        	});
        	$("#dlg").dialog("open");
        	// 弹框居中
        	dlgCenter();
        	//点击保存按钮后跳转路径
        	url = "baseInfo/saveOrUpMailList?" + urls;
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var mailListDel=$("#mailListDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
		if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].mailListId;  
                 } else {  
                     temID = selRow[i].mailListId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(mailListDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('baseInfo/deleteMailList?mailListDel='+mailListDel + urls,{mailListId:temID},function(data){
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
		$("#mailListDel").val(2);
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
                     temID = selRow[i].mailListId;  
                 } else {  
                     temID = selRow[i].mailListId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('baseInfo/deleteMailList?mailListDel=0' + urls,{mailListId:temID},function(data){
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
		//获取信息类型
		var baseInfoType = $("#baseInfoType1").val();
		//姓名
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		getValue=getValueFunction(getName, getValue);
		// 科研人员
		if(baseInfoType==0){
			var url = "ReportServer?reportlet=mailListType0.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("科研人员通讯录");
			window.open(url);
		// 行政人员
		}else if(baseInfoType==1){
			var url = "ReportServer?reportlet=mailListType1.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("行政人员通讯录");
			window.open(url);
		// 博士后
		}else if(baseInfoType==2){
			var url = "ReportServer?reportlet=mailListType2.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("博士后通讯录");
			window.open(url);
		// 博士
		}else if(baseInfoType==3){
			var url = "ReportServer?reportlet=mailListType3.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("博士通讯录");
			window.open(url);
		// 硕士
		}else if(baseInfoType==4){
			var url = "ReportServer?reportlet=mailListType4.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("硕士通讯录");
			window.open(url);
		// 其他人员
		}else{
			var url = "ReportServer?reportlet=mailListType5.cpt&baseInfoType='" + baseInfoType + "'&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("其他人员通讯录");
			window.open(url);
		}
	}
	
	function getValueFunction(name, value){
       if(name=="baseInfoPositionalTitles"){
            if(value=="研究员"){
                value=0; 
            }else if(value=="副研究员"){
                value=1; 
            }else if(value=="助理研究员"){
                value=2; 
            }else if(value=="教授"){
                value=3; 
            }else if(value=="副教授"){
                value=4; 
            }else if(value=="讲师"){
                value=5; 
            }else if(value=="助教"){
                value=6;
            }else{
                value=99;
            }
        }
        return value;
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
