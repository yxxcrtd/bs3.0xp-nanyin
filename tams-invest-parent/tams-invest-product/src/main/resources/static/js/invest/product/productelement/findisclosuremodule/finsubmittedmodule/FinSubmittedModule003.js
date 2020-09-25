/**
 * FinSubmittedModule003 申报附件模板管理添加
 */
var file = [];
var FinSubmittedModule003 = function() {
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            file = res;
            if(res && res.length > 0){
                // 为了消除进度条
                Joyin.ajax(JoyinUtil.getRealPath('invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/uploadFile'), {fileNo: res[0].fileNo,
                businessId:seqno});
            }
        }
        
    };
}();
$(function() {
    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定按钮事件
        $("#FinSubmittedModule003_btn_submit").click(function () {
            var object = $.serializeToObject('#submitForm');
            object.operateType = "submit";
            //将文件信息作为参数传递
            if (file && _.isArray(file) && file.length > 0) {
                object.fileName = file[0].srcName;
                object.fileId = file[0].fileNo;
            }
            ;
            object.schemeId = schemeId;
            object.seqno = seqno;
            //点击添加后执行保存
            if (pageModel === PAGE_MODEL_NEW) {
                Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/check', object, function (result) {
                    Joyin.confirm(result.data, function () {
                        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/save',
                            object, function (result) {
                                //点击保存后关闭窗口
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                            })
                    })
                })
            }
        });
    });
}());