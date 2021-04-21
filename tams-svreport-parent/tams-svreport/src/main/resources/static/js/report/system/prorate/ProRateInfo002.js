(function() {
    $(function() {
        //保存点击事件
        $('#ProRateInfo002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.prod_id = $("#prod_id").val();
            object.prdFeeType = $("#ProRegistCodeManage001_prdFeeType").val();
            object.feeRate = $("#feeRate").val();
            object.startDate = $("#date").val();
            if(object.prod_id==null){
                alert("产品代码不能为空");
            }
            if(object.prdFeeType==null){
                alert("信披费用类型不能为空");
            }
            if(object.feeRate==''){
                alert("费率不能为空");
            }
            if(object.prod_id!=null && object.prdFeeType!=null && object.feeRate!=''){
                Joyin.ajaxJson(basePath + 'svreport/system/prorate/ProRateInfo002/checkAndSave', object, function(result) {
                    if(result.message=='保存成功'){
                        parent.layer.closeAll();
                    }else{
                        alert(result.message);
                    }
                });
            }


        });

    });
}());