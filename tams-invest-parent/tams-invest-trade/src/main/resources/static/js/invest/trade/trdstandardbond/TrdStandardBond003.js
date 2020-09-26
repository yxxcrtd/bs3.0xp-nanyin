(function () {
    var tempOutPrinAmt;
    var tempOutStrdPrinAmt;

    function getTableData() {
        if(outBondList) {
            return JSON.parse(outBondList);
        }
        if(parent.dataTable) {
            var pData =  parent.dataTable.getAllCheckedRows();
            var inOutStdrdPrinAmtSum =  0;//标准券总额度
            var pledgeStdrdPrinAmtSum = 0;//已使用标准券总额度
            var test = Joyin.ajaxJson(basePath + 'invest/trade/trdstandardbond/TrdStandardBond001/selectPageData?portfolioId='+
                pData[0].portfolioId +'&initPortfolioId=' + pData[0].portfolioId +
                '&tradePlace=' + pData[0].tradePlace + '&searchDate=' + platDate,
                {},
                function(result){
                if (result.root[0]) {
                    inOutStdrdPrinAmtSum =  result.root[0].stdrdPrinAmtSum;//标准券总额度
                    pledgeStdrdPrinAmtSum = result.root[0].pledgeStdrdPrinAmtSum;//已使用标准券总额度
                }
                }, false);
            for (var i = 0; i < pData.length; i++) {
                pData[i].avaOutPrinAmt = pData[i].inTotalPrinAmt;
                pData[i].outPrinAmt = pData[i].inTotalPrinAmt;
                pData[i].outStrdPrinAmt = pData[i].stdrdPrinAmt;
            }
            pData[0].inOutStdrdPrinAmtSum = inOutStdrdPrinAmtSum;
            pData[0].pledgeStdrdPrinAmtSum = pledgeStdrdPrinAmtSum;
            pData[0].avaStrdPrinAmtSum = inOutStdrdPrinAmtSum - pledgeStdrdPrinAmtSum;//可使用标准券总额度
            return pData;
        }
        return [];
    }
    
    /**
     * 定义数据表格
     */
    var dataTableTBL = $("#dataTable").joyintable({
        checkbox: false, //pageModel !== PAGE_MODEL_REFERENCE,
        data: getTableData(),
        // ajax请求数据
        /*ajax: {
            // 请求路径
            url: basePath + 'invest/trade/trdstandardbond/TrdStandardBond003/selectOutListData',
            // 请求类型
            type: 'POST',
            data: function () {
                var param = {};

                param.portfolioId = $("#portfolioId").val();
                param.bondCode = $("#bondCode").val();
                param.initBondCode = $("#initBondCode").val();
                param.tradePlace = $("#tradePlace").val();
                param.secAcctId = $("#secAcctId").val();
                param.initPortfolioId = $("#initPortfolioId").val();

                // 获取请求参数
                return param;
            }
        },*/
        height: 'full-' + 220,
        page: false,
        loadonce: true,
        /**
         * 定义表头
         */
        cols: [
            // 债券代码
            {field: 'bondCode', width: 200, title: 'TrdStandardBond003.colModel.bondCode', sortable: false,}
            // 债券简称
            , {field: 'bondName', width: 300, title: 'TrdStandardBond003.colModel.bondName', sortable: false,}
            // 交易场所
            , {field: 'tradePlaceText', width: 200, title: 'TrdStandardBond003.colModel.tradePlace', sortable: false,}
            // 已质押券面总额
            , {
                field: 'avaOutPrinAmt', width: 240, title: 'TrdStandardBond003.colModel.avaOutPrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 出库券面总额
            , {
                field: 'outPrinAmt', width: 240, title: 'TrdStandardBond003.colModel.outPrinAmt',
                sortable: false,
                type: 'money',
                edit: true,
                digit: 2
            }
            // 折算率%
            , {
                field: 'discountRate', width: 120, title: 'TrdStandardBond003.colModel.discountRate',
                sortable: false,
                type: 'number',
                digit: 2
            }
            // 出库标准券总额
            , {
                field: 'outStrdPrinAmt', width: 200, title: 'TrdStandardBond003.colModel.outStrdPrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            , {field: 'portfolioId', visible: false}
            , {field: 'bondCode', visible: false}
            , {field: 'tradePlace', visible: false}
            , {field: 'secAcctId', visible: false}
        ],
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "outPrinAmt") {
                tempOutPrinAmt = preVal.replace(/\,/g, '');
            }
            if (field === "outStrdPrinAmt") {
                tempOutStrdPrinAmt = preVal.replace(/\,/g, '');
            }
            return true;
        },
        editCell: function (value, data, field, cell, column) {
            if (field === "outPrinAmt") {
                if (data.outPrinAmt * 1 > data.avaOutPrinAmt * 1) {
                    var datas = dataTableTBL.getAllRows();
                    datas[data.LAY_TABLE_INDEX].outPrinAmt = tempOutPrinAmt;
                    dataTableTBL.reload({data: datas});
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",
                        [JoyinUtil.getLabel("TrdStandardBond003.colModel.outPrinAmt"),
                            JoyinUtil.getLabel("TrdStandardBond003.colModel.avaOutPrinAmt")]));
                } else {
                    var datas = dataTableTBL.getAllRows();
                    var outPrinAmt = data.outPrinAmt;
                    var discountRate = data.discountRate;
                    if (outPrinAmt != null && outPrinAmt != "" && discountRate != null && discountRate != "") {
                        datas[data.LAY_TABLE_INDEX].outStrdPrinAmt = (Number(outPrinAmt) * Number(discountRate) / Number(100)).toFixed(2);
                        dataTableTBL.reload({data: datas});
                    }
                }
            } else if (field === "outStrdPrinAmt") {
                var datas = dataTableTBL.getAllRows();
                var outStrdPrinAmt = data.inStdrdPrinAmt;
                var discountRate = data.discountRate;
                var newOutPrinAmt = 0;
                if (outStrdPrinAmt != null && outStrdPrinAmt != "" && discountRate != null && discountRate != "") {
                    newOutPrinAmt = (Number(outStrdPrinAmt) / (Number(discountRate) / Number(100))).toFixed(2);
                }
                if (newOutPrinAmt * 1 > data.avaOutPrinAmt * 1) {
                    var datas = dataTableTBL.getAllRows();
                    datas[data.LAY_TABLE_INDEX].outStrdPrinAmt = tempOutStrdPrinAmt;
                    dataTableTBL.reload({data: datas});
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",
                        [JoyinUtil.getLabel("TrdStandardBond003.colModel.outPrinAmt"),
                            JoyinUtil.getLabel("TrdStandardBond003.colModel.avaOutPrinAmt")]));
                } else {
                    var datas = dataTableTBL.getAllRows();
                    datas[data.LAY_TABLE_INDEX].outPrinAmt = newOutPrinAmt;
                    dataTableTBL.reload({data: datas});
                }
            }
        },
        done: function (table, allData) {
            if (table != null && table[0] != null) {
                if (table[0].inOutStdrdPrinAmtSum != null) {
                    // 标准券额度
                    $("#inOutStdrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].inOutStdrdPrinAmtSum, 3, null, 2));
                }
                if (table[0].pledgeStdrdPrinAmtSum != null) {
                    // 已质押标准券总额度
                    $("#pledgeStdrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].pledgeStdrdPrinAmtSum, 3, null, 2));
                }
                if (table[0].avaStrdPrinAmtSum != null) {
                    // 可使用标准券总额度
                    $("#avaStrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].avaStrdPrinAmtSum, 3, null, 2));
                }
            }
        }
    });

    /**
     * 保存按钮按下
     *
     * @param array
     * @returns {boolean}
     */
    var saveOrSubmit = function (operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        var datas = dataTableTBL.getAllRows();
        var outStandardPrinAMT = 0;
        for (var key in datas) {
            if (datas[key] && datas[key].avaOutPrinAmt && datas[key].outPrinAmt && datas[key].discountRate) {
                outStandardPrinAMT += Number(datas[key].outPrinAmt) * Number(datas[key].discountRate) / Number("100");
            }
        }
        if ($("#avaStrdPrinAmtSum").html()
            && outStandardPrinAMT > $("#avaStrdPrinAmtSum").html().replace(/[^\d\.\-]+/g, "") * 1) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0035",
                [JoyinUtil.getLabel("TrdStandardBond003.colModel.outStrdPrinAmt"),
                    JoyinUtil.getLabel("TrdStandardBond003.avaStrdPrinAmtSum")]
            ));
            return false;
        }

        if (operateType === 'save') {
            WorkFlow.doSaveList(basePath + 'invest/trade/trdstandardbond/TrdStandardBond003/save', object, "TrdStandardBond003_btn_save");
        } else {
            WorkFlow.doSubmitList(basePath + 'invest/trade/trdstandardbond/TrdStandardBond003/save', object, "TrdStandardBond003_btn_submit");
        }
    };


    /**
     * 画面加载完成
     */
    $(function () {
        // 工作流查看时，直接加载质押券列表
        if (outBondList) {
            dataTableTBL.load();
            dataTableTBL.reload({data: JSON.parse(outBondList), url: null});
        } else {
            dataTableTBL.load();
        }
        // 保存按钮事件
        $("#TrdStandardBond003_btn_save").click(function () {
            saveOrSubmit('save');
        });
        // 提交按钮事件
        $("#TrdStandardBond003_btn_submit").click(function () {
            saveOrSubmit('submit');
        });
    });
}());