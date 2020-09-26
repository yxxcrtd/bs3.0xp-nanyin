$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId: $.trim($('#booksetId').val()),
            startDate: $.trim($('#booksetDate').startDate()),
        	endDate: $.trim($('#booksetDate').endDate()),
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
        cellMinWidth : 200,
        cols:[
            {field:'booksetId', title: '账套', width:200, sort : true}
            ,{field:'layeringId', title: '分层代码',sort : true}
            ,{field:'happenDate', title: '日期',sort : true}
            ,{field:'feeType', title: '费用类型',sort : true}
            ,{field:'clawbackAmt', title: '回补金额', type: 'money', width:200,sort : true}
            ,{field:'clawbackType', title: '回补方式',sort : true}
            ,{field:'operation', title: '记账凭证', type:'action', width:80, fixed: 'right', buttons: [{text: '&#xe60b;',clas: 'about',title: '查看记账凭证'}]}
        ]
    });
    
    /**
	 * 绑定事件： - 记账凭证
	 */
    table.bind('click', '.about', function(data, index) {
    	Joyin.ajax(
  		  basePath + "valuation/bokclawbackInfo/ValBokClawbackInfoQuery001/countVouchs",
  		  {booksetId:data.booksetId, bookDate: data.happenDate, busiId: data.layeringId},
  		  function(count) {
				if(count == 0){
					layer.open({title : '消息',content : '当前数据没有记账凭证信息！'});
				}else{
			        data.pageModel = PAGE_MODEL_EDIT;
			        Joyin.dialog(JoyinUtil.getLabel("查看记账凭证信息"),
			                basePath + "valuation/bokclawbackInfo/ValBokClawbackInfoQuery002/init",
			                "65%", "65%", {pageModel:PAGE_MODEL_REFERENCE, booksetId:data.booksetId, bookDate: data.happenDate, busiId: data.layeringId},function(){
			        		// table.reload();
			        	 });
				}
	    });
   });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValBokClawbackInfoQuery001_btn_search').click(function() {
            table.load();
        });
    });
}());