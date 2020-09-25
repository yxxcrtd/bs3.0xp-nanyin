var QueryFunc=function(){
    return {
        serachKey:function(){
        }
    }
}();

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
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW || pageModel === PAGE_MODEL_REFERENCE)) {
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

    var getElementById = function(id) {
        return $('#' + id);
    };
    
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        //多选
        object.assetProvider = $('#assetProvider').val()?$('#assetProvider').val().join(","):'';
        object.pageModel = pageModel;
        object.operateType = operateType;
        
        Joyin.ajaxJson(basePath + 'invest/asset/option/FinOptionTarget002/check', object, function(result) {
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'invest/asset/option/FinOptionTarget002/save', object, "FinOptionTarget002_btn_save");
            } else {
                WorkFlow.doSubmitList(basePath + 'invest/asset/option/FinOptionTarget002/save', object, "FinOptionTarget002_btn_submit");
            }
        });
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinOptionTarget002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinOptionTarget002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        if(pageModel == PAGE_MODEL_EDIT){
            $('#targetId').attr("disabled",true);
        }
        //除新建画面 ，资产类型置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $('.btn-save').hide();
        }
  
    });
}());

