/**
 * 产品中间付息查询
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {
            prodId: $('#prodId').val()
            ,payIntEndDateBegin: $('#payIntEndDate').startDate()
            ,payIntEndDateEnd: $('#payIntEndDate').endDate()
            ,validStatus: $('#validStatus').val()
            ,portfolioId: $('#portfolioId').val()
            ,payDateBegin: $('#payDate').startDate()
            ,payDateEnd: $('#payDate').endDate()
        };
        if ($("#prodId").val()) {
            pageParam.prodId = $("#prodId").val().join(",");
        } else {
            pageParam.prodId = null;
        }
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
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/product/payint/FinProductPayInt003/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	bussId: data.bussId, finprodId: data.finprodId});
        },
        cols:[
            {field:'validStatusText', width:120, title: 'FinProductPayInt001.table.validStatus',sortField:'validStatus',}
            ,{field:'bussId', width:150, title: 'FinProductPayInt001.table.bussId'}
            ,{field:'portfolioId', width:150, title: 'FinProductPayInt001.table.portfolioId'}
            ,{field:'prodId', width:150, title: 'FinProductPayInt001.table.prodId'}
            ,{field:'prodAbbr', width:210, title: 'FinProductPayInt001.table.prodAbbr'}
            /*,{field:'saleDepartmentText', width:130, title: 'FinProductPayInt001.table.saleDepartment'}*/
            ,{field:'payintStartDate', width:130, title: 'FinProductPayInt001.table.payintStartDate', type: 'date'}
            ,{field:'payintEndDate', width:130, title: 'FinProductPayInt001.table.payintEndDate', type: 'date'}
            ,{field:'prinAmt', width:120, title: 'FinProductPayInt001.table.prinAmt', type: 'money'}
            ,{field:'theoryCusProfit', width:120, title: 'FinProductPayInt001.table.theoryCusProfit', type: 'money'}
            ,{field:'actCusProfit', width:120, title: 'FinProductPayInt001.table.actCusProfit', type: 'money'}
            ,{field:'ccy', width:120, title: 'FinProductPayInt001.table.ccy'}
            ,{field:'payDate', width:120, title: 'FinProductPayInt001.payDate', type: 'date'}
            ,{field:'validStatus', visible : false}
            ,{field:'saleDepartment', visible : false}
            ,{field:'finprodId', visible : false}
            ,{field:'isLay', visible : false}
        ]
    });
    
    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getPageParams());
    };

    /**
     * 确认
     */
/*    var confirm = function() {
        var selArr;
        if(table!=null && table!=''){
            selArr = table.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0026"));
            return;
        }
        // 列表中勾选对象1条以上时
        if (selArr.length > 1) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
            return;
        }
        
        var data = selArr[0];

        // 选中数据的有效状态必须为待处理
        if (data.validStatus !== PAGE_DATA_STATUS_01) {
            Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0032", JoyinUtil.getLabel("FinProductPayInt001.pendingDisposal")));
            return;
        }

        // 打开产品中间付息确认画面
        data.pageModel = PAGE_MODEL_NEW;
        if (data.payintStartDate) {
            data.payintStartDate = new Date(data.payintStartDate).format("yyyy-MM-dd");
        }
        if (data.payintEndDate) {
            data.payintEndDate = new Date(data.payintEndDate).format("yyyy-MM-dd");
        }
        Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt002"), 
                JoyinUtil.getRealPath("invest/product/payint/FinProductPayInt002/init"), "90%", "90%", data);
    };*/
    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt003.new"),
        		JoyinUtil.getRealPath("invest/product/payint/FinProductPayInt003/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
                );
    };
     /**
      撤销
     
    var revoke = function() {
        var selArr;
        if(table!=null && table!=''){
            selArr = table.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0026"));
            return;
        }
        // 列表中勾选对象1条以上时
        if (selArr.length > 1) {
            Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
            return;
        }
        
        var data = selArr[0];

        // 选中数据的有效状态必须为待处理
        if (data.validStatus !== PAGE_DATA_STATUS_01) {
            Joyin.error(JoyinUtil.getMessage("FIN_PROD_E_0032", JoyinUtil.getLabel("FinProductPayInt001.pendingDisposal")));
            return;
        }
    };*/
    /**
     * 撤销
     */
    var revoke = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		data.operateType = 'revoke';
        		data.pageModel = PAGE_MODEL_REFERENCE;
                Joyin.ajaxJson(basePath + 'invest/product/payint/FinProductPayInt001/check', data, function(result) {
                	Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                        data.revokeReason = result;
                        WorkFlow.doRevokeList(basePath + 'invest/product/payint/FinProductPayInt001/doRevoke', data, "FinProductPayInt001_btn_revoke", "FinProductPayInt003");
                    });
                });
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    $(function () {
        // 绑定按钮事件
        $('#FinProductPayInt001_btn_search').click(function() {
            table.load();
        });
        $('#FinProductPayInt001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductPayInt001_btn_confirm').click(function() {
            confirm();
        });
        $('#FinProductPayInt001_btn_add').click(function() {
        	openNew();
        });
        $('#FinProductPayInt001_btn_revoke').click(function() {
            revoke();
        });
    });
}());