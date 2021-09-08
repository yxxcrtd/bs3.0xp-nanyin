(function(){

    /**
     * 获取查询参数
     */
    var genEntrustOrderParam = function() {
        var pageParam = $.serializeToObject('#cfetsBondDealQuotedPrice001_entrustOrder_form');
        if(pageParam.entrustDate){
			pageParam.entrustDateStart=$("#entrustDate").startDate();
			pageParam.entrustDateEnd=$("#entrustDate").endDate();
		}
		if(pageParam.portfolioId){
			pageParam.portfolioIdList=pageParam.portfolioId.join(",");
		}
		if(pageParam.finprodId){
			var dataList =$("#finprodId").select2("data");
			var list =[];
			$.each(dataList,function(index,val){
				list.push(val.object.finprodId);
			})
			pageParam.finprodIdList=list.join(",");
		}
		if(pageParam.invManagerId){
			pageParam.invManagerIdList=pageParam.invManagerId.join(",");
		}
        var cols = [];
        $.each(entrustOrderTable.options.cols,function(i,val){
        	if(val.visible==false){
        		
        	}else{
        		cols.push(val.field);
        	}
        })
        pageParam.keycols = cols.join(",");
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var entrustOrderTable=$("#CfetsBond001_entrust_table").joyintable({
        ajax: {
            url: 'queryEntrustOrderList',
            type: 'POST',
            data: function() {
                return genEntrustOrderParam();
            }
        },
        // height:140,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'entrustStatusStr', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.orderStatus', sortField:"entrustStatus"}
           ,{field:'transactioncom', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.finishPercent',render : function(data, type, row, meta){
	               return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text' style='color:black;'>"+row.transactioncom+"%</span></div></div>";
	           }, width:'8%',sortable:false}
           ,{field:'tradeFinishPercent', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.tradeFinishPercent',render : function(data, type, row, meta){
               return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.tradeFinishPercent+"%'><span class='layui-progress-text' style='color:black;'>"+row.tradeFinishPercent+"%</span></div></div>";
           }, width:'8%',sortable:false}
           ,{field:'invManagerName', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.invManagerId'}
           ,{field:'portfolioId', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.portfolioId',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                }
            }
           ,{field:'portfolioName', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.portfolioName'}
           ,{field:'secAcctName', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.secAcctId'}
           ,{field:'finprodMarketId', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.assetId'}
           ,{field:'finprodAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.assetAbbr'}
           ,{field:'settleSpreedStr', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.settleSpeed', sortField:"settleSpreed"}
           ,{field:'psStr', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.entrustPs', sortField:"ps"}
           ,{field:'entrustNum', width:160,type:'number', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.entrustQuotaWY'}
           ,{field:'finishAmt', width:160,type:'number', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.comQuotaWY'}
           ,{field:'avaNumStr', width:160,type:'number', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.avaQuotaWY'}
           ,{field:'priceStr', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.cleanPrice',digit:4}
           ,{field:'matureYieldStr', width:100, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.yield'}
           ,{field:'counterName', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.dealCounter'}
           ,{field:'vdate', width:80,type:'date', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.entrustStartDate'}
           ,{field:'mdate', width:80,type:'date', title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.entrustEndDate'}
           ,{field:'entrustId', width:190, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.entrustId'}
           ,{field:'quoteType', visible: false}
           ,{field:'ps', visible: false}
           ,{field:'entrustStatus', visible: false}
           ,{
               field : 'entrustStatus',
               title : '操作',
               fixed: 'right',
               width:75,
               sortable: false,
               render : function(data, type, row, meta) {
                   if (data == entrustStatusUnAccept){
                	   return '<a class="CfetsBondDealQuotedPrice001_entrust_accept layui-btn " style="color:white;line-height: 21px;padding-left: 7px;width: 42px;">接收</a>';
                   }else if (data == entrustStatusAccept && row.transactioncom == 0 && row.finishAmt == 0){
                	   return '<a class="CfetsBondDealQuotedPrice001_entrust_giveup layui-btn " style="color:white;background-color: #ff5809;line-height: 21px;padding-left: 7px;width: 42px;">放弃</a>';
                   }else{
                	   return '';
                   }
               }
           }
        ],
        done:function(allData){
        	// // 设置刷新时间
        	// $('#CfetsBondDealQuotedPrice001_entrustOrder_refresh').text(new Date());
        	//
        	// layui.layer.close(fetsBondDealQuotedPrice001_entrustOrderTable_loading);
        },
        click: function (table, rowData) {
        	// fetsBondDealQuotedPrice001_entrust_select = rowData.entrustId;
        	// /*选中委托加载指令报价信息*/
    		// orderOfferTable.table ? orderOfferTable.reload() : orderOfferTable.load();
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrInvestBondInfo002.refrence"),
                    JoyinUtil.getRealPath("invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
    });

    /**
     * 组合代码链接
     */
    entrustOrderTable.bind('click','.portfolioIdLink', function(row) {
        // 在父页面全屏展示
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath
            + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init", "98%", "95%",
            {pageModel:PAGE_MODEL_REFERENCE, portfolioId:row.portfolioId});
    });

    /**
     * 接收
     */
    entrustOrderTable.bind('click','.CfetsBondDealQuotedPrice001_entrust_accept', function(row) {
    	//调用接收委托业务组件
    	Joyin.ajax(JoyinUtil.getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/receiveEntrust'), {entrustIds:row.entrustId},function(result) {
    		//成功后刷新table
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    });
    /**
     * 放弃
     */
    entrustOrderTable.bind('click','.CfetsBondDealQuotedPrice001_entrust_giveup', function(row) {
    	//调用接收委托业务组件
    	Joyin.ajax(JoyinUtil.getRealPath('invest/bankuplink/common/CfetsQuotedCommon/entrustGiveUpHandle'), {entrustId:row.entrustId},function(result) {
    		//成功后刷新table
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    });
    /**
     * 导出
     */
    var exportExcel = function(exportType){
    	var object = genEntrustOrderParam();
    	if(exportType=="dayOffer"){
            object = genDayOfferParam();
        }
        if(exportType=="CSTPSelect"){
            object = getCstpParams();
        }
    	object.exportFlag = true;
        object.exportType = exportType;
        Joyin.exportExcel('export', object);
    };
    
    
    /**
     * 获取查询参数
     */
    var genDayOfferParam = function() {
        var pageParam = $.serializeToObject('#cfetsBondDealQuotedPrice001_dayOffer_form');
		
		if(pageParam.finprodId){
			var dataList =$("#finprodId").select2("data");
			var list =[];
			$.each(dataList,function(index,val){
				list.push(val.object.finprodId);
			})
			pageParam.finprodIdList=pageParam.finprodId.join(",");
		}
        var cols = [];
        $.each(dayOfferTable.options.cols,function(i,val){
        	if(val.visible==false){
        		
        	}else{
        		cols.push(val.field);
        	}
        })
        cols.push("quoteId");
        pageParam.keycols = cols.join(",");
        
        return pageParam;
    };
    /**
     * 委托查询
     */
    var dayOfferTable = $("#CfetsBond001_dayOffer_table").joyintable({
        ajax: {
            url: 'getDayOfferTableList',
            type: 'POST',
            data: function() {
                return genDayOfferParam();
            }
        },
        //height:380,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'validStatusStr', width:90, title: '有效状态', sortField:"validStatus"} 
           ,{field:'quoteRoleTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.myRole', sortField:"quoteRoleType"}
           ,{field:'psStr', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.myPs', sortField:"ps",render : function(data, type, row, meta) {
	               if (data == ps_pay_name){
	            	   return '<a style="color:red;">' + data + '</a>';
	               }else if (data == ps_sell_name){
	            	   return '<a style="color:green;">' + data + '</a>';
	               }
           		}
           }
           ,{field:'finprodMarketId', width:100, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.assetId'}
           ,{field:'finprodAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.assetAbbr'}
           ,{field:'quoteTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.offerType', sortField:"quoteType"}
           ,{field:'tradeStatusStr', width:100, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.dealStatus', sortField:"tradeStatus"}
           ,{field:'sendStatusStr', width:140, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.sendStatus', sortField:"sendStatus", render : function(data, type, row, meta) {
	               if (row.validStatus == '01' && data == sendStatus_fail){
	            	   return "<a class='CfetsBondDealQuotedPrice001_dayOffer_fail layui-btn' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>失败</a>"
	                    + "<a class='CfetsBondDealQuotedPrice001_dayOffer_reback layui-btn' style='color:white;background-color: orange;line-height: 21px;padding-left: 7px;width: 42px'>回退</a>";
	               }
	               // add by zhangliang 20200622 对于新增报价需要考虑超时的场景(新增中、已发送)
	               else if (row.validStatus == '01' && data == sendStatus_send && row.tradeStatus == quoteStatus_added){
	            	   return "<a class='CfetsBondDealQuotedPrice001_dayOffer_fail layui-btn' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>超时</a>"
	                    + "<a class='CfetsBondDealQuotedPrice001_dayOffer_f5 layui-btn' style='color:white;background-color: green;line-height: 21px;padding-left: 7px;width: 42px'>刷新</a>";
	               }
	               else {
	            	   return data;
	               }
	       		}
           }
           ,{field:'partyAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.my'}
           ,{field:'ouTraderName', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.myTrader'}
           ,{field:'cpPartyName', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.dealCounter'}
           ,{field:'cpTraderName', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.dealCounterId'}
           ,{field:'reqEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.reqEff'}
           ,{field:'resEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.resEff'}
           ,{field:'shareAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.resAmont', sortField:"shareAmt"}
           ,{field:'settleSpeedStr', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.settleSpeed', sortField:"settleSpeed"}
           ,{field:'unitCprice',type:'number', width:125, digit : 4, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.cleanPrice'}
           ,{field:'yield',type:'number', width:120, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.matuYield', digit:4}
           ,{field:'prinAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.bondPosition'}
           ,{field:'assetTypeOneStr', width:90, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.bondType', sortField:"assetTypeOne"}
           ,{field:'cfetsQuoteId', width:200, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.offerId'}
           ,{field:'fTradeId', width:150, title: 'CfetsBondDealQuotedPrice001.dayOffer.table.dealId'}
           ,{field:'quoteId', visible: false}
           ,{
               field : 'answerFlag',
               title : 'CfetsBondDealQuotedPrice001.dayOffer.table.operation',
               fixed: 'right',
               width:75,
               sortable: false,
               render : function(data, type, row, meta) {
                   if (row.validStatus == '01' && data == '01'){
                	   return '<a class="CfetsBondDealQuotedPrice001_dayOffer_answer layui-btn" style="color:white;line-height: 21px;padding-left: 7px;width: 42px;">回复</a>';
                   }
	               else if (row.validStatus == '01' && row.sendStatusStr == sendStatus_unsend 
	                       && (row.tradeStatusStr == '' || row.tradeStatusStr == null || row.tradeStatusStr == quoteStatus_added)){
	            	   return "<a class='CfetsBondDealQuotedPrice001_dayOffer_delete layui-btn' style='color:white;background-color: orange;line-height: 21px;padding-left: 7px;width: 42px'>删除</a>";
	               }
	               else 
                   {
                	   return '';
                   }
               }
           }
        ],
        done:function(allData){
        },
        dblclick:function(table, data) {// 1:对话报价编辑模式,2:请求报价编辑模式,3:对话报价查看模式,4:对话报价回复查看模式,5:请求报价回复模式,6:对话报价做市回复查看模式
            var openMode;
            if (data.sendStatusStr == sendStatus_unsend){
                if(data.quoteType == quoteTypeDialog){
                    openMode = '1';
                }
                else if(data.quoteType == quoteTypeRequest){
                    openMode = '2';
                }
            }
            else{
                // 询价方
                if (data.quoteRoleType == quoteRoleTypeXJ){
                    openMode = '3';
                }
                // 回复方
                else if (data.quoteRoleType == quoteRoleTypeHF){
                    openMode = '4';
                }
                // 非做市方
                else if (data.quoteRoleType == quoteRoleTypeFZS){
                    openMode = '5';
                }
                // 做市方
                else if (data.quoteRoleType == quoteRoleTypeZS){
                    openMode = '6';
                }
            }

            if (openMode == '1'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                            pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                            pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
            }
            else if (openMode == '2'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice005"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/init", "99%", "99%", {
                            pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        });
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/init", "99%", "99%", {
                            pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        });
                }

            }
            else if (openMode == '3'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }

            }
            else if (openMode == '4'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }

            }
            else if (openMode == '5'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice005"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'response', isRevert : 'Y'
                            ,quoteRoleType : data.quoteRoleType,
                            sendStatus : !data.sendStatus ? '' : data.sendStatus,
                            tradeStatus : !data.tradeStatus ? '' : data.tradeStatus,
                            cpPartyId : data.cpPartyId,openMode:openMode
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        });
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'response', isRevert : 'Y'
                            ,quoteRoleType : data.quoteRoleType,
                            sendStatus : !data.sendStatus ? '' : data.sendStatus,
                            tradeStatus : !data.tradeStatus ? '' : data.tradeStatus,
                            cpPartyId : data.cpPartyId,openMode:openMode
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        });
                }
            }
            else if (openMode == '6'){
                if(data.isDzh=='Y'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004.marketQuote"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            // 刷新做市报价
                            marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                        })
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            // 刷新做市报价
                            marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                        })
                }

            }
        }
    });
    
    /**
     * 回复
     */
    dayOfferTable.bind('click','.CfetsBondDealQuotedPrice001_dayOffer_answer', function(row) {
        var isDzh = row.isDzh;
    	row.operationType = 'answer';
        // 判断选择记录的状态
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
        	if (result.data){
        		if (row.quoteType === quoteTypeDialog){
        		    //多组合
                    if(isDzh == 'Y' || row.quoteRoleType == '02'){
                        Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                                pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'dialogueResponse'
                            }, function(){
                                // 刷新当日报价
                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            })
                    }else{
                        Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                                pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'dialogueResponse'
                                }, function(){
                                // 刷新当日报价
                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                    }
        		}else if (row.quoteType === quoteTypeRequest){
					//多组合
                    if(isDzh == 'Y' || row.quoteRoleType == '02'){
						Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
	                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/init", "99%", "99%", {
	                            pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'response', isRevert : 'Y'
	                                ,quoteRoleType : row.quoteRoleType,
	                                sendStatus : !row.sendStatus ? '' : row.sendStatus,
	                    			tradeStatus : !row.tradeStatus ? '' : row.tradeStatus
	                            }, function(){
	                                // 刷新当日报价
	                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
	                    });
					}else{
						Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
	                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/init", "99%", "99%", {
	                            pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'response', isRevert : 'Y'
	                                ,quoteRoleType : row.quoteRoleType,
	                                sendStatus : !row.sendStatus ? '' : row.sendStatus,
	                    			tradeStatus : !row.tradeStatus ? '' : row.tradeStatus
	                            }, function(){
	                                // 刷新当日报价
	                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
	                    });
					}
        		}
        	}
        });
    });
    /**
     * 失败
     */
    dayOfferTable.bind('click','.CfetsBondDealQuotedPrice001_dayOffer_fail', function(row) {
        // 判断选择记录的状态 
        Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/selectErrlog', {quoteId : row.quoteId}, function(result) {
        	if (result.data){
        		Joyin.alert(result.data,2);
        	}
        });
    });
    /**
     * 回退
     */
    dayOfferTable.bind('click','.CfetsBondDealQuotedPrice001_dayOffer_reback', function(row) {
    	row.operationType = 'doBack';
        // 判断选择记录的状态 
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
        	if (result.data){
        		Joyin.confirm(result.data, function() {
        			Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/doBack', row, function(result){
        				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                	})
        		})
        	}
        });
    });
    /**
     * 删除
     */
    dayOfferTable.bind('click','.CfetsBondDealQuotedPrice001_dayOffer_delete', function(row) {
    	row.operationType = 'doDel';
        // 判断选择记录的状态 
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
        	if (result.data){
        		Joyin.confirm(result.data, function() {
        			Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/doDel', row, function(result){
        				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                	})
        		})
        	}
        });
    });
    /**
     * 刷新
     */
    dayOfferTable.bind('click','.CfetsBondDealQuotedPrice001_dayOffer_f5', function(row) {
    	row.operationType = 'reFresh';
        // 判断选择记录的状态
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/timeOutQuoteRefreshBond', row, function(result){
				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        	})
        });
    });
    
   var getCstpParams = function() {
       var pageParam = $.serializeToObject('#searchFormCstp');
       return pageParam;
   }
    
    /**
     * 定义数据表格
     */
    var tableBondCstp = $("#CfetsBond001_cstp_table").joyintable({
        ajax: {
            url: 'gencjcxTable',
            type: 'POST',
            data: function() {
                return getCstpParams();
            }
        },
        searchOnInit: true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols: [
            {field: 'portfolioId', title: 'CSTPSelect001.portfolioId', width: '150'}
            , {field: 'portfolioName', title: 'CSTPSelect001.portfolioName', width: '100', visible: false}
            , {field: 'execid', title: 'CSTPSelect001.execid', width: '200'}
            , {field: 'securityid', title: '代码', width: '150'}
            , {field: 'symbol', title: '名称', width: '150'}
            , {field: 'psStr', title: '本方方向', sortField: 'side', width: '100'}
            , {field: 'counterinstitutioncode', title: '对手方', width: '100'}
            , {field: 'cpPartyName', title: '对手方机构', width: '100'}
            , {field: 'counterTraderName', title: '对手方交易员', width: '100'}
            , {field: 'ourinstitutioncode', title: '本方', width: '100'}
            , {field: 'ourTraderName', title: '本方交易员', width: '100'}
            , {field: 'lastqty', title: '券面总计（万元）', width: '100', type: 'money', digit: 2}
            , {field: 'price', title: '净价', width: '100', type: 'money', digit: 4}
            , {field: 'dirtyPrice', title: '全价', width: '100', type: 'money', digit: 4}
            , {field: 'underlyingyield2', title: '到期收益率(%)', width: '100', type: 'money', digit: 4}
            , {field: 'strikeyield', title: '行权收益率(%)', width: '100', type: 'money', digit: 4}
            , {field: 'tradecashamt', title: 'CSTPSelect001.tradecashamt', width: '170', type: 'money', digit: 2}
            , {field: 'settlcurramt', title: '结算金额', width: '170', type: 'money', digit: 2}
            , {field: 'accruedinterestamt', title: '应计利息', width: '170', type: 'money', digit: 2}
            , {field: 'accruedinteresttotalamt', title: '应计利息总额', width: '170', type: 'money', digit: 2}
            // , {
            //     field: 'settldate',
            //     title: '首次结算日',
            //     sortField: 'settldate',
            //     width: '110',
            //     type: 'date'
            // },
            // , {field: 'settleSpeedStr', title: '清算速度', width: '150'}
            // , {field: 'settTypeStr', title: '清算类型', sortField: 'settType', width: '110'}
            // , {field: 'deliveryTypeStr', title: '结算方式', sortField: 'deliveryType', width: '110'}
            // , {field: 'tradeDateStr', title: 'CSTPSelect001.tradedate1', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '报价类型', sortField: 'tradedate', width: '110'}// TODO
            // , {field: 'reference', title: '报价/订单编号', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '债券类型', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '债券币种', sortField: 'tradedate', width: '110'}
            // , {field: 'ourcapitalbankname', width: 130, title: '本方资金开户行'}
            // , {field: 'ourcapitalaccountname', width: 130, title: '本方资金账户户名'}
            // , {field: 'ourcapitalaccountnumber', width: 130, title: '本方资金账号'}
            // , {field: 'ourpaymentsystemcode', width: 130, title: '本方支付系统行号'}
            // , {field: 'countercapitalaccountnumber', width: 130, title: '对手方资金账号'}
            // , {field: 'counterpaymentsystemcode', width: 130, title: '对手方支付系统行号'}
            // , {field: 'counterdepositaccountname', width: 130, title: '对手方托管账户户名'}
            // , {field: 'counterdepositinstitution', width: 130, title: '对手方托管账户'}
            // , {field: 'counterdepositaccountnumber', width: 130, title: '对手方托管机构'}
            // // , {field: 'quoteTypeStr', title: '结算状态', sortField: 'tradedate', width: '110'}
            // , {field: 'ourinstitutionchineseshortname', title: '本方代理机构', width: '100'}
            // , {field: 'institutionchineseshortname', title: '对手方代理机构', width: '100'}
            // , {field: 'principal', title: '每百元本金额', width: '170', type: 'money', digit: 4}
            // , {field: 'totalprincipal', title: '本金额', width: '170', type: 'money', digit: 2}
            // // , {field: 'quoteTypeStr', title: '净额清算状态', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '代理方交易员', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '作市类型', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '渠道', sortField: 'tradedate', width: '110'}
            // // , {field: 'quoteTypeStr', title: '状态', sortField: 'tradedate', width: '110'}
            // , {field: 'tradeMethod', title: '交易方式', width: '110'}
            // , {field: 'matchStatusStr', title: 'CSTPSelect001.matchStatus', sortField: 'matchStatus', width: '100'}
        ]
        // ajax: {
        //     url: 'getCstpQryTableList',
        //     type: 'POST',
        //     data: function() {
        //     	return getCstpParams();
        //     }
        // },
        // height:150,
        // searchOnInit:true,
        // page : false,
        // loadonce: true,
        // sortfont: true,
        // cols:[
        //     {field:'portfolioId', title: 'CSTPSelect001.portfolioId',width:'150'}
        //     ,{field:'portfolioName', title: 'CSTPSelect001.portfolioName',width:'100',visible:false}
        //     ,{field:'execid', title: 'CSTPSelect001.execid',width:'200'}
        //     ,{field:'acctNo', title: 'CSTPSelect001.secAccount',width:'150'}
        //     ,{field:'businesstypeStr', title: 'CSTPSelect001.finprodType',sortField:'businesstype',width:'120'}
        //     ,{field:'securityid', title: 'CSTPSelect001.finprodId',width:'150'}
        //     ,{field:'symbol',title: 'CSTPSelect001.finprodName',width:'150'}
        //     ,{field:'psStr', title: 'CSTPSelect001.ps',sortField:'side',width:'100'}
        //     ,{field:'lastqtyStr', title: '券面总额（万元）',width:'150'}
        //     ,{field:'priceStr', title: 'CSTPSelect001.price',width:'110'}
        //     ,{field:'dirtyprice', title: 'CSTPSelect001.dirtyprice',width:'115'}
        //     ,{field:'underlyingyield2', title: 'CSTPSelect001.underlyingyield2',width:'150',type:'money',digit:4}
        //     ,{field:'tradeDateStr', title: 'CSTPSelect001.tradedate1',sortField:'tradedate',width:'110'}
        //     ,{field:'settleDate', title: 'CSTPSelect001.settleDate',sortField:'settledate',width:'110',type:'date'}
        //     ,{field:'accruedinterestamtStr',  title: 'CSTPSelect001.accruedinterestamtPer',width:'165',}
        //     ,{field:'accruedinteresttotalamt',  title: 'CSTPSelect001.accruedinteresttotalamt',width:'185',type:'money'}
        //     // modify by etliu 20210319 银行间上下行多组合 start
        //     ,{field:'tradecashamt', title: '净价总额（元）',width:'160',type:'money'}
        //     ,{field:'settlcurramt', title: '全价总额（元）',width:'160',type:'money'}
        //     // modify by etliu 20210319 银行间上下行多组合 end
        //    /* ,{field:'settlcurramt2', title: 'CSTPSelect001.settlcurramt2',width:'155',type:'money'}
        //     ,{field:'tradelimitdaysStr', title: 'CSTPSelect001.tradelimitdays',width:'110'}
        //     ,{field:'cashholdingdays', title: 'CSTPSelect001.cashholdingdays',width:'150'}*/
        //     ,{field:'matchStatusStr', title: 'CSTPSelect001.matchStatus',sortField:'matchStatus',width:'100'}
        // ]
    });
    
    
    $(function () {

        var table = layui.table;
    	 // 绑定查询按钮
        $('#CSTPSelect001_btn_search').click(function() {
        	tableBondCstp.table == null ? tableBondCstp.load() : tableBondCstp.reload();//加载表格
        });
        // 绑定导出按钮
        $('#CSTPSelect001_btn_exportExcelByColumns').click(function() {
        	 var paramObj = getCstpParams();//获取参数
        	 paramObj.exportType = 'cstp';
             Joyin.exportExcel('export', paramObj);//导出
        });
        
        $('#CSTPSelect001_btn_export').click(function() {
	       	 var paramObj = getCstpParams();//获取参数
	       	 paramObj.exportType = 'cstp';
	         Joyin.exportExcel('export', paramObj);//导出
       });
        // 绑定重置按钮
        $('#CSTPSelect001_btn_reset').click(function() {
//        	location.reload();
        	$("#keywords-cstp").val("").trigger('change');
        	$("#tradeDate").val("").trigger('change');
        	$("#matchStatus").val("").trigger('change');
        	// add by etliu 20210322 银行间上下行多组合 start
            $("#cstpPs").val("").trigger('change');
            $("#cstpPortfolioId").val("").trigger('change');
            // add by etliu 20210322 银行间上下行多组合 end
        	tableBondCstp.clear();
        });
        /**
    	 * 成交回报放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice001_cstp_search').click(function(){
    	    tableBondCstp.table == null ? tableBondCstp.load() : tableBondCstp.reload();//加载表格
        });

        /**
         * 当日报价查询弹出框
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_btn_seach').click(function() {
            $('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
            $("#CSTPselectorConditionFilter001").hide();
            if($("#CSTPselectorConditionFilter001").css("display")=="none"){
                //clearBasicCondition();
                $("#CSTPselectorConditionFilter001").show();
            }
            else{
                //clearBasicCondition();
                $("#CSTPselectorConditionFilter001").hide();
            }
        });

        /**
         * 当日报价查询弹出框查询
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_btn_realseach').click(function() {
            // fetsBondDealQuotedPrice001_CSTPSelect_loading = layui.layer.open({type:3});
            tableBondCstp.table ? tableBondCstp.reload() : tableBondCstp.load();
            $("#CSTPselectorConditionFilter001").hide();
        });

        /**
         * 当日报价查询弹出框关闭
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_btn_seachClose').click(function() {
            $("#CSTPselectorConditionFilter001").hide();
        });

        /**
         * 当日报价查询弹出框重置
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_btn_seachReset').click(function() {
            $("#quoteType").empty();
            $("#ps").empty();
            $("#finprodId").empty();
            $("#quaryDate").val("");
        });

        /**
         * 当日报价手动刷新
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_secah').click(function() {
            // fetsBondDealQuotedPrice001_CSTPSelect_loading = layui.layer.open({type:3});
            tableBondCstp.table ? tableBondCstp.reload() : tableBondCstp.load();
        });

        /**
         * 委托指令导出
         */
        $('#CfetsBondDealQuotedPrice001_CSTPSelect_btn_export').click(function() {
            exportExcel("CSTPSelect");
        });


        /**
         * 委托指令查询弹出框关闭
         */
        $('#CfetsBondDealQuotedPrice001_entrustOrder_btn_seachClose').click(function() {
            $("#selectorConditionFilter001").hide();
        });

    	/**
    	 * 委托指令放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_search').click(function(){
    	    // fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 委托指令radio点击事件
    	 */
    	$("[name=entrustStatus]").click(function(){
    	    // fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 委托指令导出
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_export').click(function() {
            exportExcel("EntrustOrder");
        });
    	
    	/**
    	 * 委托指令查询弹出框
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_seach').click(function() {
            $('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
            $('#dayOfferSelectorConditionFilter001').hide();
    		if($("#selectorConditionFilter001").css("display")=="none"){
				//clearBasicCondition();
				$("#selectorConditionFilter001").show();
			}
			else{
				//clearBasicCondition();
				$("#selectorConditionFilter001").hide();
			}
        });
    	
    	
    	/**
    	 * 委托指令查询弹出框查询
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_realseach').click(function() {
    		// fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
    		$("#selectorConditionFilter001").hide();
        });

		/**
    	 * 委托指令查询弹出框重置
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_seachReset').click(function() {
			$("#portfolioId").empty();
			$("#finprodId").empty();
			$("#ps").val('').trigger("change");
			$("#settleSpreed").val('').trigger("change");
			$("#invManagerId").empty();
			$("#entrustId").empty();
			$("#entrustDate").val("");
        });


        $('#CfetsBondDealQuotedPrice001_dayOffer_btn_export').click(function() {
            exportExcel("dayOffer");
        });


		/**
    	 * 当日报价查询弹出框
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_btn_seach').click(function() {
            $('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
            $("#selectorConditionFilter001").hide();
    		if($("#dayOfferSelectorConditionFilter001").css("display")=="none"){
				//clearBasicCondition();
				$("#dayOfferSelectorConditionFilter001").show();
			}
			else{
				//clearBasicCondition();
				$("#dayOfferSelectorConditionFilter001").hide();
			}
        });

		/**	
    	 * 当日报价查询弹出框查询
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_btn_realseach').click(function() {
    		// fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
    		$("#dayOfferSelectorConditionFilter001").hide();
        });

		/**
    	 * 当日报价查询弹出框关闭
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_btn_seachClose').click(function() {
			$("#dayOfferSelectorConditionFilter001").hide();
        });

		/**
    	 * 当日报价查询弹出框重置
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_btn_seachReset').click(function() {
    		$(".psEmpty").val("").trigger("change");
			$(".finprodIdEmpty").empty();
			$("#mySideTrader").empty();
			$("#counterId").empty();
			$("#counterTrader").empty();
			$("#quoteId").val("");
            $("#quoteType").val("");
        });

		/**
    	 * 当日报价手动刷新
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_secah').click(function() {
    		// fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });

    	/**
    	 * 委托指令查询弹出框关闭
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_seachClose').click(function() {
			$("#selectorConditionFilter001").hide();
        });
    	
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_exportExcelByColumns').click(function() {
            exportExcel("EntrustOrder");
        });

    	// 导入初始化 Added by JIAOXUJIN on 2020-05-26
    	var uploadQuoteFile = function(options, uploadCallback, callback) {
            var uploadType = 'drag';
            var opt = {type:uploadType, hideTpl:false, auto: false, height: '580px', width: '1280px', pageId: pageId};
            if($.isFunction(options)) {
                if($.isFunction(uploadCallback)){
                    callback = uploadCallback;
                }
                uploadCallback = options;
            }
            if(!$.isFunction(options) && _.isObject(options)){
                opt = $.extend({}, opt, options);
            }
            window.uploadCallback = uploadCallback || function(){};
            Joyin.dialog(JoyinUtil.getLabel('CfetsBondDealQuotedPrice001.fileUpload.title'), 
            		JoyinUtil.getRealPath('invest/bankuplink/bond/CfetsBondDealQuotedPrice001/framework/fileUpload/init'), opt.width, opt.height, opt, function(){
            	entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                // 刷新当日报价
                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
            });
        };

        // 真正导入数据 Added by JIAOXUJIN on 2020-05-26
        var quoteImport = function (dataRows) {

    		Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/importFile', dataRows, 
			function(result) {//AjaxResult
                if(!result || !($.isArray(result.joyinMessages) && result.joyinMessages.length > 0)) {
                	layui.layer.closeAll();
                	entrustOrderTbl.reload();
                	dayOfferTbl.reload();
                }
                else if ($.isArray(result.joyinMessages) && result.joyinMessages.length > 0) {
                	var errorMsg = "";
                	$.each(result.joyinMessages, function(i, joyinMessage){
                		errorMsg += joyinMessage.message + "<br />"
                	});
                	Joyin.error(errorMsg);
                }
        	});
        };

        // 验证并设置当前的预览数据中有无错误消息 Added by JIAOXUJIN on 2020-05-26
        var validatePreviewResult = function (previewDtoList) {
        	// 有错误信息则不能提交；预警信息可以继续提交
        	window.CfetsBondDealQuotedPrice001.hasError = false;

        	$.each(previewDtoList, function(i, previewDto){
        		if (previewDto.validationErrorMsg && previewDto.validationErrorMsg.length > 0) {
        			window.CfetsBondDealQuotedPrice001.hasError = true;
        			return false; //break;
        		}
        	});
        	
        	return window.CfetsBondDealQuotedPrice001.hasError;
        };

        // 导入预览 Added by JIAOXUJIN on 2020-05-26
    	$('#CfetsBondDealQuotedPrice001_quote_import').click(function() {
    		uploadQuoteFile(function(fileName) {
                Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/importCheck',
                    {fileName : fileName}, function(previewDtoList) {
                    	validatePreviewResult(previewDtoList);
                    });
                });
        });

    	// 暴露给子界面调用 Added by JIAOXUJIN on 2020-05-26
    	window.CfetsBondDealQuotedPrice001 = {
			hasError: true,
			quoteImport: quoteImport,
			validatePreviewResult: validatePreviewResult
    	};

    	/**
    	 * 当日报价放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_search').click(function(){
    	    // fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });
    	/**
    	 * 当日报价radio点击事件
    	 */
    	$("[name=dayOfferStatus]").click(function(){
//          fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });

    	/**
    	 * 撤回
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_recall').click(function(){
    		if(dayOfferTable!=null && dayOfferTable!=''){
                var selArr = dayOfferTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_001"));
                    return;
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                	var data =  {};
                	
                	$.each(selArr,function(i,val){ //两个参数，第一个参数表示遍历的数组的下标，第二个参数表示下标对应的值
                		val.operationType = 'recall';
                	});
                	data.bondQuoteInfoDtoList = selArr;
                	data.operationType = 'recall';
                	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllowList', data, function(result) {
                    	if (result.data){
                    		Joyin.confirm(result.data, function() {
                    			// Modified by JIAOXUJIN on 2020-06-02 object --> data
                    			Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/doReCallList', data, function(result){
                    				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            	})
                    		})
                    	}
                    });
                }else{
                	var data = selArr[0];
                    data.operationType = 'recall';
                    // 判断选择记录的状态 
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', data, function(result) {
                    	if (result.data){
                    		Joyin.confirm(result.data, function() {
                    			// Modified by JIAOXUJIN on 2020-06-02 object --> data
                    			Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/doReCall', data, function(result){
                    				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            	})
                    		})
                    	}
                    });
                }
                
            }
        });
    	
    	
    	/**
    	 * 复制
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_copy').click(function(){
    		if(dayOfferTable!=null && dayOfferTable!=''){
                var selArr = dayOfferTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_001"));
                    return;
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                
                var data = selArr[0];
                data.operationType = 'copy';
                var isDzh = data.isDzh;
                // 判断选择记录的状态 
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', data, function(result) {
                	// 对话报价复制
                	if (data.quoteType === quoteTypeDialog){
                	    //多组合
                        if(isDzh == 'Y' || data.quoteRoleType == '02'){
                            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                                    pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
                                }, function(){
                                    // 刷新当日报价
                                    dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                })
                        }else{
                            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                                    pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
                                    }, function(){
                                        // 刷新当日报价
                                    dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            })
                        }
                	}
                	// 请求报价复制
                	else if (data.quoteType === quoteTypeRequest){
						//多组合
                        if(isDzh == 'Y' || data.quoteRoleType == '02'){
							Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice005"), 
	                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/init", "99%", "99%", {
	                                pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
	                                }, function(){
	                                    // 刷新当日报价
	                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
	                        });
						}else{
							Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
	                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/init", "99%", "99%", {
	                                pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
	                                }, function(){
	                                    // 刷新当日报价
	                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
	                        });
						}
                		
                	}
                });
            }
        });
    	
    	/**
    	 * 修改
    	 */
    	$('#cfetsRepoDealQuotedPrice001_dayOffer_edit').click(function(){
    		if(dayOfferTable!=null && dayOfferTable!=''){
                var selArr = dayOfferTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_001"));
                    return;
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                
                var row = selArr[0];
                row.operationType = 'edit';
                var isDzh = row.isDzh;
                // 判断选择记录的状态
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
                	if (result.statuCode == 200){
                		if (row.quoteType === quoteTypeDialog){
                            //多组合
                            if(isDzh == 'Y' || row.quoteRoleType == '02'){
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'edit'
                                    }, function(){
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    })
                            }else{
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'edit'
                                        }, function(){
                                            // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                })
                            }
                		}
                	}
                });
            }
        });

    	var element = layui.element;
    	
    	// 委托新建完成需要WS推送
        SocketClient.subscribe('/cfets/bankup/api', function(result) {
			console.log("socket接收消息啦啦啦:" + JSON.stringify(result));
    	});
    	
    	/**
         * 监视用户下线操作
         */
        SocketClient.connect(function() {
            let entrustOrderTbl = entrustOrderTable;
            let dayOfferTbl = dayOfferTable;
            let marketOfferTbl;
            
            // 订阅现卷报价导入时的发送完成消息 Added by JIAOXUJIN on 2020-05-26
            SocketClient.subscribe('/bondQuoteImport/sendFinish', function(result) {
                var message = $.parseJSON(result.responseMessage);
                var sendCount = message.sendCount;

                let entrustOrderTbl2 = entrustOrderTbl;
                let dayOfferTbl2 = dayOfferTbl;

                // 等一下，防止一闪而过，被“文件上传成功”窗口覆盖掉
                setTimeout(
                    function(){
                        // 全部发送完成后，推送消息到界面，告知发送完成，关闭导入预览界面
                        Joyin.confirm(JoyinUtil.getMessage("QUOTE_UPLOAD_030", sendCount.toString()), function() {
                            layui.layer.closeAll();
                            entrustOrderTbl2.reload();
                            dayOfferTbl2.reload();
                        });
                    }, 3000);
            }, 'user');

            // 订阅网关发送的报价新增消息(包含对话报价和请求报价，以广播的形式推给所有用户，并且将数据 展示到第一行)
            SocketClient.subscribe('/' + bondTopic + '/newBondDialogueQuoteAll', function(result) {
                // 刷新当日报价
                if (result.responseMessage){
                    result = JSON.parse(result.responseMessage);
                    var dataArr = dayOfferTbl.getAllRows();
                    dataArr.unshift(result);
                    dayOfferTbl.reload({data : dataArr});
                }
            });
            SocketClient.subscribe('/' + bondTopic + '/newBondDialogueQuoteAll', function(result) {
            	// 刷新当日报价
                dayOfferTbl.table ? dayOfferTbl.reload() : dayOfferTbl.load();
            });
            
            // 订阅网关发送的报价状态更新消息（包含对话报价和请求报价，已单用户的形式推给创建人，表格刷新）
            SocketClient.subscribe('/' + bondTopic + '/refreshDayOfferTableLoad', function(result) {
                // 刷新当日报价
                dayOfferTbl.table ? dayOfferTbl.reload() : dayOfferTbl.load();
                // 刷新委托指令
                entrustOrderTbl.table ? entrustOrderTbl.reload() : entrustOrderTbl.load();
            }, 'user');
            
            // // 订阅网关发送的报价新增消息(包含对话报价和请求报价，以广播的形式推给所有用户，并且将数据 展示到第一行)
            // SocketClient.subscribe('/' + bondTopic + '/newBondMarketOfferQuoteAll', function(result) {
            //     // 刷新做市报价
            //     if (result.responseMessage){
            //         result = JSON.parse(result.responseMessage);
            //         var dataArr = marketOfferTable.getAllRows();
            //         dataArr.unshift(result);
            //         marketOfferTbl.reload({data : dataArr});
            //     }
            // });
            // modify by zhangliang 20200727 MQ 推送有问题，前台会显示重复数据
            SocketClient.subscribe('/' + bondTopic + '/newBondMarketOfferQuoteAll', function(result) {
            	// 刷新做市报价
                marketOfferTbl.table ? marketOfferTbl.reload() : marketOfferTbl.load();
            });
            
            // 订阅网关发送的报价状态更新消息（包含对话报价和请求报价，已单用户的形式推给创建人，表格刷新）
            SocketClient.subscribe('/' + bondTopic + '/refreshMarketOfferTableLoad', function(result) {
                // 刷新做市报价
                marketOfferTbl.table ? marketOfferTbl.reload() : marketOfferTbl.load();
                // 刷新委托指令
                entrustOrderTbl.table ? entrustOrderTbl.reload() : entrustOrderTbl.load();
            });

            // 委托新建完成需要WS推送
            SocketClient.subscribe('/CfetsBondDealQuotedPrice001/refreshEntrustOrderTable', function(result) {
                // 刷新委托指令
                entrustOrderTbl.table ? entrustOrderTbl.reload() : entrustOrderTbl.load();
            });
            
        });
        /**
         * 批量接收
         */
        $('#cfetsBondDealQuotedPrice001_accept').click(function() {
        	var rowArr = entrustOrderTable.getAllCheckedRows();// 所有选中行数据
            var roleidArray = new Array("");
            for(var i = 0;i<rowArr.length;i++){
                roleidArray.push(rowArr[i].entrustId);
            }
            roleidArray = roleidArray.join( "," ).substring( 1 );
            if(rowArr == null || rowArr.length == 0){
                layer.msg("请选择至少1条记录进行接收。");
            }else{
            	//调用接收委托业务组件
            	Joyin.ajax(JoyinUtil.getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/receiveEntrust'), {entrustIds:roleidArray},function(result) {
            		//成功后刷新table
            		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                });
            }
        });

        /**
         * 批量放弃
         */
        $('#cfetsBondDealQuotedPrice001_giveup').click(function() {
        	var rowArr = entrustOrderTable.getAllCheckedRows();// 所有选中行数据
            var roleidArray = new Array("");
            for(var i = 0;i<rowArr.length;i++){
//                if(rowArr[i].tradeFinishPercent!=0&&rowArr[i].entrustStatus=='03'){
//                    Joyin.error("已接收且完成度为0指令,才能执行放弃操作！");
//                    return;
//                }
                roleidArray.push(rowArr[i].entrustId);
            }
            roleidArray = roleidArray.join( "," ).substring( 1 );
            if(rowArr == null || rowArr.length == 0){
                layer.msg("请选择至少1条记录进行放弃。");
            }else{
            	//调用接收委托业务组件
            	Joyin.ajax(JoyinUtil.getRealPath('invest/bankuplink/common/CfetsQuotedCommon/entrustGiveUpHandleList'), {entrustIds:roleidArray},function(result) {
            		//成功后刷新table
            		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                });
            }
        });
        
        $('#keywords-entrustOrder').keydown(function(event) {
            if (event.keyCode == "13") {
                event.preventDefault();
                //回车执行查询  
                $('#CfetsBondDealQuotedPrice001_entrustOrder_search').click();
            }
        });

        $('#keywords-dayOffer').keydown(function(event) {
            if (event.keyCode == "13") {
                event.preventDefault();
                //回车执行查询  
                $('#CfetsBondDealQuotedPrice001_dayOffer_search').click();
            }
        });
        
        $('#keywords-cstp').keydown(function(event) {
            if (event.keyCode == "13") {
                event.preventDefault();
                //回车执行查询  
                $('#CfetsBondDealQuotedPrice001_cstp_search').click();
            }
        });
    });

    parent.CfetsBondDealQuotedPriceMainQuery ={
        entrustOrderTable : entrustOrderTable
    }
}());