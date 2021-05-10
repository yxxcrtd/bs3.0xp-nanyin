$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	positionDateBegin: $('#positionDate').startDate(),
        	positionDateEnd: $('#positionDate').endDate(),
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看"),
                basePath + "valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
            	seqNo: data.seqNo, operateType:"view"},function(){
                    table.reload();
                });
        },
        checkbox: true,
        cellMinWidth : 100,
        cols: [
            {field:'seqNo', width:200, title: '流水号',visible:false},
            {field:'booksetId', width:200, title: '账套代码' ,sort: true},
            {field:'feeType', width:200, title: '费用类型' ,sort: true,visible:false},
            {field:'feeTypeFmt', sortField: "feeType", title: '费用类型',width:200,sort: true},
            {field:'registOrg', width:200, title: '登记托管机构' ,sort: true,visible:false},
            {field:'registOrgFmt', sortField: "registOrg", title: '登记托管机构',width:200,sort: true},
            {field:'positionStartDate', width:190, type:'date', title: '计提开始日' ,sort: true},
            {field:'positionEndDate', width:190, type:'date', title: '计提结束日' ,sort: true},
            {field:'feeAmtTotal', width:200, type:'money', title: '区间费用金额' ,sort: true},
            {field:'operation', title: '操作' , type:'action', buttons: ['edit'] , width:100}
        ],
    });
    
//    /**
//     * 绑定事件：费用预提设置 - 修改
//     */
//    table.bind('click', '.edit', function(data, index) {
//        data.positionDate = new Date(data.positionDate).format();
//        data.pageModel=PAGE_MODEL_EDIT;
//        data.operateType='edit';
//        Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig001/check', data, function(result) {
//        	Joyin.dialog(JoyinUtil.getLabel("修改（<i class='iconfont must' style='display:inline'>*</i>必填）"),
//                    basePath + "valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/init",
//                    "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType:"edit"},function(){
//                    	table.reload();
//                    });
//        });
//    });

    /**
     * 事件绑定
     */
    var tableBind = function () {
    	
    	// 计提日期默认为账套当前日期
        $('#booksetId').change(function(){
            var booksetId = $('#booksetId').val();
            if(booksetId ==''|| booksetId==undefined){
                $('#positionDate').val('');
            }
            else{
            	var booksetDate = $('#booksetId').select2('data')[0].object;
            	booksetDate = booksetDate+" - "+booksetDate;
                $('#positionDate').val(booksetDate).trigger('change');
            }
        })
    	
        /**
         * 绑定事件：费用预提设置 - 新建
         */
        $("#ValTrdAccountmfConfig001_btn_add").click(function(){
            Joyin.dialog(JoyinUtil.getLabel("新建（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                basePath + "valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/init",
                "85%", "85%", {pageModel : PAGE_MODEL_NEW,  operateType:"new"},function(){
                    table.reload();
                });
        });
        
        /**
         * 绑定事件：费用预提设置 - 修改
         */
        table.bind('click', '.edit', function(data, index) {
            data.positionDate = new Date(data.positionDate).format();
            data.pageModel=PAGE_MODEL_EDIT;
            data.operateType='edit';
            Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig001/check', data, function(result) {
            	Joyin.dialog(JoyinUtil.getLabel("修改（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType:"edit"},function(){
                        	table.reload();
                        });
            });
        });

        /**
         * 绑定事件：费用预提设置 - 删除
         */
        $('#ValTrdAccountmfConfig001_btn_delete').click(function() {
	            if(table!=null && table!=''){
	                var selArr = table.getAllCheckedRows();// 所有选中行数据
	                var datas = JSON.stringify(selArr);
	            }
	            if (!selArr || selArr.length != 1) {
	                Joyin.error("请选择一条记录。");
	                return;
	            }
	            var object = selArr[0];
	            object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig001/check', object, function(result) {
                        WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valtrdaccountmfconfig/ValTrdAccountmfConfig001/delete', object,
                            "ValTrdAccountmfConfig001_btn_delete",
                            "ValTrdAccountmfConfig002",
                            function() {
                        	table.reload();
                        });
                });
        });
    };


    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
        	var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValTrdAccountmfConfig001_btn_search').click(function() {
            table.load();
        });

        // 绑定导出事件
        $('#ValTrdAccountmfConfig001_btn_export').click(function() {
            exportExcel();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });

});
