	/**
	 * 查看
	 * @param filePath 文件路径
	 */
	function viewFile(filePath) {
		var newWim = window.open(filePath, "_blank", "");
		var content = "<div id=\"a1\"></div>" +
					  "<script type=\"text/javascript\" src=\"/ckplayer/ckplayer.js\" charset=\"utf-8\"></script>" +
					  "<script type=\"text/javascript\">" +
					  	"var flashvars={f:'http://movie.ks.js.cn/flv/other/1_0.flv', c:0};" +
					  	"var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};" +
					  	"CKobject.embedSWF('/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);" +
					  "</script>";
		newWim.document.body.innerHTML = content;
	}