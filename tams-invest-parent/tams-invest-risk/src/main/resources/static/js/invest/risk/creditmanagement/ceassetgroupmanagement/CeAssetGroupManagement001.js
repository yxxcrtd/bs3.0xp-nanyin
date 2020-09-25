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
//var nodeName = '';
//树的加载
var loadTree = function (callback) {
    $("#mixTree").load(basePath +'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement001/getAssetName',
        {assetPoolName: $('#assetPoolName').val()}, function (data) {
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
        return {seqno: nodeId};
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
//        nodeName = $(this).prop('innerText');
        table.reload();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''})
        $(this).css({'background-color': '#D9ECFF'})
    });
    //系列名称编辑事件
    $("body").on('click', ".node_modify", null, function (e) {
        var thisNodeId = $(this).attr('nodeid');
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/check',
                {pageModel : PAGE_MODEL_EDIT,seqno : thisNodeId, operateType : 'edit'}, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskValStructure002.edit"),
                basePath + "invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/init",
                "50%", "75%", {pageModel:PAGE_MODEL_EDIT,id:thisNodeId,operateType:'edit'},
                function() {
                    loadTree();
                    $(".mixtree-node[nodeid='"+nodeId+"']").css({'background-color': '#D9ECFF'})
                });
        });
    })
    //系列名称删除事件
    $("body").on('click', ".node_delete", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        //获取该删除节点的参数
        var getData=function(){
            return {seqno:thisNodeId}
        }
        var object = getData();
        object.operateType = "delete";
        //用于判断是否是系列名称删除
        object.flag = true;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement001/delete', object, 
                        "CeAssetGroupManagement001_btn_delete", "CeAssetGroupManagement002",function() {
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
            {field: 'finprodId', title:'CeAssetGroupManagement001.assetId'}
            ,{field: 'finprodName', title: 'CeAssetGroupManagement001.assetName'}
            ]
    });
    //绑定新增资产按钮事件
    $('#CeAssetGroupManagement001_btn_add').click(function () {
        var object = {pageModel: PAGE_MODEL_NEW, seqno: nodeId};
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("CeAssetGroupManagement003"),
                basePath + "invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/init",
                "60%", "80%",{
                    pageModel: PAGE_MODEL_NEW,
                    seqno: nodeId,
//                    assetPoolName: nodeName,
                });
        });
    });
    var exportExcel = function() {
      var object = getSearchParams();
        Joyin.exportExcel('export', object);
    };
    //绑定导出事件
    $('#CeAssetGroupManagement001_btn_export').click(function () {
        exportExcel();
    })
    //绑定系列名称新建按钮事件
    $('#CeAssetGroupManagement001_btn_creat').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("CeAssetGroupManagement002"),
            basePath + "invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/init",
            "50%", "75%")
    });
    
    $('#CeAssetGroupManagement001_btn_refresh').click(function () {
    	loadTree();
    	table.clear();
    });
    
    //绑定资产明细删除事件
    $('#CeAssetGroupManagement001_delete').click(function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
        }
        if ( !selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = getSearchParams();
        object.selectedItem = selArr;
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/delete', object, 
                        "CeAssetGroupManagement003_btn_delete", "CeAssetGroupManagement003",function() {
                    loadTree();
                    table.clear();
                });
            });
        });
    })
    //“列表信息”上下箭头展示事件 '.icon-downarrow',  '.icon-uparrow'
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
}());