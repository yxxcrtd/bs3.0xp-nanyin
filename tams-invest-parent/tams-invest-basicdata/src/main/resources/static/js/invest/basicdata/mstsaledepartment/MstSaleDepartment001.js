/**
 * 营业部信息管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	brokerId: $('#brokerId').val() == null ? $('#brokerId').val() : $('#brokerId').val().join(','),
        	interType : $('#interType').val() == null ? $('#interType').val() : $('#interType').val().join(','),
        	saledepname : $('#saledepname').val()
        };
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
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    JoyinUtil.getRealPath("invest/basicdata/mstsaledepartment/MstSaleDepartment002/init"),
                    "95%", "85%", data,
                    function(){table.reload();});
        },
       
        cols:[
             {field:'saledepid',width:'110', sortField:'sale_depid', title: 'MstSaleDepartment001.saledepid'}
            ,{field:'saledepname',width:'150', sortField:'sale_depname',  title: 'MstSaleDepartment001.saledepname'}
            ,{field:'brokername',width:'100', sortField:'broker_id',  title: 'MstSaleDepartment001.brokerId'}
            ,{field:'ipaddr',width:'120', sortable:false,  title: 'MstSaleDepartment001.ipaddr'}
            ,{field:'portnum',width:'80', sortable:false,  title: 'MstSaleDepartment001.portnum'}
            ,{field:'contactaddr',width:'150', sortable:false,  title: 'MstSaleDepartment001.contactaddr'}
            ,{field:'contacttel',width:'120', sortable:false,  title: 'MstSaleDepartment001.contacttel'}
            ,{field:'faxno',width:'120', sortable:false,  title: 'MstSaleDepartment001.faxno'}
            ,{field:'ipaddr2',width:'120', sortable:false,  title: 'MstSaleDepartment001.ipaddr2'}
            ,{field:'portnum2',width:'80', sortable:false,  title: 'MstSaleDepartment001.portnum2'}
            ,{field:'declareunitName',width:'80', sortable:false,  title: 'MstSaleDepartment001.declareunitName'}
            ,{field:'isnetreturnName',width:'80', sortable:false,  title: 'MstSaleDepartment001.isnetreturnName'}
            ,{field:'handletypeName',width:'110', sortable:false,  title: 'MstSaleDepartment001.handletypeName'}
            ,{field:'intertypeName',width:'110', sortable:false,  title: 'MstSaleDepartment001.intertypeName'}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        var paramObj=getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = data;
        object.operateType = "edit";
                Joyin.dialog(JoyinUtil.getLabel("MstSaleDepartment001.edit"),
                    basePath + "invest/basicdata/mstsaledepartment/MstSaleDepartment002/init",
                    "95%", "85%", {
                        pageModel: PAGE_MODEL_EDIT,
                        saledepid: data.saledepid
                    }, function () {
                        table.reload();
                    })
        });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = data;
        object.operateType = "delete";
		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function() {
			Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/save', object, function(result) {
				table.reload();// 表格删除行
			});
		}, function() {
		})
    });
   
    /**
     * 画面加载完成
     */
    $(function() {
    	
        // 绑定按钮事件
        $('#MstSaleDepartment001_btn_search').click(function() {
            table.load();
        });
        $('#MstSaleDepartment001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstSaleDepartment001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstSaleDepartment002"),
                        basePath + "invest/basicdata/mstsaledepartment/MstSaleDepartment002/init",
                        "95%", "85%", {pageModel:PAGE_MODEL_NEW}, 
                        function() {
        					table.reload();
        				});
        });
        $('#MstSaleDepartment001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
    		Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function() {
    			Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/save', object, function(result) {
    				table.reload();// 刷新表格
    			});
    		}, function() {
    		})
        });
    });
}());