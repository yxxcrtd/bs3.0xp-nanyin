(function() {

    /**
     * 加载树结构
     */
    $('#tree').joyinTree({
        onTreeLoadSuccess: function () {
            if(pageModel == PAGE_MODEL_REFERENCE) {
                var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    var node = nodes[i];
                    DisabledNode(treeObj, node);
                }
            }
        }
    });

    function expandAll() {
        $.fn.zTree.getZTreeObj('tree_tree').expandAll(!$('#expandAll').is(':checked'));
    };

    $('#portfolioId').change(function(){
        $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
    });
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    }

    /**
     * 递归获取节点和父节点数据
     */
    function getParam(node, params) {
        if (node) {
            if(node.id != null && $.inArray(node.id, params) < 0){
                params.push(node.id);
            }
            getParam(node.getParentNode(), params);
        }
    }

    function init(){
        $("#portfolioId").attr("disabled",true);
        $("#portfolioName").attr("disabled",true);
        $("#profitType").attr("disabled",true);
        $("#raiseType").attr("disabled",true);
        $("#investNature").attr("disabled",true);
        $("#vdate").attr("disabled",true);
        $("#mdate").attr("disabled",true);
        if(pageModel == PAGE_MODEL_EDIT || entity.pageModel == PAGE_MODEL_EDIT){
            $("#childPortfolioId").attr("disabled",true);
            $("#userDiv").show();
        }else {
            $("#userDiv").hide();
        }
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
        var object = $.serializeToObject('#basicInfoForm',true);
        object.operateType = operateType;
        //组合类型为子组合
        object.portfolioType = "03";
        var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
        var nodes = treeObj.getCheckedNodes();
        var params = new Array();
        for (var i = 0; i < nodes.length; i++) {
            getParam(nodes[i], params);
        }
        if(params.length == 0){
            if (pageModel == PAGE_MODEL_EDIT){
                Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0011"));
                return;
            } else {
                params = null;
            }
        }
        object.userId = params;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/check', object, function (result) {
            if(operateType === 'save') {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/save', object, "PtlManagementInfo004_btn_save");
                });
            }else {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.submit")), function () {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/save', object, "PtlManagementInfo004_btn_submit");
                });
            }


        });
    }

    $(function() {
        init();

        $('#expandAll1').on('click','.layui-form-switch',function(){
            expandAll();
        });
        // 按钮事件
        $('#PtlManagementInfo004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlManagementInfo004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());