/**
 * FinNetProductBonusScheme005 红利再投份额确认
 */
(function() {    

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
				&& $('#tdyUnitValue').val() != '' && $('#tdyUnitValue').val() != ''){
				$('#tdyUnitValue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
				$('#tdyUnitValue').val(parseFloat($('#tdyUnitValue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
			}
		}else {
			$('#tdyUnitValue').attr("data-textrange", parseInt($('#unitvalueDec').val())).trigger('change');
			$('#tdyUnitValue').val(parseFloat($('#tdyUnitValue').val()).toFixed(parseInt($('#unitvalueDec').val()))).trigger("change");
		}

        if(pageModel == PAGE_MODEL_NEW){
        	//确认日默认除权日+1天
        	var delDate = $('#delDate').val();
//        	var confirmDate = Date.ofDate(delDate).DateAdd('d', 1).format('yyyy-MM-dd'); 
            $('#confirmDate').val(iPlatformDate);
            
            $('#vluRefDate').val(Date.ofDate(delDate).format('yyyy-MM-dd')).trigger("change");
            
        	//交易金额默认值=总分红金额-已分红总额
//        	var actTotalAmt = $("#actTotalAmt").val();
//        	var totalBonusCashAmt = $("#totalBonusCashAmt").val();
//        	if(!totalBonusCashAmt){
//        		totalBonusCashAmt = 0;
//        	}
//        	if(actTotalAmt){
//        		$("#bonusShareAmt").val(JoyinMath.doMinus(actTotalAmt,totalBonusCashAmt));
//        		bonusShareNumChange();
//        	}        	
        }
        
        var operateType = $('#operateType').val();
    	if("revoke"==operateType || "delete"==operateType){
    		if("N"==isFromToDoList){
	    		$('#FinNetProductBonusScheme005_btn_save').hide();
	    		$('#FinNetProductBonusScheme005_btn_submit').hide();
	    		$('#FinNetProductBonusScheme005_btn_revoke').show();
    		}else{
    			$('#FinNetProductBonusScheme005_btn_revoke').hide();
    		}
    		//layui-btn-disabled
    	}else{
    		$('#FinNetProductBonusScheme005_btn_revoke').hide();
    	}
    	
        //周期型产品 红利再投：非信息表中的交易金额bonusShareAmt不可填，交易份额bonusShareNum不可天，汇总列表中的数据
        if(IS_CYCLE == 'Y') {
        	$('#bonusShareAmt').attr("disabled",true);
        	$('#bonusShareNum').attr("disabled",true);
        	
        	$('#cycleModel').show();
        	
        	if(cycleDataTable.table) {
            	cycleDataTable.reload();
            } else {
            	cycleDataTable.load();
            }
        } else {
        	$('#cycleModel').hide();
        	$('#bonusShareAmt').attr("disabled",false);
        	$('#bonusShareNum').attr("disabled",false);
        }
    }
    
    var bonusShareAmtValid = function(){
    	//分红金额默认值=总分红金额-已分红总额
    	var actTotalAmt = $("#actTotalAmt").val();
    	var totalBonusCashAmt = $("#totalBonusCashAmt").val();
    	var bonusCashAmt = $("#bonusShareAmt").val();
    	if(!totalBonusCashAmt){
    		totalBonusCashAmt = 0;
    	}
    	if(!bonusCashAmt){
    		bonusCashAmt = 0;
    	}
    	var maxAmt = Number(JoyinMath.doMinus(actTotalAmt,totalBonusCashAmt));
    	if(Number(bonusCashAmt)>maxAmt){
    		JoyinUtil.showMessage("该分红方案的可分红总金额为"+actTotalAmt+"，已分红"+totalBonusCashAmt+"，本次分红金额大于剩余可分红金额"+maxAmt+"!","error");
    		$("#bonusShareAmt").val(maxAmt);
    	}
    	bonusShareNumChange();
    };
    
    //份额变动
    var bonusShareNumChange = function(){
    	var bonusCashAmt = $("#bonusShareAmt").val();
    	var tdyUnitValue = $("#tdyUnitValue").val();
    	if(bonusCashAmt && tdyUnitValue){
    		var shareNum = Number(JoyinMath.doDiv(bonusCashAmt,tdyUnitValue,2));
    		$("#bonusShareNum").val(shareNum);
    	}
    };
    //产品款型 下拉枚举值联动初始化
    var prodStyleSelectInit5 = function(){
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
    
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.unitBonusVlu = $("#unitBonusVlu").val();
        object.totalAmt = $("#totalAmt").val();
        object.actTotalAmt = $("#actTotalAmt").val();
        object.actUnitBonusVlu = $("#actUnitBonusVlu").val();
        object.tradeId = $("#tradeId").val();
        object.bonusShareAmt = $("#bonusShareAmt").val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        if(IS_CYCLE) {
        	object.listCycleProductInfo = cycleDataTable.getAllRows();
        }
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/save', object, "FinNetProductBonusScheme005_btn_save");
                }else if(operateType === 'submit'){
                    WorkFlow.doSubmitList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/save', object, "FinNetProductBonusScheme005_btn_submit");
                }else if(operateType === 'revoke') {
                	WorkFlow.doDeleteList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/doRevoke', object, "FinNetProductBonusScheme005_btn_revoke");
                }
            });
        });
    };
    
    
    var cycleDataTable=$("#cycleDataTable").joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/findCycleDataTableInfo'),
            type: 'POST',
            data: function() {
                return {finprodId:$("#finprodId").val()
                	,vluRefDate:$("#vluRefDate").val()
                    ,dealType : $("#dealType").val()
                    ,tradeId :  $("#tradeId").val()
                	,workflowId : workflowId
                }
            }
        },
        page:false,
        rowno:true,
        checkbox:false,
        cols:[
            {field:'finprodId',visible :false}
            ,{field:'branch',visible :false}
            ,{field:'periodId',title: 'FinNetProductDuration.cycleId', width:110,edit:false}
            ,{field:'subCycleStartDate',title: 'FinNetProductDuration.cycleStartDate', width:110,edit:false,type:'date'}
            ,{field:'subCycleEndDate',title: 'FinNetProductDuration.cycleEndDate', width:110,edit:false,type:'date'}
            ,{field:'bonusShareAmt', title: 'FinNetProductDuration.dealAmt', width:110,edit:true,type:'number',textrange:2} 
            ,{field:'bonusShareNum', title: 'FinNetProductDuration.dealNum', width:110,edit:true,type:'number',textrange:2} 
        ],
        editCell: function(value, row, field, cell, column){
        	if(field == 'bonusShareAmt') {
        		var tdyUnitValue = $("#tdyUnitValue").val();
        		if(row.bonusShareAmt && tdyUnitValue && tdyUnitValue != 0){
        			var shareNum = Number(JoyinMath.doDiv(row.bonusShareAmt,tdyUnitValue,2));
        			cycleDataTable.renderRow(row.LAY_TABLE_INDEX, {bonusShareNum : shareNum});		
        		}
        		
        		totalCycleBonusShareAmt();
        		totalCycleBonusShareNum();
        	}
        	
        	if(field == 'bonusShareNum') {
        		totalCycleBonusShareNum();
        	}
        	return true;
        }
    });
   
    
    var totalCycleBonusShareAmt = function(){
    	if(cycleDataTable) {
    		var cycleDataTableList = cycleDataTable.getAllRows();
    		var totalCycleBonusShareAmt = 0;
    		if(cycleDataTableList) {
    			for ( var i = 0; i <cycleDataTableList.length; i++){
   		   		if(cycleDataTableList[i].bonusShareAmt==""||cycleDataTableList[i].bonusShareAmt==null){
   		   			cycleDataTableList[i].bonusShareAmt = 0;
   		   		}
   		   	totalCycleBonusShareAmt = JoyinMath.doAdd(totalCycleBonusShareAmt, cycleDataTableList[i].bonusShareAmt, 2, JoyinMath.ROUND_HALF_UP);//红利再投金额累计
    		}
    	 }
    		$("#bonusShareAmt").val(totalCycleBonusShareAmt);
       }	
    }
    
    var totalCycleBonusShareNum = function(){
    	if(cycleDataTable) {
    		var cycleDataTableList = cycleDataTable.getAllRows();
    		var totalCycleBonusShareNum = 0;
    		if(cycleDataTableList) {
    			for ( var i = 0; i <cycleDataTableList.length; i++){
   		   		if(cycleDataTableList[i].bonusShareNum==""||cycleDataTableList[i].bonusShareNum==null){
   		   			cycleDataTableList[i].bonusShareNum = 0;
   		   		}
   		   	totalCycleBonusShareNum = JoyinMath.doAdd(totalCycleBonusShareNum, cycleDataTableList[i].bonusShareNum, 2, JoyinMath.ROUND_HALF_UP);//红利再投金额累计
    		}
    	 }
    		$("#bonusShareNum").val(totalCycleBonusShareNum);
       }	
    }
    
    var vluRefDateChange = function(){
    	var vluRefDate = $("#vluRefDate").val();
    	if(!vluRefDate){
    		$('#tdyUnitValue').val('').trigger("change");
    		return;
    	}
    	Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/getTdyUnitValueAndTdyShareAmt', 
            	{'finprodId':$("#finprodId").val(),'vluRefDate':Date.ofDate(vluRefDate).format('yyyy-MM-dd')}, 
            	function(result) {
            		if(result){
            			$("#tdyUnitValue").val(result.tdyUnitValue).trigger("change");
            		} else {
            			$('#tdyUnitValue').val('').trigger("change");
            		}
            	}
            );
    }
    
    
    /**
     * 画面加载完成
     *
     */
    $(function() {
        

    	
        init();
        //add by wm 默认款型下拉框隐藏  2019-09-05 start
        $("#prodStyle").val('').parent().parent().hide();
        //add by wm 默认款型下拉框隐藏  2019-09-05 end
        //长款型下拉框联动
        prodStyleSelectInit5();
        // 绑定按钮事件
        $('#FinNetProductBonusScheme005_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinNetProductBonusScheme005_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#FinNetProductBonusScheme005_btn_revoke').click(function() {
            saveOrSubmit('revoke');
        });
        //交易金额变动触发校验和份额变动
        $('#bonusShareAmt').change(function() {
        	bonusShareAmtValid();
        });
        //净值变动导致份额变动
        $('#tdyUnitValue').change(function() {
        	bonusShareNumChange();
        });
        
        //净值日变动联动净值 add by yzg 20200108
        $('#vluRefDate').change(function() {
        	vluRefDateChange();
        });

        if( $('#tdyUnitValue')  && $('#bonusShareAmt') ){
            bonusShareNumChange();
        }

    });
}());

