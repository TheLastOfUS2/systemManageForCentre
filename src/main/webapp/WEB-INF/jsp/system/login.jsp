<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=urf-8"/>
<link  rel="stylesheet" href="resources/css/login.css" />
<script type="text/javascript" src="resources/js/jquery-2.0.3.js.js"></script>
<script type="text/javascript" src="resources/js/md5.js"></script>

<script>
	//登录验证
	function login(){
		var username=$("#u").val();
		var password=$("#p").val();
		var code=$("#c").val();
		if(username==""){
			alert("请输入用户名！");
			return;
		}
		if(password==""){
			alert("请输入密码！");
			return;
		}else{
			$("#passMd5").val($.md5(password));
		}
		if(code==""){
			alert("请输入验证码！");
			return;
		}else{
			//比较验证码是否正确
			var code=$("#c").val();
			var validateCode=$("#validateCodeDivId").text().replace(/[ ]/g,"");//去掉字符串所有空格
			if(code.toLowerCase() !=validateCode.toLowerCase()){
				alert("验证码错误!");
				return;
			}
		}
	    document.forms[0].action="login.action";
		document.forms[0].submit();
	}
	
	function init(){
		validateCode();
	}
	
	//生成随机验证码
	function validateCode(){
		var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	    var res = "";
	    for(var i = 0; i < 4 ; i ++) {
	        var id = Math.ceil(Math.random()*35);
	        res += chars[id]+" ";
	    }
		if(res!=""){
			$("#validateCodeDivId").text(res);
		}
	}
</script>
</head>
<body onload="init()">
<!-- 背景图 -->
<img src="resources/images/login/background.png"/>
<!-- 登录窗口 -->
<div class="yam_login">
	<img class="title" src="resources/images/login/title.png"/>
	<img class="loginframe" src="resources/images/login/loginframe.png"/>
	<form name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
		<input type="hidden" name="passMd5" id="passMd5"/>	
	 	<div id="uNameDivId" class="uNameDiv">
	 		<input type="text" id="u" name="userName" value="${userName}" class="input_text" style="height: 29px;width: 294px"/>
	 	</div>
	 	<div id="uPassDivId" class="uPassDiv">
	 		<input type="password" id="p" name="password" value="${password}" class="input_text" style="height: 29px;width: 294px"/>
	 	</div>
	 	<div id="validateDivId" class="validateDiv">
	 		<input type="text" id="c" name="validate" class="input_text" style="height: 29px;width: 203px" />
	 	</div>
	 	<div id="validateCodeDivId" class="validateCodeDiv" onclick="validateCode()"></div>
		<input type="button" value="" onclick="login()" class="butDiv" style="height:45px;width:180px"/>
	 </form>
</div>
<!-- 登录窗口 -->
</body>
</html>