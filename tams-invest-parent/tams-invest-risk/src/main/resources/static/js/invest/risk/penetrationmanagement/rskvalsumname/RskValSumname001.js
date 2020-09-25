/**
 * RskValSumname001 估值表组合汇总科目映射
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
    $("#mixTree").load(basePath +'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname001/getSumName',
        {name: $('#name').val()}, function (data) {
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
        return {sumId: nodeId};
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
    	$("div#trimChars").html("");
    	$("div#trimChars").html($(this).attr('trimChars'));
    	//$("div#fileshow").html(result.rval);
        nodeId = $(this).attr('nodeid');
        table.reload();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''});
        $(this).css({'background-color': '#D9ECFF'})
    });
  //映射名称编辑事件
    $("body").on('click', ".node_modify", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        Joyin.dialog(JoyinUtil.getLabel("RskValStructure002.edit"),
            basePath + "invest/risk/penetrationmanagement/rskvalsumname/RskValSumname002/init",
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
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname002/delete', object,
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
            ,{field: 'sumName', title:'RskValSumname001.sumName'}
            , {field: 'sumCodeStr',sortField:"sumCode", title: 'RskValSumname001.sumCode'}
            , {field: 'nextIndexTypeStr', sortField:"nextIndexType",title: 'RskValSumname001.nextIndexType'}
            , {field: 'operation', width: 100, title: 'SysCommon.operation', type: 'action',
                buttons: ['edit','delt']
            }]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskValSumname003.edit"),
                    basePath + "invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/init",
                    "100%", "95%",data,function() {
                                        table.reload();
                                    });
            })
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {id: data.id,sumCode:data.sumCode, operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/delete',
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
    $('#RskValSumname001_btn_creat').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("RskValSumname002"),
            basePath + "invest/risk/penetrationmanagement/rskvalsumname/RskValSumname002/init",
            "40%", "35%", {pageModel: PAGE_MODEL_NEW}, function () {
                loadTree(function () {
                    $(".mixtree-node:first").trigger('click')
                });
            });
    });
  //绑定映射明细添加按钮事件
    $('#RskValSumname001_btn_add').click(function () {
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname001/check',
            {
                pageModel: PAGE_MODEL_NEW,
                id: nodeId
            }, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("RskValSumname003"),
                    basePath + "invest/risk/penetrationmanagement/rskvalsumname/RskValSumname003/init",
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