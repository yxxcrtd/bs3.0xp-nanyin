$(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return  entity1;
    };
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
	 * 定义数据表格
	 */
	var table = $("#dataTable")
			.joyintable(
					{
						searchOnInit:true,
						loadonce : true,
						checkbox : false,
						data:cashFlowDetail,
						page : false,
						cols : [
								{
									field : 'colValue1',
									width : 200,
									title : '项目',
									sortable : false
								},
								{
									field : 'colValue2',
									width : 300,
									title : '分项',
									sortable : false
								},
								{
									field : 'colValue3',
									width : 350,
									title : '细分项目',
									sortable : false
								},
								{
									field : 'amount',
									width : 250,
									title : '金额',
									sortable : false,
									type:'number'
								},
								{
									field : 'remarks',
									width : 100,
									title : '备注',
									sortable : false,
								}
								 ]
					});
	
	/**
	 * 画面加载完成
	 */
	$(function() {
		table.load();
		
		$('#FlowRiskStressTestView003_btn_search').attr('style','display:none');
		$('#FlowRiskStressTestView003_btn_reset').attr('style','display:none');
		// 绑定导出按钮事件
		$('#FlowRiskStressTestView003_btn_export').click(function() {
			exportExcel();
		});
	});
}());