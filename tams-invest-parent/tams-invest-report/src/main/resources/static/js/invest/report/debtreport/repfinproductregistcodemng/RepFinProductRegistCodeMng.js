/**
 * 
 * huwenbin
 * 产品登记编码管理
 */
(function() {
	//付息日调整未付息最小去见开始日
	var VDATE;
	
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var finprodId;
    	if($("#finprodMarketId").val() == null || $("#finprodMarketId").val() == ''){
    		finprodId = $("#finprodMarketId").val();
    	}
    	else{
    		finprodId = $("#finprodMarketId").val().join(',');
    	}
    	var pordRegistCode;
    	if($("#pordRegistCode").val() == null || $("#pordRegistCode").val() == ''){
    		pordRegistCode = $("#pordRegistCode").val();
    	}
    	else{
    		pordRegistCode = $("#pordRegistCode").val().join(',');
    	}
        return {
        	pordRegistCode:pordRegistCode,
        	finprodId:finprodId,
        	startVdate:$("#vdate").startDate(),
        	endVdate:$("#vdate").endDate(),
        	startMdate:$("#mdate").startDate(),
        	endMdate:$("#mdate").endDate()
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
        //addbtn:true,
        //addToLast:true,
        //searchOnInit: true,
        /*beforeEditCell: function(value, data, field){
        	return data.updateFlg == "02"? false:true;
    	},*/
        /*beforeEditCell: function(preVal, field, rowData){
        	if(rowData.payStatus == '未付息' && preVal == VDATE){
        		return false;
        	}
        	if(rowData.payStatus == '已付息'){
        		return false;
        	}
        	else{
        		return true;
        	}
    	},*/
        checkbox:false,
        cols: [
			{field: 'prodRegistCode', title: 'RepFinProductRegistCodeMng.pordRegistCode',sortField:'PROD_REGIST_CODE'}
			, {field: 'finprodMarketId', title: 'RepFinProductRegistCodeMng.finprodMarketId',sortField:'FINPROD_MARKET_ID'}
			, {field: 'vdate', title: 'RepFinProductRegistCodeMng.vdate', type:'date'}
            , {field: 'mdate', title: 'RepFinProductRegistCodeMng.mdate', type:'date'}
            , {field: 'repInvestorTypeStr', title: 'RepFinProductRegistCodeMng.repInvestorType',sortField:'REP_INVESTOR_TYPE'}
            , {field:'operation', title: 'SysCommon.operation', visible:false,  type:'action', buttons: ['delt']}//编辑和删除事件
        ]
        ,done: function(table, allData){
        	
        }
    });
    
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    
    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
    	//查询
    	$('#RepFinProductRegistCodeMng_btn_search').click(function(){
    		//init();
    		table.load();
    	});
    	//保存
        $('#RepFinProductRegistCodeMng_btn_save').click(function(){
        	//tablecheck();
        	saveOrSubmit('save');
        });
        //提交
        $('#RepFinProductRegistCodeMng_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        //导出
        $('#RepFinProductRegistCodeMng_btn_export').click(function() {
            exportExcel();
        });
        //导入
        $('#RepFinProductRegistCodeMng_btn_import').click(function() {
        	// Joyin.confirm(JoyinUtil.getLabel("RepFinProductRegistCodeMng.import"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + "invest/report/debtreport/repfinproductregistcodemng/RepFinProductRegistCodeMng/importFile", {
                        fileName : fileName
                    },function(result){
                        if(!result || !($.isArray(result) && result.length > 0)) {
                            layui.layer.closeAll()
                            table.reload();
                        }
                    });
                });
            // });
        });
    });
}());