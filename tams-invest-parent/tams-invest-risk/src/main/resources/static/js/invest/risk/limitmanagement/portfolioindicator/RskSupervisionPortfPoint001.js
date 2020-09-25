(function() {
	var navBtnHtml='<div class="layui-table-cell laytable-cell-2-operation" style="float:right;line-height:20px;height:20px;width:50px">'
		navBtnHtml+='<i  style="color:#208FFF" class="btn layui-icon editPortfBag" title="编辑"></i>'
		navBtnHtml+='<i  style="color:#FE6B75" class="btn layui-icon delPortfBag" title="删除"></i>'
		navBtnHtml+='</div>';
	 function searchTreeSingle(portfolioLevel) {
	  $('#treePortfInd').joyinMixTree({
	    	onTreeLoadSuccess: function () {
	            if(pageModel == PAGE_MODEL_REFERENCE) {
	                var treeObj = $.fn.zTree.getZTreeObj("treePortfInd_tree");
	                var nodes = treeObj.getNodes();
	                for(var i=0; i < nodes.length;i++) {
	                    var node = nodes[i];
	                    DisabledNode(treeObj, node);
	                }
	               
	            }
	            if(pageModel ==PAGE_MODEL_NEW){
	            	 if(portfolioLevel=='02'){
	            		$("div.mixtree-div").append('<a class="portBagAddAicon" ><i class="iconfont icon-found"></i><span>&nbsp;新建</span></a>')
	            		var treeObj = $.fn.zTree.getZTreeObj("treePortfInd_tree");
	            		var nodes = treeObj.getNodes();
	                	$("ul#treePortfInd_tree").empty();
	                	for (var i = 0; i < nodes.length; i++){
	                		if(nodes[i].id!=''){
	                			$("ul#treePortfInd_tree").append(makeUiHtml(nodes,i));
	                		}
	              		};
	              		
	            		//绑定组合包新增事件	
	            		$("div.mixtree-div").find('a.portBagAddAicon').click(function(event){
	            			event.stopPropagation();
	            			Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPoint003"),
	            	                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/init"),
	            	                "70%", "90%",{pageModel : PAGE_MODEL_NEW},function(){$("div.input-btn-append a").click();}
	            	        );
	            		});
	              		
	              		
		             }else{
		            	 $("div.mixtree-div").append('<a class="refreshAicon" ><i class="iconfont icon-refresh"></i>&nbsp;刷新</a>')
		            	 
		            	 $.fn.zTree.getZTreeObj("treePortfInd_tree").setting.callback.onClick=function(event, treeId, treeNode){
		            		 event.stopPropagation();
		            		 //---add by jjpei 20190727 清除条件----start-----
							 clearBasicCondition();
							 $("#selectorConditionFilter001").hide();
							 //---add by jjpei 20190727 清除条件----end-----
		            		if(treeNode.level==1){
		            			var bd = $("div.condition-area");
		            			bd.find('#portfolioId').val(treeNode.id);
		            			bd.find('#portfolioName').val(treeNode.name);
		            			$('#RskSupervisionPortfPoint001_btn_search').click();
		            		}
		            	 };//触发函数
		            	 
		            	//绑定组合刷新事件	
	            		$("div.mixtree-div").find('a.refreshAicon').click(function(){
	            			event.stopPropagation();
	            			$("#sortType").trigger('change');
	            		});
		             }
	            }
	        }
	    });
	 }
	 
	 
	//绑定组合包删除事件
		$("div#treePortfInd").on('click','.delPortfBag',function(event){
			event.stopPropagation();
			var jqBtn = $(this);
			var jqLi = jqBtn.closest('li');
			jqLi.siblings().removeClass('active');
			jqLi.addClass('active');
		  	var object = {ptlBagId:jqLi.attr('data-id'), operateType:"deleteBag"};
		    Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/checkDelete', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function () {
            	Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/delete', object, function(result) {
            		$("div.input-btn-append a").click();
            	});
            });
        });
		});
		
	//绑定组合包编辑事件
		$("div#treePortfInd").on('click','.editPortfBag',function(event){
			event.stopPropagation();
			var jqBtn = $(this);
			var jqLi = jqBtn.closest('li');
			jqLi.siblings().removeClass('active');
			jqLi.addClass('active');
			Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPoint003.edit"),
	                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/init"),
	                "70%", "90%",{ptlBagId:jqLi.attr('data-id'),ptlBagName:jqLi.attr('data-name'),pageModel : PAGE_MODEL_EDIT}
					,function(){$("div.input-btn-append a").click();}
	        );
		});
		//---modify by 裴家俊 2019/03/18--------绑定组合包双击事件
		$("div#treePortfInd").on('dblclick','.portBagLi',function(event){
			event.stopPropagation();
			var jqBtn = $(this);
			var jqLi = jqBtn.closest('li');
			jqLi.siblings().removeClass('active');
			jqLi.addClass('active');
			Joyin.dialog("组合包【"+jqLi.attr('data-name')+"】成分查看",
	                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint005/init"),
	                "90%", "90%",{ptlBagId:jqLi.attr('data-id'),ptlBagName:jqLi.attr('data-name')}
					
	        );
			/*Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
	                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint003/init"),
	                "70%", "90%",{ptlBagId:jqLi.attr('data-id'),ptlBagName:jqLi.attr('data-name'),pageModel : PAGE_MODEL_REFERENCE}
					
	        );*/
		});	
	
		
	 function makeUiHtml(obj,i){
			var html = '<li class="portBagLi" data-id="' + obj[i].id + '" data-name="' + obj[i].name + '">';
			html += '<a class="portBagAicon" href="javascript:void(0);">';
			html += navBtnHtml;
			html += '<div class="tree-left-width"><span id="treePortfInd_tree_2_ico" title="" treenode_ico="" class="button ico_docu" style=""></span>' + obj[i].name + '</div>';
			html += '</a></li>';
			return html;
	}
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	indName: $('#indName').val(),
        	portfolioId: $('#portfolioId').val(),
        	portfolioLevel: $("input[name='portfolioLevel']:checked").val()
        }
    };
    /**
     * 定义单组合数据表格
     */
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
        	 data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint004/init",
                    "70%", "90%", data);
        },
        
        cols:[
             {field:'indId', width:150 , visible: false}
            ,{field:'portfolioLevel', width:250 , title: 'RskSupervisionPortfPoint001.portfolioLevel',visible: false}
            ,{field:'indName',  title: 'RskSupervisionPortfPoint001.indName'}
            ,{field:'portfolioName', width:250 , title: 'RskSupervisionPortfPointInd001.portfolioOrBagName'}
            ,{field:'portfolioMix', width:250 , title: 'RskSupervisionPortfPoint001.portfolioMix',visible: false}
            ,{field:'startDate',  width:250 ,title: 'RskSupervisionPortfPoint001.effDate',render: function (data, type, row, meta) {
            	if(typeof row.endDate === 'undefined'){
            		return   new Date(row.startDate).Format('yyyy-MM-dd') +'-';
            	}
            	else{
            		return   new Date(row.startDate).Format('yyyy-MM-dd') +'-'+ new Date(row.endDate).Format('yyyy-MM-dd');
            	}
             }}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    }); 
    
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint004/check', data, function(result) {
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPoint004"),
                basePath + "invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint004/init",
                "70%", "90%",data,function() {
                                    table.reload();
                                });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data],treeFlag:"S", portfolioId:$("div.condition-area").find('#portfolioId').val(),operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	        	 WorkFlow.doDeleteList(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/delete', object, 
	                     "RskSupervisionPortfPoint001_btn_delete","RskSupervisionPortfPoint004",function() {
	                table.reload();
	            });
	        });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
    	var portfolioCode="",portfolioName="";
    	var treeObj = $.fn.zTree.getZTreeObj("treePortfInd_tree");
    	var nodes = treeObj.getSelectedNodes();
    	if($("input[name='portfolioLevel']:checked").val()=='01'){
	    	$.each(nodes,function(i,v){
	    		if(v.level==1){
		    		portfolioCode=v.id;
		    		portfolioName=v.name;
	    		}
	    	})
	    	if(nodes.length==0||portfolioCode==""){
	    		Joyin.error(JoyinUtil.getMessage("RSK_W_0017",JoyinUtil.getLabel("RskSupervisionPortfPoint001.portfolio")));
	             return;
	    	}
    	}
    	else{
    		if($("#treePortfInd_tree").find('li.active').length==0){
    			Joyin.error(JoyinUtil.getMessage("RSK_W_0017",JoyinUtil.getLabel("RskSupervisionPortfPoint001.portfolioBag")));
   	             return;
    		}
    		portfolioCode=$("ul#treePortfInd_tree").find('li.active').data('id');
    		portfolioName=$("ul#treePortfInd_tree").find('li.active').data('name');
    	}
    	Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPoint002"),
                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint002/init"),
                "70%", "90%",{pageModel : PAGE_MODEL_NEW,portfolioId:portfolioCode,portfolioName:portfolioName,portfolioLevel:$("input[name='portfolioLevel']:checked").val()}
        );
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	//隐藏导出按钮
    	$("#RskSupervisionPortfPoint001_btn_export").hide();
    	//初始化，产生树结构
		var sortType= $("#sortType").val();
		$("#treePortfInd").attr('url',getAllRelationUrl+'?type='+sortType)
		searchTreeSingle('01');
		
    	//默认选择第一个（单组合）
		 $("input[name='portfolioLevel'][value='01']").attr('checked','true');
		 layui.form.render('radio')
    	// 绑定按钮事件
        $('#RskSupervisionPortfPoint001_btn_search').click(function() {
            table.load();
        	if($("input[name='portfolioLevel']:checked").val()=='01'){
        		table.showColumns([{field:'portfolioMix', width:250,visible:false}]);
        	}
        	else{
        		table.showColumns([{field:'portfolioMix', width:250,visible:true}]);
        	}
            $('#RskSupervisionPortfPoint001_btn_delete').show();
        });
        $('#RskSupervisionPortfPoint001_btn_add').click(function() {
            openNew();
        });
        $('#RskSupervisionPortfPoint001_btn_delete').click(function() {
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, treeFlag:"S",portfolioId:$("div.condition-area").find('#portfolioId').val(),operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                WorkFlow.doDeleteList(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/delete', object, 
	                        "RskSupervisionPortfPoint001_btn_delete","RskSupervisionPortfPoint004",function() {
	                            table.reload();
	                        });
	            });
            });
        });
        //分类维度联动事件
		$("#sortType").change(function(){
			//---add by jjpei 20190727 清除条件 start-----
			clearBasicCondition();
			$("#selectorConditionFilter001").hide();
			//---add by jjpei 20190727 清除条件 end-----
			var sortType=$(this).val();
			$("#treePortfInd").css('height','470px')
			$("#treePortfInd").attr('url',getAllRelationUrl+'?type='+sortType)
	    	searchTreeSingle('01');
		})
    	layui.form.on('radio(portfolioLevel)',function(data){
    		//---add by jjpei 20190727 清除条件 start-----
			clearBasicCondition();
			$("#selectorConditionFilter001").hide();
			//---add by jjpei 20190727 清除条件 end-----
    		$("#treePortfInd").css('height','470px');
    		$("#portfolioId").val("");
    		$("#portfolioName").val("");
    		$("#indName").val("");
			if(data.value=='01'){
				$("#singlePortfolioDiv").show();
				$("#sortType").val('01');
				$("#portfolioBagNameDiv").hide();
				$("#sortType").trigger('change')//触发分类维度事件
			}
			else{
				$("#portfolioBagNameDiv").show();
				$("#portfolioBagName").val("");
				$("#singlePortfolioDiv").hide();
				$("div.input-btn-append a").click();//触发放大镜查询事件
			}
    		//$('#RskSupervisionPortfPoint001_btn_search').click();
		})	
		//组合包名称查询放大镜事件
		$("div.input-btn-append a").click(function(){
			$("#treePortfInd").css('height','470px');
			var bagName=$("#portfolioBagName").val();
	    	$("#treePortfInd").attr('url',getPortfolioBagUrl+'?bagName='+bagName)
	    	searchTreeSingle('02');
		})
		
		//组合包列表选择单击事件
		$("div#treePortfInd").on('click','li.portBagLi>a',function(event){
			var jqBtn = $(this);
			var jqLi = jqBtn.closest('li');
			jqLi.siblings().removeClass('active');
			jqLi.addClass('active');
			var bd = $("div.condition-area");
			bd.find('#portfolioId').val(jqLi.attr('data-id'));
			bd.find('#portfolioName').val(jqLi.attr('data-name'));
			$('#RskSupervisionPortfPoint001_btn_search').click();
		})

		//----add by jjpei 20190727 增加组合筛选条件---start---
		$("#selectorAtag001").click(function(){
			if($("#selectorConditionFilter001").css("display")=="none"){
				clearBasicCondition();
				$("#selectorConditionFilter001").show();
			}
			else{
				clearBasicCondition();
				$("#selectorConditionFilter001").hide();
			}
		})
		$("#RskSupervisionPortfPoint001_btn_portQuery").click(function(){
			var type=$("#sortType").val();
			if(type==""){
				return;
			}
			$("#treePortfInd").css('height','470px');
			var preUrl=getAllRelationUrl+"?";
			$("#treePortfInd").attr('url',preUrl+"type="+type+'&portfName='+$("#portfolioSingleName").val()+'&profitType='+$("#profitType").val()+'&issueMode='+$("#issueMode").val()+'&raiseType='+$("#raiseType").val()+'&investType='+$("#investType").val()+'&portfolioType='+$("#portfolioType").val()+'&saleObject='+$("#saleObject").val()+'&isComplianceProd='+$("#isComplianceProd").val());
			searchTreeSingle('01');
		})

		$("#RskSupervisionPortfPoint001_btn_portClose").click(function(){
			clearBasicCondition();
			$("#selectorConditionFilter001").hide();
		})

		//----add by jjpei 20190727 增加组合筛选条件---end---
    });
	//----add by jjpei 20190727 增加组合筛选条件清空---start---
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
	//----add by jjpei 20190727 增加组合筛选条件清空---end---
}());