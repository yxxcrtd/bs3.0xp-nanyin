$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var psTemp = $('#ps').val();
        var ps = null;
        if(psTemp != null)
            ps = psTemp.join(",");
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp != null)
            portfolioId = portfolioIdTemp.join(",");
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/8/6 -start
        var param = $.serializeToObject('#searchForm');
        if(param.type9){
            param.type9 = param.type9.join(",");
        }
        if(param.type10){
            param.type10 = param.type10.join(",");
        }
        if(param.type11){
            param.type11 = param.type11.join(",");
        }
        if ($("#chlFinprodId").val()) {
        	param.chlFinprodId = $("#chlFinprodId").val().join(",");
        }
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/8/6 -end
        return {
        	/* type9~11 added by wangguiqi on 2019/8/1 -start */
        	type9: param.type9,
        	type10: param.type10,
        	type11: param.type11,
        	/* type~11 added by wangguiqi on 2019/8/1 -end */
            entrustId:$('#entrustId').val(),
            portfolioId:portfolioId,
            finprodType:$('#finprodType').val(),
            finprodId:$('#finprodId').val(),
            chlFinprodId:param.chlFinprodId,
            ps:ps,
            status:$('#status').val(),
            tradeDateBegin:$('#tradeDate').startDate(),
            tradeDateEnd:$('#tradeDate').endDate(),
            queryType:IS_QUERY_ASSETS_LOSS
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
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    entrustId:data.entrustId,
                    finprodId:data.finprodId,
                    tradeId:data.tradeId});
        },
        cols:[
            {field:'tradeStatusStr', width:150, title: 'TrdDepositInvestTrade001.status',sortField:'tradeStatus'}
            ,{field:'tradeId', width:150, sortField:'TRADEID',title: '业务编号'}
            ,{field:'entrustId', width:150, title: 'TrdDepositInvestTrade001.entrustId'}
            ,{field:'portfolioId', width:150, title: 'TrdDepositInvestTrade001.portfolioId',render: function (data, type, row, meta) {
                return "<a  class='pro-link' style='cursor: pointer;'>"+row.portfolioId+"</a>";
            }}
            ,{field:'portfolioName', width:150, title: 'TrdDepositInvestTrade001.portfolioName'}
            ,{field:'chlFinprodId', width:150, title: 'TrdDepositInvestTrade001.chlFinprodId'}
//            ,{field:'portfolioId',visible:false}
            ,{field:'finprodMarketId', width:150, title: 'TrdDepositInvestTrade001.finprodId',render: function (data, type, row, meta) {
                return "<a  class='pro-link-fin' style='cursor: pointer;'>"+row.finprodMarketId+"</a>";
            }}
            ,{field:'finprodName', width:150, title: 'TrdDepositInvestTrade001.finprodName'}
            // 新增资产二、三、四级分类 王贵奇 2019/8/2 开始
            ,{field:'type9Name', width:150, title: 'TrdFundInvestTrade001.type9',sortField:'type9'}
            ,{field:'type10Name', width:150, title: 'TrdFundInvestTrade001.type10',sortField:'type10'}
            ,{field:'type11Name', width:150, title: 'TrdFundInvestTrade001.type11',sortField:'type11',visible:IS_SHOW_TYPE == '01'?true:false}
            // 新增资产二、三、四级分类 王贵奇 2019/8/2 结束
            ,{field:'psStr', width:150, title: 'TrdDepositInvestTrade001.ps',sortField:'ps'}
            ,{field:'tradeDate', width:150,  title: 'TrdDepositInvestTrade001.tradeDate',type:'date'}
            ,{field:'tradeAmt', width:150, title: 'TrdDepositInvestTrade001.tradeAmt',type:'number',sortField:'tradeAmt'}
            ,{field:'ccyStr', width:150, title: 'TrdDepositInvestTrade001.ccy',sortField:'ccy'}
            ,{field:'remark', width:150, title: 'TrdDepositInvestTrade001.remark'}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getPageParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#TrdDepositInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdDepositInvestTrade002"),
                    basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        
        $('#TrdDepositInvestTrade001_btn_protfolio').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdDepositInvestTrade003"),
                    basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade003/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdDepositInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdDepositInvestTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#TrdDepositInvestTrade001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
         // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }else if(selArr[0].tradeStatus !== TRADE_STATUS){
                Joyin.error(JoyinUtil.getLabel("TrdPlanRateInvestTrade002.revokeStatus"))
                return;
            }


            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            object.finprodId = selArr[0].finprodId;
            object.tradeDate = selArr[0].tradeDate;
            object.portfolioId= selArr[0].portfolioId;

            Joyin.ajaxJson(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/check', object, function(result) {//数据验证
//                Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))), function(){
                var resdata = result.data;
                Joyin.prompt(JoyinUtil.getLabel('TrdDepositInvestTrade001.cancelText'), 0, function (result) {
                    Joyin.confirm(resdata, function() {
                    for(var select in object.selectedItem){
                        object.selectedItem[select].cancelRemark = result;
                    }
                    WorkFlow.doRevokeList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/delete', object, "TrdDepositInvestTrade001_btn_delete", "TrdDepositInvestTrade002",function(){
                        table.reload();//回调事件请求及回调函数
                    });
//                    WorkFlow.doDeleteList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade001/delete', object, "TrdDepositInvestTrade001_btn_revoke", "TrdDepositInvestTrade002");//删除
                    });
                });
            });
        });
        //组合点击查看详情
        table.bind('click','.pro-link', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    portfolioId:row.portfolioId,
                    portfolioName: row.portfolioName});
            
        });
        //资产代码点击查看明细
        table.bind('click','.pro-link-fin', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",
                    "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    finprodId: row.finprodId});
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
}());