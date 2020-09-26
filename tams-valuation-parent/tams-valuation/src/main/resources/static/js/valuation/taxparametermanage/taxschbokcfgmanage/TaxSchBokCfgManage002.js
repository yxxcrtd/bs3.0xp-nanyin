$(function () {

	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#dataForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		
		Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/check', object, function(result) {
		    if (result.data || result.messageType === 'info') {
                Joyin.confirm(result.data, function() {
                    doComplete(operateType, object);
                });
            } else {
                doComplete(operateType, object);
            }


		});
	};

	var doComplete = function(operateType, object){
        if(operateType === 'save'){
            WorkFlow.doSaveList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/save',
                object, "TaxSchBokCfgManage002_btn_save");
        }else{// submit
            WorkFlow.doSubmitList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/save',
                object, "TaxSchBokCfgManage002_btn_submit");
        }
    };
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
        $("#configId").attr("disabled", "disabled");
        if (pageModel !== PAGE_MODEL_NEW){
            $("#tableId").attr("disabled", "disabled");
            $("#subjectNature").attr("disabled", "disabled");
            $("#vatType").attr("disabled", "disabled");
            $("#taxPType").attr("disabled", "disabled");
        }
    };
	
    $(function() {
        // 绑定按钮事件
        $('#TaxSchBokCfgManage002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TaxSchBokCfgManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
        
        initShow();// 根据操作类型：显示按钮等

    });
});