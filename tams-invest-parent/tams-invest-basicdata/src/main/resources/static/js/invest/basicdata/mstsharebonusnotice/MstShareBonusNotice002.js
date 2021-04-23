$(function() {

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.sharesId = $('#sharesId').val();
        object.delDate = $('#delDate').val();
        object.regDate = $('#regDate').val();
        object.devidentDate = $('#devidentDate').val();
        object.redshareListingDate = $('#redshareListingDate').val();
        object.perDevidentCash = $('#perDevidentCash').val();
        object.perDevidentAmt = $('#perDevidentAmt').val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/save', object, "MstShareBonusNotice002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/save', object, "MstShareBonusNotice002_btn_submit");
                }
            });
        });
    };


    //画面初始化
    var init = function(){
        //编辑模式下  资产代码不可修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#sharesId').attr("disabled",true);
            $('#regDate').attr("disabled",true);
        }
    };



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
        $('#MstShareBonusNotice002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstShareBonusNotice002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());