$(function () {
	
	 function saveOrSubmit(operateType) {
		  //      Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade002/check', object, function(result) {
		            var object = $.serializeToObject('#searchForm');
/*		            object.saleArea=$('#saleArea').val().join(',');
			        object.tarCustomer=$('#tarCustomer').val().join(',');
			        object.saleChannel=$('#saleChannel').val().join(',');
			        object.assetType=$('#assetType').val().join(',');
		            object.finprodId=$('#finprodId').val();*/
		        	object.operateType = operateType;
		        	object.pageModel = pageModel;
		            Joyin.confirm("确认保存本条数据吗", function() {
		                if(operateType === 'save') {
		                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease003/save', object, "PtlManualRelease003_btn_save");
		                }
		                else {
		                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease003/save', object, "PtlManualRelease003_btn_submit");
		                }
		            });
		      //  });
	 };
		    
	
    /**
     * 页面加载完后执行
     */
	$(function () {
     
        // 绑定按钮事件
        $('#PtlManualRelease003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlManualRelease003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
	});


	
});

