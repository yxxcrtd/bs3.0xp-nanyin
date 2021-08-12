$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/relatedparty/RskRelatedParty002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmitList(basePath + 'invest/risk/namelistmanage/relatedparty/RskRelatedParty002/edit', object, "RskRelatedParty002_btn_submit");
            });
        });
    };


    //画面初始化
    var init = function(){
        //编辑模式下  资产代码、资产简称、投向日不可以修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#instituteName').attr("disabled",true);
            $('#instituteCode').attr("disabled",true);
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
        $('#RskRelatedParty002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());