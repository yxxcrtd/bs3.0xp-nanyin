 /**
 * RskValSumname003 映射明细新建（含修改）
 */
$(function() {
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
    $("#RskValSumname003_btn_submit").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "submit";
        object.pageModel = pageModel;
        //判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/insert',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/update',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                })
            })
        }
    });
    //页面初始化
    var init = function(){
    	if(pageModel==PAGE_MODEL_EDIT){
    		$('#sumCode').attr("disabled",true);
    		if(hasNextIndex == YES_OR_NO_Y){
    			$('#nextIndexType').attr("disabled",false);
    			star();
    		}
    		else{
    			$('#nextIndexType').attr("disabled",true);
        		$('#nextIndexType').val('').trigger('change');
        		star();
    		}
    	}
    	
    	
    };
    $(function() {
    	init();
    	 /*$("#hasNextIndex").change(function(){
    		var hasNextIndex = $("input[type='radio'][name='hasNextIndex']:checked").val()
        	if(hasNextIndex != YES_OR_NO_Y){
        		$('#nextIndexType').attr("disabled",true);
        		$('#nextIndexType').val('').trigger('change');
        	}else{
        		$('#nextIndexType').attr("disabled",false);
        	}
        	star();
        })*/
        layui.form.on('radio(hasNextIndexFilterN)', function(data){
        	if(this.checked){
        		$('#nextIndexType').attr("disabled",true);
        		$('#nextIndexType').val('').trigger('change');
        		star();
        	}
        });
    	 layui.form.on('radio(hasNextIndexFilterY)', function(data){
         	if(this.checked){
         		$('#nextIndexType').attr("disabled",false);
         		star();
         	}
         });
    	 
    });
});
