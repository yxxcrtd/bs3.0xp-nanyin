/**
 * 产品付费规则调整
 * huwenbin
 */
$(function() {
	var flag = true;
	var flag2 = false;
    function saveOrSubmit(operateType) {
    	
		var flag1 = true;
    	var tableCheckData = table.getAllCheckedRows();
        var object = $.serializeToObject('#submitForm');
        var tableDatas=table.getAllCheckedRows();
        object.pageModel = pageModel;
        object.finprodId = $("#finprodId").val();
        object.operateType = operateType;
        object.pageId = "FinPayPlanAdjustment002";
        object.vdate = Date.ofDate($("#vdate").val()).format("yyyy-MM-dd");
        object.mdate = Date.ofDate($("#mdate").val()).format("yyyy-MM-dd");
        object.liquidationDate = Date.ofDate($("#liquidationDate").val()).format("yyyy-MM-dd");
        if(WORK_FLAG==false){
			tableDatas=table.getAllRows();
		}
        if ((!tableCheckData || tableCheckData.length == 0)&& WORK_FLAG==true) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        else {
        	object.selectedItem = tableDatas;
        	$.each(object.selectedItem,function(index,obj){
        		obj.eAccrDate = Date.ofDate(obj.eAccrDate).format("yyyy-MM-dd");
        		obj.provisionFirstDay = Date.ofDate(obj.provisionFirstDay).format("yyyy-MM-dd");
        		obj.finprodId = $("#finprodId").val();
        		obj.parentPageId = 'FinPayPlanAdjustment002';
        		obj.firstCalEndDate = Date.ofDate(obj.firstCalEndDate).format("yyyy-MM-dd");
        		obj.dealCalId = $("#dealCalId").val();
        		obj.vdate = Date.ofDate($("#vdate").val()).format("yyyy-MM-dd");
        		obj.mdate = Date.ofDate($("#mdate").val()).format("yyyy-MM-dd");
        		obj.liquidationDate = Date.ofDate($("#liquidationDate").val()).format("yyyy-MM-dd");
        		obj.profitType = $("#profitType").val();
        		if(obj.planStartDay){
        			obj.planStartDay = Date.ofDate(obj.planStartDay).format("yyyy-MM-dd");
        		}
        		if(obj.planEndDay){
        			obj.planEndDay = Date.ofDate(obj.planEndDay).format("yyyy-MM-dd");
        		}
        		//分层产品绑定子产品产品信息
//        		if(!obj.prodStyle){
//        			obj.dealCalId = $("#dealCalId").val();
//            		obj.vdate = $("#vdate").val();
//            		obj.mdate = $("#mdate").val();
//            		obj.liquidationDate = $("#liquidationDate").val();
//            		obj.profitType = $("#profitType").val();
//        		}else{
//        			obj.vdate = Date.ofDate(obj.vdate).format("yyyy-MM-dd");
//    				obj.mdate = Date.ofDate(obj.mdate).format("yyyy-MM-dd");
//    				obj.liquidationDate = Date.ofDate(obj.liquidationDate).format("yyyy-MM-dd");
//    				//分层产品使用子产品ID
//    				obj.finprodId = obj.prodStyle
//        		}
        	})
        }
        for(var i =0; i<tableCheckData.length; i++){
    		if(tableCheckData[i].feeRate){
    			if(tableCheckData[i].feeRate.length > 30){
    				Joyin.error("费率的长度不能超过30！");
    				return;
    			}
    		}
		}
        var rlenth=table.getAllRows().length;
        var mess='';
        if(tableCheckData.length<rlenth && WORK_FLAG==true){mess=JoyinUtil.getMessage("FIN_PROD_I_0013")}else {mess='已勾选所有费用品种，是否继续报价处理'};
        Joyin.confirm(mess,function(){
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			if(operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/save', object, "FinPayPlanAdjustment002_btn_save","FinPayPlanAdjustment002");
            	    }
            	    else {
            	    	WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/save', object, "FinPayPlanAdjustment002_btn_submit","FinPayPlanAdjustment002");
            	    }
            	});
            });
        },function(){
            return;
        });
    };
    var buttons = [{ text: '&#xe614;',ignore:true, clas: 'preview', title: '预览' }, { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
    /**
     * 页面加载初始化
     */
    var table = $("#dataTable").joyintable({
			ajax: {
            url: JoyinUtil.getRealPath('invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/getFeeInfo'),
            type: 'POST',
            data: function() {
                return {finprodId: $("#finprodId").val(),
                		workflowId: $('#workflowId').val(),
                		pageModel:pageModel,
                		flag:flag
                	};
            }
			},
			searchOnInit: true,
	        loadonce: true,
	        rowno: true,
	        page: false,
	        checkbox:true,
	        addbtn: true,
	        height : 200,
        cols:[
        	{field:'feeId', width:200, title: 'FinPayPlanAdjustment002.feeName', sortable: false,
            	edit:true,
            	editOption:{type:'select', url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/queryFeeInfoList')}}
            ,{field:'feeType', width:200, title: 'BussFinProductFeeInfo.feeType', sortable: false,visible:false}//visible:false默认不展示
            ,{field:'prodStyle', width:100, title: 'FinPayPlanAdjustment002.prodStyle', sortable: false, edit:true,
            	visible:false,
            	edit:true,
            	editOption:{type:'select',parentIds:'finprodMarketId',formatoption:true, url: JoyinUtil.getRealPath('/invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/getSelectProdStyle')}}
            ,{field:'feeRate', width:100, title: 'FinPayPlanAdjustment002.feeRate', sortable: false, type:'number', edit:true, digit:6}
            //,{field:'basicBill',visible : false}
            ,{field:'basicBill', width:150, title: 'FinPayPlanAdjustment002.basicBill', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BILL_BASE_FEE_KIND}
            }
            ,{field:'basis', width:100, title: 'FinPayPlanAdjustment002.basis', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BASIS_SELECT}
            }
            ,{field:'netAssetValueDay', width:150, title: 'FinPayPlanAdjustment002.netAssetValueDay', sortable: false,
        		edit:true,
				editOption:{type:'select', result: BASE_DATE_TYPE}
            }
            ,{field:'firstBaseType', width:100, title: 'FinPayPlanAdjustment002.firstBaseType', sortable: false, 
            	edit:true,
            	editOption:{type:'select',result:BILL_BASE_FEE_KIND}
            }
            ,{field:'planStartDay', width:150, title: 'FinPayPlanAdjustment002.next.planStartDay', sortable: false,
            	edit:true,
            	type:'date'
            }
            ,{field:'planEndDay', width:150, title: 'FinPayPlanAdjustment002.next.planEndDay', sortable: false,
            	edit:true,
            	type:'date'
            }
            ,{field:'provisionFirstDay', width:150, title: 'FinPayPlanAdjustment002.provisionFirstDay', sortable: false,
            	edit:true,
            	type:'date'
            }
            ,{field:'eAccrDate', width:150, title: 'FinPayPlanAdjustment002.eAccrDate', sortable: false,
            	edit:true,
            	type:'date'
            }
            ,{field:'payFeeRule', width:150, title: 'FinPayPlanAdjustment002.payFeeRule', sortable: false,
            	edit:true,
            	editOption:{type:'select', result: PAY_RULE}
            }
            ,{field:'calcFeeTradeRule', width:150, title: 'FinPayPlanAdjustment002.accrTdayRule', sortable: false,
            	edit:true,
            	editOption:{type:'select',childrenIds : 'payFeeTradeRule',id: "calcFeeTradeRule",formatoption:true,result: TDAY_RULE}
            }
            ,{field:'payFeeTradeRule', width:150, title: 'FinPayPlanAdjustment002.payTdayRule', sortable: false,
            	edit:true,
            	editOption:{type:'select',parentIds:'calcFeeTradeRule',id :"payFeeTradeRule", formatoption:true,result: TDAY_RULE}
            }
            ,{field:'firstCalEndDate', width:150, title: 'FinPayPlanAdjustment002.firstPayDate', sortable: false,
            	edit:true,
            	type:'date'
            }
            ,{field:'orgType', visible : false}
            ,{field:'effDate', visible : false,type:'date'}
            ,{field:'orgType2', visible : false}
            ,{field:'chargeType', visible : false}
            ,{field:'cashId', visible : false}
            ,{field:'dealCalId', visible : false}
            ,{field:'vdate', visible : false,type:'date'}
            ,{field:'mdate', visible : false,type:'date'}
            ,{field:'liquidationDate', visible : false,type:'date'}
            ,{field:'profitType', visible : false}
            ,{field:'remark', width:150, title: 'FinPayPlanAdjustment002.remark',sortable: false ,edit:true,type:'input',visible : false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
        ],
        beforeEditCell : function(preVal, field, rowData){
        	//付费规则为 不规则
        	if (rowData.payFeeRule ===PAY_RULE_08 && (field ==='calcFeeTradeRule' || field === 'payFeeTradeRule' || field === 'firstCalEndDate' || field === 'planStartDay' || field === 'planEndDay')){
        		return false;
        	}
        	else if (field === "feeRate" && rowData.feeType === FEE_TYPE_05NAME){
        		return false;
        	}
        	
        	//修改不允许修改参数
        	// modify by lizhi TISHKBS-719 【产品要素调整/付费计划调整】付费规则调整时，将原来不规则费用调整为月付时，首次付费日无法填写，但是提交不了 start
        	if(rowData.cashId && ( /**field == "firstCalEndDate" ||*/ field == "feeId" || field == "prodStyle" || field == "provisionFirstDay" || field == "eAccrDate" || field == "basicBill")){
    		// modify by lizhi TISHKBS-719 【产品要素调整/付费计划调整】付费规则调整时，将原来不规则费用调整为月付时，首次付费日无法填写，但是提交不了 start
        		return false;
        	}
        	//新建不允许修改参数
        	if(!rowData.cashId && (field == "planStartDay" || field == "planEndDay")){
        		return false;
        	}
        	//首次计费基数不允许修改
        	if(field == "firstBaseType"){
        		return false;
        	}

        	//费用收费类型
            if(rowData.chargeType === CHARGE_TYPE_02 && field != 'feeId'){
               return false;
            }
            
        	return true;
        },
        editCell : function(value, data, field, cell, column){
        	if (field === "feeId"){
        		var rowData = {};
        		rowData.feeType = ""; // 费用类型
        		rowData.basicBill = ""; // 计费基数
        		rowData.basis = ""; // 计算基础
        		if (value != null && value != ''){
        			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfoCommonRate/queryMstFeeinfoByFeeId'), {feeId : data.feeId, finprodMarketId : ''}, function(result){
        				if (result){
        					// 差额中收时费率(%)清空，不可编辑
        					if (result.feeType === FEE_TYPE_05){
        						rowData.feeRate = '';
        					}
        					if(result.feeType){
        						rowData.feeType = result.feeType;
        					}
        					if(result.basicBill){
        						rowData.basicBill = result.basicBill;
        						//如果计费基础为存续本金  净值日类型默认为当日
        						if(result.basicBill === BASIC_BILL_04){
        						    rowData.netAssetValueDay = BASE_DATE_TYPE_01;
        						} else {
        						    rowData.netAssetValueDay = BASE_DATE_TYPE_02;
        						}
        					}
        					if(result.basis){
        						rowData.basis = result.basis;
        					} 
        	    			rowData.orgType = result.orgType;
        	    			rowData.orgType2 = result.orgType2;
        	    			rowData.chargeType = result.chargeType;
        	    			rowData.remark = result.remark;
        	    			//收费规则不为 不规则
        	    			if(result.chargeType != CHARGE_TYPE_02){
            	    			//计提结束日=到期日-1
            	        		if($("#mdate").val()){
            	        			rowData.eAccrDate=addDate($("#mdate").val(),-1);
            	        		}
            	        		if($("#vdate").val()){
            	        			rowData.provisionFirstDay=$("#vdate").val();
            	        		}
        	    			}
        	    			//首次计费基数联动
        	        		var provisionFirstDay = Date.ofDate(rowData.provisionFirstDay).format('yyyy-MM-dd');
        	        		var vdate = Date.ofDate($("#vdate").val()).format('yyyy-MM-dd');
        	        		if((rowData.netAssetValueDay === BASE_DATE_TYPE_02 || rowData.netAssetValueDay === BASE_DATE_TYPE_03) && provisionFirstDay === vdate && rowData.basicBill === BILL_BASE_FEE_KIND_03){
        	        			rowData.firstBaseType = BASIC_BILL_04;
        	        		}else{
        	        			rowData.firstBaseType = '';
        	        		}
        	        		table.renderRow(data.LAY_TABLE_INDEX, rowData);
        				}
        			})
        		}
        	}
        	//产品款型带出子产品信息
        	else if (field === "prodStyle"){
//        		if(data.prodStyle){
//            		Joyin.ajaxSync(JoyinUtil.getRealPath('/invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/selectFinProductByFinprodId?finprodId='+data.prodStyle),'', function(result) {
//            			data.vdate = Date.ofDate(result.vdate).format("yyyy-MM-dd");
//            			data.mdate = Date.ofDate(result.mdate).format("yyyy-MM-dd");
//            			data.liquidationDate = Date.ofDate(result.liquidationDate).format("yyyy-MM-dd");
//            			data.dealCalId = result.calendarId;
//            			data.profitType = result.profitType;
//            			data.finprodId = result.finprodId;
//            			table.renderRow(data.LAY_TABLE_INDEX, data);
//            		});
//            	}
        	}
        	
        	else if(field === "netAssetValueDay"){
        		//费用信息计费基数为资产净值时,资产净值日不能为当日
        		if(data.basicBill === BILL_BASE_FEE_KIND_03 && data.netAssetValueDay === BASE_DATE_TYPE_01){
        	        Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0113') ,function(){
        	            var rowData = {};
        	            rowData.netAssetValueDay = "";
        	            table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
        	    }
        		//首次计费基数联动
        		var provisionFirstDay = Date.ofDate(data.provisionFirstDay).format('yyyy-MM-dd');
        		var vdate = Date.ofDate($("#vdate").val()).format('yyyy-MM-dd');
        		if((data.netAssetValueDay === BASE_DATE_TYPE_02 || data.netAssetValueDay === BASE_DATE_TYPE_03) && provisionFirstDay === vdate && data.basicBill === BILL_BASE_FEE_KIND_03){
        			var rowData = {};
        			rowData.firstBaseType = BASIC_BILL_04;
        			table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}else{
        			var rowData = {};
        			rowData.firstBaseType = '';
        			table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}
        	//付费规则
        	else if(field === "payFeeRule"){
        		var rowData = {};
        		//费用规则选择联动
        		payFeeRuleChange(value,rowData,data);
        		//更新表格
        		table.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}
        	else if(field === 'provisionFirstDay'){
        		var vdate = Date.ofDate($("#vdate").val()).format('yyyy-MM-dd');
        		var provisionFirstDay = Date.ofDate(data.provisionFirstDay).format('yyyy-MM-dd');
        		if((data.netAssetValueDay === BASE_DATE_TYPE_02 || data.netAssetValueDay === BASE_DATE_TYPE_03) && provisionFirstDay === vdate && data.basicBill === BILL_BASE_FEE_KIND_03){
        			var rowData = {};
        			rowData.firstBaseType = BASIC_BILL_04;
	        		table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}else{
        			var rowData = {};
        			rowData.firstBaseType = '';
        			table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}
        	else if (field === 'basicBill'){
        		var vdate = Date.ofDate($("#vdate").val()).format('yyyy-MM-dd');
        		var provisionFirstDay = Date.ofDate(data.provisionFirstDay).format('yyyy-MM-dd');
        		if((data.netAssetValueDay === BASE_DATE_TYPE_02 || data.netAssetValueDay === BASE_DATE_TYPE_03) && provisionFirstDay === vdate && data.basicBill === BILL_BASE_FEE_KIND_03){
        			var rowData = {};
        			rowData.firstBaseType = BASIC_BILL_04;
        			table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}else{
        			var rowData = {};
        			rowData.firstBaseType = '';
        			table.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}
        	else if(field === 'payFeeTradeRule'){
        		data.payFeeTradeRule = value;
        		table.renderRow(data.LAY_TABLE_INDEX, data);
        	}
        	
        }
    });
    // 日期加减
    function addDate(date,days){
        var d=Date.ofDate(date); 
        d.setDate(d.getDate()+days); 
        var m=d.getMonth()+1; 
        if(m<10){
        	m='0'+m;
        }
        var day = d.getDate();
        if(day<10){
        	day = '0'+d.getDate();      	
        }
        return d.getFullYear()+'-'+m+'-'+day; 
    };
	//产品代码画面联动
	$("#finprodMarketId").change(function(){
		if(!$("#finprodMarketId").val()) return; 
		$("#finprodAbbr").val($('#finprodMarketId').select2('data')[0].text);
		var object = $('#finprodMarketId').select2('data')[0].object;
		if(object){
			var str = object.split(',');
			if(str != null && str.length==6){
				if(str[1]){
					$("#vdate").val(Date.ofDate(str[1]).format('yyyy-MM-dd')).trigger('change');
				}
				if(str[2]){
					$("#mdate").val(Date.ofDate(str[2]).format('yyyy-MM-dd')).trigger('change');
				}
				if(str[5]){
					$("#liquidationDate").val(Date.ofDate(str[5]).format('yyyy-MM-dd')).trigger('change');
				}
				$("#dealCalId").val(str[4]).trigger('change');
				$("#profitType").val(str[3]).trigger('change');
				$("#finprodId").val(str[0]).trigger('change');
				finprodId = str[0];
			}
		}
		//分档
		if($('#finprodMarketId').select2('data')[0].col == 'Y'){
			if(table.table){
			    table.showColumns([{field:'prodStyle', visible: true}]);
    		}
		    $("#isLay").val('Y').trigger('change');
		} else {
			if(table.table){
				table.showColumns([{field:'prodStyle', visible: false}]);
    		}
			$("#isLay").val('N').trigger('change');
		}
	});
	 /**
     * 费用规则选择联动
     */
    function payFeeRuleChange(value, rowData,data){
         //不规则
        if(value === PAY_RULE_08){
            rowData.firstCalEndDate = "";
            rowData.calcFeeTradeRule = "";
            rowData.payFeeTradeRule = "";
            rowData.planStartDay = "";
            rowData.planEndDay = "";
        } else if (value === PAY_RULE_07) {
        	rowData.firstCalEndDate = rowData.mdate;
        }
        //起息日到期日必填
        if($("#vdate").val() && $("#mdate").val()){
            //非不规则
            if(value !=PAY_RULE_08) {
                //首次计费结束日先设置为到期前一天
                rowData.firstCalEndDate = $("#mdate").val();
                // add by lizhi TISHKBS-719 【产品要素调整/付费计划调整】付费规则调整时，将原来不规则费用调整为月付时，首次付费日无法填写，但是提交不了 start
                if(data.cashId){
                	data.firstCalEndDate = $("#mdate").val();
                }
                // add by lizhi TISHKBS-719 【产品要素调整/付费计划调整】付费规则调整时，将原来不规则费用调整为月付时，首次付费日无法填写，但是提交不了 end
            }
            //首次计提日
            //rowData.provisionFirstDay = $("#vdate").val();
            //日期单位    
            var strInterval = '';
            //月
            if(value === PAY_RULE_03){
                strInterval = 'm'; 
            } 
            //季
            else if(value === PAY_RULE_04){
                strInterval = 'q'; 
            }
            //半年
            else if(value === PAY_RULE_06){
                strInterval = 'q'; 
            }
            //年
            else if(value === PAY_RULE_05){
                strInterval = 'y'; 
            } 
            if(strInterval != ''){
                //首次计费结束日
                var firstCalEndDate = Date.ofDate($("#vdate").val()).DateAdd(strInterval,1);
                
                // 半年
                if (value === PAY_RULE_06) {
                	firstCalEndDate = firstCalEndDate.DateAdd(strInterval,1);
                }
                
                if(firstCalEndDate.DateDiff("d" ,$("#mdate").val()) > 0){
                    rowData.firstCalEndDate= firstCalEndDate.Format('yyyy-MM-dd');
                }
            }
            if(data.cashId){
            	rowData.firstCalEndDate = data.firstCalEndDate;
            	rowData.provisionFirstDay = data.provisionFirstDay;
            }
        }
    }
    /*
     * 绑定表格事件
     */
//	var table = $("#dataTable").joyintable(options);
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
    	if(!data.cashId){
    		table.removeRow(data);
    	}else{
    		Joyin.error("已存在费用不可删除!");
    	}
    });
    /**
     *查看付费计划
    */
    table.bind('click', '.preview', function(data, index) {
    	data.pageId = "FinPayPlanAdjustment002";
    	data.isLay = $("#isLay").val();
    	data.finprodId = $("#finprodId").val();
    	data.parentPageId = 'FinPayPlanAdjustment002';
    	data.firstCalEndDate = Date.ofDate(data.firstCalEndDate).format("yyyy-MM-dd");
    	data.eAccrDate = Date.ofDate(data.eAccrDate).format("yyyy-MM-dd");
    	data.provisionFirstDay = Date.ofDate(data.provisionFirstDay).format("yyyy-MM-dd");
    	data.dealCalId = $("#dealCalId").val();
    	data.vdate = Date.ofDate($("#vdate").val()).format("yyyy-MM-dd");
    	data.mdate = Date.ofDate($("#mdate").val()).format("yyyy-MM-dd");
    	data.liquidationDate = Date.ofDate($("#liquidationDate").val()).format("yyyy-MM-dd");
    	data.profitType = $("#profitType").val();
    	if(data.planStartDay){
    		data.planStartDay = Date.ofDate(data.planStartDay).format("yyyy-MM-dd");
    	}
    	if(data.planEndDay){
    		data.planEndDay = Date.ofDate(data.planEndDay).format("yyyy-MM-dd");
    	}
    	data.operateType="preview";
    	/*if(!data.prodStyle){
    		data.dealCalId = $("#dealCalId").val();
        	data.vdate = $("#vdate").val();
        	data.mdate = $("#mdate").val();
        	data.liquidationDate = $("#liquidationDate").val();
        	data.profitType = $("#profitType").val();
    	}else{
    		obj.vdate = Date.ofDate(obj.vdate).format("yyyy-MM-dd");
			obj.mdate = Date.ofDate(obj.mdate).format("yyyy-MM-dd");
			obj.liquidationDate = Date.ofDate(obj.liquidationDate).format("yyyy-MM-dd");
			//分层产品使用子产品ID
			obj.finprodId = obj.prodStyle
    	}*/
    	if(data.chargeType === CHARGE_TYPE_02){
    		Joyin.error("不规则费用不生成付费计划");
    		return;
    	}
    	Joyin.ajaxJson(JoyinUtil.getRealPath('invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/check'), data, function(result){
	        // 生成付费计划
	        Joyin.dialog(JoyinUtil.getLabel("FinProductFeePlanInfo"), JoyinUtil.getRealPath('component/prod/FinProductFeePlanInfo/init'),
	            "45%", "50%", {pageModel:PAGE_MODEL_REFERENCE, feeInfo: JSON.stringify(data)})
    	})
    });
    

    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#finprodMarketId').attr("disabled",true);
    			//$('#effDate').attr("disabled",true);
    		}
    		
    	}
    	if($('#finprodMarketId').select2('data')[0].col == 'Y'){
    		if(!table.table){
    			table.load();
    		}
    		table.showColumns([{field:'prodStyle', visible: true}]);
    	}
    }
    
    $(function(){
    	/**
         * 画面加载完成
         */
    	init();
        // 绑定按钮事件
        $('#FinPayPlanAdjustment002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinPayPlanAdjustment002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());