(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        	   var result = $.serializeToObject('#searchForm');
               if ($("#supStatus").val()) {
                   result.supStatus = $("#supStatus").val().join(",");
               } else {
                   result.supStatus = null;
               }
               if ($("#subjectName").val()) {
                   result.subjectName = $("#subjectName").val().join(",");
               } else {
                   result.subjectName = null;
               }
               if ($("#creditName").val()) {
                   result.creditName = $("#creditName").val().join(",");
               } else {
                   result.creditName = null;
               }
               if ($("#orgType").val()) {
                   result.orgType = $("#orgType").val().join(",");
               } else {
                   result.orgType = null;
               }
            return result;
    };

    /**
     * 定义数据表格
     */
    var table1 = {
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
                    basePath + "invest/risk/creditrisklimitmanagement/linesupervision/LineSupervision002/init",
                    "95%", "90%", data);
        },
        
        cols:[
            {field:'subjectName', width:150 , title: 'LineSupervision001.subjectName'}
            ,{field:'creditName', width:150 , title: 'LineSupervision001.creditName'}
            ,{field:'creditAmt', width:150 , title: 'LineSupervision001.checkRatifyLine'}
            ,{field:'maxTimeLimit', width:150 , title: 'LineSupervision001.maxTimeLimit'}
            ,{field:'warningRatio', width:150 , title: 'LineSupervision001.warningRatio'}
            ,{field:'availableCredit', width:150 , title: 'LineSupervision001.availableCredit'}
            ,{field:'hapCreditAmt', width:150 , title: 'LineSupervision001.usingCredit'}
            ,{field:'adjustCredit', width:150 , title: 'LineSupervision001.adjustCredit'}
            ,{field:'tradeFace', width:150 , title: 'LineSupervision001.tradeFace'}
            ,{field:'orgType', width:150 , title: 'LineSupervision001.orgType'}
            // ,{field:'orgTier', width:150 , title: 'LineSupervision001.orgTier'}
            ,{field:'supStatusName', width:150 , title: 'LineSupervision001.supStatus'}
        ]
    };

    var table=$("#dataTable2").joyintable(table1);

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
        $('#LineSupervision001_btn_search').click(function() {
            table.load();
        });

        $('#CreditRule001_btn_export').click(function() {
            exportExcel();
        });

    });
}());