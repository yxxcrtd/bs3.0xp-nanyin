$(function () {
    // 提交或保存
    function saveOrSubmit(operateType) {
        // 画面数据体
        var object = $.serializeToObject('#submitForm');

        // 券商代码
        object.customerId = $('#customerId').val()
        // 参数名称
        object.paraCode = $('#paraCode').val()
        if (object.paraCode == RECON_PARA_CODE_02) {
            // 对账维度
            object.paraValue = $('#paraValue2').val()
        } else {
            // 误差值
            object.paraValue = $('#paraValue1').val()
        }
        // 配置日期
        object.configDate = $('#configDate').val()
        // 流程ID
        object.workflowId = $('#workflowId').val()

        // seqNo
        object.seqNo = $('#seqNo').val()

        // 操作类型
        object.operateType = operateType;
        object.pageModel = pageModel;
        // 新建
        if (pageModel == PAGE_MODEL_NEW) {
            object.operateType = 'new';
        } else if (pageModel == PAGE_MODEL_EDIT) {
            // 编辑
            object.operateType = 'update';
        }

        Joyin.ajaxJson(basePath + 'invest/basicdata/stlsecreconpara/StlSecReconPara002/check',
            object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/stlsecreconpara/StlSecReconPara002/save',
                        object, "StlSecReconPara002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath  + 'invest/basicdata/stlsecreconpara/StlSecReconPara002/save',
                        object, "StlSecReconPara002_btn_submit");
                }
            });
        });
    }

    // 画面初始化
    function init() {
        if ($('#paraCode').val() == RECON_PARA_CODE_02) {
            $('#DIV_paraValue02').show()
            $('#DIV_paraValue01').hide()
        }
        else {
            // 误差值
            $('#DIV_paraValue01').show()
            $('#DIV_paraValue02').hide()
        }
        // 编辑画面禁止修改参数名称和券商ID
        if (pageModel === PAGE_MODEL_EDIT) {
            $('#customerId').attr('disabled',true).trigger('change');
            $('#paraCode').attr('disabled',true).trigger('change');
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        /**
         * 参数名称下拉框联动
         */
        $('#paraCode').change(function() {
            // 对账维度
            if ($('#paraCode').val() == RECON_PARA_CODE_02) {
                $('#DIV_paraValue02').show()
                $('#DIV_paraValue01').hide()
                $('#paraValue1').val('').trigger('change');
                $('#paraValue2').val('').trigger('change');
            }
            else {
                // 误差值
                $('#DIV_paraValue01').show()
                $('#DIV_paraValue02').hide()
                $('#paraValue1').val('').trigger('change');
                $('#paraValue2').val('').trigger('change');
            }
        });
    });
    // 绑定按钮事件
    $('#StlSecReconPara002_btn_save').click(function() {
        saveOrSubmit('save');
    });
    // 绑定按钮事件
    $('#StlSecReconPara002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
}())
