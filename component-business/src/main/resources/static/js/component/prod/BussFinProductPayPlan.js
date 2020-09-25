/**
 * 产品付息计划共通组件
 * @auth 何阳阳
 */
(function(){

	// 预览参数
	var previewParams = {};
	
	var getElementById = function(id) {
		return $('#' + payPlanEntityName + '_' + id);
	};
	
	/**
	 * 初始化加载tableFlag 
	 */
	var initFlag = false;
	// 付息规则Flag 
	var payCycleFlag = false;
	
	// 付息规则 申赎同频 开放计划flag 
	var payCycleandopenplanflag = false;
	
	// 推算方法Flag 
	var calcRuleFlag = false;
	// 快速录入标志
	var quickFlag = false;
	
	//yzg add
	var firstPayDate1=null;
	var payCycle1=null;
	var payCycleVal1=null;
	var payCycleUnit1=null;
	var endTdayRule1=null;
	var payTdayRule1=null;
	var vdate3=null;
	var mdate3=null;
	var calcRule1=null;
	var checkPayPlan = function(){
		if(calcRule1&&calcRule1!=$("#bussFinProductPayPlanDto_calcRule").val()){	
			Joyin.error(JoyinUtil.getMessage("推算方法已经改变,请重新点击付息计划生成"));
		    return false;
		}
		if(firstPayDate1&&firstPayDate1!=$("#bussFinProductPayPlanDto_firstPayDate").val()){	
			Joyin.error(JoyinUtil.getMessage("首次付息日已经改变,请重新点击付息计划生成"));
		    return false;
		}
		if(payCycle1&&payCycle1!= $("#bussFinProductPayPlanDto_payCycle").val()){	
			Joyin.error(JoyinUtil.getMessage("付息规则已经改变,请重新点击付息计划生成"));
		    return false;
		}
		if(payCycleVal1&&payCycleVal1!=$("#bussFinProductPayPlanDto_payCycleVal").val()){	
			Joyin.error(JoyinUtil.getMessage("付息周期已经改变,请重新点击付息计划生成"));
		    return false;
		}
	   if(payCycleUnit1&&payCycleUnit1!=$('#bussFinProductPayPlanDto_payCycleUnit').val()){	
			Joyin.error(JoyinUtil.getMessage("付息周期已经改变,请重新点击付息计划生成"));
		    return false;
		}
		if(endTdayRule1&&endTdayRule1!=$('#bussFinProductPayPlanDto_endTdayRule').val()){	
			Joyin.error(JoyinUtil.getMessage("计息营业日准则已经改变,请重新点击计划生成"));
		    return false;
		}
		if(payTdayRule1&&payTdayRule1!=$('#bussFinProductPayPlanDto_payTdayRule').val()){	
			Joyin.error(JoyinUtil.getMessage("付息营业日准则已经改变,请重新点击计划生成"));
		    return false;
		}
		if(vdate3&&vdate3!=$('#' + vdate1).val()){	
			Joyin.error(JoyinUtil.getMessage("起息日已经改变,请重新点击付息计划生成"));
		    return false;
		}
		if(mdate3&&mdate3!=$('#' + mdate1).val()){	
			Joyin.error(JoyinUtil.getMessage("到期日已经改变,请重新点击付息计划生成"));
		    return false;
		}
		//alert(payCycleandopenplanflag + " "+getElementById('payCycle').val() +"  "+INTPAY_PERIOD_03);
		if(!quickFlag && payCycleandopenplanflag){
			if( getElementById('payCycle').val() == INTPAY_PERIOD_03){
				if(BussFinProductOpenPlan.getOpenModeValue() != '02'){
					//高频开放不需要点击开放计划生成计划
					Joyin.error(JoyinUtil.getMessage("付息规则已经改变并且选择了申购同频，请重新点击开放计划生成计划"));
				    return false;
				}
			}else{
				Joyin.error(JoyinUtil.getMessage("付息规则已经改变，请重新点击付息信息预览生成付息计划!"));
			    return false;
			}
		}
		if(getElementById('payCycle').val() == INTPAY_PERIOD_03){
			if(BussFinProductOpenPlan.getOpenModeValue() == '02'){
				Joyin.error(JoyinUtil.getMessage("付息信息中的付息规则选择了申购同频，开放计划中的开放模式不能选择高频开放"));
			    return false;
			}
		}
	    return true;	
	}
	//yzg add
	
	/* 
	 * 付息类型选择为空时
	 *   恢复初期化状态
	 * 付息类型选择为到期时
	 *   付息营业日准则活性，计息营业日准则活性，其他项目非活性
	 * 付息类型选择为周期时
	 *   付息周期、付息周期单位、推算方法活性、计息营业日准则、付息营业日准则、预览按钮活性，首次付息日非活性
	 */
	function changePayCycleModule() {
		// 付息类型值
		var payCycle = getElementById('payCycle').val();
		if(!payCycle) {
			getElementById('payCycleVal').prop('disabled', false).trigger('change');
			getElementById('payCycleUnit').prop('disabled', false);
			getElementById('calcRule').prop('disabled', false);
			getElementById('endTdayRule').prop('disabled', false);
			getElementById('payTdayRule').prop('disabled', false);
			if (payCycleFlag){
				if (pageModel === PAGE_MODEL_NEW){
					getElementById('payCycleUnit').val(PERIOD_UNIT_01).trigger('change');
					getElementById('calcRule').val(CALC_METHOD_01).trigger('change');
					getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
					getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
				}
			}
		}
		else if(payCycle == INTPAY_PERIOD_01) {
			getElementById('payCycleVal').val('').prop('disabled', true);
			getElementById('payCycleUnit').val('').prop('disabled', true).trigger('change');
			getElementById('calcRule').val('').prop('disabled', true).trigger('change');
			getElementById('endTdayRule').val('').prop('disabled', true).trigger('change');
			getElementById('payTdayRule').prop('disabled', false);
			if (payCycleFlag){
				if (pageModel === PAGE_MODEL_NEW){
					getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
				}
			}
		}
		else if(payCycle == INTPAY_PERIOD_02) {
			getElementById('payCycleVal').prop('disabled', false);
			getElementById('payCycleUnit').val('').prop('disabled', false).trigger('change');
			getElementById('calcRule').val('').prop('disabled', false).trigger('change');
			getElementById('endTdayRule').prop('disabled', false);
			getElementById('payTdayRule').prop('disabled', false);
			if (payCycleFlag){
				if (pageModel === PAGE_MODEL_NEW){
					getElementById('calcRule').val(CALC_METHOD_01).trigger('change');
					getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
					getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
				}
			}
		}
	}

	/* 
	 * 付息类型选择为空时
	 *   恢复初期化状态
	 * 付息类型选择为到期时
	 *   付息营业日准则活性，计息营业日准则活性，其他项目非活性
	 * 付息类型选择为周期时
	 *   付息周期、付息周期单位、推算方法活性、计息营业日准则、付息营业日准则、预览按钮活性，首次付息日非活性
	 */
	function changePayCycleProduct() {
		// 付息类型值
		var payCycle = getElementById('payCycle').val();
		getElementById('btn_preview').show();
		if(!payCycle) {
			getElementById('payCycleVal').prop('disabled', false).trigger('change');
			getElementById('firstPayDate').prop('disabled', false);
			getElementById('payCycleUnit').prop('disabled', false);
			getElementById('calcRule').prop('disabled', false);
			getElementById('endTdayRule').prop('disabled', false);
			getElementById('payTdayRule').prop('disabled', false);
			if (payCycleFlag){
				if (pageModel === PAGE_MODEL_NEW){
					getElementById('firstPayDate').val('');
					getElementById('payCycleUnit').val(PERIOD_UNIT_01).trigger('change');
					getElementById('calcRule').val(CALC_METHOD_01).trigger('change');
					getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
					getElementById('payTdayRule').val(TDAY_RULE_02).trigger('change');
				}
			}
		}
		else if(payCycle == INTPAY_PERIOD_01) {
			getElementById('payCycleVal').prop('disabled', true).val('').trigger('change');
			getElementById('payCycleUnit').prop('disabled', true).val('').trigger('change');
			getElementById('calcRule').prop('disabled', true).val('').trigger('change');
			getElementById('firstPayDate').prop('disabled', true).val('').trigger('change');
			getElementById('endTdayRule').prop('disabled', true).val('').trigger('change');
		}
		else if(payCycle == INTPAY_PERIOD_02) {
			getElementById('payCycleVal').prop('disabled', false);
			getElementById('payCycleUnit').prop('disabled', false);
			getElementById('calcRule').prop('disabled', false);
			getElementById('firstPayDate').prop('disabled', true);
			getElementById('endTdayRule').prop('disabled', false);
			if (payCycleFlag){
				if (pageModel === PAGE_MODEL_NEW){
					getElementById('calcRule').val(CALC_METHOD_01).trigger('change');
					getElementById('firstPayDate').val('').trigger('change');
					getElementById('endTdayRule').val(TDAY_RULE_04).trigger('change');
				}
			}
		}else if(payCycle == INTPAY_PERIOD_03) {
			
			var operationTypeVal = $("#"+operationType).val();
			//封闭式
			if(operationTypeVal=="01"){
				getElementById('payCycle').val("");
				Joyin.error("发行方式为封闭式时,付息规则不能选择申购同频!");
				return;
			}
			
			getElementById('payCycleVal').val('').prop('disabled', true);
			getElementById('payCycleUnit').val('').prop('disabled', true).trigger('change');
			getElementById('calcRule').val('').prop('disabled', true).trigger('change');
			getElementById('endTdayRule').val('').prop('disabled', true).trigger('change');
			//getElementById('payTdayRule').prop('disabled', false);
			getElementById('payTdayRule').val('').prop('disabled', true).trigger('change');
			
			//禁用预览按钮
			getElementById('btn_preview').hide();

		}
	}
	
	/*
	 * 推算方法值变化时执行的事件
	 * 推算方法选择首次付息日时		
	 *   首次付息日设为活性、并设为平台日期	
	 * 上记以外时		
	 *   清空首次付息日内容，并将其设为非活性	
	 */
	function changeCalcRule() {
		// 推算方法
		var payCycle = getElementById('calcRule').val();
		if(payCycle === CALC_METHOD_04) {
			getElementById('firstPayDate').prop('disabled', false);
			if (calcRuleFlag){
				getElementById('firstPayDate').val(platDate);
			}
		}
		else {
			getElementById('firstPayDate').prop('disabled', true).val('');
		}
	}

	/*
	 * 保存验证
	 */
	function check() {
		if(getElementById('pageId').val() === 'FinProductBuild002'||getElementById('pageId').val() === 'FinNetValueProductBuild002') {
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
	
	/*
	 * 获取预览时所需参数
	 */
	function getPreviewParams() {
		var param = {}
		param.quickFlag = quickFlag;
		if (!initFlag){
			param.finprodId = payPlanFinprodId;
			param.workflowId = workflowId;
			param.entityName = payPlanEntityName;
		}else{
			var vdate = getElementById('vdateElement').val();
			var mdate = getElementById('mdateElement').val();
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
			param.entityName = payPlanEntityName;
			param.payType = getElementById('payType').val();
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
        ajax: {
            url: JoyinUtil.getRealPath('/component/prod/BussFinProductPayPlan/calcProductInterestPlan'),
            type: 'POST',
            data: function() {
        		// 保存预览参数，用于在主画面保存时进行验证
        		previewParams = getPreviewParams();
                return previewParams;
            }
        },
        searchOnInit: pageModel != PAGE_MODEL_NEW && $('#' + profitType).val() === PROFIT_TYPE_02,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        cols:[
             {field:'vdate', title: 'BussFinProductPayPlan.column.vdate',sortable:false, type:'date', width: 120}
            ,{field:'mdate', title: 'BussFinProductPayPlan.column.mdate',sortable:false, type:'date', width: 120}
        	,{field:'payDate', title: 'BussFinProductPayPlan.column.payDate',sortable:false, type:'date', width: 120}
        ],
        done : function(){
        	//add by yzg 20191025 start
    		firstPayDate1 = getElementById('firstPayDate').val();
    		payCycle1 = getElementById('payCycle').val();
    		payCycleVal1 = getElementById('payCycleVal').val();
    		payCycleUnit1 = getElementById('payCycleUnit').val();
    		endTdayRule1 = getElementById('endTdayRule').val();
    		payTdayRule1 = getElementById('payTdayRule').val();
    		vdate3 = $('#' + vdate1).val();
    		mdate3 = $('#' + mdate1).val();
    		calcRule1 = getElementById('calcRule').val();
        	//add by yzg 20191025 start
        }
    }) : {};
	
	
	
    var payPlanInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductPayPlan/queryPayPlanInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(payPlanEntityName, data);
    	})
    	if(getElementById('pageId').val() === 'FinProductBuild002' || getElementById('pageId').val() === 'FinNetValueProductBuild002') {
    		payPlanFinprodId = input_finprodId;
    		quickFlag = true;
    		//是否显示日期值 chenxiaoqiu 20191126
    		if(isDisplayDate == YES){
        	table.table ? table.reload() : table.load();
    		}
    	}
    }
    
    
    function reloadpayplantable(dateList){
    	table.reload({ajax:null, url: null, data:[]});
    	var rows = new Array();
    	var row = {};
		for(var i in dateList){
			if(i==0){
				row.vdate =new Date( $('#FinProductBuild002_vdate').val() ).format('yyyy-MM-dd');
				
			}
			row.mdate = new Date(dateList[i].openVdate).format('yyyy-MM-dd');
			row.payDate = new Date(dateList[i].openVdate).format('yyyy-MM-dd');
			//table.addRow(row);
			rows.push(row);
			row = {};
			row.vdate = new Date(dateList[i].openVdate).format('yyyy-MM-dd');
		}

		table.reload({data:rows});
		
		payCycleandopenplanflag = false;
    }
    
    //wj
    var genePayPlan = function (dateList){

//    	
//    	alert(getElementById('dataTable').length);
//    	
//    	table.init(element);
//    	initFlag = true;
//    	table.table ? table.reload() : table.load();
    	if(INTPAY_PERIOD_03 == getElementById('payCycle').val()){
    		
	    	initFlag = true;
	        Joyin.ajax('/component/prod/BussFinProductPayPlan/check', getPreviewParams(), function(result) {
	        	if (result.data){
	        		Joyin.confirm(result.data, function() {
	        			table.table ? table.reload() : table.load();
	        			reloadpayplantable(dateList);
	        		});
	        	}else{
	        		table.table ? table.reload() : table.load();
	        		reloadpayplantable(dateList);
	        	}
	        });
        
    	}
		
	}
    
    var genePayCycleValue = function (dateList){
    	return getElementById('payCycle').val();
    }
    
    
    // 公开接口
	window.ProductPayPlan = {
		check: check,
		payPlanInitAction : payPlanInitAction,
		checkPayPlan : checkPayPlan,
		genePayPlan : genePayPlan,
		genePayCycleValue : genePayCycleValue
//		exposePayCycleFlag : exposePayCycleFlag
	};
	
	
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + profitType).val();

        if (selectedValueProfitType && selectedValueProfitType === PROFIT_TYPE_02) {
            getElementById('div').show();
            if (pageModel === PAGE_MODEL_NEW){
            	// 付息规则默认到期
                getElementById('payCycle').attr('initValue', INTPAY_PERIOD_01).val(INTPAY_PERIOD_01).trigger('change');
                getElementById('payTdayRule').attr('initValue', TDAY_RULE_02).val(TDAY_RULE_02).trigger('change');
            }
        } else {
            getElementById('div').hide();
        }
    }
    
	$(function() {
        // 父画面处理模式联动事件
        $('#' + profitType).change(profitTypeChange);
        profitTypeChange();
        // 付息类型改变时触发的事件
        getElementById('payCycle').change(function() {
            payCycleFlag = true;
            if(getElementById('pageId').val() === 'FinProductBuild002' || getElementById('pageId').val() === 'FinNetValueProductBuild002') {
            	
            	if($("#"+operationType).val() == '02'){
            		// 只有开放式时才判断
            	    payCycleandopenplanflag = true;
            	}
            	changePayCycleProduct();
            }
            else {
                changePayCycleModule();
            }
            
        });
        // 推算方法改变时触发的事件
        getElementById('calcRule').change(function(){
        	changeCalcRule();
        	calcRuleFlag = true;
        });
        // 计息营业日准则改变时触发的事件
        getElementById('endTdayRule').change(function(){
            if(getElementById('pageId').val() === 'FinProductBuild002') {
            	if (getElementById('endTdayRule').val() == TDAY_RULE_02) {
            		getElementById('payTdayRule').prop('disabled', true).val(TDAY_RULE_02).trigger('change');
            	} else {
            		getElementById('payTdayRule').prop('disabled', false).val(TDAY_RULE_02).trigger('change');
            	}
            }
        });
        
        // 预览按钮点击事件
        getElementById('btn_preview').click(function() {
        	initFlag = true;
        	payCycleandopenplanflag = false;
            Joyin.ajax('/component/prod/BussFinProductPayPlan/check', getPreviewParams(), function(result) {
            	if (result.data){
            		Joyin.confirm(result.data, function() {
            			table.table ? table.reload() : table.load();
            		});
            	}else{
            		table.table ? table.reload() : table.load();
            	}
            });
        });
	});
}());