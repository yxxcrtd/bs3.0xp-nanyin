$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var object = $.serializeToObject('#searchForm',true);

        // if(object.brokerId){
        //     object.brokerId = object.brokerId.join(",");
        // }
        // if(object.accountType){
        //     object.accountType = object.accountType.join(",");
        // }
        return object;
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
        dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                JoyinUtil.getRealPath("invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002/init"),
                "75%", "75%", data,
                function(){
                table.reload();
            });
        },
        cols:[{field:'brokerId',width:200,  title: '券商',visible:false}
            ,{field:'brokerName',width:200,  title: '券商'}
            ,{field:'accountType',width:200,  title: '资金账户',visible:false}
            ,{field:'accountTypeStr',width:200,  title: '资金账户'}
            ,{field:'tandardRate',width:200, title: '标准券再折算率（%）'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 导出
     */
    var exportExcel = function(){
        var paramObj=getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = data;
        object.operateType = "edit";
        Joyin.ajaxJson(basePath + 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("标准券再折算率新建|修改"),
                basePath + "invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002/init",
                "75%", "75%", {
                    pageModel: PAGE_MODEL_EDIT,
                    brokerId: data.brokerId,
                    accountType: data.accountType
                }, function () {
                    table.reload();
                })
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = data;
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002/save', object, "StandardCouponReConversionRate001_btn_delete", "StandardCouponReConversionRate002");
            });
        });
    });

    // 新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('标准券再折算率新建|修改'), '../StandardCouponReConversionRate002/init', '98%', '98%')
    }


    /**
     * 画面加载完成
     */
    $(function() {
        $('#StandardCouponReConversionRate001_btn_search').click(function() {
            table.load();
        });

        $('#StandardCouponReConversionRate001_btn_add').click(showDialog);

        $('#StandardCouponReConversionRate001_btn_export').click(function() {
            exportExcel();
        });

        $('#StandardCouponReConversionRate001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001/delete', object, "StandardCouponReConversionRate001_btn_delete", "StandardCouponReConversionRate002");
                });
            });
        });
    });
}());