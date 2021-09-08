(function(){
    //试算结果
    var riskResultdata;
    var cluflag;

    /**
     * 债券简称
     */
    var finprodIdChangeAction = function(){
        var data = $('#CfetsBondDealQuotedPriceMuti002_finprodId').select2('data')[0].object;
        if (data){
            $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val(data.finprodId);
            $('#CfetsBondDealQuotedPriceMuti002_settleCcy').val(data.ccy).trigger("change");
        }
    };

    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate
            }, function (date) {
                $("#CfetsBondDealQuotedPriceMuti002_settleDate").val(date).trigger("change");
            });
        }else{
            $('#CfetsBondDealQuotedPriceMuti002_settleDate').val(platDate);
        }
    };
    /**
     * 保存
     */
    var saveAction = function(){
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val();
        }
        if(object.cfetsBondDealRequestQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealRequestQuoteDtoList;
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
        object.pageId = "CfetsBondDealQuotedPriceMuti002";
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_save", null,
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
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val();
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
        object.pageId = "CfetsBondDealQuotedPriceMuti002";
        if(object.cfetsBondDealRequestQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealRequestQuoteDtoList;
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/check', object, function(result) {
            if (result.data){
                var param = result.data;
                Joyin.confirm(param, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_submit", null,
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
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val();
        }
        object.operationType = 'recall';
        object.pageId = "CfetsBondDealQuotedPriceMuti002";
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
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val();
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
        object.pageId = "CfetsBondDealQuotedPriceMuti002";
        if(object.cfetsBondDealRequestQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealRequestQuoteDtoList;
        }
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/clinchDealCheck', object, function(result) {
            if (result.data){
                var param = result.data;
                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_done", null,
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
    var table=$("#CfetsBondDealQuotedPriceMuti002_orgTable").joyintable({
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
        var orgId = $('#CfetsBondDealQuotedPriceMuti002_orgType').val();
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
            var orgValue = $('#CfetsBondDealQuotedPriceMuti002_orgType :selected').text();
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
            $('#CfetsBondDealQuotedPriceMuti002_entrustId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti002_portfolioId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti002_ps').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti002_finprodId').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').attr('disabled', true);
            $('#CfetsBondDealQuotedPriceMuti002_mySide').attr('disabled', true);
        }
        else{
            $('#CfetsBondDealQuotedPriceMuti002_portfolioId').attr('disabled', false);
            $('#CfetsBondDealQuotedPriceMuti002_ps').attr('disabled', false);
            $('#CfetsBondDealQuotedPriceMuti002_finprodId').attr('disabled', false);
            $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').attr('disabled', false);
        }
    }

    $(function () {
        var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti002_hidden_quotaPageModel').val();
        if (quotaPageModel && quotaPageModel == 'new'){
            $('#CfetsBondDealQuotedPriceMuti002_btn_done').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti002_btn_refuse').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti002_btn_save').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti002_btn_submit').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti002_btn_cancel').toggle(false);
        }
        // 初始化页面
        $('#CfetsBondDealQuotedPriceMuti002_entrustId').attr('disabled', true);
        $('#CfetsBondDealQuotedPriceMuti002_portfolioId').attr('disabled', true);
        $('#CfetsBondDealQuotedPriceMuti002_ps').attr('disabled', true);
        $('#CfetsBondDealQuotedPriceMuti002_finprodId').attr('disabled', true);
        $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').attr('disabled', true);
        $('#CfetsBondDealQuotedPriceMuti002_mySide').attr('disabled', true);
        // 判断本方交易员是否可改
        if (ouTraderIdCanEditFlag === 'N'){
            $('#CfetsBondDealQuotedPriceMuti002_mySideTrader').attr('disabled', true);
        }

        /**
         * 资产简称
         */
        $('#CfetsBondDealQuotedPriceMuti002_finprodId').change(function(){
            finprodIdChangeAction();
        });
        /**
         * 清算速度
         */
        $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').change(function(){
            settleSpreedChangeAction();
        });

        // /**
        //  * 券面总额
        //  */
        // $("#CfetsBondDealQuotedPriceMuti002_avaNum").change(function() {
        //     proportionalMethod();
        // });
        /**
         * 保存按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti002_btn_save').click(function(){
            saveAction();
        });
        /**
         * 提交按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti002_btn_submit').click(function(){
            submitAction();
        });
        /**
         * 添加按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti002_btn_add').click(function(){
            addRow();
        });
        /**
         * 撤回按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti002_btn_revokeBtn').click(function(){
            revokeAction();
        });
        /**
         * 成交按钮按下处理
         */
        $('#CfetsBondDealQuotedPriceMuti002_btn_clinchDeal').click(function(){
            clinchDealAction();
        });
        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPriceMuti002_mySide').change(function(){
            mySideChangeAction();
        });
    });
    /**
     * 本方
     */
    var mySideChangeAction = function(){
        $('#CfetsBondDealQuotedPriceMuti002_mySideTrader').val(mySideTraderInit).trigger('change');
    }

    //撤回按钮不应该在待办及审批环节出现
    if(busWorkflowId){
        $("#CfetsBondDealQuotedPriceMuti002_btn_revokeBtn").hide();
    }


    var setQQDiv = function(entrustIdList){
        $("#CfetsBondDealQuotedPriceMuti002_hidden_quotaPageModel").val('new');
        if(entrustIdList){
            $("#CfetsBondDealQuotedPriceMuti002_hidden_entrustId").val(entrustIdList).trigger('change');
        }
    }

    $("#CfetsBondDealQuotedPriceMuti002_hidden_entrustId").change(function () {
        var entrustId = $('#CfetsBondDealQuotedPriceMuti002_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/queryRequestQuoteByEntrustIdList', {entrustId : entrustId}, function(result) {
                if (result.data){
                    $('#CfetsBondDealQuotedPriceMuti002_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPriceMuti002_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPriceMuti002_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti002_hidden_quotaPageModel').val();

                    // 做市方回复
                    if (quotaPageModel != 'marketResponse'){
                        // 这些要素应该是做市方已有的信息
                        $('#CfetsBondDealQuotedPriceMuti002_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPriceMuti002_counterId').textSelect('setValue',result.data.counterId);
                        $('#CfetsBondDealQuotedPriceMuti002_counterTrader').textSelect('setValue',result.data.counterTrader);
                        $('#CfetsBondDealQuotedPriceMuti002_finprodId').textSelect('setValue',result.data.finprodMarketId);
                        $('#CfetsBondDealQuotedPriceMuti002_ps').val(result.data.ps).trigger('change');
                        $('#CfetsBondDealQuotedPriceMuti002_avaNum').val(result.data.avaNumStr).trigger('blur');
                        $('#CfetsBondDealQuotedPriceMuti002_settleDate').val(result.data.settleDateStr);
                    }
                    // 对话报价发起模式
                    $('#CfetsBondDealQuotedPriceMuti002_finprodId').textSelect('setValue',result.data.finprodMarketId);
                    $('#CfetsBondDealQuotedPriceMuti002_mySide').textSelect('setValue',result.data.mySide);
                    $('#CfetsBondDealQuotedPriceMuti002_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                    $('#CfetsBondDealQuotedPriceMuti002_settleMethod').val(result.data.settleMethod).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti002_settleType').val(result.data.settleType).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti002_settleCcy').val(result.data.settleCcy).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti002_portfolioSeries').val(result.data.portfolioSeries).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti002_settleSpreed').val(result.data.settleSpreed).trigger('change');
                    if(result.data.cfetsBondDealRequestQuoteDtoList){
                        // cfetsQuoTedCommon.clear();
                        // for (var index in result.data.cfetsBondDealRequestQuoteDtoList){
                        //     cfetsQuoTedCommon.addRow(result.data.cfetsBondDealRequestQuoteDtoList[index]);
                        // }
                        // proportionalMethod();
                        window.CfetsQuotedCommon001.clearTable();
                        window.CfetsQuotedCommon001.reloadPtlAllocation(result.data.cfetsBondDealRequestQuoteDtoList);
                    }
                }
            });
        }
    });


