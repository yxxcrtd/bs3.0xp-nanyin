(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,finprodId: $('#finprodId').val()
            ,finprodMarketId: $('#finprodMarketId').val()
            ,tradeMarket: $('#tradeMarket').val()
            ,validStatus: $('#validStatus').val()
            ,tradeDateStart: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
        };

        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        if ($("#finprodId").val()) {
            pageParam.finprodId = $("#finprodId").val().join(",");
        } else {
            pageParam.finprodId = null;
        }
        if ($("#finprodMarketId").val()) {
            pageParam.finprodMarketId = $("#finprodMarketId").val().join(",");
        } else {
            pageParam.finprodMarketId = null;
        }
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade002.reference"),
                JoyinUtil.getRealPath("invest/trade/bondtostock/TrdBondToStock002/init"),
                "98%", "90%", data,
                function(){table.reload();});
        },
        cols:[
            //有效状态
            {field:'validStatusStr',sortField:'validStatus', width:100, title: 'TrdBondToStock001.validStatus'}
            //业务编号
            ,{field:'busiCode', sortField:'busiCode',width:200, title: 'TrdBondToStock001.tradeId'}
            //委托代码
            ,{field:'entrustId',sortField:'entrustId', width:200, title: 'TrdBondToStock001.entrustId'}
            //组合代码
            ,{field:'portfolioName',sortField:'portfolioId', width:150, title: 'TrdBondToStock001.portfolioName',
                render : function(data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioName + "</a>";
                }
            }
            //组合名称
            ,{field:'portfolioName', sortField:'portfolioName',width:140, title: 'TrdBondToStock001.portfolioName', visible: false}
            //转债代码
            ,{field:'finprodId',sortField:'finprodId', width:180, title: 'TrdBondToStock001.finprodId',
                render : function(data, type, row, meta) {
                    return "<a class='finprodId-link' style='cursor: pointer;'>" + row.finprodId + "</a>";
                }
            }
            //转债名称
            ,{field:'finprodName', sortField:'finprodName',width:140, title: 'TrdBondToStock001.finprodName'}
            //正股代码
            ,{field:'finprodMarketId',sortField:'finprodMarketId', width:180, title: 'TrdBondToStock001.finprodMarketId',
                render : function(data, type, row, meta) {
                    return "<a class='finprodMarketId-link' style='cursor: pointer;'>" + row.finprodMarketId + "</a>";
                }
            }
            //正股名称
            ,{field:'finprodMarketName', sortField:'finprodMarketName',width:100, title: 'TrdBondToStock001.finprodMarketName'}
            //转股券面总额
            ,{field:'shareAmt',sortField:'shareAmt', width:120, title: 'TrdBondToStock001.shareAmt'}
            //转股价
            ,{field:'conPrice',sortField:'conPrice', width:120, title: 'TrdBondToStock001.conPrice', type: 'money'}
            //转股数
            ,{field:'conNum',sortField:'conNum', width:120, title: 'TrdBondToStock001.conNum', type: 'number'}
            //零股数量
            ,{field:'spoShareNum', sortField:'spoShareNum',width:100, title: 'TrdBondToStock001.spoShareNum', type: 'number'}
            //零股折算现金
            ,{field:'tradeAmt',sortField:'tradeAmt', width:120, title: 'TrdBondToStock001.tradeAmt'}
            //现金到账日
            ,{field:'settleDate', sortField:'settleDate',width:100, title: 'TrdBondToStock001.settleDate', type: 'date'}
            //转股溢价率
            ,{field:'preb',sortField:'preb', width:120, title: 'TrdBondToStock001.preb'}
            //交易场所
            ,{field:'tradeMarketStr', sortField:'tradeMarket',width:130, title: 'TrdBondToStock001.tradeMarket'}
            //投资目的
            ,{field:'invAimStr', sortField:'invAim',width:130, title: 'TrdBondToStock001.invAim'}
            //币种
            ,{field:'ccyStr', sortField:'CCY',width:130, title: 'TrdBondToStock001.ccy'}
            //交易日期
            ,{field:'tradeDate', sortField:'tradeDate',width:100, title: 'TrdBondToStock001.tradeDate', type: 'date'}
            // 备注
            ,{field:'remark', sortField:'',width:400, title: 'TrdBondToStock001.remark'}
            ,{field:'validStatus', visible: false}
            ,{field:'ps', visible: false}
            ,{field:'tradeMarket', visible: false}
            ,{field:'ccy', visible: false}
            // ,{field:'action', title: '操作' , type:'action',
            //     render: function (data, type, row, meta) {
            //         if (row.flg != '01')
            //             return "";
            //         else
            //             return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
            //     }
            // }
        ],
        //loadonce:true
    });

    /**
     * 交易确认
     */
    table.bind('click','.edit',function(data,index){
        data.operateType = "confirm";
        data.pageModel= PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/trade/bondtostock/TrdBondToStock001/check', data, function(result1) {
            Joyin.dialog(JoyinUtil.getLabel("TrdConBond002.reference"),
                basePath + "invest/trade/conbond/TrdConBond003/init",
                "98%", "90%", data,function(){table.reload();});
        });
    });

    var exportExcel = function(){
        Joyin.exportExcel('export', getPageParams());
    };


    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    table.bind('click','.portfolioId-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",
            { pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName });
    });
    /**
     * 委托正股代码链接
     */
    table.bind('click','.finprodMarketId-link', function(row) {
        //在新建界面进行显示单行的详细信息
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath
            + "invest/asset/stock/FinStockInfo002/init",
            "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.shareFinprodId  //金融产品代码
            });
    });
    /**
     * 点击资产代码链接跳转到该资产标的信息查看页面；
     */
    table.bind('click','.finprodId-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002"),
            basePath + "invest/asset/bond/FinBondTargetAsset002/init",
            "95%", "90%",
            { pageModel:PAGE_MODEL_REFERENCE,
                finprodId: row.finprodId,
                finprodName: row.finprodName });
    });
    /**
     * 委托转债代码链接
     */
    table.bind('click','.finprodIdLink', function(row) {
        //在新建界面进行显示单行的详细信息
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath
            + "invest/asset/bond/FinBondTargetAsset002/init",
            "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.finprodId  //金融产品代码
            });
    });
    $(function () {
        // 绑定按钮事件
        $('#TrdBondToStock001_btn_search').click(function() {
            table.load();
            $('#TrdBondToStock001_btn_revoke').show();
        });

        $('#TrdBondToStock001_btn_export').click(function() {
            exportExcel();
        });

        $("#tradeMarket").on("change",function(){
            if ($("#tradeMarket").val() == '01') {
                $("#tradePlat").val('01').trigger('change');
            }
            else {
                $("#tradePlat").val('').trigger('change');
            }
        });
        //更新状态
        $("#TrdBondToStock001_btn_refresh").click(function(){
            if(table!=null && table!=''){
                var arrs = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!arrs || arrs.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var tradeId = "";
            var tradeIdArr = new Array();
            $.each(arrs,function(v,t){
                if(t.validStatus == STATUS_REVOKE){
                    tradeIdArr[v] = t.tradeId + STR_R;
                }else{
                    tradeIdArr[v] = t.tradeId;
                }
            });
            tradeId = tradeIdArr.join('/');
            var object = {tradeId:tradeId};
            Joyin.ajax(basePath + 'invest/trade/bond/TrdBondToStock001/refreshStatus', object, function() {
                table.reload();
            });
        });

        $('#TrdBondToStock001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdBondToStock002.new"), basePath
                + "invest/trade/bondtostock/TrdBondToStock002/init", "99%", "99%", {
                pageModel : PAGE_MODEL_NEW
            }, function() {
                table.reload();
            });
        });

        /**
         * 撤销
         */
        $('#TrdBondToStock001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                return;
            }
            else if( !selArr || selArr.length > 1 ) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var obj = selArr[0];
            obj.operatorType = 'revoke';
            Joyin.ajaxJson(basePath + 'invest/trade/bondtostock/TrdBondToStock001/check', obj, function(result1) {
                Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                    obj.cancelRemark = result2;
                    WorkFlow.doRevokeList(basePath + 'invest/trade/bondtostock/TrdBondToStock001/doRevoke', obj, "TrdBondToStock001_btn_revoke", "TrdBondToStock002");
                });
            });
        });
    });
}());