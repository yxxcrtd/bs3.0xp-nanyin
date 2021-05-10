/**
 * 债券类资产SPPI测试新建（德勤模板）测试条款JS联动处理
 */
(function() {
    //重设结论值
    var resetConslusion = function(cname) {
        uncheckRadioGroup(cname);
        switch (cname) {
            //region 由A1-A4由上到下顺序规则联动带出，字段置灰，不可改。
            case "sppiTest002C7":
                //若A3选择是，无论A4字段值是什么，A结论联动显示为是且不可修改。
                if ($('#sppiTest002Q30').val() === YES) {
                    checkDisabledRadio('sppiTest002C7_Y');
                }
                //若A3选择否或A3为空（置灰状态）：A4选择否，则A结论为是；A4选择是或不适用，则A结论联动显示为否且不可修改。
                else if ($('#sppiTest002Q30').attr("disabled") || $('#sppiTest002Q30').val() === NO) {
                    if ($('#sppiTest002Q31').val() === NO) {
                        checkDisabledRadio('sppiTest002C7_Y');
                    }
                    else if ($('#sppiTest002Q31').val() === YES || $('#sppiTest002Q31').val() === NA) {
                        checkDisabledRadio('sppiTest002C7_N');
                    }
                }
                break;
            //endregion
            //region 根据B1-B4由上到下顺序规则联动带出，字段置灰，不可改。
            case "sppiTest002C8":
                // 若B1选择否，则B2、B3、B4置灰，B结论联动显示为否且不可修改。
                if ($('#sppiTest002Q32').val() === NO) {
                    checkDisabledRadio('sppiTest002C8_N');
                }
                // 若B2选择否，则B3、B4置灰，B结论联动显示为是且不可修改。
                else if ($('#sppiTest002Q33').val() === NO) {
                    checkDisabledRadio('sppiTest002C8_Y');
                }
                // 若B3选择否，则B4置灰，B结论联动显示为是且不可修改。
                else if ($('#sppiTest002Q34').val() === NO) {
                    checkDisabledRadio('sppiTest002C8_Y');
                }
                //若B4选择否，则B结论联动显示为是且不可修改。
                else if ($('#sppiTest002Q35').val() === NO) {
                    checkDisabledRadio('sppiTest002C8_Y');
                }
                // 若B4选择是，则B结论联动显示为否且不可修改
                else if ($('#sppiTest002Q35').val() === YES) {
                    checkDisabledRadio('sppiTest002C8_N');
                }
                break;
             //endregion
            case "sppiTest002C9":
                // 若C1选择否，则C2、C3置灰，C结论联动展示为否且不可修改。
                if ($('#sppiTest002Q36').val() === NO) {
                    checkDisabledRadio('sppiTest002C9_N');
                }
                else if ($('#sppiTest002Q38').val() === YES) {
                    checkDisabledRadio('sppiTest002C9_Y');
                }
                else if ($('#sppiTest002Q38').val() === NO) {
                    checkDisabledRadio('sppiTest002C9_N');
                }
                break;
            case "sppiTest002C10":
                if ($('#sppiTest002Q39').val() === NO) {
                    checkDisabledRadio('sppiTest002C10_N');
                }
                else if ($('#sppiTest002Q41').val() === YES) {
                    checkDisabledRadio('sppiTest002C10_Y');
                }
                else if ($('#sppiTest002Q41').val() === NO) {
                    checkDisabledRadio('sppiTest002C10_N');
                }
                break;
            case "sppiTest002C11":
                if ($('#sppiTest002Q42').val() === NO) {
                    checkDisabledRadio('sppiTest002C11_N');
                }
                else if ($('#sppiTest002Q44').val() === YES) {
                    checkDisabledRadio('sppiTest002C11_N');
                }
                else if ($('#sppiTest002Q44').val() === NO) {
                    checkDisabledRadio('sppiTest002C11_Y');
                }
                break;
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#sppiTest002Q28').change(function() {
            //若选择固定利率，则A2、A3，置灰，继续判断A4。
            if ($(this).val() === FIXED_RATE) {
                uncheckRadioGroup('sppiTest002Q29');
                disableRadioGroup('sppiTest002Q29');
                uncheckRadioGroup('sppiTest002Q30');
                disableRadioGroup('sppiTest002Q30');
                uncheckRadioGroup('sppiTest002Q31');
                uncheckRadioGroup('sppiTest002C7');
            }
            //若选择浮动利率，则继续A2、A3、A4的判断。
            else if ($(this).val() === FLOAT_RATE) {
                enableRadioGroup('sppiTest002Q29');
                enableRadioGroup('sppiTest002Q30');
                uncheckRadioGroup('sppiTest002Q31');
            }
        });

        $('#sppiTest002Q29').change(function() {
            //若选择是，则A3置灰，继续判断A4
            if ($(this).val() === YES) {
                uncheckRadioGroup('sppiTest002Q30');
                disableRadioGroup('sppiTest002Q30');
            }
            // 若选择否，则继续A3、A4的判断。
            else if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q30');
            }
        });

        $('#sppiTest002Q30').change(function() {
            // 无论选择是或否，都需要继续判断A4。防止反向操作
            resetConslusion('sppiTest002C7');
        });

        $('#sppiTest002Q31').change(function() {
            resetConslusion('sppiTest002C7');
        });

        $('#sppiTest002Q32').change(function() {
            // 若B1选择是，则继续B2、B3、B4的判断。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q33');
                enableRadioGroup('sppiTest002Q34');
                enableRadioGroup('sppiTest002Q35');
                resetConslusion('sppiTest002C8');
            }
            // 若B1选择否，则B2、B3、B4置灰，B结论联动显示为否且不可修改。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q33');
                disableRadioGroup('sppiTest002Q33');
                uncheckRadioGroup('sppiTest002Q34');
                disableRadioGroup('sppiTest002Q34');
                uncheckRadioGroup('sppiTest002Q35');
                disableRadioGroup('sppiTest002Q35');
                checkDisabledRadio('sppiTest002C8_N');
            }
        });

        $('#sppiTest002Q33').change(function() {
            //若B2选择是，则继续B3、B4的判断。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q34');
                enableRadioGroup('sppiTest002Q35');
                resetConslusion('sppiTest002C8');
            }
            // 若B2选择否，则B3、B4置灰，B结论联动显示为是且不可修改。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q34');
                disableRadioGroup('sppiTest002Q34');
                uncheckRadioGroup('sppiTest002Q35');
                disableRadioGroup('sppiTest002Q35');
                checkDisabledRadio('sppiTest002C8_Y');
            }
        });

        $('#sppiTest002Q34').change(function() {
            //若B3选择是，则继续B4判断。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q35');
                resetConslusion('sppiTest002C8');
            }
            // 若B3选择否，则B4置灰，B结论联动显示为是且不可修改。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q35');
                disableRadioGroup('sppiTest002Q35');
                checkDisabledRadio('sppiTest002C8_Y');
            }
        });

        $('#sppiTest002Q35').change(function() {
            // 若B4选择是，则B结论联动显示为否且不可修改
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C8_N');
            }
            //若B4选择否，则B结论联动显示为是且不可修改。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C8_Y');
            }
        });

        $('#sppiTest002Q36').change(function() {
            //若C1选择是，则继续C2、C3的判断。
            if ($(this).val() === YES) {
                $('#sppiTest002Q37').attr("disabled",false);
                enableRadioGroup('sppiTest002Q38');
                resetConslusion('sppiTest002C9');
            }
            // 若C1选择否，则C2、C3置灰，C结论联动展示为否且不可修改。
            else if ($(this).val() === NO) {
                $('#sppiTest002Q37').val('');
                $('#sppiTest002Q37').attr("disabled",true);
                uncheckRadioGroup('sppiTest002Q38');
                disableRadioGroup('sppiTest002Q38');
                checkDisabledRadio('sppiTest002C9_N');
            }
        });

        $('#sppiTest002Q38').change(function() {
            //若C3选择是，则C结论联动展示为是且不可修改。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C9_Y');
            }
            // 若C3选择否，则C结论联动展示为否且不可修改。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C9_N');
            }
        });

        $('#sppiTest002Q39').change(function() {
            //若D1选择是，则继续D2、D3的判断。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q40');
                enableRadioGroup('sppiTest002Q41');
                resetConslusion('sppiTest002C10');
            }
            // 若D1选择否，则D2、D3置灰，D结论联动展示为否且不可修改。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q40');
                disableRadioGroup('sppiTest002Q40');
                uncheckRadioGroup('sppiTest002Q41');
                disableRadioGroup('sppiTest002Q41');
                checkDisabledRadio('sppiTest002C10_N');
            }
        });

        //无论选择是或否，都需要继续判断D3。
        $('#sppiTest002Q40').change(function() {
            resetConslusion('sppiTest002C10');
        });

        $('#sppiTest002Q41').change(function() {
            //若D3选择是，D结论联动展示为是且不可修改。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C10_Y');
            }
            // 若D3选择否，D结论联动展示为否且不可修改。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C10_N');
            }
        });

        $('#sppiTest002Q42').change(function() {
            //若E1选择是，则继续E2、E3的判断。
            if ($(this).val() === YES) {
                enableRadioGroup('sppiTest002Q43');
                enableRadioGroup('sppiTest002Q44');
                resetConslusion('sppiTest002C11');
            }
            // 若E1选择否，则E2、E3置灰，E结论联动展示为否且不可修改。
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q43');
                disableRadioGroup('sppiTest002Q43');
                uncheckRadioGroup('sppiTest002Q44');
                disableRadioGroup('sppiTest002Q44');
                checkDisabledRadio('sppiTest002C11_N');
            }
        });

        //无论选择是或否，都需要继续判断E3
        $('#sppiTest002Q43').change(function() {
            resetConslusion('sppiTest002C11');
        });

        $('#sppiTest002Q44').change(function() {
            //若E3选择是，E结论联动展示为否且不可修改。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C11_N');
            }
            // 若E3选择否，E结论联动展示为是且不可修改。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C11_Y');
            }
        });

        $("input[id^='sppiTest002C']").each(function () {
            let eventName = $(this).attr("name");
            $('#' + eventName).change(function() {
                //如果五个结论都为否，则SPPI测试结果通过。否则，联动显示为“不通过”。
                if (eventName != "sppiTest002Result") {
                    if ($('#sppiTest002C7').val() === NO && $('#sppiTest002C8').val() === NO && $('#sppiTest002C9').val() === NO
                        && $('#sppiTest002C10').val() === NO && $('#sppiTest002C11').val() === NO) {
                        checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);//通过
                    } else {
                        checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);//不通过
                    }
                }
            });
        });

    });
}());
