(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	workflowId: workflowId,
        	isTodoList: isTodoList,
        	seqno: $('#seqno').val()
        }
    };

		
		 /**
	     * 定义数据表格
	     */
	    var table=$("#dataTable").joyintable({
	        ajax: {
	            url: JoyinUtil.getRealPath('invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/getTreeCeOrgQuote'),
	            type: 'POST',
            	 data: function() {
                     return getSearchParams();
                 }
	        },
	        data:[],
	        checkbox:false,
	        page:false,
	        tree:true,
	        treeId: 'creditQuoteTypeCode',
	        treeUpId: 'upperCreditQuoteTypeCode',
	        treeShowName: 'creditQuoteTypeValue',
	        lazyload:true,
	        height:249,
	        cols:[
	             {field:'creditQuoteTypeCode', width:150, visible: false}
	            ,{field:'upperCreditQuoteTypeCode', width:150, visible: false}
	            ,{field:'seqno', width:150, visible: false}
	            ,{field:'creditMstId', width:150, visible: false}
	            ,{field:'creditAmtInit', width:150, visible: false}
	            ,{field:'creditQuoteTypeValue',sortable:false, width:550, title: 'CePreOccupancyManagement002.creditQuoteType'}
	            ,{field:'creditAmt', width:150, sortable:false,title: 'CePreOccupancyManagement002.creditAmt',edit: true,type: 'number'}
	        ],
	       
	        editCell : function(value, data, field, cell, column){
	          		var rows = table.getAllRows();
	              	var creditAmtArray = rows.map(function(i) {return i.creditAmt;});
	              	var totalAmt=$("#creditAmt").val();
	              	if(totalAmt==null||totalAmt==''){
	              		JoyinUtil.showMessage("请填写预占用总额度", 'error', function() {
              				//cell.focus();
              				if(typeof data.creditAmtInit!="undefined")
              					data.creditAmt=data.creditAmtInit;
              				else
              					data.creditAmt=null;
              				table.renderRow(data.LAY_TABLE_INDEX, data);
              				table.reload({data:table.getAllRows(), url:""});
              				return;
	              		});
	              	}
	              	if(null!=totalAmt&&totalAmt){
	              		if(JoyinMath.doMinus(value,totalAmt,8)>0){
	              			JoyinUtil.showMessage("分项额度不能超过预占用总额度", 'error', function() {
	              				//cell.focus();
	              				if(typeof data.creditAmtInit!="undefined")
	              					data.creditAmt=data.creditAmtInit;
	              				else
	              					data.creditAmt=0.00;
	              				table.renderRow(data.LAY_TABLE_INDEX, data);
	              				table.reload({data:table.getAllRows(), url:""});
	              				return;
		              		});
	              			
	              		}
	              	}
	                if(data.parentId!='root'){
	                	var pArray=rows.filter(item => item.id===data.parentId)
	                	if(pArray.length>0){
		                	if(null!=pArray[0].creditAmt&&typeof pArray[0].creditAmt!="undefined"){
			                	if(JoyinMath.doMinus(value,pArray[0].creditAmt,8)>0){
			                		JoyinUtil.showMessage("分项明细额度不能超过分项额度", 'error', function() {
			                			if(typeof data.creditAmtInit!="undefined")
			              					data.creditAmt=data.creditAmtInit;
			              				else
			              					data.creditAmt=0.00;
			                			table.renderRow(data.LAY_TABLE_INDEX, data);
			              				table.reload({data:table.getAllRows(), url:""});
			              				//$('#dataTable').data('joyintable').reload({data:$('#dataTable').data('joyintable').getAllRows(),url:""})
				              		});
			                	}
		                	}
		                	else{
		                		if(JoyinMath.doMinus(value,0,8)>0){
			                		JoyinUtil.showMessage("分项明细额度不能超过分项额度", 'error', function() {
			                			if(typeof data.creditAmtInit!="undefined")
			              					data.creditAmt=data.creditAmtInit;
			              				else
			              					data.creditAmt=0.00;
			                			table.renderRow(data.LAY_TABLE_INDEX, data);
			              				table.reload({data:table.getAllRows(), url:""});
	
				              		});
		                		}
		                	}
	                	}
	                }
	                else{
	                   	var pArray=rows.filter(item => item.parentId===data.id)
	                	if(pArray.length>0){
	                		for(var i=0;i<pArray.length;i++){
			                	if(null!=pArray[i].creditAmt&&typeof pArray[i].creditAmt!="undefined"){
				                	if(JoyinMath.doMinus(pArray[i].creditAmt,value,8)>0){
				                		JoyinUtil.showMessage("分项明细额度不能超过分项额度", 'error', function() {
				                			if(typeof data.creditAmtInit!="undefined")
				              					data.creditAmt=data.creditAmtInit;
				              				else
				              					data.creditAmt=null;
				                			table.renderRow(data.LAY_TABLE_INDEX, data);
				              				table.reload({data:table.getAllRows(), url:""});
					              		});
				                	}
			                	}
	                		}
	                	}
	                }
	          }
	    });
	    
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#cePreOccupancyManagementForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0001';
       /* if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }*/
        object.creditQuoteItem=[];
        var rows=table.getAllRows();
        rows.map(function(v){
        	if(null!=v.creditAmt&&typeof v.creditAmt!="undefined"){
        		//v.creditMstId='0004';
        		object.creditQuoteItem.push(v);
        	}
        })
    	 Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/save', object, "CePreOccupancyManagement002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/preoccupancymanagement/CePreOccupancyManagement002/save', object, "CePreOccupancyManagement002_btn_submit");
		        }
     		});
    	 });   
    };	
	
    //新建或编辑初始化
    function init(){
    	table.load();
    	//修改  指标名称置灰
        if(pageModel!=PAGE_MODEL_NEW&&!workflowId){
            $("select[name='customerId']").attr('disabled',true).trigger('change');
        }
        /*if(creditQuoteItem!=null){
    		//反显列表
        	creditQuoteItem.map(function(v){
				table.layOption.data.push(v);//table.addRow()方法无法用，因init方法与table.joyintable同步加载
				table.reload({data:$('#dataTable').data('joyintable').getAllRows(),url:""});
			 })
    	}*/
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 保存按钮执行事件
        $('#CePreOccupancyManagement002_btn_save').click(function(){
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#CePreOccupancyManagement002_btn_submit').click(function(){
    		onSaveOrSubmitAction('submit');
        });
    });
}());