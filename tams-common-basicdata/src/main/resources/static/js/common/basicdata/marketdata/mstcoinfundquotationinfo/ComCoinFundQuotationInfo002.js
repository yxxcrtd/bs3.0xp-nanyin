(function() {

	/**
	 * 进行保存和提交操作
	 * @param operateType 操作类型
	 * @returns
	 */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        if(pageModel==PAGE_MODEL_NEW){
            object.finprodId == "" ? object.finprodId = $("#finprodMarketId").val() : object.finprodId;
        }
        //设置pageModel和operateType
        object.pageModel = pageModel;
        object.operateType = operateType;
        //对保存事件进行验证
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/check', object, function(result) {
            if(operateType === 'save'){
                //保存工作流
    	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
    		        WorkFlow.doSaveList(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/save', object, "ComCoinFundQuotationInfo002_btn_save");
    	        });
            }else{
                //提交工作流
    	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
    	                WorkFlow.doSubmitList(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/save', object, "ComCoinFundQuotationInfo002_btn_submit");
    	        });
    	    }
    	});
    };
    /**
     * 界面初始化时进行调用，对界面做一要求。基金代码、估值日、基金管理人和基金类型置灰
     */
    var init = function(){
    	
        $("#fundManager").attr("disabled",true);
        $('#finprodChiType').attr("disabled",true);
        if ($("#finprodType2").val()=='F02') {
            //$("#day7YearYield_div").hide();
        }
        if ($("#finprodType2").val()=='F01') {
            //$("#totalValue_div").hide();
            //$("#restNetValue_div").hide();
        }
        if (!$("#finprodType2").val()) {
            //$("#day7YearYield_div").hide();
            //$("#totalValue_div").hide();
            //$("#restNetValue_div").hide();
        }
        if(pageModel === PAGE_MODEL_NEW){
        	if (!$('#publishDate').val()) {
	        	getIplatDate();
        	}
        }else{
        	$('#finprodMarketId').attr("disabled",true);
            $('#valDate').attr("disabled",true);
        }
    };
    /**
     * 获取平台日
     */
    var getIplatDate = function(){
        Joyin.ajax(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/getIplatDate', {}, function(data){
            $('#publishDate').val(data);
        });
    }
   /**
    * 下拉框进行选择时进行一些联动操作，基金管理人和基金类型进行联动显示
    */
    var finprodIdChange = function() {
        var finprodId = $("#finprodMarketId").val();
        $("#fundManager").val("");
        $('#finprodType2').val("");
        $('#finprodChiType').val("");
            var object = {'finprodId':finprodId};
            Joyin.ajax(basePath + 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002/getManageAndType', object, function(data){
                if(data){
                    $("#fundManager").val(data.fundManager).trigger('change');
                    $('#finprodType2').val(data.finprodType).trigger('change');
                    $('#finprodChiType').val(data.finprodType2).trigger('change');

                    /** modify by yanya on 20210224 基金类型判定修改 start**/
                    //如果基金类型为货币基金 add by gejiaao  2019/8/15
                    //if (data.finprodType=='F01'){
                    if (data.finprodType=='01'){
                        //$("#day7YearYield_div").show();
                        $("#day7YearYield").removeAttr("disabled");
                        //$("#totalValue_div").hide();
                        //$("#restNetValue_div").hide();
                        $("#restNetValue").val('').trigger('change');
                        $("#restNetValue").attr("disabled",true);
                    }else {
                        //$("#day7YearYield_div").hide();
                        $("#day7YearYield").val('').trigger('change');
                        $("#day7YearYield").attr("disabled",true);
                        //$("#totalValue_div").show();
                        //$("#restNetValue_div").show();
                        $("#restNetValue").removeAttr("disabled");
                    }
                    /** modify by yanya on 20210224 基金类型判定修改 end**/
                }
            });
            $("#fundManager").attr("disabled",true);
            $('#finprodChiType').attr("disabled",true);
        
    };
    /**
     * 画面加载完成
     */
    $(function() {
        //初始化
        init();
        //绑定保存事件
        $("#ComCoinFundQuotationInfo002_btn_save").click(function(){
            saveOrSubmit('save');
        });
        //绑定提交事件
        $("#ComCoinFundQuotationInfo002_btn_submit").click(function(){
            saveOrSubmit('submit');
        });
        //绑定下拉框改变事件
        $('#finprodMarketId').change(function() {
            finprodIdChange();
        });
    });
}());