/**
 * huwenbin
 * 信息调整管理_付费计划调整
 */
(function() {
	//未支付最小计提开始日
	var VDATE;
	var flag = true;
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var finprodId;
    	if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
    		finprodId = $("#finprodMarketId").select2('data')[0].object;
    	}else{
    		finprodId = $("#finprodMarketId").val();
    	}
        if($("#prodType").val() != null && $("#prodType").val() != ''){
            prodType = $("#prodType").select2('data')[0].id;
        }else{
            prodType = $("#prodType").val();
        }
        return {
            finprodId: finprodId,
            prodType: prodType,
            cashId: $('#cashId').val(),
            workflowId: $('#workflowId').val(),
            flag:flag
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
    	page:false,
    	ajax: {
            url: JoyinUtil.getRealPath("invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/findPage"),
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        addbtn:true,
        addToLast:true,
        searchOnInit: true,
        loadonce:true,
       /* beforeEditCell: function(value, data, field){
        	return data.updateFlg == "02"? false:true;
    	},*/
        beforeEditCell: function(preVal, field, rowData){
        	if(rowData.payStatus == '未支付' && preVal == VDATE){
        		return false;
        	}
        	if(rowData.payStatus == '已支付'){
        		return false;
        	}
        	else{
        		return true;
        	}
    	},
        cols: [
              {field: 'prodType',title: 'FinProductRateQuotation001.prodType',sortable:false,sortField:'prodType',visible:false}
            , {field: 'vdate', width:150, title: 'FinPayPlanAdjustment001.feePayStartDate',sortable:false, edit:true, type:'date'}
            , {field: 'mdate', width:150, title: 'FinPayPlanAdjustment001.feePayEndDate', sortable:false,edit:true, type:'date'}
            , {field: 'payDate', width:150,title: 'FinPayPlanAdjustment001.payDate',sortable:false, edit:true,  type:'date'}
            , {field: 'payStatus',width:150, title: 'FinPayPlanAdjustment001.status',sortable:false
            	,render:function(data, type, row, meta){
            		/*
            		if(row.payStatus != null && row.payStatus != ''){
            			
            		}else{
            			row.payStatus = '未支付';
            			return '未支付';
            		}
            		*/
        		}
            }
            /*, {field: 'effDateStr', title: 'FinPayPlanAdjustment001.effDate', type:'date'}
            , {field: 'finprodId', visible:false}
            , {field: 'effDate', visible:false}
            , {field: 'cashId', visible:false}*/
            , {field: 'updateFlg', visible:false}
            , {field:'operation',width:150, title: 'SysCommon.operation',   type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    	,done: function(table, allData){
         	flag = false;
         	$($('#dataTable').next().find('.btn,.delt')[0]).attr('style',"display:none");
        }
    });
    
    $('#cashId').change(function(){
    	if($('#cashId').val() != null && $('#cashId').val() != ''){
    		$.each(table.getAllRows(),function(index,obj){
    			table.removeRow(obj);
    		})
    	}
    })
    
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
    	Joyin.confirm('确定删除？', function(){
    		table.removeRow(data);
    	});
    });
    
    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };
    
    //保存和提交check
    var tablecheck = function(){
    	var tableData = table.getAllRows();
    	$.each(tableData,function(index,obj){
            var vdateUnadjust = Date.ofDate(obj.vdate).format('yyyy-MM-dd');
            var mdateUnadjust = Date.ofDate(obj.mdate).format('yyyy-MM-dd');
            /*var payDate = new Date(obj.payDate).format('yyyy-MM-dd');
            if((vdateUnadjust==''||typeof vdateUnadjust == 'undefined')
                    ||(mdateUnadjust==''||typeof mdateUnadjust == 'undefined')){
                msg = JoyinUtil.getMessage('COMMON_E_0057','计息开始日、计息截止日');
            }*/
            if(index != 0){
            	if(Date.ofDate(obj.vdate).format('yyyy-MM-dd') != Date.ofDate(tableData[index-1].mdate).format('yyyy-MM-dd')){
            		
            	}
            }
    	})
    }
    
    /**
     * 获取未支付最小计提开始日
     */
    var init = function(){
    	if($('#finprodMarketId').val() == null || $('#finprodMarketId').val() == ''){
    		Joyin.error(JoyinUtil.getMessage("请输入产品代码！"));
    		return;
    	}
    	if($("#finprodMarketId").select2('data')[0].col ==='Y' && ($('#prodType').val() == null || $('#prodType').val() == '')){
    	    Joyin.error(JoyinUtil.getMessage("请输入产品款型！"));
            return;
    	}
    	if($('#cashId').val() == null || $('#cashId').val() == ''){
    		Joyin.error(JoyinUtil.getMessage("请输入费用名称！"));
    		return;
    	}
    	table.load();
    	Joyin.ajax(basePath + "invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/getVDate",getSearchParams(),
				function(result){
    		VDATE = result;
    		/*if(result){
    			return result;
    		}*/
    	})
    }
    
    var init_start = function(){
        $("#prodTypeDiv").hide();
    	var workflowId = $("#workflowId").val();
    	if(workflowId != null && workflowId != ''){
    		$('#FinPayPlanAdjustment001_btn_filter').attr("style","display:none;");
    		$('#FinPayPlanAdjustment001_btn_export').attr("style","display:none;");
    	}
    	if(VDATE == null || VDATE == ''){
    		Joyin.ajax(basePath + "invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/getVDate",getSearchParams(),
    				function(result){
        		VDATE = result;
        	})
    	}
    }
    
    $('#finprodMarketId').change(function(){
		$('#cashId').val('').trigger('change');
		$('#prodType').val('').trigger('change');
		if($("#finprodMarketId").select2('data')[0].col ==='Y'){
		    $("#prodTypeDiv").show();
		}else {
		    $("#prodTypeDiv").hide();
		}
	});
    $('#prodType').change(function(){
        $('#cashId').val('').trigger('change');
    });
    
    var ruleAdjustment = function(){
    	Joyin.dialog(JoyinUtil.getLabel("FinPayPlanAdjustment002"), basePath
                + "invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002/init",
                "80%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init_start();
    	
        // 绑定按钮事件
    	//查询
    	$('#FinPayPlanAdjustment001_btn_search').click(function(){
    		init();
    	});
    	//保存
        $('#FinPayPlanAdjustment001_btn_save').click(function(){
        	//tablecheck();
        	saveOrSubmit('save');
        });
        //提交
        $('#FinPayPlanAdjustment001_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        //导出
        $('#FinPayPlanAdjustment001_btn_export').click(function() {
            exportExcel();
        });
        //付费规则调整 yzg 20191126 start 
        $('#FinPayPlanAdjustment001_btn_rule').click(function() {
        	ruleAdjustment();
        });
        //付费规则调整 yzg 20191126 end
        //批量删除
        /*$('#FinPayPlanAdjustment001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }	
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinPayPlanAdjustment001/check', object, function(result) {														
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductratequotation/FinPayPlanAdjustment001/delete', object, "FinProductRateQuotation002_btn_delete", "FinPayPlanAdjustment001");
                });
            });
        });*/
    });
    function saveOrSubmit(operateType) {
    	//var selArr = table.getAllRows();
    	if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }	
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        for(var i=0; i<selArr.length; i++){
        	if(selArr[i].vdate == null || selArr[i].vdate == ''){
        		Joyin.error(JoyinUtil.getMessage("付费计划第"+(i+1)+"行：请输入计提开始日"));
                return;
        	}
        	if(selArr[i].mdate == null || selArr[i].mdate == ''){
        		Joyin.error(JoyinUtil.getMessage("付费计划第"+(i+1)+"行：请输入计提结束日"));
                return;
        	}
        	if(selArr[i].payDate == null || selArr[i].payDate == ''){
        		Joyin.error(JoyinUtil.getMessage("付费计划第"+(i+1)+"行：请输入支付日"));
                return;
        	}
        }
        var selArrTemp;
    	for(var m=1; m<selArr.length-1; m++){
    		for(var n=m+1; n<selArr.length; n++){
    			if(selArr[m].payStatus == '未支付' || (typeof selArr[m].payStatus) == 'undefined'){
    				if(Date.ofDate(selArr[m].vdate).format()>Date.ofDate(selArr[n].vdate).format()){
        				selArrTemp = selArr[m];
        				selArr[m] = selArr[n];
        				selArr[n] = selArrTemp;
        			}
    			}
    		}
    	}
    	var object = {selectedItem1: selArr, operateType:operateType};
    	object.vdateCheck = VDATE;
    	object.pageModel = PAGE_MODEL_NEW;
    	object.finprodId = $("#finprodMarketId").select2('data')[0].object;
    	object.cashId = $("#cashId").val();
    	object.feeName = $('#cashId').select2('data')[0].text;
    	object.finprodMarketId = $("#finprodMarketId").val();
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/save', object, "FinPayPlanAdjustment001_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001/save', object, "FinPayPlanAdjustment001_btn_submit");
                }
            });
        });
    };
}());