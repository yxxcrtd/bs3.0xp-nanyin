var valTableFileName = '';
CeAssetPositionManagement002 = function(){
        return {
            uploadCallback : function(res){
                valTableFileName = '';
                if(res && res.length > 0){
                    valTableFileName = res[0].fileName;
                 // 为了消除进度条
                    Joyin.ajax(JoyinUtil.getRealPath('invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement002/uploadFile'));
                }
            }
        }
}();
(function() {
    /**
     * 获取查询参数
     */
    var param = {};
    var getSearchParams = function() {
        var param = $.serializeToObject('#submitForm');
        return param;
    };
    
    /**
     * 获取文件路径名
     */
    var getFileName = function(){
        var fun = $('#valtable').data('getSelectedFiles');
        if(typeof fun === 'function') {
            return fun();
        }
        return [];
    }
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var object = $.serializeToObject('#submitForm');
        Joyin.exportExcel('export', object);//导出
    };
    
    function showErrorMessage(result) {
        $('#importResultTable').show().joyintable({
            cols: [
              { title: "FwFileUpload.mesage.excelno", field: 'rowNum', width:120, align: 'center', sortable:false},
              { title: "FwFileUpload.mesage.validKey", field: 'validKey', width:180, align: 'center', sortable:false},
              { title: "FwFileUpload.mesage.error", field: 'errorInfo', width:467, align: 'left', sortable:false}
            ],
            searchOnInit: true,
            checkbox: false,
            resize: false,
            data: result || [],
            height: 300
        });
        JoyinUtil.hideLoading();
        $('#dragUploadAction').prop('disabled', false).removeClass('layui-btn-disabled');
    }

    /**
     * 画面加载完成
     */
    $(function() {
        //绑定导出按钮
        $('#CeAssetPositionManagement002_btn_download').click(function() {
            exportExcel();//导出
        });
        $('#CeAssetPositionManagement002_btn_submit').click(function() {
            var object = $.serializeToObject('#submitForm');
            var fileInfo = getFileName();
            if(typeof fileInfo[0] != 'undefined'){
                object.valTableFileName = fileInfo[0].targetFile;
            }
        Joyin.confirm("请确认是否要提交？", function() {
            Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement002/importFile', object, function(result) {
                if(!result || !($.isArray(result) && result.length > 0)) {
                    layer.alert("导入成功", {
                        icon:  1,
                        closeBtn: 0
                    });
                }
                else {
                    showErrorMessage(result);
                }
            });
        });
      });
    });
}());