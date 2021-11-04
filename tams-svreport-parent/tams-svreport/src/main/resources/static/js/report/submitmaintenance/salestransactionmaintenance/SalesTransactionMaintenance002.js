/**
 * 销售结算资金交易信息维护新建
 */
(function() {
    /**
     * 获取表单参数
     */
        var informationform = function() {
            return $.serializeToObject('#informationForm');
        } 

        /**
         * 保存或提交
         */
        function saveOrSubmit(operateType) {
            var object = $.serializeToObject('#informationForm');
            object.operateType = operateType;
            object.pageModel = pageModel;
            Joyin.ajaxJson(basePath + 'report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002/save', object, "SalesTransactionMaintenance002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'report/submitmaintenance/salestransactionmaintenance/SalesTransactionMaintenance002/save', object, "SalesTransactionMaintenance002_btn_submit");
                    }
                });
            });
        }


           var init = function(){
           if(pageModel==PAGE_MODEL_EDIT){
           $('#institutionCode').prop("disabled",true);
           $('#prdRegistCode').prop("disabled",true);
           $('#accountNo').prop("disabled",true);
           $('#accountName').prop("disabled",true);
           $('#tradeNo').prop("disabled",true);
           $('#tradePs').prop("disabled",true);
           $('#tradeCounterAcckNo').prop("disabled",true);
           $('#tradeCounterAccName').prop("disabled",true);
           $('#tradeCounterAccBankName').prop("disabled",true);
           $('#tradeCounterAccBankNo').prop("disabled",true);
           $('#tradeCcy').prop("disabled",true);
           $('#tradeTime').prop("disabled",true);
           }
           }


        $(function() {
            //编辑字段置灰
            init();
            // 按钮事件
            $('#SalesTransactionMaintenance002_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#SalesTransactionMaintenance002_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
            
        });    
}());