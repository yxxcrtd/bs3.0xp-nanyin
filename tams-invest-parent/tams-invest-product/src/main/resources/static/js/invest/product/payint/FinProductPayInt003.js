/**
 * 产品中间付息新建
 */
(function() {
	var finprodIdChangeAction = function(){
		var finprodId = $('#finprodId').val();
		if (finprodId){
    		Joyin.ajax(basePath + 'invest/product/payint/FinProductPayInt003/queryProdInfoByprodId', {finprodId : finprodId}, function(result){
    			if (result){                                                                               
    				$('#finprodAbbr').val(result.finprodAbbr);
    				$('#dealMode').val(result.dealMode);
    				$('#finprodName').val(result.finprodName);
    				$('#ccy').val(result.ccy).trigger("change");
    				var vdateStr = Date.ofDate(result.vdate).format('yyyy-MM-dd');
    				$('#prodVdate').val(vdateStr).trigger('change');
    				var mdateStr = Date.ofDate(result.mdate).format('yyyy-MM-dd');
    				$('#prodMdate').val(mdateStr).trigger('change');
    				$('#prinAmt').val(result.prinAmt);
    				$('#portfolioId').val(result.ptlId);
    				$('#isLay').val(result.isLay);
    				$('#bussId').val(result.bussId);
    				$('#prodRegistCode').val(prodRegistCode);
    				var payintStartDateStr = Date.ofDate(result.payintStartDate).format('yyyy-MM-dd');
    				$('#payintStartDate').val(payintStartDateStr);
    				$('#thisIntAccrDay').val(result.thisIntAccrDay);
    				$('#theoryCusProfit').val(result.theoryCusProfit);//应付客户收益
    				$('#actCusProfit').val(result.actCusProfit);//实付客户收益
    				/*$('#saleDepartment').val(result.saleDepartment).trigger("change");*/
    				$('#thisIntAccrDay').val(Computation($('#payintEndDate').val(),$('#payintStartDate').val()));
    				//显示利息明细列表
//    				intDetailInfoTable.table?intDetailInfoTable.reload():intDetailInfoTable.load();
    				intDetailInfoAndShowTable();
    				
    			}
    		})
    	}else{
    		$('#prodAbbr').val('');
    		$('#FprodName').val('');
    		$('#ccy').val('');
			$('#prodVdate').val('');
			$('#prodMdate').val('');
			$('#prinAmt').val('');
			$('#thisIntAccrDay').val('');
			$('#payintStartDate').val('');
			$('#theoryCusProfit').val('');
			$('#actCusProfit').val('');
			/*$('#saleDepartment').val('');*/
    	}
	};
	//返回两个日期之间的天数
	var Computation =function(sDate1, sDate2){ 
		
	    var aDate, oDate1, oDate2, iDays    
	    aDate = sDate1.split("-")    
	    oDate1 = Date.ofDate(aDate[1] + '-' + aDate[2] + '-' + aDate[0])  
	    aDate = sDate2.split("-")    
	    oDate2 = Date.ofDate(aDate[1] + '-' + aDate[2] + '-' + aDate[0])    
	    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24)  
//	     alert(iDays);  
	    return iDays    
	} 
	
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = $.serializeToObject('#submitForm', true);
        if (!pageParam.bussTrdTransferOrder) {
            pageParam.bussTrdTransferOrder = null;
        }
        return pageParam;
    };
    /**
     * 保存提交
     * @param operateType
     * @returns
     */
    function saveOrSubmit(operateType) {
       if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
           return;
       }
        
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.cashId = $("#finprodId").select2('data')[0].col;
        var intDetailDataList = intDetailInfoTable.getAllRows();
        object.intDetailDataList = intDetailDataList;
        if(operateType === 'revoke') {//撤销
        	object.validStatus = '05';
        	object.bussId = object.bussTrdTransferOrder[0].tradeId;
        	Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt001/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			WorkFlow.doRevokeList(basePath + 'invest/product/payint/FinProductPayInt001/doRevoke', object, "FinProductPayInt003_btn_revoke", "FinProductPayInt003");
        		});
        	});
        }else{
        	Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt003/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			if(operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/product/payint/FinProductPayInt003/save', object, "FinProductPayInt003_btn_save");
        			}
        			else {
        				WorkFlow.doSubmitList(basePath + 'invest/product/payint/FinProductPayInt003/save', object, "FinProductPayInt003_btn_submit");
        			}
        		});
        	});
        }
    };
    
    //查询利息明细
    var intDetailInfoAndShowTable = function(){
    	$('#thisIntAccrDay').val(Computation($('#payintEndDate').val(),$('#payintStartDate').val()));
    	var finprodId = $('#finprodId').val();
    	var tradeDate = $("#payintEndDate").val();
		var isLay = $("#isLay").val();
		
    	Joyin.ajax(JoyinUtil.getRealPath('invest/product/productduration/addreduce/FinProductAddReduce002/findReduceInfoData'),
    			{finprodId : finprodId,tradeDate:tradeDate,isLay:isLay,tradeStatus:'01'}, 
    			function(result){  
    				
		    		if (result){
		      		     //周期型产品展示子产品号
		    			if($('#dealMode').val()=='10'){
		    	            intDetailInfoTable.showColumns([{field:'sonFinprodId',visible: true}]);
		    	        } else {
		    	            intDetailInfoTable.showColumns([{field:'sonFinprodId',visible: false}]);
		    	        }
		    			if($('#isLay').val() == 'Y'){
		    	            intDetailInfoTable.showColumns([{field:'prodStyle',visible: true}]);
		    	        } else {
		    	            intDetailInfoTable.showColumns([{field:'prodStyle',visible: false}]);
		    	        }
		    			intDetailInfoTable.clear(function () {
							intDetailInfoTable.addRow(result)
		                });
		    			var theoryCusProfitSum = 0;
		      	    	var actCusProfitSum = 0; 
		      	    	var prinAmt = 0; 
		    			var arr = intDetailInfoTable.getAllRows();
		      	    	for ( var i = 0; i <arr.length; i++){
		    	  	   		 if(arr[i].interest==""||arr[i].interest==null){
		    	  	   			 arr[i].interest = 0;
		    	  	   		 }
		    	  	   		 if(arr[i].actInterest==""||arr[i].actInterest==null){
		    	  	   			 arr[i].actInterest = 0;
		    	  	   		 }
			      	   	     theoryCusProfitSum = JoyinMath.doAdd(theoryCusProfitSum, arr[i].interest, 2, JoyinMath.ROUND_HALF_UP);//应付利息累计
			      	   	     actCusProfitSum = JoyinMath.doAdd(actCusProfitSum, arr[i].actInterest, 2, JoyinMath.ROUND_HALF_UP);//实付利息累计
			      	   	     prinAmt = JoyinMath.doAdd(prinAmt, arr[i].remainPrinAmt, 2, JoyinMath.ROUND_HALF_UP);//实付利息累计
		      	   		}
		      		   	
		      		   	 $('#theoryCusProfit').val(theoryCusProfitSum);
		      		     $('#actCusProfit').val(actCusProfitSum);
		      		     $('#prinAmt').val(prinAmt);
		      		     
//		    			if($("#isLay").val()=='Y'){
//		    	            intDetailInfoTable.showColumns([{field:'prodType',visible: true}]);
//		    	        } else {
//		    	            intDetailInfoTable.showColumns([{field:'prodType',visible: false}]);
//		    	        }
		    		}
		   });
    	
        
    }
    
    /**
     * 利息明细
     */
    var intDetailOption = {
    	searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
//       ajax: {
//            url:JoyinUtil.getRealPath('/invest/product/payint/FinProductPayInt003/findIntDetailInfo'),
//            type: 'POST',
//            data: function() {
//            	return {
//            	finprodId : $('#finprodId').val(),
//            	tradeDate : $("#payDate").val(),
//            	payintEndDate : $('#payintEndDate').val(),
//            	payintStartDate : $('#payintStartDate').val(),
//            	isLay : $("#isLay").val()
//            	};
//            }
//        },
        done :function() {
            calcCusProfit();
        },
       cols:[
	            {field: 'sonFinprodId', title: '子产品号', width: 200, sortable: false,visible: $('#dealMode').val() == '10'}
	            , {field: 'prodStyle', title: '产品款型', width: 200, sortable: false,visible: $('#isLay').val() == 'Y'}
	            , {field: 'remainPrinAmt', title: '计息本金', width: 200, sortable: false, type: 'number'}
	            , {field: 'interest', title: '应付客户收益', width: 200, sortable: false, type: 'number'}
	            , {field: 'actInterest', title: '实付客户收益', width: 200, sortable: false, type: 'number', edit: true}
        ],
        beforeEditCell : function(preVal, field, rowData){
          
  	      return true;
  	    },
  	    editCell: function(value, data, field, cell, column){
  	    	var rowData = {};
  	    	var theoryCusProfitSum = 0;
  	    	var actCusProfitSum = 0; 
  	    	var prinAmt = 0; 
  	    	intDetailInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
  	    	var arr = intDetailInfoTable.getAllRows();
  	    	for ( var i = 0; i <arr.length; i++){
	  	   		 if(arr[i].interest==""||arr[i].interest==null){
	  	   			 arr[i].interest = 0;
	  	   		 }
	  	   		 if(arr[i].actInterest==""||arr[i].actInterest==null){
	  	   			 arr[i].actInterest = 0;
	  	   		 }
		  	   	 theoryCusProfitSum = JoyinMath.doAdd(theoryCusProfitSum, arr[i].interest, 2, JoyinMath.ROUND_HALF_UP);//应付利息累计
	 	   	     actCusProfitSum = JoyinMath.doAdd(actCusProfitSum, arr[i].actInterest, 2, JoyinMath.ROUND_HALF_UP);//实付利息累计
	 	   	     prinAmt = JoyinMath.doAdd(prinAmt, arr[i].remainPrinAmt, 2, JoyinMath.ROUND_HALF_UP);//实付利息累计
  	   		}
  		   	
  		   	 $('#theoryCusProfit').val(theoryCusProfitSum);
  		     $('#actCusProfit').val(actCusProfitSum);
  		     $('#prinAmt').val(prinAmt);
  	    } ,
  	   data:[]
        
    };
    
    if (intDetailDataList) {
    	for(var item in intDetailDataList){
    		intDetailOption.data.push(intDetailDataList[item]);
    	}
    }
	var intDetailInfoTable = $("#intDetailInfoTable").joyintable(intDetailOption);
	
    /**
     * 画面联动
     * 2.应付客户收益 = sum（应付利息）
     * 3.实付客户收益 = sum（实付利息）
     */
    var calcCusProfit = function() {
        if ($("#theoryCusProfit").val()) {
            return;
        }

        calcCusProfitByChange();
    };
    var calcCusProfitByChange = function() {
        var intDetailInfoTableDataArr = intDetailInfoTable.getAllRows();
        if (intDetailInfoTableDataArr && intDetailInfoTableDataArr.length > 0) {
            var paramObj = getPageParams();
            Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt002/calcCusProfit', paramObj, function(result) {
                if (result) {
                    if (result.theoryCusProfit) {
                        $("#theoryCusProfit").val(result.theoryCusProfit).trigger("change").trigger("blur");
                    }
                    if (result.actCusProfit) {
                        $("#actCusProfit").val(result.actCusProfit).trigger("change").trigger("blur");
                    }
                    if (result.prinAmt) {
                        $("#prinAmt").val(result.prinAmt).trigger("change").trigger("blur");
                    }
                }
            });
        }
    };   

    //初始化
    var init = function () {
        window.componentLoadCallback = function(isDone, pagePath, params){
            if (isDone) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    if (!$('#payDate').val()) {
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0038", JoyinUtil.getLabel("FinProductPayInt003.payDate")));
                        return null;
                    }

                    return {
                        'isTotalTrade': 'Y'
                        ,'bankLendingType': null
                        ,'portfolioId': $("#portfolioId").val()
                        ,'deal':{
                           'finprodType2' : 'F16',
                            'tradeDate' : $('#payDate').val(),
                            'ps' : 'C7',
                            'ccy' : $('#ccy').val(),
                            'finprodId' : $('#finprodId').val(),
                            'tradeAmt' : $("#actCusProfit").val(),
                            'bankLendingType' : null
                        }
                    };
                });
            }
        };
    };
    $(function () {
    	var operateType = $('#operateType').val();
		if("revoke"==operateType || "delete"==operateType){
			$('#FinProductPayInt003_btn_revoke').show();
		}else{
			$('#FinProductPayInt003_btn_revoke').hide();
		}
    	init();
    	$("#remark").val("客户收益支付");
    	//产品代码联动事件
		$('#finprodId').change(function(){
    		finprodIdChangeAction();
    	});
		 //本次付息结束日联动事件
        $('#payintEndDate').change(function(){
        	intDetailInfoAndShowTable();
        });
        // 绑定按钮事件
        $('#FinProductPayInt003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductPayInt003_btn_revoke').click(function() {
			saveOrSubmit('revoke');
		})
        $('#FinProductPayInt003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());