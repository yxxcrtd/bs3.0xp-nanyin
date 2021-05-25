/**
 * 资金划拨管理
 */

(function() {
    //划款指令信息
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var rows = table.getAllRows();
                    if(!rows || rows.length == 0){
                        Joyin.error("明细信息不能为空！");
                        return;
                    }
                    var list = new Array();
                    // 付款账户
                    var payAcctNoList = new Array();
                    // 收款账户
                    var recAcctNoList = new Array();

                    for(var i in rows){

                        if ($("#myAcctNo").val()) {
                            var payAcctNo = {};
                            payAcctNo.id = $("#myAcctNo").val();
                            payAcctNo.text = $("#myAcctNo").text().trim();
                            payAcctNo.object = "isUsePageAcctTrue";
                            payAcctNoList[0] = payAcctNo;
                        }


                        if ($("#counterAcctNo").val()) {
                            var recAcctNo = {};
                            recAcctNo.id = $("#counterAcctNo").val();
                            recAcctNo.text = $("#counterAcctNo").text().trim();
                            recAcctNo.object = "isUsePageAcctTrue";
                            recAcctNoList[0] = recAcctNo;
                        }
                        var dealObj = {};
                        dealObj.finprodType2 = Fin_Type;
                        dealObj.settleDate = $("#settleDate").val();
                        dealObj.tradeDate = $("#settleDate").val();
                        dealObj.ps = $("#payType").val();
                        dealObj.ccy = $("#ccy").val();
                        dealObj.tradeAmt = rows[i].settleAmt;
                        var payType = $("#payType").val();
                        var acctNoList = new Array();
                        if (rows[i].acctNo) {
                            var acctNo = {};
                            acctNo.id = rows[i].acctNo;
                            acctNo.text = rows[i].acctName.trim();
                            acctNo.object = "isUsePageAcctTrue";
                            acctNoList[0] = acctNo;
                        }
                        if(payType==payType1001){
                            dealObj.payAcctNoList = acctNoList;
                            dealObj.recAcctNoList = recAcctNoList;
                        }else{
                            dealObj.payAcctNoList = payAcctNoList;
                            dealObj.recAcctNoList = acctNoList;
                        }
                        dealObj.isUsePageAcct = true;
                        dealObj.portfolioId = rows[i].portfolioId;
                        list.push(dealObj);
                    }
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        , 'bankLendingType':null
                        ,'calendarId': '08'
                        , 'portfolioId': 'dzh'
                        , 'jiaoyiType':'dzh'
                        , 'deal':list
                        , 'repPageId': 'PtlFundAllocation002'
                    };
                });
            }
        }
    }
    //初始化
    var init =function(){
        debugger;
        var payType = $("#payType").val();
        /* 收付款账户显示 add by liuwei 20210226 start */
        if(!payType || payType==payType1001){
            $("#myAcctNo_id").hide();
            $("#counterAcctNo_id").show();
        }
        else if(payType==payType1002){
            $("#myAcctNo_id").show();
            $("#counterAcctNo_id").hide();
        }
        /* 收付款账户显示 add by liuwei 20210226 end */
        /* 导入后数据显示 add by liuwei 20210329 start */
        /**
         * 导入后基本信息不可改
         */
        if($("#isImportFlag").val() && $("#isImportFlag").val() == '01'){
            $('#settleDate').prop('disabled', true);
            $('#payType').prop('disabled', true);
            $('#counterAcctNo').prop('disabled', true);
            $('#myAcctNo').prop('disabled', true);
            $('#ccy').prop('disabled', true);
        }
        if(pageModel == PAGE_MODEL_NEW && !$("#isImportFlag").val()){
        /* 导入后数据显示 add by liuwei 20210329 end */
            Joyin.ajax(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/getInitDate',{}, function(result) {//数据验证
                if(result){
                    $('#settleDate').val(result);
                }
            });
        }else{
            initAmount();
        }
    }

    /**
     * 保存和提交处理函数
     * @param operateType 操作类型
     * @returns 没有返回值
     */
    function saveOrSubmit(operateType){
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        var object = $.serializeToObject("#submitForm");
        object.operateType = operateType;
        object.orderId = " ";

        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/check', object, function(result) {
            if(operateType === 'save') {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/save', object, "PtlFundAllocation003_btn_save");
                });
            }
            else {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/save', object, "PtlFundAllocation003_btn_submit");
                });
            }
        });
    }
    /**
     * 初始化现金余额
     */
    var initAmount = function(){
        if($('#myAcctNo').val() && $('#myAcctNo').val() != ''
            && $('#settleDate').val() && $('#settleDate').val() != ''
            && $('#ccy').val() && $('#ccy').val() != ''){
            var data = {
                acctId:$('#myAcctNo').val(),
                cdate:$('#settleDate').val(),
                portfolioId:$('#portfolioId').val(),
                ccy:$('#ccy').val()
            };
            Joyin.ajax(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation002/getAmount', data, function(result) {//数据验证
                if(result && result != ''){
                    $('#amount').val(result).trigger('blur');
                }else{
                    $('#amount').val('').trigger('blur');
                }
            });
        }else{
            $('#amount').val('').trigger('blur');
        }
    }
    var cols = [
        {field:'portfolioId', title: '组合代码', edit:true,width:280, editOption:{id: 'portfolioId', type:'select',parentIds:'acctNo,payType,ccy,settleDate,myAcctNo,counterAcctNo', url:JoyinUtil.getRealPath('invest/portfolio/ptlfundallocation/PtlFundAllocation003/getPortfolioId'),showField :'id'}},
        {field:'acctNo', title: '收/付款账户', edit:false,width:280},
        {field:'acctName', title: '收/付款账户', edit:true,visible:false,width:280},
        {field:'amount', title: '付款账户余额', edit: false, align: 'right',type: 'money', digit: 2,width:280},
        {field:'settleAmt', title: '划款金额', edit: true, align: 'right',width:280, editOption:{type: 'money', digit: 2,pattern: '#.00'}},
        {field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt'],visible: !$("#isImportFlag").val() || $("#isImportFlag").val() !== '01'}/* 导入后隐藏删除按钮 add by liuwei 20210413 start */
    ]

    var table=$("#dataTable").joyintable({
        data: ptlFundAllocation001Dtos != null ? ptlFundAllocation001Dtos : [],
        searchOnInit: true,
        checkbox: false,
        height: 180,
        page: false,
        sortfont: true,
        rowno: true,
        editCell: function(value, data, field, cell, column) {
            setTimeout(function() {
                var settleAmt = Number(0);
                if (field == 'settleAmt') {
                    var allrows = table.getAllRows();
                    for(var i in allrows){
                        if(allrows[i].settleAmt==null||allrows[i].settleAmt==''||allrows[i].settleAmt=='NaN'){
                            allrows[i].settleAmt = Number(0);
                        }
                        settleAmt = Number(settleAmt)+Number(allrows[i].settleAmt);
                    }
                    $("#settleAmt").val(settleAmt).trigger('blur');
                }
                if(field == 'portfolioId'){
                    if(!$("#settleDate").val()){
                        Joyin.error(JoyinUtil.getMessage("请选择交易时间！"));
                        return;
                    }
                    if(!$("#ccy").val()){
                        Joyin.error(JoyinUtil.getMessage("请选择币种！"));
                        return;
                    }
                    if(!$("#myAcctNo").val() && !$("#counterAcctNo").val()){
                        Joyin.error(JoyinUtil.getMessage("收/付款账户不能同时为空"));
                        return;
                    }
                    var object = {portfolioId: data.portfolioId, cdate: $("#settleDate").val(),
                                    ccy: $("#ccy").val(),payType:$("#payType").val(),myAcctNo:$("#myAcctNo").val()};

                    Joyin.ajax(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/getPosition', object, function(result){
                        if (result==null||result=="") {
                            data.portfolioId='';
                            Joyin.error(JoyinUtil.getMessage("1.组合没有挂资金账户！"));
                            return;
                        }
                        table.renderRow(data.LAY_TABLE_INDEX, {"acctNo": result.acctNo});
                        table.renderRow(data.LAY_TABLE_INDEX, {"acctName": result.acctName});
                        table.renderRow(data.LAY_TABLE_INDEX, {"amount": result.amount});
                    });
                }
            }, 20);
        },

        cols: cols,
        addbtn:!$("#isImportFlag").val() || $("#isImportFlag").val() !== '01',//导入后该按钮不显示
        done:function(){
            this.bind('click', '.delt', function(data, index) {
                table.deleteRow(index);
                var settleAmt = Number(0);
                var allrows = table.getAllRows();
                for(var i in allrows){
                    if(allrows[i].settleAmt==null||allrows[i].settleAmt==''||allrows[i].settleAmt=='NaN'){
                        allrows[i].settleAmt = Number(0);
                    }
                    settleAmt = Number(settleAmt)+Number(allrows[i].settleAmt);
                }
                $("#settleAmt").val(settleAmt).trigger('blur');
            });

        }
    });


    function reloadTable(object,acctNo){
        $("#acctNo").val(acctNo);
        //证银转账时，初始化自动在明细信息中显示各组合在该证券账户的组合情况
        //且划款金额默认等于组合该证券账户余额
        if(object.payType && object.payType == payType1002){
            Joyin.ajax(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/getDetailInfo', object, function(result){
                if(result && result != ''){
                    table.reload({data: result});
                    var settleAmt = Number(0);
                    for(var i in result){
                        if(result[i].settleAmt==null||result[i].settleAmt==''||result[i].settleAmt=='NaN'){
                            result[i].settleAmt = Number(0);
                        }
                        settleAmt = Number(settleAmt)+Number(result[i].settleAmt);
                    }
                    $("#settleAmt").val(settleAmt).trigger('blur');
                }else{
                    table.reload({ajax:null, url: null, data:[]});
                }
            });
        }else{
            table.reload({ajax:null, url: null, data:[]});
        }
    }

    /**
     * 画面加载完成
     */
    $(function() {
        init();
        /**
         * 组合、币种、划款类型变动联动事件
         */
        $(".baseInfo").change(function(){
            var payType = $("#payType").val();
            if(payType==payType1001){
                $("#myAcctNo_id").hide();
                $("#counterAcctNo_id").show();
                $('#myAcctNo').val('').trigger('change');
                $('#myAcctNo').text('');
            }else{
                $("#myAcctNo_id").show();
                $("#counterAcctNo_id").hide();
                $('#counterAcctNo').val('').trigger('change');
                $('#counterAcctNo').text('');
            }
        })

        $("#settleDate").change(function(){
            initAmount();
            var acctNo = $("#myAcctNo").val();
            var object = {payType: $("#payType").val(), acctNo: acctNo,cdate:$("#settleDate").val(),ccy:$("#ccy").val()};
            reloadTable(object,acctNo);
        })
        /**
         * 付款账户联动事件
         */
        $('#myAcctNo').change(function(){
            var ccy = $("#ccy").val();
            if(ccy){
                var acctNo = $("#myAcctNo").val();
                var object = {payType: $("#payType").val(), acctNo: acctNo,cdate:$("#settleDate").val(),ccy:$("#ccy").val()};
                reloadTable(object,acctNo);
            }
        });
        /**
         * 收款账户联动事件
         */
        $('#counterAcctNo').change(function(){
            var ccy = $("#ccy").val();
            if(ccy){
                var acctNo = $("#counterAcctNo").val();
                var object = {payType: $("#payType").val(), acctNo:acctNo,cdate:$("#settleDate").val(),ccy:$("#ccy").val()};
                reloadTable(object,acctNo);
            }
        });
        var acctNo;
        $('#ccy').change(function(){
            var counterAcctNo = $("#counterAcctNo").val();
            var myAcctNo = $("#myAcctNo").val();

            if(!counterAcctNo && !myAcctNo){
                return;
            }else if(counterAcctNo){
                acctNo = counterAcctNo;
            }else if(myAcctNo){
                acctNo = myAcctNo;
            }
            var object = {payType: $("#payType").val(), acctNo: acctNo,cdate:$("#settleDate").val(),ccy:$("#ccy").val()};
            reloadTable(object,acctNo);
        });
        //保存
        $('#PtlFundAllocation003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        //提交
        $('#PtlFundAllocation003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());