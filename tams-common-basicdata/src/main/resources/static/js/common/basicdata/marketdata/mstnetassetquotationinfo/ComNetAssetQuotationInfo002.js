/**
 * ComNetAssetQuotationInfo002 计划类（净值型）资产估值行情信息新建（含修改）
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        //获取资产代码联想文本框的hidcode金融产品代码
        if (pageModel==PAGE_MODEL_NEW || pageModel==PAGE_MODEL_EDIT){
	        object.finprodId = $('#finProdMarketId').val();
	    }
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/save', object, "ComNetAssetQuotationInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002/save', object, "ComNetAssetQuotationInfo002_btn_submit");
                }
            });
        });
    };
    //画面初始化
    var init = function(){
            //编辑模式资产代码、估值日期、资产名称和管理人不可编辑
            if (pageModel==PAGE_MODEL_EDIT){
            $('#finProdMarketId').attr("disabled",true);
            $('#valDate').attr("disabled",true);
            $('#profitType').attr("disabled",true);
        }
    };
    //画面联动：输入资产代码，联动显示资产名称和管理人
    var finprodIdChange = function() {
        var finProdMarketId = $("#finProdMarketId").val();
        if (!finProdMarketId) {
            $('#finProdName').val('');
            $('#fundManager').val('');
            $('#finprodId').val('');
            return;
        }
        var object = {'finProdMarketId':finProdMarketId, 'profitType': $("#profitType").val()};
        Joyin.ajax(getManagerAndName, object, function(data){
            if(data){
                $('#finProdName').val(data.finProdName);
                $('#fundManager').val(data.fundManager);
                /*$("#fundManager").attr("value",data.fundManager);
                $('#finProdName').attr("value",data.finProdName);*/
                $('#finprodId').val(data.finprodId);
            }
        });
    };
    $(function() {
        //初始化画面
        init();      
        // 绑定按钮事件
        $('#ComNetAssetQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ComNetAssetQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        //资产代码联动事件
        $('#finProdMarketId').change(function() {
            finprodIdChange();
        });
        //收益标识联动事件
        $('#profitType').change(function() {
        	$('#finProdMarketId option').remove();
        	finprodIdChange()
        });
    });
}());