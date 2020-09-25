/**
 * RepTradeApproval001 投资交易
 */
var rea = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var eventId = $('#eventId').val();
        return {
        	eventId : eventId,
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("RepTradeApproval002"),
                basePath + "invest/riskredis/quota/tradeapproval/RepTradeApproval002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW,
            	eventId: data.eventId});
        },
        cols:[
            {field:'eventId',width:100,title: 'RepTradeApproval001.eventId'}
        ]
    });
    
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
	
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var getParams=getSearchParams();
        Joyin.exportExcel('export', getParams);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepTradeApproval001_btn_search').click(function() {
            table.load();
        });
        $('#RepTradeApproval001_btn_export').click(function() {
            exportExcel();
        });
    });
}();


function showSupDetail(eventId,indId,portfId,supdate){
	var data={};
	data.indId=indId;
	data.portfId=portfId;
	data.supDate=supdate;
	data.eventId=eventId;
   Joyin.dialog(JoyinUtil.getLabel("RepTradeApproval002"),
           basePath + "/invest/riskredis/quota/tradeapproval/RepTradeApproval002/init",
           "70%", "90%",data);
};
