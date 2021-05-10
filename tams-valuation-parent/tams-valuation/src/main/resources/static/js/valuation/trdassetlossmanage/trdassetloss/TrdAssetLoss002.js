//@ sourceURL=TrdAssetLoss002.js
/**
 * 债券投资委托新建
 */
(function () {
    var initFlag = true;


    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.operateType = operateType;
        object.pageModel = pageModel;

        Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/check', object,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/save', object, "TrdAssetLoss002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/save', object, "TrdAssetLoss002_btn_submit");
                    }
                });
            });
    }

    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
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
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    var init = function () {

        initFlag = false;
        // 画星星
        star();
        if (typeof shortSellingSupported != 'undefined' && shortSellingSupported == '02') {
            $(".shortSellingSupported").hide();
        }
    }

    /**
     * 绑定事件
     */
    $(function () {
        /**
         * 保存
         */
        $('#TrdAssetLoss002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#TrdAssetLoss002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        /**
         * 预览
         */
        $('#TrdAssetLoss002_btn_preview').click(function () {
            //提交前将资产类型、资产起息日、资产到期日的disabled放开,避免表单提交的时候不传值
            $('#assetType1').removeAttr("disabled");
            $('#vDate').removeAttr("disabled");
            $('#mDate').removeAttr("disabled");
            $('#lossInputPattern').removeAttr("disabled");
            $('#valDate').removeAttr("disabled");
            $('#overDays').removeAttr("disabled");
            $('#residualMaturity').removeAttr("disabled");
            /**
             * 获取列表参数
             */
            var object = $.serializeToObject('#searchForm');
            object.operateType = 'preview';
            object.pageModel = pageModel;

            Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003/check', object,
                function (result) {
                    if (result && result.errorList && result.errorList.length) {
                        Joyin.error(result.errorList);
                        return;
                    } else {
                        showDialog();
                    }
                });
        });
        /**
         * 预览
         */
        function showDialog() {
            if ($('#finprodId').val()) {
                Joyin.dialog(JoyinUtil.getLabel("TrdAssetLoss003"), basePath
                    + "valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003/init", "95%", "90%", {
                    pageModel : PAGE_MODEL_REFERENCE,
                    finprodId : $('#finprodId').val(),
                    valDate : $("#valDate").val(),
                    lossTestType : $("#lossTestType").val(),
                    lossRatio : $("#lossRatio").val(),
                    pdValue : $("#pdValue").val(),
                    lgdValue : $("#lgdValue").val()
                });
            }
        }
        /**
         * 资产代码
         */

        $('#finprodId').change(function () {
            //资产代码不为空,查询金融产品代码、金融产品简称、金融产品类型、起息日、到期日
            var data = {
                finprodId: $('#finprodId').val(),
                valDate: $('#valDate').val()
            }
            if ($('#finprodId').val() && $('#valDate').val()) {
                Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/getAssetExerciseDate', data,
                    function (resultObj) {
                        if (resultObj) {
                            if (resultObj.assetExerciseDate) {
                                $("#assetExerciseDate").val(resultObj.assetExerciseDateStr).trigger('change');
                            }
                        }
                    });
            }
            if($('#finprodId').val()){
                Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/getAssetInfo', data,
                    function (resultObj) {
                        if (resultObj) {
                            if (resultObj.assetType1) {
                                $("#assetType1").val(resultObj.assetType1).trigger('change');
                            }
                            if (resultObj.vDate) {
                                $("#vDate").val(resultObj.vDateStr);
                            }
                            if (resultObj.mDate) {
                                $("#mDate").val(resultObj.mDateStr).trigger('change');
                            }
                            if (resultObj.exeDate) {
                                if (resultObj.exeDate === '04') {
                                    var days1 = (Date.ofDate($("#mDate").val()) - Date.ofDate($("#valDate").val())) / (24 * 60 * 60 * 1000);
                                    $("#peroid").val(JoyinMath.doDiv(days1, 365, 4, JoyinMath.ROUND_HALF_UP));
                                } else {
                                    var days2 = (Date.ofDate($("#assetExerciseDate").val()) - Date.ofDate($("#valDate").val())) / (24 * 60 * 60 * 1000);
                                    $("#peroid").val(JoyinMath.doDiv(days2, 365, 4, JoyinMath.ROUND_HALF_UP));
                                }
                            }
                        }
                    });
            }
            // 画星星
            star();
        });
        
        
        $("#lossTestType").change(function () {
            //外部评估
            if ($("#lossTestType").val() === '01'){
                $("#external_Evaluation_div").show();
                $("#expected_loss_div").hide();
                $("#lossInputPattern").val("01").trigger('change');
                $('#lossRatio').removeAttr("disabled");

                $("#pdValue").val('').trigger('change');
                $("#lgdValue").val('').trigger('change');
                $("#eclVaule").val('').trigger('change');
            }
            //预期信用损失模型
            if ($("#lossTestType").val() === '02'){
                $("#external_Evaluation_div").hide();
                $("#expected_loss_div").show();
                $("#lossInputPattern").val('').trigger('change');
                $('#lossRatio').val('').trigger('change');
            }
        });
        
        $("#mDate").change(function () {
            mDateOrLossDateChange();
        });
        $("#valDate").change(function () {
            mDateOrLossDateChange();
        });
        var mDateOrLossDateChange = function() {
            //到期日
            var mDate = $("#mDate").val();
            //减值行情日
            var valDate = $("#valDate").val();
            //逾期天数 = 减值行情日>资产到期日?:减值行情日-资产到期日:0;
            var days = (Date.ofDate(valDate) - Date.ofDate(mDate))/(24*60*60*1000);
            if(valDate && mDate){
                $("#overDays").val(days > 0 ? days : 0).trigger('change');
            }
        };

        $("#pdValue").change(function () {
            calculationEclvalue();
        });
        $("#lgdValue").change(function () {
            calculationEclvalue();
        });

        var calculationEclvalue = function () {
            if ($('#pdValue').val() && $('#lgdValue').val()){
                /*
                 if(PD违约概率% is not null and LGD违约损失率% is not null)
                    ECL预期信用损失比例% = PD违约概率% *LGD违约损失率%
                */
                $('#eclVaule').val(($('#pdValue').val() * $('#lgdValue').val() / 100).toFixed(4)).trigger('change');
            }else{
                $('#eclVaule').val('').trigger('change');
            }
        }

        //外部评估
        if ($("#lossTestType").val() === '01') {
            $("#external_Evaluation_div").show();
            $("#expected_loss_div").hide();
        }
        //预期信用损失模型
        if ($("#lossTestType").val() === '02') {
            $("#external_Evaluation_div").hide();
            $("#expected_loss_div").show();
        }

    });
}());
