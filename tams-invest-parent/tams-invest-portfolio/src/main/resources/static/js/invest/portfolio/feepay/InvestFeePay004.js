$(function() {
    var calcConditionObjBackUp;
    function saveOrSubmit(operateType) {
        // 检查生成划款指令参数是否发生变化
        if (isCalcConditionChanged()) {
            return;
        }
        var feeInfoList = feeInfoTable.getAllRows();
        var object = $.serializeToObject('#submitForm', false);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.isClean = 'Y';
        object.feeInfoList = feeInfoList;
        //费用提交时校验收款账户是否为空
        if (object.bussTrdTransferOrder[0]) {
            // 是否发送指令为是收付款账户均为必填，为否则付款账户必填，收款账户非必填
            for (var i = 0; i < object.bussTrdTransferOrder.length; i++) {
                if(object.bussTrdTransferOrder[i].isNeedSend == 'Y'
                    || object.bussTrdTransferOrder[i].isNeedSend == 'Y'){
                    if (!object.bussTrdTransferOrder[i].recAcctNo){
                        Joyin.error("划款指令请选择收款账户！");
                        return false;
                    }
                    if (!object.bussTrdTransferOrder[i].payAcctNo){
                        Joyin.error("划款指令请选择付款账户！");
                        return false;
                    }
                }else{
                    if (!object.bussTrdTransferOrder[i].payAcctNo){
                        Joyin.error("划款指令请选择付款账户！");
                        return false;
                    }
                }
            }
        }
        Joyin.ajaxJson(basePath + 'invest/portfolio/feepay/InvestFeePay004/check',object, function(result) {
            Joyin.confirm("是否确认？", function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/feepay/InvestFeePay004/save', object, "InvestFeePay004_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/feepay/InvestFeePay004/save', object, "InvestFeePay004_btn_submit");
                }
            })
        });
    };

    /**
     * 页面加载初始化
     */
    var options = {
        page: false,
        ajax: {
            url: JoyinUtil.getRealPath('invest/portfolio/feepay/InvestFeePay004/findPage'),
            type: 'POST',
            data: function() {
                return {tradeId: $('#tradeId').val(),
                    workflowId: $('#workflowId').val()
                };
            }
        },
        checkbox: false,
        addbtn: false,
        searchOnInit: true,
        cols:[
            {field:'ptlId', width: 100, title: 'InvestFeePay004.finprodId', edit:false, type:'text', sortable:false},
            {field:'calcStartDate', width: 100, title: 'InvestFeePay004.positionStartDate', edit:false, type:'date', sortable:false},
            {field:'calcEndDate', width: 100, title: 'InvestFeePay004.positionEndDate', edit:false, type:'date', sortable:false},
            {field:'payAmt', width: 100, title: 'InvestFeePay004.feeAmt', edit:false, type:'number', sortable:false},
            {field:'actPayAmt', width: 100, title: 'InvestFeePay004.actPayAmt', edit:true, type:'number', sortable:false},
            {field:'tradeId',width : 100,title : '交易编号',visible : false}
        ]
    };

    /**
     * 绑定表格事件
     */
    var feeInfoTable = $("#dataTable").joyintable(options);

    /**
     * 划款指令定义数据表格
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
            , {field: 'payTypeText', width:130, title: 'BussTrdTransferOrder.payType', sortable:false, sortable:false}
            , {field: 'settleDateText', width:130, title: 'BussTrdTransferOrder.settleDate', sortable:false}
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
            , {field: 'purpose', width:130, title: 'BussTrdTransferOrder.purpose', sortable:false, edit:true}
            , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable:false}
            , {field: 'portfolioName', width:150, title: 'BussTrdTransferOrder.portfolioName', sortable:false}
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
        }
    }

    var tableInitData = function () {
        var feeInfos = feeInfoTable.getAllRows();
        var dtos = [];
        for (var i = 0; i < feeInfos.length; i++) {
            var dealReal = {};
            dealReal.finprodType2=$("#InvestFeePay004_feeType").val();
            dealReal.ps=ps;
            dealReal.tradeDate=$('#InvestFeePay004_payDate').val();
            dealReal.tradeAmt=feeInfos[i].actPayAmt;
            dealReal.ccy='CNY';
            dtos.push({
                'isTotalTrade': 'Y'
                , 'portfolioId': feeInfos[i].ptlId
                , 'deal':dealReal
            })
        }
        return dtos;
    }
    if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
        option.data = option.data.concat(bussTrdTransferOrderDtos);
    }
    var orderAcctMap = [];
    var table = $("#bussTrdTransferOrder_table").joyintable(option);
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

    $("#bussTrdTransferOrder_btn_generate").on("click", function () {
        if(stlCommandInput != null) {
            var index = layer.open({
                type: 1,
                icon : 16,
                area: ['200px', '100px'],
                content: '数据加载中',closeBtn: 0
            });
            var data = stlCommandInput();
            // 保存生成指令参数
            calcConditionObjBackUp = data;
            orderAcctMap = [];
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
                    }
                });
            });
        }
    });

    /**
     * 画面加载完成
     */
    $("#InvestFeePay004_feeType").attr("disabled",true);
    $("#InvestFeePay004_secAcctId").attr("disabled",true);
    $("#InvestFeePay004_feeAmt").attr("disabled",true);
    // 绑定按钮事件
    $('#InvestFeePay004_btn_save').click(function() {
        saveOrSubmit('save');
    });
    $('#InvestFeePay004_btn_submit').click(function() {
        saveOrSubmit('submit');
    });

}());