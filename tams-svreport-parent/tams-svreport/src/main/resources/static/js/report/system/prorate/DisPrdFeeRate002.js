(function() {
    function init(){
        $(".layui-fixbar").hide();
    };
    $(function() {
        init();
        function validationNumber(e, num) {
            var regu = /^[0-9]+\.?[0-9]*$/;
            var index=e.lastIndexOf("\.");
            var str=e.substring(index+1,e.length);
            if (e != "") {
                if (!regu.test(e)) {
                    Joyin.error("请输入正确的数字");
                    return;
                } else {
                    if (str.length>14) {
                        Joyin.error("小数点后超过最大14位长度");
                        return;
                    }

                }
            }
        }

        //保存点击事件
        $('#DisPrdFeeRate002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.busiId = $("#busiId").val();
            object.cashName =$("#busiId").text();
            object.cashType2 = $("#DisPrdFeeRate002_prdFeeType").val();
            object.rate = $("#rate").val();
            object.startDate = $("#startDate").val();
            object.type =$("#type").val();
            validationNumber(object.rate,14);
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
                Joyin.ajaxJson(basePath + 'svreport/system/prorate/DisPrdFeeRate002/save', object, function(result) {
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