/**
 * 每日债券行情新建
 */
(function() {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/check', object, function(result) {
            Joyin.confirm("是否确定执行？", function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/save', object, "ComBondMarketInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/save', object, "ComBondMarketInfo002_btn_submit");
                }
            });
        });
    }
    var init = function(){
        var wfl_operType = $("#wfl_operType").val();
        if(pageModel==PAGE_MODEL_EDIT){
            if(wfl_operType == null || wfl_operType != 'NEW'){
                $('#finprodId').attr("disabled",true);
                $('#valueDate').attr("disabled",true);
            }
        }
    };
    $(function() {
        init();
        // 按钮事件
        $('#ComBondMarketInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ComBondMarketInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#finprodId').change(function(){
            var finprodId = $('#finprodId').val();
            if(finprodId ==''|| finprodId==undefined){
                $('#finprodAbbr').val('');
            }
            else{
                $('#finprodAbbr').val($('#finprodId').select2('data')[0].object).trigger('change');
            }
        })

    });
}());
