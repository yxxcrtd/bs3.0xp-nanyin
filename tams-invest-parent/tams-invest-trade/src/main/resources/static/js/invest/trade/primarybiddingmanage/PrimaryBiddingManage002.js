/**
 * 评级信息组件JS
 * @auth
 */
(function(){
	var ENTITY = entity;
    var getElementById = function(id) {
    };
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.counterId = ENTITY.counterId;
        object.workflowId = ENTITY.workflowId;
        Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage002/check', object, function(result) {
        	var message = '';
            if (operateType === 'submit') {
                message = JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit"));
            }
            Joyin.confirm(message, function() {
                WorkFlow.doSubmitList(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage002/save', object, "PrimaryBiddingManage002_btn_submit");
            });
        });
    }
    
    /**
     * 定义数据表格
     */
    var table1 =$("#dataTable1").joyintable({
    	data:ENTITY==null?[]:ENTITY.primaryBiddingPortfolioDtolist,
    	cols:[
               {field:'entrustId',  title: 'PrimaryBiddingManage002.entrustId',sortable:false}
              ,{field:'portfolioId',  title: 'PrimaryBiddingManage002.portfolioId',width:250,sortable:false}
              ,{field:'portfolioName',  title: 'PrimaryBiddingManage002.portfolioName',width:260,sortable:false}
              ,{field:'bidPrinAmt',  title: 'PrimaryBiddingManage002.bidPrinAmt',type:'number',width:250,sortable:false}
              ,{field:'bidPrice',  title: 'PrimaryBiddingManage002.bidprice',type:'number',width:250,sortable:false}
          ],
        searchOnInit: true,
        checkbox:false,
        height:290
    });
    
    /**
     * 定义数据表格
     */				 
    var table2 =$("#dataTable2").joyintable({
    	data:ENTITY==null?[]:ENTITY.primaryBiddingAllDtolist,
    	cols:[  
    	         {field:'bidPrice',  title: 'PrimaryBiddingManage002.bidprice',type:'number',sortable:false,width:650}
    	        ,{field:'hzallBidPrinAmt',  title: 'PrimaryBiddingManage002.allBidPrinAmt',type:'number',sortable:false,width:620}
    	    ],
        searchOnInit: true,
        checkbox:false,
        height:290
    });

    /**
     * 画面加载完成
     */
    $(function() {
    	//提交按钮
        $('#PrimaryBiddingManage002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });

        //分配
        $("#allocation").click(function () {
            var object = $.serializeToObject('#informationForm', true);
            object.operateType = "allocation";
            var bidAdjustAmt = $("#bidAdjustAmt").val();
            var adjustStrategy = $("#adjustStrategy").val();
            var allBidPrinAmt = $("#allBidPrinAmt").val();
            if (!bidAdjustAmt) {
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARY_BIDDING_005"));
                return;
            }
            if (!adjustStrategy) {
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARY_BIDDING_006"));
                return;
            }
            if (Number(bidAdjustAmt) > Number(allBidPrinAmt) || Number(bidAdjustAmt) < 0) {
                Joyin.error(JoyinUtil.getMessage("TRD_PRIMARY_BIDDING_007"));
                return;
            }
            object.adjustStrategy = adjustStrategy;
            object.bidAdjustAmt = bidAdjustAmt;
            Joyin.ajaxJson(basePath + 'invest/trade/primarybiddingmanage/PrimaryBiddingManage002/adjustAmtBid', object, function (result) {
                var rs1 = table1.getAllRows();
                var rs2 = table2.getAllRows();
                for (var i = rs1.length - 1; i >= 0; i--) {
                    table1.deleteRow(i);
                }
                for (var i = rs2.length - 1; i >= 0; i--) {
                    table2.deleteRow(i);
                }
                for (var row in result.primaryBiddingPortfolioDtolist) {
                    table1.addRow(result.primaryBiddingPortfolioDtolist[row]);
                }
                for (var row in result.primaryBiddingAllDtolist) {
                    table2.addRow(result.primaryBiddingAllDtolist[row]);
                }
            });
        });
    });
    window.componentLoadCallback = function(isDone, pagePath, params){
    	$("#trdInvestPersonInfo002Dto_counterId").attr('disabled',true);
    }
}());