/**
 * 港股通多对多指令新建
 */
(function() {

    var getriskdata = function() {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var list = [];
            var object = {
                tradeDirection: ['ps', 'EtrStockConnect002.ps'],
                portfolioCode: ['portfolioId', 'EtrStockConnect002.portfolioId'],
                tradeAmount: ['entrustNum', 'EtrStockConnect002.entrustAmt'],
                assetType: ['assetType1', 'EtrStockConnect002.finprodType2'],
                counterId: ['counterId', 'EtrStockConnect002.counterId'],
                data: list
            };
            for (var index in selectedItem) {
                var item = selectedItem[index];
                var tradeAmt;
                if (item.entrustAmt) {
                    tradeAmt = item.entrustAmt;
                } else if (item.entrustNum && item.price) {
                    var entrustNum = Number(item.entrustNum);
                    var price = Number(item.price);
                    tradeAmt = (entrustNum * price);
                }
                var obj = {
                    tradeDirection: item.ps,
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: iPlatformDate,
                    portfolioCode: item.portfolioId,
                    tradeAmount: tradeAmt,
                    assetType: item.finprodType2,
                    isEnabled: '01',
                    assetCode: item.finprodId
                };
                list.push(obj);
            }
        }
        return object;
    }


    /**
     * 定义数据表格
     */
    var cols = [
         {field:'parentPortfolioId', title: 'EtrStockConnect002.portfolioId', sortField:'parentPortfolioId',width : 150}
        ,{field:'portfolioId', title: 'EtrStockConnect002.childPortfolioId', sortField:'portfolioId',width : 150}
        ,{field:'portfolioName', title: 'EtrStockConnect001.portfolioName', sortField:'portfolioId',width : 150}
        ,{field:'finprodId',visible: false}
        ,{field:'finprodName', title: 'EtrStockConnect001.finprodName',sortField:'finprodId', width : 150}
        ,{field:'finprodMarketId',visible:false}
        ,{field:'hkseMarket', visible:false}
        ,{field:'hkseMarketStr', title: 'EtrStockConnect001.hkseMarket',sortField:'hkseMarket', width : 100}
        ,{field:'ps', visible: false}
        ,{field:'psStr', title: 'EtrStockConnect001.ps',sortField:'ps', width : 100}
        ,{field:'priceModel',visible:false}
        ,{field:'priceModelStr', title: 'EtrStockConnect001.priceModel',sortField:'priceModel', width : 100}
        ,{field:'entrustNum', title: 'EtrStockConnect001.entrustNum',type:'number',digit:0, width : 120,sortField:'entrustNum'}
        ,{field:'price', title: 'EtrStockConnect001.price',type:'number',digit:4, width : 120,sortField:'price'}
        ,{field:'entrustAmtHk', title: 'EtrStockConnect001.entrustAmtHk',type:'number',digit:4, width : 120,sortField:'entrustAmt'}
        ,{field:'entrustAmt', title: 'EtrStockConnect001.entrustAmt',type:'number',digit:4, width : 120,sortField:'entrustAmtRmb'}
        ,{field:'vdate', title: 'EtrStockConnect001.vdate',type:'date', width : 120,sortField:'vdate'}
        ,{field:'mdate', title: 'EtrStockConnect001.mdate',type:'date', width : 120,sortField:'mdate'}
        ,{field:'secAcctId', title: '证券账户', visible:false}
        ,{field:'ccy', title: '币种', visible:false}
        ,{field:'invManagerId', title: '投资经理', visible:false}
        ,{field:'finprodType2', title: '投管类型', visible:false}
        ,{field:'tradeMarket', title: '交易市场', visible:false}
    ]


    var table = $("#dataTable").joyintable(
        {
            data : etrStockConnectDtoList == null || etrStockConnectDtoList == '' ? null : JSON
                .parse(etrStockConnectDtoList),
            cols : cols,
            loadonce : false,
            page: false,
            filter: false,
            checkbox : false,
            height : 290,
            addbtn : true,

        });

    /**
     * 保存或提交
     *
     * @param operateType
     *            操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        // 校验数据 校验表格
        var tableData = table.getAllRows();
        if (tableData.length == 0) {
            Joyin.alert("委托列表至少有一条数据！", 2);
            return false;
        }

        var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
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
                temp.elementId = 'EtrStockConnect002_btn_submit';
            }
            temp.etrInvestPersonInfo = {};
        }
        var allDto = {};
        allDto.selectedItem = objects;
        allDto.importFlow = true;
        allDto.riskTrialRetDto = object.riskTrialRetDto;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect003/check', objects,
            function(result) {
                Joyin.confirm(result.data, function() {
                    if (operateType === 'submit') {
                        WorkFlow.doSubmitList(basePath
                            + 'invest/entrust/etrstockconnect/EtrStockConnect003/save', allDto,
                            "EtrStockConnect002_btn_submit", "EtrStockConnect002");
                    }
                });
            });
    }
    $(function() {
        if (etrStockConnectDtoList) {
            $.each(JSON.parse(etrStockConnectDtoList), function(id, value) {

            });

            table.reload({
                data : JSON.parse(etrStockConnectDtoList)
            });
        }
        /**
         * 提交
         */
        $('#EtrStockConnect003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrStockConnect003_btn_riskcal').click(function() {
            var list = $.serializeToObject('#searchForm', true).selectedItem;
            var objectList = [];
            for(var i in list){
                var temp = {};
                temp.portfolioId = list[i].portfolioId;
                temp.ps = list[i].ps;
                temp.tradeMarket = list[i].tradeMarket;
                temp.entrustAmt = list[i].entrustAmt;
                temp.vdate = list[i].vdate;
                temp.mdate = list[i].mdate;
                objectList.push(temp);
            }
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F11',
                objectList, getriskdata());
        });
    })

}());