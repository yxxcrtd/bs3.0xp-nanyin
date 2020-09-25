/**
 * 
 * huwenbin
 * 信息调整管理_付息日调整
 */
(function() {
	//付息日调整未付息最小去见开始日
	var VDATE;
	var flag = true;
	var table;
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var cashId;
    	if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
    		cashId = $("#finprodMarketId").select2('data')[0].col;
    	}
    	else{
    		cashId = $("#finprodMarketId").val();
    	}
        return {
            cashId: cashId,
            workflowId: $('#workflowId').val(),
            flag:flag
        }
    };

    /**
     * 定义数据表格
     */
    var option = {
        page:false,
//    	ajax: {
//            url: JoyinUtil.getRealPath("invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/findPage"),
//            type: 'POST',
//            data: function () {
//                return getSearchParams();
//            }
//        },
        addbtn:true,
        addToLast:true,
        searchOnInit: true,
        loadonce:true,
        /*beforeEditCell: function(value, data, field){
        	return data.updateFlg == "02"? false:true;
    	},*/
        beforeEditCell: function(preVal, field, rowData){
        	if(rowData.payStatus == '未付息' && preVal == VDATE){
        		return false;
        	}
        	if(rowData.payStatus == '已付息'){
        		return false;
        	}
        	else{
        		return true;
        	}
    	},
        cols: [
            {field: 'vdate', title: 'FinIntPayDateAdjustment001.vdate', edit:true,sortable:false, type:'date',render:function(data, type, row, meta){
        		if(workflowId){
        			return Date.ofDate(row.vdate).format('yyyy-MM-dd');
        		}}}
            , {field: 'mdate', title: 'FinIntPayDateAdjustment001.mdate', edit:true, sortable:false,type:'date',render:function(data, type, row, meta){
        		if(workflowId){
        			return Date.ofDate(row.mdate).format('yyyy-MM-dd');
        		}}}
            , {field: 'payDate', title: 'FinIntPayDateAdjustment001.payDate', edit:true,sortable:false,  type:'date',render:function(data, type, row, meta){
        		if(workflowId){
        			return Date.ofDate(row.payDate).format('yyyy-MM-dd');
        		}}}
            , {field: 'payStatus', title: 'FinIntPayDateAdjustment001.payStatus',sortable:false
            	,render:function(data, type, row, meta){
            		if(row.payStatus != null && row.payStatus != ''){
            			
            		}else{
            			/*
            			if(!$('#workflowId').val()){//add by yejiejie 2019-4-2 不在流程中走这一步
	            			row.payStatus = '未付息';
	            			return '未付息';
            			}
            			*/
            		}
        		}
            
            }
            /*, {field: 'effDateStr', title: 'FinIntPayDateAdjustment001.effDate', type:'date'}
            , {field: 'finprodId', visible:false}
            , {field: 'effDate', visible:false}
            , {field: 'cashId', visible:false}*/
            , {field: 'updateFlg', visible:false}
            , {field:'operation', title: 'SysCommon.operation',   type:'action', buttons: ['delt']}//编辑和删除事件
        ]
        ,done: function(table, allData){
        	$($('#dataTable').next().find('.btn,.delt')[0]).attr('style',"display:none");
         	flag = false;
        }
    };
    
    if(pageModel == '0'){
    	option.ajax = {
              url: JoyinUtil.getRealPath("invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/findPage"),
              type: 'POST',
              data: function () {
                  return getSearchParams();
              }
          }
    	table = $("#dataTable").joyintable(option);
    }
    
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
     * 
     * 未付息最小区间开始日
     */
    var init = function(){
    	if($('#finprodMarketId').val() == null || $('#finprodMarketId').val() == ''){
    		Joyin.error(JoyinUtil.getMessage("请输入产品代码！"));
    		return;
    	}
    	table.load();
//    	Joyin.ajax(basePath + "invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/findPage",getSearchParams(),
//				function(result){
//    		selectedItem1 = result;
//    	});
    	Joyin.ajax(basePath + "invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/getVDate",getSearchParams(),
				function(result){
    		VDATE = result;
    	})
    }
    
    var init_start = function(){
    	var workflowId = $("#workflowId").val();
    	if(workflowId != null && workflowId != ''){
    		$('#FinIntPayDateAdjustment001_btn_filter').attr("style","display:none;");
    		$('#FinIntPayDateAdjustment001_btn_export').attr("style","display:none;");
    	}
    	if(VDATE == null || VDATE == ''){
    		Joyin.ajax(basePath + "invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/getVDate",getSearchParams(),
    				function(result){
        		VDATE = result;
        	})
    	}
    	if (selectedItem1) {
    		option.data = selectedItem1;
    		table = $("#dataTable").joyintable(option);
    	}
        /**
         *绑定删除事件
        */
        table.bind('click', '.delt', function(data, index) {
            table.removeRow(data);
        });
    }
    
    $('#finprodMarketId').change(function(){
    	$.each(table.getAllRows(),function(index,obj){
			table.removeRow(obj);
		})
		table.load();
    	$('#FinIntPayDateAdjustment001_btn_submit').attr('style',"display:display");
        $('#FinIntPayDateAdjustment001_btn_submit_disabled').attr('style',"display:none");
        $('#FinIntPayDateAdjustment001_btn_save').attr('style',"display:display");
        $('#FinIntPayDateAdjustment001_btn_save_disabled').attr('style',"display:none");
    })
    
    
    /**
     * 画面加载完成
     */
    $(function() {
    	init_start();
        // 绑定按钮事件
    	//查询
    	$('#FinIntPayDateAdjustment001_btn_search').click(function(){
    		init();
    	});
    	//保存
        $('#FinIntPayDateAdjustment001_btn_save').click(function(){
        	//tablecheck();
        	saveOrSubmit('save');
        });
        //提交
        $('#FinIntPayDateAdjustment001_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        //导出
        $('#FinIntPayDateAdjustment001_btn_export').click(function() {
            exportExcel();
        });
        //批量删除
        /*$('#FinIntPayDateAdjustment001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }	
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinIntPayDateAdjustment001/check', object, function(result) {														
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductratequotation/FinIntPayDateAdjustment001/delete', object, "FinProductRateQuotation002_btn_delete", "FinIntPayDateAdjustment001");
                });
            });
        });*/
    });
    function saveOrSubmit(operateType) {
    	if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }	
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
    	for(var i =0; i<selArr.length; i++){
        	if(selArr[i].vdate == null || selArr[i].vdate == ''){
        		Joyin.error(JoyinUtil.getMessage("付息日调整第"+(i+1)+"行：请输入计息区间开始日"));
                return;
        	}
        	if(selArr[i].mdate == null || selArr[i].mdate == ''){
        		Joyin.error(JoyinUtil.getMessage("付息日调整第"+(i+1)+"行：请输入计息区间结束日"));
                return;
        	}
        	if(selArr[i].payDate == null || selArr[i].payDate == ''){
        		Joyin.error(JoyinUtil.getMessage("付息日调整第"+(i+1)+"行：请输入支付日"));
                return;
        	}
        }
    	var selArrTemp;
    	for(var m=1; m<selArr.length-1; m++){
    		for(var n=m+1; n<selArr.length; n++){
    			if(selArr[m].payStatus == '未付息'|| (typeof selArr[m].payStatus) == 'undefined'){
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
    	object.finprodId = $('#finprodMarketId').select2('data')[0].object;
    	object.finprodAbbr = $('#finprodMarketId').select2('data')[0].text; 
    	object.cashId = $("#finprodMarketId").select2('data')[0].col;
    	object.finprodMarketId = $("#finprodMarketId").val();
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/save', object, "FinIntPayDateAdjustment001_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finintpaydateadjustment/FinIntPayDateAdjustment001/save', object, "FinIntPayDateAdjustment001_btn_submit");
                }
            });
        });
    };
}());