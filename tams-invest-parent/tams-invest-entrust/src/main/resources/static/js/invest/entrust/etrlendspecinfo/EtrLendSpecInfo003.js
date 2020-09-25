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
                trdProductDeal.tradeAmt = item.entrustAmt;
                trdProductDeal.unitCprice  = item.price;
				if (item.assetType1 == S02) {
					var obj = {
						tradeDirection : item.ps,
						timePoint : MY_SUP_TIME_TYPE_02,
						valDate : sysDate,
						portfolioCode : item.portfolioId,
						tradeAmount : item.entrustAmt,
						assetType : fintype_cr,
						isEnabled : '01',
						assetCode : $('#finprodIdhid').val(),
						fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
					}
				} else if (item.ps == T32) {
					var obj = {
						tradeDirection : item.ps,
						timePoint : MY_SUP_TIME_TYPE_02,
						valDate : sysDate,
						portfolioCode : item.portfolioId,
						tradeAmount : item.entrustAmt,
						assetType : fintype_cr,
						isEnabled : '01',
						assetCode : $('#finprodIdhid').val(),
						fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
					}
				} else if (item.ps == T33) {
					var obj = {
						tradeDirection : item.ps,
						timePoint : MY_SUP_TIME_TYPE_02,
						valDate : sysDate,
						portfolioCode : item.portfolioId,
						tradeAmount : item.entrustAmt,
						assetType : fintype_cc,
						isEnabled : '01',
						assetCode : $('#finprodIdhid').val(),
						fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
					}
				} else {
					var obj = {
						tradeDirection : item.ps,
						timePoint : MY_SUP_TIME_TYPE_02,
						valDate : sysDate,
						portfolioCode : item.portfolioId,
						tradeAmount : item.entrustAmt,
						assetType : fintype_cr,
						isEnabled : '01',
						assetCode : $('#finprodIdhid').val(),
						fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
					}
				}
                var object = {
                    tradeDirection : [ 'ps', 'EtrLendSpecInfo002.ps' ],
                    portfolioCode : [ 'portfolioId', 'EtrLendSpecInfo002.portfolioId' ],
                    tradeAmount : [ 'entrustAmt','EtrLendSpecInfo002.entrustAmt' ],
                    assetType : ['assetType1','EtrLendSpecInfo002.type6'],
                    data : obj
                }
                objects.push(object);
            }
            return objects;
        }
    }
	
    var cols = [
        {field: 'indexId', title: 'EtrLendSpecInfo003.indexId', width: 100, sortable: false}
        , {field: 'vdateStr', title: 'EtrLendSpecInfo001.vdate', width: 120, sortable: false}
        , {field: 'mdateStr', title: 'EtrLendSpecInfo001.mdate', width: 120, sortable: false}
        , {field: 'portfolioId', title: 'EtrLendSpecInfo001.portfolioId', width: 120, sortable: false}
        , {field: 'psStr', title: 'EtrLendSpecInfo001.ps', width: 150, sortable: false}
        , {field: 'type6Str', title: 'EtrLendSpecInfo001.type6', width: 120, sortable: false}
        , {field: 'ccyStr', title: 'EtrLendSpecInfo001.ccy', width: 120, sortable: false}
        , {field: 'entrustAmt', title: 'EtrLendSpecInfo001.entrustAmt', width: 120, sortable: false}
        , {field: 'price', title: 'EtrLendSpecInfo001.price', width:100, type:'number', sortable: false}
        , {field: 'vdateAddStr', title: 'EtrLendSpecInfo002.vdateAdd', width: 120, sortable: false}
        , {field: 'mdateAddStr', title: 'EtrLendSpecInfo002.mdateAdd', width: 120, sortable: false}
        , {field: 'termDays', title: 'EtrLendSpecInfo001.term', width: 120, sortable: false}
        , {field: 'calendarIdName', title: 'EtrLendSpecInfo002.calendarId', width: 150, sortable: false}
        , {field: 'minAmt', title: 'EtrLendSpecInfo002.minAmt', width: 120, sortable: false}
        , {field: 'proAvailableCash', title: 'EtrBrowDepoInfo003.etrAvailableCash', type: 'number', width: 150, sortable: false}
        , {field: 'remark', title: 'EtrLendSpecInfo001.remark', width: 150, sortable: false}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile">上传附件</a> ';
            }, width: 150, sortable: false
        }
    ];
    var table =$("#dataTable").joyintable({
		data: etrLendSpecInfoDtoList == null || etrLendSpecInfoDtoList == '' ? null : JSON.parse(etrLendSpecInfoDtoList),
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
            if (operateType === 'save') {
                temp.elementId = 'EtrLendSpecInfo002_btn_save';
            } else {
                temp.elementId = 'EtrLendSpecInfo002_btn_submit';
            }
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo003/check', objects,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo003/save', objects, "EtrLendSpecInfo002_btn_save",'EtrLendSpecInfo002');
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo003/save', objects, "EtrLendSpecInfo002_btn_submit",'EtrLendSpecInfo002');
                    }
                });
            });
    }
    
    /**
     * 风险试算
     */
    $('#EtrLendSpecInfo003_btn_riskcal').click(function(){
    	//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
        $("#riskMathFlag").val("02");
    });
    
    /**
     * 提交
     */
    $('#EtrLendSpecInfo003_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
}());
