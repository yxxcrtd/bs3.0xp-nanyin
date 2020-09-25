$(function() {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.operateType = operateType;
        //object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/portfolio/actualtimecapital/ActualTimeCapital002/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                	Joyin.ajaxJson(basePath + 'invest/portfolio/actualtimecapital/ActualTimeCapital002/save', object, function(result) {
                		parent.layer.close(parent.layer.getFrameIndex(window.name));
                	});
                });
                
        });
    }
    
    function init(){
    	$('#portfolioId').prop('disabled', true);
    	$('#adjDate').prop('disabled', true);
    	$('#acctId').prop('disabled', true);
    	$('#ccy').prop('disabled', true);
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        $('#ActualTimeCapital002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());