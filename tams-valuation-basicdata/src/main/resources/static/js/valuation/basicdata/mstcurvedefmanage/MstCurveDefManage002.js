(function() {

    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#MstCurveDefManage002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        // 绑定按钮事件
        $('#MstCurveDefManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        //是否含权债change
        layui.form.on('switch(curveGroupType)',function(){
            curveGroupTypeChange();
        });




    });


    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.curveCode = $('#curveCode').val();
        }
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/check', object, function(result) {
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/save', object, "MstCurveDefManage002_btn_save");
            } else if(operateType === 'submit'){
                WorkFlow.doSubmitList(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/save', object, "MstCurveDefManage002_btn_submit");
            }
        });
    };

    function　init(){
        var curveGroupType = $('#curveGroupType')[0].checked;
        if (curveGroupType === true) {
            $('#intCurveType_redId').hide();
            $('#ccy_redId').hide();
            $('#interMethod_redId').hide();
            $('#dayRules_redId').hide();
            $('#calender_redId').hide();
            $('#interestDateRule_redId').hide();
            $('#interestCalMethod_redId').hide();
            $('#interestFreq_redId').hide();
            $('#remark_redId').hide();
        }else{
            $('#intCurveType_redId').show();
            $('#ccy_redId').show();
            $('#interMethod_redId').show();
            $('#dayRules_redId').show();
            $('#calender_redId').show();
            $('#interestDateRule_redId').show();
            $('#interestCalMethod_redId').show();
            $('#interestFreq_redId').show();
            $('#remark_redId').show();
        }

    }

    function curveGroupTypeChange(operateType) {
        var curveGroupType = $('#curveGroupType')[0].checked;
        if (curveGroupType === true) {
            $('#intCurveType').attr('disabled',true);  //曲线类型
            $('#gradeResult').attr('disabled',true);   //评级
            $('#n').attr('disabled',true);   //N
            $('#k').attr('disabled',true);   //K
            $('#curveGroupCode').attr('disabled',true);   //所属曲线族
            $('#ccy').attr('disabled',true);   //币种
            $('#interMethod').attr('disabled',true);   //币种
            $('#remark').attr('disabled',true);   //描述

            $('#intCurveType_redId').hide();
            $('#ccy_redId').hide();
            $('#interMethod_redId').hide();
            $('#dayRules_redId').hide();
            $('#calender_redId').hide();
            $('#interestDateRule_redId').hide();
            $('#interestCalMethod_redId').hide();
            $('#interestFreq_redId').hide();
            $('#remark_redId').hide();

        }else{
            $('#intCurveType').attr('disabled',false);//曲线类型
            $('#gradeResult').attr('disabled',false);   //评级
            $('#n').attr('disabled',false);   //N
            $('#k').attr('disabled',false);   //K
            $('#curveGroupCode').attr('disabled',false);   //所属曲线族
            $('#ccy').attr('disabled',false);   //币种
            $('#interMethod').attr('disabled',false);   //币种
            $('#remark').attr('disabled',false);   //描述

            $('#intCurveType_redId').show();
            $('#ccy_redId').show();
            $('#interMethod_redId').show();
            $('#dayRules_redId').show();
            $('#calender_redId').show();
            $('#interestDateRule_redId').show();
            $('#interestCalMethod_redId').show();
            $('#interestFreq_redId').show();
            $('#remark_redId').show();
        }
    };



}());