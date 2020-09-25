(function() {
	var loaded = false;
	/**
	 * 保存提交事件
	 * @param operateType
	 * @returns
	 */
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#FinProductQuotation002_form');
        if($('#effDateOld').val() != null && $('#effDateOld').val() != ''){
        	object.effDateOld =Date.ofDate($('#effDateOld').val()).format("yyyy-MM-dd") ;
        }
        object.yield = $("#yield").val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation002/save', object, "FinProductQuotation002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finproductquotation/FinProductQuotation002/save', object, "FinProductQuotation002_btn_submit");
                }
            });
        });
    };
    //输入框显示隐藏事件
	/*function changeInput(index1,index2,index3,index4,index5){
		//$("#FinProductQuotation002_div_saleDepartment").attr("style",index1);
    	$('#FinProductQuotation002_div_valuation').attr("style",index2);
    	$('#FinProductQuotation002_div_totalValue').attr("style",index3);
    	$('#FinProductQuotation002_div_day7YearYield').attr("style",index4);
    	$('#FinProductQuotation002_div_yearYield').attr("style",index5);
	}*/
	
    /**
     * 根据产品类型和是否分层显示输入框
     * @returns
     */
    function showDiv(result) {
		//changeInput("","display:none","display:none","display:none","display:none");
		if(result.isLay === YES_OR_NOY){
			$("#FinProductQuotation002_div_yield").attr("style","display:none");
			$('#FinProductQuotation002_div_layering').attr("hidden",false);
			//分层设置显示，则刷新table,获取分层详情
			/*table.reload();*/
			if (pageModel == PAGE_MODEL_NEW ) {
		        Joyin.ajax(JoyinUtil.getRealPath('invest/product/messageadjust/finproductquotation/FinProductQuotation002/queryQuoteLayDetailInfoList'),
		        		{ effDate:new Date(result.vdate).format("yyyy-MM-dd"),finprodId: $('#FinProductQuotation002_SELECT_finprodId').val()}, 
		        		function(rows) {
			        	table.table ? table.clear(function() {table.addRow(rows)}) : (table.load(),table.addRow(rows));
		                 })
	    	}else{
	    		table.clear(function() {table.addRow(layTypeInfoList)}) ;
	    	}
		}else{
			$("#FinProductQuotation002_div_yield").attr("style","");
			$('#FinProductQuotation002_div_layering').attr("hidden",true);
		}
    }
    
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable({
	/*	ajax: {
            url: JoyinUtil.getRealPath('invest/product/messageadjust/finproductquotation/FinProductQuotation002/queryLayDetailInfoList'),
            type: 'POST',
            data: function() {
                return { workflowId: workflowId, 
                	finprodId: $('#FinProductQuotation002_SELECT_finprodId').val(), 
                	effDate:$('#FinProductQuotation002_effDate').val()
                	};
            }
		},*/
		//pageModel !== PAGE_MODEL_NEW
        searchOnInit: true, 
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: false,
        height : 290,
        done: function() {
        	if(pageModel != PAGE_MODEL_NEW && !loaded) {
        		//不是新建，那么第一次刷新页面的就是[编辑和查看]功能
        		finprodIdChangeAction();
        		loaded = true;
        	}
        }, 
        cols:[
            /*{field:'customLevel', title: 'FinProductQuotation002.customLevel', visible:false, width : 170, edit:true, editOption:{type:'select', multiple: false,result: CLIENT_RANK}, sortable:false}
            ,{field:'termMin', title: 'FinProductQuotation002.termMin', visible:false, width : 170, type:'number', digit: 0, edit:true , sortable:false}
            ,{field:'termMax', title: 'FinProductQuotation002.termMax', visible:false, width : 170, type:'number', digit: 0, edit:true , sortable:false}*/
            {field:'prodStyle', title: 'FinProductQuotation002.prodType', visible:true, width : 170, edit:false , sortable:false}
//            ,{field:'amtMin', title: 'FinProductQuotation002.amtMin', visible:true, width : 170, type:'number', edit:false , sortable:false}
//            ,{field:'amtMax', title: 'FinProductQuotation002.amtMax', visible:true, width : 170, type:'number', edit:false , sortable:false}
            ,{field:'yield', title: 'FinProductQuotation001.yield', visible:true, width : 170, type:'number', edit:true , sortable:false}
            ,{field:'cashId',  visible:false}
            ,{field:'finprodId', visible : false}
            /*,{field:'operation', title: 'SysCommon.operation', visible: true, type:'action', buttons: ['delt']}*/
        ]
    });
	
	//分层信息是通过产品代码和生效日两个字段获取的
	$('#FinProductQuotation002_effDate').change(function(){
			//table.reload();
	});
	
	/**
	 * 修改，查看页面的联动；新建页面选择产品代码时也联动
	 */
	function finprodIdChangeAction(){
		//修改和查看时，页面html是有值的。//新建能走到这一步，是在产品代码中选择了值，也是有值的。
    		var finprodId=$("#FinProductQuotation002_SELECT_finprodId").val();
    		$("#FinProductQuotation002_finprodAbbr").val($('#FinProductQuotation002_SELECT_finprodId').select2('data')[0].text.split(":")[1]).trigger("change");
    		if(finprodId!=null && finprodId!='' ){//下面的方法是查询产品的分层总信息（是否是分层产品），不是具体的细节
    			Joyin.ajax('/invest/product/messageadjust/finproductquotation/FinProductQuotation002/queryProdInfoByFinprodId', 
    					{finprodId:finprodId}, 
    					function(result) {
						/*if(result.layType != null && result.layType != ''){
							var arr = (result.layType).split(',');
							tableShowAndHide(arr);
							if(pageModel == PAGE_MODEL_NEW){//如果是新建，就把分层信息都删除了，为什么？
			    				$.each(table.getAllRows(),function(index,obj){
									table.deleteRow(index);
								});
							}
						}*/
    					//根据产品类型和是否分层显示输入框
						showDiv(result);
	    				/*$('#FinProductQuotation002_saleDepartment').val(result.saleDepartment).trigger("change");*/
	    				//$("#FinProductQuotation002_finprodAbbr").attr("value",result.finprodAbbr);
	    				$('#FinProductQuotation002_vdate').val(result.vdate);
	    				$('#FinProductQuotation002_finprodMarketId').val(result.finprodMarketId);
	    				$('#FinProductQuotation002_cashId').val(result.cashId);
	    				$('#FinProductQuotation002_isLay').val(result.isLay);
                });
    		}else{
    			/*$('#FinProductQuotation002_saleDepartment').val('').trigger("change");*/
				$("#FinProductQuotation002_finprodAbbr").attr("value",'');
				$("#FinProductQuotation002_div_yield").attr("style","display:none");
				$('#FinProductQuotation002_div_layering').attr("hidden",true);
    			//changeInput("display:none","display:none","display:none","display:none","display:none");
    		}
	}
	
	//表格列隐藏和显示
	function tableShowAndHide(arr){
		tableHide();
		var param = [];
		for(var i in arr){
			if(arr[i] == '01'){
				param.push({field:'amtMin', visible: true});
				param.push({field:'amtMax',visible: true});
			}
			if(arr[i] == '02'){
				param.push({field:'termMin', visible: true});
				param.push({field:'termMax',visible: true});
			}
			if(arr[i] == '03'){
				param.push({field:'customLevel', visible: true});
			}
		}
		table.showColumns(param);
	};
	//
	function tableHide(){
		var param1 = [];
		param1.push({field:'amtMin', visible: false});
		param1.push({field:'amtMax',visible: false});
		param1.push({field:'termMin', visible: false});
		param1.push({field:'termMax',visible: false});
		param1.push({field:'customLevel', visible: false});
		table.showColumns(param1);
	}
    /**
     * 画面加载完成
     */
    $(function() {
    	// 新建可编辑、到经办可编辑，修改不可 编辑、到代办也不可编辑
		if ($('#wfl_operType').val()){
			if ($('#wfl_operType').val() != 'NEW'){
				$('#FinProductQuotation002_SELECT_finprodId').prop('disabled', true);
			}
		}else{
			if (pageModel != PAGE_MODEL_NEW ){
				$('#FinProductQuotation002_SELECT_finprodId').prop('disabled', true);
				//修改，生效日应该置灰不可修改。
				$('#FinProductQuotation002_effDate').prop('disabled', true);
			}
		}
		
    	//根据产品代码动态生成div
    	$('#FinProductQuotation002_SELECT_finprodId').change(function(){
    		finprodIdChangeAction();
    	});
    	/**
         *绑定删除事件
        */
        /*table.bind('click', '.delt', function(data, index) {
            table.removeRow(data);
        });*/
        /**
         * 保存事件
         */ 
        $('#FinProductQuotation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交事件
         */
        $('#FinProductQuotation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    	
    });
}());