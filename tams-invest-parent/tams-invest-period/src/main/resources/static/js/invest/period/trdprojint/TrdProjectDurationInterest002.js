(function () {
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var checkResult = FinPayInterestStdInfo.comfirmCheck();
        if (checkResult) {
            var object = $.serializeToObject('#informationForm');
            object.operateType = operateType;
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdprojduration/TrdProjectDuration002/save', object, "TrdProjectDuration002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdprojduration/TrdProjectDuration002/save', object, "TrdProjectDuration002_btn_submit");
                }
            });
        }
    }

    $(function () {
        $('#TrdProjectDurationInterest002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdProjectDurationInterest002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    })
}());
