$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            valDateBegin: $('#valDate').startDate(),
            valDateEnd: $('#valDate').endDate(),
            productIds:$.trim($('#finprodId').val()), 
            reconcileStatus:$.trim($('#reconcileStatus').val()),
            reconcileResult:$.trim($('#reconcileResult').val()),    
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
		searchOnInit: false,
		cellMinWidth:100 ,
        cols:[
              {field : 'prodId', title : '产品代码',sort : true,width:150}, 
              {field : 'prodName', title : '产品简称',sort : true}, 
              {field : 'valueDate', title : '估值日期',sort : true,width:100}, 
              {field : 'financialShare', title : '产品份额',sort : true,type:'money', unit: 10000,width:100}, 
              {field : 'totalNetProd', title : '产品总净值',sort : true,type:'money', unit: 10000,width:100}, 
              {field : 'netUnitProd', title : '单位净值/万份收益/收益率',sort : true, align:"right", unit: 10000,width:100}, 
              {field : 'reconcileStatus', title : '对账状态code',sort : true,visible:false,width:100}, 
              {field : 'reconcileStatusString', title : '对账状态',sort : true,width:100}, 
              {field : 'reconcileResult', title : '对账结果code',sort : true,visible:false,width:100}, 
              {field : 'reconcileResultString', title : '对账结果',sort : true,width:100},	        
		      {field : 'filedown', title: '文件下载',width:250,		      
		           render: function (data, type, row, meta) {
		        	   var html = "<a href="+basePath+"fileProcess/download2ForMy?prodId=" + row.prodId 
		        	   + "&valueDate="+ row.valueDate 
		        	   + "&valBalFlag=01 >本方文件、</a>";
	                    html += getDownLoadUrl(row.filePath2, "对方文件、");
	                    html += getDownLoadUrl(row.filePath3, "对账结果文件");
	                    return html;
                  }   
		        },
		     {field : 'remark', title : '备注',sort : false,width:100},  
			 {field : 'filePath1', title : '本方文件路径',sort : true,visible:false}, 
			 {field : 'filePath2', title : '对方文件路径',sort : true,visible:false}, 
			 {field : 'filePath3', title : '对账结果文件',sort : true,visible:false}, 
        ]
    });
    
    /**
     * 表格文件下载字段显示
     */
    var getDownLoadUrl = function(path,text){
        var html = "";
        if(path){
            html = "<a href="+basePath+"fileProcess/download2?path=" + path +  ">"+ text + "</a>";
        } else{
        	html = "<a href='javascript:void(0);' onclick='layer.alert(\"文件不存在！\");'>" + text + "</a>";
        }
        
        return html;
    };
    
    /**
     * 对账事件
     */
    var confirmFunction = function(){
    	// 获取勾选的数据
        var rows=table.getAllCheckedRows();
        
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        
        //设置传递参数
        var paArr=new Array();
        
        //数据校验结果标识和提示
        var flag=true;
        var msg='';
        
        //循环进行勾选校验和参数设置
        for(var i = 0, row; row = rows[i]; i++){
        	//只能勾选【对账状态】为【未对账】的数据
        	if(row.reconcileStatus == '1'){//状态为【已对账】状态
                flag=false;
                msg='只能对对账状态为“未对账”的记录进行对账操作，请重新选择！';
                break;
        	}
        	else{
                var parms={};
                parms.finprodId= row.prodId;//产品代码
                parms.finprodName = row.prodName;//产品名称
                parms.valDateString = row.valueDate;//估值日期                
                paArr.push(parms);
        	}	
        }
        
        
        //校验不通过状态
        if(!flag){
            layer.alert(msg);
            return;
        }
        //校验通过状态
        else{
    		Joyin.ajaxJson('doConfirm', paArr, function(result) {
    			table.reload();
    		});
        }

    };
    
    /**
     * 取消对账事件
     */
    var cancleConfirm = function(){
    	// 获取勾选的数据
        var rows=table.getAllCheckedRows();
        
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        
        //设置传递参数
        var paArr=new Array();
        
        //数据校验结果标识和提示
        var flag=true;
        var msg='';
        
        //循环进行勾选校验和参数设置
        for(var i = 0, row; row = rows[i]; i++){
        	//只能勾选【对账状态】为【已对账】的数据
        	if(row.reconcileStatus == '0'){//状态为【未对账】状态
                flag=false;
                msg='只能对对账状态为“已对账”的记录进行取消对账操作，请重新选择！';
                break;
        	}
        	else{
                var parms={};
                parms.finprodId= row.prodId;//产品代码
                parms.finprodName = row.prodName;//产品名称
                parms.valDateString = row.valueDate;//估值日期                
                paArr.push(parms);
        	}	
        }
        
        
        //校验不通过状态
        if(!flag){
            layer.alert(msg);
            return;
        }
        //校验通过状态
        else{
    		Joyin.ajaxJson('doCancle', paArr, function(result) {
    			table.reload();
    		});
        }

    };
    
    // 调整函数
    var adjust = function(){
    	// 获取勾选的数据
        var rows=table.getAllCheckedRows();
        
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        
        //设置传递参数
        var paArr=new Array();
        
        //数据校验结果标识和提示
        var flag=true;
        var msg='';
        
        //循环进行勾选校验和参数设置
        for(var i = 0, row; row = rows[i]; i++){
        	//只能勾选【对账状态】为【已对账】的数据
        	if(row.reconcileStatus == '0'){//状态为【未对账】状态
                flag=false;
                msg='只能对对账状态为“已对账”的记录进行调整操作，请重新选择！';
                break;
        	}
        	else if(row.reconcileResult == '1'){
        	    flag=false;
                msg='只能对对账结果为“对账不一致”的记录进行调整操作，请重新选择！';
                break;
        	}
        	else{
                var parms={};
                parms.finprodId= row.prodId;//产品代码
                parms.finprodName = row.prodName;//产品名称
                parms.valDateString = row.valueDate;//估值日期                
                paArr.push(parms);
        	}	
        }
        
        
        //校验不通过状态
        if(!flag){
            layer.alert(msg);
            return;
        }
        //校验通过状态
        else{
    		Joyin.ajaxJson('doAdjust', paArr, function(result) {
    			table.reload();
    		});
        }
    };

    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ProductValuationReconciliation001_btn_search').unbind('click').click(function() {
            table.load();
        });
        
        // 绑定对账事件
        $('#ProductValuationReconciliation001_BTN_confirm').unbind('click').click(function() {
        	confirmFunction();
        });
        
        // 取消对账事件
        $('#ProductValuationReconciliation001_BTN_cancleConfirm').unbind('click').click(function() {
        	cancleConfirm();
        });
        
        // 调整事件
        $('#ProductValuationReconciliation001_BTN_adjust').unbind('click').click(function() {
        	adjust();
        });

    });
}());