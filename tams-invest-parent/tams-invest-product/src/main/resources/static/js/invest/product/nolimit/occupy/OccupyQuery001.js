(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	return param;
    };
    
    /**
     * 定义数据表格
     */
    var tableAll=$("#dataTableAll").joyintable({ 
    	ajax: {
            url: 'selectForPageByAll',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        page: false,
        loadonce: true,
        
		cols: [
	    	 {field:'upperLimitScale',title: 'OccupyQuery001.upperLimitScale',width:'190', sortable: false }
	        ,{field:'totalAssetsScale', title: 'OccupyQuery001.totalAssetsScale', width:'120', sortable: false}
	        ,{field:'nolimitTotalAssets', title: 'OccupyQuery001.nolimitTotalAssets', width:'120',sortable: false}
	        ,{field:'availableTotalAssets', title: 'OccupyQuery001.availableTotalAssets', width:'190',sortable: false}
	        ,{field:'distribution1', title: 'OccupyQuery001.distribution.1', width:'120',sortable: false,visible : false}
	        ,{field:'distribution2', title: 'OccupyQuery001.distribution.2', width:'120',sortable: false,visible : false}
	        ,{field:'distribution3', title: 'OccupyQuery001.distribution.3', width:'120',sortable: false,visible : false}
	        ,{field:'distribution4', title: 'OccupyQuery001.distribution.4', width:'120',sortable: false,visible : false}
	        ,{field:'distribution5', title: 'OccupyQuery001.distribution.5', width:'120',sortable: false,visible : false}
	        ,{field:'distribution6', title: 'OccupyQuery001.distribution.6', width:'120',sortable: false,visible : false}
	        ,{field:'distribution7', title: 'OccupyQuery001.distribution.7', width:'120',sortable: false,visible : false}
	        ,{field:'distribution8', title: 'OccupyQuery001.distribution.8', width:'120',sortable: false,visible : false}
	        ,{field:'distribution9', title: 'OccupyQuery001.distribution.9', width:'120',sortable: false,visible : false}
	        ,{field:'distribution10', title: 'OccupyQuery001.distribution.10', width:'120',sortable: false,visible : false}
	        ,{field:'distribution11', title: 'OccupyQuery001.distribution.11', width:'120',sortable: false,visible : false}
	        ,{field:'distribution12', title: 'OccupyQuery001.distribution.12', width:'120',sortable: false,visible : false}
	    ]
	});
    
    var tableProdSeries=$("#dataTableProdSeries").joyintable({ 
    	ajax: {
            url: 'selectForPageByProdSeries',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        page: true,
        loadonce: false,
		cols: [
	    	 {field:'prodSeriesName',title: 'OccupyQuery001.prodSeries',width:'120', sortable: false }
	        ,{field:'nolimitTotalAssets', title: 'OccupyQuery001.nolimitTotalAssets', width:'120', sortable: false}
	        ,{field:'stockProportion', title: 'OccupyQuery001.stockProportion', width:'120',sortable: false}
	        ,{field:'distribution1', title: 'OccupyQuery001.distribution.1', width:'120',sortable: false,visible : false}
	        ,{field:'distribution2', title: 'OccupyQuery001.distribution.2', width:'120',sortable: false,visible : false}
	        ,{field:'distribution3', title: 'OccupyQuery001.distribution.3', width:'120',sortable: false,visible : false}
	        ,{field:'distribution4', title: 'OccupyQuery001.distribution.4', width:'120',sortable: false,visible : false}
	        ,{field:'distribution5', title: 'OccupyQuery001.distribution.5', width:'120',sortable: false,visible : false}
	        ,{field:'distribution6', title: 'OccupyQuery001.distribution.6', width:'120',sortable: false,visible : false}
	        ,{field:'distribution7', title: 'OccupyQuery001.distribution.7', width:'120',sortable: false,visible : false}
	        ,{field:'distribution8', title: 'OccupyQuery001.distribution.8', width:'120',sortable: false,visible : false}
	        ,{field:'distribution9', title: 'OccupyQuery001.distribution.9', width:'120',sortable: false,visible : false}
	        ,{field:'distribution10', title: 'OccupyQuery001.distribution.10', width:'120',sortable: false,visible : false}
	        ,{field:'distribution11', title: 'OccupyQuery001.distribution.11', width:'120',sortable: false,visible : false}
	        ,{field:'distribution12', title: 'OccupyQuery001.distribution.12', width:'120',sortable: false,visible : false}
	    ]
	});
    
	var init = function(){
		var queryCaliber = $("#queryCaliber").val();
		if(queryCaliber == QUERY_CALIBER_ALL){
			$("#queryAll").toggle(true);
			$("#queryByProdSeries").toggle(false);
		}else{
			$("#queryAll").toggle(false);
			$("#queryByProdSeries").toggle(true);
		}
	}
    
	//查询口径
	$("#queryCaliber").change(function(){
		var queryCaliber = $("#queryCaliber").val();
		if(queryCaliber == QUERY_CALIBER_ALL){
			$("#queryAll").toggle(true);
			$("#queryByProdSeries").toggle(false);
		}else{
			$("#queryAll").toggle(false);
			$("#queryByProdSeries").toggle(true);
		}
		queryDayChange();
	});
	
	//平台日change
	var queryDayChange = function(){
	//$("#queryDay").change(function(){
		var queryDay = $("#queryDay").val();
		if(!queryDay){
			Joyin.error('查询日不能为空');
			return;
		}/*else if (Date.ofDate(queryDay).getTime() >= platDate){
			Joyin.error('查询日必须小于平台日');
			return;
		}*/
		Joyin.ajaxJson(basePath + 'invest/product/nolimit/occupy/OccupyQuery001/checkDate?queryDay='+queryDay,
				'',function(result) {
					if(result.message == null ){
						if(queryDay){
							var month = Date.ofDate($("#queryDay").val()).getMonth();
							var showRow = [];
							for(var i = month;i<12;i++){
								var data = {};
								data.field = 'distribution'+(i+1);
								data.visible = true;
								showRow.push(data);
							}
							var queryCaliber = $("#queryCaliber").val();
							if(queryCaliber == QUERY_CALIBER_ALL){
								if(!tableAll.table){
									tableAll.load();
								}
								tableAll.showColumns(showRow);
							}else{
								if(!tableProdSeries.table){
									tableProdSeries.load();
								}
								tableProdSeries.showColumns(showRow);
							}
						}
					}
		});
		
	};
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
	$(function(){
		init();
		//查询
		$('#OccupyQuery001_btn_search').click(function() { 
			var queryDay = $("#queryDay").val();
			if(!queryDay){
				Joyin.error('查询日不能为空');
				return;
			}
			Joyin.ajaxJson(basePath + 'invest/product/nolimit/occupy/OccupyQuery001/checkDate?queryDay='+queryDay,
				'',function(result) {
					if(result.message == null ){
						var queryCaliber = $("#queryCaliber").val();
						if(queryCaliber == QUERY_CALIBER_ALL){
							tableAll.reload();
						}else{
							tableProdSeries.reload();
						}
					}
			});
			
        });
		//导出
        $('#OccupyQuery001_btn_export').click(function() {
        	exportExcel();
        });
        
        $("#queryDay").change(function(){
        	queryDayChange();
        });
	});
}());