(function () {
	$(function(){
		$("#FinProductValuation001_prodcode").val(window.parent.param.prodcode);
		$("#FinProductValuation001_vldate").val(window.parent.param.vldateStr);
		$("#FinProductValuation001_ccy").textSelect('setValue', window.parent.param.ccy);
	});
}());