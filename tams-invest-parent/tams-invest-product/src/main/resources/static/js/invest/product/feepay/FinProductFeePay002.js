	/**
	 * 计提明细
	 */
	function showFeePositionDetail(finprodId, branch, calcStartDate, calcEndDate, feeId, feeType){
//	function showFeePositionDetail(finprodId,sfinprodId, branch, calcStartDate, calcEndDate, feeId, feeType){
//		if(sfinprodId &&sfinprodId!='undefined' ){
//		finprodId=sfinprodId;
//	}
		var profitType = $('#FinProductFeePay002_hidden_profitType').val();
		var dealMode = $('#FinProductFeePay002_hidden_dealMode').val();
//		if(!sfinprodId || sfinprodId =='undefined'|| sfinprodId =='null'){
//			sfinprodId = '';
//		}
//		else if(!(profitType === profitType01 && dealMode === dealMode11)&&sfinprodId){
//			finprodId=sfinprodId;
//			sfinprodId = '';
//		}
	Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay003"), JoyinUtil.getRealPath('invest/product/feepay/FinProductFeePay003/init'),
			"60%", "70%", {pageModel:PAGE_MODEL_REFERENCE, finprodId : finprodId, //sFinprodId : sfinprodId,
						   branch : branch, calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeId : feeId, feeType: feeType})
	}
	 
	function showFeePositionDetail(finprodId, sfinprodId, branch, calcStartDate, calcEndDate, feeId, feeType){
		if(sfinprodId &&sfinprodId!='undefined' ){
			finprodId=sfinprodId;
		}
		var profitType = $('#FinProductFeePay002_hidden_profitType').val();
		var dealMode = $('#FinProductFeePay002_hidden_dealMode').val();
		if(!sfinprodId || sfinprodId =='undefined'|| sfinprodId =='null'){
			sfinprodId = '';
		}
		else if(!(profitType === profitType01 && dealMode === dealMode11)&&sfinprodId){
			finprodId=sfinprodId;
			sfinprodId = '';
		}
	Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay003"), JoyinUtil.getRealPath('invest/product/feepay/FinProductFeePay003/init'),
			"60%", "70%", {pageModel:PAGE_MODEL_REFERENCE, finprodId : finprodId, sFinprodId : sfinprodId,
						   branch : branch, calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeId : feeId, feeType: feeType})
	}
