$(function () {
	
	function saveOrSubmit(operateType,elementId) {
        var object = $.serializeToObject('#submitForm');
        object.portfolioDetailTable = table.getAllRows();
        object.pageModel = pageModel;
        object.operateType = operateType;
        
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/checkadd', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
            		WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/save', object, elementId);
            	}else{
            		WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/save', object, elementId);
            	}
            });
        });
    };
    var initFlag = true;    
    
    /**
     * 定义数据表格
     *
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/selectPortfolioDetail'),
            type: 'POST',
            data: function() {
            	 return {
            		 workflowId: $("#workflowId").val(),
            		 pageDataStatus: $("#pageDataStatus").val(),
            		 totleTradeId: $("#totleTradeId").val(),
            		 finprodId: $("#finprodId").val(),
            		 tradeDate: $("#tradeDate").val(),
            		 ccy: $("#ccy").val(),
            		 portfolioId: $("#portfolioId").val(),
            		 tradeAmt: $("#tradeAmt").val()
            	 };
            }
        },
        searchOnInit:  true,
        height : 200,
        checkbox:false,
        addbtn:true,
        loadonce : true,
        page: false,
        cols:[
        	// 调整表格宽度 by 赵东东 2019.10.29 start
            {field:'portfolioIdSub', title: 'PtlSubFundsMange002.portfolioIdSub', sortable: false}
            ,{field:'portfolioSubName', title: 'PtlSubFundsMange002.portfolioSubName', sortable: false}
            ,{field:'disPercent', title: 'PtlSubFundsMange002.disPercent', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2"}
            ,{field:'tradeAmtfp', title: 'PtlSubFundsMange002.tradeAmtfp', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2"}
            ,{field:'preSurplusCash',title: 'PtlSubFundsMange002.preSurplusCash', sortable: false, type: 'number',fixType:"2",pattern:"2"}
            // 调整表格宽度 by 赵东东 2019.10.29 end
        ],
        editCell : function(value, data, field, cell, column){
        	// 修改比例：联动修改分配金额，最后一个子组合的分配金额倒减
        	if (field === "disPercent"){
        		// 联动修改分配金额
    			data.tradeAmtfp = JoyinMath.doMul(JoyinMath.doMul(value,0.01),$('#tradeAmt').val());
    			table.renderRow(data.LAY_TABLE_INDEX, data);
    			changeRowLastTradeAmtfp();
        	}
        	// 修改分配金额，最后一个子组合的分配金额倒减
        	else if (field === "tradeAmtfp"){
        		changeRowLastTradeAmtfp();
        	}
        },
		done:function () {
			if (portfolioDetailTable&&initFlag) {
				table.reload({data: JSON.parse(portfolioDetailTable), url: null});
				initFlag = false;
			}
        }
    });
    
    /**
     * 联动修改分配金额，最后一个子组合的分配金额倒减
     */
    var changeRowLastTradeAmtfp = function (){
    	var allData = table.getAllRows();
		var length = allData.length;
		var rowLast;// 最后一行
		var rowLastIndex;// 最后一行id
    	// 最后一个子组合的分配金额倒减
		var tradeAmtfp = '0';// 最后一列外的分配金额总和
		//列表数据不止一行
    	if (length > 1){
    		// 遍历列表数据
    		$.each(allData, function(id, value){
    			if(id != length-1){
    				// 累计最后一列外的分配金额总和
    				tradeAmtfp = JoyinMath.doAdd(tradeAmtfp, value.tradeAmtfp, 2, JoyinMath.ROUND_HALF_UP);
    			}else{
    				rowLast = value;
    				rowLastIndex = id;
    			}
        	});
    		// 最后一行的分配金额 = 界面.交易金额  - 累计最后一列外的分配金额总和
    		rowLast.tradeAmtfp = JoyinMath.doMinus($('#tradeAmt').val(),tradeAmtfp);
    		table.renderRow(rowLastIndex, rowLast);//修改最后一行内容
    	}
    }
    
	var linkBind = function (){
		//  绑定联动：产品代码
		$("#finprodId").change(function(){
			var finprodId =  $("#finprodId").val();
			if(finprodId){
				Joyin.ajax(
					basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/queryProdName', 
					{finprodId : finprodId},
					function (data) {
						$("#finprodName").val(data.finprodName);
					}
				);
			}
		});
	}
    
    
    $(function() {
    	
    	linkBind(); // 联动绑定
    	 // 绑定按钮事件
        $('#PtlSubFundsMange002_btn_save').click(function() {
            saveOrSubmit('save','PtlSubFundsMange002_btn_save');
        });
        $('#PtlSubFundsMange002_btn_submit').click(function() {
            saveOrSubmit('submit','PtlSubFundsMange002_btn_submit');
        });
        				
    });
    
});