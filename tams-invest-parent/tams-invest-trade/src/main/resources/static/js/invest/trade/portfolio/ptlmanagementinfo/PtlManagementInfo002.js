$(function() {
    var fundAccountTables = FundAccountInfo.fundAccountTable();
    var securitiesAccountInfoTables = SecuritiesAccountInfo.securitiesAccountInfoTable();
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#basicInfoForm');
        var valution = $.serializeToObject('#basicInfoForm');
        var fundAccountTable = fundAccountTables.getAllRows();
        var securitiesAccountInfoTable = securitiesAccountInfoTables.getAllRows();
        object.fundAccountInfoDto = fundAccountTable;
        object.securitiesAccountInfoDto = securitiesAccountInfoTable;
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/save', object, "PtlManagementInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/save', object, "PtlManagementInfo002_btn_submit");
                }
            });
        });
    }
    
    
    $(function() {
        // 按钮事件
        $('#PtlManagementInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlManagementInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());