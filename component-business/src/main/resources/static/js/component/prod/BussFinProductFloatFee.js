 /* 开放计划共通JS
 * @auth zhengyan
 */
(function(){
	
	// 快速录入标志
	var quickFlag = false;
	
    /**
     * 获取画面元素
     */
	function getElementById(id) {
		return $('#' + floatFeeEntityName + '_' + id);
	}
	

    /**
     * 定义数据表格
     *
     */
    var floatManageFeeTable=getElementById('floatManageFee').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('component/prod/BussFinProductFloatFee/productFloatFeeQuery'),
            type: 'POST',
            data: function() {
                return {finprodId: floatFeeFinprodId, workflowId: workflowId, entityName : floatFeeEntityName, effDate : effDate, quickFlag : quickFlag};
            }
        },
        searchOnInit: pageModel != PAGE_MODEL_NEW && $('#' + profitType).val() != PROFIT_TYPE_02,
        height : 200,
        checkbox:false,
        addbtn:true,
        addclick : function(rowData) {
        	//rowData.floatBaseLevel = floatManageFeeTable.getAllRows().length;
        	rowData.lev = floatManageFeeTable.getAllRows().length;
        	rowData.baseRuleId = JoyinUtil.getLabel("BussBenchMarkInfo");
        	rowData.coefficient = 0;
        	rowData.spreadRate = 0;
        	rowData.chargeRatio = 0;
        },
        loadonce : true,
        cols:[
            {field:'lev', width:200, title: 'BussFinProductFloatFee.table.floatBaseLevel', sortable: false, render : function(data, type, row, meta) { return (row.LAY_TABLE_INDEX+1); }}
            ,{field:'baseRuleId', width:200, title: 'BussFinProductFloatFee.table.baseRate', sortable: false, render:function () {return JoyinUtil.getLabel("BussBenchMarkInfo");}}
            ,{field:'coefficient', width:200, title: 'BussFinProductFloatFee.table.coefficient', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2"}
            ,{field:'spreadRate', width:200, title: 'BussFinProductFloatFee.table.spreadRate', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2", digit:4}
            ,{field:'chargeRatio', width:200, title: 'BussFinProductFloatFee.table.chargeRatio', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2", digit:4}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    

    
    /**
     * 绑定删除事件
     */
    floatManageFeeTable.bind('click', '.delt', function(data, index) {
    	floatManageFeeTable.deleteRow(index);
    });
    
    function feeNameChangeAction(){
    	var feeId = getElementById('feeId').val();
    	if (feeId && feeId != ''){
			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFee/queryMstFeeinfoByFeeId'), {feeId : feeId}, function(result){
				if (result){
					getElementById('basicBill').val(result.basicBill).trigger('change');
					getElementById('basis').val(result.basis).trigger('change');
				}
			})
			getElementById('div_floatManageFee').toggle(true);
			getElementById('netAssetValueDay').attr('disabled', false);
			getElementById('provisionFirstDay').attr('disabled', false);
			getElementById('floatBaseRule').attr('disabled', false);
		}else{
			getElementById('basicBill').val('').trigger('change');
			getElementById('basis').val('').trigger('change');
			getElementById('div_floatManageFee').toggle(false);
			getElementById('netAssetValueDay').val('').trigger('change').attr('disabled', true);
			getElementById('provisionFirstDay').val('').trigger('change').attr('disabled', true);
			getElementById('floatBaseRule').val('').trigger('change').attr('disabled', true);
		}
    }
    
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + profitType).val();
        if (selectedValueProfitType) {
            // 非预期收益型
            if (selectedValueProfitType != PROFIT_TYPE_02) {
                getElementById('div').show();
            }
            else{
                getElementById('div').hide();
            }
        } else {
            getElementById('div').hide();
        }
    }
    
    // 公开接口
    window.BussFinProductFloatFee = {
            getAllRows:getAllRows,
            floatFeeInitAction : floatFeeInitAction
    };

    function getAllRows(){
        if(floatManageFeeTable != ''){
            return floatManageFeeTable.getAllRows();
        }
    }
    
    function floatFeeInitAction(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFee/queryFloatFeeInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(floatFeeEntityName, data);
    	})
    	floatFeeFinprodId = input_finprodId;
    	quickFlag = true;
    	floatManageFeeTable.table ? floatManageFeeTable.reload() : floatManageFeeTable.load();
    }

    $(function() {
    	getElementById('feeId').change(function(){
    		feeNameChangeAction();
    	})
        // 父画面收益类型联动事件
    	profitTypeChange();
        $('#' + profitType).change(profitTypeChange);
    });
    
}());