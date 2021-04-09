$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	feeType: $.trim($('#feeType').val()),
        	vdate: $.trim($('#vdate').val()),
        	mdate: $.trim($('#mdate').val()),
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
		done: function(data){
			t = data.finprodId2;
		},
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看"),
                basePath + "valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
            	booksetId: data.booksetId,finprodId:data.finprodId, operateType:"view"},function(){
                    table.reload();
                });
        },
        checkbox: true,
        cellMinWidth : 100,
        cols: [
            {field:'booksetId', width:200, title: '账套代码',sort: true },
            {field:'booksetName', width:200, title: '账套名称' ,sort: true},
            {field:'finprodId', title: '业务编号' ,sort: true},
            {field:'feeType', width:120, title: '费用类型' ,sort: true,visible:false},
            {field:'feeTypeFmt', sortField: "feeType", title: '费用类型',width:120,sort: true},
            {field:'prin', width:120, type:'money', title: '金额' ,sort: true},
            {field:'vdate', width:150, type:'date', title: '开始日期' ,sort: true},
            {field:'mdate', width:150, type:'date', title: '结束日期' ,sort: true}
        ],
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {

        /**
         * 绑定事件：待摊设置 - 新建
         */
        $("#ValWithholdingAmortizeSet001_btn_add").click(function(){
            Joyin.dialog(JoyinUtil.getLabel("新建（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                basePath + "valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/init",
                "85%", "85%", {pageModel : PAGE_MODEL_NEW,  operateType:"new"},function(){
                    table.reload();

                });
        });

        /**
         * 绑定事件：待摊设置 - 修改
         */
        $("#ValWithholdingAmortizeSet001_btn_modify").click(function(){
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length === 0 || rowArr.length >1){
                Joyin.error("请选择1条记录！");
            }else{
                Joyin.dialog(JoyinUtil.getLabel("修改（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT, booksetId: rowArr[0].booksetId, finprodId: rowArr[0].finprodId, operateType:"edit"},function(){
                       table.reload();
                        });
            }
        });



        /** 事件绑定: 删除 */
        $('#ValWithholdingAmortizeSet001_btn_delete').click(function() {
	            if(table!=null && table!=''){
	                var selArr = table.getAllCheckedRows();// 所有选中行数据
	                var datas = JSON.stringify(selArr);
	            }
	            if (!selArr || selArr.length == 0) {
	                Joyin.error("请选择记录进行删除。");
	                return;
	            }
	            var object = {};
	            object.listValWithholdingAmortizeSetDto = selArr;
	            object.data = datas;
	            object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet001/check', object, function(result) {
//                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/withholdingamortize/ValWithholdingAmortizeSet001/delete', object,
                            "ValWithholdingAmortizeSet001_btn_delete",
                            "ValWithholdingAmortizeSet002",
                            function() {
                        	table.reload();
                        });
//                    });
                });
//            }
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
        	Joyin.error(JoyinUtil.getMessage("请勾选查询结果记录！"));  
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
        $('#ValWithholdingAmortizeSet001_btn_search').click(function() {
            table.load();
        });

        // 绑定导出事件
        $('#ValWithholdingAmortizeSet001_btn_export').click(function() {
            exportExcel();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });

});
