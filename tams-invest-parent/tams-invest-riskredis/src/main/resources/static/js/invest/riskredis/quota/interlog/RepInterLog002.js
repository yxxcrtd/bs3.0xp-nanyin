/**
 * RepInterLog002 接口日志管理详细
 */
var rsk = function() {
	/*
	 * 消费系统+IP+端口号
	 */
	function transSysCodeFoul(sysCode1, ipAddress, port) {
		var wg = "";
		wg = sysCode1 + "@" + ipAddress + ":" + port;
		return wg;
	}

	var init = function() {
		var sysCode1 = $("#sysCode1").val();
		var ipAddress = $("#ipAddress").val();
		var port = $("#port").val();
		$("#sysCode").val(transSysCodeFoul(sysCode1, ipAddress, port));
	}

	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		$("#RepInterLog002_btn_detail").click(
				function() {
					Joyin.dialog(JoyinUtil.getLabel("RepInterLog003"), basePath
							+ "invest/riskredis/quota/interlog/RepInterLog003/init",
							"70%", "90%", {
								pageModel : PAGE_MODEL_REFERENCE,
								uuid : $("#uuid").val()
							});
				})
	});
}();
