/**
 * 债券类资产SPPI测试新建（普华永道模板）测试条款JS联动处理
 */
(function() {
    //重设结论值
    var resetConslusion = function(cname) {
        uncheckRadioGroup(cname);
        switch (cname) {
            //region 根据子条款1.1.1、1.1.2、1.2.1~1.2.12选项联动显示，置灰，不可改。
            case "sppiTest002C1":
                let a = true, b = false;
                for (var i=1; i<=14; i++) {
                    if (i < 3) {
                        // 1.1.1、1.1.2 只要一个“是”，就为false（通过）
                        a = a && !($('#sppiTest002Q' + i).val() === YES);
                    }
                    else {
                        // 1.2.1~1.2.12 只要一个“是”，就为true（不通过）
                        b = b || ($('#sppiTest002Q' + i).val() === YES);
                    }
                }

                //a && b 不通过
                // a && !b 通过
                // !a && !b 通过
                // !a && b 不可能发生
                if (a && b) {
                    checkDisabledRadio('sppiTest002C1_Y');//不通过
                }
                else if (!b) {
                    checkDisabledRadio('sppiTest002C1_N');//通过
                }
                break;
            //endregion
            //region 根据子条款2.1.1~2.1.3选项联动显示。置灰，不可改。
            case "sppiTest002C2":
                let c = false;
                for (var i=15; i<=19; i++) {
                    //只要一个“否”，就为true（不通过）
                    c = c || ($('#sppiTest002Q' + i).val() === NO);
                }
                if (c) {
                    checkDisabledRadio('sppiTest002C2_Y');//不通过
                }
                else {
                    checkDisabledRadio('sppiTest002C2_N');//通过
                }
                break;
            //endregion
            //region 根据条款2.2.1及其子条款2.2.1.1与2.2.1.2选项联动显示，置灰，不可改
            case "sppiTest002C3":
                let d = false;
                for (var i=21; i<=22; i++) {
                    //只要一个“是”，就为true（不通过）
                    d = d || ($('#sppiTest002Q' + i).val() === YES);
                }
                if (d) {
                    checkDisabledRadio('sppiTest002C3_Y');//不通过
                }
                else {
                    checkDisabledRadio('sppiTest002C3_N');//通过
                }
                break;
            //endregion
            case "sppiTest002C4":
                if ($('#sppiTest002Q23').val() === YES) {
                    checkDisabledRadio('sppiTest002C4_N');
                }
                else if ($('#sppiTest002Q24').val() === NO) {
                    checkDisabledRadio('sppiTest002C4_Y');
                }
                else if ($('#sppiTest002Q24').val() === YES && $('#sppiTest002Q23').val() === NO) {
                    checkDisabledRadio('sppiTest002C4_N');
                }

                break;
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        //若选“是”，则条款1.1.2与1.2、2.1、2.2、2.3、2.4及其下所有子条款全部清空并隐藏，同时本条款结论1联动显示为“否”。
        $('#sppiTest002Q1').change(function() {
            if ($(this).val() === YES) {
                //条款1.1.2
                $('#sppiTest002Q2_itemDiv').hide();
                addHideId('sppiTest002Q2_itemDiv');
                //1.2
                $('#p12').hide();
                addHideId('p12');
                //2.1、2.2、2.3、2.4
                $('#p2').hide();
                addHideId('p2');
                // 清空上面所述的元素值或选中状态
                let elementNames = [];
                for (var i = 2; i <= 27; i++) {
                    elementNames.push('sppiTest002Q' + i);
                }
                clearFormValues(elementNames);

                //结论1联动显示为“否”
                checkDisabledRadio('sppiTest002C1_N');
            }
            // 若选“否”，则不影响本条款结论1，继续其他条款判断。
            else if ($(this).val() === NO) {
                //对上面造成的影响进行复原
                $('#sppiTest002Q2_itemDiv').show();
                removeHideId('sppiTest002Q2_itemDiv');
                $('#p12').show();
                removeHideId('p12');
                $('#p2').show();
                removeHideId('p2');
                resetConslusion('sppiTest002C1');
            }
        });

        //若选“是”，则条款1.2、2.1、2.2、2.3、2.4及其下所有子条款全部清空并隐藏，同时本条款结论1联动显示为“否”
        $('#sppiTest002Q2').change(function() {
            if ($(this).val() === YES) {
                $('#p12').hide();
                addHideId('p12');
                $('#p2').hide();
                addHideId('p2');
                let elementNames = [];
                for (var i = 3; i <= 27; i++) {
                    elementNames.push('sppiTest002Q' + i);
                }
                clearFormValues(elementNames);
                checkDisabledRadio('sppiTest002C1_N');
            }
            // 若选“否”，则不影响本条款结论1，继续其他条款判断。
            else if ($(this).val() === NO) {
                $('#p12').show();
                removeHideId('p12');
                $('#p2').show();
                removeHideId('p2');
                resetConslusion('sppiTest002C1');
            }
        });

        for (var i=3; i <= 14; i++) {
            $('#sppiTest002Q' + i).change(function() {
                //若选“是”，则本条款结论1联动显示为“是”。
                if ($(this).val() === YES) {
                    checkDisabledRadio('sppiTest002C1_Y');
                }
                // 若选“否”，则不影响本条款结论。
                else if ($(this).val() === NO) {
                    resetConslusion('sppiTest002C1');
                }
            });
        }

        $('#sppiTest002Q15').change(function() {
            //若选“是”，则不影响本条款结论2.1
            if ($(this).val() === YES) {
                $('#p3').show();
                removeHideId('p3');
                resetConslusion('sppiTest002C2');
                uncheckRadioGroup("sppiTest002Q16");
                uncheckRadioGroup("sppiTest002Q17");
                uncheckRadioGroup("sppiTest002Q18");
                uncheckRadioGroup("sppiTest002Q19");
            }
            // 若选“否”，则本条款结论2.1联动显示为“是”，同时隐藏条款2.1.1.1与2.1.1.2。
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C2_Y');
                $('#p3').hide();
                addHideId('p3');
                clearFormValues(['sppiTest002Q16', 'sppiTest002Q17']);
                uncheckRadioGroup("sppiTest002Q18");
                uncheckRadioGroup("sppiTest002Q19");
            }
        });

        for (var i=16; i <= 19; i++) {
            $('#sppiTest002Q' + i).change(function() {
                //若选“是”，则不影响本条款结论2.1。
                if ($(this).val() === YES) {
                    resetConslusion('sppiTest002C2');
                }
                // 若选“否”，则本条款结论2.1联动显示为“是”。
                else if ($(this).val() === NO) {
                    checkDisabledRadio('sppiTest002C2_Y');
                }
            });
        }

        $('#sppiTest002Q20').change(function() {
            //若选“是”，则继续判断子条款2.2.1.1与2.2.1.2。
            if ($(this).val() === YES) {
                $('#p4').show();
                removeHideId('p4');
                uncheckRadioGroup('sppiTest002C3');
                uncheckRadioGroup('sppiTest002Q21');
                uncheckRadioGroup('sppiTest002Q22');
            }
            // 若选“否”，则不影响本条款结论。且子条款2.3.1.1与2.3.1.2隐藏，无需判断。
            else if ($(this).val() === NO) {
                $('#p4').hide();
                addHideId('p4');
                clearFormValues(['sppiTest002Q21', 'sppiTest002Q22']);
                resetConslusion('sppiTest002C3');
                uncheckRadioGroup('sppiTest002Q21');
                uncheckRadioGroup('sppiTest002Q22');
            }
        });

        for (var i=21; i <= 22; i++) {
            $('#sppiTest002Q' + i).change(function() {
                //若选“是”，则本条款结论2.2联动显示为“是”
                if ($(this).val() === YES) {
                    checkDisabledRadio('sppiTest002C3_Y');
                }
                // 若选“否”，不影响本条款结论2.2
                else if ($(this).val() === NO) {
                    resetConslusion('sppiTest002C3');
                }
            });
        }

        $('#sppiTest002Q23').change(function() {
            //若选“否”，则继续判断条款2.3.1.1。
            if ($(this).val() === NO) {
                $('#p5').show();
                removeHideId('p5');
                uncheckRadioGroup('sppiTest002C4');
                resetConslusion('sppiTest002C4');
                uncheckRadioGroup('sppiTest002Q24');
            }
            // 若选“是”，则不影响本条款结论。且子条款2.3.1.1隐藏，无需判断。
            else if ($(this).val() === YES) {
                $('#p5').hide();
                addHideId('p5');
                clearFormValues(['sppiTest002Q24']);
                //1）2.3.1为是
                checkDisabledRadio('sppiTest002C4_N');
            }
        });

        $('#sppiTest002Q24').change(function() {
            //若选“是”，则不影响本条款结论2.3。
            //若选“否”，则本条款结论2.3联动显示为“是”。
            if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C4_Y');
            }
            //2）2.3.1为否，2.3.1.1为是
            else if ($(this).val() === YES && $('#sppiTest002Q23').val() === NO) {
                checkDisabledRadio('sppiTest002C4_N');
            }
        });

        $('#sppiTest002Q25').change(function() {
            if ($(this).val() === YES) {
                uncheckRadioGroup('sppiTest002Q26');
                uncheckRadioGroup('sppiTest002C5');
                enableAllQaInputs(['sppiTest002Q26', 'sppiTest002Q27']);
            }
            //若选择否, 则2.4结论为否，若选择是则继续判断2.4.1.1
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C5_N');//通过
                //2.4.1.1和2.4.1.2不会再影响结论2.4，应该置灰显示，已与需求沟通
                clearFormValues(['sppiTest002Q26', 'sppiTest002Q27'], true);
            }
        });

        $('#sppiTest002Q26').change(function() {
            //若选择是,则2.4.1.2置灰不可判断，若选择否则继续判断2.4.1.2
            if ($(this).val() === YES) {
                uncheckRadioGroup('sppiTest002Q27');
                disableRadioGroup('sppiTest002Q27');
                checkDisabledRadio('sppiTest002C5_N');//通过
            }
            else if ($(this).val() === NO) {
                uncheckRadioGroup('sppiTest002Q27');
                enableRadioGroup('sppiTest002Q27');
            }
        });

        $('#sppiTest002Q27').change(function() {
            //如勾选否,则不影响测试结果，若选“是”，则本条款结论2.4联动显示为“是”
            if ($(this).val() === YES) {
                checkDisabledRadio('sppiTest002C5_Y');//不通过
            }
            else if ($(this).val() === NO) {
                checkDisabledRadio('sppiTest002C5_N');//通过
            }
        });

        $("input[id^='sppiTest002C']").each(function () {
            let eventName = $(this).attr("name");

            $('#' + eventName).change(function() {
                //条款2.1、2.2、2.3、2.4结论全部为“否”，则本结论联动显示为“否”；否则，联动显示为“是”。置灰，不可改。
                if (eventName != "sppiTest002C6") {
                    if ($('#sppiTest002C2').val() === NO && $('#sppiTest002C3').val() === NO
                        && $('#sppiTest002C4').val() === NO && $('#sppiTest002C5').val() === NO) {
                        checkDisabledRadio('sppiTest002C6_N');//通过
                    } else {
                        checkDisabledRadio('sppiTest002C6_Y');//不通过
                    }
                }

                //结论1、2全部为“否”，则测试结果联动显示为“通过”；否则，联动显示为“不通过”。置灰，不可改
                if (eventName != "sppiTest002Result") {
                    if ($('#sppiTest002C1').val() === NO && ($('#sppiTest002C6').val() === NO || !$('#sppiTest002C6').next().is(":visible"))) {
                        checkDisabledRadio('sppiTest002Result_' + SPPI_PASS);//通过
                    } else {
                        checkDisabledRadio('sppiTest002Result_' + SPPI_NOT_PASS);//不通过
                    }
                }
            });
        });

    });

}());
