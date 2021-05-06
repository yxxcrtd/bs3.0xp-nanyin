(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var pageParam =  {
    			finprodId: $('#finprodId').val()
                ,counterId: $('#counterId').val()
                ,tenderconfirmstatus: $('#tenderconfirmstatus').val()
            	,validStatus: $('#validStatus').val()
                ,tenderDateBegin: $('#biddate').startDate()
                ,tenderDateEnd: $('#biddate').endDate()
                ,bidCloseStatus: $('#bidCloseStatus').val()
           };
        return pageParam;
    };

    var closeBidTable = {
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("PrimaryBiddingManage002.reference"),
                JoyinUtil.getRealPath("invest/trade/primarybiddingmanage/PrimaryBiddingManage002/init"),
                "99%", "99%", data);
        },
        // searchOnInit: true,
        cols:[
            {field:'bidId',  title: 'PrimaryBiddingManage001.bidid',width:150,sortable:true}
            ,{field:'validStatus',title:'PrimaryBiddingManage001.validStatus',width:150,sortable:false,visible: false}
            ,{field:'validStatusText',title:'PrimaryBiddingManage001.validStatus',width:150,sortable:false}
            ,{field:'tenderconfirmstatus',title:'PrimaryBiddingManage001.tenderconfirmstatus',width:150,sortable:false,visible: false}
            ,{field:'tenderconfirmstatusText',title:'PrimaryBiddingManage001.tenderconfirmstatus',width:150,sortable:false}
            ,{field:'bidCloseStatus',title:'PrimaryBiddingManage001.bidCloseStatus',width:150,sortable:false,visible: false}
            ,{field:'bidCloseStatusText',title:'PrimaryBiddingManage001.bidCloseStatus',width:150,sortable:false}
            ,{field:'finprodMarketId',title:'PrimaryBiddingManage001.finprodId',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodId"
            }
            ,{field:'finprodId', title:'PrimaryBiddingManage001.finprodId',width:150,sortable:false,visible: false}
            ,{field:'finprodAbbr', title:'PrimaryBiddingManage001.finprodAbbr',width:150,sortable:false}
            ,{field:'biddate', title:'PrimaryBiddingManage001.biddate',width:150,sortable:false, type: 'date'}
            ,{field:'allBidPrinAmt', title:'PrimaryBiddingManage001.allBidPrinAmt',width:150,sortable:false,type:'number'}
            ,{field:'bidpriceStr', title:'PrimaryBiddingManage001.bidprice',width:150,sortable:false}
            ,{field:'counterId', title:'PrimaryBiddingManage001.counterParty',width:150,sortable:false,visible: false}
            ,{field:'counterIdText', title:'PrimaryBiddingManage001.counterParty',width:150,sortable:false}
            ,{field:'10', title:'PrimaryBiddingManage001.winDistributeDetail',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a class='winDistributeDetail-link' style='cursor: pointer;'>" + '中标及分配明细'+ "</a>";
                }
            }
        ]
    };

    var defaultTable = {
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("PrimaryBiddingManage002.reference"),
                JoyinUtil.getRealPath("invest/trade/primarybiddingmanage/PrimaryBiddingManage002/init"),
                "99%", "99%", data);
        },
        // searchOnInit: true,
        cols:[
            {field:'bidId',  title: 'PrimaryBiddingManage001.bidid',width:150,sortable:true}
            ,{field:'validStatus',title:'PrimaryBiddingManage001.validStatus',width:150,sortable:false,visible: false}
            ,{field:'validStatusText',title:'PrimaryBiddingManage001.validStatus',width:150,sortable:false}
            ,{field:'tenderconfirmstatus',title:'PrimaryBiddingManage001.tenderconfirmstatus',width:150,sortable:false,visible: false}
            ,{field:'tenderconfirmstatusText',title:'PrimaryBiddingManage001.tenderconfirmstatus',width:150,sortable:false}
            ,{field:'finprodMarketId',title:'PrimaryBiddingManage001.finprodId',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodId"
            }
            ,{field:'finprodId', title:'PrimaryBiddingManage001.finprodId',width:150,sortable:false,visible: false}
            ,{field:'finprodAbbr', title:'PrimaryBiddingManage001.finprodAbbr',width:150,sortable:false}
            ,{field:'biddate', title:'PrimaryBiddingManage001.biddate',width:150,sortable:false, type: 'date'}
            ,{field:'allBidPrinAmt', title:'PrimaryBiddingManage001.allBidPrinAmt',width:150,sortable:false,type:'number'}
            ,{field:'bidpriceStr', title:'PrimaryBiddingManage001.bidprice',width:150,sortable:false}
            ,{field:'counterId', title:'PrimaryBiddingManage001.counterParty',width:150,sortable:false,visible: false}
            ,{field:'counterIdText', title:'PrimaryBiddingManage001.counterParty',width:150,sortable:false}
            ,{field:'10', title:'PrimaryBiddingManage001.winDistributeDetail',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a class='winDistributeDetail-link' style='cursor: pointer;'>" + '中标及分配明细'+ "</a>";
                }
            }
        ]
    }

    var table;
    if (isCloseBid == '01'){
        table=$("#dataTable").joyintable(closeBidTable);
    } else {
        table=$("#dataTable").joyintable(defaultTable);
    }

    var exportExcel = function(){
        Joyin.exportExcel('export', getSearchParams());
    };
    
    /**
     * 点击中标及分配明细链接跳转到该组合信息查看页面
     */
    table.bind('click','.winDistributeDetail-link', function(row) {
    	if(row.tenderconfirmstatus != confirmstatus04){
        	Joyin.error(JoyinUtil.getMessage("TRD_PRIMARYBIDDINGMANAGE_004"));
            return;
        }
        Joyin.dialog(JoyinUtil.getLabel("PrimaryBiddingManage003.reference"),
                basePath + "invest/trade/primarybiddingmanage/PrimaryBiddingManage003/init",
                "95%", "90%",
                { pageModel: PAGE_MODEL_REFERENCE,
        		  bidId: row.bidId, allBidPrinAmt:row.allBidPrinAmt});
    });
    /**
     * 画面加载完成
     */
    $(function() {

        $('#'+pageId+"_btn_search").click(function () {
            $(".ptl-table1").css("display","none");
            $(".ptl-table").css("display","block");
            table.load();
        });
        //汇总投标确认
        $('#'+pageId+"_btn_sure").click(function () {
        	
        	if(table!=null && table!=''){
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
            //只能汇总确认未确认的记录
            if(selArr[0].tenderconfirmstatus != confirmstatus01){
            	Joyin.error(JoyinUtil.getMessage("TRD_PRIMARYBIDDINGMANAGE_001"));
                return;
            }
            selArr[0].pageModel = PAGE_MODEL_NEW;
            var checkParam = {};
            checkParam.finprodId=selArr[0].finprodId;
            checkParam.counterId=selArr[0].counterId;
            checkParam.bidId=selArr[0].bidId;
            Joyin.ajaxJson(basePath + "invest/trade/primarybiddingmanage/PrimaryBiddingManage002/checkSure",checkParam,function(re){
                Joyin.dialog(JoyinUtil.getLabel("PrimaryBiddingManage002"),
                    basePath + "invest/trade/primarybiddingmanage/PrimaryBiddingManage002/init",
                    "98%", "98%",selArr[0],function(){table.reload();});
            });

        });
        //中标结果维护
        $('#'+pageId+"_btn_result").click(function () {
        	if(table!=null && table!=''){
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
            //只能汇总确认已确认的记录
            if(selArr[0].tenderconfirmstatus != confirmstatus02){
            	Joyin.error(JoyinUtil.getMessage("TRD_PRIMARYBIDDINGMANAGE_003"));
                return;
            }
            var object = selArr[0];
            object.operateType="bidManager";
            Joyin.dialog(JoyinUtil.getLabel("PrimaryBiddingManage003"),
                basePath + "invest/trade/primarybiddingmanage/PrimaryBiddingManage003/init",
                "98%", "98%",object);
          });

        $('#'+pageId+'_btn_revoke').click(function() {
            if(table!=null && table!=''){
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
            
            //只能撤销已确认、已中标、未中标的记录
            if(selArr[0].tenderconfirmstatus == confirmstatus02 ||
            	selArr[0].tenderconfirmstatus == confirmstatus03||
            	selArr[0].tenderconfirmstatus == confirmstatus04){
                var paramObj = {};
                paramObj.bidId = selArr[0].bidId;
                var url = 'doRevoke';
                var checkUrl = 'checkRevoke';
                if(selArr[0].tenderconfirmstatus == confirmstatus03 || selArr[0].tenderconfirmstatus == confirmstatus04){
                    url = 'doRevoke003';
                    checkUrl = 'checkRevoke003';
                    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/getRevokeParam',paramObj,function (result){
                        if(null!=result){
                            selArr[0].bidId=result.bidId;
                            selArr[0].bidDate=result.bidDate;
                            selArr[0].finprodAbbr=result.finprodAbbr;
                            selArr[0].finprodMarketId=result.finprodMarketId;
                            selArr[0].tradeMarket=result.tradeMarket;
                            selArr[0].counterId=result.counterId;
                            selArr[0].bidOrderBidPriceList=result.bidOrderBidPriceList;
                            selArr[0].createDept=result.createDept;
                            selArr[0].createTime=result.createTime;
                            selArr[0].createUser=result.createUser;
                            selArr[0].entrustId=result.entrustId;
                            selArr[0].isWin=result.isWin;
                            selArr[0].payDate=result.payDate;
                            selArr[0].tradeMarket=result.tradeMarket;
                            selArr[0].payType=result.payType;
                            selArr[0].trdInvestPersonInfo=result.trdInvestPersonInfo;
                            selArr[0].updateTime=result.updateTime;
                            selArr[0].updateUser=result.updateUser;
                            selArr[0].winPrice=result.winPrice;
                            selArr[0].winPrinAmt=result.winPrinAmt;
                            selArr[0].winType=result.winType;

                        }
                    });
                }else {
                    var param = {}
                    param.finprodId=selArr[0].finprodId;
                    param.counterId = selArr[0].counterId;
                    param.allBidPrinAmt=selArr[0].allBidPrinAmt;
                    param.bidId=selArr[0].bidId;
                    param.entrustId = selArr[0].entrustId;
                    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage002/getRevokeParam',param,function (result){
                        selArr[0].primaryBiddingAllDtolist=result.primaryBiddingAllDtolist;
                        selArr[0].primaryBiddingPortfolioDtolist=result.primaryBiddingPortfolioDtolist;
                        selArr[0].finprodAbbr=result.finprodAbbr;
                        selArr[0].primaryBiddingAllDtolist=result.primaryBiddingAllDtolist;
                        selArr[0].tradeMarket=result.tradeMarket;
                        selArr[0].trdInvestPersonInfo002Dto=result.trdInvestPersonInfo002Dto;
                    });
                }

            	 var checkObj = {};
            	 checkObj.bidId = selArr[0].bidId;
            	 checkObj.finprodId =selArr[0].finprodId;
                checkObj.tenderconfirmstatus = selArr[0].tenderconfirmstatus;
                checkObj.counterId = selArr[0].counterId;
                checkObj.operateType = "revoke";
                selArr[0].operateType = "revoke";
           	    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage001/'+checkUrl, checkObj, function(result) {
            		Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        selArr[0].revokeReason = result;
            			WorkFlow.doDeleteList(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage001/'+url, selArr[0], "PrimaryBiddingManage001_btn_revoke", "PrimaryBiddingManage002");
            		});
           	});
            }else{
            	Joyin.error(JoyinUtil.getMessage("TRD_PRIMARYBIDDINGMANAGE_002"));
                return;
            }
        });
        /**
         * 资产代码链接
         */
        table.bind('click','.finprodIdLink', function(row) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath
                + "invest/asset/tenderbond/FinTenderBondTargetAsset002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:row.finprodId  //金融产品代码
                });
        });
        //导出
        $('#'+pageId+'_btn_export').click(function() {
            exportExcel();
        });

        //是否有截标
        if (isCloseBid == '01'){
            $('#'+pageId+"_btn_close").show();
            $('#'+pageId+"_btn_cancel_close").show();
            $("#bidCloseStatus_div").show();
        } else {
            $('#'+pageId+"_btn_close").hide();
            $('#'+pageId+"_btn_cancel_close").hide();
            $("#bidCloseStatus_div").hide();
        }

        //截标按钮
        $('#'+pageId+"_btn_close").click(function () {
            if(table!=null && table!=''){
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
            //只能截标未确认的记录
            if(selArr[0].tenderconfirmstatus != confirmstatus01){
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARYBIDDINGMANAGE_001"));
                return;
            }
            if (selArr[0].bidCloseStatus == '01') {
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARY_BIDDING_003"));
                return;
            }
            var object = selArr[0];
            object.operateType = 'closeBid';
            Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage001/closeBidCheck', object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage001/closeOrCancelBid', object,
                        function (result) {table.reload()});
                });
            });
        });
        //取消截标按钮
        $('#'+pageId+"_btn_cancel_close").click(function () {
            if(table!=null && table!=''){
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

            if (selArr[0].bidCloseStatus == '02') {
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARY_BIDDING_004"));
                return;
            }
            var object = selArr[0];
            object.operateType = 'cancelCloseBid';
            Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage001/closeOrCancelBid', object,
                function (result) {table.reload()});
        });

    });
}());