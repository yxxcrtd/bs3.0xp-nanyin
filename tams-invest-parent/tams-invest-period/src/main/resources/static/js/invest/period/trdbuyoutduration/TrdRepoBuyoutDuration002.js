(function () {
    /**
     * 页面初始化
     */
    var init = function() {
        //add by jiangpeng on 20210422 start TISHKBS-1803
        $("#settleDate").change(function () {
            if(isTodo) {
                if(isTodo){
                    let param = {};
                    let dateMap = {};
                    param.isOnlyOneDate = true;
                    dateMap.settleDate = $("#settleDate").val();
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].updateGenerateOrders(dateMap,param);
                }
            }
        });
        //add by jiangpeng on 20210422 end TISHKBS-1803
    }

    /**
     * 定义数据表格
     */
    var repoInfoTable = $("#repoInfoTable").joyintable({
        rowno: true,
        searchOnInit:true,
        ajax: {
            url: basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/findRepoInfo',
            type: 'POST',
            data: {finprodId: finprodId}
        },
        checkbox: false,
        page: false,
        cols: [
            {field: 'finprodMarketId', width: "23%", title: '债券代码'}
            , {field: 'finprodAbbr', width: "23%", title: '债券简称'}
            , {field: 'qty', width: "23%", title: '质押券面总额', type: 'money'}
            // , {field: 'pledgeRatio', width: "23%", title: '折算比例(%)', type: 'money'}
        ]
    });

    function saveOrSubmit(operateType) {
        if (GEN_PAY_ORDERS_AUTO == '01') {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].generateOrders(function () {
                realSaveOrSubmit(operateType);
            });
        }
        else {
            realSaveOrSubmit(operateType);
        }
    }

    function realSaveOrSubmit(operateType) {
        // 划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        object.operateType = operateType;
        object.pageModel = PAGE_MODEL_NEW;
        pageModel = PAGE_MODEL_NEW;
        if($("#chlId").val()){
        	object.collectType = $("#chlId").select2("data")[0].object.collectType;
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/save', object, "TrdRepoBuyoutDuration002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdbuyoutduration/TrdRepoBuyoutDuration002/save', object, "TrdRepoBuyoutDuration002_btn_submit");
                }
            });
        });
    }
    
    /**
     *	计算通道费
     * @returns
     */
    function tradeAmtChange (){
    	 if($("#chlId").val()&&$("#chlId").select2("data")[0].object.collectType==Collect_Type_01){
    		 $('#tradeAmt').val(JoyinMath.doMinus($("#fpriceAmt").val(), $("#channelFee").val(), 2));
    	 }else{
    		 $('#tradeAmt').val($("#fpriceAmt").val());
    	 }
    }
    

    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var deal = JSON.parse(dealString);
                deal.tradeAmt = $("#tradeAmt").val();
                deal.settleDate = $("#settleDate").val();
                // 通道交易 新增 强成西 2019/03/26 start
                deal.chlId = $("#chlId").val();
                // 通道交易 新增 强成西 2019/03/26 end
                return {
                    'isTotalTrade': YES_OR_NO_Y
                    , 'bankLendingType': null
                    , 'portfolioId': portfolioId
                    , 'deal':deal
                };
            });
            if($("#chlId").val()){
            	$("#channelFee_div").show();
            }
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdRepoBuyoutDuration002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdRepoBuyoutDuration002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        $('#fpriceAmt').on('change',function() {
            tradeAmtChange();
        });
        $('#channelFee').on('change',function() {
            tradeAmtChange();
        });
    });
}());

