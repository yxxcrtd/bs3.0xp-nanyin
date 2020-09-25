(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	customerId: $('#customerId').val(),
        	vdate: $('#vdate').startDate(),//生效日开始
        	mdate: $('#vdate').endDate()//生效日结束
        }
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno});
        },
        
        cols:[
             {field:'seqno', width:150 ,visible: false}
            ,{field:'customerId', width:210 , title: 'CePreOccupancyManagement001.institutionId'}
            ,{field:'customerName', width:220 , title: 'CePreOccupancyManagement001.institutionName'}
            ,{field:'insitituteCode', width:180 , title: 'CeOrg001.insitituteCode'}
            ,{field:'creditAmt', width:180 ,title: 'CePreOccupancyManagement001.creditTotalAmt'}
            ,{field:'vdate', width:190 ,title: 'CePreOccupancyManagement001.vdate',type:'date'}           
            ,{field:'mdate', width:190 , title: 'CePreOccupancyManagement001.mdate',type:'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("CePreOccupancyManagement002.edit"),
                    basePath + "invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/init",
                    "95%", "90%",data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement001/delete', object, 
                        "CePreOccupancyManagement001_btn_delete", "CePreOccupancyManagement002",function() {
                    table.reload();
                });
            });
        });
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        Joyin.dialog(JoyinUtil.getLabel("CePreOccupancyManagement002"),
                JoyinUtil.getRealPath("invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/init"),
                "95%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#CePreOccupancyManagement001_btn_search').click(function() {
            table.load();
            $('#CePreOccupancyManagement001_btn_delete').show();
        });
        $('#CePreOccupancyManagement001_btn_export').click(function() {
            exportExcel();
        });
        $('#CePreOccupancyManagement001_btn_add').click(function() {
            openNew();
        });
    });
}());