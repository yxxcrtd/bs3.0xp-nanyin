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
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repProdIndex/RepProdIndex002/save', param, "RepProdIndex002_btn_submit");
                    }
                }
            })
        },
        close : function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    };
}();

var assetsValueChange = function(){
    if($('#isGrade').val() == "02"){
        $('#pAssetsValue').val($('#assetsValue').val()).trigger("blur");
        $("#pAssetsValue").attr('disabled',true);
    }else{
        $("#pAssetsValue").attr('disabled',false);
    }
}

var capitalChange = function(){
    if($('#isGrade').val() == "02"){
        $('#pCapital').val($('#capital').val()).trigger("blur");
        $("#pCapital").attr('disabled',true);
    }else{
        $('#pCapital').val('').trigger("blur");
        $("#pCapital").attr('disabled',false);
    }
}

var unitValueChange = function(){
    if($('#isGrade').val() == "02"){
        $('#pUnitValue').val($('#unitValue').val()).trigger("blur");
        $("#pUnitValue").attr('disabled',true);
    }else{
        if($('#prodType').val() == "M"){
            $('#pUnitValue').val('1.0000').trigger("blur");;
            $("#pUnitValue").attr('disabled',true);
        }else{
            $('#pUnitValue').val('');
            $("#pUnitValue").attr('disabled',false);
        }
    }
}

var unitValueTChange = function(){
    if($('#isGrade').val() == "02"){
        $('#pUnitValueT').val($('#unitValueT').val()).trigger("blur");
        $("#pUnitValueT").attr('disabled',true);
    }else{
        if($('#prodType').val() == "M"){
            $('#pUnitValueT').val('1.0000').trigger("blur");;
            $("#pUnitValueT").attr('disabled',true);
        }else{
            $('#pUnitValueT').val('');
            $("#pUnitValueT").attr('disabled',false);
        }
    }
}

var wYieldChange = function(){
    if($('#prodType').val() == "N"){
        $('#pWYield').val('');
        $("#pWYield").attr('disabled',true);
        $('#pWYield').removeAttr("required");
    }else if($('#prodType').val() == "M"){
        if($('#isGrade').val() == "02"){
            $('#pWYield').val($('#wYield').val()).trigger("blur");
            $("#pWYield").attr('disabled',true);
        }else{
            $("#pWYield").attr('disabled',false);
        }
        $("#pWYield").attr("required", "required");
    }else{

        $('#pWYield').removeAttr("required");
    }
    star();
}

var yield7Change = function(){
    if($('#prodType').val() == "N"){
        $('#pYield7').val('');
        $("#pYield7").attr('disabled',true);
        $('#pYield7').removeAttr("required");
    }else if($('#prodType').val() == "M"){
        if($('#isGrade').val() == "02"){
            $('#pYield7').val($('#yield7').val()).trigger("blur");
            $("#pYield7").attr('disabled',true);
        }else{
            $("#pYield7").attr('disabled',false);
        }
        $("#pYield7").attr("required", "required");
    }else{
        $('#pYield7').removeAttr("required");
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
                    debugger;
                    $('#prodName').val(result.prodName);
                    $('#isGrade').val(result.isGrade);
                    $('#prodType').val(result.prodType);
                    $('#pProdId').val(result.pProdId);
                    if(result.prodType == "M"){
                        $('#unitValue').val('1.0000').trigger('change');
                        $("#unitValue").attr('disabled',true);
                        $('#unitValueT').val('1.0000').trigger('change');
                        $("#unitValueT").attr('disabled',true);
                        $("#wYield").attr('disabled',false);
                        $("#wYield").attr("required", "required").trigger('change');
                        $("#yield7").attr('disabled',false);
                        $("#yield7").attr("required", "required").trigger('change');
                    }else{
                        $('#unitValue').val('').trigger('change');
                        $("#unitValue").attr('disabled',false);
                        $('#unitValueT').val('').trigger('change');
                        $("#unitValueT").attr('disabled',false);
                        $('#wYield').removeAttr("required");
                        $('#wYield').val('').trigger('change');
                        $("#wYield").attr('disabled',true);
                        $('#yield7').removeAttr("required");
                        $('#yield7').val('').trigger('change');
                        $("#yield7").attr('disabled',true);
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
    $('#assetsValue').change(assetsValueChange);
    $('#capital').change(capitalChange);
    $('#unitValue').change(unitValueChange);
    $('#unitValueT').change(unitValueTChange);
    $('#wYield').change(wYieldChange);
    $('#yield7').change(yield7Change);
debugger;
    if(pageModel!=PAGE_MODEL_NEW){
        if($('#prodType').val() == "M"){
            $("#unitValue").attr('disabled',true);
            $("#unitValueT").attr('disabled',true);
            $("#wYield").attr('disabled',false);
            $("#wYield").attr("required", "required").trigger('change');
            $("#yield7").attr('disabled',false);
            $("#yield7").attr("required", "required").trigger('change');
        }else{
            $("#unitValue").attr('disabled',false);
            $("#unitValueT").attr('disabled',false);
            $('#wYield').removeAttr("required");
            $("#wYield").attr('disabled',true);
            $('#yield7').removeAttr("required");
            $("#yield7").attr('disabled',true);
        };

        if($('#isGrade').val() == "02"){
            $("#pAssetsValue").attr('disabled',true);
            $("#pCapital").attr('disabled',true);
            $("#pUnitValue").attr('disabled',true);
            $("#pUnitValueT").attr('disabled',true);
        }else{
            $("#pAssetsValue").attr('disabled',false);
            $("#pCapital").attr('disabled',false);
            if($('#prodType').val() == "M"){
                $("#pUnitValue").attr('disabled',true);
                $("#pUnitValueT").attr('disabled',true);
            }else{
                $("#pUnitValue").attr('disabled',false);
                $("#pUnitValueT").attr('disabled',false);
            }
        };
        if($('#prodType').val() == "N"){
            $("#pWYield").attr('disabled',true);
            $('#pWYield').removeAttr("required");
        }else if($('#prodType').val() == "M"){
            if($('#isGrade').val() == "02"){
                $("#pWYield").attr('disabled',true);
            }else{
                $("#pWYield").attr('disabled',false);
            }
            $("#pWYield").attr("required", "required");
        }else{

            $('#pWYield').removeAttr("required");
        };
        if($('#prodType').val() == "N"){
            $("#pYield7").attr('disabled',true);
            $('#pYield7').removeAttr("required");
        }else if($('#prodType').val() == "M"){
            if($('#isGrade').val() == "02"){
                $("#pYield7").attr('disabled',true);
            }else{
                $("#pYield7").attr('disabled',false);
            }
            $("#pYield7").attr("required", "required");
        }else{
            $('#pYield7').removeAttr("required");
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


