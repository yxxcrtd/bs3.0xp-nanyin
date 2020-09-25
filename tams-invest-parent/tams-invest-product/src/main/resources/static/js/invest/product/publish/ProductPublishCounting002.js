/**
 * 发行规划试算-新建
 */
(function() {
	var prdPlanConfigTable;
	var prdPlanResult;
	var prdPlanSerisResult = [];
	//发行配置列表
	var prdIssuePlanStrategyList = [];
	//发行配置明细列表
	var prdIssuePlanStrategyDetailList = [];
	//初始资产大类列表
	var assetTableValList = [];
	/**
     * 定义存续产品系列列表
     */
    var prdPlanInfoTable = $("#prdPlanInfoTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/publish/ProductPublishCounting002/getPrdPlanInfoList'),
            type: 'POST',
            data:function() {
                return getPrdPlanInfoParams();
            } 
        },
        cols:[
			 {field:'isNew',title: 'ProductPublishCounting002.isNew', width:250,sortable:false}
        	,{field:'prdPlanSerisName',title: 'ProductPublishCounting002.prdPlanSerisName', width:250,sortable:false, visible:true}
            ,{field:'planTargetAmt',title: 'ProductPublishCounting002.planTargetAmt', width:250,sortable:false,type: 'money'}
            ,{field:'planChangeAmt',title: 'ProductPublishCounting002.planChangeAmt', width:250,sortable:false,type: 'money'}
        ],
        page: false,
        checkbox: false,
        /**
         * 加载完成回调
         */
        done: function(table, allData){
        	//无数据
        	if(table.length == 0){
        		Joyin.error("规划起始日规划结束日必须等于产品规划信息维护和资产规划信息维护中的一条记录");
        		return;
        	}
        	prdPlanResult = table;
        	for(var i in table){
        		prdPlanSerisResult[i] = {
        				id : table[i].prdPlanSerisId,
        				text : table[i].prdPlanSerisName
        		}
        	}
        	prdPlanConfigTableInit();
        }
    });
    var getPrdPlanInfoParams = function(){
    	var params = {
    			vdate : $("#vdate").val(),
    			edate : $("#edate").val(),
    			workflowId : workflowId,
    			prdIssueplanCode : $("#prdIssueplanCode").val()
    	}
    	return params;
    }
    /**
     * 存续产品系列列表加载
     */
    var prdPlanInfoDataInit = function(){
    	if(prdPlanInfoTable.table){
    		prdPlanInfoTable.reload();
    	}
    	else{
    		prdPlanInfoTable.load();
    	}
    };
    /**
     * 定义资产大类列表
     */
    var assetTable = $("#assetTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/publish/ProductPublishCounting002/getAssetInfoList'),
            type: 'POST',
            data:function() {
                return getPrdPlanInfoParams();
            } 
        },
        cols:[
        	{field:'assetTypeStr',title: 'ProductPublishCounting002.assetType', width:250,sortable:false}
            ,{field:'planTargetAmt',title: 'ProductPublishCounting002.planTargetAmtBig', width:250,sortable:false,type: 'money'}
            ,{field:'planChangeAmt',title: 'ProductPublishCounting002.planChangeAmt', width:250,sortable:false,type: 'money'}
        ],
        page: false,
        checkbox: false,
        /**
         * 加载完成回调
         */
        done: function(table, allData){
        	assetTableValList = table;
        }
    });
    /**
     * 资产大类列表加载
     */
    var assetTableInit = function(){
    	if(assetTable.table){
    		assetTable.reload();
    	}
    	else{
    		assetTable.load();
    	}
    };
    
    /**
     * 产品系列配置列表
     */
    var prdPlanConfigTableInit = function(){
    	 $("#prdPlanConfigDiv").html('<table class="layui-table" id="prdPlanConfigTable"></table>');
    	prdPlanConfigTable = $("#prdPlanConfigTable").joyintable({
        	ajax: {
                url: JoyinUtil.getRealPath('invest/product/publish/ProductPublishCounting002/getPrdPlanStrategyList'),
                type: 'POST',
                data:function() {
                    return getPrdPlanInfoParams();
                } 
            },
            cols:[
            	 {field:'order',title: 'ProductPublishCounting002.order', width:100,sortable:false, visible:false}
                ,{field:'prdPlanSerisName',title: 'ProductPublishCounting002.prdPlanSerisName', width:180,sortable:false,editOption:{type:'select',result: JSON.stringify(prdPlanSerisResult)},edit:true}
                ,{field:'planTargetAmt',title: 'ProductPublishCounting002.planTargetAmt', width:140,sortable:false,type: 'money'}
                ,{field:'planChangeAmt',title: 'ProductPublishCounting002.planChangeAmt', width:180,sortable:false,type: 'money'}
                ,{field:'strategyId',title: 'ProductPublishCounting002.tacticConfigCode', width:180,sortable:false,edit:true, editOption:{type:'select', result:ISSUE_PLAN_STRATEGY}}
                ,{field:'configScale',title: 'ProductPublishCounting002.tacticConfigAmt', width:140,sortable:false,edit:true,type: 'money'}
                ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
            ],
            checkbox: false,
            addbtn: ($("#pageModel").val() == PAGE_MODEL_NEW||$("#pageModel").val() == PAGE_MODEL_EDIT) ? true : false,
            searchOnInit:true,
            page: false,
            addclick: function(){
            	prdPlanConfigTable.renderRow(prdPlanConfigTable.getAllRows().length-1, 
        				{order : prdPlanConfigTable.getAllRows().length});
            },
			dblclick:function(table, data) {
        		var strategyList = [];
				strategyList.push(data);
				if(data.strategyId==""|| data.strategyId==null ){
					Joyin.error("请填写配置策略");
					return;
				}
				if(data.configScale==""|| data.configScale==null){
					Joyin.error("请填写配置规模");
					return;
				}
				var object = {
					strategyList : JSON.stringify(strategyList)
				};
				data.pageModel=PAGE_MODEL_REFERENCE;
				Joyin.dialog(JoyinUtil.getLabel("ProductPublishCounting003.reference"),
					JoyinUtil.getRealPath("invest/product/publish/ProductPublishCounting003/init"),
					"70%", "70%",object,function(){}
				);
			},
            /**
             * table编辑后联动事件
             */
            editCell: function(value, data, field, cell, column){
            	//选择规划系列联动
        		if(field == "prdPlanSerisName"){
        			for(var i in prdPlanResult){
        				if(prdPlanResult[i].prdPlanSerisId == value){
        					prdPlanConfigTable.renderRow(data.LAY_TABLE_INDEX, 
        	        				{planTargetAmt: prdPlanResult[i].planTargetAmt});
        					prdPlanConfigTable.renderRow(data.LAY_TABLE_INDEX, 
        	        				{planChangeAmt: prdPlanResult[i].planChangeAmt});
        				}
        			}
        		}
        		//选择配置规模联动
        		if(field == "configScale"){
        			var prdPlanConfigList = prdPlanConfigTable.getAllRows();
    				var tempConfigAmtTotal = 0;
    				for(var i=0;i<data.LAY_TABLE_INDEX;i++){
    					//同规划系列相加
    					if(prdPlanConfigList[i].prdPlanSerisName == prdPlanConfigList[data.LAY_TABLE_INDEX].prdPlanSerisName){
    						tempConfigAmtTotal = (tempConfigAmtTotal*100 + parseFloat(prdPlanConfigList[i].configScale)*100)/100;
    					}
    				}
    				for(var i in prdPlanResult){
    					if(prdPlanResult[i].prdPlanSerisId == data.prdPlanSerisName){
    						prdPlanConfigTable.renderRow(data.LAY_TABLE_INDEX, 
    								{planChangeAmt: (parseFloat(prdPlanResult[i].planChangeAmt)*100 - parseFloat(tempConfigAmtTotal)*100)/100});
    					}
    				}
        			
        		}
            }
            
        });
    	prdPlanConfigDataInit();
    	prdPlanConfigTable.bind('click', '.delt', function(data, index) {
    		prdPlanConfigTableDelt(data, index);
    	});
    }
    var prdPlanConfigTableDelt = function(data, index){
    	prdPlanConfigTable.removeRow(data);
    	var prdPlanConfigList = prdPlanConfigTable.getAllRows();
    	var prdPlanSeris = data.prdPlanSerisName;
    	for(var i = index;i<prdPlanConfigList.length;i++){
    		//计算顺序重新设值
    		prdPlanConfigTable.renderRow(i, {order : i+1});
    		//同一系列的剩余规划变动量重新设值
    		if(prdPlanConfigList[i].prdPlanSerisName == prdPlanSeris){
    			var tempConfigAmtTotal = 0;
    			for(var j=0;j<i;j++){
					//同规划系列相加
					if(prdPlanConfigList[j].prdPlanSerisName == prdPlanSeris){
						tempConfigAmtTotal = (tempConfigAmtTotal*100 + parseFloat(prdPlanConfigList[j].configScale)*100)/100;
					}
				}
    			for(var k in prdPlanResult){
					if(prdPlanResult[k].prdPlanSerisId == prdPlanSeris){
						prdPlanConfigTable.renderRow(i, 
								{planChangeAmt: (parseFloat(prdPlanResult[k].planChangeAmt)*100 - parseFloat(tempConfigAmtTotal)*100)/100});
					}
				}
    		}
    	}
    }
	var init = function(){
		if(pageModel==PAGE_MODEL_EDIT){
			$('#prdIssueplanName').attr('disabled',true);
			$('#vdate').attr("disabled",true);
			$('#fiedate').attr('disabled',true);
		}
	};
    /**
     * 配置列表加载
     */
    var prdPlanConfigDataInit = function(){
    	prdPlanConfigTable.load();
    };
    /**
     * 区间改变
     */
    var dateChange = function(){
    	if($("#vdate").val()!='' && $("#edate").val()!=''){
    		//存续产品系列列表加载
    		prdPlanInfoDataInit();
    		assetTableInit();
    	}
    }
    /**
     * 试算按钮点击事件
     */
    var countingClick = function(){
    	if($("#vdate").val() == '' || $("#edate").val() == ''){
    		Joyin.error("请先选择规划日期");
    		return;
    	}
    	var prdPlanConfigList = prdPlanConfigTable.getAllRows();
    	var object = {};
    	object.prdIssuePlanStrategyList = prdPlanConfigList;
    	Joyin.ajaxJson(basePath + 'invest/product/publish/ProductPublishCounting002/getPublishCountingList',object,
			function(result) {
    			//发行配置明细列表
    			prdIssuePlanStrategyDetailList = result;
    			//设置资产大类剩余变动量
    			asssetTableValSet();
			}
    	);
    }
    /**
     * 资产大类剩余变动量重新加载
     */
    var asssetTableValSet = function(){
    	for(var i in assetTableValList){
    		var tempPlanChangeAmt = assetTableValList[i].planChangeAmt;
    		for(var j in prdIssuePlanStrategyDetailList){
    			if(assetTableValList[i].assetType == prdIssuePlanStrategyDetailList[j].assetType){
    				tempPlanChangeAmt = (tempPlanChangeAmt*100 - prdIssuePlanStrategyDetailList[j].configScale*100)/100;
    			}
    		}
    		//重置配置后的资产大类剩余变动量1
    		assetTable.renderRow(i, {planChangeAmt: tempPlanChangeAmt});
    	}
    }
    /**
     * 保存提交事件
     */
    function saveOrSubmit(operateType) {
    	if($("#vdate").val() == '' || $("#edate").val() == ''){
    		Joyin.error("请先选择规划日期");
    		return;
    	}
		//BSTOT-299 【发行规划试算】退回至代办的数据，提交时提示‘请试算’ guyunfei 20200108
    	if(prdIssuePlanStrategyDetailList.length == 0 && (workflowId === '' || workflowId == null)){
    		Joyin.error("请先试算");
    		return;
    	}
        var object = $.serializeToObject('#submitForm', true);
        //产品存续系列列表
        object.prdPlanInfoList = prdPlanInfoTable.getAllRows();
        //产品发行配置列表
        object.prdIssuePlanStrategyList = prdPlanConfigTable.getAllRows();
        //产品发行配置明细列表
        object.prdIssuePlanStrategyDetailList = prdIssuePlanStrategyDetailList;
        //产品发行资产规划信息
        object.prdIssuePlanAssetList = assetTable.getAllRows();
        if(operateType === 'revoke'){
            Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductDuration/ProductPublishCounting002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'revoke') {
                    	WorkFlow.doRevokeList(basePath + 'invest/product/productduration/netproductDuration/FinNetProductDuration/doRevoke', object, "FinNetProductDuration_btn_revoke");

                    }
                });
            });
        }else{
	        Joyin.ajaxJson(basePath + 'invest/product/publish/ProductPublishCounting002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/product/publish/ProductPublishCounting002/save', object, "ProductPublishCounting002_btn_submit",null,
	                    		function(){window.top.layui.tab.tabDelete('PRODUCT_SIDE_001_003_008','FinNetProductDuration');});
	                   
	                }else if(operateType === 'submit') {
	                    WorkFlow.doSubmitList(basePath + 'invest/product/publish/ProductPublishCounting002/save', object, "ProductPublishCounting002_btn_submit",null,
                    		function(){
	                    		window.top.layui.tab.tabDelete('PRODUCT_SIDE_001_003_008','ProductPublishCounting002');
                			}
	                    );
	                }
	            });
	        });
        }
    };
    $(function () {
		init();
    	$("#vdate").change(function(){
    		dateChange();
    	});
    	$("#edate").change(function(){
    		dateChange();
    	});
    	//来自待办，加载表格数据
    	if(workflowId && workflowId != ''){
    		prdPlanInfoDataInit();
    		assetTableInit();
    	}
    	//来自新建，加载表格数据
    	else if($("#pageModel").val() == PAGE_MODEL_NEW && $("#vdate").val() != ''&& $("#edate").val() != ''){
    		prdPlanInfoDataInit();
    		assetTableInit();
    	}
    	//来自编辑，加载表格数据
    	else if($("#pageModel").val() == PAGE_MODEL_EDIT && $("#prdIssueplanCode").val() != ''){
    		prdPlanInfoDataInit();
    		assetTableInit();
    	}
    	//试算按钮事件
    	$("#ProductPublishCounting002_count").click(function(){
    		countingClick();
    	});
    	//提交事件 
    	$("#ProductPublishCounting002_btn_submit").click(function(){
    		saveOrSubmit('submit');
    	});
    	
    	
    });
}());