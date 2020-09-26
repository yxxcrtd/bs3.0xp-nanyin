$(function() {
    var getParams = function() {
        return {
        	tableId: $('#AccountsAdjustBusScenEleConf002_tableId').val(),
        	tableName: $('#AccountsAdjustBusScenEleConf002_tableName').val(),
        	remark:$('#AccountsAdjustBusScenEleConf002_remark').val()
        }
    };
    $("#AccountsAdjustBusScenEleConf002_tableId").focus(function() {
        $("#AccountsAdjustBusScenEleConf002_tableId").blur(function() {
            if ($("#AccountsAdjustBusScenEleConf002_tableId").val().trim() == 0) {
                layui.layer.alert("业务场景代码不能为空！");
            } else {
                $.ajax({
                    type : "POST",
                    url : 'saveCheck',
                    data : {
                        tableId : $("#AccountsAdjustBusScenEleConf002_tableId").val(),
                        tableName : $("#AccountsAdjustBusScenEleConf002_tableName").val()
                    },
                    success : function(data) {
                        console.log(data);
                        if (data == "1") {
                            var layer = layui.layer;
                            layer.alert("业务场景代码已存在！");
                        }else if(data == "2"){
                        	var layer = layui.layer;
                            layer.alert("业务场景名称已存在！");
                        }
                    }
                });
            }
        });
    });
    $("#AccountsAdjustBusScenEleConf002_btn_save").click(function() {
       if ($("#AccountsAdjustBusScenEleConf002_tableName").val().trim() == 0) {
            layui.layer.alert("业务场景名称不能为空！");
        } else{
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
                       
                    } else {
                        var layer = layui.layer;
                        layer.alert("保存失败！");
                    }
            });
        }
    })
});