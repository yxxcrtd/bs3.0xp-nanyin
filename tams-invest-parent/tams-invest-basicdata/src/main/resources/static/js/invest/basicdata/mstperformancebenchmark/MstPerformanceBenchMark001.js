/**
 * 复合基准管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	indexName: $('#indexName').val(),
        	indexType : $('#indexType').val() == null ? $('#indexType').val() : $('#indexType').val().join(',')
        };
//        return $.serializeToObject('#searchForm');
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
            //data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstPerformanceBenchMark002"),
                    JoyinUtil.getRealPath("invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/init"),
                    "95%", "80%", data,
                    function(){table.reload();});
        },
       
        cols:[
            {field:'indexTypeName',width:'50%', sortField:'INDEX_TYPE', title: 'MstPerformanceBenchMark001.indexType'}
            ,{field:'indexType',visible:false}
            ,{field:'indexName',width:'50%', sortField:'INDEX_NAME',  title: 'MstPerformanceBenchMark001.indexName'}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            ,{field:'indexId',visible: false}
        ]
    });
    
    var exportExcel = function(){
        var paramObj=getSearchParams();
       // Joyin.exportExcel(basePath+"invest/basicdata/performancebenchmark/performancebenchmark002/export", getSearchParams());
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data], operateType:"edit"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/editCheck',object, function(result) {
            console.log(result[0]);
            if(result[0]=="Y"){
                Joyin.confirm("业绩基准已被产品或资产使用,是否继续修改？",function () {
                    Joyin.dialog(JoyinUtil.getLabel("MstPerformanceBenchMark002.edit"),
                        basePath + "invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/init",
                        "95%", "90%", {
                            pageModel: PAGE_MODEL_EDIT,
                            indexId: data.indexId
                        }, function () {
                            table.reload();
                        })
                });
            }else {
                Joyin.dialog(JoyinUtil.getLabel("MstPerformanceBenchMark002.edit"),
                    basePath + "invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/init",
                    "95%", "90%", {
                        pageModel: PAGE_MODEL_EDIT,
                        indexId: data.indexId
                    }, function () {
                        table.reload();
                    })
            }
        });
    });
    //}); 
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark001/delete', object, "MstPerformanceBenchMark001_btn_delete", "MstPerformanceBenchMark002",function(){
                    table.reload();
                });
            });
        });
    });
   
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#MstPerformanceBenchMark001_btn_search').click(function() {
            table.load();
            $('#MstPerformanceBenchMark001_btn_delete').show();
        });
        $('#MstPerformanceBenchMark001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstPerformanceBenchMark001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstPerformanceBenchMark002.add"),
                        basePath + "invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/init",
                        "75%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#MstPerformanceBenchMark001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark001/delete', object, "MstPerformanceBenchMark001_btn_delete", "MstPerformanceBenchMark002");
                });
            });
        });
    });
}());