/**
 * 产品费用信息维护代码新建修改画面
 */
var RepRelDeal002 = function() {
    return{
        init : function(){
            if(pageModel!=PAGE_MODEL_NEW){
                $("#prodId").attr('disabled',true);
                $("#prodName").attr('disabled',true);
            }

            // if($('#effdate').val() != '' && $('#effdate').val() != null){
            //     if($('#effdate').val()>platDate){
            //         $("#effdate").attr("disabled",false);
            //         if($('#effdelidate').val() != '' && $('#effdelidate').val() != null){
            //             $("#effdelidate").attr("disabled",false);
            //         }
            //     }else{
            //         if($('#effdate').val() <= platDate){
            //             $("#effdate").attr("disabled",true);
            //         }
            //     }
            //
            // }

            // if($('#effdelidate').val() != '' && $('#effdelidate').val() != null){
            //     if($('#effdelidate').val()<=platDate){
            //         $("#effdelidate").attr("disabled",true);
            //         if($('#effdate').val() != '' && $('#effdate').val() != null){
            //             $("#effdate").attr("disabled",true);
            //         }
            //     }else{
            //         $("#effdelidate").attr("disabled",false);
            //     }
            // }

        },
        saveOrSubmit : function(operateType){
            var param = {};
            param.operateType = operateType;
            param.pageModel = pageModel;
            param.prodName = $("#prodName").val();
            param.prodId = $("#prodId").val();
            param.creditorAmt = $("#creditorAmt").val();
            param.bondAmt = $("#bondAmt").val();
            param.investAmt = $("#investAmt").val();
            param.assetAmt = $("#assetAmt").val();
            param.trustFee = $("#trustFee").val();
            param.sellFee = $("#sellFee").val();
            param.otherFee = $("#otherFee").val();
            param.effdate = $("#effdate").val();
            param.effdelidate = $("#effdelidate").val();
            param.uuid = $("#uuid").val();
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal002/check',param,function(result){
                if(result){
                    Joyin.confirm("请确认是否要操作？", function () {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal002/save', param, "RepRelDeal002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repRelDeal/RepRelDeal002/save', param, "RepRelDeal002_btn_submit");
                    }
                    },{}, function () {
                    });
                }
            })
        },
        close : function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    };
}();

function effdateChange() {

    // if($('#effdate').val() != '' && $('#effdate').val() != null){
    //     if($('#effdate').val()<platDate){
    //         Joyin.error("生效日不能小于平台日！");
    //         $('#effdate').val('');
    //     }
    // }
}

function effdelidateChange() {

    // if($('#effdelidate').val() != '' && $('#effdelidate').val() != null){
    //     if($('#effdelidate').val()<platDate){
    //         Joyin.error("生效截止日不能小于平台日！");
    //         $('#effdelidate').val('');
    //     }
    // }

    if($('#effdelidate').val() != '' && $('#effdelidate').val() != null && $('#effdate').val() != '' && $('#effdate').val() != null) {
        if($('#effdelidate').val()< $('#effdate').val()){
            Joyin.error("生效截止日不能小于生效日！");
            $('#effdelidate').val('');
        }
    }

}

$(function() {
    RepRelDeal002.init();

    $('#RepRelDeal002_btn_submit').click(function() {
        RepRelDeal002.saveOrSubmit('submit');
    });

    $('#RepRelDeal002_btn_save').click(function() {
        RepRelDeal002.saveOrSubmit('save');
    });

    $('#prodId').change(function(){
        var prodId = $('#prodId').val();
        if('' != prodId && null != prodId){
                $('#prodName').val(prodId);
        }else{
            $('#prodName').val('');
        }
    });
    $("#effdate").change(function () {
        effdateChange();
    });
    $("#effdelidate").change(function () {
        effdelidateChange();
    });

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


