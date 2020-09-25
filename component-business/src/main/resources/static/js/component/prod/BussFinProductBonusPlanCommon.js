/**
 * 分红计划共通JS
 * @auth zhengyan
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + bussFinProductBonusPlanEntityName + '_' + id);
    };
    
    function init() {
    	if (pageId === 'FinProductModule002'||pageId === 'FinNetValueProductModule002') {
            getElementById('firstPayDate').prop('disabled', true);
        } else {
            getElementById('firstPayDate').prop('disabled', false);
        }
        profitTypeChange();
    }
    
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + getElementById('profitType').val()).val();
        if (selectedValueProfitType) {
            // 非预期收益型
            if (selectedValueProfitType === PROFIT_TYPE_03) {
                getElementById('div').show();
                getElementById('payType').val(BONUS_MODE_01).trigger('change');
            }
            else{
                getElementById('div').hide();
            }
        } else {
            getElementById('div').hide();
        }
    }
 // 快速录入标志
	var initFlag = false;
	// 付息规则Flag 
	var payCycleFlag = false;
	// 推算方法Flag 
	var calcRuleFlag = false;
	var quickFlag = false;
	
    var bonusPlanInitAction = function(input_finprodId){
    	Joyin.ajaxSync(JoyinUtil.getRealPath('component/prod/BussFinProductBonusPlanCommon/queryBonusPlanInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(bussFinProductBonusPlanEntityName, data);
    	})
    	payPlanFinprodId = input_finprodId;
		quickFlag = true;
		//loadTable();
		if ($('#' + getElementById('profitType').val()).val() === PROFIT_TYPE_03) {
	        Joyin.ajaxSync(JoyinUtil.getRealPath('/component/prod/BussFinProductBonusPlanCommon/calcProductInterestPlan'),
	        		getPreviewParams(), function(rows) {
	        		// add by yanya 20190830 分红计划生成后， 表示初始化init
	        		initFlag = true;
	        		//add by chenxiaoqiu 是否显示日期值 20191126
	        		if(isDisplayDate == YES){
		        	table.table ? table.reload() : table.load();
		        	table.clear(function() {
		        		table.addRow(rows)
	                     })
	        		}
	                 })
			//add by liupengfei for 快录时若分红规则不为到期支付01，周期支付01 规则预览表清空
			if(getElementById('payCycle').val() && getElementById('payCycle').val()!=BONUS_RULE_02&& getElementById('payCycle').val()!=BONUS_RULE_01){
				table.reload({ajax:null, url: null, data:[]});
			}
			//add by liupengfei for 快录时若分红规则不为到期支付01，周期支付01 规则预览表清空
    	}
    }
    /*
    var payPlanInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductBonusPlanCommon/queryPayPlanInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(payPlanEntityName, data);
    	})
    	    payPlanFinprodId = input_finprodId;
    		quickFlag = true;
        	table.table ? table.reload() : table.load();
    	
    }
    // 公开接口
	window.ProductBonusPlan = {
		check: check,
		payPlanInitAction : payPlanInitAction
	};
    */
    window.BussFinProductBonusPlan = {
    		check: check,
		bonusPlanInitAction : bonusPlanInitAction,
		checkIsChange: checkIsChange
    }
    
    var loadTable = function (){
    	if (pageModel != PAGE_MODEL_NEW && $('#' + getElementById('profitType').val()).val() === PROFIT_TYPE_03) {
	        Joyin.ajax(JoyinUtil.getRealPath('/component/prod/BussFinProductBonusPlanCommon/calcProductInterestPlan'),
	        		getPreviewParams(), function(rows) {
			        	// add by yanya 20190830 分红计划生成后， 表示初始化init
		        		initFlag = true;
        	            table.table ? table.clear() : table.load();
                        table.addRow(rows);
	                 })
    	}
     }      
    
    
    /*
	 * 获取预览时所需参数
	 */
	function getPreviewParams() {
		var param = {}
		param.quickFlag = quickFlag;
	   if (!initFlag){
			param.finprodId = payPlanFinprodId;
			param.workflowId = workflowId;
			param.entityName = bussFinProductBonusPlanEntityName;
		}else{
			var vdate = getElementById('vdateElement').val()
			var mdate = getElementById('mdateElement').val()
			var calendarId = getElementById('calendarIdElement').val();
			if(vdate) {
				param.vdate = $('#' + vdate).val();
			}
			if(mdate) {
				param.mdate = $('#' + mdate).val();
			}
			if(calendarId) {
				param.calendarId = $('#' + calendarId).val();
			}
			param.pageId = getElementById('pageId').val();
			param.entityName = bussFinProductBonusPlanEntityName;
			param.payType = $('#FinNetValueProductBuild002_payType').val();//getElementById('payType').val();
			param.payCycle = getElementById('payCycle').val();
			param.payCycleVal = getElementById('payCycleVal').val();
			param.payCycleUnit = getElementById('payCycleUnit').val();
			param.calcRule = getElementById('calcRule').val();
			param.endTdayRule = getElementById('endTdayRule').val();
			param.payTdayRule = getElementById('payTdayRule').val();
			param.firstPayDate = getElementById('firstPayDate').val();
		}
		return param;
	}

	var table = getElementById('dataTable').length > 0 ? getElementById('dataTable').joyintable({
//        ajax: {
//            url: JoyinUtil.getRealPath('/component/prod/BussFinProductBonusPlanCommon/calcProductInterestPlan'),
//            type: 'POST',
//            data: function() {
//            
//        		// 保存预览参数，用于在主画面保存时进行验证
//        		previewParams = getPreviewParams();
//                return previewParams;
//            }
//        },
//        searchOnInit: pageModel != PAGE_MODEL_NEW && $('#' + profitType).val() === PROFIT_TYPE_03,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        done: function(){
        	//在生成计划时将一些规则赋值
        	vdateBounse = getElementById('vdateElement').val();
        	mdateBounse = getElementById('mdateElement').val();
        	calendarIdBounse = getElementById('calendarIdElement').val();
        	if(vdateBounse) {
        		vdateBounse = $('#' + vdateBounse).val();
			}
			if(mdateBounse) {
				mdateBounse = $('#' + mdateBounse).val();
			}
			if(calendarIdBounse) {
				calendarIdBounse = $('#' + calendarIdBounse).val();
			}
        	payTypeBounse = $('#FinNetValueProductBuild002_payType').val();
        	payCycleBounse =  getElementById('payCycle').val();
        	payCycleValBounse = getElementById('payCycleVal').val();
        	payCycleUnitBounse = getElementById('payCycleUnit').val();
        	calcRuleBounse =  getElementById('calcRule').val();
        	endTdayRuleBounse = getElementById('endTdayRule').val();
        	payTdayRuleBounse = getElementById('payTdayRule').val();
        	firstPayDateBounse = getElementById('firstPayDate').val();
        },
        cols:[
             {field:'vdate', title: 'BussFinProductPayPlan.column.vdate',sortable:false, type:'date', width: 120}
            ,{field:'mdate', title: 'BussFinProductPayPlan.column.mdate',sortable:false, type:'date', width: 120}
        	,{field:'payDate', title: 'BussFinProductBonusPlan.column.payDate',sortable:false, type:'date', width: 120}
        ]
    }) : {};
    //增加改变一些影响计划因素是否重新生成计划校验
    function checkIsChange() {
		 var selectedValueProfitType = $('#' + getElementById('profitType').val()).val();
	       // 非预期收益型
	       if (selectedValueProfitType === PROFIT_TYPE_03) {
		    // 付息计划列表为空时
			var list = table.getAllRows();
			if(!list || list.length == 0) {
				getElementById('btn_preview').trigger('click');
			}
			else {
				var newParam = getPreviewParams(), msg;
				// 起息日，到期日，交易日历与计划生成时记录的值比较，值变更时
				if(vdateBounse != newParam.vdate) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', ['起息日', '分红']);
				}
				else if(mdateBounse != newParam.mdate) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', ['到期日', '分红']);
				}
				else if(calendarIdBounse != newParam.calendarId) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', ['交易日历', '分红']);
				}
				//付息规则，付息周期，付息周期单位，付息计划推算方法，首次付息日，计息营业日准则，付息营业日准则与计划生成时记录的值比较，值变更时
				else if(payCycleBounse != newParam.payCycle || payCycleValBounse != newParam.payCycleVal
						 || payCycleUnitBounse != newParam.payCycleUnit || calcRuleBounse != newParam.calcRule
						 || firstPayDateBounse != newParam.firstPayDate
						 || endTdayRuleBounse != newParam.endTdayRule || payTdayRuleBounse != newParam.payTdayRule) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', ['分红规则', '分红']);
				}
				if(msg) {
					Joyin.error(msg);
					return false;
				}
			}
		}
		return true;
	}
    /*
	 * 保存验证
	 */
	function check() {
		 var selectedValueProfitType = $('#' + getElementById('profitType').val()).val();
	       // 非预期收益型
	       if (selectedValueProfitType === PROFIT_TYPE_03) {
		    // 付息计划列表为空时
			var list = table.getAllRows();
			if(!list || list.length == 0) {
				getElementById('btn_preview').trigger('click');
			}
			else {
				var newParam = getPreviewParams(), msg;
				// 起息日，到期日，交易日历与计划生成时记录的值比较，值变更时
				if(previewParams.vdate != newParam.vdate) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', '起息日', '付息');
				}
				else if(previewParams.mdate != newParam.mdate) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', '到期日', '付息');
				}
				else if(previewParams.calendarId != newParam.calendarId) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', '交易日历', '付息');
				}
				//付息规则，付息周期，付息周期单位，付息计划推算方法，首次付息日，计息营业日准则，付息营业日准则与计划生成时记录的值比较，值变更时
				else if(previewParams.payCycle != newParam.payCycle || previewParams.payCycleVal != newParam.payCycleVal
						 || previewParams.payCycleUnit != newParam.payCycleUnit || previewParams.calcRule != newParam.calcRule
						 || previewParams.firstPayDate != newParam.firstPayDate
						 || previewParams.endTdayRule != newParam.endTdayRule || previewParams.payTdayRule != newParam.payTdayRule) {
					msg = JoyinUtil.getMessage('BUSS_COM_E_0037', '付息规则', '付息');
				}
				if(msg) {
					Joyin.error(msg);
					return false;
				}
			}
		}
		return true;
	}
    

    $(function() {
        init();
        loadTable();
        // 推算方法联动处理
        getElementById('calcRule').change(function() {
        	var calcRule = getElementById('calcRule').val();
        	if (calcRule === CAL_RULE_05){
        		getElementById('firstPayDate').prop('disabled', false);
        	}else{
        		getElementById('firstPayDate').prop('disabled', true).val('').trigger('change');
        	}
        });
        
        // 分红规则改变时触发的事件
        getElementById('payCycle').change(function() {
            if(getElementById('payCycle').val() === '' || getElementById('payCycle').val() === BONUS_RULE_02) {
                getElementById('payCycleVal').prop('disabled', false);
                getElementById('payCycleUnit').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW){
                	getElementById('calcRule').val(CAL_RULE_01).trigger('change')
                }
                getElementById('calcRule').prop('disabled', false);
               
                if(pageModel === PAGE_MODEL_NEW){
                	getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
                    getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
                }
                getElementById('endTdayRule').prop('disabled', false);
                getElementById('payTdayRule').prop('disabled', false);
                getElementById('planDiv').show();
            } else if(getElementById('payCycle').val() === BONUS_RULE_01) {
                getElementById('payCycleVal').prop('disabled', true).val('').trigger('change');
                getElementById('payCycleUnit').prop('disabled', true).val('').trigger('change');
                getElementById('calcRule').prop('disabled', true).val(CALC_RULE_01).trigger('change');
                
                getElementById('endTdayRule').prop('disabled', true).val('').trigger('change');
                getElementById('payTdayRule').prop('disabled', false);
                getElementById('planDiv').show();
            } else {
                getElementById('payCycleVal').prop('disabled', true).val('').trigger('change');
                getElementById('payCycleUnit').prop('disabled', true).val('').trigger('change');
                getElementById('calcRule').prop('disabled', true).val('').trigger('change');
                
                getElementById('endTdayRule').prop('disabled', true).val('').trigger('change');
                getElementById('payTdayRule').prop('disabled', true).val('').trigger('change');
                getElementById('planDiv').hide();
                table.reload({ajax:null, url: null, data:[]});
            }
        });
        // 父画面处理模式联动事件
        $('#' + getElementById('profitType').val()).change(profitTypeChange);
        // 预览按钮点击事件
        getElementById('btn_preview').click(function() {
        	initFlag = true;
            Joyin.ajax('/component/prod/BussFinProductBonusPlanCommon/check', getPreviewParams(), function(result) {
            	if (result.data){
            		Joyin.confirm(result.data, function() {
            			//modify by wm 2019-11-09 start 当首次分红日为节假日时，不生成分红计划问题修改
//            		    if(table.table){
//            		        table.clear();
//            		    }
            			Joyin.ajax(JoyinUtil.getRealPath('/component/prod/BussFinProductBonusPlanCommon/calcProductInterestPlan'),
            	        		getPreviewParams(), function(rows) {
                    		        table.table ? table.clear() : table.load();
            		        	    table.addRow(rows);
            	                 });
            		//modify by wm 2019-11-09 end 当首次分红日为节假日时，不生成分红计划问题修改
            		});
            	}else{
            		Joyin.ajax(JoyinUtil.getRealPath('/component/prod/BussFinProductBonusPlanCommon/calcProductInterestPlan'),
        	        		getPreviewParams(), function(rows) {
                		        table.table ? table.clear() : table.load();
        		        	    table.addRow(rows);
        	                 });
            	}
            });
        });
    });
}());