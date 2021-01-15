/**
 * FinNetProductBonusScheme004 净值型现金分红
 */
(function() {    

    //分红方案联动
    var bonuScalcFlagChange = function (isInit) {
        var bonuScalcFlag = $('#bonuScalcFlag').val();
        if(bonuScalcFlag == bonuScalcFlagUnit){
            $(".unitBonusVlu").show();
            $(".totalAmt").hide();
        }
        else{
            $(".totalAmt").show();
            $(".unitBonusVlu").hide();
        }
    };

    //产品款型 下拉枚举值联动初始化
    var prodStyleSelectInit = function(){
    	//产品代码
    	var finprodId = $('#finprodId').val(); 
//    	$('#prodStyle').empty();
    	Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/getProdStyleList', 
        	{'finprodId':finprodId}, 
        	function(result) {
        		if(result != null && result.length > 0){
    	            $("#prodStyle option").remove();
    	            $('#prodStyle').append("<option value=''>请选择...</option>");
            		$(result).each(function (i, n) {
                    	$('#prodStyle').append("<option value='"+n.finprodId+"'>"+n.prodType+"</option>");
                    });
            		$('#prodStyle').val(PROD_STYLE).parent().parent().show();
        		} else {
    				$("#prodStyle").val('').parent().parent().hide();
        		}
        	}
        );
    };
    
    
   var cycleDataTable=$("#cycleDataTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/findCycleDataTableInfo'),
            type: 'POST'
            ,data: function() {
                return {finprodId : $("#finprodId").val()
                	,vluRefDate: $("#vluRefDate").val()
                	,actTotalAmt : $("#actTotalAmt").val()
                	,regDateNum : $("#regDateNum").val()
                	,dealType : $("#dealType").val()
                	,tradeId :  $("#tradeId").val()
                	,workflowId : workflowId
                };
            }
        },
        page:false,
        rowno:true,
        checkbox:false,
        cols:[
            {field:'finprodId',visible :false}
            ,{field:'branch',visible :false}
            ,{field:'periodId',title: 'FinNetProductDuration.cycleId', width:170,edit:false}
            ,{field:'subCycleStartDate',title: 'FinNetProductDuration.cycleStartDate', width:170,edit:false,type:'date'}
            ,{field:'subCycleEndDate',title: 'FinNetProductDuration.cycleEndDate', width:170,edit:false,type:'date'}
            ,{field:'bonusCashAmt', title: 'FinNetProductDuration.bonusAmt', width:170,edit:true,type:'number',textrange:2}            
        ]
        ,editCell: function(value, row, field, cell, column){
        	var bonusMode = $("#bonusMode").val();
        	if(field == 'bonusCashAmt' && bonusMode == '01') {
        		totalCycleBonusCashAmt();	
        	}
        	return true;
        }
    });
   
    var totalCycleBonusCashAmt = function(){
    	if(cycleDataTable) {
    		var cycleDataTableList = cycleDataTable.getAllRows();
    		var totalCycleBonusCashAmt = 0;
    		if(cycleDataTableList) {
    			for ( var i = 0; i <cycleDataTableList.length; i++){
   		   		if(cycleDataTableList[i].bonusCashAmt==""||cycleDataTableList[i].bonusCashAmt==null){
   		   			cycleDataTableList[i].bonusCashAmt = 0;
   		   		}
   		   	totalCycleBonusCashAmt = JoyinMath.doAdd(totalCycleBonusCashAmt, cycleDataTableList[i].bonusCashAmt, 2, JoyinMath.ROUND_HALF_UP);//现金分红金额累计
    		}
    	 }
    		$("#bonusCashAmt").val(totalCycleBonusCashAmt);
       }	
    }
    
    var bonusCashAmtValid = function(){
    	//分红金额默认值=总分红金额-已分红总额
    	var actTotalAmt = $("#actTotalAmt").val();
    	var totalBonusCashAmt = $("#totalBonusCashAmt").val();
    	var bonusCashAmt = $("#bonusCashAmt").val();
    	if(!totalBonusCashAmt){
    		totalBonusCashAmt = 0;
    	}
    	if(!bonusCashAmt){
    		bonusCashAmt = 0;
    	}
    	var maxAmt = Number(JoyinMath.doMinus(actTotalAmt,totalBonusCashAmt));
    	if(Number(bonusCashAmt)>maxAmt){
    		JoyinUtil.showMessage("该分红方案的可分红总金额为"+actTotalAmt+"，已分红"+totalBonusCashAmt+"，本次分红金额大于剩余可分红金额"+maxAmt+"!","error");
    		$("#bonusCashAmt").val(maxAmt);
    	}
    	reloadSettle();
    };
    
    /**
     * 界面数据变化重新生成划款指令
     */
    var reloadSettle = function(){
        var tradeAmt = $('#bonusCashAmt').val();
        var portfolioId = $('#portfolioId').val();
        var ps = $('#ps').val();
        var ccy = $('#finccy').val();
        if(tradeAmt && portfolioId && ccy && ps){
            $('#bussTrdTransferOrder_btn_generate').click();
        }
    }
    
    //初始化
    var init = function () {
        var obj = $('#finprodMarketId').select2('data')[0].object;
        if(obj){
            var str = obj.split(',');
            if(str != null){
                $('#unitvalueDec').val(str[3]);
            }
        }
        if(pageModel != PAGE_MODEL_REFERENCE){
            if ($('#unitvalueDec').val() != null && $('#unitvalueDec').val() != ''
                && $('#tdyUnitValue').val() != '' && $('#tdyUnitValue').val() != ''
                && $('#netvalue').val() != '' && $('#netvalue').val() != ''){
                $('#tdyUnitValue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
                $('#netvalue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
                $('#tdyUnitValue').val(parseFloat($('#tdyUnitValue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
                $('#netvalue').val(parseFloat($('#netvalue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
            }
        }else {
            $('#tdyUnitValue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
            $('#netvalue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
            $('#tdyUnitValue').val(parseFloat($('#tdyUnitValue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
            $('#netvalue').val(parseFloat($('#netvalue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
        }

        bonuScalcFlagChange(true);
        
        if(pageModel == PAGE_MODEL_NEW){
        	//分红金额默认值=总分红金额-已分红总额
        	var actTotalAmt = $("#actTotalAmt").val();
        	var totalBonusCashAmt = $("#totalBonusCashAmt").val();
        	if(!totalBonusCashAmt){
        		totalBonusCashAmt = 0;
        	}
        	var bonusMode = $("#bonusMode").val();
        	if(actTotalAmt && bonusMode!='03'){
        		$("#bonusCashAmt").val(JoyinMath.doMinus(actTotalAmt,totalBonusCashAmt)).trigger("change");
        	}    
        	$("#bonusDealRemark").val("客户收益支付");        	
        }
        
        window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var data = $.serializeToObject("#submitForm", true);
                    data.counterId = '324';
                    data.tradeAmt = $('#bonusCashAmt').val();
                    return {
                        'isTotalTrade':'N'
                        ,'bankLendingType':null
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':data
                        ,'isLiquidation':true
                    };
                });
            }
        }
        
        var operateType = $('#operateType').val();
    	if("revoke"==operateType || "delete"==operateType){
    		if("N"==isFromToDoList){
    			$('#FinNetProductBonusScheme004_btn_save').hide();
        		$('#FinNetProductBonusScheme004_btn_submit').hide();
        		if(pageModel == PAGE_MODEL_REFERENCE){
        			$('#FinNetProductBonusScheme004_btn_revoke').show();
        		}
    		}else{
    			$('#FinNetProductBonusScheme004_btn_revoke').hide();
    		}
    	}else{
    		$('#FinNetProductBonusScheme004_btn_revoke').hide();
    	}
    	reloadSettle();
    	
        //周期型产品 分红方式：现金分红bonusCashAmt不可填，汇总列表中的数据，混合模式现金红利金额为空，可改
        var bonusMode = $("#bonusMode").val();
        if(IS_CYCLE == 'Y') {
        	$('#cycleModel').show();
        	if(bonusMode =='01') {
        		$('#bonusCashAmt').attr("disabled",true);
        	} else if(bonusMode =='03'){
        		$('#bonusCashAmt').val('');
        		$('#bonusCashAmt').attr("disabled",false);
        	}
            if(cycleDataTable.table) {
            	cycleDataTable.reload();
            } else {
            	cycleDataTable.load();
            }
        } else {
        	$('#cycleModel').hide();
        	$('#bonusCashAmt').attr("disabled",false);
        }
        
    }
    
    

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.unitBonusVlu = $("#unitBonusVlu").val();
        object.totalAmt = $("#totalAmt").val();
        object.actTotalAmt = $("#actTotalAmt").val();
        object.actUnitBonusVlu = $("#actUnitBonusVlu").val();
        object.tradeId = $("#tradeId").val();
        object.bonusCashAmt = $("#bonusCashAmt").val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        
        if(IS_CYCLE) {
        	object.listCycleProductInfo = cycleDataTable.getAllRows();
        }
        
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/save', object, "FinNetProductBonusScheme004_btn_save");
                }else if(operateType === 'submit'){
                    WorkFlow.doSubmitList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/save', object, "FinNetProductBonusScheme004_btn_submit");
                }else if(operateType === 'revoke') {
                	WorkFlow.doRevokeList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/doRevoke', object, "FinNetProductBonusScheme004_btn_revoke");
                }
            });
        });
    };
    /**
     * 画面加载完成
     *
     */
    $(function() {
        init();
        $("#prodStyle").val('').parent().parent().hide();
        //长款型下拉框联动
        prodStyleSelectInit();
        // 绑定按钮事件
        $('#FinNetProductBonusScheme004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinNetProductBonusScheme004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        $('#FinNetProductBonusScheme004_btn_revoke').click(function() {
            saveOrSubmit('revoke');
        });
        //分红金额变动，校验
        $('#bonusCashAmt').change(function() {
        	bonusCashAmtValid();
        	reloadSettle();
        });
        
        //交易日期取红利下发日期
        $('#actBonusDate').change(function() {
        	$('#tradeDate').val($('#actBonusDate').val());
        	reloadSettle();
        });
    });
}());

