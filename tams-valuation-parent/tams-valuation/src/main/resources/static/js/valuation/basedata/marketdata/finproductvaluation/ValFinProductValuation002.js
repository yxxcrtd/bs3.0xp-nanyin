$(function() {
    var table=$("#dataTable").joyintable({
        
        
        data:[{"finprodId":$('#finprodId').val(),"finprodType":$('#finprodType').val(),"type1":$('#type1').val(),"valuation":$('#valuation').val(),"publishDate":$('#publishDate').val(),"valDate":$('#valDate').val()}],
        searchOnInit: true,
        checkbox: false,
        cols : [ {
            field : 'finprodId',
            fixed : true,
            title : '金融产品代码'
        }, {
            field : 'finprodType',
            title : '金融产品类型'
        }, {
            field : 'type1',
            title : '基金类型'
        }, {
            field : 'valuation',
            align : "right",
            title : '估值'
        }, {
            field : 'publishDate',
            title : '公布日期'
        }, {
            field : 'valDate',
            title : '估值日期'
        }]
//        loadonce:true
    });
});