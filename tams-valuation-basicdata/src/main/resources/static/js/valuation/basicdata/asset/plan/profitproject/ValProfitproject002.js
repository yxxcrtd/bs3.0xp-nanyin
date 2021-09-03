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

    //获取付息信息元素id
    var getPayInterestStdInfoElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id);
        }
    };


    function saveOrSubmit(operateType) {
        //update by  zhangnian 20190920 start
        // if(!isAutoCoupon){
            if (FinPayInterestStdInfo && FinPayInterestStdInfo.checkPlanChanged()) {
                Joyin.error("付息计划已修改，请确认。")
                return;
            }
        // }
        var  couponSpecies = getInterestFactorElementById('couponSpecies').val();
        var object = $.serializeToObject('#submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        //object.finCashCalcRuleList = finCashCalcRuleList;
        if(pageModel==PAGE_MODEL_NEW){
            object.finprodId = "";
        }
        // 付息计划
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto.couponSpecies = couponSpecies;
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();
        }
        // 还本计划
        object.bussFinRepaymentDto.vdate = $('#vdate').val();
        object.bussFinRepaymentDto.mdate = $('#mdate').val();
        if(BussFinRepayment){
            object.bussFinRepaymentDto.finCashPlanList = BussFinRepayment.getAllRows();
        }

        object.finPayInterestStdInfoDto.mdate = $('#mdate').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/check', object, function(
            result) {
            if (FinPayInterestStdInfo && !FinPayInterestStdInfo.check()) {
                return ;
            }
            //息票品种为贴现、零息时不作付息计划 、还本计划check
            if(couponSpecies==COUPON_SPECIES_02||couponSpecies==COUPON_SPECIES_03){
                if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG ){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                        finalSava(object,operateType,result);
                    });
                }else{
                    finalSava(object,operateType,result);
                }
            }else{
                if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                        if (FinPayInterestStdInfo && FinPayInterestStdInfo.comfirmCheck()&&FinPayInterestStdInfo.check()&&BussFinRepayment.isComfirmCheck()) {
                            finalSava(object,operateType,result);
                        }
                    })
                }else{
                    if (FinPayInterestStdInfo && FinPayInterestStdInfo.comfirmCheck()&&FinPayInterestStdInfo.check()&&BussFinRepayment.isComfirmCheck()) {
                        finalSava(object,operateType,result);
                    }
                }
            }
        });
    }

    /**
     * 最终提交
     */
    var finalSava = function(object,operateType,result){
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/ValAssetCommon/isHoliday', {mdate:object.mdate,calendarId:object.calendarId}, function(isHoliday){
            if(isHoliday){
                Joyin.confirm("到期日为节假日，是否确认提交", function() {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/save',
                            object, "ValProfitproject002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/save',
                            object, "ValProfitproject002_btn_submit");
                    }
                })
            }else{
                Joyin.confirm(result.data, function() {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/save',
                            object, "ValProfitproject002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/save',
                            object, "ValProfitproject002_btn_submit");
                    }
                })
            }
        });
    }

    /**
     * 计息要素画面显示/隐藏
     */
    var showFinInterestFactorDiv = function(){
        var obj = $('#finInterestFactorDto_div');
        getInterestFactorElementById('investRate','div').hide();
        //getInterestFactorElementById('loadRate','div').hide();
        getInterestFactorElementById('referRate','div').hide();
        getInterestFactorElementById('buyOutRate','div').hide();
        getInterestFactorElementById('basisStyle','div').hide();
        document.getElementById("finInterestFactorDto_loadRateLabel").innerHTML = "净投资收益率";
        $('#calRateType_div').hide();
    }

    /**
     * 付息信息画面显示/隐藏
     */
    var showFinPayInterestStdInfoDiv = function(){
        var obj = $('#finPayInterestStdInfoDto_div');
        getPayInterestStdInfoElementById('endTdayRule','div').hide();
        getPayInterestStdInfoElementById('isNoIssueAmtFlag','div').show();
    }

    var resetForm = function(id,value,pageId){
        //update by zhangnian 20191112 start
        //可转债信息
        if(id == 'finProductConvertible'){
            $.each(value, function(id,value){
                resetForm(id,value,'');
            });
        }else
        //update by zhangnian 20191112 end
        //计息 付息 还本
        if (id == 'finInterestFactorDto') {
            $.each(value, function(id,value){
                resetForm(id,value,'finInterestFactorDto');
            });
        }else if(id == 'finPayInterestStdInfoDto'){
            $.each(value, function(id,value){
                resetForm(id,value,'finPayInterestStdInfoDto');
            });
        }else{
            var element;
            if(pageId != '' && pageId != undefined){
                id = pageId + '_' + id;
            }
            if($('#'+id).size() > 0){
                element = $('#'+id);
            }else{
                element = $('input[name=' + id + ']');
            }

            //下拉框特殊处理
            if (element.hasClass('input-select')) {
                element.val(value).trigger('change');
            }else if(element.hasClass('input-checkbox')){
                //单选按钮特殊处理
                element.val(value).trigger('change');
            }else if ($('#' + id).attr('lay-skin') === 'switch') {
                //switch 特殊处理
                $('#' + id).prop('checked', false);
                // 是否,有无
                if (value === 'Y' || value === '01') {
                    $('#' + id).prop('checked', true);
                }
            }else{
                element.val(value);
            }
        }

    }

    var setData = function(id, value ,pageId) {
        //update by zhangnian 20191112 start
        //可转债信息
        if(id == 'finProductConvertible'){
            $.each(value, function(id, value) {
                setData(id, value , '');
            });
        }else
        //update by zhangnian 20191112 end
        // 计息
        if (id == 'finInterestFactorDto' ) {
            $.each(value, function(id, value) {
                if (id == 'intType'){
                    id = 'rateType';
                }
                // 债券新建新增快捷录入功能 add by renping 20191010 start
                else if (id == 'highestRate'){
                    id = 'rateMax';
                }
                else if (id == 'lowestRate'){
                    id = 'rateMin';
                }
                else if (id == 'benchmarkId'){
                    id = 'indexId';
                }
                // 债券新建新增快捷录入功能 add by renping 20191010 end
                setData(id, value , 'finInterestFactorDto');
            });
        } else if (id == 'finPayInterestStdInfoDto') {
            // 付息
            $.each(value, function(id, value) {
                if (id == 'firstPayDate' && $('#finPayInterestStdInfoDto_calcRule').val()=='01') {
                    value = null;
                }
                setData(id, value , 'finPayInterestStdInfoDto');
            });
            // 表格
            $('#generationPlanTable').data('joyintable').reload({
                data : value.finCashPlanList,
                url : null
            });
        } else if (id == 'bussFinRepaymentDto') {
            // 还本
            // 表格
            $('#bussFinRepaymentTable').data('joyintable').reload({
                data : value.finCashPlanList,
                url : null
            });
        } else if (id == 'isRight') {
            if (value === 'Y' || value === '01') {
                $('#isRight').prop('checked', true);
            }
            //是否含权债
            isRightChange();

        }else if (id == 'busiFinExercisePlanDto'){
            // 行权 表格
            $('#busiFinExercisePlanTable').data('joyintable').reload({
                data : value.finExercisePlanList,
                url : null
            });
        }
        else {
            // 基本信息
            var element;
            if(pageId != '' && pageId != undefined){
                id = pageId + '_' + id;
            }
            if($('#'+id).size() > 0){
                element = $('#'+id);
            }else{
                element = $('input[name=' + id + ']');
            }

            //下拉框特殊处理
            if (element.hasClass('input-select')) {
                if (id == 'tradeMarket') {
                    AssetCommon.setTradeMarketId(value);
                    element.val(value).trigger('change');
                    // 债券新建新增快捷录入功能 modify by renping 20191010 start
                    // 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 start
                }else if(id == 'bussFinBondRepInfoDto_repTradeMarket'){
                    //修改债券快录资产代码后缀名出错 by 赵东东 start
                    //AssetCommon.setTradeMarketId(value);
                    //修改债券快录资产代码后缀名出错 by 赵东东 end
                    element.val(value).trigger('change');
                    // 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 end
                }else if (id == 'issuer' || id == 'calendarId' || id == 'sponsor' || id == 'oFinprodId' || id == 'postOrg'||id=='conFinprodMarketId') {
                    // 债券新建新增快捷录入功能 modify by renping 20191010 end
                    //data-url 赋值要特殊处理
                    element.textSelect('setValue', value);
                } else {
                    element.val(value).trigger('change');
                }
            }else if (element.hasClass('input-dateSelect')) {
                //日期框特殊处理
                if (value){
                    element.val(new Date(value).format('yyyy-MM-dd'));
                }
            }else if(element.hasClass('input-checkbox')){
                //单选按钮特殊处理
                element.val(value).trigger('change');
            }else if ($('#' + id).attr('lay-skin') === 'switch') {
                //switch 特殊处理
                $('#' + id).prop('checked', false);
                $('#' + id).next().removeClass('layui-form-onswitch').trigger('change');
                $('#' + id).next().find("em").text('否');
                // 是否,有无
                if (value === 'Y' || value === '01') {
                    $('#' + id).prop('checked', true);
                    $('#' + id).next().addClass('layui-form-onswitch').trigger('change');
                    $('#' + id).next().find("em").text('是');
                }
            }else{
                element.val(value);
            }
        }
    }

    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //计息要素展示/隐藏
            showFinInterestFactorDiv();
            //付息要素隐藏
            showFinPayInterestStdInfoDiv();
            //息票品种
            if(FinInterestFactor){
                getInterestFactorElementById('couponSpecies').change(FinInterestFactor.handleCouponSpecies).trigger("change");
            }
            //该资产存在交易 且为非 赋值界面
            if(isTransInAsset && YES_OR_NO_Y != COPY_FLAG){
                $('#vdate').attr('disabled',true).trigger('change');
                /*$('#mdate').attr('disabled',true).trigger('change');*/
                $('#ccy').attr('disabled',true).trigger('change');
                $('#finInterestFactorDto_loadRate').attr('disabled',true).trigger('change');
                getInterestFactorElementById('rate').attr('disabled',true).trigger('change');
                getInterestFactorElementById('couponSpecies').attr('disabled',true).trigger('change');
                getInterestFactorElementById('indexId').attr('disabled',true).trigger('change');
                getInterestFactorElementById('coefficient').attr('disabled',true).trigger('change');
                getInterestFactorElementById('spreadRate').attr('disabled',true).trigger('change');
                $('#assetType').attr('disabled',true).trigger('change');
                //置灰付息计划生成计划按钮
                /*$('#finPayInterestStdInfoDto_btn_generationPlan').off('click').prop('disabled', true).addClass('layui-btn-disabled');*/
            }
            /*$('#bussFinBondRepInfoDto_repAssetType_div').show();*/
            $('#finInterestFactorDto_loadRate').change(AssetCommon.loadRateChange).trigger('change');
            // $('#finInterestFactorDto_loadRate').change(function () {
            //     FinInterestFactor.calBuyOutRateByTable('loadRate');
            // });
            // $('#finInterestFactorDto_investRate').change(FinInterestFactor.calBuyOutRateByTable).trigger('change');
            if((pageModel != PAGE_MODEL_NEW || YES_OR_NO_Y == COPY_FLAG)
                && ($('#issueAmt').val() == null || $('#issueAmt').val() == "")) {
                //getPayInterestStdInfoElementById('isNoIssueAmtFlag','').val(YES_OR_NO_Y).trigger("change");
                getPayInterestStdInfoElementById('isNoIssueAmtFlag','').prop('checked', true);
                getPayInterestStdInfoElementById('isNoIssueAmtFlag','').next().addClass('layui-form-onswitch').trigger('change');
                getPayInterestStdInfoElementById('isNoIssueAmtFlag','').next().find("em").text('是');
            }
        }
    }
    var init = function(){
        star();
    }

    /**
     * 改变核算分类的价格
     */
    var changeAcccountintType = function() {
        var assetType =  $('#assetType').val();
        var blnArea =  $('#blnArea').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/getFinanceProductClass',
            {assetType:assetType,blnArea:blnArea}, function(data){
                if(data && data.accountintType) {
                    $("#accountintType").textSelect('setValue',data.accountintType);
                }
                else {
                    $("#accountintType").textSelect('setValue',"");
                }
            });
    }

    /**
     * 改变税务分类的价格
     */
    var changeTaxType = function() {
        var assetType =  $('#assetType').val();
        var blnArea =  $('#blnArea').val();
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/profitproject/ValProfitproject002/getFinanceProductTaxClass',
            {assetType:assetType,blnArea:blnArea}, function(data){
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

    }
    /**
     * 资产分类改变触发
     */
    var type18Change = function(){
        //如果为项目类
        if($('#type18').val() == SEC_TYPE_13){
            $('#lxDiv').hide();
            $('#zqDiv').show();
            $('#issuerDiv').hide();
            $('#issuer').val("").trigger("change");
        }else{
            $('#lxDiv').show();
            $('#zqDiv').hide();
            $('#issuerDiv').show();
        }
        $("#transMode").val(ACCOUNT_SPEED_01);
        var itemFwd = [];
        var TEMP_PLANNED_TYPE = JSON.parse(PLANNED_TYPE);
        if ($("#type18").val().startsWith(SEC_TYPE_13)) {
            for ( var i in TEMP_PLANNED_TYPE) {
                if (!TEMP_PLANNED_TYPE.hasOwnProperty(i))
                    continue;
                if (TEMP_PLANNED_TYPE[i].id == "01"
                    || TEMP_PLANNED_TYPE[i].id == "02"
                    || TEMP_PLANNED_TYPE[i].id == "03"
                    || TEMP_PLANNED_TYPE[i].id == "04"
                    || TEMP_PLANNED_TYPE[i].id == "05"
                    || TEMP_PLANNED_TYPE[i].id == "06"){
                    itemFwd.push(TEMP_PLANNED_TYPE[i].id);
                }
            }
            $('#assetType').updateChosenProjcetArr({
                item : itemFwd,
                disabled : false
            });

        } else if ($("#type18").val().startsWith(SEC_TYPE_12)) {
            for ( var i in TEMP_PLANNED_TYPE) {
                if (!TEMP_PLANNED_TYPE.hasOwnProperty(i))
                    continue;
                if (TEMP_PLANNED_TYPE[i].id != "01"
                    && TEMP_PLANNED_TYPE[i].id != "02"
                    && TEMP_PLANNED_TYPE[i].id != "03"
                    && TEMP_PLANNED_TYPE[i].id != "04"
                    && TEMP_PLANNED_TYPE[i].id != "05"
                    && TEMP_PLANNED_TYPE[i].id != "06") {
                    itemFwd.push(TEMP_PLANNED_TYPE[i].id);
                }
            }
            $('#assetType').updateChosenProjcetArr({
                item : itemFwd,
                disabled : false
            });
        }
        var initFlag = false;
        for ( var i in itemFwd) {
            if (itemFwd[i] == ASSET_TYPE) {
                initFlag = true;
                break;
            }
        }
        if (initFlag) {
            $('#assetType').val(ASSET_TYPE).trigger("change");
        }
    }

    var calcTermDay = function() {
        var vdate = $('#vdate').val();
        var mdate = $('#mdate').val();
        if(vdate != null && vdate != "" && mdate != null && mdate != "") {
            $("#termDays").val((new Date(vdate).DateDiff('d',mdate)));
        }

    }
    //复制跳转过来
    var copyFunc = function() {
        if(YES_OR_NO_Y == COPY_FLAG) {
            //$("#finprodMarketId").val("");
            //$("#finprodId").val("");
            //$("#circulationCode").val("");
            //$("#finprodName").val("");
            //$("#finprodAbbr").val("");
        }
    }

    /**
     * chosen.jquery.js 项目插件拓展
     *
     * 更新 下拉框通用方法
     *
     * @param dataResult 原数据，不传时，默认取[data-result]
     * @param item 筛选，这里取的数据对象中的id，如：['1','2']，不传时，全部展示
     * @param chosen 选中某一项 string
     * @param disabled 是否禁用 boolean
     * @param callback 回调
     * @return $this
     *
     * @author 言伟
     */
    $.fn.updateChosenProjcetArr = function updateChosenProjcetArr(obj){
        var $this = $(this),arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'),item = obj.item;
        $this.empty();
        $this.append($('<option value>'));
        for(var i in arr){
            if (!arr.hasOwnProperty(i))continue;
            if (obj.item && obj.item.length > 0){
                if ($.inArray(arr[i].id,item) >= 0)
                    $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
            } else
                $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
        }
        if (obj.chosen)
            $this.val(obj.chosen);
        if (obj.disabled === true || obj.disabled === false)
            $this.attr('disabled', obj.disabled);
        $this.trigger("chosen:updated").trigger('change');
        if (obj.callback)
            obj.callback($this);
        return $this;
    }

    $(function() {
        init();
        //修改  资产代码置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('.btn-save').hide();
        }
        // 绑定按钮事件
        $('#ValProfitproject002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValProfitproject002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#mdate,#vdate').change(function(obj){
            obj.type = 'F18';
            AssetCommon.checkDate(obj);
            calcTermDay();
        });
        $('#blnArea,#assetType').change(function(){
            changeAcccountintType();
            changeTaxType();
        });
        $('#assetType').change(function(){
            assetTypeChange();
        });
        $('#type18').change(function(){
            type18Change();
        });
        $("#pflsh").change(function(){
            star();
        });
        copyFunc();
        assetTypeChange();
        type18Change();
    });
});
