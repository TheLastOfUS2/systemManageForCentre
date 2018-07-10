/*
 * 用户权限菜单树共通文件
 * 2017-07-19
 */

// 所有二级菜单项
var second_menus = {
	"a1": {
		"menuid" : "a1",
		"icon" : "icon-sys",
		"class":"titleCss",
		"menuname" : "个人基本信息",
		"menus" : []
		},
	"a2": {
		"menuid" : "a2",
		"icon" : "icon-sys",
		"menuname" : "通讯录",
		"menus" : []
		},
	"b1": {
		"menuid" : "b1",
		"icon" : "icon-sys",
		"menuname" : "科研项目",
		"menus" : []
		},
	"b2": {
		"menuid" : "b2",
		"icon" : "icon-sys",
		"menuname" : "学术成果",
		"menus" : []
		},
	"b3": {
		"menuid" : "b3",
		"icon" : "icon-sys",
		"menuname" : "学术交流",
		"menus" : []
		},
	"b4": {
		"menuid" : "b4",
		"icon" : "icon-sys",
		"menuname" : "人才培养",
		"menus" : []
		},
	"b5": {
		"menuid" : "b5",
		"icon" : "icon-sys",
		"menuname" : "咨政服务",
		"menus" : []
		},
	"b6": {
		"menuid" : "b6",
		"icon" : "icon-sys",
		"menuname" : "对外合作",
		"menus" : []
		},
	"b7":  {
		"menuid" : "b7",
		"icon" : "icon-sys",
		"menuname" : "对外宣传",
		"menus" : []
		},
	"b8": {
		"menuid" : "b8",
		"icon" : "icon-sys",
		"menuname" : "各类统计报表及其他资料",
		"menus" : []
		},
	"c1": {
		"menuid" : "c1",
		"icon" : "icon-sys",
		"menuname" : "管理工作",
		"menus" : []
		},
	"d1": {
		"menuid" : "d1",
		"icon" : "icon-sys",
		"menuname" : "中心各项活动",
		"menus" : []
		},
	"e1": {
		"menuid" : "e1",
		"icon" : "icon-sys",
		"menuname" : "共享文件",
		"menus" : []
		},
	"f1": {
		"menuid" : "f1",
		"icon" : "icon-sys",
		"menuname" : "权限管理",
		"menus" : []
		}
};

