$(function() {
	var ExportDeleteExcelConfirm001 = function(){
	    return {
	    	/*提交*/
	        submit:function(){
	           var param = {};
	           param.remark = $("#remark").val();
	           // 删除原因不能为空
	           if (!param.remark || param.remark.length == 0){
	        	   Joyin.error("删除原因不能为空！")
	        	   return;
	           } 
	           // 删除原因不能大于70字符
	           if (strlen(param.remark) > 140){
	        	   Joyin.error("删除原因长度不能大于70个字符(中文俩个字符，英文一个字符)！")
	        	   return;
	           }
	           // 是否更新为手工处理
	           if ($("#isUpdate")[0].checked) {
	               param.flag="01";
	           }else{
	               param.flag="02";
	           }
	           /*原pageid*/
	           param.orgPageId=orgPageId;
	           /*选中数据*/
	           param.expKeys=expKeys;
	           Joyin.exportExcel("exportDel",param, function() {
		           var index = parent.layer.getFrameIndex(window.name);
		           parent.layer.close(index);
	           });
	           
	        },
	        /*撤销*/
	        cancel : function() {
	            var index = parent.layer.getFrameIndex(window.name);
	            parent.layer.close(index);
	        }
	    };
	}();
	$(function(){
	    $("#ExportDeleteExcelConfirm001_btn_save").on("click",ExportDeleteExcelConfirm001.submit);
	    $("#ExportDeleteExcelConfirm001_btn_cancel").on("click",ExportDeleteExcelConfirm001.cancel);
	});
	
	/**
	 * 计算字符串长度，中文俩个字符，英文一个字符
	 */ 
	function strlen(str) {
		var len = 0;
		for (var i = 0; i < str.length; i++) {
			var c = str.charCodeAt(i);
			//单字节加1   
			if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
				len++;
			} else {
				len += 2;
			}
		}
		return len;
	}
}());