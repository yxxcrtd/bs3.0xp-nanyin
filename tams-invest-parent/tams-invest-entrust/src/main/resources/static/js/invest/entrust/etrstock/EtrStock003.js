/**
 * 股票多对多委托新建
 */
(function() {
    var getriskdata = function() {
        var tableData1 = table.getAllRows();
        var selectedItem = tableData1;
        if (selectedItem.length > 0) {
            var objects = new Array();
            for ( var index in selectedItem) {
                var tradeAmt = null;
                var item = selectedItem[index];
                if (item.entrustAmt) {
                    tradeAmt = item.entrustAmt;
                } else if (item.entrustNum && item.price) {
                    var entrustNum = Number(item.entrustNum);
                    var price = Number(item.price);
                    tradeAmt = (entrustNum * price);
                }
                var trdProductDeal = {};
                trdProductDeal.shareAmt  = item.entrustNum;
                trdProductDeal.unitCprice  = item.price;
                trdProductDeal.tradeAmt = item.entrustAmt;
                var obj = {
                    tradeDirection : item.ps,
                    timePoint : MY_SUP_TIME_TYPE_01,
                    valDate : sysDate,
                    portfolioCode : item.portfolioId,
                    tradeAmount : tradeAmt,
                    assetType : fintype,
                    isEnabled : USE_STATUS_01,
                    assetCode : item.finprodId,
                    fullDataFlag : '1',
                    trdProductDeal : trdProductDeal
                }
                var object = {
                    assetCode : [ 'finprodMarketId', 'EtrStock002.finprodId' ],
                    tradeDirection : [ 'ps', 'EtrStock002.ps' ],
                    portfolioCode : [ 'portfolioId', 'EtrStock002.portfolioId' ],
                    tradeAmount : [ '', 'EtrStock002.priceAndNum' ],
                    data : obj
                }
                objects.push(object);
            }
            return objects;
        }
    }
    //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
    {
        field : 'indexId',
        title : 'EtrStock003.indexId',
        width : 200,
        sortField : 'indexId',
        sortable: false
    }// 序号
    , {
        field : 'portfolioId',
        title : 'EtrStock002.portfolioId',
        width : 200,
        sortField : 'portfolioId',
        sortable: false
    }// 组合代码
    , {
        field : 'vdate',
        title : 'EtrStock002.vdate',
        width : 200,
        sortField : 'vdate',
        type : 'date',
        sortable: false
    }// 委托开始日
    , {
        field : 'mdate',
        title : 'EtrStock002.mdate',
        width : 200,
        sortField : 'mdate',
        type : 'date',
        sortable: false
    }// 委托终止日
    , {
        field : 'ps',
        visible : false,
        sortable: false
    }, {
        field : 'busPs',
        title : 'EtrStock002.ps',
        width : 200,
        sortField : 'ps',
        sortable: false
    }// 交易方向
    , {
        field : 'price',
        title : 'EtrStock002.price',
        width : 200,
        sortField : 'price',
        type : 'money',
        sortable: false
    }// 交易价格
    , {
        field : 'finprodId',
        title : 'EtrStock002.finprodId',
        width : 200,
        sortField : 'finprodId',
        visible : false,
        sortable: false
    }// 资产代码
    , {
        field : 'finprodMarketId',
        title : 'EtrStock001.finprodId',
        width : 200,
        sortField : 'finprodMarketId',
        sortable: false
    }// 资产代码
    , {
        field : 'finprodName',
        title : 'EtrStock002.finprodName',
        width : 200,
        sortField : 'finprodName',
        sortable: false
    }// 资产名称
    , {
        field : 'board',
        title : 'EtrStock002.board',
        width : 200,
        sortField : 'board',
        visible : false,
        sortable: false
    }// 上市板
    , {
        field : 'busBoard',
        title : 'EtrStock001.board',
        width : '10%',
        sortField : "board",
        sortable: false
    }// 上市板名称
    , {
        field : 'counterId',
        title : 'etrInvestPersonInfo.counterId',
        width : 120,
        sortField : 'counterId',
        visible : false,
        sortable: false
    }// 交易对手
    , {
        field : 'counterIdName',
        title : 'etrInvestPersonInfo.counterId',
        width : 120,
        sortable: false
    }// 交易对手名称
    , {
        field : 'tradeMarket',
        visible : false,
        sortable: false
    }, {
        field : 'busTradeMarket',
        title : 'EtrStock001.tradeMarket',
        width : 200,
        sortField : 'tradeMarket',
        sortable: false
    }// 交易场所
    , {
        field : 'dealMode',
        visible : false,
        sortable: false
    }, {
        field : 'busDealMode',
        title : 'EtrStock002.dealMode',
        width : 200,
        sortField : 'dealMode',
        sortable: false
    }// 交易方式
    , {
        field : 'secAcctId',
        title : 'EtrStock002.secAcctId',
        width : 200,
        sortField : 'secAcctId',
        visible : false,
        sortable: false
    }// 证券账户
    , {
        field : 'secAcctIdName',
        title : 'EtrStock002.secAcctId',
        width : 200,
        sortField : 'secAcctId',
        sortable: false
    }// 证券账户名称
    , {
        field : 'ccy',
        visible : false,
        sortable: false
    }, {
        field : 'busCcy',
        title : 'EtrStock002.ccy',
        width : 200,
        sortField : 'ccy',
        sortable: false
    }// 币种
    , {
        field : 'priceModel',
        visible : false,
        sortable: false
    }, {
        field : 'priceModelStr',
        title : 'EtrStock002.priceModel',
        width : 200,
        sortField : 'priceModel',
        sortable: false
    }// 价格模式
    , {
        field : 'entrustAmt',
        title : 'EtrStock001.entrustAmt',
        width : 200,
        type : 'money',
        sortField : 'entrustAmt',
        sortable: false
    }// 价格模式
    , {
        field : 'entrustNum',
        title : 'EtrStock001.entrustNum',
        width : 200,
        sortField : 'entrustNum',
        type : 'number',
        sortable: false
    }// 委托数量
    , {
        field : 'proAvailableCash',
        title : 'EtrStock003.proAvailableCash',
        width : 200,
        sortField : 'proAvailableCash',
        type : 'money',
        sortable: false
    }// 指令可用现金
    , {
        field : 'tradeMarketAccAvailableCash',
        title : 'EtrStock001.tradeCash',
        width : 200,
        sortField : 'tradeMarketAccAvailableCash',
        type : 'money',
        sortable: false
    }// 交易所资金账户可用现金
    , {
        field : 'assetAvailablePosition',
        title : 'EtrStock003.assetAvailablePosition',
        width : 200,
        sortField : 'assetAvailablePosition',
        type : 'number',
        sortable: false
    }// 指令可用持仓
    , {
        field : 'remark',
        title : 'EtrStock003.remark',
        width : 200,
        sortfield : 'remark',
        sortable: false
    }// 备注
    , {
        field : 'fileArray',
        title : 'EtrStock003.fileArray',
        width : 150,
        visible : false,
        sortable: false
    }, {
        field : 'businessId',
        width : 150,
        visible : false,
        sortable: false
    }, {
        field : 'fileArrayName',
        title : 'EtrStock003.fileArray',
        type : 'text',
        width : 150,
        sortable: false
    }, {
        field : 'operation1',
        title : 'SysCommon.operation',
        sortable: false,
        render : function(data, type, row, meta) {
            return '<a class="layui-btn layui-btn-sm uploadFile" >上传附件</a> ';
        },
        width : 150
    }
    ]
    var table = $("#dataTable").joyintable(
            {
                data : etrStockDtoList == null || etrStockDtoList == '' ? null : JSON
                        .parse(etrStockDtoList),
                cols : cols,
                loadonce : false,
                page: false,
                filter: false,
                checkbox : false,
                height : 290,
                addbtn : true,
            });
    
  //绑定上传事件
    table.bind('click', '.uploadFile', function(data) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {};
        object.rowData = JSON.stringify(data);
        object.businessId = data.businessId;
        Joyin.dialog("上传附件", JoyinUtil.getRealPath("component/entrust/EtrListUploadFile/init"),
                "88%", "85%", object, function(result) {
                    var fileArrayName = '';
                    if (result && result.fileArray && _.isArray(result.fileArray)
                            && result.fileArray.length > 0) {
                        for ( var index in result.fileArray) {
                            fileArrayName += result.fileArray[index].srcName + '，';
                        }
                        result.fileArrayName = fileArrayName.substr(0, fileArrayName.length - 1);
                    }
                    table.renderRow(data.LAY_TABLE_INDEX, result);
                });
    });
    /**
     * 保存或提交
     * 
     * @param operateType
     *            操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        // 风险试算 start
        var s;
        if (BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if (!s && s != '') {
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
                return;
            }
        }
        // 风险试算 end
        // 校验数据 校验表格
        var tableData = table.getAllRows();
        if (tableData.length == 0) {
            Joyin.alert("委托列表至少有一条数据！", 2);
            return false;
        }

        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        var objects = object.selectedItem;
        for ( var index in objects) {
            var temp = objects[index];
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if (operateType === 'submit') {
                temp.elementId = 'EtrFund002_btn_submit';
            }
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstock/EtrStock003/check', objects, function(
                result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrstock/EtrStock003/save',
                            objects, "EtrStock002_btn_submit", "EtrStock002");
                }
            });
        });
    }

    $(function() {
        if (etrStockDtoList) {
            $.each(JSON.parse(etrStockDtoList), function(id, value) {

            });

            table.reload({
                data : JSON.parse(etrStockDtoList)
            });
        }
        /**
         * 提交
         */
        $('#EtrStock003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrStock003_btn_riskcal').click(function() {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            $("#riskMathFlag").val("02");
        });
    })

}());