// 所有三级菜单项
var third_menus = {
	"a11": {
		"menuid" : "a11",
		"menuname" : "科研人员",
		"icon" : "icon-nav",
		"class":"BtnDiv",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=0&menuid=a11"
		},
	"a17": {
		"menuid" : "a17",
		"menuname" : "科研人员",
		"icon" : "icon-nav",
		"class":"BtnDiv",
		"url" : "baseInfo/toBaseInfoView?baseInfoType=0&menuid=a17"
		},
	"a12":{
		"menuid" : "a12",
		"menuname" : "行政人员",
		"icon" : "icon-nav",
		"class":"BtnDiv",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=1&menuid=a12"
		},
	"a13": {
		"menuid" : "a13",
		"menuname" : "博士后",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=2&menuid=a13"
		},
	"a14": {
		"menuid" : "a14",
		"menuname" : "博士",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=3&menuid=a14"
		},
	"a15": {
		"menuid" : "a15",
		"menuname" : "硕士",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=4&menuid=a15"
		},
	"a16": {
		"menuid" : "a16",
		"menuname" : "其他人员",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBaseInfoList?baseInfoType=5&menuid=a16"
		},
	"a18": {
		"menuid" : "a18",
		"menuname" : "其他人员",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBaseInfoView?baseInfoType=5&menuid=a18"
		},	
	"a21": {
		"menuid" : "a21",
		"menuname" : "科研人员 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=0&menuid=a21"
		},
	"a27": {
		"menuid" : "a27",
		"menuname" : "科研人员 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toMailView?baseInfoType=0&menuid=a27"
		},
	"a22": {
		"menuid" : "a22",
		"menuname" : "行政人员 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=1&menuid=a22"
		},
	"a23": {
		"menuid" : "a23",
		"menuname" : "博士后 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=2&menuid=a23"
		},
	"a24": {
		"menuid" : "a24",
		"menuname" : "博士 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=3&menuid=a24"
		},
	"a25": {
		"menuid" : "a25",
		"menuname" : "硕士 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=4&menuid=a25"
		},
	"a26": {
		"menuid" : "a26",
		"menuname" : "其他人员 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toBiMailList?baseInfoType=5&menuid=a26"
		},
	"a28": {
		"menuid" : "a28",
		"menuname" : "科研人员 ",
		"icon" : "icon-nav",
		"url" : "baseInfo/toMailView?baseInfoType=5&menuid=a28"
		},
	"b11": {
		"menuid" : "b11",
		"menuname" : "横向项目",
		"icon" : "icon-nav",
		"url" : "project/toProjectList?projectType=0&menuid=b11"
		},
	"b12": {
		"menuid" : "b12",
		"menuname" : "纵向项目",
		"icon" : "icon-nav",
		"url" : "project/toProjectList?projectType=1&menuid=b12"
		},
	"b13": {
		"menuid" : "b13",
		"menuname" : "国际合作项目",
		"icon" : "icon-nav",
		"url" : "project/toProjectList?projectType=2&menuid=b13"
		},
	"b21":{
		"menuid" : "b21",
		"menuname" : "著作",
		"icon" : "icon-nav",
		"url" : "opus/toOpusList?menuid=b21"
		},
	"b22":{
		"menuid" : "b22",
		"menuname" : "论文",
		"icon" : "icon-nav",
		"url" : "paper/toPaperList?menuid=b22"
		},
	"b23": {
		"menuid" : "b23",
		"menuname" : "获奖",
		"icon" : "icon-nav",
		"url" : "prize/toPrizeList?menuid=b23"
		},
	"b24": {
		"menuid" : "b24",
		"menuname" : "采纳批示",
		"icon" : "icon-nav",
		"url" : "adopt/toAdoptList?menuid=b24"
		},
	"b31": {
		"menuid" : "b31",
		"menuname" : "学术会议",
		"icon" : "icon-nav",
		"url" : "conference/toConferenceList?menuid=b31"
		},
	"b32": {
		"menuid" : "b32",
		"menuname" : "报告讲座",
		"icon" : "icon-nav",
		"url" : "lecture/toLectureList?menuid=b32"
		},
	"b33": {
		"menuid" : "b33",
		"menuname" : "特色活动",
		"icon" : "icon-nav",
		"url" : "activity/toActivityList?menuid=b33"
		},
	"b41": {
		"menuid" : "b41",
		"menuname" : "授课信息",
		"icon" : "icon-nav",
		"url" : "teaching/toTeachingList?menuid=b41"
		},
	"b42": {
		"menuid" : "b42",
		"menuname" : "培训深造",
		"icon" : "icon-nav",
		"url" : "train/toTrainList?menuid=b42"
		},
	"b51": {
		"menuid" : "b51",
		"menuname" : "培训咨询",
		"icon" : "icon-nav",
		"url" : "consult/toConsultList?menuid=b51"
		},
	"b52": {
		"menuid" : "b52",
		"menuname" : "其他委托",
		"icon" : "icon-nav",
		"url" : "entrust/toEntrustList?menuid=b52"
		},
	"b61": {
		"menuid" : "b61",
		"menuname" : "国际",
		"icon" : "icon-nav",
		"url" : "cooperation/toCooperationList?cooperationType=1&menuid=b61"
		},
	"b62": {
		"menuid" : "b62",
		"menuname" : "国内",
		"icon" : "icon-nav",
		"url" : "cooperation/toCooperationList?cooperationType=0&menuid=b62"
		},
	"b71": {
		"menuid" : "b71",
		"menuname" : "报纸",
		"icon" : "icon-nav",	
		"url" : "publicity/toPublicityList?publicityType=0&menuid=b71"
		},
	"b72": {
		"menuid" : "b72",
		"menuname" : "期刊",
		"icon" : "icon-nav",
		"url" : "publicity/toPublicityList?publicityType=1&menuid=b72"
		},
	"b73": {
		"menuid" : "b73",
		"menuname" : "电视台",
		"icon" : "icon-nav",
		"url" : "publicity/toPublicityList?publicityType=2&menuid=b73"
		},
	"b74": {
		"menuid" : "b74",
		"menuname" : "网络",
		"icon" : "icon-nav",
		"url" : "publicity/toPublicityList?publicityType=3&menuid=b74"
		},
	"b81": {
		"menuid" : "b81",
		"menuname" : "中心",
		"icon" : "icon-nav",
		"url" : "report/toReportList?reportType=1&menuid=b81"
		},
	"b82": {
		"menuid" : "b82",
		"menuname" : "个人",
		"icon" : "icon-nav",
		"url" : "report/toReportList?reportType=0&menuid=b82"
		},
	"c11": {
		"menuid" : "c11",
		"menuname" : "基地大事件",
		"icon" : "icon-nav",
		"url" : "memorabilia/toMemorabiliaList?menuid=c11"
		},
	"c12": {
		"menuid" : "c12",
		"menuname" : "规章制度",
		"icon" : "icon-nav",
		"url" : "rule/toRuleList?menuid=c12"
		},
	"c13": {
		"menuid" : "c13",
		"menuname" : "基地规划",
		"icon" : "icon-nav",
		"url" : "plan/toPlanList?menuid=c13"
		},
	"c14": {
		"menuid" : "c14",
		"menuname" : "基地总结",
		"icon" : "icon-nav",
		"url" : "summary/toSummaryList?menuid=c14"
		},
	"c15": {
		"menuid" : "c15",
		"menuname" : "资助经费",
		"icon" : "icon-nav",
		"url" : "fund/toFundList?menuid=c15"
		},
	"c16": {
		"menuid" : "c16",
		"menuname" : "年度预算",
		"icon" : "icon-nav",
		"url" : "budget/toBudgetList?menuid=c16"
		},
	"d11": {
		"menuid" : "d11",
		"menuname" : "科研",
		"icon" : "icon-nav",
		"url" : "centralActivity/toCentralActivityList?activityType=0&menuid=d11"
		},
	"d12": {
		"menuid" : "d12",
		"menuname" : "工会",
		"icon" : "icon-nav",
		"url" : "centralActivity/toCentralActivityList?activityType=1&menuid=d12"
		},
	"d13": {
		"menuid" : "d13",
		"menuname" : "党员",
		"icon" : "icon-nav",
		"url" : "centralActivity/toCentralActivityList?activityType=2&menuid=d13"
		},
	"d14": {
		"menuid" : "d14",
		"menuname" : "其他",
		"icon" : "icon-nav",
		"url" : "centralActivity/toCentralActivityList?activityType=3&menuid=d14"
		},
	"e11": {
		"menuid" : "e11",
		"menuname" : "工作论文",
		"icon" : "icon-nav",
		"url" : "shareFile/toShareFileList?shareFileType=0&menuid=e11"
		},
	"e12": {
		"menuid" : "e12",
		"menuname" : "研讨会讲稿",
		"icon" : "icon-nav",
		"url" : "shareFile/toShareFileList?shareFileType=1&menuid=e12"
		},
	"e13": {
		"menuid" : "e13",
		"menuname" : "参考文献",
		"icon" : "icon-nav",
		"url" : "shareFile/toShareFileList?shareFileType=2&menuid=e13"
		},
	"e14": {
		"menuid" : "e14",
		"menuname" : "会议通讯录",
		"icon" : "icon-nav",
		"url" : "shareFile/toShareFileList?shareFileType=3&menuid=e14"
		},
	"e15": {
		"menuid" : "e15",
		"menuname" : "其他",
		"icon" : "icon-nav",
		"url" : "shareFile/toShareFileList?shareFileType=4&menuid=e15"
		},
	"f11": {
		"menuid" : "f11",
		"menuname" : "账户管理",
		"icon" : "icon-nav",
		"url" : "account/toAccountList?menuid=f11"
		}/*,
	"f12": {
		"menuid" : "f12",
		"menuname" : "角色管理",
		"icon" : "icon-nav",
		"url" : "role/toRoleList?menuid=f12"
		}*/
};

