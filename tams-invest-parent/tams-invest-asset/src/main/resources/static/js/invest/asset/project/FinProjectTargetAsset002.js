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

    //获取计息要素信息元素id
    var getInterestFactorElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finInterestFactorDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finInterestFactorDto' + '_' + id);
        }
    };
    
    //获取付息画面元素id
    var getPayInterestStdInfoElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id);
        }
    };
    //获取投后信息元素id
    var getBussPostInvestInfoElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'bussPostInvestInfoDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'bussPostInvestInfoDto' + '_' + id);
        }
    };
    function saveOrSubmit(operateType) {
        var type6 = $('#type6').val();
        var type7 = $('#type7').val();
        var repAssetType = $('#repAssetType').val();
        
        if(repAssetType == "1301"&& !$('#bussRepEquityInfoDto_isUnlisted').val()){
        	Joyin.error("是否政府出资产业投资基金数据不能为空。")
            return;
        }
        if(repAssetType == "1399"&& !$('#bussRepOtherInvestTargetInfoDto_isUnlisted').val()){
        	Joyin.error("是否政府出资产业投资基金数据不能为空。")
            return;
        }
        if(repAssetType == "1201" && !$('#bussRepAssetAstBillInfoDto_isBzhpj').val()){
        	Joyin.error("是否标准化票据数据不能为空。")
            return;
        }
        
        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        if(isQuickInput&&pageModel == PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        //付息信息
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();
            for(var i = 0; i < object.finPayInterestStdInfoDto.finCashPlanList.length; i++){
                if (object.finPayInterestStdInfoDto.finCashPlanList[i].vdateTerm == null){
                    object.finPayInterestStdInfoDto.finCashPlanList[i].vdateTerm = $('#vdate').val();       
                }
                if (object.finPayInterestStdInfoDto.finCashPlanList[i].mdateTerm == null){
                    object.finPayInterestStdInfoDto.finCashPlanList[i].mdateTerm = $('#mdate').val();
                }
            }
        }
        //还本信息
        if(BussFinRepayment){
            object.bussFinRepaymentDto.finCashPlanList = BussFinRepayment.getAllRows();
        }
        //投后信息
        object.bussPostInvestInfoDto.type6 = type6;
        //减持信息
        if(BussReduceShareInfo){
            object.bussReduceShareInfoDto = {};
            object.bussReduceShareInfoDto.finReduceShareInfoList = BussReduceShareInfo.getAllRows();
        }
        //认购信息
        if(BussSubscriptionInfo){
            object.bussSubscriptionInfoDto = {};
            object.bussSubscriptionInfoDto.finSubscriptionInfoList = BussSubscriptionInfo.getAllRows();
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
        }
        //增信信息
        if(BussFinProductCredit){
            object.bussFinProductCreditDto = {};
            object.bussFinProductCreditDto.finProductCreditList = BussFinProductCredit.getAllRows();
        }
        object.calRateType = $('#finInterestFactorDto_calRateType').val();
        //权益类中债报送
         object.bussRepEquityInfoDto.repAssetType = repAssetType;
        //信贷及非标资产报送信息
        object.bussRepAssetCreditNonstdInfoDto.repAssetType = repAssetType;
        //非标资产(票据或信用)报送信息
        object.bussRepAssetAstBillInfoDto.repAssetType = repAssetType;
        //其他资产报送信息
        object.bussRepOtherInvestTargetInfoDto.repAssetType = repAssetType;
        //债券报送信息
        object.bussFinBondRepInfoDto.finprodType2 = FINPROD_TYPE_TG_F18;
        object.bussFinBondRepInfoDto.repAssetType = repAssetType;
        //基金报送信息
        object.bussRepFundInfoDto.finprodType2 = FINPROD_TYPE_TG_F18;
        object.bussRepFundInfoDto.repAssetType = repAssetType;
        //计划类报送信息
        object.bussRepAssetMngProdInfo.finprodType2 = FINPROD_TYPE_TG_F18;
        object.bussRepAssetMngProdInfo.repAssetType = repAssetType;
        //是否需要验证判断
        object.bussRepAssetMngProdInfo.needValid = repAssetType=="1702";
        
        //sx个性化 草稿箱bug修复 start
        if (draftoption && object.bussRepAssetMngProdInfo){
        	object.bussRepAssetMngProdInfo.needValid = true;
        }
        //sx个性化 草稿箱bug修复 end
        
        //CMS附件信息
        //批复文件
        /*if(FinProjectTargetAssetCms){
            object.fileArrayCms1 = FinProjectTargetAssetCms.getUploadFileInfo('fileUploader1');
        }
        //CMS批复文件
        if(FinProjectTargetAssetCms){
            object.fileArrayCms2 = FinProjectTargetAssetCms.getUploadFileInfo('fileUploader2');
        }*/
        //评级信息
        object.busiFinBondRatingInfoDto = {};
        if(BusiFinProjectRatingInfo){
            object.busiFinBondRatingInfoDto.mstBondCreditRatingList = BusiFinProjectRatingInfo.getAllRows();
        }
        
        var label = $("#financier").siblings("span");
        if(label.css('display')=="none"){
        	object.financierIsHid = true;
        }
        
        Joyin.ajaxJson(basePath + 'invest/asset/project/FinProjectTargetAsset002/check', object, function(
                    result) {
        	if(FinPayInterestStdInfo &&FinPayInterestStdInfo.check()&& FinPayInterestStdInfo.comfirmCheck()&&BussFinRepayment.check()&&BussFinRepayment.isComfirmCheck()
        	  && FinPayInterestStdInfo.saveCheck()
        	  ) {
                Joyin.ajaxJson(basePath + 'invest/asset/common/isHoliday', {
                    mdate: object.mdate,
                    calendarId: object.calendarId
                }, function (isHoliday) {
                    if (isHoliday) {
                        Joyin.confirm("到期日为节假日，是否确认提交", function () {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/asset/project/FinProjectTargetAsset002/save',
                                    object, "FinProjectTargetAsset002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath + 'invest/asset/project/FinProjectTargetAsset002/save',
                                    object, "FinProjectTargetAsset002_btn_submit");
                            }
                        })
                    } else {
                        Joyin.confirm(result.data, function () {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'invest/asset/project/FinProjectTargetAsset002/save',
                                    object, "FinProjectTargetAsset002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath + 'invest/asset/project/FinProjectTargetAsset002/save',
                                    object, "FinProjectTargetAsset002_btn_submit");
                            }
                        });
                    }
                })
            }
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
     * 中债报送show/hide
     */
    var showReportInfo = function(){
        /*var type6 = $('#type6').val();
        var type7 = $('#type7').val();*/
        //add by zhangnian 20191008 start
        /*var type12 = $('#type12').val();*/
        //add by zhangnian 20191008 end
        var repAssetType = $('#repAssetType').val();
        var type9 = $('#type9').val();
        $('#bussRepOtherInvestTargetInfoDto_isUnlisted_div').hide();
        //权益类中债报送
        $('#bussRepEquityInfoDto_div').hide();
        //信贷及非标资产报送信息 
        $('#bussRepAssetCreditNonstdInfoDto_div').hide();
        //非标资产(票据或信用)报送信息 
        $('#bussRepAssetAstBillInfoDto_div').hide();
        //其他资产报送信息
        $('#bussRepOtherInvestTargetInfoDto_div').hide();
        //债券报送信息
        $('#bussFinBondRepInfoDto_div').hide();
        //基金
        $('#bussRepFundInfoDto_div').hide();
        //计划类
        $("#bussRepAssetMngProdInfo_div").hide();
        //中债新增 20200409 start
        //是否为债转股
        $('#bussRepEquityInfoDto_repDeptEquity_div').hide();
        //股权退出安排
        $('#bussRepEquityInfoDto_repEquityQuit_div').hide();
        //是否由金融资产投资公司发行
        $('#bussRepFundInfoDto_project_div').hide();
        //是否为债转股
        $('#bussRepOtherInvestTargetInfoDto_repDeptEquity_div').hide();
        //中债新增 20200409 end
        /*if(type6==ASSET_TYPE_PROJECT_10||type6==ASSET_TYPE_PROJECT_03||type6==ASSET_TYPE_PROJECT_07
                ||type6==ASSET_TYPE_PROJECT_09){
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetType_div').show();
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetTyprExplain_div').show();
            $('#bussRepOtherInvestTargetInfoDto_div').show();
        }else if(type6==ASSET_TYPE_PROJECT_06||type6==ASSET_TYPE_PROJECT_04){
            //基金中债报送
            $('#bussRepFundInfoDto_div').show();
        }else if(type6==ASSET_TYPE_PROJECT_01){
            if(type7==ASSET_TYPE_SECOND_LEVEL_1299){
                //行内资产类别、行内资产类别说明
                $('#bussRepOtherInvestTargetInfoDto_repBankAssetType_div').show();
                $('#bussRepOtherInvestTargetInfoDto_repBankAssetTyprExplain_div').show();
                $('#bussRepOtherInvestTargetInfoDto_div').show();
            }else if(type7==ASSET_TYPE_SECOND_LEVEL_1201||type7==ASSET_TYPE_SECOND_LEVEL_1210){
                $('#bussRepAssetAstBillInfoDto_div').show();
            }else{
                $('#bussRepAssetCreditNonstdInfoDto_div').show();
            }
        }else if(type6==ASSET_TYPE_PROJECT_02||type6==ASSET_TYPE_PROJECT_05){
            $('#bussRepEquityInfoDto_div').show();
        }else if(type6==ASSET_TYPE_PROJECT_11){
            $('#bussFinBondRepInfoDto_div').show();
        }else if(type6==ASSET_TYPE_PROJECT_08){
            $('#bussRepAssetCreditNonstdInfoDto_div').show();
        }*/
        if(repAssetType=="1399"){
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetType_div').show();
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetTyprExplain_div').show();
            $('#bussRepOtherInvestTargetInfoDto_div').show();
            $('#bussRepOtherInvestTargetInfoDto_isUnlisted_div').show();
            //中债新增 20200409 start
            //是否为债转股
            $('#bussRepOtherInvestTargetInfoDto_repDeptEquity_div').show();            
            //中债新增 20200409 end
            $('#bussRepOtherInvestTargetInfoDto_repFinancier').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repFcrGradeOut').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repFinancierType1').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repFinancierType2').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repFinancierType3').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repFinanceProj').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repIndustry1').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repIndustryLevel2').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repProIndustry').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repProIndustryLevel2').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repIndustryFlag').attr('disabled',false).trigger('change');
            $('#bussRepOtherInvestTargetInfoDto_repContainExpectedYield2').attr('disabled',false).trigger('change');
        }else if(repAssetType=="1301"){
            $('#bussRepEquityInfoDto_div').show();
            //中债新增 20200409 start
            //是否为债转股
            $('#bussRepEquityInfoDto_repDeptEquity_div').show();
            //股权退出安排
            $('#bussRepEquityInfoDto_repEquityQuit_div').show();
            //中债新增 20200409 end
        }else if(repAssetType=="1299"||repAssetType=="2299"){
            //行内资产类别、行内资产类别说明
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetType_div').show();
            $('#bussRepOtherInvestTargetInfoDto_repBankAssetTyprExplain_div').show();
            $('#bussRepOtherInvestTargetInfoDto_isUnlisted_div').hide();
            $('#bussRepOtherInvestTargetInfoDto_div').show();   
            $('#bussRepOtherInvestTargetInfoDto_repFinancier').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFinancier').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repFcrGradeOut').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFcrGradeOut').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType1').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType1').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType2').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType2').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType3').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFinancierType3').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repFinanceProj').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repFinanceProj').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repIndustry1').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repIndustry1').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repIndustryLevel2').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repIndustryLevel2').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repProIndustry').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repProIndustry').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repProIndustryLevel2').val('').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repProIndustryLevel2').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repIndustryFlag').val('N').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repIndustryFlag').attr('disabled',true).trigger('change');
		    $('#bussRepOtherInvestTargetInfoDto_repContainExpectedYield2').val('N').trigger('change'); 
		    $('#bussRepOtherInvestTargetInfoDto_repContainExpectedYield2').attr('disabled',true).trigger('change');
        }else if(repAssetType=="1202"||repAssetType=="1203"||repAssetType=="1204"||repAssetType=="1205"||repAssetType=="2202"
            ||repAssetType=="1206"||repAssetType=="1207"||repAssetType=="1208"||repAssetType=="1209"||repAssetType=="2201"){
            $('#bussRepAssetCreditNonstdInfoDto_div').show();  
           /* if(repAssetType=="2201"||repAssetType=="2202"){
            	$('#bussRepAssetCreditNonstdInfoDto_repIsResale').attr('disabled',false).trigger('change');
            }else{            	
            	$('#bussRepAssetCreditNonstdInfoDto_repIsResale').val('N').trigger('change'); 
            	$('#bussRepAssetCreditNonstdInfoDto_repIsResale').attr('disabled',true).trigger('change');
            }*/
            // 中债新增 20200408 信贷资产流转项目，法定到期日置灰不可填 start
            $('#bussRepAssetCreditNonstdInfoDto_repFmdate').attr('disabled',false).trigger('change');
            //含权类型 选填
            $('#bussRepAssetCreditNonstdInfoDto_repContainType').attr('disabled',false).trigger('change');
		    //行权方式 选填
		    $('#bussRepAssetCreditNonstdInfoDto_repExerciseType').attr('disabled',false).trigger('change');
		    //份额面值 必填
		    $('#bussRepAssetCreditNonstdInfoDto_repPrinBaseamt').attr('disabled',false).trigger('change');
		    //结构档次  选填
		    $('#bussRepAssetCreditNonstdInfoDto_repStructGrade').attr('disabled',false).trigger('change');
		    //还本方式  选填
		    $('#bussRepAssetCreditNonstdInfoDto_repRepaymentType').attr('disabled',false).trigger('change');
		    //超额收益分配比例  选填
		    $('#bussRepAssetCreditNonstdInfoDto_repExcessProfitRate').attr('disabled',false).trigger('change');
		    //永续条款类型  必填
		    $('#bussRepAssetCreditNonstdInfoDto_repPerpetualClauseType').attr('disabled',false).trigger('change');
		    //利息递延条款类型  必填
		    $('#bussRepAssetCreditNonstdInfoDto_repDeferredInterestType').attr('disabled',false).trigger('change');
		    //部分赎回标识  必填
		    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemFlag').attr('disabled',false).trigger('change');
		    //部分赎回比例%
		    if($('#bussRepAssetCreditNonstdInfoDto_repPartRedemFlag').val() == "02"){
		    	$('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').attr('disabled',true).trigger('change');
		    }
		    //[资产/负债类别]为“收/受益权[1205]”以外时,收/受益权类型不可填
		    if(repAssetType != "1205"){
		    	$('#bussRepAssetCreditNonstdInfoDto_repProfitType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repProfitType').attr('disabled',true).trigger('change');
		    }else{
		    	$('#bussRepAssetCreditNonstdInfoDto_repProfitType').attr('disabled',false).trigger('change');
		    }
		    //[资产/负债类别]为“收/受益权[1205]”以外时,收/受益权类型不可填
            if(repAssetType=="2201" || repAssetType=="2202"){
	            $('#bussRepAssetCreditNonstdInfoDto_repFmdate').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repFmdate').attr('disabled',true).trigger('change');
			    //含权类型  置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repContainType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repContainType').attr('disabled',true).trigger('change');
			    //行权方式 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repExerciseType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repExerciseType').attr('disabled',true).trigger('change');
			    //份额面值 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repPrinBaseamt').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPrinBaseamt').attr('disabled',true).trigger('change');
			    //结构档次 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repStructGrade').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repStructGrade').attr('disabled',true).trigger('change');
			    //还本方式 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repRepaymentType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repRepaymentType').attr('disabled',true).trigger('change');
			    //超额收益分配比例 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repExcessProfitRate').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repExcessProfitRate').attr('disabled',true).trigger('change');
			    //永续条款类型 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repPerpetualClauseType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPerpetualClauseType').attr('disabled',true).trigger('change');
			    //利息递延条款类型 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repDeferredInterestType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repDeferredInterestType').attr('disabled',true).trigger('change');
			    //部分赎回标识 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemFlag').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemFlag').attr('disabled',true).trigger('change');
			    //部分赎回比例% 置灰不可填
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').attr('disabled',true).trigger('change');
			    
            }
            //新增可投资资产时，部分赎回比例%不可填
            if(type9=="08"){
	            $('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repPartRedemRate').attr('disabled',true).trigger('change');
	            $('#bussRepAssetCreditNonstdInfoDto_repBasicAssetType').val('').trigger('change'); 
			    $('#bussRepAssetCreditNonstdInfoDto_repBasicAssetType').attr('disabled',true).trigger('change');
            }
            // 中债新增 20200408 end
        }else if(repAssetType=="1201"||repAssetType=="1210"){
            $('#bussRepAssetAstBillInfoDto_div').show();
        }else if(repAssetType=="2612"||repAssetType=="2401"||repAssetType=="2617"){
            //基金中债报送
            $('#bussRepFundInfoDto_div').show();
            //中债新增 20200409 start
            //是否由金融资产投资公司发行
            if(repAssetType=="2617"){
            	$('#bussRepFundInfoDto_project_div').show();
            }
            //中债新增 20200409 end
        }else if(repAssetType=="2101"){
            //债券中债报送
            $('#bussFinBondRepInfoDto_div').show();
        }else if(repAssetType=="1702"){
            //计划类中债报送
            $('#bussRepAssetMngProdInfo_div').show();
        }
    }
    
    /**
     * 计息要素画面显示/隐藏
     */
    var showFinInterestFactorDiv = function(){
        var obj = $('#finInterestFactorDto_div');
        getInterestFactorElementById('rate','div').hide();
        getInterestFactorElementById('couponSpecies','div').hide();
        getInterestFactorElementById('basisStyle','div').hide();
        getInterestFactorElementById('referRate','div').hide();
        getInterestFactorElementById('buyOutRate','div').hide();
    }
    
    /**
     * 投后信息显示/隐藏
     */
    var showPostInvestInfoDiv = function(){
        var type6 = $('#type6').val();
        if(type6==""){
            $('#bussPostInvestInfoDto_div').hide();
        }else{
            $('#bussPostInvestInfoDto_div').show();
            getBussPostInvestInfoElementById('div').find('div:not(.layui-unselect)').show();
          //非标债权
            /*if(type6==ASSET_TYPE_PROJECT_01){
                getBussPostInvestInfoElementById('three','div').nextAll().hide();
            }else*/ if(type6==ASSET_TYPE_PROJECT_02){
                //结构化
                getBussPostInvestInfoElementById('five','div').prevAll().hide();
                getBussPostInvestInfoElementById('nine','div').hide();
                getBussPostInvestInfoElementById('ten','div').hide();
                getBussPostInvestInfoElementById('elevn','div').hide();
                getBussPostInvestInfoElementById('twl','div').hide();
                getBussPostInvestInfoElementById('threeteen','div').hide();
                getBussPostInvestInfoElementById('fourteen','div').hide();
                getBussPostInvestInfoElementById('sixteen','div').hide();
                getBussPostInvestInfoElementById('three','div').hide();
            }else if(type6==ASSET_TYPE_PROJECT_05){
                //股票定向增发投资
                getBussPostInvestInfoElementById('six','div').prevAll().hide();
                getBussPostInvestInfoElementById('nine','div').hide();
                getBussPostInvestInfoElementById('ten','div').hide();
                getBussPostInvestInfoElementById('elevn','div').hide();
                getBussPostInvestInfoElementById('fifteen','div').hide();
                getBussPostInvestInfoElementById('sixteen','div').hide();
                getBussPostInvestInfoElementById('three','div').hide();
            }/*else if(type6==ASSET_TYPE_PROJECT_07||type6==ASSET_TYPE_PROJECT_09){
                //券商债权收益权投资,券商收益凭证
                getBussPostInvestInfoElementById('four','div').siblings().hide();
                getBussPostInvestInfoElementById('sixteen','div').show();
            }else if(type6==ASSET_TYPE_PROJECT_10){
                //股票质押式回购
                //差不方名称
                getBussPostInvestInfoElementById('diffrentPartyName','div').hide();
                getBussPostInvestInfoElementById('seven','div').prevAll().hide();
                getBussPostInvestInfoElementById('threeteen','div').hide();
                getBussPostInvestInfoElementById('fourteen','div').hide();
                getBussPostInvestInfoElementById('fifteen','div').hide();
                getBussPostInvestInfoElementById('sixteen','div').hide();
            }*/
            else{
                getBussPostInvestInfoElementById('one','div').hide();
                getBussPostInvestInfoElementById('two','div').hide();
                getBussPostInvestInfoElementById('three','div').hide();
                getBussPostInvestInfoElementById('four','div').hide();
                getBussPostInvestInfoElementById('five','div').hide();
                getBussPostInvestInfoElementById('six','div').hide();
                getBussPostInvestInfoElementById('seven','div').hide();
                getBussPostInvestInfoElementById('eight','div').hide();
                getBussPostInvestInfoElementById('nine','div').hide();
                getBussPostInvestInfoElementById('ten','div').hide();
                getBussPostInvestInfoElementById('elevn','div').hide();
                getBussPostInvestInfoElementById('twl','div').hide();
                getBussPostInvestInfoElementById('threeteen','div').hide();
                getBussPostInvestInfoElementById('fourteen','div').hide();
                getBussPostInvestInfoElementById('fifteen','div').hide();
                getBussPostInvestInfoElementById('sixteen','div').hide();
            }
        }
    }
    
    /**
     * 参与方信息显示/隐藏
     */
    
    var showBussParticipantInfo = function(){
        // modify by renping 通道什么时候都显示 2019-08-19 start
//        var type6 = $('#type6').val();
//        if(type6==""||type6==ASSET_TYPE_PROJECT_11){
//            $('#bussParticipantInfoDto_div').hide();
//        }else{
            $('#bussParticipantInfoDto_div').show();
//        }
        // modify by renping 通道什么时候都显示 2019-08-19 end
    }
    /**
     * 减持信息显示/隐藏
     */
    var showBussReduceShareInfo = function(){
        var type6 = $('#type6').val();
        $('#bussReduceShareInfoDto_div').hide();
        if(type6 == ASSET_TYPE_PROJECT_05){
            $('#bussReduceShareInfoDto_div').show();
        }
    }
    
    /**
     * 认购信息显示/隐藏
     */
    var showBussSubscriptionInfo = function(){
        var type6 = $('#type6').val();
        $('#bussSubscriptionInfoDto_div').hide();
        if(type6 == ASSET_TYPE_PROJECT_11){
            $('#bussSubscriptionInfoDto_div').show();
        }
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
        	$('#projInvestIndustry2Div').show();
        }
        else{
        	$('#projInvestIndustryDiv').hide();
        	$('#projInvestIndustry2Div').hide();
        }
        if($('#type6').val()==ASSET_TYPE_PROJECT_02
            ||$('#type6').val()==ASSET_TYPE_PROJECT_03
            ||$('#type6').val()==ASSET_TYPE_PROJECT_05){
            $("#investNature").val(INVEST_02).trigger('change');
        }
        else if( !$('#type6').val()  && $('#type6').val()!=null && $('#type6').val()!='' && typeof $('#type6').val() !='undefined' ){
            
        	$("#investNature").val(INVEST_01).trigger('change');
        }
        showPostInvestInfoDiv();
        /*showReportInfo();*/
        showBussParticipantInfo();
        showBussReduceShareInfo();
        showBussSubscriptionInfo();
    }
    
    /**
     * type7 change事件
     */
    var type7Change = function(){
        showPostInvestInfoDiv();
        /*showReportInfo();*/
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
            Joyin.ajax(basePath + 'invest/asset/project/FinProjectTargetAsset002/init', {
                'finprodMarketId': $('#finprodMarketId').val()
                ,'isQuickInput':true
            }, function (data) {
                // update by caiqun 20200721 资产代码为本机构不存在的，快录时不直接删除填的的资产，做成提醒；  start
                if($(data).find('#finprodMarketId').val()!=""){
                    $("body").html(data);
                }else{
                    Joyin.error("资产代码不存在！");
                }
                // update by caiqun 20200721 资产代码为本机构不存在的，快录时不直接删除填的的资产，做成提醒；  end
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
            // 增加推荐方与推荐费率 delete by renping 20191014 start
//            if(typeof FinInterestFactor !== 'undefined') {
//            	//买断利率change
//            	$('#buyOutRate').change(FinInterestFactor.calreferRate).trigger("change");
//            }
            // 增加推荐方与推荐费率 delete by renping 20191014 end
            if(AssetCommon) {
            	//借款利率change
            	$('#finInterestFactorDto_loadRate').change(AssetCommon.loadRateChange).trigger('change');
            }
            //基金中债报送  资产负债类别、投资阶段非置灰 
            $('#bussRepFundInfoDto_repAssetType').prop('disabled',false).trigger('change');
            $('#bussRepFundInfoDto_repInvestStage').prop('disabled',false).trigger('change');
            if(typeof FinInterestFactor !== 'undefined'){
                getInterestFactorElementById('rateType').trigger("change");
            }
            if ($('#finprodMarketId').prop('disabled')) {
                $("#magnifier").css({color: '#dbdbdb'});
            }
            //该资产存在交易
            if(isTransInAsset){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#tradeMarket').attr('disabled',true).trigger('change');
                $('#finInterestFactorDto_loadRate').attr('disabled',true).trigger('change');
                disablePageElement($("#bussParticipantInfoTable").parent());
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
                //置灰还本计划生成计划按钮
                /*$('#bussFinRepaymentDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
            //update by  zhangnian 20191120  start
            // 增加推荐方与推荐费率 modify by renping 20191014 start
            $('#finInterestFactorDto_loadRate').change(function () {
                FinInterestFactor.calBuyOutRateByTable('loadRate')
            });
            $('#finInterestFactorDto_investRate').change(FinInterestFactor.calBuyOutRateByTable).trigger('change');
            // 增加推荐方与推荐费率 modify by renping 20191014 end
            //update by  zhangnian 20191120  start
            //【计息类型】默认规则 20200409
	        getInterestFactorElementById('rateType').change(function(){
	            repCouponSpecies();
	        });
            repCouponSpecies();
	        getPayInterestStdInfoElementById('payCycle').change(function(){
	            repCouponSpecies();
	        });
	        $("#bussFinRepaymentDto_payCycle").change(function(){
	            repCouponSpecies();
                byPayCycle();
	        });
            byPayCycle();
	        //【利息分布方式】默认规则 20200409
	        getInterestFactorElementById('basis').change(function(){
                byBasis();
	        });
            byBasis();
        }
        if(objBuyOutRate!=null&&objBuyOutRate!=''){
            $('#buyOutRate').val(objBuyOutRate).trigger('change');
        }
    }
    var init = function(){
    	if($('#type6').val()==ASSET_TYPE_PROJECT_01
        		||$('#type6').val()==ASSET_TYPE_PROJECT_02
        		||$('#type6').val()==ASSET_TYPE_PROJECT_10
        		||$('#type6').val()==ASSET_TYPE_PROJECT_05){
        	$('#projInvestIndustryDiv').show();
        	$('#projInvestIndustry2Div').show();
        }
        else{
        	$('#projInvestIndustryDiv').hide();
        	$('#projInvestIndustry2Div').hide();
        }

    	//如果授信模式=='02'，则显示批复号和实际授信主体
    	if(CE_MODEL == '02'){
    		$('#actualCreditProvidDiv').show();
    		$('#pflshDiv').show();
    	}
    	else{
    		$('#actualCreditProvidDiv').hide();
    		$('#pflshDiv').hide();
    	}
    	var hapCreditAmt = $("#hapCreditAmt").val();
    	if(hapCreditAmt && hapCreditAmt>0){
    		$("#pflsh").attr("disabled",true).trigger('change');
    	}
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
        $("#type9,#type10,#type11,#type12").change(function(){
    	    window.BussAssetFiveType.doAssetFiveTypeChange(IS_SHOW_TYPE,PINAN_ASSET_TYPE_F06,'type9','type10','type11','type12','type6','type7')
    	});
        // 项目类资产五级分类改造 add by renping 2019-07-25 end
    }
    // 基本页融资人带出中债tab页融资人名称 add by zhuyongkang 20191212  start
    function financierChange() {
        var optionData = $("#financier").find("option:selected").data("data");
        if (optionData) {
            $("#bussRepAssetCreditNonstdInfoDto_repFinancier").val(optionData.text);
            $("#bussRepOtherInvestTargetInfoDto_repFinancier").val(optionData.text);
        }
    }
    /**
     * 【计息类型】默认规则 利率类型、付息频率、还款周期add by lwj 20200409
     * 利率类型 getInterestFactorElementById('rateType').val()
     * 付息频率 getPayInterestStdInfoElementById('payCycle').val() FREQUENCY_UNIT_07
     * 还款周期 $("#bussFinRepaymentDto_payCycle").val()
     */
    var repCouponSpecies = function(){
        if(getInterestFactorElementById('rateType').val()==INT_TYPE_01 && 
        	getPayInterestStdInfoElementById('payCycle').val() == FREQUENCY_UNIT_07 &&
        	$("#bussFinRepaymentDto_payCycle").val() == FREQUENCY_UNIT_07){
        		$("#bussRepAssetCreditNonstdInfoDto_repCouponSpecies").val(SVR_COUPON_SPECIES_02).attr('disabled',true).trigger('change');
        }else if(getInterestFactorElementById('rateType').val()==INT_TYPE_02 && 
        	getPayInterestStdInfoElementById('payCycle').val() == FREQUENCY_UNIT_07 &&
        	$("#bussFinRepaymentDto_payCycle").val() == FREQUENCY_UNIT_07){
            	$("#bussRepAssetCreditNonstdInfoDto_repCouponSpecies").val(SVR_COUPON_SPECIES_03).attr('disabled',true).trigger('change');
        }else if(getInterestFactorElementById('rateType').val()==INT_TYPE_01){
            $("#bussRepAssetCreditNonstdInfoDto_repCouponSpecies").val(SVR_COUPON_SPECIES_04).attr('disabled',true).trigger('change');
        }else if(getInterestFactorElementById('rateType').val()==INT_TYPE_02){
        	$("#bussRepAssetCreditNonstdInfoDto_repCouponSpecies").val(SVR_COUPON_SPECIES_05).attr('disabled',true).trigger('change');
        }else{
        	$("#bussRepAssetCreditNonstdInfoDto_repCouponSpecies").val('').attr('disabled',true).trigger('change');
        }
    }

    //【还本方式】、【分期还本条款标识】默认规则 20200409
    var byPayCycle = function(){
        //【还本方式】、【分期还本条款标识】默认规则 20200409
        if($("#bussFinRepaymentDto_payCycle").val()==FREQUENCY_UNIT_16){
            $("#bussRepAssetCreditNonstdInfoDto_repRepaymentType").val(SVR_REPAYMENT_TYPE_01).attr('disabled',true).trigger('change');
            $("#bussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val(SVR_PERIOD_REPAYMENT_FLAG_99).attr('disabled',true).trigger('change');
        }else if($("#bussFinRepaymentDto_payCycle").val()==FREQUENCY_UNIT_07){
            $("#bussRepAssetCreditNonstdInfoDto_repRepaymentType").val(SVR_REPAYMENT_TYPE_02).attr('disabled',true).trigger('change');
            $("#bussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val(SVR_PERIOD_REPAYMENT_FLAG_02).attr('disabled',true).trigger('change');
        }else if($("#bussFinRepaymentDto_payCycle").val()!=""){
            $("#bussRepAssetCreditNonstdInfoDto_repRepaymentType").val(SVR_REPAYMENT_TYPE_02).attr('disabled',true).trigger('change');
            $("#bussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val(SVR_PERIOD_REPAYMENT_FLAG_01).attr('disabled',true).trigger('change');
        }else{
            $("#bussRepAssetCreditNonstdInfoDto_repRepaymentType").val(SVR_REPAYMENT_TYPE_02).attr('disabled',true).trigger('change');
            $("#bussRepAssetCreditNonstdInfoDto_repPeriodRepaymentFlag").val('').attr('disabled',true).trigger('change');

        }
    }
    //【利息分布方式】默认规则 20200409
    var byBasis = function() {
        if (getInterestFactorElementById('basis').val() == BASIS_A_360 ||
            getInterestFactorElementById('basis').val() == BASIS_A_365) {
            $("#bussRepAssetCreditNonstdInfoDto_repIntpayRule").val(SVR_INTPAY_RULE_01).attr('disabled', true).trigger('change');
        } else if (getInterestFactorElementById('basis').val() == BASIS_A_A_B_DAY ||
            getInterestFactorElementById('basis').val() == BASIS_A_A_D) {
            $("#bussRepAssetCreditNonstdInfoDto_repIntpayRule").val(SVR_INTPAY_RULE_02).attr('disabled', true).trigger('change');
        } else {
            $("#bussRepAssetCreditNonstdInfoDto_repIntpayRule").val('').attr('disabled', true).trigger('change');
        }
    }
    // 基本页融资人带出中债tab页融资人名称 add by zhuyongkang 20191212  end
    $(function() {
    	init();
        //除新建画面 ，资产类型置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('#type6').attr('disabled',true).trigger('change');
            $('#finprodMarketId').attr('disabled', true).trigger('change');
            $('.btn-save').hide();
            $('#buyOutRate').attr('disabled',true).trigger('change');
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

        bindEvent();
        $('#mdate,#vdate').change(function(obj){
            obj.type = FINPROD_TYPE_TG_F18;
            AssetCommon.checkDate(obj);
        })
      //是否展示资产四五级分类
        /*if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        	$('#type12Div').show();
        }else{
        	$('#type11Div').hide();
        	$('#type12Div').hide();
        }*/
        //负债类别change事件
        $('#repAssetType').change(function(){
            showReportInfo();
        });
        //增加放大镜
        $('#magnifier').click(function() {
            getInterfFundDto();
        });
        $("#financier").change(financierChange)
    });
});
