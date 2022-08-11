$(function(){
	
	/**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
            prodId : $('#prodId').val()==null?null:$('#prodId').val().join(","),
            effDate: $('#effDate').val(),
            effEndDate: $('#effEndDate').val(),
            dataStatus: $('#dataStatus').val()
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
            Joyin.dialog(JoyinUtil.getLabel("合作机构信息查看"),
                    JoyinUtil.getRealPath("disclosure/information/config/prodpartner/ProdPartner002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
         cols:[
               {field:'prodPartenerId', visible: false}
              ,{field:'statusStr', width:'10%', sortField:'dataStatus', title: '数据状态'}
              ,{field:'prodId', width:'10%', sortField:'prodId', title: '产品主代码'}
              ,{field:'prodName', width:'10%', sortField:'prodName', title: '产品名称'}
              ,{field:'partnerName', width:'10%', sortField:'partnerName', title: '合作机构'}
              ,{field:'effDateStr',width:'10%' , sortField:"effDate", title: '生效日',type:'date'}
              ,{field:'effEndDateStr',width:'10%' , sortField:"effEndDate", title: '生效截止日期',type:'date'}
              ,{field: 'updateTime', type: 'datetime',width:'15%', sortField:"updateTime",title: "更新时间"}
              ,{field:'updateUser', width:'10%', sortField:'updateUser', title: '更新人'}
              ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'],
                 render: function (data, type, row, meta) {
                     if (row.dataStatus == 'D')
                         return '';
                     else
                         return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                             '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                 }}
          ]
    });
        
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("编辑"),
                    basePath + "disclosure/information/config/prodpartner/ProdPartner002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                prodPartenerId:data.prodPartenerId},function(){table.reload();})
    });
    
    
  /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        // Joyin.ajaxJson(basePath + 'disclosure/information/config/prodpartner/ProdPartner001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodpartner/ProdPartner001/delete', object, "ProdPartner001_btn_delete", "ProdPartner002",function(){
                    table.reload();
                });
            });
        // });
    });

    /*文件导出*/
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    	/*if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel('export', param);*/
    };

    $(function() {
        // 绑定按钮事件
        $('#ProdPartner001_btn_search').click(function() {
            table.load();
            $('#ProdPartner001_btn_delete').show();
        });

        /**
         *导出按钮
         */
        $('#ProdPartner001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 导入按钮
         */
        $('#ProdPartner001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'disclosure/information/config/prodpartner/ProdPartner001/checkImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath +"disclosure/information/config/prodpartner/ProdPartner001/importFile", {
                            fileName : fileName
                        },function(result){
                            if(!result || !($.isArray(result) && result.length > 0)) {
                                layui.layer.closeAll()
                                table.reload();
                            }
                        });
                    });
                });
            });
        });

        $('#ProdPartner001_btn_add').click(
                function() {
                    Joyin.dialog(JoyinUtil.getLabel("合作机构信息新建"), basePath
                            + "/disclosure/information/config/prodpartner/ProdPartner002/init",
                            "80%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
                });
        $('#ProdPartner001_btn_delete').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                if(selArr[i].dataStatus=='02'){
                    Joyin.error("请选择有效状态的数据进行删除");
                    return;
                }
            }
            var object = {
                selectedItem : selArr,
                operateType : "delete"
            };
            // Joyin.ajaxJson(basePath + 'disclosure/information/config/prodpartner/ProdPartner001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
                    function() {
                        WorkFlow.doDeleteList(basePath
                            + 'disclosure/information/config/prodpartner/ProdPartner001/delete'
                            ,object,"ProdPartner001_btn_delete","ProdPartner002",function(){table.reload();});
                    });
            // });
        });
    });
})
