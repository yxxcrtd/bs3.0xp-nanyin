$(function(){
	/**
     * 定义数据表格
     */
    var table=$("#FinNetValueProductBuild006_dataTable").joyintable({ 
    	ajax: {
            url: 'getList',
            type: 'POST',
            data: function() {
                return {items : $('#items').val(),period : $('#period').val(),cycle : $('#cycle').val()};
            }
        },
        searchOnInit : true,
        page: false,
        loadonce: true,
        rowno: true,
        cols:[
        
			//{field:'openDateStr', title: 'FinProductBuild005.table.vdateStr', width:150,type:'date'}
			{field:'vdateStr', title: 'FinProductBuild005.table.vdateStr', width:150,type:'date'}
			,{field:'investmentCycle',title:'FinProductBuild005.table.InvestmentCycle', width:150}
			,{field:'vdateEnd', title: 'FinProductBuild005.table.vdateEnd', width:150,type:'date'}
        ]
    });
    $(function() {
       if(!table){
    	   table.load();
       }else{
    	   table.reload();
       } 
   	
	});
}());
