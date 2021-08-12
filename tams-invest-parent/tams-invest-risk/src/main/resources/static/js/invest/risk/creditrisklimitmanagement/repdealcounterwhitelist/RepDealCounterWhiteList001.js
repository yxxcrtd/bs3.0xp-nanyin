/**
 * 质押回购交易对手白名单维护
 */


$(function() {
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
        var insitituteCode = $("#insitituteCode").val();
        var customerName = $("#customerName").val();
        var status = $('#status').val();
        var startUpdateTime = $('#updateTime').startDate();
        var endUpdateTime = $("#updateTime").endDate();
        return {
        	insitituteCode: insitituteCode,
        	customerName: customerName,
        	status: status,
        	startUpdateTime: startUpdateTime,
        	endUpdateTime: endUpdateTime
        }
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
        /**
         * 双击查看
         */
     /*   dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditrisklimitmanage/repdealcounterwhitelist/RepDealCounterWhiteList002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,seqNo:data.seqNo});
        },*/
        
        cols:[
             {field:'seqNo', visible: false}
            ,{field:'listTypeText',width:120 , sortField:"listType",  title: 'RepDealCounterWhiteList001.listTypeText'}
            ,{field:'insitituteCode', width:120 , sortField:"insitituteCode",title: 'RepDealCounterWhiteList001.insitituteCode'}
            ,{field:'customerName', width:180 , sortField:"customerName", title: 'RepDealCounterWhiteList001.customerName'}
            ,{field:'term', width:180 , sortField:"term",title: 'RepDealCounterWhiteList001.term'}
            ,{field:'amount', width:120 , sortField:"amount",title: 'RepDealCounterWhiteList001.amount',type: 'number', digit:4 }
            ,{field:'updateTime', width:120 ,sortField:"updateTime",title: 'RepDealCounterWhiteList001.updateTime',type:'datetime'}           
            ,{field:'statusText',sortField:"status", width:180 , title: 'RepDealCounterWhiteList001.statusText'}
            ,{field:'seqId',sortField:"seqId", title: 'RepDealCounterWhiteList001.seqId'}
        /*    ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']} */
        ]
    });    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepDealCounterWhiteList001_btn_search').click(function() {
            table.load();
        });
        // 导入按钮点击事件
        $('#RepDealCounterWhiteList001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
            	var options = {};
        		options.pageId = "RepDealCounterWhiteList001";
                Joyin.uploadFile(options,function(fileName) {
                	Joyin.ajax(basePath + 'invest/risk/creditrisklimitmanagement/repdealcounterwhitelist/RepDealCounterWhiteList001/importFile', {fileName: fileName});
      /*              Joyin.ajax(basePath + 'risk/creditrisklimitmanagement/repdealcounterwhitelist/RepDealCounterWhiteList001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        
                    }); */
                },function() {
                    table.reload();
                });
            });
        });
        $('#RepDealCounterWhiteList001_btn_export').click(function() {
            exportExcel();
        });
    });
}());