$(function () {
   
    /**
     * 定义数据表格-罚息利率报价信息
     */
    var table1=$("#dataTable1").joyintable({
        ajax: {
            url: 'collateralinfo',
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),finprodType: $("#finprodType").val(),branchNo: $("#branchNo").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [
        	{field:'effDate', title: '生效日', width:120, edit:true, editOption:{type: 'date'}},  
            {field:'yield', width:250, title: '罚息利率(%)',type: 'money', sort: true}                        
        ],
      loadonce:true,
      page:false
    });
    
        /**
     * 定义数据表格-罚息计划列表
     */
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: 'fenaltyinterestinfo',
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),finprodType: $("#finprodType").val(),branchNo: $("#branchNo").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [    
        	{field:'vdate1', title: '罚息开始日', width:120, edit:true, editOption:{type: 'date'}},
        	{field:'mdate1', title: '罚息截止日', width:120, edit:true, editOption:{type: 'date'}}                    
        ],
      loadonce:true,
      page:false
    });
    
    var docH= $(document).width();
    $('.layui-colla-content').css('width',docH-50);
 
}());