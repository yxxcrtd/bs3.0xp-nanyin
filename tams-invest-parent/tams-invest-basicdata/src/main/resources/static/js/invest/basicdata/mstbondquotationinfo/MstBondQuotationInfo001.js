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
        Joyin.dialog(JoyinUtil.getLabel('MstBondQuotationInfo002'), basePath +'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/init', '75%', '75%');
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
            Joyin.dialog(JoyinUtil.getLabel("MstBondQuotationInfo002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        
        cols:[
             
            {field:'secId', title: 'MstBondQuotationInfo001.secId', width:150}
            ,{field:'finprodAbbr', title: 'MstBondQuotationInfo001.finprodAbbr',width:150},
            {field:'valueSourceStr', title: 'MstBondQuotationInfo002.valueSource', sortField:'valueSource', width:150}
            ,{field:'valueDate', title: 'MstBondQuotationInfo002.valueDate', width:150,type:'date'}
            ,{field:'lastPeriod', title: 'MstBondQuotationInfo002.lastPeriod', width:150}
            ,{field:'price', title: 'MstBondQuotationInfo002.price', width:150}
            ,{field:'netprice', title: 'MstBondQuotationInfo002.netprice', width:150}
            ,{field:'mDuration', title: 'MstBondQuotationInfo002.mDuration', width:150}
            ,{field:'mConvexity', title: 'MstBondQuotationInfo002.mConvexity', width:150}
            ,{field:'bpvalue', title: 'MstBondQuotationInfo002.bpvalue', width:150}
            ,{field:'sduration', title: 'MstBondQuotationInfo002.sduration', width:150}
            ,{field:'scnvxty', title: 'MstBondQuotationInfo002.scnvxty', width:150}
            ,{field:'interestDuration', title: 'MstBondQuotationInfo002.interestDuration', width:150}
            ,{field:'interestCnvxty', title: 'MstBondQuotationInfo002.interestCnvxty', width:150}
            ,{field:'bpyield', title: 'MstBondQuotationInfo002.bpyield', width:150}
            ,{field:'importDay', title: 'MstBondQuotationInfo002.importDay', width:150,type:'date'}
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
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstBondQuotationInfo002.edit"),
                    basePath + "invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
        });
        
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/delete', object, "MstBondQuotationInfo001_btn_delete", "MstBondQuotationInfo002",function(){
                    table.reload();
                });
            });
        });
    });

    $(function () {
        // 绑定按钮事件
        $('#MstBondQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#MstBondQuotationInfo001_btn_delete').show();
        });
        $('#MstBondQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstBondQuotationInfo001_btn_add').click(function() {
            showDialog();
        });
        $('#MstBondQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/delete', object, "MstBondQuotationInfo001_btn_delete", "MstBondQuotationInfo002");
                });
            });
        });
      //hsgexinghua   add by zhangxing1 20200220 start
        // 导入按钮点击事件
        $('#MstBondQuotationInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });
        //hsgexinghua  add by zhangxing1 20200220 end
    });
}());