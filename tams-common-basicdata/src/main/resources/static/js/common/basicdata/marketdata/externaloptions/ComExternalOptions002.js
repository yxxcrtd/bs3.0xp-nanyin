$(function () {

    /**
     * 修改/新建
     */
    var saveOrSubmit = function(operateType) {
        var object = $.serializeToObject('#dataForm');
        object.pageModel = pageModel;
        object.operateType = operateType;

        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions002/check', object, function(result) {
            if (result.data || result.messageType === 'info') {
                Joyin.confirm(result.data, function() {
                    doComplete(operateType, object);
                });
            } else {
                doComplete(operateType, object);
            }


        });
    };

    var doComplete = function(operateType, object){
        if(operateType === 'save'){
            WorkFlow.doSaveList(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions002/save',
                object, "ComExternalOptions002_btn_save");
        }else{// submit
            WorkFlow.doSubmitList(basePath + 'common/basicdata/marketdata/externaloptions/ComExternalOptions002/save',
                object, "ComExternalOptions002_btn_submit");
        }
    };

    /**
     * 根据操作类型：显示或隐藏
     */
    var initShow = function () {

        if (pageModel !== PAGE_MODEL_NEW){
            $("#finprodId").attr("disabled", "disabled");
            $("#finprodId2").attr("disabled", "disabled");
            $("#valDate").attr("disabled", "disabled");
            $("#datasourceType").attr("disabled", "disabled");
            $("#inputType").attr("disabled", "disabled");
            
            $("#targetId").attr("disabled", "disabled");

        } else{
            $('#inputType').val("01").trigger('change');
            $("#inputType").attr("disabled","disabled");
            $("#finprodId2").attr("disabled", "disabled");
        }
    };

    /**
     * 交易编号
     */
    $('#targetId').change(function() {
        var type = $("#targetId_hidden").val();
        $('#finprodId2').val(type);
    });


    $(function() {
        // 绑定按钮事件
        $('#ComExternalOptions002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ComExternalOptions002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });


        initShow();// 根据操作类型：显示或隐藏

    });
});