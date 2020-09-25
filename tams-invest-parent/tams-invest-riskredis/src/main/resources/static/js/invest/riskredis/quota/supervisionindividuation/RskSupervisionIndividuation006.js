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
    	if(!$('#uniteIndId')){
        	Joyin.error("请输入指标名称");
            return;
        }
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
        object.pageId = "RskSupervisionIndividuation006";
        var uniteIndIdStr = "";
        for(var i=0;i<$('#uniteIndId').select2('data').length;i++){
        	uniteIndIdStr+=$('#uniteIndId').select2('data')[i].text+",";
        }
        if(uniteIndIdStr!=''){
        	uniteIndIdStr=uniteIndIdStr.substring(0,uniteIndIdStr.length-1);//去除逗号
    	}
        object.uniteIndIdStr = uniteIndIdStr;
        object.pageModel = pageModel;
    	top.returnData=object;
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
    $(function() {
//    	init();
        // 按钮事件
        $('#RskSupervisionIndividuation006_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());