/**
 * FinSubmittedModule001 申报附件方案管理查询（含删除）
 */
var QueryFunc = function () {
    //放大镜点击事件
    return {
        serachKey: function () {
            //树的重新加载
            loadTree();
        }
    }
}();
var nodeId = '';
//树的加载
var loadTree = function (callback) {
    $("#mixTree").load(basePath +'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule001/getSchemeName',
        {schemeName: $('#schemeName').val()}, function (data) {
            nodeId = '';
            if (typeof callback == 'function') {
                callback();
            }
        })
}

$(function () {
    // var nodeId = '';
    //获取查询参数
    var getSearchParams = function () {
        return {schemeId: nodeId};
    };
    //树加载
    loadTree();
    //鼠标放置显示操作列效果
    $("body").on('mouseover', ".mixtree-node", null, function () {
        $(".operate_div").hide();
        $(this).find(".operate_div").css({display: 'inline-block'});
    })
    //移开鼠标隐藏操作列效果
    $("body").on('mouseout', ".mixtree-node", null, function () {
        $(".operate_div").hide();
    })
    //点击申报方案事件
    $("body").on('click', ".mixtree-node", null, function () {
        nodeId = $(this).attr('nodeid');
        table.reload();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''})
        $(this).css({'background-color': '#D9ECFF'})
    })
    //申报附件方案编辑事件
    $("body").on('click', ".node_modify", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        Joyin.dialog(JoyinUtil.getLabel("FinSubmittedModule001.edit"),
            basePath + "invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/init",
            "95%", "50%", {pageModel:PAGE_MODEL_EDIT,schemeId:thisNodeId},
            function() {
                loadTree();
                $(".mixtree-node[nodeid='"+nodeId+"']").css({'background-color': '#D9ECFF'})
            });
    })
    //申报附件方案删除事件
    $("body").on('click', ".node_delete", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        var thisNodeName = $(this).attr('nodename');
        //获取该删除节点的参数
        var getData=function(){
            return {schemeId:thisNodeId,
                schemeName:thisNodeName}
        }
        var object = getData();
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule001/delete', object,
                    function(result){
                        loadTree();
                        table.clear();
                    });
            });
        });
    })
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        cols: [
            {field: 'seqno', visible: false}
            , {field: 'docTypeAbbr', title: 'FinSubmittedModule001.docType'}
            , {field: 'tplTypeAbbr', title: 'FinSubmittedModule001.tplType'}
            , {field: 'remark', title: 'FinSubmittedModule001.remark'}
            , {
                field: 'fileName', title: 'FinSubmittedModule001.scheme',
                render: function (data, type, row, meta) {
                    return '<a href="' + JoyinUtil.getRealPath('/framework/download/' + row.fileId) + '">'+data+'</a>';
                }
            }
            , {
                field: 'operation', width: 100, title: 'SysCommon.operation', type: 'action',
                buttons: [{
                    text: '',
                    clas: 'iconfont icon-upload',
                    title: '上传'
                }, 'delt']
            }],
    });
    /**
     * 绑定表格删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "delete"};
        object.schemeId = nodeId;
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/dcheck',
            object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/deleteScheme',
                        data, function (result) {
                            table.reload();
                        });
                });
            });
    });
    /**
     * 绑定表格上传事件
     */
    table.bind('click', '.icon-upload', function (row) {
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005/check',
            row, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("FinSubmittedModule005.upLoad"),
                    basePath + "invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005/init",
                    "60%", "50%", {businessId: row.seqno});
                
            });
    });
    /**
     * 绑定表格查看详情事件
     */
    table.bind('click', '.pro-link', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("FinSubmittedModule004"),
            basePath + "invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule004/init",
            "95%", "90%", {seqno: row.seqno});
    });
    //绑定附件模板添加按钮事件
    $('#FinSubmittedModule001_btn_add').click(function () {
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule001/check',
            {
                pageModel: PAGE_MODEL_NEW,
                schemeId: nodeId
            }, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("FinSubmittedModule003"),
                    basePath + "invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/init",
                    "80%", "70%", {
                        pageModel: PAGE_MODEL_NEW,
                        schemeId: nodeId
                    },
                    function () {
                        table.reload();
                    });
            });
    });
    //绑定申报附件方案新建按钮事件
    $('#FinSubmittedModule001_btn_creat').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("FinSubmittedModule002"),
            basePath + "invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule002/init",
            "40%", "40%", {pageModel: PAGE_MODEL_NEW}, function () {
                loadTree(function () {
                    $(".mixtree-node:first").trigger('click')
                });
            });
    });
    //“方案名称”上下箭头展示事件 '.icon-downarrow',  '.icon-uparrow'
    $("body").on('click','.mixtree-head', function () {
        if($(this).find('.icon-downarrow').length > 0) {
            $(this).find('.icon-downarrow').removeClass('icon-downarrow').addClass('icon-uparrow');
            $(".tree_node_div").toggle();
            return;
        }
        if($(this).find('.icon-uparrow').length > 0) {
            $(this).find('.icon-uparrow').removeClass('icon-uparrow').addClass('icon-downarrow');
            $(".tree_node_div").toggle();
            return;
        }

    })

    //绑定附件模板删除按钮事件
    $('#FinSubmittedModule001_btn_delete').click(function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            //没有选择删除数据，报错
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType: "delete"};
        object.schemeId = $('#code_hidden').val();
        Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/dcheck',
            object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003/delete',
                        object, function () {
                            table.reload();
                        });
                });
            });
    });
}());