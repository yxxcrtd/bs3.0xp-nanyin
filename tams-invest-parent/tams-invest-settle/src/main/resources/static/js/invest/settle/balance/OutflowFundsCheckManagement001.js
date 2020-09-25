var parenPage = function () {
    var portfolioId = []
    var portfolioName = [];
    var acctNo='';
    var busiAcctNo='';
    var flowLeftTable = $("#flowLeftTable").joyintable({
        ajax: {
            url: 'findPortfolioUnmatched',
            type: 'POST',
            data: function () {
                var acctNos = $("#escrowAcctNo").val();
                if (acctNos) {
                    acctNos = acctNos.join(',');
                }
                return {
                    acctNo: acctNos
                };
            }
        },
        click: function (table, rowData) {
            portfolioId = []
            portfolioName = [];
            if (rowData.portfolioId) {
                portfolioId = rowData.portfolioId.split(",");
                portfolioName = rowData.portfolioName.split(",");
            }
            if(rowData.acctNo) {
                acctNo = rowData.acctNo;
            }
            if(rowData.busiAcctNo) {
                busiAcctNo = rowData.busiAcctNo;
            }
            $("#portfolioId").val('');
            $("#portfolioId").html('');
            for (var key in portfolioId) {
                $("#portfolioId").append("<option value='" + portfolioId[key] + "'>" + portfolioName[key] + "</option>")
            }
            $("#portfolioId").val(portfolioId).trigger("change");

            // $("#finprodId").val("").trigger("change");
            // $("#isMatched").val(NO).trigger("change");
            // $("#operateType").val("").trigger("change");
            // $("#paBizType").val("").trigger("change");
            // $("#cashDate").val("").trigger("change");
            //
            // $("#counterAcctNo").val("").trigger("change");
            // $("#enIsMatch").val(NO).trigger("change");
            // $("#arriveDate").val("").trigger("change");
            // $("#arriveAmtMin").val("").trigger("change");
            // $("#arriveAmtMax").val("").trigger("change");

            flowTopTable.reload();
            flowBtTable.reload();
        },
        height: 'full-' + 50,
        searchOnInit: true,
        checkbox: false,
        page: false,
        cols: [
            {
                field: 'portfolio', title: '组合', sortable: false, width:'175', render: function (data, type, row, meta) {
                    return row.busiAcctNo + "<br/>(" + row.portfolioName + ")";
                }
            }
            , {field: 'unmatchedNum', width:'20', title: '数量',sortable: false}
        ]
    });

    /**
     * 获取查询参数
     */
    var getSearchParamsSys = function () {
        var result = $.serializeToObject('#form_right_top');
        if ($("#cashDate").val()) {
            result.cashDateStart = $("#cashDate").startDate();
            result.cashDateEnd = $("#cashDate").endDate();
        }
        result.isSys = true;
        if (result.portfolioId) {
            result.portfolioId = result.portfolioId.join(",");
        } else {
            result.portfolioId = portfolioId.join(",");
        }
        if (result.finprodId) {
            result.finprodId = result.finprodId.join(",");
        }
        if (result.paBizType) {
            result.paBizType = result.paBizType.join(",");
        }
        result.acctNo = acctNo;
        return result;
    };
    var flowTopTable = $("#flowTopTable").joyintable({
        ajax: {
            url: 'findSysPage',
            type: 'POST',
            data: function () {
                return getSearchParamsSys();
            }
        },
        searchOnInit: true,
        height: 'full-' + 200,
        cols: [
            {field: 'statusValidString', title: '状态', width: 80, sortable: false}//有效状态
            , {field: 'matchStatusStr', title: '匹配情况', width: 80, sortable: false, render: function (data, type, row, meta) {
                    if (row.matchStatus != YES)
                        return "未匹配";
                    else
                        return '已匹配';
                }}
            , {field: 'paBizTypeString', title: '业务类型', width: 180, sortable: false}//业务类型
        //     , {
        //     field: 'bizCode', title: '业务编号', width: 220, sortable: false, render: function (data, type, row, meta) {
        //         return "<a class='pro-link' style='cursor: pointer;' " +
        //             "href='javascript:openOperateWindow(\"" + row.statusValid + "\",\"" + row.paBizType + "\",\"" + row.portfolioId + "\",\""
        //             + row.bizCode + "\",\"" + row.workflowId + "\",\"" + row.finprodId + "\",\"" + row.wflWorkflowId + "\","+row.todo+")'>" + data + "</a>";
        //     }
        // }//业务编号
            , {field: 'finprodMarketId', title: '产品/资产代码', width: 160, sortable: false}//产品/资产代码
            , {field: 'settleAmt', title: '实际本息总额', width: 140, type: 'money', sortField: 'SETTLE_AMT'}//实际本息总额
            /*, {field: 'portfolioId', title: '组合代码', width: 200, sortable: false}//组合代码
            , {field: 'portfolioName', title: '组合名称', width: 200, sortable: false}//组合名称*/
            /*, {field: 'finprodName', title: '产品/资产名称', width: 180, sortable: false}*///产品/资产名称
            /*, {field: 'settleAmtTheory', title: '理论本息总额', width: 180, sortable: false, type: 'money'}*///理论本息总额
            /*, {field: 'settleDateTheory', title: '理论到账日', width: 120, sortable: false}*///理论到期日
            , {field: 'settleDate', title: '现金流日期', width: 120, sortable: false}//现金流日期
            , {field: 'counter', title: '交易对手', width: 150, sortable: false}//交易对手
            /*, {
                field: 'operation', title: 'SysCommon.operation', width: 100, type: 'action', buttons: ['edit'], render: function (data, type, row, meta) {
                    if (row.matchStatus != YES)
                        return "";
                    else
                        return '<i class="btn layui-icon edit" title="取消匹配">取消匹配</i>';
                }
            }*/
            ],
        dblclick:function(table, data) {
            openOperateWindow(data.statusValid, data.paBizType, data.portfolioId, data.bizCode, data.workflowId,
                data.finprodMarketId, data.ps , data.wflWorkflowId,data.todo,data.profitType,data.finprodId);
        }
    });

/*    flowTopTable.bind('click', '.edit', function (data, index) {
        var dto = {seqNo: data.sysSeqNo, sysOrEn: data.sysOrEn};
        openDetail(dto);
    })*/

    openDetail = function (dto) {
        Joyin.dialog("匹配明细", basePath + 'invest/settle/balance/OutflowFundsCheckManagement001/manualMatchCancel', '99%', '90%', dto, function () {

        })
    }

    var unMatchData = function () {
        var sysFlow = flowTopTable.getAllCheckedRows();
        if (!sysFlow || sysFlow.length != 1 || sysFlow[0].matchStatus != YES) {
            Joyin.error("系统流水中，请选择一条已匹配的数据");
            return;
        }
        var data = sysFlow[0];
        if (sysFlow[0].matchStatus == YES) {
            var dto = {seqNo: data.sysSeqNo, sysOrEn: data.sysOrEn};
            openDetail(dto);
            return;
        }
    }

    var getSearchParamsEn = function () {
        var result = $.serializeToObject('#form_right_bot');
        if ($("#arriveDate").val()) {
            result.arriveDateStart = $("#arriveDate").startDate();
            result.arriveDateEnd = $("#arriveDate").endDate();
        }
        result.acctNo = acctNo;
        result.busiAcctNo = busiAcctNo;
        result.isSys = false;
        return result;
    };
    var flowBtTable = $("#flowBottomTable").joyintable({
        ajax: {
            url: 'findEnPage',
            type: 'POST',
            data: function () {
                return getSearchParamsEn();
            }
        },
        height: 'full-' + 200,
        searchOnInit: true,
        cols: [{field: 'settleDate', title: '到账日', width: 120, sortable: false}//到账日
            , {field: 'settleAmt', title: '金额', width: 180, type: 'number'}//金额
            , {field: 'counterAcctNo', title: '对手方账户', width: 200, sortable: false}//对手方账户
            , {field: 'counterAcctName', title: '对手方账户名称', width: 200, sortable: false}//对手账户名称
            , {field: 'usage', title: '用途', width: 200, sortable: false}//用途
            , {field: 'remark', title: '备注', width: 200, sortable: false}//备注
            , {field: 'matchStatus', title: '匹配情况', width: 100, sortable: false, render: function (data, type, row, meta) {
                    if (row.matchStatus != YES)
                        return "未匹配";
                    else
                        return '已匹配';
                }}
            /*, {
                field: 'operation', title: 'SysCommon.operation', type: 'action', render: function (data, type, row, meta) {
                    if (row.matchStatus != YES)
                        return "";
                    else
                        return '<i class="btn layui-icon edit" title="取消匹配">取消匹配</i>';
                }
            }*/
        ]
    });

    flowBtTable.bind('click', '.edit', function (data, index) {
        var dto = {seqNo: data.enSeqNo, sysOrEn: data.sysOrEn};
        openDetail(dto);
    })

    /**
     * 手工匹配
     */
    var manualMatch = function () {
        var sysFlow = flowTopTable.getAllCheckedRows();
        var enFlow = flowBtTable.getAllCheckedRows();
        if (!sysFlow || sysFlow.length == 0) {
            Joyin.error("系统流水中，" + JoyinUtil.getMessage('BUSS_COM_E_0046'))
            return;
        }
        if (!enFlow || enFlow.length == 0) {
            Joyin.error("托管流水中，" + JoyinUtil.getMessage('BUSS_COM_E_0046'))
            return;
        }
        for (var key in sysFlow) {
            if (sysFlow[key].matchStatus == YES) {
                Joyin.error("请选择未匹配的数据进行匹配。")
                return;
            }
        }
        for (var i = 1; i < sysFlow.length; i++) {
            if (sysFlow[0].secManageAcctId != sysFlow[i].secManageAcctId) {
                Joyin.error("相同托管户的流水才允许手工匹配。")
                return;
            }
        }
        for (var key in enFlow) {
            if (enFlow[key].matchStatus == YES) {
                Joyin.error("请选择未匹配的数据进行匹配。")
                return;
            }
        }

        Joyin.ajaxJson('manualMatchCheck', {sysFlow: sysFlow, enFlow: enFlow}, function (data) {
            var dto = JSON.stringify({sysFlow: sysFlow, enFlow: enFlow});
            Joyin.dialog("匹配明细", 'OutflowFundsCheckManagement002', '99%', '90%', {dtoString: dto}, function () {

            })
        })

    }

    var sysExportExcel = function () {
        Joyin.exportExcel('export', getSearchParamsSys());
    };

    var enExportExcel = function () {
        Joyin.exportExcel('export', getSearchParamsEn());
    };

    var remarkSet = function () {
        var enFlow = flowBtTable.getAllCheckedRows();
        if (!enFlow || enFlow.length == 0) {
            Joyin.error("托管流水中，" + JoyinUtil.getMessage('BUSS_COM_E_0046'))
            return;
        }
        var enSeqNos = [];
        for (var key in enFlow) {
            enSeqNos.push(enFlow[key].enSeqNo);
        }
        var params = {enSeqNos: enSeqNos.join(",")};
        Joyin.prompt("备注", 0, function (result) {
            params.remark = result;
            Joyin.ajax(basePath + 'invest/settle/balance/OutflowFundsCheckManagement001/remarkSet', params, function (data) {
                flowBtTable.reload();
            })
        });


    };


    $(function () {
//        var setted = false;
//        var ref = setInterval(function () {
//            if ($(".flow-top-table .layui-table-view").length > 0) {
//                var height = $(window).height();
//                $(".flow-top-table .layui-table-view").css({height: height / 2 - 70 + 'px'});
//                $(".flow-top-table .layui-table-view .layui-table-box .layui-table-body").css({height: (height / 2 - 80 - 70) + 'px'});
//                setted = true;
//            }
//        }, 300);
//
//        if (setted) {
//            clearInterval(ref);
//        }

        $("#escrowAcctNo").change(function () {
            flowLeftTable.reload();
        })
        $(".OutflowFundsCheckManagement001_btn_sysSearch").click(function () {
            if (portfolioId.length == 0) {
                Joyin.error("请选择托管户")
                return;
            }
            flowTopTable.reload();
        })
        $(".OutflowFundsCheckManagement001_btn_enSearch").click(function () {
            if (acctNo.length == 0) {
                Joyin.error("请选择托管户")
                return;
            }
            flowBtTable.reload();
        })
        $(".OutflowFundsCheckManagement001_btn_match").click(manualMatch)
        $("#OutflowFundsCheckManagement001_btn_unmatch").click(unMatchData)
        $("#OutflowFundsCheckManagement001_btn_sysExport").click(sysExportExcel)
        $("#OutflowFundsCheckManagement001_btn_enExport").click(enExportExcel)
        $("#OutflowFundsCheckManagement001_btn_remarkSet").click(remarkSet)

        $(".flow-left-icon").on("click", function () {
            $(this).find(".iconfont").toggleClass("icon-triangle-left");
            $(this).find(".iconfont").toggleClass("icon-triangle-right");
            $(".flow-left-contain").toggle();
            $(".flow-right-contain").toggleClass("max-width");
        });
        // $(".btn-more-top").click(function () {
        //     $(".top-hidden-con").toggle();
        //     $(this).find("span").html($(this).find("span").html() == "更多" ? "收起" : "更多");
        // });
        // $(".btn-more-bot").click(function () {
        //     $(".bot-hidden-con").toggle();
        //     $(this).find("span").html($(this).find("span").html() == "更多" ? "收起" : "更多");
        // });
    })
    return {
        refreshTable: function () {
            flowTopTable.reload();
            flowBtTable.reload();
        }
    };
}();
