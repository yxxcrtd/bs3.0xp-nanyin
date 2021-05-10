/**
 * 资产减值余额查询
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var busiId = $('#busiId').val();
        var booksetId = $('#booksetId').val();
        var rangeDate = $('#rangeDate').val();
        var startDate = rangeDate.substring(0,10);
        var endDate = rangeDate.substring(13);
        return {
            busiId: busiId,
            booksetId: booksetId,
            startDate: startDate,
            endDate: endDate
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
        cols:[
            {field:'busiId', width:180 , title: '资产代码',visible:true}
            ,{field:'finprodAbbr', width:180 , title: '资产名称'}
            ,{field:'booksetId', width:180 , title: '账套代码'}
            ,{field:'booksetName', width:180 , title: '账套名称'}
            ,{field:'invAim', width:180 , title: '投资目的',visible:false}
            ,{field:'bookDate', width: 180, title: '日期', type: 'date'}
            ,{field:'costAmt', width:180 ,title: '账面价值'}
            ,{field:'tdyIntincexp', width:180 ,title: '应收利息'}
            ,{field:'tdyLossAmt', width:180 , title: '当前账面减值余额'}
            ,{
                field: 'operation', width:100, title: '操作',sort:false,type:'action',
                render: function (data, type, row, meta) {
                    return "<a id ='mingxi' class='portfolioId-link ' style='cursor: pointer;'>查看明细</a>";
                }
            }
        ]
    });

    /**
     * 绑定查看明细的按钮
     */
    table.bind('click', '#mingxi', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson('/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("资产减值余额明细"),
                "/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance002/init",
                "90%", "90%",data,function() {
                    table.reload();
                });

        });

    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var selArr;
        var datas;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
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
        $('#TrdAssetLossBalance001_btn_search').click(function() {
            table.load();
        });
        $('#TrdAssetLossBalance001_btn_export').click(function() {
            exportExcel();
        });
    });
}());