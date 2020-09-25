$(function() {
	
	if (isShowButtonChild == '02') {
		$("#PtlManagementInfo001_btn_add_child").hide();
	}
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        // return {
        //     portfolioId: $('#portfolioId').val(),//组合代码
        //     profitType:$('#profitType').val(),//收益类型
        //     portfolioType:$('#portfolioType').val(),//组合类型
        //     startVDate:$('#vdate').startDate(),//成立日
        //     endVDate:$('#vdate').endDate(),
        //     startMDate:$('#mdate').startDate(),//结束日
        //     endMDate:$('#mdate').endDate(),
        //     strategyName:$('#strategyName').val()
        //
        // }
        var object = $.serializeToObject('#searchForm');
        object.startVDate = $('#vdate').startDate();
        object.endVDate = $('#vdate').endDate();
        object.startMDate = $('#mdate').startDate();
        object.endMDate = $('#mdate').endDate();
        if(object.portfolioId){
            object.portfolioId = object.portfolioId.join(',');
        }
        return object;
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
            if (data.portfolioType!='03') {
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                        portfolioId:data.portfolioId});//主键组合代码
            }else {
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                        portfolioId:data.portfolioId});//主键组合代码
            }

        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'statusStr', width: 200, title: 'PtlAccountCurrentInterest001.status',sortable:false}//状态
            ,{field:'portfolioId', width: 200, title: 'PtlManagementInfo001.portfolioId'}//组合代码
            ,{field:'portfolioName', width: 200, title: 'PtlManagementInfo001.portfolioName'}//组合名称
            ,{field:'portfolioTypeStr', width: 200, title: 'PtlManagementInfo001.portfolioType',sortField:'portfolioType'}//组合类型
            ,{field:'profitTypeStr', width: 200, title: 'PtlManagementInfo001.revenueType',sortField:'profitType'}//收益类型
            ,{field:'strategyName', width: 200, title: 'PtlManagementInfo001.strategy',sortField:'strategyName'}//投资策略
            ,{field:'disPercent', width: 200, title: 'PtlManagementInfo004.inheritanceRatio',sortField:'disPercent'}//继承比例
            ,{field:'isGuaranteeStr', width: 200, title: 'PtlManagementInfo002.isGuarantee',sortField:'isGuarantee'}//是否有收益
            ,{field:'vdate', width: 200, title: 'PtlManagementInfo001.vDate',type:'date'}//开始日
            ,{field:'mdate', width: 200, title: 'PtlManagementInfo001.mDate',type:'date'}//结束日
            ,{field:'operation', width: 200, title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
            
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        if (data.portfolioType!='03'){
            //Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', data, function(result) {//数据验证
                Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.edit"),
                    basePath +"invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                        portfolioId:data.portfolioId});//主键组合代码
            //});
        } else {
            data.childPortfolioId=data.portfolioId;
            data.childPortfolioName=data.portfolioName;
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/check', data, function(result) {//数据验证
                Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo004.edit"),
                    basePath +"invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                        portfolioId:data.portfolioId});//主键组合代码
            });
        }

    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002");//删除
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
            Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                    basePath+ "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                        "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定创建子组合按钮
        $('#PtlManagementInfo001_btn_add_child').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0020"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0020"));
                return;
            }
            if (selArr[0].portfolioType != '01') {
                Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0020"));
                return;
            }
            var object = selArr[0];
            object.pageModel=PAGE_MODEL_NEW;
             Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/checkAddChild', object, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo004"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo004/init",
                    "98%", "90%", object);
             });
        });
        //绑定用户分配按钮
        $('#PtlManagementInfo001_btn_allocation').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo003"),
                    basePath+ "invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/init",
                        "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定删除按钮
        $('#PtlManagementInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var portfolioIdList = '';
            for(var i = 0; i<selArr.length;i++){
                portfolioIdList +=' ' + selArr[i].portfolioId;
            }
            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
                Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))+'['+portfolioIdList+']'), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002");//删除
                });
            });
        });
    });
}());