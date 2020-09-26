$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: $.trim($('#finprodId').val()),
        	vdate: $.trim($('#vdate').val()),
        	extPs: $.trim($('#extPs').val()),
        	ccy: $.trim($('#ccy').val()),
        	optFeePayType: $.trim($('#optFeePayType').val())
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
        	Joyin.dialog(JoyinUtil.getLabel("查看场外期权标的信息"),
                    JoyinUtil.getRealPath("/valuation/basedata/marketdata/valoptinfo/ValOptInfo002/init"),
                    "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                    function(){
                    });
        },
        
		checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'finprodId', title: '交易编号', width:200, sort: true},
		   {field:'finprodType2', title: '期权标的类型', width:150, sort: true,visible:false},
		   {field:'finprodType2Fmt', sortField: "finprodType2", title: '期权标的类型', width:150, sort: true},
	       {field:'type6', title: '合约类型', width:120, sort: true,visible:false},
		   {field:'type6Fmt', sortField: "type6", title: '合约类型', width:120, sort: true},
		   {field:'vdate', title: '起息日', width:120, sort: true},
		   {field:'mdate', title: '到期日', width:120, sort: true},
		   {field:'extPs', title: '交易方向', width:120, sort: true,visible:false},
		   {field:'extPsFmt', sortField: "extPs", title: '交易方向', width:120, sort: true},
		   {field:'ccy', title: '币种', width:150, sort: true,visible:false},
		   {field:'ccyFmt', sortField: "ccy", title: '币种', width:150, sort: true},
		   {field:'term', title: '期限', width:120, sort: true},
		   {field:'contractNo', title: '合同编号', width:180, sort: true},
		   {field:'contractName', title: '合同名称', width:180, sort: true},
		   {field:'calendarName', title: '交易日历', width:150, sort: true},
		   {field:'prin', type:"number", title: '数量', width:120, sort: true},
		   {field:'tradeMarket', title: '交易场所', width:120, sort: true,visible:false},
		   {field:'tradeMarketFmt', sortField: "tradeMarket", title: '交易场所', width:120, sort: true},
		   {field:'registOrg', title: '登记托管机构', width:180, sort: true},
		   {field:'optFeePayType', title: '期权费类型', width:120, sort: true,visible:false},
		   {field:'optFeePayTypeFmt', sortField: "optFeePayType", title: '期权费类型', width:120, sort: true},
		   {field:'optFeeRate', type:"number",digit: 4, title: '期权费率', width:120, sort: true},
           {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
  	        	buttons: [
//  	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
  	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
  	        }
		],
	});
	
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
     * 事件绑定
     */
    var tableBind = function () {
    	
//    	/**
//    	 * 绑定事件：修改
//    	 */
//		table.bind('click', '.edit', function(data, index) {
//		   	Joyin.dialog(JoyinUtil.getLabel("编辑场外期权标的信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
//		               basePath + "valuation/basedata/marketdata/valoptinfo/ValOptInfo002/init",
//		               "95%", "95%", {pageModel:PAGE_MODEL_EDIT, finprodId: data.finprodId, operateType: "edit"},function(){
//		            	   table.reload();
//		       });
//        });

    	/**
    	 * 绑定事件：删除
    	 */
		table.bind('click', '.delt', function(data, index) {
			var object = {};
			var listdto=[data];
            object.listValOptInfo001Dto = listdto;
	    	object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo001/delete', object, "ValOptInfo001_btn_delete", "ValOptInfo002",function() {
    	        		   table.reload();
                    });
                });
            });
        });
		
    	/**
    	 * 绑定事件：批量删除
    	 */
        $('#ValOptInfo001_btn_delete').click(function(){
            if(table != null && table != ''){
                var selArr = table.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行删除。");
                return;
            }
            var object = {};
            object.listValOptInfo001Dto = selArr;
            object.data = datas;
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valoptinfo/ValOptInfo001/delete', object, "ValOptInfo001_btn_delete", "ValOptInfo002",function() {
    	        		   table.reload();
                    });
                });
            });
        });
    };

	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询事件
        $('#ValOptInfo001_btn_search').click(function() {
            table.load();
        });
        // 绑定导出事件
        $('#ValOptInfo001_btn_export').click(function() {
            exportExcel();
        });
        //新建、修改、删除事件绑定
        tableBind();
    });
}());