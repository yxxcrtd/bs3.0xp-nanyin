$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        var chlFinprodId = null;
        if(portfolioIdTemp != null)
            portfolioId = portfolioIdTemp.join(",");
        if ($("#chlFinprodId").val()) {
        	chlFinprodId = $("#chlFinprodId").val().join(",");
        }
        return {
            entrustId:$('#entrustId').val(),
            portfolioId:portfolioId,
            finprodType:$('#finprodType').val(),
           /* 增加2,3,4分类的查询参数、zhuyongkang 2019/7/30  start*/
            type9:$('#type9').val(),
            type10:$('#type10').val(),
            type11:$('#type11').val(),
          /* zhuyongkang 2019/7/30   end*/
            finprodId:$('#finprodId').val(),
            ps:$('#ps').val(),
            status:$('#status').val(),
            tradeDateBegin:$('#tradeDate').startDate(),
            tradeDateEnd:$('#tradeDate').endDate(),
            chlFinprodId:chlFinprodId,
            //是否查询减值清理交易 add by yzg 20201029 start
            queryType:IS_QUERY_ASSETS_LOSS
            //是否查询减值清理交易 add by yzg 20201029 end
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
                    basePath + "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId,
                    entrustId:data.entrustId,
                    finprodId:data.finprodId,
                    totleTradeId:data.totleTradeId});
        },
        cols:[
            {field:'tradeStatusStr', width:120, title: 'TrdPlanRateInvestTrade001.status',sortField:'tradeStatus'}
            ,{field:'totleTradeId', width:220, title: 'TrdPlanRateInvestTrade001.tradeId'}
            ,{field:'entrustId', width:180, title: 'TrdPlanRateInvestTrade001.entrustId'}
            ,{field:'portfolioId', width:150, title: 'TrdPlanRateInvestTrade001.portfolioId',render: function (data, type, row, meta) {
                return "<a  class='pro-link' style='cursor: pointer;'>"+row.portfolioId+"</a>";
            }}
            ,{field:'portfolioName', width:150, title: 'TrdPlanRateInvestTrade001.portfolioName'}
            ,{field:'chlFinprodId', width:150, title: 'TrdPlanRateInvestTrade001.chlFinprodId'}
            ,{field:'finprodId',visible:false}
            ,{field:'finprodMarketId', width:150, title: 'TrdPlanRateInvestTrade001.finprodId',render: function (data, type, row, meta) {
                return "<a  class='pro-link-fin' style='cursor: pointer;'>"+row.finprodMarketId+"</a>";
            }}
            ,{field:'finprodName', width:180, title: 'TrdPlanRateInvestTrade001.finprodName'}
           /* ,{field:'finprodTypeStr', width:150, title: 'TrdPlanRateInvestTrade001.finprodType',sortField:'finprodType'}*/
            /*增加二—四级表单头，隐藏资产类型、zhuyongkang 2019/7/30  start*/
            ,{field:'type9Str', width:150, title: 'TrdPlanRateInvestTrade001.type9',sortField:'type9'}
            ,{field:'type10Str', width:150, title: 'TrdPlanRateInvestTrade001.type10',sortField:'type10'}
            ,{field:'type11Str', width:150, title: 'TrdPlanRateInvestTrade001.type11',sortField:'type11',visible:IS_SHOW_TYPE == '01'?true:false}
            /*原因、zhuyongkang 2019/7/30   end*/
            ,{field:'psStr', width:120, title: 'TrdPlanRateInvestTrade001.ps',sortField:'ps'}
            ,{field:'tradeDate',  width:120, title: 'TrdPlanRateInvestTrade001.tradeDate',type:'date'}
            ,{field:'tradeAmt', width:120, title: 'TrdPlanRateInvestTrade001.tradeAmt',type:'number',sortField:'tradeAmt'}
            ,{field:'ccyStr', width:120, title: 'TrdPlanRateInvestTrade001.ccy',sortField:'ccy'}
            ,{field:'remark', width:180, title: 'TrdPlanRateInvestTrade001.remark',sortable:false}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getPageParams();
        // 修改导出错误 zhuyongkang
        paramObj.isShowType=IS_SHOW_TYPE;
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#TrdPlanRateInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdPlanRateInvestTrade002"),
                    basePath+ "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        
     // add by shengqiqiang 计划类利率型资产多组合交易新建 2019.12.27 10.46.35 start
        $('#TrdPlanRateInvestTrade003_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdPlanRateInvestTrade003"), basePath
                + "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade003/init", "99%", "99%", {
                pageModel : PAGE_MODEL_NEW
            }, function() {
                table.reload();
            });
        });
        // add by shengqiqiang 计划类利率型资产多组合交易新建 2019.12.27 10.46.43 end
       
        //导出
        $('#TrdPlanRateInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdPlanRateInvestTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#TrdPlanRateInvestTrade001_btn_revoke').click(function(){
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
            Joyin.ajaxJson(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade001/check', object, function(result) {//数据验证
//                Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))), function(){
                var resdata = result.data;
                Joyin.prompt(JoyinUtil.getLabel('TrdPlanRateInvestTrade001.cancelText'), 0, function (result) {
                    Joyin.confirm(resdata, function() {
                    for(var select in object.selectedItem){
                        object.selectedItem[select].cancelRemark = result;
                    }
                    WorkFlow.doRevokeList(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade001/delete', object, "TrdPlanRateInvestTrade001_btn_delete", "TrdPlanRateInvestTrade002",function(){
                        table.reload();//回调事件请求及回调函数
                    });
//                    WorkFlow.doDeleteList(basePath + 'invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade001/delete', object, "TrdPlanRateInvestTrade001_btn_delete", "TrdPlanRateInvestTrade002");//删除
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
            Joyin.dialog(JoyinUtil.getLabel("FinPlanProfitAsset002"),
                    basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
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