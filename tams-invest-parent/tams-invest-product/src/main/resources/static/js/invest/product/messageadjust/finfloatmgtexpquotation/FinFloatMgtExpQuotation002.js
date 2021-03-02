/**
 * wangkang
 * 信息调整管理_浮动管理费报价新建
 */
(function() {
	//现金流代码
	var cashId;
	//业绩基准代码
//	var baseRuleId;
	//基准利率
	var intRate;
		
	var flag = true;
	var isFc = false;//是否为分层产品
	var isCycle = false;//是否周期型
	/**
	 * 保存提交事件
	 * @param operateType
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var wfl_operType = $("#wfl_operType").val();
		var workflowId = $('#workflowId').val();
		var finprodId = $('#finprodId').val() ;
		var selectedItem1 = table.getAllRows();
    	/*if(selectedItem1 == null || selectedItem1.length == 0){
    		Joyin.error(JoyinUtil.getMessage("业绩基准表不能为空！"));
            return;
    	}*/
    	$.each(selectedItem1 ,function(index,lev){
    		selectedItem1[index].lev=index+1;
//    		selectedItem1[index].intRate=intRate;
    	});
        var object = $.serializeToObject('#FinFloatMgtExpQuotation002_form');	
		object.baseRule='01';
        if($('#finprodId').val() != null && $('#finprodId').val() != ''){
        	object.finprodMarketId = $('#finprodId').select2('data')[0].object;
        	object.vdate = $('#finprodId').select2('data')[0].col;
        }
        if($('#effDateOld').val() != null && $('#effDateOld').val() != ''){
        	object.effDateOld =Date.ofDate($('#effDateOld').val()).format('yyyy-MM-dd');
        }
        if(cashId != null && cashId != ''){
        	object.cashId = cashId;
        }
        if($('#cashId').val() != null && $('#cashId').val() != ''){
        	object.cashId = $('#cashId').val();
    	}
        if($("#fAccrDate").is(":disabled")){
        	object.checkFlag = false;
        }
        else{
        	object.checkFlag = true;
        }
		var bussBenchMarkInfoDto = {};
		bussBenchMarkInfoDto.baseRuleId = object.baseRuleId;
		bussBenchMarkInfoDto.rateType = object.rateType;
		bussBenchMarkInfoDto.rate = object.rate;
		bussBenchMarkInfoDto.indexId = object.indexId;
		bussBenchMarkInfoDto.coefficient = object.coefficient;
		bussBenchMarkInfoDto.spreadRate = object.spreadRate;
		bussBenchMarkInfoDto.resetType = object.resetType;
		object.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.selectedItem1=selectedItem1;
        //若是子产品则保存子产品的产品代码
//        if(object.prodStyle != null && object.prodStyle != ''){
//        	object.pFinprodId = object.finprodId;
//        	object.finprodId = object.prodStyle;
//        }
        if(isFc){//如果是分层产品，则将父产品代码给pFinprodId，款型代码复制给finprodId
        	object.pFinprodId = object.finprodId;
        	object.finprodId = object.prodStyle;
        }
        if(isCycle){
        	object.pFinprodId = object.finprodId;
        	object.finprodId = object.investmentCycle;
        }
        if(pageModel == PAGE_MODEL_NEW){
        	if(object.finprodId != null && object.finprodId != '' && object.fAccrDate != null && object.fAccrDate != '' && (cashId == null || cashId == '')){
            	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/checkIsHoliday', object, function(result) {
                    if(result == true){
                    	Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_E_0039",JoyinUtil.getLabel("FinFloatMgtExpQuotation002.fAccrDate")+object.fAccrDate), function() {
                    		//ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
                            Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getFeeinfos', {finprodId:finprodId,workflowId:workflowId}, function(result) {
                            	if(result != ""){
                            		Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenMode', object, function(result) {
                            			if(result != '02'){
                            		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/checkeffDate', object, function(result) {
                            			if(result == false){
                            				Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenType', object, function(result) {
                                    			if(result == '02'){
                            				       Joyin.error(JoyinUtil.getMessage("浮动管理费计提为算尾不算头，请选择开放计划开放开始日+1天作为生效日！"));
                            	                    return;
                                    			}else{
                                    				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                                        Joyin.confirm(result.data, function() {
                                    				if(operateType === 'save') {
                                                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                                    }
                                                    else {
                                                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                                    }
                                                   });                                          
                                    			 });
                                    		 }
                            				 });
                            			}else{
                            				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                                Joyin.confirm(result.data, function() {
                            				if(operateType === 'save') {
                                                WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                            }
                                            else {
                                                WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                            }
                                           });                                          
                            			 });
                            			}
                            		 });
                            		}else{
                        				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                            Joyin.confirm(result.data, function() {
                        				if(operateType === 'save') {
                                            WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                        }
                                        else {
                                            WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                        }
                                       });                                          
                        			 });
                        			}
                            	 });
                            	}else{
                            		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                        Joyin.confirm(result.data, function() {
                    				if(operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                    }
                                    else {
                                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                    }
                                   });                                          
                    			 });
                            	}
                            	return;
                            });
                                /*if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                }*/
                                //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
                    	},
                        function(){
                        	return false;
                        })
                    }
                    else{                    	
                            	//ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
                                Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getFeeinfos', {finprodId:finprodId,workflowId:workflowId}, function(result) {
                                	if(result != ""){
                                		Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenMode', object, function(result) {
                                			if(result != '02'){
                                		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/checkeffDate', object, function(result) {
                                			if(result == false){
                                				Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenType', object, function(result) {
                                        			if(result == '02'){
                                				       Joyin.error(JoyinUtil.getMessage("浮动管理费计提为算尾不算头，请选择开放计划开放开始日+1天作为生效日！"));
                                	                    return;
                                        			}else{
                                        				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                                            Joyin.confirm(result.data, function() {
                                        				if(operateType === 'save') {
                                                            WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                                        }
                                                        else {
                                                            WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                                        }
                                                       });                                          
                                        			 });
                                        		 }
                                				 });
                                			}else{
                                				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                                    Joyin.confirm(result.data, function() {
                                				if(operateType === 'save') {
                                                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                                }
                                                else {
                                                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                                }
                                               });
                                			 });
                                			}
                                		 });
                                			}else{
                                				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                                    Joyin.confirm(result.data, function() {
                                				if(operateType === 'save') {
                                                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                                }
                                                else {
                                                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                                }
                                               });
                                			 });
                                			}
                                		});
                                	}else{
                                		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                            Joyin.confirm(result.data, function() {
                        				if(operateType === 'save') {
                                            WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                        }
                                        else {
                                            WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                        }
                                       });                                          
                        			 });
                                	}
                                });
                                    /*if(operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                    }
                                    else {
                                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                    }*/
                                    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
                        
                    }
                })
            }
            else{
            	//ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
                Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getFeeinfos', {finprodId:finprodId,workflowId:workflowId}, function(result) {
                	if(result != ""){
                		Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenMode', object, function(result) {
                			if(result != '02'){
                		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/checkeffDate', object, function(result) {
                			if(result == false){
                				Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getopenType', object, function(result) {
                        			if(result == '02'){
                				       Joyin.error(JoyinUtil.getMessage("浮动管理费计提为算尾不算头，请选择开放计划开放开始日+1天作为生效日！"));
                	                    return;
                        			}else{
                        				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                            Joyin.confirm(result.data, function() {
                        				if(operateType === 'save') {
                                            WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                        }
                                        else {
                                            WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                        }
                                       });                                          
                        			 });
                        		 }
                				 });
                			}else{
                				Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                    Joyin.confirm(result.data, function() {
                				if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                }
                               });
                			 });
                			}
                		 });
                		}else{
                			Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                Joyin.confirm(result.data, function() {
            				if(operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                            }
                            else {
                                WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                            }
                           });
            			 });
                		}
                		});
                	}else{
                		Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                            Joyin.confirm(result.data, function() {
        				if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                        }
                       });                                          
        			 });
                	}
                });
                    /*if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                    }*/
                    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
                   
                
            }
        }
        else if(pageModel == PAGE_MODEL_EDIT && (wfl_operType == null || wfl_operType == 'NEW') && !$("#fAccrDate").is(":disabled")){
    		//if(object.finprodId != null && object.finprodId != '' && object.fAccrDate != null && object.fAccrDate != '' && (cashId == null || cashId == '')){
            	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/checkIsHoliday', object, function(result) {
                    if(result == true){
                    	Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_E_0039",JoyinUtil.getLabel("FinFloatMgtExpQuotation002.fAccrDate")+object.fAccrDate), function() {
                        	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                                Joyin.confirm(result.data, function() {
                                    if(operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                    }
                                    else {
                                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                    }
                                });
                                return;
                            });
                    	},
                        function(){
                        	return false;
                        })
                    }
                    else{
                    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                            Joyin.confirm(result.data, function() {
                                if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                                }
                            });
                        });
                    }
                })
    		//}
    	}
        else{
        	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/save', object, "FinFloatMgtExpQuotation002_btn_submit");
                    }
                });
            });
        }
        
