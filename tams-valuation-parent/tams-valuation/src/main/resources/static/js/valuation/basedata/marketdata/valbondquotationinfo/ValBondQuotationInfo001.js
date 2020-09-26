/**
 * 债券行情信息查询
 */
(function(){
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
        	secId: $('#secId').val(),
        	finprodAbbr: $('#finprodAbbr').val(),
        	valueDate : $('#valueDate').val(),
        	valueSource:$('#valueSource').val()
        }
    };
    //新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('ValBondQuotationInfo002'), basePath +'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo002/init', '75%', '75%');
    }
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
        	data.valueDate = new Date(data.valueDate).format();
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ValBondQuotationInfo002.reference"),
                    JoyinUtil.getRealPath("valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        
        cols:[
             
            {field:'secId', title: 'ValBondQuotationInfo001.secId', width:150}
            ,{field:'finprodAbbr', title: 'ValBondQuotationInfo001.finprodAbbr',width:150},
            {field:'valueSourceStr', title: 'ValBondQuotationInfo002.valueSource', sortField:'valueSource', width:150}
            ,{field:'valueDate', title: 'ValBondQuotationInfo002.valueDate', width:150,type:'date'}
            ,{field:'lastPeriod', title: 'ValBondQuotationInfo002.lastPeriod', width:150}
            ,{field:'price', title: 'ValBondQuotationInfo002.price', width:150}
            ,{field:'netprice', title: 'ValBondQuotationInfo002.netprice', width:150}
            ,{field:'mDuration', title: 'ValBondQuotationInfo002.mDuration', width:150}
            ,{field:'mConvexity', title: 'ValBondQuotationInfo002.mConvexity', width:150}
            ,{field:'bpvalue', title: 'ValBondQuotationInfo002.bpvalue', width:150}
            ,{field:'sduration', title: 'ValBondQuotationInfo002.sduration', width:150}
            ,{field:'scnvxty', title: 'ValBondQuotationInfo002.scnvxty', width:150}
            ,{field:'interestDuration', title: 'ValBondQuotationInfo002.interestDuration', width:150}
            ,{field:'interestCnvxty', title: 'ValBondQuotationInfo002.interestCnvxty', width:150}
            ,{field:'bpyield', title: 'ValBondQuotationInfo002.bpyield', width:150}
            ,{field:'importDay', title: 'ValBondQuotationInfo002.importDay', width:150,type:'date'}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:150}
        ],
    });
    
    var exportExcel = function(){       
        Joyin.exportExcel('export',getSearchParams()) 
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("ValBondQuotationInfo002.edit"),
                    basePath + "valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
        });
        
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo001/delete', object, "ValBondQuotationInfo001_btn_delete", "ValBondQuotationInfo002",function(){
                    table.reload();
                });
            });
        });
    });

    $(function () {
        // 绑定按钮事件
        $('#ValBondQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ValBondQuotationInfo001_btn_delete').show();
        });
        $('#ValBondQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ValBondQuotationInfo001_btn_add').click(function() {
            showDialog();
        });
        $('#ValBondQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/valbondquotationinfo/ValBondQuotationInfo001/delete', object, "ValBondQuotationInfo001_btn_delete", "ValBondQuotationInfo002");
                });
            });
        });
    });
}());