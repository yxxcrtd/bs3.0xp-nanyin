//@ sourceURL=TrdBondDuration003.js
var TrdBondDuration003 = function () {

    let orderAcctMap = {};

    // 生成指令参数保存
    let stlCommandInput;

    let calcConditionObjBackUp;

    let finAssetIntInfoTable;

    let orderTable;


    var repoInfoTableOption = {
        rowno: true,
        searchOnInit: true,
        checkbox: false,
        page: false,
        cols: [
            {field: 'portfolioId', width: "200", title: '组合代码', sortable: false}
            , {field: 'portfolioName', width: "200", title: '组合名称', sortable: false}
            , {field: 'secAcctId', width: "200", title: '证券账户/通道', sortable: false}
            , {field: 'invAimString', width: "200", title: '投资目的', sortable: false}
            , {field: 'qty', width: "100", title: '持有面额', type: 'money', sortable: false}
            , {field: 'cPriceAmt', width: "100", title: '净价总额', type: 'money', sortable: false}
            , {field: 'intAmtTheory', width: "100", title: '理论应计利息', type: 'money', sortable: false}
            , {field: 'intAmtReal', width: "100", title: '实收利息总额', type: 'money', edit:true, sortable: false}
            , {field: 'channelFee', width: "100", title: '通道费', type: 'money', sortable: false}
            , {field: 'settleAmt', width: "110", title: '到账金额', type: 'money', sortable: false}
        ],
        done: function () {
        },
        editCell: function(value, data, field, cell, column){
            //列表中到账金额=净价总额+实收利息总额-通道费
            if("intAmtReal"==field){
                let settleAmt = JoyinMath.doAdd(data.cPriceAmt,value);
                settleAmt =JoyinMath.doMinus(settleAmt,data.channelFee);
                finAssetIntInfoTable.renderRow(data.LAY_TABLE_INDEX, {"settleAmt": settleAmt});
            }
        },
        data: []
    }

    //    2.0划款指令
    /**
     * 定义数据表格
     *
     */
    var option = {
        searchOnInit: true,
        checkbox: false,
        cols: [
            {
                field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true, sortable: false,
                editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
            }
            , {field: 'payTypeText', width: 130, title: 'BussTrdTransferOrder.payType', sortable: false, sortable: false}
            , {field: 'settleDateText', width: 130, title: 'BussTrdTransferOrder.settleDate', sortable: false}
            , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable: false}
            , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable: false}
            , {
                field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/common/multiOrder/select"), id: 'payAcctNo', postData: function () {
                        return {selectValue: JSON.stringify(orderAcctMap), type: 'pay'};
                    }
                }
            }
            , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable: false}
            , {
                field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/common/multiOrder/select"), id: 'recAcctNo', postData: function () {
                        return {selectValue: JSON.stringify(orderAcctMap), type: 'rec'};
                    }
                }
            }
            , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable: false}
            , {field: 'purpose', width: 130, title: 'BussTrdTransferOrder.purpose', sortable: false, edit: true}
            , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable: false}
            , {field: 'portfolioName', width: 150, title: 'BussTrdTransferOrder.portfolioName', sortable: false}
            , {
                field: 'orderId', visible: true, edit: true, width: 0,
                editOption: {
                    type: 'select', id: 'orderId_select', childrenIds: 'recAcctNo,payAcctNo'
                }
            }
            , {field: 'ps', visible: false}
            , {field: 'settleStatus', visible: false}
            , {field: 'isTotalTrade', visible: false}
        ],
        done: function () {
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
                        orderTable.renderRow(row.LAY_TABLE_INDEX, row);
                    })
                } else {
                    orderTable.renderRow(row.LAY_TABLE_INDEX, row);
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
                        orderTable.renderRow(row.LAY_TABLE_INDEX, row);
                    })
                } else {
                    orderTable.renderRow(row.LAY_TABLE_INDEX, row);
                }
            }
        }
    }

    function resetTableData(result,isUpdateAmtflag) {
        if (result && result.length > 0) {
            let index = layer.open({
                type: 1,
                icon: 16,
                area: ['200px', '100px'],
                content: '数据加载中', closeBtn: 0
            });
            let 券面总额 = 0;
            let 净价总额 = 0;
            let 持仓最大组合代码 = null;
            let 最大持仓 = null;
            $.each(result, function (i, v) {
                券面总额 = JoyinMath.doAdd(券面总额, v.qty);
                // 持有面额*行权净价/100
                v.cPriceAmt = JoyinMath.doMul(JoyinMath.doDiv(v.qty,100), $("#netPrice").val(), 2, JoyinMath.ROUND_HALF_UP);
                净价总额 = JoyinMath.doAdd(净价总额, v.cPriceAmt);

                if (!最大持仓 || Number(最大持仓) < Number(v.qty)) {
                    最大持仓 = v.qty;
                    持仓最大组合代码 = v.portfolioId + "_" + v.invAim + "_" + v.secAcctId;
                }
            })

            //理论应计利息
            let intAmt = result[0].intAmt;
            let intAmtTheory = JoyinMath.doMul(intAmt,JoyinMath.doDiv(券面总额,100),2, JoyinMath.ROUND_HALF_UP);
            if(isUpdateAmtflag){
                $("#intAmtTheory").val(intAmtTheory).trigger('blur');
                //实收利息总额
                $("#intAmtReal").val($("#intAmtTheory").val()).trigger('blur');
            }

            if ($("#channelFee").val() && Number($("#channelFee").val()) > 0) {
                let channelFee = $("#channelFee").val();
                let 非最大持仓组合通道费合计 = 0;
                $.each(result, function (i, v) {
                    // 通道费 = 通道费*持有面额/券面总额
                    v.channelFee = JoyinMath.doMul(channelFee, JoyinMath.doDiv(v.qty, 券面总额, 12, JoyinMath.ROUND_HALF_UP), 2, JoyinMath.ROUND_HALF_UP)
                    if (v.portfolioId + "_" + v.invAim + "_" + v.secAcctId != 持仓最大组合代码) {
                        非最大持仓组合通道费合计 = JoyinMath.doAdd(非最大持仓组合通道费合计, v.channelFee);
                    }
                })

                $.each(result, function (i, v) {
                    if (v.portfolioId + "_" + v.invAim + "_" + v.secAcctId == 持仓最大组合代码) {
                        v.channelFee = JoyinMath.doMinus(channelFee, 非最大持仓组合通道费合计);
                    }
                })
            }

//            $.each(result, function (i, v) {
//                if (v.cPriceAmt) {
//                    let fPriceAmt = v.cPriceAmt;
//                    if ($("#chlId").val() && $("#chlId").select2('data')[0].object && $("#chlId").select2('data')[0].object.collectType == '01') {
//                        fPriceAmt = JoyinMath.doMinus(fPriceAmt, v.channelFee);
//                    }
//                    v.settleAmt = fPriceAmt;
//                }
//            })
              //理论应计利息、实收利息总额
              var itemArr = ['intAmtTheory','intAmtReal'];
              //所有的钱
              var itemObj = {};
              //已分配的钱
              var sumObj = {};
              //初始化所有的钱和已分配的钱
              $.each(itemArr,function(index,val){
                itemObj[val] = $('#'+val).val()||0;
                sumObj[val] = 0;
              })
              $.each(result, function (i, v) {
                //最后一个
                if (i == result.length - 1) {
                    //将剩余的钱放入对应值
                    $.each(itemArr,function(index,val){
                        result[i][val] = JoyinMath.doMinus(itemObj[val], sumObj[val]);
                    })
                }else{
                    //计算占比
                    var prop = JoyinMath.doDiv(result[i].qty, 券面总额, 12, JoyinMath.ROUND_HALF_UP);
                    //根据占比处理所有的钱
                    $.each(itemArr,function(index,val){
                        //计算当前需要的钱
                        var thisNum = JoyinMath.doMul(itemObj[val], prop, 2, JoyinMath.ROUND_HALF_UP);
                        //处理已分配的钱
                        sumObj[val] = JoyinMath.doAdd(sumObj[val], thisNum);
                        //把钱放入对应数据
                        result[i][val] = thisNum;
                    })
                }
                //列表中：到账金额=净价总额+实收利息总额-通道费
                let settleAmt = JoyinMath.doAdd(v.cPriceAmt, v.intAmtReal);
                v.settleAmt = JoyinMath.doMinus(settleAmt, v.channelFee);
            })

            finAssetIntInfoTable.clear(function () {
                finAssetIntInfoTable.addRow(result)
                $("#qty").val(券面总额).trigger('blur');
                $("#cPriceAmt").val(净价总额).trigger('blur').trigger("change");
                layui.layer.close(index);
            })
        } else {
            finAssetIntInfoTable.clear(function () {
                $("#qty").val('').trigger('blur');
                $("#cPriceAmt").val('').trigger("change");
            })
        }
    }

    function resetTable(isUpdateAmtflag) {
        if (!$('#finprodMarketId').val()) {
            return;
        }
        if (!$("#netPrice").val()) {
            Joyin.error("请输入行权净价。")
            return;
        }
        var finprodObject = $('#finprodMarketId').select2('data')[0].object;
        var param = {finprodId: finprodObject.finprodId, exerciseDate : finprodObject.exerciseDate, chlId: $("#chlId").val()}
        Joyin.ajaxJson(basePath + 'invest/period/trdbondduration/TrdBondDuration003/findSecPosition', param,
            function (result) {
                resetTableData(result,isUpdateAmtflag);
            });
    }
    //重新联动计算到账金额
    var reValfPriceAmt = function () {
        if ($('#cPriceAmt').val()) {
            let fPriceAmt =$('#cPriceAmt').val();
            if ($("#intAmtReal").val()) {
                fPriceAmt = JoyinMath.doAdd(fPriceAmt, $("#intAmtReal").val())
            }
            if ($("#chlId").val() && $("#chlId").select2('data')[0].object && $("#chlId").select2('data')[0].object.collectType == '01') {
                fPriceAmt = JoyinMath.doMinus(fPriceAmt, $("#channelFee").val())
            }
            $("#fPriceAmt").val(fPriceAmt).trigger('blur');
        }
    }

    return {
        init: function () {
            stlCommandInput = TrdBondDuration003.tableInitData;
            TrdBondDuration003.initFinAssetIntInfoTable();
            TrdBondDuration003.initSettleOrderTable();
            $("#bussTrdTransferOrder_btn_generate").on("click", function () {TrdBondDuration003.generateOrder();});
            $("#channelFee").prop("disabled", true);
            $('#cPriceAmt').change(function () {
                //到账金额重新赋值
                reValfPriceAmt();
            });
            $('#intAmtReal').change(function () {
                //到账金额重新赋值
                reValfPriceAmt();
                resetTable(false);
            });
            $('#fPriceAmt').change(function () {
                if ($('#fPriceAmt').val() && $('#cPriceAmt').val()) {
                    let intAmtReal = JoyinMath.doMinus($('#fPriceAmt').val(),$('#cPriceAmt').val());
                    if ($("#chlId").val() && $("#chlId").select2('data')[0].object && $("#chlId").select2('data')[0].object.collectType == '01') {
                        intAmtReal = JoyinMath.doAdd(intAmtReal, $("#channelFee").val())
                    }
                    $("#intAmtReal").val(intAmtReal).trigger('blur');
                    resetTable(false);
                }
            });
            $('#TrdBondDuration003_btn_save').click(function () {
                TrdBondDuration003.saveOrSubmit('save');
            });
            $('#TrdBondDuration003_btn_submit').click(function () {
                TrdBondDuration003.saveOrSubmit('submit');
            });
            $("#chlId").change(function () {
                if ($("#chlId").val()) {
                    $("#channelFee").prop("disabled", false);
                }
                else {
                    $("#channelFee").val('').prop("disabled", true);
                }
                resetTable(true);
            })
            $("#netPrice,#channelFee").change(function () {
                let allData = finAssetIntInfoTable.getAllRows();
                resetTableData(allData,true);
            })
            $('#finprodMarketId').change(function () {
                if ($('#finprodMarketId').val()) {
                    $("#finprodMarketIdShow").val($('#finprodMarketId').val());
                    var finprodObject = $('#finprodMarketId').select2('data')[0].object;
                    $("#finprodName").val(finprodObject.finprodName);
                    $("#vdate").val(finprodObject.exerciseVdate);
                    $("#mdate").val(finprodObject.exerciseMdate);
                    $("#payDate").val(finprodObject.exerciseDate);
                    $("#netPrice").val(finprodObject.exercisePrice);
                    resetTable(true);
                }
                else {
                    $("#finprodMarketIdShow").val('');
                    $("#finprodName").val('');
                    $("#vdate").val('');
                    $("#mdate").val('');
                    $("#payDate").val('');
                    $("#netPrice").val('');
                    finAssetIntInfoTable.clear();
                }
            });
        },
        saveOrSubmit: function(operateType) {
            if (GEN_PAY_ORDERS_AUTO == '01') {
                TrdBondDuration003.generateOrder(function () {
                    TrdBondDuration003.realSaveOrSubmit(operateType);
                });
            }
            else {
                TrdBondDuration003.realSaveOrSubmit(operateType);
            }
        },
        realSaveOrSubmit: function (operateType) {
            if (!$('#finprodMarketId').val()) {
                Joyin.error("请选择资产。")
                return;
            }
            if (!$('#settleDate').val()) {
                Joyin.error("请输入资金到账日。")
                return;
            }
            if ($('#settleDate').val() < $('#payDate').val()) {
                Joyin.error("资金到账日不能早于支付日。")
                return;
            }
            if (TrdBondDuration003.isCalcConditionChanged()) {
                return;
            }
            /**
             * 获取列表参数
             */
            var object = $.serializeToObject('#informationForm', true);
            object.operateType = operateType;
            if ($('#finprodMarketId').val()) {
                object.tradeMarket = $('#finprodMarketId').select2('data')[0].object.tradeMarket;
                object.ccy = $('#finprodMarketId').select2('data')[0].object.ccy;
                object.finprodId = $('#finprodMarketId').select2('data')[0].object.finprodId;
                object.finprodName = $('#finprodMarketId').select2('data')[0].object.finprodName;
            }
            if ($("#chlId").val()) {
                object.collectType = $("#chlId").select2('data')[0].object.collectType;
            }

            if (FileCommon) {
                object.uploadFileList = FileCommon.getUploadFileInfo();
            }
            Joyin.ajaxJson(basePath + 'invest/period/trdbondduration/TrdBondDuration003/check', object, function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/period/trdbondduration/TrdBondDuration003/save', object, "TrdBondDuration003_btn_save", "TrdBondDuration003", function () {
                            if (parent.TrdBondDuration001) {
                                parent.TrdBondDuration001.reloadTable();
                            }
                        });
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/period/trdbondduration/TrdBondDuration003/save', object, "TrdBondDuration003_btn_submit", "TrdBondDuration003", function () {
                            if (parent.TrdBondDuration001) {
                                parent.TrdBondDuration001.reloadTable();
                            }
                        });
                    }
                });
            });
        },
        tableInitData : function () {
            var finAssetIntInfos = finAssetIntInfoTable.getAllRows();
            var dtos = [];
            for (var i = 0; i < finAssetIntInfos.length; i++) {
                var dealReal = {};
                dealReal.finprodType2 = 'F01';
                dealReal.settleDate = $("#settleDate").val();
                dealReal.tradeDate = platDate;
                dealReal.ps = 'T03';
                dealReal.ccy = $('#finprodMarketId').select2('data')[0].object.ccy;
                dealReal.tradeMarket=$('#finprodMarketId').select2('data')[0].object.tradeMarket;
                dealReal.tradeAmt = finAssetIntInfos[i].settleAmt;
                dealReal.secManageAcctId = finAssetIntInfos[i].secAcctId;
                dealReal.chlId = $("#chlId").val();
                dtos.push({
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': finAssetIntInfos[i].portfolioId
                    , 'invAim' : finAssetIntInfos[i].invAim
                    , 'deal':dealReal
                })
            }
            return dtos;
        },
        initFinAssetIntInfoTable: function () {
            if (finAssetIntInfoList && finAssetIntInfoList.length > 0) {
                repoInfoTableOption.data = repoInfoTableOption.data.concat(finAssetIntInfoList);
            }
            /**
             * 定义数据表格
             */
            finAssetIntInfoTable = $("#finAssetIntInfoList").joyintable(repoInfoTableOption);
        },
        initSettleOrderTable: function () {
            if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
                option.data = option.data.concat(bussTrdTransferOrderDtos);
            }

            orderTable = $("#bussTrdTransferOrder_table").joyintable(option);
        },
        generateOrder: function (callback) {
            if (!$('#finprodMarketId').val()) {
                Joyin.error("请选择资产。")
                return;
            }
            if (!$('#settleDate').val()) {
                Joyin.error("请输入资金到账日。")
                return;
            }
            if (stlCommandInput != null) {
                var index = layer.open({
                    type: 1,
                    icon: 16,
                    area: ['200px', '100px'],
                    content: '数据加载中', closeBtn: 0
                });
                var data = stlCommandInput();
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
                            orderTable.clear(function () {
                                if (TrdBondDuration003.checkMyAccount(ret.data)) {
                                    Joyin.error("本方账户为空，请检查账户配置。");
                                    return;
                                }
                                orderTable.addRow(ret.data);
                                layui.layer.close(index);
                                if (callback && typeof callback === 'function') {
                                    callback();
                                }
                            })
                        }
                    });
                });
            }
        },
        checkMyAccount : function (order) {
            var datas = [];
            if(_.isArray(order)) {
                datas = _.concat(datas, order);
            }
            else {
                datas.push(order);
            }
            $.each(datas, function (i, v) {
                if (v && ((v.ps == '01' && !v.recAcctNo) || (v.ps == '02' && !v.payAcctNo))) {
                    return true;
                }
            })
            return false;
        },
        isCalcConditionChanged: function () {
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

            if (stlCommandInput != null) {
                var currentCalcConditionObj = stlCommandInput();
                // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
                for (var key in currentCalcConditionObj) {
                    for (var key1 in currentCalcConditionObj[key]) {
                        if (typeof currentCalcConditionObj[key][key1] != "object"
                            && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                            var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                [labelText, "生成划款指令"]));
                            return true;
                        }
                    }
                }
                for (var key in currentCalcConditionObj) {
                    for (var key1 in currentCalcConditionObj[key].deal) {
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
        }
    }
}();

$(function () {
    TrdBondDuration003.init();
})