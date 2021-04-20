(function(){
    var fetsRepoDealQuotedPrice001_entrust_select = "";
    
    var fetsRepoDealQuotedPrice001_entrustOrderTable_loading;
    var fetsRepoDealQuotedPrice001_dayOfferTable_loading;

    var tabIndex=0;

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
    var genEntrustOrderParam = function() {
        var pageParam = $.serializeToObject('#cfetsRepoDealQuotedPrice001_entrustOrder_form');
        if (pageParam.invManagerId && pageParam.invManagerId != '') {
            pageParam.invManagerId = pageParam.invManagerId.join(",");
        } else {
            pageParam.invManagerId = null;
        }
		if (pageParam.portfolioId && pageParam.portfolioId != '') {
            pageParam.portfolioId = pageParam.portfolioId.join(",");
        } else {
            pageParam.portfolioId = null;
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
    var entrustOrderTable=$("#CfetsRepo001_entrust_table").joyintable({
        ajax: {
            url: 'queryEntrustOrderList',
            type: 'POST',
            data: function() {
                return genEntrustOrderParam();
            }
        },
        height:180,
        searchOnInit:true,
        loadonce : true,
        page :false,
        multiple: false,
        sortfont: true,
        cols:[
            {field:'entrustStatusStr', width:90, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.orderStatus',sortField:"entrustStatus"}
           ,{field:'transactioncom', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.finishPercent',render : function(data, type, row, meta){
	               return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
	           }, width:'8%',sortable:false}
           ,{field:'tradeFinishPercent', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.tradeFinishPercent',render : function(data, type, row, meta){
               return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.tradeFinishPercent+"%'><span class='layui-progress-text'>"+row.tradeFinishPercent+"%</span></div></div>";
           }, width:'8%',sortable:false}
           ,{field:'invManagerName', width:120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.invManagerId'}
           ,{field:'portfolioId', width:120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioId'}
           ,{field:'portfolioName', width:140, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.portfolioName'}
           ,{field:'secAcctName', width:120, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.secAcctId'}
           ,{field:'psStr', width:90, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustPs',sortField:"ps"}
           ,{field:'settleSpreedStr', width:90, title: '清算速度',sortField:"settleSpreed"}
           ,{field:'entrustNum', width:125,type:'number', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustQuota'}
           ,{field:'finishAmt', width:125,type:'number', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.comQuota'}
           ,{field:'avaNum', width:125,type:'number', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.avaQuota'}
           ,{field:'vdate', width:80,type:'date', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustStartDate'}
           ,{field:'mdate', width:80,type:'date', title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustEndDate'}
            ,{field:'term', width:150, title: '回购期限（天)'}
            ,{field:'termType', width:150, title: '期限品种'}
            ,{field:'price', width:150, title: '回购利率%'}
            ,{field:'mySide', width:150, title: '本方',visible:false}
            ,{field:'ps', width:90,visible:false}
            ,{field:'settleSpreed',visible:false}
            ,{field:'entrustStatus',visible:false}
           ,{field:'entrustId', width:170, title: 'CfetsRepoDealQuotedPrice001.entrustOrder.table.entrustId'}
           ,{
               field : 'entrustStatusForShow',
               title : 'CfetsRepoDealQuotedPrice001.entrustOrder.table.operation',
               fixed: 'right',
               width:75,
               sortable: false,
               render : function(data, type, row, meta) {
                   if (row.entrustStatus == entrustStatusUnAccept){
                	   return '<a class="CfetsRepoDealQuotedPrice001_entrust_accept layui-btn layui-btn-sm" style="color:white;line-height: 21px;padding-left: 7px;width: 42px">接收</a>';
                   }else if (row.entrustStatus == entrustStatusAccept && row.transactioncom == 0 && row.finishAmt == 0){
                	   return '<a class="CfetsRepoDealQuotedPrice001_entrust_giveup layui-btn layui-btn-sm" style="color:white;background-color: #ff5809;line-height: 21px;padding-left: 7px;width: 42px">放弃</a>';
                   }else{
                       return '';
                   }
               }
           }
        ],
        done:function(allData){
        	// 设置刷新时间
        	$('#CfetsRepoDealQuotedPrice001_entrustOrder_refresh').text(new Date());
            
            layui.layer.close(fetsRepoDealQuotedPrice001_entrustOrderTable_loading);
        },
        click: function (table, rowData) {
            fetsRepoDealQuotedPrice001_entrust_select = rowData.entrustId;
            /*选中委托加载指令报价信息*/
            orderOfferTable.table ? orderOfferTable.reload() : orderOfferTable.load();
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
             data.pageModel=PAGE_MODEL_REFERENCE;
             Joyin.dialog(JoyinUtil.getLabel("EtrRepo002.refrence"),
                     JoyinUtil.getRealPath("invest/entrust/etrrepo/EtrRepo002/init"),
                     "98%", "95%", data,
                     function(){table.reload();});
         }
    });
    
    /**
     * 接收
     */
    entrustOrderTable.bind('click','.CfetsRepoDealQuotedPrice001_entrust_accept', function(row) {
    	//调用接收委托业务组件
    	Joyin.ajax(JoyinUtil.getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/receiveEntrust'), {entrustIds:row.entrustId},function(result) {
    		//成功后刷新table
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    });
    /**
     * 放弃
     */
    entrustOrderTable.bind('click','.CfetsRepoDealQuotedPrice001_entrust_giveup', function(row) {
    	//调用接收委托业务组件
    	Joyin.ajax(JoyinUtil.getRealPath('invest/bankuplink/common/CfetsQuotedCommon/entrustGiveUpHandle'), {entrustId:row.entrustId},function(result) {
    		//成功后刷新table
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    });
    

    /**
     * 获取查询参数
     */
    var genDayOfferParam = function() {
        var pageParam = $.serializeToObject('#cfetsRepoDealQuotedPrice001_dayOffer_form');
        if (pageParam.mySideTrader && pageParam.mySideTrader != '') {
            pageParam.mySideTrader = pageParam.mySideTrader.join(",");
        } else {
            pageParam.mySideTrader = null;
        }
		if (pageParam.counterId && pageParam.counterId != '') {
            pageParam.counterId = pageParam.counterId.join(",");
        } else {
            pageParam.counterId = null;
        }
		if (pageParam.counterTrader && pageParam.counterTrader != '') {
            pageParam.counterTrader = pageParam.counterTrader.join(",");
        } else {
            pageParam.counterTrader = null;
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
    var dayOfferTable = $("#CfetsRepo001_dayOffer_table").joyintable({
        ajax: {
            url: 'getDayOfferTableList',
            type: 'POST',
            data: function() {
                return genDayOfferParam();
            }
        },
//        height:380,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'validStatusStr', width:90, title: '有效状态', sortField:"validStatus"}
            ,{field:'validStatus', width:90, title: '有效状态', sortField:"validStatus",visible:false}
            ,{field:'workflowId', width:90, title: '有效状态',visible:false}
            ,{field:'quoteRoleTypeStr', width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.myRole', sortField:"quoteRoleType"}
           ,{field:'psStr', width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.searchArea.myPs', sortField:"ps",render : function(data, type, row, meta) {
                   if (data == ps_pay_name){
                       return '<a style="color:red;">' + data + '</a>';
                   }else if (data == ps_sell_name){
                       return '<a style="color:green;">' + data + '</a>';
                   }
                }
           }
           ,{field:'quoteTypeStr', width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.offerType', sortField:"quoteType"}
           ,{field:'tradeStatusStr', width:100, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealStatus', sortField:"tradeStatus"}
            ,{field:'tradeStatus', width:100, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealStatus', sortField:"tradeStatus",visible:false}
            ,{field:'sendStatusStr', width:140, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.sendStatus', sortField:"sendStatus",
               render : function(data, type, row, meta) {
                   if (data == sendStatus_fail){
                       return "<a class='CfetsRepoDealQuotedPrice001_dayOffer_fail layui-btn layui-btn-sm' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>失败</a>"
                       +'<a class="CfetsRepoDealQuotedPrice001_dayOff_restore layui-btn layui-btn-sm" style="color:white;background-color: #ff5809;line-height: 21px;padding-left: 7px;width: 42px">回退</a>';
                   }
	               // add by zhangliang 20200622 对于新增报价需要考虑超时的场景(新增中、已发送)
	               else if (data == sendStatus_send && row.tradeStatus == quoteStatus_added){
	            	   return "<a class='CfetsRepoDealQuotedPrice001_dayOffer_fail layui-btn' style='color:white;background-color: red;line-height: 21px;padding-left: 7px;width: 42px'>超时</a>"
	                    + "<a class='CfetsRepoDealQuotedPrice001_dayOffer_f5 layui-btn' style='color:white;background-color: green;line-height: 21px;padding-left: 7px;width: 42px'>刷新</a>";
	               }else{
                       return data;
                   }
               }}
           ,{field:'ouPartyName', width:120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.my'}
           ,{field:'ouTraderName', width:120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.myTrader'}
           ,{field:'cpPartyName', width:120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.searchArea.counterId'}
           ,{field:'cpTraderName', width:120, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealCounterId'}
           ,{field:'rate', width:130, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.rate'}
           ,{field:'fpriceAmt', width:135, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.fpriceAmt',type:"money"}
           ,{field:'vSettleDate',type:"date" , width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.vSettleDate'}
           ,{field:'mSettleDate',type:"date" , width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.mSettleDate'}
           ,{field:'term', width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.term'}
           ,{field:'settleSpeedStr', width:90, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.settleSpeed', sortField:"settleSpeed"}
           ,{field:'cfetsQuoteId', width:155, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.offerId'}
           ,{field:'fTradeId', width:150, title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.dealId'}
            ,{field:'isDzh', width:150, visible:false}
            ,{field:'quoteRoleType', width:90,visible:false}
           ,{field:'operation', title: 'CfetsRepoDealQuotedPrice001.dayOffer.table.operation',width:75,
               sortable: false, fixed: 'right',
               render : function(data, type, row, meta) {
                    if (row.validStatus !='04' && row.tradeStatus == tradeStatusPending
                            && (row.quoteRoleType == quoteRoleTypeReply || row.quoteRoleType == quoteRoleTypeEnquiry)
                            && row.sendStatus != '04') {
                        return '<a class="CfetsRepoDealQuotedPrice001_dayOffer_reply layui-btn layui-btn-sm" style="color:white;line-height: 21px;padding-left: 7px;width: 42px">回复</a>';
                    }
                    //有效待发送  交易状态为空或者新增中 可删除
                    else if (row.validStatus !='04' && row.sendStatus == '00' && (row.tradeStatus==''||row.tradeStatus==null||row.tradeStatus=='01')){
                        return '<a class="CfetsRepoDealQuotedPrice001_dayOff_delete layui-btn layui-btn-sm" style="color:white;background-color: orange;line-height: 21px;padding-left: 7px;width: 42px">删除</a>';
                    }
                    else 
                    {
                 	   return '';
                    }
               }}
        ],
        done:function(allData){
            // 设置刷新时间
            $('#CfetsRepoDealQuotedPrice001_dayOffer_refresh').text(new Date());
            
            layui.layer.close(fetsRepoDealQuotedPrice001_dayOfferTable_loading);
        },
        dblclick:function(table, data) {
            // 判断交易状态
            var object = data;
            object.operation = "CfetsRepoDealQuotedPrice001.edit";
            var url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init';
            if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init';
            }
            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                + url, "99%", "99%", {
                pageModel : data.validStatus=='01'&&(data.tradeStatus==''||data.tradeStatus==null)&&data.sendStatus == unSendStatus ? PAGE_MODEL_NEW : PAGE_MODEL_REFERENCE,
                entrustId : data.entrustId,
                quoteId : data.quoteId,
                workflowStatus:data.validStatus,
                workflowId:data.workflowId,
                quotaPageModel : 'edit'  //add by zhangnian 20210116  双击进去默认做修改操作
            }, function() {
                entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                // 刷新当日报价
                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
            });
            /*Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                if (result.data){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                            + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init", "99%", "99%", {
                        pageModel : data.sendStatus == unSendStatus ? PAGE_MODEL_NEW : PAGE_MODEL_REFERENCE,
                        entrustId : data.entrustId,
                        quoteId : data.quoteId
                    }, function() {
                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                        // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    });
                }
            });*/
        }
    });
    
    /**
     * 当日报价失败信息
     */
    dayOfferTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOffer_fail', function(row) {
        // 判断选择记录的状态 
        Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/selectErrlog', {quoteId : row.quoteId}, function(result) {
            if (result.data){
                Joyin.alert(result.data,2);
            }
        });
    });
    /**
     * 当日报价回退
     */
    dayOfferTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOff_restore', function(row) {
        var object = row;
        object.operation = "CfetsRepoDealQuotedPrice001.dayOff.restore";
        object.quotaPageModel = 'restoredayOff';
        //多组合标识或者本方询价方为回复方 均跳转多组合界面
        if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
            var entrustIds = getEtrReByQuoteId(object);
            object.entrustId = entrustIds;
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
            if (result.data){
                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_002"), function() {
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/restoreDayOff', object, function(result){
                        // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    })
                })
            }
        });
    });
    
    /**
     * 当日报价删除
     */
    dayOfferTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOff_delete', function(row) {
        var object = row;
        object.operation = "CfetsRepoDealQuotedPrice001.dayOff.delete";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
            if (result.data){
                Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_003"), function() {
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/deleteDayOff', object, function(result){
                        // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    })
                })
            }
        });
    });
    
    /**
     * 当日报价回复
     */
    dayOfferTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOffer_reply', function(row) {
        var object = row;
        object.operation = "CfetsRepoDealQuotedPrice001.reply";
        //多组合标识或者本方询价方为回复方 均跳转多组合界面
        var url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init';
        if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
            var entrustIds = getEtrReByQuoteId(object);
            object.entrustId = entrustIds;
            url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init';
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
            if (result.data){
                Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                        + url, "99%", "99%", {
                    pageModel : PAGE_MODEL_EDIT,
                    entrustId : row.entrustId,
                    quoteId : row.quoteId,
                    quotaPageModel : 'dialogueResponse',
                    operation : "CfetsRepoDealQuotedPrice001.reply"
                }, function() {
                    // 刷新当日报价
                    dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                });
            }
        });
    });
    
    /**
     * 刷新
     */
    dayOfferTable.bind('click','.CfetsRepoDealQuotedPrice001_dayOffer_f5', function(row) {
    	row.operationType = 'reFresh';
        // 判断选择记录的状态 
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', row, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/timeOutQuoteRefreshRepo', row, function(result){
				dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        	})
        });
    });
    
    var orderOfferTable = $("#CfetsRepo001_orderOffer_table").joyintable({
        ajax: {
            url: 'getOrderOfferTableList',
            type: 'POST',
            data: function() {
                return {entrustId : fetsRepoDealQuotedPrice001_entrust_select};
            }
        },
//        height:380,
        searchOnInit:true,
        page : false,
        loadonce: true,
        sortfont: true,
        cols:[
              {field:'fTradeId', width:150, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.dealId'}
              ,{field:'validStatusStr', width:90, title: '有效状态', sortField:"validStatus"} 
              ,{field:'quoteRoleTypeStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.myRole', sortField:"quoteRoleType"}
              ,{field:'psStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.myPs', sortField:"ps",render : function(data, type, row, meta) {
                      if (data == ps_pay_name){
                          return '<a style="color:red;">' + data + '</a>';
                      }else if (data == ps_sell_name){
                          return '<a style="color:green;">' + data + '</a>';
                      }
                   }
              }
              ,{field:'quoteTypeStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.offerType', sortField:"quoteType"}
              ,{field:'tradeStatusStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.dealStatus', sortField:"tradeStatus"}
              ,{field:'sendStatusStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.sendStatus', sortField:"sendStatus"}
              ,{field:'ouPartyName', width:120, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.my'}
              ,{field:'ouTraderName', width:120, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.myTrader'}
              ,{field:'cpPartyName', width:120, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.counterId'}
              ,{field:'cpTraderName', width:120, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.dealCounterId'}
              ,{field:'rate', width:130, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.rate'}
              ,{field:'fpriceAmt', width:135, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.fpriceAmt',type:"money"}
              ,{field:'vSettleDate',type:"date", width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.vSettleDate'}
              ,{field:'mSettleDate',type:"date", width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.mSettleDate'}
              ,{field:'term', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.term'}
              ,{field:'settleSpeedStr', width:90, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.settleSpeed', sortField:"settleSpeed"}
              ,{field:'cfetsQuoteId', width:155, title: 'CfetsRepoDealQuotedPrice001.orderOffer.table.offerId'}
        ],
        //loadonce:true
    });
    
    var getCstpParams = function() {
 	   var pageParam = $.serializeToObject('#searchFormCstp');
 		pageParam.finprodType = "REPO";
 		pageParam.tradeDateBegin=$('#tradeDate').startDate();
 		pageParam.tradeDateEnd=$('#tradeDate').endDate();
 	    if (pageParam.matchStatus && pageParam.matchStatus != '') {
 	        pageParam.matchStatus = pageParam.matchStatus.join(",");
 	    } else {
 	        pageParam.matchStatus = null;
 	    }
 	   var cols = [];
       $.each(tableRepoCstp.options.cols,function(i,val){
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
    var tableRepoCstp = $("#CfetsRepo001_cstp_table").joyintable({
        ajax: {
            url: 'getCstpQryTableList',
            type: 'POST',
            data: function() {
                return getCstpParams();
            }
        },
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
            ,{field:'securityid', title: '期限类型',width:'120'}
            ,{field:'psStr', title: 'CSTPSelect001.ps',sortField:'side',width:'100'}
            ,{field:'lastqty', title: '质押券券面总额（万元）',width:'210',type:'money',digit:2}
            ,{field:'priceTxt', title: 'CSTPSelect001.backrate',width:'120'}
            ,{field:'tradeDateStr', title: 'CSTPSelect001.tradedate1',sortField:'tradedate',width:'110'}
            ,{field:'settleDate', title: 'CSTPSelect001.settleDate',sortField:'settledate',width:'110',type:'date'}
            ,{field:'accruedinteresttotalamt',  title: 'CSTPSelect001.accruedinteresttotalamthg',width:'165',type:'money',digit:2}
            ,{field:'tradecashamt', title: 'CSTPSelect001.tradecashamt',width:'170',type:'money'}
            ,{field:'settlcurramt2', title: 'CSTPSelect001.settlcurramt2',width:'185',type:'money'}
            ,{field:'tradelimitdaysStr', title: 'CSTPSelect001.tradelimitdays',width:'150'}
            ,{field:'cashholdingdays', title: 'CSTPSelect001.cashholdingdays',width:'200'}
            ,{field:'matchStatusStr', title: 'CSTPSelect001.matchStatus',sortField:'matchStatus',width:'100'}
			,{field:'operation', title: '操作',width:'100', sortable: false, fixed: 'right', render : function(data, type, row, meta) {
                    return "<a class='viewRepoDetai' >质押券明细</a>";
               }}
        ]
    });
    
    $(function () {
    	
//    	tableRepoCstp.load();
    	var table = layui.table;
    	table.on('row(CfetsRepo001_entrust_table)', function(obj){
        	if(obj.data && obj.data.entrustId){
        		if(tabIndex == 1){
        			fetsRepoDealQuotedPrice001_entrust_select = obj.data.entrustId;
                    /*选中委托加载指令报价信息*/
                    orderOfferTable.table ? orderOfferTable.reload() : orderOfferTable.load();
        		}
        	}
        });
    	
    	var element = layui.element;
    	element.on('tab(docDemoTabBrief)', function(data){
    		tabIndex = data.index;
    	});
    	
   	 // 绑定查询按钮
        $('#CSTPSelect001_btn_search').click(function() {
        	tableRepoCstp.table == null ? tableRepoCstp.load() : tableRepoCstp.reload();//加载表格
        });
        // 绑定导出按钮
        $('#CSTPSelect001_btn_export').click(function() {
        	 var paramObj = getCstpParams();//获取参数
			 paramObj.exportType = 'cstp';
             Joyin.exportExcel('export', paramObj);//导出
        });
        // 绑定重置按钮
        $('#CSTPSelect001_btn_reset').click(function() {
        	$("#searchFormCstp").find("#keywords-cfetsBondDay").val("").trigger('change');
        	$("#tradeDate").val("").trigger('change');
        	$("#matchStatus").val("").trigger('change');
            // add by etliu 20210322 银行间上下行多组合 start
            $("#cstpPs").val("").trigger('change');
            $("#cstpPortfolioId").val("").trigger('change');
            // add by etliu 20210322 银行间上下行多组合 end
        	tableRepoCstp.clear();
        });
        
        /**
    	 * 成交回报放大镜点击事件
    	 */
    	$('#CfetsRepoDealQuotedPrice001_cstp_search').click(function(){
    		tableRepoCstp.table == null ? tableRepoCstp.load() : tableRepoCstp.reload();//加载表格
        });
        
    	/**
    	 * 对话按钮绑定事件
    	 */
    	$('#cfetsRepoDealQuotedPrice001_dialogueBtn').click(function() {
    	    if(entrustOrderTable!=null && entrustOrderTable!=''){
                var selArr = entrustOrderTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                    if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
                    	/*Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_002"), function(){*/
                    		Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), 
                                    basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init", "99%", "99%", {
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
                //update by  zhangnian 20210225 start
                //多组合改造
                // 列表中勾选对象1条以上时
                if(selArr){
                    //单组合
                    if(selArr.length == 1){
                        var object = {entrustId:selArr[0].entrustId,operation:"CfetsRepoDealQuotedPrice001.new"};
                        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                            if (result.data){
                                Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"),
                                    basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init", "99%", "99%", {
                                        pageModel : PAGE_MODEL_NEW, entrustId : selArr[0].entrustId, quotaPageModel : 'new'
                                    }, function(){
                                        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                        // 刷新当日报价
                                        dayOfferTable.table ? dayOfferTable.reload()
                                            : dayOfferTable.load();
                                    });
                            }
                        });
                    }
                    //多组合
                    else{
                        //是否满足如下条件：同一回购期限+同一方向+同一清算速度+同一交易账户（21位码/6位码）
                        var msg="";var entrustId = "";
                        var termFlag = true;var psFlag = true; var settleSpreedFlag = true; var mysideFlag = true;
                        for(var i=0;i<selArr.length-1;i++){
                            if(termFlag&&selArr[i].term!=selArr[i+1].term){termFlag=false;msg += "回购期限&"}
                            if(psFlag&&selArr[i].ps!=selArr[i+1].ps){psFlag=false;msg += "交易方向&"}
                            if(settleSpreedFlag&&selArr[i].settleSpreed!=selArr[i+1].settleSpreed){settleSpreedFlag=false;msg += "清算速度&"}
                            if(mysideFlag&&selArr[i].mySide!=selArr[i+1].mySide){mysideFlag=false;msg += "交易账户&"}
                        }
                        if(!(termFlag&&psFlag&&settleSpreedFlag&&mysideFlag)){
                          //“所选指令不满足汇总条件，【回购期限&交易方向&清算速度&交易账户】不同
                            msg = msg.charAt(msg.length-1) == "&"?msg.substr(0,msg.length-1):msg;
                            Joyin.error("所选指令不满足汇总条件， ["+msg+"]不同");
                            return;
                        }else{
                            //进入多组合界面
                            $.each(selArr,function (i,e) {
                                entrustId += e.entrustId+",";
                            })
                            var object = {entrustId:entrustId,operation:"CfetsRepoDealQuotedPrice001.new",isDzh:"Y"};
                            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                                if (result.data){
                                    Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"),
                                        basePath + "invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init", "99%", "99%", {
                                            pageModel : PAGE_MODEL_NEW, entrustId : entrustId, quotaPageModel : 'new'
                                        }, function(){
                                            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
                                            // 刷新当日报价
                                            dayOfferTable.table ? dayOfferTable.reload()
                                                : dayOfferTable.load();
                                        });
                                }
                            });
                        }
                    }
                }
                //update by  zhangnian 20210225 end
            }
        });
    	/**
    	 * 委托指令放大镜点击事件
    	 */
    	$('#CfetsRepoDealQuotedPrice001_entrustOrder_search').click(function(){
    	    fetsRepoDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 委托指令radio点击事件
    	 */
    	$("[name=entrustStatus]").click(function(){
    	    fetsRepoDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
        /**
         * 当日报价放大镜点击事件
         */
        $('#CfetsRepoDealQuotedPrice001_dayOffer_search').click(function(){
            fetsRepoDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });
        /**
         * 当日报价radio点击事件
         */
        $("[name=dayOfferStatus]").click(function(){
            fetsRepoDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });

		/**
    	 * 委托指令导出
    	 */
    	$('#CfetsRepoDealQuotedPrice001_entrustOrder_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 当日报价撤回点击事件
         */
        $("#cfetsRepoDealQuotedPrice001_dayOffer_btn1").click(function(){
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
                		val.operation = "CfetsRepoDealQuotedPrice001.revert";
//                		if(val.validStatus == '04'){
//                         	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                              return;
//                       }
                	});
                	data.dataList = selArr;
                	Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEditList',
                			data, function(result) {
                                if (result.data) {
                                    Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_005"), function() {
                                    Joyin.ajaxJson(basePath
                                        + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/revertList',
                                        data, function(result) {
                                                dayOfferTable.table ? dayOfferTable.reload()
                                                        : dayOfferTable.load();
                                    });
                                });
                            }
                    });
                	
                }else{
                	var object = selArr[0];
//                    if(object.validStatus == '04'){
//                   	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                        return;
//                    }
                    if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                        var entrustIds = getEtrReByQuoteId(object);
                        object.entrustId = entrustIds;
                    }
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit',
                            object, function(result) {
                                if (result.data) {
                                    Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_005"), function() {
                                    Joyin.ajaxJson(basePath
                                        + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/revert',
                                        object, function(result) {
                                                dayOfferTable.table ? dayOfferTable.reload()
                                                        : dayOfferTable.load();
                                    });
                                });
                            }
                    });
                }
            }
        });
        /**
         * 当日报价复制点击事件
         */
        $("#cfetsRepoDealQuotedPrice001_dayOffer_btn2").click(function(){
            if(dayOfferTable!=null && dayOfferTable!=''){
                var selArr = dayOfferTable.getAllCheckedRows();
                
                if (!selArr || selArr.length == 0){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_004"));
                    return;
                }
                
                // 列表中勾选对象1条以上时
                if (selArr && selArr.length > 1) {
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }

                var object = selArr[0];
//                if(object.validStatus == '04'){
//                	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                     return;
//                }
                object.operation = "CfetsRepoDealQuotedPrice001.copy";
                var url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init';
                if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                    var entrustIds = getEtrReByQuoteId(object);
                    object.entrustId = entrustIds;
                    url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init';
                }
                Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object,
                    function(result) {
                        if (result.data) {
                            Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                                + url,
                                "99%", "99%", {
                                    pageModel : PAGE_MODEL_NEW,
                                    operation : "CfetsRepoDealQuotedPrice001.copy",
                                    quoteId : selArr[0].quoteId,
                                    quotaPageModel : 'new'
                                }, function() {
                                    dayOfferTable.table ? dayOfferTable.reload()
                                            : dayOfferTable.load();
                                    // 刷新当日报价
                             });
                        }
                });
            }
        });
    });
    
    /**
     * 当日报价修改点击事件
     */
    $("#cfetsRepoDealQuotedPrice001_dayOffer_btn3").click(function(){
        if(dayOfferTable!=null && dayOfferTable!=''){
            var selArr = dayOfferTable.getAllCheckedRows();
            
            if (!selArr || selArr.length == 0){
                Joyin.error(JoyinUtil.getMessage("请选择需要修改的对话报价！"));
                return;
            }
            
            // 列表中勾选对象1条以上时
            if (selArr && selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            var object = selArr[0];
//            if(object.validStatus == '04'){
//           	 Joyin.error(JoyinUtil.getMessage("审批流程中数据，该操作不允许！"));
//                return;
//           }
            object.operation = "CfetsRepoDealQuotedPrice001.edit";
            //多组合标识或者本方询价方为回复方 均跳转多组合界面
            var url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/init';
            if(object.isDzh=='Y'||object.quoteRoleType == quoteRoleTypeReply){
                var entrustIds = getEtrReByQuoteId(object);
                object.entrustId = entrustIds;
                url = 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice003/init';
            }
            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                if (result.data){
                    Joyin.dialog(JoyinUtil.getLabel("CfetsRepoDealQuotedPrice002"), basePath
                            + url, "99%", "99%", {
                        pageModel : PAGE_MODEL_EDIT,
                        entrustId : object.entrustId,
                        quoteId : object.quoteId,
                        editOperation:true,
                        quotaPageModel : 'edit',
                        operation : "CfetsRepoDealQuotedPrice001.edit"
                    }, function() {
                        // 刷新当日报价
                        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
                    });
                }
            });

            /*var object = selArr[0];
            object.operation = "CfetsRepoDealQuotedPrice001.revert";
            Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit',
                    object, function(result) {
                        if (result.data) {
                            Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_R_005"), function() {
                            Joyin.ajaxJson(basePath
                                + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/revert',
                                object, function(result) {
                                        dayOfferTable.table ? dayOfferTable.reload()
                                                : dayOfferTable.load();
                            });
                        });
                    }
            });*/
        }
    });





	// 导入初始化 
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
        Joyin.dialog(JoyinUtil.getLabel('质押式回购报价数据上传'), 
        		JoyinUtil.getRealPath('invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/framework/fileUpload/init'), opt.width, opt.height, opt, function(){
        	entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
            // 刷新当日报价
            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        });
    };
	
	// 真正导入数据
    var quoteImport = function (dataRows) {
    	// let entrustOrderTbl = entrustOrderTable;
    	// let dayOfferTbl = dayOfferTable;

		Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/importFile', dataRows, 
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

    /**
     * 通过报价编号 获取关系数据
     * @param obj
     */
    var getEtrReByQuoteId = function(obj){
        var entrustIds = "";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/selectEtrReByQuoteId', {quoteId : obj.quoteId,workflowId:obj.workflowId},
            function(result) {
            if(result.length>0){
                $.each(result,function (i,e) {
                    entrustIds += e.entrustId+",";
                })
            }
        },false)
        return entrustIds;
    }

	// 暴露给子界面调用
	window.CfetsRepoDealQuotedPrice001 = {
		hasError: true,
		quoteImport: quoteImport,
		validatePreviewResult: validatePreviewResult
	};

    // 验证并设置当前的预览数据中有无错误消息
    var validatePreviewResult = function (previewDtoList) {
    	// 有错误信息则不能提交；预警信息可以继续提交
    	window.CfetsRepoDealQuotedPrice001.hasError = false;

    	$.each(previewDtoList, function(i, previewDto){
    		if (previewDto.validationErrorMsg && previewDto.validationErrorMsg.length > 0) {
    			window.CfetsRepoDealQuotedPrice001.hasError = true;
    			return false; //break;
    		}
    	});
    	
    	return window.CfetsRepoDealQuotedPrice001.hasError;
    };

	// 导入预览 
	$('#CfetsRepoDealQuotedPrice001_quote_import').click(function() {
		uploadQuoteFile(function(fileName) {
            Joyin.ajax(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/importCheck',
                {fileName : fileName}, function(previewDtoList) {
                	validatePreviewResult(previewDtoList);
                });
            });
    });





    

    /**
     * 监视用户下线操作
     */
    SocketClient.connect(function() {
    	// 订阅网关发送的对话报价新增消息(以广播的形式推给所有用户，并且将数据 展示到第一行)
        /*SocketClient.subscribe('/' + repoTopic + '/newRepoDialogueQuoteAll', function(result) {
        	// 刷新当日报价
        	result = JSON.parse(result.responseMessage);
        	var dataArr = dayOfferTable.getAllRows();
        	dataArr.unshift(result);
        	dayOfferTable.reload({data : dataArr});
    	});*/
    	// modify by zhangliang 20200727 MQ 推送有问题，前台会显示重复数据
    	SocketClient.subscribe('/' + repoTopic + '/newRepoDialogueQuoteAll', function(result) {
    		// 刷新当日报价
            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
    	});
        
        // 订阅网关发送的对话报价状态更新消息（已单用户的形式推给创建人，表格刷新）
        SocketClient.subscribe('/' + repoTopic + '/refreshDayOfferTableLoad', function(result) {
        	// 刷新当日报价
            dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
            // 委托指令刷新
            entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
    	}, 'user');
        
        // 委托新建完成需要WS推送
        SocketClient.subscribe('/CfetsRepoDealQuotedPrice001/refreshEntrustOrderTable', function(result) {
        	// 刷新委托指令
        	entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
    	});
        
        
    });
    
    /**
     * 批量接收
     */
    $('#cfetsRepoDealQuotedPrice001_accept').click(function() {
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
    $('#cfetsRepoDealQuotedPrice001_giveup').click(function() {
    	var rowArr = entrustOrderTable.getAllCheckedRows();// 所有选中行数据
        var roleidArray = new Array("");
        for(var i = 0;i<rowArr.length;i++){
//            if(rowArr[i].tradeFinishPercent!=0&&rowArr[i].entrustStatus=='03'){
//                Joyin.error("已接收且完成度为0指令,才能执行放弃操作！");
//                return;
//            }
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
    /**
     * 委托指令查询弹出框
     */
    $('#CfetsRepoDealQuotedPrice001_entrustOrder_btn_seach').click(function() {
        $('#dayOfferSelectorConditionFilter001').hide();
        $('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
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
     * 委托指令查询弹出框关闭
     */
    $('#CfetsRepoDealQuotedPrice001_entrustOrder_btn_seachClose').click(function() {
        $("#selectorConditionFilter001").hide();
    });
    /**
     * 委托指令查询弹出框查询
     */
    $('#CfetsRepoDealQuotedPrice001_entrustOrder_btn_realseach').click(function() {
        fetsRepoDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        $("#selectorConditionFilter001").hide();
    });
    /**
     * 手动刷新
     */
    $('#CfetsRepoDealQuotedPrice001_entrustOrder_secah').click(function() {
        fetsRepoDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
        entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
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
                fetsRepoDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
                entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
            }, time);
        }else{
            clearInterval(entrustOrderrefresh);
        }
    });

	/**
	 * 委托指令查询弹出框重置
	 */
	$('#CfetsRepoDealQuotedPrice001_entrustOrder_btn_seachReset').click(function() {
		$("#portfolioId").empty();
		//$("#ps").empty();
//		var dataPs= $("#ps").select2();
//    	dataPs.val(dataPs.dataType).trigger("change");
    	$("#ps").val("").trigger("change");
		$("#term").val("");
		//$("#settleSpreed").empty();
//		var data= $("#settleSpreed").select2();
//    	data.val(data.dataType).trigger("change");
    	$("#settleSpreed").val("").trigger("change");
		$("#invManagerId").empty();
		$("#entrustId").empty();
		$("#entrustDate").val("");
    });


	/**
     * 当日报价查询弹出框
     */
    $('#CfetsRepoDealQuotedPrice001_dayOffer_btn_seach').click(function() {
        $('#selectorConditionFilter001').hide();
        $('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
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
     * 当日报价查询弹出框关闭
     */
    $('#CfetsRepoDealQuotedPrice001_dayOffer_btn_seachClose').click(function() {
        $("#dayOfferSelectorConditionFilter001").hide();
    });
    /**
     *当日报价查询弹出框查询
     */
    $('#CfetsRepoDealQuotedPrice001_dayOffer_btn_realseach').click(function() {
        fetsRepoDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
        $("#dayOfferSelectorConditionFilter001").hide();
    });

	/**
	 * 当日报价查询弹出框重置
	 */
	$('#CfetsRepoDealQuotedPrice001_dayOffer_btn_seachReset').click(function() {
		//$(".psEmpty").empty();
//		var data= $(".psEmpty").select2();//获取selectid
//    	data.val(data.dataType).trigger("change");
    	$(".psEmpty").val("").trigger("change");
		$("#mySideTrader").empty();
		$("#counterId").empty();
		$("#counterTrader").empty();
		$(".termEmpty").val("");
		$("#quoteId").val("");
    });

	/**
     * 当日报价手动刷新
     */
    $('#CfetsRepoDealQuotedPrice001_dayOffer_secah').click(function() {
        fetsRepoDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
        dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
    });
    /**
     * 当日报价自动刷新参数标记
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
                fetsRepoDealQuotedPrice001_dayOfferTable_loading = layui.layer.open({type:3});
                dayOfferTable.table ? dayOfferTable.reload() : dayOfferTable.load();
            }, time);
        }else{
            clearInterval(entrustOrderrefresh);
        }
    });


	/**
     *查看质押券明细
     */
    tableRepoCstp.bind('click','.viewRepoDetai', function(row) {
        var object = row;
        Joyin.dialog(JoyinUtil.getLabel("质押券明细"),
            basePath + "invest/bankuplink/common/CstpPledgeBond/init", "60%", "58%", {
                pageModel : PAGE_MODEL_NEW,
				execid : object.execid
            });

    });



}());