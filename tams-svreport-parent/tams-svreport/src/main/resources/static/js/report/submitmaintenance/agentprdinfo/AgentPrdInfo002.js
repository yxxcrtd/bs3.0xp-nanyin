(function() {
    /**
     * 获取表单参数
     */
        var informationform = function() {
            var param = $.serializeToObject('#informationForm');
            return param;
        }


           //代销机构代码change事件
           function agentInstitutionCodeOnChange(){
               var agentInstitutionCode = $('#agentInstitutionCode').val();
               if (agentInstitutionCode){
                  Joyin.ajax(JoyinUtil.getRealPath('report/submitmaintenance/agentprdinfo/AgentPrdInfo002/queryNameByCode'), {agentInstitutionCode : agentInstitutionCode}, function(result){
                        if (result){
                                    $('#agentInstitutionName').val(result.agentInstitutionName);
                        }
                  });
               }
            }

            //获取机构代码默认值
            function getIssuePartyId(){
               var agentInstitutionCode = $('#agentInstitutionCode').val();
               Joyin.ajax(JoyinUtil.getRealPath('report/submitmaintenance/agentprdinfo/AgentPrdInfo002/getIssuePartyId'), {agentInstitutionCode : agentInstitutionCode}, function(result){
                         if (result){
                           $('#issuePartyId').val(result);
                         }
               });
            }

        /**
         * 保存或提交
         */
        function saveOrSubmit(operateType) {
            var object = informationform();
            object.operateType = operateType;
            object.pageModel = pageModel;
            Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentprdinfo/AgentPrdInfo002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'report/submitmaintenance/agentprdinfo/AgentPrdInfo002/save', object, "AgentPrdInfo002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'report/submitmaintenance/agentprdinfo/AgentPrdInfo002/save', object, "AgentPrdInfo002_btn_submit");
                    }
                });
            });
        }

          //控制编辑时的输入框置灰
           var init = function(){
           if(pageModel==PAGE_MODEL_EDIT){
                     $('#agentInstitutionCode').prop("disabled",true);
                     $('#issuePartyId').prop("disabled",true);
                     $('#agentInstitutionName').prop("disabled",true);
           }
           else{
                     $('#issuePartyId').prop("disabled",true);
                     $('#agentInstitutionName').prop("disabled",true);
                     getIssuePartyId();
           }
           }

      $(document).ready(function(){
            $('#agentInstitutionCode').change(function(){
               agentInstitutionCodeOnChange();
            });
      });

        $(function() {
            //编辑字段置灰
            init();
            // 按钮事件
            $('#AgentPrdInfo002_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#AgentPrdInfo002_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
        });    
}());