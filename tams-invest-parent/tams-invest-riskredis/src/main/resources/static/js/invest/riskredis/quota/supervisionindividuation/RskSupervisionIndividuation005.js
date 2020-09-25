/**
 * 条件新建
 */
$(function() {
	 
    //var editable = pageModel != PAGE_MODEL_REFERENCE;
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	if(!$('#finprodId')){
        	Joyin.error("请输入证券代码");
            return;
        }
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
        object.pageId = "RskSupervisionIndividuation005";
        object.pageModel = pageModel;
    	top.returnData=object;
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    
  //必填项 
	function requiredW(){
		if(!$("#finprodId").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"证券代码"));
			return false;
    	}
		if(!$("#finprodName").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"证券名称"));
			return false;
    	}
		if(!$("#startDate").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"开始时间"));
			return false;
    	}
		if(!$("#endDate").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"结束时间"));
			return false;
    	}
		if($("#startDate").val()&&$("#endDate").val()&&$("#endDate").val()<$("#startDate").val()){
			Joyin.warn("开始时间不允许大于结束时间!");
			return false;
		}
		return true;
	};
    var finprodIdChange = function(){
		var finprodName = $('#finprodId').select2('data')[0].object;
		$('#finprodName').val(finprodName).trigger('change');
	}
    $(function() {
//    	init();
        // 按钮事件
        $('#RskSupervisionIndividuation005_btn_save').click(function() {
        	if(!requiredW()){
    			return;
		 	}
            saveOrSubmit('save');
        });
        $("#finprodId").change(function(){
        	finprodIdChange();
        })
    });
}());