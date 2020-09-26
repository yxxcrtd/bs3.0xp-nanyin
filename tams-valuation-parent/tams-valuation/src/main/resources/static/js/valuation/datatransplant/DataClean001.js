$(function() {
	// 查询查询隐藏
	$('#DataClean001_btn_search').remove();

	// 执行按钮
	$('#doInterBtn').unbind('click').click(function() {
		Joyin.ajax('deleteDate', {
			booksetIdArr : $.trim($('#booksetId').val()),
			deleteDateBegin : $("#deleteDate").startDate(),
			deleteDateEnd : $("#deleteDate").endDate(),
			modelType : $('#modelType').val()
		}, function(result) {
			// window.location.reload();
		});
	});

});
