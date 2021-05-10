/**
 * 资产减值阶段管理新建
 */
(function () {


    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#searchForm');
        object.effDate = $("#effDate").val();
        object.operateType = operateType;
        if (pageModel == PAGE_MODEL_NEW){
            Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/check', object, function(result1) {
               // WorkFlow.doSaveList(basePath+'valuation/financemanage/finproductlossstage/FinProductLossStage001/save',object,elementId||"FinProductLossStage002_btn_save");
                Joyin.confirm("是否保存？", function () {
                    Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002/saveFinProLosSta', object, function () {
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                });
            });
        }else if (pageModel == PAGE_MODEL_EDIT){
            Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/check', object, function(result1) {
                Joyin.confirm("是否修改？", function () {
                    Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002/editFinProLosSta', object,function () {
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                });
            });
        }
    }

    // /**
    //  * 画星星
    //  */
    // var star = function () {
    //     // 加红星星
    //     // 查找label
    //     var _findPrevLabel = function (element, level) {
    //         var $el = element.prev();
    //         if (!$el.is('label') && level > 0) {
    //             $el = $el.find('label');
    //             if ($el.length === 0) {
    //                 return _findPrevLabel(element.parent(), level - 1);
    //             }
    //         }
    //         return $el;
    //     };
    //
    //     // 非查看模式
    //     if (typeof pageModel !== 'undefined'
    //         && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
    //         $('.must').each(function () {
    //             $(this).remove();
    //         })
    //         $('*[required]').each(function () {
    //             var $el = $(this);
    //             var $target = _findPrevLabel($el, 3);
    //             if ($target.find('> .must').length === 0) {
    //                 $target.append($('<i class="must">*</i>'));
    //             }
    //         });
    //     }
    // }

    /**
     * 绑定事件
     */
    $(function () {
        /**
         * 保存
         */
        $('#FinProductLossStage002_btn_save').click(function (e) {
            saveOrSubmit('save');
        });

        if(pageModel == PAGE_MODEL_EDIT){
            $('#finprodId').attr("disabled",true);
            $('#effDate').attr("disabled",true);
        }

        /**
         * 当新建的时候，将生效日设置为平台日并且置灰
         */
        if (pageModel == PAGE_MODEL_NEW){
            $('#effDate').attr("disabled",true);
        }
    });
}());
