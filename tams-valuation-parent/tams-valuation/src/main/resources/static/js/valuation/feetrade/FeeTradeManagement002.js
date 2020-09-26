/**
 * 费用交易管理-新建
 */
(function() {
    /**
     * 费用交易明细列表
     */
    var feeTradeTable = $("#feeTradeTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('valuation/feetrade/FeeTradeManagement002/getFeeTradeDetailList'),
            type: 'POST',
            data:function() {
                return getFeeTradeParams();
            }
        },
        cols:[
       	 {field:'bookSetId',title: '账套名称', width:180, sortable:false, edit:true, editOption:{type:'select', id: 'bookSetId',
       		 childrenIds:"subBookSetId",url: JoyinUtil.getRealPath('valuation/feetrade/FeeTradeManagement002/getBookSetList')}, visible:true}
       	 ,{field:'subBookSetId',title: '子账套名称', width:180, sortable:false, edit:true, editOption:{type:'select',
       		 id: 'subBookSetId', parentIds:'bookSetId',url: JoyinUtil.getRealPath('valuation/feetrade/FeeTradeManagement002/getSubBookSetList')}, visible:true}
           /*,{field:'bookSetName',title: '账套名称', width:180,sortable:false,edit:true,visible:true}*/
           ,{field:'feeEndDate', title: '计费截止日', width:180, edit:true, type:'date', sortable:false,visible:true}
           ,{field:'shouldPayAmt',title: '理论费用', width:180,sortable:false,type: 'number',edit:false,visible:true}
           ,{field:'payAmt',title: '实付金额', width:180,sortable:false,type: 'number',edit:true,visible:true}
           ,{field:'isCountOver', title: '是否结清', width:140, edit:true , editOption:{type:'select', result: YES_OR_NO}, sortable:false,visible:true}
           ,{field:'isNeedTan', title: '是否待摊', width:140, edit:true , editOption:{type:'select', result: YES_OR_NO}, sortable:false,visible:true}
           ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt'],visible:true}
       ],
        checkbox: false,
        addbtn: $("#pageModel").val() == PAGE_MODEL_NEW ? true : false,
        searchOnInit: true,
        page: false,
        /**
         * 加载完成回调
         */
        done: function(table, allData){
        	if($("#pageModel").val() == PAGE_MODEL_EDIT){
        		$("#feeAmtTotal").val(table[0].payAmt);
        		$("#shouldAmtTotal").val(table[0].shouldPayAmt);
        	}
        },
        editCell: function(value, data, field, cell, column){
        	var feeTradeTableList = feeTradeTable.getAllRows();
        	if(field === "subBookSetId"){
        		feeTradeTable.renderRow(data.LAY_TABLE_INDEX, {feeEndDate: ''});
        	}
        	if(field === "feeEndDate"){
        		if(data.bookSetId && value && data.subBookSetId && $("#feeType").val()!=''){
        			Joyin.ajaxJson(basePath + 'valuation/feetrade/FeeTradeManagement002/getShouldPayAmt', 
        	        	{bookSetId:data.bookSetId,finprodId:data.subBookSetId,feeEndDate:value,feeType:$("#feeType").val(),registOrg:$("registOrg").val()}, 
	    	        	function(result) {
        	        		feeTradeTable.renderRow(data.LAY_TABLE_INDEX, {shouldPayAmt: result});
        	        		//理论金额合计设置值
        	        		feeTradeTableList = feeTradeTable.getAllRows();
        	        		var shouldAmtTotal = 0;
        	        		for(var i in feeTradeTableList){
        	        			if(feeTradeTableList[i].shouldPayAmt){
        	        				/**单估值版本问题，取出金额为 1,000.00 */
        	        				var shouldAmt = feeTradeTableList[i].shouldPayAmt.replace(/,/g,"");
        	        				shouldAmtTotal = JoyinMath.doAdd(shouldAmtTotal, shouldAmt, 2, JoyinMath.ROUND_HALF_UP);
        	        			}
        	        		}
        	        		$("#shouldAmtTotal").val(shouldAmtTotal);
	    	        	}
	        		);
        		}
        	}
        	if(field === "payAmt"){
        		//费用金额合计设置值
        		feeTradeTableList = feeTradeTable.getAllRows();
        		var feeAmtTotal = 0;
        		for(var i in feeTradeTableList){
        			if(feeTradeTableList[i].payAmt){
        				/**单估值版本问题，取出金额为 1,000.00 */
        				var payAmt = feeTradeTableList[i].payAmt.replace(/,/g,"");
        				feeAmtTotal = JoyinMath.doAdd(feeAmtTotal, payAmt, 2, JoyinMath.ROUND_HALF_UP);
        			}
        		}
        		$("#feeAmtTotal").val(feeAmtTotal);
        	}
        }
    });
    /**
     * 列表删除
     */
    feeTradeTable.bind('click', '.delt', function(data, index) {
    	feeTradeTableDelt(data, index);
	});
    var feeTradeTableDelt = function(data, index){
    	feeTradeTable.removeRow(data);
    	var feeTradeTableList = feeTradeTable.getAllRows();
		var shouldAmtTotal = 0;
		for(var i in feeTradeTableList){
			if(feeTradeTableList[i].shouldPayAmt){
				shouldAmtTotal = JoyinMath.doAdd(shouldAmtTotal, feeTradeTableList[i].shouldPayAmt, 2, JoyinMath.ROUND_HALF_UP);
			}
		}
		$("#shouldAmtTotal").val(shouldAmtTotal);
		var feeAmtTotal = 0;
		for(var i in feeTradeTableList){
			if(feeTradeTableList[i].payAmt){
				feeAmtTotal = JoyinMath.doAdd(feeAmtTotal, feeTradeTableList[i].payAmt, 2, JoyinMath.ROUND_HALF_UP);
			}
		}
		$("#feeAmtTotal").val(feeAmtTotal);
    }
    /**
     * 获取查询参数
     */
    var getFeeTradeParams = function(){
    	var params = {
    			workflowId : workflowId,
    			tradeId : $("#tradeId").val()
    	}
    	return params;
    }
    /**
     * 费用交易明细列表加载
     */
    var feeTradeTableDataInit = function(){
    	
    	if(feeTradeTable.table){
    		feeTradeTable.reload();
    	}
    	else{
    		feeTradeTable.load();
    	}
    };
    /**
     * 保存提交事件
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm', true);
        //费用交易明细列表
        object.feeTradeList = feeTradeTable.getAllRows();
        if(operateType === 'revoke'){
            
        }else{
	        Joyin.ajaxJson(basePath + 'valuation/feetrade/FeeTradeManagement002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'valuation/publish/ProductPublishCounting002/save', object, "ProductPublishCounting002_btn_submit",null,
	                    		function(){window.top.layui.tab.tabDelete('PRODUCT_SIDE_001_003_008','FinNetProductDuration');});
	                   
	                }else if(operateType === 'submit') {
	                    WorkFlow.doSubmitList(basePath + 'valuation/feetrade/FeeTradeManagement002/save', object, "FeeTradeManagement002_btn_submit",null,
                    		function(){
	                    		window.top.layui.tab.tabDelete('PRODUCT_SIDE_001_003_008','FeeTradeManagement002');
                			}
	                    );
	                }
	            });
	        });
        }
    };
    $(function () {
    	//费用类型联动
    	$("#feeType").change(function(){
    		if($("#feeType").val().indexOf("C") == 0){
    			$("#registOrgDiv").hide();
    		}
    		if($("#feeType").val().indexOf("B") == 0){
    			$("#registOrgDiv").show();
    		}
    	});
    	//提交事件 
    	$("#FeeTradeManagement002_btn_submit").click(function(){
    		saveOrSubmit('submit');
    	});
    });
}());