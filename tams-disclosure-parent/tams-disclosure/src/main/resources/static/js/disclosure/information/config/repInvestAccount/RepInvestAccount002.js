/**
 * 产品费用信息维护代码新建修改画面
 */
var RepInvestAccount002 = function() {
    return{
        init : function(){
            if(pageModel!=PAGE_MODEL_NEW){
                $("#prodId").attr('disabled',true);

                star();
            }
        },
        saveOrSubmit : function(operateType){
            var param = {};
            param.operateType = operateType;
            param.pageModel = pageModel;
            param.prodName = $("#prodName").val();
            param.accountType = $('#accountType').val();
            param.accountNum = $("#accountNum").val();
            param.accountName = $("#accountName").val();
            param.accountOpener = $("#accountOpener").val();
            param.prodId = $("#prodId").val();
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repInvestAccount/RepInvestAccount002/check',param,function(result){
                if(result){
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/repInvestAccount/RepInvestAccount002/save', param, "RepInvestAccount002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repInvestAccount/RepInvestAccount002/save', param, "RepInvestAccount002_btn_submit");
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

$(function() {
    RepInvestAccount002.init();

    $('#RepInvestAccount002_btn_submit').click(function() {
        RepInvestAccount002.saveOrSubmit('submit');
    });

    $('#RepInvestAccount002_btn_save').click(function() {
        RepInvestAccount002.saveOrSubmit('save');
    });

    $('#prodId').change(function(){
        var prodId = $('#prodId').val();
        if('' != prodId && null != prodId){
            Joyin.ajaxJson(basePath+'disclosure/information/config/repInvestAccount/RepInvestAccount002/selectProdName?prodId='+prodId,{},function (result){
                $('#prodName').val(result['PROD_NAME']);
            })
        }else{
            $('#prodName').val('');
        }
    });
    $('#accountType').val('托管账户');

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


