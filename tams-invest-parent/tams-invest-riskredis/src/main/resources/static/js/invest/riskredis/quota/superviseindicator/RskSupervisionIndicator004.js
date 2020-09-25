
(function() {
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
	function  treeHtml(type,id){
		var parenIndVal;
		if(typeof $("#rskSupervisionIndId") !== 'undefined'&& $("#rskSupervisionIndId")){
			parenIndVal=$("#rskSupervisionIndId").val();
		}
		var divStr='';
		divStr+='<div class="layui-input" style="border: 1px solid #dbdbdb; scroll-y: auto; scroll-x: hidden; height: 285px; overflow-y: scroll;margin-left: 0px;margin-right: 50px;" id="'+id+'" url="' + getAllRelationUrl + '?type='+type+'&indId='+parenIndVal+'" >';
    	divStr+='</div>';
    	return divStr;
	}
	
	//封装保存逻辑方法并开放出去
	function  packageSave(){
		 var proLevel='01';
		 $("input[name='rskPortfolioIndicatorDto.portfolioLevel']").each(function(){
			 if($(this).is(':checked')){
				 proLevel=$(this).val();
			 }
		 })
		 var treeObj = $.fn.zTree.getZTreeObj("treeDataSingle_tree");             
         var nodes = treeObj.getCheckedNodes();
         var selectedItem = [];
         $.each(nodes,function(i,v){
        	 if(proLevel=='01'){
  	        	if(v.level==1){
  	        		selectedItem.push({'portfolioId':v.id})
  	        	}
          	 }
          	 else{
          		 selectedItem.push({'portfolioId':v.id})
          	 }
         })
         return selectedItem;
	}
	function check004Func(){
		 var treeObj = $.fn.zTree.getZTreeObj("treeDataSingle_tree");             
         var nodes = treeObj.getCheckedNodes();
         /*if($('input[name="rskPortfolioIndicatorDto.startDate"]').val()==''){
    		 Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.startDate")));
    		 return false;
    	 }*/
    	 if($('input[name="rskPortfolioIndicatorDto.endDate"]').val()!=''){
    		if(((new Date($('input[name="rskPortfolioIndicatorDto.startDate"]').val().replace(/-/g,"\/"))) > (new Date($('input[name="rskPortfolioIndicatorDto.endDate"]').val().replace(/-/g,"\/"))))){
    			 Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0003',[JoyinUtil.getLabel("RskSupervisionIndicator002.startDate"),JoyinUtil.getLabel("RskSupervisionIndicator002.endDate")]));
        		 return false;
    		}
    	 }
         if(nodes.length!=0){
        	 var proLevel='01';
    		 $("input[name='rskPortfolioIndicatorDto.portfolioLevel']").each(function(){
    			 if($(this).is(':checked')){
    				 proLevel=$(this).val();
    			 }
    		 })
    		 if(proLevel=='01'){
	        	 //var parentNode=nodes.filter(item => item.level===0);
    			 var parentNode=_.filter(nodes,function(item){return item.level===0} );
	        	 for(var j=0;j<parentNode.length;j++){
	        		//var selParNode = nodes.filter(item => item.pId==parentNode[j].id);
	        		 var selParNode =_.filter(nodes,function(item){return item.pId==parentNode[j].id} ); 
	        		 if(selParNode.length==0){
	        			 Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0017',JoyinUtil.getLabel("RskSupervisionPortfPoint001.portfolio")));
	             		 return false;
	        		 }
	        	 }
    		}
    		 if($('input[name="rskPortfolioIndicatorDto.startDate"]').val()==''){
    		 Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.startDate")));
    		 return false;
    		 }else{
    			 var startDate = $('input[name="rskPortfolioIndicatorDto.startDate"]').val();
    			 if(startDate<SYS_DATE){
    				 Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0074',JoyinUtil.getLabel("RskSupervisionIndicator002.startDate")));
    	    		 return false;
    			 }
    		 }
    		 
         }
         return true;
	}
	window.RskSupervisionIndicator004={
			packageSave:packageSave,
			check004Func:check004Func
	}
	
	  //add by 裴家俊  2019-02-28  增加组合查询条件----
	function  treeHtmlForPortf(type,id){
		var divStr='';
		divStr+='<div class="layui-input" style="border: 1px solid #dbdbdb; scroll-y: auto; scroll-x: hidden; height: 250px; overflow-y: scroll;margin-left: 0px;margin-right: 50px;" id="'+id
		divStr+='" url="' + getAllRelationUrl + '?type='+type+'&portfName='+$("#portfolioSingleName").val()+'&profitType='+$("#profitType").val()+'&issueMode='+$("#issueMode").val()+'&raiseType='+$("#raiseType").val()+'&investType='+$("#investType").val()+'&portfolioType='+$("#portfolioType").val()+'&saleObject='+$("#saleObject").val()+'&isComplianceProd='+$("#isComplianceProd").val()+'" >';
    	divStr+='</div>';
    	return divStr;
	}
	
	//add by 裴家俊  2019-02-27--组合多条件查询--
	function clearBasicCondition(){
		$("#portfolioSingleName").val('');
		$("#profitType").val('').trigger('change');
		$("#issueMode").val('').trigger('change');
		$("#raiseType").val('').trigger('change');
		$("#investType").val('').trigger('change');
		$("#portfolioType").val('').trigger('change');
		$("#saleObject").val('').trigger('change');
		$("#isComplianceProd").val('').trigger('change');
	}
	
    /**
     * 画面加载完成
     */
    $(function() {    	
    	//初始化，产生树结构
    	if((pageModel == PAGE_MODEL_EDIT&&$("#startWfType").val()=='modify')||(pageModel == PAGE_MODEL_REFERENCE&&$("#startWfType").val()!='new')){
    		$("div#secStepDiv").find(".stepbar").remove();
    		$("div#secStepDiv").append($("div#threeStepDiv").find(".stepbar"));
    		$("div#threeStepDiv").hide();
    		$(".pro-module-step").find('li').eq(2).hide();
    		$("div#secStepDiv").find('.prevStep').data('step',$("div#secStepDiv").find('.prevStep').data('step')-1)
    	}
    	else{
    		$("div#RskSupervisionIndicator004Div").show();
    		var type= $("#sortType").val();
    		var divTree=treeHtml(type,"treeDataSingle");
    		$("#singleTree").append(divTree);
    		searchTreeSingle();
    	}
		
    	//组合包名称查询
		$("div.input-btn-append a").click(function(){
			var parenIndVal;
			if(typeof $("#rskSupervisionIndId") !== 'undefined'&& $("#rskSupervisionIndId")){
				parenIndVal=$("#rskSupervisionIndId").val();
			}
			var bagName=$("#portfolioBagName").val();
	    	$("#treeDataSingle").attr('url',getPortfolioBagUrl+'?bagName='+bagName+'&indId='+parenIndVal);
//	    	var divTree=treeHtml(type,"treeDataSingle");
//	    	$("#singleTree").append(divTree);
	    	searchTreeSingle();
		})
		
		 //分类维度联动事件
		$("#sortType").change(function(){
			var type=$(this).val();
			//add by 裴家俊  2019-02-27--组合多条件查询--
			clearBasicCondition();
	    	$("#singleTree").empty();	
	    	var divTree=treeHtml(type,"treeDataSingle");
	    	$("#singleTree").append(divTree);
	    	searchTreeSingle();
		})
		
		//-------add by liqianqian 20190909------指标管理新建组合成份筛选---start---
		$("#selectorAtag").click(function(){
			if($("#selectorConditionFilter").css("display")=="none"){
				clearBasicCondition();
				$("#selectorConditionFilter").show();
			}
			else{
				clearBasicCondition();
				$("#selectorConditionFilter").hide();
			}
		})
		//-------add by liqianqian 20190909------指标管理新建组合成份筛选-----end----
		
		/*//默认选择第一个（单组合）
		 $("input[name='rskPortfolioIndicatorDto.portfolioLevel'][value='01']").attr('checked','true');
		 layui.form.render('radio')*/
		 
			//默认选择第一个（单组合）
	   	 if(entity!=null&&entity.rskPortfolioIndicatorDto!=null&&entity.rskPortfolioIndicatorDto.portfolioLevel!=null){
	   		 $("input[name='rskPortfolioIndicatorDto.portfolioLevel'][value='"+entity.rskPortfolioIndicatorDto.portfolioLevel+"']").attr('checked','true');
	   		 layui.form.render('radio');
	   		 var data={};
	   		 data.value=entity.rskPortfolioIndicatorDto.portfolioLevel;
			$("#singleTree").empty();	
			var divTree=treeHtml(type,"treeDataSingle");
			$("#singleTree").append(divTree);
			portfLevelChange(data)
	   	 }
	   	 else{
	   		 $("input[name='rskPortfolioIndicatorDto.portfolioLevel'][value='01']").attr('checked','true');
	   		 layui.form.render('radio')
	   		var type= $("#sortType").val();
	   		$("#singleTree").empty();	
			var divTree=treeHtml(type,"treeDataSingle");
			$("#singleTree").append(divTree);
			searchTreeSingle();
	   	 }
		 
		layui.form.on('radio(portfolioLevel)',function(data){
			portfLevelChange(data);
		})	
	    function portfLevelChange(data){
			if(data.value==PORTFOLIO_LEVEL_01){
				$("#singlePortfolioDiv").show();
				if(entity!=null&&entity.singlePortfCond!=null){
						$("#sortType").val(entity.singlePortfCond);
				}
				else{
					$("#sortType").val(CLASSIFY_DIMENSION_01);
				}
				$("#portfolioBagNameDiv").hide();
				$("#sortType").trigger('change')
			}
			else{
				$("#portfolioBagNameDiv").show();
				$("#portfolioBagName").val("");
				$("#singlePortfolioDiv").hide();
				$("div.input-btn-append a").click();
			}
		}
		
		//add by liqianqian  2019-09-10--组合多条件查询--
		 $("#RskSupervisionIndicator004_btn_query").click(function(){
	        	var type=$("#sortType").val();
				if(type==""){
					return;
				}
		    	$("#singleTree").empty();	
		    	var divTree=treeHtmlForPortf(type,"treeDataSingle");
		    	$("#singleTree").append(divTree);
		    	searchTreeSingle();
	        })
	        $("#RskSupervisionIndicator004_btn_close").click(function(){
	    	   clearBasicCondition();
	    	   $("#selectorConditionFilter").hide();
	      })
	        
    });
}());