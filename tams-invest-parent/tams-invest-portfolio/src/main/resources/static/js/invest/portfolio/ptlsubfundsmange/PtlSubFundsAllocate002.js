(function() {
    
    $('#portfolioId').change(function(){
    	portfolioIdChange();
    })
    var getElementById = function(id) {
        return $('#' + id);
    };
	function portfolioIdChange(){
		if($('#portfolioId').val() != ''){
			$("#portfolioName").val($('#portfolioId').select2('data')[0].text.split(":")[1]).trigger("change");
			table.reload();
		}
	}
	var options = {
			page: true,
			ajax: {
            url: JoyinUtil.getRealPath('invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/findPage'),
            type: 'POST',
            data: function() {
                return {portfolioId: $('#portfolioId').val(),
                		//pageModel:pageModel,
                		portfolioName:$('#portfolioName').val(),
                		wfId: $("#wfId").val(),
                		portfolioId2: $('#portfolioId2').val(),
                		parentPortfolioId: $('#parentPortfolioId').val(),
                		totleTradeId:$('#tradeId').val()
                	};
            }
			},
		/*data:[],*/
        checkbox: true,
        done:function(){
            $('body').on('change','#portfolioId',function(event,data){
            	//alert(data);
            	/*
                if(data.value[0] != ''){
                    alert(data.value[0]);
                    var obj = {'portfolioId':data.value[0]};
                    Joyin.ajax(basePath + "invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/getCashFlowActualAmt",obj,function(result){
                        table.renderRow(data.rowIndex, {actualAmt:result});
                    });
                }*/
            });
        },
        //addbtn: true,
        editCell: function(value, data, field, cell, column){
        	if(field=='tradeAmt'){
        		var actualAmt = parseFloat(data.actualAmt);
        		var tradeAmt = parseFloat(data.tradeAmt);
        		var ajustActualAmt = actualAmt + tradeAmt;
        		var avaAmt = parseFloat(data.avaAmt);
        		var ajustAvaAmt = avaAmt + tradeAmt;
                data.ajustActualAmt = formatMoney(ajustActualAmt);
                data.ajustAvaAmt = formatMoney(ajustAvaAmt);
                table.renderRow(data.LAY_TABLE_INDEX, data);
            }
    	},

    	
        searchOnInit: true,
        resize:true,
        cols:[
        	{field:'totleTradeId', visible:false}
            ,{field:'tradeId', visible:false}
        	,{field:'portfolioId', visible:false}
            ,{field:'portfolioName', visible:false}
            ,{field:'settleDate', visible:false}
            ,{field:'subPortfolioId', title: 'PtlSubFundsAllocate001.subPortfolioId', sortable:false, width:100}
            ,{field:'subPortfolioName', title: 'PtlSubFundsAllocate001.subPortfolioName', sortable:false, width:180}
            ,{field:'disPercent', title: 'PtlSubFundsAllocate001.disPercent', sortable:false, width:100}
            ,{field:'remark', title: 'PtlSubFundsAllocate001.remark', sortable:false, width:150}
            ,{field:'actualAmt', title: 'PtlSubFundsAllocate002.actualAmt', sortable:false, digit:2, width:120}
            ,{field:'avaAmt', title: 'PtlSubFundsAllocate002.avaAmt', sortable:false, digit:2, width:120}
            ,{field:'ccy', title: 'PtlSubFundsAllocate001.ccy', sortable:false, width:70}
            ,{field:'tradeAmt', title: 'PtlSubFundsAllocate001.tradeAmt', edit:true, type:'number', sortable:false, digit:2, width:120}
            ,{field:'deployrRemark', title: 'PtlSubFundsAllocate001.deployrRemark', sortable:false, width:150, edit:true}
            ,{field:'ajustActualAmt', title: 'PtlSubFundsAllocate002.ajustActualAmt', type:'number', sortable:false, digit:2, width:120}
            ,{field:'ajustAvaAmt', title: 'PtlSubFundsAllocate002.ajustAvaAmt', type:'number', sortable:false, digit:2, width:120}
            //,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    };
	
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    
	var formatMoney = function (s, n) {
	     n = n > 0 && n <= 20 ? n : 2;
	     s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';	     
	     return s;
   }
	
    function init(){
    	/*if($('#wfId').val() != ''){
    		$('#portfolioId').attr("disabled",true);
    	}*/
    	/*if($('#portfolioId2').val() != ''){
    		$("#portfolioId").val($('#portfolioId2'))
    	}*/
    }
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */    
    function saveOrSubmit(operateType) {
    	var portfolioId = $('#portfolioId').val();
    	var portfolioName = $('#portfolioName').val();
    	var tradeId = $('#tradeId').val();
    	if(portfolioId == null || portfolioId == ''){
    		Joyin.error("组合代码必填！");
    		return;
    	}
    	var selectedItem = table.getAllRows();
    	for(var i =0; i<selectedItem.length; i++){
    		if(selectedItem[i].tradeAmt == null ||selectedItem[i].tradeAmt == ''){
    			Joyin.error("调配金额必填！");
    			return;
    		}
    		var tradeAmt = selectedItem[i].tradeAmt;
    		if(tradeAmt < 0 && Math.abs(selectedItem[i].tradeAmt) >selectedItem[i].actualAmt){
        		Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",["调配金额","当前实际现金"]));
    			return;
        	}
    	}
    	
    	var tradeAmt = 0;
    	for(var i =0; i<selectedItem.length; i++){
    		if(selectedItem[i].tradeAmt){
    			tradeAmt += parseFloat(selectedItem[i].tradeAmt);
    		}
		}
    	
    	if(tradeAmt != 0){
    		Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0014"));
			return;
    	}
    	
    	var param = {};
    	param.selectedItem = selectedItem;
    	param.operateType = operateType;
    	param.portfolioId = portfolioId;
    	param.portfolioName = portfolioName;
    	param.tradeId = tradeId;
    	
    	
    	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/check', param, function(result){
    		Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/save', param, "PtlSubFundsAllocate002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/save', param, "PtlSubFundsAllocate002_btn_submit");
                }
            })
    	})
    	
    }
    
    $(function() {
    	init();
        // 按钮事件
        $('#PtlSubFundsAllocate002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlSubFundsAllocate002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());