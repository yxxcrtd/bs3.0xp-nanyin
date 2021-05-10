/**
 * 债券类资产SPPI测试新建（毕马威模板）测试条款JS联动处理
 */
(function() {
    var qaNames = [];
    for (var i = 45; i <= 52; i++) {
        qaNames.push('sppiTest002Q' + i);
    }

    //重设结论值
    var resetConslusion = function() {
        uncheckRadioGroup('sppiTest002Result');
        if ($('#sppiTest002Q45').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q46').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q47').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q48').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q49').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q51').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q52').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q52').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
        else if ($('#sppiTest002Q52').val() === NA) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#sppiTest002Q45').change(function() {
            //选择“是”，直接得出分类判断结论为不能通过SPPI；
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(1), true);
            }
            //否则选择“否”，跳转到A2。
            else if ($(this).val() === NO) {
                enableAllQaInputs(qaNames.slice(1));
                resetConslusion();
            }
        });

        $('#sppiTest002Q46').change(function() {
            // 否则选择“是”，继续回答A3.
            if ($(this).val() === YES) {
                enableAllQaInputs(qaNames.slice(2));
                resetConslusion();
            }
            //选择“否”，直接得出利率条件不通过SPPI测试的结论；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(2), true);
            }
        });

        $('#sppiTest002Q47').change(function() {
            //否则，进行A4的判断。
            if ($(this).val() === YES) {
                enableAllQaInputs(qaNames.slice(3));
                resetConslusion();
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(3), true);
            }
        });

        $('#sppiTest002Q48').change(function() {
            //选择为“否”，则不能通过SPPI测试；
            if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(4), true);
            }
            //选择为“是”或“不适用"，进行A5的判断。
            else if ($(this).val() === YES || $(this).val() === NA) {
                enableAllQaInputs(qaNames.slice(4));
                resetConslusion();
            }
        });

        $('#sppiTest002Q49').change(function() {
            //选择为“否”，则不能通过SPPI测试；
            if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(5), true);
            }
            //选择为“是”或“不适用"，进行A6的判断。
            else if ($(this).val() === YES || $(this).val() === NA) {
                enableAllQaInputs(qaNames.slice(5));
                resetConslusion();
            }
        });

        $('#sppiTest002Q50').change(function() {
            //若选择为“否”，则进行A6.1测试；
            if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q51');
                resetConslusion();
            }
            // 选择为“是”或“不适用"，进行A7的判断。
            else if ($(this).val() === YES || $(this).val() === NA) {
                uncheckRadioGroup('sppiTest002Q51');
                disableRadioGroup('sppiTest002Q51');
                resetConslusion();
            }
        });

        $('#sppiTest002Q51').change(function() {
            //若选择为“是”，则不能通过SPPI测试；
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaNames.slice(7), true);
            }
            // 选择为“否”或“不适用"，进行A7的判断。
            else if ($(this).val() === NO || $(this).val() === NA) {
                enableAllQaInputs(qaNames.slice(7));
                resetConslusion();
            }
        });

        $('#sppiTest002Q52').change(function() {
            if ($(this).val()){
                //若选择为“否”，则不能通过SPPI测试；
                if ($(this).val() === NO) {
                    checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                }
                // 否则，能通过SPPI的测试。
                else {
                    checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
                }
            }
        });

    });
}());
