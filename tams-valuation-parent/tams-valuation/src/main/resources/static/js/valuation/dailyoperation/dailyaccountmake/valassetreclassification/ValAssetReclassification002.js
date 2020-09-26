$(function() {

    /**
	 * 定义数据表格
	 */
    var tabTable=$("#dataTable").joyintable({
    	data:eval(resultDate),
        page:false,
        checkbox: false,
		cellMinWidth : 100,
		loadonce:true,
        cols:[
              {field:'booksetId',title: '所属账套'}
	        ,{field:'booksetDate', type:'date', title: '重分类日期'}
	    	,{field:'finprodId', title: '资产代码'}
	        ,{field:'finprodName',  title: '资产名称'}
	        ,{field:'invAimFmt', sortField:"invAim",  title: '原会计分类'}
	        ,{field:'newInvAimName', title: '新会计分类'}]
    });
    
 
   
    
    /**
     * 画面加载完成
     */
    $(function() {
    	tabTable.load();
    });
}());