// 定义总的菜单对象
var menu_result = {};

var powers = null;

/**
 * 获取用户权限对应菜单项
 */
function getPower(power) {
	powers = power;
	if(powers != undefined) {
		var powerArray = powers.substring(1, power.length-1).split(",");
		if(power.indexOf(",a,") >= 0) {
			// 创建属性
			menu_result.basic = [];
			var basic = menu_result.basic;
			setMenuData(basic, powerArray, "a");
		}
		
		if(power.indexOf(",b,") >= 0) {
			menu_result.point1 = [];
			var point1 = menu_result.point1;
			setMenuData(point1, powerArray, "b");
		}
		
		if(power.indexOf(",c,") >= 0) {
			menu_result.point2 = [];
			var point2 = menu_result.point2;
			setMenuData(point2, powerArray, "c");
		}
		
		if(power.indexOf(",d,") >= 0) {
			menu_result.point3 = [];
			var point3 = menu_result.point3;
			setMenuData(point3, powerArray, "d");
		}
		
		if(power.indexOf(",e,") >= 0) {
			menu_result.point4 = [];
			var point4 = menu_result.point4;
			setMenuData(point4, powerArray, "e");
		}
		
		if(power.indexOf(",f,") >= 0) {
			menu_result.point5 = [];
			var point5 = menu_result.point5;
			setMenuData(point5, powerArray, "f");
		}
	}
	return menu_result;
}

