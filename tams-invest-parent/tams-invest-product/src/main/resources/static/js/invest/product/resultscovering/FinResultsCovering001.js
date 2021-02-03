/**
 * 业绩回补查询
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	var param = $.serializeToObject('#searchForm',true);
    	if(param.coveringDate){
    		param.coveringDateStart=param.coveringDate.slice(0,10);
    		param.coveringDateEnd=param.coveringDate.slice(13);
    	}else{
    		param.coveringDateStart = null;//$('#FinResultsCovering001.coveringDate').startDate();
        	param.coveringDateEnd = null;//$('#FinResultsCovering001.coveringDate').endDate();
    	}
    	if(param.prodId){
    		param.prodId = param.prodId.join(",");
    	}
    	if(param.layFinprodId) {
    	    param.layFinprodId = param.layFinprodId.join(",");
    	}
        return param;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinResultsCovering002.reference"),
                    JoyinUtil.getRealPath("invest/product/resultscovering/FinResultsCovering002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        cols:[
             {field:'tradeStatusStr', sortField: 'tradeStatus', width:120, title: 'FinResultsCovering001.tradeStatus'}
            ,{field:'prodId', sortField: 'prodId', width:120, title: 'FinResultsCovering001.prodId'}
            ,{field:'prodAbbr', sortField: 'prodAbbr', width:200, title: 'FinResultsCovering001.prodAbbr'}
            ,{field:'prodStyle', sortField: 'prodStyle', width:200, title: 'FinResultsCovering001.prodStyle'}
            ,{field:'coveringDate', sortField: 'coveringDate',  width:120, title: 'FinResultsCovering001.coveringDate', type: 'date'}
            ,{field:'coveringWayStr',sortField: 'coveringWay', width:200, title: 'FinResultsCovering001.coveringWay'}
            ,{field:'theoryAmount', sortField: 'theoryAmount', width:140, title: 'FinResultsCovering001.theoryAmount', type: 'money'}
            ,{field:'actualAmount', sortField: 'actualAmount', width:140, title: 'FinResultsCovering001.actualAmount', type: 'money'}
            ,{field:'coveringFormStr', sortField: 'coveringForm', width:200, title: 'FinResultsCovering001.coveringForm'}
            ,{field:'covCycleStartDate', sortField: 'covCycleStartDate', width:200, title: 'FinResultsCovering001.covCycleStartDate', type: 'date',visible:false}
            ,{field:'covCycleEndDate', sortField: 'covCycleEndDate', width:200, title: 'FinResultsCovering001.covCycleEndDate', type: 'date',visible:false}
            ,{field:'tradeId', sortField: 'tradeId', width:200, title: 'FinResultsCovering002.tradeId',visible:false}
            ,{field:'ccyStr', sortField: 'ccy', width:200, title: 'FinResultsCovering001.ccy',visible:false}
            ,{field:'layFinprodId',visible:false}
            ,{field:'isLay',visible:false}
            /*, {field: 'operation', width: 100, title: '操作', 
    			type: 'action', covCycleEndDate
                    if (row.ps=="T04"||row.ps=="T01"){
                    	return '<i class="btn layui-icon edit" title="操作">&#x1005;</i>';
                    }
                    else{ return "";}
                }}*/
        ]
    });
    /**
     * 表格中的删除事件
     */
 /*   table.bind('click', '.edit', function (data, index) {
        
    });*/
    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getPageParams());
    };
    /**
     * 确认
     */
    var newData = function() {
    	 Joyin.dialog(JoyinUtil.getLabel("FinResultsCovering002"),
               JoyinUtil.getRealPath("invest/product/resultscovering/FinResultsCovering002/init"),
               "99%", "99%");
    };
    var init = function(){
		// 隐藏筛选和导出按钮
		$('#ProductTrial001_btn_filter').attr("style","display:none;");
		$('#coveringDate').val(coveringDate);

		$('#prodId').change(function() {
		    $('#layFinprodId').val('');
        });
	};
	 /**
     * 撤销
     */
    $('#FinResultsCovering001_btn_revoke').click(function () {
        if (table != null && table != '') {
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

       // var checkObj = {};
		var data = selArr[0];
		data.operateType = 'revoke';
		data.pageModel = PAGE_MODEL_REFERENCE;
        Joyin.ajaxJson(basePath + 'invest/product/resultscovering/FinResultsCovering001/check', data, function (result) {
            Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
            	data.revokeReason = result;
                WorkFlow.doRevokeList(basePath + 'invest/product/resultscovering/FinResultsCovering001/doRevoke', data, "FinResultsCovering001_btn_revoke", "FinResultsCovering002",function(){
                    table.reload();//回调事件请求及回调函数
                });
            });
        });
    });


    $(function () {
    	init();
        // 绑定按钮事件
        $('#FinResultsCovering001_btn_search').click(function() {
            table.load();
        });
        $('#FinResultsCovering001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinResultsCovering001_btn_add').click(function() {
            newData();
        });
        /*$('#FinResultsCovering001.revoke').click(function() {
        	revokeData();
        });*/
    });
}());