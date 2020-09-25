/**
 * RskApproval003 模糊委托审批
 */
var rsk = function() {
	var getSearchParams = function(){
		return {
			uuid : $("#uuid").val(),
			eventId : $("#eventId").val()
		}
	}

    /**
     * 定义数据表格
     */
    var table = $("#data_table").joyintable({
        ajax: {
            url: '/invest/riskredis/quota/rskapproval/RskApproval001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        rowno: true,
        checkbox: false,
        cols: [
             {field:'indName', title:'RskApproval.ind.indName', width:'25.2%'}
            ,{field:'indTypeStr', sortField:'indType',title:'RskApproval.ind.indType',width:'10%'}
            ,{field:'warnCond', title:'RskApproval.ind.warnCond',width:'11%'}
            ,{field:'approveCond', title:'RskApproval.ind.approveCond',width:'11%'}
            ,{field:'foulCond', title:'RskApproval.ind.foulCond',width:'11%'}
            ,{field:'supVal', title:'RskApproval.ind.supVal',width:'11%'}
            ,{field:'supStatStr', sortField:'supStat',title:'RskApproval.ind.supStat',width:'9%'}
            ,{field:'uniteSupStatStr', sortField:'uniteSupStat',title:'RskApproval.ind.supUniteStat',width:'9%'}
        ]
    });

    var onSaveOrSubmitAction = function(operateType) {
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskapproval/RskApproval001/check', entity, function(result) {
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval001/save', entity, "RskApproval001_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval001/save', entity, "RskApproval001_btn_submit");
                }
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function() {
    	table.load();
    	// 绑定按钮事件
        $('#RskApproval001_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        $('#RskApproval001_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
    });
}();



