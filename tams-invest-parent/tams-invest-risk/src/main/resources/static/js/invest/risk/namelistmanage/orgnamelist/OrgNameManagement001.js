(function() {
    /**
     * 获取有效查询参数
     */
    var getSearchParamsByEff = function() {
        var listId;
        if ($("#listId").val()) {
            listId = $("#listId").val().join(",");
        } else {
            listId = null;
        }
        return {
            isEffFlag:'01',
            listName:$('#listName').val(),
            rskSubjectType:RSK_SUBJECT_TYPE_01,
            listId:listId
        }
    };
    /**
     * 获取失效查询参数
     */
    var getSearchParamsByDelEff = function() {
        var listId;
        if ($("#listId").val()) {
            listId = $("#listId").val().join(",");
        } else {
            listId = null;
        }
        return {
            isEffFlag:'02',
            listName:$('#listName').val(),
            rskSubjectType:RSK_SUBJECT_TYPE_01,
            listId:listId
        }
    };
    /**
     * 定义数据表格（生效名单）
     */
    var effTable=$("#effectTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParamsByEff();
            }
        },
        /**
         * 定义表格双击事件
         */
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("OrgNameManagementDetail"),
                basePath + "invest/risk/namelistmanage/orgnamelist/OrgNameManagementDetail/init",
                "98%", "98%", {/*pageModel:PAGE_MODEL_REFERENCE,*/
                    workflowId:data.workflowId?data.workflowId:'',  //工作流Id
                    workflowStatus:data.workflowStatus,//流程状态
                    listId:data.listId//名单代码
                });
        },
        checkbox: false,
        searchOnInit: false,
        cols:[
            {field:'workflowId', width:190 , visible: false,title:'OrgNameManagement001.workflowId'}
            ,{field:'workflowStatus', width:190 , visible: false,title:'OrgNameManagement001.workflowStatus'}
            ,{field:'listId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
           /* ,{field:'listName', width:400 , title:'OrgNameManagement001.listName',sortField:'LIST_NAME'}*/
            ,{field:'listName', title : 'OrgNameManagement001.listName', width:400,sortField:'LIST_NAME',
                render: function (data, type, row, meta) {
                    var obj = {};
                    return  '<a href="javascript:void(0);" onclick="seeAllInfo('+row.workflowId+','+row.workflowStatus+','+row.listId+');" >'+row.listName+'</a>';
                }
            }
            ,{field:'listTypeStr', width:400 ,title:'AssetNameManagement001.listType',type:'date',sortField:'LIST_TYPE'}
            ,{field:'effdate', width:240 ,title:'OrgNameManagement001.effdate',type:'date',sortField:'EFFDATE'}
            /*,{field:'operation', width:300 , title: 'SysCommon.operation',sortable:false,
                render:function (data, type, row, meta) {
                    return '<a class="btn layui-btn  manual" title="手工录入" style="color:white">手工录入</a><a class="btn layui-btn batch" title="批量录入" style="color:white">批量录入</a>';
                }
            }*/
            ,{field:'operation', type:'action',width:240 ,  title: 'SysCommon.operation', sortable:false,
                render:function (data, type, row, meta) {
                var buttons = '';
                if(purviewElements.indexOf('_manual')!=-1){
                       buttons += '<a class="btn layui-btn  manual" title="手工录入" style="color:white">手工录入</a>';
                 }
                 if(purviewElements.indexOf('_batch')!=-1){
                       buttons += '<a class="btn layui-btn batch" title="批量录入" style="color:white">批量录入</a>';
                  }

                return buttons;
        }
    }
            ]
    });

    /**
     * 手工录入弹出页面
     */
    effTable.bind('click', '.manual', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",listId:data.listId,listDetailType:RSK_SUBJECT_TYPE_01};//请求参数
        Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel('OrgNameManagement003'), '../OrgNameManagement003/init', '98%', '98%',
                {
                    listId:data.listId,
                    listName:data.listName,
                    operateStyle:RSK_INPUT_TYPE_MANUAL,
                    listDetailType:RSK_SUBJECT_TYPE_01
                },function(){
                    effTable.reload();//回调函数，表格刷新
                    // uneffTable.reload();//回调函数，表格刷新
                });
        });
    });
    /**
     * 批量录入弹出页面
     */
    effTable.bind('click', '.batch', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",listId:data.listId,listDetailType:RSK_SUBJECT_TYPE_01};//请求参数
        Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel('OrgNameManagement004.batch'), '../OrgNameManagement004/init', '98%', '98%',
                {
                    listId:data.listId,
                    listName:data.listName,
                    operateStyle:RSK_INPUT_TYPE_BATCH,
                    listDetailType:RSK_SUBJECT_TYPE_01
                });
        },function(){
            effTable.reload();//回调函数，表格刷新
            // uneffTable.reload();//回调函数，表格刷新
        });
    });

    /**
     * 删除
     */
    effTable.bind('click', '.del', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",listId:data.listId};//请求参数
        Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement001/delete', object,"OrgNameManagement001_btn_delete", "OrgNameManagement003", function(){
                    effTable.reload();//回调函数，表格刷新
                    // uneffTable.reload();//回调函数，表格刷新
                });
            });
        });
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParamsByOrg();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 定义数据表格（失效）
     */
    // var uneffTable=$("#uneffectTable").joyintable({
    //     ajax: {
    //         url: 'findPage',
    //         type: 'POST',
    //         data: function() {
    //             return getSearchParamsByDelEff();
    //         }
    //     },
    //     checkbox: false,
    //     searchOnInit: false,
    //     cols:[
    //         {field:'listId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
    //         ,{field:'listName', width:450 , title:'OrgNameManagement001.listName',sortField:'LIST_NAME'}
    //         ,{field:'effdate', width:400 ,title:'OrgNameManagement001.effdate',type:'date',sortField:'EFFDATE'}
    //         ,{field:'deldate', width:400 ,title:'OrgNameManagement001.deldate',type:'date',sortField:'DELDATE'}
    //     ]
    // });

    /**
     * 绑定导出事件
     */
    var exportAssetExcel = function() {
        var paramObj = getSearchParamsByAsset();
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        //初始化查询
        $('#OrgNameManagement001_btn_search').click(function() {
            effTable.load();
            // uneffTable.load();
        });
        //新建
        /*$('#OrgNameManagement001_btn_add').click(showDialog);*/
    });
}());

/**
 * 超链接查看方法
 */
var seeAllInfo = function(workflowId,workflowStatus,listId){
    Joyin.dialog(JoyinUtil.getLabel("OrgNameManagementDetail"),
        basePath + "invest/risk/namelistmanage/orgnamelist/OrgNameManagementDetail/init",
        "98%", "98%", {/*pageModel:PAGE_MODEL_REFERENCE,*/
            workflowId:workflowId?workflowId:'',  //工作流Id
            workflowStatus:workflowStatus?workflowStatus:'',//流程状态
            listId:listId?listId:''//名单代码
        });
}