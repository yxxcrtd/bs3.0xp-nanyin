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
        $(".businessDeptClass").attr('disabled',true);
        var obj = JSON.parse(AssetTypeListLink) ;
        if(obj["01"].indexOf(repAssetType)>=0){
            //本行/他行存款||大额存单
            $("#svrBussRepDepositInfoRiskDto_div").show();
        }else if(obj["02"].indexOf(repAssetType)>=0){
            //拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
            $("#svrBussRepPlacementInfoRisk_div").show();
        }else if(obj["03"].indexOf(repAssetType)>=0){
            //债券类资产/理财直接融资工具/同业存单
            $("#svrBussFinBondRepInfoRisk_div").show();
        }else if(obj["04"].indexOf(repAssetType)>=0){
            //非标准化债权类资产/信贷资产流转项目
            $("#svrBussRepAssetCreditNonstdInfoRisk_div").show();
        }else if(obj["05"].indexOf(repAssetType)>=0){
            //票据类资产/信用证类资产
            $("#svrBussRepAssetAstBillInfoRisk_div").show();
        }else if(obj["06"].indexOf(repAssetType)>=0){
            //权益类资产
            $("#svrBussRepEquityInfoRisk_div").show();
        }else if(obj["07"].indexOf(repAssetType)>=0){
            //金融衍生品
            $("#svrBussRepFinanciallInfoRisk_div").show();
        }else if(obj["08"].indexOf(repAssetType)>=0){
            //QDII债券资产
            $("#svrBussQDIIBondRepInfoRisk_div").show();
        }else if(obj["09"].indexOf(repAssetType)>=0){
            //QDII拆出/逆回购资产
            $("#svrBussRepQDIIdemolitionInfoRisk_div").show();
        }else if(obj["10"].indexOf(repAssetType)>=0){
            //QDII股票/基金类资产
            $("#svrBussQDIISharesInfoRisk_div").show();
        }else if(obj["11"].indexOf(repAssetType)>=0){
            //QDII结构性票据类资产
            $("#svrBussRepQDIIStructureInfoRisk_div").show();
        }else if(obj["12"].indexOf(repAssetType)>=0){
            //资产管理产品
            $("#svrBussRepAssetMngProdInfoRisk_div").show();
        }else if(obj["13"].indexOf(repAssetType)>=0){
            //商品类资产
            $("#svrBussRepNobleMetalInfoRisk_div").show();
        }else if(obj["14"].indexOf(repAssetType)>=0){
            //各类资产的其他项
            $("#svrBussRepOtherInvestTargetInfoRisk_div").show();
        }else if(obj["16"].indexOf(repAssetType)>=0){
            //委外投资——协议方式
            $("#svrBussRepOutInvestAgrInfoRisk_div").show();
        }else if(obj["17"].indexOf(repAssetType)>=0){
            //其他（负债）类资产
            $("#svrBussRepOtherInfoRisk_div").show();
        }else if(obj["18"].indexOf(repAssetType)>=0){
            //另类资产
            $("#svrBussRepOthKindInfoRisk_div").show();
        }else if(obj["L01"].indexOf(repAssetType)>=0){
            //公募基金/私募基金
            $("#svrBussRepFundInfoRisk_div").show();
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
                $("#svrBussRepDepositInfoRiskDto_repDepoType").val('').attr("disabled",true);
            }else{
                $("#svrBussRepDepositInfoRiskDto_repDepoType").attr("disabled",false);
            }
            if(repAssetType=='1002'||repAssetType=='1004'){
                //资金存入银行置灰不可修改
                $("#svrBussRepDepositInfoRiskDto_bankName").val('').attr("disabled",true);
            }else{
                $("#svrBussRepDepositInfoRiskDto_bankName").attr("disabled",false);
            }
        }else if(obj["02"].indexOf(repAssetType)>=0){
            //拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
            if(repAssetType=='1108'||repAssetType=='1902'){
                $("#svrBussRepPlacementInfoRiskDto_targetType1").attr("disabled",false);
                $("#issueAmt").attr("disabled",false);
                $("#svrBussRepPlacementInfoRiskDto_targetType1").val('01').trigger("change");
                // var parms={assetuuid:$("#assetUuid").val()}
                // Joyin.ajaxJson(
                //     basePath + "chb/assetinfodetail/AssetInfoDetailRisk002/getValue",parms,
                //     function(result) {
                //         $("#issueAmt").val(result);
                //     }
                // );
            }else if(repAssetType=='1107'||repAssetType=='1901'){
                $("#svrBussRepPlacementInfoRiskDto_targetType1").val('').trigger("change");
                $("#svrBussRepPlacementInfoRiskDto_targetType1").attr("disabled",true);
                $("#issueAmt").attr("disabled",true);
                $("#issueAmt").val('');
            }
        }else if(obj["03"].indexOf(repAssetType)>=0){
            //债券类资产/理财直接融资工具/同业存单
            if(repAssetType=='1112'){
                $("#svrBussFinBondRepInfoRiskDto_repSectype").remove();
            }
        }else if(obj["04"].indexOf(repAssetType)>=0){
            $("#svrBussRepAssetCreditNonstdInfoRiskDto_repChose").val('无');
            //非标准化债权类资产/信贷资产流转项目
            if(repAssetType=='2201'||repAssetType=='2202'){
                //份额面值置灰不可填
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").attr("disabled",true);
                //法定到期日置灰不可填
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").attr("disabled",true);
                //计息类型置灰不可填
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").attr("disabled",true);
                //规则付息标识
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").attr("disabled",true);
                //利息发布方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").attr("disabled",true);
                //还本付息情况
                //$("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").val('').trigger("change");
                //$("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").attr("disabled",true);

                //浮动因子
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").attr("disabled",true);
                //担保方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").attr("disabled",true);
                //抵质押物类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").attr("disabled",true);
                //抵质押物价值
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").attr("disabled",true);
                //担保性质
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").attr("disabled",true);
                //担保人与融资人关系
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").attr("disabled",true);
                //行权价格
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").attr("disabled",true);
                //递延利息是否计息
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").attr("disabled",true);
                //部分赎回比例%
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").attr("disabled",true);


                //是否有浮动因子
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").attr("disabled",true);
                //结构档次
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").attr("disabled",true);
                //还本方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").attr("disabled",true);
                //分期还本条款标识
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").attr("disabled",true);
                //基础资产类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").attr("disabled",true);
                //超额收益分配比例%
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").attr("disabled",true);
                //含权类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").attr("disabled",true);

                //永续条款类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").attr("disabled",true);

                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").attr("disabled",true);


                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").attr("disabled",true);


                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").attr("disabled",true);

                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").attr("disabled",true);



            }else{

                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPrinBaseamt").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repFmdate").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repCouponSpecies").attr("disabled",false);
                //规则付息标识
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_interestRuleFlag").attr("disabled",false);
                //利息发布方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repIntpayRule").attr("disabled",false);
                //还本付息情况
              //  $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepayCmt").attr("disabled",false);

               //浮动因子
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactor").attr("disabled",false);
                //担保方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repGuaType").attr("disabled",false);
                //抵质押物类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeType").attr("disabled",false);
                //抵质押物价值
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPledgeValue").attr("disabled",false);
                //担保性质
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repNatureGuarantee").attr("disabled",false);
                //担保人与融资人关系
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRelationFinan").attr("disabled",false);
                //行权价格
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExercisePrice").attr("disabled",false);
                //递延利息是否计息
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestInt").attr("disabled",false);
                //部分赎回比例%
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemRate").attr("disabled",false);

                //是否有浮动因子
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_floatFactorFlag").attr("disabled",false);
                //结构档次
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repStructGrade").attr("disabled",false);
                //还本方式
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repRepaymentType").attr("disabled",false);
                //分期还本条款标识
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPeriodRepaymentFlag").attr("disabled",false);
                //基础资产类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repBasicAssetType").attr("disabled",false);
                //超额收益分配比例%
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repExcessProfitRate").attr("disabled",false);
                //含权类型
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repContainType").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPerpetualClauseType").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repDeferredInterestType").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repPartRedemFlag").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgCode").attr("disabled",false);
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_creditOrgName").attr("disabled",false);

            }
            if(repAssetType=='2201'||repAssetType=='2202'||repAssetType=='1202'||repAssetType=='1203'
                ||repAssetType=='1204'||repAssetType=='1206'||repAssetType=='1207'||repAssetType=='1208'||
                repAssetType=='1209'){
                //收/受益权类型，下拉框选择空白，且置灰不可修改
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").val('').trigger("change");
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").attr("disabled",true);
            }else{
                $("#svrBussRepAssetCreditNonstdInfoRiskDto_repProfitType").attr("disabled",false);
            }


        }else if(obj["05"].indexOf(repAssetType)>=0){
            //票据类资产/信用证类资产
            $("#svrBussRepAssetAstBillInfoRiskDto_privateFlag").val('02').trigger("change");
            if(repAssetType=='1210'){
                //类型默认05置灰不可修改
                $("#svrBussRepAssetAstBillInfoRiskDto_notstandSecType").val('05').attr('disabled', true).trigger('change');
            }else{
                $("#svrBussRepAssetAstBillInfoRiskDto_notstandSecType").val('').trigger("change");
                $("#svrBussRepAssetAstBillInfoRiskDto_notstandSecType").attr("disabled",false);

            }
        }else if(obj["06"].indexOf(repAssetType)>=0){
            //权益类资产
            if(repAssetType=='1305'||repAssetType=='1302'){
                $("#svrBussRepEquityInfoRiskDto_equityQuit").val('').trigger("change");
                $("#svrBussRepEquityInfoRiskDto_equityQuit").attr("disabled",true);
            }else if(repAssetType=='1301'){
                $("#svrBussRepEquityInfoRiskDto_equityQuit").attr("disabled",false);
            }
        }else if(obj["07"].indexOf(repAssetType)>=0){
            //金融衍生品
            if(repAssetType!='1403'){
                $("#svrBussRepFinanciallInfoRiskDto_holdPurpose").val('99').trigger("change");
            }else{
                $("#svrBussRepFinanciallInfoRiskDto_holdPurpose").val('').trigger("change");
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
                $("#svrBussRepAssetMngProdInfoRiskDto_financialComissue").val('').trigger("change");
                $("#svrBussRepAssetMngProdInfoRiskDto_financialComissue").attr("disabled",true);
            }else{
                $("#svrBussRepAssetMngProdInfoRiskDto_financialComissue").attr("disabled",false);
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
            if(repAssetType=='2607'||repAssetType=='2610'||repAssetType=='2613'||
                repAssetType=='2611'||repAssetType=='2612'||repAssetType=='2617'){
                $("#svrBussRepFundInfoRiskDto_isGovernFund").val('').trigger("change");
                $("#svrBussRepFundInfoRiskDto_isGovernFund").attr("disabled",false);
            }else{
                $("#svrBussRepFundInfoRiskDto_isGovernFund").val('').trigger("change");
                $("#svrBussRepFundInfoRiskDto_isGovernFund").attr("disabled",true);
            }
            if(repAssetType=='1106'||repAssetType=='2401'||repAssetType=='2402'||
                repAssetType=='1303'||repAssetType=='2403'||repAssetType=='2499'){
                //公募
                $("#svrBussRepFundInfoRiskDto_financialComissue").val('').trigger("change");
                $("#svrBussRepFundInfoRiskDto_financialComissue").attr("disabled",true);
            }else{
                $("#svrBussRepFundInfoRiskDto_financialComissue").attr("disabled",false);
            }
        }
        if(repAssetType=='1305'||repAssetType=='1302'||repAssetType=='1301'){
        	 $("#vdate_div").hide();
        	 $("#mdate_div").hide();
        }else{
        	$("#vdate_div").show();
       	    $("#mdate_div").show();
        }
        star();
        layui.form.render();
    }

    function saveOrSubmit(operateType) {

        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;

        /**【资产基本信息维护】-新建页面（1401、1402、1403、1404、1405、1406、1499），提交报错**/
        object.svrBussRepFinanciallInfoRiskDto.targetType3 = object.svrBussRepFinanciallInfoRiskDto.targetType3?object.svrBussRepFinanciallInfoRiskDto.targetType3.join(","):'';
        object.svrBussRepDepositInfoRiskDto.repTargetType = object.svrBussRepDepositInfoRiskDto.repTargetType?object.svrBussRepDepositInfoRiskDto.repTargetType.join(","):'';

        object.pageModel = pageModel;

        Joyin.ajaxJson(basePath + 'invest/risk/assetinfodetail/AssetInfoDetailRisk002/check',object,function(result){
            if(result){
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/assetinfodetail/AssetInfoDetailRisk002/save', object, "AssetInfoDetailRisk002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/assetinfodetail/AssetInfoDetailRisk002/save', object, "AssetInfoDetailRisk002_btn_submit");
                }
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
     * 将当前子页面的数据赋值
     */
    var assetVal = function(){
        // $("input[id$='_assetId']").val($('#assetCode').val());
        $("input[id$='_assetName']").val($('#assetName').val());
    }
    /**
     * 画面加载完成
     *
     */
    $(function() {


        if(pageModel!=PAGE_MODEL_NEW){
            $("#assetCode").attr('disabled',true);
        }
        
        var repAssetType = $('#assetType2').val();
        if(repAssetType=='1305'||repAssetType=='1302'||repAssetType=='1301'){
       	 $("#vdate_div").hide();
       	 $("#mdate_div").hide();
       }else{
       	$("#vdate_div").show();
      	    $("#mdate_div").show();
       }

        //隐藏所有中债报送信息
        $(".businessDeptClass").hide();
        init();

        $('#AssetInfoDetailRisk002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#AssetInfoDetailRisk002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#assetType2').change(function(){
            showReportInfo();
            //assetType2联动子页面的内容
            changeReport();
            assetVal();
        });

        $('#assetCode').change(function(){
            assetVal();
        });

        $('#assetName').change(function(){
            assetVal();
        });


    });
}());

