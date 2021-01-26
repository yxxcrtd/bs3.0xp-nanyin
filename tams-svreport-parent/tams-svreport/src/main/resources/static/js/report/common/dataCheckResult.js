/**
 * 数据检查共通处理
 */
(function($, global) {
	var checkResult = parent.window.checkResult || [];
    var checkResultTable = $('#checkTable').joyintable({
	    cols: [
                  { title: "标识码", field: 'validKey', width:175, align: 'center', sortable:false},
                  { title: "检查结果", field: 'checkResult', align: 'center', sortable:false},
                  { title: "错误信息项", field: 'errorInfo', width:290, align: 'center', sortable:false},
                  { title: "警示信息项", field: 'warnInfo', width:290, align: 'center', sortable:false}
                  ],
        searchOnInit: true,
        checkbox: false,
		data: checkResult,
        height:420,
        done: function(){
            if ($('#dataCheckResult_hidden_opertionType').val() == 'submitChb'|| $('#dataCheckResult_hidden_opertionType').val() == 'reSubmitChb'){
                var submitFlag = true;
                var warningFlag = true;
                for(var i=0;i<checkResult.length;i++){
                    if (checkResult[i].checkResult == "检查不通过"){
                        submitFlag = false;
                    }
                    if (checkResult[i].checkResult == "存在警示信息项"){
                        warningFlag = false;
                    }
                }
                if (checkResult.length > 0){
                    // (不存在检查不通过) 且 （不存在预警信息项 或 存在预警信息项且系统参数为01）
                    if (submitFlag && (warningFlag || (!warningFlag && $('#dataCheckResult_hidden_warningCheckIsSend').val() == '01'))){
                        $('#dataCheckResult_btn_submit_btn_div').toggle(true);
                    }
                }
            }
        }
		//height: ltIE9 ? 420 : 440
	});

    /**
     * 提交按钮点击事件
     */
    $('#dataCheckResult_btn_submit').click(function(){
        parent.window.submitChb();
    })
}(jQuery, this));
