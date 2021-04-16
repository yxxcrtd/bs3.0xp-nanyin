$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.fundId = $('#fundId').val();
        object.regDate = $('#regDate').val();
        object.delDate = $('#delDate').val();
        object.devidentDate = $('#devidentDate').val();
        object.unitBonusUnitValue = $('#unitBonusUnitValue').val();
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/save', object, "MstFundBonusNotice002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/save', object, "MstFundBonusNotice002_btn_submit");
                }
            });
        });
    };


    //画面初始化
    var init = function(){
        //编辑模式下  资产代码与交易日期不可以修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#fundId').attr("disabled",true);
            $('#regDate').attr("disabled",true);

        }

    }

    /**
     * 画面加载完成
     */
    $(function() {

        //页面初始化及绑定onchange事件
        init();
        /**
         * 绑定事件
         */
        // 绑定按钮事件
        $('#MstFundBonusNotice002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstFundBonusNotice002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());