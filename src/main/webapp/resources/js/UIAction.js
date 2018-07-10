// JavaScript Document
// 页面元素动作事件绑定


$(document).ready(function(e) {
	
	//禁用浏览器默认右键菜单
	document.oncontextmenu =function(e){
		if($(e.target).is('input')||$(e.target).is('textarea'))
			return true;
		else
			return false;
	}

	window.onbeforeunload = function(){
		if($('#ModuleXML').data('manual') != true){
			return "您确定离开系统？";
		}
  	}

	//禁用所有超链接默认动作
	$('a:not(.link)').attr('href','javascript:void(0);');
	$('body').on('selectstart', '.datagrid.panel',function(){
		return false;
	});
	var titletmp;
	$('#TabPannel').tabs({
		onSelect:function(title){
			},
		onBeforeClose:function()
			{
				}
		});

	//顶部右侧工具栏事件
	$('.headRight').on('click', 'a',function(){
		$('#topFunction').data('manual', true);
			if($(this).text().replace(/(^\s*)|(\s*$)/g, "")=='修改密码1'){
				var $Change_pass=$('<div id="Change_pass" style="height:100%;width:100%"></div>');
				$Change_pass.appendTo('body');

				$Change_pass.load('/Modules/Forms/change_pass.html',function(){
					$.parser.parse($Change_pass);
						$Change_pass.dialog({
							title:'修改密码1',
							width:630,  
							height:240,
							buttons:[{
								id:'ok',
								iconCls:'icon-ok',
								text:'确定',
								handler:function(){
									var result =$Change_pass.find('#result');
									var FormValidFlag=true;
									$Change_pass.find('.easyui-validatebox').each(function(index, element) {
										var tmpFlag=$(this).validatebox('validate').validatebox('isValid');
										if(!tmpFlag)
										   FormValidFlag=false;
									});
									if(!FormValidFlag){
										return false;
									}
									if($Change_pass.find("#new_pass").val()!=$("#con_pass").val()){
										$(result).html('<font color="red"> 密码输入不一致！</font>');
										return false;
									}

									$Change_pass.find('.dialog-content').ajaxloading('正在提交...');
									$.ajax({
										url:'/Modules/public/change_pwd.asp',
										data:'oldpass='+$Change_pass.find('#old_pass').val()+"&newpass="+$Change_pass.find("#new_pass").val(),
										type:'post',
										success:function(data){
											$Change_pass.find('.dialog-content').ajaxloading('loaded');
											if(data=='success'){
												$.messager.alert("系统提示","密码修改成功！");
												$Change_pass.dialog("close");
											}else if(data=='old_pass_error'){
												$.messager.alert("系统提示","原密码错误！");
											}else{
												$.messager.alert("系统提示","修改失败！");
											}
										}
									});
								}
							},{
								id:'cancel',
								iconCls:'icon-cancel',
								text:'取消',
								handler:function(){
									$Change_pass.dialog('close');
								}
							}],
							onClose:function(){
								$Change_pass.dialog('destroy').remove();
							},
							collapsible:false,
							minimizable:false,
							maximizable:false,
							modal:true
						}).dialog('open');
					}
				);
			//修改密码结束
			}else if ($(this).text().replace(/(^\s*)|(\s*$)/g, "")=='退出登录'){
				/*$.messager.confirm('退出确认','确定退出本系统吗?',function(r){
					if (r){
						$.messager.alert("系统消息","已安全退出");
						window.location.href='/login.action';
					}
				});*/
				
			}else if($(this).text().replace(/(^\s*)|(\s*$)/g, "")=='使用帮助'){
				$(this).attr('href','/help').attr('target','_blank');
			}else if($(this).is('.switch-base')){
				if($(this).attr("id")=="choose_base"){
					$(this).getBase();
				}else if($(this).attr("id")=="choose_base_byfieldID"){
					if(localStorage.contract == 'contract'){
						$(this).getBaseByfieldID();
					}
				}
			}
		});  

	//中部区域面板标题栏变化

	// 首页frame 面板刷新
	$('.reload-frame').click(function(){
		var frame = $(this).parents('.panel_title').next('.center_pannel').find('iframe')[0];
		$(frame).ajaxloading({msg:"正在刷新数据"});
		frame.src=frame.src;
		$(frame).ajaxloading('loaded');
	});

	//绑定中央Tabs右键事件
	//bindTabEvent();
	//bindTabMenuEvent();

	//基地简况内容块动作定义
	//编辑按键加载表单
	$("body").on('click','.base_info_head a',function(){
		var $this=$(this);
		$this.parents("#content").hide();
		if(!$('#'+$(this).parents('.base_information').attr('id')+'.editForm').html()==''){
			return false;
		}else{
			//打开窗口,加载表单文件
			$.messager.progress({
				title:'提示',
				msg:'数据加载中,请稍候..',
				text:'Loading...',
				interval:50
			});
			$("<div style='display:none'></div>").appendTo($(this).parents(".base_information"))
				.attr('id',$(this).parents('.base_information').attr('id'))
				.attr('class','editForm')
				.load("/Modules/forms/BaseInfo.asp #"+$(this).parents('.base_information').attr('id'),
					function(){
						$.messager.progress('close');
						$(this).slideDown(800);
						$this.parents('.base_information').find('.FormBlock').each(function(index, element) {
                            //alert($('#ModuleXML').attr('BaseID'));
							$(this).find('.input_text,.input_easy').attr('i',$('#ModuleXML').attr('BaseID'));
							$(this).initialize({idValue:$('#ModuleXML').attr('BaseID')})
								.initValue({mode:'edit'})
                        });	
					});
		}
    });

	$('body').on('click','.FormBlock .input_submit.save',function(){
		$(this).parents('.FormBlock').submitForm({mode:'edit',type:'baseinfo',reURL:$(this).attr('reURl')});
	});

	$('body').on('click', '.FormBlock .input_submit.cancel',function(){
		$(this).parents('.editForm').prev('#content').show();
		$(this).parents('.editForm').remove();
		$('.validatebox-tip').hide();
		});

	$("body").on('click', 'form .FormBlock h3',function(){
		$(this).toggle(function(){
				$(this).siblings().hide();
				$(this).css('background','url("/Image/switch.png") no-repeat scroll right top #F0F5F8')
			},function(){
				$(this).siblings().show();
				$(this).css('background','url("/Image/switch.png") no-repeat scroll right -25px #F0F5F8')
		});
	});

	$('body').on('load','.validatebox-tip:visible',function(){
			setTimeout(function(){$(this).hide()},2000);
		});

	setInterval(function(){
		$.ajax({
			url:'/Modules/public/alivesession.asp',
			success:function(data){}
		});
	},600000);

	if($('#ModuleXML').attr('role')=='expert'){
		$('#Nav_1014').text('成果评价');
	}

	/*重新加载frame面板绑定*/
	$('.reload .easyui-panel').each(function(){
		var $this = $(this);
		$this.panel({
            tools: [{
                iconCls: 'icon-reload',
                handler: function () {
                	$this.panel('refresh');
                }
            }]
        });
	});

	/*主选项卡自动刷新事件绑定*/
	$('#TabPannel').tabs({
		onSelect: function(title){
			var panel = $('#TabPannel').tabs('getTab', title);
			$(panel).find('.easyui-panel[href]:visible').panel({
				onLoad:function(){
					$(this).panel('resize');
				}
			}).panel('refresh');

			if(title=='评审指南'){
				$(panel).find('.easyui-layout').layout('resize');
			}
		}
	});

	/*窗口大小调整时自动适应各tab内容*/
	$(window).resize(function(){
		setTimeout(function(){
			var tabTitle = $('#TabPannel').find('.tabs-selected').text();
			$('#TabPannel').tabs('select', tabTitle);
		}, 500);
	});
});
