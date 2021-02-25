/**
 * 风险试算
 */
(function() {
    _.each(RiskTrialRetDto, function(item) {
        item.supDate = (new Date(item.supDate)).format('yyyy-MM-dd');
    });
    var param = null;
    var getElementById = function(id) {
        return $('#' + entityName + '_' + id);
    };
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        param.pageModel = pageModel;
        return param;
    }

    /**
     * 定义数据表格
     */
    var cols = [
        {field:'portfolioId',  title: '组合代码', width:350,sortable:false}
        ,{field:'indName',  title: '指标名称', width:350,sortable:false}
        ,{field:'supDate',  title: '日期', type: "date", width:100,sortable:false}
        ,{field:'warnCond',  title: '预警阈值', width:200,sortable:false,render: transferWarnFoul}
        ,{field:'foulCond',  title: '违规阈值', width:200,sortable:false,render: transferWarnFoul}
        ,{field:'supVal',  title: '当前实际值',width:150,sortable:false,render: transferSupVal}
        ,{field:'supStat',  title: '状态',sortable:false,
            render: function (data, type, row, meta) {
                if(row.supStat == "NORMAL"){
                    return "<span>正常</span>";
                }
                if(row.supStat == "VIOLATION"){
                    return "<span style='color:red'>违规</span>";
                }
                if(row.supStat == "WARNING"){
                    return "<span style='color:orange'>预警</span>";
                }
                return row.supStat;
            }
        },
        {field:'totalConclu', visible: false}
    ];

    function transferSupVal(data, type, row, meta){
        var wg=data;
        if(wg==''){
            return wg;
        }
        if(row.indType.indexOf("FORBID_")!=-1){
            /* 禁止型 */
            //------update by jjpei 20190812----解析禁投项------
            var secCell=false;
            if(wg.indexOf("||")!=-1){
                secCell=true;
            }
            var wgArr=wg.split(",");
            var wgStr='';
            for(var m=0;m<wgArr.length;m++){
                if(wgArr[m]!=null&&wgArr[m]!='') {
                    if(secCell) {
                        var wgArrSub = wgArr[m].split("||");
                        if(wgArrSub.length>1)
                            wgStr += wgArrSub[1]+",";
                        else
                            wgStr += wgArrSub[0]+",";
                    }else{
                        wgStr += wgArr[m]+",";
                    }
                }
            }
            wg=wgStr.substring(0,wgStr.length-1);
            //------update by jjpei 20190812----解析禁投项------
        }
        else{
            wg = String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1");
            wg = Number(wg);
            var detailJson = JSON.parse(row.foulCond);
            var curunit=valueConvertUnit(detailJson.threshold_1.unit);
            if(detailJson.threshold_1.unit=='PERCENT'){
                wg=toDecimal(wg*100)+curunit;
            }
            else if(detailJson.threshold_1.unit=='WRMB'){
                wg=String(new Number(wg/10000)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
            }
            else{
                wg=String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
            }
        }
        return wg;
    }
    function toDecimal(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return;
        }
        f=JoyinMath.doMul(f, 1, 4)
        //f=Number(String(new Number(f)).replace(/^(.*\..{4}).*$/,"$1"));
        //f = Math.round(x*100)/100;
        return f;
    }
    function transferWarnFoul(data, type, row, meta){
        var lnkNo="";
        var wg="";var detail = "";
        if(data==''){
            return wg;
        }
        var detailJson = JSON.parse(data);
        if(detailJson!=null){
            /* 限制型 */
            detail = html2Escape(data);
            if(row.indType.indexOf("LIMIT_")!=-1){
                if(detailJson.threshold_2.lnk==lnkNo){
                    if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
                        wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
                    }
                }else{
                    wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit)+detailJson.threshold_2.lnk+detailJson.threshold_2.op+detailJson.threshold_2.value+valueConvertUnit(detailJson.threshold_2.unit);
                }

            }else{
                /* 禁止型 */
                //------update by jjpei 20190812----解析禁投项------
                wg = detailJson.threshold_1.value_cn;
                /*var wgArr=wg.split(",");
                var wgStr='';
                for(var m=0;m<wgArr.length;m++){
                    if(wgArr[m]!=null&&wgArr[m]!='')
                        wgStr+=StringToChinese(wgArr[m],row.indType)+",";
                }*/
                wg=wg.substring(0,wg.length-1);
                //------update by jjpei 20190812----解析禁投项------
            }
        }
        return wg;
    }
    /**
     * 单位转换
     */
    function valueConvertUnit(value){
        if(value==''){
            return value;
        }
        var retStr;
        var supUnitArr = JSON.parse(SUP_UNIT);
        $.each(supUnitArr,function(i,v){
            if(v.id==value){
                retStr=v.text;
                return false;
            }
        })
        return retStr;
    };

    function StringToChinese(id,type){
        var arr=[];
        var totalIndTypeEnum=JSON.parse(IND_TYPE_TOTAL_ENUM);
        var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
        if(null!=totalIndTypeEnum&&null!=arrFobEnum){
            arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text});
        }
        if(arr==[]||arr.length==0)
            return id;
        else
            return arr[0].text;
    };
    /* 普通字符转换成转意符 */
    var html2Escape = function(sHtml){
        return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
    };
    var table = $("#resoluteRiskMathCommTable").joyintable({
        data:RiskTrialRetDto==null?[]:RiskTrialRetDto,
        cols : cols,
        searchOnInit:true,
//        loadonce : true,
        checkbox : false,
        height : 200,
        page:false,
        done: function(table, allData){
            if(table[0] && table[0].totalConclu && table[0].totalConclu ){
                $("#riskTrialSpan").html("&nbsp;&nbsp;"+table[0].totalConclu);
            }
        }
    });
    /**
     * 风险试算{}
     */
    var initBaseRiskMathCommPage = function(initParam){

        param = initParam;
        //---------add by 王贵奇 20190819---组合风险试算---start--------
        if(param!=null&&Array.isArray(param)){
            var results = [];
            for(var i in param){
                //---添加交易实体dto----start---20190816-----
                param[i].data.trdProductDeal=$.serializeToObject("#"+$('form').prop('id'), true);
                if(param[i].data.timePoint=='01'&&param[i].data.trdProductDeal) {
                    if(param[i].data.trdProductDeal.entrustAmt)
                        param[i].data.trdProductDeal.tradeAmt = param[i].data.trdProductDeal.entrustAmt;
                    if(param[i].data.trdProductDeal.entrustNum)
                        param[i].data.trdProductDeal.shareAmt = param[i].data.trdProductDeal.entrustNum;
                    if(param[i].data.trdProductDeal.price)
                        param[i].data.trdProductDeal.unitCprice = param[i].data.trdProductDeal.price;
                }
                //---添加交易实体dto----end----20190816------
                var object1 = param[i];
                Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/check', object1, function() {
                    //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                    if(workflowBaskRiskId && workflowBaskRiskId != 'null') {
                        initParam[i].data.tradeCode = workflowBaskRiskId;
                    }
                    else{
                        initParam[i].data.tradeCode = 'null';
                    }
                    //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                    Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/riskMath', object1.data, function(result) {
                        $("#riskTrialSpan").empty();
                        $("#riskMathFlag").val("02");//---add by 裴家俊 2019/03/20----置为已点击风险试算---
                        var rs = table.getAllRows();
                        if (rs) {
                            rs.splice(0,rs.length);
                        }
                        var cluflag=true;
                        //不显示状态正常的指标
                        for(var row in result){
                            if(result[row].supStat!="NORMAL"){
                                results.push(result[row]);
                            }
                        }
                        // if(result.length>0)
                        //     results.push(result[0]);
                        for(var row in result){
//                            table.addRow(result[row]); // zhangrui 风险性能优化
                            if(result[row].supStat=="VIOLATION"){
                                cluflag=false;
                                break;
                            }
                        }
                        if(!cluflag){
                            $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.noclusion"))
                            var cacheData = table.getRow(0, true);
                            cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.noclusion");
                        }
                        else{
                            $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.clusion"))
                            var cacheData = table.getRow(0, true);
                            cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.clusion");
                        }
                    },false);
                    //---add by xkni 2019/08/27----获取授信使用条件---
                    if(object1.data["assetCode"] != undefined&&object1.data.assetCode.length>1 ) {
                        Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/getCreditConditionList', object1, function (result) {
                            $("#riskTrialSpan").html($("#riskTrialSpan").html() + "!" + "<span style=\"color:#F00\"> " + result.data + "</span>");
                            // $("#riskTrialSpan").after(result.data);
                        });
                    }
                },false);
            }
            // zhangrui 风险性能优化 start
            table.reload({data:results});
            // zhangrui 风险性能优化 end
        }else{
            //---------add by 王贵奇 20190819---组合风险试算---end--------
            //---添加交易实体dto----start---20190816-----
            var results = [];
            initParam.data.trdProductDeal=$.serializeToObject("#"+$('form').prop('id'), true);
            if(initParam.data.timePoint=='01'&&initParam.data.trdProductDeal) {
                if(initParam.data.trdProductDeal.entrustAmt)
                    initParam.data.trdProductDeal.tradeAmt = initParam.data.trdProductDeal.entrustAmt;
                if(initParam.data.trdProductDeal.entrustNum)
                    initParam.data.trdProductDeal.shareAmt = initParam.data.trdProductDeal.entrustNum;
                if(initParam.data.trdProductDeal.price)
                    initParam.data.trdProductDeal.unitCprice = initParam.data.trdProductDeal.price;
            }
            //---添加交易实体dto----end----20190816------
            Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/check', initParam, function() {
                //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                if(workflowBaskRiskId && workflowBaskRiskId != 'null') {
                    initParam.data.tradeCode = workflowBaskRiskId;
                }
                else{
                    initParam.data.tradeCode = 'null';
                }
                //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/riskMath', initParam.data, function(result) {
                    $("#riskTrialSpan").empty();
                    $("#riskMathFlag").val("02");//---add by 裴家俊 2019/03/20----置为已点击风险试算---
                    var rs = table.getAllRows();
                    // zhangrui 风险性能优化 start
                    //                for(var i=rs.length-1; i >= 0;i--){
                    //                    table.deleteRow(i);
                    //                }
                    if (rs) {
                        rs.splice(0,rs.length);
                    }
                    //不显示状态正常的指标
                    for(var row in result){
                        if(result[row].supStat!="NORMAL"){
                            results.push(result[row]);
                        }
                    }
                    var cluflag=true;
                    for(var row in result){
                        //                    table.addRow(result[row]); // zhangrui 风险性能优化
                        if(result[row].supStat=="VIOLATION"){
                            cluflag=false;
                            break;
                        }
                    }
                    // zhangrui 风险性能优化 start
                    table.reload({data:results});
                    // zhangrui 风险性能优化 end
                    if(!cluflag){
                        $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.noclusion"))
                        var cacheData = table.getRow(0, true);
                        cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.noclusion");
                    }
                    else{
                        $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.clusion"))
                        var cacheData = table.getRow(0, true);
                        cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.clusion");
                    }
                    //sjj add 20190409平安需求变更风险试算 start
                    /*  if(result && result.length > 0){
                          resultCommon = result;
                      }
                      Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm.riskAndCredit"),
                              basePath + "component/common/RiskMathAndCredit/init",
                              "90%", "90%");*/
                    //sjj add 20190409平安需求变更风险试算 end
                });
                //---add by xkni 2019/08/27----获取授信使用条件---

                if(initParam.data["assetCode"] != undefined&&initParam.data.assetCode.length>1 ) {
                    Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/getCreditConditionList', initParam, function (result) {
                        $("#riskTrialSpan").html($("#riskTrialSpan").html() + "!" + "<span style=\"color:#F00\"> " + result.data + "</span>");
                        //  $("#riskTrialSpan").after(result.data);
                    });
                }
            });
        }
    }

    /**
     * 风险试算{多组合}
     */
    var initBaseRiskMathCommPage2 = function(initParam){
        var results=[];
        param = initParam;
        var msg = "";
        //add by zhanggenglin 2019-08-13 start 质押式回购多组合新增
        if(initParam!=null&&initParam.data!=null&&initParam.data.length>1){
            var object1=initParam;
            var list2=initParam.data;
            delete object1.data;
            for(var i in list2){
                object1.data=list2[i];
                //---------add by 王贵奇 20190819---组合风险试算---end--------
                //---添加交易实体dto----start---20190816-----
                object1.data.trdProductDeal=$.serializeToObject("#"+$('form').prop('id'), true);
                if(object1.data.timePoint=='01'&&object1.data.trdProductDeal) {
                    if(object1.data.trdProductDeal.entrustAmt)
                        object1.data.trdProductDeal.tradeAmt = object1.data.trdProductDeal.entrustAmt;
                    if(object1.data.trdProductDeal.entrustNum)
                        object1.data.trdProductDeal.shareAmt = object1.data.trdProductDeal.entrustNum;
                    if(object1.data.trdProductDeal.price)
                        object1.data.trdProductDeal.unitCprice = object1.data.trdProductDeal.price;
                }
                //---添加交易实体dto----end----20190816------
                Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/check', object1, function() {
                    object1.data=list2[i];
                    i=i-1;
                    //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                    if(workflowBaskRiskId && workflowBaskRiskId != 'null') {
                        object1.data.tradeCode = workflowBaskRiskId;
                    }
                    else{
                        object1.data.tradeCode = 'null';
                    }
                    //---add by jjpei 20190801--------去除当前这笔流程---暂时塞进tradeCode----
                    Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/riskMath', object1.data, function(result) {
                        $("#riskTrialSpan").empty();
                        $("#riskMathFlag").val("02");//---add by 裴家俊 2019/03/20----置为已点击风险试算---
                        var rs = table.getAllRows();
                        if (rs) {
                            rs.splice(0,rs.length);
                        }
                        //不显示状态正常的指标
                        for(var row in result){
                            if(result[row].supStat!="NORMAL"){
                                results.push(result[row]);
                            }
                        }
                        var cluflag=true;
                        for(var row in result){
//                            table.addRow(result[row]); // zhangrui 风险性能优化
                            if(result[row].supStat=="VIOLATION"){
                                cluflag=false;
                                break;
                            }
                        }
                        if(!cluflag){
                            $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.noclusion"))
                            var cacheData = table.getRow(0, true);
                            cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.noclusion");
                            msg = "&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.noclusion");
                        }
                        else{
                            $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.clusion"))
                            var cacheData = table.getRow(0, true);
                            cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.clusion");
                        }
                    },false);
                    //---add by xkni 2019/08/27----获取授信使用条件---
                    if(object1.data["assetCode"] != undefined&&object1.data.assetCode.length>1 ) {
                        Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/getCreditConditionList', object1, function (result) {
                            //  $("#riskTrialSpan").after(result.data);
                            $("#riskTrialSpan").html($("#riskTrialSpan").html() + "!" + "<span style=\"color:#F00\"> " + result.data + "</span>");
                        });
                    }
                },false);
            }
        }
        // if(results.length>0){
        table.reload({data:results});
        if(msg != "") {
            $("#riskTrialSpan").html(msg);
        }
        param.data = list2;
        // }
    }
    //sjj add 20190409平安需求变更风险试算 start
    /**
     * 风险试算债券{}
     */
    var initBaseRiskMathSecPage = function(initParam){
        param = initParam;
        Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/check', initParam, function() {
            Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/riskMath', initParam.data, function(result) {
                $("#riskTrialSpan").empty();
                $("#riskMathFlag").val("02");//---add by 裴家俊 2019/03/20----置为已点击风险试算---
                var rs = table.getAllRows();
                // zhangrui 风险性能优化 start
//                for(var i=rs.length-1; i >= 0;i--){
//                    table.deleteRow(i);
//                }
                if (!rs) {
                    rs.splice(0,rs.length);
                }
                // zhangrui 风险性能优化 end
                if(result.length==0){
                    return;
                }
                var cluflag=true;
                // zhangrui 风险性能优化 start
                table.reload({data:result});
                // zhangrui 风险性能优化 end
                for(var row in result){
//                    table.addRow(result[row]); // zhangrui 风险性能优化
                    if(result[row].supStat=="VIOLATION"){
                        cluflag=false;
                        break;
                    }
                }
                if(!cluflag){
                    $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.noclusion"))
                    var cacheData = table.getRow(0, true);
                    cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.noclusion");
                }
                else{
                    $("#riskTrialSpan").html("&nbsp;&nbsp;"+JoyinUtil.getLabel("BaseRiskMathComm.clusion"))
                    var cacheData = table.getRow(0, true);
                    cacheData.totalConclu = JoyinUtil.getLabel("BaseRiskMathComm.clusion");
                }
            });
        });
    }

    var insert = function(){
        var rows = {};
        var row = table.getAllRows();
        if(row){
            rows = row;
        }
        Joyin.ajaxJson(basePath + 'invest/trade/transfer/common/ResoluteRiskMathComm/insert',rows);
    }

    var saveOrSubmitCheck = function(mes){
        if(mes == 'save'){
            mes = JoyinUtil.getLabel("SysCommon.save");
        }else if(mes == 'submit'){
            mes = JoyinUtil.getLabel("SysCommon.submit");
        }
        //---add by 裴家俊 2019/03/20  风险试算点击判断------
        //----modify by 裴家俊 2019/03/30  风险试算点击判断----start----
        if($("#riskMathFlag").val()=='01'&& risk_math_switch=='01' && (!workflowBaskRiskId || workflowBaskRiskId == 'null')){
            //内部交易对手不需要风险测试
            var paramConterId=["etrInvestPersonInfo.counterId","bussEtrInvestPersonInfoDto.counterId","trdInvestPersonInfo.counterId","trdInvestPersonInfo002Dto.counterId"];
            var counterType=findCounterType(paramConterId);
            if(counterType != 'N'){
                Joyin.alert(JoyinUtil.getMessage('ETR_X_013'),2);
                return ;
            }
        }
        //----modify by 裴家俊 2019/03/30  风险试算点击判断----end----
        if(ResoluteRiskMathComm && $('#riskTrialSpan')){
//            if(!$('#riskTrialSpan').html() || $('#riskTrialSpan').html() ==''){
//                Joyin.alert(JoyinUtil.getLabel('BaseRiskMathComm.riskCheck')+mes,2);
//                return;
//            }else
            if($('#riskTrialSpan').html().indexOf(JoyinUtil.getLabel('BaseRiskMathComm.violation'))!=-1 && risk_math_switch=='01'){
                //判断是否符合分控场景
                if(checkDiffSubmitScene()) {
                    Joyin.alert(JoyinUtil.getLabel('BaseRiskMathComm.violationAndSubmit') + mes, 2, function () {
                        return;
                    });
                    return;
                }
                else{
                    return '';
                }
            }else if($('#riskTrialSpan').html() == JoyinUtil.getLabel('BaseRiskMathComm.clusion')){
                return '';
            }else{
                return '';
            }
        }
    }
    /***
     * 检查风险试算是否满足分控场景
     */
    var checkDiffSubmitScene=function(){
        //默认禁止提交
        var forbidSubFlag=true;
        var rs = table.getAllRows();
        //交易方向，卖出_赎回_行权回售
        var psOutArr=["T02","T03","T06"];
        //交易方向，买入_申购_配股_新股中签_增发配售
        var psInArr=["T01","T04","T46","T47","T48"];
        /*  //交易方向，正逆回购_拆入拆出_借贷_即远掉
          var psFobArr=["T15","T16","T17","01","02","03","04","05","06"];*/
        //行权赎回，不受限制
        if(param.data.tradeDirection=="T39"){
            return false;
        }
        //内部交易对手(N)也是不受限制
        var paramConterId=["etrInvestPersonInfo.counterId","bussEtrInvestPersonInfoDto.counterId","trdInvestPersonInfo.counterId","trdInvestPersonInfo002Dto.counterId"];
        var counterType=findCounterType(paramConterId);
        if(typeof counterType != 'undefined'&&counterType=="N"){
            return false;
        }
        $.each(rs, function(id, value){
            var detailJson=JSON.parse(value.foulCond);
            if(value.supStat!="VIOLATION"){
                return true;//找下一个违规的
            }
            if(detailJson.threshold_1.op.indexOf(">=")>=0||detailJson.threshold_1.op.indexOf(">")>=0){
                if($.inArray(param.data.tradeDirection,psOutArr)>=0){
                    forbidSubFlag=false;
                }
                else{
                    forbidSubFlag=true;
                }
            }
            else{
                if($.inArray(param.data.tradeDirection,psInArr)>=0){
                    forbidSubFlag=false;
                }
                else{
                    forbidSubFlag=true;
                }
            }
            if(forbidSubFlag){
                return false;//若存在有一条不符合分控条件直接跳出循环
            }
        });
        return forbidSubFlag;
    }

    var findCounterType = function(list){
        var retType="Y";
        $.each(list,function(i,el){
            if($("select[name='"+el+"']").length>0) {
                if (typeof $("select[name='" + el + "']").select2('data') != 'undefined' && typeof $("select[name='" + el + "']").select2('data')[0].object != 'undefined') {
                    retType = $("select[name='" + el + "']").select2('data')[0].object;
                    return false;
                }
            }
        })
        return retType;
    };

    /***
     * 获取各委托及交易传递的参数
     */
    var getparam = function (data) {
        param = data ;
    }

    // 检查生成风险试算参数是否发生变化
    var isCalcConditionChanged = function(newdata) {
        if (risk_math_switch != '01'){
            return false;
        }
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if(newdata != null){
            // 组合风险试算参数更改检验	王贵奇	2019/8/19	开始
            if(Array.isArray(newdata)){
                if(param.length != newdata.length){
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013", ["风险生成要素", "生成风险试算"]));
                    return true;
                }
                for(var i in newdata){
                    if (param[i].data == null) {
                        return false;
                    }
                    var currentCalcConditionObj = newdata[i].data;
                    var calcConditionObjBackUp = param[i].data;
                    if (calcConditionObjBackUp == null) {
                        return false;
                    }
                    // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
                    for ( var key in currentCalcConditionObj) {
                        if (typeof currentCalcConditionObj[key] != "object"
                            && currentCalcConditionObj[key] != calcConditionObjBackUp[key]) {
//                            var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key);
                            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                ["风险生成要素", "生成风险试算"]));
                            return true;
                        }
                    }
                }
                // 组合风险试算参数更改检验	王贵奇	2019/8/19	开始
            }else{
                if (param.data == null) {
                    return false;
                }
                var currentCalcConditionObj = newdata.data;
                var calcConditionObjBackUp = param.data;
                var flag = false;
                // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
                if(Array.isArray(currentCalcConditionObj) && Array.isArray(calcConditionObjBackUp)){
                    if(currentCalcConditionObj.length == calcConditionObjBackUp.length){
                        currentCalcConditionObj.sort(function (a,b) {
                            return a.portfolioCode.localeCompare(b.portfolioCode);
                        });
                        calcConditionObjBackUp.sort(function (a,b) {
                            return a.portfolioCode.localeCompare(b.portfolioCode);
                        });
                        for(var key in currentCalcConditionObj){
                            var orig = calcConditionObjBackUp[key];
                            var now = currentCalcConditionObj[key];
                            for ( var key in now) {
                                if (typeof now[key] != "object"
                                    && now[key] != orig[key]) {
                                    flag = true;
                                    break;
                                }
                            }
                            if(flag){
                                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                    ["风险生成要素", "生成风险试算"]));
                                return true;
                            }
                        }
                    }
                    else {
                        flag = true;
                    }
                }
                else if(Array.isArray(currentCalcConditionObj) || Array.isArray(calcConditionObjBackUp)){
                    flag = true;
                }
                else {
                    for ( var key in currentCalcConditionObj) {
                        if (typeof currentCalcConditionObj[key] != "object"
                            && currentCalcConditionObj[key] != calcConditionObjBackUp[key]) {
//                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key);
                            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                                ["风险生成要素", "生成风险试算"]));
                            return true;
                        }
                    }
                }
                if(flag){
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                        ["风险生成要素", "生成风险试算"]));
                    return true;
                }
            }
        }
    };

    window.ResoluteRiskMathComm = {
        /**
         * 输入函数范例，需提供实时监控点timePoint,交易对应：'02',委托：'01'，事后：'03'对应的数据字典枚举值如：监督时分_交易监控
         * {timePoint:'01','valDate':'2018-01-01','portfolioCode':'123456','tradeAmount':111,assetType:'01',assetCode:'214'}
         */
        initBaseRiskMathCommPage: initBaseRiskMathCommPage,
        initBaseRiskMathCommPage2: initBaseRiskMathCommPage2,
        saveOrSubmitCheck:saveOrSubmitCheck,
        isCalcConditionChanged:isCalcConditionChanged,
        getparam:getparam
    };

    /*吉林银行风险试算-流程控制显示   add by caojin   2019/09/23   start*/
    if( $("#bankCode").val() == "004") {
        Joyin.ajaxJson(basePath + "invest/trade/transfer/common/ResoluteRiskMathComm/selectRole", {} , function(result) {
            if(result=="1"){
                $("#FundAccountInfo_DIV1").hide();
                $("#FundAccountInfo_DIV2").hide();
                $("#FundAccountInfo_DIV3").hide();
            }
            if(result=="2"){
                $("#FundAccountInfo_DIV1").show();
                $("#FundAccountInfo_DIV2").show();
                $("#FundAccountInfo_DIV3").show();
            }
        });

        $("#ResoluteRiskMathComm_btn_riskControl").click(function () {
            if($("#FundAccountInfo_DIV1").css("display")=="block"){
                $("#FundAccountInfo_DIV1").hide();
                $("#FundAccountInfo_DIV2").hide();
                $("#FundAccountInfo_DIV3").hide();
            }else{
                $("#FundAccountInfo_DIV1").show();
                $("#FundAccountInfo_DIV2").show();
                $("#FundAccountInfo_DIV3").show();
            }
        });
    }
    /*吉林银行风险试算-流程控制显示   add by caojin   2019/09/23   end*/
}());