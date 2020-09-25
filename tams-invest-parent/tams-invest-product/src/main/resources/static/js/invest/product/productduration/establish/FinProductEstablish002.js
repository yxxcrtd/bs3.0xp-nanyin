(function(){
	var finprodIdChangeAction = function(){
		
		var finprodId = $('#finprodId').val();
		var vdate = $('#vdate').val();
		if (finprodId){
    		Joyin.ajax(basePath + 'invest/product/productduration/establish/FinProductEstablish002/queryProdInfoByFinprodId', {finprodId : finprodId, vdate : vdate}, function(result){
    			if (result){
    				if(vdate&&result.vdateStr){
    					if(vdate>result.vdateStr&&result.operationType=='01'){
    		        		//将已经展开的信息设置为空
    		        		$('#finprodAbbr').val('');
    	    				$('#finprodName').val('');
    	    				$('#ccy').val('').trigger("change");
    	    				$('#mdate').val('');
    	    				$('#basis').val('').trigger("change");
    	    				$('#FinProductEstablish002_ps').val('').trigger("change");
    	    				$('#dealMode').val('');
    	    				$('#isLay').val('');
    	    				$('#ptlId').val('');
    	    				$('#yield').val('');
    	    				$('#FinProductEstablish002_div_layering').prop('hidden', true);
    						msg = JoyinUtil.getMessage('封闭式产品不能进行申购交易');
    		        		Joyin.warn(msg);
    		        		return;
    					}
    				}
    				$('#finprodAbbr').val(result.finprodAbbr);
    				$('#finprodName').val(result.finprodName);
    				$('#ccy').val(result.ccy).trigger("change");
    				//产品要素起息日
    				$('#vdate2').val(result.vdateStr).trigger('change');
    				//产品成立日
    				//$('#vdate').val(result.vdateStr).trigger('change');  update by dingjunfeng 
    				var vdateStr2 = Date.ofDate(result.vdateStr).format('yyyy-MM-dd');//add by yejiejie 2019-3-1
    				//lijun modify 根据需求文档4.产品到期日取值：处理模式为“普通”“分档”到期日为产品到期日，不允许编辑；处理模式为“周期型”“分档周期型”到期日为当前开放区间的周期到期日（确认日+投资周期）；允许编辑 20190329 start
    				if(result.dealMode && (result.dealMode =='10' || result.dealMode =='12')){
    					var mdateStr2 = new Date(result.vdate).DateAdd('d',result.investmentCycle).format('yyyy-MM-dd');
    					$('#mdate').val(mdateStr2).trigger('change');
    					$('#mdate').attr("disabled",false);
    				}else{
    					$('#mdate').val(result.mdateStr).trigger('change');
    					$('#mdate').attr("disabled",true);
    				}
    				//lijun modify 根据需求文档4.产品到期日取值：处理模式为“普通”“分档”到期日为产品到期日，不允许编辑；处理模式为“周期型”“分档周期型”到期日为当前开放区间的周期到期日（确认日+投资周期）；允许编辑 end
    				$('#basis').val(result.basis).trigger("change");
    				$('#dealMode').val(result.dealMode);
    				$('#isLay').val(result.isLay);
    				$('#ptlId').val(result.ptlId);
    				$('#yield').val(result.yield);
    				$('#operationType').val(result.operationType);
    				$('#continueFlag').val(result.continueFlag);
    				//add by wangcheng 2019/3/7
//    				$('#ps').val(result.ps);
    				
    				vdateChangeAction();
    				
//    				//分层产品显示分层金额明细列表
//    				layAmtTable.reload();
    				showDiv(result);
    				//add by yejiejie 2019-3-7 start
    				if((Date.ofDate($('#vdate').val()).format('yyyy-MM-dd'))>vdate2){
    					//系统时间大于起息日---->申购
//    					$('#customersBeginNumDiv').hide();//期初客户数隐藏
    					$('#customersAddNumDiv').show();//增加客户数
    					$('#customersBeginNum').val('');
    					$('#customersAddNum').val('');
    				}else{
    					//系统时间小于起息日---->认购
//    					$('#customersBeginNumDiv').show();//期初客户数隐藏
    					$('#customersAddNumDiv').hide();//增加客户数
    					$('#customersBeginNum').val('');
    					$('#customersAddNum').val('');
    				}
    			}
    		})
    	}else{
    		$('#finprodAbbr').val('');
    		$('#finprodName').val('');
    		$('#ccy').val('');
//    		$('#vdate').val('');
//    		$('#mdate').val('');
    		$('#basis').val('');
    		$('#lastAmt').val('');
			$('#lastRed').val('');
			$('#lastAmtSub').val('');
			$('#accountingAmt').val('');
			$('#currentSub').val('');
    	}
	};

	function vdateChangeAction(){
		var vdate = $('#vdate').val();
		var vdate2 = $('#vdate2').val();
		//产品成立日小于等于起息日为认购 否则为申购   
			if(vdate <= vdate2){   //update by dingjunfeng
				$('#ps').val('C1');
			}else{
				$('#ps').val('C2');
			}
		//add by yejiejie 2019-3-1 start
		$("#FinProductEstablish002_ps").val($('#ps').val()).trigger('change');
		if($('#ps').val() == 'C2'){
			//系统时间大于起息日---->申购
//			$('#customersBeginNumDiv').hide();//期初客户数隐藏
			$('#customersAddNumDiv').show();//增加客户数
			$('#customersBeginNum').val('');
			$('#customersAddNum').val('');
			
			document.getElementById('currentSubText').innerHTML = '本期申购';
		}else{
			//系统时间小于起息日---->认购
//			$('#customersBeginNumDiv').show();//期初客户数
			$('#customersAddNumDiv').hide();//增加客户数隐藏
			$('#customersBeginNum').val('');
			$('#customersAddNum').val('');
			
			document.getElementById('currentSubText').innerHTML = '本期认购';
		}
		//add by yejiejie 2019-3-1 end
		
		//分层产品显示分层金额明细列表
		layAmtTable.reload();
		//交易类型为产品申购时，列表中的字段名展示为本期申购，交易类型为产品认购时，展示为本期认购
		if($('#ps').val() == 'C2'){
			layAmtTable.showColumns([
				                        {field:'currentSub', title: 'FinProductEstablish002.currentPur', width: 160, edit: true, type:'number', visible: true}
				                    ]);
		}else{
			layAmtTable.showColumns([
				                        {field:'currentSub', title: 'FinProductEstablish002.currentSub', width: 160, edit: true, type:'number', visible: true}
				                    ]);
		}
	}
	
	//如果产品分层则显示分层明细金额列表 并且本期认购、上期余额、上期赎回、上期滚入金额、到账资金不可编辑
	function showDiv(){
		var dealMode = $('#dealMode').val();
		var continueFlag = $('#continueFlag').val();
		if(pageModel == PAGE_MODEL_NEW){
			$('#lastAmt').val('');
			$('#lastRed').val('');
			$('#lastAmtSub').val('');
			$('#accountingAmt').val('');
			$('#currentSub').val('');
		}
		if(dealMode=='11'||dealMode=='12'){
			$('#FinProductEstablish002_div_layering').prop('hidden', false);
			$('#lastAmt').attr("disabled",true);
			$('#lastRed').attr("disabled",true);
			$('#lastAmtSub').attr("disabled",true);
			$('#accountingAmt').attr("disabled",true);
			$('#currentSub').attr("disabled",true);
		}else{
			$('#FinProductEstablish002_div_layering').prop('hidden', true);
			$('#lastAmt').attr("disabled",false);
			$('#lastRed').attr("disabled",false);
			$('#lastAmtSub').attr("disabled",false);
			$('#accountingAmt').attr("disabled",false);
			$('#currentSub').attr("disabled",false);
		}
		if(continueFlag=='Y'){
			$('#FinProductEstablish002_prinAmt').show();
			$('#FinProductEstablish002_lastAmt').show();
		}else{
			$('#FinProductEstablish002_prinAmt').hide();
			$('#FinProductEstablish002_lastAmt').hide();
		}
		
			
	};
	
	
	/**
	 * 分层金额明细
	 */
	
	var layAmtTable = $('#FinProductEstablish002_layAmtTable').joyintable({ 
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/productduration/establish/FinProductEstablish002/queryLayAmtDetailByFinprodId'),
            type: 'POST',
            data: function() {
                return {finprodId : $('#finprodId').val(),workflowId : workflowId, tradeId : $('#tradeId').val(), effectStatus : effectStatus};
            }
        },
        searchOnInit: true,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        cols:[
				 {field:'prodType', title: 'FinProductEstablish002.prodType',sortable: false, width: 200}
				,{field:'lastAmt', title: 'FinProductEstablish002.lastAmt', width: 160, sortable: false, visible:$('#continueFlag').val()==='Y', edit: true, type:'number' }
				,{field:'currentSub', title: 'FinProductEstablish002.currentSub', width: 160, sortable: false, edit: true, type:'number'}
				,{field:'lastRed'  , title: 'FinProductEstablish002.lastRed', width: 160, sortable: false, visible:$('#continueFlag').val()==='Y', edit: true, type:'number'}
				,{field:'lastAmtSub', title: 'FinProductEstablish002.lastAmtSub', width: 160, sortable: false, visible:$('#continueFlag').val()==='Y', edit: true, type:'number'}
				,{field:'accountingAmt', title: 'FinProductEstablish002.accountingAmt', width: 160, sortable: false, visible:$('#continueFlag').val()==='Y', edit: true, type:'number'}
				,{field:'finprodId', visible: false}
	        ],
	    beforeEditCell : function(preVal, field, rowData){
	      
	      return true;
	    },
	    editCell: function(value, data, field, cell, column){
	    	
	    	var rowData = {};
//	    	var lastAmt = 0;
//	    	var currentSub = 0;
//	    	var lastRed = 0;
//	    	var lastAmtSub = 0;
//	    	var accountingAmt = 0;
	    	var lastAmtSum = 0;
	    	var currentSubSum = 0;
	    	var lastRedSum = 0;
	    	var lastAmtSubSum = 0;
	    	var accountingAmtSum = 0;
	    	rowData.lastAmtSub = JoyinMath.doMinus(data.lastAmt, data.lastRed, 2, JoyinMath.ROUND_HALF_UP);
	    	rowData.accountingAmt = JoyinMath.doAdd(rowData.lastAmtSub, data.currentSub, 2, JoyinMath.ROUND_HALF_UP);
	    	layAmtTable.renderRow(data.LAY_TABLE_INDEX, rowData);
	    	var arr = layAmtTable.getAllRows();
	    	 for ( var i = 0; i <arr.length; i++){
	   		 if(arr[i].lastAmt==""||arr[i].lastAmt==null){
	   			 arr[i].lastAmt = 0;
	   		 }
	   		 if(arr[i].currentSub==""||arr[i].currentSub==null){
	   			 arr[i].currentSub = 0;
	   		 }
	   		 if(arr[i].lastRed==""||arr[i].lastRed==null){
	   			 arr[i].lastRed = 0;
	   		 }
	   		 if(arr[i].lastAmtSub==""||arr[i].lastAmtSub==null){
	   			 arr[i].lastAmtSub = 0;
	   		 }
	   		 if(arr[i].accountingAmt==""||arr[i].accountingAmt==null){
	   			 arr[i].accountingAmt = 0;
	   		 }
	   		 lastAmtSum = JoyinMath.doAdd(lastAmtSum, arr[i].lastAmt, 2, JoyinMath.ROUND_HALF_UP);//上期余额累计
	   		 currentSubSum = JoyinMath.doAdd(currentSubSum, arr[i].currentSub, 2, JoyinMath.ROUND_HALF_UP);//本期认购累计
	   		 lastRedSum = JoyinMath.doAdd(lastRedSum, arr[i].lastRed, 2, JoyinMath.ROUND_HALF_UP);//上期赎回累计
	   		 lastAmtSubSum = JoyinMath.doAdd(lastAmtSubSum, arr[i].lastAmtSub);//上期滚入金额累计
	   		 accountingAmtSum = JoyinMath.doAdd(accountingAmtSum, arr[i].accountingAmt);//到账资金累计
	   		}
		   	 $('#lastAmt').val(lastAmtSum);
		   	 $('#currentSub').val(currentSubSum);
		   	 $('#lastRed').val(lastRedSum);
		   	 $('#lastAmtSub').val(lastAmtSubSum);
		   	 $('#accountingAmt').val(accountingAmtSum);
	    },
	    done:function(table){
	    	if($('#ps').val() == 'C2'){
		    	$("[data-field='currentSub']").find(' .laytable-cell-1-currentSub span').html('本期申购');
	    	}
	    	else{
		    	$("[data-field='currentSub']").find(' .laytable-cell-1-currentSub span').html('本期认购');
	    	}
        }
    });
	
	/**
	 * 绑定删除事件
	 */
//	layAmtTable.bind('click', '.delt', function(data, index) {
//		table.removeRow(data);
//	});
	/**
	 * 保存提交事件
	 */
	function saveOrSubmit(operateType) {
		// 检查生成划款指令参数是否发生变化
		//modify by wm 2019-09-03 放开检查生成划款指令参数是否发生变化判断（之前代码被注释） start
//        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
//            return;
//        }
		//modify by wm 2019-09-03 放开检查生成划款指令参数是否发生变化判断（之前代码被注释） end
        
        var object = $.serializeToObject('#FinProductEstablish002_Form');
        object.operateType = operateType;
        object.pageModel = pageModel;
        var finProductEstablishLayerDtoList = layAmtTable.getAllRows();
        object.finProductEstablishLayerDtoList = finProductEstablishLayerDtoList;
        if(operateType === 'revoke') {//撤销
            //撤销时使用
            if($('#tradeId').val()){
            	object.bussId = $('#tradeId').val();
            	object.tradeId = $('#tradeId').val();
            }
        	object.effectStatus = '01';
        	delete object.bussTrdTransferOrder;
        	delete object.finProductEstablishLayerDtoList;
        	Joyin.ajaxJson(basePath + 'invest/product/productduration/establish/FinProductEstablish001/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
					WorkFlow.doDeleteList(basePath + 'invest/product/productduration/establish/FinProductEstablish001/doRevoke', object, "FinProductEstablish002_btn_revoke", "FinProductEstablish002");
        		});
        	});
        }else{
        	Joyin.ajaxJson(basePath + 'invest/product/productduration/establish/FinProductEstablish002/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			if(operateType === 'save') {
        				//保存
        				WorkFlow.doSaveList(basePath + 'invest/product/productduration/establish/FinProductEstablish002/save', object, "FinProductEstablish002_btn_save");
        			}else {
        				WorkFlow.doSubmitList(basePath + 'invest/product/productduration/establish/FinProductEstablish002/save', object, "FinProductEstablish002_btn_submit");
        			}
        		});
        	});
        }
    };
    
    //上期滚入金额=上期余额-上期赎回
    function lastAmtSub(){
    	var lastAmt = $('#lastAmt').val();
    	var lastRed = $('#lastRed').val();
    	$('#lastAmtSub').val(JoyinMath.doMinus(lastAmt, lastRed, 2, JoyinMath.ROUND_HALF_UP)).blur('change');
    };
    //到账资金 = 上期滚入金额+本期认购
    function accountingAmt(){
    	var lastAmt = $('#lastAmt').val();
    	var lastRed = $('#lastRed').val();
    	var currPur = $('#currentSub').val();
    	$('#accountingAmt').val(JoyinMath.doMinus(JoyinMath.doAdd(lastAmt, currPur, 2, JoyinMath.ROUND_HALF_UP), lastRed, 2, JoyinMath.ROUND_HALF_UP)).blur('change');
    };
    
    /**
     * 界面数据变化重新生成划款指令
     */
    var reloadSettle = function(){
        var tradeAmt = $('#currentSub').val();
        var portfolioId = $('#ptlId').val();
        var ps = $('#ps').val();
        var ccy = $('#ccy').val();
        if(tradeAmt && portfolioId && ccy && ps){
            $('#bussTrdTransferOrder_btn_generate').click();
        }
    }
    
  //初始化
