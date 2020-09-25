(function () {
        /**
         * 页面初始化
         */
        var acctNoList;
        var initOpen = true;
        var calcConditionObjBackUp;
        var init = function () {
            acctNoList = JSON.parse($("#acctNoList").val());
            ChannelAssetCommon.setChannelSelect($("#chlId"), finprodId, true, function () {});
        }
        repoInfoTableOption = {
            rowno: true,
            searchOnInit: true,
            checkbox: false,
            page: false,
            cols: [
                {field: 'portfolioId', width: "200", title: '组合', sortable:false}
                , {field: 'amount', width: "200", title: '持有本金', type: 'money', sortable:false}
                , {field: 'returnAmount', width: "200", title: '还本金额', type: 'money', sortable:false}
                , {field: 'calcRuleString', width: "200", title: '付息周期', sortable:false}
                , {field: 'vDate', width: "200", title: '计息开始日', sortable:false}
                , {field: 'mDate', width: "200", title: '计息结束日', sortable:false}
                , {field: 'yield', width: "200", title: '借款利率(%)', type: 'money', sortable:false}
                , {field: 'repaymentUnpayInterest', width: "200", title: '区间未付利息', type: 'money', sortable:false,visible:intAmtUnpayFlag}
                , {field: 'actrRepaymentUnpayInterest', width: "200", title: '实际区间未付利息', type: 'money', sortable:false,visible:intAmtUnpayFlag}
                , {field: 'theoryAmt', width: "200", title: '理论本息总额', type: 'money', sortable:false}
                , {field: 'fpriceAmt',  width: "200",title: '实收本息总额', type: 'money', sortable:false,edit:true}
                // add by renping 2019-10-15 添加通道费 start
                ,{field: 'channelFee',  width: "200",title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdPlanInterestDuration002.channelFee");}(), type: 'money', sortable:false,visible:CHANNEL}
                // add by renping 2019-10-15 添加通道费 end
                , {field: 'calcRule', visible: false}
                , {field: 'taxAmount', visible: false}
                , {field: 'amount', visible: false}
                , {field: 'calcRule', visible: false}
                , {field: 'intAmt', visible: false}
                , {field: 'qty', visible: false},
                {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                    render: function (data, type, row, meta) {
                        //实际交易组合无子组合交易明细
                        if(row.investYesorno =='Y'){
                            return;
                        }
                        return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                    }
                }
            ],
            done : function(){
                if (formTodoList && initOpen) {
                    calcConditionObjBackUp = tableInitData();
                }
            },
            data: []
            /* 增加实收本息总额fpriceAmt与tradeAmt联动   add by caojin 2020/6/5 start*/
            ,editCell: function (value, row, field, cell, column) {
            	repoInfoTable.renderRow(row.LAY_TABLE_INDEX, {"tradeAmt": value});
            }
            /* 增加实收本息总额fpriceAmt与tradeAmt联动   add by caojin 2020/6/5 end*/
        }
        if (finAssetIntInfoList && finAssetIntInfoList.length > 0) {
            repoInfoTableOption.data = repoInfoTableOption.data.concat(finAssetIntInfoList);
        }
        /**
         * 定义数据表格
         */
        var repoInfoTable = $("#intDetailTable").joyintable(repoInfoTableOption);
        /**
         * 绑定编辑事件
         */
        repoInfoTable.bind('click', '.detail', function(data, index) {
            var param = {
                "portfolioId":data.portfolioId,
                "finprodMarketId":$('#finAssetInfo_finprodMarketId').val(),
                'bizCode':$("#busiCode").val(),
                "workflowId":typeof workflowId == "undefined" ? '':workflowId,
                "dealDate": new Date(data.mDate).format(),
                "defaultMap":JSON.stringify({
                    "calcRuleString":data.calcRuleString,
                    "vDate":new Date(data.vDate).format(),
                    "mDate":new Date(data.mDate).format(),
                    "yield":data.yield,
                    "calcRule":data.calcRule,
                }),
                "preMap":JSON.stringify({
                    "returnAmount":data.returnAmount,
                    "repaymentUnpayInterest":data.repaymentUnpayInterest,
                    "actrRepaymentUnpayInterest":data.actrRepaymentUnpayInterest,
                    "theoryAmt":data.theoryAmt,
                    "fpriceAmt":data.fpriceAmt,
                    "taxAmount":data.taxAmount,
                    "amount":data.amount,
                    "qty":data.qty,
                    // add by renping 2019-10-15 添加通道费 start
                    "channelFee":data.channelFee,
                    "tradeAmt":data.tradeAmt,
                    // add by renping 2019-10-15 添加通道费 end
                    "intAmt":data.intAmt,
                }),
                cols:JSON.stringify([
                     {field: 'amount', width: "200", title: '持有本金', type: 'money', sortable:false}
                    ,{field: 'returnAmount', width: "200", title: '还本金额', type: 'money', sortable:false}
                    , {field: 'calcRuleString', width: "200", title: '付息周期', sortable:false}
                    , {field: 'vDate', width: "200", title: '计息开始日', sortable:false}
                    , {field: 'mDate', width: "200", title: '计息结束日', sortable:false}
                    , {field: 'yield', width: "200", title: '借款利率(%)', type: 'money', sortable:false}
                    , {field: 'repaymentUnpayInterest', width: "200", title: '区间未付利息', type: 'money', sortable:false,visible:intAmtUnpayFlag}
                    , {field: 'actrRepaymentUnpayInterest', width: "200", title: '实际区间未付利息', type: 'money', sortable:false,visible:intAmtUnpayFlag}
                    , {field: 'theoryAmt', width: "200", title: '理论本息总额', type: 'money', sortable:false}
                    , {field: 'fpriceAmt',  width: "200",title: '实收本息总额', type: 'money', sortable:false}
                    // add by renping 2019-10-15 添加通道费 start
                    , {field: 'channelFee',  width: "200",title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdPlanInterestDuration002.channelFee");}(), type: 'money', sortable:false,visible:CHANNEL}
                    , {field: 'tradeAmt', visible: false}
                    // add by renping 2019-10-15 添加通道费 end
                    ,{field: 'intAmt', visible: false}/*实际支付利息*/
                    , {field: 'calcRule', visible: false}
                    , {field: 'taxAmount', visible: false}
                    , {field: 'amount', visible: false}
                    , {field: 'qty', visible: false}
                ])
            };
            Joyin.dialog(JoyinUtil.getLabel('TrdDurationCommon.ChildPortfolioInfo'), JoyinUtil.getRealPath('invest/period/common/ChildPortfolioInfo/init'), '80%', '80%',param);
        });
        /**
         * 定义数据表格
         *
         */
        var option = {
            searchOnInit: true,
            checkbox: false,
            cols: [
                {
                    field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true, sortable:false,
                    editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
                }
                , {field:'payTypeText', width:130, title: 'BussTrdTransferOrder.payType', sortable:false, sortable:false}
                , {field:'settleDateText', width:130, title: 'BussTrdTransferOrder.settleDate', sortable:false}
                , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable:false}
                , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable:false}
                , {
                    field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable:false,
                    editOption: {
                        type: 'select', multiple: false, searchable: true, showField: 'id', parentIds:'orderId_select,acctNoList',
                        url: JoyinUtil.getRealPath("/common/multiOrder/select"), id: 'payAcctNo', postData:function () {
                            return {selectValue: JSON.stringify(orderAcctMap), type: 'pay'};
                        }
                    }
                }
                , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable:false}
                , {
                    field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable:false,
                    editOption: {
                        type: 'select', multiple: false, searchable: true, showField: 'id', parentIds:'orderId_select,acctNoList',
                        url: JoyinUtil.getRealPath("/common/multiOrder/select"),id: 'recAcctNo',  postData:function () {
                            return {selectValue: JSON.stringify(orderAcctMap), type: 'rec'};
                        }
                    }
                }
                , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable:false}
                ,{field:'purpose', width:130, title: 'BussTrdTransferOrder.purpose', sortable:false, edit:true}
                , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable:false}
                ,{field:'portfolioName', width:150, title: 'BussTrdTransferOrder.portfolioName', sortable:false}
                , {field: 'orderId', visible: true, edit: true,width:0,
                    editOption: {
                        type: 'select', id:'orderId_select', childrenIds:'recAcctNo,payAcctNo'
                    }}
                , {field: 'ps', visible: false}
                , {field: 'settleStatus', visible: false}
                , {field: 'isTotalTrade', visible: false}
            ],
            done:function(){
                $("[data-field='orderId']").hide();
            },
            page: false,
            data: [],
            editCell: function (value, row, field, cell, column) {
                if (field == 'payAcctNo') {
                    if (typeof value == "undefined" || value == null || value == "") {
                        row.payAcctName = "";
                    }
                    // 选择付款账户带出贷款账户名称
                    if (typeof value != "undefined" && value) {
                        Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                            ccy: row.ccy,
                            acctNo: value
                        }, function (result) {
                            // 网点
//                        row.recAcctPoint = result.largePayno;
                            //add by wangmin 2019-0-06 begin
                            if (!result) {
                                Joyin.error("该币种下，无此账户。")
                                return;
                            }
                            row.payAcctName = result.acctName;
                            //add by wangmin 2019-0-06 end
                            table.renderRow(row.LAY_TABLE_INDEX, row);
                        })
                    } else {
                        table.renderRow(row.LAY_TABLE_INDEX, row);
                    }

                } else {
                    if (typeof value == "undefined" || value == null || value == "") {
                        row.recAcctName = "";
                    }
                    if (typeof value != "undefined" && value) {
                        // 选择收款账户带出行内/行外、网点
                        Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                            ccy: row.ccy,
                            acctNo: value
                        }, function (result) {
                            var assetSource = JSON.parse(ASSET_SOURCE);
                            $.each(assetSource, function (i, v) {
                                if (v.id == result.inOutBank) {
                                    row.inOutBank = v.text;
                                }
                            });
                            // 网点
                            row.recAcctPoint = result.largePayno;
                            //add by wangmin 2019-0-06 begin
                            row.recAcctName = result.acctName;
                            //add by wangmin 2019-0-06 end
                            table.renderRow(row.LAY_TABLE_INDEX, row);
                        })
                    } else {
                        table.renderRow(row.LAY_TABLE_INDEX, row);
                    }
                }
                table.renderRow(row.LAY_TABLE_INDEX, row);
            }
        }

        if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
            option.data = option.data.concat(bussTrdTransferOrderDtos);
        }
        var orderAcctMap = {};
        var table = $("#bussTrdTransferOrder_table").joyintable(option);
        var tableInitData = function () {
            if (!$("#arrivalDate").val()) {
                Joyin.error("请输入资金到账日");
                return false;
            }
            if (!$("#fpriceAmt").val()) {
                Joyin.error("请输入实收本息总额");
                return false;
            }
            var finAssetIntInfos = repoInfoTable.getAllRows();
            var dtos = [];
            for (var i = 0; i < finAssetIntInfos.length; i++) {
                dtos.push({
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': finAssetIntInfos[i].portfolioId
                    , 'deal': {
                        finprodType2: F17,
                        ps: ps,
                        tradeDate: $("#arrivalDate").val(),
                        chlId: $("#chlId").val(),
                        // modify by renping 2019-10-25 添加通道费 start
                        tradeAmt: finAssetIntInfos[i].fpriceAmt,
                        // modify by renping 2019-10-25 添加通道费 end
                        ccy: $("#finAssetInfo_ccy").val(),
                        finprodId: $("#finprodId").val()
                    }
                })
            }
            return dtos;
        }
        // 生成指令参数保存
        var stlCommandInput = tableInitData;

        // 检查生成指令参数是否发生变化
        var isCalcConditionChanged = function() {
            // 参照模式，不做check
            if (pageModel === PAGE_MODEL_REFERENCE) {
                return false;
            }
            if (!$("#bussTrdTransferOrder_div").is(":visible")) {
                return false;
            }
            if (calcConditionObjBackUp == null) {
                return false;
            }

            if(stlCommandInput != null){
                var currentCalcConditionObj = stlCommandInput();
                // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
                for ( var key in currentCalcConditionObj) {
                    for ( var key1 in currentCalcConditionObj[key]) {
                        if (typeof currentCalcConditionObj[key][key1] != "object"
                            && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                            var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                [labelText, "生成划款指令"]));
                            return true;
                        }
                    }
                }
                for ( var key in currentCalcConditionObj) {
                    for ( var key1 in currentCalcConditionObj[key].deal) {
                        var currentDealObj = currentCalcConditionObj[key].deal;
                        var backupDealObj = calcConditionObjBackUp[key].deal;
                        if (typeof currentDealObj[key1] != "object"
                            && currentDealObj[key1] != backupDealObj[key1]) {
                            var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                [labelText, "生成划款指令"]));
                            return true;
                        }
                    }
                }
            }
        };

        var generateOrders = function (callback) {
            if(stlCommandInput != null) {
                var index = layer.open({
                    type: 1,
                    icon : 16,
                    area: ['200px', '100px'],
                    content: '数据加载中',closeBtn: 0
                });
                var data = stlCommandInput();
                if (!data) {
                    layui.layer.close(index);
                    return;
                }
                // 保存生成指令参数
                calcConditionObjBackUp = data;
                orderAcctMap = {};
                var result = [];
                Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
                    Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/generateOrder'), data, function (ret) {
                        if (data.length == ret.data.length) {
                            for (var key in ret.data) {
                                orderAcctMap[ret.data[key].orderId] = {
                                    payAcctNoList: ret.data[key].payAcctNoList,
                                    recAcctNoList: ret.data[key].recAcctNoList
                                }
                            }
                            table.clear(function () {
                                table.addRow(ret.data)
                                layui.layer.close(index);
                            })
                            if (callback && typeof callback === 'function') {
                                callback();
                            }
                        }
                    }, false);
                }, false);
            }
        }

        $("#bussTrdTransferOrder_btn_generate").on("click", function () {generateOrders();});


        function saveOrSubmit(operateType) {
        	
        	// modify by wj 20200508  TISHKBS-387 计划类、项目类资产中间还本不能自己调整金额
        	
        	if (!$("#fpriceAmt").val()) {
                Joyin.error("请输入实收本息总额");
                return false;
            }
            var finAssetIntInfos = repoInfoTable.getAllRows();
            var actfpriceAmt = 0;
            $.each(finAssetIntInfos,function(i,v){
            	actfpriceAmt = JoyinMath.doAdd(actfpriceAmt,v.fpriceAmt) ;
            })
            
            if(actfpriceAmt != $("#fpriceAmt").val()){
            	Joyin.error("[实收本息总额]应与利息明细信息中的[实收本息总额]之和相等");
                return false;
            }
            
        	// modify by wj 20200508  TISHKBS-387 计划类、项目类资产中间还本不能自己调整金额
        	
            if (GEN_PAY_ORDERS_AUTO == '01') {
                generateOrders(function () {
                    realSaveOrSubmit(operateType);
                });
            }
            else {
                realSaveOrSubmit(operateType);
            }
        }

        function realSaveOrSubmit(operateType) {
            // 检查生成划款指令参数是否发生变化
            if (isCalcConditionChanged()) {
                return;
            }
            // generateOrders(function () {
                var object = $.serializeToObject('#informationForm', true);
                object.operateType = operateType;
                Joyin.ajaxJson(basePath + 'invest/period/trdplanintduration/TrdPlanInterestDuration002/check', object, function (result) {
                    Joyin.confirm(result.data, function () {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/period/trdplanintduration/TrdPlanInterestDuration002/save', object, "TrdPlanInterestDuration002_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'invest/period/trdplanintduration/TrdPlanInterestDuration002/save', object, "TrdPlanInterestDuration002_btn_submit");
                        }
                    });
                });
            // });
        }

        /**
         * 2.利息调整 = 实收本息总额 - 还本金额 - 理论应收利息
         */
        var setIntAdjustment = function () {
            var 实收本息总额 = $("#fpriceAmt").val();
            var 还本金额 = $("#returnAmount").val();
            var 理论应收利息 = $("#intAmtTheory").val();
            $("#intAdjustment").val(JoyinMath.doMinus(JoyinMath.doMinus(实收本息总额, 还本金额), 理论应收利息)).trigger("blur");
            initOpen = false;
            // 还本金额或实收本息变化时，重新为利息明细计算
            var 累计还本未付利息= $("#payUnpaidintAmt").val();
            if(累计还本未付利息 && 累计还本未付利息>0){//按新的方式占比
                resetTableDataEX();
            }else {
                resetTableData();}
        }

        var resetTableData = function () {
            // 按占比计算还本金额、理论本息、实收本息

            var finAssetIntInfos = repoInfoTable.getAllRows();
            var sumAmount = 0;
            for (var i = 0; i < finAssetIntInfos.length; i++) {
                sumAmount = JoyinMath.doAdd(sumAmount, finAssetIntInfos[i].amount);
            }

            var 还本金额 = $("#returnAmount").val() || 0;
            var 理论本息 = $("#theoryAmt").val() || 0;
            var 实收本息 = $("#fpriceAmt").val() || 0;
            // add by renping 2019-10-15 添加通道费 start
            var 通道费 = $("#channelFee").val() || 0;
            var 到账金额 = $("#tradeAmt").val() || 0;
            // add by renping 2019-10-15 添加通道费 end
            //特殊处理 2019/4/13 剩余本金不联动
            // modify by renping 2019-10-15 添加通道费 start
            var 总剩余本金 = $("#qty").val() || 0;
            // modify by renping 2019-10-15 添加通道费 end
            //2019/4/13 end
            // var 税费金额 = $("#taxAmount").val() || 0;

            var sum还本金额 = 0;
            var sum理论本息 = 0;
            var sum实收本息 = 0;
            var sum总剩余本金 = 0;
            // add by renping 2019-10-15 添加通道费 start
            var sum通道费 = 0;
            var sum到账金额 = 0;
            var sum实际利息 = 0;

            // add by renping 2019-10-15 添加通道费 end
            // var sum税费金额 = 0;
            for (var i = 0; i < finAssetIntInfos.length; i++) {
                if (i == finAssetIntInfos.length - 1) {
                    finAssetIntInfos[i].returnAmount = JoyinMath.doMinus(还本金额, sum还本金额);
                    finAssetIntInfos[i].theoryAmt = JoyinMath.doMinus(理论本息, sum理论本息);
                    finAssetIntInfos[i].fpriceAmt = JoyinMath.doMinus(实收本息, sum实收本息);
                    // add by renping 2019-10-15 添加通道费 start
                    finAssetIntInfos[i].qty = JoyinMath.doMinus(总剩余本金, sum总剩余本金);
                    finAssetIntInfos[i].channelFee = JoyinMath.doMinus(通道费, sum通道费);
                    finAssetIntInfos[i].tradeAmt = JoyinMath.doMinus(到账金额, sum到账金额);
                    finAssetIntInfos[i].actintAmtTheory = JoyinMath.doMinus(实收本息, sum实收本息);
                    // add by renping 2019-10-15 添加通道费 end
                    // finAssetIntInfos[i].taxAmount = JoyinMath.doMinus(税费金额, sum税费金额);
                }
                else {
                    var prop = JoyinMath.doDiv(finAssetIntInfos[i].amount, sumAmount, 12, JoyinMath.ROUND_HALF_UP);
                    var this还本金额 = JoyinMath.doMul(还本金额, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this理论本息 = JoyinMath.doMul(理论本息, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this实收本息 = JoyinMath.doMul(实收本息, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this总剩余本金 = JoyinMath.doMul(总剩余本金, prop, 2, JoyinMath.ROUND_HALF_UP);
                    // add by renping 2019-10-15 添加通道费 start
                    var this通道费 = JoyinMath.doMul(通道费, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this到账金额 = JoyinMath.doMul(到账金额, prop, 2, JoyinMath.ROUND_HALF_UP);

                    // add by renping 2019-10-15 添加通道费 end
                    // var this税费金额 = JoyinMath.doMul(税费金额, prop, 2, JoyinMath.ROUND_HALF_UP);
                    sum还本金额 = JoyinMath.doAdd(sum还本金额, this还本金额);
                    sum理论本息 = JoyinMath.doAdd(sum理论本息, this理论本息);
                    sum实收本息 = JoyinMath.doAdd(sum实收本息, this实收本息);
                    sum总剩余本金 = JoyinMath.doAdd(sum总剩余本金, this总剩余本金);
                    // add by renping 2019-10-15 添加通道费 start
                    sum通道费 = JoyinMath.doAdd(sum通道费, this通道费);
                    sum到账金额 = JoyinMath.doAdd(sum到账金额, this到账金额);
                    // add by renping 2019-10-15 添加通道费 end
                    // sum税费金额 = JoyinMath.doAdd(sum税费金额, this税费金额);
                    finAssetIntInfos[i].returnAmount = this还本金额;
                    finAssetIntInfos[i].theoryAmt = this理论本息;
                    finAssetIntInfos[i].fpriceAmt = this实收本息;
                    finAssetIntInfos[i].qty = this总剩余本金;
                    // add by renping 2019-10-15 添加通道费 start
                    finAssetIntInfos[i].channelFee = this通道费;
                    finAssetIntInfos[i].tradeAmt = this到账金额;
                    finAssetIntInfos[i].actintAmtTheory = this实收本息;
                    // add by renping 2019-10-15 添加通道费 end
                    // finAssetIntInfos[i].taxAmount = this税费金额;
                }
            }

            repoInfoTable.clear(function () {
                $.each(finAssetIntInfos, function (i, v) {
                    repoInfoTable.addRow(v);
                })
            });
        }




        var resetTableDataEX = function () {
            // 按占比计算还本金额、理论本息、实收本息

            var finAssetIntInfos = repoInfoTable.getAllRows();
            var sumAmount = 0;
            var sumTheoryAmt=0;//所有组合的理论本息和
            for (var i = 0; i < finAssetIntInfos.length; i++) {
                sumAmount = JoyinMath.doAdd(sumAmount, finAssetIntInfos[i].amount);
                sumTheoryAmt=JoyinMath.doAdd(sumTheoryAmt, finAssetIntInfos[i].theoryAmt);//理论本息和
            }

            var 还本金额 = $("#returnAmount").val() || 0;
            var 累计还本未付利息 = $("#payUnpaidintAmt").val() || 0;
            var 理论本息 = $("#theoryAmt").val() || 0;
            var 实收本息 = $("#fpriceAmt").val() || 0;
            // add by renping 2019-10-15 添加通道费 start
            var 通道费 = $("#channelFee").val() || 0;
            var 到账金额 = $("#tradeAmt").val() || 0;
            // add by renping 2019-10-15 添加通道费 end
            //特殊处理 2019/4/13 剩余本金不联动
            // modify by renping 2019-10-15 添加通道费 start
            var 总剩余本金 = $("#qty").val() || 0;
            // modify by renping 2019-10-15 添加通道费 end
            //2019/4/13 end
            // var 税费金额 = $("#taxAmount").val() || 0;
            var sum区间实际未付利息 = 0;
            var sum还本金额 = 0;
            var sum理论本息 = 0;
            var sum实收本息 = 0;
            var sum总剩余本金 = 0;
            // add by renping 2019-10-15 添加通道费 start
            var sum通道费 = 0;
            var sum到账金额 = 0;
            // add by renping 2019-10-15 添加通道费 end
            var sum实际利息 = 0;
            // var sum税费金额 = 0;
            var sumAmt = JoyinMath.doAdd(sumTheoryAmt, 累计还本未付利息);//总的理论本息和
            var isClean = $("#isClean").val();//是否结清
            var intAmt=JoyinMath.doDiv(实收本息,sumAmt,12, JoyinMath.ROUND_HALF_UP);//总的利息金额
            var 累计还本未付利息占比=JoyinMath.doMul(累计还本未付利息, intAmt, 12, JoyinMath.ROUND_HALF_UP);

            for (var i = 0; i < finAssetIntInfos.length; i++) {
                if (i == finAssetIntInfos.length - 1) {
                    if(isClean === 'Y'){
                        finAssetIntInfos[i].actrRepaymentUnpayInterest = JoyinMath.doMinus(累计还本未付利息, sum区间实际未付利息);}
                    else{
                        finAssetIntInfos[i].actrRepaymentUnpayInterest = JoyinMath.doMinus(累计还本未付利息占比, sum区间实际未付利息);
                    }
                    finAssetIntInfos[i].returnAmount = JoyinMath.doMinus(还本金额, sum还本金额);
                    finAssetIntInfos[i].theoryAmt = JoyinMath.doMinus(理论本息, sum理论本息);
                    finAssetIntInfos[i].fpriceAmt = JoyinMath.doMinus(实收本息, sum实收本息);
                    // add by renping 2019-10-15 添加通道费 start
                    finAssetIntInfos[i].qty = JoyinMath.doMinus(总剩余本金, sum总剩余本金);
                    finAssetIntInfos[i].channelFee = JoyinMath.doMinus(通道费, sum通道费);
                    finAssetIntInfos[i].tradeAmt = JoyinMath.doMinus(到账金额, sum到账金额);
                    finAssetIntInfos[i].actintAmtTheory = JoyinMath.doMinus(实收本息, sum实际利息);
                    finAssetIntInfos[i].intAmt = JoyinMath.doMinus(finAssetIntInfos[i].fpriceAmt, finAssetIntInfos[i].actrRepaymentUnpayInterest);//实际利息
                    // add by renping 2019-10-15 添加通道费 end
                    // finAssetIntInfos[i].taxAmount = JoyinMath.doMinus(税费金额, sum税费金额);
                }
                else {
                    var prop = JoyinMath.doDiv(finAssetIntInfos[i].amount, sumAmount, 12, JoyinMath.ROUND_HALF_UP);
                    var this区间本息和=JoyinMath.doAdd(finAssetIntInfos[i].repaymentUnpayInterest, finAssetIntInfos[i].theoryAmt);
                    var this还本金额 = JoyinMath.doMul(还本金额, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this理论本息 = JoyinMath.doMul(理论本息, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this实收本息 = JoyinMath.doMul(this区间本息和, intAmt, 2, JoyinMath.ROUND_HALF_UP);
                    var this总剩余本金 = JoyinMath.doMul(总剩余本金, prop, 2, JoyinMath.ROUND_HALF_UP);
                    // add by renping 2019-10-15 添加通道费 start
                    var this通道费 = JoyinMath.doMul(通道费, prop, 2, JoyinMath.ROUND_HALF_UP);
                    var this到账金额 = JoyinMath.doMul(到账金额, prop, 2, JoyinMath.ROUND_HALF_UP);
                    if (isClean === 'Y') {//是否结清为是
                        var this区间实际未付利息 = finAssetIntInfos[i].repaymentUnpayInterest;
                    } else {
                        var this区间实际未付利息 = JoyinMath.doMul(finAssetIntInfos[i].repaymentUnpayInterest, intAmt, 2, JoyinMath.ROUND_HALF_UP);
                    }
                    var this实际利息 = JoyinMath.doMinus(this实收本息, this区间实际未付利息);
                    // add by renping 2019-10-15 添加通道费 end
                    // var this税费金额 = JoyinMath.doMul(税费金额, prop, 2, JoyinMath.ROUND_HALF_UP);
                    sum还本金额 = JoyinMath.doAdd(sum还本金额, this还本金额);
                    sum理论本息 = JoyinMath.doAdd(sum理论本息, this理论本息);
                    sum实收本息 = JoyinMath.doAdd(sum实收本息, this实收本息);
                    sum总剩余本金 = JoyinMath.doAdd(sum总剩余本金, this总剩余本金);
                    // add by renping 2019-10-15 添加通道费 start
                    sum通道费 = JoyinMath.doAdd(sum通道费, this通道费);
                    sum到账金额 = JoyinMath.doAdd(sum到账金额, this到账金额);
                    sum区间实际未付利息 = JoyinMath.doAdd(sum区间实际未付利息, this区间实际未付利息);
                    // add by renping 2019-10-15 添加通道费 end
                    // sum税费金额 = JoyinMath.doAdd(sum税费金额, this税费金额);
                    finAssetIntInfos[i].returnAmount = this还本金额;
                    finAssetIntInfos[i].theoryAmt = this理论本息;
                    finAssetIntInfos[i].fpriceAmt = this实收本息;
                    finAssetIntInfos[i].qty = this总剩余本金;
                    // add by renping 2019-10-15 添加通道费 start
                    finAssetIntInfos[i].channelFee = this通道费;
                    finAssetIntInfos[i].tradeAmt = this到账金额;
                    finAssetIntInfos[i].actrRepaymentUnpayInterest = this区间实际未付利息;
                    finAssetIntInfos[i].intAmt = this实际利息;//实际利息
                    // add by renping 2019-10-15 添加通道费 end
                    // finAssetIntInfos[i].taxAmount = this税费金额;
                }
            }

            repoInfoTable.clear(function () {
                $.each(finAssetIntInfos, function (i, v) {
                    repoInfoTable.addRow(v);
                })
            });
        }

        /**
         * 3.到账金额 = 实收本息总额 - 税费金额
         */
        // var setArrivalAmt = function () {
        //     // var 实收本息总额 = $("#fpriceAmt").val();
        //     // var 税费金额 = $("#taxAmount").val();
        //     // $("#arrivalAmt").val(JoyinMath.doMinus(实收本息总额, 税费金额)).trigger("blur");
        //     resetTableData();
        // }

        // var setTaxAmount = function(){
        //     var 实收本息总额 = $("#fpriceAmt").val();
        //     var 到账金额 = $("#arrivalAmt").val();
        //     $("#taxAmount").val(JoyinMath.doMinus(实收本息总额, 到账金额)).trigger("blur");
        //     resetTableData();
        // }

        /**
         * 画面加载完成
         */
        $(function () {
            init();
        	if(CHANNEL){
        		$('#channelFee').prop('disabled',false);
        	}
            if(intAmtUnpayFlag){
                $('#repayIntAmt_div').show();
            }
            $('#TrdPlanInterestDuration002_btn_save').click(function () {
                saveOrSubmit('save');
            });
            $('#TrdPlanInterestDuration002_btn_submit').click(function () {
                saveOrSubmit('submit');
            });
            $("#returnAmount").change(setIntAdjustment);
            $("#fpriceAmt").change(setIntAdjustment);
        	// add by renping 2019-10-15 添加通道费 start
        	$('#fpriceAmt,#channelFee,#isClean').change(function(){
        		var fpriceAmt = $('#fpriceAmt').val();
//    			if(COLLECT_TYPE == COLLECT_TYPE_01){//回款前扣除
//                	var channelFee = $("#channelFee").val();
//                	if(!channelFee){
//                		channelFee = 0
//                	}
//                	 $("#tradeAmt").val(JoyinMath.doMinus(fpriceAmt, channelFee)).trigger("blur");
//                } else {
                	 $("#tradeAmt").val(fpriceAmt).trigger("blur");
//                }
                var 累计还本未付利息= $("#payUnpaidintAmt").val();
                if(累计还本未付利息 && 累计还本未付利息>0){//按新的方式占比
                    resetTableDataEX();
                }else {
                    resetTableData();}
        	});
        	$('#channelFee').change(function(){
        		var tradeAmtReal = $('#tradeAmtReal').val();
//        		if(tradeAmtReal != '' && tradeAmtReal != null) {
//    	    		if(COLLECT_TYPE == COLLECT_TYPE_01){//回款前扣除
//    	            	var channelFee = $("#channelFee").val();
//    	            	if(!channelFee){
//    	            		channelFee = 0;
//    	            	}
//    	            	 $("#tradeAmt").val(JoyinMath.doMinus(tradeAmtReal, channelFee)).trigger("blur");
//    	            }
//        		}
                var 累计还本未付利息= $("#payUnpaidintAmt").val();
                if(累计还本未付利息 && 累计还本未付利息>0){//按新的方式占比
                    resetTableDataEX();
                }else {
                    resetTableData();}
        	});
        	// add by renping 2019-10-15 添加通道费 end
            // $("#fpriceAmt").change(resetTableData);
            // $("#taxAmount").change(setArrivalAmt);
            // $("#arrivalAmt").change(setTaxAmount);
        });
    }
    ()
)
;

