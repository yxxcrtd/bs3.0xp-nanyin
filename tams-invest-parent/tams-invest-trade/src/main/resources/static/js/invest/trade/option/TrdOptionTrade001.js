(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,ps: $('#ps').val()
            ,contractType: $('#contractType').val()
            ,targetId: $('#targetId').val()
            ,exerciseType:$('#exerciseType').val()
            ,tradeDateStart: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
            ,validStatus: $('#validStatus').val()
        };
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdOptionTrade002.reference"),
                JoyinUtil.getRealPath("invest/trade/option/TrdOptionTrade002/init"),
                "99%", "99%", data/*,
                    function(){table.reload();}*/);
        },
        cols:[
            {field:'validStatusText', width:120, sortField:'validStatus', title: 'TrdOptionTrade001.validStatus'}
            ,{field:'totleTradeId', width:200, sortField:'totleTradeId', title: 'TrdOptionTrade001.totleTradeId'}
            ,{field:'entrustId', width:170, sortField:'entrustId', title: 'TrdOptionTrade001.entrustId', visible: false}
            ,{field:'portfolioId', width:140, sortField:'portfolioId', title: 'TrdOptionTrade001.portfolioId', visible: false,
                render : function(data, type, row, meta) {
                    return "<a class='pro-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }
            }
            ,{field:'portfolioName', width:200, sortField:'portfolioName', title: 'TrdOptionTrade001.portfolioName'}
            ,{field:'psText', width:110, sortField:'ps', title: 'TrdOptionTrade001.ps'}
            ,{field:'contractTypeText', width:100, sortField:'contractType', title: 'TrdOptionTrade001.contractType'}
            ,{field:'targetId', width:130, sortField:'targetId', title: 'TrdOptionTrade001.targetId'}
            ,{field:'amount', width:100, sortField:'amount', title: 'TrdOptionTrade001.amount'}
            ,{field:'exerciseTypeText', width:100, sortField:'exerciseType', title: 'TrdOptionTrade001.exerciseType'}
            ,{field:'term', width:100, sortField:'term', title: 'TrdOptionTrade001.term'}
            ,{field:'tradeDate', width:130, sortField:'tradeDate', title: 'TrdOptionTrade001.tradeDate', type: 'date'}
            ,{field:'vdate', width:130, sortField:'vdate', title: 'TrdOptionTrade001.vdate', type: 'date'}
            ,{field:'mdate', width:130, sortField:'mdate', title: 'TrdOptionTrade001.mdate', type: 'date'}
            ,{field:'remark', width:150, sortField:'remark', title: 'TrdOptionTrade001.remark'}
            ,{field:'workflowId', visible: false}
            ,{field:'workflowStatus', visible: false}
        ],
        //loadonce:true
    });

    var exportExcel = function(){
        Joyin.exportExcel('export', getPageParams());
    };

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/trade/lend/TrdOptionTrade001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/trade/lend/TrdOptionTrade001/delete', object, "TrdOptionTrade001_btn_revoke", "TrdOptionTrade001",function() {
                    table.reload();
                });
            });
        });
    });

    table.bind('click','.pro-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",{
                pageModel:PAGE_MODEL_REFERENCE,
                portfolioId:row.portfolioId,
                portfolioName: row.portfolioName});
    });

    $(function () {
        // 绑定按钮事件
        $('#TrdOptionTrade001_btn_search').click(function() {
            table.load();
            $('#TrdOptionTrade001_btn_revoke').show();
        });

        $('#TrdOptionTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdOptionTrade001_btn_add').click(
            function() {
                Joyin.dialog(JoyinUtil.getLabel("TrdOptionTrade002"), basePath
                    + "invest/trade/option/TrdOptionTrade002/init", "99%", "99%", {
                    pageModel : PAGE_MODEL_NEW
                }, function() {
                    table.reload();
                });
            });
        $('#TrdOptionTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }

            var checkObj = selArr[0];
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/trade/option/TrdOptionTrade001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/option/TrdOptionTrade001/doRevoke', checkObj, "TrdOptionTrade001_btn_revoke", "TrdOptionTrade002");
                });
            });
        });
    });
}());