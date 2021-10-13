$(function() {
    
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm',true);
        object.operateType = operateType;
        //还本计划
        if(BussFinRepayment){
            object.bussFinRepaymentDto = {};
            object.bussFinRepaymentDto.finCashPlanList = BussFinRepayment.getAllRows();
        }
        finalSave(object,operateType);
    };
    
    /**
     * 最终提交
     */
    var finalSave = function(object,operateType){
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinRepayPlan/check', object, function(result) {
            if(BussFinRepayment&& BussFinRepayment.repayPlanCheck($('#vdate').val(),$('#mdate').val(),$('#issueAmt').val())){
                    Joyin.confirm(result.data, function() {
                        if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/asset/common/FinRepayPlan/save', object, "FinRepayPlan_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'invest/asset/common/FinRepayPlan/save', object, "FinRepayPlan_btn_submit");
                        }
                    });
                }
        });
    }
    
    
    
    $(function(){
        // 绑定按钮事件
        $('#FinRepayPlan_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinRepayPlan_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
    });
