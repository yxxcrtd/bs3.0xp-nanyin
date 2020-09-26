// 交易费用管理新建
$(function () {
    $("#TradeCostManagement002_btn_submit").click(function() {
        var bookSetId = $('#bookSetId').val();
        var feeType = $('#feeType').val();
        var payDate = $('#payDate').val();
        var mDate = $('#mDate').val();
        var feeAmt = $('#feeAmt').val();
        var ccy = $('#ccy').val();
        var registOrg =$('#registOrg').val();
        if (!(bookSetId&&feeType&&payDate&&mDate&&feeAmt&&ccy)){
            Joyin.error(JoyinUtil.getMessage("费用数据录入不完整,请补充!"));
            return;
        }
        if (feeType == FEE_TYPE_B08 && !registOrg ){
            Joyin.error(JoyinUtil.getMessage("登记托管机构不能为空!"));
            return;
        }
        var object =  $.serializeToObject('#submitForm');
        // object.tradeId = $('#tradeId').val();
        // object.bookSetId = bookSetId;
        // object.feeType = feeType;
        // object.payDate= payDate;
        // object.feeAmt= feeAmt;
        // object.registOrg=$('#registOrg').val();
        // object.ccy = ccy;
        // object.isPrepaid=$('#isPrepaid').val();
        // object.remark=$('#remark').val();
        object.operateType = 'submit';
        Joyin.ajaxJson(basePath + 'valuation/tradecostmanagement/TradeCostManagement002/doCheck', object, function (data) {
            WorkFlow.doSubmitList(basePath + 'valuation/tradecostmanagement/TradeCostManagement002/save', object,
                'TradeCostManagement002_btn_submit','TradeCostManagement002',
                function(result) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
            });
            // Joyin.ajaxJson(basePath + 'valuation/tradecostmanagement/TradeCostManagement002/save', object, function(result) {
            //     var index = parent.layer.getFrameIndex(window.name);
            //     parent.layer.close(index);
            // });
        });
        

    });

    //页面全部加载完毕后 registOrgDiv
    $(document).ready(function(){
        $('#registOrgDiv').hide();
        // 费用类型变更时
        $("#feeType").change(function() {
            var feeType = $.trim($('#feeType').val());
            if (!(feeType == FEE_TYPE_B08 || feeType == FEE_TYPE_B13)) {
                //
                $('#registOrg').empty();
                $('#registOrgDiv').hide();
            }else {
                $('#registOrg').empty();
                $('#registOrgDiv').show();
            }
        });
    });

});



