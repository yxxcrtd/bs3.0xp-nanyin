/**
 * RskSupervisionIndicatorParam001 指标参数管理
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var paraName = $('#paraName').val();
        var startUpdateTime = $('#updateTime').startDate();
        var endUpdateTime = $('#updateTime').endDate();
        return {
            paraName : paraName,
            startUpdateTime : startUpdateTime,
            endUpdateTime : endUpdateTime
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
                basePath + "invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    paraCode: data.paraCode});
        },
        cols:[
            {field:'paraName',title: 'RskSupervisionIndicatorParam001.paraName'}
            ,{field:'paraCode',title: 'RskSupervisionIndicatorParam001.paraCode'}
            ,{field:'updateTime', title: 'RskSupervisionIndicatorParam001.updateTime',type:'date'}
            ,{field:'operation',width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndicatorParam001.edit"),
                basePath + "invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    paraCode: data.paraCode},function() {
                    table.reload();
                });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        data.operateType="delete";
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam001/delete', data, function(result){
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	  var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RskSupervisionIndicatorParam001_btn_search').click(function() {
            table.load();
        });
        $('#RskSupervisionIndicatorParam001_btn_export').click(function() {
            exportExcel();
        });
        $('#RskSupervisionIndicatorParam001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndicatorParam002"),
                basePath + "invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW},function () {
                    table.reload();
                });
        });
    });

    // return {refreshTable: function () {
    //         table.reload();
    //     }};
}();
