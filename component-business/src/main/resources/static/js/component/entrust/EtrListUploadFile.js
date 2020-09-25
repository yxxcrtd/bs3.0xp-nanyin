/**
 * 委托列表上传附件
 */
(function () {

    /**
     * 确定
     */
    function confirm() {
        var object = JSON.parse(rowData);
        if (FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if (object.fileArray && _.isArray(object.fileArray) && object.fileArray.length>0) {
                $.each(object.fileArray, function (index, obj) {
                    obj.seqNo = obj.fileNo;
                    if (!obj.srcName){
                        obj.srcName = obj.fileName;
                    }
                });
                //返回数据并关闭页面
                Joyin.ajaxJson(basePath + 'component/entrust/EtrListUploadFile/getBusinessId', object.fileArray, function (result) {
                    object.businessId = result.businessId;
                    top.returnData = object;
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });

            } else {
                //返回数据并关闭页面
                object.fileArrayName = "";
                top.returnData = object;
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }

    }

    $(function () {
        /**
         * 确定按钮绑定事件
         */
        $('#EtrListUploadFile_btn_confirm').click(function () {
            confirm();
        });

    })
}());