//     var proportionalMethod =function (){
//         var dealAmt = $('#CfetsBondDealQuotedPriceMuti002_avaNum').val();
//         if(!dealAmt){
//             return;
//         }
//         //组合分配列表
//         var ptlAllocationList = cfetsQuoTedCommon.getAllRows();
//         if(!ptlAllocationList||ptlAllocationList.length<1){
//             return;
//         }
//         //升序排序
//         ptlAllocationList = ptlAllocationList.sort(function (a,b) {
//             return a.entrustAvaNum - b.entrustAvaNum;
//         })
//         //比例分配
//         //分配汇总
//         var avaTotal = 0;
//         //剩余额度汇总
//         var entrustAvaNumTotal = 0;
//         $.each(ptlAllocationList,function (i,e) {
//             entrustAvaNumTotal = JoyinMath.doAdd(entrustAvaNumTotal,e.entrustAvaNum);
//         });
//         $.each(ptlAllocationList,function (i,e) {
// //                e.avaNum = JoyinMath.doMul(dealAmt,JoyinMath.doDiv(e.entrustAvaNum,entrustAvaNumTotal,6,1),2,1);
//             e.avaNum = JoyinMath.doDiv(JoyinMath.doMul(dealAmt,e.entrustAvaNum,2,1),entrustAvaNumTotal,2,1);
//             avaTotal = JoyinMath.doAdd(avaTotal,e.avaNum);
//         });
//         //尾差总数
//         var tailDifference = JoyinMath.doMinus(dealAmt,avaTotal);
//         handleTailDifference(tailDifference,ptlAllocationList,100);
//         //刷新列表
//         $.each(ptlAllocationList,function (i,e) {
//             cfetsQuoTedCommon.renderRow(e.LAY_TABLE_INDEX, {"avaNum": e.avaNum});
//         });
//     }
//
//
//     var cfetsQuoTedCommon=$("#cfetsQuoTedCommonTable").joyintable({
//         data:cfetsBondDealRequestQuoteDtoList==null?[]:JSON.parse(cfetsBondDealRequestQuoteDtoList),
//         searchOnInit: true,
//         loadonce: true,
//         page: false,
//         addbtn: false,
//         height : 120,
//         cols:[
//             {field:'entrustId', width:180, title: '指令代码',sortable: false,disabled:true}
//             ,{field:'portfolioId', width:150,  title: '组合代码',edit:false,sortable: false,
//                 editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
//                     url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true&&auth=false')
//                 }
//             }
//             ,{field:'portfolioName', width:160,  title: '组合名称',edit:false,sortable: false,
//             }
//             ,{field:'entrustAvaNum', width:160, title: '剩余额度（万元）',type:'number',sortable: false,edit:false}
//             ,{field:'avaNum', width:150, title: '券面总额（万元）',edit:false,sortable: false,type:'number', digit:2}
//             ,{field:'price', width:120, title: '净价(元)',edit:false,sortable: false,type:'number', digit:2}
//             ,{field:'matureYield', width:120, title: '到期收益率%',edit:false,sortable: false,visible: true,type:'number', digit:2}
//             ,{field:'exerYield', width:120, title: '行权收益率%',edit:false,sortable: false,type:'number', digit:2}
//         ],
//         editCell: function(value, data, field, cell, column){
//             if(Number(value) > Number(data.entrustAvaNum)){
//                 table.renderRow(data.LAY_TABLE_INDEX, {"avaNum": tempAvaNum});
//                 Joyin.error("券面总额不得大于剩余额度!");
//             }
//         },
//         beforeEditCell: function (preVal, field, rowData) {
//             if (field === "avaNum") {
//                 tempAvaNum = preVal.replace(/\,/g, '');
//             }
//             return true;
//         },
//         done: function () {
//             disabledElementByDzh(table.getAllRows());
//         }
//     });
//
//
//     /**
//      * 尾差处理
//      * @param tailDifference 尾差总数
//      * @param list
//      * @param unit 尾差单位
//      */
//     var  handleTailDifference = function(tailDifference,ptlAllocationList,unit){
//         //尾差比例
//         var entrustIdArr = [];
//         var tailDifferencePre = JoyinMath.doDiv(1,unit,unit.length-1);
//         var length = ptlAllocationList.length;
//         while(Number(tailDifference)>0&&length>0){
//             if(Number(tailDifference)>=1){
//                 //尾差大于1 始终按委托条数平均拆分尾差
//                 tailDifferencePre = JoyinMath.doDiv(tailDifference,length,unit.length-1);
//             }
//             $.each(ptlAllocationList,function (i,e) {
//                 if(Number(e.avaNum) == Number(e.entrustAvaNum)||Number(tailDifference)<=Number(0)) {
//                     if(entrustIdArr.indexOf(e.entrustId)<0){
//                         entrustIdArr.push(e.entrustId);
//                         length--;
//                     }
//                     return;
//                 }else{
//                     e.avaNum = JoyinMath.doAdd(e.avaNum,tailDifferencePre);
//                     tailDifference = JoyinMath.doMinus(tailDifference,tailDifferencePre);
//                 }
//             });
//         }
//     }



    function getBaseRiskdata(item) {
        var obj = {
            tradeDirection: $('#CfetsBondDealQuotedPriceMuti002_ps').val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            assetType: FINPROD_TYPE_F01,
            isEnabled: '01',
            assetCode: $('#CfetsBondDealQuotedPriceMuti002_finprodId').val()
        }
        obj.ps = $('#CfetsBondDealQuotedPriceMuti002_ps').val();

        obj.ccy = 'CNY';
        return obj;
    }
    var getriskdata = function () {
        let dataList = [];
        let portfolioIdList = $.serializeToObject('#submitForm').cfetsBondDealRequestQuoteDtoList;
        if (!portfolioIdList) {
            portfolioIdList = $.serializeToObject('#submitForm').cfetsBondDealDialogueQuoteDtoList;
        }
        if (portfolioIdList.length > 0) {
            for (var index in portfolioIdList) {
                let item = portfolioIdList[index];
                let obj = getBaseRiskdata();
                obj.portfolioCode = item.portfolioId;
                obj.portfolioId = item.portfolioId;
                obj.entrustId = item.entrustId;
                if (item.avaNum) {
                    obj.tradeAmount = item.avaNum * 10000;
                }
                obj.fpriceAmt = obj.tradeAmount;
                dataList.push(obj);
            }
        }else{
            let obj = getBaseRiskdata();
            obj.portfolioCode="";
            if ($("#CfetsBondDealQuotedPriceMuti001_avaNum").val()) {
                obj.tradeAmount = $("#CfetsBondDealQuotedPriceMuti002_avaNum").val() * 10000;
            }
            dataList.push(obj);
        }

        let checkParam = {
            tradeDirection: ["ps", "CfetsBondDealQuotedPriceSingle.ps"],
            portfolioCode: ["portfolioId", "CfetsBondDealQuotedPriceSingle.portfolioId"],
            tradeAmount: ["tradeAmt", "CfetsBondDealQuotedPriceSingle.bondPrinamt"],
            assetType: ["assetType", "CfetsBondDealQuotedPriceSingle.assetType"],
            counterId: ["counterId", "CfetsBondDealQuotedPriceSingle.counterId"],
            data: dataList
        }
        checkParam.price = ['price', 'CfetsBondDealQuotedPriceSingle.unitCprice'];
        checkParam.ccy = ['ccy', '币种'];
        return checkParam;
    };

    function getSaveOrSubmitParams(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti002_hidden_finprodId").val();
        }
        if(!object.calculateField) {
            object.calculateField = $("#CfetsBondDealQuotedPriceMuti002_hidden_calculateField").val();
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
        object.operatorType = operateType;
        object.pageId = "CfetsBondDealQuotedPriceMuti002";
        if(object.cfetsBondDealDialogueQuoteDtoList){
            object.cfetsBondDealRequestQuoteDtoList = object.cfetsBondDealDialogueQuoteDtoList;
        }
        return object;
    }

    var reset = function () {
        $("#CfetsBondDealQuotedPriceMuti002_hidden_entrustId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_portfolioSeries").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_finprodId").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_ps").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_settleType").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_settleSpreed").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_tradeDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_settleDate").val(sysDate).trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_settleMethod").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_avaNum").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_mySide").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_mySideTrader").val("").trigger("change");
        $("#CfetsBondDealQuotedPriceMuti002_remark").val("").trigger("change");
        window.CfetsQuotedCommon001.clearTable();
        table.clear();
        layui.form.render(); // reload layui
        pageModel = PAGE_MODEL_NEW;
    };

    function saveRiskResultdata(paraRiskResultdata) {
        riskResultdata = paraRiskResultdata;
    }
    function saveCluflag(paraCluflag) {
        cluflag = paraCluflag;
    }

    window.CfetsBondDealQuotedPriceMuti002 = {
        disabledElementByDzh:disabledElementByDzh,
    };

    parent.CfetsBondDealQuotedPriceMuti002 = {
        setQQDiv: setQQDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag,
        disabledElementByDzh: disabledElementByDzh
    }
    CfetsBondDealQuotedPriceMuti002 = {
        setQQDiv: setQQDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset,
        riskResultdata: riskResultdata,
        cluflag: cluflag,
        saveRiskResultdata: saveRiskResultdata,
        saveCluflag: saveCluflag,
        disabledElementByDzh: disabledElementByDzh
    }
}());