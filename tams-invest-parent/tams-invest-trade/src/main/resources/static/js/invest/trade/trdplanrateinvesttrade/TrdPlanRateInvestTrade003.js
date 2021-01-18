var etrAmt = '';
(function () {
    var fileArray = [];
    var entrustId = '';
    var entity = '';
    var isOpics = '';
    var isHoliday = '';
    var calendarId = '';
    var getriskdata = function () {
        var mun = null;
        var object = $.serializeToObject('#basicInfoForm');
        var bussTrdTransfenpeiDto = null;
        if (object != null && object != "" && object.bussTrdTransfenpeiDto != null && object.bussTrdTransfenpeiDto != "") {
            bussTrdTransfenpeiDto = object.bussTrdTransfenpeiDto;
        }
        var list = [];
        for (var i in bussTrdTransfenpeiDto) {
            var obj = {
                timePoint: MY_SUP_TIME_TYPE_02,
                valDate: $('#tradeDate').val(),
                portfolioCode: bussTrdTransfenpeiDto[i].portfolioId,
                prinAmt: bussTrdTransfenpeiDto[i].prinAmt,
                tradeAmt: bussTrdTransfenpeiDto[i].tradeAmt,
                assetType: FINPROD_TYPE_F17,
                isEnabled: '01',
                assetCode: $('#finprodMarketId').val(),
                tradeDirection: $('#ps').val(),
                index: null,
                mun: mun
            }
            list[i] = obj;
        }

        var object = {
            assetCode: ['finprodMarketId', 'TrdBondInvestTrade002.finprodId'],
            valDate: ['settleDate', 'TrdPlanRateInvestTrade002.settleDate'],
            portfolioCode: ['portfolioId', 'TrdDepositInvestTrade001.portfolioId'],
            prinAmt: ['prinAmt', 'TrdPlanRateInvestTrade002.prinAmt'],
            tradeAmt: ['tradeAmt', 'TrdPlanRateInvestTrade002.tradeAmt'],
            assetType: ['finprodType', 'TrdDepositInvestTrade001.finprodType'],
            tradeDirection: ['ps', 'TrdDepositInvestTrade001.ps'],
            payUnpaidintAmt: $('payUnpaidintAmt').val(),
            data: list
        }
        return object;
    };


    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            // var riskparam = getriskdata();
            if (window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    /*getEntity();*/
                    var list;
                    list = new Array();
                    var tmpList = table.getAllRows();
                    for (var dealObj in tmpList) {
                        var dealinfo = {};
                        dealinfo.finprodId = $('#finprodMarketId').select2("data")[0].object
                        dealinfo.finprodType2 = FINPROD_TYPE_F17;
                        dealinfo.tradeDate = $("#tradeDate").val();
                        dealinfo.ps = $("#ps").val();
                        dealinfo.ccy = $("#ccy").val();
                        dealinfo.tradeAmt = tmpList[dealObj].tradeAmt; // TODO 多组合有通道费
                        dealinfo.portfolioId = tmpList[dealObj].portfolioId;
                        dealinfo.counterId = $("#trdInvestPersonInfo002Dto_counterId").val();
                        // 通道交易 新增 强成西 2019/03/26 start
                        dealinfo.chlId = $("#chlId").val();
                        // 通道交易 新增 强成西 2019/03/26 end
                        list.push(dealinfo);
                    }
                    //   entity.chlId = $("#chlId").val();
                    return {
                        'isTotalTrade': YES_OR_NO_N
                        , 'bankLendingType': null
                        , 'portfolioId': 'dzh'
                        , 'jiaoyiType': 'dzh'
                        , 'deal': list
                        //存款类交易新建交易对手非空校验 yjm start 20190724
                        , 'repPageId': pageId
                        //存款类交易新建交易对手非空校验 yjm end 20190724
                    };
                }, true);
            }
            if (entrustId != '') {
                if (etrEntrust) {
                    window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                        return etrEntrust;
                    });
                }
            }
