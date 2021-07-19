/**
 *  额度校验规则管理新建（含修改）
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        if ($("#astType2").val()) {
            object.astType2 = $("#astType2").val();
        } else {
            object.astType2 = null;
        }
        if ($("#astType3").val()) {
            object.astType3 = $("#astType3").val();
        } else {
            object.astType3 = null;
        }
        if ($("#astType4").val()) {
            object.astType4 = $("#astType4").val();
        } else {
            object.astType4 = null;
        }
        if ($("#astType5").val()) {
            object.astType5 = $("#astType5").val();
        } else {
            object.astType5 = null;
        }
        object.pageModel = pageModel;
        object.operateType = operateType; 
    	if($('#creditTagId').val() != null && $('#creditTagId').val() != ''){
    		object.creditTagId = $('#creditTagId').val();
    	}
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrulemanage/CreditRule002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/creditrulemanage/CreditRule002/save', object, "CeRule002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/creditrulemanage/CreditRule002/save', object, "CeRule002_btn_submit");
                }
            });
        });
    };

    $(function() {
 
        // 绑定按钮事件
        $('#CeRule002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#CeRule002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        if(IS_SHOW_TYPE == '01'){
            $('#selector5').show();
            $('#selector6').show();
        }else{
            $('#selector5').hide();
            $('#selector6').hide();
        }
        
    });
}());