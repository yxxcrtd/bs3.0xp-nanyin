$(function() {
    
    /**
     * 初始化
     */
    function init(){
        if(pageModel==PAGE_MODEL_EDIT){
            $("#marketId").attr('disabled',true);//编辑状态置灰主键marketId
            $("#effDate").attr('disabled',true);//编辑状态置灰主键effDate
        }
    }
    /**
     * 新建和修改事件的反应类
     * @param operateType
     * @returns null
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');//序列化表单
        object.pageModel = pageModel;//设置page_model
        object.operateType = operateType;//设置操作类型
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/check', object, function(result) {
            if(operateType === 'save') {//判断是保存还是提交
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/save', object, "MstIndexQuotationInfo002_btn_save");//保存
                });
            }
            else {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/save', object, "MstIndexQuotationInfo002_btn_submit");//提交
                });
            }
        });
    };
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化
        init();
        // 绑定保存按钮事件
        $('#MstIndexQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        //绑定提交事件
        $('#MstIndexQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());