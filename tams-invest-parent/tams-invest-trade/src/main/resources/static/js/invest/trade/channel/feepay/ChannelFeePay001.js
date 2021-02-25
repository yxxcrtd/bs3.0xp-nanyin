/**
 * ChannelFeePay001 通道费用支付查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if (param.portfolioId) {
            param.portfolioId = param.portfolioId.join(",");
        }
        if (param.channelId) {
            param.channelId = param.channelId.join(",");
        }
        if ($("#payDate").val()) {
            param.payDateStart = $("#payDate").startDate();
            param.payDateEnd = $("#payDate").endDate();
        }
        return param;
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
            Joyin.dialog("合作资产费用支付查看",
                JoyinUtil.getRealPath("invest/trade/channel/feepay/ChannelFeePay002/init"),
                "99%", "99%", data);
        },
        cols:[
            {field:'effectStatusStr', sortField:'effectStatus',width:120, title: '有效状态'}
            ,{field:'effectStatus', width:120, title: '有效状态Code', visible : false}
            ,{field:'tradeId',width:200,title: '业务编号'}
            ,{field:'channelName',width:150,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodNameBak");}()}
            ,{field:'feeNameString',width:150,title: '费用名称', sortField: 'feeName'}
            ,{field:'mdate',width:150,title: '计提结束日', type: 'date'}
            ,{field:'settleDate',width:150,title: '支付日', type: 'date'}
            ,{field:'theoryInt',width:150,title: '理论费用'}
            ,{field:'tradeAmt',width:150,title: '实付费用'}
            ,{field:'finprodId',width:150,title: '通道Id', visible : false}
        ]
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    var createChannelFeePay = function(){
        Joyin.dialog("合作资产费用支付新建", basePath + 'invest/trade/channel/feepay/ChannelFeePay002/init', '98%', '98%',
            {}, function() {

            });
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#ChannelFeePay001_btn_create').click(function() {
            createChannelFeePay();
        });
        $('#ChannelFeePay001_btn_search').click(function() {
            $("#ChannelFeePay001_btn_revoke").show();
            table.load();
        });
        // 绑定按钮事件
        $('#ChannelFeePay001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 撤销
         */
        $('#ChannelFeePay001_btn_revoke').click(function() {
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

            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.channelName = selArr[0].channelName;
            checkObj.tradeAmt = selArr[0].tradeAmt;
            checkObj.operateType = "revoke";
            checkObj.effectStatus = selArr[0].effectStatus;
            // checkObj.portfolioId = selArr[0].portfolioId;
            checkObj.mdate = selArr[0].mdate;
            checkObj.chlId = selArr[0].finprodId;
            Joyin.ajaxJson(basePath + 'invest/trade/channel/feepay/ChannelFeePay001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/trade/channel/feepay/ChannelFeePay001/doRevoke', checkObj, "ChannelFeePay001_btn_revoke", "ChannelFeePay002");
                });
            });
        });
    });
}());