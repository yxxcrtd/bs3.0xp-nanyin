(function(){
    
    var selectAssetChange = function(){
        var object = {};
        if($('#finprodId').val()!=''&&typeof $('#finprodId').val() != 'undefined'){
            var finprodId = $('#finprodId').val();
            var assetSource = $('#finprodId').select2("data")[0].object;
            if(assetSource.indexOf(",")!=-1){
                assetSource = assetSource.split(",")[0]
            }
            object.finprodId = finprodId;object.assetSource = assetSource;
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002/selectAssetInfoById', object, function(result) {//请求路径，请求参数回调函数
                $('#finprodType2').text(result.finprodType2Name);
                $('#finprodAbbr').text(result.finprodAbbr);
                $('#finprodMarketId').text(result.finprodMarketId);
                $('#tradeMarket').text(result.tradeMarketName);
                });
        }
    }
    
    var save = function(operateType){
        var object = {},msg;
        object.ptlId = $('#ptlId').val();
        object.subCode = $('#subCode').text();
        object.subName = $('#subName').text();
        object.subClass = $('#subClass').text();
        object.ptlId = $('#ptlId').val();
        object.tblMarketId = $('#tblMarketId').val();
        if(operateType == 'save'){
            if($('#finprodId').val()==''){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValtblPrtBotasset001.finprodId"));
            }
            if(msg){
                Joyin.error(msg);
                return false;
            }
            object.finprodId = $('#finprodId').val();
            var assetSource = $('#finprodId').select2("data")[0].object;
            if(assetSource.indexOf(",")!=-1){
                assetSource = assetSource.split(",")[0]
            }
            object.assetSource = assetSource;
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
            Joyin.confirm("请确认是否要操作?",function() {
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002/save',
                    object,function(result){
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    })
                })
    }
    
    
    
    $(function(){
        //选择资产change事件
        $('#finprodId').change(selectAssetChange).trigger("change");
        //提交
        $('#RskValtblPrtBotasset002_btn_submit').click(function(){
            save("save");
        });
        //取消映射
        $('#RskValtblPrtBotasset002_btn_cancelMap').click(function(){
            save("cancle");
        });
        if($('#finprodId').val()==''||typeof $('#finprodId').val() == 'undefined'){
            $('#RskValtblPrtBotasset002_btn_cancelMap').off('click').prop('disabled', true).addClass('layui-btn-disabled');
        }
    });
}());