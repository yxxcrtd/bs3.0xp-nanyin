/**
 * 产品费用信息维护代码新建修改画面
 */
var RepProdIndex002 = function() {
    return{
        init : function(){
            if(pageModel!=PAGE_MODEL_NEW){
                $("#prodId").attr('disabled',true);
                $("#valueDate").attr('disabled',true);
                star();
            }
        },
        saveOrSubmit : function(operateType){
            var param = {};
            param.operateType = operateType;
            param.pageModel = pageModel;
            param.prodId = $("#prodId").val();
            param.prodName = $("#prodName").val();
            param.pProdId = $("#pProdId").val();
            param.valueDate = $("#valueDate").val();
            param.assetsValue = $("#assetsValue").val();
            param.capital = $("#capital").val();
            param.unitValue = $("#unitValue").val();
            param.unitValueT = $("#unitValueT").val();
            param.wYield = $("#wYield").val();
            param.yield7 = $("#yield7").val();
            param.pAssets = $("#pAssets").val();
            param.pAssetsValue = $("#pAssetsValue").val();
            param.pCapital = $("#pCapital").val();
            param.pUnitValue = $("#pUnitValue").val();
            param.pUnitValueT = $("#pUnitValueT").val();
            param.pWYield = $("#pWYield").val();
            param.pYield7 = $("#pYield7").val();
            param.isGrade = $("#isGrade").val();
            param.prodType = $("#prodType").val();
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/check',param,function(result){
                if(result){
                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/checkExistData', param, function(checkResult) {
                        if(checkResult){
                            Joyin.confirm("有已删除的数据，是否覆盖", function () {
                                if($('#isGrade').val() == "01" && pageModel === PAGE_MODEL_EDIT){
                                    Joyin.alert('修改后的母产品信息会同步至母产品下其它子产品数据中！');
                                }
                                /* NYLCXP-860 提示框存在时间太短做延迟处理 modify by kongzhifang 2022-08-19 */
                                setTimeout(()=>{
                                if(operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_save");
                                    }
                                    else {
                                        WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_submit");
                                    }
                                },2000);
                            },{}, function () {
                            });
                        }else{
                            if($('#isGrade').val() == "01" && pageModel === PAGE_MODEL_EDIT){
                                Joyin.alert('修改后的母产品信息会同步至母产品下其它子产品数据中！');
                            }
                            /* NYLCXP-860 提示框存在时间太短做延迟处理 modify by kongzhifang 2022-08-19 */
                            setTimeout(()=>{
                            if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_submit");
                                }
                            },2000);
                        }
                    })

                }
            })
        },
        close : function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    };
}();

var pAssetsValueChange = function(){
    if($('#isGrade').val() == "02"){
        $('#assetsValue').val($('#pAssetsValue').val()).trigger("blur");
        $("#assetsValue").attr('disabled',true);
    }else{
        $("#assetsValue").attr('disabled',false);
    }
}

var pCapitalChange = function(){
    if($('#isGrade').val() == "02"){
        $('#capital').val($('#pCapital').val()).trigger("blur");
        $("#capital").attr('disabled',true);
    }else{
        // $('#capital').val('').trigger("blur");
        $("#capital").attr('disabled',false);
    }
}

var pUnitValueChange = function(){
    if($('#isGrade').val() == "02"){
        $('#unitValue').val($('#pUnitValue').val()).trigger("blur");
        $("#unitValue").attr('disabled',true);
    }else{
        if($('#prodType').val() == "04"){
            $('#unitValue').val('1.0000').trigger("blur");;
            $("#unitValue").attr('disabled',true);
        }else{
            // $('#unitValue').val('');
            $("#unitValue").attr('disabled',false);
        }
    }
}

var pUnitValueTChange = function(){
    if($('#isGrade').val() == "02"){
        $('#unitValueT').val($('#pUnitValueT').val()).trigger("blur");
        $("#unitValueT").attr('disabled',true);
    }else{
        if($('#prodType').val() == "04"){
            $('#unitValueT').val('1.0000').trigger("blur");;
            $("#unitValueT").attr('disabled',true);
        }else{
            // $('#unitValueT').val('');
            $("#unitValueT").attr('disabled',false);
        }
    }
}

