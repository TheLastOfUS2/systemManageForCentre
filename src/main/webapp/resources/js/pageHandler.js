	var _menus = {};
	// 页面板块加载处理器
	$(document).ready(function(e) {
		// ===============================================
		/*
		 * 修改源码
		 * 用于展示主菜单和左侧菜单项
		 * 获取隐藏域中用户的权限菜单数据并创建menus对象
		 * 2017-07-19
		 */
		// 获取隐藏域中用户的权限菜单数据
		var power = $("#power").val();
		// 设置菜单权限
		_menus = getPower(power);
		
		$("#TopNavBar li").each(function() {
			if(power.indexOf("," + $(this).attr("id") + ",") < 0) {
				$(this).attr("style","display:none;");
			}
		});
		
		// ===============================================
		
		tabClose();
		tabCloseEven();
		//顶部功能栏点击事件
		$('#TopNavBar a').click(function() {
			//改变点击功能栏的背景样式
			$('#TopNavBar li').removeClass('liid');
			$(this).parent().addClass('liid');
			
			//xml中获取与name相同值的左侧导航列表xml
			var d = _menus[$(this).attr('name')];//$(this).attr('name')获取点击功能的name属性
			Clearnav();//删除左侧所有的导航
			
			//  $("#wnav").empty();
			addNav(d);//添加左侧的导航(d:传递获取到的要加载的导航列表)
			//初始化左侧
			InitLeftMenu();
		});
		// 导航菜单绑定初始化
		$("#wnav").accordion( {
			animate : false
		});
		//获取初始化绑定导航菜单的name属性
		var firstMenuName = $('#TopNavBar a:first').attr('name');
		//加载左侧导航xml
		addNav(_menus[firstMenuName]);
		//初始化左侧
		InitLeftMenu();
	});

	function tabClose() {
		/* 双击关闭TAB选项卡 */
		$(".tabs-inner").dblclick(function() {
			var subtitle = $(this).children(".tabs-closable").text();
			//关闭选项卡
			$('#tabs').tabs('close', subtitle);
			//关闭查询页面选项卡
			$('#tabsQuery').tabs('close', subtitle);
		});
		/* 为选项卡绑定右键 */
		$(".tabs-inner").bind('contextmenu', function(e) {
			$('#mmTab').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
			var subtitle = $(this).children(".tabs-closable").text();
			$('#mmTab').data("currtab", subtitle);
			$('#tabs').tabs('select', subtitle);
			$('#tabsQuery').tabs('select', subtitle);
			return false;
		});
	}
	
	//绑定右键菜单事件
	function tabCloseEven() {
		// 刷新
		$('#mm-tabupdate').click(function() {
			var currTab = $('#tabs').tabs('getSelected');
			var url = $(currTab.panel('options').content).attr('src');
			$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(url)
				}
			});
		});
		// 关闭当前
		$('#mm-tabclose').click(function() {
			var currtab_title = $('#mmTab').data("currtab");
			$('#tabs').tabs('close', currtab_title);
		});
		// 全部关闭
		$('#mm-tabcloseall').click(function() {
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				$('#tabs').tabs('close', t);
			});
		});
		// 关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function() {
			$('#mm-tabcloseright').click();
			$('#mm-tabcloseleft').click();
		});
		// 关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function() {
			var nextall = $('.tabs-selected').nextAll();
			if (nextall.length == 0) {
				// msgShow('系统提示','后边没有啦~~','error');
				//alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
		// 关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			if (prevall.length == 0) {
				//alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
	
		// 退出
		$("#mm-exit").click(function() {
			$('#mmTab').menu('hide');
		});
	}	
	
	//删除左侧导航部分
	function Clearnav() {
		//每次删除完都要重新获取左侧导航面板的个数
		//获取左侧导航的面板并删除
		var pp = $('#wnav').accordion('panels');
			for(var i=0;pp.length>0;){
				if(pp[i]){
					var t = pp[i].panel('options').title;
					$('#wnav').accordion('remove', t);
				}
			}
		
		/*$.each(pp, function(i, n) {
			if (n) {
				var t = n.panel('options').title;
				$('#wnav').accordion('remove', t);
			}
			if(i!=0){
				i--;
			}
		});*/
		
		//获取第一个功能对象，删除左侧导航的面板
		/*pp = $('#wnav').accordion('getSelected');
		if (pp) {
			var title = pp.panel('options').title;
			$('#wnav').accordion('remove', title);
		}*/
	}
	
	//添加左侧导航
	function addNav(data) {
		//遍历点击功能下的所有的左侧导航数据
		$.each(data, function(i, sm) {//sm:获取的一级导航数据
			var menulist = "";
			menulist += '<div class="panel-body accordion-body" collapsed="false" style="overflow: hidden; width: 206px; height: 102px;">';
			$.each(sm.menus, function(j, o) {//遍历一级导航下的所有下级导航
				// 根据角色判断显示列表页还是明细页=======
				menulist += '<div class="BtnDiv">'
				+'<a href="#" ref="' + o.menuid + '"  rel="'
				+ o.url + '" ><span class="icon ' + o.icon
				+ '" >&nbsp;</span><span class="nav">' + o.menuname
				+ '</span></a></div>'; 
			});
			menulist += '</div>';
			//将封装好的信息向左侧导航添加折叠面板
			$('#wnav').accordion('add', {
				title : sm.menuname,//一级标题
				content : menulist,//存放大标题下的小标题
				//iconCls : 'icon ' + sm.icon
			});
		});
		
		//获取面板的一级功能标题，设置为选中状态
		var pp = $('#wnav').accordion('panels');
		var t = pp[0].panel('options').title;
		$('#wnav').accordion('select', t);
	}
	
	//初始化左侧
	function InitLeftMenu() {
		hoverMenuItem();
		//二级导航点击事件
		$('#wnav div a').live('click', function() {
			var tabTitle = $(this).children('.nav').text();
			var url = $(this).attr("rel");
			var menuid = $(this).attr("ref");//二级导航的id
			//截取二级导航的id的第一位
			var mFirst=menuid.substring(0,1);
			//根据二级导航的id获取一级导航title
			$("li").each(function(){
				if(mFirst==$(this).attr("id")){
					tabTitle = $(this).children("a").text()+"-"+tabTitle;
				}
			});
			var icon = getIcon(menuid, icon);
			//添加tabs
			addTab(tabTitle, url, icon);
			$('#wnav div').removeClass("selected");
			$(this).parent().addClass("selected");
		});
	}
	
	//初始化数据查询按钮
	function InitLeftMenu1(url) {
		hoverMenuItem();
		//二级导航点击事件
		var tabTitle = "数据查询";
		var icon = getIcon(0, icon);
		//添加tabs
		addTab(tabTitle, url, icon);
		$('#wnav div').removeClass("selected");
		$(this).parent().addClass("selected");
	}
	
	//初始化搜索
	function InitLeftMenu2(spCodesTemp,queryParam) { 
		hoverMenuItem();
		spCodesTemp=spCodesTemp.split(",");
		for(var i=0;i<spCodesTemp.length;i++){
			var tabTitle = spCodesTemp[i];
			var url;
			if(tabTitle=="著作"){
				url = "opus/toOpusList?&menuid=b21";
			}else if(tabTitle=="论文"){
				url = "paper/toPaperList?menuid=b22&path=1";
			}else if(tabTitle=="获奖"){
				url = "prize/toPrizeList?menuid=b23";
			}else if(tabTitle=="采纳批示"){
				url = "adopt/toAdoptList?menuid=b24";
			} 
			if(tabTitle!="项目"){
				//添加tabs
				url=url+"?"+queryParam;
				addTab1(tabTitle, url, 'icon-nav');
				$('#wnav div').removeClass("selected");
				$(this).parent().addClass("selected");
			}else{
				for(var j=0;j<3;j++){
					url = "project/toProjectList?projectType="+j+queryParam+"&menuid=b11";
					if(j==0){
						tabTitle="横向项目";
					}else if(j==1){
						tabTitle="纵向项目";
					}else{
						tabTitle="国际合作项目";
					}
					//添加tabs
					addTab1(tabTitle, url, 'icon-nav');
					$('#wnav div').removeClass("selected");
					$(this).parent().addClass("selected");
				}
			}
			
		}
	}
	
	//添加tabs
	function addTab1(subtitle, url, icon) {
		if (!$('#tabsQuery').tabs('exists', subtitle)) {
			$('#tabsQuery').tabs('add', {
				title : subtitle,
				content : createFrame(url),
				closable : true,
				icon : icon
			});
		} else {
			$('#tabsQuery').tabs('select', subtitle);
			$('#mm-tabupdate').click();
		}
		tabClose();
	}
	
	/**
	 * 菜单项鼠标Hover
	 */
	function hoverMenuItem() {
		$(".easyui-accordion").find('a').hover(function() {
			$(this).parent().addClass("hover");
		}, function() {
			$(this).parent().removeClass("hover");
		});
	}
	
	//获取左侧导航的图标
	function getIcon(menuid) {
		var icon = 'icon ';
		$.each(_menus, function(i, n) {
			$.each(n, function(j, o) {
				$.each(o.menus, function(k, m){
					if (m.menuid == menuid) {
						icon += m.icon;
						return false;
					}
				});
			});
		});
		return icon;
	}
	
	//添加tabs
	function addTab(subtitle, url, icon) {
		if (!$('#tabs').tabs('exists', subtitle)) {
			$('#tabs').tabs('add', {
				title : subtitle,
				content : createFrame(url),
				closable : true,
				icon : icon
			});
		} else {
			$('#tabs').tabs('select', subtitle);
			$('#mm-tabupdate').click();
		}
		tabClose();
	}
	//在center区域创建iframe加载页面
	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0" framespacing="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
		return s;
	}