//    var init = function () {
//        window.componentLoadCallback = function(isDone, pagePath, params){
//        	
//            if(isDone){
//                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
//                    var data = $.serializeToObject("#FinProductEstablish002_Form", true);
//                    data.counterId = '324';
//                    data.tradeAmt = $('#currentSub').val();
//                    data.ccy = $('#ccy').val();
//                    data.tradeDate = $('#vdate').val();
//                    data.finprodType2 = 'F16';
//                    data.ps = $('#ps').val();
//                    return {
//                        'isTotalTrade':'N'
//                        ,'bankLendingType':null
//                        ,'portfolioId':$("#ptlId").val()
//                        ,'deal':data
//                    };
//                });
//            }
//        }
//    };
    
	$(function(){
		var operateType = $('#operateType').val();
		if("revoke"==operateType || "delete"==operateType){
			$('#FinProductEstablish002_btn_revoke').show();
		}else{
			$('#FinProductEstablish002_btn_revoke').hide();
		}
		
		window.componentLoadCallback = function(isDone, pagePath, params){
        	
            if(isDone){
				showDiv();
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var data = {};
                    data.counterId = '324';
                    data.tradeAmt = $('#currentSub').val();
                    data.ccy = $('#ccy').val();
                    data.tradeDate = $('#vdate').val();
                    data.finprodType2 = 'F16';
                    data.ps = $('#ps').val();
                    return {
                        'isTotalTrade':'Y'
                        ,'bankLendingType':null
                        ,'portfolioId':$("#ptlId").val()
                        ,'deal':data
                    };
                });
            }
        }
		
