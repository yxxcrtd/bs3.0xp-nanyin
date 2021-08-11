(function() {
    $('#finprodName').change(function(){
        countCnyAmt();
    });

    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#LgdSpecialConfig002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        // 绑定按钮事件
        $('#LgdSpecialConfig002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });

    var countCnyAmt = function() {
        $("#finprodId").val($('#finprodName').select2('data')[0].id);
    };

	/**
     * 获取表单参数
     */
    var submitForm = $.serializeToObject('#submitForm');

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.finprodName = $("#finprodName").text();
        /*object.workflowId = workflowId;*/
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqNo = $('#seqNo').val();
        }
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/check', object, function(result) {
            if(result.data.indexOf("影响历史")>0){
                Joyin.confirm("修改后需手工跑历史批处理！", function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_save");
                    } else if(operateType === 'submit'){
                        WorkFlow.doSubmitList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_submit");
                    }
                });
            }else{
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_save");
                } else if(operateType === 'submit'){
                    WorkFlow.doSubmitList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_submit");
                }
            }
            /*if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_save");
            } else if(operateType === 'submit'){
                WorkFlow.doSubmitList(basePath + 'model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002/save', object, "LgdSpecialConfig002_btn_submit");
            }*/
        }); 	
    };
    
    function　init(){
        //编辑模式资产代码、估值日期、资产简称不可编辑
        if (pageModel == PAGE_MODEL_EDIT) {
            /*$('#vouchYear').attr("disabled", true);
            $('#gradeOrgId').attr("disabled", true);
            $('#oType').attr("disabled", true);
            $('#gradeResult').attr("disabled", true);*/
        }
    }


}());