/**
 * 自然人信息管理001
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        return $.serializeToObject('#searchForm');
    };

    /**
     * 新建
     */
    function showDialog() {
        var data={"operateType":"add"};
        Joyin.dialog(JoyinUtil.getLabel('MstNaturalPerson002'),basePath+'invest/basicdata/mstnaturalperson/MstNaturalPerson002/init', '98%', '95%',data,function(){table.reload();});
    }
    
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
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstNaturalPerson002"),
                    JoyinUtil.getRealPath("invest/basicdata/mstnaturalperson/MstNaturalPerson002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[ 
            {field:'customerName', title: 'MstNaturalPerson001.customerName', width:'10%',sortField:'customerName'}
            ,{field:'sexother', title: 'MstNaturalPerson001.sex', width:'10%',sortField:'sex'}
            ,{field:'personId', title: 'MstNaturalPerson001.personId', width:'10%',sortField:'personId'}
            ,{field:'phone', title: 'MstNaturalPerson001.phone', width:'10%',sortField:'phone'}
            ,{field:'mPhone', title: 'MstNaturalPerson001.mPhone', width:'10%',sortField:'mPhone'}
            ,{field:'mail', title: 'MstNaturalPerson001.mail', width:'10%',sortField:'mail'}
            ,{field:'position', title: 'MstNaturalPerson001.position', width:'10%',sortField:'position'}
            ,{field:'address', title: 'MstNaturalPerson001.address', width:'10%',sortField:'address'}
            ,{field:'companyName', title: 'MstNaturalPerson001.companyName', width:'10%',sortField:'companyName'}
            ,{field:'property', title: 'MstNaturalPerson001.property', width:'10%',sortField:'property'}
            ,{field:'customerId', visible: false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:'10%'}
        ],
    });
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/basicdata/mstnaturalperson/MstNaturalPerson001/export", searchform());
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("MstNaturalPerson002"),
                JoyinUtil.getRealPath("invest/basicdata/mstnaturalperson/MstNaturalPerson002/init"),
                "98%", "95%", data,
           function(){table.reload();});
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath +'invest/basicdata/mstnaturalperson/MstNaturalPerson001/delete', object, function(result){
                    table.reload();
                });
            });
        });
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#MstNaturalPerson001_btn_search').click(function() {
            table.load();
            $('#MstNaturalPerson001_btn_delete').show();
        });
        /**
         * 导出按钮
         */
        $('#MstNaturalPerson001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建按钮
         */
        $('#MstNaturalPerson001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 批量删除按钮
         */
        $('#MstNaturalPerson001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstnaturalperson/MstNaturalPerson001/delete', object,"MstNaturalPerson001_btn_delete", "MstNaturalPerson002", function(result) {
                        table.reload();
                    });
                });
            });
        });
    });
}());