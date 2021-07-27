$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.finprodAbbr = $("#finprodAbbr").select2('data')[0].text;
        object.finprodId = 'F06_' + object.finprodMarket;
        object.pageId = pageId;
        Joyin.ajaxJson(basePath + 'invest/risk/fundgoal/FundGoal002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmitList(basePath + 'invest/risk/fundgoal/FundGoal002/save', object, pageModel==PAGE_MODEL_NEW?"FundGoal002_btn_submit":"FundGoal001_btn_submit");
            });
        });
    };


    $("#finprodAbbr").change(function () {
        $("#finprodMarket").val($("#finprodAbbr").select2('data')[0].id);
        finprodId = 'F06_' +  $("#finprodMarket").val();
        if (finprodId!='F06_'){
            Joyin.ajaxJson(basePath + 'invest/risk/fundgoal/FundGoal002/getNextPubDate', {
                finprodId : finprodId
            }, function (result) {
                $("#nextPubDate").val(new Date(result).format('yyyy-MM-dd'));
            });
        }
    });


    //画面初始化
    var init = function(){
        //编辑模式下  资产代码、资产简称、投向日不可以修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#finprodId').attr("disabled",true);
            $('#finprodAbbr').attr("disabled",true);
            $('#goalDate').attr("disabled",true);
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
        $('#FundGoal002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());