/**
 * FinNetProductBonusScheme002 指标参数新建
 */
(function() {
    

    //初始化
    var init = function () {
        if(pageModel==PAGE_MODEL_EDIT&&SYS_DATE!=null&&SYS_DATE!=''){
        	var sysDate = new Date(SYS_DATE);
        	sysDate = sysDate.format('yyyy-MM-dd');
        	var validDate = $("#validDate").val();
        	//validDate = validDate.format('yyyy-mm-dd');
        	if(validDate<SYS_DATE){
        		$("#validDate").attr("disabled",true);
        		$("#paraName").attr("disabled",true);
        		$("#content").attr("disabled",true);
        	}
        }
    }

    //保存按钮
    function saveOrSubmit(operateType){
        var object = $.serializeToObject('#submitForm',true);
        //-----add by 裴家俊  2019/03/20-----end---------
        object.pageModel = pageModel;
        object.workflowId = $("#workflowId").val();
        object.paraCode = $("#paraCode").val();
        var institutionId = $('#institutionId').val();
        if(!institutionId ||  institutionId.length ==0 ){
        	Joyin.error("请输入机构！");
			return;
        }
        var institutionCode = null;
        if(institutionId != null)
        	institutionCode=institutionId.join(",");
        object.institutionCode=institutionCode;
        object.pageId="RskSupervisionIndicatorParam003";
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam003/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam003/save', object, "RskSupervisionIndicatorParam003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam003/save', object, "RskSupervisionIndicatorParam003_btn_submit");
                }
            });
        });
    };
    
   
    /**
     * 画面加载完成
     *
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#RskSupervisionIndicatorParam003_btn_save').click(function() {
        	saveOrSubmit('save');
        });
        $('#RskSupervisionIndicatorParam003_btn_submit').click(function() {
        	saveOrSubmit('submit');
        });
        //--add by 裴家俊 20190506---分类维度联动
        
        //--add by 裴家俊 20190506---分类维度联动
        //类型联动
        
        //var form = layui.form;
        
        //----add by 裴家俊 2019/05/06----end------
        
    });
}());