//        object.baseRuleId=table.getRow(0).baseRuleId;
    };
	var editable = pageModel == PAGE_MODEL_NEW;
	var finprodIdDtx=null;
	var getQueryParams = function(){
		var finprodId = $('#finprodId').val();
		if (finprodIdDtx!=null && finprodIdDtx!='') {
			finprodId=finprodIdDtx;
		}

		if($('#prodStyle').val()){//支持分层
			finprodId = $('#prodStyle').val();
		}
		else if($('#investmentCycle').val()){
			finprodId = $('#investmentCycle').val();
		}else{
			finprodId = $('#finprodId').val();
		}
		return {
			finprodId: finprodId,
    		workflowId : $('#workflowId').val(),
			effDate: $('#effDate').val(),
			lev: $('#lev').val(),
			flag:flag,
			pageModel:pageModel
    	};
	}
	
	var loadTable = function(){
		Joyin.ajax(JoyinUtil.getRealPath('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getRuleDetail'),
				getQueryParams(), function(rows) {
	        	table.table ? table.reload() : table.load();
	        	table.clear(function() {
	        		table.addRow(rows)
	                 })
	             })
	}
    /**
     * 页面加载初始化
     */
	var tempRowNum = 0;
	var table = $("#dataTable").joyintable({
//			ajax: {
//				url: JoyinUtil.getRealPath('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getRuleDetail'),
//				type: 'POST',
//				data: function() {
//					return {
//						finprodId: $('#finprodId').val(),
//                		workflowId : $('#workflowId').val(),
//						effDate: $('#effDate').val(),
//						lev: $('#lev').val(),
//						flag:flag,
//						pageModel:pageModel
//                	};
//				}		
//			},
        checkbox: false,
        addbtn: true,
        loadonce:false,
        searchOnInit: true,
//        addclick:function(index){
//        	return{  }
//        },
        cols:[
            {field:'lev', title: 'FinFloatMgtExpQuotation002.lev', sortable:false,width:300
            	, render : function(data, type, row, meta) { 
            		if(row.lev == null || row.lev == ''){
            			return (row.LAY_TABLE_INDEX+1);
            		}
        		 } 
            }
            ,{field:'baseRuleStr', title: 'FinFloatMgtExpQuotation002.baseRule' ,width:300,render:function(){ return '业绩基准'}, sortable:false}
//            ,{field:'intRate', title: 'FinFloatMgtExpQuotation002.intRate',edit:true , render : function(data, type, row, meta) { intRate=table.getRow(0).intRate; return intRate}}
            ,{field:'coefficient', title: 'FinFloatMgtExpQuotation002.coefficient',width:180, type:'number', edit:true , sortable:false}
            ,{field:'spreadRate', title: 'FinFloatMgtExpQuotation002.spreadRate' ,width:180,  edit:true , sortable:false}
            ,{field:'chargeRatio', title: 'FinFloatMgtExpQuotation002.chargeRatio',width:180, type:'number', edit:true , sortable:false}
            ,{field:'cashId', visible:false}
            ,{field:'baseRuleId', visible:false}
            ,{field:'operation', width:105,title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
        ,done: function(table1, allData){
        	
        	var wfl_operType = $("#wfl_operType").val();
         	if(pageModel == PAGE_MODEL_EDIT){
         		//update by gcl 2019/3/26 start
         		/*if(wfl_operType == null || wfl_operType != 'NEW'){
	         		for(var i=0; i<$('#dataTable').next().find('.btn,.delt').length; i++){
	         			$($('#dataTable').next().find('.btn,.delt')[i]).attr('style',"display:none");
	         		}
         		}*/
         		//update by gcl 2019/3/26 end
         	}
         	flag = false;
         	//新增列设置系数 = 1
     		if(table1.length > tempRowNum){
     			table.renderRow(table1.length-1, {coefficient: 1,spreadRate:0});
     		}
     		tempRowNum = table1.length;
        }
	   });
    
	/*
     * 绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
//    	if(pageModel == PAGE_MODEL_NEW){
    		table.removeRow(data);
//    	}
    });
   
    //联动文本框
    function changeInput(result){
    	if( result.feeId !=null && result.feeId !='' ){
    		$('#feeName').textSelect('setValue',result.feeId);
    		//$('#feeName').val(result.feeName);
    	}else{
    		$("#feeName").val('').trigger("change");
    	}
    	
    	$("#basicBill").val(result.basicBill).trigger("change");
    	$("#basic").val(result.basis).trigger("change");
//    	$("#astVdateType").val(result.astVdateType).trigger("change");
    	//$("#baseRule").val(result.baseRule).trigger("change");
    	$("#fAccrDate").val(result.fAccrDateStr);
        
        $("#eAccrDate").val(result.eAccrDateStr);
//        $("#accrType").val(result.accrType).trigger("change");
//        $("#intervalRule").val(result.intervalRule).trigger("change");
//        $("#accrTdayRule").val(result.accrTdayRule).trigger("change");
//        $("#fAccrEndDate").val(result.fAccrEndDateStr);
        $("#payCycle").val(result.payCycle).trigger("change");
        $("#firstPayDate").val(result.firstPayDateStr);
        $("#endTdayRule").val(result.endTdayRule).trigger("change");
        $("#payTdayRule").val(result.payTdayRule).trigger("change");
    }
    
    //ZGBSS-42 浮动管理费报价调整控制部分字段不能修改 add by yujiaping 20210116 start
    function changeDisabled1(status){
    	$("#feeName").attr("disabled",status);    	
    	$("#basicBill").attr("disabled",status);
    	$("#basic").attr("disabled",status);
    	$("#fAccrDate").attr("disabled",status);        
        $("#eAccrDate").attr("disabled",status);       
        $("#payCycle").attr("disabled",status);
        $("#firstPayDate").attr("disabled",status);
        $("#endTdayRule").attr("disabled",status);
        $("#payTdayRule").attr("disabled",status);       
    }
    //ZGBSS-42 浮动管理费报价调整控制部分字段不能修改 add by yujiaping 20210116 end
    //文本框编辑状态
    function changeDisabled(status){
    	//$("#feeName").attr("disabled",status);
    	//$("#effDate").attr("disabled",status);
    	/*$("#basicBill").attr("disabled",status);
    	$("#basic").attr("disabled",status);*/
//    	$("#astVdateType").attr("disabled",status);
    	//$("#baseRule").attr("disabled",status);
    	//$("#fAccrDate").attr("disabled",status);
        
        //$("#eAccrDate").attr("disabled",status);
//        $("#accrType").attr("disabled",status);
//        $("#intervalRule").attr("disabled",status);
//        $("#fAccrEndDate").attr("disabled",status);
//        $("#accrTdayRule").attr("disabled",status);
        //$("#payCycle").attr("disabled",status);
        $("#firstPayDate").attr("disabled",status);
        $("#endTdayRule").attr("disabled",status);
        $("#payTdayRule").attr("disabled",status);
        
        $("#rateType").attr("disabled",status);
        $("#rate").attr("disabled",status);
//        $("#indexId").attr("disabled",status);
//        $("#coefficient").attr("disabled",status);
//        $("#spreadRate").attr("disabled",status);
//        $("#resetType").attr("disabled",status);
    }
    var init =function(){
    	 $("#prodStyle").val('').parent().parent().hide();
    	 $("#investmentCycle").val('').parent().parent().hide();
    	 //新需求去除资产净值日，计提方式，区间规则，计提营业日准则，首个计提区间结束日
//    	 $("#astVdateType").val('').parent().parent().hide();
//    	 $("#accrType").val('').parent().parent().hide();
//    	 $("#intervalRule").val('').parent().parent().hide();
//    	 $("#fAccrEndDate").val('').parent().parent().hide();
//    	 $("#accrTdayRule").val('').parent().parent().hide();
    	loadTable();
    	var wfl_operType = $("#wfl_operType").val();
    	//修改时，产品代码和生效日和层级不可修改
    	if(pageModel == PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
	    		$("#finprodId").attr("disabled",true);
	    		$("#prodStyle").attr("disabled",true);//支持分层
	    		//$("#relaeseDate").attr("disabled",true);
	    		//$("#effDate").attr("disabled",true);
	    		$("#investmentCycle").attr("disabled",true);
	    		changeDisabled(true);
    		}
    	}
    	//新建保存到代办，防止数据出不来 --增加防止删除不出数据的
    	if(wfl_operType == null || wfl_operType == 'NEW' || wfl_operType == 'DELETE'){
    		finprodId=$('#finprodId').val();
    		finprodIdChange(finprodId);
    	}

		//update by dgf 需求变更,修改页面不允许修改 20200116
		if(pageModel==PAGE_MODEL_EDIT){
			$('#finprodId').attr('disabled',true);
			$('#effDate').attr('disabled',true);
			$('#feeName').attr('disabled',true);
			$('#fAccrDate').attr('disabled',true);
			$('#eAccrDate').attr('disabled',true);
			$('#payCycle').attr('disabled',true);
		}
    }
    
    //费用名称
    $('#feeName').change(function(){
    	$("#basicBill").val($('#feeName').select2('data')[0].object).trigger("change");
    	$("#basic").val($('#feeName').select2('data')[0].col).trigger("change");
    })
   //付费规则联动
    $('#payCycle').change(function(){
    	if($('#payCycle').val() === FREQUENCY_UNIT_08){
            $("#firstPayDate").val('').attr("disabled",true);;
            $("#endTdayRule").val('').trigger("change").attr("disabled",true);;
            $("#payTdayRule").val('').trigger("change").attr("disabled",true);;
    	}
    	else{
            $("#firstPayDate").attr("disabled",false);
            $("#endTdayRule").attr("disabled",false);
            $("#payTdayRule").attr("disabled",false);
    	}
    })
	//产品代码联动
    function finprodIdChange(finprodId){
        $("#finprodAbbr").val($('#finprodId').select2('data')[0].text.split(":")[1]).trigger("change");
    	if(finprodId!=null && finprodId!='' ){
    		//ZGBUGTWO-172 产品浮动管理费新建，流程审批时，要素全部不见了 add by lizhi 20200713 start
    		$("#pFinprodId").val(finprodId);
    		//ZGBUGTWO-172 产品浮动管理费新建，流程审批时，要素全部不见了 add by lizhi 20200713 end
    		var str = $('#finprodId').select2('data')[0].object.split(',');
            if(str != null && str.length==2){
        		$("#dealMode").val(str[1]);
            }
    	    Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/queryLayerProdInfoByFinprodId', {finprodId:finprodId}, function(result) {
//    		Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/queryLayerProdStyleList', {finprodMarketId:finprodId}, function(result) {
    		  var dealMode = $("#dealMode").val();
  	          $("#prodStyle option").remove();
  	          $("#investmentCycle option").remove();
  	          if(result != null && result.length > 0){
  	        	if(dealMode == DEAL_MODE_11){
  	        	    $("#investmentCycle").val('').parent().parent().hide(); 
    	            isCycle = false;
  	  	            $("#prodStyle").append("<option></option>");
  	        		 for(var i in result){
  	    	            	$("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
  	    	            }
  	    	            $("#prodStyle").val(PROD_STYLE).parent().parent().show();
  	    	            isFc = true;
  	        	}
  	        	else if(dealMode == DEAL_MODE_10){
  	        		$("#prodStyle").val('').parent().parent().hide();
  	        		isFc = false;
  	  	            $("#investmentCycle").append("<option></option>");
  	        		for(var i in result){
	    	            	$("#investmentCycle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
	    	            	
	    	            }
	    	            $("#investmentCycle").val(INVESTMENT_CYCLE).parent().parent().show();
	    	            isCycle = true;
  	        	}
  	          }else{
  	            $("#prodStyle").val('').parent().parent().hide();
  	            $("#investmentCycle").val('').parent().parent().hide();
  	            isFc = false;
  	            isCycle = false;
  	          }
//    			if(result != null && result.length > 0){
//    				//$("#prodStyle").attr("data-result",JSON.stringify(result));
//    	            $("#prodStyle option").remove();
//    	            $("#prodStyle").append("<option></option>");
//    	            for(var i in result){
//    	            	$("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
//    	            }
//    	            $("#prodStyle").val(PROD_STYLE).parent().parent().show();
//    	            isFc = true;
//    			}else{
//    				$("#prodStyle").val('').parent().parent().hide();
//    				isFc = false;
//    			}
    			//支持分层，以分层产品的id作为finprodId
    			if($("#prodStyle").val()){
    				finprodId = $("#prodStyle").val();
    				finprodIdDtx = $("#prodStyle").val();
    			}
    			else if($("#investmentCycle").val()){
    				finprodId = $("#investmentCycle").val();
    				finprodIdDtx = $("#investmentCycle").val();
    			}
    			//ajax查询超额收益分层
				ajaxGetFeeinfos(finprodId);
				//查询业绩基准
				markInfo(finprodId);
    		});
		}else{//产品代码为空
    		//ZGBUGTWO-172 产品浮动管理费新建，流程审批时，要素全部不见了 add by lizhi 20200713 start
			$("#pFinprodId").val('');
    		//ZGBUGTWO-172 产品浮动管理费新建，流程审批时，要素全部不见了 add by lizhi 20200713 end
			changeInput('');//让页面字段置空
			changeDisabled(false);//不让页面字段置灰
			cashId='';
			loadTable();
		}
    	effDate=$('#effDate').val();
		if(finprodId != null && finprodId != '' && effDate != null && effDate != ''){
//			table.addRow(table.reload() );
			if(pageModel == PAGE_MODEL_NEW){
				loadTable();
			}
		}
    };
    /**
     * ajax查询超额收益分层
     */
    function  ajaxGetFeeinfos(finprodId){
    	var workflowId = $('#workflowId').val();
    	Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getFeeinfos', {finprodId:finprodId,workflowId:workflowId}, function(result) {
			//判断有无浮动管理费
			if(null == result.feeId || ''==result.feeId){//无浮动管理费
				if(pageModel == PAGE_MODEL_NEW){
					changeInput('');//让页面字段置空
					changeDisabled1(false);
				}
//				changeDisabled(true);//不让页面字段置灰
    			cashId='';
    			loadTable();
			}else{//有浮动管理费
				cashId=result.cashId;
				if (pageModel==PAGE_MODEL_NEW) {
				changeInput(result);//给页面字段赋值
				//ZGBSS-42 浮动管理费报价调整控制部分字段不能修改 add by yujiaping 20210116 start
				changeDisabled1(true);//非首次新建浮动管理费报价时，计提信息、支付信息带出首次信息，置灰不可改
				//ZGBSS-42 浮动管理费报价调整控制部分字段不能修改 add by yujiaping 20210116 end
					 }
//				changeDisabled(false);//让页面字段置灰，不可修改，只能修改超额收益分层。
				loadTable();
			}
        });
    }
    //产品款型改变
    function prodStyleChange(finprodId){
    	if(finprodId!=null && finprodId!='' ){
    		ajaxGetFeeinfos(finprodId);
    		markInfo(finprodId);
    	}else{//产品代码为空
    		changeInput('');//让页面字段置空
    		changeDisabled(false);//不让页面字段置灰
    		cashId='';
    		loadTable();
    	}
    	effDate=$('#effDate').val();
    	if(finprodId != null && finprodId != '' && effDate != null && effDate != ''){
    		if(pageModel == PAGE_MODEL_NEW){
    			loadTable();
    		}
    	}
    };
    //投资周期变更
    function investmentCycleChange(finprodId){
    	if(finprodId!=null && finprodId!='' ){
    		$("#investmentCycleText").val($("#investmentCycle  option:checked").text());
    		
    		ajaxGetFeeinfos(finprodId);
    		markInfo(finprodId);
    	}else{//产品代码为空
    		changeInput('');//让页面字段置空
    		changeDisabled(false);//不让页面字段置灰
    		cashId='';
    		loadTable();
    	}
    	effDate=$('#effDate').val();
    	if(finprodId != null && finprodId != '' && effDate != null && effDate != ''){
    		if(pageModel == PAGE_MODEL_NEW){
    			loadTable();
    		}
    	}
    };
    /**
     * 计提方式change
     * 计提方式为区间时，区间规则、首个计提区间结束日可填且必填
     * 计提方式为每日时，区间规则、首个计提区间结束日置灰
     */
