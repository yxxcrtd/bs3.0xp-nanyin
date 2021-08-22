(function(){


    var setDhDiv = function(entrustIdList){
        $("#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel").val('new');
        if(entrustIdList){
            $("#CfetsBondDealQuotedPriceMuti001_hidden_entrustId").val(entrustIdList).trigger('change');
        }
    }

    $("#CfetsBondDealQuotedPriceMuti001_hidden_entrustId").change(function () {
        var entrustId = $('#CfetsBondDealQuotedPriceMuti001_hidden_entrustId').val();
        if (entrustId && entrustId != ''){
            Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/queryDialogueQuoteByEntrustIdList', {entrustId : entrustId}, function(result) {
                if (result.data){
                    $('#CfetsBondDealQuotedPriceMuti001_remark').val(result.data.remark);
                    $('#CfetsBondDealQuotedPriceMuti001_hidden_entrustAmt').val(result.data.entrustNum);
                    $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(result.data.finprodId);
                    // 区分画面打开模式
                    var quotaPageModel = $('#CfetsBondDealQuotedPriceMuti001_hidden_quotaPageModel').val();

                    // 做市方回复
                    if (quotaPageModel != 'marketResponse'){
                        // 这些要素应该是做市方已有的信息
                        $('#CfetsBondDealQuotedPriceMuti001_mySide').textSelect('setValue',result.data.mySide);
                        $('#CfetsBondDealQuotedPriceMuti001_counterId').textSelect('setValue',result.data.counterId);
                        $('#CfetsBondDealQuotedPriceMuti001_counterTrader').textSelect('setValue',result.data.counterTrader);
                        $('#CfetsBondDealQuotedPriceMuti001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                        $('#CfetsBondDealQuotedPriceMuti001_ps').val(result.data.ps).trigger('change');
                        $('#CfetsBondDealQuotedPriceMuti001_avaNum').val(result.data.avaNumStr).trigger('blur');
                        $('#CfetsBondDealQuotedPriceMuti001_settleDate').val(result.data.settleDateStr);
                    }else{
                        // 做市报价回复选择委托后回复量默认显示委托剩余额度
                        $('#CfetsBondDealQuotedPriceMuti001_responseAmt').val(result.data.avaNumStr).trigger('blur');
                    }
                    // 对话报价发起模式
                    $('#CfetsBondDealQuotedPriceMuti001_finprodId').textSelect('setValue',result.data.finprodMarketId);
                    $('#CfetsBondDealQuotedPriceMuti001_mySide').textSelect('setValue',result.data.mySide);
                    $('#CfetsBondDealQuotedPriceMuti001_mySideTrader').textSelect('setValue',result.data.mySideTrader);
                    $('#CfetsBondDealQuotedPriceMuti001_settleMethod').val(result.data.settleMethod).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleType').val(result.data.settleType).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleCcy').val(result.data.settleCcy).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_portfolioSeries').val(result.data.portfolioSeries).trigger('change');
                    $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val(result.data.settleSpreed).trigger('change');
                    if(result.data.cfetsBondDealDialogueQuoteDtoList){
                        table.clear();
                        for (var index in result.data.cfetsBondDealDialogueQuoteDtoList){
                            table.addRow(result.data.cfetsBondDealDialogueQuoteDtoList[index]);
                        }
                        proportionalMethod();
                    }
                }
            });
        }
    });

    var proportionalMethod =function (){
        var dealAmt = $('#CfetsBondDealQuotedPriceMuti001_avaNum').val();
        if(!dealAmt){
            return;
        }
        //组合分配列表
        var ptlAllocationList = table.getAllRows();
        if(!ptlAllocationList||ptlAllocationList.length<1){
            return;
        }
        //升序排序
        ptlAllocationList = ptlAllocationList.sort(function (a,b) {
            return a.entrustAvaNum - b.entrustAvaNum;
        })
        //比例分配
        //分配汇总
        var avaTotal = 0;
        //剩余额度汇总
        var entrustAvaNumTotal = 0;
        $.each(ptlAllocationList,function (i,e) {
            entrustAvaNumTotal = JoyinMath.doAdd(entrustAvaNumTotal,e.entrustAvaNum);
        });
        $.each(ptlAllocationList,function (i,e) {
//                e.avaNum = JoyinMath.doMul(dealAmt,JoyinMath.doDiv(e.entrustAvaNum,entrustAvaNumTotal,6,1),2,1);
            e.avaNum = JoyinMath.doDiv(JoyinMath.doMul(dealAmt,e.entrustAvaNum,2,1),entrustAvaNumTotal,2,1);
            avaTotal = JoyinMath.doAdd(avaTotal,e.avaNum);
        });
        //尾差总数
        var tailDifference = JoyinMath.doMinus(dealAmt,avaTotal);
        handleTailDifference(tailDifference,ptlAllocationList,100);
        //刷新列表
        $.each(ptlAllocationList,function (i,e) {
            table.renderRow(e.LAY_TABLE_INDEX, {"avaNum": e.avaNum});
        });
    }

    /**
     * 尾差处理
     * @param tailDifference 尾差总数
     * @param list
     * @param unit 尾差单位
     */
    var  handleTailDifference = function(tailDifference,ptlAllocationList,unit){
        //尾差比例
        var entrustIdArr = [];
        var tailDifferencePre = JoyinMath.doDiv(1,unit,unit.length-1);
        var length = ptlAllocationList.length;
        while(Number(tailDifference)>0&&length>0){
            if(Number(tailDifference)>=1){
                //尾差大于1 始终按委托条数平均拆分尾差
                tailDifferencePre = JoyinMath.doDiv(tailDifference,length,unit.length-1);
            }
            $.each(ptlAllocationList,function (i,e) {
                if(Number(e.avaNum) == Number(e.entrustAvaNum)||Number(tailDifference)<=Number(0)) {
                    if(entrustIdArr.indexOf(e.entrustId)<0){
                        entrustIdArr.push(e.entrustId);
                        length--;
                    }
                    return;
                }else{
                    e.avaNum = JoyinMath.doAdd(e.avaNum,tailDifferencePre);
                    tailDifference = JoyinMath.doMinus(tailDifference,tailDifferencePre);
                }
            });
        }
    }


    $("#CfetsBondDealQuotedPriceMuti001_portfolioId").change(function () {
        var portfolioId = $("#CfetsBondDealQuotedPriceMuti001_portfolioId").val();
        var vdate = $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val();
        var ccy= $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val();
        if(window.EtrInitCash){
            window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
        }
    });

    $("#CfetsBondDealQuotedPriceMuti001_finprodId").change(function () {
        var chlFinprodId = '';
        var finprodIdhid = '';
        var ccy =  '';

        var data = $('#CfetsBondDealQuotedPriceMuti001_finprodId').select2('data')[0].object;
        if(data){
            finprodIdhid = data.finprodId;
            ccy = data.ccy;
            $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val(data.finprodId);
            $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val(data.ccy);
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val("");
            $("#CfetsBondDealQuotedPriceMuti001_settleCcy").val(CCY_CNY);
        }
        // $("#CfetsBondDealQuotedPriceMuti001_finprodId").val();
        var portfolioId = $("#CfetsBondDealQuotedPriceMuti001_portfolioId").val();
        var sysDateT1 = $('#CfetsBondDealQuotedPriceMuti001_tradeDate').val();
        var calId = '';
        if (parent.parent.EtrIbBondinfoHq) {
            parent.parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
        if (data&&data.assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(true);
            layui.form.render();
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPriceMuti001_div_abs2').toggle(false);
        }
    });

    /**
     * 单位净价
     */
    $('#CfetsBondDealQuotedPriceMuti001_unitCprice').on('change',function(){
        // 根据净价计算净价偏离度
        calDeviationDegree();
        calcAmt('01');
    });


    $('#CfetsBondDealQuotedPriceMuti001_avaNum').on('change',function(){
        calcAmt('01');
        proportionalMethod();
    });


    /**
     * 保存
     */
    var saveOrSubmitAction = function(operatorType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.calculateField){
            object.calculateField = $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val();
        }
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val();
        }
        object.operatorType = operatorType;
        object.isDzh = 'Y';
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/check', object,
            function(result) {
                debugger;
                if (result.data) {
                    Joyin.confirm(result.data,
                        function() {
                            //保存 走流程
                            if (operatorType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_save", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //成交 走流程
                            else if (operatorType === 'done') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_done", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //提交 走流程
                            else if (operatorType === 'submit') {
                                WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_submit", "CfetsBondDealQuotedPriceMuti001",
                                    function() {
                                        var index = window.parent.layer.getFrameIndex(window.name);
                                        window.parent.layer.close(index);
                                    })
                            }
                            //拒绝 不走流程
                            else if (operatorType === 'refuse'){
                                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/saveData', object, function(result){
                                    var index = window.parent.layer.getFrameIndex(window.name);
                                    window.parent.layer.close(index);
                                })
                            }
                        })
                }
            });
    };


    /**
     * 保存按钮按下处理
     */
    $('#CfetsBondDealQuotedPriceMuti001_btn_save').click(function(){
        saveOrSubmitAction('save');
    });

    /**
     * 提交按钮按下处理
     */
    $('#CfetsBondDealQuotedPriceMuti001_btn_submit').click(function(){
        debugger;
        saveOrSubmitAction('submit');
    });

    /**
     * 成交
     */
    $('#CfetsBondDealQuotedPriceMuti001_btn_done').click(function(){
        debugger;
        saveOrSubmitAction('done');
    });

    /**
     * 拒绝
     */
    $('#CfetsBondDealQuotedPriceMuti001_btn_refuse').click(function(){
        saveOrSubmitAction('refuse');
    });

    /**
     * 净值偏离度
     */
    function calDeviationDegree() {
        document.getElementById('deviationDegree').innerHTML = "<span style='color: red'>"+
            '0.0000%' +"</span>";
        if(!$("#CfetsBondDealQuotedPriceMuti001_unitCprice").val()||!$("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val()
            ||!$('#CfetsBondDealQuotedPriceMuti001_tradeDate').val()){
            return;
        }
        Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/calDeviationDegree",
            {
                price: $("#CfetsBondDealQuotedPriceMuti001_unitCprice").val(),
                finprodId: $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val(),
                vdate:$('#CfetsBondDealQuotedPriceMuti001_tradeDate').val()
            }, function (result) {
                if (result ) {
                    document.getElementById('deviationDegree').innerHTML ="<span style='color: red'>"+
                        JoyinUtil.formatNumber(result, 4, null, 2) + '%' +"</span>";
                }
            });
    }

    /**
     * 清算速度
     */
    $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').on('change',function(){
        var settleSpreed = $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val();
        if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPriceMuti001_settleDate").val(date).trigger("change");
                calcAmt("01");
            });
        }else{
            $('#CfetsBondDealQuotedPriceMuti001_settleDate').val(platDate);
            calcAmt("01");
        }
    });


    /**
     * 计算金额相关
     */
    var calcAmt = function(calculateField){
        var finprodId;
        finprodId = $('#CfetsBondDealQuotedPriceMuti001_hidden_finprodId').val();
        if(!finprodId){
            return;
        }

        if (calculateField){

            var object = $.serializeToObject('#submitForm', true);
            object.calculateField = calculateField;
            object.finprodId = finprodId;
            object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000));
            object.settleDate = object.tradeDate;

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/calcAmt', object, function(result){
                if (result.data){
                    if (calculateField == calcTypeUnitCprice){
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeYield){
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }else if (calculateField == calcTypeExecYield){
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                    }else{
                        $('#CfetsBondDealQuotedPriceMuti001_unitCprice').val(result.data.price).trigger('blur');
                        if(pageModel != PAGE_MODEL_REFERENCE){
                            $('#CfetsBondDealQuotedPriceMuti001_matureYield').val(result.data.matureYield).trigger('blur');
                        }
                        $('#CfetsBondDealQuotedPriceMuti001_exerYield').val(result.data.exerYield).trigger('blur');
                    }
                    $('#CfetsBondDealQuotedPriceMuti001_unitInt').val(result.data.unitInt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_unitFprice').val(result.data.unitFprice).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValue').val(result.data.parValue).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_intAmt').val(result.data.intAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
                    $('#CfetsBondDealQuotedPriceMuti001_parValueAmt').val(result.data.parValueAmt).trigger('blur');
                    // entrustIdChangeFlag = false;
                }
            })
        }
    };


    var table=$("#cfetsQuoTedCommonTable").joyintable({
        data:cfetsBondDealDialogueQuoteDtoList==null?[]:JSON.parse(cfetsBondDealDialogueQuoteDtoList),
        searchOnInit: true,
        loadonce: true,
        page: false,
        addbtn: false,
        height : 120,
        cols:[
            {field:'entrustId', width:180, title: '指令代码',sortable: false,disabled:true}
            ,{field:'portfolioId', width:150,  title: '组合代码',edit:false,sortable: false,
                editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true&&auth=false')
                }
            }
            ,{field:'portfolioName', width:160,  title: '组合名称',edit:false,sortable: false,
            }
            ,{field:'entrustAvaNum', width:160, title: '剩余额度（万元）',type:'number',sortable: false,edit:false}
            ,{field:'avaNum', width:150, title: '券面总额（万元）',edit:false,sortable: false,type:'number', digit:2}
            ,{field:'price', width:120, title: '净价(元)',edit:false,sortable: false,type:'number', digit:2}
            ,{field:'matureYield', width:120, title: '到期收益率%',edit:false,sortable: false,visible: true,type:'number', digit:2}
            ,{field:'exerYield', width:120, title: '行权收益率%',edit:false,sortable: false,type:'number', digit:2}
        ],
        editCell: function(value, data, field, cell, column){
            if(Number(value) > Number(data.entrustAvaNum)){
                table.renderRow(data.LAY_TABLE_INDEX, {"avaNum": tempAvaNum});
                Joyin.error("券面总额不得大于剩余额度!");
            }
        },
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "avaNum") {
                tempAvaNum = preVal.replace(/\,/g, '');
            }
            return true;
        },
        done: function () {
            disabledElementByDzh(table.getAllRows());
        }
    });

    var  disabledElementByDzh = function(entrustList){
        //组合分配列表
        if (entrustList.length>0){
            // 回复对话报价
            if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
            }
            else {
                $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_settleDate').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', true);
                $('#CfetsBondDealQuotedPriceMuti001_finprodId').attr('disabled', true);
            }
        }
        else{
            if (!quotaPageModel || (quotaPageModel != 'dialogueResponse')) {
                $('#CfetsBondDealQuotedPriceMuti001_settleSpreed').val('01').trigger('change').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_mySide').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_settleDate').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_ps').attr('disabled', false);
                $('#CfetsBondDealQuotedPriceMuti001_finprodId').attr('disabled', false);
            }
        }
    }

    var reset = function () {
    };

    var getriskdata = function () {
        return null;
    };

    function getSaveOrSubmitParams(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.calculateField){
            object.calculateField = $("#CfetsBondDealQuotedPriceMuti001_hidden_calculateField").val();
        }
        if(!object.finprodId){
            object.finprodId = $("#CfetsBondDealQuotedPriceMuti001_hidden_finprodId").val();
        }
        object.operatorType = operateType;
        object.isDzh = 'Y';
        return object;
    }

    parent.CfetsBondDealQuotedPriceMuti001 = {
        setDhDiv: setDhDiv,
        getriskdata: getriskdata,
        getSaveOrSubmitParams: getSaveOrSubmitParams,
        reset: reset
    }

    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            calDeviationDegree();
        }
    };


}());