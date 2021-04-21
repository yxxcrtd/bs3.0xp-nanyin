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
        var type6 = $('#type6').val();
        var type7 = $('#type7').val();
        var repAssetType = $('#repAssetType').val();
        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        if(isQuickInput&&pageModel == PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        //文件信息
        if(FileCommon){
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        //参与方信息
        if(BussParticipantInfo){
            object.bussParticipantInfoDto = {};
            object.bussParticipantInfoDto.type6 = type6;
            object.bussParticipantInfoDto.finprodType2 = INT_ASSET_TYPE;
            object.bussParticipantInfoDto.bussParticipantInfoDtoList = BussParticipantInfo.getAllRows();
            if(null!=object.bussParticipantInfoDto.bussParticipantInfoDtoList&&object.bussParticipantInfoDto.bussParticipantInfoDtoList.length>0){
                object.contactWay = object.bussParticipantInfoDto.bussParticipantInfoDtoList[0].contactWay;
                object.contact =     object.bussParticipantInfoDto.bussParticipantInfoDtoList[0].contact;
            }
        }
        //增信信息
        if(BussFinProductCredit){
            object.bussFinProductCreditDto = {};
            object.bussFinProductCreditDto.finProductCreditList = BussFinProductCredit.getAllRows();
        }
        //评级信息
        object.busiFinBondRatingInfoDto = {};
        if(BusiFinProjectRatingInfo){
            object.busiFinBondRatingInfoDto.mstBondCreditRatingList = BusiFinProjectRatingInfo.getAllRows();
        }
        Joyin.ajaxJson(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/check', object, function(
            result) {
                Joyin.ajaxJson(basePath + 'invest/asset/common/isHoliday', {
                    mdate: object.mdate,
                    calendarId: object.calendarId
                }, function (isHoliday) {
                    if (isHoliday) {
                        Joyin.confirm("到期日为节假日，是否确认提交", function () {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/save',
                                    object, "FinProjectNetTargetAsset002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/save',
                                    object, "FinProjectNetTargetAsset002_btn_submit");
                            }
                        })
                    } else {
                        Joyin.confirm(result.data, function () {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/save',
                                    object, "FinProjectNetTargetAsset002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/save',
                                    object, "FinProjectNetTargetAsset002_btn_submit");
                            }
                        });
                    }
                })
        });
    }

    /**
     * 计算期限==起息日与到期日之间的自然天数，不含到期日
     */
    var calTermDays = function(){
        var objDiv= $("#FinProjectTargetAsset002_DIV");
        var vdate = objDiv.find("input[name='vdate']").val();
        var mdate = objDiv.find("input[name='mdate']").val();
        if(vdate!=null && vdate!=''&&mdate!=null && mdate!=''){
            var nDays = Math.abs(parseInt(((Date.parse(Date.ofDate(mdate))) - (Date.parse(Date.ofDate(vdate))))/1000/3600/24));
            objDiv.find("input[name='termDays']").val(nDays);
        }else{
            objDiv.find("input[name='termDays']").val('');
        }
        objDiv.find("input[name='termDays']").attr("disabled",true);
    }


    /**
     * 参与方信息显示/隐藏
     */

    var showBussParticipantInfo = function(){
        $('#bussParticipantInfoDto_div').show();
    }
    /**
     * 资产类别的change事件
     */
    var type6Change = function(){
        if($('#type6').val()==ASSET_TYPE_PROJECT_01){
            $('#type7').prop('disabled',false).trigger('change');
            $('#type7').change(type7Change);
        }else{
            $('#type7').prop('disabled',true);
            if($('#type7').val() || pageModel === PAGE_MODEL_NEW) {
                $('#type7').trigger('change');
            }
        }
        if($('#type6').val()==ASSET_TYPE_PROJECT_01
            ||$('#type6').val()==ASSET_TYPE_PROJECT_02
            ||$('#type6').val()==ASSET_TYPE_PROJECT_10
            ||$('#type6').val()==ASSET_TYPE_PROJECT_05){
            $('#projInvestIndustryDiv').show();
        }
        else{
            $('#projInvestIndustryDiv').hide();
        }
        showBussParticipantInfo();
    }

    /**
     * type7 change事件
     */
    var type7Change = function(){
        showBussParticipantInfo();
    }

    /**
     * 资产来源change事件
     */
    var assetSourceChange = function(){
        var assetSource = $('#assetSource').val();
        if(assetSource==ASSET_SOURCE_01){
            // 增加推荐方与推荐费率 delete by renping 20191014 start
//            $('#assetProvider').removeAttr('disabled').trigger('change');
            // 增加推荐方与推荐费率 delete by renping 20191014 end
            $('#assetSourceUnit').removeAttr('disabled').trigger('change');
        }else{
            // 增加推荐方与推荐费率 delete by renping 20191014 start
//            $('#assetProvider').val('').trigger('change');
            // 增加推荐方与推荐费率 delete by renping 20191014 end
            $('#assetSourceUnit').val('').trigger('change');
            // 增加推荐方与推荐费率 delete by renping 20191014 start
//            $('#assetProvider').attr('disabled',true).trigger('change');
            // 增加推荐方与推荐费率 delete by renping 20191014 end
            $('#assetSourceUnit').attr('disabled',true).trigger('change');
        }
    }

    //快捷录入
    //add by  zhangnian  20191015 start
    var getInterfFundDto = function(){
        if ($('#finprodMarketId').prop('disabled')) {
            return;
        }
        if (finprodMarketId) {
            Joyin.ajax(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset002/init', {
                'finprodMarketId': $('#finprodMarketId').val()
                ,'isQuickInput':true
            }, function (data) {
                $("body").html(data);
            });
        }else{
            Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('FinDepositTarget002.finprodId')));
        }
    };
    //add by  zhangnian  20191015 end

    window.componentLoadCallback = function(isDone, pagePath, params){
        if(params.entityName === 'finInterestFactorDto') {
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
        }
        if(isDone){
            //资产二级分类change
            type6Change();
            type7Change();
            $('#repAssetType').trigger('change');
            if ($('#finprodMarketId').prop('disabled')) {
                $("#magnifier").css({color: '#dbdbdb'});
            }
            //该资产存在交易
            if(isTransInAsset && isTransInAsset == 'true'){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#tradeMarket').attr('disabled',true).trigger('change');
                disablePageElement($("#bussParticipantInfoTable").parent());
            }
        }
    }
    var init = function(){
        if($('#type6').val()==ASSET_TYPE_PROJECT_01
            ||$('#type6').val()==ASSET_TYPE_PROJECT_02
            ||$('#type6').val()==ASSET_TYPE_PROJECT_10
            ||$('#type6').val()==ASSET_TYPE_PROJECT_05){
            $('#projInvestIndustryDiv').show();
        }
        else{
            $('#projInvestIndustryDiv').hide();
        }

        //如果授信模式=='02'，则显示批复号和实际授信主体
        //注意：原来是条件或，现在是条件与
        if(CE_MODEL == '02' && pflshType == 'Y'){
            $('#actualCreditProvidDiv').hide();
            $('#pflshDiv').show();
        }
        else{
            $('#actualCreditProvidDiv').hide();
            $('#pflshDiv').show();
        }
        var hapCreditAmt = $("#hapCreditAmt").val();
       /* if(hapCreditAmt && hapCreditAmt>0){
            $("#pflsh").attr("disabled",true).trigger('change');
        }*/
        if($("#creditFlag").val() == 'Y'){
            $("#actualCreditProvid").attr("required", true).trigger('change');
        }
        if($("#creditFlag").val() != 'Y'){
            $("#actualCreditProvid").attr("required", false).trigger('change');
        }
        star();
    }

    function bindEvent() {
        //按资产类别show/hide投后信息、报送信息
        $('#type6').change(type6Change);
        //资产来源change
        $('#assetSource').change(assetSourceChange).trigger('change');

        // 项目类资产五级分类改造 add by renping 2019-07-25 start
        // 资产五级分类联动投管分类
        $("#type9,#type10").change(function(){
            window.BussAssetFiveType.doAssetFiveTypeChange('02',PINAN_ASSET_TYPE_F19,'type9','type10',null,null,'type6','type7')
        });
        // 项目类资产五级分类改造 add by renping 2019-07-25 end
    }

    $(function() {
        init();
        //除新建画面 ，资产类型置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('#type6').attr('disabled',true).trigger('change');
            $('#finprodMarketId').attr('disabled', true).trigger('change');
            $('.btn-save').hide();
        }
        // 绑定按钮事件
        $('#FinProjectTargetAsset002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProjectTargetAsset002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $("#FinProjectTargetAsset002_DIV").find("input[name='vdate'],input[name='mdate']").change(calTermDays);
        $("#pflsh").change(function(){
            var obj = $('#pflsh').select2('data')[0].object;
            if(obj){
                var str = obj.split(',');
                if(str != null && str.length==3){
                    $("#seqno").val($("#pflsh").val());
                    $("#creditMstId").val(str[1]);
                    $("#creditFlag").val(str[2]);
                }
                if(str[2] == 'Y'){
                    $("#actualCreditProvid").attr("required", true).trigger('change');
                }
                if(str[2] != 'Y'){
                    $("#actualCreditProvid").attr("required", false).trigger('change');
                }
            }
            else{
                $("#seqno").val("");
                $("#creditMstId").val("");
                $("#creditFlag").val("");
            }
            star();
        });

        bindEvent();

        //增加放大镜
        $('#magnifier').click(function() {
            getInterfFundDto();
        });

        $('#profitType').change(function(){
            var profitType = $('#profitType').val();
            if(profitType == PROFIT_TYPE_02){
                $('#finInterestFactorDto_div').css('display','block');
                $('#finPayInterestStdInfoDto_div').css('display','block');
                $('#bussFinRepaymentDto_div').css('display','block');
                $('#mdate').parent().siblings('label').append($('<i class="must">*</i>'));
            }else if(profitType == PROFIT_TYPE_01){
                $('#finInterestFactorDto_div').css('display','none');
                $('#finPayInterestStdInfoDto_div').css('display','none');
                $('#bussFinRepaymentDto_div').css('display','none');
                $('#mdate').parent().siblings('label').find('i').remove();
            }
        });
    });
});
