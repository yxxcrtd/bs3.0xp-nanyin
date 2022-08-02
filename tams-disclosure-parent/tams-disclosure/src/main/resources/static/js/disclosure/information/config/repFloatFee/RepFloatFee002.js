var RepFloatFee002 = function() {
    return{
        init : function(){
            if(pageModel!=PAGE_MODEL_NEW){
                $("#prodId").attr('disabled',true);
                $("#affirmDate").attr('disabled',true);
                star();
            }
        },
        saveOrSubmit : function(operateType){
            var param = {};
            param.operateType = operateType;
            param.pageModel = pageModel;
            param.prodId = $("#prodId").val();
            param.prodName = $("#prodName").val();
            param.affirmDate = $('#affirmDate').val();
            param.businessCode = $("#businessCode").val();
            param.affirmNum = $("#affirmNum").val();
            param.affirmAmt = $("#affirmAmt").val();
            param.aboveAmt = $("#aboveAmt").val();
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/check',param,function(result){
                if(result){
                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/checkExistData', param, function(checkResult) {
                        if(checkResult){
                            Joyin.confirm("有已删除的数据，是否覆盖", function () {
                                if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/save', param, "RepFloatFee002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/save', param, "RepFloatFee002_btn_submit");
                                }
                            },{}, function () {
                            });
                        }else{
                            if(operateType === 'save') {
                                WorkFlow.doSaveList(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/save', param, "RepFloatFee002_btn_save");
                            }
                            else {
                                WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repFloatFee/RepFloatFee002/save', param, "RepFloatFee002_btn_submit");
                            }
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

$(function() {
    RepFloatFee002.init();

    $('#RepFloatFee002_btn_submit').click(function() {
        RepFloatFee002.saveOrSubmit('submit');
    });

    $('#RepFloatFee002_btn_save').click(function() {
        RepFloatFee002.saveOrSubmit('save');
    });

    $('#prodId').change(function(){
        var prodId = $('#prodId').val();
        if('' != prodId && null != prodId){
            Joyin.ajaxJson(basePath+'disclosure/information/config/repFloatFee/RepFloatFee002/selectProdName?prodId='+prodId,{},function (result){
                $('#prodName').val(result['PROD_NAME']);
            })
        }else{
            $('#prodName').val('');
        }
    });
    $('#businessCode').val("03").trigger("change");

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


