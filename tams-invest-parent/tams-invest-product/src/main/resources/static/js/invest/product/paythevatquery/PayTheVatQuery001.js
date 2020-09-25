$(function () {
    var isShowWaterTrafficTax = false;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var result = $.serializeToObject('#searchForm');
        if($("#portfolioId").val()){
        	result.portfolioId = $("#portfolioId").val().join(",");
    	}
        if($("#vatDate").val()){
        	result.vatDateBegin = $("#vatDate").startDate();
        	result.vatDateEnd = $("#vatDate").endDate();
        }
        return result;
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }    
        },
        dblclick:function(table, data) {
            Joyin.dialog("缴纳增值税查看",
                    basePath + "invest/product/paythevatquery/PayTheVatQuery003/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                                    totleTradeId:data.totleTradeId,
                                    taxClass:data.taxClass
                                   });
        },
        checkbox: true,
        cellMinWidth : 100,
		cols: [
		   {field:'tradeId', visible: false},
		   {field:'totleTradeId', title: '交易编号',width:180,sort:true,visible:true},
		   {field:'tradeStatus', title: '有效状态', visible:false},
		   {field:'taxClassStr',width:100, title: '增值税品种', sort:true ,sortField:'taxClass'},
		   {field:'tradeStatusStr',width:100, title: '有效状态', sort:true ,sortField:'tradeStatus'},
		   {field:'taxidenTypeStr',width:100, title: '类型', sort:true ,sortField:'taxidenType'},
		   {field:'portfolioId',width:150, title: '组合代码', sort: true},
		   {field:'portfolioName', visible:false},
		   {field:'finprodId',title: '产品代码', visible:false},
		   {field:'finprodMarketId',width:150, title: '产品代码', sort: true},
		   {field:'finprodAbbr',width:150, title: '产品简称', sort: true},
		   {field:'channelId',width:150,title: '通道代码', visible:true,sort: true},
           {field:'channelName',width:150, title: '通道名称', sort: true},
		   {field:'ccyStr',width:100, title: '币种', sort: true,sortField:'ccy'},
            /*ZGBUG-332 【增值税缴纳】不支持退补税，列表表头，不应展示退税 guyunfei 20200311 end*/
           {field:'vDate',width:130, title: '缴税开始日', sort: true},
           {field:'mDate',width:130, title: '缴税结束日', sort: true},
           {field:'seitleDate',width:130, title: '缴税日期', sort: true},
            /*ZGBUG-332 【增值税缴纳】不支持退补税，列表表头，不应展示退税 guyunfei 20200311 end*/
           {field:'interestIncome',width:150, title: '利息收入税', sort: true},
           {field:'dividendIncome',width:150, title: '红利收入税', sort: true},
           {field:'difbetIncome',width:150, title: '价差收入税', sort: true},
           {field:'appreciationTax',width:150, title: '增值税', sort: true},
           {field:'constructionTax',width:150, title: '城建税附加税', sort: true},
           {field:'educationTax',width:150, title: '教育税附加税', sort: true},
           {field:'placeeducationTax',width:150, title: '地方教育费附加税', sort: true},
           {field:'waterTrafficTax',width:150, title: '水利基金附加税', sort: true},
           {field:'additionalTax',width:150, title: '附加税', sort: true},
           {field:'taxTotal',width:150, title: '税费总额', sort: true},
           {field:'actName',width:100, title: '收款账户', sort: true},
           {field:'showWaterTrafficTax', visible: false}
		]
	});
	
    /**
     * 导出
     */
	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
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
	
	var refreshTable = function() {
	    table.table ? table.reload() : table.load(); 
	    var colsArr = table.getAllCheckedRows(); 
	    if(colsArr && colsArr.length>0) {
	         var isShowWaterTrafficTax = colsArr.showWaterTrafficTax;
	         table.showColumns([{field:'waterTrafficTax',visible: isShowWaterTrafficTax}]); 
	    }
	    

	}

//    var exportExcel = function(){
//        Joyin.exportExcel('export',getSearchParams());
//    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinproductPayTheVatQuery001_btn_search').click(function() {
            refreshTable();
        });
        //  绑定导出事件
        $('#FinproductPayTheVatQuery001_btn_export').click(function() {
            exportExcel();
        });
        
        $("#taxClass").change(function(){
            var taxClass = $("#taxClass").val();
            $('#channelId').val("").trigger('change');
            if(taxClass == '02'){
                $('#channelId').prop('disabled', false); 
            } else {// 增值税品种非通道增值税时，通道代码选项置灰
                $('#channelId').prop('disabled', true);
            }
        });
        // 绑定新建事件
        $('#FinproductPayTheVatQuery001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建纳税增值税（ "+ "<span style='color:red'> * </span>" + "必填）"),
                    basePath + "invest/product/paythevatquery/PayTheVatQuery002/init",
                    "90%", "90%", {pageModel:PAGE_MODEL_NEW, operateType: "add"},function(){
                        refreshTable();
            });
        });
    });
    
    
  /**
    * 撤销-走流程
    */
   $('#FinproductPayTheVatQuery001_btn_delete').click(function() {
   	var object = {};
   	var rowArr = table.getAllCheckedRows();// 所有选中行数据   	
   	if(rowArr == null || rowArr.length == 0){
   		layer.msg("请选择一条记录！");   		
   	}else if(rowArr != null && rowArr.length>1){
			layer.msg("请选择一条记录！");
		}else{	
			object =rowArr[0];	
			if(object.tradeStatus=='03'){
				layer.msg("请选择一条有效记录！");
				return;
			}
			var totleTradeId = object.totleTradeId;
			var taxClass = object.taxClass
			 Joyin.dialog(JoyinUtil.getLabel("缴纳增值税撤销："+totleTradeId),
	                    basePath + "invest/product/paythevatquery/PayTheVatQuery003/init",
	                    "95%", "90%",{pageModel:PAGE_MODEL_EDIT,data:rowArr,editFlag:"02",totleTradeId:totleTradeId,taxClass:taxClass},function(){
	                        refreshTable();
	         });
   	}
   }); 
	
});



