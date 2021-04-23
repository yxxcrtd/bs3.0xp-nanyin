(function(){

    
    /**
	 * 委托代码
	 */
    var entrustIdChangeAction = function(){
    	var entrustId = $('#CfetsBondDealQuotedPrice003_entrustId').val();
    	if (entrustId && entrustId != ''){
    		Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
            	if (result){
            		$('#CfetsBondDealQuotedPrice003_hidden_entrustAmt').val(result.entrustNum);
            		$('#CfetsBondDealQuotedPrice003_hidden_finprodId').val(result.finprodId);
            		$('#CfetsBondDealQuotedPrice003_portfolioName').attr('disabled', true).textSelect('setValue',result.portfolioId);
            		$('#CfetsBondDealQuotedPrice003_mySide').attr('disabled', true).textSelect('setValue',result.mySide);
            		$('#CfetsBondDealQuotedPrice003_mySideTrader').textSelect('setValue',result.mySideTrader);
            		$('#CfetsBondDealQuotedPrice003_ps').val(result.ps).trigger('change').attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice003_finprodName').attr('disabled', true).textSelect('setValue',result.finprodMarketId);
            		$('#CfetsBondDealQuotedPrice003_settleCcy').val(result.settleCcy == null ? CNY : result.settleCcy);
            		$('#CfetsBondDealQuotedPrice003_settleSpreed').val(result.settleSpreed).trigger('change').attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice003_settleDate').val(result.settleDateStr).attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice003_avaNum').val(result.avaNumStr).trigger('change');
            		$('#CfetsBondDealQuotedPrice003_effDate').val(result.effDate);
                    $('#CfetsBondDealQuotedPrice003_remark').val(result.remark);
            	}
            });
    	}
    };
    /**
     * 债券简称
     */
    var finprodNameChangeAction = function(){
    	var data = $('#CfetsBondDealQuotedPrice003_finprodName').select2('data')[0].object;
    	if (data){
    		$('#CfetsBondDealQuotedPrice003_finprodMarketId').val(data.finprodMarketId);
    		$("#CfetsBondDealQuotedPrice003_hidden_finprodId").val(data.finprodId);
    		$('#CfetsBondDealQuotedPrice003_settleCcy').val(data.ccy).trigger("change");
    	}
    };
    /**
	 * 组合名称
	 */
    var portfolioNameChangeAction = function(){
    	var portfolioId = $('#CfetsBondDealQuotedPrice003_portfolioName').val();
		$('#CfetsBondDealQuotedPrice003_portfolioId').val(portfolioId);
    };
    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
    	var settleSpreed = $('#CfetsBondDealQuotedPrice003_settleSpreed').val();
    	if (settleSpreed == ACCOUNT_SPEED_1){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate
            }, function (date) {
                $("#CfetsBondDealQuotedPrice003_settleDate").val(date).trigger("change");
            });
    	}else{
    		$('#CfetsBondDealQuotedPrice003_settleDate').val(platDate);
    	}
    };
    /**
     * 保存
     */
    var saveAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice003_hidden_finprodId").val();
        }
        var orgList = table.getAllRows();
        if(orgList != null && orgList.length>0){
            var orgIdList = [];
            for(var i = 0 ;i<orgList.length; i++){
                var org = orgList[i];
                orgIdList.push(org.counterId);
            }
            object.counterIdList = orgIdList;
        }
        object.operatorType = 'save';
    	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/check', object, function(result) {
        	if (result.data){
        	    var param = result.data;
        		Joyin.confirm(param, function() {
        			WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/save', object, "CfetsBondDealQuotedPrice003_btn_save", null,
                        function() {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
        		})
        	}
        });
    };
    /**
     * 提交
     */
    var submitAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice003_hidden_finprodId").val();
        }
        var orgList = table.getAllRows();
        if(orgList != null && orgList.length>0){
            var orgIdList = [];
            for(var i = 0 ;i<orgList.length; i++){
                var org = orgList[i];
                orgIdList.push(org.counterId);
            }
            object.counterIdList = orgIdList;
        }
        object.operatorType = 'submit';
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/save', object, "CfetsBondDealQuotedPrice003_btn_submit", null,
                       function() {
                          var index = window.parent.layer.getFrameIndex(window.name);
                          window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    /**
     * 撤回
     */
    var revokeAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice003_hidden_finprodId").val();
        }
        object.operationType = 'recall';
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/checkOperationAllow', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice001/doReCall', object, function(result){
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    })
                })
            }
        });
    };
    /**
     * 成交
     */
    var clinchDealAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice003_hidden_finprodId").val();
        }
        var orgList = table.getAllCheckedRows();
        if(orgList != null && orgList.length > 0){
            var orgInfoList = [];
            var orgIdList = [];
            var detIdList = [];
            for(var i = 0 ;i<orgList.length; i++){
                var org = orgList[i];
                // 勾选对象的交易状态不是已回复时
                if (org.tradeStatusStr != tradeStatus12) {
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R_028"));
                    return;
                }
                orgInfoList.push(org);
                orgIdList.push(org.counterId);
                detIdList.push(org.detId);
            }
            object.counterInfoList = orgInfoList;
            object.counterIdList = orgIdList;
            object.detIdList = detIdList;
        }
        // 列表中勾选对象1条以上时
        if (orgList && orgList.length > 1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        //请求报价回复页面成交按钮
        object.checkType = "answerClinchDeal";
        object.operatorType = "done";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/clinchDealCheck', object, function(result) {
            if (result.data){
                var param = result.data;
                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/save', object, "CfetsBondDealQuotedPrice003_btn_done", null,
                        function() {
                           var index = window.parent.layer.getFrameIndex(window.name);
                           window.parent.layer.close(index);
                     })
            }
        });
    };
    /**
     * 定义数据表格
     */
    var table=$("#CfetsBondDealQuotedPrice003_orgTable").joyintable({
        checkbox:true,
        data:(entity && entity.counterInfoList) ? entity.counterInfoList : [],
        searchOnInit:true,
        page:false,
        height:300,
        cols:[
              {field:'counterValue', title: 'CfetsBondDealQuotedPrice003.counterId'},
              {field:'cpTrader', title: 'CfetsBondDealQuotedPrice003.cpTrader', visible: YES_OR_NO_Y == isRevert},
              {field:'tradeStatusStr', title: 'CfetsBondDealQuotedPrice003.tradeStatus', visible: YES_OR_NO_Y == isRevert,sortField:"tradeStatus"},
              {field:'shareAmt', title: 'CfetsRepoDealQuotedPrice003.shareAmt', type:'number',digit:2, visible: YES_OR_NO_Y == isRevert},
              {field:'unitCprice', title: 'CfetsRepoDealQuotedPrice003.unitCprice', type:'number',digit:4, visible: YES_OR_NO_Y == isRevert},
              {field:'unitFprice', title: 'CfetsRepoDealQuotedPrice003.unitFprice', type:'number',digit:4, visible: YES_OR_NO_Y == isRevert},
              {field:'yield', title: 'CfetsRepoDealQuotedPrice003.yield', type:'number',digit:4, visible: YES_OR_NO_Y == isRevert},
              {field:'exerYield', title: 'CfetsRepoDealQuotedPrice003.exerYield',type:'number',digit:4, visible: (YES_OR_NO_Y == isRevert && YES_OR_NO_Y == assetIsRight)},
              {field:'counterId', visible: false},
              {field:'detId', visible: false},
              {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt'], visible: YES_OR_NO_Y != isRevert}
             ]
        });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    
    /**
     * 添加可选机构事件
     */
    var addRow = function(){
        var orgId = $('#CfetsBondDealQuotedPrice003_orgType').val();
        if(orgId && orgId != ''){
            var orgList = table.getAllRows();
            if(orgList != null && orgList.length>0){
                for(var i = 0 ;i<orgList.length; i++){
                    var org = orgList[i];
                    if(org.counterId == orgId){
                        Joyin.error("可选机构中不允许存在多条相同对手方信息!");
                         return;
                    }
                }
            }
            var orgValue = $('#CfetsBondDealQuotedPrice003_orgType :selected').text();
            var temp = {};
            temp.counterId=orgId;
            temp.counterValue=orgValue;
            table.addRow(temp);
        }
    };
    
    $(function () {
    	// 初始化页面
    	var entrustId = $('#CfetsBondDealQuotedPrice003_hidden_entrustId').val();
    	if (entrustId && entrustId != ''){
    		$('#CfetsBondDealQuotedPrice003_entrustId').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice003_portfolioName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice003_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice003_finprodName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice003_settleSpreed').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice003_mySide').attr('disabled', true);
    	}else{
    	    //未选择委托时,默认：T+0
    		if ($("#CfetsBondDealQuotedPrice003_settleSpreed").val() == ''){
    			$("#CfetsBondDealQuotedPrice003_settleSpreed").val(ACCOUNT_SPEED_0).trigger("change");
    		}
    		if ($("#CfetsBondDealQuotedPrice003_settleDate").val() == ''){
    			$('#CfetsBondDealQuotedPrice003_settleDate').val(platDate);
    		}
            //默认人民币
    		if ($("#CfetsBondDealQuotedPrice003_settleCcy").val() == ''){
    			$('#CfetsBondDealQuotedPrice003_settleCcy').val(CNY).trigger("change");
    		}
            //默认券款对付
    		if ($("#CfetsBondDealQuotedPrice003_settleMethod").val() == ''){
    			$('#CfetsBondDealQuotedPrice003_settleMethod').val(SETT_MODE_03).trigger("change");
    		}
            //默认全额清算
    		if ($("#CfetsBondDealQuotedPrice003_settleType").val() == ''){
    			$('#CfetsBondDealQuotedPrice003_settleType').val(CLEAR_TYPE_02).trigger("change");
    		}
    		// 默认登录用户
    		if ($("#CfetsBondDealQuotedPrice003_mySideTrader").val() == ''){
    			$('#CfetsBondDealQuotedPrice003_mySideTrader').val(mySideTraderInit).trigger("change");
    		}
    	}
    	// 判断本方交易员是否可改
    	if (ouTraderIdCanEditFlag === 'N'){
    		$('#CfetsBondDealQuotedPrice003_mySideTrader').attr('disabled', true);
    	}
    	
    	// 初始化对手方列表
    	/*if(entity && entity.counterInfoList){
    	    debugger;
            table.table ? table.reload() : table.load();
            var counterInfoList = entity.counterInfoList;
            var tempList = [];
            for(var i=0; i< counterInfoList.length; i++){
                var temp = {};
                temp.counterId=counterInfoList[i].counterId;
                temp.counterValue=counterInfoList[i].counterValue;
                temp.cpTrader=counterInfoList[i].cpTrader;
                temp.tradeStatusStr=counterInfoList[i].tradeStatusStr;
                temp.shareAmt=counterInfoList[i].shareAmt;
                temp.unitCprice=counterInfoList[i].unitCprice;
                temp.unitFprice=counterInfoList[i].unitFprice;
                temp.yield=counterInfoList[i].yield;
                temp.exerYield=counterInfoList[i].exerYield;
                temp.detId=counterInfoList[i].detId;
                tempList.push(temp);
            }
            table.addRow(tempList);
        }*/
    	/**
    	 * 委托代码
    	 */
    	$('#CfetsBondDealQuotedPrice003_entrustId').change(function(){
    		entrustIdChangeAction();
        });
    	/**
    	 * 资产简称
    	 */
    	$('#CfetsBondDealQuotedPrice003_finprodName').change(function(){
    		finprodNameChangeAction();
        });
    	/**
    	 * 组合名称
    	 */
    	$('#CfetsBondDealQuotedPrice003_portfolioName').change(function(){
    		portfolioNameChangeAction();
        });
    	/**
    	 * 清算速度
    	 */
    	$('#CfetsBondDealQuotedPrice003_settleSpreed').change(function(){
    		settleSpreedChangeAction();
        });
    	/**
    	 * 保存按钮按下处理
    	 */
    	$('#CfetsBondDealQuotedPrice003_btn_save').click(function(){
    		saveAction();
    	});
        /**
         * 提交按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice003_btn_submit').click(function(){
            submitAction();
        });
        /**
         * 添加按钮按下处理
         */
    	$('#CfetsBondDealQuotedPrice003_btn_add').click(function(){
    	    addRow();
    	});
        /**
         * 撤回按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice003_btn_revokeBtn').click(function(){
            revokeAction();
        });
        /**
         * 成交按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice003_btn_clinchDeal').click(function(){
            clinchDealAction();
        });
        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPrice003_mySide').change(function(){
            mySideChangeAction();
        });
    });
    /**
     * 本方
     */
    var mySideChangeAction = function(){
        /*$('#CfetsBondDealQuotedPrice003_mySideTrader').val('').trigger('change');
        // add by zhangliang 20200630 start
        var mySideTemp = $('#CfetsBondDealQuotedPrice003_mySide').val();
        if (mySideTemp == mySideInit){
        	$('#CfetsBondDealQuotedPrice003_mySideTrader').val(mySideTraderInit).trigger('change');
        }*/
    	$('#CfetsBondDealQuotedPrice003_mySideTrader').val(mySideTraderInit).trigger('change');
    }
    //撤回按钮不应该在待办及审批环节出现
    if(busWorkflowId){
        $("#CfetsBondDealQuotedPrice003_btn_revokeBtn").hide();
    }
}());