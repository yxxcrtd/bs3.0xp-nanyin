$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            indexName: $('#indexName').val(),//指数
            effDate:$('#effDate').val(),//有效日
            relaeseDate:$('#relaeseDate').val()//发布日
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/init",
                    "80%", "60%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                                    marketId:data.marketId,//主键一，指数代码
                                    effDate:data.effDate});//主键二，有效日
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'relaeseDate',width:200, title: 'MstIndexQuotationInfo001.relaeseDate',type:'date'}//发布日
            ,{field:'indexName',width:200, title: 'MstIndexQuotationInfo001.indexName', sortField:'marketId'}//指数名称
            ,{field:'closePrice',width:200, title: 'MstIndexQuotationInfo001.closePrice',type:'money'}//收盘价
            ,{field:'openPrice',width:200, title: 'MstIndexQuotationInfo001.openPrice',type:'money'}//开盘价           
            ,{field:'maxPrice',width:200, title: 'MstIndexQuotationInfo001.maxPrice',type:'money'}//最高价
            ,{field:'minPrice',width:200, title: 'MstIndexQuotationInfo001.minPrice',type:'money'}//最低价
            ,{field:'pClosePrice',width:200, title: 'MstIndexQuotationInfo001.pClosePrice',type:'money'}//前收盘价
            ,{field:'effDate',width:200, title: 'MstIndexQuotationInfo001.effDate',type:'date'}//有效日
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//操作类型
            
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/check', data, function(result) {//数据验证
            Joyin.dialog(JoyinUtil.getLabel("MstIndexQuotationInfo001.edit"),
        		basePath +"invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                marketId:data.marketId,//主键一，指数代码
                                effDate:data.effDate});//主键二，有效日
        });
        
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo001/delete', object, "MstIndexQuotationInfo001_btn_delete", "MstIndexQuotationInfo002",function(){
                    table.reload();
                });//删除
            });
        });
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#MstIndexQuotationInfo001_btn_search').click(function() {
            table.load();//加载表格
            $('#MstIndexQuotationInfo001_btn_delete').show();//删除按钮显示
        });
        //绑定到处按钮
        $('#MstIndexQuotationInfo001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#MstIndexQuotationInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstIndexQuotationInfo002"),
            		basePath+ "invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo002/init",
                        "60%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定删除按钮
        $('#MstIndexQuotationInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo001/check', object, function(result) {//数据验证
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstindexquotationinfo/MstIndexQuotationInfo001/delete', object, "MstIndexQuotationInfo001_btn_delete", "MstIndexQuotationInfo002");//删除
                });
            });
        });
    });
}());