	function format(time){
		var time = new Date(time);
		var y = time.getFullYear();//年
		var m =strlen(time.getMonth() + 1+"");//月
		var d =strlen(time.getDate()+"");//日
		var h =strlen(time.getHours()+"");//时
		var mm =strlen(time.getMinutes()+"");//分
		var s =strlen(time.getSeconds()+"");//秒
		
		return y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
	}
	
	function strlen(strlen){
		if(strlen.length==1){
			strlen=0+strlen;
		}
		return strlen
	}