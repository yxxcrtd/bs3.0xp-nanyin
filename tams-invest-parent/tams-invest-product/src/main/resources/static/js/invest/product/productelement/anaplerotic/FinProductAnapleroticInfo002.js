/**
 * 回补信息新建
 */
(function() {

    var getElementById = function(id) {
        return $('#' + id);
    };
    var calcConditionObjBackUp='';
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return $.serializeToObject('#submitForm', true);
    };

    // 预览按钮在产品模板新建时不显示
    //增加ignore 属性
//    var buttons = [{ text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];

//    var loadTable = function () {
//        // 校验缴税日期与产品账套日期
//        Joyin.ajax(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/calcProductAnapleroticPlan',
//                getCalcAnapleroticPlanParams(),function (rows) {
//                             table.table ? table.reload() : table.load();
//                             table.clear(function () {
//                                 table.addRow(rows)
//                              })
//                        })
//    }

//    var table = getElementById('finProductAnapleroticPlanTable').joyintable({
////        ajax: {
////            url: JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/calcProductAnapleroticPlan'),
////            type: 'POST',
////            data: function() {
////                // 生成回补计划参数
////                return getCalcAnapleroticPlanParams();
////            }
////        },
//        searchOnInit: false,
//        height: 200,
//        page: false,
//        checkbox: false,
//        loadonce: true,
//        rowno: true,
//        done: function() {
//        },
//        cols:[
//             {field:'anapleroticCycleStartDateStr', title: 'FinProductAnapleroticInfo.anapleroticCycleStartDate' ,sortable: false}//回补周期开始日
//            ,{field:'anapleroticCycleEndDateStr', title: 'FinProductAnapleroticInfo.anapleroticCycleEndDate',sortable: false}//回补周期结束日
//            ,{field:'anapleroticDateStr', title: 'FinProductAnapleroticInfo.anapleroticDate',sortable: false}//回补日
//            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
//        ]
//    });
//    if (pageModel!=PAGE_MODEL_NEW && (finProductAnapleroticPlan && finProductAnapleroticPlan.length > 0)) {
//        table.load();
//        table.clear(function () {
//            table.addRow(finProductAnapleroticPlan)
//      })
//    }
    /**
     * 绑定删除事件
     */
//    table.bind('click', '.delt', function(data, index) {
//        table.removeRow(data);
//    });

    // 检查生成指令参数是否发生变化
//    var isCalcConditionChanged = function () {
//
//        var currentCalcConditionObj = getCalcAnapleroticPlanParams();
//        // 参照模式，不做check
//        if (pageModel === PAGE_MODEL_REFERENCE) {
//            return false;
//        }
//        if (calcConditionObjBackUp == null) {
//            return false;
//        }
//        if (currentCalcConditionObj != null) {
//            for (var key in currentCalcConditionObj) {
//                if (currentCalcConditionObj[key] != calcConditionObjBackUp[key]) {
//                   var labelText = JoyinUtil.getLabel("FinProductAnapleroticInfo." + key);
//                   Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013", [labelText, "回补计划"]));
//                   return true;
//                }
//            }
//        }
//    };
    // 业绩基准利率类型改变时触发的事件
    $('#rateType').change(function() {
        if( $('#rateType').val() === '') {
            $('#rate').val('').trigger('change').prop('disabled', false);
            $('#indexId').val('').trigger('change').prop('disabled', false);
            $('#coefficient').val('').trigger('change').prop('disabled', false);
            $('#spreadRate').val('').trigger('change').prop('disabled', false);
            $('#resetType').val('').trigger('change').prop('disabled', false);
        } else if( $('#rateType').val() === INT_TYPE_AP_01) {
            $('#rate').trigger('change').prop('disabled', false);
            $('#indexId').val('').trigger('change').prop('disabled', true);
            $('#coefficient').val('').trigger('change').prop('disabled', true);
            $('#spreadRate').val('').trigger('change').prop('disabled', true);
//            getElementById('resetType').val('').trigger('change').prop('disabled', true);
            //当利率为固定利率时，业绩基准重置规则不要置灰
            $('#resetType').val('').trigger('change').prop('disabled', true);
        } else {
            $('#rate').val('').trigger('change').prop('disabled', true);
            $('#indexId').trigger('change').prop('disabled', false);
            if(!$('#coefficient').val()){
                $('#coefficient').val('1.00').trigger('change').prop('disabled', false);
            }
            else{
                $('#coefficient').trigger('change').prop('disabled', false);
            }
            $('#spreadRate').trigger('change').prop('disabled', false);
            $('#resetType').val('').trigger('change').prop('disabled', false);
        }
    });
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        //增加 提交时首次回补日校验 add by wangcheng 20191101 start
        if($('#operationType').val()=='01'&& !$("#bussFinProductFloatFeeDto_provisionFirstDay",parent.document).val()){
            var firstAnapleroticDate = $('#firstAnapleroticDate').val();
            var mdate_1 = Date.ofDate(getElementById('mdate').val()).DateAdd("d", -1).format('yyyy-MM-dd');
            var mdate = getElementById('mdate').val();
            if(!(firstAnapleroticDate == mdate_1 || firstAnapleroticDate == mdate)){
                Joyin.error(JoyinUtil.getMessage("首次回补日只能等于到期日"+"("+mdate+")"+"或到期日前一自然日，请重新输入！"));
                return;
            }
        }

        // 开放 高频开放 增加起息日  到期日 判断 开放式产品录入回补信息，输入首次回补日小于起息日，回补计息结束日大于产品到期日，应无法提交
        //首次回补日 <= 回补计息结束日大
        if($('#operationType').val()=='02' && applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() =='02' ){
                var vdate = getElementById('vdate').val();
                var firstAnapleroticDate = $('#firstAnapleroticDate').val();
                var anapleroticEndDate = $('#anapleroticEndDate').val();
                var mdate = getElementById('mdate').val();
                //debugger;
                if (new Date(vdate).DateDiff('d', $('#firstAnapleroticDate').val() ) < 0) {
                       Joyin.error(JoyinUtil.getMessage("首次回补日必须大于等于起息日，请重新输入！"));
                        return;
                }
                if (new Date(firstAnapleroticDate).DateDiff('d', $('#anapleroticEndDate').val() ) < 0) {
                                       Joyin.error(JoyinUtil.getMessage("回补计提结束日必须大于等于首次回补日，请重新输入！"));
                                        return;
                 }
                 if (new Date(anapleroticEndDate).DateDiff('d', getElementById('mdate').val() ) < 0) {
                        Joyin.error(JoyinUtil.getMessage("到期日必须大于等于回补计提结束日，请重新输入！"));
                         return;
                  }
        }

        if(applyType =='' || applyType ==null  ){

            if($('#operationType').val()=='02' && $('#openMode') && $('#openMode').val()=='02' ){

                var vdate = getElementById('vdate').val();
                var firstAnapleroticDate = $('#firstAnapleroticDate').val();
                var anapleroticEndDate = $('#anapleroticEndDate').val();
                var mdate = getElementById('mdate').val();
                //debugger;
                if (new Date(vdate).DateDiff('d', $('#firstAnapleroticDate').val() ) < 0) {
                       Joyin.error(JoyinUtil.getMessage("首次回补日必须大于等于起息日，请重新输入！"));
                        return;
                }
                if (new Date(firstAnapleroticDate).DateDiff('d', $('#anapleroticEndDate').val() ) < 0) {
                                       Joyin.error(JoyinUtil.getMessage("回补计提结束日必须大于等于首次回补日，请重新输入！"));
                                        return;
                 }
                 if (new Date(anapleroticEndDate).DateDiff('d', getElementById('mdate').val() ) < 0) {
                        Joyin.error(JoyinUtil.getMessage("到期日必须大于等于回补计提结束日，请重新输入！"));
                         return;
                  }
            }
        }


        //增加 提交时首次回补日校验 add by wangcheng 20191101 end
//    	if(getElementById('isList').val() !='01'){
//    		//修复 bug 产品快录后 改变开放模式为 高频开放 ，只能在呢个回补信息提交 ，提示首次回补周期开始日已改变，请重新生成回补计划 add by wangcheng 20191031
//    		if(applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() !='02'){
//            	if(isCalcConditionChanged()){
//            		return;
//            	}
//            }
//    	}
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
//        var finProductAnapleroticPlanList = table.getAllRows();
//        object.finProductAnapleroticPlanList = finProductAnapleroticPlanList;
        object.operateType = operateType;
        object.applyType = $('#defalutApplyType').val();
        object.anapleroticBasic = $('#anapleroticBasic').val();
        object.parentId = parentId;

        var bussBenchMarkInfoDto = {};
        bussBenchMarkInfoDto.baseRuleId = object.baseRuleId;
        bussBenchMarkInfoDto.rateType = object.rateType;
        bussBenchMarkInfoDto.rate = object.rate;
        bussBenchMarkInfoDto.indexId = object.indexId;
        bussBenchMarkInfoDto.coefficient = object.coefficient;
        bussBenchMarkInfoDto.spreadRate = object.spreadRate;
        bussBenchMarkInfoDto.resetType = object.resetType;
        object.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
        object.dealMode = dealMode;
        if(applyType =='prod'){
            object.openMode = $("#bussFinProductOpenPlanDto_openMode",parent.document).val();
            object.prodStyleList = anapleroticList;
        }
        if ($('#defalutApplyType').val()) {
            Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/check',object, function(result) {
//                Joyin.confirm(result.data, function() {
                object.finprodMarketId = finprodMarketId;
                Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/finProductAnapleroticSave',
                    object, function(result) {
                        if (result && result.data) {
                            top.returnData = result.data;
                            var index = parent.layer
                                .getFrameIndex(window.name);
                            parent.layer.close(index);
                            return;
                        }
                    })
//                                        })
            });
        }else{
            Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/saveCheck',object, function(result) {
//                Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/save', object, "FinProductAnapleroticInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/save', object, "FinProductAnapleroticInfo002_btn_submit");
                }