//		init();
		
		//产品代码联动事件
		$('#finprodId').change(function(){
    		finprodIdChangeAction();
    	});
		//update by wangcheng 2019/3/7
		//产品成立日联动事件
		$('#vdate').change(function(){
    		vdateChangeAction();
    		reloadSettle();
    	});

		//上期余额
		$('#lastAmt').change(function(){
			lastAmtSub();
			accountingAmt();
		});
		// 上期赎回
		$('#lastRed').change(function(){
			lastAmtSub();
			accountingAmt();
		});
		// 上期滚入金额
		$('#lastAmtSub').change(function(){
			lastAmtSub();
			accountingAmt();
		});
		// 本期认购
		$('#currentSub').change(function(){
			lastAmtSub();
			accountingAmt();
			reloadSettle();
		});
		
		// 保存
		$('#FinProductEstablish002_btn_save').click(function(){
			saveOrSubmit('save');
		});
		//撤销
		$('#FinProductEstablish002_btn_revoke').click(function() {
			saveOrSubmit('revoke');
		});
		// 提交
		$('#FinProductEstablish002_btn_submit').click(function(){
			saveOrSubmit('submit');
		});
		
		$('#FinProductEstablish002_prinAmt').hide();
		$('#FinProductEstablish002_lastAmt').hide();
		//add by yejiejie 2019-3-10 start
		if($('#ps').val() == 'C2'){
			//系统时间大于起息日---->申购
//			$('#customersBeginNumDiv').hide();//期初客户数隐藏
			$('#customersAddNumDiv').show();//增加客户数
		}else if($('#ps').val() == 'C1'){
			//系统时间小于起息日---->认购
//			$('#customersBeginNumDiv').show();//期初客户数隐藏
			$('#customersAddNumDiv').hide();//增加客户数
		}
		//add by yejiejie 2019-3-10 end
	});
	//隐藏期初客户数
	$('#customersBeginNumDiv').hide();
	if($('#ps').val() == 'C2'){
		document.getElementById('currentSubText').innerHTML = '本期申购';
	}else{
		document.getElementById('currentSubText').innerHTML = '本期认购';
	}
}());