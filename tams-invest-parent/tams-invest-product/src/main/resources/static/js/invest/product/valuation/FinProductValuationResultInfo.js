(function() {
	if (window.parent.param.profitType == "01") { // 净值型
		$("#mprodOwner").hide();
		$("#mprod_unpayProfit").hide();
	} else { // 货币型
		$("#nprodOwner").hide();
		$("#nprod_totCurYield").hide();
	}
}());