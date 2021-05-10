$(function() {
    //当前是否包含流程
	var workflowIdNow = $("#workflowId").val();

    var getSearchParams = function() {
        return {
            //当前是否包含流程
             workflowId: $("#workflowId").val(),
             finprodMarketId:$('#finprodMarketId').val(),
             finprodId:$('#finprodId').val(),
             vdate:$('#vdate').val(),
             assetType:$('#assetType').val(),
             finInterestFactorDto_loadRate:$('#finInterestFactorDto_loadRate').val(),
//             table1data:JSON.stringify(table1.getAllRows()),
             OPT_TYPE:OPT_TYPE
        }
    };
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


      //获取付息信息元素id
        var getPayInterestStdInfoElementById = function(id,type) {
            if(type=="div"){
                return $('#' + 'finPayInterestStdInfoDto' + '_' + id+'_'+'div');
            }else{
                return $('#' + 'finPayInterestStdInfoDto' + '_' + id);
            }
        };
    
    function saveOrSubmit(operateType) {
        if($('#mdate').val()&&typeof $('#mdate').val() != 'undefined'
            &&iPlatformDate && typeof iPlatformDate != 'undefined'){
            if(Date.ofDate($('#mdate').val()).DateDiff('d',iPlatformDate)>0){
                Joyin.error("到期日不能小于平台日");
                return;
            }
        }
        var assetType = $('#assetType').val();
        var object = $.serializeToObject('#submitForm',true);
        object.issuer=$('#orgClass').val();
        object.operateType = operateType;
        object.pageModel = pageModel;
//        object.table1 = table1.getAllRows();
        if(isQuickInput&&pageModel == PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        //计息要素
        /*object.finInterestFactorDto.investRate = "";*/

        //支付计划
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();
            object.finPayInterestStdInfoDto.type6 = assetType;
            object.finPayInterestStdInfoDto.payMonth = $("#finPayInterestStdInfoDto_payMonth").val() ? $('#finPayInterestStdInfoDto_payMonth').attr('multiple')?$("#finPayInterestStdInfoDto_payMonth").val().join(','):$("#finPayInterestStdInfoDto_payMonth").val() : '';
            object.finPayInterestStdInfoDto.dayValue = $("#finPayInterestStdInfoDto_dayValue").val() ? $("#finPayInterestStdInfoDto_dayValue").val().join(',') : '';
        }
        /*Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {*/
            if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
                Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                    handTip(object,operateType);
                    });
            }else{
                handTip(object,operateType);
            }
       /* });*/
    };
    
    /**
     * check提示
     */
    var handTip = function(object,operateType){
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget002/check', object, function(result) {
            if($('#assetType').val()==DEPO_ASSET_TYPE_02){
                if(object.mdate&&null!=object.mdate){
                    Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/ValAssetCommon/isHoliday', {
                        mdate: object.mdate,
                        calendarId: object.calendarId
                    }, function (isHoliday) {
                        if (isHoliday) {
                            Joyin.confirm("到期日为节假日，是否确认提交", function () {
                                finalSava(object,operateType);
                            })
                        }
                        else {
                            Joyin.confirm(result.data, function () {
                                finalSava(object,operateType);
                            });
                        }
                    })
                }
                else {
                        Joyin.confirm(result.data, function () {
                            finalSava(object,operateType);
                        });
                    }
            }
            else{
                if(FinPayInterestStdInfo &&FinPayInterestStdInfo.check()&& FinPayInterestStdInfo.comfirmCheck()) {
                    Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/ValAssetCommon/isHoliday', {
                        mdate: object.mdate,
                        calendarId: object.calendarId
                    }, function (isHoliday) {
                        if (isHoliday) {
                            Joyin.confirm("到期日为节假日，是否确认提交", function () {
                                finalSava(object,operateType);
                            })
                        } else {
                            Joyin.confirm(result.data, function () {
                                finalSava(object,operateType);
                            });
                        }
                    })
                }
            }
        })
    }

    /**
     * 最终提示
     * @param object
     * @param operateType
     */
    var finalSava = function(object,operateType){
        if (operateType === 'save') {
            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget002/save', object, "FinDepositTarget002_btn_save");
        } else {
            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget002/save', object, "FinDepositTarget002_btn_submit");
        }
    }
    /**
     * 计算期限==起息日与到期日之间的自然天数，不含到期日
     */
    var calTermDays = function(){
        var objDiv= $("#FinDepositTarget002_DIV");
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
     * 改变核算分类
     */
    var changeAcccountintType = function() {
        var objDiv= $("#FinDepositTarget002_DIV");
        var orgClass =  $('#orgClass').val();
        //var blnArea =  $('#blnArea').val();
        var assetType =  $('#assetType').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget002/getFinanceProductClass',
            {orgClass:orgClass,assetType,assetType}, function(data){
            if(data) {
             objDiv.find("input[name='accountingClassName']").val(data.accountingClassName).trigger("change");
             $('#accountintType').val(data.accountingClass).trigger("change");
            }
            else {
                objDiv.find("input[name='accountingClassName']").val('').trigger("change");
                 $('#accountintType').val('').trigger("change");
            }
        });
    }

    /**
     * 改变税务分类
     */
    var changeTaxType = function() {
        var orgClass =  $('#orgClass').val();
        //var blnArea =  $('#blnArea').val();
        var assetType =  $('#assetType').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget002/getFinanceProductTaxClass',
            {orgClass:orgClass,assetType:assetType}, function(data){
                if(data && data.taxType) {
                    $("#taxType").textSelect('setValue',data.taxType);
                }
                else {
                    $("#taxType").textSelect('setValue',"");
                }
            });
    }

     /**
     * 资产分类改变触发
     */
    var assetTypeChange = function(){
        //如果为活期存放，隐藏期限
        if($('#assetType').val() == DEPO_ASSET_TYPE_02 ){
            $('#termDays_div').hide();
            $('#finPayInterestStdInfoDto_div').hide();
        }else{
            $('#termDays_div').show();
            $('#finPayInterestStdInfoDto_div').show();
        }
    }

    /**
     * 计息要素画面显示/隐藏
     */
    var showFinInterestFactorDiv = function(){
        var obj = $('#finInterestFactorDto_div');
        /*getInterestFactorElementById('two','div').hide();*/
        getInterestFactorElementById('basisStyle','div').hide();
        getInterestFactorElementById('rate','div').hide();
        getInterestFactorElementById('rateMax','div').hide();
        getInterestFactorElementById('basisStyle','div').hide();
        getInterestFactorElementById('three','div').hide();
        getInterestFactorElementById('four','div').hide();
        getInterestFactorElementById('investRate','div').hide();
         $('#calRateType_div').hide();
//        if(isCalLoadrate=='01'){
//            getInterestFactorElementById('investRate','div').hide();
//            $('#calRateType_div').hide();
//        }
        getInterestFactorElementById('couponSpecies','div').hide();
        if(OPT_TYPE =='new'){
            $('#finInterestFactorDto_basis').textSelect('setValue',false);
        }
    }

     /**
     * 付息信息画面显示/隐藏
     */
    var showFinPayInterestStdInfoDiv = function(){
        var obj = $('#finPayInterestStdInfoDto_div');
        getPayInterestStdInfoElementById('endTdayRule','div').hide();
        if(OPT_TYPE =='new'){
            $('#finPayInterestStdInfoDto_payTdayRule').textSelect('setValue',false);
            $('#finPayInterestStdInfoDto_payCycle').textSelect('setValue',false);
            $('#finPayInterestStdInfoDto_calcRule').textSelect('setValue',false);
        }

    }


    //快捷录入
    //add by  zhangnian  20191015 start
    var getInterfFundDto = function(){
        if ($('#finprodMarketId').prop('disabled')) {
            return;
        }
        if (finprodMarketId) {
            Joyin.ajax(basePath + 'invest/asset/depo/FinDepositTarget002/init', {
                'finprodMarketId': $('#finprodMarketId').val()
                ,'isQuickInput':true
            }, function (data) {
                    $("body").html(data);
            });
        }else{
            Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('FinDepositTarget002.finprodId')));
        }
    };


    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
             //付息信息展示/隐藏
            showFinPayInterestStdInfoDiv();
            //计息要素利率类型不可更改
            getInterestFactorElementById('rateType').prop('disabled',true).trigger("change");

             if($('#assetType').val() == DEPO_ASSET_TYPE_02 ){
                  $('#termDays_div').hide();
                  $('#finPayInterestStdInfoDto_div').hide();
                  $('#finPayInterestStdInfoDto_div').attr('disabled',true).trigger('change');
            }else{
                 $('#termDays_div').show();
                 $('#finPayInterestStdInfoDto_div').show();
             }

          //该资产存在交易
            if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG ){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#blnArea').attr('disabled',true).trigger('change');
                $('#finInterestFactorDto_loadRate').attr('disabled',true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
        }
    }
    var init = function(){
        var objDiv= $("#FinDepositTarget002_DIV");
    	star();
    	objDiv.find("input[name='accountingClassName']").attr("disabled",true);
    }
    $(function(){
    	init();
        //除新建画面 ，资产类型置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('#assetType').attr('disabled',true).trigger('change');
            $('#finprodMarketId').attr('disabled', true).trigger('change');
            $('#orgClass').attr('disabled',true).trigger('change');
            $('.btn-save').hide();
            if($('#assetType').val()==DEPO_ASSET_TYPE_03){
                $('#orgClass').attr('required',false).trigger('change');
                $('#blnArea').attr('required',false).trigger('change');
            }
        }

        if($('#assetType').val() == DEPO_ASSET_TYPE_02 ){
              $('#termDays_div').hide();
              $('#finPayInterestStdInfoDto_div').hide();
              $('#finPayInterestStdInfoDto_div').attr('disabled',true).trigger('change');
        }else{
             $('#termDays_div').show();
             $('#finPayInterestStdInfoDto_div').show();
         }
        // 绑定按钮事件
        $('#FinDepositTarget002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinDepositTarget002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $("#FinDepositTarget002_DIV").find("input[name='vdate'],input[name='mdate']").change(calTermDays);
        $("#FinDepositTarget002_DIV").find("input[name='vdate'],input[name='mdate']").trigger("change");
        $('#mdate,#vdate').change(function(obj){
            obj.type = FINPROD_TYPE_TG_F07;
            AssetCommon.checkDate(obj);
        });

        //根据存放机构和境内外联动带出；核算分类、税务分类
        $('#orgClass,#assetType').change(function(){
            changeAcccountintType();
            changeTaxType();
        });

        //资产分类联动
        $('#assetType').change(function(){
            assetTypeChange();
        });

    });

	star();
});
