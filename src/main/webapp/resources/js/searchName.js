	// 根据选中人员类型,查询该类型下的人员
	function searchByType(type,name,idIndex){
		var urls = getPowerMenuId();
		// 当name不为空时,将name的值放到隐藏域中
		if(name!=""){
			$("#name").val(name);
		}else{
			name=$("#name").val();
		}
		// 合作者:选择人员的索引
		if(idIndex!=""){
			$("#controllerNum").val(idIndex);
		}
		p=0;
		$.ajax({
			url:"baseInfo/baseInfoNames?baseInfoType="+type+urls,
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
	        		//获取索引
	        		idIndex=$("#controllerNum").val();
	        		if(idIndex!=""){
	        			idIndex=idIndex.substr(10, idIndex.length);
	        		}
	            	for(var i=0;i<data.length;i++){
	            		if(i%10==0){
	            			n++;
	            			var social=$("#personId").append("<tr id='ptr"+n+"'>" +
		        					"<td id='tdname"+i+"' class='baseInfoNameTd' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>" +
		        					"<span id='spanname"+i+"' class='baseInfoNameSpan' onclick='subName(this.id,\""+name+"\",\""+idIndex+"\")'>"+data[i].baseInfoName+ "</span>"+
		        					"</td>" +
		        					"<td id='tdid"+i+"' style='display:none'>"+data[i].baseInfoId+"</td>" +
		        					"</tr>");
	            			
	            		}else{
	            			var social=$("#ptr"+n).append("<td id='tdname"+i+"' class='baseInfoNameTd' onmousemove='moveName(this.id)'; onmouseout ='outName(this.id)'>"+
	            					"<span id='spanname"+i+"' class='baseInfoNameSpan' onclick='subName(this.id,\""+name+"\",\""+idIndex+"\")'>"+data[i].baseInfoName+ "</span>"+
	            					"</td>"+
	            					"<td id='tdid"+i+"' style='display:none'>"+data[i].baseInfoId+"</td>");
	            		}
	            	}
	            	
	    	    	$("#plg").dialog("open");
	    	    	var idVal = $(".selected").attr("id");
	    	    	$("#"+idVal).removeClass("selected").addClass('cata-item'); 
	    			//将本次选择的人员类型选中
	    			$("#type"+type).addClass("selected");
	    	    	p=1;
				}
			}
		});
	}
	
	//选择某一人员后,获取选中人员的姓名和id,放到新增页面的文本框中
	function subName(id,name,idIndex){
		var baseInfoName=$("#"+id).text();
		var baseInfoId=$("#"+id).parent().next().text();
		if(idIndex!=""){
			// num=1合作者
			$("#baseInName"+idIndex).val(baseInfoName);
			$("#baseInId"+idIndex).val(baseInfoId);
		}else{
			//授课信息：指导学生姓名,当选择学生后，保留之前的学生名称
			if(name=="teachingStudentName"){
				var v=$("#"+name).textbox("getValue");
				if(v!=""){
					$("#"+name).textbox("setValue", v+","+baseInfoName);
				}else{
					$("#"+name).textbox("setValue", baseInfoName);
				}
			}else{
				$("#"+name).textbox("setValue", baseInfoName);
				$("#"+name+"Id").val(baseInfoId);
			}
		}
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
	
	
	
	/**
	 * 列表页新增弹框居中
	**/
	function dlgCenter(){
		//获取新增弹出div的父div
		$("#dlg").parent().css({
			"top":"50%",
			"left":"50%",
			"width":"795px",
			"height":"367px",
			"margin-top":"-205px",
			"margin-left":"-397px",
			"position":"absolute"
		});
		$("#dlg").parent().next().css({
			"top":"50%",
			"left":"50%",
			"width":"795px",
			"height":"376px",
			"margin-top":"-205px",
			"margin-left":"-397px",
			"position":"absolute"
		});
	}
	
