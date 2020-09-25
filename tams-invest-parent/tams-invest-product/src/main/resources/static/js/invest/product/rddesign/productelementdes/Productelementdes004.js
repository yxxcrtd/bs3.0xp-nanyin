/**
 * 债券行情信息新建
 */
(function() {

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm');
        var workflowId;
        if(workflowId != null || workflowId != ''){
            object.workflowId = workflowId;
        }

        object.operateType=operateType;
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/productelementdes/productelementdes002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/rddesign/productelementdes/productelementdes004/save', object, "Productelementdes004_btn_save");
                }
                else if (operateType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'invest/product/rddesign/productelementdes/productelementdes004/save', object, "Productelementdes004_btn_submit");
                }
            });
        });
    }
    /**
     * 添加必填标志
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
    }
    var init = function(){
        $("#prodStyle").val('').parent().parent().hide();
        var wfl_operType = $("#wfl_operType").val();
        if(pageModel==PAGE_MODEL_EDIT){
            if(wfl_operType == null || wfl_operType != 'NEW'){
                $('#finprodMarketId').attr("disabled",true);
                $('#effDate').attr("disabled",true);
                $('#prodStyle').attr("disabled",true);
            }
        }
        finprodIdChange($("#finprodId").val());
    };
    //产品代码联动
    function finprodIdChange(finprodId){
        if(finprodId!=null && finprodId!='' ){
            Joyin.ajax('/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/queryLayerProdInfoByFinprodId', {finprodId:finprodId}, function(result) {
                $("#prodStyle option").remove();
                if(result != null && result.length > 0){
                    //$("#prodStyle").append("<option></option>");
                    for(var i in result){
                        $("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");

                    }
                    $("#prodStyle").val(PROD_STYLE).parent().parent().show();
                }else{
                    $("#prodStyle").val('').parent().parent().hide();
                }
            });

        }
    };
    $(function() {
        init();
        // 按钮事件
       $('#Productelementdes004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#Productelementdes004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#finprodMarketId').change(function(){
            var str = $('#finprodMarketId').select2('data')[0].object.split(',');
            if(str != null && str.length==3){
                $("#finprodId").val(str[0]);
                $("#profitType").val(str[1]);
                $("#finprodAbbr").val(str[2])
            }
            //分档产品加载适用款型
            finprodIdChange($('#finprodId').val());
        })
    });
}());

function selectCallback(data) {
    $('#finprodAbbr').val(data.text);
}