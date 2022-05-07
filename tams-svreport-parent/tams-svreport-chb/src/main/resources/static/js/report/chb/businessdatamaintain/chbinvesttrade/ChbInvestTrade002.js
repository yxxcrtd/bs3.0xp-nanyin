(function() {
    var tradeCode = $('#tradeCode').val();
    if(''==tradeCode||null==tradeCode){
        //新建页面
        $('#tradeCode').val(tradeCode_New);
        $('#registBankCode').val(registBankCode_New);
        $('#contractCode').val(tradeCode_New);
    }else{
        //修改页面
        $("#tradeCode").attr("disabled","disabled");
        $("#prodId").attr("disabled","disabled");
        $("#registBankCode").attr("disabled","disabled");
        $("#assetCode").attr("disabled","disabled");
        $("#prodName").attr("disabled","disabled");
    }

    $('#prodName').change(function(){
       var prodName = $('#prodName').val();
       if(''==prodName||null==prodName){
       }else{
           Joyin.ajaxJson(basePath+'/chb/businessdatamaintain/chbinvesttrade/chbinvesttrade002/selectProdIdAndRegCodeByProdName?prodName='+prodName,{},function (result){
                $('#prodId').val(result['PROD_ID']);
                $('#regCode').val(result['REG_CODE']);
           })
       }
   });
    $('#assetCode').change(function (){
        var assetCode = $('#assetCode').val();
        if(''==prodName||null==prodName){
        }else{
            Joyin.ajaxJson(basePath+'/chb/businessdatamaintain/chbinvesttrade/chbinvesttrade002/selectAssetNameByAssetCode?assetCode='+assetCode,{},function (result){
                $('#assetName').val(result['ASSET_NAME']);
            })
        }

    });
    let curAssetType = ['01','02','03','04','05','06','07','08']
    $('#curAssetType').change(function (){
       if(!curAssetType.includes( $('#curAssetType').val())&&''!=$('#curAssetType').val()){
           $("#assetMethod").attr("disabled","disabled");
       }else{
           $("#assetMethod").removeAttr("disabled");
       }
    });
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + '/chb/businessdatamaintain/chbinvesttrade/chbinvesttrade002/check', object, function(result) {
           //提交工作流
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + '/chb/businessdatamaintain/chbinvesttrade/chbinvesttrade002/save', object, "CchbInvestTrade002_btn_submit");
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定按钮事件
        $('#CchbInvestTrade002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });
        
    });
}());