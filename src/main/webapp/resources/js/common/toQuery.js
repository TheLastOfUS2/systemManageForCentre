	var p=0;	
	var urls = "";
	$(document).ready(function() { 
		//当"责任人姓名"文本框被触发时,去后台获取人员列表
		$("input",$("#baseInfoName").next("span")).click(function(){
			if(p==0){
				searchByType(0,"baseInfoName","");
			}
		});
	});
	
	function query(){
	   // 获取选择的数据查找范围
	   var spCodesTemp = "";
       $('input:checkbox[name=fanweiType]:checked').each(function(i){
	       if(0==i){
	        spCodesTemp = $(this).val();
	       }else{
	        spCodesTemp += (","+$(this).val());
	       }
       });
      var typeLen= $('input:checkbox[name=fanweiType]:checked').length;
      if(typeLen==0){
    	  alert("请选择数据查找范围");
    	  return;
      }
       // 获取选择的责任人类型
       var baseInfoType = "";
       $('input:checkbox[name=baseInfoType]:checked').each(function(i){
	       if(0==i){
	    	   baseInfoType = $(this).val();
	       }else{
	    	   baseInfoType += (","+$(this).val());
	       }
       });
       // 获取选择的责任人职称
	   var positionalTitleType = "";
       $('input:checkbox[name=positionalTitleType]:checked').each(function(i){
	       if(0==i){
	    	   positionalTitleType = $(this).val();
	       }else{
	    	   positionalTitleType += (","+$(this).val());
	       }
       });
       // 获取选择的责任人级别
       var levelType = "";
       $('input:checkbox[name=levelType]:checked').each(function(i){
	       if(0==i){
	    	   levelType = $(this).val();
	       }else{
	    	   levelType += (","+$(this).val());
	       }
       });
       
       // 获取选择的责任人年龄区间
       var baseInfoStartAge = $("#baseInfoStartAge").val();
       var baseInfoEndAge = $("#baseInfoEndAge").val();
       
       //成果/项目名称
       var projectName= $('#projectName').searchbox('getValue');
       // 成果时间区间
       var projectStartTime = $("#projectStartTime").val();
       var projectEndTime = $("#projectEndTime").val();
       
       $("#dlgQuery").dialog({  
   		title: "查询结果",
			iconCls : "icon-search",
			collapsible: true,
            minimizable: true,
            maximizable: true,
            resizable: true,
			width: 1095,
			height:610, 
			top: 2,
			left: 40,
			onClose: function(){
			   closeAll();
			   win.window('destroy');//关闭即销毁
			},
			onOpen:function(){
				//获取查询条件
				var queryParam="";
				// 责任人姓名
				var baseInfoName=$("#baseInfoName").textbox("getValue");
				if(baseInfoName!=""){
					queryParam="&qBaseInfoName="+baseInfoName;
				}
				// 责任人类别
				if(baseInfoType!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&baseInfoType="+baseInfoType;
					}
				}
				// 责任人职称
				if(positionalTitleType!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&baseInfoPositionalTitles="+positionalTitleType;
					}	
				}
				// 责任人级别
				if(levelType!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&baseInfoLevel="+levelType;
					}	
				}
				// 责任人年龄区间
				if(baseInfoStartAge!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&baseInfoStartAge="+baseInfoStartAge;
					}	
				}
				if(baseInfoEndAge!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&baseInfoEndAge="+baseInfoEndAge;
					}	
				}
				// 成果/项目名称
				if(projectName!=""){
					if(queryParam!=""){
						queryParam=queryParam+"&projectName="+projectName;
					}	
				}
				// 成果时间区间
				if(projectStartTime!=""){
					queryParam=queryParam+"&projectStartTime="+projectStartTime;
				}
				if(projectEndTime!=""){
					queryParam=queryParam+"&projectEndTime="+projectEndTime;
				}
				InitLeftMenu2(spCodesTemp,queryParam);
			},
	   	});
	   	$("#dlgQuery").dialog("open");
	}
	
	//关闭所有的tab  
    function closeAll(){  
        var tiles = new Array();  
          var tabs = $('#tabsQuery').tabs('tabs');    
          var len =  tabs.length;           
          if(len>0){  
            for(var j=0;j<len;j++){  
                var a = tabs[j].panel('options').title;               
                tiles.push(a);  
            }  
            for(var i=0;i<tiles.length;i++){               
                $('#tabsQuery').tabs('close', tiles[i]);  
            }  
          }  
    } 
    
    // 查询条件重置
    function clearQuery(){
    	// 清空责任人姓名
    	$("#baseInfoName").textbox("setValue","");
    	$("#baseInfoNameId").val("");
    	
    	// 清空责任人类型
    	$('input:checkbox[name=baseInfoType]:checked').each(function(i){
    		$(this).attr("checked", false); 
        });
    	
    	// 清空责任人职称
    	$('input:checkbox[name=positionalTitleType]:checked').each(function(i){
    		$(this).attr("checked", false); 
        });
    	
    	// 清空责任人级别
    	$('input:checkbox[name=levelType]:checked').each(function(i){
    		$(this).attr("checked", false); 
        });
    	// 清空责任人年龄区间
    	$("#baseInfoStartAge").textbox("setValue","");
    	$("#baseInfoEndAge").textbox("setValue","");
    	
    	// 清空成果时间区间
    	$("#projectStartTime").textbox("setValue","");
    	$("#projectEndTime").textbox("setValue","");
    	
    	// 清空著作名称
    	$("#projectName").textbox("setValue","");
    	
    	// 清空数据查找范围
    	$('input:checkbox[name=fanweiType]:checked').each(function(i){
    		$(this).attr("checked", false); 
        });
    	
    }
	
