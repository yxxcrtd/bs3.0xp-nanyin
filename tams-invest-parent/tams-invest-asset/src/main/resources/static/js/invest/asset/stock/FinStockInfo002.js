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
        if (initgp == 'MST23') {
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

    /**
     * 中债报送信息DIV控制显示
     */
    function typeSelect() {
        var type = $('#type').val();
        if (type == publicOffPlan) {
            $(".bussRepAssetMngProdInfo_div").show();
            $(".bussRepOutInvestAgrInfo_div").hide();
        } else {
            $(".bussRepAssetMngProdInfo_div").hide();
            $(".bussRepOutInvestAgrInfo_div").show();
        }
    };

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');    
        object.finprodAbbr = $("#finprodName").val();
        var assetProviderTemp = $('#assetProvider').val();
        var assetProvider = null;
        if (assetProviderTemp != null)
            assetProvider = assetProviderTemp.join(",");
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
        object.fileCommonList = FileCommon.getUploadFileInfo();
        //alert(4);
        Joyin.ajaxJson(basePath + 'invest/asset/stock/FinStockInfo002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_submit");
                }
            });
        });
    };
    //控件加载完成后
    window.componentLoadCallback = function (isDone, pagePath, params) {
    }
    var init = function () {
        //资产代码拆分
        var finprodMarketId =  $('#finprodMarketId').val();
        if (finprodMarketId){
            $('#finprodMarketId').val(finprodMarketId.split('.')[0]);
        }
        //如果授信模式=='02'，则显示批复号和实际授信主体
        if (CE_MODEL == '02') {
            $('#actualCreditProvidDiv').show();
            $('#pflshDiv').show();
        } else {
            $('#actualCreditProvidDiv').hide();
            $('#pflshDiv').hide();
        }
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
        if(isTransInAsset){
        	$("#ccy").attr("disabled",true);
        }
    }
    var getCalendarId = function(){
            Joyin.ajax(basePath + 'invest/asset/stock/FinStockInfo002/getCalendarId?tradeMarket=' + $('#tradeMarket').val(),'' , function(result){
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
        $('#magnifier').click(function() {
            getInterfFundDto();
        });
        $('#tradeMarket').change(function(){
            AssetCommon.setTradeMarketId($('#tradeMarket').val());
            getCalendarId();
        });
        //typeSelect();
        if (pageModel != PAGE_MODEL_NEW) {
            $('#type').attr("disabled", true);
            $('.btn-save').hide();
        }
        $("#pflsh").change(function () {
            var str = $('#pflsh').select2('data')[0].object.split(',');
            if (str != null && str.length == 3) {
                $("#seqno").val(str[0]);
                $("#creditMstId").val(str[1]);
                $("#creditFlag").val(str[2]);
            }
            if (str[2] == 'Y') {
                $("#actualCreditProvid").attr("required", true).trigger('change');
            }
            if (str[2] != 'Y') {
                $("#actualCreditProvid").attr("required", false).trigger('change');
            }
            star();
        });
    });
}());

