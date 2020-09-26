$(function () {

    /**
     * 修改/新建
     */
    var saveOrSubmit = function(operateType) {
        var object = $.serializeToObject('#dataForm');
        object.pageModel = pageModel;
        object.operateType = operateType;

        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valfxvaluztioninfo/ValFxValuztionInfo002/check', object, function(result) {
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
            WorkFlow.doSaveList(basePath + 'valuation/basedata/marketdata/valfxvaluztioninfo/ValFxValuztionInfo002/save',
                object, "ValFxValuztionInfo002_btn_save");
        }else{// submit
            WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/valfxvaluztioninfo/ValFxValuztionInfo002/save',
                object, "ValFxValuztionInfo002_btn_submit");
        }
    };

    /**
     * 根据操作类型：显示或隐藏
     */
    var initShow = function () {
    	
        if (pageModel !== PAGE_MODEL_NEW){
            $("#finprodId2").attr("disabled", "disabled");
            $("#foreignexChangeType").attr("disabled", "disabled");
            $("#valDate").attr("disabled", "disabled");
            $("#datasourceType").attr("disabled", "disabled");
            $("#inputType").attr("disabled", "disabled");
           
        } else{
        	$('#inputType').val("01").trigger('change');
        	$("#inputType").attr("disabled","disabled");
        	$("#foreignexChangeType").attr("disabled", "disabled");
        }
    };

   /**
     * 交易编号
     */
    $('#finprodId2').change(function() {
    	var type = $("#finprodId2_hidden").val();
    	$('#foreignexChangeType').val(type).trigger('change');
    });
    
    $(function() {
        // 绑定按钮事件
        $('#ValFxValuztionInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValFxValuztionInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });


        initShow();// 根据操作类型：显示或隐藏

    });
});