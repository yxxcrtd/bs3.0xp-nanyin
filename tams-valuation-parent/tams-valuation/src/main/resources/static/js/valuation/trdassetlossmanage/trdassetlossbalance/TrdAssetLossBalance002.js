/**
 * 资产减值余额明细
 */

$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var busiId = $('#busiId').val();
        var booksetId = $('#booksetId').val();
        var bokDetailId = $('#bokDetailId').val();
        var bookDate = $('#bookDate').val();
        return {
            busiId: busiId,
            booksetId: booksetId,
            bokDetailId: bokDetailId,
            bookDate: bookDate
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
            ,{field:'bookDate', width:180 , title: '业务日期',type:'date'}
            ,{field:'lossExeTypeValue', width:180 , title: '业务类型',sortField:'lossExeType'}
            ,{field:'tdyCostAmt', width:180 ,title: '账面价值'}
            ,{field:'tdyIntAmt', width:180 ,title: '应收利息'}
            ,{field:'tdyLossamtAdd', width:180 , title: '发生额'}
            ,{field:'tdyLossamt', width:180 , title: '账面减值余额'}
        ]
    });

    /**
     * 画面加载完成
     */
    $(function() {
       table.load();
    });
}());