//            psChange();
//            updateCash();
            //交易对手改变时  影响通道费金额，内部交易对手随交易支付，外部交易对手根据收取方式
            $('#trdInvestPersonInfo002Dto_counterId').on("change", function () {
            		checkChannelFee();    
            		changeTradeAmtAndFee();
                    channelFeeDistribution();
               /*     var counterId = $('#trdInvestPersonInfo002Dto_counterId');
                    if (counterId.select2('data') && counterId.select2('data')[0]) {
                        var 交易对手 = counterId.select2('data')[0].object;
                        // 【债券投资委托】交易对手选择内部交易对手时，清算速度为T+0不可改
                        if (交易对手 == 'N') {
                            if (pageModel != PAGE_MODEL_REFERENCE) {
                                $('#settleSpeed').val("01").prop("disabled", true).trigger("change");
                            }
                        } else {
                            if (pageModel != PAGE_MODEL_REFERENCE) {
                                $('#settleSpeed').prop("disabled", false).trigger("change");
                            }
                        }
                    }*/
                }
            );
        }
    }

    /**
     * 标的挂通道 内转交易 通道费置灰
     * 外部卖出显示可填  其他置灰
     */
    var checkChannelFee = function(){
        var ps = $('#ps').val();
        if(ps==PS_T02&&!$('#trdInvestPersonInfo002Dto_counterId').val()){
        	$("#channelFee").attr('disabled',false);
        }
        else if(ps==PS_T02&&$('#trdInvestPersonInfo002Dto_counterId').val()&&$("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object!='N'){
        	$("#channelFee").attr('disabled',false);
        }
        else{
        	$("#channelFee").val("").attr('disabled',true);
        }
    }

    function getEntity() {
        var object = $.serializeToObject('#basicInfoForm');
        if (object.loadRate != null) {
            object.loadRate = object.loadRate.valueOf() / 100;
        }
        var data = $("#chlId").find("option:selected").data("collecttype");
        if (data) {
            object.collectType = data;
        }
        if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
            object.finprodId = $("#finprodMarketId").select2("data")[0].object;
        } else if (finprodId != null) {
            object.finprodId = finprodId;
        }
        Joyin.ajaxSync(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getEntity', object, function (result) {
            result.createTime = null;
            result.updateTime = null;
            result.tradeId = null;
            result.totleTradeId = null;
            result.cpriceAmt = null;
            result.shareAmt = null;
            result.prinAmt = null;
            result.intAmt = null;
            result.fpriceAmt = null;
            // 输入备注等，点击提交，提示计息要素已变更 add by renping 20191018 start
            result.remark = null;
            result.counterContact = null;
            result.counterTrader = null;
            // 输入备注等，点击提交，提示计息要素已变更 add by renping 20191018 end
            entity = result;
        });
    }

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        var s;
	        var maxTradeamt = 0;
            if(typeof(BaseRiskMathComm) != 'undefined') {
	        if (BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if (!s && s != '') {
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
	                return;
                    }
	            }
	        }
	        // 检查生成划款指令参数是否发生变化
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        /**
	         * 获取列表参数
	         */
	        var object = $.serializeToObject('#basicInfoForm', true);
	        object.operateType = operateType;
	        if ($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != '') {
	            object.finprodId = $("#finprodMarketId").select2("data")[0].object;
	        }
	        object.pageModel = pageModel;
	        if (FileCommon) {
	            object.fileArray = FileCommon.getUploadFileInfo()
	        }
	
	        //组合分配  校验
	        //检查组合分配的本金金额和金额信息是否一致
	        if ($('#prinAmt').val()) {
	            var amt = 0;
	            for (var i = 0; i < table.getAllRows().length; i++) {
	                if (table.getAllRows()[i].prinAmt) {
	                    amt = JoyinMath.doAdd(amt, table.getAllRows()[i].prinAmt, 2);
	                }
	            }
	            if (JoyinMath.doAdd($('#prinAmt').val(), 0, 2) != amt) {
	                Joyin.warn("组合分配本金金额和金额信息本金金额不一致！");
	                return;
	            }
	        }
	
	        //检查组合分配的应计利息总额和金额信息是否一致
	        if ($('#intAmt').val()) {
	            var amt = 0;
	            for (var i = 0; i < table.getAllRows().length; i++) {
	                if (table.getAllRows()[i].intAmt) {
	                    amt = JoyinMath.doAdd(amt, table.getAllRows()[i].intAmt, 2);
	                }
	            }
	            if (JoyinMath.doAdd($('#intAmt').val(), 0, 2) != amt) {
	                Joyin.warn("组合分配应计利息总额和金额信息应计利息总额不一致！");
	                return;
	            }
	        }
	
	        //检查组合分配的通道费和金额信息是否一致
	        if ($('#channelFee').val()) {
	            var amt = 0;
	            for (var i = 0; i < table.getAllRows().length; i++) {
	                if (table.getAllRows()[i].channelFee) {
	                    amt = JoyinMath.doAdd(amt, table.getAllRows()[i].channelFee, 2);
	                }
	            }
	            if (JoyinMath.doAdd($('#channelFee').val(), 0, 2) != amt) {
	                Joyin.warn("组合分配通道费和金额信息通道费不一致！");
	                return;
	            }
	        }
	
	        //检查组合分配的交易金额和金额信息是否一致
	        if ($('#tradeAmt').val()) {
	            var amt = 0;
	            for (var i = 0; i < table.getAllRows().length; i++) {
	                if (table.getAllRows()[i].tradeAmt) {
	                    amt = JoyinMath.doAdd(amt, table.getAllRows()[i].tradeAmt, 2);
	                }
	                if (parseFloat(table.getAllRows()[i].tradeAmt) > parseFloat(maxTradeamt)){
						maxTradeamt = table.getAllRows()[i].tradeAmt;
					}
	            }
	            if (JoyinMath.doAdd($('#tradeAmt').val(), 0, 2) != amt) {
	                Joyin.warn("组合分配交易金额和金额信息交易金额不一致！");
	                return;
	            }
	        }
	
	
	        var data = $("#chlId").find("option:selected").data("collecttype");
	        if (data) {
	            object.collectType = data;
	        }
	
	        data = $("#chlId").find("option:selected").data("paytype");
	        if (data) {
	            object.payType = data;
	        }
	        if (isOpics == 'Y') {
	//            object.bussTrdTransferOrder = null;
	        } else {
	            object.interfSendOpicsList = null;
	        }
	        if ($('#trdInvestPersonInfo002Dto_counterId').select2('data') && $('#trdInvestPersonInfo002Dto_counterId').select2('data')[0]) {
	            object.counterType = $('#trdInvestPersonInfo002Dto_counterId').select2('data')[0].object;
	        }
	        if ($("#entrustId").val()) {
	            for (var i = 0; i < table.getAllRows().length; i++) {
	                if (object.ps == PS_T02&&(Number(table.getAllRows()[i].prinAmt) > Number(table.getAllRows()[i].doEntrustMoney))) {
	                    Joyin.warn("本金金额不能大于剩余委托额度！");
	                    return;
	                }
	                if (object.ps == PS_T01&&(Number(table.getAllRows()[i].tradeAmt) > Number(table.getAllRows()[i].doEntrustMoney))) {
	                    Joyin.warn("交易金额不能大于剩余委托额度！");
	                    return;
	                }
	            }
	        }
	        if (!object.bussTrdTransfenpeiDto.length) {
	            Joyin.warn("组合分配为空！");
	            return;
	        }
	        if (object.bussTrdTransfenpeiDto.length) {
	            object.portfolioId = object.bussTrdTransfenpeiDto[0].portfolioId;
	        }
	
	        //组合分配中本金金额逐一授权额度检查
