(function() {
    /**
     * 获取有效查询参数
     */
    var getSearchParams = function() {
        return {
            listDate:listDate,
            listId:listId,
            spr:spr,
            createUser:createUser,
            updateUser:updateUser,
            listDetailId:$('#listDetailId').val()
        }
    };

    /**
     * 定义数据表格（生效名单）
     */
    var effTable=$("#dataTable").joyintable({
        ajax: {
            url: 'selectNameListLogDetail',
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
            ,{field:'listDetailId', width:200 , title:'OrgNameManagement003.listDetailId',sortField:'LIST_DETAIL_ID'}
            ,{field:'listDetailName', width:200 , title:'OrgNameManagement003.listDetailName',sortField:'LIST_DETAIL_NAME'}
            ,{field:'listDate', width:200 ,title:'OrgNameManagement001.effdate',type:'date',sortField:'LIST_DATE'}
            ,{field:'operateTypeStr', width:150 ,title:'OrgNameManagementDetail.operateType',sortField:'OPERATE_TYPE'}
            ,{field:'operateType', width:200 ,visible: false,title:'OrgNameManagementDetail.operateType',sortField:'OPERATE_TYPE'}
            ,{field:'operatePsStr', width:150 ,title:'OrgNameManagement003.operatePs',sortField:'OPERATE_PS'}
            ,{field:'operatePs', width:200 ,visible: false,title:'OrgNameManagement003.operatePs',sortField:'OPERATE_PS'}
            ,{field:'updateUser', width:150 , title:'OrgNameManagementDetail.createUser',sortField:'CREATE_USER'}
            ,{field:'spr', width:150 , title:'OrgNameManagementDetail.spr',sortField:'UPDATE_USER'}
        ]
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        //初始化查询
        $('#OrgNameManagementLogDetail_btn_search').click(function() {
            effTable.load();
        });
        //导出
        $('#OrgNameManagementLogDetail_btn_export').click(function() {
            exportExcel();
        });
    });
}());