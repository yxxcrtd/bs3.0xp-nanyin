/**
 * 委托列表JS
 */
(function () {
    var getriskdata = function () {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var objects = new Array();
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                    var obj = {
                        tradeDirection : etrPs,
                        timePoint : MY_SUP_TIME_TYPE_02,
                        valDate : sysDate,
                        portfolioCode : item.portfolioId,
                        tradeAmount : item.purchBondTotal,
                        assetType : fintype,
                        assetCode :item.finprodId,
                        isEnabled : '01',
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                var object = {
                    tradeDirection : [ 'ps', 'EtrConBond002.ps' ],
                    portfolioCode : [ 'portfolioId', 'EtrConBond002.portfolioId' ],
                    tradeAmount : [ 'purchBondTotal', 'EtrConBond001.purchBondTotal' ],
                    assetType : [ 'assetType1', 'EtrConBond002.assetType1' ],
                    assetCode : [ 'finprodId','EtrBondFirstLBidding002.finprodId'],
                    data : obj
                }
                objects.push(object);
            }
            return objects;
        }
    }
	
	var cols = [
		{field: 'indexId', title: 'EtrConBond003.indexId', width: 100, sortable: false}
		, {field: 'purchDateText', title: 'EtrConBond001.purchDate', width: 120, sortable: false}
		, {field: 'purchDate', title: 'EtrConBond001.purchDate', width: 120, visible: false, sortable: false}
		, {field: 'portfolioId', title: 'EtrConBond003.portfolioId', width: 120, sortable: false}
		, {field: 'finprodId', title: 'EtrConBond003.finprodId', width: 150, sortable: false}
		, {field: 'finprodMarketId', title: 'EtrConBond003.finprodId', width: 120, visible: false, sortable: false}
		, {field: 'finprodName', title: 'EtrConBond003.finprodName', width: 150, sortable: false}
		, {field: 'tradeMarket', title: 'EtrConBond003.tradeMarket', width: 120, visible: false, sortable: false}
		, {field: 'tradeMarketName', title: 'EtrConBond003.tradeMarket', width: 120, sortable: false}
		, {field: 'ccy', title: 'EtrConBond003.ccy', width: 120, visible: false, sortable: false}
		, {field: 'assetType1', title: 'EtrConBond002.assetType1', width: 120, visible: false, sortable: false}
		, {field: 'ccyName', title: 'EtrConBond003.ccy', width: 120, sortable: false}
		, {field: 'purchModleName', title: 'EtrConBond001.purchModle', width: 120, sortable: false}
		, {field: 'purchTypeName', title: 'EtrConBond001.purchType', width: 120, sortable: false}
		, {field: 'purchModle', title: 'EtrConBond001.purchModle', width: 120, visible: false, sortable: false}
		, {field: 'purchType', title: 'EtrConBond001.purchType', width: 120, visible: false, sortable: false}
		, {field: 'purchBondTotal', title: 'EtrConBond001.purchBondTotal', width: 120, sortable: false}
		, {field: 'invAim', title: 'EtrConBond003.invAim', width: 120, visible: false, sortable: false}
		, {field: 'invAimName', title: 'EtrConBond003.invAim', width: 120, sortable: false}
		, {field: 'secAcctName', title: 'EtrConBond003.secAcctId', width: 150, sortable: false}
        , {field: 'chlIdName', title: 'invest.channelId', width: 150, sortable: false}
        , {field: 'remark', title: 'EtrConBond003.remark', width: 150, sortable: false}
        ,{field:'proAvailableCash', width:150, title: 'EtrConBond003.etrAvailableCash', sortable: false, type:'number', digit:2, sortable: false}
        ,{field:'channelAvailableCash', width:150, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("EtrConBond002.channelAvailableCash");}(), sortable: false, type:'number', digit:2, sortable: false}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile">上传附件</a> ';
            }, width: 150, sortable: false
        }
    ];
	var table =$("#dataTable").joyintable({
		data: etrConBondDtoList == null || etrConBondDtoList == '' ? null : JSON.parse(etrConBondDtoList),
        cols:cols,
        page: false,
        checkbox: false,
        filter: false,
        addbtn:false,
        addToLast:true,
        searchOnInit: true
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
            var etrInvestPersonInfo = {};
            temp.etrInvestPersonInfo = etrInvestPersonInfo;
            if (operateType === 'save') {
                temp.elementId = 'EtrConBond002_btn_save';
            } else {
                temp.elementId = 'EtrConBond002_btn_submit';
            }
        }
        var allDto = {};
        allDto.importFlow = true;
        allDto.selectedItem = objects;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrconbond/EtrConBond003/check', allDto,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrconbond/EtrConBond003/save', allDto, "EtrConBond002_btn_save",'EtrConBond002');
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrconbond/EtrConBond003/save', allDto, "EtrConBond002_btn_submit",'EtrConBond002');
                    }
                });
            });
    }
    
    /**
     * 风险试算
     */
    $('#EtrConBond003_btn_riskcal').click(function(){
    	//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
        $("#riskMathFlag").val("02");
    });
    
    /**
     * 提交
     */
    $('#EtrConBond003_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
}());
