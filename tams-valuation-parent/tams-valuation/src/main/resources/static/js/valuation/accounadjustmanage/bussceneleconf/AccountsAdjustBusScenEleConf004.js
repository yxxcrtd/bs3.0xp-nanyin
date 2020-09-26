$(function() {
    var getParams = function() {
        return {
        	tableId: $('#AccountsAdjustBusScenEleConf004_tableId').val(),
        	tableName: $('#AccountsAdjustBusScenEleConf004_tableName').val(),
        	dictCode:$('#AccountsAdjustBusScenEleConf004_dictCode').val(),
        	dictName: $('#AccountsAdjustBusScenEleConf004_dictName').val(),
        	dictType: $('#AccountsAdjustBusScenEleConf004_dictType').val(),
        	dictSource: $('#AccountsAdjustBusScenEleConf004_dictSource').val(),
        	dealType : $('#AccountsAdjustBusScenEleConf004_dealtype').val()
        }
    };
    $("#AccountsAdjustBusScenEleConf004_btn_save").click(function() {
       if ($("#AccountsAdjustBusScenEleConf004_dictCode").val().trim() == 0) {
            layui.layer.alert("数据项代码不能为空！");
        } else  if ($("#AccountsAdjustBusScenEleConf004_dictName").val().trim() == 0) {
            layui.layer.alert("数据项名称不能为空！");
        }else if ($("#AccountsAdjustBusScenEleConf004_dictType").val().trim() == 0) {
            layui.layer.alert("数据类型不能为空！");
        }else if ($("#AccountsAdjustBusScenEleConf004_dictSource").val().trim() == 0) {
            layui.layer.alert("数据源不能为空！");
        }else{
            var datas =JSON.stringify(getParams());
            console.log(datas); 
            Joyin.ajax('save',{datas:datas},function(data) {
                    // console.log(data);
                    if (data) {
                        var layer = layui.layer;
                        layer.alert("保存成功！");
                        // 延迟1秒后执行查询，为控件本身的enter事件提供充足的响应时间
        				setTimeout(function() {
        					 var index = parent.layer.getFrameIndex(window.name);
                             if(index) {
                                 parent.layer.close(index);
                             }
        				},1000);
        				parent.refresh();
                       
                    } else {
                        var layer = layui.layer;
                        layer.alert("保存失败！");
                    }
            });
        }
    })
});