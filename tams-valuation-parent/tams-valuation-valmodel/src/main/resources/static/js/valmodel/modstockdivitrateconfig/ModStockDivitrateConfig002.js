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
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmitList(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/save', object, "ModStockDivitrateConfig002_btn_submit");
            });
        });
    }
    var init = function(){
    	if(pageModel==PAGE_MODEL_EDIT){
    		$('#finprodId').attr("disabled",true);
    	}
		/*else if(pageModel==PAGE_MODEL_NEW){
			$('#finprodId').attr("data-url","/tams/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/getAssetCodeListExceptExsits");
			$('#finprodId').select2();
		}*/
	
	};
	

    $(function() {
    	init();
        // 按钮事件
        /*$('#ModStockDivitrateConfig002_btn_save').click(function() {
            saveOrSubmit('save');
        });*/
        $('#ModStockDivitrateConfig002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#finprodId').change(function(){
        	var finprodId = $('#finprodId').val();
        	if(finprodId ==''|| finprodId==undefined){
        		$('#finProdName').val('');
				$('#secTypeModel').val('');
				$('#secDetailTypeModel').val('');
        	}else{
        		$('#finProdName').val($('#finprodId').select2('data')[0].text).trigger('change');
				var fiveClass = $('#finprodId').select2('data')[0].object;
				Joyin.ajaxSync(basePath + 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002/findModAseetMapping',{
					output5:fiveClass},function(result){
					if(result){
						$('#secTypeModel').val(result.input1).select2();
						$('#secDetailTypeModel').val(result.input2).select2();
					}
				});
        	}
        	
        });
        $('#finprodId').trigger('change');
    });
}());

function selectCallback(data) {
	$('#finprodAbbr').val(data.shortName);
}