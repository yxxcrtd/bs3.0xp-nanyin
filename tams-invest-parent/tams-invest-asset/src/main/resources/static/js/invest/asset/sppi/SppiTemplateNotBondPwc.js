/**
 * 非标准化债权资产SPPI测试新建（普华永道模板）测试条款JS联动处理
 */
(function() {
    //3、4段的问题名称集合
    var qa34Names = [];
    for (var i = 58; i <= 77; i++) {
        qa34Names.push('sppiTest002Q' + i);
    }

    //重设结论值
    var resetConslusion = function() {
        uncheckRadioGroup('sppiTest002Result');

        if ($('#sppiTest002Q53').val() === 'N') {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        if ($('#sppiTest002Q55').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q56').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q57').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q58').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q59').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q60').val() === 'D') {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q61').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q66').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q67').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q68').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q70').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q73').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q76').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q77').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
        else if ($('#sppiTest002Q77').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#sppiTest002Q53').change(function() {
            let disableNames = [];
            $('input[id*="sppiTest002Q"]').each(function () {
                let eleName = $(this).attr("name");
                if ('sppiTest002Q53' != eleName) {
                    disableNames.push(eleName);
                }
            });

            // 若勾选第1、2项，则进入2
            if ($(this).val() === 'B' || $(this).val() === 'E') {
                enableAllQaInputs(disableNames);
                resetConslusion();
            }
            //如勾选第3项，则测试结果直接联动为不通过，其他条件置灰;
            else if ($(this).val() === 'N') {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(disableNames, true);
            }
        });

        //如任意一项勾选是则无法通过SPPI测试,测试结果联动为不通过；若均勾选否则进入3
        $('#sppiTest002Q55').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                // disable 3、4
                clearFormValues(qa34Names, true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q55').val() === NO && $('#sppiTest002Q56').val() === NO && $('#sppiTest002Q57').val() === NO) {
                    enableAllQaInputs(qa34Names);
                }
                resetConslusion();
            }
        });

        $('#sppiTest002Q56').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                // disable 3、4
                clearFormValues(qa34Names, true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q55').val() === NO && $('#sppiTest002Q56').val() === NO && $('#sppiTest002Q57').val() === NO) {
                    enableAllQaInputs(qa34Names);
                }
                resetConslusion();
            }
        });

        $('#sppiTest002Q57').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                // disable 3、4
                clearFormValues(qa34Names, true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q55').val() === NO && $('#sppiTest002Q56').val() === NO && $('#sppiTest002Q57').val() === NO) {
                    enableAllQaInputs(qa34Names);
                }
                resetConslusion();
            }
        });

        $('#sppiTest002Q58').change(function() {
            // 如勾选是则进入3.2
            if ($(this).val() === YES) {
                enableAllQaInputs(qa34Names.slice(1));
                resetConslusion();
            }
            //如勾选否则无法通过SPPI测试，测试结果联动为不通过，剩余字段置灰；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(1), true);
            }
        });

        //如是则无法通过SPPI测试，测试结果联动为不通过；如选择否则继续判断3.3
        $('#sppiTest002Q59').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(2), true);
            }
            else if ($(this).val() === NO) {
                enableAllQaInputs(qa34Names.slice(2));
                resetConslusion();
            }
        });

        //如果是动态管理，测试结果联动为不通过；如选择静态管理则继续判断3.4
        $('#sppiTest002Q60').change(function() {
            if ($(this).val() === 'D') {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(3), true);
            }
            else if ($(this).val() === 'S') {
                enableAllQaInputs(qa34Names.slice(3));
                resetConslusion();
            }
        });

        //如是则无法通过SPPI测试，测试结果联动为不通过；如选择否则继续判断3.5
        $('#sppiTest002Q61').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(4), true);
            }
            else if ($(this).val() === NO) {
                enableAllQaInputs(qa34Names.slice(4));
                resetConslusion();
            }
        });

        $('#sppiTest002Q65').change(function() {
            // 如果选是，新增选项，对（1）（2）（3）进行勾选
            if ($(this).val() === YES) {
                enableAllQaInputs(qa34Names.slice(8));
                resetConslusion();
            }
            //如选择否不影响测试结果，进入3.9继续判断；
            else if ($(this).val() === NO) {
                //disable 3.8.1-3.8.3
                uncheckRadioGroup('sppiTest002Q66');
                disableRadioGroup('sppiTest002Q66');
                uncheckRadioGroup('sppiTest002Q67');
                disableRadioGroup('sppiTest002Q67');
                uncheckRadioGroup('sppiTest002Q68');
                disableRadioGroup('sppiTest002Q68');
                enableAllQaInputs(qa34Names.slice(11));
                resetConslusion();
            }
        });

        //如任一有勾选则无法通过SPPI测试，测试结果联动为不通过；如均选择否则进入3.9继续判断；
        $('#sppiTest002Q66').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(11), true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q66').val() === NO && $('#sppiTest002Q67').val() === NO && $('#sppiTest002Q68').val() === NO) {
                    enableAllQaInputs(qa34Names.slice(11));
                }
                resetConslusion();
            }
        });

        $('#sppiTest002Q67').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(11), true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q66').val() === NO && $('#sppiTest002Q67').val() === NO && $('#sppiTest002Q68').val() === NO) {
                    enableAllQaInputs(qa34Names.slice(11));
                }
                resetConslusion();
            }
        });

        $('#sppiTest002Q68').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(11), true);
            }
            else if ($(this).val() === NO) {
                // enable 3、4
                if ($('#sppiTest002Q66').val() === NO && $('#sppiTest002Q67').val() === NO && $('#sppiTest002Q68').val() === NO) {
                    enableAllQaInputs(qa34Names.slice(11));
                }
                resetConslusion();
            }
        });

        //如是则不通过SPPI测试，系统测试结果联动为不通过，如否则进入4.
        $('#sppiTest002Q70').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(13), true);
            }
            else if ($(this).val() === NO) {
                enableAllQaInputs(qa34Names.slice(13));
                resetConslusion();
            }
        });

        //如勾选匹配则进入4.2；如勾选不匹配生成输入框录入期限差异的现金流安排
        $('#sppiTest002Q71').change(function() {
            if ($(this).val() === YES) {
                $('#sppiTest002Q72').val('');
                $('#sppiTest002Q72').attr("disabled", true);
                enableAllQaInputs(qa34Names.slice(15));
                resetConslusion();
            }
            else if ($(this).val() === NO) {
                enableAllQaInputs(qa34Names.slice(14));
                resetConslusion();
            }
        });

        //如勾选匹配则进入4.3；如勾选不匹配则测试不通过
        $('#sppiTest002Q73').change(function() {
            if ($(this).val() === YES) {
                enableAllQaInputs(qa34Names.slice(16));
                resetConslusion();
            }
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(16), true);
            }
        });

        //如勾选匹配则进入4.4；如勾选不匹配生成输入框录入金额差异的现金流安排
        $('#sppiTest002Q74').change(function() {
            if ($(this).val() === YES) {
                $('#sppiTest002Q75').val('');
                $('#sppiTest002Q75').attr("disabled", true);
                enableAllQaInputs(qa34Names.slice(18));
                resetConslusion();
            }
            else if ($(this).val() === NO) {
                enableAllQaInputs(qa34Names.slice(17));
                resetConslusion();
            }
        });

        //如勾选匹配则进入4.5；如勾选不匹配则测试不通过
        $('#sppiTest002Q76').change(function() {
            if ($(this).val() === YES) {
                enableAllQaInputs(qa34Names.slice(19));
                resetConslusion();
            }
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qa34Names.slice(19), true);
            }
        });

        //如勾选匹配则测试通过；如勾选不匹配则测试不通过
        $('#sppiTest002Q77').change(function() {
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
            }
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
            }
        });

    });
}());