//	        var tt = false;
//	        if (object.bussTrdTransfenpeiDto.length) {
//	            var list = [];
//	            for (var i in object.bussTrdTransfenpeiDto) {
//	                if (object.bussTrdTransfenpeiDto[i].prinAmt) {
//	                    list[i] = object.bussTrdTransfenpeiDto[i].prinAmt;
//	                }
//	                object.prinAmtData = list;
//	                Joyin.ajaxJson(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade003/checkPrinAmt', object, function (result) {
//	                    if (result) {
//	                        if (result.flag) {
//	                            if (result.flag == "false") {
//	                                Joyin.warn("交易本金金额超过副总裁/总裁助理授权额度，无法发起交易。");
//	                                tt = true;
//	                                return;
//	                            }
//	                        }
//	                    }
//	                });
//	            }
//	        }
//	        if (tt) {//上面return不了，再次return
//	            return;
//	        }
	
	        //可用持仓效验
	        var flag1 = true;
	        var message = "";
	        if (object.bussTrdTransferOrder.length) {
	            for (var bussTrdTransferOrder in object.bussTrdTransferOrder) {
	                for (var bussTrdTransfenpeiOrder in object.bussTrdTransfenpeiDto) {
	                    if ((object.bussTrdTransferOrder[bussTrdTransferOrder].portfolioId == object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].portfolioId) &&
	                        (JoyinMath.doAdd(object.bussTrdTransferOrder[bussTrdTransferOrder].settleAmt, 0, 2) == JoyinMath.doAdd(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt, 0, 2))) {
//	                        object.prinAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt;
//	                        object.intAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].intAmt;
//	                        object.channelFee = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].channelFee;
//	                        object.tradeAmt = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].tradeAmt;
//	                        object.bussTrdAvailableCashQueryavalibleCash = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash;
//	                        object.bussTrdAvailableCashQueryavalibleCash1 = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash1;
//	                        object.channelAvailableCash = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].channelAvailableCash;
//	                        object.Assetsavailablepositions = object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].Assetsavailablepositions;
	                     //   if (object.ps === PS_T01 && object.prinAmt && object.bussTrdAvailableCashQueryavalibleCash && Number(object.prinAmt) > (object.chlId && object.payType == '02' ? Number(object.channelAvailableCash) : Number(object.bussTrdAvailableCashQueryavalibleCash))) {
	                        if (object.ps === PS_T01 && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash && Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt) > (object.chlId && object.payType == '02' ? Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].channelAvailableCash) : Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].bussTrdAvailableCashQueryavalibleCash))) {
	                            if (object.chlId && object.payType == '02') {
	                                message = JoyinUtil.getMessage("TRD_C_0004");
	                                flag1 = false;
	                            } else {
	                                message = JoyinUtil.getMessage("TRD_C_0002");
	                                flag1 = false;
	                            }
	                        //} else if (object.ps == PS_T02 && object.prinAmt && object.Assetsavailablepositions && Number(object.prinAmt) > Number(object.Assetsavailablepositions)) {
	                        } else if (object.ps == PS_T02 && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt && object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].Assetsavailablepositions && Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].prinAmt) > Number(object.bussTrdTransfenpeiDto[bussTrdTransfenpeiOrder].Assetsavailablepositions)) {
	                            Joyin.warn("资产可用持仓必须大于本金金额。");
	                            return;
	                        }
	                    }
	                }
	            }
	        }
	        if (flag1) {
	            message = JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.save"));
	            if (operateType === 'submit') {
	                message = JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit"));
	            }
	            if (isHoliday != '' && isHoliday == 'Y') {
	                message = JoyinUtil.getMessage("BUSS_COM_E_0039", JoyinUtil.getLabel("TrdDepositInvestTrade002.dealdate"));
	            }
	        }
	
	
	        //授权额度检查已完成，不在检查   把总本金金额置为0
	        
	        object.entrustId = '';
	        object.counterId = $('#trdInvestPersonInfo002Dto_counterId').val();
	        object.maxTradeamt = maxTradeamt;
	        object.inputValue1 = 'F03';
	        object.prinAmt = $('#prinAmt').val();
	        Joyin.ajaxJson(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/check', object, function (result) {
	        	Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	            message = s + message;
	            if (result.data && message != result.data) {
	                message += result.data;
	            }
	            object.prinAmt = 0;
	            if (operateType === 'save') {
	                Joyin.confirm(message, function () {
	                    WorkFlow.doSaveList(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/save', object, "TrdPlanRateInvestTrade002_btn_save",function(result){

					     });
	                });
	            } else {
	                Joyin.confirm(message, function () {
	                    WorkFlow.doSubmitList(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/save', object, "TrdPlanRateInvestTrade002_btn_sbumit", pageId,null, function(result){
						      window.BaseRiskMathComm.reloadRiskTable(result);
	 				    });
	                });
	            }
	        });    
	        });
    	});
    }


    /**
     * 参数是json字符串的ajax请求，必须是Post请求
     */
    var ajaxJsonSync = function (url, param, callback) {
        var elementIdForLog = Joyin.getElementId(url);
        if (url && url.indexOf("pageIdForLog") == -1) {
            if (url.indexOf("?") != -1) {
                if (pageId) {
                    url += "&pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                }

            } else {
                if (pageId) {
                    url += "?pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                }
            }
        }
        // 是否为待办事项标识
        if (param) {
            url += (url.indexOf("?") != -1 ? '&' : '?') + 'isTodoList=' + isTodoList;
        }
        param = JSON.stringify(param);
        if (url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
            url = JoyinUtil.getRealPath(url);
        }
        // “保存”、“提交”操作时，记录画面修改值
        if (elementIdForLog && (elementIdForLog.indexOf('_submit') != -1 || elementIdForLog.indexOf('_save') != -1)) {
            var data = JSON.parse(param);
            if (elementIdForLog.indexOf('_submit') != -1 && data.businessInfo) {
                JoyinUtil.setModifyFormData(JSON.parse(data.businessInfo));
            } else {
                JoyinUtil.setModifyFormData(data);
            }
        }
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            contentType: "application/json",
            data: param,
            async: false,
            success: function (result) {
                Joyin.processResult(result, callback);
            }
        });
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        searchOnInit: true,
        loadonce: true,
        data: BussTrdTransfenpeiDto == null ? [] : BussTrdTransfenpeiDto,
        page: false,
        addbtn: true,
        height: 200,
        cols: [
            {field: 'entrustId', width: 150, title: '委托代码', sortable: false, disabled: true}
            , {
                field: 'portfolioId', width: 100, title: '组合代码', edit: true, sortable: false,
                editOption: {
                    type: 'select',
                    childrenIds: 'secManageAcctIdDuo',
                    id: 'portfolioId',
                    multiple: false,
                    showField: 'text',
                    searchable: true,
                    url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
                }
            }
            , {field: 'doEntrustMoney', width: 150, title: '剩余委托额度', sortable: false}
            , {field: 'prinAmt', width: 150, title: '本金金额', edit: true, sortable: false, type: 'number', digit: 2}
            , {field: 'intAmt', width: 150, title: '应计利息总额', sortable: false, type: 'number', digit: 2}
            , {field: 'channelFee', width: 150, title: function () {
                    return JoyinUtil.getLabel("invest.channelId") + JoyinUtil.getLabel("TrdDepoDuration002.channelFee");
                }(), edit: false, sortable: false, type: 'number', digit: 2, visible:false}
            , {field: 'tradeAmt', width: 150, title: '交易金额', sortable: false, type: 'number', digit: 2}
            , {field: 'bussTrdAvailableCashQueryavalibleCash',width: 150,title: '组合T日可用现金', sortable: false, type: 'number', digit: 2}
            , {field: 'bussTrdAvailableCashQueryavalibleCash1', width: 150, title: '组合T+1日可用现金', sortable: false, type: 'number', digit: 2}
            , {field: 'channelAvailableCash', width: 150, title: function () {
                    return JoyinUtil.getLabel("invest.channelId") + JoyinUtil.getLabel("EtrInvestBondInfo002.channelAvailableCash");
                }(), sortable: false, type: 'number', digit: 2}
            , {field: 'Assetsavailablepositions', width: 150, title: '资产可用持仓', type: 'date', sortable: false, type: 'number', digit: 2}
            , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['delt']}//编辑和删除事件
        ],
        editCell: function (value, data, field, cell, column) {
//     	   var params={};
//		   table.renderRow(data.LAY_TABLE_INDEX,  {"portfolioName": '2222'});
            //买入
            var intAmta = data.intAmt;
            var mmm = $("#chlId").find("option:selected").data("collecttype");
            var finprodId = null;

            if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
                finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            if ("portfolioId" == field) {
                object = {
                    finprodId: finprodId,
                    portfolioId: value,
                    ps: $('#ps').val(),
                    chlId: $('#chlId').val(),
                    tradeDate: $('#tradeDate').val(),
                    ccy: $('#ccy').val()
                };
                if (object.portfolioId != null && object.portfolioId != '' && object.ps != null && object.ps != '' && object.ccy != null && object.ccy != '' && object.tradeDate != null) {
                    Joyin.ajax(basePath + "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/available", object, function (result) {
                        if (object.ps == PS_T01) {
                            //如果资产代码为空，后台返回空，赋值报错
                            if (result.length > 0) {
                                table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": result[0].name});
                                table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": result[1].name});
                                table.renderRow(data.LAY_TABLE_INDEX, {"Assetsavailablepositions": ' '});
                                if ($('#chlId').val()) {
                                    table.renderRow(data.LAY_TABLE_INDEX, {"channelAvailableCash": result[3].name});
                                }

                                if (result[3].name) {
                                    isHoliday = result[3].name;
                                }
                            }

                        } else {
                            if (result.length > 0) {
                                table.renderRow(data.LAY_TABLE_INDEX, {"Assetsavailablepositions": result[0].name});
                                table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash": ' '});
                                table.renderRow(data.LAY_TABLE_INDEX, {"bussTrdAvailableCashQueryavalibleCash1": ' '});
//                                $('#Assetsavailablepositions').val(result[0].name).trigger('blur');
                                if (result[1].name) {
                                    isHoliday = result[1].name;
                                }
                            }
                        }
                    });
                }
                Joyin.ajax(basePath + "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade003/getIsGuarantee", {portfolioId: data.portfolioId}, function (result) {
                    if (result != null && result !== '') {
                        if (result !== YES) {
                            /*划款取消隐藏 panbin 2020-7-12 */
                            // $("#bussTrdTransferOrder_div").css('display', '');
                            isOpics = 'N';
                        } else {
                            /*划款取消隐藏 panbin 2020-7-12 */
                            // $("#bussTrdTransferOrder_div").css('display', 'none');
                            isOpics = 'Y';
                        }
                    }
                });
            }
            if ("prinAmt" == field) {
                var obj = {flag: false, row: data.LAY_TABLE_INDEX, value: value}
                intAmtDistribution(obj);
                channelFeeDistribution();
            }
        }
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        table.removeRow(data);
    });

    /**
     * 界面初始化
     * @returns
     */
    function init() {
        if (pageModel == PAGE_MODEL_NEW) {
            Joyin.ajax(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getInitDate', {}, function (result) {
                $('#tradeDate').val(result);
            });
        }
        if (pageModel == PAGE_MODEL_NEW) {
            Joyin.ajaxJson(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getTradeDate', {}, function (result) {
                $('#tradeDate').val(result.tradeDate);
                settleDateCalcu();
            });
        }
