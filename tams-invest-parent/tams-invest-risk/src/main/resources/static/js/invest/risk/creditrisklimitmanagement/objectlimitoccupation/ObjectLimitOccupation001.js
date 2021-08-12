(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
        return param;
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
                    basePath + "invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation003/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,customerId:data.customerId, creditMstId:data.creditMstId});
        },
        
        cols:[
              {field:'xuhao', width:50 ,title: 'ObjectLimitOccupation001.xuhao'}
             ,{field:'customerId', width:50 ,visible: false}
             ,{field:'customerName', width:250 , title: 'ObjectLimitOccupation001.object'}
             ,{field:'creditType', width:50 ,visible: false}
             ,{field:'creditTypeName', width:250 , title: 'ObjectLimitOccupation001.creditName'}
             ,{field:'term', width:50 ,visible: false}
             ,{field:'termUnit', width:50 ,visible: false}
             ,{field:'termUnitName', width:250 , title: 'ObjectLimitOccupation001.termUnitName'}
             ,{field:'totleamount', width:250 , type:'number', title: 'ObjectLimitOccupation001.totleamount'}
             ,{field:'creditMstId', width:50 ,visible: false}
             ,{field:'ccy', width:50 ,visible: false}
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
        $('#ObjectLimitOccupation001_btn_search').click(function() {
            table.load();
            $('#ObjectLimitOccupation001_btn_add').show();
        });
        $('#ObjectLimitOccupation001_btn_export').click(function() {
            exportExcel();
        });
        $('#ObjectLimitOccupation001_btn_add').click(function() {
//            if(table!=null && table!=''){
//                var selArr = table.getAllCheckedRows();
//            }
//            if (!selArr || selArr.length == 0) {
//                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
//                return;
//            }
            var param = {
//            		customerId: selArr[0].customerId,
//            		creditType: selArr[0].creditType,
//            		term: selArr[0].term,
//            		termUnit: selArr[0].termUnit,
//            		creditMstId: selArr[0].creditMstId,
//            		ccy:  selArr[0].ccy,
            		pageModel : PAGE_MODEL_NEW
            };
            Joyin.dialog(JoyinUtil.getLabel("ObjectLimitOccupation002"),
                    JoyinUtil.getRealPath("invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation002/init"),
                    "95%", "90%",param,function(){table.reload();}
            );
        });
        
    });
}());