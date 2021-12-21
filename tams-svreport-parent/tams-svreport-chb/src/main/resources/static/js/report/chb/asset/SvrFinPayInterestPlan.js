$(function() {
    //获取付息画面元素id
    var getPayInterestStdInfoElementById = function(id,type) {
        if(type=="div"){
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id+'_'+'div');
        }else{
            return $('#' + 'finPayInterestStdInfoDto' + '_' + id);
        }
    };
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm',true);
        object.operateType = operateType;
        //付息计划
        if(FinPayInterestStdInfo){
            object.finPayInterestStdInfoDto = {};
            object.finPayInterestStdInfoDto.finCashPlanList = FinPayInterestStdInfo.getAllRows();
        }
        finalSava(object,operateType);
    };
    
    /**
     * 最终提交
     */
    var finalSava = function(object,operateType){
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinPayInterestPlan/check', object, function(result) {
            if(FinPayInterestStdInfo&& FinPayInterestStdInfo.comfirmCheck()){
                    Joyin.confirm(result.data, function() {
                        if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/asset/common/FinPayInterestPlan/save', object, "FinPayInterestPlan_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'invest/asset/common/FinPayInterestPlan/save', object, "FinPayInterestPlan_btn_submit");
                        }
                    });
                }
        });
    }
    
    
    /**
     * 付息信息画面显示/隐藏
     */
    var hidePayInterestStdInfoDiv = function(){
        var obj = $('#finPayInterestStdInfoDto_div');
        getPayInterestStdInfoElementById('form','div').hide();
        getPayInterestStdInfoElementById('title','div').hide();
    }
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //付息信息展示/隐藏
            hidePayInterestStdInfoDiv();
        }
    }
    
    $(function(){
        // 绑定按钮事件
        $('#FinPayInterestPlan_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinPayInterestPlan_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
    });
