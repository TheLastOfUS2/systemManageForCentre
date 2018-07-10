	var p=0;
	var a=0;
	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		
		//当科研人员"账号"文本框被触发时,去后台获取账号列表
		$("input",$("#accountName0").next("span")).focus(function(){
			if(a==0){
				getAccount(3,"accountName0");
			}
		});
		//当行政人员"账号"文本框被触发时,去后台获取账号列表
		$("input",$("#accountName1").next("span")).focus(function(){
			if(a==0){
				getAccount(2,"accountName1");
			}
		});
		//当其他人员"账号"文本框被触发时,去后台获取账号列表
		$("input",$("#accountName2").next("span")).focus(function(){
			if(a==0){
				getAccount(4,"accountName2");
			}
		});
		
		//获取信息类型
		var baseInfoType=$("#baseInfoType1").val();
		//当类型为2(博士后)时,清空Staff1
		if(baseInfoType==2){
			$('#Staff1').empty();
		}else{
			$('#Staff2').empty();
		}
		//当"导师姓名"文本框被触发时,去后台获取人员列表
		$("input",$("#baseInfoMentor").next("span")).focus(function(){
			if(p==0){
				searchByType(0,"baseInfoMentor","");
			}
		});
		
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#baseInfoDel").val(1);
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
	
	//获取账号列表
	function getAccount(type,name){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		// 当name不为空时,将name的值放到隐藏域中
		if(name!=""){
			$("#aName").val(name);
		}else{
			name=$("#aName").val();
		}
		a=0;
		//num=0;科研人员,可以选择的账号角色有“科研人员”
		//num=1;行政人员,可以选择的账号角色有“领导”
		//num=2;其他人人员,可以选择的账号角色有“行政人员”
		//num=3;其他人人员,可以选择的账号角色有“其他人员”
		$.ajax({
			url:"account/accountNames?roleName="+type+urls,
			type:"post",
			success:function (data){
				if(data!=null && a==0){
	            	$("#alg").dialog({  
	    	    		title: "请选择账号",
    					iconCls : "icon-search",
    					collapsible: true,
    	                resizable: true,
    					width: 795,
    					height:410, 
    					top: 2,
    					left: 141,
    					onClose: function(){
    						a=0;
    						win.window('destroy');//关闭即销毁
    					},
	    	    	});
	            	var n=0;
	            	$("#accoutIdt").empty();
	            	for(var i=0;i<data.length;i++){
	            		if(i%5==0){
	            			n++;
	            			var social=$("#accoutIdt").append("<tr id='ptr"+n+"'>" +
		        					"<td id='tdname"+i+"' class='accId' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>" +
		        					"<span style='display:inline-block;width:100%;cursor:pointer;' id='spananame"+i+"' class='accountNameSpan' onclick='subaName(this.id,\""+name+"\")'>"+data[i].accountName+ "</span>"+
		        					"</td>" +
		        					"<td id='tdid"+i+"' style='display:none'>"+data[i].accountId+"</td>" +
		        					"</tr>");
	            			
	            		}else{
	            			var social=$("#ptr"+n).append("<td id='tdname"+i+"' class='accId' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>"+
	            					"<span style='display:inline-block;width:100%;cursor:pointer;' id='spananame"+i+"' class='accountNameSpan' onclick='subaName(this.id,\""+name+"\")'>"+data[i].accountName+ "</span>"+
	            					"</td>"+
	            					"<td id='tdid"+i+"' style='display:none'>"+data[i].accountId+"</td>");
	            		}
	            	}
	            	
	    	    	$("#alg").dialog("open");
	    	    	var idVal = $(".selected").attr("id");
	    	    	$("#"+idVal).removeClass("selected").addClass('cata-item'); 
	    			//将本次选择的人员类型选中
	    	    	if(type==3){
	    	    		$("#atype1").show();
	    	    	}
	    	    	$("#atype"+type).show();
	    			$("#atype"+type).addClass("selected");
	    	    	a=1;
				}
			}
		});
	}
	
	
	//选择某一人员后,获取选中人员的姓名和id,放到新增页面的文本框中
	function subaName(id,name){
		var accountName=$("#"+id).text();
		var accountId=$("#"+id).parent().next().text();
		$("#"+name).textbox("setValue", accountName);
		$("#"+name+"Id").val(accountId);
		$("#alg").dialog("close");
		$("#accoutIdt").empty();
		a=0;
	}
	
	// 当鼠标放到td上时,td显示背景颜色
	function moveName(id){
		$("#"+id).css('background-color','#99BBE8');
	}
	// 当鼠标放到td上时,td取消背景颜色
	function outName(id){
		$("#"+id).css('background-color','#FFFFFF');
	}
	
	//添加年度事件
	function yearChoose(baseInfoGrade){
		$("#yearChoose").combobox({
			valueField:'year',
			textField:'year'
			/*panelHeight:'auto'*/
        });	
		var data = [];//创建年度数组
		var startYear;//起始年份
		//var thisYear=new Date().getUTCFullYear();//今年
		var thisYear=new Date().getUTCFullYear();//今年
		var endYear=thisYear+20;//结束年份
		//数组添加值（2012-2016）//根据情况自己修改
		for(startYear=2000;startYear<endYear;startYear++){
		    data.push({"year":startYear});
		}
		$("#yearChoose").combobox("loadData",data);//下拉框加载数据
		if(baseInfoGrade!=""){
			$("#yearChoose").combobox("setValue",baseInfoGrade);//设置默认值为今年
		}else{
			$("#yearChoose").combobox("setValue",thisYear);//设置默认值为今年
		}
	}
	
	//根据类型,显示隐藏查询条件
	function showOrHide(){
		//获取信息类型
		var baseInfoType=$("#baseInfoType1").val();
		if(baseInfoType!=0 && baseInfoType!=1){ 
			$("#eId").hide(); // 学历
			$("#dId").hide(); // 学位
			$("#sId").hide(); // 专业
			$("#wId").hide(); // 参加工作时间
		}
		if(baseInfoType!=2){ 
			$("#mId").hide(); // 合作导师
			$("#stId").hide(); // 进站时间
			$("#etId").hide(); // 出站时间
		}
		if(baseInfoType!=3 && baseInfoType!=4 ){
			$("#m1Id").hide(); // 合作导师
			$("#st1Id").hide(); // 进站时间
			$("#et1Id").hide(); // 出站时间
		}
		if(baseInfoType!=0 && baseInfoType!=1 && baseInfoType!=5){ 
			$("#tId").hide(); // 职称
			$("#lId").hide(); // 级别
			$("#pId").hide(); // 职务
		}
		if(baseInfoType!=5 ){ // 其他人员
			$("#aId").hide(); // 级别
			$("#estId").hide(); // 职务
			$("#eetId").hide(); // 参加工作时间
		}
		if(baseInfoType==5 ){ // 其他人员
			$("#bId").hide(); // 出生日期
		}
		if(baseInfoType!=0){ 
			$("#aetId").hide();//出国日期
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
	function queryAction(baseInfoDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		//获取信息类型
		var baseInfoType=$("#baseInfoType1").val();
		showTitleType(baseInfoType);
		
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			//后台获取数据
			url:'baseInfo/baseInfoList?baseInfoType='+baseInfoType+"&baseInfoDel="+baseInfoDel+"&getName="+getName+"&getValue="+getValue+urls,
			title: showTitle+"列表",
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
                 field : 'baseInfoId',
                 title : 'ID',
                 width : 240,
                 align : 'center',
                 hidden: 'hidden',
                 sortable:true
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
             },{
                 field : 'accountName',
                 title : '账号',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){  
                	 if(value!=null && value!=''){
     				    return value
     				 } else{return '--'}
                 }
             }, {
                 field : 'baseInfoSex',
                 title : '性别',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
    				 if(value!=null && value!=''){
    				   if(value=='1'){return '男'} else if(value=='0'){return '女'}
    				 } else{return '--'}
                 }
             }, {
                 field : 'baseInfoBirthDate',
                 title : '出生日期',
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
                 field : 'baseInfoEducation',
                 title : '学历',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                     if(value!=null && value!=''){
                     	if(value=='0'){return '博士'} else if(value=='1'){return '硕士'} else if(value=='2'){return '本科'} 
                     }else{return '--'}
                 }
             }, {
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
                 field : 'baseInfoLevel',
                 title : '级别',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                     if(value!=null && value!=''){
                     	 if(value=='0'){return '高级'} else if(value=='1'){return '副高'} else if(value=='2'){return '中级'} 
                         else if(value=='3'){return '初级'}
                     }else{return '--'}
                 }
             }, {
                 field : 'baseInfoIfDoctorTutor',
                 title : '是否博导',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                     if(value!=null && value!=''){
                       	if(value=='0'){return '否'} else if(value=='1'){return '是'}  
                     }else{return '--'}
                 }
             }, {
                 field : 'baseInfoIfMasterTutor',
                 title : '是否硕导',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                     if(value!=null && value!=''){
                       	if(value=='0'){return '否'} else if(value=='1'){return '是'}  
                     }else{return '--'}
                 }
             }, {
                 field : 'baseInfoStartWorkTime',
                 title : '参加工作时间',
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
                 field : 'baseInfoMentor',
                 title : '合作导师',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                	 if(value!=null && value!=''){
     				    return value
     				 } else{return '--'}
                 }
             }, {
                 field : 'baseInfoStartTime',
                 title : '进站时间',
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
                 field : 'baseInfoEndTime',
                 title : '出站时间',
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
                 field : 'baseInfoExternalAppellation',
                 title : '外聘称谓',
                 width : 80,
                 sortable :true,
                 formatter : function(value,row,index){  
                	 if(value!=null && value!=''){
     				    return value
     				 } else{return '--'}
                 }
             }, {
                 field : 'baseInfoEngageStartTime',
                 title : '聘用开始日期',
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
                 field : 'baseInfoEngageEndTime',
                 title : '聘用截止日期',
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
             }] ],
             
             //加载后设置
			onLoadSuccess:function(data){
				// 根据角色判断，如果是领导或者行政人员，判断其是否已添加过基本信息，如果已添加过基本信息，页面新增按钮不让点击
				var role=$("#role").val();
				// 领导只能添加科研人员  行政只能添加行政人员
				if(role==1){
					// 当角色为领导,类型为行政人员和其他人员时,新增按钮置灰
					if(baseInfoType==1 || baseInfoType==5){
						$('#addBtn').linkbutton('disable');
					}
				}else if(role==2){
					// 当角色为行政,类型为科研人员和其他人员时,新增按钮置灰
					if(baseInfoType==0 || baseInfoType==5){
						$('#addBtn').linkbutton('disable');
					}
				}
				
				if(role==1 || role==2){
					if((data.accountId!=null && data.accountId!=0) && (baseInfoType==0 || baseInfoType==1)){//存在,将新增按钮置灰
						$('#addBtn').linkbutton('disable');
					}
				}
				//当为科研人员、行政人员和其他人员时，显示账号
				if($("#baseInfoType1").val()!=0 && $("#baseInfoType1").val()!=1 && $("#baseInfoType1").val()!=5){
					$("#dg").datagrid("hideColumn", "accountName"); 
				}
				
				//当不为"科研人员时"隐藏的列
				if($("#baseInfoType1").val()!=0){
					$("#dg").datagrid("hideColumn", "baseInfoIfDoctorTutor"); // 是否博导
					$("#dg").datagrid("hideColumn", "baseInfoIfMasterTutor"); // 是否硕导
				}
				//当不为"科研人员、行政人员时"隐藏的列
				if($("#baseInfoType1").val()!=0 && $("#baseInfoType1").val()!=1){
					$("#dg").datagrid("hideColumn", "baseInfoStartWorkTime"); // 参加工作时间
				}
				//当不为"其他人员时"隐藏的列
				if($("#baseInfoType1").val()!=5){
					$("#dg").datagrid("hideColumn", "baseInfoExternalAppellation");// 外聘称谓
					$("#dg").datagrid("hideColumn", "baseInfoEngageStartTime");// 聘用开始日期
					$("#dg").datagrid("hideColumn", "baseInfoEngageEndTime");// 聘用截止日期
				}
				//当为"科研人员、行政人员或其他人员时"隐藏的列
				if($("#baseInfoType1").val()==0 || $("#baseInfoType1").val()==1 || $("#baseInfoType1").val()==5){
					$("#dg").datagrid("hideColumn", "baseInfoMentor");// 合作导师
					$("#dg").datagrid("hideColumn", "baseInfoStartTime");// 进站时间
					$("#dg").datagrid("hideColumn", "baseInfoEndTime");// 出站时间
					//当为"其他人员时"隐藏的列
					if($("#baseInfoType1").val()==5){
						$("#dg").datagrid("hideColumn", "baseInfoBirthDate");// 出生日期
					}
				}
				if($("#baseInfoType1").val()==5){
					$("#dg").datagrid("hideColumn", "baseInfoEducation");// 学历
				}
				
				//当为"博士后、博士或硕士"隐藏的列
				if($("#baseInfoType1").val()==2 || $("#baseInfoType1").val()==3 || $("#baseInfoType1").val()==4){
					$("#dg").datagrid("hideColumn", "baseInfoEducation");// 学历
					$("#dg").datagrid("hideColumn", "baseInfoPositionalTitles");// 职称
					$("#dg").datagrid("hideColumn", "baseInfoLevel");// 级别
					//当为"博士或硕士"更改列名
					if($("#baseInfoType1").val()==3 || $("#baseInfoType1").val()==4){
						//更改列名(判断当标题不为"入学时间"时更改列名)
						var option = $("#dg").datagrid("getColumnOption", "baseInfoStartTime");
						if(option.title!="入学时间"){
							option.title = "入学时间";
							$('#dg').datagrid();
						}
						var option = $("#dg").datagrid("getColumnOption", "baseInfoEndTime");
						if(option.title!="毕业时间"){
							option.title = "毕业时间";
							$('#dg').datagrid();
						}
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
	
	var num=0;
	var url;
	function add(){
		// 给父div添加样式
	/*	pdlg.style.top=(document.documentElement.clientHeight-pdlg.offsetHeight)/2+"px";
		pdlg.style.left=(document.documentElement.clientWidth-pdlg.offsetWidth)/2+"px";*/
		
		/*pdlg.style.top='50%'
		pdlg.style.left='50%';
		pdlg.style.width='300px'
		pdlg.style.height='300px';
		pdlg.style.marginTop='-150px';
		pdlg.style.marginLeft='-150px';
		pdlg.style.position='absolute';*/
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		num=0;
    	$("#dlg").dialog({  
    		title: "基本信息-"+showTitle+"-信息申报",
			iconCls : "icon-add",
			collapsible: true,
           /* minimizable: true,
            maximizable: true,*/
            resizable: true,
         /*  width: 795,
			height:410, */
			/* top: 2,
			left: 141,*/
			onClose: function(){
				win.window('destroy');//关闭即销毁
			},
			onOpen:function(){
				if(num==0){//防止点击编辑按钮后,再走一遍onOpen
					//将列表中的类型放到新增信息form中
   					var baseInfoType=$("#baseInfoType1").val();
   					$('#baseInfoType').val(baseInfoType);
   					$('#baseInfoPath').val(0);
   					//每次进来设置educationalNum为0
   					educationalNum=0;
   					workNum=0;
   					jobNum=0;
   					abroadNum=0;
   					//加载对应基本信息和经历信息
   					pageDisplay('',baseInfoType);
   					//新增时调用添加年度事件
					yearChoose("");
				}
			}
    	});
    	
    	$('#fm').form('clear');
    	$("#dlg").dialog("open");
    	
    	// 弹框居中
    	dlgCenter();
    	var param=""
    	var role=$("#role").val();
		var baseInfoType=$('#baseInfoType').val();
    	//如果是领导或者行政人员添加科研人员或者行政人员时，绑定登录账号id
		if((role==1 && baseInfoType==0) || (role==2 && baseInfoType==1)){
			var accountId=$("#accountId").val();
    		param = "accountId="+accountId;
		}
    	//点击保存按钮后跳转路径
    	url = "baseInfo/saveOrUpBaseInfo?" + param +urls;
	}
	
	function edit(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		
		var row = $('#dg').datagrid('getSelected');
		//清空存储原数据的id
		$("#educationalIds").val("");
		$("#workIds").val("");
        if (row){
        	num=1;
        	$.ajax({
				url:"baseInfo/baseInfoView?baseInfoId="+row.baseInfoId + urls,
				type:"post",
				success:function (data){
					if(data!=null && data != ""){
						//将列表中的类型和选择数据的ID放到新增信息form中
						var baseInfoType=$("#baseInfoType1").val();
						$('#fm').form('load', data); 
	   					$('#baseInfoType').val(baseInfoType);
						$('#baseInfoId').val(row.baseInfoId);
						$('#baseInfoPath').val(1);
						//每次进来设置educationalNum为0
						educationalNum=0;
	   					workNum=0;
	   					jobNum=0;
	   					abroadNum=0;
						//加载对应基本信息和经历信息
	   					pageDisplay(data,baseInfoType);
	   					//加载教育经历数据
						if(data.biEducationals!=null && data.biEducationals.length>0){
		   					editEducational(data);
						}
						//加载工作经历数据
						if(data.biWorks!=null && data.biWorks.length>0){
		   					editWork(data);
						}
						//加载工作经历数据
						if(data.biJobs!=null && data.biJobs.length>0){
							editJob(data);
						}
						//加载出国经历数据
						if(data.biAbroads!=null && data.biAbroads.length>0){
							editAbroad(data);
						}
						// 隐藏账号
						$("#atr0").hide();
						$("#dlg").dialog({  
		            		title: "基本信息-"+showTitle+"-信息编辑",
		   					iconCls : "icon-edit",
		   					collapsible: true,
		   	               /* minimizable: true,
		   	                maximizable: true,*/
		   	                resizable: true,
		   					/*width: 795,
		   					height:410, 
		   					top: 2,
		   					left: 141,*/
		   					//modal:true,遮罩层
		   					onClose: function(){
		   					   win.window('destroy');//关闭即销毁
		   					},
		            	});
						//新增时调用添加年度事件
						yearChoose(data.baseInfoGrade);
		            	$("#dlg").dialog("open");
		            	// 弹框居中
		            	dlgCenter();
		            	//点击保存按钮后跳转路径
		            	url = "baseInfo/saveOrUpBaseInfo?"+urls;
					}else {
						// 重定向到登录页
						window.open('http://xrj.widonet.com/www/dongcai/','_top');
					}
				}
			});
        	
        	//$('#fm').form('load', 'baseInfo/baseInfoView?baseInfoId='+row.baseInfoId); 
        }else{ 
            $.messager.alert('提示信息','未选中任何记录!','info'); 
        }
	}
	
	function del(){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		//获取是在未被删除数据的列表还是已被删除数据的列表
		var baseInfoDel=$("#baseInfoDel").val();
		//返回选中多行
		var selRow = $('#dg').datagrid('getSelections');
        if (selRow!=""){
        	 var temID="";  
             //批量获取选中行的评估模板ID  
             for (i = 0; i < selRow.length;i++) {  
                 if (temID =="") {  
                     temID = selRow[i].baseInfoId;  
                 } else {  
                     temID = selRow[i].baseInfoId + "," + temID;  
                 }                 
             }
             var delTitle;
             if(baseInfoDel==1){
            	delTitle="确认要删除吗?"
             }else{
            	delTitle="删除后将无法恢复,确认删除吗?"
             }
            $.messager.confirm('确认',delTitle,function(r){
                if (r){
                    $.post('baseInfo/deleteBaseInfo?baseInfoDel=' + baseInfoDel +urls,{baseInfoId:temID},function(data){
                    	if(data.status == 1){
                    		if(data.count!=0){
                    			alert("有"+data.count+"个已绑定了通讯录信息,请先从通讯列表回收站中彻底删除关联的通讯录信息!");
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
		queryAction(1,"","");
		//彻底删除数据
		$("#baseInfoDel").val(2);
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
                     temID = selRow[i].baseInfoId;  
                 } else {  
                     temID = selRow[i].baseInfoId + "," + temID;  
                 }                 
             }
            $.messager.confirm('确认','确定要恢复吗?',function(r){
                if (r){
                    $.post('baseInfo/deleteBaseInfo?baseInfoDel=0' + urls,{baseInfoId:temID},function(data){
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
		var baseInfoType = $("#baseInfoType1").val();
		//姓名
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		getValue=getValueFunction(getName, getValue);
		// 如果选择了出国时间，则判断输入的长度
		var abLen="";
		if(getName=='abroadEndTime'){
			if(getValue!=""){
				abLen=getValue.length;
			}
		}
		// 科研人员
		if(baseInfoType==0){//&extype=simple  原样导出excel 不分页
			var url = "ReportServer?reportlet=baseInfoType0.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue +"&abLen=" + abLen +"&format=excel&extype=simple&__filename__=" + cjkEncode("科研人员基本信息");
			window.open(url);
		// 行政人员
		}else if(baseInfoType==1){
			var url = "ReportServer?reportlet=baseInfoType1.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("行政人员基本信息");
			window.open(url);
		// 博士后
		}else if(baseInfoType==2){
			var url = "ReportServer?reportlet=baseInfoType2.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("博士后基本信息");
			window.open(url);
		// 博士
		}else if(baseInfoType==3){
			var url = "ReportServer?reportlet=baseInfoType3.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("博士基本信息");
			window.open(url);
		// 硕士
		}else if(baseInfoType==4){
			var url = "ReportServer?reportlet=baseInfoType4.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("硕士基本信息");
			window.open(url);
		// 其他人员
		}else{
			var url = "ReportServer?reportlet=baseInfoType5.cpt&baseInfoType=" + baseInfoType + "&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("其他人员基本信息");
			window.open(url);
		}
	}
	function getValueFunction(name, value){
        if(name=="baseInfoSex"){
            if(value=="女"){
               value=0; 
            }else if(value=="男"){
               value=1;
            }else{
               value=99;
            }
        }else if(name=="baseInfoEducation"){
            if(value=="博士"){
                value=0; 
            }else if(value=="硕士"){
                value=1; 
            }else if(value=="本科"){
                value=2;
            }else{
               value=99;
            }
        }else if(name=="baseInfoDegree"){
            if(value=="博士"){
                value=0; 
            }else if(value=="硕士"){
                value=1; 
            }else if(value=="学士"){
                value=2;
            }else{
               value=99;
            }
        }else if(name=="baseInfoPositionalTitles"){
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
        }else if(name=="baseInfoLevel"){
            if(value=="高级"){
                value=0; 
            }else if(value=="副高"){
                value=1; 
            }else if(value=="中级"){
                value=2; 
            }else if(value=="初级"){
                value=3;
            }else{
               value=99;
            }
        }
        return value;
    }
	
	function formatDate(now) {
		var year = now.getFullYear(),
		month = now.getMonth() + 1,
		date = now.getDate(),
		hour = now.getHours(),
		minute = now.getMinutes(),
		second = now.getSeconds();
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	
	// 上传材料
	function upload(){
		
		$("input",$("#uploadFileid").next("span")).text("");
		
		//获取选择上传数据id
		var row = $('#dg').datagrid('getSelected');
		//表名
		var tableName="bi_base_info"
		$("#relevanceId").val(row.baseInfoId);
		$("#tableName").val(tableName);
		uploadList(tableName,row.baseInfoId);
	}
	
	function uploadList(tableName,baseInfoId){
		//获取类型
		var baseInfoType=$("#baseInfoType1").val();
		$('#fileList').empty();
		// 首先根据id查询附件
		$.ajax({
			url:"common/fileList?relevanceId="+baseInfoId+"&tableName="+tableName,
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
						"<option value='0'>身份证</option>" +
						"<option value='1'>毕业证</option>" +
						"<option value='2'>学位证</option>" +
						"<option value='3'>兼职证明</option>" +
						"<option value='4'>人才计划证明</option>" +
						"<option value='5'>荣誉称号证明</option>" +
						"<option value='6'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist1"+i+"' style='display:none'>" +
						"<select id='selector1"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>身份证</option>" +
						"<option value='1'>毕业证</option>" +
						"<option value='2'>学位证</option>" +
						"<option value='3'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist2"+i+"' style='display:none'>" +
						"<select id='selector2"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>博士后证书</option>" +
						"<option value='1'>其他成果证明</option>" +
						"<option value='2'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist3"+i+"' style='display:none'>" +
						"<select id='selector3"+i+"' style='display:none' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>获奖证书</option>" +
						"<option value='1'>其他成果</option>" +
						"<option value='2'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist4"+i+"' style='display:none'>" +
						"<select id='selector4"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>获奖证书</option>" +
						"<option value='1'>其他成果</option>" +
						"<option value='2'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td id ='typelist5"+i+"' style='display:none'>" +
						"<select id='selector5"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:120px;border: 1px solid #BDC7D8;' readonly>"+
						"<option value=''>无</option>" +
						"<option value='0'>身份证</option>" +
						"<option value='1'>护照</option>" +
						"<option value='2'>聘书</option>" +
						"<option value='3'>合作协议</option>" +
						"<option value='4'>其他</option>" +
						"</select>"+
						"</td>"+
						"<td>" +
						"<input type='text' value='"+data[i].fileContent+"'style='border: 1px solid #BDC7D8;' readonly>"+
						"</td>" +
						"<td>" +
						""+t+""+
						"</td>" +
						"<td width='30px'>" +
						/*"<a href='javascript:downFile("+data[i].fileId+");' title='下载此附件'>下载" "</a>"++*/
						/*"<a href='javascript:viewFile("+data[i].fileId+");' title='查看'></a>"+
						"<a href='javascript:delFile("+data[i].fileId+");' title='删除'></a>"+*/
						"<a href='javascript:void(0)' onclick='downUploadFile(\""+data[i].fileId+"\")'>下载</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='viewFile(\""+data[i].filePath+"\")'>查看</a>&nbsp;"+
						"<a href='javascript:void(0)' onclick='delFile(\""+data[i].fileId+"\")'>删除</a>"+
						"</td>" +
						"</tr>");
						$("#typelist"+baseInfoType+i).show();
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
		var baseInfoType=$("#baseInfoType1").val();
		$("#typeBaseInfo"+baseInfoType).show();
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
		var baseInfoType=$("#baseInfoType1").val();//获取类型
		
		if(fileFullName!=""){
			$("#fmUpload").ajaxSubmit({
				type: "POST",
				url:"common/uploadFile?pathWay=baseinfo/sr"+baseInfoType+"&fileFullName="+fileFullName+"&tableName=bi_base_info&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent+urls,
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
		window.open("common/downloadFile?fileId="+fileId+ urls);
	}
	//删除文件
	function delFile(fileId){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
		 $.messager.confirm('确认',"确认要删除吗?",function(r){
             if (r){
                 $.post('common/delFile?fileId='+fileId+urls,function(data){
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
	
	//保存基本信息
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
    
	//当进入编辑页面时,需要手动赋值
	function textHtml(data,baseInfoType){
		//设置被选中的姓名
		$("#baseInfoName").val(data.baseInfoName);
		//设置被选中的性别
 		$("#baseInfoSex").val(data.baseInfoSex);
 		//设置被选中的身份证号
 		$("#baseInfoIdNumber").val(data.baseInfoIdNumber);
 		//设置被选中的出生日期
 		$("#baseInfoBirthDate").val(data.baseInfoBirthDate);
 		//设置被选中的籍贯
 		$("#baseInfoNativePlace").val(data.baseInfoNativePlace);
 		//设置被选中的民族
 		$("#baseInfoNation").val(data.baseInfoNation);
 		//设置被选中的政治面貌
 		$("#baseInfoPoliticalOutlook").val(data.baseInfoPoliticalOutlook);
 		//设置被选中的专业
 		$("#baseInfoSpecialty").val(data.baseInfoSpecialty);
 		//设置被选中的职称
 		$("#baseInfoPositionalTitles").val(data.baseInfoPositionalTitles);
 		//设置被选中的级别
 		$("#baseInfoLevel").val(data.baseInfoLevel);
 		//设置被选中的职务
 		$("#baseInfoPost").val(data.baseInfoPost);
 		//设置被选中的参加工作时间
 		$("#baseInfoStartWorkTime").val(data.baseInfoStartWorkTime);
 		//设置被选中的备注
 		$("#baseInfoRemarks").val(data.baseInfoRemarks);
 		//设置被选中的学历
 		$("#baseInfoEducation").val(data.baseInfoEducation);
 		//设置被选中的学位
 		$("#baseInfoDegree").val(data.baseInfoDegree);
		if(baseInfoType==0){
	 		//设置被选中的工行卡号
	 		$("#baseInfoCampusCard").val(data.baseInfoCampusCard);
	 		//设置被选中的工资卡
	 		$("#baseInfoPayrollCard").val(data.baseInfoPayrollCard);
	 		//设置被选中的人才计划
	 		$("#baseInfoTalentPlan").val(data.baseInfoTalentPlan);
	 		//设置被选中的荣誉称号
	 		$("#baseInfoHonoraryTitle").val(data.baseInfoHonoraryTitle);
	 		//设置被选中的是否博导
	 		$("#baseInfoIfDoctorTutor").val(data.baseInfoIfDoctorTutor);
	 		//设置被选中的是否硕导
	 		$("#baseInfoIfMasterTutor").val(data.baseInfoIfMasterTutor);
		}
		if(baseInfoType==2){
			//设置被选中的合作导师
	 		$("#baseInfoMentor").val(data.baseInfoMentor);
	 		//设置被选中的出站报告
	 		$("#baseInfoDepartureReport").val(data.baseInfoDepartureReport);
	 		//设置被选中的进站时间
	 		$("#baseInfoStartTime").val(data.baseInfoStartTime);
	 		//设置被选中的出站时间
	 		$("#baseInfoEndTime").val(data.baseInfoEndTime);
	 		//设置被选中的研究方向
	 		$("#baseInfoResearchDirection").val(data.baseInfoResearchDirection);
	 		//设置被选中的就业单位
	 		$("#baseInfoEmploymentCompany").val(data.baseInfoEmploymentCompany);
		}
		if(baseInfoType==3 || baseInfoType==4){
			//设置被选中的合作导师
	 		$("#baseInfoMentor").val(data.baseInfoMentor);
	 		//设置被选中的年级
	 		$("#baseInfoGrade").val(data.baseInfoGrade);
	 		//设置被选中的入学时间
	 		$("#baseInfoStartTime").val(data.baseInfoStartTime);
	 		//设置被选中的毕业时间
	 		$("#baseInfoEndTime").val(data.baseInfoEndTime);
	 		//设置被选中的就业单位
	 		$("#baseInfoEmploymentCompany").val(data.baseInfoEmploymentCompany);
	 		//设置被选中的学位论文题目
	 		$("#baseInfoContactInformation").val(data.baseInfoContactInformation);
	 		//设置被选中的荣誉
	 		$("#baseInfoHonor").val(data.baseInfoHonor);
	 		//设置被选中的成果
	 		$("#baseInfoAchievement").val(data.baseInfoAchievement);
		}
		if(baseInfoType==5){
			//设置被选中的国籍
	 		$("#baseInfoNationality").val(data.baseInfoNationality);
	 		//设置被选中的工资卡
	 		$("#baseInfoPayrollCard").val(data.baseInfoPayrollCard);
	 		//设置被选中的就业单位
	 		$("#baseInfoPrimaryWorkUnit").val(data.baseInfoPrimaryWorkUnit);
	 		//设置被选中的外聘称谓
	 		$("#baseInfoExternalAppellation").val(data.baseInfoExternalAppellation);
	 		//设置被选中的聘用开始日期
	 		$("#baseInfoEngageStartTime").val(data.baseInfoEngageStartTime);
	 		//设置被选中的聘用截止日期
	 		$("#baseInfoEngageEndTime").val(data.baseInfoEngageEndTime);
	 		//设置被选中的个人简介
	 		$("#baseInfoProfile").val(data.baseInfoProfile);
		}
	}
	
	//点击新增和编辑按钮时，动态显示文本内容
	function pageDisplay(data,baseInfoType){
		// 当用户角色为管理员时，添加科研人员、行政人员和其他人员时，显示选择“账号”
		var role=$("#role").val();
		//当为博士后时,显示博士后信息
		
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoType==0){
			$('#Staff3').show();
			$('#Staff4').empty();
			$('#Staff2').empty();
			$('#Staff1').empty();
			$('#Staff5').empty();
			if(role==0 && baseInfoPath==0){
				$("#atr0").show();
			}
		}else if(baseInfoType==1){
			$('#Staff4').show();
			$('#Staff3').empty();
			$('#Staff2').empty();
			$('#Staff1').empty();
			$('#Staff5').empty();
			if(role==0 && baseInfoPath==0){
				$("#atr1").show();
			}
		}else if(baseInfoType==2){
			$('#Staff2').show();
			$('#Staff4').empty();
			$('#Staff3').empty();
			$('#Staff1').empty();
			$('#Staff5').empty();
		}else if(baseInfoType==3 || baseInfoType==4){
			$('#Staff1').show();
			$('#Staff4').empty();
			$('#Staff2').empty();
			$('#Staff3').empty();
			$('#Staff5').empty();
		}else{
			$('#Staff5').show();
			$('#Staff4').empty();
			$('#Staff2').empty();
			$('#Staff1').empty();
			$('#Staff3').empty();
			if(role==0 && baseInfoPath==0){
				$("#atr2").show();
			}
		}
		
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
			//拼接页面(社会兼职)
			var social=$("#socialAppointments").append("<div class='headCaption'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>社会兼职</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addJob(event)'>添加</a></td>" +
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
			var abroad=$("#abroadExperience").append("<div class='headCaption'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>出国经历</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addAbroad(event)'>添加</a></td>" +
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
			$.parser.parse(social );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			$.parser.parse(abroad );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
		}
		//科研人员和行政人员的新增和编辑页面
		if(baseInfoType==0 || baseInfoType==1){
			//拼接页面(教育经历)
			var educational=$("#educationalExperience").append("<div class='headCaption'>" +
					"<table width='100%'><tbody>" +
					"<tr>" +
					"<td><h2>教育经历</h2></td>" +
					"<td width='60px'><a href='javascript:void(0)' class='easyui-linkbutton' iconcls='icon-add'" +
					" data-options='plain:true' onclick='addEducational(event)'>添加</a></td>" +
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
			var work=$("#workExperience").append("<div class='headCaption'>" +
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
			$.parser.parse(educational );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
			$.parser.parse(work );//表示对整个页面重新渲染，渲染完就可以看到easyui原来的样式了；
		}
	}
	
	//教育经历====================================================================================================
	//教育经历索引
	var educationalNum=0;
	//添加教育经历
	function addEducational(event){
		//判断是从新增页 还是从编辑页添加
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoPath==1){
			//获取已有条数
			var tbodys = $("#educationalId").find("tbody");
			educationalNum=tbodys.length;
		}
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
		updateDomCss(2, $($($(event.currentTarget).parents(".headCaption")[0]).next().children()[0]), educationalNum);
		
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
		//判断是从新增页 还是从编辑页添加
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoPath==1){
			//获取已有条数
			var tbodys = $("#workId").find("tbody");
			workNum=tbodys.length;
		}
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
		updateDomCss(1, $($($(event.currentTarget).parents(".headCaption")[0]).next().children()[0]), workNum);
		
		// 下标累加
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
					"<input type='text' name='biWorks[" + i + "].workStartTime' value='"+data.biWorks[i].workStartTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"~"+
					"<input type='text' name='biWorks[" + i + "].workEndTime' value='"+data.biWorks[i].workEndTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biWorks[" + i + "].workCompany' value='"+data.biWorks[i].workCompany+"'  class='easyui-textbox' style='width:45px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biWorks[" + i + "].workPost' value='"+data.biWorks[i].workPost+"' class='easyui-textbox' style='width:86px'>"+
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
		//判断是从新增页 还是从编辑页添加
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoPath==1){
			//获取已有条数
			var tbodys = $("#jobId").find("tbody");
			jobNum=tbodys.length;
		}
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
		updateDomCss(3, $($($(event.currentTarget).parents(".headCaption")[0]).next().children()[0]), jobNum);
		
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
					"<select class='easyui-combobox' id='jobMechanismType"+i+"' name='biJobs[" + i + "].jobMechanismType' value='"+data.biJobs[i].jobMechanismType+"' panelMaxHeight='70px' style='width:65px'>"+
					"<option value=''>请选择</option>" +
					"<option value='0'>国内兼职</option>" +
					"<option value='1'>国外兼职</option>" +
					"</select>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobStartTime' value='"+data.biJobs[i].jobStartTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"~"+
					"<input type='text' name='biJobs[" + i + "].jobEndTime' value='"+data.biJobs[i].jobEndTime+"' class='easyui-datebox EdiInputText' style='width:88px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobMechanism' value='"+data.biJobs[i].jobMechanism+"' class='easyui-textbox' style='width:45px'>"+
					"</td>"+
					"<td>" +
					"<input type='text' name='biJobs[" + i + "].jobPost' value='"+data.biJobs[i].jobPost+"' class='easyui-textbox' style='width:86px'>"+
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
		//判断是从新增页 还是从编辑页添加
		var baseInfoPath=$('#baseInfoPath').val();
		if(baseInfoPath==1){
			//获取已有条数
			var tbodys = $("#abroadId").find("tbody");
			abroadNum=tbodys.length;
		}
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
		updateDomCss(4, $($($(event.currentTarget).parents(".headCaption")[0]).next().children()[0]), abroadNum);
		
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
			$($(tds[1]).children()[1]).width("115");
			$($($(tds[1]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 89px;");
			$($(tds[1]).children()[3]).width("115");
			$($($(tds[1]).children()[3]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 89px;");
			$($(tds[2]).children()[1]).width("168");
			$($($(tds[2]).children()[1]).children()[0]).width("160");
			$($(tds[3]).children()[1]).width("97");
			$($($(tds[3]).children()[1]).children()[1]).attr("style", "padding-top: 0px; padding-bottom: 0px; height: 22px; line-height: 22px; width: 70px;");
		}
	}
