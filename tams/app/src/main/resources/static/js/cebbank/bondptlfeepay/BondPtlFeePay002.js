
// 定义函数作用域(立即执行),防止全局变量污染
$(function() {
	
	var calcConditionObjBackUp;
	var cols1 = [{field : 'ptlId',width:140,title : '组合代码'
		}, {field : 'portFolioName',width:180,title : 'InvestFeePay002.feeTable.portFolioName'
		}, {field : 'yesterdayPosition',width:240,title : '前一日港股持仓市值（港元）',type : 'number',textrange : '2'
		}, {field : 'actPayAmt',width:160,title : '实付金额（元）',type : 'number',textrange : '2',edit:true
		}, {field : 'secAcctId',visible : false
		}];
	
	/**
	 * 付费明细
	 */
	var feeInfoTable = $('#fee_dataTable').joyintable({
		ajax : {
			url : JoyinUtil.getRealPath('/cebbank/bondptlfeepay/BondPtlFeePay002/queryPayFeeInfoList'),
			type : 'POST',
			data : function() {
				return {
						workflowId : workflowId,
						secAcctId : $('#BondPtlFeePay002_secAcctId').val(),
						payDate : $("#BondPtlFeePay002_payDate").val(),
						actPayAmt : $("#BondPtlFeePay002_actPayAmt").val(),
						operateType : $('#BondPtlFeePay002_hidden_operateType').val()
				};
			}
		},
		loadonce : true,
		//searchOnInit: !($('#BondPtlFeePay002_hidden_feeType').val() === ''),
		searchOnInit: true,
        height: 300,
        page: false,
        checkbox: false,
        rowno: true,
		cols : cols1,
        editCell : function(value, data, field, cell, column){
        	if (field === "actPayAmt"){
        		if (typeof value == "undefined" || value == null || value == "") {
        			data.adjustAmt = JoyinMath.doMinus(data.actPayAmt, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        		} else {
        			data.adjustAmt = JoyinMath.doMinus(value, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        		}
            	feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
            		
        		var totalAmt = '0';
        		var allData = feeInfoTable.getAllRows();
            	if (allData && allData.length > 0){
            		$.each(allData, function(id, value){
    	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
    	        	});
            	}
            	$('#BondPtlFeePay002_hidden_tradeAmt').val(totalAmt);
            	//为一次性费用实付金额赋值
            	$('#BondPtlFeePay002_actPayAmt').val(totalAmt).trigger('change');
        	}
        },
        done : function(allData, table){
        	var totalAmt = '0';
        	var actPayAmtCurSum = '0';
        	var yesterdayPositionSum = '0';debugger;
        	if (allData && allData.length > 0 && !isTodoList && $('#BondPtlFeePay002_hidden_operateType').val() === 'new'){
        		// 当前理论支付金额
        		//var payAmtCur = $('#BondPtlFeePay002_payAmt').val();
        		// 当前理论实际支付金额
        		//var actPayAmtCur = $('#BondPtlFeePay002_actPayAmt').val();
        		/*$.each(allData, function(id, value){
        			// 如果理论支付金额不等于理论实际支付金额，按比例计算实际支付金额，尾差计入最后一条数据
        			if (!(payAmtCur === actPayAmtCur)) {
        				var indexCur = value.LAY_TABLE_INDEX + 1;
        				if (indexCur === allData.length) {
        					value.actPayAmt = JoyinMath.doMinus(actPayAmtCur, actPayAmtCurSum, 2, JoyinMath.ROUND_HALF_UP);
        				} else {
        					value.actPayAmt = JoyinMath.doMul(actPayAmtCur,JoyinMath.doDiv(value.payAmt,payAmtCur,2, JoyinMath.ROUND_HALF_UP));
        					actPayAmtCurSum = JoyinMath.doAdd(actPayAmtCurSum,value.actPayAmt);
        				}
        			}
	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
    				var adjustAmt = JoyinMath.doMinus(value.actPayAmt, value.payAmt, 2, JoyinMath.ROUND_HALF_UP);
    				value.adjustAmt = adjustAmt;
    				feeInfoTable.renderRow(value.LAY_TABLE_INDEX, value);
        		});*/
        		
        		$.each(allData, function(id, value){
        			yesterdayPositionSum = JoyinMath.doAdd(yesterdayPositionSum, value.yesterdayPosition, 2, JoyinMath.ROUND_HALF_UP);
    				feeInfoTable.renderRow(value.LAY_TABLE_INDEX, value);
        		});
        		$('#BondPtlFeePay002_yesterdayPosition').val(yesterdayPositionSum);
        	}
        }
	});
	
	function reloadTable(){
		$('#fee_div').toggle(true);
		if(feeInfoTable.table){
			feeInfoTable.reload();
		}else{
			/*if ($('#BondPtlFeePay002_hidden_feeType').val()) {*/
				feeInfoTable.load();
			/*}*/
		}
	}
	
	/**
	 * 支付日联动处理
	 */
	function payDateChangeAction(){
		endDateChangeAction();
		payTradeAmtChangeAction();
	}
	
	/**
	 * 实付金额联动处理
	 */
	function actPayAmtChangeAction(){
		var actPayAmt = $('#BondPtlFeePay002_actPayAmt').val();
		var payAmt = $('#BondPtlFeePay002_payAmt').val();
		if (payAmt && actPayAmt){
			$('#BondPtlFeePay002_adjustAmt').val(JoyinMath.doMinus(actPayAmt, payAmt, 2, JoyinMath.ROUND_HALF_UP)).trigger('change');
		}else{
			$('#BondPtlFeePay002_adjustAmt').val('').trigger('change');		
		}
		$('#BondPtlFeePay002_hidden_tradeAmt').val($('#BondPtlFeePay002_actPayAmt').val());
		
	};
	
	/**
	 * 实付金额联动处理
	 */
	function calcActPayAmtAction(){
		var actPayAmtHk = $('#BondPtlFeePay002_actPayAmtHk').val();
		var sellSettleRate = $('#BondPtlFeePay002_sellSettleRate').val();
		if (actPayAmtHk && sellSettleRate){
			$("#BondPtlFeePay002_actPayAmt").val(JoyinMath.doMul(actPayAmtHk, sellSettleRate, 4)).trigger('blur');
		}else{
			$('#BondPtlFeePay002_actPayAmt').val('').trigger('change');		
		}
	};
	
    /**
     * 计算按钮点击事件
     */
    $('#BondPtlFeePay002_btn_calculate').click(function() {
		var secAcctId = $('#BondPtlFeePay002_secAcctId').val();
		var payDate = $("#BondPtlFeePay002_payDate").val();
		var actPayAmtHk = $("#BondPtlFeePay002_actPayAmtHk").val();
		if (!secAcctId) {
			Joyin.error("证券账户不能为空！")
            return;
		}
    	if (!payDate) {
    		Joyin.error("付费日期不能为空！")
            return;
    	}
    	if (!actPayAmtHk) {
    		Joyin.error("实付金额不能为空！")
            return;
    	}
    	reloadTable();
    });
	
	/**
     * 划款指令定义数据表格
     *
     */
    var option = {
        searchOnInit: true,
        checkbox: false,
        cols: [
            {
                field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true, sortable:false,
                editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
            }
            , {field: 'payTypeText', width:130, title: 'BussTrdTransferOrder.payType', sortable:false, sortable:false}
            , {field: 'settleDateText', width:130, title: 'BussTrdTransferOrder.settleDate', sortable:false}
            , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable:false}
            , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable:false}
            , {
            	field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable:false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds:'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/common/multiOrder/select"), id: 'payAcctNo', postData:function () {
                        return {selectValue: JSON.stringify(orderAcctMap), type: 'pay'};
                    }
                }
            }
            , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable:false}
            , {
            	field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable:false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds:'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/common/multiOrder/select"),id: 'recAcctNo',  postData:function () {
                        return {selectValue: JSON.stringify(orderAcctMap), type: 'rec'};
                    }
                }
            }
            , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable:false}
            , {field: 'purpose', width:130, title: 'BussTrdTransferOrder.purpose', sortable:false, edit:true}
            , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable:false}
            , {field: 'portfolioName', width:150, title: 'BussTrdTransferOrder.portfolioName', sortable:false}
            , {field: 'orderId', visible: true, edit: true,width:0,
                editOption: {
                    type: 'select', id:'orderId_select', childrenIds:'recAcctNo,payAcctNo'
                }}
            , {field: 'ps', visible: false}
            , {field: 'settleStatus', visible: false}
            , {field: 'isTotalTrade', visible: false}
        ],
        done:function(){
            $("[data-field='orderId']").hide();
        },
        page: false,
        data: [],
        editCell: function (value, row, field, cell, column) {
            if (field == 'payAcctNo') {
                if (typeof value == "undefined" || value == null || value == "") {
                    row.payAcctName = "";
                }
                // 选择付款账户带出贷款账户名称
                if (typeof value != "undefined" && value) {
                    Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                        ccy: row.ccy,
                        acctNo: value
                    }, function (result) {
                        //add by wangmin 2019-0-06 begin
                        if (!result) {
                            Joyin.error("该币种下，无此账户。")
                            return;
                        }
                        row.payAcctName = result.acctName;
                        //add by wangmin 2019-0-06 end
                        table.renderRow(row.LAY_TABLE_INDEX, row);
                    })
                } else {
                    table.renderRow(row.LAY_TABLE_INDEX, row);
                }

            } else {
                if (typeof value == "undefined" || value == null || value == "") {
                    row.recAcctName = "";
                }
                if (typeof value != "undefined" && value) {
                    // 选择收款账户带出行内/行外、网点
                    Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                        ccy: row.ccy,
                        acctNo: value
                    }, function (result) {
                        // 网点
                        row.recAcctPoint = result.largePayno;
                        //add by wangmin 2019-0-06 begin
                        row.recAcctName = result.acctName;
                        //add by wangmin 2019-0-06 end
                        table.renderRow(row.LAY_TABLE_INDEX, row);
                    })
                } else {
                    table.renderRow(row.LAY_TABLE_INDEX, row);
                }
            }
        }
    }

    var tableInitData = function () {
        var feeInfos = feeInfoTable.getAllRows();
        var dtos = [];
        for (var i = 0; i < feeInfos.length; i++) {
            var dealReal = {};
            //dealReal.finprodType2=$("#BondPtlFeePay002_hidden_feeType").val();
            dealReal.finprodType2="06";
            dealReal.ps=ps;
            dealReal.tradeDate=$('#BondPtlFeePay002_payDate').val();
            dealReal.tradeAmt=feeInfos[i].actPayAmt;
            dealReal.ccy='CNY';
            dtos.push({
                'isTotalTrade': 'Y'
                , 'portfolioId': feeInfos[i].ptlId
                , 'deal':dealReal
            })
        }
        return dtos;
    }
    
    if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
        option.data = option.data.concat(bussTrdTransferOrderDtos);
    }
    var orderAcctMap = [];
    var table = $("#bussTrdTransferOrder_table").joyintable(option);
    // 生成指令参数保存
    var stlCommandInput = tableInitData;

    // 检查生成指令参数是否发生变化
    var isCalcConditionChanged = function() {
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if (!$("#bussTrdTransferOrder_div").is(":visible")) {
            return false;
        }
        if (calcConditionObjBackUp == null) {
            return false;
        }

        if(stlCommandInput != null){
            var currentCalcConditionObj = stlCommandInput();
            // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
            for ( var key in currentCalcConditionObj) {
                for ( var key1 in currentCalcConditionObj[key]) {
                    if (typeof currentCalcConditionObj[key][key1] != "object"
                        && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
            for ( var key in currentCalcConditionObj) {
                for ( var key1 in currentCalcConditionObj[key].deal) {
                    var currentDealObj = currentCalcConditionObj[key].deal;
                    var backupDealObj = calcConditionObjBackUp[key].deal;
                    if (typeof currentDealObj[key1] != "object"
                        && currentDealObj[key1] != backupDealObj[key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
        }
    };

    $("#bussTrdTransferOrder_btn_generate").on("click", function () {
        if(stlCommandInput != null) {
            var index = layer.open({
                type: 1,
                icon : 16,
                area: ['200px', '100px'],
                content: '数据加载中',closeBtn: 0
            });
            var data = stlCommandInput();
            // 保存生成指令参数
            calcConditionObjBackUp = data;
            orderAcctMap = [];
            var result = [];
            Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
                Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/generateOrder'), data, function (ret) {
                    if (data.length == ret.data.length) {
                        for (var key in ret.data) {
                            orderAcctMap[ret.data[key].orderId] = {
                                payAcctNoList: ret.data[key].payAcctNoList,
                                recAcctNoList: ret.data[key].recAcctNoList
                            }
                        }
                        table.clear(function () {
                            table.addRow(ret.data)
                            layui.layer.close(index);
                        })
                    }
                });
            });
        }
    });
    
	function saveAction(){
		saveOrSubmitAction('save');
	};
	
	function submitAction(){
		saveOrSubmitAction('submit');
	};
	
	function saveOrSubmitAction(operateType){
		// 检查生成划款指令参数是否发生变化
        if (isCalcConditionChanged()) {
            return;
        }
        
		var object = $.serializeToObject('#BondPtlFeePay002_Form', false);
		object.operateType = operateType;
        object.pageModel = pageModel;
        object.isClean = 'Y';
        
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        
        var actPayAmtSum = '0';
        var actPayAmt = parseFloat($('#BondPtlFeePay002_actPayAmt').val());
        $.each(object.feeInfoList, function(id, value){
        	actPayAmtSum = JoyinMath.doAdd(actPayAmtSum,value.actPayAmt);
        	value.isClean = 'Y';
		});
        if (actPayAmt != actPayAmtSum) {
        	Joyin.error("各组合的实付金额之和不等于费用信息中填写的实付费用金额！");
            return false;
        }
        
        //费用提交时校验收款账户是否为空
        if (object.bussTrdTransferOrder[0]) {
        	// 是否发送指令为是收付款账户均为必填，为否则付款账户必填，收款账户非必填
        	for (var i = 0; i < object.bussTrdTransferOrder.length; i++) {
        		if(object.bussTrdTransferOrder[i].isNeedSend == 'Y'
            			|| object.bussTrdTransferOrder[i].isNeedSend == 'Y'){
            		if (!object.bussTrdTransferOrder[i].recAcctNo){
                        Joyin.error("划款指令请选择收款账户！");
                        return false;
                    }
            		if (!object.bussTrdTransferOrder[i].payAcctNo){
                        Joyin.error("划款指令请选择付款账户！");
                        return false;
                    }
            	}else{
            		if (!object.bussTrdTransferOrder[i].payAcctNo){
                        Joyin.error("划款指令请选择付款账户！");
                        return false;
                    }
            	}
            }
        }
        
        Joyin.ajaxJson(basePath + 'cebbank/bondptlfeepay/BondPtlFeePay002/check',object, function(result) {
            Joyin.confirm("是否确认？", function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'cebbank/bondptlfeepay/BondPtlFeePay002/save', object, "BondPtlFeePay002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'cebbank/bondptlfeepay/BondPtlFeePay002/save', object, "BondPtlFeePay002_btn_submit");
                    }
                })
            });
	}
	
	// 所有dom对象加载完的处理
	$(document).ready(function() {
		$('#fee_div').toggle(false);
		reloadTable();
		$('#BondPtlFeePay002_secAcctId').change();
		$('#BondPtlFeePay002_payDate').change(payDateChangeAction);
		$('#BondPtlFeePay002_actPayAmt').change(actPayAmtChangeAction);
		$('#BondPtlFeePay002_actPayAmtHk').change(calcActPayAmtAction);
		$('#BondPtlFeePay002_sellSettleRate').change(calcActPayAmtAction);
		$('#BondPtlFeePay002_btn_save').click(saveAction);
		$('#BondPtlFeePay002_btn_submit').click(submitAction);
	});
}());