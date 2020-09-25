(function() {
   
    var rateTypeChange = function(){
        var rateType = $("#rateType").val();
        if('01'==rateType){
            $("#currentRateDiv").hide();
            $("#currentRate").val("").trigger("change");
            $("#rateDiv").show();
        }else{
            $("#rateDiv").hide();
            $("#rate").val("").trigger("change");
            $("#currentRateDiv").show();
        }
        if(pageModel!=PAGE_MODEL_NEW){
            $("#busiAcctNo").attr("disabled",true);
            $("#ccy").attr("disabled",true);
            $("#rateType").attr("disabled",true);
            //update by leipeng  【账户活期利率管理】：修改账户活期利率，计息基础请改为不可编辑
            $("#basis").attr("disabled",true);
        }
    }
    
    $("#busiAcctNo").change(function(){
        $("#ccy").val($("#busiAcctNo").select2('data')[0].object).trigger('change');
    });
   
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.acctName = $("#busiAcctNo option:selected").text();
        if ($("#currentRate").select2('data')[0].object) {
            object.indexSource = $("#currentRate").select2('data')[0].object;
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/check', object, function(result) {
                if(operateType === 'save') {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                        WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/save', object, "PtlAccountCurrentInterest002_btn_save");
                    });
                }
                else {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                        WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/save', object, "PtlAccountCurrentInterest002_btn_submit");
                    });
                }
        });
    }
   
    function init(){
    	//初始化判断
    	rateTypeChange();
    	
        if(pageModel == PAGE_MODEL_NEW){
            Joyin.ajax(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/getSysDate', {}, function(result) {//数据验证
                $("#effDate").val(result);
                $("#basis").val('A/360').trigger('change');
            });
        }
    }
    $(function() {
        init();
        //利率类型联动
        $("#rateType").change(rateTypeChange);
        // 绑定查询按钮
        $('#PtlAccountCurrentInterest002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#PtlAccountCurrentInterest002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());