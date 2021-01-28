(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.portfolioId){
    		param.portfolioId = param.portfolioId.join(",");
    	}
    	if (param.chlFinprodId) {
            param.chlFinprodId = param.chlFinprodId.join(",");
        }
        if (param.finprodId) {
            param.finprodId = param.finprodId.join(",");
        }
    	param.dealDateStart = $('#TrdOutRightRepoTrade001_dealDate').startDate();
    	param.dealDateEnd = $('#TrdOutRightRepoTrade001_dealDate').endDate();
        return param;
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdOutRightRepoTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/buyoutrepo/TrdOutRightRepoTrade002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        
        cols:[
             {field:'effectStatusStr', title: 'TrdOutRightRepoTrade001.table.effectStatus', width:100, sortField:'effectStatus'}
            ,{field:'tradeId', title: 'TrdOutRightRepoTrade001.table.busiId', width:200}
			,{field:'entrustId', title: 'TrdOutRightRepoTrade001.table.entrustId', width:100}
			,{field:'portfolioId', title: 'TrdOutRightRepoTrade001.table.pltId', width:130,
                render : function(data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }}
			,{field:'portfolioName', title: 'TrdOutRightRepoTrade001.table.ptlName', width:200}
			,{field:'finprodIdShow', title: 'TrdOutRightRepoTrade001.table.finprodMarketId', width:160}
			,{field:'finprodNAME', title: 'TrdOutRightRepoTrade001.table.finprodName', visible: false}
            ,{field:'finprodAbbr', title: 'TrdOutRightRepoTrade001.finprodAbbr', width:160}
            ,{field:'chlFinprodId', title: 'TrdOutRightRepoTrade001.chlFinprodId', width:160}
			,{field:'tradeMarketStr', title: 'TrdOutRightRepoTrade001.table.dealPlace', width:150, sortField:'tradeMarket'}
			,{field:'psStr', title: 'TrdOutRightRepoTrade001.table.ps', width:100, sortField:'ps'}
			,{field:'tradeDate', title: 'TrdOutRightRepoTrade001.table.dealDate', width:100, type:'date'}
			/*,{field:'bondTotalAmt', title: 'TrdOutRightRepoTrade001.table.bondTotalAmt', width:120}*/
			,{field:'fCprice', title: 'TrdOutRightRepoTrade001.table.firstCleanPrice', width:130}
			,{field:'fYield', title: 'TrdOutRightRepoTrade001.table.firstYield', type:'date'}
			,{field:'prinAmt', title: 'TrdOutRightRepoTrade001.table.firstSettleAmt', width:100, type:'money'}
			,{field:'mCprice', title: 'TrdOutRightRepoTrade001.table.mdateCleanPrice', type:'date'}
			,{field:'mYield', title: 'TrdOutRightRepoTrade001.table.mdateYield', type:'date'}
			,{field:'mSettleAmt', title: 'TrdOutRightRepoTrade001.table.mdateSettleAmt', width:100,type:'money'}
			,{field:'ccyStr', title: 'TrdOutRightRepoTrade001.table.ccy', width:120, sortField:'ccy'}
			,{field:'remark', title: 'TrdOutRightRepoTrade001.table.remark', width:100}
            ,{field:'ps', visible:false}
            /*,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}*/
        ]
    });
    
    
    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    table.bind('click','.portfolioId-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "95%", "90%",
                { pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName });
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("TrdOutRightRepoTrade002.new"),
                JoyinUtil.getRealPath("invest/trade/buyoutrepo/TrdOutRightRepoTrade002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    //add by zhanggenglin 20191225 买断式回购多组合新建 start
    var openNew2 = function(){
        Joyin.dialog(JoyinUtil.getLabel("TrdOutRightRepoTrade003.new"),
                JoyinUtil.getRealPath("invest/trade/buyoutrepo/TrdOutRightRepoTrade003/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    //add by zhanggenglin 20191225 买断式回购多组合新建 end
    
	$(function() {
        $('#TrdOutRightRepoTrade001_btn_search').click(function() {
            table.load();
        });
        $('#TrdOutRightRepoTrade001_btn_export').click(function() {
        	exportExcel();
        });
        $('#TrdOutRightRepoTrade001_btn_add').click(function() {
        	openNew();
        });
        $('#TrdOutRightRepoTrade003_btn_add').click(function() {
            openNew2();
        });
        $('#TrdOutRightRepoTrade001_btn_revoke').click(function() {
        	if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }

            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade001/checkRevoke', checkObj, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    checkObj.ps = selArr[0].ps;
                    checkObj.tradeDate = selArr[0].tradeDate;
                    WorkFlow.doRevokeList(basePath + 'invest/trade/buyoutrepo/TrdOutRightRepoTrade001/doRevoke', checkObj, "TrdOutRightRepoTrade001_btn_revoke", "TrdOutRightRepoTrade002");
                });
            });
        });
	});
}());