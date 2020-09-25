/**
 * RepEntrustedApproval002 委托审批
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var eventId=$('#eventId').val();
        return {
        	eventId : eventId,
        	workflowId : $('#workflowId').val(),
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#portfRelateIndTable").joyintable({
        /*ajax: {
            url: basePath +'invest/riskredis/quota/entrustedapproval/RepEntrustedApproval002/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
        searchOnInit: true,
        checkbox:false,
        cols:[
            {field:'indId',visible :false}
            ,{field:'portfolioId',visible :false}
            ,{field:'supDate',type:'date',visible :false}
            ,{field:'indName',visible :false}
            ,{field:'expr',visible :false}
            ,{field:'supType',visible :false}
            ,{field:'isEnabled',visible :false}
            ,{field:'tradeId',visible :false}
            ,{field:'conclusion',visible :false}
            ,{field:'indName',title:'RepEntrustedApproval002.indName',width:100}
            ,{field:'indTypeAbbr',sortField:'indType',title:'RepEntrustedApproval002.indType',width:100}
            ,{field:'warnCond',title:'RepEntrustedApproval002.warnCond',width:100,render: transferWarnFoul}
            ,{field:'approveCond',title:'RepEntrustedApproval002.approveCond',width:100,render: transferWarnFoul}
            ,{field:'foulCond',title:'RepEntrustedApproval002.foulCond',width:100,render: transferWarnFoul}
            ,{field:'supVal',title:'RepEntrustedApproval002.supVal',width:200}
            ,{field:'supStatAbbr',sortField:'supStat',title:'RepEntrustedApproval002.supStat',width:200}
            ,{field:'uniteSupStatAbbr',sortField:'uniteSupStat',title:'RepEntrustedApproval002.supUniteStat'}
            ]
    });
    
    function transferWarnFoul(data, type, row, meta){
    	var wg="";
    	if(data==''){
    		return wg;
    	}
    	var detailJson = JSON.parse(data);
    	if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
			wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
		}
    	return wg;
    }
    
    /**
     * 单位转换
     */
    function valueConvertUnit(value){
    	if(value==''){
    		return value;
    	}
    	var retStr;
    	var supUnitArr = JSON.parse(SUP_UNIT);
    	$.each(supUnitArr,function(i,v){
    		if(v.id==value){
    			retStr=v.text;
    			return false;
    		}
    	})
    	return retStr;
    };
    
    function onSaveOrSubmitAction(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	debugger;
    	var object = $.serializeToObject('#submitForm', false);
    	if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }
        
        object.selectedItem = selArr;
        object.pageId = "RepEntrustedApproval002";
        object.pageModel = pageModel;
        object.operateType = operateType;
        if(operateType === 'submit') {
        	WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/entrustedapproval/RepEntrustedApproval002/submit', object, "RepEntrustedApproval002_btn_submit");
        }
    };
    
    function transintRateFoul(assetRate1){
    	var wg = assetRate1;
    	if(wg!=null&&wg!=''){
    		wg = String((wg*100).toFixed(2));
    		return wg;
    	}else{
    		return wg;
    	}
    	
    }
    
    var init = function(){
    	debugger;
    	
    	if(entity&&entity.selectedItem){
    		setTimeout(function(){
    		table.reload({data:entity.selectedItem})
    		},1000);
    	}
    	else{
    		var assetRate1 = $("#assetRate1").val();
        	$("#assetRate").val(transintRateFoul(assetRate1));
    		table.reload();
    	}
    }
   
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
    	
    	// 绑定按钮事件
        $('#RepEntrustedApproval002_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
    });
}();