//        if($('#entrustId').val() != null && $('#entrustId').val() != ''){
//            $('#portfolioId').attr('disabled',true);
//            $('#finprodMarketId').attr('disabled',true);
//            $('#prinAmt').attr('disabled',true);
//            $('#ps').attr('disabled',true).trigger("change");
//        }
        $(".hidden").attr('disabled', true);
//            if($('#bussTrdAvailableCashQueryavalibleCash').val() != null && $('#bussTrdAvailableCashQueryavalibleCash').val() != ''){
//                available();
//            }
//            if($('#portfolioId').val() != null){
//                portChange();
//            }
//            finChange();
//            psChange();
        getEntrustByEntrustId();
    }

    /**
     * 清算速度联动带出
     */
    $('#settleSpeed').change(function () {
        settleDateCalcu();
    });

    /**
     * 交割日计算
     */
    var settleDateCalcu = function () {
        if ($('#tradeDate').val() != null && $('#tradeDate').val() != ''
            && $('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
            Joyin.ajaxJson(basePath + "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/settleDateCalcu",
                {
                    tradeDate: $('#tradeDate').val(),
                    settleSpeed: $('#settleSpeed').val(),
                    finprodId: $('#finprodMarketId').select2("data")[0].object
                }, function (result) {
                    $('#settleDate').val(result.settleDate);
                    calendarId = result.calendarId;
                    isHoliday = result.isHoliday;
                    // 重新计算利息总额，先清空利息总额
                	$('#intAmt').val('').trigger('blur');
                    intAmtCalcu();
                    if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery']) {
                        updateCash();
                    }
                });
        } else {
            $('#settleDate').val('');
        }

    }
  //modify by hxy 20200325 start
    //判断列表中通道和通道费是否显示
    function channelFeeIsShow(){
    	if($('#finprodMarketId').val()){
    		if($('#chlId').val() && 'T02'==$('#ps').val()){
                var data = $('#chlId').find('option:selected').data('collecttype');
                if(data && data==collectType_01){
                      $('#channelFee_div').show();
                  	  table.showColumns([{field:'channelFee',visible: true}]);
               }else{
                  	 $('#channelFee_div').hide();
                   	 $('#channelFee').val('');
                   	table.showColumns([{field:'channelFee',visible: false}]);
               }
          }else{
          	     $('#channelFee_div').hide();
          	     $('#channelFee').val('');
             	table.showColumns([{field:'channelFee',visible: false}]);
          }	
       }
    }
    //modify by hxy 20200325 end 
    
    /**
     * 查询资产管理人
     */
    var selectManeger = function (){
        var finprodId = $("#finprodMarketId").select2("data")[0].object;
        if(finprodId){
            Joyin.ajaxJson(basePath + '/invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/selectFManeger', {finprodId:finprodId},
                    function (result) {
                        $("#trdInvestPersonInfo002Dto_counterId").textSelect('setValue', result.id);
                        $("#trdInvestPersonInfo002Dto_counterId").trigger('change');
                    });
        }    
    }

    /**
     * 获取查询信息
     */
    var getParameters = function () {
        if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
            finprodId = $("#finprodMarketId").select2("data")[0].object;
        }
        return {
            finprodId: finprodId,
            vdate: $('#vdate').val(),
            mdate: $('#mdate').val(),
            portfolioId: $('#portfolioId').val(),
            ps: $('#ps').val(),
            chlId: $('#chlId').val(),
            tradeDate: $('#tradeDate').val(),
            ccy: $('#ccy').val()
        };
    };

    /*
     * 资产代码联动带出
     */
    $('#finprodMarketId').change(function () {
        finChange();
        psChange();
        settleDateCalcu();
        channelFeeIsShow();
    });

    /**
     * 资产代码变动联动事件
     */
    var finChange = function () {
        var finprodIdJson = null;
        var finprodId = null;
        if ($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != '') {
            finprodId = $("#finprodMarketId").select2("data")[0].object;
            finprodIdJson = {finprodId: finprodId};
        }
        if (finprodIdJson != null && finprodIdJson.finprodId != null && finprodIdJson.finprodId != '') {
            Joyin.ajaxJson(basePath + "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getFinprodType",
                {finprodId: $('#finprodMarketId').select2('data')[0].object}, function (result) {
                    if (result != null && result != '') {
                        var vdate = Date.ofDate(result.vdate);
                        var mdate = Date.ofDate(result.mdate);
                        $('#vdate').val(vdate.format('yyyy-MM-dd'));
                        $('#mdate').val(mdate.format('yyyy-MM-dd'));
                        $('#finprodType').val(result.finprodType).trigger('change');
                        // 新增五级分类 王贵奇 2019/8/1 开始
                        $('#type9').val(result.type9).trigger('change');
                        $('#type10').val(result.type10).trigger('change');
                        $('#type11').val(result.type11).trigger('change');
                        // 新增五级分类 王贵奇 2019/8/1 结束
                        $('#ccy').val(result.ccy).trigger('change');
                        $('#investRate').val(result.investRate);
                        available();
                    } else {
                        $('#vdate').val('');
                        $('#mdate').val('');
                        $('#loadRate').val('');
                        $('#finprodType').val('').trigger('change');
                        $('#termDays').val('');
                        $('#ccy').val('').trigger('change');
                        $('#investRate').val('').trigger('change');
                    }
                    // 重新计算利息总额，先清空利息总额
                	$('#intAmt').val('').trigger('blur');
                    intAmtCalcu();
                });
            $('#finprodName').val($('#finprodMarketId').select2('data')[0].id);
        } else {
            $('#finprodName').val('');
            $('#finprodType').val('').trigger('change');
            $('#investRate').val('').trigger('change');
            $('#ccy').val('').trigger('change');
        }
        ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, $("#entrustId").val(), function () {

        });
    }


    /*
     * 交易方向修改联动显示持仓，和非持仓
     */
    $('#ps').change(function () {
        psChange();
        available();
        channelFeeIsShow();
    });

    var psChange = function () {
        var ps = $('#ps').val();
//        if(ps == PS_T01){
//            window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showCash(true);
//        }else{
//            window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showPosition(true);
//        }
        if(ps == PS_T01){

            table.showColumns([{field: 'Assetsavailablepositions', visible: false},{field: 'bussTrdAvailableCashQueryavalibleCash', visible: true},{field: 'bussTrdAvailableCashQueryavalibleCash1', visible: true}]);
        }else if(ps == PS_T02){
            table.showColumns([{field: 'Assetsavailablepositions', visible: true},{field: 'bussTrdAvailableCashQueryavalibleCash', visible: false},{field: 'bussTrdAvailableCashQueryavalibleCash1', visible: false}]);
        }

//        if(ps !== '01'){
//            $('#Buy').css('display','none');
//            // <!-- 通道交易 新增 强成西 2019/03/26 start -->
//            $('.channel_cash').css('display','none');
//            // <!-- 通道交易 新增 强成西 2019/03/26 end -->
//            $('#Sale').css('display','');
//        }else{
//            $('#Buy').css('display','');
//            // <!-- 通道交易 新增 强成西 2019/03/26 start -->
//            $('.channel_cash').css('display','');
//            // <!-- 通道交易 新增 强成西 2019/03/26 end -->
//            $('#Sale').css('display','none');
//        }
        //卖出 且选择通道时 显示通道费
//        if(ps==PS_T02&&$("#chlId").val()){
//        	$("#channelFee_div").show();
//        }else{
//        	$("#channelFee_div").hide();
//        	$("#channelFee").val(JoyinMath.doAdd(0, 0, 2));
//        }
        var finprodId = null;
        if ($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != '') {
            finprodId = $("#finprodMarketId").select2("data")[0].object;
        }
        Joyin.ajaxJson(basePath
            + 'component/trade/BussChannelCommon/getChannelIdAndCollectTypeForAsset?assetChl=true&finprodId=' + finprodId + "&_v=" + Math.random(),
            {}, function (result) {
                if (result && result.length > 0 && $('#ps').val() == PS_T02) {
                	//$("#channelFee_div").show();
                	channelFeeIsShow();
                } else {
                    $("#channelFee_div").hide();
                    $("#channelFee").val(JoyinMath.doAdd(0, 0, 2));
                }
            });
        changeTradeAmtAndFee();
        channelFeeDistribution();
        channelFeeIsShow();
    }

    var showTableChannelFee = function () {

        var ps = $('#ps').val();
        //卖出 且选择通道时 显示通道费
        if (ps == PS_T02 && $("#chlId").val()) {
            table.showColumns([{field: 'channelFee', visible: true}]);
        } else {
            table.showColumns([{field: 'channelFee', visible: false}]);
        }
        table.clear();
    }

    var portChange = function () {
        Joyin.ajax(basePath + "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade003/getIsGuarantee", {portfolioId: $('#portfolioId').val()}, function (result) {
            if (result != null && result !== '') {
                if (result !== YES) {
                    //$("#OpicsTable").css('display','none');
                    $("#BussTrdTransferOrder").css('display', '');
                    isOpics = 'N';
                } else {
                    //$("#OpicsTable").css('display','');
                    $("#BussTrdTransferOrder").css('display', 'none');
                    isOpics = 'Y';
                }
            }
        });
    }

    /**
     * 通道事件
     */
    $('#chlId').change(function () {
        available();
//    	table.reload();
//      //卖出 且选择通道时 显示通道费
//        if($("#ps").val()&&$("#ps").val()==PS_T02&&$("#chlId").val()){
//        	$("#channelFee_div").show();
//        }else{
//        	$("#channelFee_div").hide();
//        }
//        changeTradeAmtAndFee();
//    	psChange();
//        showTableChannelFee();
        channelFeeIsShow();

    });
    /**
     * 资产代码
     */
    $('#finprodMarketId').change(function(){
        selectManeger();
    });

    /**
     * 金融信息-本金金额事件
     */
    $('.rateCal').change(function () {
    	// 重新计算利息总额，先清空利息总额
    	$('#intAmt').val('').trigger('blur');
        intAmtCalcu();
        channelFeeDistribution();
    });

    /**
     * 金融信息-利息金额事件
     */
    $('#intAmt').change(function () {
    	//update by liuwenjuan 应计利息总额多组合分配 start
    	intAmtCalcu();
    	//update by liuwenjuan end
        changeTradeAmtAndFee();
    });


    /**
     * 金融信息-通道费事件
     */
    $("#channelFee").on("change", function () {
        changeTradeAmtAndFee();
        channelFeeDistribution();
    });

    /**
     * 金融信息-利息总额计算
     */
    var intAmtCalcu = function () {
        if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
            finprodId = $("#finprodMarketId").select2("data")[0].object;
        }
        object = {
            finprodId: finprodId,
            settleDate: $('#settleDate').val(),
            prinAmt: $('#prinAmt').val()
        }
        if (object.finprodId != null && object.finprodId != null &&
            object.settleDate != '' && object.settleDate != '' && object.prinAmt != null && object.prinAmt != '') {
            Joyin.ajaxJson(basePath + "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getIntAmt", object, function (result) {
                //update by liuwenjuan 应计利息总额多组合分配 start
                $('#intAmt').val($('#intAmt').val()==""?result.data:$('#intAmt').val()).trigger('blur');
                changeTradeAmtAndFee();
                var obj = {flag: true, intAmtAll: $('#intAmt').val()==""?result.data:$('#intAmt').val()}
                //update by liuwenjuan end
                intAmtDistribution(obj);
            });
        } else {
            $('#intAmt').val('').trigger('blur');
            changeTradeAmtAndFee();
        }
    }

    /**
     * 组合分配-应计利息总额分配
     */
    function intAmtDistribution(object) {
        var intAmt = 0;
        var intAmtAll;
        var prinAmtAll = $("#prinAmt").val();
        if (object.flag) {
            intAmtAll = object.intAmtAll;
        } else {
            intAmtAll = $("#intAmt").val();
        }
        if (prinAmtAll && intAmtAll) {
            var rownum = 0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for (var i = 0; i < table.getAllRows().length; i++) {
                var a;
                if (object.row == i && !object.flag) {
                    a = object.value;
                } else {
                    a = table.getAllRows()[i].prinAmt;
                }
                if (a && Number(a) != Number(0)) {
                    rownum = i;
                }
            }
            for (var i = 0; i < table.getAllRows().length; i++) {
                var aa;
                if (object.row == i && !object.flag) {
                    aa = object.value;
                } else {
                    aa = table.getAllRows()[i].prinAmt;
                }
                if (i == rownum && Number(aa) != Number(0)) {//最后一个组合采用倒减法
                    if (aa) {
                        intAmt = JoyinMath.doMinus(intAmtAll, sumAmt, 2);
                    } else {
                        intAmt = JoyinMath.doAdd(0, 0, 2);
                    }
                } else {
                    if (aa && Number(aa) != Number(0)) {
                        var scale = JoyinMath.doMul(intAmtAll, aa, 6);
                        intAmt = JoyinMath.doDiv(scale, prinAmtAll, 2);
                    } else {
                        intAmt = JoyinMath.doAdd(0, 0, 2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, intAmt, 2);
                }
                table.renderRow(i, {"intAmt": intAmt});
            }
            tradeAmtAndFeeDistribution();
        }
    }

    /**
     * 组合分配-通道费计算
     */
    var channelFeeDistribution = function() {
        var channelFee = 0;
        var prinAmtAll = $("#prinAmt").val();
        var channelFeeAll = $("#channelFee").val();
        if(prinAmtAll && channelFeeAll){
            var rownum =0;
            var sumAmt = 0;
            //寻求表格数据本金金额非零最后索引值
            for(var i=0;i<table.getAllRows().length;i++){
                var a = table.getAllRows()[i].prinAmt;
                if(a && Number(a) != Number(0)){
                    rownum = i;
                }
            }
            for(var i=0;i<table.getAllRows().length;i++){
                if(i== rownum){//最后一个组合采用倒减法
                    channelFee = JoyinMath.doMinus(channelFeeAll,sumAmt,2);
                }else{
                    if(table.getAllRows()[i].prinAmt && Number(table.getAllRows()[i].prinAmt)!= Number(0)){
                        var scale = JoyinMath.doMul(channelFeeAll,table.getAllRows()[i].prinAmt,6);
                        channelFee = JoyinMath.doDiv(scale, prinAmtAll, 2);
                    }else{
                        channelFee = JoyinMath.doAdd(0,0,2);
                    }
                    sumAmt = JoyinMath.doAdd(sumAmt, channelFee, 2);
                }
                table.renderRow(i,  {"channelFee": channelFee});
            }
            tradeAmtAndFeeDistribution();
        }else{
            for(var i=0;i<table.getAllRows().length;i++){
                table.renderRow(i,  {"channelFee": JoyinMath.doAdd(0, 0, 2)});
            }
            tradeAmtAndFeeDistribution();
        }
    }

    /**
     * 金融信息-交易金额计算
     */
    var changeTradeAmtAndFee = function () {
        var data = $("#chlId").find("option:selected").data("collecttype");
        if ($("#chlId").val() && data) {
            if (($("#trdInvestPersonInfo002Dto_counterId").val() && $("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object == 'N')
                || ($("#trdInvestPersonInfo002Dto_counterId").val() && $("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object != 'N'
                    && $("#ps").val() == PS_T02 && data == collectType_01)) {
                $("#tradeAmt").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
            } else if (!$("#trdInvestPersonInfo002Dto_counterId").val() && data == collectType_01) {
                $("#tradeAmt").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
            } else {
                $("#tradeAmt").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
            }
        } else {
            $("#tradeAmt").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
        }
        //标的挂通道，通道费不影响交易金额
        //$("#tradeAmt").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
    }


    /**
     * 组合分配-交易金额计算
     */
    var tradeAmtAndFeeDistribution = function () {
        var mmm = $("#chlId").find("option:selected").data("collecttype");
        var tmpList = table.getAllRows();
        for (var i in tmpList) {
            if (tmpList[i].prinAmt) {
                if ($("#chlId").val() && mmm) {
                    if (($("#trdInvestPersonInfo002Dto_counterId").val() && $("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object == 'N')
                        || ($("#trdInvestPersonInfo002Dto_counterId").val() && $("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object != 'N'
                            && $("#ps").val() == PS_T02 && mmm == collectType_01)) {
                        table.renderRow(i, {"tradeAmt": JoyinMath.doMinus(JoyinMath.doAdd(tmpList[i].prinAmt, tmpList[i].intAmt, 2), tmpList[i].channelFee, 2)});
                    } else if (!$("#trdInvestPersonInfo002Dto_counterId").val() && mmm == collectType_01) {
                        table.renderRow(i, {"tradeAmt": JoyinMath.doMinus(JoyinMath.doAdd(tmpList[i].prinAmt, tmpList[i].intAmt, 2), tmpList[i].channelFee, 2)});
                    } else {
                        table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(tmpList[i].prinAmt, tmpList[i].intAmt, 2)});
                    }
                } else {
            	//标的挂通道不影响交易金额
                    table.renderRow(i, {"tradeAmt": JoyinMath.doAdd(tmpList[i].prinAmt, tmpList[i].intAmt, 2)});
                }
            }
        }
    }

    /**
     * 获取持仓、现金余额、可用现金等
     * @param object json对象
     * @returns 获取持仓、现金余额、可用现金等
     */
    function available() {
        if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
            finprodId = $("#finprodMarketId").select2("data")[0].object;
        }
        var tmpList = table.getAllRows();
        for (var i in tmpList) {
            object = {
                finprodId: finprodId,
                vdate: $('#vdate').val(),
                mdate: $('#mdate').val(),
                portfolioId: tmpList[i].portfolioId,
                ps: $('#ps').val(),
                chlId: $('#chlId').val(),
                tradeDate: $('#tradeDate').val(),
                ccy: $('#ccy').val()
            };
            if (object.portfolioId != null && object.portfolioId != '' && object.ps != null && object.ps != '' && object.ccy != null && object.ccy != '' && object.tradeDate != null) {
                Joyin.ajaxSync(basePath + "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade003/available", object, function (result) {
                    if (object.ps == PS_T01) {
                        //如果资产代码为空，后台返回空，赋值报错
                        if (result.length > 0) {
                            table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash": result[0].name});
                            table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash1": result[1].name});
                            table.renderRow(i, {"Assetsavailablepositions": ' '});
                            table.renderRow(i, {"channelAvailableCash": ' '});
                            if ($('#chlId').val()) {
                                table.renderRow(i, {"channelAvailableCash": result[3].name});
                            }
                            if (result[3].name) {
                                isHoliday = result[3].name;
                            }
                        }

                    } else {
                        if (result.length > 0) {
                            table.renderRow(i, {"Assetsavailablepositions": result[0].name});
                            table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash": ' '});
                            table.renderRow(i, {"bussTrdAvailableCashQueryavalibleCash1": ' '});
                            if (result[1].name) {
                                isHoliday = result[1].name;
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * 若从委托场合点击交易录单
     */
    function getEntrustByEntrustId() {

        var entrustId = $("#entrustId").val();
        if (entrustId) {

            finChange();
            psChange();

            $('#TrdPlanRateInvestTrade003_distribution').attr('disabled', false);
            $('#TrdPlanRateInvestTrade003_btn_autoAllocation').removeClass('layui-btn-disabled');

            $("#finprodName").attr('disabled', true);
            $("#finprodMarketId").attr('disabled', true);
            $("#ps").attr('disabled', true).trigger("change");
//            $("#prinAmt").attr('disabled',false);
//            $("#channelFee").attr('disabled',false);
//            table.showColumns([{field:'portfolioId',edit:false}]);
//            table.showColumns([{field:'prinAmt',edit:false}]);
//            table.showColumns([{field:'channelFee',edit:false}]);
            var finprodId = null;
            if ($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != '') {
                finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            Joyin.ajaxJson(basePath
                + 'component/trade/BussChannelCommon/getChannelIdAndCollectTypeForAsset?assetChl=true&finprodId=' + finprodId + "&_v=" + Math.random(),
                {}, function (result) {
                    if (result && result.length > 0 && $('#ps').val() == PS_T02) {
                        $("#channelFee_div").show();
                    } else {
                        $("#channelFee_div").hide();
                    }
                });
            available();
            setTimeout(function () {
                $('.btn-table-add').remove();
                setTimeout(function () {
                    $('.delt').remove();
                }, 1000)
            }, 1500);
        }else{
            $('#TrdPlanRateInvestTrade003_distribution').attr('disabled', true);
            $('#TrdPlanRateInvestTrade003_btn_autoAllocation').addClass('layui-btn-disabled');
        }
    }

    $('#TrdPlanRateInvestTrade003_btn_autoAllocation').click(function () {
        if ($("#entrustId").val()) {
            //平均分配
            if ($('#TrdPlanRateInvestTrade003_distribution').val() == '139') {//平均分配
                extracted();
            } else if ($('#TrdPlanRateInvestTrade003_distribution').val() == '140') {//时间优先
                extractedTime();
            } else if ($('#TrdPlanRateInvestTrade003_distribution').val() == '141') {//数量优先
                extractedNumber();
            }
            var obj = {flag: true, intAmtAll: $("#intAmt").val()}
            intAmtDistribution(obj);
            channelFeeDistribution();
            setTimeout(function () {
                $('.btn-table-add').remove();
                setTimeout(function () {
                    $('.delt').remove();
                }, 1000)
            }, 1500);
        }
    });

    /**
     * 平均分配(按剩余委托额度占比)
     */
    function extracted() {
        var prinAmt = $('#prinAmt').val();
        if (prinAmt) {
            prinAmt = JoyinMath.doAdd(prinAmt, 0, 2);
            //总剩余委托额度
            var entrustMoneyAll = JoyinMath.doAdd(0, 0, 2);
            for (var i = 0; i < table.getAllRows().length; i++) {
                entrustMoneyAll = JoyinMath.doAdd(entrustMoneyAll, table.getAllRows()[i].doEntrustMoney, 2);
            }
            var rownum = table.getAllRows().length - 1;
            var sumAmt = 0;
            for (var i = 0; i < table.getAllRows().length; i++) {
                var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
                doEntrustMoney = JoyinMath.doAdd(doEntrustMoney, 0, 2);
                var amt = JoyinMath.doAdd(0, 0, 2);
                if (i == rownum) {//最后一个组合采用倒减法
                    amt = JoyinMath.doMinus(prinAmt, sumAmt, 2);
                } else {
                    var scale = JoyinMath.doMul(prinAmt, doEntrustMoney, 6);
                    amt = JoyinMath.doDiv(scale, entrustMoneyAll, 2);
                }
                if (Number(amt) > Number(doEntrustMoney)) {
                    table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
                    sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
                } else {
                    table.renderRow(i, {"prinAmt": JoyinMath.doAdd(amt, 0, 2)});
                    sumAmt = JoyinMath.doAdd(sumAmt, amt, 2);
                }
            }
        }
    }

    /**
     * 时间优先
     */
    function extractedTime() {
        table.reload();
        var prinAmt = $('#prinAmt').val();
        if (prinAmt) {
            prinAmt = JoyinMath.doAdd(prinAmt, 0, 2);
            var sumAmt = JoyinMath.doAdd(0, 0, 2);
            for (var i = 0; i < table.getAllRows().length; i++) {
                var doEntrustMoney = table.getAllRows()[i].doEntrustMoney;
                if (Number(JoyinMath.doAdd(sumAmt, doEntrustMoney, 2)) > Number(prinAmt)) {
                    table.renderRow(i, {"prinAmt": JoyinMath.doMinus(prinAmt, sumAmt, 2)});
                    for (var j = i + 1; j < table.getAllRows().length; j++) {
                        table.renderRow(j, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
                    }
                    break;
                } else {
                    table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
                    sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
                }
            }
        }
    }


    /**
     * 数量优先
     */
    function extractedNumber() {
        var prinAmt = $('#prinAmt').val();
        if (prinAmt) {
            var allRows = table.getAllRows();

            //先按照本金金额排序
            function sortValue(a, b) {
                return b.doEntrustMoney - a.doEntrustMoney;
            }

            allRows = allRows.sort(sortValue);
            table.clear();
            table.addRow(allRows);
            var sumAmt = JoyinMath.doAdd(0, 0, 2);
            for (var i = 0; i < table.getAllRows().length; i++) {
                var list = new Array();
                var doEntrustMoney = JoyinMath.doAdd(table.getAllRows()[i].doEntrustMoney, 0, 2);
                for (var j = i + 1; j < table.getAllRows().length; j++) {
                    if (Number(doEntrustMoney) == Number(JoyinMath.doAdd(table.getAllRows()[j].doEntrustMoney, 0, 2))) {
                        list.push(j);
                    }
                }
                if (list.length > 0) {
                    var amt = JoyinMath.doAdd(doEntrustMoney, 0, 2);
                    for (var k = 0; k < list.length; k++) {
                        amt = JoyinMath.doAdd(amt, table.getAllRows()[list[k]].doEntrustMoney, 2);
                    }
                    if (Number(JoyinMath.doAdd(sumAmt, amt, 2)) > Number(prinAmt)) {
                        var num = JoyinMath.doMinus(prinAmt, sumAmt, 5);
                        table.renderRow(i, {"prinAmt": JoyinMath.doDiv(num, list.length + 1, 2)});
                        for (var k = 0; k < list.length; k++) {
                            table.renderRow(list[k], {"prinAmt": JoyinMath.doDiv(num, list.length + 1, 2)});
                        }
                        for (var m = i + list.length + 1; m < table.getAllRows().length; m++) {
                            table.renderRow(m, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
                        }
                        break;
                    } else {
                        table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
                        for (var k = 0; k < list.length; k++) {
                            table.renderRow(list[k], {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
                        }
                        sumAmt = JoyinMath.doAdd(sumAmt, amt, 2);
                        i = i + list.length;
                    }
                } else {
                    if (Number(JoyinMath.doAdd(sumAmt, doEntrustMoney, 2)) > Number(prinAmt)) {
                        table.renderRow(i, {"prinAmt": JoyinMath.doMinus(prinAmt, sumAmt, 2)});
                        for (var j = i + 1; j < table.getAllRows().length; j++) {
                            table.renderRow(j, {"prinAmt": JoyinMath.doAdd(0, 0, 2)});
                        }
                        break;
                    } else {
                        table.renderRow(i, {"prinAmt": JoyinMath.doAdd(doEntrustMoney, 0, 2)});
                        sumAmt = JoyinMath.doAdd(sumAmt, doEntrustMoney, 2);
                    }
                }

            }
        }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#bussTrdTransferOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end

    /**
     * 画面加载完成
     */
    $(function () {
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#BussTrdTransferOrder').hide() ;
        }else{
        	$('#BussTrdTransferOrder').show() ;
        }
        //保存
        $('#TrdPlanRateInvestTrade002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        //提交
        $('#TrdPlanRateInvestTrade002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });

        //查看
        $('#TrdPlanRateInvestTrade003_btn_001').click(function () {
            if ($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != '') {
                finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            Joyin.dialog(JoyinUtil.getLabel("FinPlanProfitAsset002"),
                basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    finprodId: finprodId
                });
        })

      //风险试算
        $('#TrdPlanRateInvestTrade003_btn_riskMath').click(function () {
          //检查组合分配的和金额信息是否一致
            if($('#prinAmt').val()){
                var number = 0;
                for(var i=0;i<table.getAllRows().length;i++){
                    var prinAmt=table.getAllRows()[i].prinAmt==" "?0:table.getAllRows()[i].prinAmt;
                    if(prinAmt){
                        number =  JoyinMath.doAdd(number,prinAmt,2);
                    }
                }
                if(JoyinMath.doAdd($('#prinAmt').val(),0,2)!=number){
                    Joyin.warn("组合分配中本金金额累计值和金额信息中本金金额不一致");
                    return;
                }
            }
            var object = $.serializeToObject('#basicInfoForm', true);
            if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
                object.finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/1/RSK002/F03',object,getriskdata());
//            Joyin.ajax(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/selectIndex', {}, function (result) {
//                if (result) {
//                    var index = result;
//                    var object1 = getriskdata();
//                    var list2 = object1.data;
//                    for (var i in list2) {
//                        object1.data[i].mun = list2.length;
//                        object1.data[i].index = index;
//                    }
//                    //window.BaseRiskMathComm.initBaseRiskMathCommPage2(object1);
//                    //window.BaseRiskMathComm.getparam(getriskdata());
//                    $("#riskMathFlag").val("02")
//                }
//            });
        });
    });
}());