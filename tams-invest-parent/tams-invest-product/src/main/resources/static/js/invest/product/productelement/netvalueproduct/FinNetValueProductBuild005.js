$(function(){
	/**
     * 定义数据表格
     */
    var table=$("#FinNetValueProductBuild005_dataTable").joyintable({ 
    	ajax: {
            url: 'queryForList',
            type: 'POST',
            data: function() {
                return {finprodId : finprodId};
            }
        },
        searchOnInit : true,
        page: false,
        loadonce: true,
        rowno: true,
        cols:[
        
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
