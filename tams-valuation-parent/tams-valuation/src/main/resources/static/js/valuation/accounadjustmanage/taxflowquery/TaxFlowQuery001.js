$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            booksetId: $.trim($('#booksetId').val()),
            bokDetailId:$('#bokDetailId').val(),
            finType:$('#finType').val(),
            vatType:$('#vatType').val(),
            calcType:$('#calcType').val(),
            happenDateBegin: $("#happenDate").startDate(),
            happenDateEnd: $("#happenDate").endDate(),
            taxPType:$('#taxPType').val(),
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cellMinWidth:100 ,
        cols: [
            {field:'booksetId', width:160, title: '账套代码'}
            ,{field:'booksetName', width:200, title: '账套名称'}
            ,{field:'bokDetailId', width:200, title: '资产代码'}
            ,{field:'finProdAbbr', width:200, title: '资产名称'}
            ,{field:'happenDateFmt', sortField: "happenDate",width:120, title: '发生日期'}
            ,{field:'happenDate',width:120, title: '发生日期', visible:false}
            ,{field:'profitTypeFmt', width:120,sortField: "profitType", title: '收益类型'}
            ,{field:'profitType', width:120, title: '收益类型',visible:false}
            ,{field:'finTypeFmt', width:160, sortField: "finType", title: '资产分类'}
            ,{field:'finType', width:120, title: '收益类型',visible:false}
            ,{field:'taxPTypeFmt', width:120,sortField: "taxPType", title: '业务类型'}
            ,{field:'taxPType', width:120, title: '业务类型',visible:false}
            ,{field:'vatTypeFmt', width:120, sortField: "vatType",title: '收入类型'}
            ,{field:'vatType', width:120, title: '业务类型',visible:false}
            ,{field:'happenAmt', type:"money", width:140, title: '含税销售额'}
            ,{field:'happenAmtB', type:"money", width:140, title: '含税销售额本币'}
            ,{field:'calcTypeFmt', width:120, align : "right",sortField: "calcType", title: '应税标识'}
            ,{field:'calcType', width:120, align : "right", title: '应税标识',visible:false}
            ,{field:'balAmt', type:"money", width:160, title: '增值税税额'}
            ,{field:'balAmtB', type:"money", width:140, title: '增值税税额本币'}
            ,{field:'sellAmt', type:"money", digit:2, width:140, title: '销售额'}
            ,{field:'taxAmtB', type:"money", digit:2, width:140, title: '销售额本币'}
            ,{field:'taxRate', type:"money", width:140, title: '应税税率(%)'}
        ]
    });
    /*    var exportExcel = function() {
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', getPageParams(), function(result) {
                if(result.data){
                    Joyin.exportExcel('export', getPageParams());
                }
            });

        };*/

    /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getPageParams();
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
        $('#TaxFlowQuery001_btn_search').click(function() {
            table.load();
        });
        $('#TaxFlowQuery001_btn_export').click(function() {
            exportExcel();
        });
    });
});
