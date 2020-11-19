 /* 分档产品浮动管理费共通JS
 * @auth 孟宏伟
 */
(function(){
	
	// 快速录入标志
	var quickFlag = false;
	
    /**
     * 获取画面元素
     */
	function getElementById(id) {
		return $('#'+ id);
	}
	  var loadfloatManageFeeTable = function() {
		floatManageFeeTable.load();
		floatManageFeeTable.clear(function() {
			if (finFloatCalcRuleDetailDtoList) {
				for ( var i in finFloatCalcRuleDetailDtoList) {
					floatManageFeeTable.addRow(finFloatCalcRuleDetailDtoList[i]);
				}
			}
		})
	    }

    /**
     * 定义数据表格
     *
     */
    var floatManageFeeTable=getElementById('floatManageFee').joyintable({
        height : 200,
        checkbox:false,
        addbtn:true,
        addclick : function(rowData) {
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
            ,{field:'operation',width:200, title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    

    
    /**
     * 绑定删除事件
     */
    floatManageFeeTable.bind('click', '.delt', function(data, index) {
    	floatManageFeeTable.deleteRow(index);
    });
    
    function feeNameChangeAction(){
    	var feeId = getElementById('feeId').val();
    	if (feeId && feeId != ''){
			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFeeCommon/queryMstFeeinfoByFeeId'), {feeId : feeId}, function(result){
				if (result){
					getElementById('basicBill').val(result.basicBill).trigger('change');
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
			
			
			if(!getElementById('eAccrDate').val() && !getElementById('provisionFirstDay').val()){
				//add by yejiejie 
				var object = JSON.parse(prdinfo);
				if(object){
					if(object.operationType == "01"){
						
						getElementById('provisionFirstDay').val(DateUtil.parse(mdate).DateAdd('d', -1).Format('yyyy-MM-dd'));
						getElementById('eAccrDate').val(getElementById('provisionFirstDay').val()).attr('disabled', true);	;
					}else if(object.operationType == "02") {
						var setFlag = true;
						if(object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.openMode == "02"){
							//初始化首次计提
							getElementById('provisionFirstDay').val(vdate);
							//初始化计提结束日
							getElementById('eAccrDate').val(mdate);
							setFlag = false;
						}
	                    if(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.openMode == "02"){
	                    	//初始化首次计提
							getElementById('provisionFirstDay').val(vdate);
							//初始化计提结束日
							getElementById('eAccrDate').val(mdate);
							setFlag = false;
	                    }
	                    var profitType = object.profitType;
	                    var firstdate;
	                    var enddate;
						if(object.bussFinProductOpenPlanDto && object.bussFinProductOpenPlanDto.finOpenPlanList && setFlag){
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
	                    if(object.bussFinProductOpenPlanRedDto && object.bussFinProductOpenPlanRedDto.finOpenPlanList && setFlag ){
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
	    					getElementById('provisionFirstDay').val(vdate);
	                    }
	                    if(enddate){
	                    	//初始化计提结束日
	    					getElementById('eAccrDate').val(Date.ofDate(enddate).Format('yyyy-MM-dd'));
	                    }else if(setFlag){
	                    	//初始化计提结束日
	    					getElementById('eAccrDate').val(mdate);
	                    }
				    }else{
				    	//add by yejiejie 
						//初始化计提结束日
						getElementById('eAccrDate').val(mdate);
						//初始化首次计提日
						getElementById('provisionFirstDay').val(vdate);
						//add by yejiejie 
				    }
				}else{
					//add by yejiejie 
					//初始化计提结束日
					getElementById('eAccrDate').val(mdate);
					//初始化首次计提日
					getElementById('provisionFirstDay').val(vdate);
					//add by yejiejie 
				}
			}
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
    /*	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFee/queryFloatFeeInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(floatFeeEntityName, data);
    	})
    	floatFeeFinprodId = input_finprodId;
    	quickFlag = true;
    	if($("#"+vdate).val())effDate=$("#"+vdate).val();
    	floatManageFeeTable.table ? floatManageFeeTable.reload() : floatManageFeeTable.load();*/
    }
    
    var rateTypeChange = function () {
    	if(getElementById('rateType').val() === '') {
            getElementById('rate').val('').trigger('change').prop('disabled', false);
            getElementById('indexId').val('').trigger('change').prop('disabled', false);
            getElementById('coefficient').val('').trigger('change').prop('disabled', false);
            getElementById('spreadRate').val('').trigger('change').prop('disabled', false);
            getElementById('resetType').val('').trigger('change').prop('disabled', false);
        } else if(getElementById('rateType').val() === INT_TYPE_01) {
            getElementById('rate').trigger('change').prop('disabled', false);
            getElementById('indexId').val('').trigger('change').prop('disabled', true);
            getElementById('coefficient').val('').trigger('change').prop('disabled', true);
            getElementById('spreadRate').val('').trigger('change').prop('disabled', true);
//            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则置灰 yzg 20191115 start
            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则置灰 yzg 20191115 end
        } else {
            getElementById('rate').val('').trigger('change').prop('disabled', true);
            getElementById('indexId').trigger('change').prop('disabled', false);
            getElementById('coefficient').val('1.00').trigger('change').prop('disabled', false);
            getElementById('spreadRate').trigger('change').prop('disabled', false);
            getElementById('resetType').val('').trigger('change').prop('disabled', false);
        }
    }
    /**
	 * 初始化加载tableFlag 
	 */
	var initFlag = false;
	
	var getSearchParams = function(){
	}
	/**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function forsure(operateType) {
    	/**
    	 * 获取列表参数
    	 */
        var object = $.serializeToObject('#submitForm', true);
/*      object.operateType = operateType;
        object.profitType = profitType;// 收益类型*/
        object.pageId = pageId;
		object.bussFinProductFloatFeeInfoDtoList = JSON.parse(feeList);// 费用名称在费用列表中已存在时
		object.dealMode=dealMode;/ 产品处理模式*/
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/check', object, function(result) {
        	Joyin.confirm(result.data, function() {
        			if (object){
        				top.returnData = object;
        				var index = parent.layer.getFrameIndex(window.name);
        				parent.layer.close(index);
        				return;
        			}
        	});
        });
    }
    $(function() {
    	// 计提方式改变时触发的事件
       /*
    	getElementById('accrType').change(function() {
             if(getElementById('accrType').val() =="01") {
            	getElementById('interValRule').val('').trigger('change').prop('disabled', true);
            }else{
            	getElementById('interValRule').trigger('change').prop('disabled', false);
            }
        });
        */
    	//产品模板不展示日期 yzg 20200203 start
    	if(pageId == 'FinProductModule002'){
    		$("#firstPayDate_div").toggle(false);
    		$("#provisionFirstDay_div").toggle(false);
    		$("#eAccrDate_div").toggle(false);
    	}else{
    		$("#firstPayDate_div").toggle(true);
    		$("#provisionFirstDay_div").toggle(true);
    		$("#eAccrDate_div").toggle(true);
    	}
    	//产品模板不展示日期 yzg 20200203 end
    	if(dealMode === DISPOSE_PATTERN_10){
    		getElementById('prodStyle').parent().parent().hide();
    	    getElementById('investmentCycle').parent().parent().show();
    	}
    	else if(dealMode === DISPOSE_PATTERN_11){
    		getElementById('prodStyle').parent().parent().show();
    	    getElementById('investmentCycle').parent().parent().hide();
    	}
    	 getElementById('endTdayRule').change(function() {
             if(getElementById('endTdayRule').val() =="02") {
             	getElementById('payTdayRule').val('02').trigger('change').prop('disabled', true);
             }else{
             	getElementById('payTdayRule').val('').trigger('change').prop('disabled', false);
             }
         });
    	getElementById('feeId').change(function(){
    		feeNameChangeAction();
    	});
    	getElementById('rateType').change(function() {
    		rateTypeChange();
        });
    	getElementById('payCycle').change(function(){
    		var payCycle = getElementById('payCycle').val();
    		if(payCycle == "08"){
    			getElementById('firstPayDate').val("").trigger('change').prop('disabled', true);
    			getElementById('endTdayRule').val("").trigger('change').prop('disabled', true);
    			getElementById('payTdayRule').val("").trigger('change').prop('disabled', true);
    		}else if(payCycle == "03" && vdate){
    			if(!getElementById('firstPayDate').val()){
    				var newDate = Date.ofDate(vdate).DateAdd("m",1);
    				if(mdate && (newDate - Date.ofDate(mdate)>0)){
    					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
    				}else{
    					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
    				}
    			}
    			
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);

    		}else if(payCycle == "04" &&  vdate){
    			if(!getElementById('firstPayDate').val()){
    				var newDate = Date.ofDate(vdate).DateAdd("m",3);
    				if(mdate && (newDate - Date.ofDate(mdate)>0)){
    					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
    				}else{
    					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
    				}
    			}
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "05" &&  vdate){
				if(!getElementById('firstPayDate').val()){
    				var newDate = Date.ofDate(vdate).DateAdd("y",1);
    				if(mdate && (newDate - Date.ofDate(mdate)>0)){
    					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
    				}else{
    					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
    				}
    			}
				getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "06" &&  vdate){
				if(!getElementById('firstPayDate').val()){
    				var newDate = Date.ofDate(vdate).DateAdd("m",6);
    				if(mdate && (newDate - Date.ofDate(mdate)>0)){
    					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
    				}else{
    					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
    				}
    			}
				getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
				
			}else if(payCycle == "07" && mdate){
				if(!getElementById('firstPayDate').val())
				getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);
			}else{
				getElementById('firstPayDate').trigger('change').prop('disabled', false);
    			getElementById('endTdayRule').trigger('change').prop('disabled', false);
    			getElementById('payTdayRule').trigger('change').prop('disabled', false);

			}
    	});
    	getElementById('provisionFirstDay').change(function() {
			var provisionFirstDay = getElementById('provisionFirstDay').val();
			var object = JSON.parse(prdinfo);
			var mdate1 = mdate;
			var vdate1 = vdate;
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
			if(object.operationType == "01" && provisionFirstDay){
				var days = (Date.ofDate(mdate1) - Date.ofDate(provisionFirstDay))/(24*60*60*1000);
				if(days>1 || days<0){
					getElementById('provisionFirstDay').val(mdate1);
					Joyin.error("封闭式产品 浮动管理费  首次计提日 只能 产品到期日 或 产品到期日-1");
				}
				getElementById('eAccrDate').val(getElementById('provisionFirstDay').val()).attr('disabled', true);
			}
		});
//        getElementById('eAccrDate').change(function() {
//			var eAccrDate = getElementById('eAccrDate').val();
//			var object = JSON.parse(prdinfo);
//			var mdate1 = mdate;
//			var vdate1 = vdate;
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
        $('#MstFeeVarieties002_btn_save').click(forsure);
        loadfloatManageFeeTable();    	
        $("#netAssetValueDaydiv").hide();
		$("#interValRulediv").hide();
		$("#hidediv5").hide();
		$("#accrTypediv").hide();
		if(getElementById('rateType').val() === '') {
            getElementById('rate').val('').trigger('change').prop('disabled', false);
            getElementById('indexId').val('').trigger('change').prop('disabled', false);
            getElementById('coefficient').val('').trigger('change').prop('disabled', false);
            getElementById('spreadRate').val('').trigger('change').prop('disabled', false);
            getElementById('resetType').val('').trigger('change').prop('disabled', false);
        } else if(getElementById('rateType').val() === INT_TYPE_01) {
            getElementById('rate').trigger('change').prop('disabled', false);
            getElementById('indexId').val('').trigger('change').prop('disabled', true);
            getElementById('coefficient').val('').trigger('change').prop('disabled', true);
            getElementById('spreadRate').val('').trigger('change').prop('disabled', true);
//            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则置灰 yzg 20191115 start
            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则置灰 yzg 20191115 end
        } else {
            getElementById('rate').val('').trigger('change').prop('disabled', true);
            getElementById('indexId').trigger('change').prop('disabled', false);
            getElementById('coefficient').val('1.00').trigger('change').prop('disabled', false);
            getElementById('spreadRate').trigger('change').prop('disabled', false);
            getElementById('resetType').val('').trigger('change').prop('disabled', false);
        }
		var payCycle = getElementById('payCycle').val();
		if(payCycle == "08"){
			getElementById('firstPayDate').val("").trigger('change').prop('disabled', true);
			getElementById('endTdayRule').val("").trigger('change').prop('disabled', true);
			getElementById('payTdayRule').val("").trigger('change').prop('disabled', true);
		}else if(payCycle == "03" && vdate){
			if(!getElementById('firstPayDate').val()){
				var newDate = Date.ofDate(vdate).DateAdd("m",1);
				if(mdate && (newDate - Date.ofDate(mdate)>0)){
					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
				}else{
					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
				}
			}

			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);

		}else if(payCycle == "04" &&  vdate){
			if(!getElementById('firstPayDate').val()){
				var newDate = Date.ofDate(vdate).DateAdd("m",3);
				if(mdate && (newDate - Date.ofDate(mdate)>0)){
					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
				}else{
					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
				}
			}
			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);

		}else if(payCycle == "05" &&  vdate){
			if(!getElementById('firstPayDate').val()){
				var newDate = Date.ofDate(vdate).DateAdd("y",1);
				if(mdate && (newDate - Date.ofDate(mdate)>0)){
					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
				}else{
					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
				}
			}
			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);

		}else if(payCycle == "06" &&  vdate){
			if(!getElementById('firstPayDate').val()){
				var newDate = Date.ofDate(vdate).DateAdd("m",6);
				if(mdate && (newDate - Date.ofDate(mdate)>0)){
					getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
				}else{
					getElementById('firstPayDate').val(newDate.Format('yyyy-MM-dd')).trigger('change').prop('disabled', false);
				}
			}
			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);

		}else if(payCycle == "07" && mdate){
			if(!getElementById('firstPayDate').val())
				getElementById('firstPayDate').val(mdate).trigger('change').prop('disabled', false);
			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);
		}else{
			getElementById('firstPayDate').trigger('change').prop('disabled', false);
			getElementById('endTdayRule').trigger('change').prop('disabled', false);
			getElementById('payTdayRule').trigger('change').prop('disabled', false);

		}
    });
    
    /*
    $(document).ready(function() {
    	getElementById('rateType').val('333').trigger('change');
    })*/
    
}());