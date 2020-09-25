$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            parameterType: $('#parameterType').val()
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({        
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/basicdata/parammaintenance/ParamMaintenance002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                                    projectCode:data.projectCode,
                                    status:data.status});
        },
        data:[
              
              {'status':'有效', 'projectCode':'00001', 'projectcodeName':'项目一号','projectValuec':'项目值一号',
                 'projectValuee':'projectvalueNo.1', 'sort':'顺序', 'remark':'无'},
              {'status':'新建', 'projectCode':'00002', 'projectcodeName':'项目二号','projectValuec':'项目值二号',
                 'projectValuee':'projectvalueNo.2', 'sort':'倒序', 'remark':'无'},             
              {'status':'待处理', 'projectCode':'00003', 'projectcodeName':'项目三号','projectValuec':'项目值三号',
                 'projectValuee':'projectvalueNo.3', 'sort':'顺序', 'remark':'无'}
          ],
        cols:[
            {field:'status', width:200, title: 'ParamMaintenance001.status'}
            ,{field:'projectCode', width:200, title: 'ParamMaintenance001.projectCode'}
            ,{field:'projectcodeName', width:200, title: 'ParamMaintenance001.projectcodeName'}
            ,{field:'projectValuec', width:200, title: 'ParamMaintenance001.projectValuec'}
            ,{field:'projectValuee', width:200, title: 'ParamMaintenance001.projectValuee'}
            ,{field:'sort', width:200, title: 'ParamMaintenance001.sort'}
            ,{field:'remark', width:200, title: 'ParamMaintenance001.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("ParamMaintenance001.edit"),
                basePath + "invest/basicdata/parammaintenance/ParamMaintenance002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                                parameterType:data.parameterType,
                                projectCode:data.projectCode,
                                projectcodeName:data.projectcodeName,
                                projectValuec:data.projectValuec,
                                projectValuee:data.projectValuee,
                                sort:data.sort,
                                remark:data.remark,
                                status:data.status});
        
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/parammaintenance/ParamMaintenance001/delete', object, "ParamMaintenance001_btn_delete", "ParamMaintenance001");
            });
//        });
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ParamMaintenance001_btn_search').click(function() {
            table.load();
            $('#ParamMaintenance001_btn_delete').show();
        });
        $('#ParamMaintenance001_btn_export').click(function() {
            exportExcel();
        });
        $('#ParamMaintenance001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("ParamMaintenance002"),
                        basePath + "invest/basicdata/parammaintenance/ParamMaintenance002/init",
                        "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ParamMaintenance001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/parammaintenance/ParamMaintenance001/delete', object, "ParamMaintenance001_btn_delete", "SysInstitution002");
                });
        });
    });
}());