$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            releaseDate: $('#releaseDate').val(),
        	intRateId: $('#intRateId').val(),
        };
    };

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
            //在新建界面进行显示单行的详细信息
            data.effDate = new Date(data.effDate).format();
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ComInterestRateQuotationInfo001"),
                    basePath + "common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002/init",
                    "95%", "80%", data);
        },
       
        cols:[
             {field:'relaeseDate',width:200,  title: 'ComInterestRateQuotationInfo001.relaeseDate',type:'date'}
            ,{field:'intRateName',title: 'ComInterestRateQuotationInfo001.intRateName', sortField:'intRateId'}
            ,{field:'ccyName',width:200,  title: 'ComInterestRateQuotationInfo001.ccy', sortField:'ccy'}
            ,{field:'intRate',width:200,  title: 'ComInterestRateQuotationInfo001.intRate',type:'number'}
            ,{field:'effDate',width:200, title: 'ComInterestRateQuotationInfo001.effDate',type:'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	data.effDate = new Date(data.effDate).format();
        data.pageModel = PAGE_MODEL_EDIT;
         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002/check', data, function(result) {
             Joyin.confirm("请确认是否修改?", function(){
                 Joyin.dialog(JoyinUtil.getLabel("ComInterestRateQuotationInfo002.edit"),
                    basePath + "common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002/init",
                    "98%", "95%", data,function(){table.reload();});
             });
         });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
         Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001/delete', object, "ComInterestRateQuotationInfo001_btn_delete", "ComInterestRateQuotationInfo002",function() {
                    table.reload();
                });
            });
         }); 
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        // var paramObj = getSearchParams();//请求参数json格式
        // Joyin.exportExcel('export', paramObj);//导出（共通）

        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ComInterestRateQuotationInfo001_btn_search').click(function() {
            table.load();
            $('#ComInterestRateQuotationInfo001_btn_delete').show();
        });
        $('#ComInterestRateQuotationInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ComInterestRateQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ComInterestRateQuotationInfo002"),
                        basePath + "common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002/init",
                        "60%", "85%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ComInterestRateQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001/delete', object, "ComInterestRateQuotationInfo001_btn_delete", "ComInterestRateQuotationInfo002",function() {
                        table.reload();
                    });
                });
        });

        // add by zwq 20210222  start
        $('#ComInterestRateQuotationInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
                Joyin.uploadFile({pageId:'ValInterestRateQuotationInfo001'},function(fileName) {
                    Joyin.ajax(basePath +"common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001/importFile", {
                        fileName : fileName
                    },function(result){
                        if(!result || !($.isArray(result) && result.length > 0)) {
                            layui.layer.closeAll();
                            table.reload();
                        }
                    });
                });
            });
        });
        // add by zwq 20210222  end
    });
}());