(function(){
	
	 var fetsBondDealQuotedPrice001_entrust_select = "";
	 
	 var fetsBondDealQuotedPrice001_entrustOrderTable_loading;
	 var fetsBondDealQuotedPrice001_dayOfferTable_loading;
	 var fetsBondDealQuotedPrice001_marketOfferTable_loading;
	 var tabIndex = 0;
	
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
        height:180,
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
           ,{field:'portfolioId', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.portfolioId'}
           ,{field:'portfolioName', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.portfolioName'}
           /*,{field:'secAcctName', width:120, title: 'CfetsBondDealQuotedPrice001.entrustOrder.table.secAcctId'}*/
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
        	// 设置刷新时间
        	$('#CfetsBondDealQuotedPrice001_entrustOrder_refresh').text(new Date());
        	
        	layui.layer.close(fetsBondDealQuotedPrice001_entrustOrderTable_loading);
        },
        click: function (table, rowData) {
        	fetsBondDealQuotedPrice001_entrust_select = rowData.entrustId;
        	/*选中委托加载指令报价信息*/
    		orderOfferTable.table ? orderOfferTable.reload() : orderOfferTable.load();
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
    var exportExcel = function(){
    	var object = genEntrustOrderParam();
    	object.exportFlag = true;
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
        /*if (pageParam.mySideTrader && pageParam.mySideTrader != '') {
            pageParam.mySideTrader = pageParam.mySideTrader.join(",");
        } else {
            pageParam.mySideTrader = null;
        }*/
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
     * 当日报价
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
        	// 设置刷新时间
        	$('#CfetsBondDealQuotedPrice001_dayOffer_refresh').text(new Date());
        	
        	layui.layer.close(fetsBondDealQuotedPrice001_dayOfferTable_loading);
        },
        dblclick:function(table, data) {
        	// 1:对话报价编辑模式,2:请求报价编辑模式,3:对话报价查看模式,4:对话报价回复查看模式,5:请求报价回复模式,6:对话报价做市回复查看模式
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
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                            }, function(){
                                // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    })
        		}else{
        			Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
                        pageModel : data.validStatus == "04" ? PAGE_MODEL_REFERENCE : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', statusValid : data.validStatus
                        }, function(){
                            // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                	});
				}else{
					Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
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
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                            }, function(){
                                // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    })
        		}else{
        			Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                            }, function(){
                                // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    })
        		}else{
        			Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
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
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
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
                    		basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                                pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse' 
                            }, function(){
                            	// 刷新当日报价
                            	dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            	// 刷新做市报价
                            	marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                            })
        		}else{
        			Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"), 
                    		basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
     * 流程中查看模式
     */
    
    
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
                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                                pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'dialogueResponse'
                            }, function(){
                                // 刷新当日报价
                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            })
                    }else{
                        Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
	                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
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
	                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
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
    
    
    /**
     * 指令报价
     */
    var orderOfferTable = $("#CfetsBond001_orderOffer_table").joyintable({
        ajax: {
            url: 'getOrderOfferTableList',
            type: 'POST',
            data: function() {
                return {entrustId : fetsBondDealQuotedPrice001_entrust_select};
            }
        },
        //height:420,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
          	{field:'fTradeId', width:150, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.dealId'}
           ,{field:'validStatusStr', width:90, title: '有效状态', sortField:"validStatus"} 
           ,{field:'quoteRoleTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.myRole', sortField:"quoteRoleType"}
           ,{field:'psStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.myPs', sortField:"ps"}
           ,{field:'finprodMarketId', width:100, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.assetId'}
           ,{field:'finprodAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.assetAbbr'}
           ,{field:'quoteTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.offerType', sortField:"quoteType"}
           ,{field:'tradeStatusStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.dealStatus', sortField:"tradeStatus"}
           ,{field:'sendStatusStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.sendStatus', sortField:"sendStatus"}
           ,{field:'partyAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.my'}
           ,{field:'ouTraderName', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.myTrader'}
           ,{field:'cpPartyName', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.dealCounter'}
           ,{field:'cpTraderName', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.dealCounterId'}
           ,{field:'reqEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.reqEff'}
           ,{field:'resEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.resEff'}
           ,{field:'shareAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.resAmont', sortField:"shareAmt"}
           ,{field:'settleSpeedStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.settleSpeed', sortField:"settleSpeed"}
           ,{field:'unitCprice',type:'number', width:125, digit : 4, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.cleanPrice'}
           ,{field:'yield',type:'number', width:120, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.matuYield', digit:4}
           ,{field:'prinAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.bondPosition', sortField:"prinAmt"}
           ,{field:'assetTypeOneStr', width:90, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.bondType', sortField:"assetTypeOne"}
           ,{field:'cfetsQuoteId', width:155, title: 'CfetsBondDealQuotedPrice001.orderOffer.table.offerId'}
           
        ]
        ,dblclick :function (table, data){
            // 1:对话报价编辑模式,2:请求报价编辑模式,3:对话报价查看模式,4:对话报价回复查看模式,5:请求报价回复模式,6:对话报价做市回复查看模式
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
            // modify by etliu 20210329 银行间上下行多组合 start
            if (data.isDzh && data.isDzh == 'Y') {
                // 多组合报价
                if (openMode == '1'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '2'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '3'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '4'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '5'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'response', isRevert : 'Y'
                            ,quoteRoleType : data.quoteRoleType,
                            sendStatus : !data.sendStatus ? '' : data.sendStatus,
                            tradeStatus : !data.tradeStatus ? '' : data.tradeStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '6'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            // 刷新做市报价
                            marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                        })
                }
            }
            else {
                // 单组合报价
                if (openMode == '1'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '2'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '3'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'new'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '4'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'dialogueResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '5'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'response', isRevert : 'Y'
                            ,quoteRoleType : data.quoteRoleType,
                            sendStatus : !data.sendStatus ? '' : data.sendStatus,
                            tradeStatus : !data.tradeStatus ? '' : data.tradeStatus
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                }
                else if (openMode == '6'){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"),
                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                            pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse'
                        }, function(){
                            // 刷新当日报价
                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                            // 刷新做市报价
                            marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                        })
                }
            }
            // modify by etliu 20210329 银行间上下行多组合 end
        }
    });
    
    
    /**
     * 获取查询参数
     */
    var genMarketOfferParam = function() {
        return $.serializeToObject('#cfetsBondDealQuotedPrice001_marketOffer_form');
    };
    /**
     * 做市报价
     */
    var marketOfferTable = $("#CfetsBond001_marketOffer_table").joyintable({
        ajax: {
            url: 'getMarketOfferTableList',
            type: 'POST',
            data: function() {
                return genMarketOfferParam();
            }
        },
        //height:380,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'quoteRoleTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.myRole', sortField:"quoteRoleType"}
            ,{field:'psStr', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.myPs', sortField:"ps",render : function(data, type, row, meta) {
	               if (data == ps_pay_name){
	            	   return '<a style="color:red;">' + data + '</a>';
	               }else if (data == ps_sell_name){
	            	   return '<a style="color:green;">' + data + '</a>';
	               }
        		}
            }
            ,{field:'finprodMarketId', width:100, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.assetId'}
            ,{field:'finprodAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.assetAbbr'}
            ,{field:'quoteTypeStr', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.offerType', sortField:"quoteType"}
            ,{field:'tradeStatusStr', width:100, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.dealStatus', sortField:"tradeStatus"}
            ,{field:'partyAbbr', width:120, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.my'}
            ,{field:'cpPartyName', width:120, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.dealCounter'}
            ,{field:'cpTraderName', width:120, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.dealCounterId'}
            ,{field:'reqEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.reqEff'}
            ,{field:'resEffTime', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.resEff'}
            ,{field:'shareAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.resAmont', sortField:"shareAmt"}
            ,{field:'prinAmtHun',type:'number', width:125, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.bondPosition', sortField:"prinAmt"}
            ,{field:'settleSpeedStr', width:90, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.settleSpeed', sortField:"settleSpeed"}
            ,{field:'cfetsQuoteId', width:155, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.offerId'}
            ,{field:'ouTraderId', width:120, title: 'CfetsBondDealQuotedPrice001.marketOffer.table.myTrader'}
            ,{field:'quoteId', visible: false}
            ,{
                field : 'operation',
                title : 'CfetsBondDealQuotedPrice001.marketOffer.table.operation',
                fixed: 'right',
                width:75,
                sortable: false,
                render : function(data, type, row, meta) {
             	   return '<a class="CfetsBondDealQuotedPrice001_marketOffer_answer layui-btn " style="color:white;line-height: 21px;padding-left: 7px;width: 42px;">回复</a>';
                }
            }
        ],
        done:function(allData){
        	// 设置刷新时间
        	//$('#CfetsBondDealQuotedPrice001_marketOffer_refresh').text(new Date());
        	
        	layui.layer.close(fetsBondDealQuotedPrice001_marketOfferTable_loading);
        },
        dblclick :function (table, data){
            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"), 
                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                        pageModel : PAGE_MODEL_REFERENCE, quoteId : data.quoteId, quotaPageModel : 'marketResponse' 
                    }, function(){
                        // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        // 刷新做市报价
                        marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                    })
        }
    });
    
    /**
     * 回复
     */
    marketOfferTable.bind('click','.CfetsBondDealQuotedPrice001_marketOffer_answer', function(row) {
    	Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002.marketQuote"), 
        		basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                    pageModel : PAGE_MODEL_NEW, quoteId : row.quoteId, quotaPageModel : 'marketResponse' 
                }, function(){
                	// 刷新当日报价
                	dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                	// 刷新做市报价
                	marketOfferTable ? marketOfferTable.reload() : marketOfferTable.load();
                })
    });
    
   var getCstpParams = function() {
	   var pageParam = $.serializeToObject('#searchFormCstp');
		pageParam.finprodType = "BOND";
		pageParam.tradeDateBegin=$('#tradeDate').startDate();
 		pageParam.tradeDateEnd=$('#tradeDate').endDate();
	    if (pageParam.matchStatus && pageParam.matchStatus != '') {
	        pageParam.matchStatus = pageParam.matchStatus.join(",");
	    } else {
	        pageParam.matchStatus = null;
	    }
	    var cols = [];
        $.each(tableBondCstp.options.cols,function(i,val){
        	if(val.visible==false){
        		
        	}else{
        		cols.push(val.field);
        	}
        })
        pageParam.keycols = cols.join(",");
        
        // add by etliu 20210322 银行间上下行多组合 start
        if (pageParam.portfolioId && pageParam.portfolioId != '') {
            pageParam.portfolioId = pageParam.portfolioId.join(",");
        } else {
            pageParam.portfolioId = null;
        }
        // add by etliu 20210322 银行间上下行多组合 end
        
		return pageParam; 
   }
    
    /**
     * 定义数据表格
     */
    var tableBondCstp = $("#CfetsBond001_cstp_table").joyintable({
        ajax: {
            url: 'getCstpQryTableList',
            type: 'POST',
            data: function() {
            	return getCstpParams();
            }
        },
        //height : document.documentElement.clientHeight * 0.33,
        searchOnInit:false,
        page : false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'portfolioId', title: 'CSTPSelect001.portfolioId',width:'150'}
            ,{field:'portfolioName', title: 'CSTPSelect001.portfolioName',width:'100',visible:false}
            ,{field:'execid', title: 'CSTPSelect001.execid',width:'200'}
            ,{field:'acctNo', title: 'CSTPSelect001.secAccount',width:'150'}
            ,{field:'businesstypeStr', title: 'CSTPSelect001.finprodType',sortField:'businesstype',width:'120'}
            ,{field:'securityid', title: 'CSTPSelect001.finprodId',width:'150'}
            ,{field:'symbol',title: 'CSTPSelect001.finprodName',width:'150'}
            ,{field:'psStr', title: 'CSTPSelect001.ps',sortField:'side',width:'100'}
            ,{field:'lastqtyStr', title: '券面总额（万元）',width:'150'}
            ,{field:'priceStr', title: 'CSTPSelect001.price',width:'110'}
            ,{field:'dirtyprice', title: 'CSTPSelect001.dirtyprice',width:'115'}
            ,{field:'underlyingyield2', title: 'CSTPSelect001.underlyingyield2',width:'150',type:'money',digit:4}
            ,{field:'tradeDateStr', title: 'CSTPSelect001.tradedate1',sortField:'tradedate',width:'110'}
            ,{field:'settleDate', title: 'CSTPSelect001.settleDate',sortField:'settledate',width:'110',type:'date'}
            ,{field:'accruedinterestamtStr',  title: 'CSTPSelect001.accruedinterestamtPer',width:'165',}
            ,{field:'accruedinteresttotalamt',  title: 'CSTPSelect001.accruedinteresttotalamt',width:'185',type:'money'}
            // modify by etliu 20210319 银行间上下行多组合 start
            ,{field:'tradecashamt', title: '净价总额（元）',width:'160',type:'money'}
            ,{field:'settlcurramt', title: '全价总额（元）',width:'160',type:'money'}
            // modify by etliu 20210319 银行间上下行多组合 end
           /* ,{field:'settlcurramt2', title: 'CSTPSelect001.settlcurramt2',width:'155',type:'money'}
            ,{field:'tradelimitdaysStr', title: 'CSTPSelect001.tradelimitdays',width:'110'}
            ,{field:'cashholdingdays', title: 'CSTPSelect001.cashholdingdays',width:'150'}*/
            ,{field:'matchStatusStr', title: 'CSTPSelect001.matchStatus',sortField:'matchStatus',width:'100'}
        ]
    });
    
    
    $(function () {
    	
        var table = layui.table;
        table.on('row(CfetsBond001_entrust_table)', function(obj){
        	if(obj.data && obj.data.entrustId){
        		if(tabIndex == 1){
        			fetsBondDealQuotedPrice001_entrust_select = obj.data.entrustId;
                	/*选中委托加载指令报价信息*/
            		orderOfferTable.table ? orderOfferTable.reload() : orderOfferTable.load();
        		}
        	}
        });
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
    	 * 对话按钮绑定事件
    	 */
    	$('#cfetsBondDealQuotedPrice001_dialogueBtn').click(function() {
    		if(entrustOrderTable!=null && entrustOrderTable!=''){
                var selArr = entrustOrderTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                	if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                		/*Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_002"), function(){*/
                			Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                            		basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_NEW, quotaPageModel : 'new'
                                    }, function(){
                                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    })
                		/*});*/
                    	return;
                	}else{
                		Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_001"));
                        return;
                	}
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                	var error ="";
                	var entrustIdList = "";
                	$.each(selArr,function(index,val){
                		
                		if(index==selArr.length-1){
                			entrustIdList = entrustIdList.concat(val.entrustId);
                		}else{
                			entrustIdList = entrustIdList.concat(val.entrustId+",");
                		}
                		 if (val.entrustStatus != entrustStatusAccept){
                         	error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_002")+"指令编号为:"+val.entrustId);
                         }
                         
                         //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                         if (new Date(val.vdate) > platDate || new Date(val.mdate) < platDate){
                        	 error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_003")+"指令编号为:"+val.entrustId);
                         }
                		
                	});
                	if(error){
                		Joyin.error(error);
                		return;
                	}
                	var object = {entrustIdList:entrustIdList};
                	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice004/checkEntrustList', object, function(result) {
                        if (result.data){
//                            if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeDialog){
//                                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
//                                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
//                                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
//                                                pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
//                                            }, function(){
//                                                entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
//                                                // 刷新当日报价
//                                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
//                                            });
//                                });
//                            }else{
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"), 
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                                            pageModel : PAGE_MODEL_NEW,entrustIdList:entrustIdList, quotaPageModel : 'new'
                                        }, function(){
                                            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                            // 刷新当日报价
                                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                        });
//                            }
                        }
                    });
                	
                	return;
                }
                
                if (selArr[0].entrustStatus != entrustStatusAccept){
                	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_002"));
                    return;
                }
                
                //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                if (new Date(selArr[0].vdate) > platDate || new Date(selArr[0].mdate) < platDate){
                	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_003"));
                    return;
                }
                var object = {entrustId:selArr[0].entrustId};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                    if (result.data){
                        if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeDialog){
                            Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                                            pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
                                        }, function(){
                                            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                            // 刷新当日报价
                                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                        });
                            });
                        }else{
                            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
                                    }, function(){
                                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    });
                        }
                    }
                });
            }
        });
    	// add by wangzhuangzhuang 新增请求画面 20200518 start
        /**
         * 请求按钮绑定事件
         */
        $('#cfetsBondDealQuotedPrice001_requestBtn').click(function() {
            if(entrustOrderTable!=null && entrustOrderTable!=''){
                var selArr = entrustOrderTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                    if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                    	/*Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_002"), function(){*/
                    		Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_NEW, quotaPageModel : 'new'
                                    }, function(){
                                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    })
                    	/*});*/
                        return;
                    }else{
                        Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_004"));
                        return;
                    }
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                	var error ="";
                	var entrustIdList = "";
                	$.each(selArr,function(index,val){
                		
                		if(index==selArr.length-1){
                			entrustIdList = entrustIdList.concat(val.entrustId);
                		}else{
                			entrustIdList = entrustIdList.concat(val.entrustId+",");
                		}
                		 if (val.entrustStatus != entrustStatusAccept){
                         	error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_002")+"指令编号为:"+val.entrustId);
                         }
                         
                         //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                         if (new Date(val.vdate) > platDate || new Date(val.mdate) < platDate){
                        	 error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_003")+"指令编号为:"+val.entrustId);
                         }
                		
                	});
                	if(error){
                		Joyin.error(error);
                		return;
                	}
                	var object = {entrustIdList:entrustIdList};
                	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice004/checkEntrustList', object, function(result) {
                        if (result.data){
//                            if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeDialog){
//                                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
//                                    Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
//                                            basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
//                                                pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
//                                            }, function(){
//                                                entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
//                                                // 刷新当日报价
//                                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
//                                            });
//                                });
//                            }else{
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice005"), 
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
                                            pageModel : PAGE_MODEL_NEW,entrustIdList:entrustIdList, quotaPageModel : 'new'
                                        }, function(){
                                            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                            // 刷新当日报价
                                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                        });
