/**
 * 产品中间付息确认
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = $.serializeToObject('#submitForm', true);
        if (!pageParam.bussTrdTransferOrder) {
            pageParam.bussTrdTransferOrder = null;
        }
        return pageParam;
    };
    /**
     * 保存提交
     * @param operateType
     * @returns
     */
    function saveOrSubmit(operateType) {
        // 检查生成划款指令参数是否发生变化
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }

        var object = getPageParams();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/payint/FinProductPayInt002/save', object, "FinProductPayInt002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/payint/FinProductPayInt002/save', object, "FinProductPayInt002_btn_submit");
                }
            });
        });
    };
    /**
     * 画面联动
     * 2.应付客户收益 = sum（应付利息）
     * 3.实付客户收益 = sum（实付利息）
     */
    var calcCusProfit = function() {
        if ($("#theoryCusProfit").val()) {
            return;
        }

        calcCusProfitByChange();
    };
    var calcCusProfitByChange = function() {
        var intDetailInfoTableDataArr = intDetailInfoTable.getAllRows();
        if (intDetailInfoTableDataArr && intDetailInfoTableDataArr.length > 0) {
            var paramObj = getPageParams();
            Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt002/calcCusProfit', paramObj, function(result) {
                if (result) {
                    if (result.theoryCusProfit) {
                        $("#theoryCusProfit").val(result.theoryCusProfit).trigger("change").trigger("blur");
                    }
                    if (result.actCusProfit) {
                        $("#actCusProfit").val(result.actCusProfit).trigger("change").trigger("blur");
                    }
                }
            });
        }
    };
    
    /**
     * 定义数据表格
     */
    var intDetailInfoTable = $("#intDetailInfoTable").joyintable({
        searchOnInit:true,
        checkbox:false,
        page:false,
        rowno:true,
        ajax: {
            url: 'findIntDetailInfo',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        done :function() {
            calcCusProfit();
        },
        cols:[
            {field:'sonFinprodId', width:120, title: 'FinProductPayInt002.intDetailInfo.sonFinprodId'}
            ,{field:'customLevel', width:120, title: 'FinProductPayInt002.intDetailInfo.customLevel', visible: ($("#isLay").val() == YES_OR_NO_Y && $("#layType").val() == LAY_TYPE_KHFC)}
            ,{field:'amtMin', width:120, title: 'FinProductPayInt002.intDetailInfo.amtMin', type: "money", visible: $("#isLay").val() == YES_OR_NO_Y && $("#layType").val() == LAY_TYPE_JEFC}
            ,{field:'amtMax', width:120, title: 'FinProductPayInt002.intDetailInfo.amtMax', type: "money", visible: $("#isLay").val() == YES_OR_NO_Y && $("#layType").val() == LAY_TYPE_JEFC}
            ,{field:'termMinText', width:120, title: 'FinProductPayInt002.intDetailInfo.termMin', visible: $("#isLay").val() == YES_OR_NO_Y && $("#layType").val() == LAY_TYPE_QXFC}
            ,{field:'termMaxText', width:120, title: 'FinProductPayInt002.intDetailInfo.termMax', visible: $("#isLay").val() == YES_OR_NO_Y && $("#layType").val() == LAY_TYPE_QXFC}
            ,{field:'intAccrPrinAmt', width:120, title: 'FinProductPayInt002.intDetailInfo.intAccrPrinAmt', type: "money"}
            ,{field:'theoryYield', width:120, title: 'FinProductPayInt002.intDetailInfo.theoryYield', type: "money"}
            ,{field:'theoryInt', width:120, title: 'FinProductPayInt002.intDetailInfo.theoryInt', type: "money"}
            ,{field:'actYield', width:120, title: 'FinProductPayInt002.intDetailInfo.actYield', type: "money", edit: true}
            ,{field:'actInt', width:120, title: 'FinProductPayInt002.intDetailInfo.actInt', type: "money", edit: true}
            ,{field:'layFinprodId', visible: false}
            ,{field:'calcIntPeriod', visible: false}
            ,{field:'calcIntBasic', visible: false}
        ],
        editCell: function (value, row, field, cell, column) {
            if (column.field == 'actInt') {
                calcCusProfitByChange();
                // 反算实付收益率
                if (value) {
                    var paramObj = row;
                    Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt002/calcActYield', paramObj, function(result) {
                        if (result) {
                            if (result.actYield) {
                                row.actYield = result.actYield;
                                intDetailInfoTable.renderRow(row.LAY_TABLE_INDEX, row);
                            }
                        }
                    });
                } else {
                    row.actYield = null;
                    intDetailInfoTable.renderRow(row.LAY_TABLE_INDEX, row);
                }
            }
            return true;
        }
    });
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$('#payDate').val()) {
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0038", JoyinUtil.getLabel("FinProductPayInt002.payDate")));
                    return null;
                }

                return {
                    'isTotalTrade': YES_OR_NO_Y
                    ,'bankLendingType': null
                    ,'portfolioId': $("#portfolioId").val()
                    ,'deal':{
                        'finprodType2' : FINPROD_TYPE_TG_LCCP,
                        'tradeDate' : $('#payDate').val(),
                        'ps' : PS_ZJFX,
                        'ccy' : $('#ccy').val(),
                        'tradeAmt' : $("#actCusProfit").val(),
                        'bankLendingType' : null
                    }
                };
            });
        }
    };

    $(function () {
        // 绑定按钮事件
        $('#FinProductPayInt002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductPayInt002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());