$(function() {
    //组件
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone) {
            //组合持仓现金组件
            var ps = $('#BuyOutRepoTransfer001_ps').val();
            //交易方向为逆回购时显示组合可用现金，不可编辑
            if(ps && ps == BACK_LEND_DIRECTION_04){
                $("#bussTrdAvailableCashQuery_div_cash").show();
                $("#bussTrdAvailableCashQuery_div_channelCash").hide();
                $("#bussTrdAvailableCashQuery_div_position").hide();
                $("#bussTrdAvailableCashQuery_avalibleCashT1").parent().parent().hide();
                $("#bussTrdAvailableCashQuery_avalibleCash").parent().prev().html("组合可用现金");
            }
            //交易方向为正回购时显示资产可用持仓，不可编辑
            else if(ps && ps == BACK_LEND_DIRECTION_03){
                $("#bussTrdAvailableCashQuery_div_cash").hide();
                $("#bussTrdAvailableCashQuery_div_channelCash").hide();
                $("#bussTrdAvailableCashQuery_div_position").show();
            }

            //风险试算组件
        	if(typeof(BaseRiskMathComm) != 'undefined') {
                var riskparam = getriskdata();
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
        	}
        }
    };

    /**
     * 组合代码联动组合现金、资产持仓变化
     */
    var portfolioIdChange = function () {
        var params = {};
        params.portfolioId = $("#BuyOutRepoTransfer001_portfolioId").val();
        params.calendarId = $("#calendarId").val();
        params.ccy = $("#BuyOutRepoTransfer001_ccy").val();
        params.tradeDate = $("#BuyOutRepoTransfer001_tradeDate").val();
        params.finprodId = $("#finprodId").val();
        params.chlId = $("#chlId").val();
        params.secAcctId = $("#secManageAcctId").val();
        params.multiParam = 100;
        if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"]) {
            window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].updateCash(function () {
                return params
            });
        }
        $("#BuyOutRepoTransfer001_proPrinAmt").val('').trigger("change").trigger('blur');
        $("#BuyOutRepoTransfer001_firSettleAmt").val('').trigger("change").trigger('blur');
        $("#BuyOutRepoTransfer001_dueSettleAmt").val('').trigger("change").trigger('blur');
        $("#bussTrdAvailableCashQuery_avalibleCash").val('').trigger("change").trigger('blur');
        $("#bussTrdAvailableCashQuery_assetPosition").val('').trigger("change").trigger('blur');
    };

    /**
     * 转仓信息——交易金额联动
     */
    var proPrinAmtChange = function (callback) {
        var proPrinAmt = $("#BuyOutRepoTransfer001_proPrinAmt").val();
        var unitCprice = Number($("#unitCprice").val());
        var undistributedAmount = Number($("#BuyOutRepoTransfer001_undistributedAmount").val());
        var prinAmt = Number($("#BuyOutRepoTransfer001_prinAmt").val());
        //计算首次应计利息
        var paramObj = getcheckdata();
        if(paramObj.portfolioId && proPrinAmt) {
            Joyin.ajaxJson(JoyinUtil.getRealPath('/invest/trade/buyoutrepo/TrdOutRightRepoTrade002/calculateData'), paramObj, function (result) {
                if (result && result.data) {
                    var data = result.data;
                    //首次全价
                    var unitFprice = unitCprice + Number(data.unitInt);
                    // 计算首次结算金额
                    var fSettleAmt = JoyinMath.doDiv(JoyinMath.doMul(JoyinMath.doMul(Number(proPrinAmt),Number(10000)), unitFprice, 8),
                        100, 8);//首次结算金额=券面总额*首次全价/100
                    $('#BuyOutRepoTransfer001_firSettleAmt').val(fSettleAmt).trigger('change').trigger('blur');

                    //计算到期结算金额
                    var dueSettleAmtS = Number($("#dueSettleAmtS").val());
                    var mAmt = $("#mAmt").val() == null ? 0 : Number($("#mAmt").val());
                    //全部转仓完,倒减
                    if (Number(proPrinAmt) == undistributedAmount && dueSettleAmtS) {
                        var dueSettleAmt = mAmt;
                        var tableArr = table.getAllRows();
                        if (tableArr) {
                            $.each(tableArr, function (idx, v) {
                                dueSettleAmt = Number(dueSettleAmt) + Number(v.dueSettleAmt);
                            });
                        }
                        $('#BuyOutRepoTransfer001_dueSettleAmt').val(dueSettleAmtS - dueSettleAmt).trigger('change').trigger('blur');
                    } else if (dueSettleAmtS) {
                        //原始交易到期结算金额*转仓券面总额/原始交易券面总额
                        $('#BuyOutRepoTransfer001_dueSettleAmt').val(dueSettleAmtS * Number(proPrinAmt) / prinAmt).trigger('change').trigger('blur');
                    }
                    
                    if (typeof callback === 'function') {
                    	callback();
                    }
                }
            });
        }
    };

    var getcheckdata = function () {
        //update by liuwei for huaxing 20200310 start
        var calcModel = CALC_MODEL_03;
        var paramObj = {
            vdate:$("#BuyOutRepoTransfer001_vdate").val(),
            mdate:$("#BuyOutRepoTransfer001_mdate").val(),
            repRate:$("#BuyOutRepoTransfer001_intRate").val(),
            settleSpeed:$("#settleSpeed").val(),
            basis:$("#BuyOutRepoTransfer001_basis").val(),
            termType:$("#BuyOutRepoTransfer001_termType").val(),
            repoterm:$("#repoterm").val(),
            tradeMarket:$("#tradeMarket").val()
        };
        paramObj.repoCalculateField = "";
        paramObj.fCleanPrice = $('#unitCprice').val();
        paramObj.mCleanPrice = $('#mUnitCprice').val();
        paramObj.repoVdate = $('#BuyOutRepoTransfer001_vdate').val();
        paramObj.repoMdate = $('#BuyOutRepoTransfer001_mdate').val();
        paramObj.repoRate = $('#BuyOutRepoTransfer001_intRate').val();

        paramObj.portfolioId = $('#BuyOutRepoTransfer001_portfolioId').val();
        paramObj.finprodId = $('#finprodId').val();
        paramObj.tradeAmt = $('#BuyOutRepoTransfer001_proPrinAmt').val()*10000;
        paramObj.invAim = INV_AIM_TC;
        paramObj.tradeMarketMode = TRADE_PLACE_SEC;
        paramObj.calculateField = 'unitCprice';
        paramObj.entrustId = "";
        paramObj.calcModel = calcModel;
        paramObj.unitCprice = $('#unitCprice').val();
        paramObj.settleDate = $('#BuyOutRepoTransfer001_tradeDate').val();//首次应计利息的计算方法参考债券银行间交易的应计利息计算方法，交割日=起息日；
        paramObj.calendarId = $('#calendarId').val();
        paramObj.secTotalPrin = $('#BuyOutRepoTransfer001_proPrinAmt').val()*10000;
        paramObj.mdate = $('#BuyOutRepoTransfer001_mdate').val();
        return paramObj;
        //update by liuwei for huaxing 20200310 end
    };

    var options = {
        /*
         * 给表格赋虚拟数据
         */
        page:false,
        height:'140px',
        /*
         * 定义表头
         */
        cols:[
            {field:'portfolioId', title: '组合代码',width:160}//组合代码
            ,{field:'portfolioName', title: '组合名称',width:160}//组合名称
            ,{field:'finprodMarketId', title: '标的资产代码',width:160}//标的资产代码
            ,{field:'tradeDate', title: '交易日期',type:'date',width:160}//交易日期
            ,{field:'tradeAmt', title: '券面总额（万元）',width:160,
                render : function(data, type, row, meta) {
                    if(row.tradeAmt !=undefined){
                        return Number(row.tradeAmt).toFixed(6);
                    }
                }}//券面总额（万元）
            ,{field:'intRate', title: '回购利率%',type:'number',width:160}//回购利率%
            ,{field:'vdate', title: '起息日',type:'date',width:160}//起息日
            ,{field:'mdate', title: '到期日',type:'date',width:160}//到期日
            ,{field:'firSettleAmt', title: '首次结算金额',type:'number',width:150}//首次结算金额
            ,{field:'mUnitCprice', title: '到期净价',type:'number',width:150}//到期净价
            ,{field:'mInt', title: '到期应计利息',type:'number',width:150}//到期应计利息
            ,{field:'mUnitFprice', title: '到期全价',type:'number',width:150}//到期全价
            ,{field:'dueSettleAmt', title: '到期结算金额',type:'number',width:150}//到期结算金额
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: [ 'delt']}//编辑和删除事件
        ]
    };
    if(formTodoList) {
        options.data = entityData;
    }
    var table=$("#dataTable").joyintable(options);

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
        //3、联动剩余未拆分金额
        var undistributedAmount = $('#BuyOutRepoTransfer001_undistributedAmount').val();
        var tradeAmt = data.tradeAmt;
        $("#BuyOutRepoTransfer001_undistributedAmount").val(Number(undistributedAmount) + Number(tradeAmt)).trigger("change").trigger('blur');
    });

    /**
     * 添加按钮按下处理
     */
    var transferChange = function () {
        var param=$.serializeToObject('#buyOutRepoTransferForm',true);
        param.buyOutRepoTransfer002DtoList = table.getAllRows();
        param.assetPosition = param.bussTrdAvailableCashQuery.assetPosition;
        param.avalibleCash = param.bussTrdAvailableCashQuery.avalibleCash;
        Joyin.ajaxJson(basePath + 'invest/trade/transfer/buyoutrepo/BuyOutRepoTransfer001/addCheck', param, function (result) {
            if(result && result.statuCode == 200){
                //1、向列表中插入数据
                var options=$("#BuyOutRepoTransfer001_portfolioId").find("option:selected");
                var row = {
                    portfolioId: options.val(),
                    portfolioName: options.text().split(":")[1],
                    finprodMarketId: $('#BuyOutRepoTransfer001_finprodMarketId').val(),
                    tradeDate: $('#BuyOutRepoTransfer001_tradeDate').val(),
                    tradeAmt: $('#BuyOutRepoTransfer001_proPrinAmt').val(),
                    intRate : $("#BuyOutRepoTransfer001_intRate").val(),
                    vdate: $('#BuyOutRepoTransfer001_vdate').val(),
                    mdate: $('#BuyOutRepoTransfer001_mdate').val(),
                    firSettleAmt: $('#BuyOutRepoTransfer001_firSettleAmt').val(),
                    mUnitCprice: $('#mUnitCprice').val(),
                    mInt: $('#mUnitInt').val(),
                    mUnitFprice: $('#mUnitFprice').val(),
                    dueSettleAmt: $('#BuyOutRepoTransfer001_dueSettleAmt').val()
                };
                table.addRow(row);
                //2、联动剩余未拆分金额
                var undistributedAmount = $('#BuyOutRepoTransfer001_undistributedAmount').val();
                var proPrinAmt = $("#BuyOutRepoTransfer001_proPrinAmt").val();
                $("#BuyOutRepoTransfer001_undistributedAmount").val(Number(undistributedAmount) - Number(proPrinAmt)).trigger("change").trigger('blur');
                //3、清空转仓信息的信息
                $("#BuyOutRepoTransfer001_portfolioId").val('').trigger("change").trigger('blur');
                $("#BuyOutRepoTransfer001_proPrinAmt").val('').trigger("change").trigger('blur');
                $("#BuyOutRepoTransfer001_firSettleAmt").val('').trigger("change").trigger('blur');
                $("#BuyOutRepoTransfer001_dueSettleAmt").val('').trigger("change").trigger('blur');
                $("#bussTrdAvailableCashQuery_avalibleCash").val('').trigger("change").trigger('blur');
                $("#bussTrdAvailableCashQuery_assetPosition").val('').trigger("change").trigger('blur');
                $("#bussTrdAvailableCashQuery_channelAvailableCash").val('').trigger("change").trigger('blur');
            }
        })
    };

    /**
     * 查询待转仓组合
     */
    var findAvaTransferPortfolioInfo = function(){
        var listInfoList = [];
        var allRows = table.getAllRows();
        for(var i in allRows){
            var obj = {
                portfolioId : allRows[i].portfolioId,
                tradeAmt : allRows[i].firSettleAmt
            };
            listInfoList[i] = obj;
        }
        Joyin.dialog("待转仓产品组合信息",basePath+"/invest/trade/transfer/common/TransferCommon001/init",'60%','60%',{
            portfolioId : $("#portfolioIdOut").val(),
            chlId : $("#chlId").val(),
            tradeDate : $("#BuyOutRepoTransfer001_tradeDate").val(),
            ccy : $("#BuyOutRepoTransfer001_ccy").val(),
            secAcctId : $("#secManageAcctId").val(),
            listInfoList : JSON.stringify(listInfoList)
        })
    };

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        if(typeof(BaseRiskMathComm) != 'undefined') {
	        if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                return;
	            }
	        }
        }
        var object = $.serializeToObject('#buyOutRepoTransferForm', true);
        var data = table.getAllRows();
        object.operateType = operateType;
        object.buyOutRepoTransfer002DtoList = data;
        Joyin.ajaxJson(basePath + 'invest/trade/transfer/buyoutrepo/BuyOutRepoTransfer001/check', object, function(result) {
            if(operateType === 'save') {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                    WorkFlow.doSaveList(basePath + 'invest/trade/transfer/buyoutrepo/BuyOutRepoTransfer001/save',
                        object, "BuyOutRepoTransfer001_btn_save");
                });
            }
            else {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/transfer/buyoutrepo/BuyOutRepoTransfer001/save',
                        object, "BuyOutRepoTransfer001_btn_submit");
                });
            }
        });
    }

    var getriskdata = function () {
        var mun=null;
        var data = table.getAllRows();
        //转仓列表
        var list = [];
        var object = {};
        if(data!=null && data!="") {
            for (var i in data) {
                var obj = {
                    timePoint: MY_SUP_TIME_TYPE_DEAL,
                    valDate: $('#BuyOutRepoTransfer001_tradeDate').val(),
                    portfolioCode: data[i].portfolioId,
                    tradeAmount: data[i].firSettleAmt,
                    isEnabled: '01',
                    tradeDirection: $('#BuyOutRepoTransfer001_ps').val(),
                    index: null,
                    mun: mun
                };
                if ($('#BuyOutRepoTransfer001_ps').val() == BACK_LEND_DIRECTION_03) {
                    obj.assetType = FINPROD_TYPE_TG_F04;
                } else if ($('#BuyOutRepoTransfer001_ps').val() == BACK_LEND_DIRECTION_04) {
                    obj.assetType = FINPROD_TYPE_TG_F05;
                } else {
                    obj.assetType = null;
                }
                list[i] = obj;
            }
            object = {
                valDate: ['tradeDate', '交易日期'],
                portfolioCode: ['portfolioId', '组合代码'],
                tradeAmount: ['tradeAmt', '交易金额'],
                tradeDirection: ['ps', '交易方向'],
                data: list
            };
        }
        return object;
    };

    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        //组合代码联动
        $('#BuyOutRepoTransfer001_portfolioId').change(function () {
            portfolioIdChange();
        });
        //转仓信息——交易份额联动
        $('#BuyOutRepoTransfer001_proPrinAmt').change(function () {
            proPrinAmtChange();
        });
        //添加按钮
        $('#BuyOutRepoTransfer001_btn_add').click(function() {
        	proPrinAmtChange(function(){
        		transferChange();
        	});
        });
        //待转仓组合查询链接
        $("#BuyOutRepoTransfer001_btn_searchInfo").click(function () {
            findAvaTransferPortfolioInfo();
        });
        // 保存按钮事件
        $('#BuyOutRepoTransfer001_btn_save').click(function() {
            saveOrSubmit('save');
        });
        // 提交按钮事件
        $('#BuyOutRepoTransfer001_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //风险试算
        $('#BuyOutRepoTransfer001_btn_risk').click(function(){
            var data = table.getAllRows();
            if(data != null && data != ''){
                var object = $.serializeToObject('#buyOutRepoTransferForm', true);
                object.portfolioId = $("#portfolioIdOut").val();
                //深拷贝
                var bussTrdTransfenpeiDto = JSON.parse(JSON.stringify(data));
                for(var i=0;i<bussTrdTransfenpeiDto.length;i++){
                	bussTrdTransfenpeiDto[i].counterId = bussTrdTransfenpeiDto[i].portfolioId;
                	delete bussTrdTransfenpeiDto[i].portfolioId;
                }
                object.trdOutRightRepoTradePtl = bussTrdTransfenpeiDto;
                window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F10',object,getriskdata());
            }
            else {
                Joyin.error("拆分列表信息不能为空！");
            }
        });
    });
}());