(function(){

	var tableCols = [];


	tableCols = [
		{field:'prodSeriesStr',sortField:'prodSeries',  title: 'FinProductBuild001.table.prodSeries', width:100}
		,{field:'finprodName', title: 'FinProductBuild001.table.prodName', width:200}
		,{field:'finprodMarketId', title: 'FinProductBuild001.table.prodId', width:'130',
			render : function(data, type, row, meta) {
				return "<a  class='pro-link finprodMarketIdLink' style='cursor: pointer;'>"+row.finprodMarketId+"</ a>";
			},sortField:"finprodMarketId"
		}
		,{field:'prodRegistCode', title: 'FinProductBuild001.table.prodRegistCode', width:130}
		,{field:'prodManager', title: 'FinProductLedger001.prodManager', width:100}
		,{field:'profitTypeStr', sortField:'profitType', title: 'FinProductBuild001.table.profitType', width:100}
		,{field:'profitFlagStr',sortField:'profitFlag', title: 'FinProductBuild001.table.profitFlag', width:150}
		,{field:'investNatureStr',sortField:'investNature', title: 'FinProductBuild001.table.investNature', width:150}
		,{field:'operationTypeStr',sortField:'operationType', title: 'FinProductBuild001.table.operationType', width:100}
		,{field:'issueStatusStr',sortField:'issueStatus', title: 'FinProductBuild001.table.issueStatus', width:100}
		,{field:'payTypeStr',sortField:'payType', title: 'FinProductLedger001.payType', width:100}
		,{field:'repRiskLevelStr',sortField:'repRiskLevel', title: 'FinProductBuild001.table.repRiskLevel', width:150}
		,{field:'issueTypeStr', sortField:'issueType', title: 'FinProductBuild001.table.issueType', width:100}
		,{field:'firstSaleVdate', title: 'FinProductBuild001.table.raiseStrDate', type:'date'}
		,{field:'firstSaleMdate', title: 'FinProductBuild001.table.raiseEndDate', type:'date'}
		,{field:'vdate', title: 'FinProductBuild001.table.vdate', type:'date'}
		,{field:'mdate', title: 'FinProductBuild001.table.mdate', type:'date'}
		,{field:'investmentCycle',sortField:'investmentCycle', title: 'FinProductLedger001.investmentCycle', width:140}
		,{field:'remainDays',sortField:'remainDays', title: 'FinProductLedger001.remainDays', width:140,sortable:false}
		,{field:'raiseAmtPlan',sortField:'raiseAmtPlan', title: 'FinProductLedger001.repRaiseAmtPlan', width:170,type:'money'}
		,{field:'subDealAmt',sortField:'subDealAmt', title: 'FinProductLedger001.subDealAmt', width:170,type:'money'}
		,{field:'subDealNum',sortField:'subDealNum', title: 'FinProductLedger001.subDealNum', width:150,type:'money'}
		,{field:'rateRepYieldStr',sortField:'rateRepYieldStr', title: 'FinProductLedger001.rateRepYield', width:220}
		,{field:'durationflag',sortField:'durationflag', title: 'FinProductLedger001.durationflag', width:120}
		//,{field:'cycs',sortField:'payCycle', title: 'FinProductLedger001.payCycle', width:100}
		,{field:'payCycle',sortField:'payCycle', title: 'FinProductLedger001.payCycle', width:100}
		,{field:'prodUnitValue',sortField:'prodUnitValue', title: 'FinProductLedger001.prodUnitValue', width:190,type:'number',digit:6}
		,{field:'prodAssetAmt',sortField:'prodAssetAmt', title: 'FinProductLedger001.prodAssetAmt', width:190,type:'number',digit:4,sortable:false}
		,{field:'tdyUnitValue',sortField:'tdyUnitValue', title: 'FinProductLedger001.tdyUnitValue', width:100}
		,{field:'yield7',sortField:'yield7', title: 'FinProductLedger001.yield7', width:150}
		,{field:'tdyAssetAmt',sortField:'tdyAssetAmt', title: 'FinProductLedger001.tdyAssetAmt', width:150,type:'money'}
		,{field:'floatFeeProportion',sortField:'floatFeeProportion', title: 'FinProductLedger001.floatFeeProportion', width:360,sortable:false}
		,{field:'feeFixedRate',sortField:'feeFixedRate', title: 'FinProductLedger001.feeFixedRate', width:150}
		,{field:'trustFeeRate',sortField:'trustFeeRate', title: 'FinProductLedger001.trustFeeRate', width:150}
		//BSTOT-1139 产品费用的费率均未显示 guyunfei 20200212 start
        ,{field:'saleProcessRate',sortField:'saleProcessRate', title: 'FinProductLedger001.saleProcessRate', width:150}
        //BSTOT-1139 产品费用的费率均未显示 guyunfei 20200212 end
		,{field:'customerRealRate',sortField:'customerRealRate', title: 'FinProductLedger001.customerRealRate', width:150}
		,{field:'customerProfit',sortField:'customerProfit', title: 'FinProductLedger001.customerProfit', width:150,type:'money'}
		,{field:'trustFee',sortField:'trustFee', title: 'FinProductLedger001.trustFee', width:150,type:'money'}
		,{field:'saleFee',sortField:'saleFee', title: 'FinProductLedger001.saleFee', width:150,type:'money'}
		,{field:'fixedFee',sortField:'fixedFee', title: 'FinProductLedger001.fixedFee', width:150,type:'money'}
		,{field:'floatDiffFee',sortField:'floatDiffFee', title: 'FinProductLedger001.floatDiffFee', width:160,type:'money'}
		,{field:'othSaleFee',sortField:'othSaleFee', title: 'FinProductLedger001.othSaleFee', width:150,type:'money'}
		,{field:'vatFee',sortField:'vatFee', title: 'FinProductLedger001.vatFee', width:160,type:'money'}
		,{field:'floatFee',sortField:'floatFee', title: 'FinProductLedger001.floatFee', width:150,type:'money'}
		,{field:'othFee',sortField:'othFee', title: 'FinProductLedger001.othFee', width:150,type:'money'}
		,{field:'midBusFee',sortField:'midBusFee', title: 'FinProductLedger001.midBusFee', width:220,type:'money'}
		,{field:'monthPubFee',sortField:'monthPubFee', title: 'FinProductLedger001.monthPubFee', width:180,type:'money'}
		,{field:'monthDueFee',sortField:'monthDueFee', title: 'FinProductLedger001.monthDueFee', width:180,type:'money'}		
		,{field:'rateYieldFloat',sortField:'rateYieldFloat',title:'FinProductLedger001.rateYieldFloat',width:180,sortable:false,width:225}
		];


	/**
	 * 定义数据表格
	 */
	table=$("#dataTable").joyintable({
		ajax: {
			url: 'selectForPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},

		cols: tableCols,
		rowno: false
	});

	table.bind('click','.finprodMarketIdLink', function(row) {
		console.log(row.finprodId);
       var label='';
       var pagePath='';
       if(row.profitType == '02'){
            pagePath = "invest/product/productelement/build/FinProductBuild002/init";
       }else{
            pagePath = "invest/product/productelement/netvalueproduct/FinNetValueProductBuild002/init";
       }
       Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
           "98%", "95%", {
               pageModel: PAGE_MODEL_REFERENCE,
               finprodId: row.finprodId
           });
	});
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.prodId){
    		param.prodId = param.prodId.join(",");
    	}
    	if(param.groupId){
    		param.groupId = param.groupId.join(",");
    	}
    	param.vdateStart = $('#FinProductBuild001_date_vdate').startDate();
    	param.vdateEnd = $('#FinProductBuild001_date_vdate').endDate();
    	param.mdateStart = $('#FinProductBuild001_date_mdate').startDate();
    	param.mdateEnd = $('#FinProductBuild001_date_mdate').endDate();
    	if(param.profitFlag)
    	param.profitFlag=param.profitFlag.join(',');
    	if(param.operationType)
        param.operationType=param.operationType.join(',');
    	if(param.durationStatus)
            param.durationStatus=param.durationStatus.join(',');
    	if(param.riskLevel){
    	    param.riskLevel = param.riskLevel.join(",");
    	}
    	if(param.targetCustomType){
    		param.targetCustomType = param.targetCustomType.join(",");
    	}
    	if(param.issueStatus){
    		param.issueStatus = param.issueStatus.join(",");
    	}
    	return param;
    };   

    var exportExcel = function() {
	    var selArr;
        var datas;
	    if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    var search = function(){
    	if($('#FinProductBuild001_queryDate').val() == null || $('#FinProductBuild001_queryDate').val() == ''){
    		Joyin.error(JoyinUtil.getMessage("请输入查询日！"));
    		return;
    	}
    	table.load();
    };
    
	$(function() {
        $('#FinProductLedger001_btn_search').click(function() { 
        	search();
        });
        $('#FinProductLedger001_btn_export').click(function() {
        	exportExcel();
        });
	});
}());