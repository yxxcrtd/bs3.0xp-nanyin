/**
 * 交易商新建
 */
$(function() {
    /**
     * 保存或提交
     *
     * @param operateType
     *            操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        if (pageModel == PAGE_MODEL_NEW) {
            object.operateType = 'new';
        } else if (pageModel == PAGE_MODEL_EDIT) {
            object.operateType = 'update';
        }else{
            object.operateType = 'delete';
        }
        Joyin.ajaxJson(basePath + 'disclosure/information/config/bonuscalist/BonusCalist002/check',
            object, function(result) {
                Joyin.confirm(result.data, function() {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'disclosure/information/config/bonuscalist/BonusCalist002/save',
                            object, "BonusCalist002_btn_save");
                    } else {
                        WorkFlow.doSubmitList(basePath  + 'disclosure/information/config/bonuscalist/BonusCalist002/save',
                            object, "BonusCalist002_btn_submit");
                    }
                });
            });
    }

    function addrule() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/repProdIndex/RepProdIndex003/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };

    /**
     * 页面初始化
     */
    var init = function() {
        $('#prodId').change(function () {
            var prodId = $('#prodId').val();
            var data = $('#prodId').select2("data")[0].object;
            var prodName = $('#prodId').select2("data")[0].text;
            $('#prodRegistNo').textSelect('setValue',data);
            // $('#prodName').textSelect('setValue',prodName);
        });
        $('#prodRegistNo').prop("disabled",true);
        if (pageModel == PAGE_MODEL_EDIT){
            $('#prodRegistNo').prop("disabled",true);
            $('#prodId').prop("disabled",true);
            $('#profitBaseDate').prop("disabled",true);
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
        init();

        $('#RepProdIndex001_btn_treeAdd').click(addrule);
        // 绑定按钮事件
        $('#BonusCalist002_btn_save').click(function() {
            saveOrSubmit('save');
        });


        // 绑定按钮事件
        $('#BonusCalist002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());