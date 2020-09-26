$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	totleTradeId: $.trim($('#totleTradeId').val()),     
        	benchmarkId: $.trim($('#benchmarkId').val()), 
        	ps:$.trim($('#ps').val()),   
        	optFeePayType: $.trim($('#optFeePayType').val()),
        	activeState: $.trim($('#activeState').val()),
        	tradeDateStart:$.trim($('#tradeDate').startDate()),
        	tradeDateEnd:$.trim($('#tradeDate').endDate())
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        
        dblclick:function(table, data) {
        	Joyin.dialog(JoyinUtil.getLabel("查看场外期权交易信息"),
                    JoyinUtil.getRealPath("/valuation/basedata/marketdata/valotcopt/ValOtcOpt002/init"),
                    "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,totleTradeId:data.totleTradeId},
                    function(){
                    });
        },
        
		checkbox: true,
		cellMinWidth : 100,
		cols: [
           {field:'totleTradeId', title: '交易编号',width:180, sort: true},
           {field:'benchmarkId', title: '合约标的',width:120, sort: true},
		   {field:'booksetId', title: '账套代码',width:150, sort: true},
	       {field:'booksetName', title: '账套名称',width:180, sort: true},
		   {field:'ps', title: '交易方向',width:120, sort: true,visible:false},
		   {field:'psFmt', sortField: "ps", title: '交易方向',width:120, sort: true},
		   {field:'tradeDate', title: '交易日期',width:120, sort: true},
		   {field:'settleDate', title: '结算日期',width:120, sort: true},
	       {field:'shareAmt',type:"money", title: '名义本金',width:120, sort: true},
		   {field:'exePrice',type:"money", title: '行权价格',width:120, sort: true},
		   {field:'fpriceAmt',type:"money", title: '期权费',width:120,sort: true},
           {field:'optFeePayType', title: '期权费类型',width:120,sort: true,visible:false},
           {field:'optFeePayTypeFmt', sortField: "optFeePayType", title: '期权费类型',width:120, sort: true},
           {field:'activeState', title: '有效状态',width:120, sort: true,visible:false},
           {field:'activeStateFmt', sortField: "activeState", title: '有效状态',width:120, sort: true}
		],
	});
	
	var revoke = function() {
		if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0026"));
            return;
        }else if(!selArr || selArr.length > 1){
            var mes = "";
            for(var data in selArr){
                mes = mes+ data.tradeId + ","
            }
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058","标的代码",mes));
            return;
        }
        
        var object = {selectedItem: selArr, operateType:"revoke"};
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valotcopt/ValOtcOpt001/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	WorkFlow.doRevokeList(basePath + 'valuation/basedata/marketdata/valotcopt/ValOtcOpt001/revoke', 
		    			object, "ValOtcOpt001_btn_revoke", "ValOtcOpt002");
		    });
		});
        
    };
	
	var exportExcel = function() {
	    var selArr;
        var datas;
        if (table != null && table != '') {
            selArr= table.getAllCheckedRows();
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

	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询事件
        $('#ValOtcOpt001_btn_search').click(function() {
            table.load();
        });
        // 绑定导出事件
        $('#ValOtcOpt001_btn_export').click(function() {
            exportExcel();
        });
//        // 绑定撤销事件
//        $('#ValOtcOpt001_btn_Revoke').click(function() {
//        	revoke();
//        });
    });
}());