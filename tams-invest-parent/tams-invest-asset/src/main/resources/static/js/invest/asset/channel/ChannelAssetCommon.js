var ChannelAssetCommon = function () {
    /**
     * 设置通道
     * 对于挂通道的资产：项目类、计划类等，资产代码选择时，自动设置通道
     * @param chlIdSelect 通道控件id
     * @param finprodId 资产的金融产品代码
     * @param fromEtr 是否是委托（有初始值时，禁用通道）
     * @param callback 回调函数，这个里的方法可能依赖通道的值
     */
    var setChannelSelect = function (chlIdSelect, finprodId, fromEtr, callback) {
        if (typeof finprodId == 'undefined' || !finprodId) {
            if (typeof callback == 'function') {
                callback.call();
            }
            return;
        }
        chlIdSelect.html('');
        chlIdSelect.prop("disabled", false);
        if (fromEtr) {
            chlIdSelect.prop("disabled", true);
        }
        Joyin.ajaxJson(basePath
            + 'component/trade/BussChannelCommon/getChannelIdForAsset?assetChl=true&finprodId='+finprodId + "&_v="+Math.random(),
            {}, function(result) {
                if (result && result.length > 0) {
                    for (var key in result) {
                        chlIdSelect.append('<option value="'+result[key].id+'" data-object="'+result[key].object+'">'+result[key].text+'</option>');
                    }
                    if (result.length == 1 && !chlIdSelect.attr('initvalue')) {
                        chlIdSelect.val(result[0].id).change();
                        chlIdSelect.prop("disabled", true);
                    }
                    else if (chlIdSelect.attr('initvalue')) {
                        chlIdSelect.val(chlIdSelect.attr('initvalue')).change();
                    }
                    else if (result.length > 1){
                        chlIdSelect.val(result[0].id).change();
                    }
                }
                else {
                    chlIdSelect.val('').change();
                }
                if (typeof callback == 'function') {
                    callback.call();
                }
            });
    }
    
    /**
     * 设置通道
     * 对于挂通道的资产：项目类、计划类等，资产代码选择时，自动设置通道
     * @param chlIdSelect 通道控件id
     * @param finprodId 资产的金融产品代码
     * @param fromEtr 是否是委托（有初始值时，禁用通道）
     * @param callback 回调函数，这个里的方法可能依赖通道的值
     */
    var setChannelSelectObject = function (chlIdSelect, finprodId, fromEtr, callback) {
        if (typeof finprodId == 'undefined' || !finprodId) {
            if (typeof callback == 'function') {
                callback.call();
            }
            return;
        }
        chlIdSelect.html('');
        //chlIdSelect.prop("disabled", false);
        if (fromEtr) {
            chlIdSelect.prop("disabled", true);
        }
        Joyin.ajaxJson(basePath
            + 'component/trade/BussChannelCommon/getChannelIdAndCollectTypeForAsset?assetChl=true&finprodId='+finprodId + "&_v="+Math.random(),
            {}, function(result) {
            	chlIdSelect.html('');
                if (result && result.length > 0) {
                    for (var key in result) {
                        chlIdSelect.append('<option value="'+result[key].id+'" data-payType="'+result[key].object.payType+'" data-collectType="'+result[key].object.collectType+'">'+result[key].text+'</option>');
                    }
                    if (result.length == 1 && !chlIdSelect.attr('initvalue')) {
                        chlIdSelect.val(result[0].id).change();
                        chlIdSelect.prop("disabled", true);
                    }
                    else if (chlIdSelect.attr('initvalue')) {
                        chlIdSelect.val(chlIdSelect.attr('initvalue')).change();
                    }
                    else if (result.length > 1){
                        chlIdSelect.val(result[0].id).change();
                    }
                }
                else {
                    chlIdSelect.val('').change();
                }
                if (typeof callback == 'function') {
                    callback.call();
                }
            });
    }

    return {setChannelSelect: setChannelSelect
    		,setChannelSelectObject:setChannelSelectObject};
}();