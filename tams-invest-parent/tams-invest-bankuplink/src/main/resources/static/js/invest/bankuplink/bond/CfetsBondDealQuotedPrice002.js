(function(){

	/**
	 * 是否可编辑
	 */
	var editFlag = true;
	//这个标志是防止查询结果返回赋值后不停的联动calcAmt;
	var entrustIdChangeFlag = false;
    /**
	 * 委托代码
	 */
    var entrustIdChangeAction = function(){
    	var entrustId = $('#CfetsBondDealQuotedPrice002_entrustId').val();
    	if (entrustId && entrustId != ''){
    		Joyin.ajax(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/queryEntrustInfoByEntrustId', {entrustId : entrustId}, function(result) {
            	if (result.data){
            		entrustIdChangeFlag = true;
                    $('#CfetsBondDealQuotedPrice002_remark').val(result.data.remark);
            		$('#CfetsBondDealQuotedPrice002_hidden_entrustAmt').val(result.data.entrustNum);
            		$('#CfetsBondDealQuotedPrice002_hidden_finprodId').val(result.data.finprodId);
            		// 区分画面打开模式
            		var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
            		
            		if (result.data.calculateField){
            			if (result.data.calculateField === calcTypeUnitCprice){
            				$("input[name=calculateField][value=01]").prop("checked","true");
            				$('#calculateField_span_unitCprice').css({'color':'blue'});
            				$('#calculateField_span_yield').css({'color':'black'});
            				$('#calculateField_span_exerYield').css({'color':'black'});
            			}else if (result.data.calculateField === calcTypeYield){
            				$("input[name=calculateField][value=02]").prop("checked","true");
            				$('#calculateField_span_yield').css({'color':'blue'});
            				$('#calculateField_span_unitCprice').css({'color':'black'});
            				$('#calculateField_span_exerYield').css({'color':'black'});
            			}else if (result.data.calculateField === calcTypeExecYield){
            				$("input[name=calculateField][value=03]").prop("checked","true");
            				$('#calculateField_span_exerYield').css({'color':'blue'});
            				$('#calculateField_span_unitCprice').css({'color':'black'});
            				$('#calculateField_span_yield').css({'color':'black'});
            			}
            			layui.form.render();
            		}
            		
            		// 回复对话报价
            		if (quotaPageModel && (quotaPageModel == 'dialogueResponse')){
            			$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
            		}
            		else {
            			// 做市方回复  
            			if (quotaPageModel != 'marketResponse'){
            				// 这些要素应该是做市方已有的信息
            				$('#CfetsBondDealQuotedPrice002_mySide').textSelect('setValue',result.data.mySide);
            				$('#CfetsBondDealQuotedPrice002_counterId').textSelect('setValue',result.data.counterId);
                			$('#CfetsBondDealQuotedPrice002_counterTrader').textSelect('setValue',result.data.counterTrader);
                			$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true).textSelect('setValue',result.data.finprodMarketId);
            				$('#CfetsBondDealQuotedPrice002_ps').val(result.data.ps).trigger('change').attr('disabled', true);
            				$('#CfetsBondDealQuotedPrice002_settleSpreed').val(result.data.settleSpreed).trigger('change').attr('disabled', true);
                			$('#CfetsBondDealQuotedPrice002_settleDate').val(result.data.settleDateStr).attr('disabled', true);
                			$('#CfetsBondDealQuotedPrice002_avaNum').val(result.data.avaNumStr).trigger('blur');
            			}else{
            			    // 做市报价回复选择委托后回复量默认显示委托剩余额度
            			    $('#CfetsBondDealQuotedPrice002_responseAmt').val(result.data.avaNumStr).trigger('blur');
            			}
            			// 对话报价发起模式
            			$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true).textSelect('setValue',result.data.portfolioId);
                        $('#CfetsBondDealQuotedPrice002_mySide').textSelect('setValue',result.data.mySide);
                		$('#CfetsBondDealQuotedPrice002_mySideTrader').textSelect('setValue',result.data.mySideTrader);
            			$('#CfetsBondDealQuotedPrice002_settleMethod').val(result.data.settleMethod).trigger('change');
            			$('#CfetsBondDealQuotedPrice002_settleType').val(result.data.settleType).trigger('change');
            			$('#CfetsBondDealQuotedPrice002_div_settleCcy').val(result.data.settleCcy).trigger('change');
            			$('#CfetsBondDealQuotedPrice002_unitCprice').val(result.data.price).trigger('blur');
            			$('#CfetsBondDealQuotedPrice002_matureYield').val(result.data.matureYield).trigger('blur');
            			$('#CfetsBondDealQuotedPrice002_exerYield').val(result.data.exerYield).trigger('blur');
            			$('#CfetsBondDealQuotedPrice002_unitInt').val(result.data.unitInt).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_unitFprice').val(result.data.unitFprice).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_parValue').val(result.data.parValue).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_intAmt').val(result.data.intAmt).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_parValueAmt').val(result.data.parValueAmt).trigger('blur');
        				
        	    		$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
        	    		$('#CfetsBondDealQuotedPrice002_ps').attr('disabled', true);
        	    		$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true);
        	    		$('#CfetsBondDealQuotedPrice002_settleSpreed').attr('disabled', true);
        	    		//add by lijie2 
        	    		if(result.data && result.data.priceModel == PRICE_MODEL_PRM01){
        	    			$('#CfetsBondDealQuotedPrice002_unitCprice').attr('disabled', true);
        	    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
        	    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
        	    			$("input[name='calculateField']").attr('disabled', true);
        	    			$("#CfetsBondDealQuotedPrice002_hidden_calculateField").val("01");
        	    			$("input[name=calculateField][value=01]").prop("checked","true");
        	    			layui.form.render();
        	    		}else if(result.data && result.data.assetTypeOne == assetTypeOne_abs){
        	    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
        	    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
        	    			$("input[name='calculateField']").attr('disabled', true);
        	    			$("#CfetsBondDealQuotedPrice002_hidden_calculateField").val("01");
        	    			$("input[name=calculateField][value=01]").prop("checked","true");
        	    			layui.form.render();
        	    		}else{
        	    			$('#CfetsBondDealQuotedPrice002_unitCprice').attr('disabled', false);
        	    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', false);
        	    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', false);
        	    			$("input[name='calculateField']").attr('disabled', false);
        	    			$("#CfetsBondDealQuotedPrice002_hidden_calculateField").val(result.data.calculateField);
        	    			layui.form.render();
        	    		}
            		}
            		entrustIdChangeFlag = false;
            	}
            });
    	}
    	else {
            $('#CfetsBondDealQuotedPrice002_remark').val("");
            var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
            //add by yzg 20210116 委托代码清空联动清空 start
            if(!quotaPageModel || (quotaPageModel != 'dialogueResponse')){
	            $('#CfetsBondDealQuotedPrice002_hidden_entrustAmt').val('');
	    		$('#CfetsBondDealQuotedPrice002_hidden_finprodId').val('');
	    		$('#CfetsBondDealQuotedPrice002_mySide').val('').trigger('change');
				$('#CfetsBondDealQuotedPrice002_counterId').val('').trigger('change');
				$('#CfetsBondDealQuotedPrice002_counterTrader').textSelect('setValue','');
				$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', false).val('').trigger('change');
				$('#CfetsBondDealQuotedPrice002_ps').val('').trigger('change').attr('disabled', false);
				$('#CfetsBondDealQuotedPrice002_settleSpreed').val('01').trigger('change').attr('disabled', false);
				$('#CfetsBondDealQuotedPrice002_settleDate').val('');
				$('#CfetsBondDealQuotedPrice002_avaNum').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_responseAmt').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true).val('').trigger('change');
	    		$('#CfetsBondDealQuotedPrice002_mySideTrader').val('');
				$('#CfetsBondDealQuotedPrice002_settleMethod').val('03').trigger('change');
				$('#CfetsBondDealQuotedPrice002_settleType').val('01').trigger('change');
				$('#CfetsBondDealQuotedPrice002_div_settleCcy').val('CNY').trigger('change');
				$('#CfetsBondDealQuotedPrice002_unitCprice').val('').trigger('blur').attr('disabled', false);;
				$('#CfetsBondDealQuotedPrice002_matureYield').val('').trigger('blur').attr('disabled', false);;
				$('#CfetsBondDealQuotedPrice002_exerYield').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_unitInt').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_unitFprice').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_cpriceAmt').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_parValue').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_intAmt').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_fpriceAmt').val('').trigger('blur');
				$('#CfetsBondDealQuotedPrice002_parValueAmt').val('').trigger('blur');
            }else if(quotaPageModel == 'dialogueResponse'){
            	$('#CfetsBondDealQuotedPrice002_portfolioName').val('').trigger('change');
            }
            //add by yzg 20210116 委托代码清空联动清空 end
            if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
    			$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', false);
    		}
            $('#CfetsBondDealQuotedPrice002_settleDate').val(platDate);
    	}
    };
    /**
     * 债券简称
     */
    var finprodNameChangeAction = function(){
    	var data = $('#CfetsBondDealQuotedPrice002_finprodName').select2('data')[0].object;
    	if (data){
    		$('#CfetsBondDealQuotedPrice002_finprodMarketId').val(data.finprodMarketId);
    		$("#CfetsBondDealQuotedPrice002_hidden_finprodId").val(data.finprodId);
    		if (data.isRight == yesNoFlag_yes){
    			$('#CfetsBondDealQuotedPrice002_div_exerYield').toggle(true);
    		}
    		else{
    			$('#CfetsBondDealQuotedPrice002_div_exerYield').toggle(false);
    		}
    		if (data.assetType1 == assetTypeOne_abs){
    			$('#CfetsBondDealQuotedPrice002_div_abs1').toggle(true);
    			$('#CfetsBondDealQuotedPrice002_div_abs2').toggle(true);
    			
    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
    			$("input[name='calculateField']").attr('disabled', true);
    			$("#CfetsBondDealQuotedPrice002_hidden_calculateField").val("01");
    			$("input[name=calculateField][value=01]").prop("checked","true");
    			layui.form.render();
    			
    		}else{
    			$('#CfetsBondDealQuotedPrice002_div_abs1').toggle(false);
    			$('#CfetsBondDealQuotedPrice002_div_abs2').toggle(false);
    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', false);
    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', false);
    			$("input[name='calculateField']").attr('disabled', false);
    		}
    		$('#CfetsBondDealQuotedPrice002_settleCcy').val(data.ccy).trigger("change");
    	}else{
    		$('#CfetsBondDealQuotedPrice002_finprodMarketId').val('');
    		$("#CfetsBondDealQuotedPrice002_hidden_finprodId").val('');
    	}
    	var calcField = $("[name=calculateField]:checked").val();
    	if(calcField){
    		calcAmt(calcField);
    	}
    };
    /**
	 * 组合名称
	 */
    var portfolioNameChangeAction = function(){
    	var portfolioId = $('#CfetsBondDealQuotedPrice002_portfolioName').val();
		$('#CfetsBondDealQuotedPrice002_portfolioId').val(portfolioId);
		var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
		if(quotaPageModel != 'dialogueResponse'){
			$('#CfetsBondDealQuotedPrice002_mySide').val('').trigger('change');
		}
    };
    /**
     * 清算速度
     */
    var settleSpreedChangeAction = function(){
    	var settleSpreed = $('#CfetsBondDealQuotedPrice002_settleSpreed').val();
    	if (settleSpreed == ACCOUNT_SPEED_1 || settleSpreed == ACCOUNT_SPEED_2){
            Joyin.ajax(basePath + 'invest/bankuplink/common/CfetsQuotedCommon/getNextWorkDay', {
                'tradeDate': platDate,
                'settleSpreed': settleSpreed
            }, function (date) {
                $("#CfetsBondDealQuotedPrice002_settleDate").val(date).trigger("change");
                var calcField = $("[name=calculateField]:checked").val();
            	if(calcField){
            		calcAmt(calcField);
            	}
            });
    	}else{
    		$('#CfetsBondDealQuotedPrice002_settleDate').val(platDate);
    		var calcField = $("[name=calculateField]:checked").val();
        	if(calcField){
        		calcAmt(calcField);
        	}
    	}
    };
    /**
     * 保存
     */
    var saveOrSubmitAction = function(operatorType) {
        var object = $.serializeToObject('#submitForm', true);
        if(!object.calculateField){
        	object.calculateField = $("#CfetsBondDealQuotedPrice002_hidden_calculateField").val();
        }
        if(!object.finprodId){
        	object.finprodId = $("#CfetsBondDealQuotedPrice002_hidden_finprodId").val();
        }
        object.operatorType = operatorType;
        Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/check', object,
        function(result) {
            if (result.data) {
                Joyin.confirm(result.data,
                function() {
                	//保存 走流程
                    if (operatorType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/save', object, "CfetsBondDealQuotedPrice002_btn_save", null,
                        function() {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                    } 
                    //成交 走流程
                    else if (operatorType === 'done') {
                        WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/save', object, "CfetsBondDealQuotedPrice002_btn_done", null,
                                function() {
                                    var index = window.parent.layer.getFrameIndex(window.name);
                                    window.parent.layer.close(index);
                        })
                     }
                    //提交 走流程
                    else if (operatorType === 'submit') {
                        WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/save', object, "CfetsBondDealQuotedPrice002_btn_submit", null,
                        function() {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                    }
                    //拒绝 不走流程
                    else if (operatorType === 'refuse'){
                    	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/saveData', object, function(result){
                    		var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                    	})
                    }
                })
            }
        });
    };
    /**
     * 计算金额相关
     */
    var calcAmt = function(calculateField){
    	if(entrustIdChangeFlag){
    		return;
    	}
    	var calcField = $("[name=calculateField]:checked").val();
    	var finprodId = $('#CfetsBondDealQuotedPrice002_hidden_finprodId').val();
    	if(!finprodId){
    		return;
    	}
    	if (calculateField && calculateField != ''){
    		if (calculateField != calcField){
    			 //$("input[name=calculateField" + "][value='"+ calcField +"']").attr("checked",true);
                 //layui.form.render(); //更新;
    			return;
    		}
    	}
    	
    	if (calcField){
    		if (calcField === calcTypeUnitCprice && !$('#CfetsBondDealQuotedPrice002_unitCprice').val()){
    			return;
    		}
    		if (calcField === calcTypeYield && !$('#CfetsBondDealQuotedPrice002_matureYield').val()){
    			return;
    		}
    		if (calcField === calcTypeExecYield && !$('#CfetsBondDealQuotedPrice002_exerYield').val()){
    			return;
    		}
    		
    		
    		var object = $.serializeToObject('#submitForm', true);
        	object.calculateField = calcField;
        	object.finprodId = finprodId;
        	// 区分画面打开模式
    		var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
    		// 做市报价回复
    		if (quotaPageModel && quotaPageModel == 'marketResponse' ){
    			if (!object.responseAmt){
    	    		return;
    	    	}
    			object.avaNum = new BigNumber(object.responseAmt).multipliedBy(new BigNumber(10000));
    		}else{
    			if (!object.avaNum){
    	    		return;
    	    	}
        		object.avaNum = new BigNumber(object.avaNum).multipliedBy(new BigNumber(10000))
    		}
        	
        	Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/calcAmt', object, function(result){
        		if (result.data){
        			entrustIdChangeFlag = true;
        			if (calculateField == calcTypeUnitCprice){
        				if(pageModel != PAGE_MODEL_REFERENCE){
        					$('#CfetsBondDealQuotedPrice002_matureYield').val(result.data.matureYield).trigger('blur');
        				}
        	    		$('#CfetsBondDealQuotedPrice002_exerYield').val(result.data.exerYield).trigger('blur');
        			}else if (calculateField == calcTypeYield){
        				$('#CfetsBondDealQuotedPrice002_unitCprice').val(result.data.price).trigger('blur');
        				$('#CfetsBondDealQuotedPrice002_exerYield').val(result.data.exerYield).trigger('blur');
        			}else if (calculateField == calcTypeExecYield){
        				$('#CfetsBondDealQuotedPrice002_unitCprice').val(result.data.price).trigger('blur');
        				if(pageModel != PAGE_MODEL_REFERENCE){
        					$('#CfetsBondDealQuotedPrice002_matureYield').val(result.data.matureYield).trigger('blur');
        				}
        			}else{
        				$('#CfetsBondDealQuotedPrice002_unitCprice').val(result.data.price).trigger('blur');
        				if(pageModel != PAGE_MODEL_REFERENCE){
        					$('#CfetsBondDealQuotedPrice002_matureYield').val(result.data.matureYield).trigger('blur');
        				}
        	    		$('#CfetsBondDealQuotedPrice002_exerYield').val(result.data.exerYield).trigger('blur');
        			}
        			$('#CfetsBondDealQuotedPrice002_unitInt').val(result.data.unitInt).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_unitFprice').val(result.data.unitFprice).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_cpriceAmt').val(result.data.cpriceAmt).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_parValue').val(result.data.parValue).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_intAmt').val(result.data.intAmt).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_fpriceAmt').val(result.data.fpriceAmt).trigger('blur');
    				$('#CfetsBondDealQuotedPrice002_parValueAmt').val(result.data.parValueAmt).trigger('blur');
    				entrustIdChangeFlag = false;
        		}
        	})
    	}
    };
    
    /**
     * 接收子页面返回的委托信息
     */
    var saveEntrustSelectData = function(entrustInfo){
    	if (entrustInfo){
    		$('#CfetsBondDealQuotedPrice002_entrustId').textSelect('setValue',entrustInfo.entrustId);
    	}
    }
    /**
     * 画面初始化
     */
    var initPage = function(){
   	    calcAmt();
   	    var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
   	    if(quotaPageModel && quotaPageModel == 'dialogueResponse'){
   	    	//$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
   	    	$('#calculateField_exerYield').attr('disabled', true);
   	    	$('#calculateField_yield').attr('disabled', true);
   	    }
        if(workflowId || (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) && !(quotaPageModel && (quotaPageModel == 'dialogueResponse'))){//流程中、修改、查看
        	$('#CfetsBondDealQuotedPrice002_entrustId_search').css('opacity',0.4);
		}
        
        /**
    	 * 券面总额
    	 */
    	$('#CfetsBondDealQuotedPrice002_avaNum').on('change',function(){
    		calcAmt();
        });
    	/**
    	 * 回复量
    	 */
    	$('#CfetsBondDealQuotedPrice002_responseAmt').on('change',function(){
    		calcAmt();
        });
    	/**
    	 * 单位净价
    	 */
    	$('#CfetsBondDealQuotedPrice002_unitCprice').on('change',function(){
    		calcAmt('01');
        });
    	/**
    	 * 到期收益率
    	 */
    	$('#CfetsBondDealQuotedPrice002_matureYield').on('change',function(){
    		calcAmt('02');
        });
    	/**
    	 * 行权收益率
    	 */
    	$('#CfetsBondDealQuotedPrice002_exerYield').on('change',function(){
    		calcAmt('03');
        });
   }
    
    $(function () {
    	// 初始化页面
        initPage();
    	// 委托代码
    	var entrustId = $('#CfetsBondDealQuotedPrice002_hidden_entrustId').val();
    	if (entrustId && entrustId != ''){
    		$('#CfetsBondDealQuotedPrice002_entrustId').textSelect('setValue',entrustId);
    		$('#CfetsBondDealQuotedPrice002_entrustId').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_settleSpreed').attr('disabled', true);
    		//add by lijie2 
    		if(PRICE_MODEL && PRICE_MODEL == PRICE_MODEL_PRM01){
    			$('#CfetsBondDealQuotedPrice002_unitCprice').attr('disabled', true);
    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
    			$("input[name='calculateField']").attr('disabled', true);
    			$("input[name=calculateField][value=01]").prop("checked","true");
    			layui.form.render();
    		}else if(assetTypeOne == assetTypeOne_abs){
    			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
    			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
    			$("input[name='calculateField']").attr('disabled', true);
    			$("#CfetsBondDealQuotedPrice002_hidden_calculateField").val("01");
    			$("input[name=calculateField][value=01]").prop("checked","true");
    			layui.form.render();
    		}
    		editFlag = false;
    	}
    	// 画面操作模式
    	var quotaPageModel = $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val();
    	// 做市报价回复
    	if (quotaPageModel && quotaPageModel == 'marketResponse'){
    		$('#CfetsBondDealQuotedPrice002_div_responseAmt').toggle(true);
    		$('#CfetsBondDealQuotedPrice002_div_settleCcy').toggle(false);
    		if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
    			$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
    		}
    		$('#CfetsBondDealQuotedPrice002_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_mySide').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_counterId').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_counterTrader').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_finprodMarketId').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleSpreed').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleDate').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_avaNum').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleMethod').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleType').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_btn_done').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_refuse').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_save').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_submit').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_cancel').toggle(true);
    	}
    	// 对话报价回复(除委托代码外置灰)
    	else if (quotaPageModel && quotaPageModel == 'dialogueResponse'){
    	    $('#CfetsBondDealQuotedPrice002_div_settleCcy').toggle(true);
    		$('#CfetsBondDealQuotedPrice002_btn_done').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_refuse').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_save').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_submit').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_cancel').toggle(false);
			if (ibuFlag_startDialogueQuoteWithNoEntrust != '02'){
    			$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
    		}
			$('#CfetsBondDealQuotedPrice002_mySideTrader').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_mySide').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_counterId').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_counterTrader').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleSpreed').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_unitCprice').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_matureYield').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_exerYield').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_avaNum').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleMethod').attr('disabled', true);
			$('#CfetsBondDealQuotedPrice002_settleType').attr('disabled', true);
			//无指令时，组合可改
			if (ibuFlag_startDialogueQuoteWithNoEntrust == '02'){
				var mySideTemp = $('#CfetsBondDealQuotedPrice002_mySide').val();
				if(mySideTemp){
					$('#CfetsBondDealQuotedPrice002_responseMySide').val(mySideTemp);
				}
			}
    	}
    	// 对话报价发起和保存后双击
    	else if (quotaPageModel && quotaPageModel == 'new'){
    		$('#CfetsBondDealQuotedPrice002_div_responseAmt').toggle(false);
    		$('#CfetsBondDealQuotedPrice002_div_settleCcy').toggle(true);
    		if (mySideTrader && mySideTrader != ''){
        		$('#CfetsBondDealQuotedPrice002_mySideTrader').textSelect('setValue',mySideTrader);
        	}
    		if (counterTrader && counterTrader != ''){
        		$('#CfetsBondDealQuotedPrice002_counterTrader').textSelect('setValue',counterTrader);
        	}
        	if (ouTraderIdCanEditFlag === 'N'){
        		$('#CfetsBondDealQuotedPrice002_mySideTrader').attr('disabled', true);
        	}
        	$('#CfetsBondDealQuotedPrice002_btn_done').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_refuse').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_save').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_submit').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_cancel').toggle(true);
    	}// 对话报价发起和保存后双击
    	else if (quotaPageModel && quotaPageModel == 'edit'){
    		$('#CfetsBondDealQuotedPrice002_div_settleCcy').toggle(true);
    		$('#CfetsBondDealQuotedPrice002_mySide').attr('disabled', true);
        	$('#CfetsBondDealQuotedPrice002_mySideTrader').attr('disabled', true);
        	$('#CfetsBondDealQuotedPrice002_counterId').attr('disabled', true);
        	$('#CfetsBondDealQuotedPrice002_counterTrader').attr('disabled', true);
        	$('#CfetsBondDealQuotedPrice002_settleType').attr('disabled', false);
        	$('#CfetsBondDealQuotedPrice002_remark').attr('disabled', true);
        	//修改无委托初始化增加不可修改项 add by yzg 20210123 start
        	$('#CfetsBondDealQuotedPrice002_entrustId').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_portfolioName').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_ps').attr('disabled', true);
    		$('#CfetsBondDealQuotedPrice002_finprodName').attr('disabled', true);
        	//修改无委托初始化增加不可修改项 add by yzg 20210123 end
        	$('#CfetsBondDealQuotedPrice002_btn_done').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_refuse').toggle(false);
			$('#CfetsBondDealQuotedPrice002_btn_save').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_submit').toggle(true);
			$('#CfetsBondDealQuotedPrice002_btn_cancel').toggle(true);
        	
    	}
    	// 含权、资产一级类型
    	var isRight = $('#CfetsBondDealQuotedPrice002_hidden_isRight').val();
        if (isRight == yesNoFlag_yes){
            $('#CfetsBondDealQuotedPrice002_div_exerYield').toggle(true);
        }
        else{
            $('#CfetsBondDealQuotedPrice002_div_exerYield').toggle(false);
        }
        var assetType1 = $('#CfetsBondDealQuotedPrice002_hidden_assetTypeOne').val();
        if (assetType1 == assetTypeOne_abs){
            $('#CfetsBondDealQuotedPrice002_div_abs1').toggle(true);
            $('#CfetsBondDealQuotedPrice002_div_abs2').toggle(true);
        }else{
            $('#CfetsBondDealQuotedPrice002_div_abs1').toggle(false);
            $('#CfetsBondDealQuotedPrice002_div_abs2').toggle(false);
        }
    	
    	/**
    	 * 委托代码
    	 */
    	$('#CfetsBondDealQuotedPrice002_entrustId').change(function(){
    		entrustIdChangeAction();
        });
    	/**
    	 * 委托代码放大镜
    	 */
    	$('#CfetsBondDealQuotedPrice002_entrustId_search').click(function(){
    		if(workflowId || (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) && !(quotaPageModel && (quotaPageModel == 'dialogueResponse'))){//流程中、修改、查看
    			return;
    		}
    		Joyin.dialog(JoyinUtil.getLabel("CfetsBondDealQuotedPrice006"), 
        		basePath + "invest/bankuplink/bond/CfetsBondDealQuotedPrice006/init", "99%", "80%", {
                    pageModel : PAGE_MODEL_NEW, finprodId : $('#CfetsBondDealQuotedPrice002_finprodMarketId').val()
                    , ps : $('#CfetsBondDealQuotedPrice002_ps').val(), entrustId : $('#CfetsBondDealQuotedPrice002_entrustId').val(), editFlag : editFlag
                    , mySide : $('#CfetsBondDealQuotedPrice002_mySide').val(), quotaPageModel : $('#CfetsBondDealQuotedPrice002_hidden_quotaPageModel').val()
                    , settleSpreed : $('#CfetsBondDealQuotedPrice002_settleSpreed').val()
                })
    	})
    	/**
    	 * 资产简称
    	 */
    	$('#CfetsBondDealQuotedPrice002_finprodName').change(function(){
    		finprodNameChangeAction();
        });
    	/**
    	 * 组合名称
    	 */
    	$('#CfetsBondDealQuotedPrice002_portfolioName').change(function(){
    		portfolioNameChangeAction();
        });
    	/**
    	 * 清算速度
    	 */
    	$('#CfetsBondDealQuotedPrice002_settleSpreed').change(function(){
    		settleSpreedChangeAction();
        });
    	/**
    	 * 券面总额
    	 */
    	$('#CfetsBondDealQuotedPrice002_avaNum').blur(function(){
    		calcAmt();
        });
    	/**
    	 * 券面总额
    	 */
        $("#CfetsBondDealQuotedPrice002_avaNum").change(function() {
        	calcAmt();
        });
    	/**
    	 * 回复量
    	 */
    	$('#CfetsBondDealQuotedPrice002_responseAmt').blur(function(){
    		calcAmt();
        });
    	/**
    	 * 单位净价
    	 */
    	$('#CfetsBondDealQuotedPrice002_unitCprice').blur(function(){
    		calcAmt('01');
        });
    	/**
    	 * 到期收益率
    	 */
    	$('#CfetsBondDealQuotedPrice002_matureYield').blur(function(){
    		calcAmt('02');
        });
    	/**
    	 * 行权收益率
    	 */
    	$('#CfetsBondDealQuotedPrice002_exerYield').blur(function(){
    		calcAmt('03');
        });
    	
    	var calculateFieldChange = function(init) {
            var calculateField = $("[name=calculateField" + "]:radio").next(".layui-form-radioed").prev("input").val();
            calcAmt(calculateField);
    	}
    	
    	 layui.use(['form'], function() {
             // 绑定计算模式的点击事件
             var form = layui.form;
             form.on('radio(calculateField)', function(data) {
                 layui.form.render(); //更新
                 calculateFieldChange();
             });
         });
    	
    	/**
    	 * 保存按钮按下处理
    	 */
    	$('#CfetsBondDealQuotedPrice002_btn_save').click(function(){
    		saveOrSubmitAction('save');
    	});
    	
    	/**
    	 * 提交按钮按下处理
    	 */
    	$('#CfetsBondDealQuotedPrice002_btn_submit').click(function(){
    		saveOrSubmitAction('submit');
    	});
    	
    	/**
    	 * 成交
    	 */
    	$('#CfetsBondDealQuotedPrice002_btn_done').click(function(){
    		saveOrSubmitAction('done');
    	});
    	
    	/**
    	 * 拒绝
    	 */
    	$('#CfetsBondDealQuotedPrice002_btn_refuse').click(function(){
    		saveOrSubmitAction('refuse');
    	});
        /**
         * 本方
         */
        $('#CfetsBondDealQuotedPrice002_mySide').change(function(){
            mySideChangeAction();
        });
        /**
         * 对手方方
         */
        $('#CfetsBondDealQuotedPrice002_counterId').change(function(){
            counterIdChangeAction();
        });
    });

    /**
     * 本方
     */
    var mySideChangeAction = function(){
        /*$('#CfetsBondDealQuotedPrice002_mySideTrader').val('').trigger('change');
        // add by zhangliang 20200630 start
        var mySideTemp = $('#CfetsBondDealQuotedPrice002_mySide').val();
        if (!mySideInit || mySideTemp == mySideInit){
        	$('#CfetsBondDealQuotedPrice002_mySideTrader').val(mySideTraderInit).trigger('change');
        }*/
        $('#CfetsBondDealQuotedPrice002_mySideTrader').val('').trigger('change');
    }
    /**
     * 对手方
     */
    var counterIdChangeAction = function(){
        $('#CfetsBondDealQuotedPrice002_counterTrader').val('').trigger('change');
    }
    window.CfetsBondDealQuotedPrice002 = {
            saveEntrustSelectData: saveEntrustSelectData
        };
}());