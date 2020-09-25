/**
 * 自定义现金流
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId= null;
        if(portfolioIdTemp != null)
            portfolioId=portfolioIdTemp.join(",");
        return {
            busiNo: $('#busiNo').val(),//业务编号
            portfolioId:portfolioId,//组合代码
            startDate:$('#settleDate').startDate(),//发生日期
            endDate:$('#settleDate').endDate(),
            ps: $('#ps').val(),//收支方向
            finprodType2: $('#finprodType2').val(),//资产类型
            finprodId: $('#finprodMarketId_hidden').val(),//资产代码
            customCashType:$("#customCashType").val()//业务类型
            ,status: $("#status").val()
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
                    basePath + "invest/portfolio/ptlcashflow/PtlCashFlow002/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
            	busiNo:data.busiNo,finprodType2:data.finprodType2});//主键组合代码
        },
        /**
         * 定义表格名称
         */
        cols:[
        {field:'tradeStatusStr', width:150, title: 'PtlCashFlow001.status',sortField:'tradeStatus'}
            ,{field:'busiNo', width:150 ,title: 'PtlCashFlow001.busiNo'}//业务编号
            ,{field:'finprodType2',visible: false}//资产类型
            ,{field:'portfolioId', width:150 ,title: 'PtlCashFlow001.portfolioId'}//组合代码
            ,{field:'portfolioName', width:150 ,title: 'PtlCashFlow001.portfolioName'}//组合名称
            ,{field:'settleDate', width:150 ,title: 'PtlCashFlow001.settleDate',type:'date'}//业务发生日期
            ,{field:'psStr', width:150 ,title: 'PtlCashFlow001.ps',sortField:'ps'}//收支方向
            ,{field:'tradeAmt', width:150 ,title: 'PtlCashFlow001.tradeAmt'}//金额
            ,{field:'ccyStr',sortField:'ccy', width:150 ,title: 'PtlCashFlow001.ccy'}//币种
            ,{field:'finprodType2Str',sortField:'finprodType2',width:150 , title: 'PtlCashFlow001.finprodType2'}//资产类型
            ,{field:'finprodMarketId', width:150, title: 'PtlCashFlow001.finprodMarketId'}//资产代码
            ,{field:'finprodAbbr', width:150 ,title: 'PtlCashFlow001.finprodAbbr'}
            ,{field:'customCashTypeStr',sortField:'customCashType', width:150 ,title: 'PtlCashFlow001.customCashType'}//业务类型
            ,{field:'fTradeId', width:150 ,title: 'PtlCashFlow001.fTradeId'}//关联业务编号
            ,{field:'counterId', width:150 ,title: 'PtlCashFlow001.counterId'}//交易对手
        ]
    });
    
    /**
     * 绑定编辑事件
     *//*
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("PtlCashFlow001.edit"),
                basePath +"invest/portfolio/ptlcashflow/PtlCashFlow002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
        	busiNo:data.busiNo,finprodType2:data.finprodType2});//主键组合代码
        
    });
    */
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
        $('#PtlCashFlow001_btn_search').click(function() {
            table.load();//加载表格
            $('#PtlCashFlow001_btn_revoke').show();//删除按钮显示
        });
        //绑定到处按钮
        $('#PtlCashFlow001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#PtlCashFlow001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlCashFlow002"),
                    basePath+ "invest/portfolio/ptlcashflow/PtlCashFlow002/init",
                        "80%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定删除按钮
        $('#PtlCashFlow001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            /*var portfolioIdList = '';
            for(var i = 0; i<selArr.length;i++){
                portfolioIdList +=' ' + selArr.portfolioId;
            }*/
            /*Joyin.dialog(JoyinUtil.getLabel("PtlCashFlow001.edit"),
                    basePath +"invest/portfolio/ptlcashflow/PtlCashFlow002/init",
                    "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                    portfolioId:data.portfolioId});//主键组合代码
*/           
            if (selArr.length > 1) {//判断是否选择多条数据，如果是则报错BUSS_COM_E_0046
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var object = selArr[0];
            object.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                    WorkFlow.doRevokeList(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow001/revoke', object, 
                            "PtlCashFlow001_btn_revoke","PtlCashFlow002");
                });
            });
            /*var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlcashflow/PtlCashFlow001/check', object, function(result) {//数据验证
            	Joyin.dialog(JoyinUtil.getLabel("PtlCashFlow001.edit"),
                        basePath +"invest/portfolio/ptlcashflow/PtlCashFlow002/init",
                        "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
            		busiNo:object.selectedItem[0].busiNo,finprodType2:object.selectedItem[0].finprodType2});
            });*/
        });
    });
}());