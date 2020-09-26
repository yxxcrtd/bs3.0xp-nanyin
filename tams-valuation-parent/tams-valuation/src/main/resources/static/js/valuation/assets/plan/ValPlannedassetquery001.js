$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            bond:$.trim($('#bond').val()),
            profitType:$.trim($('#profitType').val()),
            assetType:$.trim($('#assetType').val()),
            vDateBegin: $('#vdate').startDate(),
            vDateEnd: $('#vdate').endDate(),
            mDateBegin: $('#mdate').startDate(),
            mDateEnd: $('#mdate').endDate()
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
        cellMinWidth : 120,
        dblclick:function(table, data) {
            var selChe = table.getAllCheckedRows();
            if(selChe.length > 1){
                alert("请选择一条记录进行操作！");
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("查看计划类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/plan/ValPlannedassetquery002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_EDIT, finprodId:data.finprodId, profitType:data.profitTypeCode},
                    function(){
                        //table.reload();
                        });
        },
        cols: [
            {field:'finprodId', title: '资产代码', sort: true},
            {field:'finprodAbbr', title: '资产名称', sort: true},
            {field:'assetTypeFt', title: '资产类型', sortField:'assetTypeCode',sort: true},
            {field:'assetTypeCode', title: '资产类型', sort: true,visible:false},
            {field:'vdate1', title: '起息日',sort: true},
            {field:'mdate1', title: '到期日',sort: true},
            {field:'profitTypeFt', title: '收益类型', sortField:'profitTypeCode',sort: true},
            {field:'profitTypeCode', title: '收益类型', sort: true,visible:false},
            {field:'currentquotation', title: '当前报价(%)', sort: true},
            {field:'basic', title: '计息基础',sort: true},
            {field:'issuer', title: '发行人',sort: true}
           // {fixed : 'right', title : '操作', align : 'center',type : 'action', buttons : [ 'edit' ] }
        ] 
    });
    
    /**
     * 绑定编辑事件
     */
/*    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog(basePath+JoyinUtil.getLabel("ValBondassetquery001.editRemark"),
                basePath + "valuation/assets/posit/ValBondassetquery002/init",
                "40%", "40%", {pageModel:PAGE_MODEL_EDIT, vouchNum:data.vouchNum},function(){
                    table.reload();
                });
    });*/
    
    /**
     * 导出功能
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
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

        $('#ValPlannedassetquery001_btn_search').click(function() {
            table.load();
        });
        
        $('#ValPlannedassetquery001_btn_export').click(function() {
            exportExcel();
        });
    });
}());