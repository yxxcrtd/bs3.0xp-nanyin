$(function() {
	//初始化估值日期等于平台日
	$('#valDate').val(sysDate);
	
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			valDate : $('#valDate').val(),
			fundId : $('#fundId').val(),
			xmlStatus : $.trim($('#xmlStatus').val()),
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
				{field : 'serialNo', title : '业务流水号',sort : true,width:200}, 
				{field : 'valDate', title : '估值日期',sort : true,width:150},
				{field : 'interType', title : '接口类型code',sort : true,visible:false,width:200},
				{field : 'interTypeString', title : '接口类型',sort : true,width:150},
				{field : 'xmlStatus', title : '报文状态code',sort : true,visible:false,width:200},
				{field : 'xmlStatusString', title : '报文状态',sort : true,width:150},
				{field : 'checkStatus', title : '对账状态code',sort : true,visible:false,width:200},
				{field : 'checkStatusString', title : '对账状态',sort : true,width:150}
		]
	});
	
	var confirmFunction = function(){
		// 获取勾选的数据
        var rows=table.getAllCheckedRows();
        var prodIds = "";
        var valDate = $('#valDate').val();
        var msg='';
        
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valsztinterfbalance/ValSztInterfBalance001/check', getSearchParams(), function(result) {
    		if(result.data){
    			if(rows.length<=0){//没有选择发送失败的数据默认为全产品发送重新
    				msg = '请确认是否发起对账请求？';
    				
    	        }else{
    	        	for(var i = 0, row; row = rows[i]; i++){
    	        		prodIds+= row.fundId + ",";//产品代码
    	            }
    	        	
    	        	msg = '请确认是否只对当前选择数据发起对账请求？';
    	        	
    	        }
    			
    			Joyin.confirm(msg,function() {
	        		Joyin.ajax('sztRunAs', 
	                        {   
	                            prodIds : prodIds,
	                            valDate : valDate
	                        }, 
	                     function(data) {
	                        var result = JSON.parse(data);
	                        layui.layer.alert("请求发送成功!",function(index){
	                            if(!table.table){
	                                table.load();
	                            }else{
	                                table.reload();
	                            }
	                            layui.layer.close(index);
	                        });
	                    });
	            }, function(){
	            	console.log('取消');
	            });
    		}
    	});
        
	};
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValSztInterfBalance001_btn_search').click(function() {
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valsztinterfbalance/ValSztInterfBalance001/check', getSearchParams(), function(result) {
        		if(!table.table) {
        			table.load();
                } else {
                	table.reload();
                }
        	});
        });
        
        $('#ValSztInterfBalance001_BTN_confirm').click(function() {
        	confirmFunction();
        });
    });
	
}());