/**
 * 信息披露模板管理查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var fileType = $('#fileType').val();
    	var fileNameSelected = $('#fileName').data('selected');
        var fileId = fileNameSelected ? fileNameSelected.code : null;
        if(fileId == null){
        	fileName = $('#fileName').val();
        }
        return {
        	fileType : fileType,
        	fileName : fileName,
        	fileId:fileId
        }
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinDisclosureModule002.reference"),
                    JoyinUtil.getRealPath("invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        
        cols:[
              {field:'pathName',visible: false}
             ,{field:'revealFileId',visible: false}
             ,{field:'fileTypeStr', sortField:'fileType', title: 'FinDisclosureModule001.fileType'}
             ,{field:'fileName', title: 'FinDisclosureModule001.fileName',
            	render : function(data, type, row, meta) {
                    return '<a href="' + JoyinUtil.getRealPath('/framework/download/' + row.fileId) + '">'+data+'</a>';
                }
            }
            ,{field:'remark', title: 'FinDisclosureModule001.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var opType = "delete";
    	data.opType = opType;
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule001/delete', object, "FinDisclosureModule001_btn_delete", "FinDisclosureModule002");
            });
        });
    });
   
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams()) 
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinDisclosureModule001_btn_search').click(function() { 
            table.load();
        });
        $('#FinDisclosureModule001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinDisclosureModule001_btn_upload').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinDisclosureModule002"),
                        basePath + "invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002/init",
                        "70%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
    });
}());