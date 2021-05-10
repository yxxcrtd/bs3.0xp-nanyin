/**
 * 非标准化债权资产SPPI测试新建（德勤模板）测试条款JS联动处理
 */
(function() {
    // 类型1，类型2标记
    var typeValue = 0;
    var type1Names = ['sppiTest002Q79', 'sppiTest002Q80','sppiTest002C14'];
    var type2Names = ['sppiTest002Q81', 'sppiTest002Q82', 'sppiTest002Q83', 'sppiTest002Q84', 'sppiTest002Q85'];
    var bcdeNames = ['sppiTest002C14'];

    for (var i = 86; i <= 98; i++) {
        bcdeNames.push('sppiTest002Q' + i);
    }

    //重设结论值
    var resetConslusion = function(cname) {
        uncheckRadioGroup(cname);
        switch (cname) {
            case "sppiTest002C13":
                if ($('#sppiTest002Q78').val() === YES) {
                    checkDisabledRadio('sppiTest002C13_Y');
                }
                else if ($('#sppiTest002Q78').val() === NO) {
                    checkDisabledRadio('sppiTest002C13_N');
                }
                break;
            case "sppiTest002C14":
                if ($('#sppiTest002Q79').val() === YES) {
                    checkDisabledRadio('sppiTest002C14_N');
                }
                else if ($('#sppiTest002Q80').val() === YES) {
                    checkDisabledRadio('sppiTest002C14_N');
                }
                else if ($('#sppiTest002Q80').val() === NO) {
                    checkDisabledRadio('sppiTest002C14_Y');
                }
                break;
            case "sppiTest002C15":
                //若A4选择是，无论A5做出什么选择，A结果为是。
                // 若A4选择否或A4为空（置灰状态）：A5选择否，则A结果为是；A5选择是或不适用，则A结果为否。
                if ($('#sppiTest002Q85').val() === YES) {
                    if ($('#sppiTest002Q84').val() === YES) {
                        checkDisabledRadio('sppiTest002C15_Y');
                    }
                    //if ($('#sppiTest002Q84').attr("disabled") || $('#sppiTest002Q84').val() === NO)
                    else {
                        checkDisabledRadio('sppiTest002C15_N');
                    }
                }
                else if ($('#sppiTest002Q85').val() === NO) {
                    if ($('#sppiTest002Q84').val() === YES) {
                        checkDisabledRadio('sppiTest002C15_Y');
                    }
                    else {
                        checkDisabledRadio('sppiTest002C15_Y');
                    }
                }
                else if ($('#sppiTest002Q85').val() === NA) {
                    if ($('#sppiTest002Q84').val() === YES) {
                        checkDisabledRadio('sppiTest002C15_Y');
                    }
                    else {
                        checkDisabledRadio('sppiTest002C15_N');
                    }
                }
                break;
            case "sppiTest002C16":
                if ($('#sppiTest002Q86').val() === NO) {
                    checkDisabledRadio('sppiTest002C16_N');
                }
                else if ($('#sppiTest002Q87').val() === NO) {
                    checkDisabledRadio('sppiTest002C16_Y');
                }
                else if ($('#sppiTest002Q88').val() === NO) {
                    checkDisabledRadio('sppiTest002C16_Y');
                }
                else if ($('#sppiTest002Q89').val() === NO) {
                    checkDisabledRadio('sppiTest002C16_Y');
                }
                else if ($('#sppiTest002Q89').val() === YES) {
                    checkDisabledRadio('sppiTest002C16_N');
                }
                break;
            case "sppiTest002C17":
                if ($('#sppiTest002Q90').val() === NO) {
                    checkDisabledRadio('sppiTest002C17_N');
                }
                else if ($('#sppiTest002Q92').val() === YES) {
                    checkDisabledRadio('sppiTest002C17_Y');
                }
                else if ($('#sppiTest002Q92').val() === NO) {
                    checkDisabledRadio('sppiTest002C17_N');
                }
                break;
            case "sppiTest002C18":
                if ($('#sppiTest002Q93').val() === NO) {
                    checkDisabledRadio('sppiTest002C18_N');
                }
                else if ($('#sppiTest002Q95').val() === YES) {
                    checkDisabledRadio('sppiTest002C18_N');
                }
                else if ($('#sppiTest002Q95').val() === NO) {
                    checkDisabledRadio('sppiTest002C18_Y');
                }
                break;
            case "sppiTest002C19":
                if ($('#sppiTest002Q96').val() === NO) {
                    checkDisabledRadio('sppiTest002C19_N');
                }
                else if ($('#sppiTest002Q98').val() === YES) {
                    checkDisabledRadio('sppiTest002C19_N');
                }
                else if ($('#sppiTest002Q98').val() === NO) {
                    checkDisabledRadio('sppiTest002C19_Y');
                }
                break;
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#sppiTest002Q78').change(function() {
            // 若选择是，判断类型2。类型1中所有字段置灰。
            // 底层资产并表处理，按底层资产类型进行分类计量判断，判断类型2、判断类型1
            if ($(this).val() === YES) {
                typeValue = 2;
                enableAllQaInputs(type2Names);
                enableAllQaInputs(bcdeNames);
                clearFormValues(type1Names, true);
                resetConslusion('sppiTest002C13');
            }
            //若选择否，判断类型1。类型2中所有字段置灰。
            else if ($(this).val() === NO) {
                typeValue = 1;
                enableAllQaInputs(type1Names);
                clearFormValues(type2Names, true);
                //#XMTOF-79(需求不明确) 如果判断类型1的A合同条款，B C D E需要置灰不选择，B C D E属于类型2下的条款判断。
                clearFormValues(bcdeNames, true);
                resetConslusion('sppiTest002C13');
            }
        });

        $('#sppiTest002Q79').change(function() {
            //若选择是，A2置灰，A结果为否。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C14_N');
                uncheckRadioGroup('sppiTest002Q80');
                disableRadioGroup('sppiTest002Q80');
            }
            // 若选择否，继续判断A2。
            else if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q80');
                resetConslusion('sppiTest002C14');
            }
        });

        $('#sppiTest002Q80').change(function() {
            //若选择是，A结果为否。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C14_N');
            }
            // 若选择否，A结果为是。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C14_Y');
            }
        });

        $('#sppiTest002Q81').change(function() {
            //若选择固定利率，A2、A3、A4置灰，继续判断A5
            if ($(this).val() === FIXED_RATE) {
                uncheckRadioGroup('sppiTest002Q82');
                disableRadioGroup('sppiTest002Q82');
                uncheckRadioGroup('sppiTest002Q83');
                disableRadioGroup('sppiTest002Q83');
                uncheckRadioGroup('sppiTest002Q84');
                disableRadioGroup('sppiTest002Q84');
                uncheckRadioGroup('sppiTest002Q85');
                resetConslusion('sppiTest002C15');
            }
            // 若选择浮动利率，继续判断A2、A3、A4、A5
            else if ($(this).val() === FLOAT_RATE) {
                enableRadioGroup('sppiTest002Q82');
                enableRadioGroup('sppiTest002Q83');
                enableRadioGroup('sppiTest002Q84');
                uncheckRadioGroup('sppiTest002Q85');
                resetConslusion('sppiTest002C15');
            }
        });

        $('#sppiTest002Q82').change(function() {
            //若选择是，A3、A4置灰，继续判断A5。
            if ($(this).val() === YES) {
                uncheckRadioGroup('sppiTest002Q83');
                disableRadioGroup('sppiTest002Q83');
                uncheckRadioGroup('sppiTest002Q84');
                disableRadioGroup('sppiTest002Q84');
                resetConslusion('sppiTest002C15');
            }
            // 若选择否，继续判断A3、A4、A5。
            else if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q83');
                enableRadioGroup('sppiTest002Q84');
                resetConslusion('sppiTest002C15');
            }
        });

        $('#sppiTest002Q83').change(function() {
            //若选择是，A4置灰，直接判断A5。
            if ($(this).val() === YES) {
                uncheckRadioGroup('sppiTest002Q84');
                disableRadioGroup('sppiTest002Q84');
                resetConslusion('sppiTest002C15');
            }
            // 若选择否，继续判断A4、A5。
            else if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q84');
                resetConslusion('sppiTest002C15');
            }
        });

        //无论选择是或否，都需要继续判断A5。
        $('#sppiTest002Q84').change(function() {
            resetConslusion('sppiTest002C15');
        });

        //若A4选择是，无论A5做出什么选择，A结果为是。
        //若A4选择否或A4为空（置灰状态）：A5选择否，则A结果为是；A5选择是或不适用，则A结果为否。
        $('#sppiTest002Q85').change(function() {
            if ($(this).val() === YES) {
                if ($('#sppiTest002Q84').val() === YES) {
                    checkDisabledRadio('sppiTest002C15_Y');
                }
                //if ($('#sppiTest002Q84').attr("disabled") || $('#sppiTest002Q84').val() === NO)
                else {
                    checkDisabledRadio('sppiTest002C15_N');
                }
            }
            else if ($(this).val() === NO) {
                if ($('#sppiTest002Q84').val() === YES) {
                    checkDisabledRadio('sppiTest002C15_Y');
                }
                else {
                    checkDisabledRadio('sppiTest002C15_Y');
                }
            }
            else if ($(this).val() === NA) {
                if ($('#sppiTest002Q84').val() === YES) {
                    checkDisabledRadio('sppiTest002C15_Y');
                }
                else {
                    checkDisabledRadio('sppiTest002C15_N');
                }
            }
        });

        $('#sppiTest002Q86').change(function() {
            //若B1选择是，则继续判断B2、B3、B4。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q87');
                enableRadioGroup('sppiTest002Q88');
                enableRadioGroup('sppiTest002Q89');
                resetConslusion('sppiTest002C16');
            }
            // 若B1选择否，则B2、B3、B4置灰，B结果为否。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q87');
                disableRadioGroup('sppiTest002Q87');
                uncheckRadioGroup('sppiTest002Q88');
                disableRadioGroup('sppiTest002Q88');
                uncheckRadioGroup('sppiTest002Q89');
                disableRadioGroup('sppiTest002Q89');
                checkDisabledRadio('sppiTest002C16_N');
            }
        });

        $('#sppiTest002Q87').change(function() {
            //若B2选择是，则继续判断B3、B4。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q88');
                enableRadioGroup('sppiTest002Q89');
                resetConslusion('sppiTest002C16');
            }
            // 若B2选择否，则B3、B4置灰，B结果为是。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q88');
                disableRadioGroup('sppiTest002Q88');
                uncheckRadioGroup('sppiTest002Q89');
                disableRadioGroup('sppiTest002Q89');
                checkDisabledRadio('sppiTest002C16_Y');
            }
        });

        $('#sppiTest002Q88').change(function() {
            //若B3选择是，则继续判断B4。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q89');
                resetConslusion('sppiTest002C16');
            }
            // 若B3选择否，则B4置灰，B结果为是。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q89');
                disableRadioGroup('sppiTest002Q89');
                checkDisabledRadio('sppiTest002C16_Y');
            }
        });

        $('#sppiTest002Q89').change(function() {
            // 若B4选择是，则B结果为否
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C16_N');
            }
            //若B4选择否，则B结果为是
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C16_Y');
            }
        });

        $('#sppiTest002Q90').change(function() {
            //若C1选择是，则继续判断C2、C3。
            if ($(this).val() === YES) {
                $('#sppiTest002Q91').attr("disabled",false);
                enableRadioGroup('sppiTest002Q92');
                resetConslusion('sppiTest002C17');
            }
            // 若C1选择否，则C2、C3置灰，C结果为否。
            else if ($(this).val() === NO) {
                $('#sppiTest002Q91').val('');
                $('#sppiTest002Q91').attr("disabled",true);
                uncheckRadioGroup('sppiTest002Q92');
                disableRadioGroup('sppiTest002Q92');
                checkDisabledRadio('sppiTest002C17_N');
            }
        });

        $('#sppiTest002Q92').change(function() {
            //若C3选择是，则C结果为是。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C17_Y');
            }
            // 若C3选择否，则C结果为否。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C17_N');
            }
        });

        $('#sppiTest002Q93').change(function() {
            //若D1选择是，则继续判断D2、D3。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q94');
                enableRadioGroup('sppiTest002Q95');
                resetConslusion('sppiTest002C18');
            }
            // 若D1选择否，则D2、D3置灰，D结果为否。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q94');
                disableRadioGroup('sppiTest002Q94');
                uncheckRadioGroup('sppiTest002Q95');
                disableRadioGroup('sppiTest002Q95');
                checkDisabledRadio('sppiTest002C18_N');
            }
        });

        //无论选择是或否，都需要继续判断D3。
        $('#sppiTest002Q94').change(function() {
            resetConslusion('sppiTest002C18');
        });

        $('#sppiTest002Q95').change(function() {
            //若D3选择是，D结果为否。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C18_N');
            }
            // 若D3选择否，D结果为是。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C18_Y');
            }
        });

        $('#sppiTest002Q96').change(function() {
            //若E1选择是，则继续判断E2、E3。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q97');
                enableRadioGroup('sppiTest002Q98');
                resetConslusion('sppiTest002C19');
            }
            // 若E1选择否，则E2、E3置灰，E结果为否。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q97');
                disableRadioGroup('sppiTest002Q97');
                uncheckRadioGroup('sppiTest002Q98');
                disableRadioGroup('sppiTest002Q98');
                checkDisabledRadio('sppiTest002C19_N');
            }
        });

        //无论选择是或否，都需要继续判断E3。
        $('#sppiTest002Q97').change(function() {
            resetConslusion('sppiTest002C19');
        });

        $('#sppiTest002Q98').change(function() {
            //若E3选择是，E结果为否。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C19_N');
            }
            // 若E3选择否，E结果为是。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C19_Y');
            }
        });

        $("input[id^='sppiTest002C']").each(function () {
            let eventName = $(this).attr("name");
            $('#' + eventName).change(function() {
                if (eventName != "sppiTest002Result") {
                    if (typeValue == 1) {
                        if ($('#sppiTest002C14').val() === YES) {
                            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);//不通过
                        }
                        else {
                            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);//通过
                        }
                    }
                    else {
                        if ($('#sppiTest002C15').val() === NO && $('#sppiTest002C16').val() === NO && $('#sppiTest002C17').val() === NO
                            && $('#sppiTest002C18').val() === NO && $('#sppiTest002C19').val() === NO) {
                            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);//通过
                        } else {
                            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);//不通过
                        }
                    }
                }
            });
        });

    });
}());