//                })
            })
        }

    }
    var init = function(){
        calcConditionObjBackUp = getCalcAnapleroticPlanParams();
        //影藏回补比例
        getElementById('anapleroticRatio_div').hide();
        //如果是产品新建界面  产品信息隐藏
        if(applyType =='prod'){
            getElementById('finprodInfoDiv').hide();
//         if($("#bussFinProductOpenPlanDto_openMode",parent.document).val() =='02'){
//       		 getElementById('anapleroticPlanInfo').hide();
//       	 }else{
//       		 getElementById('anapleroticPlanInfo').show();
//       	 }
            operationTypeChange();
        }
        else{
            prodStyleSelectInit();
            if(!firstAnapleroticCycleBegDate&&pageModel != PAGE_MODEL_NEW){
                getElementById('firstAnapleroticCycleStrDateDiv').hide();
            }
            else{
                getElementById('firstAnapleroticCycleStrDateDiv').show();
            }
        }
        if (pageModel === PAGE_MODEL_EDIT && applyType !='prod'){
            getElementById('finprodId').trigger('change');
            getElementById('finprodId').prop('disabled', true);
            getElementById('effDate').prop('disabled', true);
//            getElementById('anapleroticFrequency').prop('disabled', true);
            getElementById('firstAnapleroticDate').prop('disabled', true);
            getElementById('cycleTdayRule').prop('disabled', true);
//            getElementById('FinProductAnapleroticInfo_btn_preview').prop('disabled', true);
            getElementById('firstAnapleroticCycleEndDate').prop('disabled', true);
            getElementById('anapleroticBasic').prop('disabled', true);
            getElementById('prodStyle').prop('disabled', true);
            getElementById('anapleroticEndDate').prop('disabled', true);
            getElementById('firstAnapleroticCycleBegDate').prop('disabled', true);
//            getElementById('astVluDateType').prop('disabled', true);
        }
//        isListChange();
        //处理模式联动
        if(dealMode === DISPOSE_PATTERN_11){
            getElementById('prodStyleDivId').show();
        }
        else{
            getElementById('prodStyleDivId').hide();
        }
        //产品模板页面回补要素隐藏 yzg 20200203 start
        if(parentId == 'FinProductModule002'){
        	$('#anapleroticPlanInfo').toggle(false);
        }else{
        	$('#anapleroticPlanInfo').toggle(true);
        }
        //产品模板页面回补要素隐藏 yzg 20200203 end
    };

    /**
     * 获取生成回补计划条件
     */
    var getCalcAnapleroticPlanParams = function(){
        var param = {};
//        param.anapleroticFrequency = getElementById('anapleroticFrequency').val();
        param.firstAnapleroticCycleBegDate= getElementById('firstAnapleroticCycleBegDate').val();
        param.firstAnapleroticDate = getElementById('firstAnapleroticDate').val();
//        param.cycleTdayRule = getElementById('cycleTdayRule').val();
        param.firstAnapleroticCycleEndDate = getElementById('firstAnapleroticCycleEndDate').val();
        param.anapleroticEndDate = getElementById('mdate').val();
        param.calendarId = getElementById('calendarId').val();
        return param;
    }
    /**
     * 根据发行方式生成首次回补日
     */
    var operationTypeChange = function(){
        if(pageModel != PAGE_MODEL_NEW && quickInputAnapleroticFlag ==='true' && applyType !='prod'){
            $('#firstAnapleroticDate').val('');
            //回补计提结束日
            getElementById('anapleroticEndDate').val('');
            getElementById('firstAnapleroticCycleBegDate').val('');
        }
        if(pageModel != PAGE_MODEL_NEW){
        	if(applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() =='02'){
                getElementById('firstAnapleroticCycleStrDateDiv').hide();
            }
            return;
        }
        var operationType = $('#FinNetValueProductBuild002_operationType',parent.document).val();
        if(operationType == '01'){
            var mdate =  getElementById('mdate').val();
            var vdate =  getElementById('vdate').val();
            //判断 产品新建 有无 浮动管理费
//    		if($("#bussFinProductFloatFeeDto_provisionFirstDay",parent.document).val()){
//    			//首次回补日 = 浮动管理费.首次计提日 置灰不可修改 add by wangcheng 20191101
//    			$('#firstAnapleroticDate').val($("#bussFinProductFloatFeeDto_provisionFirstDay",parent.document).val()).prop('disabled', true);
//    		}else{
            //首次回补日 = 产品.到期日 - 1自然日
            $('#firstAnapleroticDate').val(Date.ofDate(mdate).DateAdd("d", -1).format('yyyy-MM-dd'));
//    		}
            //回补计提结束日
            getElementById('anapleroticEndDate').val(getElementById('firstAnapleroticDate').val()).prop('disabled', true);
            getElementById('firstAnapleroticCycleBegDate').val(Date.ofDate($("#FinNetValueProductBuild002_vdate",parent.document).val()).DateAdd("d", -1).format('yyyy-MM-dd'));
        }else if(operationType == '02'){
        // 开放
            if(applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() =='02'){
            //高频开放
                getElementById('firstAnapleroticCycleStrDateDiv').hide();
                var mdate =  getElementById('mdate').val();
                var vdate =  getElementById('vdate').val();
                getElementById('firstAnapleroticDate').val(Date.ofDate(vdate).format('yyyy-MM-dd'));
                getElementById('anapleroticEndDate').val(Date.ofDate(mdate).format('yyyy-MM-dd'));
                return;
            }
            else if(applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() !='02'){
                getElementById('firstAnapleroticCycleStrDateDiv').show();
            }
            if($("#openNdateStart",parent.document).val()&&$("#openNdateEnd",parent.document).val()){
                if(!$("#openNdateRedStart",parent.document).val()&&!$("#openNdateRedEnd",parent.document).val()){
                    getElementById('firstAnapleroticDate').val($("#openNdateStart",parent.document).val());
                    //开放式产品 新建 导致新建页面提交报到期日改变，重新生成回补计划 update by wangcheng 20191028 start
//           		 getElementById('mdate').val($("#openNdateEnd",parent.document).val());
                    //开放式产品 新建 导致新建页面提交报到期日改变，重新生成回补计划 update by wangcheng 20191028 end
                    getElementById('anapleroticEndDate').val($("#openNdateEnd",parent.document).val());
                }
                else{
                    if($("#openNdateStart",parent.document).val()>$("#openNdateRedStart",parent.document).val()){
                        getElementById('firstAnapleroticDate').val($("#openNdateRedStart",parent.document).val());
                    }
                    else{
                        getElementById('firstAnapleroticDate').val($("#openNdateStart",parent.document).val());
                    }
                    if($("#openNdateEnd",parent.document).val()<$("#openNdateRedEnd",parent.document).val()){
                        getElementById('anapleroticEndDate').val($("#openNdateRedEnd",parent.document).val());
                    }
                    else{
                        getElementById('anapleroticEndDate').val($("#openNdateEnd",parent.document).val());
                    }
                }
                getElementById('firstAnapleroticCycleBegDate').val(Date.ofDate($("#FinNetValueProductBuild002_vdate",parent.document).val()).DateAdd("d", -1).format('yyyy-MM-dd'));
                //getElementById('firstAnapleroticCycleBegDate').val();
            }

        }
    }
    // 预览按钮点击事件
