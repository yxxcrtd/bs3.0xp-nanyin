/**
 * 资产申报代码维护新建
 */
(function() {

    /**
     * 显示*号
     */
    var displayStar = function() {
        // 加红*
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
        if (typeof pageModel !== 'undefined' && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
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
    
    /**
	 * 验证保存信息
	 * 
	 * @param relateTypeChecked
	 * @param executiveInfos
	 * @returns
	 */
	function checkInput(obj) {
				if (obj.finprodId == null || obj.finprodId == "" || $.trim(obj.finprodId) == "") {
					Joyin.error("资产代码不可为空！");
					return false;
				}
				if (obj.othCodeType == null || obj.othCodeType == "" || $.trim(obj.othCodeType) == "") {
					Joyin.error("业务类型不可为空！");
					return false;
				}
				if (obj.othCode == null || obj.othCode == "" || $.trim(obj.othCode) == "") {
					Joyin.error("资产申报代码不可为空！");
					return false;
				}else if ($.trim(obj.othCode).getBytesLength() > 50) {
					Joyin.error("资产申报代码过长！");
					return false;
				}
		return true;
	}
	
	/**
	 * 获取字符串字节数
	 */
		
	String.prototype.getBytesLength = function() {
		var totalLength = 0;
		var charCode;
		for (var i = 0; i < this.length; i++) {
			charCode = this.charCodeAt(i);
			if (charCode < 0x007f) {
				totalLength++;
			} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
				totalLength += 2;
			} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
				totalLength += 3;
			} else {
				totalLength += 4;
			}
		}
		return totalLength;
	}

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#assetDeclarationCodeForm');
        if(!checkInput(object)){
        	return;
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        if(pageModel == PAGE_MODEL_NEW){
        	object.operateType ='new';
        }else if(pageModel == PAGE_MODEL_EDIT){
        	object.operateType ='update';
        }
        Joyin.ajaxJson(basePath + 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                	Joyin.ajaxJson(basePath + 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001/save', object, function(result){
                		WorkFlow.cancel();//暂时不关闭了
                	});
                }
            });
        });
    }

    var init = function(){
        if(pageModel == PAGE_MODEL_EDIT){
            displayStar();
        }
    };
    $(function() {
        init();
        if(pageModel != PAGE_MODEL_NEW){
            $("#finprodId").attr("disabled",true);
            $("#othCodeType").attr("disabled",true);
        }
        // 按钮事件
        $('#AssetDeclarationCodeMaintenance002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
}());

