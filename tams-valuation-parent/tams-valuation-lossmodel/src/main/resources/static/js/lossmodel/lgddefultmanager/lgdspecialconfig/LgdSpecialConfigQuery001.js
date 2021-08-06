$(function () {
    
    var flag1 = false;
    var flag2 = false;
    if(hzSchemeSwitch == "01"){
        flag1 = true;
        flag2 = false;
    }else{
        flag1 = false;
        flag2 = true;
    }

    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            finprodIds:$('#finprodIds').val()==null?null:$('#finprodIds').val().join(','),
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        /*双击查看*/
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("特殊资产LGD详情"),
                JoyinUtil.getRealPath("model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        },
        cols: [
            {field: 'seqNo', visible: false}
            ,{field:'finprodId', title: '资产代码', render: function (data, type, row, meta) {
                    if(row.level == 1) {
                        return '<strong>' + data + '</strong>';
                    }
                    return data;
                }}
            ,{field:'finprodName',  title: '资产名称'}
            ,{field:'lgd', digit: 4,   title: 'LGD(%)'}
            ,{field:'operation', width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
      });

    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001/check', getPageParams(), function(result) {
            Joyin.exportExcel('export', getPageParams());
        });
        
    };


    /**
     * 画面加载完成
     */
    $(function() {
        if(isInit){
            table.load();
        }
        // 绑定查询按钮事件
        $('#LgdSpecialConfig001_btn_search').click(function() {
            table.load();
        });

        //绑定新增按钮事件
        $('#LgdSpecialConfig001_btn_add').click(function() {
            showDialog();
        });
        //绑定导出按钮事件
        $('#LgdSpecialConfig001_btn_export').click(function() {
            exportExcel();
        });


    });



    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel(basePath+"model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001/export", getPageParams());
    };

    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog('新建特殊资产LGD维护', basePath +'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/init', '90%', '90%',function(){table.reload();});
    }

    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/check', data, function (result) {
            Joyin.dialog('特殊资产LGD维护编辑',
                basePath + "model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_EDIT,
                    seqNo: data.seqNo
                }, function () {
                    table.reload();
                });
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",seqNo: data.seqNo};
        Joyin.ajaxJson(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001/deleteCheck', object, function(result) {

            /*Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig001_btn_delete", "LgdSpecialConfig002",function(){
                    table.reload();
                });
            });*/

            if(result.data != undefined &&  result.data.indexOf("已被使用")>0){
                Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
                    WorkFlow.doDeleteList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig001_btn_delete", "LgdSpecialConfig002",function(){
                        table.reload();
                    });
                });
            }else{
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig001_btn_delete", "LgdSpecialConfig002",function(){
                        table.reload();
                    });
                });
            }


        });
    });

    /**
     * 批量删除
     */
    $('#LgdSpecialConfig001_btn_del').click(function() {
        delRows();
    });

    var delRows = function () {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig001_btn_delete", "LgdSpecialConfig002", function(){
                table.reload();//删除事件请求及回调函数
            });
        });

    };

});
