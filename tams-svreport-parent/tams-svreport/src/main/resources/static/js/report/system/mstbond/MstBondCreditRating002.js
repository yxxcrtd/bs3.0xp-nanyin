(function() {
    function init(){
        $(".layui-fixbar").hide();
        if('update'==$("#type").val()){
            document.getElementById("oCode").readOnly="readonly";
        }
    };

    $(function() {
        init();
        //保存点击事件
        $('#MstBondCreditRating002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.oldGradeOrgId = $("#oldGradeOrgId").val();
            object.oldGradeDate = $("#oldGradeDate").val();
            object.oldOType = $("#oldOType").val();
            object.oCode = $("#oCode").val();
            object.gradeOrgId = $("#gradeOrgId").val();
            object.gradeDate = $("#gradeDate").val();
            object.oType = $("#oType").val();
            object.gradeResult = $("#gradeResult").val();
            object.type =$("#type").val();
            if(object.gradeOrgId==''){
                alert("评级机构不能为空");
            }
            if(object.gradeDate==''){
                alert("评级日期不能为空");
            }
            if(object.oType==''){
                alert("评级类型类型不能为空");
            }
            if(object.gradeResult==''){
                alert("评级结果不能为空");
            }
            if(object.gradeOrgId!='' && object.gradeDate!='' && object.oType!=''&& object.gradeResult!=''){
                Joyin.ajaxJson(basePath + 'svreport/system/mstbond/MstBondCreditRating002/save', object, function(result) {
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