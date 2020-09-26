$(function() {
    var table=$("#dataTable").joyintable({
        
        
        data:[{"intRateId":$('#intRateId').val(),"intRate":$('#intRate').val(),"relaeseDate":$('#relaeseDate').val(),"effDate":$('#effDate').val()}],
        searchOnInit: true,
        checkbox: false,
        cols : [ {
            field : 'intRateId',
            fixed : true,
            title : '行情代码'
        }, {
            field : 'intRate',
            title : '利率'
        }, {
            field : 'relaeseDate',
            title : '发布日'
        }, {
            field : 'effDate',
            title : '生效日'
        }],
        loadonce:true,
        page:false
    });
});