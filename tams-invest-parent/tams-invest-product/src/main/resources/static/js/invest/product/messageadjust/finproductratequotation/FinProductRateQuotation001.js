/**
 * huwenbin
 * 产品费率报价管理
 */
(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var finprodId = '';
    	if($('#finprodId').val() == null || $('#finprodId').val() == ''){
    		finprodId = $('#finprodId').val();
    	}
    	else{
    		//finprodId = $('#finprodId').select2('data')[0].object.join(',');
    		$.each($('#finprodId').select2('data'),function(index,obj){
    			finprodId = finprodId+"," + obj.object;
    		})
    	}
    	if($('#prodStyle').val() != null && $('#prodStyle').val() != ''){
    	    finprodId = $('#prodStyle').select2('data')[0].object;
    	}
        return {
            finprodId: finprodId,
            effDateStart: $('#effDate').startDate(),
            effDateEnd: $('#effDate').endDate()
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinProductRateQuotation002.detial"),
                    JoyinUtil.getRealPath("invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/init"),
                    "80%", "90%", data,
                    function(){table.reload();});
        },
        cols: [
            {field: 'finprodMarketId', width: 200, title: 'FinProductRateQuotation001.finprodId',sortField:'FINPROD_MARKET_ID'}
            , {field: 'finprodAbbr', width: 200, title: 'FinProductRateQuotation002.finprodAbbr',sortField:'FINPROD_ABBR'}
            , {field: 'prodStyle', width: 200, title: 'FinProductRateQuotation001.prodStyle',sortField:'PROD_STYLE'}
            , {field: 'feeName', width: 200, title: 'FinProductRateQuotation002.feeName',sortField:'FEE_NAME'}
            , {field: 'feeTypeStr', width: 200, title: 'FinProductRateQuotation002.feeType',sortField:'FEE_TYPE'}
            , {field: 'yield', width: 200, title: 'FinProductRateQuotation001.yield', type:'number',sortField:'YIELD',digit:6}
            , {field: 'effDateStr', width: 200, title: 'FinProductRateQuotation001.effDate', type:'date',sortField:'EFF_DATE'}
            , {field: 'finprodId', width: 200, visible:false}
            , {field: 'effDate', width: 200, visible:false}
            , {field: 'cashId', width: 200, visible:false}
            , {field: 'isInitial', width: 200, visible:false}
            , {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 明细查看
     */
    table.bind('click', '.edit', function(data, index) {
    	data.operateType = 'edit';
    	data.pageModel = PAGE_MODEL_EDIT;
    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/check', data, function(result) {
	        Joyin.dialog(JoyinUtil.getLabel("FinProductRateQuotation002.edit"),
	                basePath + "invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/init",//打开新界面
	                "80%", "90%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
	        						finprodId:data.finprodId,
	        						cashId:data.cashId,
	        						effDateStr:data.effDateStr
	        						},function(){
	                                    table.reload();//回调函数，表格刷新
	                                });
    	})
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	//发起流程
    	var object = {selectedItem: [data], operateType:"delete", finprodId : data.finprodId,cashId:data.cashId, effDate : data.effDate};
    	data.operateType = 'delete';
    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation001/check', object, function(result) {
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	        	WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation001/delete', object, "FinProductRateQuotation001_btn_delete", "FinProductRateQuotation002",function(){table.reload()});
	        });
    	})
    });
    
    
    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
    	Joyin.dialog(JoyinUtil.getLabel("FinProductRateQuotation002"), basePath
                + "invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002/init",
                "80%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
    	//查询
    	$('#FinProductRateQuotation001_btn_search').click(function(){
    		table.load();
    	});
    	//
        $('#FinProductRateQuotation001_btn_add').click(function(){
        	showDialog();
        });
        //导出
        $('#FinProductRateQuotation001_btn_export').click(function() {
            exportExcel();
        });
        //批量删除
        $('#FinProductRateQuotation001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation001/check', object, function(result) {														
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finproductratequotation/FinProductRateQuotation001/delete', object, "FinProductRateQuotation001_btn_delete", "FinProductRateQuotation002",function(){table.reload()});
                });
            });
        });
    });
}());