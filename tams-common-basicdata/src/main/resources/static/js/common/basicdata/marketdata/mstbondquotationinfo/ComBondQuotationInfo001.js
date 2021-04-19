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
        Joyin.dialog(JoyinUtil.getLabel('ComBondQuotationInfo002'), basePath +'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002/init', '75%', '75%');
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
            Joyin.dialog(JoyinUtil.getLabel("ComBondQuotationInfo002.reference"),
                    JoyinUtil.getRealPath("common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        
        cols:[
             
            {field:'secId', title: 'ComBondQuotationInfo001.secId', width:150}
            ,{field:'finprodAbbr', title: 'ComBondQuotationInfo001.finprodAbbr',width:150},
            {field:'valueSourceStr', title: 'ComBondQuotationInfo002.valueSource', sortField:'valueSource', width:150}
            ,{field:'valueDate', title: 'ComBondQuotationInfo002.valueDate', width:150,type:'date'}
            ,{field:'lastPeriod', title: 'ComBondQuotationInfo002.lastPeriod', width:150}
            ,{field:'price', title: 'ComBondQuotationInfo002.price', width:150}
            ,{field:'netprice', title: 'ComBondQuotationInfo002.netprice', width:150}
            ,{field:'mDuration', title: 'ComBondQuotationInfo002.mDuration', width:150}
            ,{field:'mConvexity', title: 'ComBondQuotationInfo002.mConvexity', width:150}
            ,{field:'bpvalue', title: 'ComBondQuotationInfo002.bpvalue', width:150}
            ,{field:'sduration', title: 'ComBondQuotationInfo002.sduration', width:150}
            ,{field:'scnvxty', title: 'ComBondQuotationInfo002.scnvxty', width:150}
            ,{field:'interestDuration', title: 'ComBondQuotationInfo002.interestDuration', width:150}
            ,{field:'interestCnvxty', title: 'ComBondQuotationInfo002.interestCnvxty', width:150}
            ,{field:'bpyield', title: 'ComBondQuotationInfo002.bpyield', width:150}
            ,{field:'importDay', title: 'ComBondQuotationInfo002.importDay', width:150,type:'date'}
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
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("ComBondQuotationInfo002.edit"),
                    basePath + "common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
        });
        
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/delete', object, "ComBondQuotationInfo001_btn_delete", "ComBondQuotationInfo002",function(){
                    table.reload();
                });
            });
        });
    });

    $(function () {
        // 绑定按钮事件
        $('#ComBondQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ComBondQuotationInfo001_btn_delete').show();
        });
        $('#ComBondQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ComBondQuotationInfo001_btn_add').click(function() {
            showDialog();
        });
        $('#ComBondQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/delete', object, "ComBondQuotationInfo001_btn_delete", "ComBondQuotationInfo002");
                });
            });
        });
      //hsgexinghua   add by zhangxing1 20200220 start
        // 导入按钮点击事件
        $('#ComBondQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
        //hsgexinghua  add by zhangxing1 20200220 end
    });
}());