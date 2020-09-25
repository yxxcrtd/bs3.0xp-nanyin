$(function () {
    /**
     * 获取页面查询参数
     */
    var getSearchParams = function() {
        return {
        	sendDateBegin:$('#sendDate').startDate(), 
        	sendDateEnd:$('#sendDate').endDate(), 
        	sendStatus:$.trim($("#sendStatus").val()),
        	receiveStatus:$.trim($("#receiveStatus").val()),
        	reportCategory:$.trim($("#repType").val()),
        	fileName:$.trim($("#fileName").val()),
        	prodId:$.trim($("#prodId").val()),
        	channelCode:$.trim($("#channelCode").val()),
        	reportId:$("#reportId").val()
        }
    };
    
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
	    	/*url: basePath + 'disclosure/common/ChannelInterface001/ChannelInterface001Search',*/
	    	url:'ChannelInterface001Search',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        minWidth:100,
		checkbox: true,
		cols: [ 
              	{field:'prodId', title: '产品编号',width:120,sort: true,visible:true},
              	{field:'sendStatusStr', title: '发送状态',width:100,sort: true,sortField:"sendStatus"},
		        {field:'sendDateStr', title: '发送日期',width:150,sort: true,visible:true},
                {field:'receiveDateStr', title: '反馈日期',width:150,sort: true,visible:true},
                {field:'receiveStatusStr', title: '反馈状态',width:120, sort: true,sortField:"receiveStatus"},
                {field:'fileName', title: '文件名称',width:200, sort: true},
                {field:'reportCategory', title: '报表类别',width:120, sort: true,visible:false},
                {field:'reportCategoryName', title: '报表类别',width:120, sort: true},
                {field:'reportId', title: '报表编号',width:200,sort: true,visible:true},
                {field:'reportName', title: '报表名称',width:200,sort: true,visible:true},
                {field:'channelCodeStr', title: '披露渠道',width:170, sort: true,sortField:"channelCode"},
                {field:'statrDateStr', title: '开始日期',width:150, sort: true},
                {field:'endDateStr', title: '结束日期',width:150, sort: true},
                {field:'id', title: '发送编号',width:150, sort: true,visible:false},
                {field:'revealId', title: '披露编号',width:150, sort: true,visible:false},
                
		]
	});

	
  //导出
	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    
    //获取反馈信息
    var getReceiveInfo = function() {
    	var object ={};
     	var rowArr = table.getAllCheckedRows(); // 所有选中行数据
     	var ids = [];
     	var param = [];
        if(rowArr == null || rowArr.length ==0) {
     		layer.msg("请至少选中一条数据");
     	}
        
        else{
            	for(var i = 0 ; i<rowArr.length ; i++){
            		if(rowArr[i].sendStatus == '99'){
            			Joyin.error("仅已发送数据可查询反馈信息！");
            			break;
            		}
            		else if(rowArr[i].receiveStatus != null){
            			Joyin.error("数据已有反馈信息!");
            			break;
            		}
            		else {
            			ids[i] = rowArr[i].id;
         			}
         		}
            	
            	// 表格所选数据中发送状态为已发送，并且没有反馈状态执行获取反馈信息操作
         		if(ids.length != 0 && ids.length == rowArr.length){
         			Joyin.confirm("是否获取反馈信息？", function(){

         				//将选中行的数据放入object中
         				object.data = JSON.stringify(rowArr);
                    	Joyin.ajaxJson(basePath + 'disclosure/information/reveal/channelinterface/ChannelInterface001/ChannelInterface001getReceiveInfo', object, function(result) {
                            table.reload();
                        });
         			});
            }
        }
    }
    
    
/*    //获取反馈信息
    var getReceiveInfo = function() {
    	var object ={};
     	var rows = table.getAllCheckedRows(); // 所有选中行数据
     	var ids = [];
     	//var param = [];
     	var arr=new Array();
        if(rows.length<=0) {
     		layer.msg("请至少选中一条数据");
     	}
        
        else{
            	for(var i = 0, row; row = rows[i]; i++){
            		if(row.sendStatus == '99'){
            			Joyin.error("仅已发送数据可查询反馈信息！");
            			break;
            		}
            		else if(row.receiveStatus != null){
            			Joyin.error("数据已有反馈信息!");
            			break;
            		}
            		else {
            			var parms={};
            			
            			parms.id = row.id;
                        parms.prodId=row.prodId;
                        parms.reportId = row.reportId;
                        parms.reportCategory=row.reportCategory;
                        parms.statrDateStr = row.statrDateStr;
                        parms.endDateStr=row.endDateStr;
                        parms.RevealId= row.revealId;
                        
                        ids[i] = rows[i].id;
                        arr.push(parms);
         			}
         		}
            	
            	// 表格所选数据中发送状态为已发送，并且没有反馈状态执行获取反馈信息操作
         		if(ids.length != 0 && ids.length == rows.length){
         			Joyin.confirm("是否获取反馈信息？", function(){

         				object.id = JSON.stringify(arr);//将选中行的数据放入object中

                    	Joyin.ajaxJson(basePath + 'disclosure/information/reveal/channelinterface/ChannelInterface001/ChannelInterface001getReceiveInfo', object, function(result) {
                            table.reload();
                        });
         			});
            }
        }
    }*/
    
    
    
    //重新发送
    var resend = function() {
    	var object ={};
     	var rows = table.getAllCheckedRows(); // 所有选中行数据
     	var ids = [];
     	//var param = [];
     	var arr=new Array();
        if(rows.length<=0) {
     		layer.msg("请至少选中一条数据");
     	}
        
        else{
        	 	for(var i = 0, row; row = rows[i]; i++){
            		if(row.sendStatus != '02'){
            			Joyin.error("仅发送失败数据可进行重新发送!");
            			break;
            		}
            		else {
                        ids[i] = rows[i].id;
            		}
        	 	}
            	
            	// 表格所选数据条数不为零且发送状态为发送失败，才能执行重新发送操作
         		if(ids.length != 0 && ids.length == rows.length){
         			Joyin.confirm("是否重新发送？", function(){
         				
                    	object.data = JSON.stringify(rows);//将选中行的数据放入object中
         				Joyin.ajaxJson(basePath + 'disclosure/information/reveal/channelinterface/ChannelInterface001/ChannelInterface001Resend', object, function(result) {
                            table.reload();
         					});
         				});
         			}
         	}
       }	 


    
    
    
    
    /**
     * 画面加载完成
     */
    $(function(){
    	
    	/*var searchDateStart = $('#searchDateStart').val();
    	var searchDateEnd = $('#searchDateEnd').val();
    	if($('#confromPage').val() == '01'){
    		if(searchDateEnd && searchDateStart) {
    			$('#sendDate').val(searchDateStart + ' - ' + searchDateEnd);
    		}
    		table.load();
    	}*/
    	
    	/**
         * 绑定按钮事件   
         * 查询 
         */
    	$('#ChannelInterface001_btn_search').click(function(){
            //直接加载显示
            table.load();
        });
    	
    	 // 导出
    	$('#ChannelInterface001_btn_export').click(function() {
            exportExcel();
        });
    	
    	//获取反馈信息
    	$('#ChannelInterface001_btn_receiveinfo').click(function(){
    		getReceiveInfo();
        });
    	
    	//重新发送
    	$('#ChannelInterface001_btn_resend').click(function(){
    		resend();
        });
    });
 });   
    
    
	
