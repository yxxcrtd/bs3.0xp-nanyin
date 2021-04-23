(function() {

	$(function () {
    	
    	/**
    	 * 机构代码
    	 */
    	$('#partyName').change(function(){
    		partyNameChangeAction();
        });
    	
    });
	

    /**
	 * 机构代码
	 */
    var partyNameChangeAction = function(){
    	var data = $('#partyName').select2('data')[0].object;
    	if (data){
    		$('#partyId').val(data.partyId);
    		$('#partyCustomerId').val(data.partyCustomerId);
    	}
    }

    /**
     * 画面加载完成
     */
    $(function() {
        /**
         * 提交事件
         */
        $('#CfetsPortfolioAcctRela002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        // 按钮事件
        $('#CfetsPortfolioAcctRela002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });

	/**
	 * 保存提交事件
	 * @param operateType
	 * @returns
	 */
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#CfetsPortfolioAcctRela002_form');

		var obj =  {
            portfolioId: $('#portfolioId').val()//组合代码
        }
		if(obj.portfolioId){
            object.portfolioId = obj.portfolioId.join(',');
        }

        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela002/save', object, "CfetsPortfolioAcctRela002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/basic/cfetsportfolioacctrela/CfetsPortfolioAcctRela002/save', object, "CfetsPortfolioAcctRela002_btn_submit");
                }
            });
        });
    };
}());