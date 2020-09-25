(function(){
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
        	sbidDate:$('#bidDate').startDate(),
        	ebidDate:$('#bidDate').endDate()
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
        
       
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/basicdata/mstbondfirstmarketcalendar/MstBondFirstMarketCalendar001/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId});
        },
        
        cols:[
            {field:'secId', title: 'MstBondFirstMarketCalendar001.secId', width:150,render:function(data, type, row, meta){
            	if(!row.secId){return "--";}
            }}
            ,{field:'secAbbr', title: 'MstBondFirstMarketCalendar001.secAbbr',width:150,render:function(data, type, row, meta){
            	if(!row.secAbbr){return "--";}
            }}
            ,{field:'termYears', title: 'MstBondFirstMarketCalendar001.termYears', width:150,type:'number',digit:4,render(data,type,row, meta){
            	if(data==''||data==null){return '--';}else{return parseFloat(data).toFixed(4);}
            }}
            ,{field:'issueAmt', title: 'MstBondFirstMarketCalendar001.issueAmt', width:150,type:'money',render(data,type,row, meta){
            	if(data==''||data==null){return '--';}else{return parseFloat(data).toFixed(2);}
            }}
            ,{field:'secGradeResult', title: 'MstBondFirstMarketCalendar001.secGradeResult', width:150,render:function(data, type, row, meta){
            	if(!row.secGradeResult){return "--";}
            }}
            ,{field:'issuerGradeResult', title: 'MstBondFirstMarketCalendar001.issuerGradeResult', width:150,render:function(data, type, row, meta){
            	if(!row.issuerGradeResult){return "--";}
            }}
            ,{field:'bidPriceMin', title: 'MstBondFirstMarketCalendar001.bidPriceMin', width:150,render:function(data, type, row, meta){
            	if(!row.bidPriceMin){return "--";}
            }}
            ,{field:'bidPriceMax', title: 'MstBondFirstMarketCalendar001.bidPriceMax', width:150,render:function(data, type, row, meta){
            	if(!row.bidPriceMax){return "--";}
            }}
            ,{field:'issueRate', title: 'MstBondFirstMarketCalendar001.issueRate', width:150,digit:4,type:'number',render(data,type,row, meta){
            	if(data==''||data==null){return '--';}else{return parseFloat(data).toFixed(4);}
            }}
            ,{field:'issuerName', title: 'MstBondFirstMarketCalendar001.issuer', width:150,sortField:'issuer',render:function(data, type, row, meta){
            	if(!row.issuerName){return "--";}
            }}
            ,{field:'sponsorName', title: 'MstBondFirstMarketCalendar001.sponsor', width:150,sortField:'sponsor',render:function(data, type, row, meta){
            	if(!row.sponsorName){return "--";}
            }}
            ,{field:'othClause', title: 'MstBondFirstMarketCalendar001.othClause', width:150,render:function(data, type, row, meta){
            	if(!row.othClause){return "--";}
            }}
            ,{field:'bidDate', title: 'MstBondFirstMarketCalendar001.bidDate', width:150,type:'date',render(data,type,row, meta){
            	if(!row.bidDate){return '--';}else{var t = new Date(row.bidDate);return t.format("yyyy-MM-dd"	);}
            }}
            ,{field:'payDate', title: 'MstBondFirstMarketCalendar001.payDate', width:150,type:'date',render(data,type,row, meta){
            	if(!row.payDate){return '--';}else{var t = new Date(row.payDate);return t.format("yyyy-MM-dd"	);}
            }}
            ,{field:'onmarketDate', title: 'MstBondFirstMarketCalendar001.onmarketDate', width:150,type:'date',render(data,type,row, meta){
            	if(!row.onmarketDate){return '--';}else{var t = new Date(row.onmarketDate);return t.format("yyyy-MM-dd"	);}
            }}
            ,{field:'mainSalerName', title: 'MstBondFirstMarketCalendar001.mainSaler', width:150,sortField:'mainSaler',render:function(data, type, row, meta){
            	if(!row.mainSalerName){return "--";}
            }}
            ,{field:'tradeMarketName', title: 'MstBondFirstMarketCalendar001.tradeMarket', width:150,sortField:'TRADE_MARKET',render:function(data, type, row, meta){
            	if(!row.tradeMarketName){return "--";}
            }}
            ,{field:'registOrgName', title: 'MstBondFirstMarketCalendar001.registOrg',sortField:'REGIST_ORG', width:150,render:function(data, type, row, meta){
            	if(!row.registOrgName){return "--";}
            }}
//            ,{field:'action', title: '操作' ,width:150,
//            	render : function(data, type, row, meta) {
//                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + '委托申购 '+ "</a>";
//                }
//            	}
        ]
    });
    //导出
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    //点击委托申购事件
    table.bind('click','.portfolioId-link', function(data) {
    	data.createTime = '';
    	data.updateTime = '';
    	mId = "1";
    	var finprodOld ;
    	//根据债券代码从finproduct表中查询是否存在finprodId
    	Joyin.ajaxSync(basePath + 'invest/basicdata/mstbondfirstmarketcalendar/MstBondFirstMarketCalendar001/getFinprodId',data,function(result)
     			{
    		finprodOld= result.data;
    		//已经存在finprodId直接进入一级投标委托界面
    		if(finprodOld){
    			
    	    		Joyin.dialog(JoyinUtil.getLabel("EtrBondFirstLBidding001"),
    	                    basePath + "invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/init",
    	                    "99%", "99%",{finprodId:data.secId,tradeMarket:data.tradeMarket,mId}
    	                    );
    	    // 不存在finprodId先创建一级债券标的再进入一级投标委托界面
    		}else{
    			Joyin.ajaxSync(basePath + 'invest/asset/tenderbond/FinTenderBondTargetAsset002/getInsert',data,function(){
    	    		Joyin.dialog(JoyinUtil.getLabel("EtrBondFirstLBidding001"),
    	                    basePath + "invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/init",
    	                    "99%", "99%",{finprodId:data.secId,tradeMarket:data.tradeMarket,mId}
    	                    );
    	    	});
    			
    		}
    			});
    });
    //点击按钮事件
    $(function() {
    	//查询
    	$('#MstBondFirstMarketCalendar001_btn_search').click(function() {
    		var bidDate = $('#bidDate').val();
    		if(!bidDate){
    			Joyin.error('投标日期不能为空');
    		}else{
            table.load();}
        });
    	//导出
    	 $('#MstBondFirstMarketCalendar001_btn_export').click(function() {
    		 Joyin.confirm(JoyinUtil.getMessage("MST_FIRSTBOND_001"), function(){
    			    console.log('确认');
    			    exportExcel();
    			}, {}, function() {
    			    console.log('取消');
    			});
             
         });
    	 //重置
    	 $('#MstBondFirstMarketCalendar001_btn_reset').click(function(){
    		 $("#type6").val('');
    		 $("#bidDate").val('');
    		 table.clear();
    	 });
    });
    
    
}());
		