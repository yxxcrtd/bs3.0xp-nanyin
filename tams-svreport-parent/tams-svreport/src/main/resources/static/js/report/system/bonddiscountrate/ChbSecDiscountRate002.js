(function() {

    $(function() {
        //保存点击事件
        $('#ChbSecDiscountRate002_btn_save').click(function() {
        var object = $.serializeToObject('#submitForm',true);
            Joyin.ajaxJson(basePath + 'svreport/system/bonddiscountrate/ChbSecDiscountRate002/check', object, function(result) {
                if(result){
                    Joyin.ajaxJson(basePath + 'svreport/system/bonddiscountrate/ChbSecDiscountRate002/save',object,function(result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            });
        });

    });
}());