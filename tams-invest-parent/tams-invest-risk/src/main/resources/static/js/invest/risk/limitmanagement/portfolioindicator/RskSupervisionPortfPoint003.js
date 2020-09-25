(function() {
	function searchTreeSingle(){
	    $('#treeDataSingle').joyinTree({
	        onTreeLoadSuccess: function () {
	            if(pageModel == PAGE_MODEL_EDIT||pageModel == PAGE_MODEL_REFERENCE) {//编辑或待办处理
	            	var treeObj = $.fn.zTree.getZTreeObj("treeDataSingle_tree");
	                if(entity!=null&&entity.selectedPortfIndItem!=null){
	                	$.each(entity.selectedPortfIndItem,function(i,v){
	                		 var node=treeObj.getNodeByParam("id",v.portfolioId);
	                		 treeObj.checkNode(node, true, true);//指定勾选ID的节点
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
	    
    var table =$("#definePortfTable").joyintable({
       
    	data:[],
        cols:[
              {field:'classifyDimension',sortable : false ,title: 'RskSupervisionPortfPoint003.sortType',editOption:{type:'select',childrenIds:'classifyDimensionVal',id:"classifyDimension",width:340, multiple: false,result: CLASSIFY_DIMENSION},edit:true}
              ,{field:'classifyDimensionVal', sortable : false , title: 'RskSupervisionPortfPoint003.sortTypeValu',editOption:{type:'select',parentIds:'classifyDimension',id:"classifyDimensionVal", multiple: true,result: CLASS_DIMENSION_SOURCE},edit:true}
              ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//删除事件
          ],
          editCell : function(value, data, field, cell, column){
          	if (field === "classifyDimension"){
          		var rows = table.getAllRows();
              	var classifyDimensions = rows.map(function(i) {return i.classifyDimension;});
          		if(classifyDimensions.indexOf(value) != rows.length - 1) {
              		JoyinUtil.showMessage(JoyinUtil.getMessage('RSK_W_0016'), 'error', function() {
              			table.deleteRow(data.LAY_TABLE_INDEX);
              		});
          			return;
          		}
          	}
          },
        searchOnInit: true,
        page:false,
        checkbox:false,
        height:220,
        done:function(){
        	 if(pageModel == PAGE_MODEL_REFERENCE){
        		 $("a.btn-table-add").hide();
        	 }
        },
        addbtn:true
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    var treeLeftSelectNode=function(widget){
    	var protreeObj = $.fn.zTree.getZTreeObj(widget); 
    	var nodes = protreeObj.getCheckedNodes(true);
    	
    	var proCodeArr="";
    	var proNameArr="";
    	for(var  i=0;i<nodes.length;i++){
    		if(nodes[i].level!=0){
    			proCodeArr+=nodes[i].id+",";
    			proNameArr+=nodes[i].name+",";
    		}
    	}
    	var retArr=new Array();
    	if(proCodeArr!=''){
    		proCodeArr=proCodeArr.substring(0,proCodeArr.length-1);//去除逗号
    	}
    	if(proNameArr!=''){
    		proNameArr=proNameArr.substring(0,proNameArr.length-1);//去除逗号
    	}
    	retArr[0]=proCodeArr;
    	retArr[1]=proNameArr;
    	return retArr;
    }
    function init(){
    	//修改  组合包名称置灰
        if(pageModel!=PAGE_MODEL_NEW){
            $("input[name='ptlBagName']").attr('disabled',true);
        }
    	searchTreeSingle();
    	if(entity!=null){
    		var treeArray= entity.rskPortfolioPackageDtlDto.rskPortfolioPackageDtlList;
    		for (var i = 0; i < treeArray.length; i++){
        		var trStr = '<li data-id="'+treeArray[i].portfolioId+'"><input type="checkbox" name="zhibao1">'+treeArray[i].portfolioName+'</li>';
        		$("#treeDepartmentYxuan").append(trStr);
        		
        	}
    		layui.form.render('checkbox');
    		//反显列表
			 entity.rskPortfolioPoolConfigDto.ptlParamPoolConfigList.map(function(v){
				table.layOption.data.push(v);//table.addRow()方法无法用，因init方法与table.joyintable同步加载
				 
			 })
    	}
    	
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
    	//初始化调用
    	init();
        // 保存按钮执行事件
        $('#RskSupervisionPortfPoint003_btn_save').click(function(){
    		var object = $.serializeToObject('#rskSupervisionPortfPoint003Form', true);
	        object.pageModel = pageModel;
	        object.operateType = 'save';
	        var porfMixArray=[];
	        $("#treeDepartmentYxuan").find('li').each(function(){
	        	porfMixArray.push({'portfolioId':$(this).data('id')});
	        })
	        object.rskPortfolioPackageDtlDto={};
	        object.rskPortfolioPoolConfigDto={};
	        object.rskPortfolioPackageDtlDto.rskPortfolioPackageDtlList = porfMixArray;
	    	object.rskPortfolioPoolConfigDto.ptlParamPoolConfigList = table.getAllRows();
	    	object.rskPortfolioPoolConfigDto.ptlParamPoolConfigList.map(function(v) {
	        	if(v.classifyDimensionVal) {
	        		var ptlParamArray=v.classifyDimensionVal.split(',')
	        		var ptlParamArrayN=[];
	        		ptlParamArray.map(function(y){
	        			var i = y.indexOf('.');
    	        		if(i != -1) {
    	        			ptlParamArrayN.push(y.substring(i + 1));
    	        		}
	        		})
	        		v.classifyDimensionVal = ptlParamArrayN.join(',');
	        	}
	        });
            Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/check', object, function (result) {
                Joyin.confirm(result.data, function () {
                	Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/save', object, function(result) {
       	          	 var index = parent.layer.getFrameIndex(window.name);
       	               parent.layer.close(index);
                	});
                });
            });
        	       
        });
        //-----modify by 裴家俊 2019/03/21-----------
        //加入点击事件
        $(".addPortf").click(function(){
        	var treeArr=treeLeftSelectNode("treeDataSingle_tree");
        	var havedLi=new Array();
        	$("#treeDepartmentYxuan").find('li').each(function(){
    			havedLi.push(""+$(this).data('id'));
    		})
        	var treeIdArray=treeArr[0].split(',');
        	var treeArray=treeArr[1].split(',');
        	for (var i = 0; i < treeArray.length; i++){
        		if(treeIdArray[i]==''){
        			return;
        		}
        		if($.inArray(treeIdArray[i], havedLi)>=0){
        			continue;
        		}
        		var trStr = '<li data-id="'+treeIdArray[i]+'"><input type="checkbox" name="zhibao1">'+treeArray[i]+'</li>';
        		$("#treeDepartmentYxuan").append(trStr);
        		/*if($("#treeDepartmentYxuan").find('li').length==0){
        			$("#treeDepartmentYxuan").append(trStr);
        		}
        		else{
        			var flag=true
	        		$("#treeDepartmentYxuan").find('li').each(function(){
	        			if($(this).data('id')==treeIdArray[i]){
	        				flag=false;
	        				return false;
	        			}
	        		})
	        		if(flag){
	        			$("#treeDepartmentYxuan").append(trStr);
	        		}
        		}*/
        		
        	}
        	layui.form.render('checkbox')
        })
        
        //移除点击事件
        $(".removePortf").click(function(){
        	$("#treeDepartmentYxuan").find('input[type="checkbox"]').each(function(){
        		if($(this).is(':checked')){
        			$(this).closest('li').remove();
        		}
        	})
        })
        
        //--add by 裴家俊  2019/03/21----全选-----
		layui.form.on('checkbox(haveSelPortfck)', function(data){
            if(data.elem.checked){
            	$("#treeDepartmentYxuan").find('input[type="checkbox"]').each(function(){
	        		$(this).prop("checked",true);
	        	})
            }
            else {
            	$("#treeDepartmentYxuan").find('input[type="checkbox"]').each(function(){
	        		$(this).prop('checked',false);
	        	})
            }
            layui.form.render('checkbox');
        });
         //分类维度联动事件
		$("#sortType").change(function(){
			clearBasicCondition();
			var type=$(this).val();
			var preUrl=$("#singleTree").find("#treeDataSingle").attr('url').substring(0,$("#singleTree").find("#treeDataSingle").attr('url').indexOf("type="));
			$("#singleTree").find("#treeDataSingle").attr('url',preUrl+"type="+type);
	    	searchTreeSingle();
		})
		
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
		
		//add by 裴家俊  2019-02-27--组合多条件查询--
		 $("#RskSupervisionPortfPoint003_btn_portQuery").click(function(){
	        	var type=$("#sortType").val();
				if(type==""){
					return;
				}
				var preUrl=$("#singleTree").find("#treeDataSingle").attr('url').substring(0,$("#singleTree").find("#treeDataSingle").attr('url').indexOf("type="));
				$("#singleTree").find("#treeDataSingle").attr('url',preUrl+"type="+type+'&portfName='+$("#portfolioSingleName").val()+'&profitType='+$("#profitType").val()+'&issueMode='+$("#issueMode").val()+'&raiseType='+$("#raiseType").val()+'&investType='+$("#investType").val()+'&portfolioType='+$("#portfolioType").val()+'&saleObject='+$("#saleObject").val()+'&isComplianceProd='+$("#isComplianceProd").val());
		    	searchTreeSingle();
	      })
	     
	       $("#RskSupervisionPortfPoint003_btn_portClose").click(function(){
	    	   clearBasicCondition();
	    	   $("#selectorConditionFilter").hide();
	      })
    });
}());