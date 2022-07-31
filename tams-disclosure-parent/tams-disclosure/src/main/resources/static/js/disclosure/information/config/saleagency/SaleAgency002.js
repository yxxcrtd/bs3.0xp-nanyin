(function() {
    /**
     * 获取表单参数
     */
    var submitForm = $.serializeToObject('#submitForm');

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

    //画面联动：输入产品名称，带出产品主代码
    var prodNameChange = function() {
        if (!$('#prodName').select2('data') || $('#prodName').select2('data').length == 0 || !$('#prodName').select2('data')[0]) {
            return;
        }
        var finprodId = $('#prodName').select2('data')[0].id;
        $('#prodId').val(finprodId);
    };

    //维护类型联动
    var maintenanceTypeChange = function() {
        var maintenanceTyp = $('#maintenanceType').val();
        if('02'==maintenanceTyp){
            maintenanceTypeDivChange();
            $('#maintenanceType_agency_div').hide();
            $('#maintenanceType_prod_div').show();
        }else{
            maintenanceTypeDivChange();
            $('#maintenanceType_agency_div').show();
            $('#maintenanceType_prod_div').hide();
        }
    };

    /**
     * 清除上次录入的数据
     */
    var maintenanceTypeDivChange = function(){
        if(typeof pageModel !== 'undefined'
            && pageModel === PAGE_MODEL_NEW){
            $("#maintenanceType_agency_div").find(".layui-form-item .input-inline input").val("");
            $("#maintenanceType_agency_div").find(".layui-form-item .input-inline textarea").val("");
            $("#maintenanceType_agency_div").find(".layui-form-item .input-inline select").empty();
            $('#maintenanceType_prod_div').find(".layui-form-item .input-inline input").val("");
            $('#maintenanceType_prod_div').find(".layui-form-item .input-inline select").empty();
        }
    }



    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.prodIds=$('#prodIds').val()==null?null:$('#prodIds').val().join(",");
        object.agencyIds=$('#agencyIds').val()==null?null:$('#agencyIds').val().join(",");
        var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
        var nodes = treeObj.getCheckedNodes();
        var params = new Array();
        for (var i = 0; i < nodes.length; i++) {
            getParam(nodes[i], params);
        }
        object.nodes=params;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/saleagency/SaleAgency002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/saleagency/SaleAgency002/save', object, "SaleAgency002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/saleagency/SaleAgency002/save', object, "SaleAgency002_btn_submit");
                }
            });
        });
    }

    function　init(){

        $('#maintenanceType_agency_div').show();
        $('#maintenanceType_prod_div').hide();
        if(pageModel != PAGE_MODEL_NEW){
            $("#maintenanceType").attr("disabled",true);
            $("#agencyName").attr("disabled",true);
        }
        //修改时，如果协议起始日小于平台日，协议起始日不能修改
        if($('#agreementVdate').val() != '' && $('#agreementVdate').val() != null){
            if($('#agreementVdate').val()<platDate){
                $("#agreementVdate").attr("disabled",true);
            }
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#SaleAgency002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#SaleAgency002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        //维护类型联动
        $('#maintenanceType').change(function() {
            maintenanceTypeChange();
        });

        maintenanceTypeChange();

        //产品名称联动
        $('#prodName').change(function() {
            prodNameChange();
        });

        $('#expandAll').next().on('click',function(){
            expandAll();
        });

    });
}());