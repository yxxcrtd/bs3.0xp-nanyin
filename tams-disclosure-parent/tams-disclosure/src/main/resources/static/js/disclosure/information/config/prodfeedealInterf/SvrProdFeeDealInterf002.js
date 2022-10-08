/**
 * 产品费用信息维护代码新建修改画面
 */
var SvrPrdFeeDealInterfCode002 = function() {
    return{
        init : function(){
            if(pageModel!=PAGE_MODEL_NEW){
                $("#prodId").attr('disabled',true);
                $("#feeType").attr('disabled',true);
                $("#payDate").attr('disabled',true);
                if ($('#feeType').val() === '05'|| $('#feeType').val() === '06' || $('#feeType').val() === '07') {
                    $("#amt").attr("required", "required");
                } else {
                    $('#amt').removeAttr("required");
                }
                if ($('#feeType').val() === '01'|| $('#feeType').val() === '02' || $('#feeType').val() === '03' || $('#feeType').val() === '04') {
                    $("#accrual").attr("required", "required");
                } else {
                    $('#accrual').removeAttr("required");
                }
                star();
            }
        },
        saveOrSubmit : function(operateType){
            var param = {};
            param.operateType = operateType;
            param.pageModel = pageModel;
            param.prodName = $("#prodName").val();
            param.prodId = $('#prodId').val();
            param.feeType = $("#feeType").val();
            param.paidFee = $("#amt").val();
            param.accrual = $("#accrual").val();
            param.payDate = $("#payDate").val();
            Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/check',param,function(result){
                if(result){
                    Joyin.ajaxJson(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/checkExistData', param, function(checkResult) {
                        if(checkResult){
                            Joyin.confirm("有已删除的数据，是否覆盖", function () {
                                Joyin.confirm("请确认是否要操作？", function () {
                                    if(operateType === 'save') {
                                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/save', param, "SvrProdFeeDealInterf002_btn_save");
                                    }
                                    else {
                                        WorkFlow.doSubmitList(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/save', param, "SvrProdFeeDealInterf002_btn_submit");
                                    }
                                },{}, function () {
                                });

                            },{}, function () {
                            });
                        }else{
                            Joyin.confirm("请确认是否要操作？", function () {
                                if(operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/save', param, "SvrProdFeeDealInterf002_btn_save");
                                }
                                else {
                                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/save', param, "SvrProdFeeDealInterf002_btn_submit");
                                }
                            },{}, function () {
                            });

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
    SvrPrdFeeDealInterfCode002.init();

    $('#SvrProdFeeDealInterf002_btn_submit').click(function() {
        SvrPrdFeeDealInterfCode002.saveOrSubmit('submit');
    });

    $('#SvrProdFeeDealInterf002_btn_save').click(function() {
        SvrPrdFeeDealInterfCode002.saveOrSubmit('save');
    });

    $('#prodId').change(function(){
        var prodId = $('#prodId').val();
        if('' != prodId && null != prodId){
            Joyin.ajaxJson(basePath+'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002/selectProdName?prodId='+prodId,{},function (result){
                $('#prodName').val(result['PROD_NAME']);
            })
        } else {
            $('#prodName').val('');
        }
    });

    $('#feeType').change(function(){
        //调整为 当费用类型为“客户收益”时必填 二阶段变更
        if ($('#feeType').val() === '07') {
            $("#amt").attr("required", "required");
        } else {
            $('#amt').removeAttr("required");
        }
        //调整为 费用类型为“托管费”“管理费（固定）”“管理费（赎回）”“销售费”“申购费”“赎回费”时必填 二阶段变更
        if ($('#feeType').val() === '01'|| $('#feeType').val() === '02' || $('#feeType').val() === '03' || $('#feeType').val() === '04' || $('#feeType').val() === '05'|| $('#feeType').val() === '06') {
            $("#accrual").attr("required", "required");
        } else {
            $('#accrual').removeAttr("required");
        }
        star();
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


