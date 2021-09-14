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
        /*var curveGroupType = $('#curveGroupType')[0].checked;
        if (curveGroupType === false) {
            $('#intCurveType').attr('required',true);
            $('#ccy').attr('required',true);
            $('#interMethod').attr('required',true);
            $('#dayRules').attr('required',true);
            $('#calender').attr('required',true);
            $('#interestCalMethod').attr('required',true);
            $('#interestFreq').attr('required',true);
            $('#remark').attr('required',true);
        }*/

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

          /*  $('#intCurveType').prop('required',true);
            $('#ccy').attr('required',false);
            $('#interMethod').attr('required',false);
            $('#dayRules').attr('required',false);
            $('#calender').attr('required',false);
            $('#interestCalMethod').attr('required',false);
            $('#interestFreq').attr('required',false);
            $('#remark').attr('required',false);*/

        }else{
            $('#intCurveType').attr('disabled',false);//曲线类型
            $('#gradeResult').attr('disabled',false);   //评级
            $('#n').attr('disabled',false);   //N
            $('#k').attr('disabled',false);   //K
            $('#curveGroupCode').attr('disabled',false);   //所属曲线族
            $('#ccy').attr('disabled',false);   //币种
            $('#interMethod').attr('disabled',false);   //币种
            $('#remark').attr('disabled',false);   //描述

           /* $('#intCurveType').attr('required',true).trigger('change');
            $('#ccy').attr('required',true).trigger('change');
            $('#interMethod').attr('required',true).trigger('change');
            $('#dayRules').attr('required',true);
            $('#calender').attr('required',true);
            $('#interestCalMethod').attr('required',true);
            $('#interestFreq').attr('required',true);
            $('#remark').attr('required',true);*/
        }
    };



}());