/**
 * 质押式回购多对多委托新建
 */
(function () {
    var getriskdata = function () {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var list = [];
            var object = {
                tradeDirection : [ 'ps', 'EtrRepo002.ps' ],
                portfolioCode : [ 'portfolioId', 'EtrRepo002.portfolioId' ],
                tradeAmount : [ 'entrustAmt', 'EtrRepo002.entrustAmt' ],
                assetType : [ 'assetType1', 'EtrRepo002.assetType1' ],
                data : list
            };
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.tradeAmt = item.entrustAmt;
                trdProductDeal.unitCprice  = item.price;
                var obj = {
                    tradeDirection : item.ps,
                    timePoint : MY_SUP_TIME_TYPE_02,
                    valDate : sysDate,
                    portfolioCode : item.portfolioId,
                    tradeAmount : item.entrustAmt,
                    assetType : item.ps == ps_04 ? fintype_nhg : fintype_zhg,
                    isEnabled : '01',
                    fullDataFlag : '1',
                    repoTerm:item.repoTerm,
                    vdate:item.vdateText,
                    mdate:item.mdateText,
                    trdProductDeal : trdProductDeal
                };
                list.push(obj);
            }
            return object;
        }
    }

    //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
        {field: 'indexId', title: 'EtrRepo003.indexId', width: 80, sortable: false}
        , {field: 'vdateText', title: 'EtrRepo003.vdate', width: 120, sortable: false}
        , {field: 'mdateText', title: 'EtrRepo003.mdate', width: 120, sortable: false}
        , {field: 'portfolioId', title: 'EtrRepo003.portfolioId', width: 120, sortable: false}
        // , {field: 'portfolioId', title: '子组合代码', width: 120, sortable: false}
        , {field: 'busPs', title: 'EtrRepo003.ps', width: 150, sortable: false}
        , {field: 'busCcy', title: 'EtrRepo003.ccy', width: 150, sortable: false}
        , {field: 'settleSpreedName', title: 'EtrRepo003.settleSpreed', width: 150, sortable: false}
        //, {field: 'busAssetType1', title: 'EtrRepo003.assetType1', width: 150, sortable: false}
        , {field: 'entrustAmt', title: 'EtrRepo003.entrustAmt', type: 'number', width: 150, sortable: false}
        , {field: 'priceModelStr', title: 'EtrRepo003.priceModel', width: 150, sortable: false}
        , {field: 'price', title: 'EtrRepo003.rate', type: 'number', width: 150, sortable: false}
        //, {field: 'minAmt', title: 'EtrRepo003.minAmt', type: 'number', width: 150, sortable: false}
        //, {field: 'tradeMarketName', title: 'EtrRepo003.tradeMarket', width: 150, sortable: false}
        , {field: 'repoTerm', title: 'EtrRepo003.repoTerm', type: 'number', width: 150, sortable: false}
        //, {field: 'calendarIdName', title: 'EtrRepo003.calendarId', width: 150, sortable: false}
        //, {field: 'securitiesCustName', title: 'EtrRepo003.securitiesCust', width: 150, sortable: false}
        // , {field: 'chlIdName', title: 'invest.channelId', width: 150, sortable: false}
        , {field: 'proAvailableCash', title: 'EtrRepo003.proAvailableCash', type: 'number', width: 150, sortable: false}
        , {field: 'remark', title: 'EtrRepo003.remark', width: 150, sortable: false}
        // , {field: 'zyqk', title: 'EtrRepo003.zyqk', width: 150, sortable: false}
        , {field: 'fileArray', width: 150, visible: false}
        , {field: 'businessId', width: 150, visible: false}
        , {field: 'fileArrayName', title: 'EtrRepo003.fileArray', type: 'text', width: 150, sortable: false}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile">选择文件</a> ';
            }, width: 150, sortable: false
        }
    ];
    var table = $("#dataTable").joyintable({
        data: etrRepoList == null || etrRepoList == '' ? null : JSON.parse(etrRepoList),
        cols: cols,
        click:function(table, data) {
            /*pledgeInfoTable.clear();
            var rowPledgeBondList = data.bussRepoSec.pledgeBondList;
            $.each(rowPledgeBondList,function(i,v){
                pledgeInfoTable.addRow(v);
            });*/
        },
        height: 250,
        checkbox: false,
        filter: false,
        page: false,
        addbtn:true
    });

    //绑定上传事件
    table.bind('click', '.uploadFile', function (data) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {};
        object.rowData = JSON.stringify(data);
        object.businessId = data.businessId;
        Joyin.dialog("上传附件",
            JoyinUtil.getRealPath("component/entrust/EtrListUploadFile/init"),
            "88%", "85%", object,
            function(result){
                var fileArrayName = '';
                if (result && result.fileArray && _.isArray(result.fileArray) && result.fileArray.length>0) {
                    for (var index in result.fileArray) {
                        fileArrayName += result.fileArray[index].srcName +'，';
                    }
                    result.fileArrayName = fileArrayName.substr(0,fileArrayName.length-1);
                }
                table.renderRow(data.LAY_TABLE_INDEX, result);
            });
    });