//    var handleAccrType = function(){
//        var accrType = $('#accrType').val();
//        //当计提方式改为区间时，画面.区间规则改为必填。否则，画面.区间规则清空，且改为只读。01:每日；02：区间
//        if(accrType=="02"){
//        	$('#intervalRule').removeAttr('disabled');
//        	$('#fAccrEndDate').removeAttr('disabled');
//        }else{
//        	$('#intervalRule').val('');
//        	$('#fAccrEndDate').val('');
//        	$('#intervalRule').attr('disabled',true).trigger("change");
//        	$('#fAccrEndDate').attr('disabled',true).trigger("change");
//        }
//    }
 // 利率类型改变时触发的事件
    $('#rateType').change(function() {
        if( $('#rateType').val() === '') {
			//update by dgf 20200116 需求变更,修改时候不允许修改这部分
			if(pageModel==PAGE_MODEL_EDIT){
				$('#rate').trigger('change').prop('disabled', true);
			}else{
				$('#rate').trigger('change').prop('disabled', false);
			}
        	// $('#rate').val('').trigger('change').prop('disabled', false);
        	$('#indexId').val('').trigger('change').prop('disabled', false);
        	$('#coefficient').val('').trigger('change').prop('disabled', false);
        	$('#spreadRate').val('').trigger('change').prop('disabled', false);
        	$('#resetType').val('').trigger('change').prop('disabled', false);
        } else if( $('#rateType').val() === INT_TYPE_01) {
			//update by dgf 20200116 需求变更,修改时候不允许修改这部分
			if(pageModel==PAGE_MODEL_EDIT){
				$('#rate').trigger('change').prop('disabled', true);
			}else{
				$('#rate').trigger('change').prop('disabled', false);
			}
        	// $('#rate').trigger('change').prop('disabled', false);
        	$('#indexId').val('').trigger('change').prop('disabled', true);
        	$('#coefficient').val('').trigger('change').prop('disabled', true);
        	$('#spreadRate').val('').trigger('change').prop('disabled', true);
//            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则不要置灰
        	$('#resetType').val('').trigger('change').prop('disabled', false);
        } else {
        	$('#rate').val('').trigger('change').prop('disabled', true);
        	$('#indexId').trigger('change').prop('disabled', false);
        	$('#coefficient').val('1.00').trigger('change').prop('disabled', false);
        	$('#spreadRate').trigger('change').prop('disabled', false);
        	$('#resetType').val('').trigger('change').prop('disabled', false);
        }
    });
	/**
	 * 新建选中产品代码联动业绩基准信息
	 */
	var markInfo=function(finprodId){
		if($('#wfl_operType').val()){
			return;
		}
        Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/getMarkInfo', {finprodId:finprodId,effDate:$('#effDate').val(),pageModel:pageModel}, function(result) {
        	if (result!=null && result!=''){
			$('#rateType').textSelect('setValue',result.rateType);
			$('#rate').val(result.rate);
			$('#indexId').val(result.indexId);$('#coefficient').val(result.coefficient);
			$('#spreadRate').val(result.spreadRate);$('#resetType').textSelect('setValue',result.resetType);

		}
		 })
	}
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
    	//计提方式联动
