/**
 * FinSubmittedModule005 附件上传界面
 */
var file = [];
var FinSubmittedModule005 = function() {
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            file = res;
            if(res && res.length > 0){
                // 为了消除进度条
                Joyin.ajax(JoyinUtil.getRealPath('invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005/uploadFile'), {fileNo: res[0].fileNo,
                    businessId:businessId});
            }
        }

    };
}();
// $(function() {
//
//     /**
//      * 画面加载完成
//      */
//     $(function() {
//         // 绑定按钮事件
//         $("#FinSubmittedModule005_btn_save").click(function() {
//             var object = $.serializeToObject('#submitForm');
//             object.operateType = "save";
//             //将文件信息作为参数传递
//             if(file && _.isArray(file) && file.length > 0){
//                 object.fileName = file[0].srcName;
//                 object.fileId = file[0].fileNo;
//             };
//             object.businessId = businessId;
//                 Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005/check',object,function(result) {
//                     Joyin.confirm(result.data, function() {
//                         Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005/save',
//                             object,function(result){
//                                 //点击保存后关闭窗口
//                                 var index = parent.layer.getFrameIndex(window.name);
//                                 parent.layer.close(index);
//                             })
//                     })
//                 })
//         });
//     });
// }());