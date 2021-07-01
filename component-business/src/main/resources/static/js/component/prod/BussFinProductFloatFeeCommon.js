 /* 开放计划共通JS
 * @auth zhengyan
 */
(function(){
	
	// 快速录入标志
	var quickFlag = false;
	
    /**
     * 获取画面元素
     */
	function getElementById(id) {
		return $('#' + floatFeeEntityName + '_' + id);
	}


    /**
     * 定义数据表格
     *
     */
    var floatManageFeeTable=getElementById('floatManageFee').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('component/prod/BussFinProductFloatFeeCommon/productFloatFeeQuery'),
            type: 'POST',
            data: function() {
            	 return {finprodId: floatFeeFinprodId, workflowId: workflowId, entityName : floatFeeEntityName, effDate : effDate, quickFlag : quickFlag};
            }
        },
        searchOnInit:  true,
        height : 200,
        checkbox:false,
        addbtn:true,
        addclick : function(rowData) {
        	//rowData.floatBaseLevel = floatManageFeeTable.getAllRows().length;
        	
        	rowData.lev = floatManageFeeTable.getAllRows().length;
        	rowData.baseRuleId = JoyinUtil.getLabel("BussBenchMarkInfo");
        	rowData.coefficient = 1;
        	rowData.spreadRate = 0;
        	rowData.chargeRatio = 0;
        },
        loadonce : true,
        cols:[
            {field:'lev', width:200, title: 'BussFinProductFloatFee.table.floatBaseLevel', sortable: false, render : function(data, type, row, meta) { return (row.LAY_TABLE_INDEX+1); }}
            ,{field:'baseRuleId', width:200, title: 'BussFinProductFloatFee.table.baseRate', sortable: false, render:function () {return JoyinUtil.getLabel("BussBenchMarkInfo");}}
            ,{field:'coefficient', width:200, title: 'BussFinProductFloatFee.table.coefficient', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2"}
            ,{field:'spreadRate', width:200, title: 'BussFinProductFloatFee.table.spreadRate', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2", digit:4}
            ,{field:'chargeRatio', width:200, title: 'BussFinProductFloatFee.table.chargeRatio', sortable: false,edit:true, type: 'number',fixType:"2",pattern:"2", digit:4}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    

    
    /**
     * 绑定删除事件
     */
    floatManageFeeTable.bind('click', '.delt', function(data, index) {
    	floatManageFeeTable.deleteRow(index);
    });
    /** ADD BY YANYA ON 20190921 记录费用原始对应的计费基数，用来当产品类型变更时还原，防止净值型产品选择资源本金时，再切换非净值型产品时，无法还原**/
    var feedBasicBillVal = '';
    function feeNameChangeAction(){
    	var feeId = getElementById('feeId').val();
    	if (feeId && feeId != ''){
			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFeeCommon/queryMstFeeinfoByFeeId'), {feeId : feeId}, function(result){
				if (result){
					debugger;
					// /** ADD BY YANYA ON 20190920 增加是否修改时初始化值判定，为初始化值时，不进行联动（防止选择的值被替换）**/
					// var selectedValueProfitType = $('#' + parentProfitType).val();
					// if(selectedValueProfitType != PROFIT_TYPE_01 || (getElementById('basicBill').val() == '')){
					getElementById('basicBill').val(result.basicBill).trigger('change');	
					// }
					feedBasicBillVal = result.basicBill;
					getElementById('basis').val(result.basis).trigger('change');
				}
			})
			getElementById('div_floatManageFee').toggle(true);
			getElementById('netAssetValueDay').attr('disabled', false);
			getElementById('provisionFirstDay').attr('disabled', false);
			getElementById('floatBaseRule').attr('disabled', false);
			//add by yejiejie 隐藏业绩基准规则  2019-2-11 start
			$("#floatBaseRuleDiv").hide();
			//add by yejiejie 隐藏业绩基准规则  2019-2-11 end
			getElementById('eAccrDate').attr('disabled', false);
			getElementById('fAccrEndDate').attr('disabled', false);
			getElementById('firstPayDate').attr('disabled', false);
			getElementById('basicBill').attr('disabled', true);//计费基数置灰
			
			
			if(!getElementById('eAccrDate').val() && !getElementById('provisionFirstDay').val()){
				var object = $.serializeToObject('#'+productBuildForm, false);
				//add by yejiejie 
				if(object.operationType == "01"){
                    //OPEX-572 产品端-浮动管理费首次计提日应为-1日 guyunfei start
                    var mdate1 = $('#'+mdate).val();
                    var mdate1Low = Date.ofDate(mdate1).DateAdd('d', -1).format('yyyy-MM-dd');
                    getElementById('provisionFirstDay').val(mdate1Low);
                    //OPEX-572 产品端-浮动管理费首次计提日应为-1日 guyunfei end
					getElementById('eAccrDate').val(getElementById('provisionFirstDay').val()).attr('disabled', true);
				}else if(object.operationType == "02") {
					var setFlag = true;
					if(object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.openMode == "02"){
						//初始化首次计提
						getElementById('provisionFirstDay').val($('#' + vdate).val());
						//初始化计提结束日
						getElementById('eAccrDate').val($('#'+mdate).val());
						setFlag = false;
					}
                    if(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.openMode == "02"){
                    	//初始化首次计提
						getElementById('provisionFirstDay').val($('#' + vdate).val());
						//初始化计提结束日
						getElementById('eAccrDate').val($('#'+mdate).val());
						setFlag = false;
                    }
                    var profitType = object.profitType;
                    var firstdate;
                    var enddate;
					if(object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.finOpenPlanList && setFlag && profitType){
						var finOpenPlanList = object.bussFinProductOpenPlanDto.finOpenPlanList;
						if(finOpenPlanList.length>0){
							if(profitType == "01"){
								firstdate = object.bussFinProductOpenPlanDto.finOpenPlanList[0].openNdate;
								enddate = object.bussFinProductOpenPlanDto.finOpenPlanList[finOpenPlanList.length-1].openNdate;
							}else if(profitType == "03"){
								firstdate = object.bussFinProductOpenPlanDto.finOpenPlanList[0].openDateEnd;
								enddate = object.bussFinProductOpenPlanDto.finOpenPlanList[finOpenPlanList.length-1].openDateEnd;
							}
							
						}
						
					}
                    if(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.finOpenPlanList && setFlag  && profitType){
                    	var finOpenPlanList = object.bussFinProductOpenPlanRedDto.finOpenPlanList;
                    	var firstdate1;
                    	var enddate1;
                    	if(finOpenPlanList.length>0 ){
                    		if(profitType == "01"){
                        		firstdate1 = finOpenPlanList[0].openNdate;
                        		enddate1 = finOpenPlanList[finOpenPlanList.length-1].openNdate;
                        	}else if(profitType == "03"){
                        		firstdate1 = finOpenPlanList[0].openDateEnd;
                        		enddate1 = finOpenPlanList[finOpenPlanList.length-1].openDateEnd;
                        	}
							if(!firstdate){
								firstdate = firstdate1;
							}else if(firstdate1 && Date.ofDate(firstdate) - Date.ofDate(firstdate1) >0 ){
								firstdate  = firstdate1;
							}
							if(!enddate){
								enddate = enddate1;
							}else if(enddate1 && Date.ofDate(enddate) - Date.ofDate(enddate1) < 0 ){
								enddate  = enddate1;
							}
						}
					}
                    if(firstdate){
                    	//初始化首次计提日
    					getElementById('provisionFirstDay').val(Date.ofDate(firstdate).Format('yyyy-MM-dd'));
                    }else if(setFlag){
                    	//初始化首次计提日
    					getElementById('provisionFirstDay').val($('#'+vdate).val());
                    }
                    if(enddate){
                    	//初始化计提结束日
    					getElementById('eAccrDate').val(Date.ofDate(enddate).Format('yyyy-MM-dd'));
                    }else if(setFlag){
                    	//初始化计提结束日
    					getElementById('eAccrDate').val($('#'+mdate).val());
                    }
					
					
				}else{
					//初始化计提结束日
					getElementById('eAccrDate').val($('#'+mdate).val());
					//初始化首次计提日
					getElementById('provisionFirstDay').val($('#'+vdate).val());
				}
				//add by yejiejie 
			}
			$("#hidediv1").hide(); 
			$("#hidediv2").hide(); 
			
			
		}else{
			getElementById('basicBill').val('').trigger('change');
			getElementById('basis').val('').trigger('change');
			getElementById('div_floatManageFee').toggle(false);
			getElementById('netAssetValueDay').val('').trigger('change').attr('disabled', true);
			getElementById('provisionFirstDay').val('').trigger('change').attr('disabled', true);
			getElementById('floatBaseRule').val('').trigger('change').attr('disabled', true);
			//add by yejiejie 隐藏业绩基准规则  2019-2-11 start
			$("#floatBaseRuleDiv").hide();
			//add by yejiejie 隐藏业绩基准规则  2019-2-11 end
			getElementById('eAccrDate').val('').trigger('change').attr('disabled', true);
			getElementById('fAccrEndDate').val('').trigger('change').attr('disabled', true);
			getElementById('firstPayDate').val('').trigger('change').attr('disabled', true);
			$("#hidediv1").hide(); 
			$("#hidediv2").hide();
			getElementById('basicBill').attr('disabled', false);//计费基数不置灰
		}
    }
    
    var profitTypeChange = function () {
        var selectedValueProfitType = $('#' + profitType).val();
        /** ADD BY YANYA ON 20190921 净值型产品选择资源本金时，再切换非净值型产品时，还原原产品的基数类型 **/
        if(selectedValueProfitType != PROFIT_TYPE_01 && feedBasicBillVal != ''){
			getElementById('basicBill').val(feedBasicBillVal).trigger('change');
		}
        if (selectedValueProfitType) {
            // 净值型展示
        	 // 非预期收益型
            if (selectedValueProfitType != PROFIT_TYPE_02) {
                getElementById('div').show();
                /*if(selectedValueProfitType === PROFIT_TYPE_01)
                	getElementById('netAssetValueDay').val('').parent().parent().show();//add by zhangweiqi
                else
                	getElementById('netAssetValueDay').val('').parent().parent().hide();//add by zhangweiqi
*/            }
            else{
                getElementById('div').hide();
            }
            /** ADD BY YANYA ON 20190919收益类型为净值型基数类型可选，其他类型时不可选**/
            if(selectedValueProfitType == PROFIT_TYPE_01){
            	getElementById('basicBill').prop('disabled', false).trigger('change');
            }
            else {
            	getElementById('basicBill').prop('disabled', true).trigger('change');
            }
        } else {
            getElementById('div').hide();
            /** ADD BY YANYA ON 20190919 收益类型没有值时，基数类型不可选**/
            getElementById('basicBill').prop('disabled', true).trigger('change');
        }
    }
    
    // 公开接口
    window.BussFinProductFloatFee = {
            getAllRows:getAllRows,
            floatFeeInitAction : floatFeeInitAction
    };
    
   
    
    function getAllRows(){
        if(floatManageFeeTable != ''){
            return floatManageFeeTable.getAllRows();
        }
    }
    
    function floatFeeInitAction(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFee/queryFloatFeeInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(floatFeeEntityName, data);
    	})
    	floatFeeFinprodId = input_finprodId;
    	quickFlag = true;
    	if($("#"+vdate).val())effDate=$("#"+vdate).val();
    	floatManageFeeTable.table ? floatManageFeeTable.reload() : floatManageFeeTable.load();
    }
    
    /**
	 * 初始化加载tableFlag 
	 */
	var initFlag = false;
	
	var getSearchParams = function(){
		var param = {};
			param.pageId = pageId;
			param.dealMode = $('#' + dealMode).val();
			param.vdate = $('#' + vdate).val();
			param.mdate = $('#' + mdate).val();
			param.tradCalender = $('#' + tradCalender).val();
			param.raiseStrDate = $('#' + raiseStrDate).val();
			param.raiseEndDate = $('#' + raiseEndDate).val();
			param.purConfDays =getElementById('purSpeed').val();
			if ($('#' + isCycle).next().find("em").text() === '是') {
				param.isCycle = 'Y';
	        } else {
	        	param.isCycle = 'N';
	        }
			param.closeTerm = $('#' + closeTerm).val();
			
			param.feeId = getElementById('feeId').val();
			param.basicBill = getElementById('basicBill').val();
			param.basis = getElementById('basis').val();
			param.netAssetValueDay = getElementById('netAssetValueDay').val();
			if (getElementById('dayOfWeek').val()){
				param.dayOfWeek = getElementById('dayOfWeek').val().join(",");
			}
			param.openDate = getElementById('radioDiv').find(':radio:checked').val();
			if (getElementById('dayOfMonth').val()){
				param.dayOfMonth = getElementById('dayOfMonth').val().join(",");
			}
			if (getElementById('workdayOfMonth').val()){
				param.workdayOfMonth = getElementById('workdayOfMonth').val().join(",");
			}
			param.calcMethod = getElementById('calcMethod').val();
			param.firstOpenDate = getElementById('firstOpenDate').val();
			param.tradedayRule = getElementById('tradedayRule').val();
			param.calcRule = getElementById('calcRule').val();
			param.cpriceRule=getElementById('cpriceRule').val();
			param.openType=getElementById('sameFLag').next().find("em").text()==="是"?"03":"01";
			//getElementByRedId('openType').val(param.openType);
			if ($('#hdayOpenFlag').prop('checked')) {
				param.hdayOpenFlag = 'Y';
	        } else {
	        	param.hdayOpenFlag = 'N';
	        }
			
			//********
			param.provisionFirstDay = getElementById('provisionFirstDay').val();
			param.accrType = getElementById('accrType').val();
			param.interValRule = getElementById('interValRule').val();
			param.eAccrDate = getElementById('eAccrDate').val();
			param.cpriceRule=getElementById('cpriceRule').val();
			param.fAccrEndDate=getElementById('fAccrEndDate').val();
			param.accrTdayRule=getElementById('accrTdayRule').val();
			param.payCycle=getElementById('payCycle').val();
			param.firstPayDate=getElementById('firstPayDate').val();
			param.endTdayRule=getElementById('endTdayRule').val();
		   
			param.payTdayRule=getElementById('payTdayRule').val();
		return param;
	}
	 /**
     * 产品处理模式联动事件
     */
    var dealModeChange = function (){
        //收益类型为净值型 且产品处理模式为分档 改界面不显示  其他场景显示
        if($('#' + profitType).val() === PROFIT_TYPE_01 && $('#' + dealModeElement).val() === DISPOSE_PATTERN_11){
            getElementById('div').hide();
        } else {
            getElementById('div').show();
        }
    }
    /**
     * 期限联动事件
     */
    var periodChange = function (){
    	if($('#' + dealModeElement).val() === DISPOSE_PATTERN_10){
            //期限为多固定期限隐藏
            if($('#' + profitType).val() === PROFIT_TYPE_01 && $('#' + periodElement).val() === TERM_TYPE_02){
                getElementById('div').hide();
            } else {
                getElementById('div').show();
            }
    	}
    }
    $(function() {
    	// 计提方式改变时触发的事件
        getElementById('accrType').change(function() {
             if(getElementById('accrType').val() =="01") {
            	getElementById('interValRule').val('').trigger('change').prop('disabled', true);
            }else{
            	getElementById('interValRule').val('').trigger('change').prop('disabled', false);
            }
        });
        getElementById('endTdayRule').change(function() {
           if(getElementById('endTdayRule').val() =="02") {
           	getElementById('payTdayRule').val('02').trigger('change').prop('disabled', true);
           }else{
           	getElementById('payTdayRule').val('').trigger('change').prop('disabled', false);
           }
       });
        getElementById('provisionFirstDay').change(function() {
			var provisionFirstDay = getElementById('provisionFirstDay').val();
			var object = $.serializeToObject('#'+productBuildForm, false);
			var mdate1 = $('#'+mdate).val();
			var vdate1 = $('#'+vdate).val();
//			if(!mdate1 && provisionFirstDay){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("到期日没有选择");
//				return;
//			}
//			if(!vdate1 && provisionFirstDay){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("起息日没有选择");
//				return;
//			}
//			if(!object.operationType && provisionFirstDay){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("开放方式没有选择");
//				return;
//			}
            ////OPEX-572 产品端-浮动管理费首次计提日应为-1日 guyunfei start
            var mdate1Low = Date.ofDate(mdate1).DateAdd('d', -1).format('yyyy-MM-dd');
			if(object.operationType == "01" && provisionFirstDay){
				var days = (Date.ofDate(mdate1) - Date.ofDate(provisionFirstDay))/(24*60*60*1000);
				if(days>1 || days<0){
					getElementById('provisionFirstDay').val(mdate1Low);
                    //OPEX-572 产品端-浮动管理费首次计提日应为-1日 guyunfei end
					Joyin.error("封闭式产品 浮动管理费  首次计提日 只能 产品到期日 或 产品到期日-1");
				}
				getElementById('eAccrDate').val(getElementById('provisionFirstDay').val()).attr('disabled', true);
			}
		});
//        getElementById('eAccrDate').change(function() {
//			var eAccrDate = getElementById('eAccrDate').val();
//			var object = $.serializeToObject('#'+productBuildForm, false);
//			var mdate1 = $('#'+mdate).val();
//			var vdate1 = $('#'+vdate).val();
//			if(!mdate1 && eAccrDate){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("到期日没有选择");
//				return;
//			}
//			if(!vdate1  && eAccrDate ){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("起息日没有选择");
//				return;
//			}
//			if(!object.operationType  && eAccrDate){
//				getElementById('provisionFirstDay').val("");
//				Joyin.error("开放方式没有选择");
//				return;
//			}
//			if(object.operationType == "02" && eAccrDate){
//				if(!((object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.openMode == "02")||
//					(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.openMode == "02"))){
//					
//					var flag = true;
//					if(object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.finOpenPlanList && flag){
//						var finOpenPlanList = object.bussFinProductOpenPlanDto.finOpenPlanList;
//						
//						if(finOpenPlanList.length>0){
//							for(var i=0;i<finOpenPlanList.length;i++){
//								if(eAccrDate == Date.ofDate(finOpenPlanList[i].openVdate).DateAdd("d",-1).Format('yyyy-MM-dd')){
//									flag =false;
//								}
//		
//							}
//					     }
//						
//					}
//                    if(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.finOpenPlanList && flag ){
//                    	var finOpenPlanList = object.bussFinProductOpenPlanRedDto.finOpenPlanList;
//						if(finOpenPlanList.length>0 ){
//							for(var i=0;i<finOpenPlanList.length;i++){
//								if(eAccrDate == Date.ofDate(finOpenPlanList[i].openVdate).DateAdd("d",-1).Format('yyyy-MM-dd')){
//									flag =false;
//								}
//		
//							}
//						}
//					}
//                    if(flag){
//                    	getElementById('eAccrDate').val("");
//						Joyin.error("开放式产品  浮动管理费  非高频  计提结束日 只能选  申购确认日-1自然日/赎回确认日-1自然日");
//                    }
//					
//				}
//			}
//		});
    	getElementById('feeId').change(function(){
    		feeNameChangeAction();
    	});
    	getElementById('payCycle').change(function(){
    		var payCycle = getElementById('payCycle').val();
			if (pageModel != PAGE_MODEL_REFERENCE) {
    		if(payCycle == "08"){
    			getElementById('firstPayDate').val("").trigger('change').prop('disabled', true);
    			getElementById('endTdayRule').val("").trigger('change').prop('disabled', true);
    			getElementById('payTdayRule').val("").trigger('change').prop('disabled', true);
    		}else if(payCycle == "02" && $('#' + vdate).val()){
                var newDate = Date.ofDate($('#' + vdate).val()).DateAdd("d",7);
                if($('#' + mdate).val() && (newDate - Date.ofDate($('#' + mdate).val())>0)){
                    getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
                }else{
                    getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
                }
                getElementById('endTdayRule').trigger('change').prop('disabled', false);
                getElementById('payTdayRule').trigger('change').prop('disabled', false);

            }else if(payCycle == "03" && $('#' + vdate).val()){
    			var newDate = Date.ofDate($('#' + vdate).val()).DateAdd("m",1);
                if($('#' + mdate).val() && (newDate - Date.ofDate($('#' + mdate).val())>0)){
                    getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
                }else{
                    getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
                }
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);

    		}else if(payCycle == "04" && $('#' + vdate).val()){

    			var newDate = Date.ofDate($('#' + vdate).val()).DateAdd("m",3);
                if($('#' + mdate).val() && (newDate - Date.ofDate($('#' + mdate).val())>0)){
                    getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
                }else{
                    getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
                }
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "05" && $('#' + vdate).val()){
				var newDate = Date.ofDate($('#' + vdate).val()).DateAdd("y",1);
                if($('#' + mdate).val() && (newDate - Date.ofDate($('#' + mdate).val())>0)){
                    getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
                }else{
                    getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
                }
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "06" && $('#' + vdate).val()){
				var newDate = Date.ofDate($('#' + vdate).val()).DateAdd("m",6);
                if($('#' + mdate).val() && (newDate - Date.ofDate($('#' + mdate).val())>0)){
                    getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
                }else{
                    getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
                }
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "07" && $('#' + mdate).val()){
				getElementById('firstPayDate').val($('#' + mdate).val()).trigger('change').prop('disabled', false);
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
			}else{
				getElementById('firstPayDate').trigger('change').prop('disabled', false);
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);

			}
			}
    	});
        // 父画面收益类型联动事件
        profitTypeChange();
        $('#' + profitType).change(profitTypeChange);
        //产品处理模式联动事件
        dealModeChange();
        $('#' + dealModeElement).change(dealModeChange);
        //期限联动事件
        periodChange();
        $('#' + periodElement).change(periodChange);
    });
    
}());