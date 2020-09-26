/**
 * 数据检查共通处理
 */
(function($, global) {
	var checkResult = parent.window.checkResult || [];
	$('#checkTable').joyintable({
	    cols: [
                  { title: "标识码", field: 'validKey', width:175, align: 'center', sortable:false},
                  { title: "检查结果", field: 'checkResult', align: 'center', sortable:false},
                  { title: "错误信息项", field: 'errorInfo', width:290, align: 'center', sortable:false},
                  { title: "警示信息项", field: 'warnInfo', width:290, align: 'center', sortable:false}
                  ],
        searchOnInit: true,
        checkbox: false,
		data: checkResult
		//height: ltIE9 ? 420 : 440
	});
}(jQuery, this));
