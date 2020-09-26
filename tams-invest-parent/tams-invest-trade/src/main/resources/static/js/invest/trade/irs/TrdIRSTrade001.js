/**
 * 利率互换交易
 */
(function(){
	/**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,irsType: $('#irsType').val()
            ,intType: $('#intType').val()
            ,validStatus: $('#validStatus').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
        };
        // 组合代码
        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        
        return pageParam;
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
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdIRSTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/irs/TrdIRSTrade002/init"),
                    "99%", "99%", data);
        },
        cols:[
            {field:'validStatusStr',sortField:'validStatus', width:100, title: 'TrdIRSTrade.statusValid'}
            ,{field:'tradeId', sortField:'tradeId',width:170, title: 'TrdIRSTrade.tradeId'}
            ,{field:'entrustId',sortField:'entrustId', width:180, title: 'TrdIRSTrade.entrustId'}
        	,{field:'portfolioId',sortField:'portfolioId', width:140, title: 'TrdIRSTrade.portfolioId',
                render : function(data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }
        	}
        	,{field:'portfolioName', sortField:'portfolioName',width:140, title: 'TrdIRSTrade.portfolioName'}
        	,{field:'tradeDateStr', sortField:'tradeDate',width:100, title: 'TrdIRSTrade.tradeDate'}
        	,{field:'irsTypeStr', sortField:'irsType',width:100, title: 'TrdIRSTrade.irsType'}
        	,{field:'vdateStr', sortField:'vdate',width:100, title: 'TrdIRSTrade.vdate'}
        	,{field:'mdateStr', sortField:'mdate',width:100, title: 'TrdIRSTrade.mdate'}
        	,{field:'term', sortField:'term',width:100, title: 'TrdIRSTrade.term'}
        	,{field:'ccyStr', sortField:'ccy',width:100, title: 'TrdIRSTrade.ccy'}
        	,{field:'prin', sortField:'prin',width:100, title: 'TrdIRSTrade.prin'}
        	,{field:'yield', sortField:'yield',width:100, title: 'TrdIRSTrade.yield'}
        	,{field:'fixPayCycleStr', sortField:'fixPayCycle',width:100, title: 'TrdIRSTrade.fixPayCycle'}
        	,{field:'firstPayDateRStr', sortField:'firstPayDateR',width:100, title: 'TrdIRSTrade.fixFirstPayDate'}
        	,{field:'intTypeStr', sortField:'intType',width:100, title: 'TrdIRSTrade.intType'}
        	,{field:'spreadRate', sortField:'spreadRate',width:100, title: 'TrdIRSTrade.spreadRate'}
        	,{field:'floatPayCycleStr', sortField:'floatPayCycle',width:100, title: 'TrdIRSTrade.floatPayCycle'}
        	,{field:'firstPayDateFStr', sortField:'firstPayDateF',width:100, title: 'TrdIRSTrade.floatFirstPayDate'}
        	,{field:'counterId', sortField:'counterId',width:100, title: 'TrdIRSTrade.counterId'}
        	,{field:'remark', sortField:'remark',width:100, title: 'TrdIRSTrade.remark'}
        ],
    });
  
    var exportExcel = function(){
        Joyin.exportExcel('export', getPageParams());
    };
  
    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    table.bind('click','.portfolioId-link', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "95%", "90%",
                { pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName });
    });
    
	
	$(function () {
        // 绑定按钮事件
        $('#TrdIRSTrade001_btn_search').click(function() {
            table.load();
            $('#TrdIRSTrade001_btn_revoke').show();
        });
        
        $('#TrdIRSTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdIRSTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdIRSTrade002"),
                    basePath + "invest/trade/irs/TrdIRSTrade002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#TrdIRSTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            
            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.finprodName = selArr[0].finprodName;
            checkObj.tradeDate = selArr[0].tradeDateStr;
            checkObj.operateType = "revoke";
            checkObj.ps = selArr[0].ps;
            Joyin.ajaxJson(basePath + 'invest/trade/irs/TrdIRSTrade001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doRevokeList(basePath + 'invest/trade/irs/TrdIRSTrade001/doRevoke', checkObj, "TrdIRSTrade001_btn_revoke", "TrdIRSTrade002");
                });
            });

        });
    });
}());