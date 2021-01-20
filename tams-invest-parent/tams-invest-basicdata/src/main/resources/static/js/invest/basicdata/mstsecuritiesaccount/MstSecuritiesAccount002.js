/**
 * 证券账户管理
 */
$(function() {
    /**
     * 保存或提交
     * 
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.remark = $('#remark').val();
        object.recAcctId=$('#recAcctId').select2('data')[0].id;
        object.recAcctCcy=$('#recAcctId').select2('data')[0].col;
        object.trdAcctCcy=$('#trdAcctId').select2('data')[0].col;
        object.trdAcctId=$('#trdAcctId').select2('data')[0].id;
        object.pageModel = pageModel;
    	if ($("#registOrg").val() == "03") {
    		//暂不支持多选
    		//object.tradePlace=$("#tradePlace").val().join();
    		object.tradePlace=$("#tradePlace").val();
    	}else{
    		object.tradePlace=$("#tradePlace2").val();
    	}
    	if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo()
    	}
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/check',
                object, function(result) {
                    Joyin.confirm(result.data, function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath
                                    + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/save',
                                    object, "MstSecuritiesAccount002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath
                                    + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/save',
                                    object, "MstSecuritiesAccount002_btn_submit");
                        }
                    });
                });
   
    };
    /*【证券账户管理】：增加逻辑,当托管机构选择“中国证券登记结算有限责任公司”，
     * 交易场所支持多选，多选选项限制为：上海证券交易所、深圳证券交易所*/
	    var registOrgChange = function() {
			if ($("#registOrg").val() == "03") {
				var op = document.getElementsByName('tradePlace')[0]
		    	.getElementsByTagName('option');
				for (var i = 0; i < op.length; i++) {
					if (op[i].value == "01") {
						op[i].remove(op[i]);
					}
				};
				var p = document.getElementById("tradePlaceChange");
				p.removeAttribute('style');
				
				//暂不支持多选
				// var p = document.getElementById("tradePlace");
				// p.removeAttribute('multiple');
				
				var p = document.getElementById("tradePlaceOld");
				p.setAttribute('style','display:none');
				
		} else {
			var p = document.getElementById("tradePlaceChange");
			p.setAttribute('style','display:none');
			
			var p = document.getElementById("tradePlaceOld");
			p.removeAttribute('style');
		}
	};
	
    /**
	 * 画面加载完成
	 */
    $(function() {
        if (!$('#secAcctSource').val()) {
            $('#secAcctSource').val('01').trigger('change');
        }
        
        $('#registOrg').on('change',function(){
        	registOrgChange();
        });
        if ($('#registOrg').val()=='03') {
        	registOrgChange();
        }
        if($('#pageModel').val()!=''){
        	pageModel=$('#pageModel').val();
        }
        //绑定init界面转换事件
    	if(pageModel==PAGE_MODEL_EDIT){
            $('#secAcctId').attr("disabled","disabled");
        }
        // 绑定按钮事件
        $('#MstSecuritiesAccount002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstSecuritiesAccount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
