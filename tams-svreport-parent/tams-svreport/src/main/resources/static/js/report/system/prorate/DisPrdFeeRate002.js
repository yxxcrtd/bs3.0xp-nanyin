(function() {
    function init(){
        $(".layui-fixbar").hide();
        if('update'==$("#type").val()){
            $("#busiId").attr("disabled", "disabled");
            $("#busiId").attr("style", "background-color: #EEEEEE;");
        }
    };
    $(function() {
        init();
        //保存点击事件
        $('#DisPrdFeeRate002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.busiId = $("#busiId").val();
            object.cashName =$("#busiId").text();
            object.cashType2 = $("#DisPrdFeeRate002_prdFeeType").val();
            object.rate = $("#rate").val();
            object.startDate = $("#startDate").val();
            object.type =$("#type").val();
            object.oldCashType2 =$("#oldCashType2").val();
            object.oldStartDateStr =$("#oldStartDateStr").val();
            if(object.busiId==''){
                alert("产品代码不能为空");
            }
            if(object.cashType2==''){
                alert("信披费用类型不能为空");
            }
            if(object.rate==''){
                alert("费率不能为空");
            }
            if(object.busiId!='' && object.cashType2!='' && object.rate!=''){
                Joyin.ajaxJson(basePath + 'svreport/system/prorate/DisPrdFeeRate002/check', object, function(result) {
                    if(result){
                        Joyin.ajaxJson(basePath + 'svreport/system/prorate/DisPrdFeeRate002/save',object,function(result){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                });
            }
        });

    });
}());