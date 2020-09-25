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

	var tables=[];
	if (ASSETLIST && NOLIST != "Y") {
		$.map(ASSETLIST,function(obj, i){
			 var table = $("#" + obj.assetCodeStr).joyintable({
			        ajax: {
			            url: '/invest/riskredis/quota/rskapproval/RskApproval003/findPage',
			            type: 'POST',
			            data: function() {
			            	var data = getSearchParams();
			            	data.assetCode = obj.assetCode;
			            	if (data.assetCode == null || data.assetCode == '') {
			            		data.assetCode = obj.issuer;
			            	}
			            	data.uuid = obj.uuid;
			                return data;
			            }
			        },
			        rowno: true,
			        checkbox: false,
			        height:300,
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
			 tables.push(table);
		});
	}

    var onSaveOrSubmitAction = function(operateType) {
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskapproval/RskApproval003/check', entity, function(result) {
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval003/save', entity, "RskApproval003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskapproval/RskApproval003/save', entity, "RskApproval003_btn_submit");
                }
            });
        });
    };

    var data_table = $("#data_table").joyintable({
    	ajax: {
            url: '/invest/riskredis/quota/rskapproval/RskApproval003/findPage',
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
            ,{field:'uniteSupStatStr', sortField:'supUniteStat',title:'RskApproval.ind.supUniteStat',width:'9%'}
        ]
    });
    var data_assetTable = $("#data_assetTable").joyintable({
    	page:false,
		searchOnInit:true,
		loadonce : true,
        checkbox:false,
        height:200,
        cols: [
             {field:'assetCode', title:'RskApproval.finprodId', width:'25%', sortable: false}
            ,{field:'assetName',title:'RskApproval.finprodName',width:'15%', sortable: false}
            ,{field:'issuerName', title:'RskApproval.issuerName',width:'10%', sortable: false}
            ,{field:'market', title:'RskApproval.market',width:'10%', sortable: false,edit:true,
              	editOption:{id:'market',type:'select',formatoption:true,showField:'text',result:TRADE_PLACE}}
            ,{field:'amount', title:'RskApproval.amount',width:'10%', sortable: false}
            ,{field:'tradeNum', title:'RskApproval.tradeNum',width:'10%', sortable: false}
            ,{field:'priceAmt', title:'RskApproval.priceAmt', sortable: false}
        ]
    });
    var init = function(){
    	if (NOLIST == 'Y'){
    		$("#data_table_div").show();
    		data_table.load();
    	}
    	else {
    		$("#data_table_div").hide();
    	}
    	if(ASSETLIST && ASSETLIST.length>0){
    		$("#data_assetTable_div").show();
    		$.each(ASSETLIST,function(i,v){
    			setTimeout(function(){data_assetTable.addRow(v);},500);
    		})
    	}else{
    		$("#data_assetTable_div").hide();
    	}
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	if (tables) {
    		$.map(tables, function(table, i){
        		table.load();
        	});
    	}

    	init();
 
    	// 绑定按钮事件
        $('#RskApproval003_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        $('#RskApproval003_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
    });
}();



