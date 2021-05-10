/**
 * SPPI测试新建
 */
(function() {
    //var selectedRadioText = '\ue643';
    //var unSelectedRadioText = '\ue63f';
    var radioDisabledColor = "#9e9d9d";
    //隐藏的项目的id
    var hideIds = new Set();

    window.addHideId = function (id) {
        hideIds.add(id);
    };

    window.removeHideId = function (id) {
        hideIds.delete(id);
    };

    function hideHtmlItems() {
        if (HIDE_IDS) {
            $.each(HIDE_IDS.split(','), function(i, value){
                $('#' + value).hide();
            });
        }
    }

    function disableHtmlItems() {
        if (DISABLE_IDS) {
            $.each(DISABLE_IDS, function(i, value){
                disableRadioGroup(value);
            });
        }
    }

    function disableInputCheckBoxColor(radioGroupName) {
        let ul = $('#' + radioGroupName).next();
        if (ul && ul.length && $(ul).hasClass("layui-checkbox-u")) {
            $(ul).css('color', radioDisabledColor);
        }
    }

    function enableInputCheckBoxColor(radioGroupName) {
        let ul = $('#' + radioGroupName).next();
        if (ul && ul.length && $(ul).hasClass("layui-checkbox-u")) {
            $(ul).css('color', '');
        }
    }

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
    };

    function submit() {
        if (typeof $('#sppiTest002Result').val() === 'undefined' || $('#sppiTest002Result').val() == "") {
            Joyin.error(JoyinUtil.getMessage("FIN_ASSET_SPPI_004"));
            return;
        }

        var object = $.serializeToObject('#submitForm', false);
        object.pageModel = pageModel;
        object.pageId = realPageId ? realPageId : pageId;
        object.operateType = 'submit';
        object.assetType = assetType;
        object.sppiTmpType = sppiTmpType;
        object.sppiTmpNo = sppiTmpNo;

        // diabled元素，通过form拿不到
        object.testStatus = $('#sppiTest002Result').val();
        object.finprodId = $('#finprodId').val();
        object.finprodName = $('#finprodName').val();
        object.assetTypeName = $('#assetTypeName').val();

        // 01下拉框的id为name_*_s, 02文本框的id为name, 03radioBox的id为name_*, 04checkbox的id为name_*_c
        // 目前只有02文本框，03radioBox
        var inputAttrMap = {};
        var inputValueMap = {};
        var requiredQaSet = new Set();
        var disabledFieldSet = new Set();
        $('input[id*="sppiTest002Q"]').each(function () {
            let input = $(this);
            let elemetType = input.attr("type");

            // 换了radio后，这段永远走不到了(不需要了，代码保留吧) on 2020-10-23
            if (("radio" === elemetType || "checkbox" === elemetType)) {
                //隐藏的项目不需要提交
                if (!$(this).next().is(":visible")) {
                    return true;
                }
                // disabled问题可以不提交，disabled结论需要提交
                if (true === input[0].checked) {
                    inputValueMap[input.attr("name")] = input.val();
                }
            }
            else if ("text" === elemetType) {
                //隐藏的项目不需要提交，“是、否”平铺框对应的Input也是被隐藏的（头疼2020-10-23,判断是否具有样式class:input-checkbox）
                if (!$(this).is(":visible") && !($(this).hasClass("input-checkbox") && $(this).next().is(":visible"))) {
                    return true;
                }
                // disabled问题可以不提交，disabled结论需要提交
                inputValueMap[input.attr("name")] = input.val();
            }

            inputAttrMap[input.attr("name")] = elemetType;

            //disabled不要验证required
            if ("required" === input.attr("required")) {
                if (input.attr("disabled")) {
                    disabledFieldSet.add(input.attr("id"));
                }
                else {
                    requiredQaSet.add(input.attr("name"));
                }
            }
        });

        //结论需要提交，不然没法查看
        $('input[id*="sppiTest002C"]').each(function () {
            let input = $(this);
            let elemetType = input.attr("type");//"radio" 2020-10-23修改：变为变了形的text

            //隐藏的项目不需要提交
            if (!$(this).next().is(":visible")) {
                //continue
                return true;
            }
            //if (true === input[0].checked) {
                inputValueMap[input.attr("name")] = input.val();
            //}
            inputAttrMap[input.attr("name")] = elemetType;
            if ("required" === input.attr("required")) {
                requiredQaSet.add(input.attr("name"));

                //所有的结论都是disabled
                //disabledFieldSet.add(input.attr("id"));
            }
        });

        object.inputAttrMap = inputAttrMap;
        object.inputValueMap = inputValueMap;
        object.requiredQaList = Array.from(requiredQaSet);
        object.hideIds = Array.from(hideIds).join(",");
        object.disabledFieldList = Array.from(disabledFieldSet);

        Joyin.ajaxJson(basePath + 'invest/asset/sppi/SppiTest002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")),function () {
                WorkFlow.doSubmitList(basePath + 'invest/asset/sppi/SppiTest002/save', object, "SppiTest002_btn_submit");
            });
        })
    };

    var init = function(){
        if (workflowId) {
            //region 审核窗口内嵌内容区域变小，导致页面变形，所以调整下样式。(css变量不支持!important)
            let offset = 2;
            if ('SppiTemplateBondDtt' == realPageId || 'SppiTemplateBondKpmg' == realPageId || 'SppiTemplateNotBondDtt' == realPageId) {
                offset = 3;
            }
            else if ('SppiTemplateNotBondKpmg' == realPageId) {
                offset = 3;
            }
            else if ('SppiTemplateNotBondPwc' == realPageId) {
                offset = 6;
            }
            let a = 84 - offset, b = 77 - offset, c= 60 - offset, d = 80 - offset, e = 11 + offset, f = 18 + offset, g = 35 + offset, h = 15 + offset, i = 60 - offset;

            $("label.questionLbl").each(function(){
                //$(this).css('width', '82%!important');//无效
                $(this).css('cssText', 'width:' + a + '%!important');
            });
            $("label.questionLblc").each(function(){
                $(this).css('cssText', 'width:' + a + '%!important');
            });
            $("label.questionLbld").each(function(){
                $(this).css('cssText', 'width:' + i + '%!important');
            });
            $("label.questionLblSub").each(function(){
                $(this).css('cssText', 'width:' + a + '%!important');
            });
            $("label.questionLblSmaller").each(function(){
                $(this).css('cssText', 'width:' + b + '%!important');
            });
            $("label.questionLblSubSmaller").each(function(){
                $(this).css('cssText', 'width:' + b + '%!important');
            });
            $("label.questionLblSmaller1").each(function(){
                $(this).css('cssText', 'width:' + c + '%!important');
            });
            $("label.questionLblSmaller2").each(function(){
                $(this).css('cssText', 'width:' + d + '%!important');
            });

            $("div.questionRadio").each(function(){
                $(this).css('cssText', 'width:' + e + '%!important');
            });
            $("div.questionRadioBigger").each(function(){
                $(this).css('cssText', 'width:' + f + '%!important');
            });
            $("div.questionRadioBigger1").each(function(){
                $(this).css('cssText', 'width:' + g + '%!important');
            });
            $("div.questionRadioBigger2").each(function(){
                $(this).css('cssText', 'width:' + h + '%!important');
            });
            //.questionTextLbl, .questionTextLblSub	width: 30% !important; .questionText width: 65% !important;
            //endregion

             $('#SppiTest002_btn_reset').hide();

            if (HIDE_IDS) {
                $.each(HIDE_IDS.split(','), function(i, value){
                    hideIds.add(value);
                });
            }

            //隐藏的项目查看时要隐藏 hideIds
            hideHtmlItems();

            // disabled项目驳回修改时需要disabled
            disableHtmlItems();
        }
        else if(pageModel == PAGE_MODEL_NEW){
            $('#SppiTest002_btn_reset').show();
        }
        else if(pageModel == PAGE_MODEL_REFERENCE){
            $('#SppiTest002_btn_reset').hide();

            if (HIDE_IDS) {
                $.each(HIDE_IDS.split(','), function(i, value){
                    hideIds.add(value);
                });
            }

            //隐藏的项目查看时要隐藏 hideIds
            hideHtmlItems();
        }

        // 修改“是、否radiobox”的样式
        $('ul.layui-checkbox-u > li.layui-chcekbox').each(function(i, e) {
            $(e).css('min-width', '48px');
            $(e).css('float', 'right');
            if ($(e).parent('.disabled').length) {
                $(e).css('color', radioDisabledColor);
            }
        });

        star();
    };

    /**
     * 清空form中指定名称元素的内容（或选中状态）
     * @param elementNames 数组
     * @param disable 是否禁用置灰它们
     */
    window.clearFormValues = function(elementNames, disable) {
        //$("select[@name=items],'#submitForm')
        //$("select[name='select_name']").find('option:selected')
        //.removeAttr('selected');
        $(':input','#submitForm').not(':button, :submit, :reset').each(function () {
            let input = $(this);
            let elementType = input.attr("type");
            let eleName = input.attr("name");

            if ($.inArray(eleName, elementNames) >= 0) {
                switch (elementType) {
                    case "text":
                        input.val('');
                        if (disable) {
                            if ($(this).hasClass("input-checkbox")) {
                                // 调整样式，使其真的像disabled
                                disableInputCheckBoxColor(eleName);
                            }

                            $(input).attr("disabled", true);
                            $(input).trigger('change');
                        }
                        break;
                    // 下面代码已经不可能走到了（不需要），2020-10-23修改后
                    case "radio":
                        input.removeAttr('checked');
                        //html中定义的radio,其实都是不可见的,界面上显示的实际上是它的下一个元素
                        uncheckRadioGroup(eleName);
                        if (disable) {
                            disableRadioGroup(eleName);
                        }
                        break;
                }
            }
        });
    };

    window.enableAllQaInputs = function(elementNames) {
        $('input[id*="sppiTest002Q"]').each(function () {
            let input = $(this);
            let elementType = input.attr("type");
            let eleName = input.attr("name");

            if(elementNames && $.isArray(elementNames) && elementNames.length > 0) {
                if (!($.inArray(eleName, elementNames) >= 0)) {
                    //continue
                    return true;
                }
            }

            switch (elementType) {
                case "text":
                    if ($(this).hasClass("input-checkbox")) {
                        enableInputCheckBoxColor(eleName);
                    }
                    $(input).attr("disabled",false);
                    $(input).trigger('change');
                    break;
                // 下面代码已经不可能走到了（不需要），2020-10-23修改后
                case "radio":
                    enableRadioGroup(eleName);
                    break;
            }
        });
    };

    // 选中disabled radio
    window.checkDisabledRadio = function(radioId) {
/*        $('#' + radioId).attr("disabled",false);
        $($('#' + radioId).next()).click();
        $('#' + radioId).attr("disabled",true);*/
        // 2020-10-23修改后，所有6个模板中已经没有radio了。采用下面新的逻辑
        var nameArr = radioId.split("_");
        if (nameArr.length == 2) {
            // 先uncheck 所有
            let optionAll = $('#' + nameArr[0]).next().children();
            if (optionAll && optionAll.length) {
                $.each(optionAll, function(i, value){
                    $(value).removeClass('laytable-checkbox-selected');
                });
            }

            let option = $('#' + nameArr[0]).next().children("li[data-value='" + nameArr[1] + "']");
            if (option && option.length) {
                $(option[0]).removeClass('laytable-checkbox-selected');
                $(option[0]).addClass('laytable-checkbox-selected');
                $('#' + nameArr[0]).val(nameArr[1]);
                $('#' + nameArr[0]).trigger('change');
            }
        }
    };

    // 将radio的选中样式清除
    window.uncheckRadioGroup = function(radioGroupName) {
/*        if ($('#' + radioGroupName + '_Y').length) {
            $('#' + radioGroupName + '_Y').removeAttr('checked');
            $($('#' + radioGroupName + '_Y').next()).children()[0].innerText = unSelectedRadioText;
        }
        if ($('#' + radioGroupName + '_N').length) {
            $('#' + radioGroupName + '_N').removeAttr('checked');
            $($('#' + radioGroupName + '_N').next()).children()[0].innerText = unSelectedRadioText;
        }
        if ($('#' + radioGroupName + '_NA').length) {
            $('#' + radioGroupName + '_NA').removeAttr('checked');
            $($('#' + radioGroupName + '_NA').next()).children()[0].innerText = unSelectedRadioText;
        }

        if ($('#' + radioGroupName + '_D').length) {
            $('#' + radioGroupName + '_D').removeAttr('checked');
            $($('#' + radioGroupName + '_D').next()).children()[0].innerText = unSelectedRadioText;
        }
        if ($('#' + radioGroupName + '_S').length) {
            $('#' + radioGroupName + '_S').removeAttr('checked');
            $($('#' + radioGroupName + '_S').next()).children()[0].innerText = unSelectedRadioText;
        }
        if ($('#' + radioGroupName + '_fixed').length) {
            $('#' + radioGroupName + '_fixed').removeAttr('checked');
            $($('#' + radioGroupName + '_fixed').next()).children()[0].innerText = unSelectedRadioText;
        }
        if ($('#' + radioGroupName + '_float').length) {
            $('#' + radioGroupName + '_float').removeAttr('checked');
            $($('#' + radioGroupName + '_float').next()).children()[0].innerText = unSelectedRadioText;
        }*/
        // 2020-10-23修改后，所有6个模板中已经没有radio了。采用下面新的逻辑
        $('#' + radioGroupName).val('');
        let option = $('#' + radioGroupName).next().children();
        if (option && option.length) {
            $.each(option, function(i, value){
                $(value).removeClass('laytable-checkbox-selected');
            });
        }
    };

    // disable radio group
    window.disableRadioGroup = function(radioGroupName) {
/*        if ($('#' + radioGroupName + '_Y').length) {
            $('#' + radioGroupName + '_Y').attr("disabled", true);
            $($('#' + radioGroupName + '_Y').next()).addClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_N').length) {
            $('#' + radioGroupName + '_N').attr("disabled", true);
            $($('#' + radioGroupName + '_N').next()).addClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_NA').length) {
            $('#' + radioGroupName + '_NA').attr("disabled", true);
            $($('#' + radioGroupName + '_NA').next()).addClass("layui-radio-disbaled layui-disabled");
        }

        if ($('#' + radioGroupName + '_D').length) {
            $('#' + radioGroupName + '_D').attr("disabled", true);
            $($('#' + radioGroupName + '_D').next()).addClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_S').length) {
            $('#' + radioGroupName + '_S').attr("disabled", true);
            $($('#' + radioGroupName + '_S').next()).addClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_fixed').length) {
            $('#' + radioGroupName + '_fixed').attr("disabled", true);
            $($('#' + radioGroupName + '_fixed').next()).addClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_float').length) {
            $('#' + radioGroupName + '_float').attr("disabled", true);
            $($('#' + radioGroupName + '_float').next()).addClass("layui-radio-disbaled layui-disabled");
        }*/
        // 2020-10-23修改后，所有6个模板中已经没有radio了。采用下面新的逻辑
        disableInputCheckBoxColor(radioGroupName);

        $('#' + radioGroupName).attr("disabled", true);
        $('#' + radioGroupName).trigger('change');
    };

    // enable radio group
    window.enableRadioGroup = function(radioGroupName) {
/*        if ($('#' + radioGroupName + '_Y').length) {
            $('#' + radioGroupName + '_Y').attr("disabled", false);
            $($('#' + radioGroupName + '_Y').next()).removeClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_N').length) {
            $('#' + radioGroupName + '_N').attr("disabled", false);
            $($('#' + radioGroupName + '_N').next()).removeClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_NA').length) {
            $('#' + radioGroupName + '_NA').attr("disabled", false);
            $($('#' + radioGroupName + '_NA').next()).removeClass("layui-radio-disbaled layui-disabled");
        }

        if ($('#' + radioGroupName + '_D').length) {
            $('#' + radioGroupName + '_D').attr("disabled", false);
            $($('#' + radioGroupName + '_D').next()).removeClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_S').length) {
            $('#' + radioGroupName + '_S').attr("disabled", false);
            $($('#' + radioGroupName + '_S').next()).removeClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_fixed').length) {
            $('#' + radioGroupName + '_fixed').attr("disabled", false);
            $($('#' + radioGroupName + '_fixed').next()).removeClass("layui-radio-disbaled layui-disabled");
        }
        if ($('#' + radioGroupName + '_float').length) {
            $('#' + radioGroupName + '_float').attr("disabled", false);
            $($('#' + radioGroupName + '_float').next()).removeClass("layui-radio-disbaled layui-disabled");
        }*/
        // 2020-10-23修改后，所有6个模板中已经没有radio了。采用下面新的逻辑
        enableInputCheckBoxColor(radioGroupName);

        $('#' + radioGroupName).attr("disabled", false);
        $('#' + radioGroupName).trigger('change');
    };

    /**
     * 画面加载完成
     */
    $(function() {
        init();

        $('#SppiTest002_btn_submit').click(function() {
            submit();
        });
        $('#SppiTest002_btn_reset').click(function(){
            document.location.reload();
        });
        $('#SppiTest002_btn_cancel').click(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });

}());
