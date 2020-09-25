$(function () {
	
    var getTradeIdParams = function() {
    	return {
    		tradeId:_tradeId
        }
    }
    
    typeDivTable = {
            rowno: true,
            searchOnInit: true,
            checkbox: false,
            page: false,
            height:200,
            cols: [
   				{field:'secId', width:120, title: '债券代码'},
				{field:'secName', width:120, title: '债券名称'},
				{field:'pledgeNum', width:130, title: '数量',type: 'money'},
				{field:'allPrice', width:130, title: '券面总额（万元）',type: 'money'},
				{field:'rate', width:120,title: '折算比例（%）',type: 'money'},
            ],          
            data: []
        }
    
    /**
     * 定义数据表格
     */
   
    if (repInfoList && repInfoList.length > 0) {
    	typeDivTable.data = typeDivTable.data.concat(repInfoList);
    }
    var repoInfoTable = $("#typeDiv-table").joyintable(typeDivTable);

	/*var typeDivTable=$("#typeDiv-table").joyintable({
	      ajax: {
	    	url: 'findPageRepInfo',
	        type: 'POST',
	        data: function() {
	        	alert("ddd");
	            return getTradeIdParams();
	        }
	    },
		searchOnInit: false,
      page : false,
      height: 180,
      checkbox:false,
		cols: [						   
				{field:'secId', width:120, title: '债券代码',sortField:'fp.pledge_finprod_id'},
				{field:'secName', width:120, title: '债券名称',sortField:'f.finprod_abbr'},
				{field:'pledgeNum', width:130, title: '数量',type: 'money',sortField:'fp.pledge_number'},
				{field:'allPrice', width:130, title: '券面总额（万元）',type: 'money',sortField:'fp.pledge_amt'},
				{field:'rate', width:120,title: '折算比例（%）',type: 'money',sortField:'fp.pledge_ratio'},
		],	
	  });*/
	
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
		                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease002/save', object, "PtlManualRelease002_btn_save");
		                }
		                else {
		                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease002/save', object, "PtlManualRelease002_btn_submit");
		                }
		            });
		      //  });
	 };
		    
	
    /**
     * 页面加载完后执行
     */
	$(function () {
        //alert("d");
        //alert("tradeId="+tradeId)
        // 绑定按钮事件
        $('#PtlManualRelease002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlManualRelease002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //typeDivTable.load();
	});


	
});

