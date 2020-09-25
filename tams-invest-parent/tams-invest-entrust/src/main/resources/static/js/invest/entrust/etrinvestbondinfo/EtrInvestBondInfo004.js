/**
 * 债券多对多委托新建
 */
(function () {
	
	var getriskdatas = function () {
        var mun=null;
        var object = $.serializeToObject('#searchForm'); 
        var bussCombDistribtList=null;
        if(object!=null&&object!=""){
        	bussCombDistribtList=object.selectedItem;
        }
        var list=[];
        for(var i in bussCombDistribtList){
            	var item = bussCombDistribtList[i];
	    		var obj = {tradeDirection:item.ps,timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:item.portfolioId,tradeAmount:item.entrustNum, assetType:fintype,assetSecType:item.assetType1,isEnabled:'01',assetCode:item.finprodId}
	            list[i]=obj;
        } 
        var object = {tradeDirection:['ps','EtrInvestBondInfo002.ps'],portfolioCode:['portfolioId','EtrInvestBondInfo002.portfolioId'],tradeAmount:['entrustNum','EtrInvestBondInfo002.entrustAmt'],assetType:['assetType1','EtrInvestBondInfo002.bondType'],data:list}
        return object;
    }
 
    var getriskdata = function () {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var objects = new Array();
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.shareAmt  = item.entrustNum;
                trdProductDeal.unitCprice  = item.price;
                var obj = {
                    tradeDirection : item.ps,
                    timePoint : MY_SUP_TIME_TYPE_02,
                    valDate : sysDate,
                    portfolioCode : item.portfolioId,
                    tradeAmount : item.entrustNum,
                    assetType : fintype,
                    isEnabled : '01',
                    assetCode: item.finprodId,
                    fullDataFlag : '1',
                    trdProductDeal : trdProductDeal
                }
                var object = {
                    tradeDirection : [ 'ps', 'EtrInvestBondInfo002.ps' ],
                    portfolioCode : [ 'portfolioId', 'EtrInvestBondInfo002.portfolioId' ],
                    tradeAmount : [ 'entrustAmt', 'EtrInvestBondInfo002.entrustAmt' ],
                    assetType : [ 'assetType1', 'EtrInvestBondInfo002.assetType1' ],
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
    	{field: 'indexId', title: 'EtrInvestBondInfo004.indexId', width: 80, sortable: false}
        , {field: 'vdateText', title: 'EtrInvestBondInfo004.vdate', width: 120, sortable: false}
        , {field: 'mdateText', title: 'EtrInvestBondInfo004.mdate', width: 120, sortable: false}
        , {field: 'portfolioId', title: 'EtrInvestBondInfo004.portfolioId', width: 120, sortable: false}
        , {field: 'finprodAbbr', title: 'EtrInvestBondInfo004.finprodId', width: 120, sortable: false}
//        , {field: 'assetType1Name', title: 'EtrInvestBondInfo002.bondType', width: 150, sortable: false}
        , {field: 'assetType1Name', title: 'EtrInvestBondInfo001.type9', width: 150, sortable: false}
        , {field: 'assetType10Name', title: 'EtrInvestBondInfo001.type10', width: 150, sortable: false}
        , {field: 'assetType11Name', title: 'EtrInvestBondInfo001.type11', width: 150, sortable: false}
        , {field: 'busPs', title: 'EtrInvestBondInfo004.ps', width: 150, sortable: false}
        , {field: 'busCcy', title: 'EtrInvestBondInfo004.ccy', width: 150, sortable: false}
        , {field: 'entrustNum', title: 'EtrInvestBondInfo004.entrustAmt', type: 'number', width: 150, sortable: false}
        , {field: 'priceModelStr', title: 'EtrInvestBondInfo004.priceModel', width: 150, sortable: false}
        , {field: 'price', title: 'EtrInvestBondInfo004.price', type: 'number', width: 150, sortable: false, digit:4}
        , {field: 'fpriceAmt', title: 'EtrInvestBondInfo004.fpriceAmt', type: 'number', width: 150, sortable: false, digit:4}
        , {field: 'exerYield', title: 'EtrInvestBondInfo004.exerYield', type: 'number', width: 150, sortable: false, digit:12}
        , {field: 'matureYield', title: 'EtrInvestBondInfo004.matureYield', type: 'number', width: 150, sortable: false, digit:12}
        , {field: 'minAmt', title: 'EtrInvestBondInfo004.minAmt', type: 'number', width: 150, sortable: false}
        , {field: 'settleSpreedName', title: 'EtrInvestBondInfo004.settleSpreed', width: 150, sortable: false}
        , {field: 'invAimName', title: 'EtrInvestBondInfo004.invAim', width: 150, sortable: false}
        , {field: 'tradeMarketName', title: 'EtrInvestBondInfo004.tradeMarket', width: 150, sortable: false}
        , {field: 'tradePlatName', title: 'EtrInvestBondInfo004.tradePlat', width: 150, sortable: false}
        , {field: 'secManageAcctIdName', title: 'EtrInvestBondInfo004.secManageAcctId', width: 150, sortable: false}
        , {field: 'chlIdName', title: 'invest.channelId', width: 150, sortable: false}
        , {field: 'proAvailableCash', title: 'EtrInvestBondInfo004.proAvailableCash', type: 'number', width: 150, sortable: false}
        , {field: 'assetAvailablePosition', title: 'EtrInvestBondInfo004.assetAvailablePosition', type: 'number', width: 150, sortable: false}
//        , {field: 'SecManageAvailableCash', title: 'EtrInvestBondInfo004.SecManageAvailableCash', type: 'number', width: 150, sortable: false}
//        , {field: 'channelAvailableCash', title: 'invest.channelId', type: 'number', width: 150, sortable: false}
        , {field: 'counterIdName', title: 'EtrInvestBondInfo004.counterId', width: 150, sortable: false}
        , {field: 'remark', title: 'EtrInvestBondInfo004.remark', width: 150, sortable: false}
        , {field: 'fileArray', width: 150, visible: false}
        , {field: 'businessId', width: 150, visible: false}
        , {field: 'fileArrayName', title: 'EtrInvestBondInfo004.fileArray', type: 'text', width: 150, sortable: false}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile">选择文件</a> ';
            }, width: 150, sortable: false
        }
    ];
    var table = $("#dataTable").joyintable({
        data: etrInvestBondInfoList == null || etrInvestBondInfoList == '' ? null : JSON.parse(etrInvestBondInfoList),
        cols: cols,
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

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        //校验数据  校验表格
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
        for (var index in objects) {
            var temp = objects[index];
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if (operateType === 'save') {
                temp.elementId = 'EtrInvestBondInfo002_btn_save';
            } else {
                temp.elementId = 'EtrInvestBondInfo002_btn_submit';
            }
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo004/check', objects,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo004/save', objects, "EtrInvestBondInfo002_btn_save", "EtrInvestBondInfo002");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo004/save', objects, "EtrInvestBondInfo002_btn_submit", "EtrInvestBondInfo002");
                    }
                });
            });
    }

    $(function () {
        if (etrInvestBondInfoList) {
            table.reload({data: JSON.parse(etrInvestBondInfoList)});
        }
        /**
         * 提交
         */
        $('#EtrInvestBondInfo004_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        /**
         * 风险试算
         */
        $('#EtrInvestBondInfo004_btn_riskcal').click(function () {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
//            $("#riskMathFlag").val("02");
        	var object = $.serializeToObject('#searchForm');
        	object.bussCombDistribtList = object.selectedItem;
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/0/RSK002/F01', object,getriskdatas());
        });
    })
}());