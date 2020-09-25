//var file;
(function() {
	/**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
                && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
	//存放多文件上传的文件信息
	var fileArray =[];
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#informationForm');
    	if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo()
    	}
    	object.acctNo = object.largePayno + ':' + object.busiAcctNo;
        object.remark=$('#remark').val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/save', object, "MstCaptialAccount002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/save', object, "MstCaptialAccount002_btn_submit");
                }
            });
        });
    }
    
    var editable = pageModel != PAGE_MODEL_REFERENCE;
    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#busiAcctNo').attr("disabled",true);
        		$('#largePayno').attr("disabled",true);
        		$('#inOutBank').attr("disabled",true).trigger('change');

        		$('#ccy').attr("disabled",true);
        		$('#swiftCode').attr("disabled",true);
    		}
    	}
    	var ccy = $('#ccy').val();
        if(ccy == CNY){
        	$('#swiftCode').attr("required",false).trigger('change');
        }
        else{
        	$('#swiftCode').attr("required",true).trigger('change');
        }
        star();
    };
        
        
    $(function() {
    	init();
        // 按钮事件
        $('#MstCaptialAccount002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstCaptialAccount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#largePayno').change(function(){
            var largePayno = $('#largePayno').val();
            if(largePayno.length > 12){
                Joyin.error(JoyinUtil.getMessage("MST_CAP_COUNT_1"));
            }
        });
        $('#ccy').change(function(){
            var ccy = $('#ccy').val();
            if(ccy == CNY){
            	$('#swiftCode').attr("required",false).trigger('change');
            }
            else{
            	$('#swiftCode').attr("required",true).trigger('change');
            }
            star();
        });
    });
}());
