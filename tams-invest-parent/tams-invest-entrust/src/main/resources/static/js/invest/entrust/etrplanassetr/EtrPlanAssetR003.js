/**
 * 计划利率型多对多指令新建
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
                   tradeDirection : [ 'ps', 'EtrPlanAssetR002.ps' ],
                   portfolioCode : [ 'portfolioId', 'EtrPlanAssetR002.portfolioId' ],
                   tradeAmount : [ 'entrustAmt', 'EtrPlanAssetR002.entrustAmt' ],
                   assetType : [ 'assetType1', 'EtrPlanAssetR002.assetType1' ],
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
	var cols = [ {field : 'indexId',title : 'EtrPlanAssetR003.indexId',width : '10%',sortField : "indexId", sortable: false}// 序号
	, {field : 'portfolioId',title : 'EtrPlanAssetR002.portfolioId',width : '10%',id : 'portfolioId', sortable: false}// 组合代码
	, {field : 'vdate',title : 'EtrPlanAssetR002.vdate',width : '10%',type : 'date',sortField : "vdate",id : 'vdate', sortable: false}// 委托开始日
	, {field : 'mdate',title : 'EtrPlanAssetR002.mdate',width : '10%',type : 'date',sortField : "mdate",id : 'mdate', sortable: false}// 委托结束日
	, {field : 'finprodId',title : 'EtrPlanAssetR002.finprodId',fixtype : 2,width : '10%',visible: false,id : 'finprodId', sortable: false}// 资产代码
	, {field : 'finprodMarketId',title : 'EtrPlanAssetR002.finprodId',width : '10%',id : "finprodMarketId", sortable: false}
	, {field : 'ps',title : 'EtrPlanAssetR002.ps',fixtype : 2,width : '10%',visible : false, sortable: false}// 交易方向
	, {field : 'ccy',title : 'EtrPlanAssetR002.ccy',fixtype : 2,width : '10%',visible : false, sortable: false}// 币种
	, {field : 'psStr', title: 'EtrPlanAssetR001.ps', width:'10%',sortField:"ps", sortable: false}// 交易方向
    , {field : 'ccyStr', title: 'EtrPlanAssetR001.ccy', width:'10%',sortField:"ccy", sortable: false}// 币种
	, {field : 'entrustAmt',title : 'EtrPlanAssetR002.entrustAmt',type : 'number',width : '10%',id : 'entrustAmt', sortable: false}// 委托金额
	, {field : 'entrustNum',title : 'EtrPlanAssetR002.entrustNum',type : 'number',id : 'entrustNum',width : '10%', sortable: false}// 委托本金
	, {field : 'chlIdName',title : 'invest.channelId',width : '10%',id : 'chlId', sortable: false}// 通道
	, {field : 'finprodName', title: 'EtrPlanAssetR001.finprodName', width:'10%',sortField:"finprodName", sortable: false}
	, {field : 'assetType1',title : 'EtrPlanAssetR001.assetType1',width : '10%',visible : false, sortable: false}// 资产二级分类
	, {field : 'assetType10',title : 'EtrPlanAssetR001.assetType10',width : '10%',visible : false, sortable: false}// 资产三级分类
	, {field : 'assetType11',title : 'EtrPlanAssetR001.assetType11',width : '10%',visible : false, sortable: false}// 资产四级分类
	, {field : 'assetType1Str', title: 'EtrPlanAssetR001.assetType1', width:'10%',sortField:"assetType1", sortable: false}
    , {field : 'assetType10Str', title: 'EtrPlanAssetR001.assetType10', width:'10%',sortField:"assetType10", sortable: false}
    , {field : 'assetType11Str', title: 'EtrPlanAssetR001.assetType11', width:'10%',sortField:"assetType11",visible:IS_SHOW_TYPE == '01'?true:false, sortable: false}
	, {field : 'etrAvailableCash',title : 'EtrPlanAssetR003.proAvailableCash',width : '10%',id : 'etrAvailableCash', sortable: false}// 指令可用现金
	, {field : 'assetAvailablePosition',title : 'EtrPlanAssetR003.assetAvailablePosition',width : '10%',id : 'assetAvailablePosition', sortable: false}// 指令可用持仓
	, {field : 'accAvailableCash',title : 'EtrPlanAssetR003.accAvailableCash',width : '10%',id : 'accAvailableCash', sortable: false}// 托管户剩余现金
    , {field : 'invManagerId',width : 150,id :'invManagerId',title:'投资经理',visible : false, sortable: false}
	, {field : 'remark',title : 'EtrPlanAssetR003.remark',width : '10%', sortable: false}// 备注
	, {field : 'fileArray',title : 'EtrBondFirstLBidding003.fileArray',width : 150,visible : false, sortable: false}
	, {field : 'businessId',width : 150,visible : false, sortable: false}
	, {field : 'fileArrayName',title : 'EtrBondFirstLBidding003.fileArray',type : 'text',width : 150, sortable: false}
	, {field : 'operation1',title : 'SysCommon.operation',render : function(data, type, row, meta) {return '<a class="pro-link uploadFile">上传附件</a> ';},width : 150, sortable: false}
	];
	var table = $("#dataTable").joyintable({data : etrPlanAssetRDtoList == null || etrPlanAssetRDtoList == '' ? null : JSON.parse(etrPlanAssetRDtoList),
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
		   
	        // 指令授信 start
		   temp.finprodType2 = 'F17';
		   temp.inputValue1 = 'F03';
		   
	        if(temp.ps == 'T04' || temp.ps == 'T01'){
	        	temp.tradeAmt = temp.entrustAmt;
	        	temp.prinAmt = temp.entrustAmt;
	        }else{
	        	temp.tradeAmt = temp.entrustNum;
	        	temp.prinAmt = temp.entrustNum;
	        }
		   temp.tradeDate = temp.vdate;
	        // 指令授信 start
		   
            temp.operateType = operateType;
            temp.pageModel = pageModel;
			if (operateType === 'save') {
                  temp.elementId = 'EtrPlanAssetR002_btn_save';
            } else {
                temp.elementId = 'EtrPlanAssetR002_btn_submit';
        }
		}
		Joyin.ajaxJson(basePath+ 'invest/entrust/etrplanassetr/EtrPlanAssetR003/check',objects,
						function(result) {Joyin.confirm(
											result.data,
											function() {
												if (operateType === 'save') {
													WorkFlow.doSaveList(basePath+ 'invest/entrust/etrplanassetr/EtrPlanAssetR003/save',objects,"EtrPlanAssetR002_btn_save","EtrPlanAssetR002");
												  }else {
							                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrplanassetr/EtrPlanAssetR003/save', objects, "EtrPlanAssetR002_btn_submit", "EtrPlanAssetR002");
								                  }
											});
						});
	}
	$(function() {
		if (etrPlanAssetRDtoList) {
			table.reload({
				data : JSON.parse(etrPlanAssetRDtoList)
			});
		}
		/**
		 * 提交
		 */
		$('#EtrPlanAssetR003_btn_submit').click(function() {
			saveOrSubmit('submit');
		});
		/**
		 * 风险试算
		 */
		$('#EtrPlanAssetR003_btn_riskcal').click(function() {
			//window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
			$("#riskMathFlag").val("02")
		});
	})
}());