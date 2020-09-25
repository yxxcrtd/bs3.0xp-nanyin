(function() {
	
    /**
     * 画面加载完成
     */
    $(function() {
    	$("li[data-id='A']").css({"color":"#4893E6","border-bottom":"2px solid #4893E6"});
    	var jqWin = $(window);
		var winH = jqWin.height();
		console.log('winH=' + winH);
		
		var jqReport = $('#isup_iframe');
		var reportTop = jqReport.offset().top;
		var reportH = winH - reportTop;
		jqReport.height(reportH);
		
		jqWin.resize(function() {
			var winH = jqWin.height();
			var reportH = winH - reportTop;
			jqReport.height(reportH);
		});
		$("#sheet_ul").on('click', 'li', function() {
			var jqLi = $(this);
			var indexId = jqLi.attr('data-id');
			
			if(indexId=='S'){
				jqLi.css({"color":"#4893E6","border-bottom":"2px solid #4893E6"});
				$("li[data-id='A']").css({"color":"#444","border-bottom":"0px solid #4893E6"});
				$("#isup_iframe").attr("src",basePath+"invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/init");
			}
			else{
				jqLi.css({"color":"#4893E6","border-bottom":"2px solid #4893E6"});
				$("li[data-id='S']").css({"color":"#444","border-bottom":"0px solid #4893E6"});
				$("#isup_iframe").attr("src",basePath+"invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPointMain/initInd");
			}
		});
	
    });
}());