//                            }
                        }
                    });
                	
                	return;
                }
                
                if (selArr[0].entrustStatus != entrustStatusAccept){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_005"));
                    return;
                }
                
                //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                if (new Date(selArr[0].vdate) > platDate || new Date(selArr[0].mdate) < platDate){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_006"));
                    return;
                }

                var object = {entrustId:selArr[0].entrustId};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                    if (result.data){
                        if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeRequest){
                            Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
                                            pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
                                        }, function(){
                                            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                            // 刷新当日报价
                                            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                        });
                            });
                        }else{
                        	Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
                                    }, function(){
                                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    });
                        }
                    }
                });
            }
        });
        // add by wangzhuangzhuang 新增请求画面 20200518 end
    	/**
    	 * 委托指令放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_search').click(function(){
    	    fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 委托指令radio点击事件
    	 */
    	$("[name=entrustStatus]").click(function(){
    	    fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 委托指令导出
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_export').click(function() {
            exportExcel();
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
    		fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
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
    		fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
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
			//$(".psEmpty").empty();
//			var dataPs= $(".psEmpty").select2();
//    		dataPs.val(dataPs.dataType).trigger("change");
    		$(".psEmpty").val("").trigger("change");
			$(".finprodIdEmpty").empty();
			$("#mySideTrader").empty();
			$("#counterId").empty();
			$("#counterTrader").empty();
			$("#quoteId").val("");
        });

		/**
    	 * 当日报价手动刷新
    	 */
    	$('#CfetsBondDealQuotedPrice001_dayOffer_secah').click(function() {
    		fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });

		/**
         * 自动刷新参数标记
         * */
        var dayOfferrefresh;
		/**
                           * 当日报价自动刷新开关控制
         * */
        $("#dayOfferRefreshSwitch").click(function () {
            if($("#dayOfferRefreshSwitch").is(':checked')){
            	var time =  parseInt($('#dayOfferRefreshTime') .val());
            	if($("#dayOfferRefreshCircle").val()=="min"){
            		time= time*60;
            	}
            	time= time*1000;
                clearInterval(dayOfferrefresh);
                dayOfferrefresh = setInterval(function(){
                	fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
            		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                }, time);
            }else{
                clearInterval(dayOfferrefresh);
            }
        });

    	
    	/**
         * 自动刷新参数标记
         * */
        var entrustOrderrefresh;
        /**
         * 自动刷新开关控制
         * */
        
        $("#entrustOrderRefreshSwitch").click(function () {
            if($("#entrustOrderRefreshSwitch").is(':checked')){
            	var time =  parseInt($('#entrustOrderRefreshTime') .val());
            	if($("#entrustOrderRefreshCircle").val()=="min"){
            		time= time*60;
            	}
            	time= time*1000;
                clearInterval(entrustOrderrefresh);
                entrustOrderrefresh = setInterval(function(){
                	fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
            		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                }, time);
            }else{
                clearInterval(entrustOrderrefresh);
            }
        });
    	/**
    	 * 委托指令查询弹出框关闭
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_seachClose').click(function() {
			$("#selectorConditionFilter001").hide();
        });
    	/**
    	 * 手动刷新
    	 */
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_secah').click(function() {
    		fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	
    	
    	$('#CfetsBondDealQuotedPrice001_entrustOrder_btn_exportExcelByColumns').click(function() {
            exportExcel();
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
        	// let entrustOrderTbl = entrustOrderTable;
        	// let dayOfferTbl = dayOfferTable;

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
    	    fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });
    	/**
    	 * 当日报价radio点击事件
    	 */
    	$("[name=dayOfferStatus]").click(function(){
    	    fetsBondDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
    		dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });
    	
    	
    	
    	/**
    	 * 当日报价放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice001_marketOffer_search').click(function(){
    	    fetsBondDealQuotedPrice001_marketOfferTable_loading = layui.layer.open({type:3});
    		marketOfferTable.table ? marketOfferTable.reload() : marketOfferTable.load();
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
//                		if(val.validStatus == '04'){
//                         	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                              return;
//                       }
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
//                    if(data.validStatus == '04'){
//                      	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                           return;
//                    }
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
//                if(data.validStatus == '04'){
//                 	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                      return;
//                }
                data.operationType = 'copy';
                var isDzh = data.isDzh;
                // 判断选择记录的状态 
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', data, function(result) {
                	// 对话报价复制
                	if (data.quoteType === quoteTypeDialog){
                	    //多组合
                        if(isDzh == 'Y' || data.quoteRoleType == '02'){
                            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                                    pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
                                }, function(){
                                    // 刷新当日报价
                                    dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                })
                        }else{
                            Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
	                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice005/init", "99%", "99%", {
	                                pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
	                                }, function(){
	                                    // 刷新当日报价
	                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
	                        });
						}else{
							Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice003"), 
	                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice003/init", "99%", "99%", {
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
//                if(row.validStatus == '04'){
//                 	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                      return;
//                }
                // 发送成功 已新增或已修改 才可做更改
//                if(!(row.sendStatus == '02' && (row.tradeStatus == '02' || row.tradeStatus == '04'))){
//                	Joyin.error(JoyinUtil.getMessage("当前交易状态、发送状态不允许做修改操作！"));
//                    return;
//                }
                /*// 判断选择记录的状态 
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', data, function(result) {
                	// 对话报价复制
                	if (data.quoteType === quoteTypeDialog){
                		Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"), 
                                basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
                                pageModel : PAGE_MODEL_NEW, quoteId : data.quoteId, quotaPageModel : 'new', openModel : 'copy'
                                }, function(){
                                    // 刷新当日报价
                                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                        })
                	}
                });*/
                row.operationType = 'edit';
                var isDzh = row.isDzh;
                // 判断选择记录的状态
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
                	if (result.statuCode == 200){
                		if (row.quoteType === quoteTypeDialog){
                            //多组合
                            if(isDzh == 'Y' || row.quoteRoleType == '02'){
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice004"),
                                    basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice004/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_EDIT, quoteId : row.quoteId, quotaPageModel : 'edit'
                                    }, function(){
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                                    })
                            }else{
                                Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice002"),
                                        basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice002/init", "99%", "99%", {
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
    	/*element.on('tab(docDemoTabBrief)', function(data){
    		tabIndex = data.index;
    		if (data.index == 0){
    			$('#CfetsBondDealQuotedPrice001_dayOffer_refresh').toggle(true);
        		$('#CfetsBondDealQuotedPrice001_marketOffer_refresh').toggle(false);
    		}else if (data.index == 2){
    			$('#CfetsBondDealQuotedPrice001_dayOffer_refresh').toggle(false);
        		$('#CfetsBondDealQuotedPrice001_marketOffer_refresh').toggle(true);
        		tableBondCstp.reload();//加载表格
    		}else{
    			$('#CfetsBondDealQuotedPrice001_dayOffer_refresh').toggle(false);
        		$('#CfetsBondDealQuotedPrice001_marketOffer_refresh').toggle(false);
    		}
    		});*/
    	
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
            let marketOfferTbl = marketOfferTable;
            
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
            
            // 订阅网关发送的报价新增消息(包含对话报价和请求报价，以广播的形式推给所有用户，并且将数据 展示到第一行)
            SocketClient.subscribe('/' + bondTopic + '/newBondMarketOfferQuoteAll', function(result) {
                // 刷新做市报价
                if (result.responseMessage){
                    result = JSON.parse(result.responseMessage);
                    var dataArr = marketOfferTable.getAllRows();
                    dataArr.unshift(result);
                    marketOfferTbl.reload({data : dataArr});
                }
            });
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
}());