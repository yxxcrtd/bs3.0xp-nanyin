(function () {
    function query(ele){
        return $("#CfetsEtrIbBondinfo003_bodydiv " + ele);
    }

    // 风险试算结果
    var riskResultdata;
    var initFlag = false;
    var cluflag;
    var businessId;
    var fprice;
    var prodIdFlag = 0;
    var ps = query("#ps").val();


    CfetsEtrIbBondinfo003 = {
        riskResultdata: riskResultdata,
        initFlag: initFlag,
        cluflag: cluflag,
       // getriskdata: getriskdata,
      //  doRiskcal: doRiskcal,
        getSubmitParam: getSubmitParam,
     //   doReset: doReset,
        getSearchParams: getSearchParams,
        businessId: businessId
    }



    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }
    function getBussCombDistribtTableParams() {
       var entityBussCombDistritList=entity_BussCombDistribtList||[];
       if (entityBussCombDistritList.length > 0) {
           for (var index in entityBussCombDistritList) {
               entityBussCombDistritList[index].entrustNum = entityBussCombDistritList[index].entrustNum/10000;
               entityBussCombDistritList[index].entrustOwnNum = entityBussCombDistritList[index].entrustOwnNum/10000;
               entityBussCombDistritList[index].entrustIntegrateNum = entityBussCombDistritList[index].entrustIntegrateNum/10000;
           }
       }
       return entityBussCombDistritList;
    }
    var colsCombDistribt = [
        {
            /* 组合代码 */
            field: 'portfolioId',
            width: 200,
            title: 'CfetsEtribBondInfo003.portfolioId',
            sortable: false
            ,edit:true
            ,editOption: {
                type: 'select',
                id: 'portfolioId',
                showField: 'text',
                multiple: false,
                searchable: true,
                url: JoyinUtil.getRealPath('cfets/etrib/cfetsEtrIbBondinfo/selectPortfolioIdForSelect'),
                postData:function () {
                    var param = {finprodId:$("#finprodIdhid").val()};
                    if((ps == 'T02' || ps == 'T03') && prodIdFlag == 1) {
                        param.initValue = null;
                    }
                    if(ps == 'T02' || ps == 'T03') {
                        param.ps = $("#ps").val();
                    }
                    return param;
                }
            }
        }
        , {/*业务模式*/
              field: 'busiModelStr',
              width: 180,
              title: 'CfetsEtribBondInfo003.businessModel',
              sortable: false,
              edit: ps == 'T03'? false : true,
              editOption:{type:'select', formatoption:true, showField: 'text',result: BUSINESS_MODEL}
       }
        , {
            /*劵面总额*/
            field: 'entrustNum',
            width: 140,
            title: 'CfetsEtribBondInfo003.entrustAmt',
            sortable: false,
            edit: true,
            type: 'number',
            digit: 2
        }
        , {
            /*本方券券面总额（万元）*/
            field: 'entrustOwnNum',
            width: 180,
            title: 'CfetsEtribBondInfo003.entrustOwnNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?(ps !='T03'?true:false):false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'entrustIntegrateNum',
            width: 180,
            title: 'CfetsEtribBondInfo003.entrustIntegratCasheNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?(ps !='T03'?true:false):false,
            type: 'number',
            edit: true,
            digit: 2
        }
      , {
            field: 'proAvailableCash',
            width: 150,
            title: 'CfetsEtribBondInfo003.proAvailableCash',
            sortable: false,
            type: 'number',
            digit: 2
        }
      , {
            field: 'proAvailableCash1',
            width: 150,
            title: 'CfetsEtribBondInfo003.proAvailableCash1',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'secAcctAvailableCash',
            width: 150,
            title: 'CfetsEtribBondInfo003.secAcctAvailableCash',
            sortable: false,
            type: 'number',
            digit: 2,
        }
       , {
             field: 'porAvailableSec',
             width: 150,
             title: 'CfetsEtribBondInfo003.porAvailableSec',
             sortable: false,
             visible:SHORT_SELLING_SUPPORTED == '02'?false:true,
             type: 'number',
             digit: 2
         }
         , {
             field: 'porAvailableSec1',
             width: 130,
             title: 'CfetsEtribBondInfo003.porAvailableSec1',
             sortable: false,
             visible:SHORT_SELLING_SUPPORTED == '02'?false:true,
             type: 'number',
             digit: 2,
         }
       , {
            field: 'entrustIntegrateSecNum',
            width: 180,
            title: 'CfetsEtribBondInfo003.entrustIntegrateSecNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
       , {
            field: 'ownPorAvailableSec',
            width: 150,
            title: 'CfetsEtribBondInfo003.ownPorAvailableSec',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'ownProAvailableSec1',
            width: 150,
            title: 'CfetsEtribBondInfo003.ownProAvailableSec1',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
    , {
         field: 'entrustId',
         visible: false
      }
    ]

    var table = query("#bussCombDistribtTable").joyintable({
        data:getBussCombDistribtTableParams(),
        cols: colsCombDistribt,
        page: false,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290,
        editCell: function (value, data, field, cell, column) {
            if (field == 'portfolioId') {
                debugger
                var arr = [];
                var param = {}
                arr.push(value);
                param.portfolioIdList = arr;
                Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbBondinfo/queryPositionInfo', param, function (result) {
                    if(result.data[0].amount && "--" == result.data[0].amount) {
                        data.proAvailableCash = 0;
                    }else {
                        data.proAvailableCash = result.data[0].amount;;
                    }
                    if(result.data[1].amount && "--" == result.data[1].amount) {
                        data.proAvailableCash1 = 0;
                    }else {
                        data.proAvailableCash1 = result.data[1].amount;;
                    }
                    if(result.data[2].amount && "--" == result.data[2].amount) {
                        data.secAcctAvailableCash = 0;
                    }else {
                        data.secAcctAvailableCash = result.data[2].amount;;
                    }
                    if(result.data[3].amount && "--" == result.data[3].amount) {
                        data.porAvailableSec = 0;
                    }else {
                        data.porAvailableSec = result.data[3].amount;;
                    }
                    if(result.data[4].amount && "--" == result.data[4].amount) {
                        data.entrustIntegrateSecNum = 0;
                    }else {
                        data.entrustIntegrateSecNum = result.data[4].amount;;
                    }
                    if(result.data[5].amount && "--" == result.data[5].amount) {
                        data.ownPorAvailableSec = 0;
                    }else {
                        data.ownPorAvailableSec = result.data[5].amount;;
                    }
                    if(result.data[6].amount && "--" == result.data[6].amount) {
                        data.ownProAvailableSec1 = 0;
                    }else {
                        data.ownProAvailableSec1 = result.data[6].amount;;
                    }
                    data.entrustIntegrateNum = 0;
                    table.renderRow(data.LAY_TABLE_INDEX, data);
                });
            }
            if(field == 'entrustNum') {
                data.entrustOwnNum = value;
                table.renderRow(data.LAY_TABLE_INDEX, data);
            }
        }
    });
   var colsRiskTrialRet = [
        {
            /* 指标名称*/
            field: 'kriName', width: 100, title: 'BaseRiskMathComm.indName',sortable: false
        }
        , {/*指标类型*/
              field: 'kriTypeCn',
              width: 50,
              title: 'BaseRiskMathComm.indType',
              sortable: false
       }
        , {
            /*组合名称*/
            field: 'portfolioName',
            width: 150,
            title: 'BaseRiskMathComm.portfolioName',
            sortable: false
        }
        , {
            /*预警阈值*/
            field: 'warningLine',
            width: 150,
            title: 'BaseRiskMathComm.wthreshold',
            sortable: false
        }
        , {
           /*审批线*/
            field: 'approveLine',
            width: 150,
            title: 'BaseRiskMathComm.approveold',
            sortable: false
        }
      , {
           /*当前实际值*/
            field: 'kriValue',
            width: 200,
            title: 'BaseRiskMathComm.currValue',
            sortable: false
        }
      , {
           /*原值*/
            field: 'oldValue',
            width: 200,
            title: 'BaseRiskMathComm.preValue',
            sortable: false
        }
        , {
            /*趋势*/
            field: 'kriTrend',
            width: 200,
            title: 'BaseRiskMathComm.kriTrend',
            sortable: false
        }
        ,{field:'kriStatusCn',  title: 'BaseRiskMathComm.currStatus',sortable:false,
               render: function (data, type, row, meta) {
                   if(row.kriStatus == 0){
                       return "<span>正常</span>";
                   }
                   if(row.kriStatus == 1){
                       return "<span style='color:orange'>预警</span>";
                   }
                   if(row.kriStatus == 2){
                       return "<span style='color:#F7580D'>审批</span>";
                   }
                   if(row.kriStatus == 3){
                       return "<span style='color:#F71A0D'>违规</span>";
                   }
                   if(row.kriStatus == 4){
                       return "<span style='color:#951F1F'>异常</span>";
                   }
                   return row.kriStatus;
               }
        }
    ]

    var tableRisk = query("#riskTrialRetTable").joyintable({
        data:entity_RiskTrialRetList||[],
        cols: colsRiskTrialRet,
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
    });



    var init = function () {
        query("#calculateField_price").prop('checked', true);
        layui.form.render('radio');
        if(calculateField){
            query("#calculateField_" + calculateField).attr("checked", true);
            layui.form.render('radio');
            query("#"+calculateField +"Show").attr("disabled", false);
        }
        var restTradeAmt = query("#tradeAmt").val()==null?0:query("#tradeAmt").val();
        query("#tradeAmt").val(JoyinMath.doDiv(restTradeAmt,10000,2)).trigger("change");
        var restEntrustOwnNum=query("#entrustOwnNum").val()==null?0:query("#entrustOwnNum").val();
        query("#entrustOwnNum").val(JoyinMath.doDiv(restEntrustOwnNum,10000,2)).trigger("change");
        var restEntrustIntegrateNum=query("#entrustIntegrateNum").val()==null?0:query("#entrustIntegrateNum").val();
        query("#entrustIntegrateNum").val(JoyinMath.doDiv(restEntrustIntegrateNum,10000,2)).trigger("change");
        businessId = query('#businessId').val();
        var riskLists=entity_RiskTrialRetList||[];
        if (riskLists.length > 0) {
            $("#riskTrialSpan").html("&nbsp;&nbsp;" + riskLists[0].conclusion);
        }
        if (SHORT_SELLING_SUPPORTED && SHORT_SELLING_SUPPORTED != '02') {
            //本方券和卖空购回字段隐藏
            $('.selling').hide();
            table.showColumns([{field: 'entrustOwnNum', visible: false}]);
        }
    }

    init();

    var calData = function (calflag) {
        if(ps == 'T03') {
            return;
        }
        if(!calflag){
            calflag =  query("input[name=calculateField]:checked").val();
        }
        if (!query("#"+calflag).val()) {
            return;
        }
        if (!initFlag) {
            let finvdate = query("#finvdate").val();
            let finmdate = query("#finmdate").val();
            if(finvdate !=='' && finvdate !== finmdate){
                // 只有指令开始日=指令结束日时才联动
                return;
            }
            if (!ps) {
                return;
            }
            if (!query("#priceModel").val() || !(query("#priceModel").val() == "PRM01" || query("#priceModel").val() == "PRM02")) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!query("#finprodIdhid").val()) {
                return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 start
            var calculateField =  query("input[name=calculateField]:checked").val();
            if(calflag != calculateField){
                return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 end
            var object = {};
            object.calflag = calflag;
            object.finvdate = query("#finvdate").val();
            object.finmdate = query("#finmdate").val();
            object.finprodId = query("#finprodIdhid").val();
            object.tradeMarketMode = SHARES_TRADE_MARKET_02;
            object.matureYield = $("#matureYield").val();
            object.ps = ps;
            object.priceModel = $("#priceModel").val();
            object.settleSpeed = $("#settleSpeed").val();
            object.tradeMarket = $("#tradeMarket").val();
            object.etrDate = $("#etrDate").val();
            object.price = $("#price").val();
            object.unitFprice = $("#unitFprice").val();

            if (object.bussCombDistribtList != null) {
                if (object.bussCombDistribtList.length > 0) {
                    var entrustNum = 0.0;
                    for (var index in object.bussCombDistribtList) {
                        var item = object.bussCombDistribtList[index];
                        if (item.entrustNum == null || item.entrustNum == ""){
                            // return;
                        }else{
                            entrustNum += parseFloat(item.entrustNum) * 10000;
                        }
                    }
                    object.entrustNum = String(entrustNum);
                } else {
                    object.entrustNum = 10000;
                }
            }
            fprice = "";
            Joyin.ajaxJson(basePath + "cfets/etrib/CfetsEtrIbBondinfo003/calData", object, function (result) {
                if (result && result.data) {
                    var data = result.data.data;
                    if (data.price) {
                        fprice = JoyinMath.doDiv(data.unitFprice,1,4);
                        query("#price").val(data.price)
                        query("#priceShow").val(JoyinMath.doDiv(data.price,1,4));
                        query("#unitFpriceShow").val(JoyinMath.doDiv(data.unitFprice,1,4));
                        query("#unitFprice").val(data.unitFprice);
                        if(calflag == 'price') {
                            query("#matureYieldShow").val(JoyinMath.doMul(data.matureYield,100,4));
                            query("#matureYield").val(data.matureYield);
                        }
                        calAmountData();
                    } else {
                        fprice = "";
                        query("#price").val("");
                        query("#priceShow").val("");
                        query("#unitFpriceShow").val("");
                        query("#unitFprice").val("")
                        calAmountData();
                    }
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        query("#exerYield").val(data.exerYield).trigger("blur");
                        query("#matureYield").val(data.matureYield).trigger("blur");
                    }
                }
            });
        }
    };

    function calAmountData() {
        var tradeAmt = query("#tradeAmt").val();
        var entrustIntegrateNum = query("#entrustIntegrateNum").val();
        if(entrustIntegrateNum >= tradeAmt) {
            Joyin.error("融入/卖空购回券面总额不能大于券面总额")
        }
        if(tradeAmt && entrustIntegrateNum) {
            query("#entrustOwnNum").val(JoyinMath.doMinus(tradeAmt,entrustIntegrateNum,2));
        }
        var param = {};
        param.shareNum = query("#tradeAmt").val();
        param.entrustOwnNum = query("#entrustOwnNum").val();
        param.entrustIntegrateNum = query("#entrustIntegrateNum").val();
        param.price = query("#priceShow").val();
        param.unitFprice = query("#unitFprice").val();
        Joyin.ajaxJson(basePath + 'cfets/etrib/CfetsEtrIbBondinfo003/calAmountData', param, function (result) {
           var data = result.data.data;
           if(data) {
               //给一大串字段赋值
                query("#priceShow").val(JoyinMath.doDiv(data.price,1,4));
                query("#price").val(data.price);
                query("#priceAmt").val(JoyinMath.doDiv(data.priceAmt,1,2));
                query("#unitInt").val(JoyinMath.doDiv(data.unitInt,1,4));
                query("#unitIntShow").val(JoyinMath.doDiv(data.unitInt,1,4));
                query("#unitIntAmt").val(JoyinMath.doDiv(data.unitIntAmt,1,2));
                query("#unitFpriceShow").val(JoyinMath.doDiv(data.unitFprice,1,4));
                query("#unitFprice").val(data.unitFprice);
                query("#unitFpriceAmt").val(JoyinMath.doDiv(data.unitFpriceAmt,1,2));
                query("#entrustOwnInt").val(JoyinMath.doDiv(data.entrustOwnInt,1,2));
                query("#entrustIntegrateInt").val(JoyinMath.doDiv(data.entrustIntegrateInt,1,2));
                query("#entrustOwnUnitFpriceAmt").val(JoyinMath.doDiv(data.entrustOwnUnitFpriceAmt,1,2));
                query("#entrustIntegrateUnitFpriceAmt").val(JoyinMath.doDiv(data.entrustIntegrateUnitFpriceAmt,1,2));
           }
        });
    }


    /**
     * 获取提交的数据
     * @param operateType
     * @returns {{}}
     */
    function getSubmitParam(operateType) {
        var res = {}
        var object = $.serializeToObject('#searchForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.tradeMarket = query('#tradeMarket').val();
        object.tradeMarketMode= query('#tradeMarketMode').val();
        object.entrustId = query('#entrustId').val();
        object.businessId = query('#businessId').val();
        object.totalEtrustId = query('#totalEtrustId').val();
        object.entrustStatus = query('#entrustStatus').val();
        object.finvdate = query('#finvdate').val();
        object.invManagerId = query('#invManagerId').val();
        object.transmitTime = query('#transmitTime').val();
        // 证券账户ID
        object.secAcctId = query('#secAcctId').val();
        object.vdate=object.etrDate;
        object.mdate=object.etrDate;
        object.entrustDate = object.vdate > object.finvdate.split(' ')[0] ?
        object.vdate: object.finvdate.split(' ')[0];
        //提交获取计算方式单选框的值
        object.calculateField = query("input[name=calculateField]:checked").val();
        if(query('#exerYield').val()){
           object.exerYield=query('#exerYield').val()/100;
        }
        if(query('#matureYield').val()){
          object.matureYield=query('#matureYield').val()/100;
        }
        if(query('#tradeAmt').val()){
          object.tradeAmt=query('#tradeAmt').val()*10000;
          object.entrustNum=query('#tradeAmt').val()*10000;
        }
        if(query('#entrustOwnNum').val()){
          object.entrustOwnNum=query('#entrustOwnNum').val()*10000;
        }
        if(query('#entrustIntegrateNum').val()){
          object.entrustIntegrateNum=query('#entrustIntegrateNum').val()*10000;
        }

        object.finmdate = query('#finmdate').val();
        object.ccy = query('#ccy').val();
        var objects = new Array();
        var entrustNum = 0;
        var finprodId = null;
        var counterId = null;
        var tradeMarket = query('#tradeMarket').val();
        var ps = null;
        var param = {};

        var tradeAmt = Number(0)
        for(var index in object.bussCombDistribtList){
            var item = object.bussCombDistribtList[index];
            var temp = JSON.parse(JSON.stringify(object));
            temp.portfolioId = item.portfolioId;
            tradeAmt += parseFloat(item.entrustNum) * 10000;
            if(item.entrustNum&&item.entrustNum!=''){
                item.entrustNum=item.entrustNum * 10000;
                temp.entrustNum = item.entrustNum;
            }
            if(item.entrustOwnNum&&item.entrustOwnNum!=''){
                item.entrustOwnNum=item.entrustOwnNum * 10000;
                temp.entrustOwnNum = item.entrustOwnNum;
            }
            if(item.entrustIntegrateNum&&item.entrustIntegrateNum!=''){
                item.entrustIntegrateNum=item.entrustIntegrateNum * 10000;
                temp.entrustIntegrateNum = item.entrustIntegrateNum;
            }
            finprodId = temp.finprodId;
            tradeMarket = temp.tradeMarket;
            temp.entrustId = item.entrustId;
            ps = temp.ps;
            temp.invAim = item.invAim;
            temp.businessModel = item.businessModel;
            temp.elementId = 'CfetsEtribBondInfo003_btn_submit';
            objects.push(temp);
        }
       if(tradeAmt && tradeAmt !== parseFloat(object.tradeAmt)){
            JoyinUtil.showMessage("各组合分配的券面总额汇总不等于总的券面总额!", "error")
            return null;
        }
        param = JSON.parse(JSON.stringify(object));
       // param.entrustNum = object.tradeAmt;
        param.finprodId = finprodId;
        param.finprodAbbr = query("#finprodAbbr").val();
        param.tradeMarket = tradeMarket;
        param.invManagerId = query('#invManagerId').val();
        param.ps = ps;
        param.ccy = query('#ccy').val();
        param.etrInvestBondInfoDtos = objects;
        param.bussCombDistribtList = object.bussCombDistribtList;
        // add by zhangrui1 start
        param.operateType = operateType;
        param.pageModel = pageModel;
        //param.riskTrialRetDto = object.riskTrialRetDto;
        param.riskTrialRetDto = entity_RiskTrialRetList;

        param.elementId = 'CfetsEtrIbBondinfo003_btn_submit';

        res.param = param;
        res.objects = objects;
        return res;
    }

    function paramCheck() {
        if(!query("#finprodName").val() ||  !query("#priceModel").val() || !query("#tradeAmt").val()) {
            Joyin.error("有*必填项未填写,请填写完整")
            return false;
        }
        if(!query("#priceShow") && !query("#matureYieldShow") && !query("#exerYieldShow") ) {
            Joyin.error("有*必填项未填写,请填写完整")
            return false;
        }
        if(!entity_RiskTrialRetList) {
            Joyin.error("请先完成风险试算")
            return false;
        }
        return true;
    }

    function saveOrSubmit(operateType) {
        var res = getSubmitParam(operateType)
            if(res.objects.length>0){
                if (operateType === "save") {
                    WorkFlow.doSaveList(basePath+'cfets/etrib/CfetsEtrIbBondinfoController/save',res.param,"CfetsEtrIbBondinfo003_btn_save","CfetsEtrIbBondinfo003");
                } else {
                   WorkFlow.doSubmitList(basePath+'cfets/etrib/CfetsEtrIbBondinfoController/save',res.param,"CfetsEtrIbBondinfo003_btn_submit","CfetsEtrIbBondinfo003");
                }
            }
    }

    // 绑定提交按钮
    $('#CfetsEtrIbBondinfo003_btn_submit').click(function () {
        if(paramCheck()) {
            saveOrSubmit('submit');
        }
    });

    // 绑定保存按钮
    $('#CfetsEtrIbBondinfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });
    //绑定风险是算
    $('#CfetsEtrIbBondinfo003_btn_riskcal').click(function () {
        var object = $.serializeToObject('#searchForm');
        var param = JSON.parse(JSON.stringify(object))
        Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbBondinfo/doRiskcal', param, function (data) {
            $("#riskTrialSpan").html("&nbsp;&nbsp;" + data.data.data.riskTrialSpan);
            entity_RiskTrialRetList = data.data.data.riskcalReturnList;
            $.each(entity_RiskTrialRetList,function (index,item) {
                item.conclusion = data.data.data.riskTrialSpan;
            })
            tableRisk.clear();
            tableRisk.addRow(entity_RiskTrialRetList);
        });
    });

    query("#finprodName").change(function () {
        var data = query("#finprodName").select2('data')[0];
        if(data) {
            query("#finprodMarketId").val(data.id);
            query("#finprodIdhid").val(data.object.finprodId);
            query("#ccy").val(data.object.ccy);
            query("#finvdate").val(data.object.finvdate);
            query("#finmdate").val(data.object.finmdate);
            query("#isRight").val(data.object.isRight);
            query("#calendarId").val(data.object.calendarId);
            query("#tradeMarket").val(data.object.tradeMarket);
            query("#finprodAbbr").val(data.text);
            if("Y" == data.object.isRight) {
                //query("#exerYieldShow").attr("disabled", false);
                $("input[name='calculateField'][value='exerYield']").attr('disabled',false);
                layui.form.render('radio');
                /*query("#calculateField_matureYield").attr("disabled", true);
                query("#calculateField_price").attr("disabled", true);*/

            }else {
                //query("#exerYieldShow").attr("disabled", true);
                $("input[name='calculateField'][value='exerYield']").attr('disabled',true);
                layui.form.render('radio');
                /* $("input[name='calculateField'][value='price']").attr('checked','true');
                   query("#calculateField_matureYield").attr("disabled", false);
                   query("#calculateField_price").attr("disabled", false);
                   query("#calculateField_exerYield").attr("disabled", true);
                   layui.form.render('radio');*/
            }
            prodIdFlag = 1;
        }
        calAmountData();
        if(ps == 'T02' || ps == 'T03') {
           table.reload();
           var allRow = table.getAllRows();
           $.each(allRow,function (index,item) {
               item.portfolioId = null;
               item.proAvailableCash = 0;
               item.proAvailableCash1 = 0;
               item.secAcctAvailableCash = 0;
               item.porAvailableSec = 0;
               item.entrustIntegrateSecNum = 0;
               item.ownPorAvailableSec = 0;
               item.ownProAvailableSec1 = 0;
               table.renderRow(item.LAY_TABLE_INDEX, item);
           })
        }

        })
    query("#tradeAmt").blur(function () {
        query("#tradeAmt").val(JoyinMath.doDiv(query("#tradeAmt").val(),1,2));
        query("#entrustOwnNum").val(JoyinMath.doDiv(query("#tradeAmt").val(),1,2));
        calAmountData();
    })
    query("#entrustIntegrateNum").blur(function () {
        var tradeAmt = query("#tradeAmt").val();
        var entrustIntegrateNum = query("#entrustIntegrateNum").val();
        if(entrustIntegrateNum >= tradeAmt) {
            Joyin.error("融入/卖空购回券面总额不能大于券面总额")
        }
        if(tradeAmt && entrustIntegrateNum) {
            query("#entrustOwnNum").val(JoyinMath.doMinus(tradeAmt,entrustIntegrateNum,2));
        }
        calAmountData();
    })

    query("input[name=calculateField]").click(function(){
        calflag = query("input[name=calculateField]:checked").val();
        query("#" + calflag +"Show").attr("disabled", false);
        if('price' == calflag) {
            query("#matureYieldShow").attr("disabled", true);
            query("#exerYieldShow").attr("disabled", true);
        }
        if('matureYield' == calflag) {
            query("#exerYieldShow").attr("disabled", true);
            query("#priceShow").attr("disabled", true);
        }
        if('exerYield' == calflag) {
            query("#matureYieldShow").attr("disabled", true);
            query("#priceShow").attr("disabled", true);
        }
        calAmountData();
    })

    query('#matureYieldShow').blur(function () {
        query('#matureYield').val(query('#matureYieldShow').val());
        calData('matureYield');
    })
    query('#exerYieldShow').blur(function () {
        query('#exerYield').val(query('#exerYieldShow').val());
        calData('exerYield');
    })
    query('#priceShow').blur(function () {
        if(ps != 'T03') {
            query('#price').val(query('#priceShow').val());
            calData('price');
        }
    })

    if(ps == 'T03') {//回售时特殊处理
        query("#calculateField_price").attr("th:title","行权净价");
        query("#settleSpeed").attr("disabled",true);
        query("#entrustIntegrateNum").attr("disabled",true);
        query("#calculateField_matureYield").attr("disabled", true);
        query("#calculateField_exerYield").attr("disabled", true);
        query("#priceModel").attr("disabled", true);
        layui.form.render('radio');
    }

}());
