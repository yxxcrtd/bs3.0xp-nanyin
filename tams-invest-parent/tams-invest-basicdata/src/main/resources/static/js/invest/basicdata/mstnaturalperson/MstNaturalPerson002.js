/**
 * 自然人信息管理002
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
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmit(operateType) {
            var object = $.serializeToObject('#informationForm');
            object.customerId= $('#customerId').val();
            object.operateType = operateType;
            object.pageModel = pageModel;
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson002/save', object, "MstNaturalPerson002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson002/save', object, "MstNaturalPerson002_btn_submit");
                    }
                });
            });
        }
      
        $(function() {
            // 按钮事件
            $('#MstNaturalPerson002_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#MstNaturalPerson002_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
            
        });    
}());