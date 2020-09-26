$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            portfolioId: $('#portfolioId').val(),//组合代码
            profitType:$('#profitType').val(),//收益类型
            portfolioType:$('#portfolioType').val(),//组合类型
            startVDate:$('#vdate').startDate(),//成立日
            endVDate:$('#vdate').endDate(),
            startMDate:$('#mdate').startDate(),//结束日
            endMDate:$('#mdate').endDate()
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
                    basePath + "invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "80%", "60%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId});//主键组合代码
        },
        /**
         * 定义表格名称
         */
        cols:[
             {field:'portfolioId', title: 'PtlManagementInfo001.portfolioId'}//组合代码
            ,{field:'portfolioName', title: 'PtlManagementInfo001.portfolioName'}//组合名称
            ,{field:'portfolioTypeStr', title: 'PtlManagementInfo001.portfolioType',sortField:'portfolioType'}//组合类型
            ,{field:'profitTypeStr', title: 'PtlManagementInfo001.revenueType',sortField:'profitType'}//收益类型
            ,{field:'isGuaranteeStr', title: 'PtlManagementInfo001.isBenefits',sortField:'isGuarantee'}//是否有收益
            ,{field:'vdate', title: 'PtlManagementInfo001.vDate',type:'date'}//开始日
            ,{field:'mdate', title: 'PtlManagementInfo001.mDate',type:'date'}//结束日
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
            
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("MstIndexQuotationInfo001.edit"),
                basePath +"invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                portfolioId:data.portfolioId});//主键组合代码
        
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002",function(){
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
        $('#PtlManagementInfo001_btn_search').click(function() {
            table.load();//加载表格
            $('#PtlManagementInfo001_btn_delete').show();//删除按钮显示
        });
        //绑定到处按钮
        $('#PtlManagementInfo001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#PtlManagementInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstIndexQuotationInfo002"),
                    basePath+ "invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                        "80%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定用户分配按钮
        $('#PtlManagementInfo001_btn_allocation').click(function() {
        });
        //绑定删除按钮
        $('#PtlManagementInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0026"));
                return;
            }
            var portfolioIdList = '';
            for(var i = 0; i<selArr.length;i++){
                portfolioIdList +=' ' + selArr.portfolioId;
            }
            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
                Joyin.confirm(JoyinUtil.getMessage('COMMON_I_0030',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))+portfolioIdList), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/trade/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002");//删除
                });
            });
        });
    });
}());