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
        if(pageModel != PAGE_MODEL_NEW){
            //曲线代码不可修改
            $('#curveCode').attr('disabled',true);
        }
    }

}());