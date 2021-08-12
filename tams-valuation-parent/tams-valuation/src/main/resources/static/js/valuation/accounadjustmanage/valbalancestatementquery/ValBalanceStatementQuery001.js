$(function () {

    var flag1 = false;
    var flag2 = false;
    if(hzSchemeSwitch == "01"){
        flag1 = true;
        flag2 = false;
    }else{
        flag1 = false;
        flag2 = true;
    }
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            booksetId: $('#booksetId').val()
            ,valDate: $('#valDate').val()
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        tree:true,
        treeId: 'id',
        treeUpId: 'parentId',
        treeShowName: 'id',
        lazyload:true,
        // height: 270,
        searchOnInit: false,
        checkbox: false,
        page : false,
        cols: [
            {field:'id', width:160, title: 'ValBalanceStatementQuery001.subjectno',render:function (data, type, row, meta) {
                    if(row.level == 1) {
                        return '<strong>' + data + '</strong>';
                    }
                    if (row.leaf==true){
                        return "<a id='mingxi' class='portfolioId-link' style='cursor: pointer;'>"+data+"</a>";
                    }
                    return data;
                }}
            ,{field:'name', width:300, title: 'ValBalanceStatementQuery001.subjectName', render: function (data, type, row, meta) {
                    if(row.level == 1) {
                        return '<strong>' + data + '</strong>';
                    }
                    return data;
                }}
            ,{field:'oCcyFmt', width:150, title: 'ValBalanceStatementQuery001.oCcyForGZB'}
            ,{field:'exchangeRate',type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.exchangeRate'}
            ,{field:'numAmt', type:"number", width:150, title: 'ValBalanceStatementQuery001.numAmt'}
            ,{field:'unitCost', type:"money", width:150, title: 'ValBalanceStatementQuery001.unitCost'}
            ,{field:'oCost', type:"money", width:150, title: 'ValBalanceStatementQuery001.oCost'}
            ,{field:'cost', type:"money", width:150, title: 'ValBalanceStatementQuery001.cost'}
            ,{field:'costPercent', type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.costPercent'}
            ,{field:'closePrice', type:"money", digit: 4, width:150, title: 'ValBalanceStatementQuery001.closePrice',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link closePriceLink' style='cursor: pointer;'>"+data+"</ a>";
                }
            }
            ,{field:'oMarketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.oMarketValue'}
            ,{field:'marketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.marketValue'}
            ,{field:'valuePercent',type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.valuePercent'}
            ,{field:'valueIncrement', type:"money", width:150, title: 'ValBalanceStatementQuery001.valueIncrement'}
            ,{field:'shadowPriceValue', visible:flag1, type:"money", width:150, title: 'ValBalanceStatementQuery001.shadowPriceValue'}
            ,{field:'marketDate',visible:true,width:150, title: 'ValBalanceStatementQuery001.marketDate'}
        ]
    });

    /**
     * 绑定数据溯源信息查看的按钮
     */
    table.bind('click', '#mingxi', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson('/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("数据溯源信息查看"),
                "/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002/init",
                "95%", "90%",data,function() {
                });
        });
    });

    /**
     * 行情价格链接
     */
    table.bind('click','.closePriceLink', function(row) {
        if (!row.marketDate){
            Joyin.alert("行情日期为空时，不能确定基础信息行情界面！");
            return;
        }
        var data = {
            subjectName: row.name,
            subjectno: row.id,
            booksetId: $('#booksetId').val(),
            valueDate: row.marketDate
        }
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/selectCorrespondingFrame', data,
            function (resultObj) {
                if (resultObj) {
                    //"F17" "计划类资产"
                    if (resultObj.finprodType === 'F17'){
                        //计划类资产行情信息
                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/valnetassetquotationinfo/ValNetAssetQuotationInfo002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                finprodId: resultObj.detailSubjectNoTail,
                                valDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }
                    //基金类资产行情信息
                    else if (resultObj.finprodType === 'F06'){
                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/valcoinfundquotationinfo/ValCoinFundQuotationInfo002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                finprodMarketId: resultObj.finprodMarketId,
                                valDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }
                    //股票行情信息
                    else if (resultObj.finprodType === 'F10'){
                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/valshareprice/ValSharePrice002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                sharesId: resultObj.finprodMarketId,
                                dealDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }
                    //债券行情信息
                    else if (resultObj.finprodType === 'F01'){
                        if (resultObj.valueSource) {
                            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                                basePath + "valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo002/init",
                                "95%", "80%", {
                                    pageModel: PAGE_MODEL_REFERENCE,
                                    secId: resultObj.finprodMarketId,
                                    valueSource: resultObj.valueSource,
                                    valueDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                                });
                        } else {
                            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                                basePath + "valuation/basedata/marketdata/bondmarketinfo/ValBondMarketInfo002/init",
                                "95%", "80%", {
                                    pageModel: PAGE_MODEL_REFERENCE,
                                    secId: resultObj.finprodMarketId,
                                    valueDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                                });
                        }
                    }
                    //外汇行情信息
                    else if (resultObj.finprodType === 'F11' || resultObj.finprodType === 'F12' || resultObj.finprodType === 'F13'){

                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/valfxvaluztioninfo/ValFxValuztionInfo002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                finprodId2: resultObj.finprodMarketId,
                                valmodelId:resultObj.valmodelId,
                                datasourceType: resultObj.datasourceType,
                                operateType:"view",
                                valueDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }
                    //利率互换估值信息
                    else if (resultObj.finprodType === 'F14'){
                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                finprodId2: resultObj.finprodMarketId,
                                datasourceType: resultObj.datasourceType,
                                operateType:"view",
                                valueDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }
                    // TODO 汇率行情信息[换汇] 无数据，待确定
                    else {
                        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "valuation/basedata/marketdata/valexchangerateinfo/ValExchangeQuotationInfo002/init",
                            "95%", "80%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                exchargeCode:resultObj.finprodMarketId,
                                tradeDate: (new Date(row.marketDate)).format('yyyy-MM-dd')
                            });
                    }

                }
            });
    });

    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', getPageParams(), function(result) {
            Joyin.exportExcel('export', getPageParams());
        });

    };
    var gradeChange=function(){
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''||booksetId==null){
            return;
        }else{
            $.ajax({
                url : 'checkBokkSetDate',
                type : "POST",
                data : {booksetId:booksetId},
                success: function(msg){
                    if(msg=='-1'){
                        $('#booksetId').val('').trigger('change');
                        layer.alert('所选账套的会计日期不一致，请分开执行!!');
                    }else{
                        $('#valDate').val(msg);
                        bookSetDate=msg;
                    }
                },
                failure : function() {
                }
            });
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        if(isInit){
            table.load();
        }
        // 绑定按钮事件
        $('#ValBalanceStatementQuery001_btn_search').click(function() {
            // 修复GZHSXT-1362：避免 输入不存在的账套，导致异常。
            var dataId = $("#booksetId").attr("data-id");// 从列表中选择的值
            var alt = $("#booksetId").attr("alt");// 当前输入的值
            if(dataId != alt){ // 输入不存在的账套
                $("#booksetId").val("");
            }

            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', getPageParams(), function(result) {
//              if(result.data){
//                  var data = result.data;
//                  if(data.finprodAbbr){
//                      $("#prod").html(data.finprodAbbr+" / "+result.data.finprodId);
//                  }
//                  else{
//                      $("#prod").html(data.finprodName+" / "+result.data.finprodId);
//                  }
//                  var date = $("#valDate").val();
//                  var datearr = date.split("-");
//                  $("#valueDate").html(datearr[0]+"年"+datearr[1]+"月"+datearr[2]+"日");
//                  $("#prodInfo").show();
//              }else{
//                  $("#prodInfo").hide();
//              }
                // 若返回的结果存在，则在账套配置表存在模板标志
                if(result.data){
                    if(result.data == "01"){
                        flag1 = true;
                        flag2 = false;
                    }else{
                        flag1 = false;
                        flag2 = true;
                    }
                }
                // 不存在，则用系统参数中的模板开关控制
                else{
                    if(hzSchemeSwitch == "01"){
                        flag1 = true;
                        flag2 = false;
                    }else{
                        flag1 = false;
                        flag2 = true;
                    }
                }
//              table.load();
//              tableSum.load();
//              $("#tableSumDiv").show();
//                 if(!tableSum.table) {
//                     tableSum.load();
//                     $("#tableSumDiv").show();
//                 }
//                 else {
//                     tableSum.reload();
//                     $("#tableSumDiv").show();
//                 }
                if(!table.table) {
                    table.load();
                }
                else {
                    table.reload();
                }
                // 根据标志位显示影子定价还是行情日期
                table.showColumns([
                    //{field:'marketDate', visible: flag2},
                    {field:'shadowPriceValue', visible: flag1}]);
            });
        });
        $('#ValBalanceStatementQuery001_btn_export').click(function() {
            exportExcel();
        });
        $('#booksetId').change(function(){
            gradeChange();
        });
    });
});
