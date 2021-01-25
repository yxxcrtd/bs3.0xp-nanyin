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
        var obj = JSON.parse(AssetTypeListLink) ;
        if(obj["01"].indexOf(repAssetType)>=0){
            //本行/他行存款||大额存单
            $("#SvrBussRepDepositInfoDto_div").show();
        }else if(obj["02"].indexOf(repAssetType)>=0){
            //拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
            $("#svrBussRepPlacementInfo_div").show();
        }else if(obj["03"].indexOf(repAssetType)>=0){
            //债券类资产/理财直接融资工具/同业存单
            $("#svrBussFinBondRepInfo_div").show();
        }else if(obj["04"].indexOf(repAssetType)>=0){
            //非标准化债权类资产/信贷资产流转项目
            $("#svrBussRepAssetCreditNonstdInfo_div").show();
        }else if(obj["05"].indexOf(repAssetType)>=0){
            //票据类资产/信用证类资产
            $("#svrBussRepAssetAstBillInfo_div").show();
        }else if(obj["06"].indexOf(repAssetType)>=0){
            //权益类资产
            $("#svrBussRepEquityInfo_div").show();
        }else if(obj["07"].indexOf(repAssetType)>=0){
            //金融衍生品
            $("#svrBussRepFinanciallInfo_div").show();
        }else if(obj["08"].indexOf(repAssetType)>=0){
            //QDII债券资产
            $("#svrBussQDIIBondRepInfo_div").show();
        }else if(obj["09"].indexOf(repAssetType)>=0){
            //QDII拆出/逆回购资产
            $("#svrBussRepQDIIdemolitionInfo_div").show();
        }else if(obj["10"].indexOf(repAssetType)>=0){
            //QDII股票/基金类资产
            $("#svrBussQDIISharesInfo_div").show();
        }else if(obj["11"].indexOf(repAssetType)>=0){
            //QDII结构性票据类资产
            $("#svrBussRepQDIIStructureInfo_div").show();
        }else if(obj["12"].indexOf(repAssetType)>=0){
            //资产管理产品
            $("#svrBussRepAssetMngProdInfo_div").show();
        }else if(obj["13"].indexOf(repAssetType)>=0){
            //商品类资产
            $("#svrBussRepNobleMetalInfo_div").show();
        }else if(obj["14"].indexOf(repAssetType)>=0){
            //各类资产的其他项
            $("#svrBussRepOtherInvestTargetInfo_div").show();
        }else if(obj["16"].indexOf(repAssetType)>=0){
            //委外投资——协议方式
            $("#svrBussRepOutInvestAgrInfo_div").show();
        }else if(obj["17"].indexOf(repAssetType)>=0){
            //其他（负债）类资产
            $("#svrBussRepOtherInfo_div").show();
        }else if(obj["18"].indexOf(repAssetType)>=0){
            //另类资产
            $("#svrBussRepOthKindInfo_div").show();
        }else if(obj["L01"].indexOf(repAssetType)>=0){
            //公募基金/私募基金
            $("#svrBussRepFundInfo_div").show();
        }
        star();
        layui.form.render();
    }
    /**
     * 主画面嵌子页面的联动
     */
    var changeReport = function(){
        var repAssetType = $('#assetType2').val();
        var obj = JSON.parse(AssetTypeListLink) ;
        if(obj["01"].indexOf(repAssetType)>=0){
            //本行/他行存款||大额存单
            if(repAssetType=='1004'||repAssetType=='1005'){
                //存款类型置灰不可修改
                $("#SvrBussRepDepositInfoDto_repDepoType").val('').attr("disabled",true);
            }else{
                $("#SvrBussRepDepositInfoDto_repDepoType").attr("disabled",false);
            }
            if(repAssetType=='1002'||repAssetType=='1004'){
                //资金存入银行置灰不可修改
                $("#SvrBussRepDepositInfoDto_bankName").val('').attr("disabled",true);
            }else{
                $("#SvrBussRepDepositInfoDto_bankName").attr("disabled",false);
            }
        }else if(obj["02"].indexOf(repAssetType)>=0){
            //拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
            if(repAssetType=='1108'||repAssetType=='1902'){
                $("#svrBussRepPlacementInfoDto_targetType1").attr("disabled",false);
                $("#issueAmt").attr("disabled",false);
                $("#svrBussRepPlacementInfoDto_targetType1").val('01').trigger("change");
                // var parms={assetuuid:$("#assetUuid").val()}
                // Joyin.ajaxJson(
                //     basePath + "chb/assetinfodetail/AssetInfoDetail002/getValue",parms,
                //     function(result) {
                //         $("#issueAmt").val(result);
                //     }
                // );
            }else if(repAssetType=='1107'||repAssetType=='1901'){
                $("#svrBussRepPlacementInfoDto_targetType1").val('').trigger("change");
                $("#svrBussRepPlacementInfoDto_targetType1").attr("disabled",true);
                $("#issueAmt").attr("disabled",true);
                $("#issueAmt").val('');
            }
        }else if(obj["03"].indexOf(repAssetType)>=0){
            //债券类资产/理财直接融资工具/同业存单
            if(repAssetType=='1112'){
                $("#svrBussFinBondRepInfoDto_repSectype").remove();
            }
        }else if(obj["04"].indexOf(repAssetType)>=0){
            //非标准化债权类资产/信贷资产流转项目
            if(repAssetType=='2201'||repAssetType=='2202'){
                //份额面值置灰不可填
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").attr("disabled",true);
                //法定到期日置灰不可填
                $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").attr("disabled",true);
                //计息类型置灰不可填
                $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").attr("disabled",true);
                //规则付息标识
                $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").attr("disabled",true);
                //利息发布方式
                $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").attr("disabled",true);
                //还本付息情况
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").attr("disabled",true);
                //是否有浮动因子
                $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").attr("disabled",true);
                //结构档次
                $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").attr("disabled",true);
                //还本方式
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").attr("disabled",true);
                //分期还本条款标识
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").attr("disabled",true);
                //基础资产类型
                $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").attr("disabled",true);
                //超额收益分配比例%
                $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").attr("disabled",true);
                //含权类型
                $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").attr("disabled",true);

                //永续条款类型
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").attr("disabled",true);

                $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").attr("disabled",true);


                $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").attr("disabled",true);


                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").attr("disabled",true);

                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").attr("disabled",true);



            }else{

                $("#SvrBussRepAssetCreditNonstdInfoDto_repPrinBaseamt").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_repFmdate").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_repCouponSpecies").attr("disabled",false);
                //规则付息标识
                $("#SvrBussRepAssetCreditNonstdInfoDto_interestRuleFlag").attr("disabled",false);
                //利息发布方式
                $("#SvrBussRepAssetCreditNonstdInfoDto_repIntpayRule").attr("disabled",false);
                //还本付息情况
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepayCmt").attr("disabled",false);
                //是否有浮动因子
                $("#SvrBussRepAssetCreditNonstdInfoDto_floatFactorFlag").attr("disabled",false);
                //结构档次
                $("#SvrBussRepAssetCreditNonstdInfoDto_repStructGrade").attr("disabled",false);
                //还本方式
                $("#SvrBussRepAssetCreditNonstdInfoDto_repRepaymentType").attr("disabled",false);
                //分期还本条款标识
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").attr("disabled",false);
                //基础资产类型
                $("#SvrBussRepAssetCreditNonstdInfoDto_repBasicAssetType").attr("disabled",false);
                //超额收益分配比例%
                $("#SvrBussRepAssetCreditNonstdInfoDto_repExcessProfitRate").attr("disabled",false);
                //含权类型
                $("#SvrBussRepAssetCreditNonstdInfoDto_repContainType").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPerpetualClauseType").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_repDeferredInterestType").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_repPartRedemFlag").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgCode").attr("disabled",false);
                $("#SvrBussRepAssetCreditNonstdInfoDto_creditOrgName").attr("disabled",false);

            }
            if(repAssetType=='2201'||repAssetType=='2202'||repAssetType=='1202'||repAssetType=='1203'
            ||repAssetType=='1204'||repAssetType=='1206'||repAssetType=='1207'||repAssetType=='1208'||
                repAssetType=='1209'){
                //收/受益权类型，下拉框选择空白，且置灰不可修改
                $("#SvrBussRepAssetCreditNonstdInfoDto_repProfitType").val('').trigger("change");
                $("#SvrBussRepAssetCreditNonstdInfoDto_repProfitType").attr("disabled",true);
            }else{
                $("#SvrBussRepAssetCreditNonstdInfoDto_repProfitType").attr("disabled",false);
            }

        }else if(obj["05"].indexOf(repAssetType)>=0){
            //票据类资产/信用证类资产
            if(repAssetType=='1210'){
                //类型默认05置灰不可修改
                $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").val('05').attr('disabled', true).trigger('change');
            }else{
                $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").val('').trigger("change");
                $("#SvrBussRepAssetAstBillInfoDto_notstandSecType").attr("disabled",false);

            }
        }else if(obj["06"].indexOf(repAssetType)>=0){
            //权益类资产
            if(repAssetType=='1305'||repAssetType=='1302'){
                $("#SvrBussRepEquityInfoDto_equityQuit").val('').trigger("change");
                $("#SvrBussRepEquityInfoDto_equityQuit").attr("disabled",true);
            }else if(repAssetType=='1301'){
                $("#SvrBussRepEquityInfoDto_equityQuit").attr("disabled",false);
            }
        }else if(obj["07"].indexOf(repAssetType)>=0){
            //金融衍生品
            if(repAssetType!='1403'){
                $("#svrBussRepFinanciallInfoDto_holdPurpose").val('99').trigger("change");
            }else{
                $("#svrBussRepFinanciallInfoDto_holdPurpose").val('').trigger("change");
            }
        }else if(obj["08"].indexOf(repAssetType)>=0){
            //QDII债券资产
        }else if(obj["09"].indexOf(repAssetType)>=0){
            //QDII拆出/逆回购资产
        }else if(obj["10"].indexOf(repAssetType)>=0){
            //QDII股票/基金类资产
        }else if(obj["11"].indexOf(repAssetType)>=0){
            //QDII结构性票据类资产
        }else if(obj["12"].indexOf(repAssetType)>=0){
            //资产管理产品
            if(repAssetType=='1701'||repAssetType=='1703'||
                repAssetType=='1704'||repAssetType=='1706'||repAssetType=='1702'){
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").val('').trigger("change");
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").attr("disabled",true);
            }else{
                $("#SvrBussRepAssetMngProdInfoDto_financialComissue").attr("disabled",false);
            }
        }else if(obj["13"].indexOf(repAssetType)>=0){
            //商品类资产
        }else if(obj["14"].indexOf(repAssetType)>=0){
            //各类资产的其他项
        }else if(obj["16"].indexOf(repAssetType)>=0){
            //委外投资——协议方式
        }else if(obj["17"].indexOf(repAssetType)>=0){
            //其他（负债）类资产
        }else if(obj["18"].indexOf(repAssetType)>=0){
            //另类资产
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


        if(pageModel!=PAGE_MODEL_NEW){
            $("#assetCode").attr('disabled',true);
        }



        //隐藏所有中债报送信息
        $(".businessDeptClass").hide();
        init();
        $('#AssetInfoDetail002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#assetType2').change(function(){
            showReportInfo();
            //assetType2联动子页面的内容
            changeReport();

        });
    });
}());

