/**
 * 债券行情信息新建
 */
(function() {
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
        var object = $.serializeToObject('#informationForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/save', object, "MstBondQuotationInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstbondquotationinfo/MstBondQuotationInfo002/save', object, "MstBondQuotationInfo002_btn_submit");
                }
            });
        });
    }
    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#secId').attr("disabled",true);
        		$('#valueDate').attr("disabled",true);
        		$('#valueSource').attr("disabled",true);
    		}
    	}
    };
    $(function() {
    	init();
        // 按钮事件
        $('#MstBondQuotationInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstBondQuotationInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#secId').change(function(){
        	var secId = $('#secId').val();
        	if(secId ==''|| secId==undefined){
        		$('#finprodAbbr').val('');
        	}
        	else{
//        		$('#finprodAbbr').val($('#secId').select2('data')[0].object).trigger('change');
        		$('#finprodAbbr').val(secId).trigger('change');
        	}
        })
        
    });
}());

function selectCallback(data) {
	$('#finprodAbbr').val(data.shortName);
}