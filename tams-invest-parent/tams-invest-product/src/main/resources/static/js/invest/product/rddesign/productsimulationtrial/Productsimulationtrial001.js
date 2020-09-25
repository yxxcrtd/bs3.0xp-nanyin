(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var pageParam =  {
            prdDesignId: $('#prdDesignId').val()
            ,mockDate: $('#mockDate').val()
            ,dataStatus: $('#dataStatus').val()
        };
        if ($("#prdDesignId").val()) {
            pageParam.prdDesignId = $("#prdDesignId").val().join(",");
        } else {
            pageParam.prdDesignId = null;
        }
        return  pageParam;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick : function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ProductSimulationTrial002.reference"),
                            JoyinUtil.getRealPath("invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/init"),
                            "98%", "95%", data, function() {
                                table.reload();
                            });
        },
        cols:[
            {field:'dataStatusStr',sortField:'dataStatus', title: 'ProductSimulationTrial001.dataStatus', width:'10%'}
            ,{field:'mockDate', title: 'ProductSimulationTrial001.mockDate', width:'10%', sortField:'mockDate',type:'date'}
            ,{field:'generateTime', title: 'ProductSimulationTrial001.generateTime', width:'10%', sortField:'requirementId',type:'datetime'}
            ,{field:'prdDesignId', title: 'ProductSimulationTrial001.prdDesignId', width:'10%',sortField:'dataStatus'}
            ,{field:'prdScale', title: 'ProductSimulationTrial001.prdScale', width:'10%',sortField:'prdScale'}
            ,{field:'prdPeriod', title: 'ProductSimulationTrial001.prdPeriod', width:'10%',sortField:'prdPeriod'}
            ,{field:'rateMax', title: 'ProductSimulationTrial001.rateMax', width:'10%',sortField:'rateMax'}
            ,{field:'rateMix', title: 'ProductSimulationTrial001.rateMix', width:'10%',sortField:'rateMix'}
            ,{field:'requirementId', width:130, title: 'ProductSimulationTrial001.requirementId', type: 'action', buttons: ['showDetail'],
                render: function (data, type, row, meta) {
                    return "<a class='pro-link showDetail' style='cursor: pointer;'>" + row.requirementId + "</a>";
                }
            }
            ,{field:'seqNo', visible: false}
            ,{field:'workflowId', visible: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });

    /**
     * 绑定删除事件
     */
   table.bind('click', '.delt', function(data, index) {
        data.operateType = 'delete';
        if(data.seqNo){
            Joyin.ajaxJson(basePath + 'invest/product/rddesign/productsimulationtrial/ProductSimulationTrial001/check', data, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    data.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/productsimulationtrial/ProductSimulationTrial001/delete', data, "ProductSimulationTrial001_btn_delete", "ProductSimulationTrial002");
                });
            });
        }
    });


    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };

    /**
     * 绑定查看明细事件
     */
    table.bind('click', '.showDetail', function (data, index) {
        var requirementId=data.requirementId;
        Joyin.dialog(JoyinUtil.getLabel("Productelementdes002"),
            basePath + "invest/product/rddesign/productelementdes/productelementdes003/init",
            "90%", "90%", {requirementId:requirementId
            });   });

    /**
     * 查询要素变更后需重新点击查询
     */
    function itemChange(){
        flag = false;
    }

    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("ProductSimulationTrial002.new"),
            JoyinUtil.getRealPath("invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/init"),
            "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){
                table.reload();
            }
        );
    }


    $(function() {
        $('#ProductSimulationTrial001_btn_search').click(function() {
            table.load();
        });
        $('#ProductSimulationTrial001_btn_add').click(function() {
            openNew();
        });
        $('#ProductSimulationTrial001_btn_export').click(function() {
            exportExcel();
        });
     /*  $('#ColProductRequirement001_btn_add').click(function() {
            openNew();
        });*/
    });
}());