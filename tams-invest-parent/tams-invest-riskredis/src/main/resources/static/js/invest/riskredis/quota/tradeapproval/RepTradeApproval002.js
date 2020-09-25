/**
 * RepTradeApproval002 基本信息(投资交易)
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var eventId = $('#eventId').val();
        return {
        	eventId : eventId,
        	workflowId : $('#workflowId').val(),
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
//        ajax: {
//            url: basePath+'invest/riskredis/quota/tradeapproval/RepTradeApproval002/findPage',
//            type: 'POST',
//            data: function() {
//                return getSearchParams();
//            }
//        },
        searchOnInit:true,
        checkbox:false,
        cols:[
             {field:'indName',title:'RepTradeApproval001.indName',width:100}
            ,{field:'indTypeAbbr', title: 'RepTradeApproval001.indType'}
            ,{field:'warnCond', title: 'RepTradeApproval001.warnCond',render: transferWarnFoul}
            ,{field:'approveCond', title: 'RepTradeApproval001.approveCond',render: transferWarnFoul}
            ,{field:'foulCond', title: 'RepTradeApproval001.foulCond',render: transferWarnFoul}
            ,{field:'supVal', title: 'RepTradeApproval001.supVal'}
            ,{field:'supStatAbbr', sortField:'supStat',title: 'RepTradeApproval001.supStat'}
            ,{field:'uniteSupStatAbbr', sortField:'uniteSupStat',title: 'RepTradeApproval001.uniteSupStat'}
        ]
    });
    
    function transferWarnFoul(data, type, row, meta){
    	var lnkNo="";
    	var wg="";var detail = "";
    	if(data==''){
    		return wg;
    	}
    	var detailJson = JSON.parse(data);
		if(detailJson!=null){
			/* 限制型 */
			detail = html2Escape(data);
			if(row.indType.indexOf("LIMIT_")!=-1){
				if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
					wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
				}
				
			}else{
				/* 禁止型 */
				wg = detailJson.threshold_1.value_cn;
				wg=wg.substring(0,wg.length-1);
			}
		}
		return wg;
    }
    /* 普通字符转换成转意符 */
	var html2Escape = function(sHtml){
		return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
	};
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
    function StringToChinese(id,type){
    	var arr=[];
    	var totalIndTypeEnum=JSON.parse(IND_TYPE_TOTAL_ENUM);
    	var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
    	if(null!=totalIndTypeEnum&&null!=arrFobEnum){
    		arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text});
    	}
    	if(arr==[]||arr.length==0)
    		return id;
    	else
    		return arr[0].text;
	};
	
function onSaveOrSubmitAction(operateType) {
		/**
		 * 获取列表参数
		 */
		var object = $.serializeToObject('#submitForm', false);
		if (table != null && table != '') {
			var selArr = table.getAllRows();
		}

		object.selectedItem = selArr;
		object.pageId = "RepTradeApproval002";
		object.pageModel = pageModel;
		object.operateType = operateType;
		if (operateType === 'submit') {
			WorkFlow
					.doSubmitList(
							basePath
									+ 'invest/riskredis/quota/tradeapproval/RepTradeApproval002/submit',
							object, "RepTradeApproval002_btn_submit");
		}
	};

	var init = function() {
		debugger;
		if (entity && entity.selectedItem) {
			setTimeout(function() {
				table.reload({
					data : entity.selectedItem
				})
			}, 1000);
		} else{
    		table.reload();
    	}

	}
	
	function transintRateFoul(assetRate1){
		debugger;
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
        $('#RepTradeApproval002_btn_submit').click(function() {
           onSaveOrSubmitAction('submit');
        });
    });
}();