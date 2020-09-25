/**
 * 授信权数管理
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        return {
        	assetTypeOne:$("#assetTypeOne").val(),
        	assetTypeTwo:$("#assetTypeTwo").val(),
        	startDate:$("#effDate").startDate(),
        	endDate:$("#effDate").endDate()
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
                return searchform();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("CreditRightsManagement002.detial"),
                    JoyinUtil.getRealPath("invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[ 
            {field:'assetTypeOneStr', title: 'CreditRightsManagement001.assetTypeOne', width:'18%',sortField:'asset_Type_One'}
            ,{field:'assetTypeTwoStr', title: 'CreditRightsManagement001.assetTypeTwo', width:'18%',sortField:'asset_Type_Two'}
            ,{field:'effDateStr', title: 'CreditRightsManagement001.effDate', width:'18%',sortField:'eff_Date'}
            ,{field:'riskWeight', title: 'CreditRightsManagement001.riskWeight', width:'18%',sortField:'risk_Weight'}
            ,{field:'remark',title: 'CreditRightsManagement001.remark'}
            ,{field:'seqno',visible:false}
            ,{field:'action', title: 'SysCommon.operation' , type:'action',buttons: ['edit','delt']}
        ],
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("CreditRightsManagement002.edit"),
                    basePath + "invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
            						seqno:data.seqno},
            						function(){table.reload();});
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0024",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/delete', object, 
                        "CreditRightsManagement001_btn_delete", "CreditRightsManagement002",function() {
                    table.reload();
                });
            	/*Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/delete', object, function(result){
            		table.reload();
            		
            	});*/
            });
        });	//画面提示message【BUSS_COM_C_0024：请确认是否要删除?】																	
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/export", searchform());
    };
    
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('CreditRightsManagement002'), basePath +'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement002/init', '98%', '95%');
    }

    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#CreditRightsManagement001_btn_search').click(function() {
            table.load();
        });
        
        /** 
         * 新建
         */
        $('#CreditRightsManagement001_btn_add').click(function(){
        	showDialog();
        	table.reload()
        });
        /**
         * 删除
         */
        $('#CreditRightsManagement001_btn_delete').click(function(){
        	if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
        	Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0024",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/delete', object, 
                            "CreditRightsManagement001_btn_delete", "CreditRightsManagement002",function() {
                        table.reload();
                    });
                	/*Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001/delete', object, function(result){
                		table.reload();
                		
                	});*/
                });
            });	//画面提示message【BUSS_COM_C_0024：请确认是否要删除?】	
        })
        
        /**
         * 导出按钮
         */
        $('#CreditRightsManagement001_btn_export').click(function() {
            exportExcel();
        });
        
    });
}());