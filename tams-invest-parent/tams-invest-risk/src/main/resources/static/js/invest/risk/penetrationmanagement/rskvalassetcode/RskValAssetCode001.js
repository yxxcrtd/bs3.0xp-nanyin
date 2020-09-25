/**
 * RskValAssetCode001001 估值表资产科目映射
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
var nodeName = '';
//树的加载
var loadTree = function (callback) {
    $("#mixTree").load(basePath +'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/selectAll',
        {/*structName: $('#structName').val()*/}, function (data) {
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
    //获取查询参数
    var getSearchParams = function () {
        return {id: nodeId};
    };
    
    //鼠标放置显示操作列效果
    $("body").on('mouseover', ".mixtree-node", null, function () {
        $(".operate_div").hide();
        $(this).find(".operate_div").css({display: 'inline-block'});
    });
    
    //移开鼠标隐藏操作列效果
    $("body").on('mouseout', ".mixtree-node", null, function () {
        $(".operate_div").hide();
    });
    
    //点击映射事件
    $("body").on('click', ".mixtree-node", null, function () {
        nodeId = $(this).attr('nodeid');
        nodeName = $(this).find('.node_div').html()
        table.reload();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''});
        $(this).css({'background-color': '#D9ECFF'})
    });
    
    //映射名称编辑事件
    $("body").on('click', ".node_modify", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        Joyin.dialog(JoyinUtil.getLabel("RskValAssetCode002.edit"),
            basePath + "invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002/init",
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
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/check',object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/delete', object,
                    function(result){
                        loadTree(function () {
                            $(".mixtree-node:first").trigger('click')
                        });
                        table.clear();
                    });
            });
        })
    })
    
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPageDtl',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox:false,
        cols: [
              {field: 'id',visible: false}
            ,{field: 'srcCode', title:'RskValAssetCode004.srcCode',sortField:'SRC_CODE'}
            , {field: 'tgtCode', title: 'RskValAssetCode004.tgtCode',sortField:'TGT_CODE'}
            , {field: 'allowSumName', title: 'RskValAssetCode004.allowSum',sortField:'ALLOW_SUM'}
            , {field: 'memo', title: 'RskValAssetCode004.memo'}
            , {field: 'operation', width: 100, title: 'SysCommon.operation', type: 'action',
                buttons: ['delt']
            }]
    });
    /**
     * 绑定编辑事件
     */
 /*   table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("RskValStructure002.edit"),
                    basePath + "invest/risk/penetrationmanagement/rskvalassetcode/RskValStructure003/init",
                    "100%", "95%",data,function() {
                                        table.reload();
                                    });
    });*/
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        var parentObj = getSearchParams();parentObj.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/check',parentObj, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/deleteDtl', object,
                    function(result){
                        table.reload();//删除事件请求及回调函数
                    });
            });
        })
            /*});*/
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

    });
    
    /**
     * 新建映射
     */
    var showDialog = function(){
        Joyin.dialog(JoyinUtil.getLabel("RskValAssetCode002.add"),
                basePath + "invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode002/init",
                "40%", "35%", {pageModel: PAGE_MODEL_NEW}, function () {
                    loadTree(function () {
                        $(".mixtree-node:first").trigger('click')
                    });
                });
    }
    /**
     * 新建明细
     */
    function showMxDialog() {
        Joyin.dialog(JoyinUtil.getLabel('RskValAssetCode003.title'), '../RskValAssetCode003/init',
                '90%', '90%',{pageModel: PAGE_MODEL_NEW,id:nodeId,name:nodeName},function () {
                    loadTree(function () {
                        $(".mixtree-node:first").trigger('click')
                    });
                });
    } 
    
    $(function(){
        //树加载
        loadTree(function () {
            $(".mixtree-node:first").trigger('click')
        });
        $('#RskValAssetCode001_btn_creat').click(showDialog);
        $('#RskValAssetCode001_btn_add').click(showMxDialog);
    });
}());