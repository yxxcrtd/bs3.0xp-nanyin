/**
 * 非标准化债权资产SPPI测试新建（毕马威模板）测试条款JS联动处理
 */
(function() {
    //A3 A4 A5 类型1、2的问题名称集合
    var qa345T12Names = [];
    var qaT123Names = [];
    var qa45T13Names = [
        'sppiTest002Q102', 'sppiTest002Q103',
        'sppiTest002Q104', 'sppiTest002Q105', 'sppiTest002Q106', 'sppiTest002Q107', 'sppiTest002Q108',
        'sppiTest002Q112', 'sppiTest002Q113', 'sppiTest002Q114', 'sppiTest002Q115', 'sppiTest002Q116', 'sppiTest002Q117', 'sppiTest002Q118', 'sppiTest002Q119'
    ];
    var qa5T23Names = [
        'sppiTest002Q103',
        'sppiTest002Q109', 'sppiTest002Q110', 'sppiTest002Q111',
        'sppiTest002Q112', 'sppiTest002Q113', 'sppiTest002Q114', 'sppiTest002Q115', 'sppiTest002Q116', 'sppiTest002Q117', 'sppiTest002Q118', 'sppiTest002Q119'
    ];
    var qaT1Names = [
        'sppiTest002Q104', 'sppiTest002Q105', 'sppiTest002Q106', 'sppiTest002Q107', 'sppiTest002Q108'
    ];
    var qaT2Names = [
        'sppiTest002Q109', 'sppiTest002Q110', 'sppiTest002Q111'
    ];
    var qaT3Names = [
        'sppiTest002Q112', 'sppiTest002Q113', 'sppiTest002Q114', 'sppiTest002Q115', 'sppiTest002Q116', 'sppiTest002Q117', 'sppiTest002Q118', 'sppiTest002Q119'
    ];
    for (var i = 101; i <= 111; i++) {
        qa345T12Names.push('sppiTest002Q' + i);
    }
    for (var i = 104; i <= 119; i++) {
        qaT123Names.push('sppiTest002Q' + i);
    }

    //重设结论值
    var resetConslusion = function() {
        uncheckRadioGroup('sppiTest002Result');
        if ($('#sppiTest002Q99').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q103').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q104').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q105').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q106').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q107').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q108').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q108').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
        else if ($('#sppiTest002Q109').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q110').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q111').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
        else if ($('#sppiTest002Q111').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q112').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q113').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q114').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q115').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q116').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q118').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q119').val() === NO) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
        }
        else if ($('#sppiTest002Q119').val() === YES) {
            checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
        }
    }

    /**
     * 画面加载完成
     */
    $(function() {
        $('#sppiTest002Q99').change(function() {
            let disableNames = [];
            $('input[id*="sppiTest002Q"]').each(function () {
                let eleName = $(this).attr("name");
                if ('sppiTest002Q99' != eleName) {
                    disableNames.push(eleName);
                }
            });

            //A1判断为是，则不能通过SPPI测试，不需进行下一步的判断。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(disableNames, true);
            }
            //A1判断为否，进行A2的判断
            else if ($(this).val() === NO) {
                enableAllQaInputs(disableNames);
                resetConslusion();
            }
        });

        $('#sppiTest002Q100').change(function() {
            //选择是，适用以下类型3合同条款的判断
            if ($(this).val() === YES) {
                //disable A3 A4 A5 类型1、2
                clearFormValues(qa345T12Names, true);
                //enable 类型3
                enableAllQaInputs(qaT3Names);
                resetConslusion();
            }
            // 若选择否，进行A3的判断。
            else if ($(this).val() === NO) {
                //disable 类型3
                clearFormValues(qaT3Names, true);
                enableAllQaInputs(qa345T12Names);
                resetConslusion();
            }
        });

        $('#sppiTest002Q101').change(function() {
            //为是，进行类型2-合同链接工具的合同条款判断
            if ($(this).val() === YES) {
                //disable A4 A5 类型1、3
                clearFormValues(qa45T13Names, true);
                //enable 类型2
                enableAllQaInputs(qaT2Names);
                resetConslusion();
            }
            // 如A3为否，则进行A4的判断
            else if ($(this).val() === NO) {
                //disable 类型2
                clearFormValues(qaT2Names, true);
                enableAllQaInputs(qa45T13Names);
                resetConslusion();
            }
        });

        $('#sppiTest002Q102').change(function() {
            //为是，则进行类型1的判断，
            if ($(this).val() === YES) {
                //disable A5 类型2、3
                clearFormValues(qa5T23Names, true);
                //enable 类型1
                enableAllQaInputs(qaT1Names);
                resetConslusion();
            }
            // 若A4选择为否，则判断A5。
            else if ($(this).val() === NO) {
                //disable 类型1
                clearFormValues(qaT1Names, true);
                enableAllQaInputs(qa5T23Names);
                resetConslusion();
            }
        });

        $('#sppiTest002Q103').change(function() {
            //为是，则进行类型1的判断。
            if ($(this).val() === YES) {
                //disable 类型2、3
                clearFormValues(qa5T23Names.slice(1), true);
                //enable 类型1
                enableAllQaInputs(['sppiTest002Q104', 'sppiTest002Q105', 'sppiTest002Q106', 'sppiTest002Q107', 'sppiTest002Q108']);
                resetConslusion();
            }
            // 若选择否，则不可通过SPPI的测试，不需进行下一步的判断。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                // disable类型1、2、3
                clearFormValues(qaT123Names, true);
            }
        });

        $('#sppiTest002Q104').change(function() {
            // 否则，进行B2的判断。
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT1Names.slice(1));
                resetConslusion();
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(1), true);
            }
        });

        $('#sppiTest002Q105').change(function() {
            // 否则，进行B3的判断。
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT1Names.slice(2));
                resetConslusion();
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(2), true);
            }
        });

        $('#sppiTest002Q106').change(function() {
            // 否则选择“是”，继续回答B4.
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT1Names.slice(3));
                resetConslusion();
            }
            //选择“否”，直接得出利率条件不通过SPPI测试的结论；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(3), true);
            }
        });

        $('#sppiTest002Q107').change(function() {
            // 否则，进行B5的判断。
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT1Names.slice(4));
                resetConslusion();
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(4), true);
            }
        });

        $('#sppiTest002Q108').change(function() {
            // 否则，能通过SPPI的测试。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
            }
            clearFormValues(qaT123Names.slice(5), true);
        });

        $('#sppiTest002Q109').change(function() {
            //如C1为是，进行C2的判断，
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT2Names.slice(1));
                resetConslusion();
            }
            // 如C1为否，则不能通过SPPI测试
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(6), true);
            }
        });

        $('#sppiTest002Q110').change(function() {
            //如C2为是，进行C3的判断
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT2Names.slice(2));
                resetConslusion();
            }
            // 如C2为否，则不能通过SPPI测试
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(7), true);
            }
        });

        $('#sppiTest002Q111').change(function() {
            //如C3为是，通过SPPI测试
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
            }
            // 如C3为否，则不能通过SPPI测试
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
            }
            clearFormValues(qaT123Names.slice(8), true);
        });

        $('#sppiTest002Q112').change(function() {
            //选择“是”，直接得出分类判断结论为不能通过SPPI
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(9), true);
            }
            // 否则选择“否”，跳转到D2
            else if ($(this).val() === NO) {
                enableAllQaInputs(qaT3Names.slice(1));
                resetConslusion();
            }
        });

        $('#sppiTest002Q113').change(function() {
            // 否则选择“是”，继续回答D3.
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT3Names.slice(2));
                resetConslusion();
            }
            //选择“否”，直接得出利率条件不通过SPPI测试的结论
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(10), true);
            }
        });

        $('#sppiTest002Q114').change(function() {
            // 否则，进行D4的判断。
            if ($(this).val() === YES) {
                enableAllQaInputs(qaT3Names.slice(3));
                resetConslusion();
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(11), true);
            }
        });

        $('#sppiTest002Q115').change(function() {
            // 选择为“是”或“不适用"，进行D5的判断。
            if ($(this).val() === YES || $(this).val() === NA) {
                enableAllQaInputs(qaT3Names.slice(4));
                resetConslusion();
            }
            //选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(12), true);
            }
        });

        $('#sppiTest002Q116').change(function() {
            // 选择为“是”或“不适用"，进行D6的判断
            if ($(this).val() === YES || $(this).val() === NA) {
                enableAllQaInputs(qaT3Names.slice(5));
                resetConslusion();
            }
            //选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                clearFormValues(qaT123Names.slice(13), true);
            }
        });

        $('#sppiTest002Q117').change(function() {
            enableRadioGroup('sppiTest002Q119');
            // 选择为“是”或“不适用"，进行D7的判断。
            if ($(this).val() === YES || $(this).val() === NA) {
                uncheckRadioGroup('sppiTest002Q118');
                disableRadioGroup('sppiTest002Q118');
            }
            //选择为“否”，则进行D6.1测试；
            else if ($(this).val() === NO) {
                enableRadioGroup('sppiTest002Q118');
            }
            resetConslusion();
        });

        $('#sppiTest002Q118').change(function() {
            //若选择为“是”，则不能通过SPPI测试；
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
                uncheckRadioGroup('sppiTest002Q119');
                disableRadioGroup('sppiTest002Q119');
            }
            // 选择为“否”或“不适用"，进行D7的判断。
            else if ($(this).val() === NO || $(this).val() === NA) {
                enableRadioGroup('sppiTest002Q119');
                resetConslusion();
            }
        });

        $('#sppiTest002Q119').change(function() {
            // 否则，能通过SPPI的测试。
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);
            }
            //若选择为“否”，则不能通过SPPI测试；
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);
            }
        });

    });
}());