/**
 * 设置对象属性（权限菜单属性）
 * @param array
 * @param powerArray
 * @param startChart
 */
function setMenuData(array, powerArray, startChart) {
	// 取出所有a开头的数据
	var a_basics = [];
	// 存放二级菜单数据
	var a_array = []; 
	
	for(var i = 0; i < powerArray.length; i ++) {
		if(powerArray[i].indexOf(startChart) >= 0){
			a_basics.push(powerArray[i]);
		}
	}
	
	// 判断二级菜单权限
	for(var i = 0; i < a_basics.length; i ++) {
		// 如果不等于undefined相当于有权限, 添加到basic中
		var key = a_basics[i];
		if(second_menus[key] != undefined) {
			array.push(second_menus[key]);
			a_array.push(key);
		}
	}
	// 判断三级菜单权限
	for(var i = 0; i < a_array.length; i ++) {
		for(var j = 0; j < a_basics.length; j ++) {
			// 如果不等于undefined相当于有权限, 添加到basic.property.menus中
			var key = a_basics[j];
			if(third_menus[key] != undefined) {
				// 判断如果将三级菜单截取前两位与二级菜单相同,则显示三级菜单
				if(key.substring(0,2).indexOf(a_array[i]) >= 0){
					array[i].menus.push(third_menus[key]);
				}
			}
		}
	}
}

/**
 * 获取操作页面所属的菜单ID
 * @returns
 */
function getPowerMenuId() {
	var urls = "";
	$(window.parent.document).find("div[id='tabs']").find("div:first").next().children().each(function(){
		var styles= $(this).attr("style");
		if(styles.indexOf("block") >= 0){
			urls = $($($(this).children()[0]).children()[0]).attr("src");
		}
	});
	//判断是否包含"&"
	if(urls.indexOf("&")>=0){
		urls = urls.substring(urls.lastIndexOf("&"), urls.length);
	}else{
		urls = "&"+urls.substring(urls.lastIndexOf("?")+1, urls.length);
	}
	return urls;
}


