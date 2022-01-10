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

    var businessModelReulst="";
    var entityBussCombDistritList;


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
     * 风险试算时不需要进行风险试算的校验
     */
    var isNeedRiskcal = function (){
        //质押券入库和出库不需要走风险试算
        ps = query("#ps").val();
        return true;
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
            field: 'portfolioName', width: 100, title: 'CfetsEtribBondInfo003.portfolioId',  sortable: false
            //,edit: true
            ,disabled:true
           /* ,editOption: {
                type: 'select',
                id: 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }*/
        }
        , {/*业务模式*/
              field: 'busiModelStr',
              width: 150,
              title: 'CfetsEtribBondInfo003.businessModel',
              sortable: false,
              editOption:{type:'select', formatoption:true, showField: 'text',result: BUSINESS_MODEL}
       }
        , {
            /*劵面总额*/
            field: 'entrustNum',
            width: 150,
            title: 'CfetsEtribBondInfo003.entrustAmt',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            /*本方券券面总额（万元）*/
            field: 'entrustOwnNum',
            width: 150,
            title: 'CfetsEtribBondInfo003.entrustOwnNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'entrustIntegrateNum',
            width: 150,
            title: 'CfetsEtribBondInfo003.entrustIntegratCasheNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
      , {
            field: 'proAvailableCash',
            width: 200,
            title: 'CfetsEtribBondInfo003.proAvailableCash',
            sortable: false,
            type: 'number',
            digit: 2
        }
      , {
            field: 'proAvailableCash1',
            width: 200,
            title: 'CfetsEtribBondInfo003.proAvailableCash1',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'secAcctAvailableCash',
            width: 200,
            title: 'CfetsEtribBondInfo003.secAcctAvailableCash',
            sortable: false,
            type: 'number',
            digit: 2,
        }
       , {
             field: 'porAvailableSec',
             width: 200,
             title: 'CfetsEtribBondInfo003.porAvailableSec',
             sortable: false,
             visible:SHORT_SELLING_SUPPORTED == '02'?false:true,
             type: 'number',
             digit: 2
         }
         , {
             field: 'porAvailableSec1',
             width: 200,
             title: 'CfetsEtribBondInfo003.porAvailableSec1',
             sortable: false,
             visible:SHORT_SELLING_SUPPORTED == '02'?false:true,
             type: 'number',
             digit: 2,
         }
       , {
            field: 'entrustIntegrateSecNum',
            width: 200,
            title: 'CfetsEtribBondInfo003.entrustIntegrateSecNum',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
       , {
            field: 'ownPorAvailableSec',
            width: 200,
            title: 'CfetsEtribBondInfo003.ownPorAvailableSec',
            sortable: false,
            visible:SHORT_SELLING_SUPPORTED == '02'?true:false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'ownProAvailableSec1',
            width: 200,
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
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
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


    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            // 代办事项中 行权收益率置灰问题
            let exerYield = query('#exerYield');
            exerYield.attr('disabled', true).removeAttr("required");
            query('#calculateField_exerYield').attr('disabled', true);
            var finprodId = query("#finprodId").val();
            if (finprodId) {
                var data = query("#finprodId").select2("data")[0].object;
                query("#isRight").val(data.isRight).trigger("change");
            }
            if(query("#isRight").val() === Y && query("#priceModel").val() !== PRICEMODEL_03){
                // 含权债或为空 且 价格模式不是市价
                exerYield.attr('disabled', false).removeAttr("required").trigger("change").trigger("blur");
                query('#calculateField_exerYield').attr('disabled', false);
            }
            calDeviationDegree();
        }
    };


    var init = function () {
        query("#calculateField_price").prop('checked', true);
        layui.form.render('radio');
        if(calculateField){
            query("#calculateField_" + calculateField).attr("checked", true);
            layui.form.render('radio');

        }
        var restTradeAmt = query("#tradeAmt").val()==null?0:query("#tradeAmt").val();
        query("#tradeAmt").val(restTradeAmt/10000).trigger("change");
        var restEntrustOwnNum=query("#entrustOwnNum").val()==null?0:query("#entrustOwnNum").val();
        query("#entrustOwnNum").val(restEntrustOwnNum/10000).trigger("change");
        var restEntrustIntegrateNum=query("#entrustIntegrateNum").val()==null?0:query("#entrustIntegrateNum").val();
        query("#entrustIntegrateNum").val(restEntrustIntegrateNum/10000).trigger("change");
      /*  if(query("#matureYield").val()){
            query("#matureYield").val(JoyinMath.doMul(query("#matureYield").val(), 100, 4)).trigger("change");
        }
        if(query("#exerYield").val()){
            query("#exerYield").val(JoyinMath.doMul(query("#exerYield").val(), 100, 4)).trigger("change");
        }*/
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

    /**
     * 获取提交的数据
     * @param operateType
     * @returns {{}}
     */
    function getSubmitParam(operateType) {
        var res = {}
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = query('#select2-finprodId-container').attr('title');
        }
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
            tradeAmt += parseFloat(item.entrustNum);
            if(item.entrustNum&&item.entrustNum!=''){
                item.entrustNum=item.entrustNum * 10000;
              //  temp.entrustNum = item.entrustNum;
            }
            if(item.entrustOwnNum&&item.entrustOwnNum!=''){
                item.entrustOwnNum=item.entrustOwnNum * 10000;
              //  temp.entrustOwnNum = item.entrustOwnNum;
            }
            if(item.entrustIntegrateNum&&item.entrustIntegrateNum!=''){
                item.entrustIntegrateNum=item.entrustIntegrateNum * 10000;
             //   temp.entrustIntegrateNum = item.entrustIntegrateNum;
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
       /* if(tradeAmt && tradeAmt !== parseFloat(object.tradeAmt)){
            JoyinUtil.showMessage("各组合分配的券面总额汇总不等于总的券面总额!", "error")
            return null;
        }*/
        param = JSON.parse(JSON.stringify(object));
       // param.entrustNum = object.tradeAmt;
        param.finprodId = finprodId;
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

    function saveOrSubmit(operateType) {
        var s;
        var res = CfetsEtrIbBondinfo003.getSubmitParam(operateType)
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
        saveOrSubmit('submit');
    });

    // 绑定保存按钮
    $('#CfetsEtrIbBondinfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });

}());
