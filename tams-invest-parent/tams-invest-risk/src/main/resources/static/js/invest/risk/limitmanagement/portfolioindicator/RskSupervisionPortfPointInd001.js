(function() {
	 function searchTreeSingle() {
	  $('#treeIndPortf').joyinMixTree({
	    	onTreeLoadSuccess: function () {
	            if(pageModel == PAGE_MODEL_REFERENCE) {
	                var treeObj = $.fn.zTree.getZTreeObj("treeIndPortf_tree");
	                var nodes = treeObj.getNodes();
	                for(var i=0; i < nodes.length;i++) {
	                    var node = nodes[i];
	                    DisabledNode(treeObj, node);
	                }
	               
	            }
	            if(pageModel ==PAGE_MODEL_NEW){
	            	$("div.mixtree-div").append('<a class="refreshAicon" ><i class="iconfont icon-refresh"></i>&nbsp;刷新</a>')
	            	
	            	 $.fn.zTree.getZTreeObj("treeIndPortf_tree").setting.callback.onClick=function(event, treeId, treeNode){
	            		 event.stopPropagation();
	            		if(treeNode.level==1){
	            			var bd = $("div.condition-area");
	            			bd.find('#indId').val(treeNode.id);
	            			bd.find('#indName').val(treeNode.name);
	            			$('#RskSupervisionPortfPointMain_btn_search').click();
	            		}
	            	 };//触发函数
	            	 
	            	//绑定组合刷新事件	
            		$("div.mixtree-div").find('a.refreshAicon').click(function(){
            			event.stopPropagation();
            			$("#indType").trigger('change');
            		});
	            }
	        }
	    });
	 }
	 
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	portfolioName: $('#portfolioName').val(),
        	indId: $('#indId').val(),
        	portfolioLevel: $("#portfolioLevel").val()
        }
    };
    /**
     * 定义单组合数据表格
     */
	var table=$("#dataIndTable").joyintable({
        ajax: {
            url:  basePath + "invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPointMain/findPage",
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
            ,{field:'portfolioId', width:150 , visible: false}
            ,{field:'indName', width:250 , title: 'RskSupervisionPortfPointInd001.indName'}
            ,{field:'portfolioLevelStr', sortField:'portfolioLevel',width:150 , title: 'RskSupervisionPortfPointInd001.portfolioLevel'}
            ,{field:'portfolioName', width:250 , title: 'RskSupervisionPortfPointInd001.portfolioOrBagName'}
            ,{field:'startDate',  title: 'RskSupervisionPortfPointInd001.effDate',render: function (data, type, row, meta) {
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
        var object = {selectedItem: [data],treeFlag:"A",indId:$("div.condition-area").find('#indId').val(),operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	        	 WorkFlow.doDeleteList(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/delete', object, 
	                     "RskSupervisionPortfPointInd001_btn_delete","RskSupervisionPortfPoint004",function() {
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
    	var indId="",indName="";
    	var treeObj = $.fn.zTree.getZTreeObj("treeIndPortf_tree");
    	var nodes = treeObj.getSelectedNodes();
    	
    	$.each(nodes,function(i,v){
    		if(v.level==1){
	    		indId=v.id;
	    		indName=v.name;
    		}
    	})
    	if(nodes.length==0||indId==""){
   		 	Joyin.error(JoyinUtil.getMessage("RSK_W_0017",JoyinUtil.getLabel("RskSupervisionPortfPoint001.indicator")));
            return;
    	}
    	Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPointInd002"),
                JoyinUtil.getRealPath("invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPointInd002/init"),
                "70%", "90%",{pageModel : PAGE_MODEL_NEW,indId:indId,indName:indName}
        );
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	//隐藏导出按钮
    	$("#RskSupervisionPortfPointMain_btn_export").hide();
    	//初始化，产生树结构
		var indType= $("#indType").val();
		$("#treeIndPortf").attr('url',getAllRelationUrl+'?type='+indType)
		searchTreeSingle();
		
    	// 绑定按钮事件
        $('#RskSupervisionPortfPointMain_btn_search').click(function() {
            table.load();
            $('#RskSupervisionPortfPointInd001_btn_delete').show();
        });
        $('#RskSupervisionPortfPointInd001_btn_add').click(function() {
            openNew();
        });
        $('#RskSupervisionPortfPointInd001_btn_delete').click(function() {
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, treeFlag:"A",indId:$("div.condition-area").find('#indId').val(),operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                WorkFlow.doDeleteList(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint001/delete', object, 
	                        "RskSupervisionPortfPointInd001_btn_delete","RskSupervisionPortfPoint004",function() {
	                            table.reload();
	                        });
	            });
            });
        });
        //指标类型联动事件
		$("#indType").change(function(){
			var indType=$(this).val();
			$("#treeIndPortf").css('height','470px')
			$("#treeIndPortf").attr('url',getAllRelationUrl+'?type='+indType)
	    	searchTreeSingle();
		})
    	
    });
}());