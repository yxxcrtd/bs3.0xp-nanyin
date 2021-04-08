	/**
	 * 计提明细
	 */
	function showFeePositionDetail(finprodId,sfinprodId, branch, calcStartDate, calcEndDate, feeId){
//		if(sfinprodId&&sfinprodId!='undefined'){
//		finprodId=sfinprodId;
//	}
	if(!sfinprodId || sfinprodId =='undefined'){
		sfinprodId = '';
	}
	Joyin.dialog(JoyinUtil.getLabel("FinProductFeePay003"), JoyinUtil.getRealPath('invest/product/feepay/FinProductFeePay003/init'),
			"60%", "70%", 
			{pageModel:PAGE_MODEL_REFERENCE, finprodId : finprodId, sFinprodId : sfinprodId, 
		branch : branch, calcStartDate : calcStartDate, calcEndDate : calcEndDate, feeId : feeId})
	}
// 定义函数作用域(立即执行),防止全局变量污染
$(function() {
	
	
	// 业务组件加载 完成处理
	window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussFinProductFeePay']) {
                window.BussTrdTransferOrder['bussFinProductFeePay'].setStlCommandInput(function () {
					if (!$("#FinProductFeePay004_hidden_tradeAmtSum").val() || typeof $("#FinProductFeePay004_hidden_tradeAmtSum").val() == 'undefined') {
						Joyin.error(JoyinUtil.getMessage('FW_E_0006', [JoyinUtil.getLabel("FinProductFeePay004.actPayAmtSum")]));
						return null;
					}
					if (!$("#FinProductFeePay004_payDate").val() || typeof $("#FinProductFeePay004_payDate").val() == 'undefined') {
						Joyin.error(JoyinUtil.getMessage('FW_E_0006', [JoyinUtil.getLabel("FinProductFeePay004.payDate")]));
						return null;
					}
					var allData = prodInfoTable.getAllRows();
					if (!allData) {
						Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0096', [JoyinUtil.getLabel("FinProductFeePay004.field.prodTable")]));
						return null;
					}
					var list = new Array();
					for (var dealObj in allData){
						if(allData[dealObj].actPayAmt!=0&&allData[dealObj].actPayAmt!=null&&allData[dealObj].actPayAmt!=" ") {
							var dealinfo = {};
							dealinfo.tradeDate = $('#FinProductFeePay004_payDate').val();
							dealinfo.portfolioId =  allData[dealObj].ptlId;
							dealinfo.ccy= $('#FinProductFeePay004_ccy').val();
							dealinfo.finprodType2 = $('#FinProductFeePay004_hidden_feeType').val();
							dealinfo.tradeAmt=allData[dealObj].actPayAmt;
							dealinfo.ps = ps;
							list.push(dealinfo);
						}
					}
					// 划款指令所需参数
					return {
						'isTotalTrade': yesFlag
						, 'portfolioId': 'fee'
						, 'deal': list
					};
                }, true);
            }
        }
    };
	
	/**
	 * 产品明细表格
	 */
	var prodInfoTable = $('#prod_dataTable').joyintable({
		ajax : {
			url : JoyinUtil.getRealPath('/invest/product/feepay/FinProductFeePay004/queryprodInfoList'),
			type : 'POST',
			data : function() {
				return {
					workflowId : workflowId,
					// 父表格带出的参数
					finProductFee : JSON.stringify(window.parent.listdto)
				};
			}
		},

		// 是否前台分页
		loadonce : true,
		// 是否初始化时加载数据
		searchOnInit: true,
        height: 300,
        page: false,
        // 是否显示复选框
        checkbox: false,
        rowno: true,
		cols : [ {
			field : 'ptlId',
			width:100,
			title : 'FinProductFeePay004.prodTable.ptlId'
		}, {
			field : 'finprodId',
			width:130,
			title : 'FinProductFeePay004.prodTable.finprodId'
		}, {
			field : 'sFinprodId',
			width:110,
			title : 'FinProductFeePay004.prodTable.sonFinprodId',
			visible : $('#FinProductFeePay004_hidden_profitType').val()== profitType01 && $('#FinProductFeePay004_hidden_dealMode').val()==dealMode11
		}, {
			field : 'finprodAbbr',
			width:150,
			title : 'FinProductFeePay004.prodTable.finprodAbbr'
		}, {
			field : 'basicBill',
			width:100,
			title : 'FinProductFeePay004.prodTable.basicBill'
		}, {
			field : 'startDate',
			width:120,
			title : 'FinProductFeePay001.table.feePayStartDate',
			type:'date'
		}, {
			field : 'endDate',
			width:120,
			title : 'FinProductFeePay001.table.feePayEndDate',
			type:'date'
		}, {
			field : 'isClean',
			title : 'FinProductFeePay004.prodTable.isClean',
			edit:true,
			width:90,
			editOption:{type:'select', multiple: false, searchable: false, result: YES_NO}, 
			sortable:false
		}, {
			field : 'payAmt',
			width:120,
			title : 'FinProductFeePay004.prodTable.payAmt',
			type : 'number',
			textrange : '2',
		}, {
			field : 'actPayAmt',
			width:120,
			title : 'FinProductFeePay004.prodTable.actPayAmt',
			type : 'number',
			textrange : '2',
			edit:true
		}, {
			field : 'adjustAmt',
			width:100,
			title : 'FinProductFeePay004.prodTable.adjustAmt',
			render: function (data, type, row, meta) {
				if(typeof data == 'undefined' || data == null || data == '') {
		    		return '0.00';
		    	}
				else{
					return JoyinUtil.formatNumber(data, 2, null, 2);
				}
		    }
		}, {
			field : 'unclearAmt',
			width:160,
			title : 'FinProductFeePay004.prodTable.unclearAmt',
			type : 'number',
			textrange : '2',
		}, {
			field : 'status',
			width:100,
			title : 'FinProductFeePay004.prodTable.detail',
			sort : false,
			render : function(data, type, row, meta) {
				return "<a onclick=\"showFeePositionDetail('" + row.finprodId +"','" + row.sFinprodId +"','" + row.branch +"','" + row.startDateStr +"','" + row.endDateStr +"','" + row.feeId + "')\" + style='cursor: pointer;'>"+JoyinUtil.getLabel("FinProductFeePay004.prodTable.detail")+"</a>";
			}
		}, {
			field : 'feeId',
			visible : false
		} , {
			field : 'startDateStr',
			visible : false
		}, {
			field : 'endDateStr',
			visible : false
		}, {
			field : 'feeName',
			visible : false
		}, {
			field : 'feeType',
			visible : false
		}, {
			field : 'cashId',
			visible : false
		}, {
			field : 'tradeId',
			visible : false
		}],
		// 复制应付总额，实付总额，调整总额
        editCell : function(value, data, field, cell, column){
			//add by jyl start 解决选择是否结清后，数据未更新，该字段值仍为空值
			if (field === "isClean"){
				data.isClean=value;
				prodInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
			}
			//add by jyl end
        	if (field === "actPayAmt"){
        		if(data.isClean=="Y"){
        			data.adjustAmt = JoyinMath.doMinus(value, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        		}
        		else{
        			data.adjustAmt = '0';
        		}
        		prodInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
        		var payAmtSum = '0';
        		var actPayAmtSum = '0';
        		var adjustAmtSum = '0';
        		var allData = prodInfoTable.getAllRows();
            	if (allData && allData.length > 0){
            		$.each(allData, function(id, value){
            			//BSTOT-809 产品费用支付确认，修改实付金额后，点击生成指令，报系统异常 guyunfei start
                        var str = value.actPayAmt;
                        if(str){
                        	//去除字符串中的逗号
                            str = str.replace(/,/g, "");
						}
            			actPayAmtSum = JoyinMath.doAdd(actPayAmtSum, str, 2, JoyinMath.ROUND_HALF_UP);
                        //BSTOT-809 产品费用支付确认，修改实付金额后，点击生成指令，报系统异常 guyunfei end
            			payAmtSum = JoyinMath.doAdd(payAmtSum, value.payAmt, 2, JoyinMath.ROUND_HALF_UP);
            			adjustAmtSum = JoyinMath.doAdd(adjustAmtSum, value.adjustAmt, 2, JoyinMath.ROUND_HALF_UP);
    	        	});
            	}
            	$('#FinProductFeePay004_hidden_tradeAmtSum').val(actPayAmtSum);
             	$('#FinProductFeePay004_actPayAmtSum').val(actPayAmtSum);
            	$('#FinProductFeePay004_payAmtSum').val(payAmtSum);
            	if(data.isClean=="Y"){
                	$('#FinProductFeePay004_adjustAmtSum').val(adjustAmtSum);
            	}
        	}
        	else if (field === "isClean"){
        		if(data.isClean=="Y"){
        			data.adjustAmt = JoyinMath.doMinus(data.actPayAmt, data.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        		}
        		else{
            		data.adjustAmt = '0.00';
        		}
        		prodInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
        		var actPayAmtSum = '0';
                var adjustAmtSum = '0';
                var allData = prodInfoTable.getAllRows();
        		if (allData && allData.length > 0){
                    $.each(allData, function(id, value){
                        actPayAmtSum = JoyinMath.doAdd(actPayAmtSum, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
                        adjustAmtSum = JoyinMath.doAdd(adjustAmtSum, value.adjustAmt, 2, JoyinMath.ROUND_HALF_UP);
                    });
                }
            	$('#FinProductFeePay004_adjustAmtSum').val(adjustAmtSum);
            	$('#FinProductFeePay004_hidden_tradeAmtSum').val(actPayAmtSum);
        	}
        },
        done : function(allData, table){
    		var payAmtSum = '0';
    		var actPayAmtSum = '0';
    		var adjustAmtSum = '0';
    		var allData = prodInfoTable.getAllRows();
        	if (allData && allData.length > 0){
        		$.each(allData, function(id, value){
        			actPayAmtSum = JoyinMath.doAdd(actPayAmtSum, value.actPayAmt, 2, JoyinMath.ROUND_HALF_UP);
        			payAmtSum = JoyinMath.doAdd(payAmtSum, value.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        			if(value.isClean=="Y"){
        				var adjustAmt = JoyinMath.doMinus(value.actPayAmt, value.payAmt, 2, JoyinMath.ROUND_HALF_UP);
        				value.adjustAmt = adjustAmt;
        				adjustAmtSum = JoyinMath.doAdd(adjustAmtSum, adjustAmt, 2, JoyinMath.ROUND_HALF_UP);
        				prodInfoTable.renderRow(value.LAY_TABLE_INDEX, value);
        			}
        		});
        	}
        	//adjustAmtSum = JoyinMath.doMinus(actPayAmtSum, payAmtSum, 2, JoyinMath.ROUND_HALF_UP);
        	$('#FinProductFeePay004_hidden_tradeAmtSum').val(actPayAmtSum);
         	$('#FinProductFeePay004_actPayAmtSum').val(actPayAmtSum);
        	$('#FinProductFeePay004_payAmtSum').val(payAmtSum);
        	$('#FinProductFeePay004_adjustAmtSum').val(adjustAmtSum);
        	if (formTodoList) {
                window.BussTrdTransferOrder['bussFinProductFeePay'].saveCalcCondition();
            }
        }
	});
	
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
        // 表单参数
		var object = $.serializeToObject('#FinProductFeePay004_Form', false);
		object.operateType = operateType;
        object.pageModel = pageModel;
        
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        //费用提交时校验收款账户是否为空  OPEX-545  guyunfei start
        if (object.bussTrdTransferOrder[0]) {
        	// modify by yujiaping 20210331 ZGBSS-1327 是否发送指令为是收付款账户均为必填，为否则付款账户必填，收款账户非必填start
        	if(object.bussTrdTransferOrder[0].isNeedSend == 'Y'
        			|| object.bussTrdTransferOrder[0].isNeedSend == '是'){
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
        	//modify by yujiaping 20210331 ZGBSS-1327 是否发送指令为是收付款账户均为必填，为否则付款账户必填，收款账户非必填end
        }
        //费用提交时校验收款账户是否为空  OPEX-545  guyunfei end

        // 保存check
        Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay004/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/feepay/FinProductFeePay004/save', object, "FinProductFeePay004_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/feepay/FinProductFeePay004/save', object, "FinProductFeePay004_btn_submit");
                    }
                })
            });
	}
	
	/**
	 * 查询组合剩余现金 add by yzg 20200107
	 */
	var proAvailableCashVal = function(){
		var portfolioId = ptlId;
		var ccy = $('#FinProductFeePay004_ccy').val();
		var vdate = $('#FinProductFeePay004_payDate').val();
		if(!portfolioId || !ccy || !vdate){
			$('#FinProductFeePay004_proAvailableCash').val('').trigger('blur');//组合T日可用现金
			return;
		}
		var data = {
		    portfolioId: portfolioId,
		    ccy: ccy,
		    vdate: vdate,
		};
		Joyin.ajaxJson(basePath + 'invest/product/feepay/FinProductFeePay002/getProAvailableCash', data,function (result) {
            $('#FinProductFeePay004_proAvailableCash').val(result.cashT).trigger('blur');//组合T日可用现金
        });
	};
	
	// 所有dom对象加载完的处理
	$(document).ready(function() {
		if(!workflowId){
			proAvailableCashVal();
		}
			$('#FinProductFeePay004_feeId').prop('disabled', true);
			$('#FinProductFeePay004_ccy').prop('disabled', true);
			$('#FinProductFeePay004_payAmtSum').prop('disabled', true);
			$('#FinProductFeePay004_actPayAmtSum').prop('disabled', true);
			$('#FinProductFeePay004_adjustAmtSum').prop('disabled', true);	
		// 保存按钮
		$('#FinProductFeePay004_btn_save').click(saveAction);
		// 提交按钮
		$('#FinProductFeePay004_btn_submit').click(submitAction);
	})
}());