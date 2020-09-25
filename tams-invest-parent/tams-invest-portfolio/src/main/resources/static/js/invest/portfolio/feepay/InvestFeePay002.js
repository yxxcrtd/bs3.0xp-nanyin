/**
 * 费用明细
 */
function showFeeDetail(secAcctId, ptlId, calcStartDate, calcEndDate, feeType){
	if(!ptlId || ptlId =='undefined'){
		ptlId = '';
	}
	Joyin.dialog(JoyinUtil.getLabel("InvestFeePay003"), JoyinUtil.getRealPath('invest/portfolio/feepay/InvestFeePay003/init'),
		"60%", "70%", {pageModel:PAGE_MODEL_REFERENCE, secAcctId : secAcctId, ptlId : ptlId, 
					   calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeType: feeType})
}
// 定义函数作用域(立即执行),防止全局变量污染
$(function() {
	
	var calcConditionObjBackUp;
	var cols1 = [{field : 'portFolioName',width:120,title : 'InvestFeePay002.feeTable.portFolioName'
		}, {field : 'payAmt',width:100,title : 'InvestFeePay002.feeTable.payAmt',type : 'number',textrange : '2'
		}, {field : 'actPayAmt',width:100,title : 'InvestFeePay002.feeTable.actPayAmt',type : 'number',textrange : '2',edit:true
		}, {field : 'adjustAmt',width:100,title : 'InvestFeePay002.feeTable.adjustAmt',type : 'number',textrange : '2'
		}, {field : 'status',width:100,title : 'InvestFeePay002.feeTable.showDetail',sort : false,
			render : function(data, type, row, meta) {
					var secAcctId = row.secAcctId;
					if (!secAcctId || secAcctId == 'undefined') {
						secAcctId = '';
					}
					return "<a onclick=\"showFeeDetail('" + secAcctId +"','"+ row.ptlId +"','" + $('#InvestFeePay002_positionStartDate').val() +"','" + $('#InvestFeePay002_positionEndDate').val() +"','" + $('#InvestFeePay002_feeType').val()+"')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("InvestFeePay002.feeTable.showDetail")+"</a>";
				}
		}, {field : 'secAcctId',visible : false
		}, {field : 'ptlId',visible : false}];
	
	/**
	 * 付费明细
	 */
	var feeInfoTable = $('#fee_dataTable').joyintable({
		ajax : {
			url : JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay002/queryPayFeeInfoList'),
			type : 'POST',
			data : function() {
				return {
						workflowId : workflowId,
						secAcctId : $('#InvestFeePay002_secAcctId').val(),
						tradeId : $('#InvestFeePay002_hidden_tradeId').val(),
						calcStartDate : $('#InvestFeePay002_positionStartDate').val(),
						calcEndDate : $('#InvestFeePay002_positionEndDate').val(),
						payDate : $("#InvestFeePay002_payDate").val(),
						feeType : $('#InvestFeePay002_feeType').val(),
						operateType : $('#InvestFeePay002_hidden_operateType').val(),
						cashId:$('#InvestFeePay002_hidden_cashId').val(),
						chargeType : $('#FinProductFeePay002_hidden_chargeType').val(),
						org : $('#FinProductFeePay002_regTrustOrg').val()
						};
			}
		},
		loadonce : true,
		searchOnInit: !($('#InvestFeePay002_hidden_feeType').val() === ''),
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
            	$('#InvestFeePay002_hidden_tradeAmt').val(totalAmt);
            	//为一次性费用实付金额赋值
            	$('#InvestFeePay002_actPayAmt').val(totalAmt).trigger('change');
        	}
        },
        done : function(allData, table){
        	var totalAmt = '0';
        	var actPayAmtCurSum = '0';
        	if (allData && allData.length > 0 && !isTodoList && $('#InvestFeePay002_hidden_operateType').val() === 'new'){
        		// 当前理论支付金额
        		var payAmtCur = $('#InvestFeePay002_payAmt').val();
        		// 当前理论实际支付金额
        		var actPayAmtCur = $('#InvestFeePay002_actPayAmt').val();
        		$.each(allData, function(id, value){
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
        		});
        	}
        	$('#InvestFeePay002_hidden_tradeAmt').val(totalAmt);
        }
	});
	
	function feeTypeChangeAction(){
		var feeType = $('#InvestFeePay002_feeType').val();
		var feeName = $('#InvestFeePay002_feeType option:selected').text();
		if (feeType){
			$('#InvestFeePay002_hidden_feeType').val(feeType);
			$('#InvestFeePay002_hidden_feeName').val(feeName);
			if (feeType == '06'){
				// 当费用类型选择交易手续费时，证券账户和登记托管机构置灰，不可选，直接带出每个组合的费用
				$('#InvestFeePay002_secAcctId').val('').trigger('change');
				$('#InvestFeePay002_regTrustOrg').val('').trigger('change');
				$("#InvestFeePay002_secAcctId").prop("disabled", true);
				$("#InvestFeePay002_regTrustOrg").prop("disabled", true);
//				reloadTable();
				// 查询上一次计提开始日
				if ($('#InvestFeePay002_hidden_operateType').val() === 'new'){
					secAcctIdFeeTypeChangeAction();
				}
				$("#InvestFeePay002_positionStartDate").prop("disabled", true);
			} else {
				// 当费用类型选择结算费或佣金时，证券账户必填，此时以证券账户为维度计算费用
				$('#InvestFeePay002_regTrustOrg').val('').trigger('change');
				$("#InvestFeePay002_secAcctId").prop("disabled", false);
				$("#InvestFeePay002_regTrustOrg").prop("disabled", false);
				if (feeType == '14') {
					// 费用类型选佣金时，默认为中国证券登记结算有限责任公司，不可修改
//					$("#InvestFeePay002_regTrustOrg").val('03').trigger('change');
//					$("#InvestFeePay002_regTrustOrg").prop("disabled", true);

                    //费用类型选佣金时 默认为空且置灰 TISHKBS-894
                     // 【晋商五期】【组合管理-投资费用支付】佣金的理论费用金额、实付费用金额未带出，无法提交
					$("#InvestFeePay002_regTrustOrg").val('').trigger('change');
                    $("#InvestFeePay002_regTrustOrg").prop("disabled", true);

				}
				
//				reloadTable();
				// 查询上一次计提开始日
				if ($('#InvestFeePay002_hidden_operateType').val() === 'new'){
					secAcctIdFeeTypeChangeAction();
				}
			}
			payTradeAmtChangeAction();
		}else{
			$('#InvestFeePay002_hidden_feeType').val('');
		}
		payTradeAmtChangeAction();
	};
	/**
	 * 结算服务费联动带出默认费用（add by wm 增加交易手续费的查询）
	 */
	function payTradeAmtChangeAction(){
		var org = $('#InvestFeePay002_regTrustOrg').val();
		var secAcctId = $('#InvestFeePay002_secAcctId').val();
		var feeType = $('#InvestFeePay002_hidden_feeType').val();
		var cdate = $('#InvestFeePay002_positionEndDate').val();
		if(feeType){
			Joyin.ajax(JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay002/queryPayTradeAmt'), {secAcctId : secAcctId, feeType : feeType, org : org, cdate : cdate}, function(result){
			   $('#InvestFeePay002_payAmt').val(result);
			   $('#InvestFeePay002_actPayAmt').val(result).trigger('change');
			   //add by wm 将值赋值给生成指令的值 start 2019-09-05
			   $('#InvestFeePay002_hidden_tradeAmt').val(result);
			   //add by wm 将值赋值给生成指令的值 end 2019-09-05
			})
		}
	}
	/**
	 * 证券账户和费用代码下拉框联动处理
	 */
	function secAcctIdFeeTypeChangeAction(){
		var feeType = $('#InvestFeePay002_feeType').val();
		var secAcctId = $('#InvestFeePay002_secAcctId').val();
		if (feeType){
			// 查询上一次计提开始日
			Joyin.ajax(JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay002/queryLastPayDate'), 
					{secAcctId : secAcctId, feeType : feeType, operateType:$('#InvestFeePay002_hidden_operateType').val()}, function(result){
					$('#InvestFeePay002_positionStartDate').val(result).trigger('change');
					if (!result || result == 'undefined') {
						$('#InvestFeePay002_positionStartDate').prop("disabled", false);
					}
					var endDate = $('#InvestFeePay002_positionEndDateHide').val();
                    $('#InvestFeePay002_positionEndDate').val(endDate).trigger('change');
			});
			// 当费用类型为佣金或结算服务费时，自动带出登记托管机构
			if (secAcctId) {
//				if(feeType == '07' || feeType == '14'){
//					Joyin.ajax(JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay002/querySecAcctOrg'), {secAcctId : secAcctId}, function(result){
//						$('#InvestFeePay002_regTrustOrg').val(result).trigger('change');
//					});
//				}
                // TISHKBS-894 【晋商五期】【组合管理-投资费用支付】佣金的理论费用金额、实付费用金额未带出，无法提交
                //  add by wj 20200810 start 费用类型为佣金时 登记托管机构为空
                if(feeType == '07'){
					Joyin.ajax(JoyinUtil.getRealPath('/invest/portfolio/feepay/InvestFeePay002/querySecAcctOrg'), {secAcctId : secAcctId}, function(result){
						$('#InvestFeePay002_regTrustOrg').val(result).trigger('change');
					});
				}
			}
		} else {
			$('#InvestFeePay002_positionStartDate').val('').trigger('change');
		}
	}
	
	/**
	 * 计提结束日联动处理
	 */
	function endDateChangeAction(){
		var endDate = $('#InvestFeePay002_positionEndDate').val();
		if (endDate){
			var startDate = $('#InvestFeePay002_positionStartDate').val();
			if (startDate && endDate <= startDate){
				// 计提结束日不能小于等于计提开始日
				Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0015',[JoyinUtil.getLabel("InvestFeePay002.positionEndDate"), JoyinUtil.getLabel("InvestFeePay002.positionStartDate")]));
				$('#InvestFeePay002_positionEndDate').val('').trigger('change');
			}else{
				var secAcctId = $('#InvestFeePay002_secAcctId').val();
				var feeType = $('#InvestFeePay002_hidden_feeType').val();
				if (feeType){
//					reloadTable();
					payTradeAmtChangeAction();
				}
			}
		}else{
			$('#InvestFeePay002_payAmt').val('').trigger('change');
			$('#InvestFeePay002_actPayAmt').val('').trigger('change');
		}
	}
	
	function reloadTable(){
		$('#fee_div').toggle(true);
		if(feeInfoTable.table){
			feeInfoTable.reload();
		}else{
			if ($('#InvestFeePay002_hidden_feeType').val()) {
				feeInfoTable.load();
			}
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
		var actPayAmt = $('#InvestFeePay002_actPayAmt').val();
		var payAmt = $('#InvestFeePay002_payAmt').val();
		if (payAmt && actPayAmt){
			$('#InvestFeePay002_adjustAmt').val(JoyinMath.doMinus(actPayAmt, payAmt, 2, JoyinMath.ROUND_HALF_UP)).trigger('change');
		}else{
			$('#InvestFeePay002_adjustAmt').val('').trigger('change');		
		}
		$('#InvestFeePay002_hidden_tradeAmt').val($('#InvestFeePay002_actPayAmt').val());
		
	};
	
    /**
     * 计算按钮点击事件
     */
    $('#InvestFeePay002_btn_calculate').click(function() {
    	var feeType = $('#InvestFeePay002_feeType').val();
		var secAcctId = $('#InvestFeePay002_secAcctId').val();
    	if (feeType == '07' || feeType == '14') {
    		if (!secAcctId) {
    			Joyin.error("当前费用类型下，证券账户必选！")
                return;
    		}
    	}
    	var startDate = $('#InvestFeePay002_positionStartDate').val();
    	var endDate = $('#InvestFeePay002_positionEndDate').val();
    	var payDate = $("#InvestFeePay002_payDate").val();
    	if (!startDate) {
    		Joyin.error("计费开始日不能为空！")
            return;
    	}
    	if (!endDate) {
    		Joyin.error("计费结束日不能为空！")
            return;
    	}
    	if (!payDate) {
    		Joyin.error("支付日不能为空！")
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
            dealReal.finprodType2=$("#InvestFeePay002_hidden_feeType").val();
            dealReal.ps=ps;
            dealReal.tradeDate=$('#InvestFeePay002_payDate').val();
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
        
		var object = $.serializeToObject('#InvestFeePay002_Form', false);
		object.operateType = operateType;
        object.pageModel = pageModel;
        object.isClean = 'Y';
        
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        
        var actPayAmtSum = '0';
        var actPayAmt = parseFloat($('#InvestFeePay002_actPayAmt').val());
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
        
        Joyin.ajaxJson(basePath + 'invest/portfolio/feepay/InvestFeePay002/check',object, function(result) {
            Joyin.confirm("是否确认？", function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/portfolio/feepay/InvestFeePay002/save', object, "InvestFeePay002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/portfolio/feepay/InvestFeePay002/save', object, "InvestFeePay002_btn_submit");
                    }
                })
            });
	}
	
	// 所有dom对象加载完的处理
	$(document).ready(function() {
		$('#fee_div').toggle(false);
		var feeType = $('#InvestFeePay002_hidden_feeType').val();
		var chargeType = $('#InvestFeePay002_hidden_chargeType').val();
		if(feeType != null && feeType != 'undefined'){
			reloadTable();
		}
		// 费用代码
		$('#InvestFeePay002_feeType').change(feeTypeChangeAction);
		// 组合代码
		$('#InvestFeePay002_secAcctId').change(secAcctIdFeeTypeChangeAction);
		//登记托管机构
		$('#InvestFeePay002_regTrustOrg').change(payTradeAmtChangeAction);
		// 计提结束日
		$('#InvestFeePay002_positionEndDate').change(endDateChangeAction);
		// 支付日
		$('#InvestFeePay002_payDate').change(payDateChangeAction);
		// 实付金额
		$('#InvestFeePay002_actPayAmt').change(actPayAmtChangeAction);
		// 保存按钮
		$('#InvestFeePay002_btn_save').click(saveAction);
		// 提交按钮
		$('#InvestFeePay002_btn_submit').click(submitAction);
	});
}());