//    	$('#accrType').change(handleAccrType).trigger("change");
    	var finprodId=$('#finprodId').val();
    	var effDate=$('#effDate').val();
    	//产品代码联动
    	$('#finprodId').change(function(){
    		finprodId=$('#finprodId').val();
    		finprodIdChange(finprodId);
    	});
    	//产品款型改变事件
    	$('#prodStyle').change(function(){
    		var prodStyle=$('#prodStyle').val();
    		prodStyleChange(prodStyle);
    	});
    	//产品款型改变事件
    	$('#investmentCycle').change(function(){
    		var investmentCycle=$('#investmentCycle').val();
    		investmentCycleChange(investmentCycle);
    	});
    	//日期联动利差等
    	/*$('#effDate').change(function(){
    		effDate=$('#effDate').val();
			if(finprodId != null && finprodId != '' && effDate != null && effDate != ''){
//				table.addRow(table.reload() );
				if(pageModel == PAGE_MODEL_NEW){
    				table.reload();
    			}
			}
			var wfl_operType = $("#wfl_operType").val();
			if(wfl_operType == 'NEW'){
				table.reload();
			}
		});*/
    	//利率类型联动
    	$('#resetType').attr("disabled","disabled");
    	$('#rateType').change(function(){
    		if($('#rateType').val() == '02'){
    			$('#resetType').removeAttr("disabled");
    		}
    		else{
    			$('#resetType').attr("disabled","disabled");
    		}
    	});
        // 绑定按钮事件
    	$('#FinFloatMgtExpQuotation002_btn_save').click(function(){
    		saveOrSubmit('save');
    	});
    	//
    	$('#FinFloatMgtExpQuotation002_btn_submit').click(function(){
    		saveOrSubmit('submit');
    	});
        
    });
}());