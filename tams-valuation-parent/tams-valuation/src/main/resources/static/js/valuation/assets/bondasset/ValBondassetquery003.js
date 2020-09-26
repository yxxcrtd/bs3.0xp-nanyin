$(function () {
	
	/**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery003/searchList',
            type: 'POST',
            data: {workflowId : workflowId}
        },
        searchOnInit: true,
        checkbox: false,
        cols: [
               {field:'bondid',title: '债券代码'},
               {field:'bondname',title: '债券简称'},
               {field:'vdate1',title: '起息日'},
               {field:'mdate1',title: '到期日'},
               {field:'couponTypeName',title: '息票品种'},
               {field:'bondtype',title: '债券分类'},
               {field:'rate',title: '当前利率(%)'},
               {field:'basis',title: '计息基础'},
               {field:'price',title: '发行价'},
               {field:'finprodName',title: '债券名称'}
           ] ,
		loadonce:true,
		page:false
	});
});