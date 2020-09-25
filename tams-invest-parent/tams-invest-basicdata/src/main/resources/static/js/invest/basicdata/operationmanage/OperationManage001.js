/**
 * huwenbin
 */
(function(){
	
	var dataTable2;
	var dataTable1;
    var dataTable;
    var index = 0;
	
    /**
     * 获取查询参数
     */
    var getSearchParams = function (){
    	if(index==0){
    		return {
    			portfolioId : $('#portfolioId').val(),
    			secAcctId:$('#secAcctId').val(),
    			finprodId:$('#finprodId').val(),
        		startCdate:$('#cdate').startDate(),
        		endCdate:$('#cdate').endDate(),
        		yesOrNo:$('#yesOrNo').val(),
        		index:'0'
        		
        	};
    	}else if(index==1){
    		return {
    			portfolioId1 : $('#portfolioId1').val() ,
    			seqno:$('#seqno').val(),
    			acctId:$('#acctId').val(),
        		startCdate1:$('#cdate1').startDate(),
        		endCdate1:$('#cdate1').endDate(),
        		cashType:$('#cashType').val(),
        		ccy:$('#ccy').val(),
        		index:'1'
        		
        	};
    	}else{
    		return {
    			orderId : $('#orderId').val() ,
    			orderType:$('#orderType').val(),
    			bussType:$('#bussType').val(),
        		startSettleDate:$('#settleDate').startDate(),
        		endSettleDate:$('#settleDate').endDate(),
                portfolioId:$('#portfolioId2').val(),
        		ps:$('#ps').val(),
        		index:'2'
        		
        	};
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
    	cols:[
            {field:'portfolioId', width:100, title: 'OperationManage001.portfolioId'}
            ,{field:'secAcctId', width:180, title: function(){return JoyinUtil.getLabel("OperationManage001.secAcctId")+JoyinUtil.getLabel("invest.channelId")+'代码';}()}
            ,{field:'finprodId', width:200, title: 'OperationManage001.finprodId'}
            ,{field:'finprodTypeStr', width:150, sortable:false, title: 'OperationManage001.finprodType'}
            ,{field:'hodingTypeStr', width:100, sortable:false, title: 'OperationManage001.hodingType'}
            ,{field:'yesOrNo', width:100, sortable:false, title: 'OperationManage001.yesOrNo'}
            ,{field:'cdate', width:120, sortable:false, title: 'OperationManage001.cdate'}
            ,{field:'amount', width:150, sortable:false, type:'number', title: 'OperationManage001.amount'}
            ,{field:'faceValue', width:100, sortable:false, title: 'OperationManage001.faceValue'}
            ,{field:'createUser', width:100, sortable:false, title: 'OperationManage001.createUser'}
            ,{field:'createDept', width:100, sortable:false, title: 'OperationManage001.createDept'}
            ,{field:'createTime', width:100, sortable:false, type:'date', title: 'OperationManage001.createTime'}
            ,{field:'updateUser', width:100, sortable:false, title: 'OperationManage001.updateUser'}
            ,{field:'updateTime', width:100, sortable:false, type:'date', title: 'OperationManage001.updateTime'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'], visible:false}
        ]
    });
    
    /**
     * 定义数据表格
     */
    var table1=$("#dataTable1").joyintable({        
    	ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
    	cols:[
            {field:'seqno', width:200, sortable:false, title: 'OperationManage001.seqno'}
            ,{field:'portfolioId', width:150, title: 'OperationManage001.portfolioId'}
            ,{field:'acctId', width:180, title: 'OperationManage001.acctId'}
            ,{field:'hodingTypeStr', width:100, sortable:false, title: 'OperationManage001.cashType'}
            ,{field:'cdate', width:150, sortable:false, type:'date', title: 'OperationManage001.cdate'}
            ,{field:'ccyStr', width:150, sortable:false, title: 'OperationManage001.ccy'}
            ,{field:'amount', width:150, sortable:false, type:'money', title: 'OperationManage001.amount'}
            ,{field:'bussTypeStr', width:150, sortable:false, title: 'OperationManage001.bussType'}
            ,{field:'createUser', width:100, sortable:false, title: 'OperationManage001.createUser'}
            ,{field:'createDept', width:100, sortable:false, title: 'OperationManage001.createDept'}
            ,{field:'createTime', width:120, sortable:false, type:'date', title: 'OperationManage001.createTime'}
            ,{field:'updateUser', width:100, sortable:false, title: 'OperationManage001.updateUser'}
            ,{field:'updateTime', width:120, sortable:false, type:'date', title: 'OperationManage001.updateTime'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'], visible:false}
        ]
    });
    
    /**
     * 定义数据表格
     */
    var table2=$("#dataTable2").joyintable({        
    	ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
    	cols:[
			{field:'orderId', width:150, title: 'OperationManage001.orderId'}
			,{field:'bussTypeStr', sortField:'bussType', width:100, title: 'OperationManage001.bussType'}
            ,{field:'portfolioId', width:100, title: '组合代码'}
            ,{field:'settleDate', width:100, type:'date', title: 'OperationManage001.settleDate',type:'date'}
            ,{field:'tradeId', width:150, sortable:false, title: 'OperationManage001.tradeId'}
            ,{field:'myAcctNo', width:150, sortable:false, title: 'OperationManage001.myAcctNo'}
            ,{field:'psStr', width:100, sortable:false, title: 'OperationManage001.ps'}
            ,{field:'counterAcctNo', width:150, sortable:false, title: 'OperationManage001.counterAcctNo'}
            ,{field:'settleAmt', width:100, type:'money', sortable:false, title: 'OperationManage001.settleAmt'}
            ,{field:'ccyStr', width:120, sortable:false, title: 'OperationManage001.ccy'}
            ,{field:'settleStatusStr', width:120, sortable:false, title: 'OperationManage001.settleStatus'}
            ,{field:'orderStatusStr', width:120, sortable:false, title: 'OperationManage001.orderStatus'}
            ,{field:'payTypeStr', width:120, sortable:false, title: 'OperationManage001.payType'}
            ,{field:'createUser', width:100, sortable:false, title: 'OperationManage001.createUser'}
            ,{field:'createDept', width:100, sortable:false, title: 'OperationManage001.createDept'}
            ,{field:'createTime', width:100, sortable:false, type:'date', title: 'OperationManage001.createTime'}
            ,{field:'updateUser', width:100, sortable:false, title: 'OperationManage001.updateUser'}
            ,{field:'updateTime', width:100, sortable:false, type:'date', title: 'OperationManage001.updateTime'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt'], visible:false}
        ]
    });
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', getSearchParams());
    };
    
    $(function () {
    	//导出
    	$('.btn-export').click(function(){
    		exportExcel();
    		/*if(index==0 || index==undefined){
    			exportExcel();
    		}else if(index==1 || index==undefined){
    			exportExcel();
    		}
    		else{
    			exportExcel1();
    		}*/
    	})
    	
    	//tab切换监听
    	layui.use('element', function(){
    		  var element = layui.element;
    		  //一些事件监听
    		  element.on('tab(docDemoTabBrief)', function(data){
    			index = data.index;
    		  });
    		});
    	
    	//查询按钮
    	$('.btn-search').click(function(){
    		if(index==0){
    			table.load();
    		}else if(index==1){
    			table1.load();
    		}else{
    			table2.load();
    		}
    	});
    });
}());