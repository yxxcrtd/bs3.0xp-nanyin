/**
 * 销售结算资金交易信息维护新建
 */
(function() {
    /**
     * 获取表单参数
     */
        var informationform = function() {
            var param = $.serializeToObject('#informationForm');
            return param;
        }

        /**
         * 保存或提交
         */
        function saveOrSubmit(operateType) {
            var object = informationform();
            object.operateType = operateType;
            object.pageModel = pageModel;
            Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution002/save', object, "AgentInstitution002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution002/save', object, "AgentInstitution002_btn_submit");
                    }
                });
            });
        }
        //获取机构代码默认值
                    function getIssuePartyId(){
                       var agentInstitutionCode = $('#agentInstitutionCode').val();
                       Joyin.ajax(JoyinUtil.getRealPath('report/submitmaintenance/agentprdinfo/AgentPrdInfo002/getIssuePartyId'),{agentInstitutionCode : agentInstitutionCode},function(result){
                                 debugger;
                                 if (result){
                                   $('#issuePartyId').val(result);
                                 }
                       });
                    }

          //控制新建，编辑时的输入框置灰
           var init = function(){
           if(pageModel==PAGE_MODEL_EDIT){
              $('#issuePartyId').prop("disabled",true);
              $('#agentInstitutionCode').prop("disabled",true);
           }
           else{
              $('#issuePartyId').prop("disabled",true);
              getIssuePartyId();
           }
           }


        $(function() {
            //字段置灰
            init();
            // 按钮事件
            $('#AgentInstitution002_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#AgentInstitution002_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
            
        });    
}());