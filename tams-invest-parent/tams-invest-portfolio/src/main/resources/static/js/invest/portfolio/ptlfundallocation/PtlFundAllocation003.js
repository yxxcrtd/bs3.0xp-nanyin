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
                        if(payType==1001){
                            dealObj.payAcctNoList = acctNoList;
                            dealObj.recAcctNoList = recAcctNoList;
                        }else{
                            dealObj.payAcctNoList = payAcctNoList;
                            dealObj.recAcctNoList = acctNoList;
                        }
//                    dealObj.payAcctNo = $("#counterAcctNo").val();
//                    dealObj.secManageAcctId = $("#myAcctNo").val();
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
                        , 'repPageId': 'TrdRepoTrade003'
                    };
                });
            }
        }
    }
    //初始化
    var init =function(){
        var payType = $("#payType").val();
        /* 收付款账户显示 add by liuwei 20210226 start */
        if(!payType || payType=='1001'){
            $("#myAcctNo_id").hide();
            $("#counterAcctNo_id").show();
        }
        else if(payType=='1002'){
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
        {field:'portfolioId', title: '组合代码', edit:true,width:280, editOption:{id: 'portfolioId', type:'select',parentIds:'acctNo', url:JoyinUtil.getRealPath('invest/portfolio/ptlfundallocation/PtlFundAllocation003/getPortfolioId'),showField :'id'}},
        {field:'acctNo', title: '收/付款账户', edit:false,width:280},
        {field:'acctName', title: '收/付款账户', edit:true,visible:false,width:280},
        {field:'amount', title: '付款账户余额', edit: false, align: 'right',type: 'money', digit: 2,width:280},
        {field:'settleAmt', title: '划款金额', edit: true, align: 'right',width:280, editOption:{type: 'money', digit: 2,pattern: '#.00'}},
        {field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt'],visible: !$("#isImportFlag").val() || $("#isImportFlag").val() !== '01'}/* 导入后隐藏删除按钮 add by liuwei 20210413 start */
    ]
    /**
     * 定义数据表格
     */
//     function reloadTable(object,acctNo){
//         $("#showTable").empty();
//         $("#showTable").append('<table class="layui-table" id="dataTable" name="ptlFundAllocation001Dtos"></table>');
//         var table1 = $("#dataTable").joyintable({
//             ajax: {
//                 url: 'getPtlFundAllocation001Dtos',
//                 type: 'POST',
//                 data: object
//             },
//             searchOnInit: true,
//             checkbox: false,
//             height: 180,
//             page: false,
//             editCell: function(value, data, field, cell, column) {
//                 setTimeout(function() {
//                     var settleAmt = Number(0);
//                     if (field == 'settleAmt') {
//                         var allrows = table1.getAllRows();
//                         for(var i in allrows){
//                             if(allrows[i].settleAmt==null||allrows[i].settleAmt==''||allrows[i].settleAmt=='NaN'){
//                                 allrows[i].settleAmt = Number(0);
//                             }
//                             settleAmt = Number(settleAmt)+Number(allrows[i].settleAmt);
//                         }
//                         $("#settleAmt").val(settleAmt+'.00');
//                     }
//                     if (!(field == 'portfolioId' || field == "myAcctNo" )) {
//                         return;
//                     }
//                     if(!value || value.trim() == ''){
//                         return;
//                     }
//
//                     if(!$("#settleDate").val()){
//                         Joyin.error(JoyinUtil.getMessage("1.请选择交易时间！"));
//                         return;
//                     }
//                     if(!$("#ccy").val()){
//                         Joyin.error(JoyinUtil.getMessage("1.请选择币种！"));
//                         return;
//                     }
//                     var object = {portfolioId: data.portfolioId, cdate: $("#settleDate").val(),ccy: $("#ccy").val()};
//                     Joyin.ajax(basePath + 'portfolio/ptlfundallocation/PtlFundAllocation003/getPosition', object, function(result){
//                         if (result==null||result=="") {
//                             data.portfolioId='';
//                             Joyin.error(JoyinUtil.getMessage("1.组合没有挂资金账户！"));
//                             return;
//                         }
//                         var rstdata = result;
//                         data.acctNo = rstdata.acctNo;
//                         data.amount = rstdata.amount;
//                         data.acctName = rstdata.acctName;
//                         // 刷新表格
//                         var allrows = table1.getAllRows();
//                         table1.reload({data : allrows});
//                         table1.events();
//                     });
//                 }, 10);
//             },
//             cols: [
//                 {field:'portfolioId', title: '组合代码', edit:true, editOption:{id: 'portfolioId', type:'select', url:JoyinUtil.getRealPath('/portfolio/ptlfundallocation/PtlFundAllocation003/getPortfolioId?acctNo='+acctNo),showField :'id'}},
//                 {field:'acctNo', title: '收/付款账户', edit:false},
//                 {field:'acctName', title: '收/付款账户', edit:true,visible:false},
//                 {field:'amount', title: '付款账户余额', edit: false, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
//                 {field:'settleAmt', title: '划款金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
//                 {field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
//             ],
//             addbtn:true,
//             done:function(){
//                 this.bind('click', '.delt', function(data, index) {
//                     table1.deleteRow(index);
//                 });
//                 var settleAmt = Number(0);
//                 var allrows = table1.getAllRows();
//                 for(var i in allrows){
//                     if(allrows[i].settleAmt==null||allrows[i].settleAmt==''||allrows[i].settleAmt=='NaN'){
//                         allrows[i].settleAmt = Number(0);
//                     }
//                     settleAmt = Number(settleAmt)+Number(allrows[i].settleAmt);
//                 }
//                 $("#settleAmt").val(settleAmt+'.00');
//             }
//         });
// //        Joyin.ajax(basePath + 'portfolio/ptlfundallocation/PtlFundAllocation003/getPtlFundAllocation001Dtos', object, function(result){
// //                table.clear();
// //                table.addRow(result == null || result == '' ? null : JSON.parse(result));
// //        })
//     }


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
                if (!(field == 'portfolioId' || field == "myAcctNo" )) {
                    return;
                }
                if(!value || value.trim() == ''){
                    return;
                }

                if(!$("#settleDate").val()){
                    Joyin.error(JoyinUtil.getMessage("1.请选择交易时间！"));
                    return;
                }
                if(!$("#ccy").val()){
                    Joyin.error(JoyinUtil.getMessage("1.请选择币种！"));
                    return;
                }
                var object = {portfolioId: data.portfolioId, cdate: $("#settleDate").val(),ccy: $("#ccy").val(),payType:$("#payType").val(),myAcctNo:$("#myAcctNo").val()};
                Joyin.ajax(basePath + 'invest/portfolio/ptlfundallocation/PtlFundAllocation003/getPosition', object, function(result){
                    if (result==null||result=="") {
                        data.portfolioId='';
                        Joyin.error(JoyinUtil.getMessage("1.组合没有挂资金账户！"));
                        return;
                    }
                    var rstdata = result;
                    data.acctNo = rstdata.acctNo;
                    data.amount = rstdata.amount;
                    data.acctName = rstdata.acctName;
                    // 刷新表格(表单添加新行时会覆盖旧行)
//                    var allrows = table.getAllRows();
//                    var dataList = [];
//                    dataList.push(data);
//                    table.reload({data : dataList});
                    var allrows = table.getAllRows();
                    table.reload({data : allrows});
                });
            }, 20);
        },

        cols: cols,
        addbtn:!$("#isImportFlag").val() || $("#isImportFlag").val() !== '01',//导入后该按钮不显示
        done:function(){
            // this.cols = [];
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
            if(payType==1001){
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