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

    /**
     * 中债报送信息DIV控制显示
     *//*
    function typeSelect() {
        var type = $('#type').val();
        if (type == publicOffPlan) {
            $(".bussRepAssetMngProdInfo_div").show();
            $(".bussRepOutInvestAgrInfo_div").hide();
        } else {
            $(".bussRepAssetMngProdInfo_div").hide();
            $(".bussRepOutInvestAgrInfo_div").show();
        }
    };*/
    //画面联动：输入出账编号，联动显示关联合同编号、BECIF号和发行金额
    /*var cmsIdChange = function() {
        var cmsId = $("#cmsId").val();
        var object = {'cmsId':cmsId};
        Joyin.ajax(getChange, object, function(data){
            if(data){
                $('#cmsContractId').val(data.cmsContractId);
                $('#cmsBecif').val(data.cmsBecif);
                $('#issueAmt').val(data.issueAmt);
            }
        });
    };*/

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.finprodAbbr = $("#finprodName").val();
        var assetProviderTemp = $('#assetProvider').val();
        var assetProvider= null;
        if(assetProviderTemp != null)
        	assetProvider=assetProviderTemp.join(",");
        object.assetProvider = assetProvider;
        object.pageModel = pageModel;
        object.operateType = operateType;
        //update by zhangnian 20191102 start
        //文件信息

        //分红计划-货币型
        //object.bussFinIntPayDto.fundType = FUND_ASSET_TYPE_F01;
        object.setFinprodType2 = FINPROD_TYPE_TG_F17;
        // if (object.bussFinIntPayDto != null || object.bussFinIntPayDto != undefined){
        //     if ($('#bussFinIntPayDto_isCompoundInt').next().find("em").text() === '是') {
        //         object.bussFinIntPayDto.isCompoundInt = 'Y';
        //     } else {
        //         object.bussFinIntPayDto.isCompoundInt = 'N';
        //     }
        // }

        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/check', object, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/isHoliday', {
                mdate: object.mdate,
                calendarId: object.calendarId
            }, function (isHoliday) {
                if(isHoliday){
                    Joyin.confirm("终止日期为节假日，是否确认提交", function () {
                        if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/save', object, "FinPlanAssetC002_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/save', object, "FinPlanAssetC002_btn_submit");
                        }
                    })
                }else{
                    Joyin.confirm(result.data, function() {
                        if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/save', object, "FinPlanAssetC002_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/save', object, "FinPlanAssetC002_btn_submit");
                        }
                    });
                }
            })
        });
    };
    //控件加载完成后
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            $("#bussRepAssetMngProdInfo_repAssetType_div").show();
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
    	if($("#category").val() == PLAN_ASSET_TYPE_N02){
    		$('#bankTrustCodeDiv').show();
    	}
    	else{
    		$('#bankTrustCodeDiv').hide();
    	}
    	if($("#creditFlag").val() == 'Y'){
    		$("#actualCreditProvid").attr("required", true).trigger('change');
    	}
    	if($("#creditFlag").val() != 'Y'){
    		$("#actualCreditProvid").attr("required", false).trigger('change');
    	}
    	star();
    }
    /**
     * 画面加载完成
     *
     */
    $(function() {

        if (pageModel == PAGE_MODEL_EDIT || pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_NEW){
            var profitTypeValue = $("#profitType").val();
            if (profitTypeValue!=""&&profitTypeValue!="03"){           // 只有当收益类型为货币型03的时候才显示出分红计划
                $("#bussFinIntPayDto_div").hide();
                $(".page-component").hide();
            }
        }

        if (pageModel == PAGE_MODEL_EDIT){
            $("#blnArea").attr("disabled",true).trigger('change');
        }

        // 进入新建或复制页面的时候清空所有的隐藏表单中的值
        if (pageModel == PAGE_MODEL_NEW){
            $("#finprodId").val("");
            $("#workflowId").val("");
            $("#fjId").val("");
            $("#seqno").val("");
            $("#creditMstId").val("");
            $("#pfhId").val("");
            $("#hapCreditAmt").val("");
            $("#creditFlag").val("");
        }

    	init();
        //二级分类联动
        $('#type').change(function() {
            //$('#bussRepAssetMngProdInfo_repAssetType').repSelectArr(pageModel);
            //typeSelect();
        });
        /*category and type's automatic match added by lishuping on 2019/7/24 -start*/
        $("#type9,#type10,#type11,#type12").change(function(){
            window.BussAssetFiveType.doAssetFiveTypeChange(IS_SHOW_TYPE,'F09', 'type9', 'type10', 'type11', 'type12', 'category', 'type');
            /*if($('#type12').val()!=''){
                if($('#type9').val() == '16') {
                    $('#category').val('N01') ,
                    $('#type').val('02')
                }else {
                    window.BussAssetFiveType.doAssetFiveTypeChange('F09','type9','type10','type11','type12','category','type');
                    if($('#type').val() == '') {
                        $('#type').val('02')
                    }

                }
            }*/
        });
        /*category and type's automatic match added by lishuping on 2019/7/24 -end*/
        /*//出账编号联动
        $('#cmsId').change(function() {
            cmsIdChange();
        });*/
        // 绑定按钮事件
        $('#FinPlanAssetC002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinPlanAssetC002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
       /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
        $('#FinPlanAssetC002_btn_quickInput').click(function() {

            Joyin.ajax(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/init', {
                'finprodMarketId': $('#finprodMarketId').val(),
            }, function (data) {
                $("body").html(data);
                $("#finprodId").val('');
                $("#fjId").val('');
            });
        });
        /* 增加快捷录入 add by zhuyongkang 2019/10/12 start*/
        //typeSelect();
        if(pageModel!=PAGE_MODEL_NEW) {
            $('#type').attr("disabled", true);
            $('#finprodMarketId').attr('disabled', true).trigger('change');
            $('.btn-save').hide();
        }
        $('#category').change(function() {
        	if($("#category").val() == PLAN_ASSET_TYPE_N02){
        		$('#bankTrustCodeDiv').show();
        	}
        	else{
        		$("#bankTrustCode").val('');
        		$('#bankTrustCodeDiv').hide().trigger('change');
        	}
            //$('#bussRepAssetMngProdInfo_repAssetType').repSelectArr(pageModel);
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

        $("#profitType").change(function () {
            var profitTypeValue = $("#profitType").val();
            if (profitTypeValue == "01"){        // 选择01：净值型时
                $("#bussFinIntPayDto_div").hide();
            }else if (profitTypeValue == "03"){     // 选择03：货币型时
                $("#bussFinIntPayDto_div").show();
            }
        });

        $("#profitType").change(function () {
            getFinanceProductClassOrFinanceProductTaxClass();
        });

        $("#assetType").change(function () {
            getFinanceProductClassOrFinanceProductTaxClass();
        });

        $("#blnArea").change(function () {
            getFinanceProductClassOrFinanceProductTaxClass();
        });

        /**
         * 根据收益类型、资产类型、境内外获取核算分类和税务分类
         */
        function getFinanceProductClassOrFinanceProductTaxClass() {
            var profitTypeValue = $("#profitType").val();
            var blnAreaValue = $("#blnArea").val();
            var assetTypeValue = $("#assetType").val();
            var object = {
                profitType: profitTypeValue,
                assetType: assetTypeValue,
                blnArea: blnAreaValue
            };
            if (profitTypeValue!=""&&blnAreaValue!=""&&assetTypeValue!=""){
                Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/getFinanceProductClass', object, function(result){
                    $('#accountintType').val(result.accountintType).trigger("change");
                });
                Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/plan/plancur/FinPlanAssetC002/getFinanceProductTaxClass', object, function(result){
                    $('#taxType').val(result.taxType).trigger("change");
                });
            }
        }

      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        	$('#type12Div').show();
        }else{
        	$('#type11Div').hide();
        	$('#type12Div').hide();
        }
    });
}());

