var QueryFunc = function () {
    return {
        serachKey: function () {
        }
    }
}();

(function () {
    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        var gp2=['02']
        if (initgp == 'MST23' && YES_OR_NO_Y != COPY_FLAG) {
            $("#finprodMarketId").attr("disabled",true).trigger('change');
            $("#tradeMarket").attr("disabled",true).trigger('change');
        }
        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }


    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        //新建 快录提交时  清空主键  避免原标的的多于check
        if(pageModel==PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        if (object.tradeMarketId != null) {
            object.finprodMarketId = object.finprodMarketId + object.tradeMarketId;
        }else {
            object.finprodMarketId = object.finprodMarketId + AssetCommon.setTradeMarketId(object.finprodMarketId);
        }
        object.finprodAbbr = $("#finprodName").val();
        var assetProviderTemp = $('#assetProvider').val();
        var assetProvider = null;
        if (assetProviderTemp != null)
            assetProvider = assetProviderTemp.join(",");
        object.minSaleUnit = parseInt($("#minSaleUnit").val(), 10);
        object.assetProvider = assetProvider;
        object.pageModel = pageModel;
        object.operateType = operateType;
        // 为type9，type10，type11，type12赋值 by 赵东东 start
        object.type9 = "04";
        object.type10 = "47";
        object.type11 = "095";
        object.type12 = "097";
        // 为type9，type10，type11，type12赋值 by 赵东东 end
        //文件信息
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        // if (FileCommon){
        //     object.fileCommonList = FileCommon.getUploadFileInfo();
        // }
        //alert(4);
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/stock/FinStockInfo002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_submit");
                }
            });
        });
    };
    //控件加载完成后
    window.componentLoadCallback = function (isDone, pagePath, params) {
    }
    /**
     * 设置拆分
     * @param tradeMarket
     * @param finprodMarketId
     */
    var setInitFinprodMarketId = function(tradeMarket, finprodMarketId) {
        var reObj = {};
        reObj.finprodMarketId = finprodMarketId;
        reObj.tradeMarket = tradeMarket;
        if (tradeMarket == '01') {//银行间
            reObj.tradeMarket = '.IB';
        }else if (tradeMarket == '03') {//上海证券交易所
            reObj.tradeMarket = '.SH';
        }else if (tradeMarket == '04') {//深圳证券交易所
            reObj.tradeMarket = '.SZ';
        }else if (tradeMarket == '09') {//OTC场外
            reObj.tradeMarket = '.OF';
        }else if (tradeMarket == '05') {//OTC场外
            reObj.tradeMarket = '.IOC';
        }
        else if (tradeMarket == '13') {//OTC场外
            reObj.tradeMarket = '.HK';
        }
        else{//机构间私募系统
            reObj.tradeMarket = '';
        }
        if(reObj.tradeMarket != '') {
            var lastFinprodMarketId = finprodMarketId.substring(0,finprodMarketId.length - reObj.tradeMarket.length);
            reObj.finprodMarketId = lastFinprodMarketId;
        }
        return reObj;
    }
    var init = function () {
        //港股通改造
        $('#shOrZh_div').hide();
        $('#minSaleUnit_div').hide();
        // //资产代码拆分
        // var finprodMarketId =  $('#finprodMarketId').val();
        // if (finprodMarketId){
        //     $('#finprodMarketId').val(finprodMarketId.split('.')[0]);
        // }
        var hapCreditAmt = $("#hapCreditAmt").val();
        if (hapCreditAmt && hapCreditAmt > 0) {
            $("#pflsh").attr("disabled", true).trigger('change');
        }
        // if (initgp == '03') {
        //     AssetCommon.setTradeMarketId($('#tradeMarket').val());//在js的初始化添加，给tradeMarketId赋值
        // }
        // if (initps == '01') {
        //     AssetCommon.setTradeMarketId($('#tradeMarket').val());//在js的初始化添加，给tradeMarketId赋值
        // }
        star();
        if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
            $("#ccy").attr("disabled",true);
        }

        if($('#tradeMarket').val() == '13'){
            $('#minSaleUnit_div').show();
            $('#shOrZh_div').show();
        }
        //资产代码拆分
        var finprodMarketId =  $('#finprodMarketId').val();
        var tradeMarket = $('#tradeMarket').val();
        if (finprodMarketId){
            var reObj = setInitFinprodMarketId(tradeMarket, finprodMarketId);
            $('#tradeMarketId').val(reObj.tradeMarket);
            $('#finprodMarketId').val(reObj.finprodMarketId);

        }
    }
    var getCalendarId = function(){
        Joyin.ajax(basePath + 'valuation/basicdata/asset/stock/FinStockInfo002/getCalendarId?tradeMarket=' + $('#tradeMarket').val() + "&shOrZh="+$('#shOrZh').val(),'' , function(result){
            // $('#calendarId').val(result.data[0].code);
            $('#calendarId').textSelect("setValue",result.data[0].code);
        });
    };

    /**
     * 画面加载完成
     *
     */
    $(function () {
        init();
        // 绑定按钮事件
        $('#FinStockInfo002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#FinStockInfo002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });


        $('#tradeMarket').change(function(){
            AssetCommon.setTradeMarketId($('#tradeMarket').val());
            if ($('#tradeMarket').val() == '13') {//香港联合交易所
                $('#shOrZh_div').show();
                $('#minSaleUnit_div').show();
                $('#isMarginFinprod').attr("disabled", true).trigger('change');
                $('#ccy').textSelect("setValue",'HKD');
                $('#tradeMarketId').val('.HK');
            }else{
                $('#shOrZh_div').hide();
                $('#minSaleUnit_div').hide();
                $('#isMarginFinprod').attr("disabled", false).trigger('change');
                $('#ccy').textSelect("setValue",'CNY');
            }
            getCalendarId();
        });

        $('#shOrZh').change(function(){
            getCalendarId();
            // Joyin.ajax(basePath + 'valuation/basicdata/asset/stock/FinStockInfo002/getCalendarId2?tradeMarket=' + $('#shOrZh').val()+"&tradeMarketID=?",'' , function(result){
            //     // $('#calendarId').val(result.data[0].code);
            //     $('#calendarId').textSelect("setValue",result.data[0].code);
            // });
        });

        //typeSelect();
        if (pageModel != PAGE_MODEL_NEW) {
            $('#type').attr("disabled", true);
            $('.btn-save').hide();
        }
    });
}());

