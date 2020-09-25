/**
 * 压力测试-流动相资产设置
 */


$(function() {
	pageModel = PAGE_MODEL_EDIT;
	
	/**获取当前ifram对象**/
	function getIframeByElement(element){
	    var iframe;
	    $("iframe", window.parent.document).each(function(){
	        if(element.ownerDocument === this.contentWindow.document) {
	            iframe = this;
	        }
	        return !iframe;
	    });
	    return iframe;
	}
	/**解决无法滚动的问题**/
	var iframe=getIframeByElement(document.body);
	var form = document.getElementById("form");
	form.style.height = (iframe.offsetHeight - 17)+"px";
	window.onresize = function() {
	    form.style.height = (iframe.offsetHeight - 17)+"px";
    };
    
	/**
     * 加载树结构
     */
    $('#assetssortOneTree,#assetssortTwoTree,#flowassets1Tree,#flowassets2Tree').joyinTree({
        onTreeLoadSuccess: function () {
            if(pageModel == PAGE_MODEL_REFERENCE) {
                var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    var node = nodes[i];
                    DisabledNode(treeObj, node);
                }
            }
        }
    });
    /**
     * 禁用树节点
     * @param treeObj
     * @param node
     * @returns
     */
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    }
    /**
     * 一级流动性资产-资产分类
     */
    var treeLeftOneSelectNode=function(widget){
    	var asstreeObj = $.fn.zTree.getZTreeObj(widget); 
    	var nodes = asstreeObj.getCheckedNodes(true);
    	
    	var assCodeArr="";
    	var assCodepIdArr="";
    	var assNameArr="";
    	var assAllpId="";
    	var assNoAllpId="";
    	var asspId="";
    	for(var  i=0;i<nodes.length;i++){
    		//得到哪些二级下面的三级全被勾选
    		if(nodes[i].level == 0){
    			if(nodes[i].check_Child_State == 2){
    				assAllpId+=nodes[i].id+",";
    				assCodeArr+=nodes[i].id+",";
    				assCodepIdArr+="E"+",";//无父节点的存E
        			assNameArr+=nodes[i].name+",";
    			}else if(nodes[i].check_Child_State == 1){
    				assNoAllpId+=nodes[i].id+",";
    			}
    		}
    	}
    	for(var  i=0;i<nodes.length;i++){
    		//只选中最下级的
    		if(nodes[i].level == 1){
    			if(assAllpId != ""){
    				var count =0;
    				var assAllArr = assAllpId.split(',');
    				for( var j=0; j<assAllArr.length; j++){
        				if(assAllArr[j] == nodes[i].pId){
        					count++;
        				}
        			}
    				if(count == 0){
    					asspId+=nodes[i].pId+",";
    					assCodeArr+=nodes[i].id+",";
    					assCodepIdArr+=nodes[i].pId+",";
            			assNameArr+=nodes[i].name+",";
    				}
    			}else{
    				asspId+=nodes[i].pId+",";
    				assCodeArr+=nodes[i].id+",";
    				assCodepIdArr+=nodes[i].pId+",";
        			assNameArr+=nodes[i].name+",";
    			}
    			
    			
    		}
    	}
    	
    	var retArr=new Array();
    	if(assCodeArr!=''){
    		assCodeArr=assCodeArr.substring(0,assCodeArr.length-1);//去除逗号
    	}
    	if(assNameArr!=''){
    		assNameArr=assNameArr.substring(0,assNameArr.length-1);//去除逗号
    	}
    	retArr[0]=assCodeArr;
    	retArr[1]=assNameArr;
    	retArr[2]=assAllpId;
    	retArr[3]=asspId;
    	retArr[4]=assCodepIdArr;
    	retArr[5]=assNoAllpId;
    	
    	
    	return retArr;
    }
    
    /**
     * 二级流动性资产-资产分类
     */
    var treeLeftTwoSelectNode=function(widget){
    	var asstreeObj = $.fn.zTree.getZTreeObj(widget); 
    	var nodes = asstreeObj.getCheckedNodes(true);
    	
    	var assCodeArr="";
    	var assNameArr="";
    	for(var  i=0;i<nodes.length;i++){
    		if(nodes[i].level!=0){
    			assCodeArr+=nodes[i].id+",";
    			assNameArr+=nodes[i].name+",";
    		}
    	}
    	var retArr=new Array();
    	if(assCodeArr!=''){
    		assCodeArr=assCodeArr.substring(0,assCodeArr.length-1);//去除逗号
    	}
    	if(assNameArr!=''){
    		assNameArr=assNameArr.substring(0,assNameArr.length-1);//去除逗号
    	}
    	retArr[0]=assCodeArr;
    	retArr[1]=assNameArr;
    	return retArr;
    }
    /**
     * 流动性资产添加点击事件
     * @param butClass:按钮的类
     * @param selctedDiv:被选择的div
     * @param selectedUl:被选择的的ul
     */
    var addFlowAss = function(butClass, selctedDiv, selectedUl){
    	$(butClass).click(function(){
        	var treeArr=treeLeftOneSelectNode(selctedDiv + "_tree");
        	var havedLi=new Array();
        	$(selectedUl).find('li').each(function(){
    			havedLi.push(""+$(this).data('id'));
    		})
        	var treeIdArray=treeArr[0].split(',');
        	var treeArray=treeArr[1].split(',');
        	var assCodpIdArr=treeArr[4].split(',');
        	for (var i = 0; i < treeArray.length; i++){
        		if(treeIdArray[i]==''){
        			return;
        		}
        		if($.inArray(treeIdArray[i], havedLi)>=0){
        			continue;
        		}
        		var trStr = '<li data-id="'+treeIdArray[i]+'" data-name="'+treeArray[i]+'" data-pid="'+assCodpIdArr[i]+'"><input type="checkbox">'+treeArray[i]+'</li>';
        		$(selectedUl).append(trStr);
        	}
            var pIdArray=treeArr[3].split(',');
        	$(selectedUl).find('li').each(function(){
        		for(var k=0;k<pIdArray.length;k++){
        			if($(this).data('id') ==pIdArray[k]){
            			$(this).closest('li').remove();
            		}
        		}
        		
        	})
        	//如果父节点全选，子节点删除
        	var assAllpIdArr = treeArr[2].split(',');
        	var flag =false;
        	$(selectedUl).find('li').each(function(){
        		for(var k=0;k<assAllpIdArr.length;k++){
        			if($(this).data('pid') == assAllpIdArr[k] && $(this).data('pId') != "E"){
            			$(this).closest('li').remove();
            		}
        		}
        		
        	})
        	
        	layui.form.render('checkbox');
    		
        })
        
    };
    /**
     * 流动性资产添加移除事件
     */
    var removeFlowAss = function(btnClass, selectedUl){
    	$(btnClass).click(function(){
        	$(selectedUl).find('input[type="checkbox"]').each(function(){
        		if($(this).is(':checked')){
        			$(this).closest('li').remove();
        		}
        	})
        })
    	
    	
    	
    }
    /**
     * 初始化生成一级流动性资产和二级流动性资产
     */
    var initOneTwoFlowAss = function(){
    	//一级流动性资产
    	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001/getFlowAssets1', {workflowId : workflowId}, function(result) {
    		if(result){
    			for (var i = 0; i < result.length; i++){
            		var trStr = '<li data-id="'+result[i].id+'" data-name="'+result[i].name+'" data-pId="'+result[i].pId+'"><input type="checkbox" name="flowAss1">'+result[i].name+'</li>';
            		$("#treeFlowAssOne").append(trStr);
            		
            	}
        		layui.form.render('checkbox');
    			
    			
    		}
        });
    	
    	//二级流动性资产
    	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001/getFlowAssets2', {workflowId: workflowId}, function(result) {
    		if(result){
    			for (var i = 0; i < result.length; i++){
            		var trStr = '<li data-id="'+result[i].id+'" data-name="'+result[i].name+'" data-pId="'+result[i].pId+'"><input type="checkbox" name="flowAss2">'+result[i].name+'</li>';
            		$("#treeFlowAssTwo").append(trStr);
            		
            	}
        		layui.form.render('checkbox');
    		}
        });
    	
    }
    
    /**
	 * 提交或保存事件
	 * @param operateType
	 * @returns
	 */
	function onSaveOrSubmitAction(operateType) {
        //已选一级流动性资产类型
		var flowAss1Array=[];
		//已选二级流动性资产类型
		var flowAss2Array=[];
        $("#treeFlowAssOne").find('li').each(function(){
        	flowAss1Array.push({'id':$(this).data('id'),'name':$(this).data('name'),'pId':$(this).data('pid')});
        })
        $("#treeFlowAssTwo").find('li').each(function(){
        	flowAss2Array.push({'id':$(this).data('id'),'name':$(this).data('name'),'pId':$(this).data('pid')});
        })
        var object = {};
        object.pageModel = PAGE_MODEL_EDIT;
        object.operateType = operateType;
        object.selectedFlowAss1 = flowAss1Array; 
        object.selectedFlowAss2 = flowAss2Array; 
        object.remark = $('#remark').val(); 
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001/check', object, function(result) {        	
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList('/invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001/save', object, "FlowRiskFlowAssets001_btn_save");
                }
                else {
                    WorkFlow.doSubmitList('/invest/risk/stresstestmanage/flowriskflowassets/FlowRiskFlowAssets001/save', object, "FlowRiskFlowAssets001_btn_submit");
                }
    		});
        });   	    
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//不在工作流中才会注册事件
    	if(workflowId == null){
    		//一级流动性资产加入点击事件
        	addFlowAss(".addFlowAss1", "assetssortOneTree","#treeFlowAssOne");
            //一级流动性资产移除点击事件
        	removeFlowAss(".removeFlowAss1", "#treeFlowAssOne");
        	//二级流动性资产加入点击事件
        	addFlowAss(".addFlowAss2", "assetssortTwoTree","#treeFlowAssTwo");
            //二级流动性资产移除点击事件
        	removeFlowAss(".removeFlowAss2", "#treeFlowAssTwo");
    	}
        //初始化生成一级和二级流动性资产
        initOneTwoFlowAss();
        // 绑定按钮事件
        $('#FlowRiskFlowAssets001_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        // 绑定按钮事件
        $('#FlowRiskFlowAssets001_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
        
        //一级流动性资产全选事件
        layui.form.on('checkbox(haveSelPortOne)', function(data){
            if(data.elem.checked){
            	$("#treeFlowAssOne").find('input[type="checkbox"]').each(function(){
	        		$(this).prop("checked",true);
	        	})
            }
            else {
            	$("#treeFlowAssOne").find('input[type="checkbox"]').each(function(){
	        		$(this).prop('checked',false);
	        	})
            }
            layui.form.render('checkbox');
        });
        //二级流动性资产全选事件
        layui.form.on('checkbox(haveSelPortTwo)', function(data){
            if(data.elem.checked){
            	$("#treeFlowAssTwo").find('input[type="checkbox"]').each(function(){
	        		$(this).prop("checked",true);
	        	})
            }
            else {
            	$("#treeFlowAssTwo").find('input[type="checkbox"]').each(function(){
	        		$(this).prop('checked',false);
	        	})
            }
            layui.form.render('checkbox');
        });
        
        
    });
}());