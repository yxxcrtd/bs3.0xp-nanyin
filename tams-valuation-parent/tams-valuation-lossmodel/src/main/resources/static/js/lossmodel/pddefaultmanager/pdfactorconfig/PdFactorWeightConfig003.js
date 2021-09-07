/**
 * PdFactorWrightConfig003 权重配置
 */
$(function () {
	
    //判断是提交
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        //获取资产代码联想文本框的hidcode金融产品代码
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqno = $('#seqno').val();
        }
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003/save', object, "PdFactorConfig002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003/save', object, "PdFactorWeightConfig003_btn_submit");
                }
            });
        });
    };
    //画面初始化
    var init = function () {
        //编辑模式资产代码、估值日期、资产简称不可编辑
        if (pageModel == PAGE_MODEL_EDIT) {
			/*$('#vouchYear').attr("disabled", true);
            $('#vouchQuarter').attr("disabled", true);*/
        }
    };

    $("#optimisticWeight").bind('change',function(){
       if($("#optimisticWeight").val() && $("#basicWeight").val()){
           $("#pessimismWeight").val(100 - $("#optimisticWeight").val() - $("#basicWeight").val());
       }
    });

    $("#basicWeight").bind('change',function(){
        if($("#optimisticWeight").val() && $("#basicWeight").val()){
            $("#pessimismWeight").val(100 - $("#optimisticWeight").val() - $("#basicWeight").val());
        }
    });
    
    $(function () {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#PdFactorWeightConfig003_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        
    });
}());