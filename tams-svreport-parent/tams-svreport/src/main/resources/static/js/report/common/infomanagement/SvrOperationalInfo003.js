$(function() {
    $("#SvrOperationalInfo003_BTN_submit").click(function() {
    	var mgrTableName = $("#SvrOperationalInfo003_mgrTableName").val();
        var mgrTableChiName = $("#SvrOperationalInfo003_mgrTableChiName").val();
        if (typeof (mgrTableName) == "undefined" || mgrTableName == null || mgrTableName == "") {
        	layer.alert("待维护表名不能为空！");
        	return;
        }
        if (typeof (mgrTableChiName) == "undefined" || mgrTableChiName == null || mgrTableChiName == "") {
        	layer.alert("待维护表中文名不能为空！");
        	return;
        }
        var param = {};
        param.mgrTableName = mgrTableName;
        param.mgrTableChiName = mgrTableChiName;
        param.dataObject = $("#SvrOperationalInfo003_dataObject").val();
        param.pageModel = pageModel;
        Joyin.ajax("checkData", param, function(result) {
       	 if (result.data == "noTable") {
       		 layer.alert("数据库中不存在所填的待维护表，请重新填写！");
       	     return;
       	 }
       	 if (result.data == "doubleTable") {
       		layer.alert("该表已经做过配置！");
      	    return;
       	 }
            Joyin.ajax("saveData", param, function(result) {
            	var index = parent.layer.getFrameIndex(window.name);  
                parent.layer.close(index);//关闭当前页  
                window.parent.location.reload()//刷新父级页面
            });
        });
        
    });
});