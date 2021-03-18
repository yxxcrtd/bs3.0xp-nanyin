(function () {
    var pledgedRepoRolloverList = entity.pledgedRepoRolloverList;
    var pledgedRepoInfoList = entity.pledgedRepoInfoList;
    console.log('formTodoList',formTodoList)
    if(formTodoList){
        pledgedRepoInfoList = PledgedRepoInfoListData
    }
    var buttons = [{ text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
    var getPageParam = function () {
        var object = $.serializeToObject('#TrdRolloverPledgedRepo001_form',true);
        return object;
    }
    function init(){
        var repoType = $("#repoType").val();
        var ps = $("#ps").val();
        if(repoType && ps && repoType==H01 && ps==BACK_LEND_DIRECTION_03){//协议式正回购需要对质押券进行转仓
            $("#pledgedBondSplit_div").show();
        }
        else{
            $("#pledgedBondSplit_div").hide();
        }
    }
    //风险测试数据
    var getriskdata = function () {
        var rows = pledgedRepoInfoListTable.getAllRows();
        var objects = [];
        for (var i in rows) {
            var obj = {
                timePoint: MY_SUP_TIME_TYPE_02,
                valDate: $('#tradeDate').val(),
                portfolioCode: rows[i].portfolioId,
                tradeAmount: rows[i].rolloverPrinAmt,
                assetType: $("#ps").val() == BACK_LEND_DIRECTION_03 ? FINPROD_TYPE_TG_F02 : FINPROD_TYPE_TG_F03,
                tradeDirection: $('#ps').val(),
                isEnabled: '01',
                index: null,
                mun: i
            };
            if ($('#ps').val() == BACK_LEND_DIRECTION_03) {
                obj.assetType = FINPROD_TYPE_TG_F02;
            } else if ($('#ps').val() == BACK_LEND_DIRECTION_04) {
                obj.assetType = FINPROD_TYPE_TG_F03;
            } else {
                obj.assetType = null;
            }
            objects[i] = {
                valDate: ['tradeDate', 'TrdRepoTrade002.tradeDate'],
                portfolioCode: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
                tradeAmount: ['rolloverPrinAmt', 'TrdRepoTrade002.prinAmt'],
                assetType: ['repoType', 'TrdRepoTrade001.repoType'],
                tradeDirection: ['ps', 'TrdRepoTrade002.ps'],
                data: obj
            };
        }
        return objects;
    };
    /**
     * 质押券转仓表
     */
    var pledgedBondSplitTable = $("#pledgedRepoRolloverList").joyintable({
        page: false,
        data: pledgedRepoRolloverList ? pledgedRepoRolloverList : [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 债券代码
            {field: 'pledgeFinprodId',width: 150, title: '债券代码', sortable: false}
            // 债券简称
            , {field: 'finprodAbbr',width: 150,title: '债券简称',sortable: false}
            // 质押券面额
            , {field: 'pledgePrin',width: 150,title: '质押券面额',type: 'money',digit: 2,sortable: false}
            // 剩余可转仓质押券面总额
            , {field: 'avaPledgePrin',width: 200,title: '剩余可转仓质押券面总额',type: 'money', digit: 2, sortable: false}
            // 折算比例%
            , {field: 'pledgeRatio',width: 100,title: '折算比例%',type: 'number',digit: 2, sortable: false}
            //产品组合可质押券面总额
            , {field: 'prdPledgePrin',width: 200,title: '产品组合可质押券面总额',type: 'money',digit: 2, sortable: false}
            //本次转仓指定质押券面总额
            , {field: 'desiPledgePrin',width: 200,title: '本次转仓指定质押券面总额',type: 'money',edit:true,digit: 2, sortable: false}
        ],
        editCell: function (value, row, field, cell, column) {
            if(field=="desiPledgePrin"){
                row.avaPledgePrin = JoyinMath.doMinus(row.pledgePrin,value);
                var allRows = pledgedBondSplitTable.getAllRows();
                var sumDesiPledgePrin=0;
                $.each(allRows,function(index,obj){
                    if(obj.desiPledgePrin == undefined||obj.desiPledgePrin==""){
                        obj.desiPledgePrin=0;
                    }
                    sumDesiPledgePrin=JoyinMath.doAdd(JoyinMath.doDiv(JoyinMath.doMul(obj.desiPledgePrin,obj.pledgeRatio),100),sumDesiPledgePrin);
                });
                $("#sumRolloverPrinAmt").val(sumDesiPledgePrin).trigger("blur");
                pledgedBondSplitTable.renderRow(row.LAY_TABLE_INDEX, row);
            }
        }
    });
    /**
     * 列表信息（Total）
     */
    var pledgedRepoInfoListTable = $("#pledgedRepoInfoList").joyintable({
        page: false,
        data: pledgedRepoInfoList ? pledgedRepoInfoList : [],
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 组合代码
            {field: 'portfolioId',width: 150, title: '组合代码', sortable: false}
            // 组合名称
            , {field: 'portfolioName',width: 150,title: '组合名称',sortable: false}
            // 交易日期
            , {field: 'tradeDate',width: 150,title: '交易日期',type: 'date', digit: 2, sortable: false}
            // 本金金额
            , {field: 'rolloverPrinAmt',width: 150,title: '本金金额',type: 'money', digit: 2, sortable: false}
            // 回购利率%
            , {field: 'rebackPrice',width: 150,title: '回购利率%',type: 'number',digit: 4, sortable: false}
            // 起息日
            , {field: 'vdate',width: 150,title: '起息日',type: 'date', digit: 2, sortable: false}
            // 到期日
            , {field: 'mdate',width: 150,title: '到期日',type: 'date', digit: 2, sortable: false}
            //应计利息总额
            , {field: 'rolloverRepoIntAmt',width: 150,title: '应计利息总额',type: 'money',digit: 2, sortable: false}
            //交易金额
            , {field: 'rolloverTradeAmt',width: 150,title: '交易金额',type: 'money',digit: 2, sortable: false}
            //质押券信息
            , {field: 'pledgedRepoRolloverList',visible:false}
            //交易编号
            ,{field: 'tradeId',visible:false}
            // 质押券信息
            ,{field: 'showPledgedInfo',width:150,title: '质押券信息',sortable: false,
                render:function (data, type, row, meta) {
                    return '<a class="btn_show_plegin" style="padding-left: 0px;display:block;width: 80px;height: 20px;line-height: 20px;text-align: center;background-color: rgb(38, 147, 255);border-radius: 2px;color: #fff;margin: 3px 15px;">质押券信息</a>'}}
            //操作
            ,{field:'operation',title: 'SysCommon.operation',type:'action',buttons: buttons}
        ],done:function(){
            this.bind('click', '.delt', function(data, index) {
                pledgedRepoInfoListTable.deleteRow(index);
                refreshCashAndBalance();
                calUnRolloverPrinAmt();
            });
        }
    });
    pledgedRepoInfoListTable.bind('click', 'a.btn_show_plegin', function(data,index) {
        var object = {};
        var pledgedRepoRolloverList = data.pledgedRepoRolloverList;
        if (pledgedRepoRolloverList == null) {
            pledgedRepoRolloverList = {};
        }
        object.pledgedRepoRolloverList = JSON.stringify(pledgedRepoRolloverList);
        object.pageModel=pageModel;
        object.portfolioId = data.portfolioId;
        object.tardeDate = data.tardeDate;
        object.secAcctId = $("#secAcctId").val();
        object.chlId = $("#chlId").val();
        object.workflowId= $("#workflowId").val();
        Joyin.dialog(JoyinUtil.getLabel('质押券信息'),basePath+
            '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/showPledgedInfo', '60%', '50%',object);
    });
    //组合change事件
    var portfolioIdChange = function(){
        var object = getPageParam();
        Joyin.ajaxJson(basePath + '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/selectPledgedRepoRolloverList', object, function (result) {
            if (result && result.statuCode == 200) {
                pledgedRepoRolloverList = result.data;
                pledgedBondSplitTable.clear(function(){
                    pledgedBondSplitTable.addRow(pledgedRepoRolloverList);
                });
            }
        });
        refreshCashAndBalance();
    };
    /**
     * 刷新托管户剩余现金、组合T日可用现金、组合T+1日可用现金
     */
    var refreshCashAndBalance = function () {
        var params = {};
        params.portfolioId = $("#portfolioId").val();
        params.calendarId = $("#calendarId").val();
        params.ccy = $("#ccy").val();
        // 通道交易 新增 强成西 2019/03/26 start
        params.chlId = $("#chlId").val();
        // 通道交易 新增 强成西 2019/03/26 end
        params.tradeDate = $("#tradeDate").val();
        if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"]) {
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].updateCash(function () {
                return params
            });
        }
    };
    //转仓change事件
    var rolloverPrinAmtChange = function(){
        var rolloverPrinAmt = $("#rolloverPrinAmt").val();//转仓本金
        var rebackPrice = $("#rebackPrice").val();//回购利率
        var tradeDate = $("#tradeDate").val();
        var vdate = $("#vdate").val();
        var basis = $("#basis").val();
        if(basis){
            if(basis=="A/360"){
                basis=360;
            }
            else{
                basis=365;
            }
        }
        var rolloverRepoIntAmt = JoyinMath.doDiv((rolloverPrinAmt*rebackPrice*DateUtil.daysBetween(tradeDate,vdate)),basis,6,BigNumber.ROUND_HALF_UP)/100
        $("#rolloverRepoIntAmt").val(rolloverRepoIntAmt).trigger('blur');
        $("#rolloverTradeAmt").val(JoyinMath.doAdd(rolloverPrinAmt,rolloverRepoIntAmt,4,BigNumber.ROUND_HALF_UP)).trigger('blur');
    };
    //交易金额change事件
    var rolloverTradeAmtChange = function(){
        var rolloverPrinAmt = $("#rolloverPrinAmt").val();//转仓本金
        var rolloverTradeAmt = $("#rolloverTradeAmt").val();//交易金额
        if(rolloverPrinAmt){
            $("#rolloverRepoIntAmt").val(JoyinMath.doMinus(rolloverTradeAmt,rolloverPrinAmt,4,BigNumber.ROUND_HALF_UP)).trigger('blur');
        }
    };
    //add事件
    function doAddAction(){
        var object = getPageParam();
        var flag = false;
        var rows = pledgedRepoInfoListTable.getAllRows();
        var portfolioId = $("#portfolioId").val();
        var portfolioName = $("#portfolioId").find("option:selected").text();
        object.portfolioName = portfolioName;
        $.each(rows,function (index,data) {
            if(portfolioId == data.portfolioId){
                Joyin.alert("产品组合："+portfolioId+"在转仓列表已存在！",2);
                flag = true;
            }
        });
        if(!flag){
            Joyin.ajaxJson(basePath + '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/addCheck',object,function(result){
                if(result && result.statuCode==200){
                    listInfoDtos = result.data.pledgedRepoInfoList;
                    pledgedRepoInfoListTable.addRow(listInfoDtos[0]);
                    calUnRolloverPrinAmt();

                    $("#rolloverPrinAmt").val('');
                    $("#rolloverTradeAmt").val('');
                    $("#sumRolloverPrinAmt").val('');
                    $("#rolloverRepoIntAmt").val('');
                    $("#bussTrdAvailableCashQuery_avalibleCash").val('')
                    $("#portfolioId").val('').trigger("change")
//                    pledgedBondSplitTable.clear(function(){
//                        console.log("进到这儿了")
//                        console.log(pledgedBondSplitTable)
//                    });

                }
            });
        }


    };
    //计算金额联动
    function calUnRolloverPrinAmt(){
        var rows = pledgedRepoInfoListTable.getAllRows();
        var rolloverPrinAmtSum = 0;
        var prinAmt = $("#prinAmt").val();
        var rolloverTradeAmtSum = 0;
        $.each(rows,function (index,data) {
            if($("#portfolioId").val() && $("#portfolioId").val()==data.portfolioId){
                rolloverTradeAmtSum = JoyinMath.doAdd(data.rolloverTradeAmt,rolloverTradeAmtSum);
            }
            rolloverPrinAmtSum = JoyinMath.doAdd(data.rolloverPrinAmt,rolloverPrinAmtSum);
        });
        var avalibleCash = $("#bussTrdAvailableCashQuery_avalibleCash").val();
        $("#avaRolloverAmt").val(JoyinMath.doMinus(prinAmt,rolloverPrinAmtSum,2)).trigger('blur');
        $("#bussTrdAvailableCashQuery_avalibleCash").val(JoyinMath.doMinus(avalibleCash,rolloverTradeAmtSum,2)).trigger("blur");
    };
    function saveOrSubmit(operateType) {
        var objects = getriskdata();
        var object = getPageParam();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/saveCheck', object, function (result) {
            Joyin.confirm(result.data, function () {
                var results =[];
                if(risk_math_switch=="01"){
                    for(var i in objects){
                        var object1 = objects[i];
                        Joyin.ajaxJson(basePath + 'component/common/BaseRiskMathComm/check', object1, function() {
                            Joyin.ajaxJson(basePath + 'component/common/BaseRiskMathComm/riskMath', object1.data, function(result) {
                                var cluflag=true;
                                for(var row in result){
                                    if(result[row].supStat=="VIOLATION"){
                                        results.push(result[row]);;
                                    }
                                }
                            },false);
                        },false);
                    }
                }
                if(risk_math_switch=="01" && results && results.length>0){
                    var param = {};
                    param.results = JSON.stringify(results);
                    Joyin.dialog("风险测算",basePath+'/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/riskMathComm','60%','50%',param);
                }
                else{
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/save', object, "TrdRolloverPledgedRepo001_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + '/invest/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo001/save', object, "TrdRolloverPledgedRepo001_btn_submit");
                    }
                }
            });
        },false);
    };
    var findAvaTransferPortfolioInfo = function(){
        var listInfoList = [];
        var allRows = pledgedRepoInfoListTable.getAllRows();
        for(var i in allRows){
            var obj = {
                portfolioId : allRows[i].portfolioId,
                tradeAmt : allRows[i].rolloverTradeAmt
            };
            listInfoList[i] = obj;
        }
        Joyin.dialog("待转仓产品组合信息",basePath+"/invest/trade/transfer/common/TransferCommon001/init",'60%','60%',{
            portfolioId : $("#invTransPortf").val(),
            chlId : $("#chlId").val(),
            tradeDate : $("#tradeDate").val(),
            ccy : $("#ccy").val(),
            secAcctId : $("#secAcctId").val(),
            listInfoList : JSON.stringify(listInfoList)
        })
    };
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            $("#bussTrdAvailableCashQuery_div_position").hide();
            $("#bussTrdAvailableCashQuery_div_channelCash").hide();
            $("#bussTrdAvailableCashQuery_avalibleCashT1").parent().parent().hide();
            $("#bussTrdAvailableCashQuery_avalibleCash").parent().prev().html("组合可用现金");
        }
    };
    $(function () {
        init();
        //质押券拆分列表加载
        pledgedBondSplitTable.load();
        //列表信息加载
        pledgedRepoInfoListTable.load();
        //组合change事件
        $("#portfolioId").change(function(){
            portfolioIdChange();
        });
        //转仓本金change事件
        $("#rolloverPrinAmt").change(function(){
            rolloverPrinAmtChange();
        });
        //交易金额change事件
        $("#rolloverTradeAmt").change(function(){
            rolloverTradeAmtChange();
        });
        $("#TrdRolloverPledgedRepo001_btn_searchInfo").click(function () {
            findAvaTransferPortfolioInfo();
        });
        $("#TrdRolloverPledgedRepo001_btn_add").click(function () {
            doAddAction();
        });
        // 绑定按钮事件
        $('#TrdRolloverPledgedRepo001_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdRolloverPledgedRepo001_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });
}());