$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId:$.trim($('#booksetId').val()),
            market:$.trim($('#market').val()),
            bondId:$.trim($('#bondId').val()),
            vDateBegin: $('#vdate').startDate(),
            vDateEnd: $('#vdate').endDate(),
            workflowId:workflowId
        }
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondrate/ValBondRealRate001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }    
        },
        searchOnInit: false,
        dblclick:function(table, data) {
          var datas = JSON.stringify(data);
            Joyin.dialog(JoyinUtil.getLabel("债券实际利率详情"),
                    JoyinUtil.getRealPath("/valuation/assets/bondrate/ValBondRealRate002/init"),
                    "80%", "70%", {pageModel:PAGE_MODEL_REFERENCE,data:datas},
                    function(){
                        //table.reload();
                        });
        },
        cols: [
            {field:'booksetId',title: '账套代码', sort: true},
            {field:'booksetName',title: '账套名称', sort: true},
            {field:'bondId',title: '债券代码',sort: true},
            {field:'bondName',title: '债券名称',sort: true},
            {field:'vdate',title: '会计日期',sort: true},
            {field:'changeAmt', title: '折溢价余额', width:140,sort: true,type:'number'},
            {field:'realRate',title: '实际日利率',width:140,sort: true,type:'number',digit:12},
            {field:'marketFt',title: '交易市场', sort: true,sortField:'market'},
            {field:'tdyCostAmt', title: '摊余成本', width:140,sort: true,type:'number'},
            {field:'tdyNPAmt', title: '摊余净价', width:140,sort: true,type:'number'},
            {field:'tdyFPAmt', title: '摊余全价', width:140,sort: true,type:'number'}
//            {field:'operation', title: 'SysCommon.operation', type:'action',width:80,buttons:['edit']}
        ] 
    });
    
    
    /**
     * 导出
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
        // 绑定查询事件
        $('#ValBondRealRate001_btn_search').click(function() {
            table.load();
        });
        // 绑定导出事件
        $('#ValBondRealRate001_btn_export').click(function() {
            exportExcel();
        });
    });
}());