//    getElementById('FinProductAnapleroticInfo_btn_preview').click(function() {
//        calcConditionObjBackUp = getCalcAnapleroticPlanParams();
//        initFlag = true;
//        Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/check', getCalcAnapleroticPlanParams(), function(result) {
//            if (result.data){
//                Joyin.confirm(result.data, function() {
//                    loadTable();
//                });
//            }else{
//                loadTable();
//            }
//        });
//    });

    /**
     * 获取首次回补结束日
     */
    getfirstAnapleroticCycleEndDate = function(){
        //首次回补日非空判断
        if(null == $('#firstAnapleroticCycleBegDate').val() || '' == $('#firstAnapleroticCycleBegDate').val()){
            return;
        }
        //回补规则非空判断
//        if(null == $('#anapleroticFrequency').val() || '' == $('#anapleroticFrequency').val()){
//            return;
//        }
//        var anapleroticFrequency = $('#anapleroticFrequency').val();
        //浮动管理费同频 17
//        if(anapleroticFrequency =='17'){
//            return;
//        }
//        //到期
//        else if(anapleroticFrequency =='07'){
//            $('#firstAnapleroticCycleEndDate').val(getElementById('mdate').val()).trigger('change');
//            return;
//        }
//
//        var strInterval = '';
//        var number = 1;
//        //月
//        if(anapleroticFrequency =='03'){
//            strInterval = 'm';
//        }
//        //季
//        else if(anapleroticFrequency =='04'){
//            strInterval = 'q';
//        }
//        //半年
//        else if(anapleroticFrequency =='06'){
//            strInterval = 'q';
//            number = 2;
//        }
//        //年
//        else if(anapleroticFrequency =='05'){
//            strInterval = 'y';
//        }
        //封闭式产品限制首次回补日
//        if($('#operationType').val()&&$('#operationType').val()=='01'){
//            var timea = Date.ofDate($('#firstAnapleroticDate').val()).getTime();
//            var timeb = Date.ofDate(getElementById('mdate').val()).DateAdd("d", -1).getTime();
//            var timec = Date.ofDate(getElementById('mdate').val()).getTime();
//            if(!(timea <= timec && timea >= timeb)){
               // $('#firstAnapleroticDate').val("").trigger('change');/*fixed BS-1486 bug 【净值化产品管理】-【新建】：回补新建功能存在问题*/
//                Joyin.error(JoyinUtil.getMessage("首次回补日不符合条件请重新输入！"));
//                return;
//            }else{
//            	$('#anapleroticEndDate').val(getElementById('firstAnapleroticDate').val()).trigger('change');
//            }
//        }
//       var firstAnapleroticCycleEndDate = Date.ofDate($('#firstAnapleroticCycleBegDate').val()).DateAdd(strInterval,number);
//       var days = (Date.ofDate(firstAnapleroticCycleEndDate) - Date.ofDate(getElementById('mdate').val()))/(24*60*60*1000);
//       if(days>0){
//           $('#firstAnapleroticCycleEndDate').val(getElementById('mdate').val()).trigger('change');
//       }else{
//           $('#firstAnapleroticCycleEndDate').val(Date.ofDate(firstAnapleroticCycleEndDate).format('yyyy-MM-dd')).trigger('change');
//       }
    };
    var prodStyleSelectInit = function(){
        //产品代码
        var finprodId = $('#finprodId').val();
        if(!finprodId){
            return;
        }
//    	$('#prodStyle').empty();
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/getProdStyleList',
            {'finprodId':finprodId},
            function(result) {
                if(result != null && result.length > 0){
                    $("#prodStyle option").remove();
                    $('#prodStyle').append("<option value=''>请选择...</option>");
                    $(result).each(function (i, n) {
                        $('#prodStyle').append("<option value='"+n.finprodId+"'>"+n.prodType+"</option>");
                    });
                    $('#prodStyle').val(PRODSTYLE).parent().parent().show();
                } else {
                    $("#prodStyle").val('').parent().parent().hide();
                }
            }
        );
    };
    var clearAnapleroticElement = function(){
        getElementById('mdate').val("").trigger('change');
        getElementById('effDate').val("");
        getElementById('finprodAbbr').val("");
        getElementById('firstAnapleroticDate').val("");
        getElementById('isList').val("").trigger("blur").trigger('change');
        getElementById('calendarId').val("").trigger("blur").trigger('change');
        getElementById('anapleroticType').val("").trigger("blur").trigger('change');
        getElementById('anapleroticRule').val("").trigger("blur").trigger('change');
        getElementById('anapleroticRatio').val("").trigger("blur").trigger('change');
        getElementById('anapleroticBasic').val("").trigger("blur").trigger('change');
//         getElementById('astVluDateType').val("").trigger("blur").trigger('change');
//         getElementById('anapleroticFrequency').val("").trigger("blur").trigger('change');
        getElementById('firstAnapleroticDate').val("").trigger("blur").trigger('change');
//         getElementById('cycleTdayRule').val("").trigger("blur").trigger('change');
        getElementById('firstAnapleroticCycleEndDate').val("").trigger("blur").trigger('change');
        getElementById('firstAnapleroticCycleBegDate').val("").trigger("blur").trigger('change');
        getElementById('anapleroticEndDate').val("").trigger("blur").trigger('change');

        getElementById('rateType').val("").trigger("blur").trigger('change');
        getElementById('rate').val("").trigger("blur").trigger('change');
        getElementById('indexId').val("").trigger("blur").trigger('change');
        getElementById('coefficient').val("").trigger("blur").trigger('change');
        getElementById('spreadRate').val("").trigger("blur").trigger('change');
        getElementById('resetType').val("").trigger("blur").trigger('change');
    };
    var queryAnapleroticInfoByfinprodId = function(input_finprodId){
        Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/queryAnapAndProdInfo', {finprodId : input_finprodId,dealMode : dealMode} , function(result) {
            if (result){
                if(result.isAnapleroticList == 'N'){
                    getElementById('mdate').val(new Date(result.mDate).format('yyyy-MM-dd')).trigger('change');
                    getElementById('effDate').val(result.effDateStr);
                    getElementById('finprodAbbr').val(result.finprodAbbr);
                    getElementById('firstAnapleroticDate').val(new Date(result.firstAnapleroticDate).format('yyyy-MM-dd'));
                    getElementById('isList').val(result.isList).trigger("blur").trigger('change');
                    getElementById('calendarId').val(result.calendarId).trigger("blur").trigger('change');
                    getElementById('isAnapleroticList').val(result.isAnapleroticList).trigger("blur").trigger('change');

                    //封闭式产品回补结束日不可编辑
                    if(result.operateType === '01'){
                        getElementById('anapleroticEndDate').val(new Date(result.anapleroticEndDate).format('yyyy-MM-dd')).prop('disabled', true);
                    }
                    else{
                        getElementById('anapleroticEndDate').val(new Date(result.anapleroticEndDate).format('yyyy-MM-dd')).prop('disabled', false);
                    }
                    if(result.isList === '01'){
                        getElementById('firstAnapleroticCycleStrDateDiv').hide();
                    }
                    else{
                        getElementById('firstAnapleroticCycleStrDateDiv').show();
                        getElementById('firstAnapleroticCycleBegDate').val(new Date(result.firstAnapleroticCycleBegDate).format('yyyy-MM-dd')).trigger('change');
                    }
                }else{
                    getElementById('mdate').val(new Date(result.mDate).format('yyyy-MM-dd')).trigger('change');
                    getElementById('effDate').val(result.effDateStr);
                    getElementById('finprodAbbr').val(result.finprodAbbr);
                    getElementById('firstAnapleroticDate').val(new Date(result.firstAnapleroticDate).format('yyyy-MM-dd'));
                    getElementById('isList').val(result.isList).trigger("blur").trigger('change');
                    getElementById('calendarId').val(result.calendarId).trigger("blur").trigger('change');
                    getElementById('anapleroticType').val(result.anapleroticType).trigger("blur").trigger('change');
                    getElementById('anapleroticRule').val(result.anapleroticRule).trigger("blur").trigger('change');
                    getElementById('anapleroticRatio').val(result.anapleroticRatio).trigger("blur").trigger('change');
                    getElementById('anapleroticBasic').val(result.anapleroticBasic).trigger("blur").trigger('change');
//                      getElementById('astVluDateType').val(result.astVluDateType).trigger("blur").trigger('change');
//                      getElementById('anapleroticFrequency').val(result.anapleroticFrequency).trigger("blur").trigger('change');
                    getElementById('firstAnapleroticDate').val(new Date(result.firstAnapleroticDate).format('yyyy-MM-dd')).trigger("blur").trigger('change');
//                      getElementById('cycleTdayRule').val(result.cycleTdayRule).trigger("blur").trigger('change');
                    getElementById('firstAnapleroticCycleEndDate').val(new Date(result.firstAnapleroticCycleEndDate).format('yyyy-MM-dd')).trigger("blur").trigger('change');
                    getElementById('firstAnapleroticCycleBegDate').val(new Date(result.firstAnapleroticCycleBegDate).format('yyyy-MM-dd')).trigger('change');
                    getElementById('anapleroticEndDate').val(new Date(result.anapleroticEndDate).format('yyyy-MM-dd')).trigger('change');
//                      if(result.anapleroticFrequency&&result.cycleTdayRule){
////                    	  loadTable();
//                    	  getElementById('anapleroticPlanInfo').show();
//                      }else{
//                    	  getElementById('anapleroticPlanInfo').hide();
//                      }

                    getElementById('vdate').val(new Date(result.vDate).format('yyyy-MM-dd')).trigger('change');
                    getElementById('operationType').val(result.operationType).trigger('change');
                    getElementById('dealMode').val(result.dealMode).trigger('change');
                    getElementById('openMode').val(result.openMode).trigger('change');

                }
                getElementById('rateType').val(result.rateType).trigger("blur").trigger('change');
                getElementById('rate').val(result.rate).trigger("blur").trigger('change');
                getElementById('indexId').val(result.indexId).trigger("blur").trigger('change');
                getElementById('coefficient').val(result.coefficient).trigger("blur").trigger('change');
                getElementById('spreadRate').val(result.spreadRate).trigger("blur").trigger('change');
                getElementById('resetType').val(result.resetType).trigger("blur").trigger('change');
                //table.addRow(result.finProductAnapleroticPlanList);
            }
        });
    };
    var finprodIdChange = function(){
        var finprodId = $('#finprodId').val();
//        if(null == finprodId || ''== finprodId) {
//            return;
//        }
        if (!finprodId||!$('#finprodId').select2('data') || $('#finprodId').select2('data').length == 0 || !$('#finprodId').select2('data')[0]
            || !$('#finprodId').select2('data')[0].object) {
            return;
        }
        clearAnapleroticElement();

        var str = $('#finprodId').select2('data')[0].object.split(',');
        if(str!=null){
            dealMode = str[0];
            //处理模式联动
            if(dealMode === DISPOSE_PATTERN_11){
                getElementById('prodStyleDivId').show();
                prodStyleSelectInit();
            }
            else{
                getElementById('prodStyleDivId').hide();
                queryAnapleroticInfoByfinprodId(finprodId);
            }
        }

//        loadTable();
    };
    var prodStyleChange = function(input_finprodId){
        if(applyType =='prod'){
            return;
        }
        var input_prodStyle = $('#prodStyle').val();
        if(input_prodStyle){
            queryAnapleroticInfoByfinprodId(input_prodStyle);
        }
    }

    var firstAnapleroticDateChangeAction = function(){
        // 处理 模式 为 普通 /普通分档  封闭式产品 则 修改首次回补日后，回补计提结束日与之相同 切不可修改
        if( (dealMode == '01' || dealMode == '11') && $('#operationType').val() == '01' ){
            getElementById('anapleroticEndDate').val(getElementById('firstAnapleroticDate').val()).prop('disabled', true);
        }
    }
