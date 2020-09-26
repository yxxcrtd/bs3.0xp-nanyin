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
        ,{field:'bidPrinAmt',  title: '投标券面总额'}
        ,{field:'winPrice',  title: '中标标位（%）'}
        ,{field:'theWinPrinAmt',  title: '理论中标券面总额'}
        ,{field:'winPrinAmt',  title: '实际中标券面总额',edit: true,type: 'number',render: function (data, type, row, meta) {
                if(null==data||''==data.toString()) {
                    return row.theWinPrinAmt;
                }else {
                    return data;
                }
		    }}
        ,{field:'winCprice',  title: '中标净价'}
    ];
    var table =$("#dataTable").joyintable({
    	 /*ajax: {
             url: 'findBidPrice',
             type: 'POST',
             data: function() {
                 return getSearchParams();
             }
         },*/
    	 data:entity==null?[]:entity.bidOrderBidPriceList==null?[]:entity.bidOrderBidPriceList,
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
        if(null!=entity){
        	object.bidId=entity.bidId;
        	object.bidDate=entity.bidDate;
        	object.finprodAbbr=entity.finprodAbbr;
        	object.finprodMarketId=entity.finprodMarketId;
        	object.tradeMarket=entity.tradeMarket;
            object.counterId=entity.counterId;
        }
        object.bidOrderBidPriceList=table.getAllRows();
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
            object.operateType = "autoAllocation";
            var data = {};
            data.winPrice = $("#winPrice").val();
            data.winType = $("#winType").val();
            data.winPrinAmt = $("#winPrinAmt").val();
            data.finprodId = $("#finprodId").val();
            data.counterId = entity.counterId;
            data.bidId = entity.bidId;
            if ($("#isWin").val() != 'N') {
                Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/checkBidPrice', object, function (result) {
                    Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage003/findBidPrice', data, function (retPrice) {
                        var rs = table.getAllRows();
                        for (var i = rs.length - 1; i >= 0; i--) {
                            table.deleteRow(i);
                        }
                        for (var row in retPrice) {
                            table.addRow(retPrice[row]);
                        }

                    });
                });
            }
        });

    });

}());