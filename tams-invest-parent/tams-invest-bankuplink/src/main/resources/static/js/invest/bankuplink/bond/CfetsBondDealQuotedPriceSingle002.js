(function(){
    //试算结果
    var riskResultdata;
    var cluflag;

    /**
     * 委托代码
     */
    var entrustIdChangeAction = function(){
        var entrustId = $('#CfetsBondDealQuotedPriceSingle002_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
                if (result){
                    $('#CfetsBondDealQuotedPriceSingle002_hidden_entrustAmt').val(result.entrustNum);
                    $('#CfetsBondDealQuotedPriceSingle002_hidden_finprodId').val(result.finprodId);
                    $('#CfetsBondDealQuotedPriceSingle002_portfolioId').attr('disabled', true).textSelect('setValue',result.portfolioId);
                    $('#CfetsBondDealQuotedPriceSingle002_mySide').attr('disabled', true).textSelect('setValue',result.mySide);
                    $('#CfetsBondDealQuotedPriceSingle002_mySideTrader').textSelect('setValue',result.mySideTrader);
                    $('#CfetsBondDealQuotedPriceSingle002_ps').val(result.ps).trigger('change').attr('disabled', true);
                    $('#CfetsBondDealQuotedPriceSingle002_finprodId').attr('disabled', true).textSelect('setValue',result.finprodMarketId);
                    $('#CfetsBondDealQuotedPriceSingle002_settleCcy').val(result.settleCcy == null ? CNY : result.settleCcy);
                    $('#CfetsBondDealQuotedPriceSingle002_settleSpreed').val(result.settleSpreed).trigger('change').attr('disabled', true);
                    $('#CfetsBondDealQuotedPriceSingle002_settleDate').val(result.settleDateStr).attr('disabled', true);
                    $('#CfetsBondDealQuotedPriceSingle002_avaNum').val(result.avaNumStr).trigger('change');
                    $('#CfetsBondDealQuotedPriceSingle002_effDate').val(result.effDate);
                    $('#CfetsBondDealQuotedPriceSingle002_remark').val(result.remark);
                    $('#CfetsBondDealQuotedPriceSingle002_secManageAcctId').textSelect('setValue',result.secManageAcctId);
                    $("#CfetsBondDealQuotedPriceSingle002_hidden_calculateField").val(result.calculateField);
                }
            });
        }
    };


    /**
     * 债券简称
     */
    var finprodIdChangeAction = function(){
        var chlFinprodId = '';
        var finprodIdhid = '';
        var ccy =  '';
        var data = $('#CfetsBondDealQuotedPriceSingle002_finprodId').select2('data')[0].object;
        if(data){
            finprodIdhid = data.finprodId;
            ccy = data.ccy;
            $('#CfetsBondDealQuotedPriceSingle002_hidden_finprodId').val(data.finprodId);
            $("#CfetsBondDealQuotedPriceSingle002_settleCcy").val(data.ccy);
        }else{
            $('#CfetsBondDealQuotedPriceSingle002_hidden_finprodId').val("");
            $("#CfetsBondDealQuotedPriceSingle002_settleCcy").val(CNY);
        }
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle002_portfolioId").val();
        var sysDateT1 = $('#CfetsBondDealQuotedPriceSingle002_tradeDate').val();
        var calId = '';
        if (todoList == false&&parent.parent.EtrIbBondinfoHq) {
            parent.parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
    };

    /**
     * 组合名称
     */
    var portfolioIdChangeAction = function(){
        var portfolioId = $("#CfetsBondDealQuotedPriceSingle002_portfolioId").val();
        var vdate = $('#CfetsBondDealQuotedPriceSingle002_tradeDate').val();
        var ccy= $("#CfetsBondDealQuotedPriceSingle002_settleCcy").val();
        if(window.EtrInitCash){
            window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
        }
    };


    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceSingle002_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate
            }, function (date) {
                $("#CfetsBondDealQuotedPriceSingle002_settleDate").val(date).trigger("change");
            });
        }else{
            $('#CfetsBondDealQuotedPriceSingle002_settleDate').val(platDate);
        }
    };

    /**
     * 保存
     */
    var saveAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceSingle002_hidden_finprodId").val();
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
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_save", "CfetsBondDealQuotedPriceSingle002",
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
            object.finprodId = $("#CfetsBondDealQuotedPriceSingle002_hidden_finprodId").val();
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
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_submit", "CfetsBondDealQuotedPriceSingle002",
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
            object.finprodId = $("#CfetsBondDealQuotedPriceSingle002_hidden_finprodId").val();
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
            object.finprodId = $("#CfetsBondDealQuotedPriceSingle002_hidden_finprodId").val();
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
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/clinchDealCheck', object, function(result) {
            if (result.data){
                var param = result.data;
                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_done", "CfetsBondDealQuotedPriceSingle002",
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
    var table=$("#CfetsBondDealQuotedPriceSingle002_orgTable").joyintable({
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
        var orgId = $('#CfetsBondDealQuotedPriceSingle002_orgType').val();
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
            var orgValue = $('#CfetsBondDealQuotedPriceSingle002_orgType :selected').text();
            var temp = {};
            temp.counterId=orgId;
            temp.counterValue=orgValue;
            table.addRow(temp);
        }
    };
    
    $(function () {
        // 初始化页面
        var entrustId = $('#CfetsBondDealQuotedPriceSingle002_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            $('#CfetsBondDealQuotedPriceSingle002_entrustId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle002_portfolioName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle002_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle002_finprodName').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle002_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceSingle002_mySide').attr('disabled', true);
        }else{
            //未选择委托时,默认：T+0
            if ($("#CfetsBondDealQuotedPriceSingle002_settleSpreed").val() == ''){
                $("#CfetsBondDealQuotedPriceSingle002_settleSpreed").val(ACCOUNT_SPEED_0).trigger("change");
            }
            if ($("#CfetsBondDealQuotedPriceSingle002_settleDate").val() == ''){
                $('#CfetsBondDealQuotedPriceSingle002_settleDate').val(platDate);
            }
            //默认人民币
            if ($("#CfetsBondDealQuotedPriceSingle002_settleCcy").val() == ''){
                $('#CfetsBondDealQuotedPriceSingle002_settleCcy').val(CNY).trigger("change");
            }
            //默认券款对付
            if ($("#CfetsBondDealQuotedPriceSingle002_settleMethod").val() == ''){
                $('#CfetsBondDealQuotedPriceSingle002_settleMethod').val(SETT_MODE_03).trigger("change");
            }
            //默认全额清算
            if ($("#CfetsBondDealQuotedPriceSingle002_settleType").val() == ''){
                $('#CfetsBondDealQuotedPriceSingle002_settleType').val(CLEAR_TYPE_02).trigger("change");
            }
            // 默认登录用户
            if ($("#CfetsBondDealQuotedPriceSingle002_mySideTrader").val() == ''){
                $('#CfetsBondDealQuotedPriceSingle002_mySideTrader').val(mySideTraderInit).trigger("change");
            }
        }
        // 判断本方交易员是否可改
        if (ouTraderIdCanEditFlag === 'N'){
            $('#CfetsBondDealQuotedPriceSingle002_mySideTrader').attr('disabled', true);
        }
        /**
         * 委托代码
         */
        $('#CfetsBondDealQuotedPriceSingle002_entrustId').change(function(){
            entrustIdChangeAction();
        });
        /**
         * 资产简称
         */
        $('#CfetsBondDealQuotedPriceSingle002_finprodName').change(function(){
            finprodIdChangeAction();
        });
        /**
         * 组合名称
         */
        $('#CfetsBondDealQuotedPriceSingle002_portfolioName').change(function(){
            portfolioIdChangeAction();
        });
        /**
         * 清算速度
         */
        $('#CfetsBondDealQuotedPriceSingle002_settleSpreed').change(function(){
            settleSpreedChangeAction();
        });
        /**
         * 保存按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceSingle002_btn_save').click(function(){
            saveAction();
        });
        /**
         * 提交按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceSingle002_btn_submit').click(function(){
            submitAction();
        });
        /**
         * 添加按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceSingle002_btn_add').click(function(){
            addRow();
        });
        /**
         * 撤回按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceSingle002_btn_revokeBtn').click(function(){
            revokeAction();
        });
        /**
         * 成交按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceSingle002_btn_clinchDeal').click(function(){
            clinchDealAction();
        });
        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPriceSingle002_mySide').change(function(){
            mySideChangeAction();
        });
    });

    /**
     * 本方
     */
    var mySideChangeAction = function(){
        $('#CfetsBondDealQuotedPriceSingle002_mySideTrader').val(mySideTraderInit).trigger('change');
    }

    //撤回按钮不应该在待办及审批环节出现
    if(busWorkflowId){
        $("#CfetsBondDealQuotedPrice003_btn_revokeBtn").hide();
    }

    var setQQDiv = function(selArr){
        $("#CfetsBondDealQuotedPriceSingle002_hidden_quotaPageModel").val('new');
        if(selArr&&selArr[0]){
            $("#CfetsBondDealQuotedPriceSingle002_entrustId").textSelect('setValue',selArr[0].entrustId);
        }
    }

    var reset = function () {
    };

    var getriskdata = function () {
        var obj = {
            tradeDirection: $("#CfetsBondDealQuotedPriceSingle002_ps").val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            portfolioCode: $("#CfetsBondDealQuotedPriceSingle002_portfolioId").val(),
            assetType: FINPROD_TYPE_F01,
            isEnabled: "01",
            assetCode: $("#CfetsBondDealQuotedPriceSingle002_finprodId").val()
        };
        if ($("#CfetsBondDealQuotedPriceSingle002_avaNum")) {
            obj.tradeAmount = $("#CfetsBondDealQuotedPriceSingle002_avaNum").val() * 10000;
        }

        var object = {
            tradeDirection: ["ps", "CfetsBondDealQuotedPriceSingle.ps"],
            portfolioCode: ["portfolioId", "CfetsBondDealQuotedPriceSingle.portfolioId"],
            tradeAmount: ["fpriceAmt", "CfetsBondDealQuotedPriceSingle.fprinceAmt"],
            assetType: ["assetType", "CfetsBondDealQuotedPriceSingle.assetType"],
            counterId: ["counterId", "CfetsBondDealQuotedPriceSingle.counterId"],
            data: obj
        };
        return object;
    };

    function saveRiskResultdata(paraRiskResultdata) {
        riskResultdata = paraRiskResultdata;
    }
    function saveCluflag(paraCluflag) {
        cluflag = paraCluflag;
    }

    function getSaveOrSubmitParams(operateType) {
        var object = $.serializeToObject("#submitForm");
        if(!object.finprodId){
            object.finprodId = $('#CfetsBondDealQuotedPriceSingle002_hidden_finprodId').val();
        }
        if(!object.calculateField) {
            object.calculateField = $("#CfetsBondDealQuotedPriceSingle002_hidden_calculateField").val();
        }
        object.operatorType = operateType;
        var orgList = table.getAllRows();
        if(orgList != null && orgList.length>0){
            var orgIdList = [];
            for(var i = 0 ;i<orgList.length; i++){
                var org = orgList[i];
                orgIdList.push(org.counterId);
            }
            object.counterIdList = orgIdList;
        }
        return object;
    }

    parent.CfetsBondDealQuotedPriceSingle002 = {
        setQQDiv: setQQDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag
    }
    CfetsBondDealQuotedPriceSingle002 = {
        setQQDiv: setQQDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag
    }

}());