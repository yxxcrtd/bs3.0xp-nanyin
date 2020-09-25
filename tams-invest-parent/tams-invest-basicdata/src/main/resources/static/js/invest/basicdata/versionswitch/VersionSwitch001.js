$(function() {

    
    
    $(function() {
        
            // 绑定按钮事件
            $('#VersionSwitch001_btn_save').click(function() {
            	var versionType = $('#versionType').val();
            	if(versionType){
            		var object = {versionType:versionType};
            		Joyin.ajaxJson(basePath + 'invest/basicdata/versionswitch/VersionSwitch001/save', object, function(result) {
            			if(result.flag == "01"){
            				Joyin.alert("保存成功！");
            			}else{
            				Joyin.error("保存失败！");
            			}
            		});            		            		
            	}else{
            		Joyin.error("请选择版本类型！");
            	}
            });

      });
}());
    
