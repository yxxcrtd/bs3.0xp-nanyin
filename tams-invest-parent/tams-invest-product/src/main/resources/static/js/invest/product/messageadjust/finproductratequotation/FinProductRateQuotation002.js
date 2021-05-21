/**
 * 产品费率报价管理新建
 * huwenbin
 */
$(function() {
	var flag = true
    function saveOrSubmit(operateType) {
    	
		var flag1 = true;
    	//table.checkRow(0);
    	var tableCheckData = table.getAllCheckedRows();
        var object = $.serializeToObject('#submitForm');
        if($('#effDateOld').val() != null && $('#effDateOld').val() != ''){
        	object.effDateOld = Date.ofDate($('#effDateOld').val()).format('yyyy-MM-dd');
        }
        object.pageModel = pageModel;
        object.remark = $("#remark").val();
        object.finprodId = finprodId;
        object.operateType = operateType;
		if ((!tableCheckData || tableCheckData.length == 0) && operateType === 'submit') {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        else{
			if ((!tableCheckData || tableCheckData.length == 0) && operateType === 'save') {
				object.selectedItem = table.getAllRows();
			}else {
				object.selectedItem = tableCheckData;
			}
        	$.each(object.selectedItem,function(index,obj){
        		obj.finprodId = object.finprodId;
        		obj.relaeseDate = object.relaeseDate;
        		obj.effDate = object.effDate;
        		obj.remark = object.remark;
        	})
        }
        for(var i =0; i<tableCheckData.length; i++){
    		if(tableCheckData[i].yield){
    			if(tableCheckData[i].yield.length > 30){
    				Joyin.error("费率的长度不能超过30！");
    				return;
    			}
    		}else{
    			Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
	            return;
    		}
		}
        var tableData = table.getAllRows();
        for(var i=0; i<tableData.length; i++){
        	if(tableData[i].yield){
        		if(!tableData[i].LAY_CHECKED && tableData[i].yield != 0){
            		flag1 = false;
            		Joyin.confirm(JoyinUtil.getMessage("FIN_PROD_I_0013"),function(){
            			Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/check', object, function(result) {
            	            Joyin.confirm(result.data, function() {
            	                if(operateType === 'save') {
            	                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_save");
            	                }
            	                else {
            	                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_submit");
            	                }
            	            });
            	        });
            		},function(){
            			return;
            		});
            		break;
            	}
        	}
        }
        if(flag1){
        	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_save");
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_submit");
	                }
	            });
	        });
        }
        /*var flag1 = true;
        if(flag2){
        	for(var i=0; i<tableCheckData.length; i++){
        		if(tableCheckData[i].yield != 0){
        			flag1 = false;
        			Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/check', object, function(result) {
        	            Joyin.confirm(result.data, function() {
        	                if(operateType === 'save') {
        	                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_save");
        	                }
        	                else {
        	                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/save', object, "FinProductRateQuotation002_btn_submit");
        	                }
        	            });
        	        });
        			break;
        		}
        	}
        }*/
        
        /*if(flag1){
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }*/
    };
    
    //var editable = pageModel != PAGE_MODEL_REFERENCE;
    /**
     * 页面加载初始化
     */
	var options = {
			page: true,
			ajax: {
            url: JoyinUtil.getRealPath('invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/findPage'),
            type: 'POST',
            data: function() {
                return {finprodId: finprodId,
                		workflowId: $('#workflowId').val(),
                		pageModel:pageModel,
                		cashId:$('#cashId').val(),
                		effDate:$('#effDate').val(),
                		flag:flag
                	};
            }
			},
		/*data:[],*/
        checkbox: true,
        done:function(){
        	flag = false;
        },
        //addbtn: true,
        editCell: function(value, data, field, cell, column){
        	table.checkRow(data.LAY_TABLE_INDEX);
//            table.checkRow(1);
    	},

    	
        searchOnInit: true,
        cols:[
            {field:'feeName', title: 'FinProductRateQuotation002.feeName', sortable:false}
            ,{field:'feeTypeStr', title: 'FinProductRateQuotation002.feeType', sortable:false}
            ,{field:'prodStyle', title: 'FinProductRateQuotation001.prodStyle', sortable:false}
            //update by gcl 2019/3/25 start
            ,{field:'yield', title: 'FinProductRateQuotation001.yield', edit:true, type:'number', sortable:false, digit:6}
            //update by gcl 2019/3/25 end
            ,{field:'cashIdStr', visible:false}
            ,{field:'feeType', visible:false}
            //,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    };
	//产品代码画面联动
	$("#finprodMarketId").change(function(){
		$("#finprodAbbr").val($('#finprodMarketId').select2('data')[0].text);
		$("#prodStyle").val('').trigger('change');
		//分档
		if($('#finprodMarketId').select2('data')[0].col == 'Y'){
		    $("#prodStyleDiv").show();
		    if(table.table){
	            table.clear();
	        }
		} else {
		    $("#prodStyleDiv").hide();
		    finprodId = $('#finprodMarketId').select2('data')[0].object;
		    table.reload();
		}
	});
	$("#prodStyle").change(function(){
	    if($('#prodStyle').val()!=null && $('#prodStyle').val()!= ''){
	        finprodId = $('#prodStyle').select2('data')[0].object;
	        table.reload();
	    } else if(table.table){
            table.clear();
        }
	});
	//
	var workflowId = $("#workflowId").val();
	if(workflowId){
	    $("#finprodAbbr").val($('#finprodMarketId').select2('data')[0].text);
	}
    
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    

    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#finprodMarketId').attr("disabled",true);
    			$('#effDate').attr("disabled",true);
    		}
    		
    	}
    	if($('#finprodMarketId').select2('data')[0].col != 'Y'){
    	    $("#prodStyleDiv").hide();
    	}
    }
    
    $(function(){
    	/**
         * 画面加载完成
         */
    	init();
        // 绑定按钮事件
        $('#FinProductRateQuotation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductRateQuotation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());