/**
 * 计划货币型多对多指令新建
 */
(function() {
	var getriskdata = function() {
    var tableData1 = table.getAllRows();
    var selectedItem = tableData1;
    if (selectedItem.length > 0) {
        var objects = new Array();
        for ( var index in selectedItem) {
            var item = selectedItem[index];
            var trdProductDeal = {};
            trdProductDeal.tradeAmt = item.entrustAmt;
            trdProductDeal.shareAmt  = item.entrustNum;
             if(item.entrustAmt){
               var obj = {
                     tradeDirection : item.ps,
                     timePoint : MY_SUP_TIME_TYPE_02,
                     valDate : sysDate,
                     portfolioCode : item.portfolioId,
                     tradeAmount : item.entrustAmt,
                     assetType : fintype,
                     isEnabled : '01',
                     assetCode : item.finprodId,
                     fullDataFlag : '1',
                     trdProductDeal : trdProductDeal
                 }
             }else if (item.entrustNum) {
                var obj = {
                     tradeDirection : item.ps,    
                     timePoint : MY_SUP_TIME_TYPE_02,
                     valDate : sysDate,
                     portfolioCode : item.portfolioId,
                     tradeAmount : item.entrustNum,
                     assetType : fintype,
                     isEnabled : '01',
                     amtType:'1',
                     assetCode : item.finprodId,
                     fullDataFlag : '1',
                     trdProductDeal : trdProductDeal
                 }
            }else {
              var obj = {
                    tradeDirection : item.ps,
                    timePoint : MY_SUP_TIME_TYPE_02,
                    valDate : sysDate,
                    portfolioCode : item.portfolioId,
                    tradeAmount : item.entrustAmt,
                    assetType : fintype,
                    isEnabled : '01',
                    assetCode : item.finprodId,
                    fullDataFlag : '1',
                    trdProductDeal : trdProductDeal
                }
            }
             var object = {
                 tradeDirection : [ 'ps', 'EtrPlanAssetC002.ps' ],
                 portfolioCode : [ 'portfolioId', 'EtrPlanAssetC002.portfolioId' ],
                 tradeAmount : [ 'entrustAmt', 'EtrPlanAssetC002.entrustAmt' ],
                 assetType : [ 'assetType1', 'EtrPlanAssetC002.assetType1' ],
                 data : obj
             }
             objects.push(object);
         }
         return objects;
     }
	}

	/**
	 * 定义数据表格
	 */
	var cols = [ {field : 'indexId',title : 'EtrPlanAssetR003.indexId',width : '10%', sortable: false}// 序号
	, {field : 'portfolioId',title : 'EtrPlanAssetR002.portfolioId',width : '10%',id : 'portfolioId', sortable: false}// 组合代码
	, {field : 'vdate',title : 'EtrPlanAssetR002.vdate',width : '10%',type : 'date',sortField : "vdate",id : 'vdate', sortable: false}// 委托开始日
	, {field : 'mdate',title : 'EtrPlanAssetR002.mdate',width : '10%',type : 'date',sortField : "mdate",id : 'mdate', sortable: false}// 委托结束日
	, {field : 'finprodId',title : 'EtrPlanAssetR002.finprodId',visible: false, sortable: false}// 资产代码
	, {field : 'finprodMarketId',title : 'EtrPlanAssetR002.finprodId',width : '10%',id : "finprodMarketId", sortable: false}
	, {field : 'ps', visible:false, sortable: false}// 交易方向
    , {field : 'busPs', title: 'EtrPlanAssetC001.ps', width:'10%',sortField:"ps", sortable: false}// 交易方向
    , {field : 'ccy',visible:false, sortable: false}// 币种
    , {field : 'busCcy', title: 'EtrPlanAssetC001.ccy', width:'10%',sortField:"ccy", sortable: false}// 币种
	, {field : 'entrustAmtShow',title : 'EtrPlanAssetR003.entrustAmt',type : 'number',width : '10%', sortable: false}// 委托金额
	, {field : 'entrustNumShow',title : 'EtrPlanAssetR003.entrustNum',type : 'number',width : '10%', sortable: false}// 委托份额
	, {field : 'price',title : 'TrdPlanCcyInvestTrade003.carryAmt',width : '10%',id : 'price', sortable: false}// 待结转份额
	, {field : 'finprodName', title: 'EtrPlanAssetR001.finprodName', width:'10%', sortable: false}
    , {field : 'assetType1',visible:false, sortable: false}// 资产二级分类
    , {field : 'busAssetType1', title: 'EtrPlanAssetC001.type9', width:'10%', sortable: false}// 资产二级分类
    , {field : 'assetType10',visible:false, sortable: false}// 资产三级分类
    , {field : 'busAssetType10', title: 'EtrPlanAssetC001.type10', width:'10%', sortable: false}// 资产三级分类
    , {field : 'assetType11',visible:false, sortable: false}// 资产四级分类
    , {field : 'busAssetType11', title: 'EtrPlanAssetC001.type11', width:'10%',visible:IS_SHOW_TYPE == '01'?true:false, sortable: false}
	, {field : 'etrAvailableCash',title : 'EtrPlanAssetR003.proAvailableCash',width : '10%',id : 'etrAvailableCash', sortable: false}// 指令可用现金
	, {field : 'assetAvailablePosition',title : 'EtrPlanAssetR003.assetAvailablePosition',width : '10%',id : 'assetAvailablePosition', sortable: false}// 指令可用持仓
    , {field : 'invManagerId',width : 150,id :'invManagerId',title:'投资经理',visible : false, sortable: false}
	, {field : 'remark',title : 'EtrPlanAssetR003.remark',width : '10%', sortable: false}// 备注
	, {field : 'fileArray',title : 'EtrBondFirstLBidding003.fileArray',width : 150,visible : false, sortable: false}
	, {field : 'businessId',width : 150,visible : false, sortable: false}
	, {field : 'fileArrayName',title : 'EtrBondFirstLBidding003.fileArray',type : 'text',width : 150, sortable: false}
	, {field : 'operation1',title : 'SysCommon.operation',render : function(data, type, row, meta) {return '<a class="pro-link uploadFile">上传附件</a> ';},width : 150, sortable: false}
	];
	var table = $("#dataTable").joyintable({data : etrPlanAssetCDtoList == null || etrPlanAssetCDtoList == '' ? null : JSON.parse(etrPlanAssetCDtoList),
						cols : cols,
						loadonce : false,
						checkbox : true,
						height : 290,
						addbtn : true
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
	 * 提交
	 * 
	 * @param operateType 操作类型（submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
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
        var objects = object.bussCombDistribtList;   
        for ( var index in objects) {
           var temp = objects[index];
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if (operateType === 'save') {
                  temp.elementId = 'EtrPlanAssetC002_btn_save';
            } else {
                temp.elementId = 'EtrPlanAssetC002_btn_submit';
        }
        }
		Joyin.ajaxJson(basePath+ 'invest/entrust/etrplanassetc/EtrPlanAssetC003/check',objects,
						function(result) {Joyin.confirm(
											result.data,
											function() {
												if (operateType === 'submit') {
													WorkFlow.doSaveList(basePath+ 'invest/entrust/etrplanassetc/EtrPlanAssetC003/save',objects,"EtrPlanAssetC002_btn_save");
													}else {
                                                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetC003/save', objects, "EtrPlanAssetC002_btn_submit", "EtrPlanAssetR002");
	                                                  }
											});
						});
	}
	$(function() {
	  if (etrPlanAssetCDtoList) {
			table.reload({
				data : JSON.parse(etrPlanAssetCDtoList)
			});
		}
		/**
		 * 提交
		 */
		$('#EtrPlanAssetC003_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
		/**
		 * 风险试算
		 */
		$('#EtrPlanAssetC003_btn_riskcal').click(function() {
		//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            $("#riskMathFlag").val("02")
				});
	})
}());