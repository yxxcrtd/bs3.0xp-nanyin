(function(){
    var getObject = function(){
        var object = {};
        var finprodId = $('#finprodId').val();
        var assetSource = $('#assetSource').val();
        var ptlId = $('#ptlId').val();
        var subCode = $('#subCode').text();
        var tblMarketId = $('#tblMarketId').val();
        object.finprodId = finprodId;
        object.assetSource = assetSource;
        object.ptlId = ptlId;
        object.subCode = subCode;
        object.tblMarketId = tblMarketId;
        return object;
    }
    
    /**
     * 应用未映射
     */
    var applyMapForValUnmapped = function(){
        Joyin.confirm("请确认是否要操作?",function() {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003/applyMapForValUnmapped',
                    getObject(),function(result){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                })
            })
    }
    
    /**
     * 应用不同映射
     */
    var applyMapForValDiffmapped = function(){
        Joyin.confirm("请确认是否要操作?",function() {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003/applyMapForValDiffmapped',
                    getObject(),function(result){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                })
            })
    }
    
    
    
    $(function(){
        //应用未映射
        $('#RskValtblPrtBotasset003_btn_applyMapForValUnmapped').click(function(){
            applyMapForValUnmapped();
        });
        //应用不同映射
        $('#RskValtblPrtBotasset003_btn_applyMapDiffmapped').click(function(){
            applyMapForValDiffmapped();
        })
        if($('#countForValUnmapped').text()=='0'){
            $('#RskValtblPrtBotasset003_btn_applyMapForValUnmapped').off('click').prop('disabled', true).addClass('layui-btn-disabled');
        }
        if($('#countForValDiffmapped').text()=='0'){
            $('#RskValtblPrtBotasset003_btn_applyMapDiffmapped').off('click').prop('disabled', true).addClass('layui-btn-disabled');
        }
    });
}());