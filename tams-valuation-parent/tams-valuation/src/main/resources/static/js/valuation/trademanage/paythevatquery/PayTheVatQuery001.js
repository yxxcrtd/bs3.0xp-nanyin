$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	tradeId: $.trim($('#tradeId').val()),
        	taxidenType: $.trim($('#taxidenType').val()),
        	vatDateBegin: $('#vatDate').startDate(),
        	vatDateEnd: $('#vatDate').endDate(),
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
        checkbox: true,
        cellMinWidth : 100,
		cols: [
		   {field:'tradeId',width:150, title: '交易编号', sort:true},
           {field:'booksetId',width:150, title: '账套代码', sort: true},
		   {field:'booksetName',width:150, title: '账套名称', sort: true},	   
           {field:'vDate',width:130, title: '缴税/退税开始日', sort: true},
           {field:'mDate',width:130, title: '缴税/退税截止日', sort: true},
           {field:'seitleDate',width:130, title: '缴税/退税日期', sort: true},
           {field:'interestIncome',width:150, type:"money", title: '利息收入', sort: true},
           {field:'dividendIncome',width:150, type:"money", title: '红利收入', sort: true},
           {field:'difbetIncome',width:150, type:"money", title: '价差收入', sort: true},
           {field:'appreciationTax',width:150, type:"money", title: '增值税', sort: true},
           {field:'constructionTax',width:150, type:"money", title: '城建税附加', sort: true},
           {field:'educationTax',width:150, type:"money", title: '教育税附加', sort: true},
           {field:'placeeducationTax',width:150, type:"money", title: '地方教育费附加', sort: true},
           {field:'additionalTax',width:150, type:"money", title: '增值税附加', sort: true},
           {field:'taxidenTypeFmt',width:100, title: '缴税/退税', sortField:"taxidenType", sort: true},
           {field:'tradeStatusFmt',width:100, title: '交易状态', sortField:"tradeStatus", sort: true},
           {field:'acctsubjectType',width:100, title: '核算主体类别', sort: true,visible:false},
           {field:'standardCcy',width:100, title: '本位币', sort: true,visible:false},
		],
	});
	
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

	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#PayTheVatQuery001_btn_search').click(function() {
            table.load();
        });
        //  绑定导出事件
        $('#PayTheVatQuery001_btn_export').click(function() {
            exportExcel();
        });
        // 绑定新建事件
        $('#PayTheVatQuery001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("产品缴税/退税新建"),
                    basePath + "valuation/trademanage/paythevatquery/PayTheVatQuery002/init",
                    "90%", "90%", {pageModel:PAGE_MODEL_NEW, operateType: "add"},function(){
                        table.reload();
            });
        });
    });
    
    
  /**
    * 撤销-走流程
    */
   $('#PayTheVatQuery001_btn_delete').click(function() {
   	var object = {};
   	var rowArr = table.getAllCheckedRows();// 所有选中行数据   	
   	if(rowArr == null || rowArr.length == 0){
   		layer.msg("请选择一条记录！");   		
   	}
   	else if(rowArr != null && rowArr.length>1){
		layer.msg("请选择一条记录！");
	}else{	
		object =rowArr[0];			
		var booksetId = object.booksetId;
		var tradeId = object.tradeId;
		Joyin.ajaxJson(basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery001/doCheck', object, function(result) {
		    Joyin.dialog(JoyinUtil.getLabel("缴纳增值税撤销："+tradeId),
                    basePath + "valuation/trademanage/paythevatquery/PayTheVatQuery003/init",
                    "95%", "90%",{pageModel:PAGE_MODEL_EDIT,data:rowArr,editFlag:"02",booksetId:booksetId,tradeId:tradeId},function(){
                        table.reload();
            });
        });
		
   	}
   }); 
	
});



