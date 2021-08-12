/**
 * RskApproval003 模糊委托审批
 */
var rsk = function() {
	var getSearchParams = function(){
        var rows;
        if (table2 != null && table2 != '') {
            rows = table2.getAllRows();
        }
		return {keyList: JSON.stringify(rows)};
	}

    /**
     * 定义数据表格
     */
    var table = $("#data_table").joyintable({
        ajax: {
            url: '/invest/riskredis/quota/rskapproval/RskApproval004/selectForList',
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
            ,{field:'portfolioId', sortField:'portfolioId',title:'组合(包)代码',width:'15%'}
            ,{field:'assetCode', sortField:'assetCode',title:'资产代码',width:'15%'}
            ,{field:'warnCond', title:'RskApproval.ind.warnCond',width:'11%'}
            ,{field:'approveCond', title:'RskApproval.ind.approveCond',width:'11%'}
            ,{field:'foulCond', title:'RskApproval.ind.foulCond',width:'11%'}
            ,{field:'supVal', title:'RskApproval.ind.supVal',width:'11%'}
            ,{field:'oldSupVal', title:'原值',width:'11%'}
            ,{field:'supTrend', sortField:'supTrend',title:'趋势',width:'9%'}
            ,{field:'supStatStr', sortField:'supStat',title:'RskApproval.ind.supStat',width:'9%'}
            ,{field:'uniteSupStatStr', sortField:'uniteSupStat',title:'RskApproval.ind.supUniteStat',width:'9%'}
        ]
    });

    /**
     * 定义数据表格
     */
    var table2 = $("#rskTable").joyintable({
        rowno: true,
        checkbox: false,
        cols: [
            {field:'eventId', title:'交易代码', width:200}
            ,{field:'portfolioCode', title:'组合代码',width:200}
            ,{field:'amount', title:'交易金额',width:200}
        ]
    });


    var onSaveOrSubmitAction = function(operateType) {
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskapproval/RskApproval002/check', entity, function(result) {
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval002/save', entity, "RskApproval002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval002/save', entity, "RskApproval002_btn_submit");
                }
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function() {

        table2.load();
    	// 绑定按钮事件
        $('#RskApproval002_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        $('#RskApproval002_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });

        if(rsklist){
            for(var i = 0;i<rsklist.length;i++) {
                var object = rsklist[i];
                table2.addRow(object);
            }
        }

        table.load();

    });
}();



