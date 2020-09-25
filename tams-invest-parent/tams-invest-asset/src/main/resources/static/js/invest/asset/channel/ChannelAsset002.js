$(function() {
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
        if (typeof pageModel !== 'undefined'
                && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
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
    }

    
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        //费用信息
        if(BussFinProductFeeInfo){
            object.bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList = BussFinProductFeeInfo.getAllRows();
        }
        //文件信息 add by yanya 20190903
        object.fileCommonList = FileCommon.getUploadFileInfo();
        Joyin.ajaxJson(basePath + 'invest/asset/channel/ChannelAsset002/check', object, function(
                result) {
                if(isTransInAsset){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                            finalSava(object,operateType,result);
                    })
                }else{
                        finalSava(object,operateType,result);
                }
        });
    }
    
    /**
     * 最终提交
     */
    var finalSava = function(object,operateType,result){
        Joyin.confirm(result.data, function() {
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'invest/asset/channel/ChannelAsset002/save',
                        object, "ChannelAsset002_btn_save");
            } else {
                WorkFlow.doSubmitList(basePath + 'invest/asset/channel/ChannelAsset002/save',
                        object, "ChannelAsset002_btn_submit");
            }
        });
    }
    
    var init = function(){
        star();
    }
    
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
          //该资产存在交易
            if(isTransInAsset){
                $('#vdate').attr('disabled',true).trigger('change');
                $('#mdate').attr('disabled',true).trigger('change');
            }
            $("#bussRepAssetMngProdInfo_repAssetType_div").show();
        }
    }
    
    $(function() {
        init();
        //修改  资产代码,通道名称置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $("#finprodMarketId").attr('disabled',true);
            $("#finprodName").attr('disabled',true);
        }
        // 绑定按钮事件
        $('#ChannelAsset002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ChannelAsset002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#mdate,#vdate').change(function(obj){
            obj.type = FINPROD_TYPE_F28;
            AssetCommon.checkDate(obj);
        });

        $("#pflsh").change(function(){
            var str = $('#pflsh').select2('data')[0].object.split(',');
            if(str != null && str.length==3){
                $("#seqno").val(str[0]);
                $("#creditMstId").val(str[1]);
                $("#creditFlag").val(str[2]);
            }
            if(str[2] == 'Y'){
                $("#actualCreditProvid").attr("required", true).trigger('change');
            }
            if(str[2] != 'Y'){
                $("#actualCreditProvid").attr("required", false).trigger('change');
            }
            star();
        });
    });
});