/*    //初始化质押券信息
    *//**
     * 定义数据表格
     *//*
    var pledgeInfoCols = [
        {field: 'bondCode', title: 'EtrRepo003.bondCode', width: 150, sortable: false}
        , {field: 'bondAbbrName', title: 'EtrRepo003.bondAbbrName', width: 150, sortable: false}
        , {field: 'avaPledgePrin', title: 'EtrRepo003.avaPledgePrin', width: 150, sortable: false}
        , {field: 'pledgePrin', title: 'EtrRepo003.pledgePrin', width: 150, sortable: false}
        , {field: 'firstNetPrice', title: 'EtrRepo003.firstNetPrice', width: 150, sortable: false}
        , {field: 'pledgeRatio', title: 'EtrRepo003.pledgeRatio', width: 150, sortable: false}
        , {field: 'secType', title: 'EtrRepo003.secType', width: 150, visible: false, sortable: false}
        , {field: 'repRegistOrg', title: 'EtrRepo003.repRegistOrg', width: 150, visible: false, sortable: false}
    ];
    var pledgeInfoTable = $("#pledgeInfo").joyintable({
        data: pledgeBondList == null || pledgeBondList == '' ? null : JSON.parse(pledgeBondList),
        cols: pledgeInfoCols,
        height: 160,
        checkbox: false,
        filter: false,
        page: false,
        addbtn:true
    });*/

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
            }
        }

        //校验数据  校验表格
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
        var allDto = {};
        for (var index in objects) {
            var temp = objects[index];
            
            // 指令授信 start
            temp.inputValue1 = 'F07';
            temp.tradeAmt = temp.entrustAmt;
            temp.prinAmt = temp.entrustAmt;
            temp.tradeDate = temp.vdate;
            temp.counterId = temp.counterId;
            temp.repoType = temp.busAssetType1;
            temp.assetType1 = temp.busAssetType1;
            temp.remark = temp.repoType;
            if(temp.ps == '03'){
            	temp.finprodType2 = 'F02';
            }else{
            	temp.finprodType2 = 'F03';
            }
            // 指令授信 end
            
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if(!temp.assetType1){
                temp.assetType1 = TARGET_BACK_TYPE_H01;
            }
            if (operateType === 'save') {
                temp.elementId = 'EtrIbRepoMain_btn_save';
            } else {
                temp.elementId = 'EtrIbRepoMain_btn_submit';
            }
            allDto.assetType = temp.assetType;
            allDto.repoTerm = temp.repoTerm;
            allDto.entrustAmt = temp.entrustAmt;
            allDto.operateType =  temp.operateType;
        }
        allDto.importFlow = true;
        allDto.selectedItem = objects;
        allDto.riskTrialRetDto = object.riskTrialRetDto;
        Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepoInfo004/check', objects,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/entrust/etribrepo/EtrIbRepoInfo004/save', allDto, "EtrIbRepoMain_btn_save", "EtrIbRepoinfo002");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etribrepo/EtrIbRepoInfo004/save', allDto, "EtrIbRepoMain_btn_submit", "EtrIbRepoinfo002");
                    }
                });
            });
    }

    $(function () {
        if (etrRepoList) {
            table.reload({data: JSON.parse(etrRepoList)});
        }
        /*if (pledgeBondList) {
            pledgeInfoTable.reload({data: JSON.parse(pledgeBondList)});
        }*/
        /**
         * 提交
         */
        $('#EtrIbRepoInfo004_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        /**
         * 风险试算
         */
        $('#EtrIbRepoInfo004_btn_riskcal').click(function () {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            // $("#riskMathFlag").val("02");
            //add by zhangnian strat
            var list = $.serializeToObject('#searchForm', true).selectedItem;
            var objectList = [];
            for(var i in list){
                var temp = {};
                temp.portfolioId = list[i].portfolioId;
                temp.ps = list[i].ps;
                temp.ccy = list[i].ccy;
                temp.entrustAmt = list[i].entrustAmt;
                temp.tradeMarket = list[i].tradeMarket;
                temp.price = list[i].price;
                temp.groupCode = 'F07_0_'+i;//格式类型+委托/交易+序号
                temp.repoTerm = list[i].repoTerm;
                temp.vdate = list[i].vdateText,
                    temp.mdate=list[i].mdateText,
                    objectList.push(temp);
            }
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F07',
                objectList, getriskdata());
            //add by zhangnian end
        });
    })
}());