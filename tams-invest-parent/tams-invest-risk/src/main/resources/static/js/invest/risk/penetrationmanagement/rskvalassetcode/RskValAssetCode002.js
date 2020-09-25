(function() {
    
    var save = function(){
        var object = $.serializeToObject('#submitForm');
        object.operateType = "save";
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002/save',
                    object,function(result){
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    })
                })
            })
    }
    
    $(function(){
        //保存
        $('#RskValAssetCode002_btn_save').click(function(){
            save();
        });
    });       
}());