var pWYieldChange = function(){
    if($('#prodType').val() == "01"){
        $('#wYield').val('');
        $("#wYield").attr('disabled',true);
        $('#wYield').removeAttr("required");
    }else if($('#prodType').val() == "04"){
        if($('#isGrade').val() == "02"){
            $('#wYield').val($('#pWYield').val()).trigger("blur");
            $("#wYield").attr('disabled',true);
        }else{
            $("#wYield").attr('disabled',false);
        }
        $("#wYield").attr("required", "required");
    }else{

        $('#wYield').removeAttr("required");
    }
    star();
}

var pYield7Change = function(){
    if($('#prodType').val() == "01"){
        $('#yield7').val('');
        $("#yield7").attr('disabled',true);
        $('#yield7').removeAttr("required");
    }else if($('#prodType').val() == "04"){
        if($('#isGrade').val() == "02"){
            $('#yield7').val($('#pYield7').val()).trigger("blur");
            $("#yield7").attr('disabled',true);
        }else{
            $("#yield7").attr('disabled',false);
        }
        $("#yield7").attr("required", "required");
    }else{
        $('#yield7').removeAttr("required");
    }
    star();
}



$(function() {
    RepProdIndex002.init();

    $('#RepProdIndex002_btn_submit').click(function() {
        RepProdIndex002.saveOrSubmit('submit');
    });

    $('#RepProdIndex002_btn_save').click(function() {
        RepProdIndex002.saveOrSubmit('save');
    });

    $('#prodId').change(function(){
        var prodId = $('#prodId').val();
        if('' != prodId && null != prodId){
            Joyin.ajaxJson(basePath+'disclosure/information/config/repProdIndex/RepProdIndex002/selectProdInfo?prodId='+prodId,{},function (result){
                if(result){
                    $('#prodName').val(result.prodName);
                    $('#isGrade').val(result.isGrade);
                    $('#prodType').val(result.prodType);
                    $('#pProdId').val(result.pProdId);
                    if(result.prodType == "04"){
                        $('#pUnitValue').val('1.0000').trigger('change');
                        $("#pUnitValue").attr('disabled',true);
                        $('#pUnitValueT').val('1.0000').trigger('change');
                        $("#pUnitValueT").attr('disabled',true);
                        $("#pWYield").attr('disabled',false);
                        $("#pWYield").attr("required", "required").trigger('change');
                        $("#pYield7").attr('disabled',false);
                        $("#pYield7").attr("required", "required").trigger('change');
                    }else{
                        $('#pUnitValue').val('').trigger('change');
                        $("#pUnitValue").attr('disabled',false);
                        $('#pUnitValueT').val('').trigger('change');
                        $("#pUnitValueT").attr('disabled',false);
                        $('#pWYield').removeAttr("required");
                        $('#pWYield').val('').trigger('change');
                        $("#pWYield").attr('disabled',true);
                        $('#pYield7').removeAttr("required");
                        $('#pYield7').val('').trigger('change');
                        $("#pYield7").attr('disabled',true);
                    }

                    if(result.isGrade == "02"){
                        $('#assetsValue').val($('#pAssetsValue').val()).trigger("blur");
                        $("#assetsValue").attr('disabled',true);
                        $('#capital').val($('#pCapital').val()).trigger("blur");
                        $("#capital").attr('disabled',true);
                        $('#unitValue').val($('#pUnitValue').val()).trigger("blur");
                        $("#unitValue").attr('disabled',true);
                        $('#unitValueT').val($('#pUnitValueT').val()).trigger("blur");
                        $("#unitValueT").attr('disabled',true);
                    }else{
                        $("#assetsValue").attr('disabled',false);
                        $("#capital").attr('disabled',false);
                        if(result.prodType == "04"){
                            $('#unitValue').val('1.0000').trigger("blur");;
                            $("#unitValue").attr('disabled',true);
                            $('#unitValueT').val('1.0000').trigger("blur");;
                            $("#unitValueT").attr('disabled',true);
                        }else{
                            $('#unitValue').val('');
                            $("#unitValue").attr('disabled',false);
                            $('#unitValueT').val('');
                            $("#unitValueT").attr('disabled',false);
                        }

                        if(result.prodType == "01"){
                            $('#wYield').removeAttr("required");
                            $('#wYield').val('').trigger('change');
                            $("#wYield").attr('disabled',true);
                            $('#yield7').removeAttr("required");
                            $('#yield7').val('').trigger('change');
                            $("#yield7").attr('disabled',true);
                        }else if(result.prodType == "04"){
                            if(result.isGrade == "02"){
                                $('#wYield').val($('#pWYield').val()).trigger("blur");
                                $("#wYield").attr('disabled',true);
                                $('#yield7').val($('#pYield7').val()).trigger("blur");
                                $("#yield7").attr('disabled',true);
                            }else{
                                $("#wYield").attr('disabled',false);
                                $("#yield7").attr('disabled',false);
                            }
                        }
                    }
                    star();
                }
            })
        }else{
            $('#prodName').val('');
            $('#isGrade').val('');
            $('#prodType').val('');
        }
    });
    $('#pAssetsValue').change(pAssetsValueChange);
    $('#pCapital').change(pCapitalChange);
    $('#pUnitValue').change(pUnitValueChange);
    $('#pUnitValueT').change(pUnitValueTChange);
    $('#pWYield').change(pWYieldChange);
    $('#pYield7').change(pYield7Change);
debugger;
    if(pageModel!=PAGE_MODEL_NEW){
        if($('#prodType').val() == "04"){
            $("#pUnitValue").attr('disabled',true);
            $("#pUnitValueT").attr('disabled',true);
            $("#pWYield").attr('disabled',false);
            $("#pWYield").attr("required", "required");
            $("#pYield7").attr('disabled',false);
            $("#pYield7").attr("required", "required");
        }else{
            $("#pUnitValue").attr('disabled',false);
            $("#pUnitValueT").attr('disabled',false);
            $('#pWYield').removeAttr("required");
            $("#pWYield").attr('disabled',true);
            $('#pYield7').removeAttr("required");
            $("#pYield7").attr('disabled',true);
        };

        if($('#isGrade').val() == "02"){
            $("#assetsValue").attr('disabled',true);
            $("#capital").attr('disabled',true);
            $("#unitValue").attr('disabled',true);
            $("#unitValueT").attr('disabled',true);
        }else{
            $("#assetsValue").attr('disabled',false);
            $("#capital").attr('disabled',false);
            if($('#prodType').val() == "04"){
                $("#unitValue").attr('disabled',true);
                $("#unitValueT").attr('disabled',true);
            }else{
                $("#unitValue").attr('disabled',false);
                $("#unitValueT").attr('disabled',false);
            }
        };
        if($('#prodType').val() == "01"){
            $("#wYield").attr('disabled',true);
            $('#wYield').removeAttr("required");
        }else if($('#prodType').val() == "04"){
            if($('#isGrade').val() == "02"){
                $("#wYield").attr('disabled',true);
            }else{
                $("#wYield").attr('disabled',false);
            }
            $("#wYield").attr("required", "required");
        }else{

            $('#wYield').removeAttr("required");
        };
        if($('#prodType').val() == "01"){
            $("#yield7").attr('disabled',true);
            $('#yield7').removeAttr("required");
        }else if($('#prodType').val() == "04"){
            if($('#isGrade').val() == "02"){
                $("#yield7").attr('disabled',true);
            }else{
                $("#yield7").attr('disabled',false);
            }
            $("#yield7").attr("required", "required");
        }else{
            $('#yield7').removeAttr("required");
        }
        star();
    }
});

/**
 * 画星星
 */
function star() {
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


