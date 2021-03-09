/**
 * ChannelFeePay001 通道资产查询
 */
$(function() {

    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                if (!$("#settleDate").val()) {
                    Joyin.error("请输入支付日")
                    return;
                }
                var feeInfoList = feeInfoTable.getAllRows();
                // if(!feeInfoList || feeInfoList.length == 0){
                //     Joyin.error("费用明细不能为空");
                // }
                list = new Array();
                for(var i in feeInfoList){
                    var deal = {};
                    deal.ccy = $("#ccy").val();
                    deal.tradeAmt = feeInfoList[i].actPayAmt;
                    deal.settleDate = $("#settleDate").val();
                    deal.ps = T10;
                    deal.tradeDate = $("#settleDate").val();
                    deal.finprodType2 = F28;
                    var data = $("#finprodMarketId").find("option:selected").data("data");
                    deal.chlId = data.object;
                    deal.portfolioId = feeInfoList[i].portfolioId;
                    list.push(deal)
                }

                return {
                    'isTotalTrade': YN_N
                    , 'bankLendingType': null
                    , 'portfolioId': "dzh"
                    , 'deal': list
                };
            },true);
            if (formTodoList) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        }
    }

    var feeInfoTable = $('#fee_dataTable').joyintable({
        ajax : {
            url : JoyinUtil.getRealPath('/invest/trade/channel/feepay/ChannelFeePay002/queryPayFeeInfoList'),
            type : 'POST',
            data : function() {
                return {
                    tradeId:$('#tradeId').val(),
                    workflowId:workflowId
                };
            }
        },
        searchOnInit:true,
        addbtn: true,
        height: 300,
        page: false,
        checkbox: false,
        rowno: true,
        cols : [{field: 'portfolioId',width:230, title : '组合代码',edit:true,sortable:false,editOption:{type:'select',id:'booksetId',showField:'id',formatoption:true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?prod=true')}
        },{field : 'portFolioName',width:230,title : '组合名称',edit: true
        },{field : 'payAmt',width:120,title : '理论费用',type : 'number',textrange : '2',sortable:false,edit: true
        },{field : 'actPayAmt',width:120,title : '实付费用',type : 'number',textrange : '2',edit:true,sortable:false
        },{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']
        }],
        editCell : function(value, data, field, cell, column){
            if(field === 'portfolioId'){
                Joyin.ajaxSync(JoyinUtil.getRealPath('/invest/trade/channel/feepay/ChannelFeePay002/findPortfolioName'),{portfolioId:value}, function(result) {
                    data.portFolioName = result;
                })

                if($("#finprodMarketId").val() && $("#mdate").val()){
                    var dataChl = $("#finprodMarketId").find("option:selected").data("data");
                    // var dataPtl = $("#portfolioId").find("option:selected").data("data");
                    var chlId = dataChl.object ? dataChl.object || '' : '';
                    // var ptlId = dataPtl.id ? dataPtl.id || '' : '';
                    var param = {chlId: chlId, ptlId: value, mdate: $("#mdate").val()};
                    Joyin.ajaxSync(basePath + "invest/trade/channel/feepay/ChannelFeePay002/searchTheoryAmt", param,
                    function(result){
                        if (result) {
                            // $("#vdate").val(new Date(result.vdate).format('yyyy-MM-dd'));
                            // $("#theoryInt").val(result.tradeAmt).trigger('blur');
                            // $("#tradeAmt").val(result.tradeAmt).trigger('blur');
                            // $("#ccy").val(result.ccy).trigger('change');
                            data.payAmt = result.tradeAmt;
                            data.actPayAmt = data.payAmt;
                        }
                    });
                }
                feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
            }
            var totalAmt = '0';
            var allData = feeInfoTable.getAllRows();
            if (allData && allData.length > 0){
                $.each(allData, function(id, value){
                    totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
                });
            }
            $('#tradeAmt').val(totalAmt).trigger('change');
        },
        beforeEditCell : function(preVal, field, rowData) {
            if(field === 'portFolioName' || field === 'payAmt'){
                return false;
            }
            return true;
        }
    });

    feeInfoTable.bind("click",".delt",function (data,index){
        feeInfoTable.removeRow(data);
        var totalAmt = '0';
        var allData = feeInfoTable.getAllRows();
        if (allData && allData.length > 0){
            $.each(allData, function(id, value){
                totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
            });
        }
        $('#tradeAmt').val(totalAmt).trigger('change');
    })

    function saveOrSubmit(operateType) {
        // 划款指令参数变更校验
        if(window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()){
            return ;
        }
        // if (isCalcConditionChanged()) {
        //     return;
        // }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        var data = $("#finprodMarketId").find("option:selected").data("data");
        object.channelName = data.text;
        var finprodId = data.object ? data.object || '' : '';
        object.finprodId = finprodId;
        object.finprodMarketId = $("#finprodMarketId").val();
        object.chlId = finprodId;
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        Joyin.ajaxJson(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/save', object, "ChannelFeePay002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/channel/feepay/ChannelFeePay002/save', object, "ChannelFeePay002_btn_submit");
                }
            });
        });
    }

    var searchTheoryAmt = function() {
        //BS1.9
        //通道
        var dataChl = $("#finprodMarketId").find("option:selected").data("data");
        var chlId = dataChl.object ? dataChl.object || '' : '';
        var finprodMarketId = $("#finprodMarketId").val();
      //add by yujiaping 2020-12-22 TISHKBS-1617 【合作资产费用支付管理】所选合作资产费用收取方式设置为“回款前扣除”，计提结束日清空、置灰，不可填start
        Joyin.ajax(basePath + "invest/trade/channel/feepay/ChannelFeePay002/getCollectType", {finprodMarketId : finprodMarketId},
                function(result){
                    if (result == '01') {
                    	$('#mdate').val('').attr('disabled',true);                     	
                    }else{
                    	$('#mdate').attr('disabled',false);   
                    }
                });
        //add by yujiaping 2020-12-22 TISHKBS-1617 【合作资产费用支付管理】所选合作资产费用收取方式设置为“回款前扣除”，计提结束日清空、置灰，不可填end
        // if ($("#finprodMarketId").val() && $("#mdate").val() && $("#portfolioId").val()) {
        //     $("#vdate").val('');
        //     $("#settleDate").val($("#mdate").val());
        //     $("#theoryInt").val('').trigger('blur');
        //     $("#tradeAmt").val('').trigger('blur');
        //     // 给币种赋个默认值CNY by zhaodongdong 20191210 start
        //     //$("#ccy").val('').trigger('change');
        //     // 给币种赋个默认值CNY by zhaodongdong 20191210 end
        //     var param = {chlId: chlId, ptlId: ptlId, mdate: $("#mdate").val()};
        //     Joyin.ajax(basePath + "invest/trade/channel/feepay/ChannelFeePay002/searchTheoryAmt", param,
        //         function(result){
        //             if (result) {
        //                 $("#vdate").val(new Date(result.vdate).format('yyyy-MM-dd'));
        //                 $("#theoryInt").val(result.tradeAmt).trigger('blur');
        //                 $("#tradeAmt").val(result.tradeAmt).trigger('blur');
        //                 $("#ccy").val(result.ccy).trigger('change');
        //             }
        //         });
        // }
    }

    /**
     * 画面加载完成
     */
    $(function() {
        /**
         * 保存
         */
        $('#ChannelFeePay002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#ChannelFeePay002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $("#finprodMarketId").change(searchTheoryAmt);
        $("#mdate").change(searchTheoryAmt);
        $("#portfolioId").change(searchTheoryAmt);
    });
}());