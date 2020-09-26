// 交易费用管理修改
$(function () {

    $("#TradeCostManagement003_btn_submit").click(function() {
        console.log(pageModel);
        var payDate = $('#payDate').val();
        var mDate = $('#mDate').val();
        var feeAmt = $('#feeAmt').val();
        var ccy = $('#ccy').val();
        if (!(payDate&&mDate&&feeAmt&&ccy)){
            Joyin.error(JoyinUtil.getMessage("费用数据录入不完整,请补充!"));
            return;
        }
        if (feeType == FEE_TYPE_B08 && !registOrg ){
            Joyin.error(JoyinUtil.getMessage("登记托管机构不能为空!"));
            return;
        }
        var object =  $.serializeToObject('#submitForm');
        object.operateType = 'submit';
        Joyin.ajaxJson(basePath + 'valuation/tradecostmanagement/TradeCostManagement003/doCheck', object, function (data) {
            WorkFlow.doSubmitList(basePath + 'valuation/tradecostmanagement/TradeCostManagement003/update', object,
                'TradeCostManagement003_btn_submit','TradeCostManagement003',
                function(result) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
        });
    });

    //页面全部加载完毕后
    $(document).ready(function(){
        var feeType = $.trim($('#feeType').val());
        if (!(feeType == FEE_TYPE_B08 || feeType == FEE_TYPE_B13)) {//不是结算手续费和维护费
            //
            $('#registOrgDiv').hide();
        }
    });


});



