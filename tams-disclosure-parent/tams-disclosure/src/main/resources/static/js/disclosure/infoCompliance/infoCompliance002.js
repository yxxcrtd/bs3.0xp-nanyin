
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
            if(FileCommon.getUploadFileInfo().length >5){
                Joyin.error("报表/模板附件超过5个");
                return;
            }
            var fileArray = FileCommon.getUploadFileInfo();
            if(fileArray && _.isArray(fileArray)){

                $.each(fileArray, function(index,obj){
                    var srcName = obj.srcName;
                    if(!srcName){
                        srcName = obj.fileName;
                    }
                    var index= srcName.lastIndexOf('.');
                    var ext = srcName.substr(index+1).toUpperCase();
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
        } else {
            Joyin.error("报表/模板附件必须上传!");
            return;
        }

        if(FileCommon2 && FileCommon2.getUploadFileInfo().length >0) {
            if(FileCommon2.getUploadFileInfo().length >5){
                Joyin.error("其他附件超过5个");
                return;
            }
            var fileArray = FileCommon2.getUploadFileInfo();
            if(fileArray && _.isArray(fileArray)){

                $.each(fileArray, function(index,obj){
                    var srcName = obj.srcName;
                    if(!srcName){
                        srcName = obj.fileName;
                    }
                    var index= srcName.lastIndexOf('.');
                    var ext = srcName.substr(index+1).toUpperCase();
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

    function viewPdf(fileNo,fileName){
        var index= fileName.lastIndexOf('.');
        var ext = fileName.substr(index+1).toUpperCase();
        //如果是pdf或者word则进行预览
        if(ext=='PDF'){
            Joyin.ajax(basePath + 'disclosure/infoCompliance/uploadFile001/getFilePath', {fileNo:fileNo}, function(result) {
                Joyin.dialog("文件预览",
                    basePath+"/js/disclosure/pdf/web/viewer.html?file="+result+"&",
                    "90%", "90%", {},function(){});
            });
        } else if(ext=='DOC' || ext=='DOCX'){
            //预览word文件
            Joyin.dialog("文件预览",
                basePath+"/disclosure/common/WordView/init",
                "1120px", "90%", {fileNo:fileNo},function(){});
        }else {
            window.location = basePath+"framework/download/"+fileNo;
        }
    }

    //控件加载完成后
    window.componentLoadCallback = function(isDone, pagePath, params){
    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 去掉工作流框架的“意见和说明”
        $('#wfl_comment').val('无').parent().hide();
        // 去掉工作流中的“完成”按钮
        $('#btnComplete_wfl').remove();

        if(typeof JoyinWorkFlow != 'undefined') {
            JoyinWorkFlow.doCheckOnSubmit = function() {
                var comment = $('#comment').val();
                if(!comment) {
                    Joyin.error('请填写修订意见!');
                    return false;
                }
                $('#wfl_comment').val(comment.length > 200 ? comment.substring(0, 200) : comment);
                return true;
            };

            JoyinWorkFlow.doCheckOnBack = function() {
                var comment = $('#comment').val();
                if(!comment) {
                    Joyin.error('请填写修订意见!');
                    return false;
                }
                $('#wfl_comment').val(comment.length > 200 ? comment.substring(0, 200) : comment);
                return true;
            }
        }

        // 绑定按钮事件
        $('#InfoCompliance002_btn_save').click(function() {
            saveOrSubmit('save');
        });

        $('#InfoCompliance002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        var pageModel = $("#pageModel").val();
        var viewFlag = $("#viewFlag").val();

        //修建,修改
        if(pageModel==='0' || pageModel === '1'){
            $("#commentDiv").hide();
            if($("#btnSubmit_wfl").is(":visible")){
                $("#commentContent").show();
            }
        } else if(pageModel==='2'){
            $("#commentContent").show();
            if (!$("#btnSubmit_wfl").is(":visible")){
                $('#comment').attr('disabled',true);
            }
            else {
                $("#commentDiv").show();
            }
        }
    });
}());
