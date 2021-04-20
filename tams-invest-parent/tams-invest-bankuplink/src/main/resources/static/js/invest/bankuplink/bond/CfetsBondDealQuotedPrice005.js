(function(){

    
    /**
	 * 委托代码
	 */
    var entrustIdChangeAction = function(){
    	var entrustId = $('#CfetsBondDealQuotedPrice005_entrustId').val();
    	if (entrustId && entrustId != ''){
    		Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice003/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
            	if (result){
            		$('#CfetsBondDealQuotedPrice005_hidden_entrustAmt').val(result.entrustNum);
            		$('#CfetsBondDealQuotedPrice005_hidden_finprodId').val(result.finprodId);
            		$('#CfetsBondDealQuotedPrice005_portfolioName').attr('disabled', true).textSelect('setValue',result.portfolioId);
            		$('#CfetsBondDealQuotedPrice005_mySide').attr('disabled', true).textSelect('setValue',result.mySide);
            		$('#CfetsBondDealQuotedPrice005_mySideTrader').textSelect('setValue',result.mySideTrader);
            		$('#CfetsBondDealQuotedPrice005_ps').val(result.ps).trigger('change').attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice005_finprodName').attr('disabled', true).textSelect('setValue',result.finprodMarketId);
            		$('#CfetsBondDealQuotedPrice005_settleCcy').val(result.settleCcy == null ? CNY : result.settleCcy);
            		$('#CfetsBondDealQuotedPrice005_settleSpreed').val(result.settleSpreed).trigger('change').attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice005_settleDate').val(result.settleDateStr).attr('disabled', true);
            		$('#CfetsBondDealQuotedPrice005_avaNum').val(result.avaNumStr).trigger('change');
            		$('#CfetsBondDealQuotedPrice005_effDate').val(result.effDate);
                    $('#CfetsBondDealQuotedPrice005_remark').val(result.remark);
            	}
            });
    	}
    };
    /**
     * 债券简称
     */
    var finprodNameChangeAction = function(){
    	var data = $('#CfetsBondDealQuotedPrice005_finprodName').select2('data')[0].object;
    	if (data){
    		$('#CfetsBondDealQuotedPrice005_finprodMarketId').val(data.finprodMarketId);
    		$("#CfetsBondDealQuotedPrice005_hidden_finprodId").val(data.finprodId);
    		$('#CfetsBondDealQuotedPrice005_settleCcy').val(data.ccy).trigger("change");
    	}
    };
    /**
	 * 组合名称
	 */
    var portfolioNameChangeAction = function(){
    	var portfolioId = $('#CfetsBondDealQuotedPrice005_portfolioName').val();
		$('#CfetsBondDealQuotedPrice005_portfolioId').val(portfolioId);
    };
    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
    	var settleSpreed = $('#CfetsBondDealQuotedPrice005_settleSpreed').val();
    	if (settleSpreed == ACCOUNT_SPEED_1){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate
            }, function (date) {
                $("#CfetsBondDealQuotedPrice005_settleDate").val(date).trigger("change");
            });
    	}else{
    		$('#CfetsBondDealQuotedPrice005_settleDate').val(platDate);
    	}
    };
    /**
     * 保存
     */
    var saveAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice005_hidden_finprodId").val();
        }
        if(object.cfetsBondDealDialogueQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealDialogueQuoteDtoList;
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
		object.isDzh = 'Y';
        object.operatorType = 'save';
		object.pageId = "CfetsBondDealQuotedPrice005";
    	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/check', object, function(result) {
        	if (result.data){
        	    var param = result.data;
        		Joyin.confirm(param, function() {
        			WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/save', object, "CfetsBondDealQuotedPrice005_btn_save", null,
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
        	object.finprodId = $("#CfetsBondDealQuotedPrice005_hidden_finprodId").val();
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
		object.isDzh = 'Y';
        object.operatorType = 'submit';
		object.pageId = "CfetsBondDealQuotedPrice005";
        if(object.cfetsBondDealDialogueQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealDialogueQuoteDtoList;
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/save', object, "CfetsBondDealQuotedPrice005_btn_submit", null,
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
        	object.finprodId = $("#CfetsBondDealQuotedPrice005_hidden_finprodId").val();
        }
        object.operationType = 'recall';
		object.pageId = "CfetsBondDealQuotedPrice005";
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
        	object.finprodId = $("#CfetsBondDealQuotedPrice005_hidden_finprodId").val();
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
		object.isDzh = 'Y';
		object.pageId = "CfetsBondDealQuotedPrice005";
		if(object.cfetsBondDealDialogueQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealDialogueQuoteDtoList;
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/clinchDealCheck', object, function(result) {
            if (result.data){
                var param = result.data;
                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005/save', object, "CfetsBondDealQuotedPrice005_btn_done", null,
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
    var table=$("#CfetsBondDealQuotedPrice005_orgTable").joyintable({
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
        var orgId = $('#CfetsBondDealQuotedPrice005_orgType').val();
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
            var orgValue = $('#CfetsBondDealQuotedPrice005_orgType :selected').text();
            var temp = {};
            temp.counterId=orgId;
            temp.counterValue=orgValue;
            table.addRow(temp);
        }
    };

	var  disabledElementByDzh = function(entrustList){
		// 回复对话报价
	    if (quotaPageModel == 'response'){
			return;
	    }
    	//组合分配列表
	   if (entrustList.length>0){
		    $('#CfetsBondDealQuotedPrice005_entrustId').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_portfolioName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_finprodName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_settleSpreed').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_mySide').attr('disabled', true);
	   }
	   else{
    		$('#CfetsBondDealQuotedPrice005_portfolioName').attr('disabled', false);
    		$('#CfetsBondDealQuotedPrice005_ps').attr('disabled', false);
    		$('#CfetsBondDealQuotedPrice005_finprodName').attr('disabled', false);
    		$('#CfetsBondDealQuotedPrice005_settleSpreed').attr('disabled', false);
	   }
   }

    
    $(function () {
    	// 初始化页面
    	/*var entrustId = $('#CfetsBondDealQuotedPrice005_hidden_entrustId').val();
    	if (entrustId && entrustId != ''){*/
    		$('#CfetsBondDealQuotedPrice005_entrustId').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_portfolioName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_finprodName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_settleSpreed').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice005_mySide').attr('disabled', true);
    	/*}else{
    	    //未选择委托时,默认：T+0
    		if ($("#CfetsBondDealQuotedPrice005_settleSpreed").val() == ''){
    			$("#CfetsBondDealQuotedPrice005_settleSpreed").val(ACCOUNT_SPEED_0).trigger("change");
    		}
    		if ($("#CfetsBondDealQuotedPrice005_settleDate").val() == ''){
    			$('#CfetsBondDealQuotedPrice005_settleDate').val(platDate);
    		}
            //默认人民币
    		if ($("#CfetsBondDealQuotedPrice005_settleCcy").val() == ''){
    			$('#CfetsBondDealQuotedPrice005_settleCcy').val(CNY).trigger("change");
    		}
            //默认券款对付
    		if ($("#CfetsBondDealQuotedPrice005_settleMethod").val() == ''){
    			$('#CfetsBondDealQuotedPrice005_settleMethod').val(SETT_MODE_03).trigger("change");
    		}
            //默认全额清算
    		if ($("#CfetsBondDealQuotedPrice005_settleType").val() == ''){
    			$('#CfetsBondDealQuotedPrice005_settleType').val(CLEAR_TYPE_02).trigger("change");
    		}
    		// 默认登录用户
    		if ($("#CfetsBondDealQuotedPrice005_mySideTrader").val() == ''){
    			$('#CfetsBondDealQuotedPrice005_mySideTrader').val(mySideTraderInit).trigger("change");
    		}
    	}*/
    	// 判断本方交易员是否可改
    	if (ouTraderIdCanEditFlag === 'N'){
    		$('#CfetsBondDealQuotedPrice005_mySideTrader').attr('disabled', true);
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
    	$('#CfetsBondDealQuotedPrice005_entrustId').change(function(){
    		entrustIdChangeAction();
        });
    	/**
    	 * 资产简称
    	 */
    	$('#CfetsBondDealQuotedPrice005_finprodName').change(function(){
    		finprodNameChangeAction();
        });
    	/**
    	 * 组合名称
    	 */
    	$('#CfetsBondDealQuotedPrice005_portfolioName').change(function(){
    		portfolioNameChangeAction();
        });
    	/**
    	 * 清算速度
    	 */
    	$('#CfetsBondDealQuotedPrice005_settleSpreed').change(function(){
    		settleSpreedChangeAction();
        });
    	/**
    	 * 保存按钮按下处理
    	 */
    	$('#CfetsBondDealQuotedPrice005_btn_save').click(function(){
    		saveAction();
    	});
        /**
         * 提交按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice005_btn_submit').click(function(){
            submitAction();
        });
        /**
         * 添加按钮按下处理
         */
    	$('#CfetsBondDealQuotedPrice005_btn_add').click(function(){
    	    addRow();
    	});
        /**
         * 撤回按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice005_btn_revokeBtn').click(function(){
            revokeAction();
        });
        /**
         * 成交按钮按下处理
         */
        $('#CfetsBondDealQuotedPrice005_btn_clinchDeal').click(function(){
            clinchDealAction();
        });
        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPrice005_mySide').change(function(){
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
    	$('#CfetsBondDealQuotedPrice005_mySideTrader').val(mySideTraderInit).trigger('change');
    }
    //撤回按钮不应该在待办及审批环节出现
    if(busWorkflowId){
        $("#CfetsBondDealQuotedPrice005_btn_revokeBtn").hide();
    }
	
	window.CfetsBondDealQuotedPrice005 = {
			disabledElementByDzh:disabledElementByDzh
        };
}());