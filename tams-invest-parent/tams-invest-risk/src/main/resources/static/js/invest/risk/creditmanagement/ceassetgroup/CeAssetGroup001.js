(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	pflsh: $('#pflsh').val(),
        	vdate: $('#vdate').startDate(),//生效日开始
        	mdate: $('#vdate').endDate(),//生效日结束
            customerId: $('#customerId').val()
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
                    basePath + "invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno});
        },
        
        cols:[
              {field:'seqno', width:150 ,visible: false}
             ,{field:'pflsh', width:210 , title: 'CeAssetGroup001.pflsh'}
             ,{field:'customerId', width:210 , title: 'CeAssetGroup001.assetGroupId'}
             ,{field:'customerName', width:250 , title: 'CeAssetGroup001.assetGroupName'}
             ,{field:'creditAmt', width:210 ,title: 'CeAssetGroup001.creditTotalAmt'}
             ,{field:'vdate', width:180 ,title: 'CeAssetGroup001.vdate',type:'date'}           
             ,{field:'mdate',  title: 'CeAssetGroup001.mdate',type:'date'}
             ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
         ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.startWfType="modify";
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("CeAssetGroup002.edit"),
                    basePath + "invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/init",
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
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/ceassetgroup/CeAssetGroup001/delete', object, 
                        "CeAssetGroup001_btn_delete", "CeAssetGroup002",function() {
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
        Joyin.dialog(JoyinUtil.getLabel("CeAssetGroup002"),
                JoyinUtil.getRealPath("invest/risk/creditmanagement/ceassetgroup/CeAssetGroup002/init"),
                "95%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#CeAssetGroup001_btn_search').click(function() {
            table.load();
            $('#CeAssetGroup001_btn_delete').show();
        });
        $('#CeAssetGroup001_btn_export').click(function() {
            exportExcel();
        });
        $('#CeAssetGroup001_btn_add').click(function() {
            openNew();
        });
    });
}());