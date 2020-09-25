(function() {
    
    // 保存或者提交按钮按下处理
    var onSaveOrSubmitAction = function(operateType){
        var object = $.serializeToObject('#productModuleForm', false);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.templateId = object.productModuleInfo.templateId;
        if(object.productModuleInfo && object.productModuleInfo.entruster){
        	object.productModuleInfo.entruster = object.productModuleInfo.entruster.join(",");
        }
        if(object.productModuleInfo && object.productModuleInfo.saleDepartment){
    		object.productModuleInfo.saleDepartment = object.productModuleInfo.saleDepartment.join(",");
    	}
        if(object.productModuleInfo && object.productModuleInfo.sameOrg){
    		object.productModuleInfo.sameOrg = object.productModuleInfo.sameOrg.join(",");
    		object.bussFinProductSaleParamDto.sameOrg = object.productModuleInfo.sameOrg;
    	}
        //募集要素
        if(object.bussFinProductSaleParamDto){
        	if(object.bussFinProductSaleParamDto.saleArea){
        		object.bussFinProductSaleParamDto.saleArea = object.bussFinProductSaleParamDto.saleArea.join(",");
        	}
        	if(object.bussFinProductSaleParamDto.investorType){
        		object.bussFinProductSaleParamDto.investorType = object.bussFinProductSaleParamDto.investorType.join(",");
        	}
        	/*if(object.bussFinProductSaleParamDto.sameOrg){
        		object.bussFinProductSaleParamDto.sameOrg = object.bussFinProductSaleParamDto.sameOrg.join(",");
        	}*/
        	if(object.bussFinProductSaleParamDto.saleChannel){
        		object.bussFinProductSaleParamDto.saleChannel = object.bussFinProductSaleParamDto.saleChannel.join(",");
        	}
        	
        }
        if($("#productModuleInfo_operationType").val() ==ISSUE_MODE_02){
        	object.bussFinProductOpenPlanDto = null;
        	object.bussFinProductOpenPlanRedDto = null;
        }
        //收益型浮动管理费为空
        if($("#productModuleInfo_profitType").val() == PROFIT_TYPE_02){
        	object.bussBenchMarkFloatInfoDto = null;
        	object.bussFinProductFloatFeeDto = null;
        }
        var openMode ="";
        if (object.bussFinProductOpenPlanDto){
        	openMode =object.bussFinProductOpenPlanDto.openMode;
        }
        //开放计划
        if(object.bussFinProductOpenPlanDto){
        	if (object.bussFinProductOpenPlanDto.dayOfWeek){
        		object.bussFinProductOpenPlanDto.dayOfWeek = object.bussFinProductOpenPlanDto.dayOfWeek.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.dayOfMonth){
        		object.bussFinProductOpenPlanDto.dayOfMonth = object.bussFinProductOpenPlanDto.dayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.workdayOfMonth){
        		object.bussFinProductOpenPlanDto.workdayOfMonth = object.bussFinProductOpenPlanDto.workdayOfMonth.join(',');
        	}
        	if (!object.bussFinProductOpenPlanDto.hdayOpenFlag) {
        		object.bussFinProductSaleParamDto.hdayOpenFlag = 'N';
            }
        	if(object.operationType) {
        		object.bussFinProductOpenPlanDto.operationType = object.operationType;//发行方式不为空时，将发行方式传递到开放计划DTO里
        	}
        	if (object.bussFinProductOpenPlanDto.weekOfMonth){
        		object.bussFinProductOpenPlanDto.weekOfMonth = object.bussFinProductOpenPlanDto.weekOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanDto.workdayOfWeek){
        		object.bussFinProductOpenPlanDto.workdayOfWeek = object.bussFinProductOpenPlanDto.workdayOfWeek.join(',');
        	}
        }
        //赎回计划
        if (object.bussFinProductOpenPlanRedDto){
        	var openRedMode = object.bussFinProductOpenPlanRedDto.openMode;
        	/*if(openMode!='02'&&object.dealMode != dealMode_10){
        		return false;
        	}*/
        	if (object.bussFinProductOpenPlanRedDto.dayOfWeek){
        		object.bussFinProductOpenPlanRedDto.dayOfWeek = object.bussFinProductOpenPlanRedDto.dayOfWeek.join(',');
        	}
        	if (object.bussFinProductOpenPlanRedDto.dayOfMonth){
        		object.bussFinProductOpenPlanRedDto.dayOfMonth = object.bussFinProductOpenPlanRedDto.dayOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanRedDto.workdayOfMonth){
        		object.bussFinProductOpenPlanRedDto.workdayOfMonth = object.bussFinProductOpenPlanRedDto.workdayOfMonth.join(',');
        	}
        	if (!object.bussFinProductOpenPlanRedDto.hdayOpenFlag) {
        		object.bussFinProductOpenPlanRedDto.hdayOpenFlag = 'N';
            }
        	if (object.bussFinProductOpenPlanRedDto.weekOfMonth){
        		object.bussFinProductOpenPlanRedDto.weekOfMonth = object.bussFinProductOpenPlanRedDto.weekOfMonth.join(',');
        	}
        	if (object.bussFinProductOpenPlanRedDto.workdayOfWeek){
        		object.bussFinProductOpenPlanRedDto.workdayOfWeek = object.bussFinProductOpenPlanRedDto.workdayOfWeek.join(',');
        	}
        }
        //回补信息
        if(object.productModuleInfo.isAnaplerotic != 'Y'){
        	object.finProductAnapleroticTabInfoDtoList = '';
        }
        //报送信息
        if (object.bussRepFinProductInfoDto){
        	// 投资者风险偏好
        	if (object.bussRepFinProductInfoDto.repRiskType){
        		object.bussRepFinProductInfoDto.repRiskType = object.bussRepFinProductInfoDto.repRiskType.join(',');
        	}
        	// 产品增信机构类型
        	if (object.bussRepFinProductInfoDto.repCreditType){
        		object.bussRepFinProductInfoDto.repCreditType = object.bussRepFinProductInfoDto.repCreditType.join(',');
        	}
            // 产品投资国家或地区
        	if (object.bussRepFinProductInfoDto.repInvestCountry){
        		object.bussRepFinProductInfoDto.repInvestCountry = object.bussRepFinProductInfoDto.repInvestCountry.join(',');
        	}
            // 境外托管机构国别
        	if (object.bussRepFinProductInfoDto.repOutDepCtry){
        		object.bussRepFinProductInfoDto.repOutDepCtry = object.bussRepFinProductInfoDto.repOutDepCtry.join(',');
        	}
        	//REP_IS_YIELD是否有预期收益率
        	if(object.bussRepFinProductInfoDto.repIsYield && object.bussRepFinProductInfoDto.repIsYield === NO){
        	    object.bussRepFinProductInfoDto.repIsYield = NO_01;
        	}
        	//REP_TERM_RIGHT_FLAG发行机构提前终止权标识、REP_CUS_REDEEM_FLAG客户赎回权标识、REP_CREDIT_FLAG产品增信标识
        	if(object.bussRepFinProductInfoDto.repTermRightFlag && object.bussRepFinProductInfoDto.repTermRightFlag === NO){
        	    object.bussRepFinProductInfoDto.repTermRightFlag = NO_01;
        	}
        	//REP_CUS_REDEEM_FLAG客户赎回权标识、REP_CREDIT_FLAG产品增信标识
        	if(object.bussRepFinProductInfoDto.repCusRedeemFlag && object.bussRepFinProductInfoDto.repCusRedeemFlag === NO){
        	    object.bussRepFinProductInfoDto.repCusRedeemFlag = NO_01;
        	}
        	//REP_CREDIT_FLAG产品增信标识
        	if(object.bussRepFinProductInfoDto.repCreditFlag && object.bussRepFinProductInfoDto.repCreditFlag === NO){
        	    object.bussRepFinProductInfoDto.repCreditFlag = NO_01;
        	}
        	//计划募集金额
        	if(object.bussFinProductSaleParamDto) {
        	    object.bussRepFinProductInfoDto.repRaiseAmtPlan  = object.bussFinProductSaleParamDto.raiseAmtPlan;
        	 }
        }
        
        //分层列表
        object.bussFinProductLayeredInfoDto = bussFinProductLayeredInfoTable.getAllRows();
        
        //回补信息
        var finProductAnapleroticData = bussFinProductAnapleroticInfoTable.getAllRows();
		if($('#FinNetValueProductBuild002_isAnaplerotic').val()=='Y'&& (finProductAnapleroticData && finProductAnapleroticData.length>1)
			&&object.dealMode != DISPOSE_PATTERN_11){
             Joyin.alert("回补信息不能大于一条数据");
             return;
         }
         if(finProductAnapleroticData){
             for(var i=0;i<finProductAnapleroticData.length;i++){
                 var finProductAnapleroticInfoDtoData = finProductAnapleroticData[i].finProductAnapleroticInfoDto;
                 if((typeof finProductAnapleroticInfoDtoData) != 'object') {
                	 finProductAnapleroticData[i].finProductAnapleroticInfoDto = JSON.parse(finProductAnapleroticInfoDtoData);
             	 }
             }
         }
         object.finProductAnapleroticTabInfoDtoList = finProductAnapleroticData;
         
        Joyin.ajaxJson(basePath + '/invest/product/productelement/module/FinProductModule002/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + '/invest/product/productelement/module/FinProductModule002/save', object, "FinProductModule002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + '/invest/product/productelement/module/FinProductModule002/save', object, "FinProductModule002_btn_submit");
                }
            })
        })
    }
    
    /*window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if (!FinProductCommon.getElementById('finprodMarketId').val()) {
                // 处理模式默认普通
            	FinProductCommon.getElementById('dealMode').val(DISPOSE_PATTERN_01).trigger('change');
                // 收益类型默认预期收益型
            	FinProductCommon.getElementById('profitType').val(PROFIT_TYPE_02).trigger('change');
                // 产品收益标识默认非保本浮动收益
            	FinProductCommon.getElementById('profitFlag').val(PRODPROFITFLAG_03).trigger('change');
                // 发行方式默认单一
            	FinProductCommon.getElementById('entrustType').val(SCHEDUAL_ISSUE_02).trigger('change');
                // 计息基础默认A/365
            	FinProductCommon.getElementById('basis').val(BASIS_A_365).trigger('change');
                // 币种默认人民币
            	FinProductCommon.getElementById('ccy').val(CNY_CNY).trigger('change');
                // 募集方式默认公募
            	FinProductCommon.getElementById('issueType').val(RAISETYPE_01).trigger('change');
                // 投资性质默认固定收益类
            	FinProductCommon.getElementById('investNature').val(INVEST_TYPE_01).trigger('change');
            }
        }
    }
    
    
    /**
	 * 模板代码快录
	 */
	var moduleIdQuickInputAction = function(){
		var finprodMarketId = FinProductCommon.getElementById('finprodMarketId').val()
		if (finprodMarketId){
			FinProductCommon.initPageInfoAction(pageId, finprodMarketId, finprodTypeTmpl);
		}else{
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038', JoyinUtil.getLabel('FinProductModule002.templetId')))
		}
	}
	
	//业绩基准表格
	var bussBenchMarkInfoTable = $('#bussBenchMarkLayeredInfoDto_markInfo').joyintable({
		data:bussBenchMarkLayeredInfoDtoList==null?[]:bussBenchMarkLayeredInfoDtoList,
        searchOnInit: true,
        loadonce: true,
        checkbox:false,
        page: false,
        height : 200,
        cols:[
            {field:'publishType',title: 'BussBenchMarkInfo.publishType', visible: false}
              ,{field:'prodStyle', width:200, title: 'BussBenchMarkInfo.prodStyle', visible: true,sortable: false, edit:true,editOption:{type:'select',formatoption:true,result: PROD_STYLE}}
              ,{field:'investmentCycle', width:200, title: 'BussBenchMarkInfo.investmentCycle', visible: true,sortable: false, edit:true,editOption:{type:'select',formatoption:true,result: PROD_STYLE}}
              ,{field:'rateType', width:150, title: 'BussBenchMarkInfo.rateType', sortable: false, edit:true,editOption:{type:'select',formatoption:true,result: INT_TYPE_2}}
              ,{field:'rate', width:150, title: 'BussBenchMarkInfo.rate', sortable: false, type:'number',digit:4}
              ,{field:'floatRate', sortable:false, width:150 ,title: 'BussBenchMarkInfo.indexId'}
              ,{field:'resetType', width:200,title: 'BussBenchMarkInfo.resetType', sortable: false, edit:true,editOption:{type:'select',formatoption:true,result: EFFECTIVE_RULE}}
              ,{field:'indexId',title: 'BussBenchMarkInfo.indexId', visible: false}
              ,{field:'coefficient',title: 'BussBenchMarkInfo.coefficient', visible: false}
              ,{field:'spreadRate',title: 'BussBenchMarkInfo.spreadRate', visible: false}
              ,{field:'rateLower', width:150,title: 'BussBenchMarkInfo.rateLower', visible: false}
              ,{field:'rateLimit', width:150,title: 'BussBenchMarkInfo.rateLimit', visible: false}
              ,{field:'lowLimitRate', width:200,title: 'FinProductBmQuotation001.lowLimitRate'}
              ,{field:'operation', title: 'SysCommon.operation',type:'action', buttons: ['delt']}
          ],
          done : function(table, allData){
              //设置都不可编辑
               var allrowData =  bussBenchMarkInfoTable.options.cols;
               for(var i in allrowData){
                     allrowData[i].edit = false;
               }
          }
    });
	
	/**
	 * 绑定删除事件
	 */
	bussBenchMarkInfoTable.bind('click', '.delt', function(data, index) {
		bussBenchMarkInfoTable.removeRow(data);
	});
	
    //业绩基准点击“新增”按钮，在业绩基准信息列表中展示该条信息，并重置上方要素信息	
	$('#bussBenchMarkLayeredInfoDto_btn_create').click(function() {
	    var tableArry = bussBenchMarkInfoTable.getAllRows();
	    var flag = false;
	    if(tableArry && tableArry.length > 0){
	    	if($('#productModuleInfo_dealMode').val() === dealMode_11){
	    		if((!$('#bussBenchMarkLayeredInfoDto_prodStyle').val() || $('#bussBenchMarkLayeredInfoDto_prodStyle').val() =='')){
	                Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_005"));
	                return;
	            }
		        _.each(tableArry,function (data){
		            if($('#bussBenchMarkLayeredInfoDto_prodStyle').val() && $('#bussBenchMarkLayeredInfoDto_prodStyle').val() == data.prodStyle){
		                Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_006"));
		                flag = true;
		                return; 
		            }
		        })
	    	}
	    	else if($('#productModuleInfo_dealMode').val() === dealMode_10){

	    		if((!$('#bussBenchMarkLayeredInfoDto_investmentCycle').val() || $('#bussBenchMarkLayeredInfoDto_investmentCycle').val() =='')){
	                Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_003"));
	                return;
	            }
		        _.each(tableArry,function (data){
		            if($('#bussBenchMarkLayeredInfoDto_investmentCycle').val() && $('#bussBenchMarkLayeredInfoDto_investmentCycle').val() == data.investmentCycle){
		                Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_003"));
		                flag = true;
		                return; 
		            }
		        })
	    	}
	        if(flag){
	            return;
	        }
	    }
	    if(!$('#bussBenchMarkLayeredInfoDto_publishType').val()){
	    	Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.publishType")));
	    	return;
	    }
	    var lowLimitRate = '';
	    if($('#bussBenchMarkLayeredInfoDto_publishType').val() === YES_01){
	    	lowLimitRate = $('#bussBenchMarkLayeredInfoDto_rateLower').val() + '%~' + $('#bussBenchMarkLayeredInfoDto_rateLimit').val() + '%';
	    }
	    var floatRate = '';
	    if($('#bussBenchMarkLayeredInfoDto_publishType').val() === NO_01 && $('#bussBenchMarkLayeredInfoDto_rateType').val() === intType_02) {
	    	if(!$('#bussBenchMarkLayeredInfoDto_rateType').val()){
	    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.rateType")));
		    	return;
	    	}
	    	if(!$('#bussBenchMarkLayeredInfoDto_indexId').val()){
	    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.indexId")));
		    	return;
	    	}
	    	if(!$('#bussBenchMarkLayeredInfoDto_coefficient').val()){
	    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.coefficient")));
		    	return;
	    	}
	    	if(!$('#bussBenchMarkLayeredInfoDto_spreadRate').val()){
	    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.spreadRate")));
		    	return;
	    	}
	    	if(!$('#bussBenchMarkLayeredInfoDto_resetType').val()){
	    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.resetType")));
		    	return;
	    	}
		var row = {
				publishType: $('#bussBenchMarkLayeredInfoDto_publishType').val(),
				prodStyle: $('#bussBenchMarkLayeredInfoDto_prodStyle').val(),
				investmentCycle:$('#bussBenchMarkLayeredInfoDto_investmentCycle').val(),
				rateType: $('#bussBenchMarkLayeredInfoDto_rateType').val(),
				rate: $('#bussBenchMarkLayeredInfoDto_rate').val(),
				floatRate: floatRate,
				resetType: $('#bussBenchMarkLayeredInfoDto_resetType').val(),
				indexId: $('#bussBenchMarkLayeredInfoDto_indexId').val(),
				coefficient: $('#bussBenchMarkLayeredInfoDto_coefficient').val(),
				spreadRate: $('#bussBenchMarkLayeredInfoDto_spreadRate').val(),
				rateLower: $('#bussBenchMarkLayeredInfoDto_rateLower').val(),
				rateLimit: $('#bussBenchMarkLayeredInfoDto_rateLimit').val(),
				lowLimitRate: lowLimitRate
				//vdate: $('#FinNetValueProductBuild002_vdate').val()
    		};
	    Joyin.ajaxJson(basePath + 'component/prod/BussBenchMarkLayeredInfoCommon/getFloatRate', row, function(result) {
			floatRate = result;
			row = {
					publishType: $('#bussBenchMarkLayeredInfoDto_publishType').val(),
					prodStyle: $('#bussBenchMarkLayeredInfoDto_prodStyle').val(),
					investmentCycle:$('#bussBenchMarkLayeredInfoDto_investmentCycle').val(),
					rateType: $('#bussBenchMarkLayeredInfoDto_rateType').val(),
					rate: $('#bussBenchMarkLayeredInfoDto_rate').val(),
					floatRate: floatRate,
					resetType: $('#bussBenchMarkLayeredInfoDto_resetType').val(),
					indexId: $('#bussBenchMarkLayeredInfoDto_indexId').val(),
					coefficient: $('#bussBenchMarkLayeredInfoDto_coefficient').val(),
					spreadRate: $('#bussBenchMarkLayeredInfoDto_spreadRate').val(),
					rateLower: $('#bussBenchMarkLayeredInfoDto_rateLower').val(),
					rateLimit: $('#bussBenchMarkLayeredInfoDto_rateLimit').val(),
					lowLimitRate: lowLimitRate
	    		};
	        	bussBenchMarkInfoTable.addRow(row);  
	       
	    	$('#bussBenchMarkLayeredInfoDto_publishType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_prodStyle').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rate').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_indexId').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_coefficient').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_spreadRate').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_resetType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateLower').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateLimit').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_investmentCycle').val('').trigger('change');
	        
		});
	    }
	    else{
	    	if($('#bussBenchMarkLayeredInfoDto_publishType').val() === NO_01){
		    	if(!$('#bussBenchMarkLayeredInfoDto_rateType').val()){
		    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.rateType")));
			    	return;
		    	}
		    	if(!$('#bussBenchMarkLayeredInfoDto_rate').val()){
		    		Joyin.error(JoyinUtil.getMessage("FIN_PROD_M_004",JoyinUtil.getLabel("BussBenchMarkInfo.rate")));
			    	return;
		    	}
	    	}
			var row = {
					publishType: $('#bussBenchMarkLayeredInfoDto_publishType').val(),
					prodStyle: $('#bussBenchMarkLayeredInfoDto_prodStyle').val(),
					investmentCycle:$('#bussBenchMarkLayeredInfoDto_investmentCycle').val(),
					rateType: $('#bussBenchMarkLayeredInfoDto_rateType').val(),
					rate: $('#bussBenchMarkLayeredInfoDto_rate').val(),
					floatRate: floatRate,
					resetType: $('#bussBenchMarkLayeredInfoDto_resetType').val(),
					indexId: $('#bussBenchMarkLayeredInfoDto_indexId').val(),
					coefficient: $('#bussBenchMarkLayeredInfoDto_coefficient').val(),
					spreadRate: $('#bussBenchMarkLayeredInfoDto_spreadRate').val(),
					rateLower: $('#bussBenchMarkLayeredInfoDto_rateLower').val(),
					rateLimit: $('#bussBenchMarkLayeredInfoDto_rateLimit').val(),
					lowLimitRate: lowLimitRate
	    		};
        	bussBenchMarkInfoTable.addRow(row);  
 	       
	    	$('#bussBenchMarkLayeredInfoDto_publishType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_prodStyle').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rate').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_indexId').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_coefficient').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_spreadRate').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_resetType').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateLower').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_rateLimit').val('').trigger('change');
	        $('#bussBenchMarkLayeredInfoDto_investmentCycle').val('').trigger('change');
	    }
	    
	});
	
	//分层信息
	var bussFinProductLayeredInfoTable = $('#bussFinProductLayeredInfoDto_dataTable').joyintable(
			{
				data:finProductPtlLayeredDtoList==null?[]:finProductPtlLayeredDtoList,
				  searchOnInit: true,
		          loadonce: true,
		          rowno: true,
		          page: false,
		          checkbox:false,
		          addbtn: true,
		          height : 200,
		          cols:[
			             {field:'prodStyle', title: 'bussFinProductLayeredInfoDto.finProductLayeredType',sortable:false,edit:true}
			             ,{field:'saleChannel', title: 'bussFinProductLayeredInfoDto.finProductLayeredSaleChannel', sortable: false,
			            	edit:true,
			            	editOption:{type:'select',formatoption:true,multiple:true,result: SALECHANNELCHB}}
			             ,{field:'saleDepartment', title: 'bussFinProductLayeredInfoDto.finProductLayeredSaleDepartment', edit:true,sortable:false, editOption:{type:'select',formatoption:true,multiple:true,result: SALE_DEPT}}
			             ,{field:'tarCustomerType', title: 'bussFinProductLayeredInfoDto.finProductLayeredRepInvestorType',sortable:false,edit : true,editOption:{type:'select',formatoption:true,multiple:true,result: SALETARGET_DECLARE}}
			             //update by leipeng 20190627 BSTWO-224 【收益型产品管理】新建，分层信息中删除持有上、下线，增加认购起点字段。
			             /*,{field:'amtMin', title: 'bussFinProductLayeredInfoDto.finProductLayeredHoldDown',sortable:false,edit : true,type : 'number'}				         
			             ,{field:'amtMax', title: 'bussFinProductLayeredInfoDto.finProductLayeredHoldUp',sortable:false,edit : true,type : 'number'}*/
			             ,{field:'amtMin', title: 'bussFinProductLayeredInfoDto.finProductLayeredtoffStart',sortable:false,edit : true,type : 'number'}	
			             ,{field:'isSale', title: 'bussFinProductLayeredInfoDto.finProductLayeredIsReplaceSall',sortable:false,edit : true,editOption:{type:'select',formatoption:true,result: YES_OR_NO}}
			             ,{field:'yield', title: 'bussFinProductLayeredInfoDto.finProductLayeredield',sortable:false,edit : true,type : 'number',visible: false}
			             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
			         ],
		         addbtn:true,
				beforeEditCell: function(preVal, field, rowData){
					return true;
				},
		         editCell : function(value, data, field, cell, column){
		             //设置产品款型JSON数组
		             if(field === 'prodStyle'){
		                 tableDataChange();
		             }
		         },
		         done : function(table, allData){
		        	 tableDataChange();
		         }
		         
			});
	/**
	 * 绑定删除事件
	 */
	bussFinProductLayeredInfoTable.bind('click', '.delt', function(data, index) {
		bussFinProductLayeredInfoTable.removeRow(data);
		tableDataChange();
	});
	
	//产品分层信息同步
	var tableDataChange = function(){
		prodStyleArr = [];
	    var prodArr = bussFinProductLayeredInfoTable.getAllRows();
        _.each(prodArr,function (prod){
            prodStyleArr[prodStyleArr.length] = {id: prod.prodStyle, text : prod.prodStyle};
        });
        PROD_STYLE = JSON.stringify(prodStyleArr);
	}
	
	//产品费用
	var buttons = [{ text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
	var bussFinProductFeeInfoTable = $('#bussFinProductFeeInfoDto_feeInfo').joyintable({
		data:bussFinProductFeeInfoDtoList==null?[]:bussFinProductFeeInfoDtoList,
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'feeId', width:200, title: 'BussFinProductFeeInfo.feeName', sortable: false,
            	edit:true,
            	editOption:{type:'select', url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/queryFeeInfoList')}}
            ,{field:'feeType', width:200, title: 'BussFinProductFeeInfo.feeType', sortable: false,visible:false}//visible:false默认不展示
            ,{field:'prodStyle', width:100, title: 'BussFinProductFeeInfo.prodStyle', sortable: false, edit:true, editOption:{type:'select', formatoption : false, result: PROD_STYLE}}
            ,{field:'feeRate', width:100, title: 'BussFinProductFeeInfo.feeRate', sortable: false, type:'number', edit:true, digit:6}
            //,{field:'basicBill',visible : false}
            ,{field:'basicBill', width:150, title: 'BussFinProductFeeInfo.basicBill', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BILL_BASE_FEE_KIND}
            }
            ,{field:'basis', width:100, title: 'BussFinProductFeeInfo.basis', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BASIS_SELECT}
            }
            ,{field:'netAssetValueDay', width:150, title: 'BussFinProductFeeInfo.netAssetValueDay', sortable: false,
                visible : true,
        		edit:true,
				editOption:{type:'select', result: BASE_DATE_TYPE}
            }
            ,{field:'firstBaseType', width:100, title: 'BussFinProductFeeInfo.firstBaseType', sortable: false, 
            	edit:true,
            	visible : false,
            	editOption:{type:'select',result:BILL_BASE_FEE_KIND}
            }
            ,{field:'provisionFirstDay', width:150, title: 'BussFinProductFeeInfo.provisionFirstDay1', sortable: false,
            	edit:true,
            	visible : false,
            	type:'date'
            }
            ,{field:'payFeeRule', width:150, title: 'BussFinProductFeeInfo.payFeeRule', sortable: false,
            	edit:true,
            	editOption:{type:'select', result: PAY_RULE}
            }
            ,{field:'calcFeeTradeRule', width:150, title: 'BussFinProductFeeInfo.calcFeeTradeRule', sortable: false,
            	edit:true, 
            	editOption:{type:'select',childrenIds : 'payFeeTradeRule',id: "calcFeeTradeRule",formatoption:true,result: TDAY_RULE}
            }
            ,{field:'payFeeTradeRule', width:150, title: 'BussFinProductFeeInfo.payFeeTradeRule', sortable: false,
            	edit:true,
            	editOption:{type:'select',parentIds:'calcFeeTradeRule',id :"payFeeTradeRule", formatoption:true,result: TDAY_RULE}
            }
            ,{field:'eAccrDate', width:150, title: 'BussFinProductFeeInfo.eAccrDate', sortable: false,
            	edit:true,
            	visible : false,
            	type:'date'
            }
            ,{field:'firstCalEndDate', width:150, title: 'BussFinProductFeeInfo.firstCalEndDate.two', sortable: false,
            	edit:true,
            	visible : false,
            	type:'date'
            }
            ,{field:'orgType', visible : false}
            ,{field:'orgType2', visible : false}
            ,{field:'chargeType', visible : false}
            ,{field:'remark', visible : false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
        ],
        beforeEditCell : function(preVal, field, rowData){
            
            if($('#productModuleInfo_profitType').val() === PROFIT_TYPE_01 && field === 'prodStyle'){
                bussFinProductFeeInfoTable._getColByField("prodStyle").editOption.result = PROD_STYLE;
                return true;
            }
        	var profitType = $('#productModuleInfo_profitType').val();   
            //费用收费类型
            if(rowData.chargeType === CHARGE_TYPE_02 && field != 'feeId'){
               return false;
            }
        	//付费规则为 不规则
        	if (rowData.payFeeRule ===PAY_RULE_08 && (field ==='calcFeeTradeRule' || field === 'payFeeTradeRule' || field === 'firstCalEndDate')){
        		return false;
        	}
        	else if (field === "feeRate" && rowData.feeType === FEE_TYPE_05NAME){
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
        			//由于data.feeid为老的值，故取value的值
        			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfoCommonRate/queryMstFeeinfoByFeeId'), {feeId : value}, function(result){
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
        	    			convertRow(rowData);
        	        		bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        				}
        			})
        		}else{
        			bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}else if (field === "payFeeRule"){
        		var rowData = {};
        		//费用规则选择联动
        		//payFeeRuleChange(value,rowData);
        		//更新表格
        		bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}else if (field === "netAssetValueDay"){
        	    if(data.basicBill === BILL_BASE_FEE_KIND_03 && data.netAssetValueDay === BASE_DATE_TYPE_01){
        	        Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0113') ,function(){
        	            var rowData = {};
        	            rowData.netAssetValueDay = "";
        	            bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
        	    }
        	    
        	} else if(field === 'calcFeeTradeRule'){
        	    var rowData = {};
        	    rowData.payFeeTradeRule='';
        	    //更新表格
                bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        	} 
        }
    });
	
	  /**
     * 转换表格数据
     */
    var convertRow = function(rowData) {
    	_.each(FEE_TYPE, function(v) {
    		if(v.id === rowData.feeType) {
				rowData.feeType = v.text;
    		}
    	});
    	_.each(JSON.parse(BILL_BASE_FEE_KIND), function(v) {
    		if(v.id === rowData.basicBill) {
    			rowData.basicBillStr = v.text;
    		}
    	});
    }
	
	/**
	 * 绑定删除事件
	 */
    bussFinProductFeeInfoTable.bind('click', '.delt', function(data, index) {
    	bussFinProductFeeInfoTable.removeRow(data);
	});
	
	//分档浮动管理费
	var bussFinProductFloatFeeInfoTable = $('#bussFinProductFloatFeeDto_floatfeeInfo').joyintable({
		
		data:bussFinProductFloatFeeInfoDtoList==null?[]:bussFinProductFloatFeeInfoDtoList,
        searchOnInit: true,
        loadonce: true,
        page: false,
        checkbox:false,
        height : 200,
        cols:[
            {field:'feeId', width:150, title: '费用名称', sortable: false,edit:true,editOption:{type:'select',formatoption:true,url: JoyinUtil.getRealPath('/component/prod/BussFinProductFloatFee/queryFeeNameInfoList')}}
            ,{field:'prodStyle', width:100, title: '适用款型', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: PROD_STYLE}}
            ,{field:'investmentCycle', width:100, title: '投资周期', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: INVESTMENT_CLCYE}}
            ,{field:'basicBill', width:100, title: '计费基数', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: BILL_BASE_FEE_KIND}}
            ,{field:'basis', width:100, title: '计费基础', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: BASIS_SELECT}}
            ,{field:'netAssetValueDay', width:100, title: '资产净值日',visible:false,edit:true, sortable: false,editOption:{type:'select',formatoption:true,result: BASE_DATE_TYPE}}
            ,{field:'payCycle', width:100, title: '付费规则', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: FREQUENCY_UNIT}}
            ,{field:'endTdayRule', width:100, title: '计费营业日准则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'payTdayRule', width:100, title: '付费营业日准则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'provisionFirstDay', width:100, title: '首次计提日', type:'date',sortable: false,visible:false}
            ,{field:'eAccrDate', width:100, title: '计提结束日',type:'date', sortable: false,visible:false}
            ,{field:'fAccrEndDate', width:100, title: '首个计提区间结束日',type:'date',visible:false, sortable: false}
            ,{field:'accrTdayRule', title: '计提营业日准则', visible:false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'firstPayDate', title: '首次付费结束日', type:'date',visible:false}
            ,{field:'interValRule', title: '区间规则',visible:false,edit:true,editOption:{type:'select',formatoption:true,result: FREQUENCY_UNIT}}
            ,{field:'accrType', title: '计提方式', visible: false,edit:true,editOption:{type:'select',formatoption:true,result: DRAW_TYPE}}
            ,{field:'finFloatCalcRuleDetailDtoList', visible:false}
            ,{field:'rateType', visible:false}
            ,{field:'rate', visible:false}
            ,{field:'indexId', visible:false}
            ,{field:'coefficient', visible:false}
            ,{field:'spreadRate', visible:false}
            ,{field:'resetType', visible:false,editOption:{type:'select',formatoption:true,result: EFFECTIVE_RULE}}
            ,{field:'resetTypeName',title: '业绩基准重置规则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: EFFECTIVE_RULE}}
            ,{field:'remark', visible:false}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            
        ],
        done : function(table, allData){
        	//设置都不可编辑
        	 var allrowData =  bussFinProductFloatFeeInfoTable.options.cols;
             for(var i in allrowData){
            	   allrowData[i].edit = false;
             }
        },
        //增加双击查看事件 yzg 20191111 start
        dblclick:function(table, data) {
        	data.pageModel=PAGE_MODEL_REFERENCE;
        	var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
        	var prdinfo = $.serializeToObject('#productModuleForm', false);
        	feeList = JSON.stringify(feeList);
            Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init"),
                    "98%", "95%",{operateType:'reference',pageModel:PAGE_MODEL_REFERENCE,pageId:pageId, PROD_STYLE: PROD_STYLE,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#productModuleInfo_dealMode').val(),prdinfo:JSON.stringify(prdinfo), feeList:feeList,data:JSON.stringify(data)},function(){}
            );
        }
        //增加双击查看事件 yzg 20191111 end
    });
	
	/**
	 * 绑定删除事件
	 */
    bussFinProductFloatFeeInfoTable.bind('click', '.delt', function(data, index) {
    	bussFinProductFloatFeeInfoTable.removeRow(data);
	});
    
    /**
     * 绑定编辑事件
     */
    bussFinProductFloatFeeInfoTable.bind('click', '.edit', function(data,index) {
    	
    	var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
    	var prdinfo = $.serializeToObject('#productModuleForm', false);
    	feeList.splice(index,1);//不包括修改对象本身的校验
    	feeList = JSON.stringify(feeList);
    	Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002_edit"),
                basePath + "invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init","98%", "95%",{operateType:'edit',pageId:pageId, PROD_STYLE: PROD_STYLE,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#productModuleInfo_dealMode').val(),prdinfo:JSON.stringify(prdinfo), feeList:feeList,data:JSON.stringify(data)},function(result){
	        	if(!result){
	        		return;
	        	} else{
	        		bussFinProductFloatFeeInfoTable.removeRow(data);
                	var resetTypeName = '';
                	if(result.resetType==01)
                		resetTypeName = '期初'
            		else if(result.resetType==02)
                		resetTypeName = '期末'
            		else if(result.resetType==03)
                		resetTypeName = '当期生效'
	        		var row = {
	                          feeId: result.feeId,
	                          basicBill: result.basicBill,
	                          prodStyle: result.prodStyle,
	                          investmentCycle: result.investmentCycle,
	                          basis: result.basis,
	                          netAssetValueDay: result.netAssetValueDay,
	                          payCycle: result.payCycle,
	                          endTdayRule: result.endTdayRule,
	                          payTdayRule : result.payTdayRule,
	                          provisionFirstDay: result.provisionFirstDay,
	                          eAccrDate: result.eAccrDate,
	                          fAccrEndDate: result.fAccrEndDate,
	                          accrTdayRule : result.accrTdayRule,
	                          firstPayDate: result.firstPayDate,
	                          interValRule: result.interValRule,
	                          accrType: result.accrType,
	                          finFloatCalcRuleDetailDtoList : result.finFloatCalcRuleDetailDtoList,
	                          rateType: result.rateType,
	                          rate: result.rate,
	                          indexId: result.indexId,
	                          coefficient: result.coefficient,
	                          spreadRate: result.spreadRate,
	                          resetType: result.resetType,
	                          resetTypeName: resetTypeName,
	                          remark: result.remark
	                        };
                          bussFinProductFloatFeeInfoTable.addRow(row);
	        	}
        	});
    });
    
	//分档浮动管理费新增按钮
	$("#bussFinProductFloatFeeDto_btn_create").click(function(){
		var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
        feeList = JSON.stringify(feeList);
        var prdinfo = $.serializeToObject('#productModuleForm', false);
        var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002_add"),
        		basePath +"invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_NEW,pageId:pageId,PROD_STYLE: PROD_STYLE ,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#productModuleInfo_dealMode').val(),feeList:feeList, entityName: 'bussFinProductFloatFeeDto', workflowId:workflowId, profitType : $('#productModuleInfo_profitType').val(),prdinfo:JSON.stringify(prdinfo)}, function(data) {
                    	var resetTypeName = '';
                    	if(!data) return;
                    	if(data.resetType==01)
                    		resetTypeName = '期初'
                		else if(data.resetType==02)
                    		resetTypeName = '期末'
                		else if(data.resetType==03)
                    		resetTypeName = '当期生效'
                      
                      // 新建费用品种后，在费用列表中，添加一行数据，费用名称，费用类型，计费基数，计算基础，默认带出上述添加的数据。
                      
                      var row = {
                      feeId: data.feeId,
                      basicBill: data.basicBill,
                      prodStyle: data.prodStyle,
                      investmentCycle: data.investmentCycle,
                      basis: data.basis,
                      netAssetValueDay: data.netAssetValueDay,
                      payCycle: data.payCycle,
                      endTdayRule: data.endTdayRule,
                      payTdayRule : data.payTdayRule,
                      provisionFirstDay: data.provisionFirstDay,
                      eAccrDate: data.eAccrDate,
                      fAccrEndDate: data.fAccrEndDate,
                      accrTdayRule : data.accrTdayRule,
                      firstPayDate: data.firstPayDate,
                      interValRule: data.interValRule,
                      accrType: data.accrType,
                      rateType: data.rateType,
                      rate: data.rate,
                      indexId: data.indexId,
                      coefficient: data.coefficient,
                      spreadRate: data.spreadRate,
                      resetType: data.resetType,
                      resetTypeName: resetTypeName,
                      remark: data.remark,
                      finFloatCalcRuleDetailDtoList : data.finFloatCalcRuleDetailDtoList
                    };
                     // convertRow(row);
                      bussFinProductFloatFeeInfoTable.addRow(row);
                    });
	})
	
	var buttons2 = [{ text: '&#xe642;',ignore:true, clas: 'edit', title: '编辑' },
		{ text: '&#xe614;',ignore:true, clas: 'preview', title: '预览' },
        { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }
    ];
	//回补信息
	var bussFinProductAnapleroticInfoTable = $('#finProductAnapleroticTabInfoDtoList_anapleroticInfo').joyintable({
    	
		data:finProductAnapleroticTabInfoDtoList==null?[]:finProductAnapleroticTabInfoDtoList,
        searchOnInit: true,
        loadonce: false,
        rowno: true,
        page: false,
        checkbox:false,
        height : 200,
        //data:finProductAnapleroticInfo==null?[]:finProductAnapleroticInfo,
        cols:[
            {field:'anapleroticType', visible : false}//回补形式
            ,{field:'anapleroticTypeStr', title: 'BussFinProductAnapleroticInfo.anapleroticType',sortable: false}//回补形式
            ,{field:'prodStyle', title: 'BussFinProductAnapleroticInfo.prodStyle',sortable: false}//适用款型
            ,{field:'anapleroticRule', visible : false}//回补方式
            ,{field:'anapleroticRuleStr', title: 'BussFinProductAnapleroticInfo.anapleroticRule',sortable: false}//回补方式
//            ,{field:'anapleroticRatio', title: 'BussFinProductAnapleroticInfo.anapleroticRatio',sortable: false, visible : false}//回补比例
            ,{field:'anapleroticBasic', title: 'BussFinProductAnapleroticInfo.anapleroticBasic',sortable: false}//计费基础
            ,{field:'anapleroticFrequency',title:'回补规则', visible : true}//回补规则
//            ,{field:'anapleroticFrequencyStr', title: 'BussFinProductAnapleroticInfo.anapleroticFrequency',sortable: false, visible : false}//回补规则
//            ,{field:'cycleTdayRule', visible : false}//周期营业日准则
//            ,{field:'cycleTdayRuleStr', title: 'BussFinProductAnapleroticInfo.cycleTdayRule',sortable: false, visible : false}//周期营业日准则
            ,{field:'firstAnapleroticDate', visible : false}//首次回补日
            ,{field:'anapleroticEndDate', visible : false}//回补结束日
            ,{field:'firstAnapleroticCycleBegDate', visible : false}//首次回补周期开始日
            ,{field:'firstAnapleroticDateStr', title: 'BussFinProductAnapleroticInfo.firstAnapleroticDate', visible : true, type:'date',visible : false}//首次回补日
            ,{field:'anapleroticEndDateStr', title: 'FinProductAnapleroticInfo.anapleroticEndDate', visible : true, type:'date',visible : false}//回补结束日
            ,{field:'firstAnapleroticCycleBegDateStr', title: 'FinProductAnapleroticInfo.firstAnapleroticCycleEndDate', visible : true , type:'date',visible : false}//首次回补周期开始日
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons2,width:130}
            ,{field:'finProductAnapleroticInfoDto', visible : false}//model
            ,{field:'rateType', visible : false}
            ,{field:'rate', visible : false}
            ,{field:'indexId', visible : false}
            ,{field:'indexSource', visible : false}
            ,{field:'coefficient', visible : false}
            ,{field:'spreadRate', visible : false}
            ,{field:'resetType', visible : false}
        ]
    });
	
	/**
     * 绑定删除事件
     */
    bussFinProductAnapleroticInfoTable.bind('click', '.delt', function(data, index) {
        bussFinProductAnapleroticInfoTable.removeRow(data);
    });
    
    /**
     * 绑定修改事件
     */
    //传参时增加 发行方式 operationType : $('#' + getElementById('operationTypeElement').val()).val() add by wangcheng 20191101
    bussFinProductAnapleroticInfoTable.bind('click', '.edit', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002.update"), JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
            "98%", "95%", {pageModel:PAGE_MODEL_EDIT,applyType : 'prod',calendarId : $('#productModuleInfo_calendarId').val(), operationType : $('#productModuleInfo_operationType').val() , finProductAnapleroticInfoDto : data.finProductAnapleroticInfoDto,dealMode:$('#productModuleInfo_dealMode').val(),PROD_STYLE:PROD_STYLE,parentId:'FinProductModule002'},function(result) {
                if(!result) return;
                data.anapleroticId = result.anapleroticId;
                data.finprodMarketId = result.finprodMarketId;
                data.anapleroticType = result.anapleroticType;
                data.anapleroticTypeStr = result.anapleroticTypeStr;
                data.anapleroticRule = result.anapleroticRule;
                data.anapleroticRuleStr = result.anapleroticRuleStr;
                data.anapleroticBasic = result.anapleroticBasic;

                data.rateType = result.rateType;
                data.rate = result.rate;
                data.indexId = result.indexId;
                data.indexSource = result.indexSource;
                data.coefficient = result.coefficient;
                data.spreadRate = result.spreadRate;
                data.resetType = result.resetType;

                data.prodStyle = result.prodStyle;
                data.firstAnapleroticDate = result.firstAnapleroticDate;
                data.anapleroticEndDate = result.anapleroticEndDate;
                data.firstAnapleroticCycleBegDate = result.firstAnapleroticCycleBegDate;
                data.firstAnapleroticDateStr = result.firstAnapleroticDateStr;
                data.anapleroticEndDateStr = result.anapleroticEndDateStr;
                data.firstAnapleroticCycleBegDateStr = result.firstAnapleroticCycleBegDateStr;
                
                data.finProductAnapleroticInfoDto = JSON.stringify(result);
                bussFinProductAnapleroticInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
            });
    });
    /**
     * 绑定查看事件
     */
    bussFinProductAnapleroticInfoTable.bind('click', '.preview', function(data, index) {
    	var finProductAnapleroticInfoDto;
    	if((typeof data.finProductAnapleroticInfoDto) == 'object') {
    		finProductAnapleroticInfoDto = JSON.stringify(data.finProductAnapleroticInfoDto);
    	}else{
    		finProductAnapleroticInfoDto = data.finProductAnapleroticInfoDto;
    	}
        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002"), JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
            "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,applyType : 'prod', finProductAnapleroticInfoDto : finProductAnapleroticInfoDto,parentId:'FinProductModule002'})
    });
	
	//回补信息增加按钮
	$("#finProductAnapleroticTabInfoDtoList_btn_create").click(function(){
        var operationType = $('#productModuleInfo_operationType').val();
        var calendarId = $('#productModuleInfo_calendarId').val();
        if(null==calendarId || "" == calendarId){
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0057", JoyinUtil.getLabel("FinProductBuild002.calendarId")));
            return;
        }
        var anapleroticInfoList = bussFinProductAnapleroticInfoTable.getAllRows();
        if($('#productModuleInfo_dealMode').val() != dealMode_11 && anapleroticInfoList && anapleroticInfoList.length>0){
            Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0028", "回补信息"));
            return;
        }
        var anapleroticList = '';
        for(var m=0;m<anapleroticInfoList.length;m++){
            anapleroticList = anapleroticInfoList[m].prodStyle+',';
        }
        var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002"),
            "/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init",
            "98%", "95%", {pageModel:PAGE_MODEL_NEW, applyType: 'prod', workflowId : workflowId, calendarId: calendarId ,operationType:operationType,dealMode:$('#productModuleInfo_dealMode').val(),PROD_STYLE:PROD_STYLE,anapleroticList:anapleroticList,parentId:'FinProductModule002'}, function(data) {
            	if(!data) return;
                // 新建回补 返回列
                var row = {
                    anapleroticId : data.anapleroticId,
                    finprodMarketId : data.finprodMarketId,
                    anapleroticType : data.anapleroticType,
                    anapleroticTypeStr : data.anapleroticTypeStr,
                    anapleroticRule : data.anapleroticRule,
                    anapleroticRuleStr : data.anapleroticRuleStr,
//                           anapleroticRatio : data.anapleroticRatio,
                    anapleroticBasic : data.anapleroticBasic,
//                           anapleroticFrequency : data.anapleroticFrequkency,
//                           anapleroticFrequencyStr : data.anapleroticFrequencyStr,
//                           cycleTdayRule : data.cycleTdayRule,
//                           cycleTdayRuleStr : data.cycleTdayRuleStr,

                    prodStyle : data.prodStyle,

                    firstAnapleroticDate : data.firstAnapleroticDate,
                    anapleroticEndDate : data.anapleroticEndDate,
                    firstAnapleroticCycleBegDate : data.firstAnapleroticCycleBegDate,
                    firstAnapleroticDateStr : data.firstAnapleroticDateStr,
                    anapleroticEndDateStr : data.anapleroticEndDateStr,
                    firstAnapleroticCycleBegDateStr : data.firstAnapleroticCycleBegDateStr,
                    
                    
                    finProductAnapleroticInfoDto : JSON.stringify(data)
                };
                bussFinProductAnapleroticInfoTable.addRow(row);
            });
	});

	
	/**
	 * 产品收益类型联动
	 */
	var profitTypeChange = function(){
		var profitType = $("#productModuleInfo_profitType").val();
		var dealMode = $("#productModuleInfo_dealMode").val();
		var period = $("#productModuleInfo_period").val();
		//净值型
		if(profitType == PROFIT_TYPE_01 || profitType == PROFIT_TYPE_03){
			//产品收益标识为非保本浮动收益
			$("#productModuleInfo_profitFlag").val(PRODPROFITFLAG_03).trigger('change').prop('disabled', true);
			//利率类型，预期收益率不可见可见
			$("#productModuleInfo_calcType").val('').trigger('change');
			$("#productModuleInfo_yield").val('').trigger('change');
			$("#calcType_div").toggle(false);
			$("#yield_div").toggle(false);
			//估值信息
			$("#rateDec_div").toggle(false);
			$("#ratevalueMode_div").toggle(false);
			$("#unitvalueDec_div").toggle(true);
			$("#milionProfitDecMode_div").toggle(true);
			$("#bussFinProductValuationDto_rateDec").val('').trigger('change');
			$("#bussFinProductValuationDto_ratevalueMode").val('').trigger('change');
			$("#isAnaplerotic_div").toggle(true);
			//$("#finProductAnapleroticTabInfoDtoList_div").toggle(true);
			//普通净值型和货币性产品不显示浮动管理费列表
			$("#bussFinProductFloatFeeDto_div").toggle(true);
			if(dealMode == dealMode_01){
				$("#bussFinProductFloatFeeDto_Layerdiv").toggle(false);
			}else{
				$("#bussFinProductFloatFeeDto_Layerdiv").toggle(true);
			}
			if(profitType == PROFIT_TYPE_01 && (dealMode == dealMode_11 ||(dealMode == dealMode_10 && period == period_02))){
				$("#bussBenchMarkInfoDto_div").toggle(false);
				$("#bussBenchMarkLayeredInfoDto_div").toggle(true);
				//分档产品显示款型，周期产品显示周期
				if(dealMode == dealMode_11){
					$("#prodStyle_div").toggle(true);
					$("#investmentCycle_div").toggle(false);
					$("#bussBenchMarkLayeredInfoDto_investmentCycle").val('');
					//分档产品业绩基准列表显示产品款型
					if(bussBenchMarkInfoTable){
						//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: true},{field:'investmentCycle', visible: false}]);
					}
				}else if(dealMode == dealMode_10){
					$("#investmentCycle_div").toggle(true);
					$("#prodStyle_div").toggle(false);
					$("#bussBenchMarkLayeredInfoDto_prodStyle").val('');
					//周期型产品业绩基准列表显示产品周期
					if(bussBenchMarkInfoTable){
						//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: false},{field:'investmentCycle', visible: true}]);
					}
				}
			}else{
				$("#bussBenchMarkInfoDto_div").toggle(true);
				$("#bussBenchMarkLayeredInfoDto_div").toggle(false);
			}
			//净值型
			if(profitType == PROFIT_TYPE_01){
				//净值型不显示分红计划和付息计划
				$("#bussFinProductBonusPlanDto_div").toggle(false);
				$("#bussFinProductPayPlanDto_div").toggle(false);
				//净值型开放计划显示净值日准则
				$("#bussFinProductOpenPlanRedDto_cpriceRuleDiv").toggle(true);
				$("#bussFinProductOpenPlanDto_cpriceRuleDiv").toggle(true);
				//净值型分层信息不显示预期收益率
				if(bussFinProductLayeredInfoTable){
					//bussFinProductLayeredInfoTable.showColumns([{field:'yield', visible: false}]);
				}
			}else if(profitType == PROFIT_TYPE_03){
				//货币型显示分红计划，不显示付息计划
				$("#bussFinProductBonusPlanDto_div").toggle(true);
				$("#bussFinProductPayPlanDto_div").toggle(false);
				//不显示净值日准则
				$("#bussFinProductOpenPlanRedDto_cpriceRuleDiv").val('').toggle(false).trigger('change');
				$("#bussFinProductOpenPlanDto_cpriceRuleDiv").val('').toggle(false).trigger('change');
			}
		}else if(profitType == PROFIT_TYPE_02){
			//不显示净值日准则
			$("#bussFinProductOpenPlanRedDto_cpriceRuleDiv").val('').toggle(false).trigger('change');
			$("#bussFinProductOpenPlanDto_cpriceRuleDiv").val('').toggle(false).trigger('change');
			//收益型
			$("#productModuleInfo_profitFlag").val(PRODPROFITFLAG_03).prop('disabled', false).trigger('change');
			//收益型利率类型，预期收益率可见
			$("#calcType_div").toggle(true);
			$("#yield_div").toggle(true);
			//业绩基准不可见
			$("#bussBenchMarkInfoDto_div").toggle(false);
			$("#bussBenchMarkLayeredInfoDto_div").toggle(false);
			//估值信息
			$("#rateDec_div").toggle(true);
			$("#ratevalueMode_div").toggle(true);
			$("#unitvalueDec_div").toggle(false);
			$("#milionProfitDecMode_div").toggle(false);
			$("#bussFinProductValuationDto_milionProfitDecMode").val('').trigger('change');
			$("#bussFinProductValuationDto_unitvalueDec").val('').trigger('change');
			$("#bussFinProductValuationDto_unitvalueMode").val('').trigger('change');
			$("#bussFinProductValuationDto_milionProfitDec").val('').trigger('change');
			//收益型显示付息计划，不显示分红计划
			$("#bussFinProductBonusPlanDto_div").toggle(false);
			$("#bussFinProductPayPlanDto_div").toggle(true);
			//收益型是否回补不显示
			$("#isAnaplerotic_div").toggle(false);
			$("#productModuleInfo_isAnaplerotic").val('N').trigger('change')
			$("#finProductAnapleroticTabInfoDtoList_div").toggle(false);
			//收益型不显示浮动管理费
			$("#bussFinProductFloatFeeDto_Layerdiv").toggle(false);
			$("#bussFinProductFloatFeeDto_div").toggle(false);
			//净值型分层信息不显示预期收益率
			if(bussFinProductLayeredInfoTable){
				//bussFinProductLayeredInfoTable.showColumns([{field:'yield', visible: true}]);
			}
		}
	}
	
	//是否回补联动
	var investmentCycleChange = function(){
		var investmentCycle = $("#productModuleInfo_isAnaplerotic").val();
		var profitType = $("#productModuleInfo_profitType").val(); 
		if((profitType == PROFIT_TYPE_01 || profitType == PROFIT_TYPE_03) && investmentCycle == YES){
			$("#finProductAnapleroticTabInfoDtoList_div").toggle(true);
		}else{
			$("#finProductAnapleroticTabInfoDtoList_div").toggle(false);
		}
	}
	
	//产品处理模式联动
	var dealModeChange = function(){
		var dealMode = $("#productModuleInfo_dealMode").val();
		//投资周期
		if(dealMode == dealMode_10){
			$("#productModuleInfo_investmentCycle").prop('disabled', false).trigger('change');
			//期限显示
			$("#period_div").toggle(true);
		}else{
			$("#productModuleInfo_investmentCycle").val('').prop('disabled', true).trigger('change');
			$("#period_div").toggle(false);
			$("#productModuleInfo_period").val('').trigger('change');
		}
		var profitType = $("#productModuleInfo_profitType").val();
		var period = $("#productModuleInfo_period").val();
		//期限
		var period = $("#productModuleInfo_period").val();
		if(profitType == PROFIT_TYPE_01 && (dealMode == dealMode_11 ||(dealMode == dealMode_10 && period == period_02)) ){
			$("#bussBenchMarkInfoDto_div").toggle(false);
			$("#bussBenchMarkLayeredInfoDto_div").toggle(true);
			//分档产品显示款型，周期产品显示周期
			if(dealMode == dealMode_11){
				$("#prodStyle_div").toggle(true);
				$("#investmentCycle_div").toggle(false);
				$("#bussBenchMarkLayeredInfoDto_investmentCycle").val('');
				//分档产品业绩基准列表显示产品款型
				if(bussBenchMarkInfoTable){
					//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: true},{field:'investmentCycle', visible: false}]);
				}
			}else if(dealMode == dealMode_10){
				$("#investmentCycle_div").toggle(true);
				$("#prodStyle_div").toggle(false);
				$("#bussBenchMarkLayeredInfoDto_prodStyle").val('');
				//周期型产品业绩基准列表显示产品周期
				if(bussBenchMarkInfoTable){
					//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: false},{field:'investmentCycle', visible: true}]);
				}
			}
		}else{
			$("#bussBenchMarkInfoDto_div").toggle(true);
			$("#bussBenchMarkLayeredInfoDto_div").toggle(false);
		}
		//普通净值型和货币性产品不显示浮动管理费列表
		if((profitType == PROFIT_TYPE_01 || profitType == PROFIT_TYPE_03) && dealMode == dealMode_01){
			$("#bussFinProductFloatFeeDto_Layerdiv").toggle(false);
			$("#bussFinProductFloatFeeDto_div").toggle(true);
		}
		else if(profitType == PROFIT_TYPE_02){
			$("#bussFinProductFloatFeeDto_Layerdiv").toggle(false);
			$("#bussFinProductFloatFeeDto_div").toggle(false);
		}
		else{
			$("#bussFinProductFloatFeeDto_Layerdiv").toggle(true);
			$("#bussFinProductFloatFeeDto_div").toggle(false);
		}
		//分档、周期型分档产品显示分层信息
		if(dealMode == dealMode_11 || dealMode == dealMode_12){
			$("#bussFinProductLayeredInfoDto_div").toggle(true);
			if(profitType == PROFIT_TYPE_01){
				//净值型分层信息不显示预期收益率
				if(bussFinProductLayeredInfoTable){
					//bussFinProductLayeredInfoTable.showColumns([{field:'yield', visible: false}]);
				}
			}else if (profitType == PROFIT_TYPE_02){
				//净值型分层信息不显示预期收益率
				if(bussFinProductLayeredInfoTable){
					//bussFinProductLayeredInfoTable.showColumns([{field:'yield', visible: true}]);
				}
			}
		}else{
			$("#bussFinProductLayeredInfoDto_div").toggle(false);
		}
		//普通、周期型产品销售渠道必填
		/*if(dealMode == dealMode_10 || dealMode == dealMode_01){
			$("#bussFinProductSaleParamDto_saleChannel").prop('disabled', false).trigger('change');
		}else{
			$("#bussFinProductSaleParamDto_saleChannel").val('').prop('disabled', true).trigger('change');
		}*/
	}
	
	//发行方式联动
	var operationTypeChange = function(){
		var operationType = $("#productModuleInfo_operationType").val();
		if(operationType == ISSUE_MODE_01){
			//是否现金管理类
			$("#productModuleInfo_isCashManage").prop('disabled', false).trigger('change');
			//开放式显示开放计划
			$("#bussFinProductOpenPlanDto_div").toggle(true);
		}else{
			$("#productModuleInfo_isCashManage").val('').prop('disabled', true).trigger('change');
			$("#bussFinProductOpenPlanDto_div").toggle(false);
		}
	}
	//是否区间披露联动
	var publishTypeChange = function(){
		var publishType = $("#bussBenchMarkInfoDto_publishType").val();
		if(publishType == YES_01){
			$("#rateTypeDiv").toggle(false);
			$("#rate_div").toggle(false);
			$("#bussBenchMarkInfoDto_rateType").val('').trigger('change');
			$("#bussBenchMarkInfoDto_rate").val('').trigger('change');
			//浮动利率不可编辑
			$("#bussBenchMarkInfoDto_indexId").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
			//业绩基准重置规则不允许编辑
			$("#bussBenchMarkInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			//利率区间允许可见允许编辑
			$("#bussBenchMarkInfoDto_rateLower").prop('disabled', false);
			$("#bussBenchMarkInfoDto_rateLimit").prop('disabled', false);
			$("#bussBenchMarkInfoDto_rateLower_div").toggle(true);
		}else if(publishType == NO_01){
			$("#rateTypeDiv").toggle(true);
			$("#rate_div").toggle(true);
			var rateType = $("#bussBenchMarkInfoDto_rateType").val();
			//利率类型为浮动利率时浮动利率允许编辑
			if(rateType == intType_02){
				$("#bussBenchMarkInfoDto_indexId").prop('disabled', false).trigger('change');
				$("#bussBenchMarkInfoDto_coefficient").prop('disabled', false).trigger('change');
				$("#bussBenchMarkInfoDto_spreadRate").prop('disabled', false).trigger('change');
				//业绩基准重置规则
				$("#bussBenchMarkInfoDto_resetType").prop('disabled', false).trigger('change');
			}else{
				$("#bussBenchMarkInfoDto_indexId").val('').prop('disabled', true).trigger('change');
				$("#bussBenchMarkInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
				$("#bussBenchMarkInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
				//业绩基准重置规则
				$("#bussBenchMarkInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			}
			//利率区间允许可见允许编辑
			$("#bussBenchMarkInfoDto_rateLower").val('').prop('disabled', true);
			$("#bussBenchMarkInfoDto_rateLimit").val('').prop('disabled', true);
			$("#bussBenchMarkInfoDto_rateLower_div").toggle(false);
		}
	}
	//业绩基准利率类型联动
	var rateTypeChange = function(){
		var rateType = $("#bussBenchMarkInfoDto_rateType").val();
		if(rateType == intType_01){
			$("#bussBenchMarkInfoDto_rate").prop('disabled', false);
			$("#bussBenchMarkInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_indexId").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
		}else{
			$("#bussBenchMarkInfoDto_rate").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkInfoDto_resetType").prop('disabled', false);
			$("#bussBenchMarkInfoDto_indexId").prop('disabled', false).trigger('change');
			$("#bussBenchMarkInfoDto_coefficient").prop('disabled', false).trigger('change');
			$("#bussBenchMarkInfoDto_spreadRate").prop('disabled', false).trigger('change');
		}
	}
	
	//分档-是否区间披露联动
	var publishTypeChangeFd = function(){
		var publishType = $("#bussBenchMarkLayeredInfoDto_publishType").val();
		if(publishType == YES_01){
			$("#rateTypeDiv_fd").toggle(false);
			$("#rate_div_fd").toggle(false);
			$("#bussBenchMarkLayeredInfoDto_rateType").val('').trigger('change');
			$("#bussBenchMarkLayeredInfoDto_rate").val('').trigger('change');
			//浮动利率不可编辑
			$("#bussBenchMarkLayeredInfoDto_indexId").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
			//业绩基准重置规则不允许编辑
			$("#bussBenchMarkLayeredInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			//利率区间允许可见允许编辑
			$("#bussBenchMarkLayeredInfoDto_rateLower").prop('disabled', false);
			$("#bussBenchMarkLayeredInfoDto_rateLimit").prop('disabled', false);
			$("#bussBenchMarkLayeredInfoDtoRateLower_div").toggle(true);
		}else if(publishType == NO_01){
			$("#rateTypeDiv_fd").toggle(true);
			$("#rate_div_fd").toggle(true);
			var rateType = $("#bussBenchMarkLayeredInfoDto_rateType").val();
			//利率类型为浮动利率时浮动利率允许编辑
			if(rateType == intType_02){
				$("#bussBenchMarkLayeredInfoDto_indexId").prop('disabled', false).trigger('change');
				$("#bussBenchMarkLayeredInfoDto_coefficient").prop('disabled', false).trigger('change');
				$("#bussBenchMarkLayeredInfoDto_spreadRate").prop('disabled', false).trigger('change');
				//业绩基准重置规则
				$("#bussBenchMarkLayeredInfoDto_resetType").prop('disabled', false).trigger('change');
			}else{
				$("#bussBenchMarkLayeredInfoDto_indexId").val('').prop('disabled', true).trigger('change');
				$("#bussBenchMarkLayeredInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
				$("#bussBenchMarkLayeredInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
				//业绩基准重置规则
				$("#bussBenchMarkLayeredInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			}
			//利率区间允许不可见不允许编辑
			$("#bussBenchMarkLayeredInfoDto_rateLower").val('').prop('disabled', true);
			$("#bussBenchMarkLayeredInfoDto_rateLimit").val('').prop('disabled', true);
			$("#bussBenchMarkLayeredInfoDtoRateLower_div").toggle(false);
		}
	}
	//分档-业绩基准利率类型联动
	var rateTypeChangeFd = function(){
		var rateType = $("#bussBenchMarkLayeredInfoDto_rateType").val();
		if(rateType == intType_01){
			$("#bussBenchMarkLayeredInfoDto_rate").prop('disabled', false);
			$("#bussBenchMarkLayeredInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_indexId").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
		}else{
			$("#bussBenchMarkLayeredInfoDto_rate").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_resetType").prop('disabled', false);
			$("#bussBenchMarkLayeredInfoDto_indexId").prop('disabled', false).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_coefficient").prop('disabled', false).trigger('change');
			$("#bussBenchMarkLayeredInfoDto_spreadRate").prop('disabled', false).trigger('change');
		}
	}
	
	//申购-开放模式
	var openModeChange = function(){
		//开放模式
		var openMode = $("#bussFinProductOpenPlanDto_openMode").val();
		//开放天数类型
		var openDaysType = $("#bussFinProductOpenPlanDto_openDaysType").val();
		if(openMode == openMode_01 && openDaysType == openDaysType_01 ){
			$("#bussFinProductOpenPlanDto_openDays").prop('disabled',false).trigger('change');
		}else{
			$("#bussFinProductOpenPlanDto_openDays").val('').prop('disabled',true);
		}
		//开放模式为定期开放或周期开放开放周期可编辑
		if(openMode == openMode_01 || openMode == openMode_03){
			$("#bussFinProductOpenPlanDto_openCycle").prop('disabled',false);
			$("#bussFinProductOpenPlanDto_openCycleUnit").prop('disabled',false);
			//高频开放开放天数类型为固定天数，开放天数置灰不可编辑
			$("#bussFinProductOpenPlanDto_openDaysType").prop('disabled',false);
			$("#bussFinProductOpenPlanDto_tradedayRule").prop('disabled',false);
			if(openDaysType == openDaysType_01){
				$("#bussFinProductOpenPlanDto_openDays").prop('disabled',false);
			}else{
				$("#bussFinProductOpenPlanDto_openDays").val('').prop('disabled',true).trigger('change');
			}
		}else{
			$("#bussFinProductOpenPlanDto_openCycle").val('').prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanDto_openCycleUnit").prop('disabled',true).trigger('change');
			//高频开放开放天数类型为固定天数，开放天数置灰不可编辑
			$("#bussFinProductOpenPlanDto_openDaysType").val(openDaysType_01).prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanDto_openDays").val('').prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanDto_tradedayRule").val('').prop('disabled',true).trigger('change');
		}
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanDto_openCycleUnit").val();
		//定期开放
		if(openMode == openMode_01){
			if (openCycleUnit == openCycleUnit_day){
				$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_week){
				$('#bussFinProductOpenPlanDto_dayOfWeek').prop('disabled', false);
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_month){
				$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', false);
				if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanDto_radioDiv').find(':radio:checked').length === 0) {
					$('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
				} 
				$('#bussFinProductOpenPlanDto_dayOfMonth').prop('disabled', false);
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (openCycleUnit == openCycleUnit_year){
                $('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanDto_radioDiv').find(':radio:checked').length === 0) {
                    $('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
                    $('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="2"]').prop('disabled', true);
                }
                $('#bussFinProductOpenPlanDto_dayOfMonth').prop('disabled', false).trigger('change');
                $('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
                $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
            }
		}else{
			$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
			$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
		}
		layui.form.render('radio');
	}
	
	//周期单位
	var openCycleUnitChange = function(){
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanDto_openCycleUnit").val();
		//开放模式
		var openMode = $("#bussFinProductOpenPlanDto_openMode").val();
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanDto_openCycleUnit").val();
		//定期开放
		if(openMode == openMode_01){
			if (openCycleUnit == openCycleUnit_day){
				$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_week){
				$('#bussFinProductOpenPlanDto_dayOfWeek').prop('disabled', false);
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_month){
				$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', false);
				if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanDto_radioDiv').find(':radio:checked').length === 0) {
					$('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
				} 
				$('#bussFinProductOpenPlanDto_dayOfMonth').prop('disabled', false);
				$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (openCycleUnit == openCycleUnit_year){
                $('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanDto_radioDiv').find(':radio:checked').length === 0) {
                    $('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
                    $('#bussFinProductOpenPlanDto_radioDiv').find(':radio[value="2"]').prop('disabled', true);
                }
                $('#bussFinProductOpenPlanDto_dayOfMonth').prop('disabled', false).trigger('change');
                $('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
                $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
            }
		}else{
			$('#bussFinProductOpenPlanDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
			$('#bussFinProductOpenPlanDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
            $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
		}
		layui.form.render('radio');
	}
	
	//申购-开放天数类型
	var openDaysTypeChange = function(){
		//开放模式
		var openMode = $("#bussFinProductOpenPlanDto_openMode").val();
		//开放天数类型
		var openDaysType = $("#bussFinProductOpenPlanDto_openDaysType").val();
		if((openMode == openMode_01 || openMode == openMode_03) && openDaysType == openDaysType_01 ){
			$("#bussFinProductOpenPlanDto_openDays").prop('disabled',false).trigger('change');
		}else{
			$("#bussFinProductOpenPlanDto_openDays").val('').prop('disabled',true);
		}
		if(openDaysType == openDaysType_02){
			$("#bussFinProductOpenPlanDto_calcRule").val('').trigger('change');
		}
	}
	//申购-开放规则联动
	var calcRuleChange = function(){
		var calcRule = $("#bussFinProductOpenPlanDto_calcRule").val();
		var openDaysType = $("#bussFinProductOpenPlanDto_openDaysType").val();
		if(calcRule == '02' && openDaysType == openDaysType_02){
			$("#bussFinProductOpenPlanDto_calcRule").val('');
			Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0126"));
			return;
		}
		if($('#bussFinProductOpenPlanDto_openMode').val() != '02'){
			if(calcRule == '01'){
				$("#bussFinProductOpenPlanDto_tradedayRule").val('02').trigger('change').prop('disabled', true);
			}else{
				$("#bussFinProductOpenPlanDto_tradedayRule").val('02').trigger('change').prop('disabled', false);
			}
		}
	}
	
	
	//赎回-开放规则联动
	var calcRuleRedChange = function(){
		var calcRule = $("#bussFinProductOpenPlanRedDto_calcRule").val();
		var openDaysType = $("#bussFinProductOpenPlanRedDto_openDaysType").val();
		if(calcRule == '02' && openDaysType == openDaysType_02){
			$("#bussFinProductOpenPlanRedDto_calcRule").val('');
			Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0126"));
			return;
		}
		if($('#bussFinProductOpenPlanRedDto_openMode').val() != '02'){
			if(calcRule == '01'){
				$("#bussFinProductOpenPlanRedDto_tradedayRule").val('02').trigger('change').prop('disabled', true);
			}else{
				$("#bussFinProductOpenPlanRedDto_tradedayRule").val('02').trigger('change').prop('disabled', false);
			}
		}
	}
	
	//申购-推算方法联动
	var openPlancalcRuleChange = function(){
		var calcRule = $("#bussFinProductOpenPlanDto_calcMethod").val();
		if(calcRule == '03'){
			$('#bussFinProductOpenPlanDto_firstOpenDate').attr("disabled",false);
		}else{
			$('#bussFinProductOpenPlanDto_firstOpenDate').val('').attr("disabled",true).trigger('change');
		}
	}
	
	//赎回-推算方法联动
	var openPlanRedcalcRuleChange = function(){
		var calcRule = $("#bussFinProductOpenPlanRedDto_calcMethod").val();
		if(calcRule == '03'){
			$('#bussFinProductOpenPlanRedDto_firstOpenDate').attr("disabled",false);
		}else{
			$('#bussFinProductOpenPlanRedDto_firstOpenDate').val('').attr("disabled",true).trigger('change');
		}
	}
	
	
	//申赎同频
	var sameFLagChange = function(){
		if($('#bussFinProductOpenPlanDto_sameFLag').next().find("em").text()==="是"){
			$("#red_div").hide();
			$('#bussFinProductOpenPlanDto_openType').val('03');
			$("#bussFinProductOpenPlanRedDto_openMode").val('');
			$("#bussFinProductOpenPlanRedDto_openDays").val('');
			$("#bussFinProductOpenPlanRedDto_openCycle").val('');
			$("#bussFinProductOpenPlanRedDto_calcMethod").val('');
			$("#bussFinProductOpenPlanRedDto_firstOpenDate").val('');
			$("#bussFinProductOpenPlanRedDto_cpriceRule").val('');
			$("#bussFinProductOpenPlanRedDto_tradedayRule").val('');
			$("#bussFinProductOpenPlanRedDto_redSpeed").val('');
		}else{
			$("#red_div").show();
			$('#bussFinProductOpenPlanDto_openType').val('01');
		}
	}
	
	//赎回-开放模式
	var openModeRedChange = function(){
		//开放模式
		var openMode = $("#bussFinProductOpenPlanRedDto_openMode").val();
		//开放天数类型
		var openDaysType = $("#bussFinProductOpenPlanRedDto_openDaysType").val();
		if(openMode == openMode_01 && openDaysType == openDaysType_01 ){
			$("#bussFinProductOpenPlanRedDto_openDays").prop('disabled',false).trigger('change');
		}else{
			$("#bussFinProductOpenPlanRedDto_openDays").val('').prop('disabled',true);
		}
		//开放模式为定期开放或周期开放开放周期可编辑
		if(openMode == openMode_01 || openMode == openMode_03){
			$("#bussFinProductOpenPlanRedDto_openCycle").prop('disabled',false);
			$("#bussFinProductOpenPlanRedDto_openCycle").prop('disabled',false);
			//高频开放开放天数类型为固定天数，开放天数置灰不可编辑
			$("#bussFinProductOpenPlanRedDto_openDaysType").prop('disabled',false);
			$("#bussFinProductOpenPlanRedDto_tradedayRule").prop('disabled',false);
			if(openDaysType == openDaysType_01){
				$("#bussFinProductOpenPlanRedDto_openDays").prop('disabled',false);
			}else{
				$("#bussFinProductOpenPlanRedDto_openDays").val('').prop('disabled',true).trigger('change');
			}
		}else{
			$("#bussFinProductOpenPlanRedDto_openCycle").val('').prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanRedDto_openCycle").prop('disabled',true).trigger('change');
			//高频开放开放天数类型为固定天数，开放天数置灰不可编辑
			$("#bussFinProductOpenPlanRedDto_openDaysType").val(openDaysType_01).prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanRedDto_openDays").val('').prop('disabled',true).trigger('change');
			$("#bussFinProductOpenPlanRedDto_tradedayRule").val('').prop('disabled',true).trigger('change');
		}
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanRedDto_openCycleUnit").val();
		//定期开放
		
		if(openMode == openMode_01){
			if (openCycleUnit == openCycleUnit_day){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_week){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').prop('disabled', false);
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_month){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', false);
				if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio:checked').length === 0) {
					$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
				} 
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').prop('disabled', false);
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (openCycleUnit == openCycleUnit_year){
                $('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio:checked').length === 0) {
                    $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
                    $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="2"]').prop('disabled', true);
                }
                $('#bussFinProductOpenPlanRedDto_dayOfMonth').prop('disabled', false).trigger('change');
                $('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
                $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
            }
		}else{
			$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
			$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
		}
		layui.form.render('radio');
	}
	
	//赎回-周期单位
	var openCycleUnitRedChange = function(){
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanRedDto_openCycleUnit").val();
		//开放模式
		var openMode = $("#bussFinProductOpenPlanRedDto_openMode").val();
		//周期单位
		var openCycleUnit = $("#bussFinProductOpenPlanRedDto_openCycleUnit").val();
		//定期开放
		if(openMode == openMode_01){
			if (openCycleUnit == openCycleUnit_day){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_week){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').prop('disabled', false);
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
	            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
			}
			else if (openCycleUnit == openCycleUnit_month){
				$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
				$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', false);
				if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio:checked').length === 0) {
					$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
				} 
				$('#bussFinProductOpenPlanRedDto_dayOfMonth').prop('disabled', false);
				$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			}
			else if (openCycleUnit == openCycleUnit_year){
                $('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', false);
                if(pageModel === PAGE_MODEL_NEW || $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio:checked').length === 0) {
                    $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="0"]').prop('checked', true);
                    $('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio[value="2"]').prop('disabled', true);
                }
                $('#bussFinProductOpenPlanRedDto_dayOfMonth').prop('disabled', false).trigger('change');
                $('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
                $('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
                $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
            }
		}else{
			$('#bussFinProductOpenPlanRedDto_dayOfWeek').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_radioDiv').find(':radio').prop('disabled', true).prop('checked', false);
			$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').prop('disabled', true).trigger('change');
			$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true);
            $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true);
		}
		layui.form.render('radio');
	}
	
	//赎回-开放天数类型
	var openDaysTypeRedChange = function(){
		//开放模式
		var openMode = $("#bussFinProductOpenPlanRedDto_openMode").val();
		//开放天数类型
		var openDaysType = $("#bussFinProductOpenPlanRedDto_openDaysType").val();
		if((openMode == openMode_01 || openMode == openMode_03) && openDaysType == openDaysType_01 ){
			$("#bussFinProductOpenPlanRedDto_openDays").prop('disabled',false).trigger('change');
		}else{
			$("#bussFinProductOpenPlanRedDto_openDays").val('').prop('disabled',true);
		}
		if(openDaysType == openDaysType_02){
			$("#bussFinProductOpenPlanRedDto_calcRule").val('').trigger('change');
		}
	}
	
	//浮动管理费-利率类型
	var floatFeeRateTypeChange = function(){
		var rateType = $("#bussBenchMarkFloatInfoDto_rateType").val();
		if(rateType == intType_01){
			$("#bussBenchMarkFloatInfoDto_rate").prop('disabled', false);
			$("#bussBenchMarkFloatInfoDto_resetType").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkFloatInfoDto_indexId").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkFloatInfoDto_coefficient").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkFloatInfoDto_spreadRate").val('').prop('disabled', true).trigger('change');
		}else{
			$("#bussBenchMarkFloatInfoDto_rate").val('').prop('disabled', true).trigger('change');
			$("#bussBenchMarkFloatInfoDto_resetType").prop('disabled', false);
			$("#bussBenchMarkFloatInfoDto_indexId").prop('disabled', false).trigger('change');
			$("#bussBenchMarkFloatInfoDto_coefficient").prop('disabled', false).trigger('change');
			$("#bussBenchMarkFloatInfoDto_spreadRate").prop('disabled', false).trigger('change');
		}
	}
	
	//募集要素-目标客户类型
	var investorTypeChange = function(){
		var investorType = $("#bussFinProductSaleParamDto_investorType").val();
		if(investorType && investorType.length == 1 && investorType[0] == investorType_04){
			$("#bussRepFinProductInfoDto_excFinSector").val(YES_01).trigger('change');
		}else{
			$("#bussRepFinProductInfoDto_excFinSector").val(NO_01).trigger('change');
		}
		if(investorType == null){
			$('#bussFinProductSaleParamDto_sameOrg').val('').prop('disabled',true).trigger('change');
			return;
		}
		
		//客户类型不含同业，同业机构不允许编辑
		for(var i = 0;i < investorType.length ; i++){
			if(investorType[i] == '04'){
				$('#bussFinProductSaleParamDto_sameOrg').prop('disabled',false);
				return;
			}
		}
		$('#bussFinProductSaleParamDto_sameOrg').val('').prop('disabled',true).trigger('change');
	}
	
	//募集要素-目标客户类型
	var investorTypeChange2 = function(){
		var investorType = $("#bussFinProductSaleParamDto_investorType").val();
		if(investorType == null){
			$('#bussFinProductSaleParamDto_sameOrg').val('').prop('disabled',true).trigger('change');
			return;
		}
		
		//客户类型不含同业，同业机构不允许编辑
		for(var i = 0;i < investorType.length ; i++){
			if(investorType[i] == '04'){
				$('#bussFinProductSaleParamDto_sameOrg').prop('disabled',false);
				return;
			}
		}
		$('#bussFinProductSaleParamDto_sameOrg').val('').prop('disabled',true).trigger('change');
	}
		
	
	
	
	//申报要素-是否设置最短持有期限
	var isShortTermChange = function(){
		var isShortTerm = $("#bussRepFinProductInfoDto_isShortTerm").val();
		if(isShortTerm == YES_01){
			$("#bussRepFinProductInfoDto_shortTerm").prop("disabled",false);
		}else{
			$("#bussRepFinProductInfoDto_shortTerm").val('').prop("disabled",true).trigger('change');
		}
	}
	
	//投资周期
	var investmentCycleChangeAction=function(){
		var investmentCycleArr = [];
		var investmentCycle=$('#productModuleInfo_investmentCycle').val();
		if(investmentCycle){
			investmentCycleArr[0] = {id: investmentCycle, text : investmentCycle};
			INVESTMENT_CLCYE = JSON.stringify(investmentCycleArr);
		}
		else{
			INVESTMENT_CLCYE = '';
		}
	};
	
	//期限联动
	var periodChange = function(){
		var period = $("#productModuleInfo_period").val();
		var dealMode = $("#productModuleInfo_dealMode").val();
		var profitType = $("#productModuleInfo_profitType").val();
		if(period == period_02 && dealMode == dealMode_10 && profitType == PROFIT_TYPE_01){
			$("#bussBenchMarkInfoDto_div").toggle(false);
			$("#bussBenchMarkLayeredInfoDto_div").toggle(true);
			//分档产品显示款型，周期产品显示周期
			if(dealMode == dealMode_11){
				$("#prodStyle_div").toggle(true);
				$("#investmentCycle_div").toggle(false);
				$("#bussBenchMarkLayeredInfoDto_investmentCycle").val('');
				//分档产品业绩基准列表显示产品款型
				if(bussBenchMarkInfoTable){
					//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: true},{field:'investmentCycle', visible: false}]);
				}
			}else if(dealMode == dealMode_10){
				$("#investmentCycle_div").toggle(true);
				$("#prodStyle_div").toggle(false);
				$("#bussBenchMarkLayeredInfoDto_prodStyle").val('');
				//周期型产品业绩基准列表显示产品周期
				if(bussBenchMarkInfoTable){
					//bussBenchMarkInfoTable.showColumns([{field:'prodStyle', visible: false},{field:'investmentCycle', visible: true}]);
				}
			}
		}else {
			$("#bussBenchMarkInfoDto_div").toggle(true);
			$("#bussBenchMarkLayeredInfoDto_div").toggle(false);
		}
	}
	
	//资金投向地区联动
	$("#bussRepFinProductInfoDto_repInvestArea").change(function(){
		var repInvestArea = $("#bussRepFinProductInfoDto_repInvestArea").val();
		if(repInvestArea == saleAreaDeclareIn){
			$("#bussRepFinProductInfoDto_repInvestCountry").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repInvestCountry").attr('disabled',true);
			//境外托管机构国别
			$("#bussRepFinProductInfoDto_repOutDepCtry").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repOutDepCtry").attr('disabled',true);
			//境外托管机构名称
			$("#bussRepFinProductInfoDto_repOutDepName").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repOutDepName").attr('disabled',true);
		}else{
			$("#bussRepFinProductInfoDto_repInvestCountry").attr('disabled',false);
			$("#bussRepFinProductInfoDto_repOutDepCtry").attr('disabled',false);
			$("#bussRepFinProductInfoDto_repOutDepName").attr('disabled',false);
		}
		if(repInvestArea == saleAreaDeclareOut){
			$("#bussRepFinProductInfoDto_repFinanSerModel").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repFinanSerModel").attr('disabled',true);
			$("#bussRepFinProductInfoDto_repInDepName").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repInDepName").attr('disabled',true);
		}else{
			$("#bussRepFinProductInfoDto_repFinanSerModel").attr('disabled',false);
			$("#bussRepFinProductInfoDto_repInDepName").attr('disabled',false);
		}
	});
	//产品管理模式联动
	$("#bussRepFinProductInfoDto_repPmmode").change(function(){
		var repPmmode = $("#bussRepFinProductInfoDto_repPmmode").val();
		if(repPmmode == prodManageModeBank || repPmmode == prodManageModeSpv){
			$("#bussRepFinProductInfoDto_repMname").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repMname").attr("disabled",true);
		}else{
			$("#bussRepFinProductInfoDto_repMname").attr("disabled",false);
		}
	});
	//设置最短期限联动
	$("#bussRepFinProductInfoDto_isShortTerm").change(function(){
		var isShortTerm = $("#bussRepFinProductInfoDto_isShortTerm").val();
		if(isShortTerm == NO_01){
			$("#bussRepFinProductInfoDto_shortTerm").val('').trigger('change');
			$("#bussRepFinProductInfoDto_shortTerm").attr("disabled",true);
			$("#bussRepFinProductInfoDto_isFreeRedem").val('').trigger('change');
			$("#bussRepFinProductInfoDto_isFreeRedem").attr("disabled",true);
		}else{
			$("#bussRepFinProductInfoDto_shortTerm").attr("disabled",false);
			$("#bussRepFinProductInfoDto_isFreeRedem").attr("disabled",false);
		}
	});
	
	//合作模式联动
	$("#bussRepFinProductInfoDto_repContMode").change(function(){
		var repContMode = $("#bussRepFinProductInfoDto_repContMode").val();
		if(repContMode == copModeSigRun){
			$("#bussRepFinProductInfoDto_repCoopName").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repCoopName").attr("disabled",true);
		}else{
			$("#bussRepFinProductInfoDto_repCoopName").attr("disabled",false);
		}
	});
	
	//产品增信机构联动
	var repCreditFlagChange = function(){
		var repCreditFlag = $("#bussRepFinProductInfoDto_repCreditFlag")[0].checked;
		if(!repCreditFlag){
			//产品增信机构类型
			$("#bussRepFinProductInfoDto_repCreditType").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repCreditType").attr("disabled",true);
			//产品增信形式
			$("#bussRepFinProductInfoDto_repCreditMode").val('').trigger('change');
			$("#bussRepFinProductInfoDto_repCreditMode").attr("disabled",true);
		}else{
			$("#bussRepFinProductInfoDto_repCreditType").attr("disabled",false);
			$("#bussRepFinProductInfoDto_repCreditMode").attr("disabled",false);
		}
	};
	//运作模式联动
	$("#bussRepFinProductInfoDto_repOperateMode").change(function(){
		var repOperateMode = $("#bussRepFinProductInfoDto_repOperateMode").val();
			if(repOperateMode == prodRunModeCloNN || repOperateMode == prodRunModeCloN){
				//是否设置最短持有期限
				$("#bussRepFinProductInfoDto_isShortTerm").val('').trigger('change');
				$("#bussRepFinProductInfoDto_isShortTerm").attr("disabled",true);
				$("#bussRepFinProductInfoDto_shortTerm").val('').trigger('change');
				$("#bussRepFinProductInfoDto_shortTerm").attr("disabled",true);
				$("#bussRepFinProductInfoDto_isFreeRedem").val('').trigger('change');
				$("#bussRepFinProductInfoDto_isFreeRedem").attr("disabled",true);
			}else{
				$("#bussRepFinProductInfoDto_isShortTerm").attr("disabled",false);
			}
	});
	//分红规则联动
	var payCycleChange = function(){
		var payCycle = $('#bussFinProductBonusPlanDto_payCycle').val();
		if(payCycle == payCycle_01){
			$('#bussFinProductBonusPlanDto_payCycleVal').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductBonusPlanDto_payCycleUnit').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductBonusPlanDto_calcRule').val('01').attr("disabled",true).trigger('change');
			$('#bussFinProductBonusPlanDto_firstPayDate').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductBonusPlanDto_endTdayRule').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductBonusPlanDto_payTdayRule').val('').trigger('change').trigger('change');
		}else{
			$('#bussFinProductBonusPlanDto_payCycleVal').attr("disabled",false);
			$('#bussFinProductBonusPlanDto_payCycleUnit').attr("disabled",false);
			$('#bussFinProductBonusPlanDto_calcRule').val('01').attr("disabled",false).trigger('change');
			//$('#bussFinProductBonusPlanDto_firstPayDate').val('').attr("disabled",false).trigger('change');
			$('#bussFinProductBonusPlanDto_endTdayRule').val('04').attr("disabled",false).trigger('change');
			$('#bussFinProductBonusPlanDto_payTdayRule').val('02').trigger('change').trigger('change');
		}
	}
	
	//分红计划推算方法联动
	var bonusPlancalcRuleChange = function(){
		var calcRule = $("#bussFinProductBonusPlanDto_calcRule").val();
		if(calcRule == '03'){
			$('#bussFinProductBonusPlanDto_firstPayDate').attr("disabled",false);
		}else{
			$('#bussFinProductBonusPlanDto_firstPayDate').val('').attr("disabled",true).trigger('change');
		}
	}
	
	//付息规则联动
	var payPlanpayCycleChange = function(){
		var payCycle = $('#bussFinProductPayPlanDto_payCycle').val();
		if(payCycle == payCycle_01){
			$('#bussFinProductPayPlanDto_payCycleVal').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductPayPlanDto_payCycleUnit').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductPayPlanDto_calcRule').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductPayPlanDto_firstPayDate').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductPayPlanDto_endTdayRule').val('').attr("disabled",true).trigger('change');
			$('#bussFinProductPayPlanDto_payTdayRule').val('02').trigger('change');
		}else{
			$('#bussFinProductPayPlanDto_payCycleVal').attr("disabled",false);
			$('#bussFinProductPayPlanDto_payCycleUnit').attr("disabled",false);
			$('#bussFinProductPayPlanDto_calcRule').val('01').attr("disabled",false).trigger('change');
			//$('#bussFinProductBonusPlanDto_firstPayDate').val('').attr("disabled",false).trigger('change');
			$('#bussFinProductPayPlanDto_endTdayRule').val('04').attr("disabled",false).trigger('change');
			$('#bussFinProductPayPlanDto_payTdayRule').val('02').trigger('change').trigger('change');
		}
	}
	
	//付息计划推算方法联动
	var payPlanCalcRuleChange = function(){
		var calcRule = $("#bussFinProductPayPlanDto_calcRule").val();
		if(calcRule == '03'){
			$('#bussFinProductPayPlanDto_firstPayDate').attr("disabled",false);
		}else{
			$('#bussFinProductPayPlanDto_firstPayDate').val('').attr("disabled",true).trigger('change');
		}
	}
	
	//费用名称联动
	var feeNameChangeAction = function(){
    	var feeId = $('#bussFinProductFloatFeeDto_feeId').val();
    	if (feeId && feeId != ''){
			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFloatFeeCommon/queryMstFeeinfoByFeeId'), {feeId : feeId}, function(result){
				if (result){
					$('#bussFinProductFloatFeeDto_basis').val(result.basis).trigger('change');
				}
			})
			$('#bussFinProductFloatFeeDto_div_floatManageFee').toggle(true);
			
		}else{
			$('#bussFinProductFloatFeeDto_basis').val('').trigger('change');
			$('#bussFinProductFloatFeeDto_div_floatManageFee').toggle(false);
		}
    }
	
	/**
     * 不分档浮动管理费超额收益分层
     *
     */
    var floatManageFeeTable=$('#bussFinProductFloatFeeDto_floatManageFee').joyintable({
    	data:finFloatCalcRuleDetailDtoList==null?[]:finFloatCalcRuleDetailDtoList,
        searchOnInit:  true,
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
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    
    /**
     * 绑定删除事件
     */
    floatManageFeeTable.bind('click', '.delt', function(data, index) {
    	floatManageFeeTable.deleteRow(index);
    });
	
	//快录
	var quickRecord = function(){
		var templateId = $("#productModuleInfo_templateId").val();
		if(templateId){
        	Joyin.ajax(JoyinUtil.getRealPath('/invest/product/productelement/module/FinProductModule002/getModeleInfoByTemplateId'), {'templateId' : templateId}, function(result){
				if (result && result.data){
					Joyin.confirm(JoyinUtil.getMessage('FIN_PROD_E_0002'), function() {
						
						// 初始化页面
						var finProductModule = result.data;
						
						// 基础信息
			        	initPageDataAction("productModuleInfo", finProductModule.productModuleInfo);
			        	//募集要素
			        	initPageDataAction("bussFinProductSaleParamDto", finProductModule.bussFinProductSaleParamDto);
			        	//业绩基准
			        	initPageDataAction("bussBenchMarkInfoDto", finProductModule.bussBenchMarkInfoDto);
			        	//分档业绩基准
			        	initPageDataAction("bussBenchMarkLayeredInfoDto", finProductModule.bussBenchMarkLayeredInfoDto);
			        	bussBenchMarkLayeredInfoDtoList = finProductModule.bussBenchMarkLayeredInfoDto.bussBenchMarkLayeredInfoDtoList;
			        	if(bussBenchMarkLayeredInfoDtoList && bussBenchMarkLayeredInfoDtoList.length > 0){
			        		bussBenchMarkInfoTable.clear();
			        		_.each(bussBenchMarkLayeredInfoDtoList,function (data){
			        			bussBenchMarkInfoTable.addRow(data);
			        		})
			    	    }
			        	//估值信息
			        	initPageDataAction("bussFinProductValuationDto", finProductModule.bussFinProductValuationDto);
			        	//开放计划-申购
			        	initPageDataAction("bussFinProductOpenPlanDto", finProductModule.bussFinProductOpenPlanDto);
			        	if($("#bussFinProductOpenPlanDto_openType").val() == '01'){
			        		$('#bussFinProductOpenPlanDto_sameFLag').prop("checked",false);
			        		$("#red_div").show();
			        	}
			        	//开放计划-赎回
			        	initPageDataAction("bussFinProductOpenPlanRedDto", finProductModule.bussFinProductOpenPlanRedDto);
			        	//分红计划
			        	initPageDataAction("bussFinProductBonusPlanDto", finProductModule.bussFinProductBonusPlanDto);
			        	//付息计划
			        	initPageDataAction("bussFinProductPayPlanDto", finProductModule.bussFinProductPayPlanDto);
			        	//浮动管理费
			        	initPageDataAction("bussBenchMarkFloatInfoDto_float", finProductModule.bussBenchMarkFloatInfoDto);
			        	initPageDataAction("bussFinProductFloatFeeDto", finProductModule.bussFinProductFloatFeeDto);
			        	bussFinProductFloatFeeInfoDtoList = finProductModule.bussFinProductFloatFeeDto.bussFinProductFloatFeeInfoDtoList
			        	if(bussFinProductFloatFeeInfoDtoList && bussFinProductFloatFeeInfoDtoList.length > 0){
			        		bussFinProductFloatFeeInfoTable.clear();
			        		_.each(bussFinProductFloatFeeInfoDtoList,function (data){
			        			bussFinProductFloatFeeInfoTable.addRow(data);
			        		})
			    	    }
			        	finFloatCalcRuleDetailDtoList = finProductModule.bussFinProductFloatFeeDto.finFloatCalcRuleDetailDtoList;
			        	if(finFloatCalcRuleDetailDtoList && finFloatCalcRuleDetailDtoList.length > 0){
			        		floatManageFeeTable.clear();
			        		_.each(finFloatCalcRuleDetailDtoList,function (data){
			        			floatManageFeeTable.addRow(data);
			        		})
			    	    }
			        	//申报信息
			        	initPageDataAction("bussRepFinProductInfoDto", finProductModule.bussRepFinProductInfoDto);
			        	//分层信息
			        	finProductPtlLayeredDtoList = finProductModule.bussFinProductLayeredInfoDto;
			        	if(finProductPtlLayeredDtoList && finProductPtlLayeredDtoList.length > 0){
			        		bussFinProductLayeredInfoTable.clear();
			        		_.each(finProductPtlLayeredDtoList,function (data){
			        			bussFinProductLayeredInfoTable.addRow(data);
			        		})
			    	    }
			        	tableDataChange();
			        	//产品费用
			        	bussFinProductFeeInfoDtoList = finProductModule.bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList;
			        	if(bussFinProductFeeInfoDtoList && bussFinProductFeeInfoDtoList.length > 0){
			        		bussFinProductFeeInfoTable.clear();
			        		_.each(bussFinProductFeeInfoDtoList,function (data){
			        			bussFinProductFeeInfoTable.addRow(data);
			        		})
			    	    }
			        	//回补信息
			        	finProductAnapleroticTabInfoDtoList = finProductModule.finProductAnapleroticTabInfoDtoList;
			        	if(finProductAnapleroticTabInfoDtoList && finProductAnapleroticTabInfoDtoList.length > 0){
			        		bussFinProductAnapleroticInfoTable.clear();
			        		_.each(finProductAnapleroticTabInfoDtoList,function (data){
			        			bussFinProductAnapleroticInfoTable.addRow(data);
			        		})
			    	    }
			        	
					});
				} else {
					Joyin.error(JoyinUtil.getLabel('FinProductModule001.templetId.right'));
                }
			});
		}else{
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038', JoyinUtil.getLabel('FinProductModule001.templetId')));
		}
	}
	
	/**
     * 数据赋值
     */
    var initPageDataAction = function(entityName, data){
    	$.each(data, function(id,value){
			if ($('#' + entityName + '_' + id).length > 0){
				// 快速录入不需要模板代码
				if (id != 'templateId'){
					// switch 特殊处理
					if ($('#' + entityName + '_' + id).attr('lay-skin') === 'switch'){
						$('#' + entityName + '_' + id).prop('checked', false);
						// 是否,有无
						if (value === 'Y' || value === '01'){
							$('#' + entityName + '_' + id).prop('checked', true);
						}
						layui.form.render('checkbox');
						
						// 中债的  增信标识
						if (id === 'repCreditFlag'){
							repCreditFlagChange();
						}
					}else{
						// 清空值
						$('#' + entityName + '_' + id).val('');
						// 日期特殊处理
						if ($('#' + entityName + '_' + id).hasClass('input-dateSelect')){
							$('#' + entityName + '_' + id).val(Date.ofDate(value).format('yyyy-MM-dd'));
						}
						// data-url 下拉框
						else if ($('#' + entityName + '_' + id).attr("data-url")){
							$('#' + entityName + '_' + id).textSelect('setValue', value); 
						}else{
							// 多选下拉框特殊处理
							if ($('#' + entityName + '_' + id).attr('multiple') === 'multiple'){
								$('#' + entityName + '_' + id).val(value.split(",")).trigger('change');
								
								// 开放计划特殊处理radio
								if ((entityName === 'bussFinProductOpenPlanDto' && value != '') || (entityName === 'bussFinProductOpenPlanRedDto' && value != '')){
									if (id === 'dayOfMonth'){
										$('#' + entityName + '_radioDiv').find(':radio[value="0"]').prop('checked', true);
										$('#' + entityName + '_radioDiv').find(':radio[value="1"]').prop('checked', false);
										$('#' + entityName + '_radioDiv').find(':radio[value="2"]').prop('checked', false);
										$('#' + entityName + '_radioDiv').find(':radio').prop('disabled', false);
										layui.form.render();
									}
									if (id === 'workdayOfMonth'){
										$('#' + entityName + '_radioDiv').find(':radio[value="0"]').prop('checked', false);
										$('#' + entityName + '_radioDiv').find(':radio[value="1"]').prop('checked', true);
										$('#' + entityName + '_radioDiv').find(':radio[value="2"]').prop('checked', false);
										$('#' + entityName + '_radioDiv').find(':radio').prop('disabled', false);
										layui.form.render();
									}
									if (id === 'weekOfMonth'){
									    $('#' + entityName + '_radioDiv').find(':radio[value="0"]').prop('checked', false);
                                        $('#' + entityName + '_radioDiv').find(':radio[value="1"]').prop('checked', false);
                                        $('#' + entityName + '_radioDiv').find(':radio[value="2"]').prop('checked', true);
                                        $('#' + entityName + '_radioDiv').find(':radio').prop('disabled', false);
                                        layui.form.render();
                                    }
								}
							} else{
								$('#' + entityName + '_' + id).val(value).trigger('change');
								
							}
						}
					}
				}
			}else{
				//募集信息存申报信息参数赋值处理
				if(entityName == 'bussRepFinProductInfoDto' && id == 'repIssueType' ){
					$("#bussFinProductSaleParamDto_issueType").val(value).trigger('change');
				}
				//募集币种
				if(entityName == 'bussRepFinProductInfoDto' && id == 'repRaiseCcy'){
					$('#bussFinProductSaleParamDto_ccy').textSelect('setValue', value);
				}
				//兑付本金币种
				if (entityName == 'bussRepFinProductInfoDto' && id == 'repCashPrinCcy'){
					$('#bussFinProductSaleParamDto_repCashPrinCcy').textSelect('setValue', value);
				}
				//兑付收益币种
				if(entityName == 'bussRepFinProductInfoDto' && id == 'repCashProfCcy'){
					$('#bussFinProductSaleParamDto_repCashProfCcy').textSelect('setValue', value);
				}
				//销售部门
				if(entityName === 'productModuleInfo' && value != '' && id == 'saleDepartment'){
					$('#bussFinProductSaleParamDto_saleDepartment').val(value.split(",")).trigger('change');
				}
				//赎回确认天数
				if(id == 'redSpeed' && value != ''){
					$("#bussFinProductOpenPlanRedDto_redSpeed").val(value).trigger('change');
				}
				//申购确认天数
				if(id == 'purSpeed' && value != ''){
					$("#bussFinProductOpenPlanDto_purSpeed").val(value).trigger('change');
				}
				//同业机构
				if(id == 'sameOrg' && value != ''){
					$("#bussFinProductSaleParamDto_sameOrg").val(value.split(",")).trigger('change');
				}
			}
		});
    }
	
	//画面初始化
    var init = function(){
    		profitTypeChange();
    		dealModeChange();
    		operationTypeChange();
    		publishTypeChange();
    		rateTypeChange();
    		publishTypeChangeFd();
    		rateTypeChangeFd();
    		//openModeChange();
    		openDaysTypeChange();
    		//openCycleUnitRedChange();
    		//openModeRedChange();
    		openDaysTypeRedChange();
    		//openCycleUnitChange();
    		floatFeeRateTypeChange();
    		investmentCycleChange();
     		investorTypeChange2();
    		isShortTermChange();
    		sameFLagChange();
    		investmentCycleChangeAction();
    		tableDataChange();
    		payCycleChange();
    		feeNameChangeAction();
    		payPlanCalcRuleChange();
    		payPlanpayCycleChange();
    		openPlancalcRuleChange();
    		openPlanRedcalcRuleChange();
    		//申报页面初始化
    		//资金投向地区为境内
    		var repInvestArea = $("#bussRepFinProductInfoDto_repInvestArea").val();
    		if(repInvestArea != saleAreaDeclareInOut && repInvestArea != saleAreaDeclareOut){
    			$("#bussRepFinProductInfoDto_repInvestCountry").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repInvestCountry").attr('disabled',true);
    			//境外托管机构国别
    			$("#bussRepFinProductInfoDto_repOutDepCtry").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repOutDepCtry").attr('disabled',true);
    			//境外托管机构名称
    			$("#bussRepFinProductInfoDto_repOutDepName").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repOutDepName").attr('disabled',true);
    		}
    		if(repInvestArea != saleAreaDeclareIn && repInvestArea != saleAreaDeclareInOut){
    			$("#bussRepFinProductInfoDto_repFinanSerModel").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repFinanSerModel").attr('disabled',true);
    			$("#bussRepFinProductInfoDto_repInDepName").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repInDepName").attr('disabled',true);
    		}
    		//运作模式
    		var repOperateMode = $("#bussRepFinProductInfoDto_repOperateMode").val();
    		if(repOperateMode != prodRunModeOpeN && repOperateMode != prodRunModeOpeNN){
    			//是否设置最短持有期限
    			$("#bussRepFinProductInfoDto_isShortTerm").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_isShortTerm").attr("disabled",true);
    			$("#bussRepFinProductInfoDto_shortTerm").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_shortTerm").attr("disabled",true);
    			$("#bussRepFinProductInfoDto_isFreeRedem").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_isFreeRedem").attr("disabled",true);
    		}
    		//产品增信机构
    		var repCreditFlag = $("#bussRepFinProductInfoDto_repCreditFlag")[0].checked;
    		if(!repCreditFlag){
    			//产品增信机构类型
    			$("#bussRepFinProductInfoDto_repCreditType").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repCreditType").attr("disabled",true);
    			//产品增信形式
    			$("#bussRepFinProductInfoDto_repCreditMode").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repCreditMode").attr("disabled",true);
    		}
    		//合作模式
    		var repContMode = $("#bussRepFinProductInfoDto_repContMode").val();
    		if(repContMode == copModeSigRun || !repContMode){
    			$("#bussRepFinProductInfoDto_repCoopName").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repCoopName").attr("disabled",true);
    		}
    		//最短有效期限
    		var isShortTerm = $("#bussRepFinProductInfoDto_isShortTerm").val();
    		if(isShortTerm != YES_01){
    			$("#bussRepFinProductInfoDto_shortTerm").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_shortTerm").attr("disabled",true);
    			$("#bussRepFinProductInfoDto_isFreeRedem").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_isFreeRedem").attr("disabled",true);
    		}
    		//产品管理模式
    		var repPmmode = $("#bussRepFinProductInfoDto_repPmmode").val();
    		if(repPmmode != prodManageModeActM && repPmmode != prodManageModeOthO){
    			$("#bussRepFinProductInfoDto_repMname").val('').trigger('change');
    			$("#bussRepFinProductInfoDto_repMname").attr("disabled",true);
    		}
    }
    /**
     * 画面加载完成
     *
     */
    $(function() {
        init();
        layui.form.on('switch(repCreditFlagFilter)', repCreditFlagChange);
        //FinProductCommon.bindEvent();
        // 保存按钮执行事件
        $('#FinProductModule002_btn_save').click(function(){
            onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#FinProductModule002_btn_submit').click(function(){
            onSaveOrSubmitAction('submit');
        });
        // 模板代码快速录入
        $('#FinProductModule002_btn_quickInput').click(function(){
        	quickRecord();
		});
        //产品收益类型
        $("#productModuleInfo_profitType").change(function(){
        	profitTypeChange();
        });
        //产品处理模式
        $("#productModuleInfo_dealMode").change(function(){
        	dealModeChange();
        });
        //发行方式
        $("#productModuleInfo_operationType").change(function(){
        	operationTypeChange();
        });
        //是否区间披露
        $("#bussBenchMarkInfoDto_publishType").change(function(){
        	publishTypeChange();
        });
        //业绩基准-利率类型
        $("#bussBenchMarkInfoDto_rateType").change(function(){
        	rateTypeChange();
        });
        //分档-是否区间披露
        $("#bussBenchMarkLayeredInfoDto_publishType").change(function(){
        	publishTypeChangeFd();
        });
        //分档-业绩基准-利率类型
        $("#bussBenchMarkLayeredInfoDto_rateType").change(function(){
        	rateTypeChangeFd();
        });
        //申购-开放模式
        $("#bussFinProductOpenPlanDto_openMode").change(function(){
        	openModeChange();
        })
        //申购-开放天数类型
        $("#bussFinProductOpenPlanDto_openDaysType").change(function(){
        	openDaysTypeChange();
        })
        //赎回-周期单位
        $("#bussFinProductOpenPlanRedDto_openCycleUnit").change(function(){
        	openCycleUnitRedChange();
        })
        //赎回-开放模式
        $("#bussFinProductOpenPlanRedDto_openMode").change(function(){
        	openModeRedChange();
        })
        //赎回-开放天数类型
        $("#bussFinProductOpenPlanRedDto_openDaysType").change(function(){
        	openDaysTypeRedChange();
        })
        //申购-周期单位
        $("#bussFinProductOpenPlanDto_openCycleUnit").change(function(){
        	openCycleUnitChange();
        })
        //申购推算规则联动
        $('#bussFinProductOpenPlanDto_calcRule').change(function(){
        	calcRuleChange();
        })
        //赎回推算规则联动
        $('#bussFinProductOpenPlanRedDto_calcRule').change(function(){
        	calcRuleRedChange();
        })
        //申赎同频
        layui.form.on('switch(isSameFLagFilter)', sameFLagChange);
        //浮动管理费-利率类型
        $("#bussBenchMarkFloatInfoDto_rateType").change(function(){
        	floatFeeRateTypeChange();
        })
        //是否回补
        $("#productModuleInfo_isAnaplerotic").change(function(){
        	investmentCycleChange();
        })
        //募集要素-目标客户类型
        $("#bussFinProductSaleParamDto_investorType").change(function(){
        	investorTypeChange();
        })
        //申报要素-是否设置最短持有期限
        $("#bussRepFinProductInfoDto_isShortTerm").change(function(){
        	isShortTermChange();
        })
        //投资周期
        $('#productModuleInfo_investmentCycle').change(function(){
			investmentCycleChangeAction();
		});
        //期限品种联动
        $("#productModuleInfo_period").change(function(){
        	periodChange();
        })
        //分红规则联动
        $('#bussFinProductBonusPlanDto_payCycle').change(function(){
        	payCycleChange();
        })
        //分红计划推算方法联动
        $("#bussFinProductBonusPlanDto_calcRule").change(function(){
        	bonusPlancalcRuleChange();
        })
        //分红规则联动
        $('#bussFinProductPayPlanDto_payCycle').change(function(){
        	payPlanpayCycleChange();
        })
        //分红计划推算方法联动
        $("#bussFinProductPayPlanDto_calcRule").change(function(){
        	payPlanCalcRuleChange();
        })
        //浮动管理费费用名称联动
        $('#bussFinProductFloatFeeDto_feeId').change(function(){
        	feeNameChangeAction();
        })
        // 投资资产种类及比例
		$('#bussRepFinProductInfoDto_repInvTypeRat_showRule').click(function(){
			Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repInvTypeRat.showRule"));
		});
        // 收益率分档情况说明
		$('#bussRepFinProductInfoDto_repYieldComment_showRule').click(function(){
			Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repYieldComment.showRule"));
		});
		//申购-推算方法联动
		$('#bussFinProductOpenPlanDto_calcMethod').change(function(){
			openPlancalcRuleChange();
		})
		//赎回-推算方法联动
		$('#bussFinProductOpenPlanRedDto_calcMethod').change(function(){
			openPlanRedcalcRuleChange();
		})
        //申购计划单选框联动
        layui.form.on('radio(openDateFilter)', function(data){
    		if(data) {
                if(data.value === '0') {
                	$('#bussFinProductOpenPlanDto_dayOfMonth').prop('disabled', false).prop('readonly', false);
                	$('#bussFinProductOpenPlanDto_workdayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                	$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true).prop('readonly', true);
                } else if(data.value === '1') {
                	$('#bussFinProductOpenPlanDto_workdayOfMonth').prop('disabled', false).prop('readonly', false);
                	$('#bussFinProductOpenPlanDto_dayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                	$('#bussFinProductOpenPlanDto_weekOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanDto_workdayOfWeek').val('').trigger('change').prop('disabled',true).prop('readonly', true);
                } else {
                    $('#bussFinProductOpenPlanDto_weekOfMonth').trigger('change').prop('disabled', false).prop('readonly', false);
                    $('#bussFinProductOpenPlanDto_workdayOfWeek').trigger('change').prop('disabled',false).prop('readonly', false);
                    $('#bussFinProductOpenPlanDto_workdayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanDto_dayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                }
            }
		});
        //赎回计划单选框联动
        layui.form.on('radio(openDateFilterRed)', function(data){
    		if(data) {
                if(data.value === '0') {
                	$('#bussFinProductOpenPlanRedDto_dayOfMonth').prop('disabled', false).prop('readonly', false);
                	$('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                	$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true).prop('readonly', true);
                } else if(data.value === '1') {
                	$('#bussFinProductOpenPlanRedDto_workdayOfMonth').prop('disabled', false).prop('readonly', false);
                	$('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                	$('#bussFinProductOpenPlanRedDto_weekOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanRedDto_workdayOfWeek').val('').trigger('change').prop('disabled',true).prop('readonly', true);
                } else {
                    $('#bussFinProductOpenPlanRedDto_weekOfMonth').trigger('change').prop('disabled', false).prop('readonly', false);
                    $('#bussFinProductOpenPlanRedDto_workdayOfWeek').trigger('change').prop('disabled',false).prop('readonly', false);
                    $('#bussFinProductOpenPlanRedDto_workdayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                    $('#bussFinProductOpenPlanRedDto_dayOfMonth').val('').trigger('change').prop('disabled', true).prop('readonly', true);
                }
            }
		});
    });
}());