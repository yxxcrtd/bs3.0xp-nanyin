/**
 * 平安基金类标 的资产
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        //获取查询条件金融产品名称
        var targetType;
        var targetTypeTemp =$('#targetType').val();
        if(targetTypeTemp)
            // targetType=targetTypeTemp.join(",");
            targetType = targetTypeTemp;
        return {
            targetId: $('#targetId').val(),
            targetType: targetType
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "valuation/basicdata/asset/option/FinOptionTarget002/init",
                "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,targetId:data.targetId});
        },

        cols:[
            {field:'targetId',width:250 , title: 'FinOptionTarget001.targetId'}
            ,{field:'targetName',width:300 ,  title: 'FinOptionTarget001.targetName'}
            ,{field:'targetTypeStr',width:250 ,sortField:"targetType",  title: 'FinOptionTarget001.targetType'}
            ,{field:'updateUser',width:150, title: '更新人'}
            ,{field:'updateTime',width:170, title: '更新时间',type:'date'}
            ,{field:'dataSourceValue',width:170, sortField:"dataSource", title: '数据来源'}
            ,{field:'operation', width:170,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt',{clas: 'iconfont icon-copy',title: '复制'}]
        }
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/check', data, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/editCheck', data, function(editResult){
                Joyin.dialog(JoyinUtil.getLabel("FinOptionTarget002.edit"),
                    basePath + "valuation/basicdata/asset/option/FinOptionTarget002/init",
                    "90%", "90%",data,function() {
                        table.reload();
                    });
            });

        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/delete', object,
                    "FinOptionTarget001_btn_delete", "FinOptionTarget002",function() {
                        table.reload();
                    });
            });
        });
    });

    /**
     * 绑定复制事件
     */
    table.bind('click', '.icon-copy', function(data, index) {
        data.pageModel = PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/check', data, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/editCheck', data, function(editResult){
                Joyin.dialog(JoyinUtil.getLabel("复制：场外期权标的"),
                    basePath + "valuation/basicdata/asset/option/FinOptionTarget002/init",
                    "90%", "90%",data,function() {
                        table.reload();
                    });
            });

        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        // var paramObj = getSearchParams();
        // Joyin.exportExcel('export', paramObj);
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinOptionTarget001_btn_search').click(function() {
            table.load();
            $('#FinOptionTarget001_btn_delete').show();
        });
        $('#FinOptionTarget001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinOptionTarget001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinOptionTarget002"),
                basePath + "valuation/basicdata/asset/option/FinOptionTarget002/init",
                "90%", "90%",{pageModel:PAGE_MODEL_NEW});
        });
        $('#FinOptionTarget001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/option/FinOptionTarget001/delete', object,
                        "FinOptionTarget001_btn_delete","FinOptionTarget002");
                });
            });
        });

    });
}());