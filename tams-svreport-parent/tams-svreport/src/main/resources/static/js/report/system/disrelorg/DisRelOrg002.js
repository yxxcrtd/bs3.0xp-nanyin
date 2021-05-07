(function() {
    function init(){
        $(".layui-fixbar").hide();
    };
    $(function() {
        init();
        //保存点击事件
        $('#DisRelOrg002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.relorgId = $("#relorgId").val();
            object.organName = $("#organName").val();
            object.type =$("#type").val();
            if(object.relorgId==''){
                alert("关联机构代码不能为空");
            }
            if(object.organName==''){
                alert("关联机构代码名称不能为空");
            }
            if(object.relorgId!='' && object.organName!='' ){
                Joyin.ajaxJson(basePath + 'svreport/system/disrelorg/DisRelOrg002/save', object, function(result) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    /*if(result){
                        Joyin.ajaxJson(basePath + 'svreport/system/prorate/DisPrdFeeRate002/checkAndSave',object,function(result){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }*/
                });
            }
        });

    });
}());