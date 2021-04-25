(function() {
    $(function() {
        //新建按钮点击事件
        $('#ProRegistCodeManage004_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            if(FileCommon){
                //错误信息记录
                var errorMessage="";
                //文件信息
                object.fileArray = FileCommon.getUploadFileInfo();
                if(object.fileArray!=null && object.fileArray!=''){
                    if(object.fileArray && _.isArray(object.fileArray)){
                            $.each(object.fileArray, function(index,obj){
                            var str=obj.srcName;
                            var index=str.lastIndexOf("\.");
                            var fileType="pdf";
                            var fileName=str.substring(0,index);
                            str=str.substring(index+1,str.length);
                            if(fileType!=str){
                                errorMessage+=fileName+"请上传.pdf格式文件！";
                            }
                        });
                            if(errorMessage!=""){
                                Joyin.error(errorMessage);
                            }else{
                                Joyin.ajaxJson(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage004/checkAndSave', object, function(result) {
                                    if(result.message=='保存成功'){
                                        parent.layer.closeAll();
                                    }
                                })
                            }
                        }
                }else{
                    Joyin.error("请选择需要上传的文件！");
                }
            }
        });
    });
}());