//    var isListChange=function(){
//    	 if(getElementById('isList').val() =='01'){
//    		 getElementById('anapleroticPlanInfo').hide();
//    		 //高频开放 回补计划隐藏 add by wangcheng 20191030
//    	 }else if(applyType =='prod' && $("#bussFinProductOpenPlanDto_openMode",parent.document).val() =='02'){
//    		 getElementById('anapleroticPlanInfo').hide();
//    	 }else{
//    		 getElementById('anapleroticPlanInfo').show();
//    	 }
//    }
//    var isAnapleroticChange = function(){
//    	if($("#FinProductAnapleroticInfo002_isAnaplerotic").val() ==='N'){
//    		  getElementById('anapleroticType_div').hide();
//              getElementById('anapleroticRule_div').hide();
////              getElementById('anapleroticRatio_div').hide();
//              getElementById('anapleroticBasic_div').hide();
//              getElementById('astVluDateType_div').hide();
//    	}else{
//    		  getElementById('anapleroticType_div').show();
//              getElementById('anapleroticRule_div').show();
////              getElementById('anapleroticRatio_div').show();
//              getElementById('anapleroticBasic_div').show();
//              getElementById('astVluDateType_div').show();
//    	}
//    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        $('#finprodId').change(function() {
            finprodIdChange();
        });
        //发行方式
        $('#operationType').change(function() {
            operationTypeChange();
        });
        //首次回补日
        $('#firstAnapleroticDate').change(function() {
            getfirstAnapleroticCycleEndDate();
            firstAnapleroticDateChangeAction();
        });
        $('#prodStyle').change(function() {
            prodStyleChange();
        });
        //回补规则
//    	$('#anapleroticFrequency').change(function() {
//    	    getfirstAnapleroticCycleEndDate();
//    	});
//    	getElementById('isList').change(function(){
//    		isListChange();
//    	});
        //是否回补
//    	$("#FinProductAnapleroticInfo002_isAnaplerotic").change(function(){
//    		isAnapleroticChange();
//    	});
        // 绑定按钮事件
        $('#FinProductAnapleroticInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductAnapleroticInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });

}());