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
	var jqModal = $('#rskSupervisionIndicatorForm');	
	  function searchTreeSingle() {
	        $('#treeDataSingle').joyinTree({
	            onTreeLoadSuccess: function () {
	                if(pageModel == PAGE_MODEL_EDIT||pageModel == PAGE_MODEL_REFERENCE) {//编辑或待办处理
	                	var treeObj = $.fn.zTree.getZTreeObj("treeDataSingle_tree");
	                    if(entity!=null&&entity.selectedPortfIndItem!=null){
	                    	$.each(entity.selectedPortfIndItem,function(i,v){
	                    		 var node=treeObj.getNodeByParam("id",v.portfolioId);
	                    		 treeObj.checkNode(node, true, true);//指定勾选ID的节点
	                    		 //treeObj.selectNode(node,true);//指定选中ID的节点
	                    	})
	                    }
	                    if(pageModel == PAGE_MODEL_REFERENCE){
	                    	var nodes = treeObj.getNodes();
	                    	 $.each(nodes,function(i,v){
	                    		  DisabledNode(treeObj, v);
	                    	 })
             			
             		 	}
	                }
	            }
	        });
	    }
	
	  function DisabledNode(treeObj, node) {
	        treeObj.setChkDisabled(node, true);
	        if(node.isParent) {
	            var children = node.children;
	            for(var i=0; i < children.length; i++) {
	                DisabledNode(treeObj, children[i]);
	            }
	        }
	    };

	  //初始化
		function  treeHtml(id){
			var divStr='';
			divStr+='<div class="layui-input" style="border: 1px solid #dbdbdb; scroll-y: auto; scroll-x: hidden; height: 285px; overflow-y: scroll;margin-left: 0px;margin-right: 50px;" id="'+id+'" url="' + getCeLimitKindUrl +'" >';
	    	divStr+='</div>';
	    	return divStr;
		}
	
		
		 /**
	     * 定义数据表格
	     */
	    var table=$("#dataTable").joyintable({
	        ajax: {
	            url: JoyinUtil.getRealPath('invest/risk/creditmanagement/ceorg/CeOrg002/getTreeCeOrgQuote'),
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
	            ,{field:'creditQuoteTypeValue',sortable:false, width:550, title: 'CeOrg002.creditQuoteType'}
	            ,{field:'creditAmt', width:150, sortable:false,title: 'CeOrg002.creditAmt',edit: true,align:'center',type: 'number',digit:4 }
	        ],
	       
	        editCell : function(value, data, field, cell, column){
	          		var rows = table.getAllRows();
	              	var creditAmtArray = rows.map(function(i) {return i.creditAmt;});
	              	var totalAmt=$("#creditAmt").val();
	              	if(totalAmt==null||totalAmt==''){
	              		JoyinUtil.showMessage("请填写授信总额度", 'error', function() {
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
	              			JoyinUtil.showMessage("分项额度不能超过授信总额度", 'error', function() {
	              				//cell.focus();
	              				if(typeof data.creditAmtInit!="undefined")
	              					data.creditAmt=data.creditAmtInit;
	              				else
	              					data.creditAmt=null;
	              				//data.creditAmt=0.00;
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
			              					data.creditAmt=null;
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
			              					data.creditAmt=null;
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
				              				//$('#dataTable').data('joyintable').reload({data:$('#dataTable').data('joyintable').getAllRows(),url:""})
					              		});
				                	}
			                	}
			                	else{
			                		/*if(JoyinMath.doMinus(value,0,8)>0){
				                		JoyinUtil.showMessage("分项明细额度不能超过分项额度", 'error', function() {
				                			if(typeof data.creditAmtInit!="undefined")
				              					data.creditAmt=data.creditAmtInit;
				              				else
				              					data.creditAmt=null;
				                			table.renderRow(data.LAY_TABLE_INDEX, data);
				              				table.reload({data:table.getAllRows(), url:""});
		
					              		});
			                		}*/
			                	}
	                		}
	                	}
	                }
	          }
	    });
	    
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#ceOrgForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0001';
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        object.creditQuoteItem=[];
        var rows=table.getAllRows();
        rows.map(function(v){
        	// modify by wj 20200428  有时双击会传"" TISHKBS-290
        	if(null!=v.creditAmt && typeof v.creditAmt !="undefined" && v.creditAmt !=''){
        		//v.creditMstId='0004';
        		object.creditQuoteItem.push(v);
        	}
        })
    	 Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceorg/CeOrg002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceorg/CeOrg002/save', object, "CeOrg002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceorg/CeOrg002/save', object, "CeOrg002_btn_submit");
		        }
     		});
    	 });   
    };	
	
    //新建或编辑初始化
    function init(){
    	table.load();
    	//修改  指标名称置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $("select[name='customerId']").attr('disabled',true).trigger('change');
            $("input[name='pflsh']").attr('disabled',true).trigger('change');
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
    	/*var divTree=treeHtml("treeDataSingle");
		$("#singleTree").append(divTree);
		searchTreeSingle();*/
        // 保存按钮执行事件
        $('#CeOrg002_btn_save').click(function(){
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#CeOrg002_btn_submit').click(function(){
    		onSaveOrSubmitAction('submit');
        });
    });
}());