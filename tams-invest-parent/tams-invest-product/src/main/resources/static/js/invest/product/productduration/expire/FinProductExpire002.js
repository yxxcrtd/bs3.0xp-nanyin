(function(){
	var platdateext = platDate;
	  // 生成指令参数保存  add by dingjunfeng
    var calcConditionObjBackUp = null;
    var init = false;
    var rePayAndIntpFlag =false;
    var subProductTableOption = {
//    		ajax: {
//                url: JoyinUtil.getRealPath('invest/product/productduration/addreduce/FinProductAddReduce002/findReduceInfoData'),
//                type: 'POST',
//                data: function() {
//                    return {
//                    	finprodId : $('#FinProductExpire002_SELECT_finprodMarketId').val(),
//                    	tradeDate : $("#FinProductExpire002_endDate").val(),
//                    	isLay : $("#FinProductExpire002_isLay").val()
//                    	};
//                }
//            },
            searchOnInit: true,
            height: 200,
            page: false,
            checkbox: false,
            loadonce: true,
            rowno: true,
	        cols: [
		            {field: 'indexNo', title: '子产品号',  visible:false,sortable: false}
		            ,{field: 'finprodId', title: '产品代码', visible:false,sortable: false}
		            , {field: 'prodStyle', title: '产品款型', width: 200, sortable: false, visible: true}
		            , {field: 'remainPrinAmt', title: '计息本金', width: 200, sortable: false, type: 'number', visible: false}
		            , {field: 'vDate', title: '计息开始日', width: 200, sortable: false, edit: false}
		            , {field: 'mDate', title: '计息结束日', width: 200, sortable: false, edit: false}
		            , {field: 'interest', title: '应付客户收益', width: 200, sortable: false, type: 'number'}
		            , {field: 'actInterest', title: '实付客户收益', width: 200, sortable: false, type: 'number', edit: true}
		            , {field: 'reduceAmt', title: '本期客户赎回本金', width: 200, sortable: false, type: 'number', edit: true}
		            , {field: 'reduceAmtTher', title: '本期客户赎回本金', type: 'number', visible: false}
		            , {field: 'idEditFlag', title: '兑付客户收益是否能编辑', type: 'number', visible: false}
		        ],
		        beforeEditCell : function(preVal, field, rowData){
		        	//add by wm 当为到期交易时，不允许编辑本金框 start 2019-08-02
		        	if($("#FinProductExpire002_ps").val()=='C5'&&field =='reduceAmt' ){
		        		return false;
		        	}
		        	//add by wm 当为到期交易时，不允许编辑本金框 end 2019-08-02
		        	if(field =='actInterest' && rowData.idEditFlag ){
                       return false;
                    }

		  	      return true;
		  	    },
		  	    editCell: function(value, data, field, cell, column){
		  	    	var interestSum = 0;
		  	    	var actInterestSum = 0;
		  	    	var reduceAmtSum = 0;
		  	    	loadSubProdInfo.renderRow(data.LAY_TABLE_INDEX);
		  	    	 var arr = loadSubProdInfo.getAllRows();
		  	    	 for ( var i = 0; i <arr.length; i++){
				  	   		 if(arr[i].interest==""||arr[i].interest==null){
				  	   			 arr[i].interest = 0;
				  	   		 }
				  	   		 if(arr[i].actInterest==""||arr[i].actInterest==null){
				  	   			 arr[i].actInterest = 0;
				  	   		 }
				  	   		 if(arr[i].reduceAmt==""||arr[i].reduceAmt==null){
				  	   		     arr[i].reduceAmt = 0;
				  	   		 }
				  	   		 if(arr[i].reduceAmtTher==""||arr[i].reduceAmtTher==null){
                                 arr[i].reduceAmtTher = 0;
                             }
					  	    //部分赎回：判断赎回/到期日=付息日 且 赎回/到期日！=到期日 时应付客户收益=0
					  	    if(field == 'reduceAmt'){
                                if(arr[i].reduceAmt && arr[i].reduceAmtTher){
    					  	        if(rePayAndIntpFlag && parseFloat(arr[i].reduceAmt) != parseFloat(arr[i].reduceAmtTher)){
                                        arr[i].actInterest = 0;
                                        arr[i].interest = 0;
                                        arr[i].idEditFlag = true;

    					  	        }else{
    					  	            arr[i].idEditFlag = false;
    					  	        }
                                }
					  	    }
					  	   	interestSum = JoyinMath.doAdd(interestSum, arr[i].interest, 2, JoyinMath.ROUND_HALF_UP);
					  	   	actInterestSum = JoyinMath.doAdd(actInterestSum, arr[i].actInterest, 2, JoyinMath.ROUND_HALF_UP);
					  	    reduceAmtSum = JoyinMath.doAdd(reduceAmtSum, arr[i].reduceAmt, 2, JoyinMath.ROUND_HALF_UP);
		  	   		 }
					 loadSubProdInfo.reload({data:arr});
		  		   	 $('#FinProductExpire002_cusProfit').val(interestSum);
		  		   	 $('#FinProductExpire002_actCusProfit').val(actInterestSum);
		  		   	 $('#FinProductExpire002_reduceAmt').val(reduceAmtSum);
		  	    },
		  	    data:[],
		  	    done:function(){
		  	    	if (init) {
                        if('Y' == $("#FinProductExpire002_isLay").val()){
                            loadSubProdInfo.showColumns([{field:'prodStyle',visible: true}]);
                        } else {
                            loadSubProdInfo.showColumns([{field:'prodStyle',visible: false}]);
                        }
                        init = false;
                    }
		  	    }
    };
    if (finProductExpireList) {
    	for(var item in finProductExpireList){
    	    subProductTableOption.data.push(finProductExpireList[item]);
        }
    }
    var loadSubProdInfo = $('#subProductTable').joyintable(subProductTableOption);

    /**
     * 定义数据表格
     *
     */
    var settleOption = {
        searchOnInit: true,
        checkbox: false,
        cols: [
            {
                field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true,  sortable: false,
                editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
            }
            , {field: 'payTypeText', width: 130, title: 'BussTrdTransferOrder.payType', sortable: false, sortable: false}
            , {field: 'settleDateText', width: 130, title: 'BussTrdTransferOrder.settleDate', sortable: false}
            , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable: false}
            , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable: false}
            , {
                field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable:  true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'payAcctNo', postData: function () {
                       // return {selectValueOrder: JSON.stringify(orderAcctMap), type: 'pay'};
                     //解决账户列表太大页面卡死问题   zhangweiqi 2019-4-11
                    }
                }
            }
            , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable: false}
            , {
                field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable:  true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'recAcctNo', postData: function () {
                        //return {selectValueOrder: JSON.stringify(orderAcctMap), type: 'rec'};
                    	//解决账户列表太大页面卡死问题   zhangweiqi 2019-4-11
                    }
                }
            }
            , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable: false}
            , {field: 'purpose', width: 130, title: 'BussTrdTransferOrder.purpose', sortable: false, edit: true}
            , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable: false}
            , {field: 'portfolioName', width: 150, title: 'BussTrdTransferOrder.portfolioName', sortable: false}
            , {
                field: 'orderId', visible: true, edit: true, width: 0,
                editOption: {
                    type: 'select', id: 'orderId_select', childrenIds: 'recAcctNo,payAcctNo'
                }
            }
            , {field: 'ps', visible: false}
            , {field: 'settleStatus', visible: false}
            , {field: 'isTotalTrade', visible: false}
            , {field: 'finprodType2', visible: false}
        ],
        done: function () {
            $("[data-field='orderId']").hide();
            resetTable();
        },
        page: false,
        data: [],
        editCell: function (value, row, field, cell, column) {
            if (field == 'payAcctNo') {
                $.each(JSON.parse(acctNoList.value), function (i, v) {
                    if (v.id == value) {
                        row.payAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.payAcctName = "";
                }
            } else {
                $.each(JSON.parse(acctNoList.value), function (i, v) {
                    if (v && v.id == value) {
                        row.recAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.recAcctName = "";
                }
            }
            if (field == 'isNeedSend') {
                row.isNeedSend=value;
            }
            table.renderRow(row.LAY_TABLE_INDEX, row);
        }
    };
    
    if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
        settleOption.data = settleOption.data.concat(bussTrdTransferOrderDtos);
    }
    var table = $("#bussTrdTransferOrder_table").joyintable(settleOption);
    
 // 生成指令参数保存
    var orderAcctMap = {};
    var stlCommandInput = function () {
        if (!$("#FinProductExpire002_ptlId").val()) {
            Joyin.error("未选择产品代码，或产品未挂组合");
            return;
        }
        if (!$("#FinProductExpire002_ps").val()) {
            Joyin.error("请选择交易方向");
            return;
        }
        if (!$("#FinProductExpire002_endDate").val()) {
            Joyin.error("请选择赎回/到期日");
            return;
        }
        if (!$("#FinProductExpire002_ccy").val()) {
            Joyin.error("请输入币种");
            return;
        }

        var dtos = [];
        if (!$("#FinProductExpire002_mCusRevAmt").val()) {
            Joyin.error("赎回本金未输入或为0");
            return;
        }
        if (!$("#FinProductExpire002_actCusProfit").val()) {
            Joyin.error("实付客户收益未输入或为0");
            return;
        }
        var reduceAmt = $("#FinProductExpire002_reduceAmt").val();
        var actCusProfit = $("#FinProductExpire002_actCusProfit").val();
        var tradeAmt = JoyinMath.doAdd(reduceAmt ,actCusProfit);
        //update by leipeng 20190627 BSTWO-176 【收益型产品赎回到期划款】本金和收益要分开划款。 start
        dtos.push({
            'isTotalTrade': YES
            , 'bankLendingType': null
            , 'portfolioId': $("#FinProductExpire002_ptlId").val()
            , 'deal': {
                finprodType2: F16,
                ps: $("#FinProductExpire002_ps").val(),
                tradeDate: $("#FinProductExpire002_payDate").val(),
                tradeAmt: tradeAmt,
                finprodId: $('#FinProductExpire002_SELECT_finprodMarketId').val(),
                ccy: $("#FinProductExpire002_ccy").val()
            }
        });
        //update by leipeng 20190628 需求撤回,还原原代码
//        dtos.push({
//            'isTotalTrade': YES
//            , 'bankLendingType': null
//            , 'portfolioId': $("#FinProductExpire002_ptlId").val()
//            , 'deal': {
//                finprodType2: F16,
//                ps: $("#FinProductExpire002_ps").val(),
//                tradeDate: $("#FinProductExpire002_endDate").val(),
//                tradeAmt: actCusProfit,
//                ccy: $("#FinProductExpire002_ccy").val()
//            }
//        })
        //update by leipeng 20190627 BSTWO-176 【收益型产品赎回到期划款】本金和收益要分开划款。 end
        return dtos;
    };

    // 检查生成指令参数是否发生变化
    var isCalcConditionChanged = function () {
    	debugger;
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if (!$("#bussTrdTransferOrder_div").is(":visible")) {
            return false;
        }
        if (calcConditionObjBackUp == null) {
            return false;
        }
        if (stlCommandInput != null) {
            var currentCalcConditionObj = stlCommandInput();
            // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
            for (var key in currentCalcConditionObj) {
                for (var key1 in currentCalcConditionObj[key]) {
                    if (typeof currentCalcConditionObj[key][key1] != "object"
                        && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
            for (var key in currentCalcConditionObj) {
                for (var key1 in currentCalcConditionObj[key].deal) {
                    var currentDealObj = currentCalcConditionObj[key].deal;
                    var backupDealObj = calcConditionObjBackUp[key].deal;
                    if (typeof currentDealObj[key1] != "object"
                        && currentDealObj[key1] != backupDealObj[key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
        }
    };

    // 生成划款指令
    $("#bussTrdTransferOrder_btn_generate").on("click", function () {
        if (stlCommandInput != null) {
            var data = stlCommandInput();
            if (!data) {
                return;
            }
            // 保存生成指令参数
            calcConditionObjBackUp = data;
            orderAcctMap = {};
            var result = [];
            Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
                Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/generateOrder'), data, function (ret) {
                    if (data.length == ret.data.length) {
                        for (var key in ret.data) {
                            orderAcctMap[ret.data[key].orderId] = {
                                payAcctNoList: ret.data[key].payAcctNoList,
                                recAcctNoList: ret.data[key].recAcctNoList
                            }
                        }
                        table.clear(function () {
                            table.addRow(ret.data)
                        })
                    }
                }, false);
            });
        }
    });
    
    function resetTable() {
        ref = setInterval(function () {
            if ($(".layui-table-view").length > 0) {
                var height = $(window).height();
                $(".layui-table-view").css({height: height / 2 - 92 + 'px'});
                $(".layui-table-view .layui-table-box .layui-table-body").css({height: (height / 2 - 175) + 'px'});
                setted = true;
            }
        }, 300);
        var set = setInterval(function () {
            if (setted) {
                clearInterval(ref);
                clearInterval(set);
            }
        }, 300);
    }
    
	/**
	 * 保存提交事件
	 */
	function saveOrSubmit(operateType) {
        // 检查生成划款指令参数是否发生变化  update by dingjunfeng
        if (isCalcConditionChanged()) {
            return;
        }
        
        var object = $.serializeToObject('#FinProductExpire002_Form',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        console.log(object);

        //代办提交留痕表保存
        if(object.tradeId && object.tradeId.length > 0){
            object.busiNo = object.tradeId;
        }
        var finProductExpireMInfoDtoList = loadSubProdInfo.getAllRows();
        object.finProductExpireMInfoDtoList = finProductExpireMInfoDtoList;
        if(operateType === 'revoke') {//撤销
        	object.effectStatus = '01';
        	object.busiNo = object.bussTrdTransferOrder[0].tradeId;
        	object.prinamt = object.bussTrdTransferOrder[0].settleAmt;
        	Joyin.ajaxJson(basePath + 'invest/product/productduration/expire/FinProductExpire001/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			WorkFlow.doDeleteList(basePath + 'invest/product/productduration/expire/FinProductExpire001/doRevoke', object, "FinProductExpire002_btn_revoke", "FinProductExpire002");
        		});
        	});
        }else{
        	Joyin.ajaxJson(basePath + 'invest/product/productduration/expire/FinProductExpire002/check', object, function(result) {
        		Joyin.ajaxJson(basePath + 'invest/product/productduration/expire/FinProductExpire002/checkzl', object, function(result) {
        			// Joyin.ajaxJson(basePath + 'invest/product/productduration/expire/FinProductExpire002/checkAmt', object, function(result1) {
        			//if(result1 != null){
        			// Joyin.confirm(result1.data, function(){ 
        			Joyin.confirm(result.data, function() {
        				if(operateType === 'save') {
        					WorkFlow.doSaveList(basePath + 'invest/product/productduration/expire/FinProductExpire002/save', object, "FinProductExpire002_btn_save");
        				}
        				else {
        					WorkFlow.doSubmitList(basePath + 'invest/product/productduration/expire/FinProductExpire002/save', object, "FinProductExpire002_btn_submit");
        				}
        			});
        			//});
        			//}
        			// });
        		});
        	});
        }

}
    var init = function () {
		//
		
       /* window.componentLoadCallback = function(isDone, pagePath, params){
            if(isDone){
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var data = $.serializeToObject("#FinProductExpire002_Form", true);
                    data.counterId = '324';
                    var amt = $('#FinProductExpire002_mCusRevAmt').val();
                    var samt = $('#FinProductExpire002_actCusProfit').val();
                    data.tradeAmt = JoyinMath.doAdd(amt, samt);
                    data.ccy = $('#FinProductExpire002_ccy').val();
                    data.tradeDate = $('#FinProductExpire002_endDate').val();
                    data.finprodType2 = 'F16';
                    data.ps = 'C5';
                    return {
                        'isTotalTrade':'N'
                        ,'bankLendingType':null
                        ,'portfolioId':$("#FinProductExpire002_ptlId").val()
                        ,'deal':data
                    };
                });
            }
        }*/
    };

    var init1 = function () {
//        initTables();
//        finprodIdChangeAction();
        init = true;
    };
    
    //加载利息明细
    var loadSubProdInfoAndShow = function(){
    	var finprodId = $('#FinProductExpire002_SELECT_finprodMarketId').val();
    	var tradeDate = $("#FinProductExpire002_endDate").val();
		var isLay = $("#FinProductExpire002_isLay").val();
    	Joyin.ajax(JoyinUtil.getRealPath('invest/product/productduration/addreduce/FinProductAddReduce002/findReduceInfoData'),
    			{finprodId : finprodId,tradeDate:tradeDate,isLay:isLay}, function(result){
    		if (result){
    		    if(result[0]){
    		        rePayAndIntpFlag = result[0].rePayAndIntpFlag;
    		    }
     		     //周期型产品展示子产品号
    			if($('#FinProductExpire002_dealMode').val()=='10'){
    				loadSubProdInfo.showColumns([{field:'indexNo',visible: true}]);
    	        } else {
    	        	loadSubProdInfo.showColumns([{field:'indexNo',visible: false}]);
    	        }
    			loadSubProdInfo.clear(function () {
					loadSubProdInfo.addRow(result)
                });

    			 var arr = loadSubProdInfo.getAllRows();
    	    	 var interestSum = 0;
    	    	 var actInterestSum = 0;
    	    	 var reduceAmtSum = 0;
    	    	 for ( var i = 0; i <arr.length; i++){
    	  	   		 if(arr[i].interest==""||arr[i].interest==null){
    	  	   			 arr[i].interest = 0;
    	  	   		 }
    	  	   		 if(arr[i].actInterest==""||arr[i].actInterest==null){
    	  	   			 arr[i].actInterest = 0;
    	  	   		 }
    	  	   		 if(arr[i].reduceAmt==""||arr[i].reduceAmt==null){
    	  	   		     arr[i].reduceAmt = 0;
    	  	   		 }
    		  	   	interestSum = JoyinMath.doAdd(interestSum, arr[i].interest, 2, JoyinMath.ROUND_HALF_UP);
    		  	   	actInterestSum = JoyinMath.doAdd(actInterestSum, arr[i].actInterest, 2, JoyinMath.ROUND_HALF_UP);
    		  	    reduceAmtSum = JoyinMath.doAdd(reduceAmtSum, arr[i].reduceAmt, 2, JoyinMath.ROUND_HALF_UP);
    	   		 }
    		   	 $('#FinProductExpire002_cusProfit').val(interestSum);
    		   	 $('#FinProductExpire002_actCusProfit').val(actInterestSum);
    		   	 $('#FinProductExpire002_reduceAmt').val(reduceAmtSum);
    		}
    	});



        if('Y' == $("#FinProductExpire002_isLay").val()){
            loadSubProdInfo.showColumns([{field:'prodStyle',visible: true}]);
        } else {
            loadSubProdInfo.showColumns([{field:'prodStyle',visible: false}]);
        }
    };
    
    /**
	 * 新建页面选择产品代码时联动
	 */
	var finprodIdChangeAction = function(){
		var finprodId = $('#FinProductExpire002_SELECT_finprodMarketId').val();
		if (finprodId){
    		Joyin.ajax(basePath + 'invest/product/productduration/expire/FinProductExpire002/queryProdInfoByProdId', {finprodId : finprodId}, function(result){
    			if (result){
    				$('#FinProductExpire002_prodAbbr').val(result.finprodAbbr);
    				$('#FinProductExpire002_prodName').val(result.finprodName);
    				$('#FinProductExpire002_ccy').val(result.ccy).trigger("change");
    				$('#FinProductExpire002_startDate').val(result.vdateStr).trigger('change');
    				$('#FinProductExpire002_dealMode').val(result.dealMode);
    	    		$('#FinProductExpire002_saleDepartment').val("");
    	    		$('#FinProductExpire002_isLay').val(result.isLay);
    	    		$('#FinProductExpire002_ptlId').val(result.ptlId);
    	    		$('#targetCustomer1').val(result.targetCustomer1).trigger('change');
    				$('#FinProductExpire002_dealMode').val(result.dealMode).trigger("change");
    				$('#FinProductExpire002_saleDepartment').val(result.saleDepartment).trigger("change");
    				
    				$('#FinProductExpire002_mCusRevAmt').val(result.mCusRevAmt).trigger("change");
    				if(pageModel == PAGE_MODEL_NEW){
//	    				if(result.operationType=="01"&&result.dealMode=="01"){
//	    					$('#FinProductExpire002_endDate').val(result.mdateStr).trigger('change');
//	    					$('#FinProductExpire002_endDate').attr("disabled",true);
//	    				}else if(result.dealMode=="01"||result.dealMode=="11"&&result.operationType=="02"){
//	    					$('#FinProductExpire002_endDate').val(result.mdateStr).trigger('change');
//	    					$('#FinProductExpire002_endDate').attr("disabled",false);
//	    				}else if(result.dealMode=="10"||result.dealMode=="12"&&result.operationType=="02"){
//	    					$('#FinProductExpire002_endDate').val(result.mdateStr).trigger('change');
//	    					$('#FinProductExpire002_endDate').attr("disabled",false);
//	    				}else{
//	    					$('#FinProductExpire002_endDate').val(result.mdateStr).trigger('change');
//	    				}
	    				
    					//封闭式是产品到期日，开放式是平台日期
    					if(result.operationType=="01"&&(result.dealMode=="01"||result.dealMode=="11")){
	    					$('#FinProductExpire002_endDate').val(result.mdateStr).trigger('change');
	    					$('#FinProductExpire002_endDate').attr("disabled",true);
	    				}
	    				else{
	    					$('#FinProductExpire002_endDate').val(platdateext).trigger('change');
	    					$('#FinProductExpire002_endDate').attr("disabled",false);
	    				}
	    				
    				}
    				loadSubProdInfoAndShow();
    			}
    		})
    	}else{
    		$('#FinProductExpire002_prodAbbr').val('');
    		$('#FinProductExpire002_prodName').val('');
    		$('#FinProductExpire002_ccy').val('');
    		$('#FinProductExpire002_startDate').val('');
    		$('#FinProductExpire002_dealMode').val('');
    		$('#FinProductExpire002_saleDepartment').val('');
    		$('#FinProductExpire002_endDate').val('');
    		$('#FinProductExpire002_mCusRevAmt').val('');
    	}
	};
	
	//赎回到期日改吧联动交易类型
    var endDateChangeAction = function(){
    	var endDate = $("#FinProductExpire002_endDate").val();
    	var finprodId = $('#FinProductExpire002_SELECT_finprodMarketId').val();
    	//update by leipeng 【收益型产品赎回到期管理新建】：存在一个产品代码为空会造成系统异常的问题
    	if(finprodId){
    		Joyin.ajax(basePath + 'invest/product/productduration/expire/FinProductExpire002/queryProdPs', 
        			{finprodId : finprodId,endDate:endDate}, function(result){
        				$("#FinProductExpire002_ps").val(result).trigger('change');
//    			if (result){
//    				$("#FinProductExpire002_ps").val(result).trigger('change');
//    			} 
            });
        	loadSubProdInfoAndShow();
    	}else{
    		Joyin.alert("产品代码不能为空",7);
    	}
    };
    
	$(function(){
		var operateType = $('#operateType').val();
        if("revoke"==operateType || "delete"==operateType){
            $('#FinProductExpire002_btn_revoke').show();
        }else{
            $('#FinProductExpire002_btn_revoke').hide();
        }
        if($('#workflowId').val()){
        	$('#FinProductExpire002_btn_revoke').hide();
        }
		$("#targetCustomer1Div").hide();
		//$("#FinProductExpire002_SELECT_finprodMarketId").change(changeProdId);
		$("#FinProductExpire002_branch").val(0);
		//指令生成
        $("#BussTrdTransferOrder_btn_generation").on('click', function () {
            Joyin.ajaxJson(basePath+'invest/product/productduration/expire/FinProductExpire002/check2',getParameter(),function(){
                generateSettleOrder();
            });
        });
		
		//产品代码联动事件
		$('#FinProductExpire002_SELECT_finprodMarketId').change(function(){
    		finprodIdChangeAction();
    	});
		
		//赎回到期日改变联动子产品信息事件
		$('#FinProductExpire002_endDate').change(function(){
			endDateChangeAction();
    	});
		
		// 保存
		$('#FinProductExpire002_btn_save').click(function(){
			saveOrSubmit('save');
		});
		// 撤销
        $('#FinProductExpire002_btn_revoke').click(function(){
            saveOrSubmit('revoke');
        });
		// 提交
		$('#FinProductExpire002_btn_submit').click(function(){
			saveOrSubmit('submit');
		});
		init();
		init1();
		
		if (pageModel === '1') {
			calcConditionObjBackUp = stlCommandInput();
        }
	});
}());