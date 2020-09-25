(function() {
    var save = function(){
        var object = $.serializeToObject('#submitForm');
        object.operateType = "save";
        object.pageModel = pageModel;
        Joyin.confirm("请确认是否要保存?", function() {
            Joyin.ajaxJson(basePath + 'invest/asset/credit/FinCreditProject002/save',
                object,function(result){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                })
            })
    }
    
    $(function(){
        //保存
        $('#FinCreditProject002_btn_save').click(function(){
            save();
        });
    });       
}());
