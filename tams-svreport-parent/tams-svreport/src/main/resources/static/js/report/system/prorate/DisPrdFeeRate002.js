(function() {
    function init(){
        $(".layui-fixbar").hide();
        // if('update'==$("#type").val()){
        //     $("#busiId").attr("disabled", "disabled");
        //     $("#busiId").attr("style", "background-color: #EEEEEE;");
        // }
        if($("#busiId").val() != "") {
            $("#busiId").attr("disabled", "disabled");
            $("#busiId").attr("style", "background-color: #EEEEEE;");
        }
    };

    /**
     * 判定是否为数值
     * @param strValue
     * @returns {boolean}
     */
    var judgeIsNumFunc = function(strValue) {
        if(!isNaN(strValue)){
            return false;
        }
        return true;
    }
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
                Joyin.error("产品代码不能为空");
                return ;
            }
            if(object.cashType2==''){
                Joyin.error("信披费用类型不能为空");
                return ;
            }
            if(object.rate==''){
                Joyin.error("费率不能为空");
                return ;
            }
            if(judgeIsNumFunc(object.rate)) {
                Joyin.error("费率必须为数值类型");
                return ;
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