/**
 * RepInterLog001 接口日志管理
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var infId = $('#infId').val();
        var sysCode = $('#sysCode').val();
        var seqNo = $('#seqNo').val();
        var doStatus = $('#doStatus').val();
        var starTranDate = $('#tranDate').startDate();
        var endTranDate = $('#tranDate').endDate();
        var requestData = $('#requestData').val();
        var responseData= $('#responseData').val();
        return {
        	infId : infId,
        	sysCode : sysCode,
        	seqNo : seqNo,
        	doStatus : doStatus,
        	starTranDate : starTranDate,
            endTranDate : endTranDate,
            requestData : requestData,
            responseData : responseData
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/riskredis/quota/interlog/RepInterLog002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
            	uuid: data.uuid});
        },
        cols:[
             {field:'uuid',visible :false}
            ,{field:'infIdAbbr',sortField:"infId",title:'RepInterLog001.infId',width:120}
            /*,{field:'sysCodeAbbr',sortField:"sysCode", title: 'RepInterLog001.sysCode',width:120}*/
            ,{field:'ipAddress', title: 'RepInterLog001.ipAddressPort',width:200,render:transIpAddressPort}
            ,{field:'seqNo', title: 'RepInterLog001.seqNo',width:200}
            ,{field:'rtnCode', title: 'RepInterLog001.rtnCode',width:80}
            ,{field:'rtnMsg', title: 'RepInterLog001.rtnMsg',width:200}
            ,{field:'doStatusAbbr',sortField:"doStatus", title: 'RepInterLog001.doStatus',width:100}
            ,{field:'tranDateRet', sortField:"tranDate",title: 'RepInterLog001.tranDate'}
            ,{field:'tranTimeRet', sortField:"tranTime",title: 'RepInterLog001.tranTime'}
            ]
    });
    
    function transIpAddressPort(data, type, row, meta){
    	if(data != null && row.port != null){
    		return data+":"+row.port;
    	}else if(data != null && row.port == null){
    		return data+":"+"";
    	}else if(data == null && row.port != null){
    		return ""+":"+row.port;
    	}else{
    		return ""+":"+"";
    	}
    	
    }
	
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var getParams=getSearchParams();
        Joyin.exportExcel('export', getParams);
    };

    /**
     * 查询
     */
    var demand = function() {
    	debugger;
    	var paramObj = getSearchParams();//获取参数
    	var blank = null;
    	if(paramObj.starTranDate == null || paramObj.starTranDate == ''){
    		Joyin.ajaxJson(basePath + 'invest/riskredis/quota/interlog/RepInterLog001/check', paramObj, function(result) {
            });
    	}else{
    		table.load();
    	}
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepInterLog001_btn_search').click(function() {
        	demand();
        });
        $('#RepInterLog001_btn_export').click(function() {
            exportExcel();
        });
    });
}();


