$(function () {

    /**
     * 修改/新建
     */
    var saveOrSubmit = function(operateType) {
        var object = $.serializeToObject('#searchForm');
        object.pageModel = pageModel;
        object.operateType = operateType;

        if(object.spread){
            var spread = object.spread/100;
            spread = spread.toFixed(6);
            object.spread = spread;
        }

        if(object.spreadAdj){
            var spreadAdj = object.spreadAdj/100;
            spreadAdj = spreadAdj.toFixed(6);
            object.spreadAdj = spreadAdj;
        }

        Joyin.ajaxJson(basePath + 'valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002/check', object, function(result) {
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
            WorkFlow.doSaveList(basePath + 'valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002/save',
                object, "RiskPremiumAdjConf002_btn_save");
        }else{// submit
            WorkFlow.doSubmitList(basePath + 'valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002/save',
                object, "RiskPremiumAdjConf002_btn_submit");
        }
    };


    /**
     * 计算
     */
    var initShow = function () {

    };

    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
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
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    $(function() {
        $('#AssetImpairmentManagement002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $("#spreadAdj").change(function(){
            var spreadAdj = $('#spreadAdj').val();
            if(spreadAdj<-50 || spreadAdj>50){
                layer.alert('维护范围为[-50,50]！');
                $('#spreadAdj').val('');
                return ;
            }
        });

        star();
        initShow();// 根据操作类型：显示按钮等

    });
});