// 定义函数作用域(立即执行),防止全局变量污染
$(function() {
	
	// 业务组件加载 完成处理
	window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussFinProductFeePay']) {
                window.BussTrdTransferOrder['bussFinProductFeePay'].setStlCommandInput(function () {
                	if (!$("#FinProductFeePay002_hidden_tradeAmt").val() || typeof $("#FinProductFeePay002_hidden_tradeAmt").val() == 'undefined'){
                		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("FinProductFeePay002.actPayAmt")]));
                		return null;
                	}
                	if (!$("#FinProductFeePay002_payDate").val() || typeof $("#FinProductFeePay002_payDate").val() == 'undefined'){
                		Joyin.error(JoyinUtil.getMessage('FW_E_0006',[JoyinUtil.getLabel("FinProductFeePay002.payDate")]));
                		return null;
                	}
                	
            		return {
                    	'isTotalTrade':yesFlag
                        ,'portfolioId' : $('#FinProductFeePay002_ptlId').val()
                        ,'deal':{
                        	'finprodType2' : $('#FinProductFeePay002_hidden_feeType').val(),
                        	'ps' : ps,
                        	'tradeDate' : $('#FinProductFeePay002_payDate').val(),
                        	'tradeAmt' : $("#FinProductFeePay002_hidden_tradeAmt").val(),
                            'ccy' : $('#FinProductFeePay002_ccy').val()==''?'CNY':$('#FinProductFeePay002_ccy').val()
                        }
                    };
                });
                if (formTodoList) {
                    window.BussTrdTransferOrder['bussFinProductFeePay'].saveCalcCondition();
                }
            }
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showFeePay(feePayShow);
        }
    };
	
	

	
	var cols1 = [{field : 'finprodMarketId',width:120,title : 'FinProductFeePay002.feeTable.finprodMarketId'
	//},{field : 'branch',width:120,title : 'FinProductFeePay002.taxTable.sonFinprodId',visible : false
	//},{field : 'sFinprodId',width:120,title : 'FinProductFeePay002.taxTable.sonFinprodId'
	},{field : 'feeName',minWidth:120,title : 'FinProductFeePay002.feeTable.feeName'
	}, {field : 'isClean',title : 'FinProductFeePay002.feeTable.isClean',edit:true,width:100,
		editOption:{type:'select', multiple: false, searchable: false, result: YES_NO}, sortable:false
	},{field : 'calcStartDate',width:100,title : 'FinProductFeePay002.feeTable.calcStartDate',type:'date'
	},{field : 'calcEndDate',title : 'FinProductFeePay002.feeTable.calcEndDate',type:'date'
	}, {field : 'payAmt',width:100,title : 'FinProductFeePay002.feeTable.payAmt',type : 'number',textrange : '2',
	}, {field : 'actPayAmt',width:100,title : 'FinProductFeePay002.feeTable.actPayAmt',type : 'number',textrange : '2',edit:true
	}, {field : 'adjustAmt',width:100,title : 'FinProductFeePay002.feeTable.adjustAmt',render: function (data, type, row, meta) {
		if(row.isClean=="Y"){
			if(typeof data == 'undefined' || data == null || data == ''){
				return '0.00';
			}
			else if(data.indexOf(",") != -1){
				return data;
			}
			else{
				return JoyinUtil.formatNumber(data, 2, null, 2);
			}	
		}else if(row.isClean=="N"||typeof data == 'undefined' || data == null || data == ''){
			return '0.00';
		}
    }
	}, {field : 'remainAmt',width:100,title : 'FinProductFeePay002.feeTable.remainAmt',type : 'number',textrange : '2',visible : false
	}, {field : 'unclearAmt',width:100,title : 'FinProductFeePay002.feeTable.unclearAmt',type : 'number',textrange : '2',
	}, {field : 'status',width:200,title : 'FinProductFeePay002.feeTable.showDetail',sort : false,
		render : function(data, type, row, meta) {
			if(row.feeType == feeType_fdcezs){
				return "";
			}else{
 				return "<a onclick=\"showFeePositionDetail('" + row.finprodId +"','"+ row.sFinprodId +"','" + row.branch +"','" + row.calcStartDateStr +"','" + row.calcEndDateStr +"','" + row.feeId + "','"+row.feeType+"')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("FinProductFeePay002.feeTable.showDetail")+"</a>";
//				return "<a onclick=\"showFeePositionDetail('" + row.finprodId +"','" + row.branch +"','" + row.calcStartDateStr +"','" + row.calcEndDateStr +"','" + row.feeId + "','"+row.feeType+"')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("FinProductFeePay002.feeTable.showDetail")+"</a>";
			}}
	}, {field : 'finprodId',visible : false
	}, {field : 'feeId',visible : false
	} , {field : 'calcStartDateStr',visible : false
	}, {field : 'calcEndDateStr',visible : false}];
	
	
	var cols2 = [{field : 'finprodMarketId',width:120,title : 'FinProductFeePay002.feeTable.finprodMarketId',visible : false
	//},{field : 'branch',width:120,title : 'FinProductFeePay002.taxTable.sonFinprodId',visible : false
	//},{field : 'sFinprodId',width:150,title : 'FinProductFeePay002.taxTable.sonFinprodId'
	},{field : 'feeName',width:120,title : 'FinProductFeePay002.feeTable.feeName',visible : false
	}, {field : 'isClean',title : 'FinProductFeePay002.feeTable.isClean',edit:true,width:100,
		editOption:{type:'select', multiple: false, searchable: false, result: YES_NO}, sortable:false,visible : false
	},{field : 'calcStartDate',width:100,title : 'FinProductFeePay002.feeTable.calcStartDate',type:'date',visible : false
	},{field : 'calcEndDate',title : 'FinProductFeePay002.feeTable.calcEndDate',type:'date',visible : false
	}, {field : 'payAmt',width:100,title : 'FinProductFeePay002.feeTable.payAmt',type : 'number',textrange : '2',visible : false
	}, {field : 'actPayAmt',width:100,title : 'FinProductFeePay002.feeTable.actPayAmt',type : 'number',textrange : '2',edit:true
	}, {field : 'adjustAmt',width:100,title : 'FinProductFeePay002.feeTable.adjustAmt',type : 'number',textrange : '2',visible : false
	}, {field : 'remainAmt',width:100,title : 'FinProductFeePay002.feeTable.remainAmt',type : 'number',textrange : '2',visible : false
	}, {field : 'unclearAmt',width:100,title : 'FinProductFeePay002.feeTable.unclearAmt',type : 'number',textrange : '2',visible : false
	}, {field : 'status',width:100,title : 'FinProductFeePay002.feeTable.showDetail',sort : false,visible : false,
		render : function(data, type, row, meta) {
			if(row.feeType == feeType_fdcezs){
				return "";
			}else{
     			return "<a onclick=\"showFeePositionDetail('" + row.finprodId +"','"+ row.sFinprodId +"','" + row.branch +"','" + row.calcStartDateStr +"','" + row.calcEndDateStr +"','" + row.feeId + "','"+row.feeType+"')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("FinProductFeePay002.feeTable.showDetail")+"</a>";
//				return "<a onclick=\"showFeePositionDetail('" + row.finprodId +"','" + row.branch +"','" + row.calcStartDateStr +"','" + row.calcEndDateStr +"','" + row.feeId + "','"+row.feeType+"')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("FinProductFeePay002.feeTable.showDetail")+"</a>";
			}}
	}, {field : 'finprodId',visible : false
	}, {field : 'feeId',visible : false
	} , {field : 'calcStartDateStr',visible : false
	}, {field : 'calcEndDateStr',visible : false}];
	
	/**
	 * 付费明细
	 */
	var feeInfoTable = $('#fee_dataTable').joyintable({
		ajax : {
			url : JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryPayFeeInfoList'),
			type : 'POST',
			data : function() {
				return {
						workflowId : workflowId,
						tradeId : $('#FinProductFeePay002_hidden_tradeId').val(),
						calcStartDate : $('#FinProductFeePay002_positionStartDate').val(),
						calcEndDate : $('#FinProductFeePay002_positionEndDate').val(),
						finprodId : $('#FinProductFeePay002_hidden_finprodId').val(),
						feeId : $('#FinProductFeePay002_feeId').val(),
						payDate : $("#FinProductFeePay002_payDate").val(),
						feeType : $('#FinProductFeePay002_hidden_feeType').val(),
						operateType : $('#FinProductFeePay002_hidden_operateType').val(),
						ptlId : $('#FinProductFeePay002_ptlId').val(),
						cashId:$('#FinProductFeePay002_hidden_cashId').val()
						};
			}
		},
		loadonce : true,
		searchOnInit: !($('#FinProductFeePay002_hidden_chargeType').val() === '' || $('#FinProductFeePay002_hidden_chargeType').val() === feeChargetype_norule),
        height: 300,
        page: false,
        checkbox: false,
        rowno: true,
		cols : cols1,
        editCell : function(value, data, field, cell, column){
            //add by jyl start 解决选择是否结清后，数据未更新，该字段值仍为空值
		    if (field === "isClean"){
				data.isClean=value;
				feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
			}
            //add by jyl end
        	if (field === "actPayAmt"){
        		if(data.isClean=="Y"){
        			data.adjustAmt = JoyinMath.doMinus(value, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);

            		feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
            		
        		}else if(data.isClean=="N"){
        			data.adjustAmt = '0';
            		feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
        		}
        		var totalAmt = '0';
        		var allData = feeInfoTable.getAllRows();
            	if (allData && allData.length > 0){
            		$.each(allData, function(id, value){
    	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
    	        	});
            	}
            	$('#FinProductFeePay002_hidden_tradeAmt').val(totalAmt);
        	}
        	else if (field === "isClean"){
        		if(data.isClean=="Y"){
        			data.adjustAmt = JoyinMath.doMinus(data.actPayAmt, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        		}
        		else{
            		data.adjustAmt = '0';
        		}
        		feeInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
        	}

        },
        done : function(allData, table){
        	var totalAmt = '0';
        	if (allData && allData.length > 0){
        		$.each(allData, function(id, value){
	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
	        		if(value.isClean=="Y"){
        				var adjustAmt = JoyinMath.doMinus(value.actPayAmt, value.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        				value.adjustAmt = adjustAmt;
        				feeInfoTable.renderRow(value.LAY_TABLE_INDEX, value);
        			}
	        		
        		});
        	}
        	$('#FinProductFeePay002_hidden_tradeAmt').val(totalAmt);
        }
	});
	
	
	
	
	/**
	 * 不规则付费明细
	 */
	var feelayeredInfoTable = $('#feelayered_dataTable').joyintable({
		ajax : {
			url : JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryPayFeeInfoList'),
			type : 'POST',
			data : function() {
				return {
						workflowId : workflowId,
						tradeId : $('#FinProductFeePay002_hidden_tradeId').val(),
						calcStartDate : $('#FinProductFeePay002_positionStartDate').val(),
						calcEndDate : $('#FinProductFeePay002_positionEndDate').val(),
						finprodId : $('#FinProductFeePay002_hidden_finprodId').val(),
						feeId : $('#FinProductFeePay002_feeId').val(),
						payDate : $("#FinProductFeePay002_payDate").val(),
						feeType : $('#FinProductFeePay002_hidden_feeType').val(),
						operateType : $('#FinProductFeePay002_hidden_operateType').val(),
						ptlId : $('#FinProductFeePay002_ptlId').val(),
						chargeType : $('#FinProductFeePay002_hidden_chargeType').val()
						};
			}
		},
		loadonce : true,
		searchOnInit: ($('#FinProductFeePay002_hidden_chargeType').val() === feeChargetype_norule),
        height: 300,
        page: false,
        checkbox: false,
        rowno: true,
		cols : cols2,
        editCell : function(value, data, field, cell, column){
			//add by jyl start 解决选择是否结清后，数据未更新，该字段值仍为空值
		    if (field === "isClean"){
				data.isClean=value;
                feelayeredInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
			}
            //add by jyl end
			if (field === "actPayAmt"){
        		data.adjustAmt = JoyinMath.doMinus(data.payAmt, value, 2, JoyinMath.ROUND_HALF_UP);

        		feelayeredInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
        		
        		var totalAmt = '0';
        		var allData = feelayeredInfoTable.getAllRows();
            	if (allData && allData.length > 0){
            		$.each(allData, function(id, value){
    	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
    	        	});
            	}
            	$('#FinProductFeePay002_hidden_tradeAmt').val(totalAmt);
            	//为一次性费用实付金额赋值
            	$('#FinProductFeePay002_actPayAmt').val(totalAmt);
        	}
        },
        done : function(allData, table){
        	var totalAmt = '0';
        	if (allData && allData.length > 0){
        		$.each(allData, function(id, value){
	        		totalAmt = JoyinMath.doAdd(totalAmt, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
	        	});
                $('#FinProductFeePay002_hidden_tradeAmt').val(totalAmt);
        	}

        }
	});

	/**
	 * 增值附加税明细
	 */
	/*
	if ($('#FinProductFeePay002_hidden_operateType').val() != 'confirm'){
		var taxFeeTable = $('#taxFee_dataTable').joyintable({
			ajax : {
				url : JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryTaxUnpayPosition'),
				type : 'POST',
				data : function() {
					return {ptlId : $('#FinProductFeePay002_ptlId').val(), cdate : $('#FinProductFeePay002_positionEndDate').val(), workflowId : workflowId, tradeId : $('#FinProductFeePay002_hidden_tradeId').val()};
				}
			},
			loadonce : true,
			searchOnInit: $('#FinProductFeePay002_hidden_feeType').val() === feeType_tax,
	        height: 210,
	        page: false,
	        checkbox: false,
	        rowno: true,
			cols : [ {
				field : 'feeName',
				title : 'FinProductFeePay002.taxTable.feeName'
			}, {
				field : 'feeAmt',
				title : 'FinProductFeePay002.taxTable.feeAmt'
			}, {
				field : 'actFeeAmt',
				title : 'FinProductFeePay002.taxTable.actFeeAmt'
			}, {
				field : 'adjustFeeAmt',
				title : 'FinProductFeePay002.taxTable.adjustFeeAmt'
			} ],
	        done : function(allData, table){
	        	var totalAmt = '0';
	        	if (allData && allData.length > 0){
	        		$.each(allData, function(id, value){
		        		totalAmt = JoyinMath.doAdd(totalAmt, value.actFeeAmt, 2, JoyinMath.ROUND_HALF_UP)
		        	});
	        	}
	        	$('#FinProductFeePay002_hidden_tradeAmt').val(totalAmt);
	        }
		});
	}
	*/
	
	/**
	 * 查询组合剩余现金 add by yzg 20200107
	 */
	var proAvailableCashVal = function(){
		var portfolioId = $('#FinProductFeePay002_ptlId').val();
		var ccy = $('#FinProductFeePay002_ccy').val();
		var vdate = $('#FinProductFeePay002_payDate').val();
		if(!portfolioId || !ccy || !vdate){
			$('#FinProductFeePay002_proAvailableCash').val('').trigger('blur');//组合T日可用现金
			return;
		}
		var data = {
		    portfolioId: portfolioId,
		    ccy: ccy,
		    vdate: vdate,
		};
		Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay002/getProAvailableCash', data,function (result) {
            $('#FinProductFeePay002_proAvailableCash').val(result.cashT).trigger('blur');//组合T日可用现金
        });
	};
	
	function feeIdChangeAction(){
		var feeId = $('#FinProductFeePay002_feeId').val();
		if (feeId){
			Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryFeeInfoByFeeId'), {feeId : feeId}, function(result){
				if (result){
					// 隐藏费用类型赋值
					$('#FinProductFeePay002_hidden_feeType').val(result.feeType);
					$('#FinProductFeePay002_hidden_feeName').val(result.feeName);
					$('#FinProductFeePay002_hidden_chargeType').val(result.chargeType);
					// 结算服务费、账户维护费显示登记托管机构，其余隐藏
					if (result.feeType === feeType_settle || result.feeType === feeType_acctno){
						$('#FinProductFeePay002_regTrustOrg').prop('disabled', false);
						$('#FinProductFeePay002_DIV_regTrustOrg').toggle(true);
						
					}else{
						$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
						$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
					}
					// 不规则费用
					if(result.chargeType === feeChargetype_norule){
//						$('#FinProductFeePay002_div_positionStartDate').toggle(false);
//						$('#FinProductFeePay002_div_positionEndDate').toggle(false);
						//交易手续费，结算服务费展示计提开始日和结束日 yzg 20191218 start
						if(result.feeType != feeType_tradeFee && result.feeType != feeType_settle){
							$('#FinProductFeePay002_div_positionStartDate').toggle(false);
							$('#FinProductFeePay002_div_positionEndDate').toggle(false);
						}else{
							$('#FinProductFeePay002_div_positionStartDate').toggle(true);
							$('#FinProductFeePay002_div_positionEndDate').toggle(true);
						}
						//交易手续费，结算服务费展示计提开始日和结束日 yzg 20191218 end
						$('#unRegular_div').toggle(true);
						$('#unRegular_div2').toggle(true);
						$('#FinProductFeePay002_div_payAmt').toggle(false);
						$('#FinProductFeePay002_div_adjustAmt').toggle(false);
						reloadTable();
						// 查询上一次计提开始日
						if ($('#FinProductFeePay002_hidden_operateType').val() === 'new'){
							ptlIdFeeIdChangeAction();
						}
						if(result.feeType != feeType_acctno && result.feeType != feeType_settle
								&& result.feeType != feeType_tradeFee && result.feeType != '99'){ 
							$('#FinProductFeePay002_isPerpaid').prop('disabled', true);
						}else{
							$('#FinProductFeePay002_isPerpaid').prop('disabled', false);
						}
					// 增值税
						/*
					}else if(result.feeType === feeType_tax){
						$('#fee_div').toggle(false);
						$('#taxFee_div').toggle(true);
						$('#unRegular_div').toggle(false);
						$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
						$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
						*/
					// 计提式费用
					}else{
						$('#FinProductFeePay002_div_positionStartDate').toggle(true);
						$('#FinProductFeePay002_div_positionEndDate').toggle(true);
						$('#unRegular_div').toggle(false);
						$('#unRegular_div2').toggle(false);
						reloadTable();
						// 查询上一次计提开始日
						if ($('#FinProductFeePay002_hidden_operateType').val() === 'new'){
							ptlIdFeeIdChangeAction();
						}
					}
					if($('#FinProductFeePay002_regTrustOrg').val() != undefined && $('#FinProductFeePay002_regTrustOrg').val() != "" ){
						payTradeAmtChangeAction();
					}
				}else{
					$('#unRegular_div').toggle(false);
					$('#unRegular_div2').toggle(false);
					$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
					$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
					$('#FinProductFeePay002_hidden_feeType').val('');
				}
			})
		}else{
			$('#unRegular_div').toggle(false);
			$('#unRegular_div2').toggle(false);
			$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
			$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
			$('#FinProductFeePay002_hidden_feeType').val('');
		}

}
    /**
	 * 结算服务费、账户维护费联动带出默认费用（add by wm 增加交易手续费的查询）
	 */
	function payTradeAmtChangeAction(){
		
		var org = $('#FinProductFeePay002_regTrustOrg').val();
		var ptlId = $('#FinProductFeePay002_ptlId').val();
		var feeType = $('#FinProductFeePay002_hidden_feeType').val();
		var cdate = $('#FinProductFeePay002_payDate').val();
		if(feeType == '07' || feeType == '06'){
			cdate = $('#FinProductFeePay002_positionEndDate').val();
		}
		var chargeType = $('FinProductFeePay002_hidden_chargeType').val();
		//add by cxq 20200117  解决OPEX-822
		if(feeType == '08' || feeType == '07' ){
			if(org==""||org==null){
	            return false;
			}
		}
		if(feeType != ""){
			if(feeType == feeType_acctno || feeType == feeType_settle || feeType == feeType_tradeFee){
				Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryPayTradeAmt'), {ptlId : ptlId,feeType:feeType, org : org, cdate : cdate}, function(result){
					   $('#FinProductFeePay002_actPayAmt').val(result);
					   //add by wm 将值赋值给生成指令的值 start 2019-09-05
					   $('#FinProductFeePay002_hidden_tradeAmt').val(result);
					   //add by wm 将值赋值给生成指令的值 end 2019-09-05
					//$('#FinProductFeePay002_actPayAmt').val(result);
					//$('#FinProductFeePay002_adjustAmt').val('0');
				})
			}
		} 
		var feeId = $('#FinProductFeePay002_feeId').val();
		var ptlId = $('#FinProductFeePay002_ptlId').val();
		// 查询上一次计提开始日
		if(feeId&&ptlId) {
			Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryLastPayDate'),
				{
					ptlId: ptlId,
					feeId: feeId,
					operateType: $('#FinProductFeePay002_hidden_operateType').val(),
					org: org
				}, function (result) {
					$('#FinProductFeePay002_positionStartDate').val(result).trigger('change');
					var endDate = $('#FinProductFeePay002_positionEndDateHide').val();
					if ($('#FinProductFeePay002_positionEndDate').val()) {
						endDate = $('#FinProductFeePay002_positionEndDate').val();
					}
					var vDate = $('#FinProductFeePay002_hidden_vDate').val();
					//上一次计提日如果是组合起息日返回false add by cxq 20200120
					if (vDate == result && chargeType === feeChargetype_norule) {
						return false;
					}
					//增加计提日已支付校验 add by cxq 20200120
					if (result == endDate && chargeType === feeChargetype_norule) {
						Joyin.error("该计提日已支付费用");
					} else {
						if(endDate>result) {
							$('#FinProductFeePay002_positionEndDate').val(endDate);
						}
					}
				})
		}
	}
	/**
	 * 组合代码和费用代码下拉框联动处理
	 */
	function ptlIdFeeIdChangeAction(){
		var feeId = $('#FinProductFeePay002_feeId').val();
		var ptlId = $('#FinProductFeePay002_ptlId').val();
		var chargeType = $('#FinProductFeePay002_hidden_chargeType').val();
		var org = $('#FinProductFeePay002_regTrustOrg').val();
		if (ptlId){
			// 查询组合产品的产品代码以及募集币种
			Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryByPtlId'), {ptlId : ptlId}, function(result){
					$('#FinProductFeePay002_ccy').val(result.ccy).trigger('change');
					$('#FinProductFeePay002_hidden_finprodId').val(result.finprodId);
					//组合剩余现金查询 yzg
					proAvailableCashVal();
					// 如果mdate不为空才走这一步
					if(null != result.mdate 
							&& '' != result.mdate ){
					$('#FinProductFeePay002_hidden_mDate').val(new Date(result.mdate).Format('yyyy-MM-dd'));
			}

					$('#FinProductFeePay002_hidden_profitType').val(result.profitType);
					$('#FinProductFeePay002_hidden_dealMode').val(result.dealMode);
			})
		}
		if (feeId && ptlId){
//			// 不规则费用
//			if(chargeType === feeChargetype_norule){
//				$('#FinProductFeePay002_positionStartDate').val('').trigger('change');
//				$('#FinProductFeePay002_positionEndDate').val('').trigger('change');
//			}
//			// 增值税
//				/*
//			}else if(result.feeType === feeType_tax){
//				$('#fee_div').toggle(false);
//				$('#taxFee_div').toggle(true);
//				$('#unRegular_div').toggle(false);
//				$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
//				$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
//				*/
//			// 计提式费用
//			else{
//				// 查询上一次计提开始日
//				Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryLastPayDate'), 
//						{ptlId : ptlId, feeId : feeId,operateType:$('#FinProductFeePay002_hidden_operateType').val()}, function(result){
//						$('#FinProductFeePay002_positionStartDate').val(result).trigger('change');
//						var endDate = $('#FinProductFeePay002_positionEndDateHide').val();
//						$('#FinProductFeePay002_positionEndDate').val(endDate).trigger('change');
//				})
//			}
			// 查询组合起息日 add by cxq 20200120
			Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryVdate'), 
					{ptlId : ptlId}, function(result){
						if(null != result&& '' != result ){
						$('#FinProductFeePay002_hidden_vDate').val(new Date(result).Format('yyyy-MM-dd'));
				}
			});

            // 查询上一次计提开始日
            Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryLastPayDate'), 
                    {ptlId : ptlId, feeId : feeId,operateType:$('#FinProductFeePay002_hidden_operateType').val(),org:org}, function(result){
                    $('#FinProductFeePay002_positionStartDate').val(result).trigger('change');
            })
		}else{
			$('#FinProductFeePay002_positionStartDate').val('').trigger('change');
			$('#FinProductFeePay002_positionEndDate').val('').trigger('change');
		}
		
    	window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].updateCash(function(){
    		return {
    			'tradeDate': $("#FinProductFeePay002_payDate").val(),
                'calendarId': '01',
                'ccy': $('#FinProductFeePay002_ccy').val()==''?'CNY':$('#FinProductFeePay002_ccy').val(),
                'portfolioId': $("#FinProductFeePay002_ptlId").val()
    		}
    	});
	}
	
	/**
	 * 计提结束日联动处理
	 */
	function endDateChangeAction(){
		var endDate = $('#FinProductFeePay002_positionEndDate').val();
		var feeId = $('#FinProductFeePay002_feeId').val();
		var ptlId = $('#FinProductFeePay002_ptlId').val();
		var feeType = $('#FinProductFeePay002_hidden_feeType').val();
		var chargeType = $('#FinProductFeePay002_hidden_chargeType').val();
		if (endDate && feeId && ptlId){
			var startDate = $('#FinProductFeePay002_positionStartDate').val();
			if (startDate && endDate <= startDate){
				// 计提结束日不能小于等于计提开始日
				if(chargeType === feeChargetype_norule){
					//交易手续费，结算服务费展示计提开始日和结束日 yzg 20191218 start
					if(!(feeType != feeType_tradeFee && feeType != feeType_settle)){
						Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0015',[JoyinUtil.getLabel("FinProductFeePay002.positionEndDate"), JoyinUtil.getLabel("FinProductFeePay002.positionStartDate")]));
					}
				}else {
					Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0015',[JoyinUtil.getLabel("FinProductFeePay002.positionEndDate"), JoyinUtil.getLabel("FinProductFeePay002.positionStartDate")]));
				}
				$('#FinProductFeePay002_positionEndDate').val('');
			}else{
				var ptlId = $('#FinProductFeePay002_ptlId').val();
				if (ptlId && feeType){
					reloadTable();
					//交易手续费、结算服务费根据计提结束日带出金额 add by yzg 20191218 start
					if(feeType == feeType_tradeFee || feeType == feeType_settle){
						payTradeAmtChangeAction();
					}
					//交易手续费、结算服务费根据计提结束日带出金额 add by yzg 20191218 end
					/*
					// 账户维护费理论金额为空，实付可编辑，调整为空
					if(feeType === feeType_acctno){
						$('#FinProductFeePay002_payAmt').val('');
						$('#FinProductFeePay002_adjustAmt').val('');
					}else{
						Joyin.ajax(JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay002/queryUnpayPosition'), {ptlId : ptlId, feeType : feeType, cdate : endDate}, function(result){
							$('#FinProductFeePay002_payAmt').val(result);
							$('#FinProductFeePay002_actPayAmt').val(result);
							$('#FinProductFeePay002_adjustAmt').val('0');
						})
					}
					*/
				}
			}
		}else{
			$('#FinProductFeePay002_payAmt').val('');
			$('#FinProductFeePay002_actPayAmt').val('').trigger('change');
		}
	}
	
	
	
	function reloadTable(){
		var profitType = $('#FinProductFeePay002_hidden_profitType').val();
		var dealMode = $('#FinProductFeePay002_hidden_dealMode').val();
		$('#fee_div').toggle(false);
		$('#taxFee_div').toggle(false);
		$('#feelayered_div').toggle(false);
		if(!($('#FinProductFeePay002_hidden_chargeType').val() === feeChargetype_norule)){
			$('#fee_div').toggle(true);
			$('#taxFee_div').toggle(false);
			$('#feelayered_div').toggle(false);
			
			if(feeInfoTable.table){
				feeInfoTable.reload();
			}else{
				feeInfoTable.load();
			}
//			if(profitType === profitType01 && dealMode === dealMode11){
//				feeInfoTable.showColumns([{field:'sFinprodId',visible: true}], true);
//			}
//			else{
//				feeInfoTable.showColumns([{field:'sFinprodId',visible: false}], true);
//			}
		}
		else{
			$('#fee_div').toggle(false);
			$('#taxFee_div').toggle(false);
			if(profitType === profitType01 && dealMode === dealMode11){
				$('#feelayered_div').toggle(true);
				$('#FinProductFeePay002_actPayAmt').prop('disabled', true);
//				if(feelayeredInfoTable.table){
//					feelayeredInfoTable.reload();
//				}else{
//					feelayeredInfoTable.load();
//				}
			}
			else{
				$('#feelayered_div').toggle(false);
				$('#FinProductFeePay002_actPayAmt').prop('disabled', false);
			}
			
		}
	}
	/**
	 * 支付日联动处理
	 */
	function payDateChangeAction(){
		//结算服务费交易手续费不根据支付日联动  yzg 20191218 start
		var endDate = $('#FinProductFeePay002_positionEndDate').val();
		if (endDate){
			var startDate = $('#FinProductFeePay002_positionStartDate').val();
			if (startDate && endDate <= startDate){
				// 计提结束日不能小于等于计提开始日
				Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0015',[JoyinUtil.getLabel("FinProductFeePay002.positionEndDate"), JoyinUtil.getLabel("FinProductFeePay002.positionStartDate")]));
				$('#FinProductFeePay002_positionEndDate').val('');
			}else{
				var ptlId = $('#FinProductFeePay002_ptlId').val();
				var feeType = $('#FinProductFeePay002_hidden_feeType').val();
				//账户维护费根据支付日带出费用
				if(feeType == '08'){
					payTradeAmtChangeAction();
				}
				if (ptlId && feeType){
					reloadTable();
				}
			}
		}else{
			$('#FinProductFeePay002_payAmt').val('');
			$('#FinProductFeePay002_actPayAmt').val('').trigger('change');
		}
		//结算服务费交易手续费不根据支付日联动  yzg 20191218 end
		//add 组合剩余现金查询 yzg 20200107
		proAvailableCashVal();
		//endDateChangeAction();
		//payTradeAmtChangeAction();
	}
	/**
	
	/**
	 * 实付金额联动处理
	 */
	function actPayAmtChangeAction(){
		var feeType = $('#FinProductFeePay002_hidden_feeType').val();
		if (feeType != feeType_acctno){
			var actPayAmt = $('#FinProductFeePay002_actPayAmt').val();
			var payAmt = $('#FinProductFeePay002_payAmt').val();
			if (payAmt && actPayAmt){
				$('#FinProductFeePay002_adjustAmt').val(JoyinMath.doMinus(actPayAmt, payAmt, 2, JoyinMath.ROUND_HALF_UP));
			}else{
				$('#FinProductFeePay002_adjustAmt').val('');		
			}
		}
		$('#FinProductFeePay002_hidden_tradeAmt').val($('#FinProductFeePay002_actPayAmt').val());

}
    function saveAction(){
		saveOrSubmitAction('save');
    }
    function submitAction(){
		saveOrSubmitAction('submit');
    }
    function saveOrSubmitAction(operateType){
		// 检查生成划款指令参数是否发生变化
        if (window.BussTrdTransferOrder['bussFinProductFeePay'].isCalcConditionChanged()) {
            return;
        }
        
		var object = $.serializeToObject('#FinProductFeePay002_Form', false);
		object.operateType = operateType;
        object.pageModel = pageModel;
        
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        //费用提交时校验收款账户是否为空
        if (object.bussTrdTransferOrder[0]) {
        	// 是否发送指令为是收付款账户均为必填，为否则付款账户必填，收款账户非必填
        	if(object.bussTrdTransferOrder[0].isNeedSend == IS_NEEDSEND_YES
        			|| object.bussTrdTransferOrder[0].isNeedSend == IS_NEEDSEND_YES_NAME){
        		if (!object.bussTrdTransferOrder[0].recAcctNo){
                    Joyin.error("划款指令请选择收款账户！");
                    return false;
                }
        		if (!object.bussTrdTransferOrder[0].payAcctNo){
                    Joyin.error("划款指令请选择付款账户！");
                    return false;
                }
        	}else{
        		if (!object.bussTrdTransferOrder[0].payAcctNo){
                    Joyin.error("划款指令请选择付款账户！");
                    return false;
                }
        	}
        }
        Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay002/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/feepay/FinProductFeePay002/save', object, "FinProductFeePay002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/feepay/FinProductFeePay002/save', object, "FinProductFeePay002_btn_submit");
                    }
                })
            });
	}
	
	// 所有dom对象加载完的处理
	$(document).ready(function() {
		$('#fee_div').toggle(false);
		$('#taxFee_div').toggle(false);
		$('#feelayered_div').toggle(false);
		var feeType = $('#FinProductFeePay002_hidden_feeType').val();
		var chargeType = $('#FinProductFeePay002_hidden_chargeType').val();
		if(feeType){
			// 结算服务费、账户维护费显示登记托管机构，其余隐藏
			if (feeType === feeType_settle || feeType === feeType_acctno){
				$('#FinProductFeePay002_regTrustOrg').prop('disabled', false);
				$('#FinProductFeePay002_DIV_regTrustOrg').toggle(true);
			}else{
				$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
				$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
			}		
		}else{
			
			$('#unRegular_div').toggle(false);
			$('#unRegular_div2').toggle(false);
			$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
			$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
			$('#FinProductFeePay002_hidden_feeType').val('');
		}
		if(chargeType){
			// 不规则费用
			if(chargeType === feeChargetype_norule){
//				$('#FinProductFeePay002_div_positionStartDate').toggle(false);
//				$('#FinProductFeePay002_div_positionEndDate').toggle(false);
				//交易手续费，结算服务费展示计提开始日和结束日 yzg 20191218 start
				if(feeType != feeType_tradeFee && feeType != feeType_settle){
					$('#FinProductFeePay002_div_positionStartDate').toggle(false);
					$('#FinProductFeePay002_div_positionEndDate').toggle(false);
				}else{
					$('#FinProductFeePay002_div_positionStartDate').toggle(true);
					$('#FinProductFeePay002_div_positionEndDate').toggle(true);
				}
				//交易手续费，结算服务费展示计提开始日和结束日 yzg 20191218 end
				$('#unRegular_div').toggle(true);
				$('#unRegular_div2').toggle(true);
				$('#FinProductFeePay002_div_payAmt').toggle(false);
				$('#FinProductFeePay002_div_adjustAmt').toggle(false);
			// 增值税
				/*
			}else if(result.feeType === feeType_tax){
				$('#fee_div').toggle(false);
				$('#taxFee_div').toggle(true);
				$('#unRegular_div').toggle(false);
				$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
				$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
				*/
			// 计提式费用
			}else{
				$('#FinProductFeePay002_div_positionStartDate').toggle(true);
				$('#FinProductFeePay002_div_positionEndDate').toggle(true);
				$('#unRegular_div').toggle(false);
				$('#unRegular_div2').toggle(false);
			}
			reloadTable();
		}else{
			$('#unRegular_div').toggle(false);
			$('#unRegular_div2').toggle(false);
			$('#FinProductFeePay002_regTrustOrg').val('').trigger('change');
			$('#FinProductFeePay002_DIV_regTrustOrg').toggle(false);
			$('#FinProductFeePay002_hidden_feeType').val('');
		}
		//不在流程中的数据查询组合剩余现金
		if(pageModel == PAGE_MODEL_REFERENCE && !workflowId){
			$('#proAvailableCash_div').toggle(false);
		}
		// 费用代码
		$('#FinProductFeePay002_feeId').change(feeIdChangeAction);
		// 组合代码
		$('#FinProductFeePay002_ptlId').change(ptlIdFeeIdChangeAction);
		//登记托管机构
		$('#FinProductFeePay002_regTrustOrg').change(payTradeAmtChangeAction);
		// 计提结束日
		$('#FinProductFeePay002_positionEndDate').change(endDateChangeAction);
		// 支付日
		$('#FinProductFeePay002_payDate').change(payDateChangeAction);
		// 实付金额
		$('#FinProductFeePay002_actPayAmt').change(actPayAmtChangeAction);
		// 保存按钮
		$('#FinProductFeePay002_btn_save').click(saveAction);
		// 提交按钮
		$('#FinProductFeePay002_btn_submit').click(submitAction);
	})
}());