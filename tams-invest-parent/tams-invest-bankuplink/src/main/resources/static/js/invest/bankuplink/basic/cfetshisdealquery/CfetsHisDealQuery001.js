(function(){
    /** 获取查询参数 **/
    //现券买卖历史报价查询
    var getDvpTransferSearchParams = function() {
        var result = $.serializeToObject('#dvpTransferForm');
        if ($("#bondQuoteDate").val()) {
            result.startQuoteDate = $("#bondQuoteDate").startDate();
            result.endQuoteDate = $("#bondQuoteDate").endDate();
        }
        result.isDvp=true;
        return result;
	
    };
    //质押式回购历史报价查询
    var getTransferSearchParams = function() {
        var result = $.serializeToObject('#transferForm');
        if ($("#repoQuoteDate").val()) {
            result.startQuoteDate = $("#repoQuoteDate").startDate();
            result.endQuoteDate = $("#repoQuoteDate").endDate();
        }
        result.isDvp=false;
        return result;
    };


    /** 定义数据表格 **/
    //现券买卖历史报价查询
    var dvpTransferTable=$("#dvpTransferTable").joyintable({
        ajax: {
            url: 'queryHisDealPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getDvpTransferSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'quoteDate', title: 'CfetsHisDealQuery001.Bond.quoteDate', width:90, type:"date" ,sortField:'I_Q_B_QUOTE_DATE'}//交易日期
            ,{field:'quoteRoleTypeStr', title: 'CfetsHisDealQuery001.Bond.quoteRoleType', width:100 ,sortField:'I_Q_B_QUOTE_ROLE_TYPE'}//本方角色
            ,{field:'psStr', title: 'CfetsHisDealQuery001.Bond.ps', width:100 ,sortField:'I_Q_B_PS' ,render : function(data, type, row, meta) {
                    if (data == ps_pay_name){
                        return '<a style="color:red;">' + data + '</a>';
                    }else if (data == ps_sell_name){
                        return '<a style="color:green;">' + data + '</a>';
                    }
                }
             }//本方方向
            ,{field:'finprodMarketId', title: 'CfetsHisDealQuery001.Bond.finprodMarketId', width:120 ,sortField:'I_Q_B_FINPROD_MARKET_ID'}//债券代码
            ,{field:'finprodAbbr', title: 'CfetsHisDealQuery001.Bond.finprodAbbr', width:120 ,sortField:'EXT_FINPROD_ABBR'}//债券简称
            ,{field:'quoteTypeStr', title: 'CfetsHisDealQuery001.Bond.quoteType', width:100 ,sortField:'I_Q_B_QUOTE_TYPE'}//报价类型
            ,{field:'tradeStatusStr', title: 'CfetsHisDealQuery001.Bond.status', width:100 ,sortField:'I_Q_B_TRADE_STATUS'}//交易状态
            ,{field:'sendStatusStr', title: 'CfetsHisDealQuery001.Bond.sendStatus', width:100 ,sortField:'I_Q_B_SEND_STATUS'}//发送状态
            ,{field:'partyAbbr', title: 'CfetsHisDealQuery001.Bond.ouPartyId', width:120 ,sortField:'I_Q_B_OU_PARTY_ID'}//本方
            ,{field:'cpPartyName', title: 'CfetsHisDealQuery001.Bond.cpParty', width:120 ,sortField:'I_Q_B_D_CP_PARTY_ID'
            	/*render : function(data, type, row, meta) {
            	if (row.ibuQuoteBondDetList!=null&&row.ibuQuoteBondDetList[0].cpPartyName != null ){
                		return row.ibuQuoteBondDetList[0].cpPartyName;
                	}else{ return "";}
                	}*/
            }//对手方
            ,{field:'cpTraderName', title: 'CfetsHisDealQuery001.Bond.cpTrader', width:120 ,sortField:'I_Q_B_D_CP_TRADER'
            	/*render : function(data, type, row, meta) {
            	if (row.ibuQuoteBondDet!=null&&row.ibuQuoteBondDet.cpTraderName != null ){
                		return row.ibuQuoteBondDet.cpTraderName;
                	}else{ return "";}
            	}*/
            }//对手方交易员
            ,{field:'shareAmtHun', title: 'CfetsHisDealQuery001.Bond.shareAmt', type:'number', digit : 2, width:120, sortField:'I_Q_B_D_SHARE_AMT'
                /*,
                render : function(data, type, row, meta) {
                    if (row.ibuQuoteBondDet.shareAmt != null ){
                        return Number(row.ibuQuoteBondDet.shareAmt/100.0).toFixed(4);
                    }else{ return "";}
                }*/
            }//回复量
            ,{field:'settleSpeedStr', title: 'CfetsHisDealQuery001.Bond.settleSpeed', width:100 ,sortField:'I_Q_B_SETTLE_SPEED'}//清算速度
            ,{field:'ibuQuoteBondDetNnitCprice', title: 'CfetsHisDealQuery001.Bond.unitPrice', digit : 4, type:"number", width:120, sortField:'I_Q_B_D_UNIT_CPRICE'
                /*,
            	render : function(data, type, row, meta) {
                	if (row.ibuQuoteBondDet.unitCprice != null ){
                    		return Number(row.ibuQuoteBondDet.unitCprice).toFixed(4);
                    	}else{ return "";}
                	}*/
            }//净价
            ,{field:'ibuQuoteBondDetYield', title: 'CfetsHisDealQuery001.Bond.yield', digit : 4, type:'number', width:120, sortField:'I_Q_B_D_YIELD'
                /*,
            	render : function(data, type, row, meta) {
                	if (row.ibuQuoteBondDet.yield != null ){
                    		return Number(row.ibuQuoteBondDet.yield * 100.0).toFixed(4);
                    	}else{ return "";}
                	}*/
            }//回购利率 modified by JIAOXUJIN on 2020-06-09
            ,{field:'shareAmtExt', title: 'CfetsHisDealQuery001.Bond.iqbShareAmt', digit : 2, type:"number", width:120, sortField:'I_Q_B_SHARE_AMT'
                /*,
            	render : function(data, type, row, meta) {
                	if (row.shareAmt != null ){
                    		return Number(row.shareAmt/100.0).toFixed(4);
                    	}else{ return "";}
                	}*/
            }//券面
            ,{field:'type6Str', title: 'CfetsHisDealQuery001.Bond.assetTypeOne', width:100 ,sortField:'EXT_TYPE6'}//债券代码
            ,{field:'cfetsQuoteId', title: 'CfetsHisDealQuery001.Bond.cfetsQuoteId',width:170 ,sortField:'I_Q_B_CFETS_QUOTE_ID'}//报价编号
            ,{field:'execid', title: 'CfetsHisDealQuery001.Bond.fTradeId',width:170 ,sortable:true, sortField:'I_Q_B_D_EXECID'
            	/*render : function(data, type, row, meta) {
                	if (row.ibuQuoteBondDet!=null&&row.ibuQuoteBondDet.execid != null ){
                    		return row.ibuQuoteBondDet.execid;
                    	}else{ return "";}
                	}*/
            }//成交编号
            ,{field:'portfolioId', title: 'CfetsHisDealQuery001.Bond.portfolioId',width:120 ,sortField:'I_Q_B_PORTFOLIO_ID'}//组合代码
            ,{field:'entrustId', title: 'CfetsHisDealQuery001.Bond.entrustId',width:170 ,render : function(data, type, row, meta) {
                if(row.entrustId){
                    return "<a  class='pro-link entrustIdLink' style='cursor: pointer;'>"+row.entrustId+"</ a>";
                }else{
                    return '';
                }
            },sortField:'I_Q_B_ENTRUST_ID'}//委托代码
        ],
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            var quoteType = data.quoteType;
            var quoteRoleType = data.quoteRoleType;
            var isDzh = data.isDzh;
            var label = "CfetsBondDealQuotedPrice002"; var url = "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init";
            //对话报价
            if (quoteType == '01'){
                //本方角色：询价方
                if (quoteRoleType == "01"||quoteRoleType == "02"||quoteRoleType == "03"){
                    if(isDzh =='Y'){
                        label = "CfetsBondDealQuotedPrice004";
                        url = "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init";
                    }
                }
            }
            //请求报价
            else{
                //本方角色：回复方
                if (quoteRoleType == "02"||quoteRoleType == "03"){
                    if(isDzh =='Y'){
                        label = "CfetsBondDealQuotedPrice005";
                        url = "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init";
                    }else{
                        label = "CfetsBondDealQuotedPrice003";
                        url = "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init";
                    }
                }
                //本方角色：做市方
                else if (quoteRoleType == "04"){
                    if(isDzh =='Y'){
                        label = "CfetsBondDealQuotedPrice004";
                        url = "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init";
                    }
                }
            }
            //对话报价
            if (quoteType == '01'){
            	//本方角色：询价方
            	if (quoteRoleType == "01"){
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            	//本方角色：回复方
            	else if (quoteRoleType == "02"){
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            	//本方角色：非做市方
            	else{
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            }
            //请求报价
            else{
            	//本方角色：回复方
            	if (quoteRoleType == "02"){
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'response'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            	//本方角色：非做市方
            	else if (quoteRoleType == "03"){
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new', isRevert : 'Y'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            	//本方角色：做市方
            	else if (quoteRoleType == "04"){
            		Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + url, "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                            }, function(){
                                // 刷新当日报价
								   // dayOfferTable这个换成你现券报价查询的tableId
                            	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
                    });
            	}
            }
        }       
    });
    
    /**
     * 组合代码链接
     */
    dvpTransferTable.bind('click','.entrustIdLink', function(row) {
    	row.pageModel=PAGE_MODEL_REFERENCE;
        Joyin.dialog(JoyinUtil.getLabel("EtrInvestBondInfo002.refrence"),
                JoyinUtil.getRealPath("invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/init"),
                "98%", "95%", row,
                function(){dvpTransferTable.reload();});
    });
    
    //质押式回购历史报价查询
    var transferTable=$("#transferTable").joyintable({
        ajax: {
            url: 'queryPledgeRepoPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getTransferSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'quoteDate', title: 'CfetsHisDealQuery001.Repo.quoteDate', width:90, type:"date" ,sortField:'I_Q_C_QUOTE_DATE'}
            ,{field:'quoteRoleTypeStr', title: 'CfetsHisDealQuery001.Repo.quoteRoleType', width:90,sortField:'I_Q_C_QUOTE_ROLE_TYPE'}
            ,{field:'repoPsStr', title: 'CfetsHisDealQuery001.Repo.ps', width:90,sortField:'I_Q_C_REPO_PS'}
            ,{field:'quoteTypeStr', title: 'CfetsHisDealQuery001.Repo.quoteType', width:90 ,sortField:'I_Q_C_QUOTE_TYPE'}
            ,{field:'tradeStatusStr', title: 'CfetsHisDealQuery001.Repo.status', width:90 ,sortField:'I_Q_C_TRADE_STATUS'}
            ,{field:'sendStatusStr', title: 'CfetsHisDealQuery001.Repo.sendStatus', width:90 ,sortField:'I_Q_C_SEND_STATUS'}
            ,{field:'ouPartyName', title: 'CfetsHisDealQuery001.Repo.ouPartyId', width:120 ,sortField:'I_Q_C_OU_PARTY_ID'}
            ,{field:'cpPartyName', title: 'CfetsHisDealQuery001.Repo.cpPartyId', width:120 ,sortField:'I_Q_C_CP_PARTY_ID'}
            ,{field:'cpTraderName', title: 'CfetsHisDealQuery001.Repo.cpTrader', width:120 ,sortField:'I_Q_C_CP_TRADER_ID'}
            ,{field:'rate', title: 'CfetsHisDealQuery001.Repo.rate', width:120, digit:4, type:'number',sortField:'I_Q_C_RATE'}
            ,{field:'shareAmt', title: 'CfetsHisDealQuery001.Repo.shareAmt', width:120, type:'number', sortField:'I_Q_C_SHARE_AMT'}
            ,{field:'vSettleDate', title: 'CfetsHisDealQuery001.Repo.vSettleDate', width:90, type:"date" ,sortField:'I_Q_C_V_SETTLE_DATE'}
            ,{field:'mSettleDate', title: 'CfetsHisDealQuery001.Repo.mSettleDate', width:90, type:"date" ,sortField:'I_Q_C_M_SETTLE_DATE'}
            ,{field:'term', title: 'CfetsHisDealQuery001.Repo.term', type:'number', digit:0, width:120 ,sortField:'I_Q_C_TERM'}
            ,{field:'settleSpeedStr', title: 'CfetsHisDealQuery001.Repo.settleSpeed', width:90 ,sortField:'I_Q_C_SETTLE_SPEED'}
            ,{field:'cfetsQuoteId', title: 'CfetsHisDealQuery001.Repo.cfetsQuoteId',width:155 ,sortField:'I_Q_C_CFETS_QUOTE_ID'}
            ,{field:'execid', title: 'CfetsHisDealQuery001.Repo.fTradeId',width:120, sortField:'I_Q_C_EXECID'}
            ,{field:'portfolioId', title: 'CfetsHisDealQuery001.Repo.portfolioId',width:120 ,sortField:'I_Q_C_PORTFOLIO_ID'}
            ,{field:'entrustId', title: 'CfetsHisDealQuery001.Repo.entrustId',width:170 ,render : function(data, type, row, meta) {
                if(row.entrustId){
                    return "<a  class='pro-link entrustIdLink' style='cursor: pointer;'>"+row.entrustId+"</ a>";
                }else{
                    return '';
                }
            },sortField:'I_Q_C_ENTRUST_ID'}//委托代码
        ],
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;

            if(data.isDzh == 'Y'){
                Joyin.dialog(JoyinUtil.getLabel("质押式回购历史报价明细"),
                    JoyinUtil.getRealPath("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init"),
                    "95%", "95%", data,function(){transferTable.reload();});
            }else{
                Joyin.dialog(JoyinUtil.getLabel("质押式回购历史报价明细"),
                        JoyinUtil.getRealPath("/invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init"),
                        "95%", "95%", data,function(){transferTable.reload();});
            }
        },
    });
    
    /**
     * 组合代码链接
     */
    transferTable.bind('click','.entrustIdLink', function(row) {
    	row.pageModel=PAGE_MODEL_REFERENCE;
        Joyin.dialog(JoyinUtil.getLabel("EtrRepo002.refrence"),
                JoyinUtil.getRealPath("invest/entrust/etrrepo/EtrRepo002/init"),
                "98%", "95%", row,
                function(){transferTable.reload();});
    });


    /**
     * 导出
     */
    //现券买卖历史报价查询
    var dvpExportExcel = function() {
        var paramObj = getDvpTransferSearchParams();//获取参数
        paramObj.tableId='dvpTransferTable';
        Joyin.exportExcel('export', paramObj);//导出
    };
    //质押式回购历史报价查询
    var exportExcel = function() {
        var paramObj = getTransferSearchParams();//获取参数
        paramObj.tableId='transferTable';
        Joyin.exportExcel('export', paramObj);//导出
    };

    /**
     * 画面加载完成
     */
    $(function() {

        /** 绑定查询按钮 **/
        //现券买卖历史报价查询
        $('#CfetsHisDealQuery001_btn_bondSearch').click(function() {
        	dvpTransferTable.table ? dvpTransferTable.reload() : dvpTransferTable.load();
        });
        //质押式回购历史报价查询
        $('#CfetsHisDealQuery001_btn_repoSearch').click(function() {
            transferTable.table ? transferTable.reload() : transferTable.load();
        });


        /** 绑定导出按钮 **/
        $('#CfetsHisDealQuery001_btn_export').click(function() {
        	 //现券买卖历史报价查询 
        	dvpExportExcel();//导出
        });
        
        $('.substitutionSearchDiv #CfetsHisDealQuery001_btn_export').click(function() {
        	//质押式回购历史报价查询
        	exportExcel();
        });
        
        /*$('#CfetsHisDealQuery001_btn_repoExport').click(function() {
            exportExcel();//导出
        });
*/

        /** 绑定撤销按钮 **/
        //现券买卖历史报价查询
        $('#CfetsHisDealQuery001_btn_bondRevoke').click(function() {
        	var selArr;
        	if(dvpTransferTable !=null && dvpTransferTable !=''){
                selArr = dvpTransferTable.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr && selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            var object = selArr[0];
            object.flag = 'bond';
            object.operatorType = 'revoke';//走流程后被改变了，之前只有这一个走流程，没关系，现在多个操作走流程，判断不了
            object.optType = 'revoke';
            Joyin.ajaxJson(basePath + 'invest/bankuplink/basic/cfetshisdealquery/CfetsHisDealQuery001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("CFETSHIS_COMMON_REVOKE"), function(){
                	if (object.quoteRoleTypeStr == quoteRoleTypeFZS){
                		WorkFlow.doDeleteList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/bondRevoke', object, 
                    			"CfetsBondDealQuotedPrice003_btn_bondRevoke","CfetsBondDealQuotedPrice003");
                	}else{
                		WorkFlow.doDeleteList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/bondRevoke', object, 
                    			"CfetsBondDealQuotedPrice002_btn_bondRevoke","CfetsBondDealQuotedPrice002");
                	}
                });
            });
        });

        //质押式回购历史报价查询
        $('#CfetsHisDealQuery001_btn_repoRevoke').click(function() {
        	var selArr;
        	if(transferTable !=null && transferTable !=''){
                selArr = transferTable.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr && selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            var object = selArr[0];
            object.flag = 'repo';
            object.operatorType = 'revoke';
            Joyin.ajaxJson(basePath + 'invest/bankuplink/basic/cfetshisdealquery/CfetsHisDealQuery001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("CFETSHIS_COMMON_REVOKE"), function(){
                	WorkFlow.doDeleteList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/repoRevoke', object, 
                			"CfetsRepoDealQuotedPrice002_btn_repoRevoke","CfetsRepoDealQuotedPrice002");
                });
            });
        });

    });

}());