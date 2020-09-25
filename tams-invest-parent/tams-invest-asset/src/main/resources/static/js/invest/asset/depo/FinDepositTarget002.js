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
    
    function saveOrSubmit(operateType) {
        /*if($('#oldMdate').val() && typeof $('#oldMdate').val() != 'undefined'
            &&iPlatformDate && typeof iPlatformDate != 'undefined'){
            if(Date.ofDate($('#oldMdate').val()).DateDiff('d',iPlatformDate)>0){
                Joyin.error("已过期，不允许修改");
                return;
            }
        }*/
        if($('#mdate').val()&&typeof $('#mdate').val() != 'undefined'
            &&iPlatformDate && typeof iPlatformDate != 'undefined'){
            if(Date.ofDate($('#mdate').val()).DateDiff('d',iPlatformDate)>0){
                Joyin.error("到期日不能小于平台日");
                return;
            }
        }
        var type6 = $('#type6').val();
        var object = $.serializeToObject('#submitForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        if(isQuickInput&&pageModel == PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        //计息要素
        /*object.finInterestFactorDto.investRate = "";*/
        object.bussRepDlRepoInfoDto.type6 = type6;
        object.bussRepDepositInfoDto.type6 = type6;
        object.bussRepDepositInfoDto.repTargetType = $('#bussRepDepositInfoDto_repTargetType').val()?$('#bussRepDepositInfoDto_repTargetType').val().join(","):'';
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
        //支付计划
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();
            object.finPayInterestStdInfoDto.type6 = type6;
            object.finPayInterestStdInfoDto.payMonth = $("#finPayInterestStdInfoDto_payMonth").val() ? $('#finPayInterestStdInfoDto_payMonth').attr('multiple')?$("#finPayInterestStdInfoDto_payMonth").val().join(','):$("#finPayInterestStdInfoDto_payMonth").val() : '';
            object.finPayInterestStdInfoDto.dayValue = $("#finPayInterestStdInfoDto_dayValue").val() ? $("#finPayInterestStdInfoDto_dayValue").val().join(',') : '';
        }
        object.calRateType = $('#finInterestFactorDto_calRateType').val();
        /*Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {*/
        if(isTransInAsset){
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
      Joyin.ajaxJson(basePath + 'invest/asset/depo/FinDepositTarget002/check', object, function(result) {
          if($('#type6').val()==DEPO_ASSET_TYPE_03){
              if(object.mdate&&null!=object.mdate){
                Joyin.ajaxJson(basePath + 'invest/asset/common/isHoliday', {
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
                Joyin.ajaxJson(basePath + 'invest/asset/common/isHoliday', {
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
	 * 
	 * @param object
	 * @param operateType
	 */
	var finalSava = function(object,operateType){
	    if (operateType === 'save') {
	        WorkFlow.doSaveList(basePath + 'invest/asset/depo/FinDepositTarget002/save', object, "FinDepositTarget002_btn_save");
	    } else {
	        WorkFlow.doSubmitList(basePath + 'invest/asset/depo/FinDepositTarget002/save', object, "FinDepositTarget002_btn_submit");
	    }
	    FileCommon.emptyUploadFileInfo();
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
     * 资产类型改变，画面联动
     */
    var typeChange = function(){
        var type6 = $('#type6').val();
        //同业借款
        if(type6==DEPO_ASSET_TYPE_01){
            //存款类型置灰
            $('#type7').val("").trigger('change');
            $('#type7').attr("disabled",true).trigger('change');
            //update by zhangnian 20191015 start
            //$('#'+'finPayInterestStdInfoDto'+'_'+'calcRule').removeAttr("disabled").trigger('change');
            //update by zhangnian 20191015 end
            $('#'+'finPayInterestStdInfoDto'+'_'+'payCycle').removeAttr("disabled").trigger('change');
        }else{
        	if (pageModel != PAGE_MODEL_REFERENCE) {
        		$('#type7').removeAttr("disabled").trigger('change');
        	}
            //update by zhangnian 20191015 start
            //推算方式置灰
            if($('#'+'finPayInterestStdInfoDto'+'_'+'calcRule').val()==""){
                $('#'+'finPayInterestStdInfoDto'+'_'+'calcRule').val(CALC_METHOD_01).trigger('change');
            }
            //$('#'+'finPayInterestStdInfoDto'+'_'+'calcRule').attr("disabled",true).trigger('change');
            //update by zhangnian 20191015 end
            //付息频率
            if($('#'+'finPayInterestStdInfoDto'+'_'+'payCycle')==""){
                $('#'+'finPayInterestStdInfoDto'+'_'+'payCycle').val(INTPAY_CYCLE_01).trigger('change');
            }
            //$('#'+'finPayInterestStdInfoDto'+'_'+'payCycle').attr("disabled",true).trigger('change');
            //首次付息日
            //update by  zhangnian  20191102 start
            /*$('#'+'finPayInterestStdInfoDto'+'_'+'firstPayDate').val('').trigger('change');
            $('#'+'finPayInterestStdInfoDto'+'_'+'firstPayDate').attr("disabled",true).trigger('change');*/
            //update by  zhangnian  20191102 end
        }
        //活期存放
        if(type6==DEPO_ASSET_TYPE_03){
            //显示是否有存单、隐藏到期日、期限
            /*$('#mdate').val("");$('#mdate_div').hide();*/$('#termDays_div').hide();$('#isReceipt_div').show();$('#issueAmt').val("");$("#issueAmt_div").hide();
            $('#finInterestFactorDto_loadRate_div').find('label').html('存/借款利率<i class="must">*</i>');
            //显示付息月、付息日
            $('#'+'finPayInterestStdInfoDto'+'_'+'payMonth_div').show();
            $('#'+'finPayInterestStdInfoDto'+'_'+'dayValue_div').show();
            //update by  yantianyuan  20200721 活期存放 付息日星号提示 start
            if($('#'+'finPayInterestStdInfoDto'+'_'+'dayValue_div').find('label').find('i').length==0){
                $('#'+'finPayInterestStdInfoDto'+'_'+'dayValue_div').find('label').append('<i class="must">*</i>');
            }
            //update by  yantianyuan  20200721 活期存放 付息日星号提示 end
            //隐藏付息计划
            $('#'+'finPayInterestStdInfoDto'+'_'+'btn_generationPlan').hide();
            $('#'+'finPayInterestStdInfoDto'+'_'+'btn_confirm').hide();
            $('#generationPlanTable_div').hide();
        }
        else{
            /*$('#mdate_div').show();*/$('#termDays_div').show();$('#isReceipt_div').hide();$("#issueAmt_div").show()
            $('#finInterestFactorDto_loadRate_div').find('label').html('借款利率<i class="must">*</i>')
            $('#'+'finPayInterestStdInfoDto'+'_'+'payMonth_div').hide();
            $('#'+'finPayInterestStdInfoDto'+'_'+'dayValue_div').hide();
            $('#'+'finPayInterestStdInfoDto'+'_'+'btn_generationPlan').show();
            $('#'+'finPayInterestStdInfoDto'+'_'+'btn_confirm').show();
            $('#generationPlanTable_div').show();
        }
        initAssetTypeOption();
        //是否展示中债报送信息
        showReportInfo();
    }
    
    /**
     * 资产类型联动资产负债类别
     */
    var initAssetTypeOption = function(){
        var childrenIds = $('#type6').attr("childrenIds");
        if (childrenIds) {
            var childrenArray = childrenIds.split(",");
            $.each(childrenArray, function (i, item) {
                    // 联动子项
                    var _val = $('#type6').val();
                    var selected = $('#type6').data('select2') ? $('#type6').select2('data') :$('#type6').data();
                    var _child = $("#" + item);
                    var _results = _child.data("result");
                    var _data = [];
                    var _initValue = [];
                    if(_val && _results) {
                        if(typeof _results === 'string') {
                            _results = JSON.parse(_results);
                        }
                        if(selected) {
                            _val = selected[0].col + '.' + _val;
                        }
                        _.each(_results, function(v) {
                            // 如果数据字典项的parentId未指定，或者指定的parentId中包含当前选中的值
                            var _isVal = v.parentId && (v.parentId === _val || v.parentId.split(',').indexOf(_val) != -1);
                            if(!v.parentId || _isVal) {
                                _data.push(v);
                            }
                        });
                        // 清空原始值
                        var _opt = _child.data('select2').options.options, _oldVal = _child.val();
                        _child.select2('destroy').empty();
                        if(_opt.allowClear && !_opt.multiple) {
                            _child.append('<option></option>');
                        }
                        _child.select2($.extend(_opt, {data: _data}));

                        if(_oldVal) {
                            if(typeof _oldVal === 'string') {
                                _oldVal = _oldVal.split(',');
                            }
                            _.each(_data, function(d) {
                                if(_oldVal.indexOf(d.id) != -1) {
                                    _initValue.push(d.id);
                                }
                            });
                        }
                    }
                    _child.val(_initValue.length == 0 ? '' : _initValue).trigger('change');
            });
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
        if(isCalLoadrate=='01'){
            getInterestFactorElementById('investRate','div').hide();
            $('#calRateType_div').hide();
        }
        getInterestFactorElementById('couponSpecies','div').hide();
    }
    
    /**
     * 中债报送show/hide
     */
    var showReportInfo = function(){
        var type6 = $('#type6').val();
        //存款资产中债报送信息
        $('#bussRepDepositInfoDto_div').hide();
        //拆放同业及买入返售报送信息
        $('#bussRepDlRepoInfoDto_div').hide();
        if(type6==DEPO_ASSET_TYPE_01){
            $('#bussRepDlRepoInfoDto_div').show();
        }else if(type6==DEPO_ASSET_TYPE_02||DEPO_ASSET_TYPE_03){
            $('#bussRepDepositInfoDto_div').show();
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
        if(isDone){
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
            //计息要素利率类型不可更改
            getInterestFactorElementById('rateType').prop('disabled',true).trigger("change");
        // 增加推荐方与推荐费率 add by renping 20191014 start
//            $('#referRate').change(function () {
//                FinInterestFactor.calbuyOutRate('referRate');
//            }).trigger("change");
            if(AssetCommon) {
            	//借款利率change
            	$('#finInterestFactorDto_loadRate').change(AssetCommon.loadRateChange).trigger('change');
            }
        // 增加推荐方与推荐费率 add by renping 20191029 end
            $('#type6').change(typeChange).trigger('change');
            if(pageModel === PAGE_MODEL_REFERENCE) {
                $('#finPayInterestStdInfoDto_calcRule').prop("disabled",true);
                $('#finPayInterestStdInfoDto_payCycle').prop("disabled",true);
            }
            if ($('#finprodMarketId').prop('disabled')) {
                $("#magnifier").css({color: '#dbdbdb'});
            }
          //该资产存在交易
            if(isTransInAsset){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#blnArea').attr('disabled',true).trigger('change');
                $('#finInterestFactorDto_loadRate').attr('disabled',true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
            //update by  zhangnian 20191120  start
            // 增加推荐方与推荐费率 modify by renping 20191014 start
            $('#finInterestFactorDto_loadRate').change(function () {
                FinInterestFactor.calBuyOutRateByTable('loadRate')
            });
            $('#finInterestFactorDto_investRate').change(FinInterestFactor.calBuyOutRateByTable).trigger('change');
            // 增加推荐方与推荐费率 modify by renping 20191014 end
            //update by  zhangnian 20191120  start
        }
    }
    var init = function(){
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
    $(function(){
    	init();
        //除新建画面 ，资产类型置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('#type6').attr('disabled',true).trigger('change');
            $('#finprodMarketId').attr('disabled', true).trigger('change');
            $('.btn-save').hide();
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
        // 调用资产五级分类联动处理 赵东东 2019.7.26 start
        $("#type9,#type10,#type11,#type12").change(function(){
    		window.BussAssetFiveType.doAssetFiveTypeChange(IS_SHOW_TYPE,'F05','type9','type10','type11','type12','type6','type7');
        });
        // 调用资产五级分类联动处理 赵东东 2019.7.26 end
    });
    //是否展示资产四五级分类
    if(IS_SHOW_TYPE == '01'){
    	$('#type11Div').show();
    	$('#type12Div').show();
    }else{
    	$('#type11Div').hide();
    	$('#type12Div').hide();
    }
    $('#magnifier').click(function() {
        getInterfFundDto();
    });
});
