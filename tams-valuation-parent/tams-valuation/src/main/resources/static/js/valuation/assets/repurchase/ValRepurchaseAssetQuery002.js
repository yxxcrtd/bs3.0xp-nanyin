$(function () {
   
    /**
     * 定义数据表格-抵押品信息
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'collateralinfo',
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [           
            {field:'pledgeFinprodId', title: '抵押品代码', sort: true},
            {field:'pledgeFaceValue',width:200, title: '质押面值', type: 'money', sort: true},
            {field:'pledgeNumber', width:250, title: '数量',type: 'money', sort: true},           
            {field:'pledgeRatio', width:250, title: '质押率(%)',type: 'money', sort: true},
            {field:'pledgeAmt', width:250, title: '质押金额', type: 'money', sort: true}                          
        ],
      loadonce:true,
      page:false
    });
    
    var docH= $(document).width();
    $('.layui-colla-content').css('width',docH-50);
 
}());