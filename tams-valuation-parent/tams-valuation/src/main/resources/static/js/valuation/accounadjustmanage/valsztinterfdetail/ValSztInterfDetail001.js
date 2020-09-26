$(function() {
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			fundId : $('#fundId').val(),
			refNo : $.trim($('#refNo').val()),
			checkFlag : $.trim($('#checkFlag').val()),
			checkStatus : $.trim($('#checkStatus').val()),
		}
	};
	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'post',
			data : function(){
				return getSearchParams();
			}
		},
		searchOnInit : false,
		cellMinWidth : 100,
		cols : [
				{field : 'fundId', title : '产品代码',sort : true,width:200}, 
				{field : 'finprodName', title : '产品简称',sort : true,width:200}, 
				{field : 'result', title : '对账结果',sort : true,width:200}, 
				{field : 'checkFlag', title : '对账类型code',sort : true,visible:false,width:200},
				{field : 'checkFlagString', title : '对账类型',sort : true,width:200},
				{field : 'checkStatus', title : '对账状态code',sort : true,visible:false,width:200},
				{field : 'checkStatusString', title : '对账状态',sort : true,width:200},
				{field : 'refNo', title : '关联流水号',sort : true,width:200},
				{field : 'bje', title : '本方金额',type: 'money',sort : true,width:150},
				{field : 'dje', title : '对方金额',type: 'money',sort : true,width:150},
				{field : 'bsl', title : '本方数量',type: 'money',sort : true,width:150},
				{field : 'dsl', title : '对方数量',type: 'money',sort : true,width:150},
				{field : 'dealer', title : '处理人',sort : true,width:150},
				{field : 'time', title : '时间',sort : true,width:150},
				{field : 'bjeO', title : '本方金额一',type: 'money',sort : true,width:150},
				{field : 'djeO', title : '对方金额一',type: 'money',sort : true,width:150},
				{field : 'bjeT', title : '本方金额二',type: 'money',sort : true,width:150},
				{field : 'djeT', title : '对方金额二',type: 'money',sort : true,width:150}
		]
	});
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValSztInterfDetail001_btn_search').click(function() {
        	table.load();
        });
    });
	
}());