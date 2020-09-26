$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        // 组合代码多选
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp){
            portfolioId = portfolioIdTemp.join(",");
        }
        // 货币对多选
        var curPairTemp = $('#curPair').val();
        var curPair = null;
        if(curPairTemp){
            curPair = curPairTemp.join(",");
        }
        
            
        return {
            entrustId:$('#entrustId').val(),
            portfolioId:portfolioId,
            excDealType:$('#excDealType').val(),
            curPair:curPair,
            ps:$('#ps').val(),
            validStatus:$('#validStatus').val(),
            tradeDateBegin:$('#tradeDate').startDate(),
            tradeDateEnd:$('#tradeDate').endDate()
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
        	//START modify by wj 20200421双击交易记录查看交易详情时，页面弹出报错      原因workflowid传字符undefined给后台查流程数据导致报错
        	var workflowId = '';
        	if(data.workflowId ==null || data.workflowId == undefined || data.workflowId ==='' ){
            	workflowId = '';
            }else{
            	workflowId = data.workflowId;
            }
        	//END modify by wj 20200421双击交易记录查看交易详情时，页面弹出报错      原因workflowid传字符undefined给后台查流程数据导致报错

            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/trade/spotex/TrdSpotexInvestTrade002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    validStatus:data.validStatus,
                    workflowId:workflowId,
                    portfolioId:data.portfolioId,
                    entrustId:data.entrustId,
                    finprodId:data.finprodId,
                    tradeId:data.tradeId});
        },
        cols:[
            {field:'validStatusName', title: 'TrdSpotexInvestTrade.validStatus',width:100,sortField:'validStatus'}
            ,{field:'validStatus', visible: false}
            ,{field:'tradeId', title: 'TrdSpotexInvestTrade.tradeId',width:170,sortField:'tradeId'}
            ,{field:'entrustId', title: 'TrdSpotexInvestTrade.entrustId',width:180,sortField:'tradeId'}
            ,{field:'portfolioId', title: 'TrdSpotexInvestTrade.portfolioId',width:100,sortField:'portfolioId',render: function (data, type, row, meta) {
                return "<a  class='pro-link' style='cursor: pointer;'>"+row.portfolioId+"</a>";
            }}
            ,{field:'portfolioName', title: 'TrdSpotexInvestTrade.portfolioName',width:140,sortField:'portfolioId'}
            ,{field:'portfolioId',visible:false}
            ,{field:'excDealTypeName', title: 'TrdSpotexInvestTrade.excDealType',width:140,sortField:'excDealType'}
            ,{field:'excDealType', visible:false}
            ,{field:'curPairName', title: 'TrdSpotexInvestTrade.curPair',width:140,sortField:'excDealType'}
            ,{field:'curPair', visible:false}
            ,{field:'psName', title: 'TrdSpotexInvestTrade.ps',width:100,sortField:'ps'}
            ,{field:'ps', visible:false}
            ,{field:'tradeDateText',  title: 'TrdSpotexInvestTrade.tradeDate',type:'date',width:100,sortField:'tradeDate'}
            ,{field:'settleSpeedName',  title: 'TrdSpotexInvestTrade.settleSpeed',width:100,sortField:'settleSpeed'}
            ,{field:'settleDateText',  title: 'TrdSpotexInvestTrade.settleDate',type:'date',width:100,sortField:'tradeDate'}
            ,{field:'unitCprice', title: 'TrdSpotexInvestTrade.unitCprice',width:100,sortField:'unitCprice'}
            ,{field:'dealCurName', title: 'TrdSpotexInvestTrade.dealCur',width:100,sortField:'dealCur'}
            ,{field:'prinAmt', title: 'TrdSpotexInvestTrade.prinAmt',type:'money',width:100,sortField:'prinAmt'}
            ,{field:'ccyName', title: 'TrdSpotexInvestTrade.ccy',width:100,sortField:'ccy'}
            ,{field:'tradeAmt', title: 'TrdSpotexInvestTrade.tradeAmt',type:'money',width:100,sortField:'tradeAmt'}
            ,{field:'usdAmt', title: 'TrdSpotexInvestTrade.usdAmt',type:'money',width:100,sortField:'usdAmt'}
            ,{field:'remark', title: 'TrdSpotexInvestTrade.remark',width:180,}
            ,{field:'workflowStatus', visible:false}
            ,{field:'workflowId', visible:false}
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
        $('#TrdSpotexInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdSpotexInvestTrade002"),
                    basePath+ "invest/trade/spotex/TrdSpotexInvestTrade002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
       
        //导出
        $('#TrdSpotexInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#TrdSpotexInvestTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#TrdSpotexInvestTrade001_btn_revoke').click(function(){
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
            }else if(selArr[0].validStatus !== VALID_STATUS_EFFECTIVE){
                Joyin.error(JoyinUtil.getLabel("TrdSpotexInvestTrade.revokeStatus"))
                return;
            }
            var object = {selectedItem: selArr, operateType:"revoke"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade001/checkRevoke', object, function(result) {//数据验证
                Joyin.prompt(JoyinUtil.getLabel('TrdSpotexInvestTrade.cancelText'), 0, function (result) {
                    for(var select in object.selectedItem){
                        object.selectedItem[select].cancelRemark = result;
                    }
                    WorkFlow.doDeleteList(basePath + 'invest/trade/spotex/TrdSpotexInvestTrade001/doRevoke', object, "TrdSpotexInvestTrade001_btn_revoke", "TrdSpotexInvestTrade002");//删除
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
    });
}());