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


        if (repAssetType=="1701"||repAssetType=="1702"||repAssetType=="1703"||repAssetType=="1704"
            ||repAssetType=="1705"||repAssetType=="1706") {
            // $("#svrBussFinBondRepInfo_div").show();

        }//新增页面拆房同业买入返售资产
        else if((repAssetType=="1107"||repAssetType=="1108"||repAssetType=="1901"||repAssetType=="1902")){
            $("#svrBussRepPlacementInfo_div").show();
        }
        //新增金融衍生品
        else if((repAssetType=="1401"||repAssetType=="1402"||repAssetType=="1403"||repAssetType=="1404"
            ||repAssetType=="1405"||repAssetType=="1406"||repAssetType=="1409")){
            $("#svrBussRepFinanciallInfo_div").show();
        }
        //QDII拆出/逆回购资产
        else if((repAssetType=="1504"||repAssetType=="1506")){
            $("#svrBussRepQDIIdemolitionInfo_div").show();
        }
        else if((repAssetType=="1101"||repAssetType=="1110"||repAssetType=="1109"||repAssetType=="1102"
            ||repAssetType=="1111"||repAssetType=="1112"||repAssetType=="1113"||repAssetType=="1114"
            ||repAssetType=="1115"||repAssetType=="1116"||repAssetType=="1117"||repAssetType=="2101"
            ||repAssetType=="2501"||repAssetType=="2502"||repAssetType=="1111"||repAssetType=="1111")){
            $("#svrBussFinBondRepInfo_div").show();

        }else if((repAssetType=="1201"||repAssetType=="1202"||repAssetType=="1203"||repAssetType=="1204"
            ||repAssetType=="1205")){
            $(".svrBussRepFinanciallInfo_div").show();
        }
        else if((repAssetType=="1301"||repAssetType=="1302"||repAssetType=="1303"||repAssetType=="1304"
            ||repAssetType=="1305")){
            $(".svrBussRepQDIIdemolitionInfo_div").show();
        }
        else if((repAssetType=="1108"||repAssetType=="1902"||repAssetType=="1901"||repAssetType=="1107")){
            $(".SvrBussRepDlRepoInfoDto_div").show();
        }
        else if((repAssetType=="1002"||repAssetType=="1003"||repAssetType=="1004"||repAssetType=="1005")){
            $(".SvrBussRepDepositInfoDto_div").show();
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

