/**
 * RskValStructure001 估值表指标结构映射
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
    $("#mixTree").load(basePath +'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure001/getStructName',
        {structName: $('#structName').val()}, function (data) {
            nodeId = '';
          //update by yantianyuan 二级法人按钮改造 start
            if(purviewElements.indexOf('_edit')==-1){
            	$(".node_modify").hide();
            }
            if(purviewElements.indexOf('_delete')==-1){
            	$(".node_delete").hide();
            }
          //update by yantianyuan 二级法人按钮改造 end
            if (typeof callback == 'function') {
                callback();
            }
        })
}

$(function () {
    // var nodeId = '';
    //获取查询参数
    var getSearchParams = function () {
        return {structId: nodeId};
    };
    //树加载
    loadTree();
    //鼠标放置显示操作列效果
    $("body").on('mouseover', ".mixtree-node", null, function () {
        $(".operate_div").hide();
        $(this).find(".operate_div").css({display: 'inline-block'});
    });
    //移开鼠标隐藏操作列效果
    $("body").on('mouseout', ".mixtree-node", null, function () {
        $(".operate_div").hide();
    });
    //点击申报方案事件
    $("body").on('click', ".mixtree-node", null, function () {
        nodeId = $(this).attr('nodeid');
        table.reload();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''});
        $(this).css({'background-color': '#D9ECFF'})
    });
  //映射名称编辑事件
    $("body").on('click', ".node_modify", null, function (e) {
        var thisNodeId = $(this).attr('nodeid');
        Joyin.dialog(JoyinUtil.getLabel("RskValStructure002.edit"),
            basePath + "invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/init",
            "40%", "35%", {pageModel:PAGE_MODEL_EDIT,id:thisNodeId},
            function() {
                loadTree();
                $(".mixtree-node[nodeid='"+nodeId+"']").css({'background-color': '#D9ECFF'})
            });
    })
    //映射名称删除事件
    $("body").on('click', ".node_delete", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        //获取该删除节点的参数
        var getData=function(){
            return {id:thisNodeId}
        }
        var object = getData();
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/delete', object,
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
        checkbox:false,
        cols: [
              {field: 'id',visible: false}
            ,{field: 'srcColIndex',visible: false, title:'RskValStructure001.srcColIndex'}
            ,{field: 'srcColIndexLetter', title:'RskValStructure001.srcColIndex',sortField:'TO_NUMBER(SRC_COL_INDEX)'}/**修复bug： 理财资产管理系统BS2.1.3 BS-807。修改排序问题 ，这种方式修改只支持oracle和db2*/
            , {field: 'srcColDataTypeStr',sortField:"srcColDataType", title: 'RskValStructure001.srcColDataType'}
            , {field: 'tgtIndexStr', sortField:"tgtIndex",title: 'RskValStructure001.tgtIndex'}
            , {field: 'memo', title: 'RskValStructure001.memo'}
            , {field: 'operation', width: 100, title: 'SysCommon.operation', type: 'action',
                buttons: ['edit','delt']
            }]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/check',data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskValStructure003.edit"),
                    basePath + "invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/init",
                    "100%", "95%",data,function() {
                                        table.reload();
                                    });
            })
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {id: data.id,srcColIndex:data.srcColIndex, operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/delete',
                    object,function(result){
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        table.reload();
                    })
                })
            })
    });
  //新增映射事件
    $('#RskValStructure001_btn_creat').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("RskValStructure002"),
            basePath + "invest/risk/penetrationmanagement/rskvalstructure/RskValStructure002/init",
            "40%", "35%", {pageModel: PAGE_MODEL_NEW}, function () {
                loadTree(function () {
                    $(".mixtree-node:first").trigger('click')
                });
            });
    });
  //绑定映射明细添加按钮事件
    $('#RskValStructure001_btn_add').click(function () {
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure001/check',
            {
                pageModel: PAGE_MODEL_NEW,
                id: nodeId
            }, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("RskValStructure003"),
                    basePath + "invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/init",
                    "75%", "75%", {
                        pageModel: PAGE_MODEL_NEW,
                        id: nodeId
                    },
                    function () {
                        table.reload();
                    });
            });
    });
    //“映射名称”上下箭头展示事件 '.icon-downarrow',  '.icon-uparrow'
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
}());