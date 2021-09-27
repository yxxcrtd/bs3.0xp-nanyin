/**
 * 评级信息组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
    };
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId:$("#finprodId").val()
        }
    };
    /**
     * 定义数据表格
     */
    var cols = [
        {field:'entrustId',  title: '委托编号'}
        ,{field:'portfolioId',  title: '组合代码'}
        ,{field:'portfolioName',  title: '组合名称'}
        ,{field:'invManagerId',  title: '投资经理'}
        ,{field:'bidPriceStr',  title: '投标标位（%）'}
        ,{field:'bidPrinAmt',  title: '投标券面总额（万元）'}
        ,{field:'winPrice',  title: '中标标位（%）'}
        ,{field:'theWinPrinAmt',  title: '理论中标券面总额（万元）'}
        ,{field:'winPrinAmt', digit:4, title: '实际中标券面总额（万元）',edit: true,type: 'number',render: function (data, type, row, meta) {
                if(null==data||''==data.toString()) {
                    return row.theWinPrinAmt;
                }else {
                    return data;
                }
		    }}
        ,{field:'winCprice',  title: '中标净价'}
        ,{field:'isFrozenPosition', title: '是否头寸冻结', edit:true, editOption:{id: 'isFrozenPosition', type:'select', result: YES_OR_NO}}
    ];
    var table =$("#dataTable").joyintable({
    	 /*ajax: {
             url: 'findBidPrice',
             type: 'POST',
             data: function() {
                 return getSearchParams();
             }
         },*/
    	 data:PrimaryBiddingManage003_entity==null?[]:PrimaryBiddingManage003_entity.bidOrderBidPriceList==null?[]:PrimaryBiddingManage003_entity.bidOrderBidPriceList,
        searchOnInit:true,
        cols:cols,
        checkbox:false,
        page:false,
        height:290
    });
  
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#informationForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        if(null!=PrimaryBiddingManage003_entity){
        	object.bidId=PrimaryBiddingManage003_entity.bidId;
        	object.bidDate=PrimaryBiddingManage003_entity.bidDate;
        	object.finprodAbbr=PrimaryBiddingManage003_entity.finprodAbbr;
        	object.finprodMarketId=PrimaryBiddingManage003_entity.finprodMarketId;
        	object.tradeMarket=PrimaryBiddingManage003_entity.tradeMarket;
            object.counterId=PrimaryBiddingManage003_entity.counterId;

            // add by zhangrui1 start
            object.entrustId = PrimaryBiddingManage003_entity.entrustId;
            object.portfolioId = PrimaryBiddingManage003_entity.bidOrderBidPriceList[0].portfolioId;
            // add by zhangrui1 end
        }
        //万元转换 add by yeming 20210730 start
        // object.bidOrderBidPriceList=table.getAllRows();
        var tableData  = table.getAllRows();
        for (var i=0; i<tableData.length; i++){
            if(tableData[i].bidPrinAmt){
                tableData[i].bidPrinAmt = JoyinMath.doMul(tableData[i].bidPrinAmt, 10000, 2) ;
            }
            if(tableData[i].theWinPrinAmt){
                tableData[i].theWinPrinAmt = JoyinMath.doMul(tableData[i].theWinPrinAmt, 10000, 2) ;
            }
            if(tableData[i].winPrinAmt){
                tableData[i].winPrinAmt = JoyinMath.doMul(tableData[i].winPrinAmt, 10000 , 2) ;
            }
        }
        object.bidOrderBidPriceList = tableData;
        if($("#allBidPrinAmt").val()) {
            object.allBidPrinAmt = JoyinMath.doMul($("#allBidPrinAmt").val(), 10000, 2);
        }
        if($("#winPrinAmt").val()){
            object.winPrinAmt = JoyinMath.doMul($("#winPrinAmt").val(), 10000 , 2) ;
        }
        //万元转换 add by yeming 20210730 end
        Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/save', object, "PrimaryBiddingManage003_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/save', object, "PrimaryBiddingManage003_btn_submit");
                    }
                });
        });
    };
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            $("select#trdInvestPersonInfo_counterId").attr("disabled", true).trigger("change");
            $("#trdInvestPersonInfo_counterTrader").attr("disabled", true).trigger("change");
            $("#trdInvestPersonInfo_contactInfo").attr("disabled", true).trigger("change");
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化画面
        //init();
        // 绑定按钮事件
        $('#PrimaryBiddingManage003_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#PrimaryBiddingManage003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        $("#isWin").change(function () {
            if ($(this).val() == 'N') {
                $("#winPrice").val("").trigger("change").attr("disabled", true);
                $("#winPrinAmt").val("").trigger("change").attr("disabled", true);
                $("#winType").val("").trigger("change").attr("disabled", true);
                $("#payType").trigger("change").attr("disabled", true);
                $("#payDate").val("").trigger("change").attr("disabled", true);
                $("#autoAllocation").addClass("layui-btn-disabled").trigger("change");
                var rs = table.getAllRows();
                for (var i = rs.length - 1; i >= 0; i--) {
                    // table.deleteRow(i);
                    // rs[i].winPrice="";
                    // rs[i].theWinPrinAmt="";
                    // rs[i].winPrinAmt="";
                    // rs[i].winCprice="";
                    table.renderRow(i,{"winPrice":"","theWinPrinAmt":"","winPrinAmt":"","winCprice":""});
                }
                $("#allocationDetail").hide();
            } else {
                $("#winPrice").attr("disabled", false).trigger("change");
                $("#winPrinAmt").attr("disabled", false).trigger("change");
                $("#payType").attr("disabled", false).trigger("change");
                $("#payDate").attr("disabled", false).trigger("change");
                $("#winType").attr("disabled", false).trigger("change");
                $("#autoAllocation").removeClass("layui-btn-disabled");
                $("#allocationDetail").show();

            }
        });
        if ($("#isWin").val() == 'N') {
            $("#winPrice").val("").trigger("change").attr("disabled", true);
            $("#winPrinAmt").val("").trigger("change").attr("disabled", true);
            $("#winType").val("").trigger("change").attr("disabled", true);
            $("#payType").trigger("change").attr("disabled", true);
            $("#payDate").val("").trigger("change").attr("disabled", true);
            $("#autoAllocation").addClass("layui-btn-disabled").trigger("change");
            $("#allocationDetail").hide();
            // var rs = table.getAllRows();
            // for (var i = rs.length - 1; i >= 0; i--) {
            //     table.deleteRow(i);
            // }
        }

        //自动分配
        $("#autoAllocation").click(function () {
            var object = $.serializeToObject('#informationForm', true);
            //配置单位 add by yeming 20210723 start
            if($("#allBidPrinAmt").val()) {
                object.allBidPrinAmt = JoyinMath.doMul($("#allBidPrinAmt").val(), 10000, 2);
            }
            if($("#winPrinAmt").val()){
                object.winPrinAmt = JoyinMath.doMul($("#winPrinAmt").val(), 10000 , 2) ;
            }
            //配置单位 add by yeming 20210723 end
            object.operateType = "autoAllocation";
            var data = {};
            data.winPrice = $("#winPrice").val();
            data.winType = $("#winType").val();
            data.winPrinAmt = JoyinMath.doMul($("#winPrinAmt").val(), 10000 , 2) ;
            data.finprodId = $("#finprodId").val();
            data.counterId = PrimaryBiddingManage003_entity.counterId;
            data.bidId = PrimaryBiddingManage003_entity.bidId;
            if ($("#isWin").val() != 'N') {
                Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/checkBidPrice', object, function (result) {
                    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/findBidPrice', data, function (retPrice) {
                        var rs = table.getAllRows();
                        for (var i = rs.length - 1; i >= 0; i--) {
                            table.deleteRow(i);
                        }
                        for (var row in retPrice) {
                            //配置单位 add by yeming 20210723 start
                            retPrice[row].bidPrinAmt = JoyinMath.doDiv(retPrice[row].bidPrinAmt,entrustAmtUnit == '02'? 10000:1,4,JoyinMath.ROUND_HALF_UP);
                            retPrice[row].theWinPrinAmt = JoyinMath.doDiv(retPrice[row].theWinPrinAmt,entrustAmtUnit == '02'? 10000:1,4,JoyinMath.ROUND_HALF_UP);
                            retPrice[row].winPrinAmt = JoyinMath.doDiv(retPrice[row].winPrinAmt,entrustAmtUnit == '02'? 10000:1,4,JoyinMath.ROUND_HALF_UP);
                            retPrice[row].isFrozenPosition = "N";
                            //配置单位 add by yeming 20210723 end
                            table.addRow(retPrice[row]);
                        }

                    });
                });
            }
        });

    });

}());