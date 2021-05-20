(function() {
    function init(){
        $(".layui-fixbar").hide();
        if('update'==$("#type").val()){
            $("#prodId").attr("disabled", "disabled");
            $("#prodName").attr("disabled", "disabled");
        }
    };
    $(function() {
        init();
        //保存点击事件
        $('#ChbPrdBaseinfo002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.prodId = $("#prodId").val();
            object.prodName =$("#prodName").val();
            object.raiseVdate = $("#raiseVdate").val();
            object.raiseEnddate = $("#raiseEnddate").val();
            object.vdate = $("#vdate").val();
            object.mdate = $("#mdate").val();
            object.manageType =$("#manageType").val();
            object.isConstracte = $("#isConstracte").val();
            object.yieldHigh = $("#yieldHigh").val();
            object.yieldLow = $("#yieldLow").val();
            object.yieldRemark = $("#yieldRemark").val();
            object.openMode =$("#openMode").val();
            object.raiseVdate = $("#raiseVdate").val();
            object.openCircle = $("#openCircle").val();
            object.openCircleDays = $("#openCircleDays").val();
            object.ruleComment = $("#ruleComment").val();
            object.firstOpenDay =$("#firstOpenDay").val();
            object.holidayOnFlag = $("#holidayOnFlag").val();
            object.avgOpenCount = $("#avgOpenCount").val();
            object.openBuss = $("#openBuss").val();
            object.openBussRemark = $("#openBussRemark").val();
            object.busiAcctNo =$("#busiAcctNo").val();
            object.acctName = $("#acctName").val();
            object.prodtype = $("#prodtype").val();
            object.basic = $("#basic").val();
            object.fiduciaryDuty =$("#fiduciaryDuty").val();
            object.customerType = $("#customerType").val();
            object.isTransfer = $("#isTransfer").val();
            object.isFullyCompliant = $("#isFullyCompliant").val();
            object.isYllccp = $("#isYllccp").val();
            object.isSyqzccp =$("#isSyqzccp").val();
            object.issuingStatus = $("#issuingStatus").val();
            object.dataUuid =$("#dataUuid").val();
            object.type =$("#type").val();

                Joyin.ajaxJson(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo002/check', object, function(result) {
                    Joyin.confirm(result.data, function() {
                        Joyin.ajaxJson(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo002/save',object,function(result){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    });
                });
        });
    });
}());