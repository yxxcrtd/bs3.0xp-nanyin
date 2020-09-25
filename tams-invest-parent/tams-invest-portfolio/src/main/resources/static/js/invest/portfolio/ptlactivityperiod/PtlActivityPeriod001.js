$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var acctNo = $('#acctNo').val();
    	if(acctNo){
    		acctNo = acctNo.join(",");
    	}
        return {
            busiNo: $('#busiNo').val(),//业务编号
            acctNo:acctNo,//账户类型
            startMDate:$('#mdate').startDate(),//本次付息日
            endMDate:$('#mdate').endDate(),
            startsettleDate:$('#settleDate').startDate(),//到期日
            endsettleDate:$('#settleDate').endDate(),
            tradeStatus:$('#tradeStatus').val()
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
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                    "90%", "80%",
//                    "80%", "60%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
////                    portfolioId:data.portfolioId,
////                    busiNo:data.busiNo,
////                    vdate:data.vdate,
////                    mdate:data.mdate,
////                    busiAcctNo:data.busiAcctNo,
////                    ccy:data.ccy,
////                    tradeAmt:data.tradeAmt
//                data
//                    });//主键组合代码
                    data);
        },
        /**
         * 定义表格名称
         */
        cols:[
             {field:'tradeStatusStr', title: 'PtlActivityPeriod001.status',sortField:'tradeStatus'}//状态
            ,{field:'busiNo', title: 'PtlActivityPeriod001.busiNo',sortField:'busiNo'}//业务编号
            ,{field:'settleDate', title: 'PtlActivityPeriod001.settleDate',type:'date',sortField:'settleDate'}//支付日
            ,{field:'acctName', title: 'PtlActivityPeriod001.acctName',sortField:'acctName'}//账号名称
            ,{field:'busiAcctNo', title: 'PtlActivityPeriod001.acctNo',sortField:'busiAcctNo'}//账号
            ,{field:'vdate', title: 'PtlActivityPeriod001.vdate',type:'date',sortField:'vdate',visible:false}//开始日
            ,{field:'mdate', title: 'PtlActivityPeriod001.mdate',type:'date',sortField:'mdate'}//结束日
            ,{field:'tradeAmt', title: 'PtlActivityPeriod001.tradeAmt',type:'number',sortField:'tradeAmt'}//交易金额
            ,{field:'ccyStr', title: 'PtlActivityPeriod001.ccy',sortField:'ccy'}//币种
            ,{field:'tradeStatus',visible:false}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("PtlActivityPeriod001.edit"),
                basePath +"invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                portfolioId:data.portfolioId});//主键组合代码
        
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
        $('#PtlActivityPeriod001_btn_search').click(function() {
            table.load();//加载表格
            $('#PtlActivityPeriod001_btn_delete').show();//删除按钮显示
        });
        //绑定到处按钮
        $('#PtlActivityPeriod001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#PtlActivityPeriod001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlActivityPeriod002"),
                    basePath+ "invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                        "90%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定删除按钮
        $('#PtlActivityPeriod001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }else if(selArr.length > 1){
                Joyin.error(JoyinUtil.getLabel("PtlActivityPeriod001.onlyOne"));
                return;
            }
            var object = selArr[0];
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod001/check', object, function(result) {//数据验证
//                Joyin.prompt(JoyinUtil.getLabel('PtlActivityPeriod001.cancelText'), 0, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("PtlActivityPeriod001.cancel")), function() {
                    WorkFlow.doRevokeList(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod001/delete', object, "PtlActivityPeriod001_btn_delete", "PtlActivityPeriod002");//删除
                });
            });
        });
    });
}());
