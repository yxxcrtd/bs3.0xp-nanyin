(function(){
	//add by tuqirong 2019-03-25 start 定义开放计划中的确认天数
	var redSpeed;
	var purSpeed;
	//add by tuqirong 2019-03-25 end
	
	/**
	 * 初始化方法
	 */
	var ColProductRequirement002Init = function(){
		if(pageModel === PAGE_MODEL_EDIT ){
			$('#requirementTime').prop('disabled', true);
		}
	}
    /**
     * 添加必填标志
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
	
	/**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#ColProductRequirement002_form',true);
		object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement002/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement002/save', object, "ColProductRequirement002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/rddesign/colproductrequirement/ColProductRequirement002/save', object, "ColProductRequirement002_btn_submit");
                    }
                })
            })
	}
	
	$(function() {
		ColProductRequirement002Init();
		
		// 保存按钮执行事件
		$('#ColProductRequirement002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#ColProductRequirement002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
	});
}());
