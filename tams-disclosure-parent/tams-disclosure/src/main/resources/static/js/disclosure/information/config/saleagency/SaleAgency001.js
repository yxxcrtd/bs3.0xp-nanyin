$(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            agencyId : $('#agencyId').val()==null?null:$('#agencyId').val().join(","),
            prodSeries : $('#prodSeries').val()==null?null:$('#prodSeries').val().join(",")
        };
    };

    /**
     * 表格数据查询
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("代销机构查看"),
                JoyinUtil.getRealPath("disclosure/information/config/saleagency/SaleAgency002/init"),
                "80%", "90%", data,
                function(){table.reload();});
        },
        cols:[
            {field:'sequenceNo', visible: false}
            ,{field:'agencyName', width:'10%', sortField:'AGENCY_NAME', title: '代销机构'}
            ,{field:'agreementVdateStr', width:'10%', sortField:'AGREEMENT_VDATE', title: '协议起始时间'}
            ,{field:'agreementMdateStr', width:'10%', sortField:'AGREEMENT_MDATE', title: '协议终止时间'}
            ,{field:'prodSeriesStr', width:'10%',sortable:false, title: '产品系列'}
            ,{field:'updateUser', width:'10%', sortField:'UPDATE_USER', title: '更新人'}
            ,{field: 'updateTimeStr', type: 'datetime',width:'15%', sortable:'UPDATE_TIME',title: "更新时间"}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });


    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("编辑"),
            basePath + "disclosure/information/config/saleagency/SaleAgency002/init",
            "80%", "90%", {pageModel:PAGE_MODEL_EDIT,
                sequenceNo:data.sequenceNo},function(){table.reload();})
    });


    /**
     *绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'disclosure/information/config/saleagency/SaleAgency001/deleteCheck', data, function(deleteResult) {
            if(deleteResult.joyinMessages.length>0){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/saleagency/SaleAgency001/delete', object, "SaleAgency001_btn_delete", "SaleAgency002",function(){
                    table.reload();
                });
            }else{
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/saleagency/SaleAgency001/delete', object, "SaleAgency001_btn_delete", "SaleAgency002",function(){
                        table.reload();
                    });
                });
            }
        });
    });

    /*文件导出*/
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    $(function() {
        // 绑定按钮事件
        $('#SaleAgency001_btn_search').click(function() {
            table.load();
            $('#SaleAgency001_btn_delete').show();
        });

        /**
         *导出按钮
         */
        $('#SaleAgency001_btn_export').click(function() {
            exportExcel();
        });

        $('#SaleAgency001_btn_add').click(
            function() {
                Joyin.dialog(JoyinUtil.getLabel("代销机构产品关联关系维护"), basePath
                    + "/disclosure/information/config/saleagency/SaleAgency002/init",
                    "80%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
            });

        /**
         * 多选删除按钮
         */
        $('#SaleAgency001_btn_delete').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请至少选择一条记录!");
                return;
            }
            var object = {
                selectedItem : selArr,
                operateType : "delete"
            };
            Joyin.ajaxJson(basePath + 'disclosure/information/config/saleagency/SaleAgency001/deleteCheck', object, function(deleteResult) {
                if(deleteResult.joyinMessages.length>0){
                    WorkFlow.doDeleteList(basePath
                        + 'disclosure/information/config/saleagency/SaleAgency001/delete'
                        ,object,"SaleAgency001_btn_delete","SaleAgency002",function(){table.reload();});
                }else{
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
                        function() {
                            WorkFlow.doDeleteList(basePath
                                + 'disclosure/information/config/saleagency/SaleAgency001/delete'
                                ,object,"SaleAgency001_btn_delete","SaleAgency002",function(){table.reload();});
                        });
                }
            });
        });
    });
})
