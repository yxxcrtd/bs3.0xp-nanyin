var QueryFunc=function(){
    return {
        serachKey:function(){
        }
    }
}();

(function() {
	/**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        $('.must').each(function() {
            $(this).remove();
        })
        $('*[required]').each(function() {
            var $el = $(this);
            var $target = _findPrevLabel($el, 3);
            if ($target.find('> .must').length === 0) {
                $target.append($('<i class="must">*</i>'));
            }
        });
    }
    /**
     * 中债报送show/hide
     */
    var showReportInfo = function(){
        var repAssetType = $('#assetType2').val();
        //隐藏所有中债报送信息
        $(".businessDeptClass").hide();
        var i=obj["17"].indexOf(repAssetType);
        if(obj["01"].indexOf(repAssetType)>=0){
            //本行/他行存款||大额存单
        }else if(obj["02"].indexOf(repAssetType)>=0){
            //拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
            $("#svrBussRepPlacementInfo_div").show();
        }else if(obj["03"].indexOf(repAssetType)>=0){
            //债券类资产/理财直接融资工具/同业存单
            $("#svrBussFinBondRepInfo_div").show();
        }else if(obj["04"].indexOf(repAssetType)>=0){
            //非标准化债权类资产/信贷资产流转项目
        }else if(obj["05"].indexOf(repAssetType)>=0){
            //票据类资产/信用证类资产
        }else if(obj["06"].indexOf(repAssetType)>=0){
            //权益类资产
        }else if(obj["07"].indexOf(repAssetType)>=0){
            //金融衍生品
            $("#svrBussRepFinanciallInfo_div").show();
        }else if(obj["08"].indexOf(repAssetType)>=0){
            //QDII债券资产
        }else if(obj["09"].indexOf(repAssetType)>=0){
            //QDII拆出/逆回购资产
            $("#svrBussRepQDIIdemolitionInfo_div").show();
        }else if(obj["10"].indexOf(repAssetType)>=0){
            //QDII股票/基金类资产
        }else if(obj["11"].indexOf(repAssetType)>=0){
            //QDII结构性票据类资产
        }else if(obj["12"].indexOf(repAssetType)>=0){
            //资产管理产品
        }else if(obj["13"].indexOf(repAssetType)>=0){
            //商品类资产
        }else if(obj["14"].indexOf(repAssetType)>=0){
            //各类资产的其他项
        }else if(obj["16"].indexOf(repAssetType)>=0){
            //委外投资——协议方式
        }else if(obj["17"].indexOf(repAssetType)>=0){
            //其他（负债）类资产
            $("#svrBussRepOtherInfo_div").show();
        }else if(obj["18"].indexOf(repAssetType)>=0){
            //另类资产
            $("#svrBussRepOthKindInfo_div").show();
        }else if(obj["L01"].indexOf(repAssetType)>=0){
            //公募基金/私募基金
        }
        star();
        layui.form.render();
    }


    function saveOrSubmit(operateType) {

        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;

        Joyin.ajaxJson(basePath + 'chb/assetinfodetail/AssetInfoDetail002/check',object,function(result){
            if(result){
                Joyin.ajaxJson(basePath + 'chb/assetinfodetail/AssetInfoDetail002/save',object,function(result){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            }
        })
    }

    /**
     * 最终提交
     */
    var finalSava = function(object,operateType,result){
        Joyin.ajaxJson(basePath + 'invest/asset/common/isHoliday', {mdate:object.mdate,calendarId:object.calendarId}, function(isHoliday){
            if(isHoliday){
                Joyin.confirm("到日期为节假日，是否确认提交", function() {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/asset/bond/FinBondTargetAsset002/save',
                            object, "FinBondTargetAsset002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/asset/bond/FinBondTargetAsset002/save',
                            object, "FinBondTargetAsset002_btn_submit");
                    }
                })
            }else{
                Joyin.confirm(result.data, function() {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/asset/bond/FinBondTargetAsset002/save',
                            object, "FinBondTargetAsset002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/asset/bond/FinBondTargetAsset002/save',
                            object, "FinBondTargetAsset002_btn_submit");
                    }
                })
            }
        });
    }



    //控件加载完成后
    // window.componentLoadCallback = function(isDone, pagePath, params){
    //     if(isDone){
    //         if(typeof pageModel !== 'undefined'
    //             && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)){
    //             $('.purSpeed').append('<i class="must">*</i>');
    //             $('.redSpeed').append('<i class="must">*</i>');
    //         }
    //         $('#assetType2').trigger('change');
    //     }
    // }
    var init = function(){
        showReportInfo();
    }
    /**
     * 画面加载完成
     *
     */
    $(function() {

        //隐藏所有中债报送信息
        $(".businessDeptClass").hide();
        init();
        $('#AssetInfoDetail002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#assetType2').change(function(){
            showReportInfo();
        });
    });
}());

