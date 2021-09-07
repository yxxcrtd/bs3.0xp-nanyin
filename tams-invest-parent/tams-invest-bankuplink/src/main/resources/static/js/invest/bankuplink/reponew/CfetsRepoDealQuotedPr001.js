(function () {
    var getEtrInfo = false;
    $(".layui-tab").show();
    /**
     * 获取指令执行查询参数
     */
    var genzlzxTableParam = function () {
        var pageParam = $.serializeToObject('#searchForm_zlgl');
        if (pageParam.portfolioId_zlgl && pageParam.portfolioId_zlgl != '') {
            pageParam.portfolioId_zlgl = pageParam.portfolioId_zlgl.join(",");
        } else {
            pageParam.portfolioId_zlgl = null;
        }
        var cols = [];
        $.each(zlzxTable.options.cols, function (i, val) {
            if (val.visible == false) {

            } else {
                cols.push(val.field);
            }
        })
        pageParam.keycols = cols.join(",");
        return pageParam;
    };

    /**
     * 定义指令执行数据表格
     */
    var zlzxTable = $("#zlzxTable").joyintable({
        ajax: {
            url: 'queryZlzxList',
            type: 'POST',
            data: function () {
                return genzlzxTableParam();
            }
        },
        height: 180,
        searchOnInit: true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols: [
            {
                field: 'entrustStatusStr',
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.orderStatus',
                sortField: "entrustStatus"
            }
            , {
                field: 'transactioncom',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.finishPercent',
                render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:" + row.transactioncom + "%'><span class='layui-progress-text'>" + row.transactioncom + "%</span></div></div>";
                },
                width: '8%',
                sortable: false
            }
            , {
                field: 'tradeFinishPercent',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.tradeFinishPercent',
                render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:" + row.tradeFinishPercent + "%'><span class='layui-progress-text'>" + row.tradeFinishPercent + "%</span></div></div>";
                },
                width: '8%',
                sortable: false
            }
            , {
                field: 'invManagerName',
                width: 120,
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.invManagerId'
            }
            , {field: 'portfolioId', width: 120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioId'}
            , {
                field: 'portfolioName',
                width: 140,
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioName'
            }
            , {field: 'secAcctName', width: 120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.secAcctId'}
            , {field: 'secAcctId', width: 120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.secAcctId',visible: false}
            , {field: 'settleSpreedStr', width: 90, title: '清算速度', sortField: "settleSpreed"}
            , {
                field: 'psStr',
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustPs',
                sortField: "ps"
            }
            , {
                field: 'entrustNum',
                width: 125,
                type: 'number',
                title: 'CfetsRepoDealQuotedPrice001.zlzxTable.table.entrustNum'
            }
            , {
                field: 'finishAmt',
                width: 125,
                type: 'number',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.comQuota'
            }
            , {
                field: 'avaNum',
                width: 125,
                type: 'number',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.avaQuota'
            }
            , {field: 'price', width: 150, title: '回购利率%'}
            , {
                field: 'vdate',
                width: 80,
                type: 'date',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustStartDate'
            }
            , {
                field: 'mdate',
                width: 80,
                type: 'date',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustEndDate'
            }
            , {field: 'term', width: 150, title: '回购期限（天)', visible: false}
            , {field: 'termType', width: 150, title: '期限品种', visible: false}
            , {field: 'mySide', width: 150, title: '本方', visible: false}
            , {field: 'ps', width: 90, visible: false}
            , {field: 'settleSpreed', visible: false}
            , {field: 'entrustStatus', visible: false}
            , {field: 'portfolioSeries', visible: false}
            , {field: 'entrustId', width: 170, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustId'}
            , {
                field: 'entrustStatusForShow',
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.operation',
                fixed: 'right',
                width: 75,
                sortable: false,
                render: function (data, type, row, meta) {
                    if (row.entrustStatus == entrustStatusUnAccept) {
                        return '<a class="CfetsRepoDealQuotedPrice001_entrust_accept layui-btn layui-btn-sm" style="color:white;line-height: 21px;padding-left: 7px;width: 42px">接收</a>';
                    } else if (row.entrustStatus == entrustStatusAccept && row.transactioncom == 0 && row.finishAmt == 0) {
                        return '<a class="CfetsRepoDealQuotedPrice001_entrust_giveup layui-btn layui-btn-sm" style="color:white;background-color: #ff5809;line-height: 21px;padding-left: 7px;width: 42px">放弃</a>';
                    } else {
                        return '';
                    }
                }
            }
        ]
    });
    /**
     * 接收
     */
    zlzxTable.bind('click', '.CfetsRepoDealQuotedPrice001_entrust_accept', function (row) {
        //调用接收委托业务组件
        Joyin.ajax(JoyinUtil.getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/receiveEntrust'), {entrustIds: row.entrustId}, function (result) {
            //成功后刷新table
            zlzxTable.table ? zlzxTable.reload() : zlzxTable.load();
        });
    });
    /**
     * 放弃
     */
    zlzxTable.bind('click', '.CfetsRepoDealQuotedPrice001_entrust_giveup', function (row) {
        //调用接收委托业务组件
        Joyin.ajax(JoyinUtil.getRealPath('invest/bankuplink/common/CfetsQuotedCommon/entrustGiveUpHandle'), {entrustId: row.entrustId}, function (result) {
            //成功后刷新table
            zlzxTable.table ? zlzxTable.reload() : zlzxTable.load();
        });
    });
    /**
     * 传输数据给002
     */
    var getEtrInfoChange = function (date) {
        //获取指令执行被选中的数据
        var selArr = zlzxTable.getAllCheckedRows();
        if (date == 'cfetsRepoDealQuotedPre003') {
            if (selArr && selArr.length > 1) {
                //是否满足如下条件：同一回购期限+同一方向+同一清算速度+同一交易账户（21位码/6位码）
                var msg = "";
                var entrustId = "";
                var termFlag = true;
                var psFlag = true;
                var settleSpreedFlag = true;
                var mysideFlag = true;
                for (var i = 0; i < selArr.length - 1; i++) {
                    if (termFlag && selArr[i].term != selArr[i + 1].term) {
                        termFlag = false;
                        msg += "回购期限&"
                    }
                    if (psFlag && selArr[i].ps != selArr[i + 1].ps) {
                        psFlag = false;
                        msg += "交易方向&"
                    }
                    if (settleSpreedFlag && selArr[i].settleSpreed != selArr[i + 1].settleSpreed) {
                        settleSpreedFlag = false;
                        msg += "清算速度&"
                    }
                    if (mysideFlag && selArr[i].mySide != selArr[i + 1].mySide) {
                        mysideFlag = false;
                        msg += "交易账户&"
                    }
                }
                if (!(termFlag && psFlag && settleSpreedFlag && mysideFlag)) {
                    //“所选指令不满足汇总条件，【回购期限&交易方向&清算速度&交易账户】不同
                    msg = msg.charAt(msg.length - 1) == "&" ? msg.substr(0, msg.length - 1) : msg;
                    Joyin.error("所选指令不满足汇总条件， [" + msg + "]不同");
                    return;
                } else {
                    //进入多组合界面
                    $.each(selArr, function (i, e) {
                        entrustId += e.entrustId + ",";
                    })
                    var object = {entrustId: entrustId, operation: "CfetsRepoDealQuotedPrice001.new", isDzh: "Y"};
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function (result) {
                        if (result.data) {
                            parent.CfetsRepoDealQuotedPr003.dbclickEtr(selArr, entrustId);
                        }
                    });
                }
            }
        }
        if (date == 'cfetsRepoDealQuotedPre002') {
            if (selArr) {
                //单组合
                if (selArr.length == 1) {
                    var object = {entrustId: selArr[0].entrustId, operation: "CfetsRepoDealQuotedPrice001.new"};
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function (result) {
                        if (result.data) {
                            parent.CfetsRepoDealQuotedPr002.dbclickEtr(selArr);
                        }
                    });
                }
            } else {
                parent.CfetsRepoDealQuotedPr002.dbclickEtr(selArr);
            }
        }
    };

    /**
     * 获取质押券管理查询参数
     */
    var genzyqglTableParam = function () {
        var pageParam = $.serializeToObject('#searchForm_zyqgl');
        if (pageParam.portfolioId_zyqgl && pageParam.portfolioId_zyqgl != '') {
            pageParam.portfolioId_zyqgl = pageParam.portfolioId_zyqgl.join(",");
        } else {
            pageParam.portfolioId_zyqgl = null;
        }
        var cols = [];
        $.each(zyqglTable.options.cols, function (i, val) {
            if (val.visible == false) {

            } else {
                cols.push(val.field);
            }
        })
        pageParam.keycols = cols.join(",");
        return pageParam;
    };

    /**
     * 定义质押券管理数据表格
     */
    var zyqglTable = $("#zyqglTable").joyintable({
        ajax: {
            url: 'queryZyqglList',
            type: 'POST',
            data: function () {
                return genzyqglTableParam();
            }
        },
        height: 180,
        searchOnInit: true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols: [
            {field: 'portfolioId', width: 120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioId'}
            , {
                field: 'portfolioName',
                width: 140,
                title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioName'
            }
            , {field: 'secAcctName', width: 150, title: '证券账户'}
            , {field: 'bondCode', width: 150, title: '债券代码'}
            , {field: 'bondAbbrName', width: 150, title: '债券简称'}
            // 可质押券面总额
            , {field: 'posStrdPrinAmtTo', width: 200, title: '持仓券面总额(万元)', type: 'number', sortable: false}
            //质押券汇总券面总额
            , {field: 'pledStrdPrinAmtTo', width: 200, title: '质押券券面总额(万元)', type: 'number', sortable: false}
            //折算比例%
            , {field: 'pledgeRate', width: 150, title: '折算比例%', type: 'number'}
            , {field: 'gradeResultStr', width: 150, title: '债项评级',sortable: 'gradeResult'}
            , {field: 'mdate', width: 150, type: 'date', title: '到期日'}
            , {field: 'repRegistOrgStr', width: 150, title: '登记托管机构', type: 'text'}
            , {field: 'lastPeriod', width: 150, title: '剩余期限', type: 'number', sortable: false}
        ]
    });
    /**
     * 获取委托查询对话查询参数
     */
    var genwtdhbjTableParam = function () {
        var pageParam = $.serializeToObject('#searchForm_wtcxdh');
        return pageParam;
    };

    /**
     * 定义委托查询对话数据表格
     */
    var wtdhbjTable = $("#wtdhbjTable").joyintable({
        ajax: {
            url: 'queryWtdhbjList',
            type: 'POST',
            data: function () {
                return genwtdhbjTableParam();
            }
        },
        height: 180,
        searchOnInit: true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols: [
            {field: 'validStatusStr', width: 90, title: '有效状态', sortField: "validStatus"}
            , {field: 'validStatus', width: 90, title: '有效状态', sortField: "validStatus", visible: false}
            , {field: 'workflowId', width: 90, title: '有效状态', visible: false}
            , {
                field: 'quoteRoleTypeStr',
                width: 120,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.myRole',
                sortField: "quoteRoleType"
            }
            , {
                field: 'psStr',
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.searchArea.myPs',
                sortField: "ps",
                render: function (data, type, row, meta) {
                    if (data == ps_pay_name) {
                        return '<a style="color:red;">' + data + '</a>';
                    } else if (data == ps_sell_name) {
                        return '<a style="color:green;">' + data + '</a>';
                    }
                }
            }
            , {
                field: 'tradeStatusStr',
                width: 100,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealStatus',
                sortField: "tradeStatus"
            }
            , {
                field: 'tradeStatus',
                width: 120,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealStatus',
                sortField: "tradeStatus",
                visible: false
            }
            , {
                field: 'sendStatusStr',
                width: 140,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.sendStatus',
                sortField: "sendStatus",
                render: function (data, type, row, meta) {
                    if (data == sendStatus_fail) {
                        return "<a class='CfetsRepoDealQuotedPrice001_dayOffer_fail layui-btn layui-btn-sm' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>失败</a>"
                            + '<a class="CfetsRepoDealQuotedPrice001_dayOff_restore layui-btn layui-btn-sm" style="color:white;background-color: #ff5809;line-height: 21px;padding-left: 7px;width: 42px">回退</a>';
                    } else if (data == sendStatus_send && row.tradeStatus == quoteStatus_added) {
                        return "<a class='CfetsRepoDealQuotedPrice001_dayOffer_fail layui-btn' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>超时</a>"
                            + "<a class='CfetsRepoDealQuotedPrice001_dayOffer_f5 layui-btn' style='color:white;background-color: green;line-height: 21px;padding-left: 7px;width: 42px'>刷新</a>";
                    } else {
                        return data;
                    }
                }
            }
            , {field: 'ouPartyName', width: 120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.my'}
            , {field: 'cpPartyName', width: 120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.searchArea.counterId'}
            , {field: 'cpTraderName', width: 120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealCounterId'}
            , {
                field: 'fpriceAmt',
                width: 160,
                title: '交易金额',
                type: "money"
            }
            , {field: 'term', width: 90, title: '回购期限'}
            , {field: 'rate', width: 130, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.rate'}
            , {
                field: 'bondCode',
                width: 90,
                title: '代码',
                sortField: "bondCode"
            }
            , {
                field: 'bondAbbrName',
                width: 90,
                title: '名称',
                sortField: "bondAbbrName"
            }
            , {field: 'pledgeRate', width: 130, title: '折算比例(%)'}
            , {
                field: 'prin',
                width: 135,
                title: '券面总计(万)',
                type: "money"
            }
            , {
                field: 'settleSpeedStr',
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.settleSpeed',
                sortField: "settleSpeed"
            }
            , {
                field: 'mIntAmt',
                width: 135,
                title: '回购利息',
                type: "money"
            }
            , {
                field: 'mTradeAmt',
                width: 150,
                title: '到期结算金额',
                type: "money"
            }
            , {
                field: 'settTypeStr',
                width: 120,
                title: '清算类型',
                sortField: "settType"
            }
            , {
                field: 'quoteTypeStr',
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.offerType',
                sortField: "quoteType"
            }
            , {
                field: 'settCcyStr',
                width: 120,
                title: '债券币种',
                sortField: "settCcy"
            }
            , {
                field: 'ccyStr',
                width: 120,
                title: '结算币种',
                sortField: "ccy"
            }
            , {field: 'exchangerate', width: 130, title: '汇率'}
            , {field: 'cfetsQuoteId', width: 155, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.offerId'}
            , {
                field: 'lstmntdate',
                type: "date",
                width: 120,
                title: '最后更新时间'
            }
            , {field: 'fTradeId', width: 150, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealId'}
            , {field: 'isDzh', width: 150, visible: false}
            , {field: 'quoteRoleType', width: 90, visible: false}
            , {field: 'ouTraderName', width: 120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.myTrader',visible: false}
            , {
                field: 'vSettleDate',
                type: "date",
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.vSettleDate',
                visible: false
            }
            , {
                field: 'mSettleDate',
                type: "date",
                width: 90,
                title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.mSettleDate',
                visible: false
            }
            , {
                field: 'operation', title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.operation', width: 75,
                sortable: false, fixed: 'right',
                render: function (data, type, row, meta) {
                    if (row.validStatus != '04' && row.tradeStatus == tradeStatusPending
                        && (row.quoteRoleType == quoteRoleTypeReply || row.quoteRoleType == quoteRoleTypeEnquiry)
                        && row.sendStatus != '04') {
                        return '<a class="CfetsRepoDealQuotedPrice001_dayOffer_reply layui-btn layui-btn-sm" style="color:white;line-height: 21px;padding-left: 7px;width: 42px">回复</a>';
                    }
                    //有效待发送  交易状态为空或者新增中 可删除
                    else if (row.validStatus != '04' && row.sendStatus == '00' && (row.tradeStatus == '' || row.tradeStatus == null || row.tradeStatus == '01')) {
                        return '<a class="CfetsRepoDealQuotedPrice001_dayOff_delete layui-btn layui-btn-sm" style="color:white;background-color: orange;line-height: 21px;padding-left: 7px;width: 42px">删除</a>';
                    } else {
                        return '';
                    }
                }
            }
        ],
        dblclick: function (table, data) {
            // 判断交易状态
            var object = data;
            object.operation = "CfetsRepoDealQuotedPrice001.edit";
            var url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/init';
            if (object.isDzh == 'Y' || object.quoteRoleType == quoteRoleTypeReply) {
                url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr003/init';
            }
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                + url, "99%", "99%", {
                pageModel: data.validStatus == '01' && (data.tradeStatus == '' || data.tradeStatus == null) && data.sendStatus == unSendStatus ? PAGE_MODEL_NEW : PAGE_MODEL_REFERENCE,
                entrustId: data.entrustId,
                quoteId: data.quoteId,
                workflowStatus: data.validStatus,
                workflowId: data.workflowId,
                quotaPageModel: 'edit'  //add by zhangnian 20210116  双击进去默认做修改操作
            }, function () {
                wtdhbjTable.table ? wtdhbjTable.reload() : wtdhbjTable.load();
            });
        }
    });

    /**
     * 获取委托查询请求查询参数
     */
    var gencjcxTableParam = function () {
        var pageParam = $.serializeToObject('#searchForm_cjcx');
        pageParam.finprodType = "REPO";
        pageParam.tradeDateBegin = $('#tradeDate').startDate();
        pageParam.tradeDateEnd = $('#tradeDate').endDate();
        if (pageParam.matchStatus && pageParam.matchStatus != '') {
            pageParam.matchStatus = pageParam.matchStatus.join(",");
        } else {
            pageParam.matchStatus = null;
        }
        var cols = [];
        $.each(cjcxTable.options.cols, function (i, val) {
            if (val.visible == false) {

            } else {
                cols.push(val.field);
            }
        })
        pageParam.keycols = cols.join(",");
        return pageParam;
    };

    /**
     * 定义委托查询对请求数据表格
     */
    var cjcxTable = $("#cjcxTable").joyintable({
        ajax: {
            url: 'gencjcxTableParam',
            type: 'POST',
            data: function () {
                return gencjcxTableParam();
            }
        },
        height: 180,
        searchOnInit: true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols: [
            {field: 'portfolioId', title: 'CSTPSelect001.portfolioId', width: '150'}
            , {field: 'portfolioName', title: 'CSTPSelect001.portfolioName', width: '100', visible: false}
            , {field: 'execid', title: 'CSTPSelect001.execid', width: '200'}
            , {field: 'psStr', title: '本方方向', sortField: 'side', width: '100'}
            , {field: 'ourinstitutioncode', title: '本方', width: '100'}
            , {field: 'counterinstitutioncode', title: '对手方', width: '100'}
            , {field: 'cpPartyName', title: '对手方机构', width: '100'}
            , {field: 'tradecashamt', title: 'CSTPSelect001.tradecashamt', width: '170', type: 'money'}
            , {field: 'tradelimitdaysStr', title: 'CSTPSelect001.tradelimitdays', width: '150'}
            , {field: 'priceTxt', title: 'CSTPSelect001.backrate', width: '120'}
            , {field: 'securityid', title: '代码', width: '150'}
            , {field: 'symbol', title: '名称', width: '150'}
            , {field: 'pledgeRateStr', title: '折算比例(%)', width: '120'}
            , {field: 'lastqty', title: '券面总计（万元）', width: '210', type: 'money', digit: 2}
            , {field: 'ourTradeName', title: '本方交易员', width: '100'}
            , {field: 'ourinstitutionchineseshortname', title: '本方代理机构', width: '100'}
            , {field: 'counterTraderName', title: '对手方交易员', width: '100'}
            , {field: 'institutionchineseshortname', title: '对手方代理机构', width: '100'}
            , {field: 'settleSpeedStr', title: '清算速度', width: '100'}
            , {
                field: 'accruedinteresttotalamt',
                title: 'CSTPSelect001.accruedinteresttotalamthg',
                width: '165',
                type: 'money',
                digit: 2
            }
            , {field: 'settlcurramt2', title: 'CSTPSelect001.settlcurramt2', width: '185', type: 'money'}
            , {field: 'cashholdingdays', title: 'CSTPSelect001.cashholdingdays', width: '200'}
            , {
                field: 'settldate',
                title: '首次结算日',
                sortField: 'settldate',
                width: '110',
                type: 'date'
            }
            , {
                field: 'settldate2',
                title: '到期结算日',
                sortField: 'settldate2',
                width: '110',
                type: 'date'
            }
            , {field: 'settTypeStr', title: '清算类型', sortField: 'settType', width: '110'}
            , {field: 'deliveryTypeStr', title: '首次结算方式', sortField: 'deliveryType', width: '110'}
            , {field: 'deliveryType2Str', title: '到期结算方式', sortField: 'deliveryType2', width: '110'}
            , {field: 'tradeMethod', title: '交易方式', sortField: 'tradeMethod', width: '110'}
            , {field: 'repoMethodStr', title: '回购方式', sortField: 'repoMethod', width: '110'}
            , {field: 'tradeDateStr', title: 'CSTPSelect001.tradedate1', sortField: 'tradedate', width: '110'}
            , {field: 'execType', title: '成交状态', sortField: 'execType', width: '110'}
            , {field: 'securityId', title: '交易品种', sortField: 'securityId', width: '110'}
            , {field: 'ccyStr', title: '债券币种', sortField: 'ccy', width: '110'}
            , {field: 'settlcurrencyStr', title: '结算币种', sortField: 'settlcurrency', width: '110'}
            , {field: 'exchangerate', width: 130, title: '汇率'}
            , {field: 'reference', width: 130, title: '报价编号'}
            , {field: 'ourcapitalbankname', width: 130, title: '本方资金开户行'}
            , {field: 'ourcapitalaccountname', width: 130, title: '本方资金账户户名'}
            , {field: 'ourcapitalaccountnumber', width: 130, title: '本方资金账号'}
            , {field: 'ourpaymentsystemcode', width: 130, title: '本方支付系统行号'}
            , {field: 'ourdepositaccountname', width: 130, title: '本方托管账户户名'}
            , {field: 'ourdepositaccountnumber', width: 130, title: '本方托管账户'}
            , {field: 'ourdepositinstitution', width: 130, title: '本方托管机构'}
            , {field: 'countercapitalbankname', width: 130, title: '对手方资金开户行'}
            , {field: 'countercapitalaccountname', width: 130, title: '对手方资金账户户名'}
            , {field: 'countercapitalaccountnumber', width: 130, title: '对手方资金账号'}
            , {field: 'counterpaymentsystemcode', width: 130, title: '对手方支付系统行号'}
            , {field: 'counterdepositaccountname', width: 130, title: '对手方托管账户户名'}
            , {field: 'counterdepositinstitution', width: 130, title: '对手方托管账户'}
            , {field: 'counterdepositaccountnumber', width: 130, title: '对手方托管机构'}
            , {field: 'matchStatusStr', title: 'CSTPSelect001.matchStatus', sortField: 'matchStatus', width: '100'}
            , {field: 'acctNo', title: 'CSTPSelect001.secAccount', width: '150',visible: false}
            , {field: 'businesstypeStr', title: 'CSTPSelect001.finprodType', sortField: 'businesstype', width: '120',visible: false}
            , {field: 'securityid', title: '期限类型', width: '120',visible: false}
            , {field: 'entrustId', width: '120',visible: false}
            , {field: 'tradeId', width: '120',visible: false}
            , {field: 'fTradeId', width: '120',visible: false}
            , {field: 'rTradeId', width: '120',visible: false}
            , {field: 'ps', sortField: 'side', visible: false}
            , {field: 'settleSpeed', title: '清算速度', width: '100', visible: false}
            , {
                field: 'settleDate',
                title: 'CSTPSelect001.settleDate',
                sortField: 'settledate',
                width: '110',
                type: 'date',
                visible: false
            }
        ]
    });
    /**
     * 导出
     */
    var exportExcel = function (bttn) {
        if (bttn === 'zlzx') {
            if (zlzxTable != null && zlzxTable != '') {
                var selArr = zlzxTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                var paramObj = genzlzxTableParam();
                paramObj.exportMenu = bttn;
                Joyin.exportExcel('export', paramObj);
            } else {
                Joyin.exportExcel('export', {
                    datas: datas,
                    exportMenu: bttn
                });
            }
        }
        if (bttn === 'wtcxdh') {
            if (wtdhbjTable != null && wtdhbjTable != '') {
                var selArr = wtdhbjTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                var paramObj = genwtdhbjTableParam();
                paramObj.exportMenu = bttn;
                Joyin.exportExcel('export', paramObj);
            } else {
                Joyin.exportExcel('export', {
                    datas: datas,
                    exportMenu: bttn
                });
            }
        }
        /*        if (bttn === 'wtcxqq') {
                    if (wtcxqqTable != null && wtcxqqTable != '') {
                        var selArr = zlzxTable.getAllCheckedRows();
                        var datas = JSON.stringify(selArr);
                    }
                    if (!selArr || selArr.length == 0) {
                        var paramObj = genwtcxqqTableParam();
                        paramObj.exportMenu = bttn;
                        Joyin.exportExcel('export', paramObj);
                    } else {
                        Joyin.exportExcel('export', {
                            datas: datas,
                            exportMenu: bttn
                        });
                    }
                }*/
        if (bttn === 'cjcx') {
            if (cjcxTable != null && cjcxTable != '') {
                var selArr = cjcxTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                var paramObj = gencjcxTableParam();
                paramObj.exportMenu = bttn;
                Joyin.exportExcel('export', paramObj);
            } else {
                Joyin.exportExcel('export', {
                    datas: datas,
                    exportMenu: bttn
                });
            }
        }
    };
    /**
     * 通过报价编号 获取关系数据
     * @param obj
     */
    var getEtrReByQuoteId = function (obj) {
        var entrustIds = "";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/selectEtrReByQuoteId', {
                quoteId: obj.quoteId,
                workflowId: obj.workflowId
            },
            function (result) {
                if (result.length > 0) {
                    $.each(result, function (i, e) {
                        entrustIds += e.entrustId + ",";
                    })
                }
            }, false)
        return entrustIds;
    }


    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPr001 = {
        getEtrInfoChange: getEtrInfoChange
    };
    CfetsRepoDealQuotedPr001 = {
        getEtrInfoChange: getEtrInfoChange
    };
    /**
     * 画面加载完成
     */
    $(function () {
        $(".duihua").show();
        $('.zyqgl-transfer-form #CfetsRepoDealQuotedPr001_btn_export').hide();
        $('.zyqgl-transfer-form #CfetsRepoDealQuotedPr001_btn_filter').hide();
        $('.wtcxdh-transfer-form #CfetsRepoDealQuotedPr001_btn_filter').hide();
        $('.wtcxdh-transfer-form #CfetsRepoDealQuotedPr001_btn_export').hide();
        $('.wtcxbj-transfer-form #CfetsRepoDealQuotedPr001_btn_filter').hide();
        $('.wtcxbj-transfer-form #CfetsRepoDealQuotedPr001_btn_export').hide();
        /**
         * 对话按钮点击事件
         */
        $('#cfetsRepoDealQuotedPre001_wtcx_duihua').click(function () {
            $(".duihua").show();
        });
        /**
         * 请求按钮点击事件
         */
        $('#cfetsRepoDealQuotedPr001_wtcx_qingqiu').click(function () {
        });

        /**
         * 质押券管理点击事件
         */
        $('#cfetsRepoDealQuotedPr001_cjcx_zyqzh').click(function () {
            if (cjcxTable != null && cjcxTable != '') {
                var selArr = cjcxTable.getAllCheckedRows();
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
            Joyin.dialog(JoyinUtil.getLabel("质押券置换"), basePath + "invest/bankuplink/reponew/CfetsRepoDealQuotedPr006/init", "99%", "99%", selArr[0], {pageModel: PAGE_MODEL_EDIT}, function () {
                cjcxTable.reload();
            });
        });

        /**
         * 指令执行查询按钮点击事件
         */
        $('.zlgl-transfer-form #CfetsRepoDealQuotedPr001_btn_search').click(function () {
            zlzxTable.load();
        });
        /**
         * 质押券管理查询按钮点击事件
         */
        $('.zyqgl-transfer-form #CfetsRepoDealQuotedPr001_btn_search').click(function () {
            zyqglTable.load();
        });

        /**
         * 委托查询对话查询按钮点击事件
         */
        $('.wtcxdh-transfer-form #CfetsRepoDealQuotedPr001_btn_search').click(function () {
            wtdhbjTable.load();
        });
        /**
         * 委托查询报价查询按钮点击事件
         */
        $('.wtcxbj-transfer-form #CfetsRepoDealQuotedPr001_btn_search').click(function () {
            /*wtcxqqTable.load();*/
        });
        /**
         * 成交查询查询按钮点击事件
         */
        $('.cjcx_transfer-form #CfetsRepoDealQuotedPr001_btn_search').click(function () {
            cjcxTable.load();
        });

        /**
         * 指令执行放弃按钮点击事件
         */
        $('#cfetsRepoDealQuotedPr001_zlg1_tuihui').click(function () {
            var rowArr = zlzxTable.getAllCheckedRows();// 所有选中行数据
            var roleidArray = new Array("");
            for (var i = 0; i < rowArr.length; i++) {
                roleidArray.push(rowArr[i].entrustId);
            }
            roleidArray = roleidArray.join(",").substring(1);
            if (rowArr == null || rowArr.length == 0) {
                layer.msg("请选择至少1条记录进行放弃。");
            } else {
                //调用接收委托业务组件
                Joyin.ajax(JoyinUtil.getRealPath('invest/bankuplink/common/CfetsQuotedCommon/entrustGiveUpHandleList'), {entrustIds: roleidArray}, function (result) {
                    //成功后刷新table
                    zlzxTable.table ? zlzxTable.reload() : zlzxTable.load();
                });
            }
        });

        /**
         * 指令执行接收按钮点击事件
         */
        $('#cfetsRepoDealQuotedPr001_zlg1_jieshou').click(function () {
            var rowArr = zlzxTable.getAllCheckedRows();// 所有选中行数据
            var roleidArray = new Array("");
            for (var i = 0; i < rowArr.length; i++) {
                roleidArray.push(rowArr[i].entrustId);
            }
            roleidArray = roleidArray.join(",").substring(1);
            if (rowArr == null || rowArr.length == 0) {
                layer.msg("请选择至少1条记录进行接收。");
            } else {
                //调用接收委托业务组件
                Joyin.ajax(JoyinUtil.getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/receiveEntrust'), {entrustIds: roleidArray}, function (result) {
                    //成功后刷新table
                    zlzxTable.table ? zlzxTable.reload() : zlzxTable.load();
                });
            }
        });

        /**
         * 指令执行导出按钮点击事件
         */
        $('.zlgl-transfer-form #CfetsRepoDealQuotedPr001_btn_export').click(function () {
            exportExcel('zlzx');
        });

        /**
         * 委托查询对话导出按钮点击事件
         */
        $('#CfetsRepoDealQuotedPr002_btn_export').click(function () {
            exportExcel('wtcxdh');
        });

        /**
         * 成交查询导出按钮点击事件
         */
        $('.cjcx_transfer-form #CfetsRepoDealQuotedPr001_btn_export').click(function () {
            exportExcel('cjcx');
        });

        $("#cfetsRepoDealQuotedPrice001_wtdh_xiugai").click(function () {
            if (wtdhbjTable != null && wtdhbjTable != '') {
                var selArr = wtdhbjTable.getAllCheckedRows();
                if (!selArr || selArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("请选择需要修改的对话报价！"));
                    return;
                }
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                var object = selArr[0];
                object.operation = "CfetsRepoDealQuotedPrice001.edit";
                //多组合标识或者本方询价方为回复方 均跳转多组合界面
                var url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/init';
                if (object.isDzh == 'Y' || object.quoteRoleType == quoteRoleTypeReply) {
                    var entrustIds = getEtrReByQuoteId(object);
                    object.entrustId = entrustIds;
                    url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr003/init';
                }
                Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function (result) {
                    if (result.data) {
                        Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                            + url, "99%", "99%", {
                            pageModel: PAGE_MODEL_EDIT,
                            entrustId: object.entrustId,
                            quoteId: object.quoteId,
                            editOperation: true,
                            quotaPageModel: 'edit',
                            operation: "CfetsRepoDealQuotedPrice001.edit"
                        }, function () {
                            // 刷新当日报价
                            wtdhbjTable.table ? wtdhbjTable.reload() : wtdhbjTable.load();
                        });
                    }
                });
            }
        });

        /**
         * 成交按钮点击事件
         */
        $("#cfetsRepoDealQuotedPrice001_wtdh_chengjiao").click(function () {

        });

        /**
         * 拒绝按钮点击事件
         */
        $("#cfetsRepoDealQuotedPrice001_wtdh_jujue").click(function () {
            var object = wtdhbjTable.getAllCheckedRows();
            if(object.length != 1){
                Joyin.error("请选择一条数据进行操作")
                return;
            }

        });
        /**
         * 当日报价失败信息
         */
        wtdhbjTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOffer_fail', function(row) {
            // 判断选择记录的状态
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/selectErrlog', {quoteId : row.quoteId}, function(result) {
                if (result.data){
                    Joyin.alert(result.data,2);
                }
            });
        });
        /**
         * 当日报价回退
         */
        wtdhbjTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOff_restore', function(row) {
            var object = row;
            object.operation = "CfetsRepoDealQuotedPrice001.dayOff.restore";
            object.quotaPageModel = 'restoredayOff';
            //多组合标识或者本方询价方为回复方 均跳转多组合界面
            if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                var entrustIds = getEtrReByQuoteId(object);
                object.entrustId = entrustIds;
            }
            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                if (result.data){
                    Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_002"), function() {
                        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/restoreDayOff', object, function(result){
                            // 刷新当日报价
                            wtdhbjTable.table ? wtdhbjTable.reload() : wtdhbjTable.load();
                        })
                    })
                }
            });
        });
        /**
         * 当日报价回复
         */
        wtdhbjTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOffer_reply', function(row) {
            var object = row;
            object.operation = "CfetsRepoDealQuotedPrice001.reply";
            //多组合标识或者本方询价方为回复方 均跳转多组合界面
            var url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002/init';
            if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                var entrustIds = getEtrReByQuoteId(object);
                object.entrustId = entrustIds;
                url = 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr003/init';
            }
            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                if (result.data){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                        + url, "99%", "99%", {
                        pageModel : PAGE_MODEL_EDIT,
                        entrustId : row.entrustId,
                        quoteId : row.quoteId,
                        quotaPageModel : 'dialogueResponse',
                        operation : "CfetsRepoDealQuotedPrice001.reply"
                    }, function() {
                        // 刷新当日报价
                        wtdhbjTable.table ? wtdhbjTable.reload() : wtdhbjTable.load();
                    });
                }
            });
        });
        /**
         * 当日报价删除
         */
        wtdhbjTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOff_delete', function(row) {
            var object = row;
            object.operation = "CfetsRepoDealQuotedPrice001.dayOff.delete";
            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                if (result.data){
                    Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_003"), function() {
                        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/deleteDayOff', object, function(result){
                            // 刷新当日报价
                            wtdhbjTable.table ? wtdhbjTable.reload() : wtdhbjTable.load();
                        })
                    })
                }
            });
        });
        /**
         * 当日报价撤回点击事件
         */
        $("#cfetsRepoDealQuotedPr001_wtdh_chenxiao").click(function () {
            if (wtdhbjTable != null && wtdhbjTable != '') {
                var selArr = wtdhbjTable.getAllCheckedRows();
                if (!selArr || selArr.length == 0) {
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_001"));
                    return;
                }
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                    var data = {};
                    $.each(selArr, function (i, val) { //两个参数，第一个参数表示遍历的数组的下标，第二个参数表示下标对应的值
                        val.operation = "CfetsRepoDealQuotedPrice001.revert";
                    });
                    data.dataList = selArr;
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEditList',
                        data, function (result) {
                            if (result.data) {
                                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_005"), function () {
                                    Joyin.ajaxJson(basePath
                                        + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/revertList',
                                        data, function (result) {
                                            wtdhbjTable.table ? wtdhbjTable.reload()
                                                : wtdhbjTable.load();
                                        });
                                });
                            }
                        });

                } else {
                    var object = selArr[0];
                    if (object.isDzh == 'Y' || object.quoteRoleType == quoteRoleTypeReply) {
                        var entrustIds = getEtrReByQuoteId(object);
                        object.entrustId = entrustIds;
                    }
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit',
                        object, function (result) {
                            if (result.data) {
                                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_005"), function () {
                                    Joyin.ajaxJson(basePath
                                        + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/revert',
                                        object, function (result) {
                                            wtdhbjTable.table ? wtdhbjTable.reload()
                                                : wtdhbjTable.load();
                                        });
                                });
                            }
                        });
                }
            }
        });

    });

}());