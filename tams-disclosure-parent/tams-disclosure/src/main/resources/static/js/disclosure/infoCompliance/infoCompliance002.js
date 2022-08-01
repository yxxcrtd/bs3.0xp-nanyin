
/**
 * 上传文件
 */
(function() {
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var infoType = $("#infoType").val();
        if(!infoType){
            Joyin.error("类型不能为空!");
            return;
        }
        var infoName = $("#infoName").val();
        if(!infoName){
            Joyin.error("报表/模板名称不能为空!");
            return;
        }
        var remark = $("#remark").val();
        if(!remark){
            Joyin.error("备注不能为空!");
            return;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm', false);

        var checkFlag = true;
        if(FileCommon && FileCommon.getUploadFileInfo().length >0) {
            var fileArray = FileCommon.getUploadFileInfo();
            debugger;
            if(fileArray && _.isArray(fileArray)){

                $.each(fileArray, function(index,obj){
                    var srcName = obj.srcName;
                    if(!srcName){
                        srcName = obj.fileName;
                    }
                    var index= srcName.lastIndexOf('.');
                    var ext = srcName.substr(index+1).toUpperCase();
                    debugger;
                    //支持word、pdf、Excel、zip 文件
                    if(ext!='DOC' && ext!='DOCX' && ext!='PDF' && ext!='XLS'  && ext!='XLSX' && ext!='ZIP'){
                        checkFlag = false;
                        Joyin.error(srcName+"文件格式不是word、pdf、Excel或zip,不支持上传!");
                        return;
                    }
                    if(obj.size > 20*1024*1024){
                        checkFlag = false;
                        Joyin.error(srcName+"文件大于20M,不支持上传");
                        return;
                    }
                });

            }
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }

        if(FileCommon2 && FileCommon2.getUploadFileInfo().length >0) {
            var fileArray = FileCommon2.getUploadFileInfo();
            if(fileArray && _.isArray(fileArray)){

                $.each(fileArray, function(index,obj){
                    var srcName = obj.srcName;
                    if(!srcName){
                        srcName = obj.fileName;
                    }
                    var index= srcName.lastIndexOf('.');
                    var ext = srcName.substr(index+1).toUpperCase();
                    debugger;
                    //支持word、pdf、Excel、zip 文件
                    if(ext!='DOC' && ext!='DOCX' && ext!='PDF' && ext!='XLS'  && ext!='XLSX' && ext!='ZIP'){
                        checkFlag = false;
                        Joyin.error(srcName+"文件格式不是word、pdf、Excel或zip,不支持上传!");
                        return;
                    }
                    if(obj.size > 20*1024*1024){
                        checkFlag = false;
                        Joyin.error(srcName+"文件大于20M,不支持上传");
                        return;
                    }
                });

            }
            object.uploadFileList2 = FileCommon2.getUploadFileInfo();
        }
        if(checkFlag){
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                .getLabel("SysCommon.submit")), function() {
                if(operateType === 'save'){
                    object.operationType = 'save';
                    WorkFlow.doSaveList(basePath + 'disclosure/infoCompliance/infoCompliance002/save', object, "InfoCompliance002_btn_save");
                } else {
                    object.operationType = 'submit';
                    WorkFlow.doSubmitList(basePath + 'disclosure/infoCompliance/infoCompliance002/save', object, "InfoCompliance002_btn_submit");
                }
            });
        }

    }

    function checkBeforeSubmit(){
        return false;
    }

    //控件加载完成后
    window.componentLoadCallback = function(isDone, pagePath, params){
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#InfoCompliance002_btn_save').click(function() {
            saveOrSubmit('save');
        });

        $('#InfoCompliance002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //提交流程前检查批注是否已经填写
        $('#checkBeforeSubmit').click(function() {
            checkBeforeSubmit();
        });

        var pageModel = $("#pageModel").val();
        //新建页面隐藏批注
        if(pageModel==='0'){
            $("#commentDiv").hide();
        } else if(pageModel==='2'){
            debugger;
            $("#commentDiv").show();
            setTimeout(function(){
                debugger;
                $("#commentRemark").attr("disabled",false);
            },5000);
        }

    });
}());
