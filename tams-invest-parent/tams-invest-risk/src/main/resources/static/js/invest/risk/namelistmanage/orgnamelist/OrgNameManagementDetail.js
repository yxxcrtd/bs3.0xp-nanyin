(function() {
    /**
     * 获取有效查询参数
     */
    var getSearchParams = function() {
        return {
            workflowId:workflowId,
            listId:listId,
            workflowStatus:workflowStatus
        }
    };

    /**
     * 定义数据表格（生效名单）
     */
    var effTable=$("#effectTable").joyintable({
        ajax: {
            url: 'selectAssetListDetail',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        loadonce:true,
        checkbox: false,
        searchOnInit: true,
        cols:[
            {field:'listId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
            ,{field:'workflowId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
            ,{field:'listDateStr', width:190 , visible: false,title:'OrgNameManagement001.listId'}
            ,{field:'listDetailId', width:200 , title:'OrgNameManagement003.listDetailId',sortField:'LIST_DETAIL_ID'}
            ,{field:'listDetailName', width:200 , title:'OrgNameManagement003.listDetailName',sortField:'LIST_DETAIL_NAME'}
            ,{field:'listDate', width:200 ,title:'OrgNameManagement001.effdate',type:'date',sortField:'LIST_DATE'}
            ,{field:'operateTypeStr', width:200 ,title:'OrgNameManagementDetail.operateType',sortField:'OPERATE_TYPE'}
            ,{field:'operateType', width:200 ,visible: false,title:'OrgNameManagementDetail.operateType',sortField:'OPERATE_TYPE'}
            ,{field:'updateUser', width:200 , title:'OrgNameManagementDetail.createUser',sortField:'CREATE_USER'}
            ,{field:'spr', width:200 , title:'OrgNameManagementDetail.spr',sortField:'UPDATE_USER'}
        ]
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        paramObj.type = 'record';
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 定义数据表格（失效）
     */
    var uneffTable=$("#uneffectTable").joyintable({
        ajax: {
            url: 'selectAssetListDetailLog',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 定义表格双击事件
         */
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("OrgNameManagementLogDetail"),
                basePath + "invest/risk/namelistmanage/orgnamelist/OrgNameManagementLogDetail/init",
                "98%", "98%", {/*pageModel:PAGE_MODEL_REFERENCE,*/
                    updateUser:data.updateUser,  //工作流Id
                    spr:data.spr,//流程状态
                    listId:data.listId,//名单代码
                    listDate:data.listDateStr
                });
        },
        loadonce:true,
        checkbox: false,
        searchOnInit: true,
        cols:[
            {field:'listId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
            ,{field:'workflowId', width:190 , visible: false,title:'OrgNameManagement001.listId'}
            ,{field:'listDate', width:250 ,title:'OrgNameManagement001.effdate',type:'date',sortField:'LIST_DATE'}
            ,{field:'updateUser', width:250 , title:'OrgNameManagementDetail.createUser',sortField:'CREATE_USER'}
            ,{field:'spr', width:250 , title:'OrgNameManagementDetail.spr',sortField:'UPDATE_USER'}
            , {
                field: 'workflowId',
                width: 250,
                title: 'OrgNameManagementDetail.wflStatus',
                sortable: false,
                render: function (data, type, row, meta) {
                    if (data != '') {
                        return "生效流程中";
                    } else {
                        return '生效'
                    }
                }
            }

        ]
    });

    /**
     * 绑定导出事件
     */
    var exportAssetExcel = function() {
        var paramObj = getSearchParams();
        paramObj.type = 'detail';
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        //初始化查询
        $('#OrgNameManagement001_btn_search').click(function() {
            effTable.load();
            uneffTable.load();
        });
        //导出
        $('#OrgNameManagementDetail_btn_export').click(function() {
            exportAssetExcel();
        });
      //导出
        $('#OrgNameManagementDetail2_btn_export').click(function() {
            exportExcel();
        });
    });
}());