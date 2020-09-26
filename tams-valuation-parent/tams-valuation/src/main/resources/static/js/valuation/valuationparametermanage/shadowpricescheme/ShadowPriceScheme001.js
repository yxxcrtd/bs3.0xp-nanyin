$(function () {
//	左侧列表主键： -- 单击时使用
	var schId = null;
	var allLoadFalg = false;// 右侧表格是否加载过
	
	/**
	 * 左侧 - 动态获取：影子定价方案Id -- 单击时使用
	 */
	var getSchemeId = function() {
        return {
        	schId:schId,
        	schName:schName
        }
    };
	
    /**
     * 左侧 - 获取查询参数
     */
    var getSearchKey = function() {
        return {
        	searchKey: $('#searchKey').val()
        }
    };
    
    var getShadowPriceDetial = function() {
    	return {          
            schId:schId
        }
    }
    
	/**
     * 导出
     */
    var exportExcel = function() {
    	if(getSchemeId().schId!=null){
    		 if (shadowPriceDetialTable != null && shadowPriceDetialTable != '') {
    	            var selArr = shadowPriceDetialTable.getAllCheckedRows();
    	            var datas = JSON.stringify(selArr);
    	        }
    	        if (!selArr || selArr.length == 0) {
    	            var paramObj = getShadowPriceDetial();
    	            Joyin.exportExcel('export', paramObj);
    	        } else {
    	            Joyin.exportExcel('export', {
    	                datas : datas
    	            });
    	        }
    	}else{
    		Joyin.alert("请选择左侧的影子方案",2);
    	}
       
    };

    
//    =========================  设置（左侧列表）   =======================
    /**
     * 设置表格：左侧影子定价方案列表
     */
	var shadowPriceTable=$("#shadowPriceTable").joyintable({
        ajax: {
            url: 'findShadowPrice',
            type: 'POST',
            data: function() {
                return getSearchKey();
            }
        },
        
		searchOnInit: false,
		cellMinWidth:100 ,
		click:function(table, data){
			//点击时刷新右侧表格
			if(!allLoadFalg){
				schId=data.schId;
				schName=data.schName;
				document.getElementById("span").innerText ="方案描述:" +data.remark ;
				allLoadTable();
			}else{
				schId=data.schId;
				schName=data.schName;
				document.getElementById("span").innerText ="方案描述:" +data.remark ;
				reloadTable(data.schId);
			}
			
          //	----------------  添加操作图标和绑定事件  ------------------
	      var index=data.LAY_TABLE_INDEX;
	      var code=data.schId;
	      var tt=$('#shadowPriceTable').next().children(".layui-table-box").find(".layui-table-body").eq(0);
	      tt.find("tr").each(function(i){
	          $(this).find('td[data-field="schName"]').each(function(){  // 遍历 tr 的各个 td
	              var _text=$(this).text();
	              if(index==i){
	                  $(this).children("div").nextAll().remove();
//	                  var html = "<div style='float:right'><i class='iconfont icon-edit schName' ></i>" +
//                                  "  <i class='iconfont icon-copy schName' ></i>" +
//                                  "  <i class='iconfont icon-delete schName' ></i>";
	                  //update by yantianyuan 20200723 start
	                  var html = "<div style='float:right'>";
	                  if(purviewElements.indexOf('_edit')!=-1){
	                	  html = html+"<i class='iconfont icon-edit edit schName'></i>";
	                  }
	                  if(purviewElements.indexOf('_manual')!=-1){
	                	  html = html+"<i class='iconfont icon-copy  manual schName'></i>";
	                  }
	                  if(purviewElements.indexOf('_delete')!=-1){
	                	  html = html+"<i class='iconfont icon-delete delete schName'></i>";
	                  }
	                  //update by yantianyuan 20200723 end
	                  // 若不是默认方案，则在后面添加设为默认按钮
	                  if(purviewElements.indexOf('_batch')!=-1 && data.isDefault != 'Y'){
	                      html +="  <i class='iconfont icon-location batch schName' title='设为默认'></i>";
	                  }
	                  $(this).children("div").after(html);
	              }else{
	                  $(this).children("div").nextAll().remove();
	              }
	          });
	      }); 
	      $(".schName.edit.icon-edit").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("修改影子定价方案名称（ "+ "<span style='color:red'> * </span>" + "必填）"),
		                basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/init",
		                "60%", "70%", {pageModel:PAGE_MODEL_EDIT, schId: data.schId, operateType: "edit"},function(){
		                	shadowPriceTable.reload();//加载左侧影子定价方案列表
		                	schId = null;// 重置选择记录
		                	//allClearTable();// 清空右侧表格
		        });
		    });
		    $(".schName.manual.icon-copy").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("复制影子定价方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
        	            basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/init",
        	            "60%", "70%", {pageModel:PAGE_MODEL_NEW, schId: data.schId , operateType: "copy"},function(){
        	            	shadowPriceTable.reload();//加载左侧影子定价方案列表
        	            	schId = null;// 重置选择记录
        	            	//allClearTable();// 清空右侧表格
        	    });
		    });
		    $(".schName.delete.icon-delete").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	var object = {};
		    	object.schId = data.schId;
		    	object.operateType = "delete";
        	    Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/check', object, function(result) {
        	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	        	/*WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteShadowPriceSch', object, "", "ShadowPriceScheme001",function() {
        	        		shadowPriceTable.reload();//加载左侧影子定价方案列表
        	            		schId = null;// 重置选择记录
        	              });*/
        	        	 WorkFlow.doDeleteList(
	    		            		basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteShadowPriceSch', 
		                    		object, 
		                    		"ShadowPriceScheme001_btn_delete", 
		                    		"ShadowPriceScheme001",
		                    		function() {
	    		            		    shadowPriceTable.reload();//加载左侧影子定价方案列表
	    	                            schId = null;// 重置选择记录
		                    		}
		                    	);
        	        });
        	    });
		    });
		    $(".schName.batch.icon-location").click(function (event){
                event.stopPropagation();// 阻止事件冒泡
                var object = {};
                object.schId = data.schId;
                object.operateType = 'modify';
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.default")), function(){
                     WorkFlow.doSubmitList(
                                basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/setDefaultScheme', 
                                object, 
                                "ShadowPriceScheme001_btn_modify", 
                                "ShadowPriceScheme002",
                                function() {
                                    shadowPriceTable.reload();//加载左侧影子定价方案列表
                                    schId = null;// 重置选择记录
                                }
                            );
                });
            });
		},
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看影子定价方案"),
	                basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/init",
	                "60%", "70%", {pageModel:PAGE_MODEL_REFERENCE, schId: data.schId, operateType: "view"},function(){
	        });
        },
        checkbox:false,
		cols: [
			{field:'schName', title: '方案名称', sortable: false,},
			{field:'schId', title: '方案代码', sortable: false,visible:false},
			{field:'remark', title: '方案描述', sortable: false,visible:false}
		],
		done:function(){
			$('#shadowPriceTable').next().find("thead").remove();// 删除表头
			
			var schArr = $('#shadowPriceTable').next().find("td");
			if( schArr != undefined && schArr.length > 0 ){
				schArr[0].click();// 默认选中第一个影子定价方案
			}
		}
	  });
	var indexBind = function() {
    	/** 事件绑定: 影子定价方案-新建 */
    	$("#ShadowPriceScheme001_btn_add_index").click(function(event){
    		event.stopPropagation();// 阻止事件冒泡
            Joyin.dialog(JoyinUtil.getLabel("新建影子定价方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme002/init",
                "60%", "70%", {pageModel:PAGE_MODEL_NEW},function(){                
                	shadowPriceTable.reload();//加载左侧影子定价方案列表
                	schId = null;// 重置选择记录               	
                })
        });
    	
    	/** 带图标的输入框绑定事件:searchKey */
    	$(".iconinput").click(function(){
    		shadowPriceTable.reload();//加载左侧名称列表
    		schId = null;// 重置选择记录
    	});	
    	
    	
	}
	var shadowPriceDetialTable=$("#shadowPriceDetialTable").joyintable({
	      ajax: {
	    	url: 'findShadowPriceDetial',
	        type: 'POST',
	        data: function() {
	            return getShadowPriceDetial();
	        }
	    },
	    dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看影子定价方案明细"),
	                basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/init",
	                "95%", "75%", {pageModel:PAGE_MODEL_REFERENCE, schemeDetailId: data.schemeDetailId,schName:schName, operateType: "view"},function(){
	        });
        },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
            {field:'schemeDetailId', width:150, title: '方案明细代码',visible:false},
            {field:'schemeId', width:150, title: '方案代码',visible:false},
            {field:'secType', width:150, title: '证券品种',visible:false},
            {field:'secTypeFmt', width:150, title: '证券品种',sortField:"secType"},
            {field:'tradeMarket', width:150, title: '交易场所',visible:false},
            {field:'tradeMarketFmt', width:150, title: '交易场所',sortField:"tradeMarket"},
            {field:'secDetailType', width:150, title: '证券细分类',visible:false},
            {field:'secDetailTypeFmt', width:150, title: '证券细分类',sortField:"secDetailType"},
			{field:'valuationPrice', width:150, title: '估值第一市价',visible:false},
			{field:'valuationPriceFmt', width:150, title: '估值第一市价',sortField:"valuationPrice"},
			{field:'valuationPrice2', width:150, title: '估值第二市价',visible:false},
			{field:'valuationPrice2Fmt', width:150, title: '估值第二市价',sortField:"valuationPrice2"},
			{field:'quotaDigit', width:150, title: '行情位数'},
			{field:'quotaDecType', width:160, title: '行情位数处理',visible:false},
			{field:'quotaDecTypeFmt', width:160, title: '行情位数处理',sortField:"quotaDecType"},
			{field:'priceClass', width:160, title: '价格类型',visible:false},
			{field:'priceClassFmt', width:160, title: '价格类型',sortField:"priceClass"},
			{field:'valuationDate', width:150, title: '行情日期',visible:false},
			{field:'valuationDateFmt', width:150, title: '行情日期',sortField:"valuationDate"},
			{field:'vdate', width:150, title: '开始日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
//	        	buttons: [
//	                {text: '&#xe642;',clas: 'edit',title: '修改'},
//	                {clas: 'iconfont icon-copy',title: '复制'},
//	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
				render:function(data,type,row,meta){
					var html= "";
					if(purviewElements.indexOf('_edit')!=-1){
	                	  html = html+"<i class='btn layui-icon edit' title='修改'>&#xe642;</i>";
	                  }
	                  if(purviewElements.indexOf('_manual')!=-1){
	                	  html = html+"<i class='iconfont icon-copy manual' title='复制'></i>";
	                  }
	                  if(purviewElements.indexOf('_delete')!=-1){
	                	  html = html+"<i class='iconfont icon-delete delete' title = '删除'></i>";
	                  }
	                  return html
				}
	        }
		],	
	  });
	
	var typeAssetsBind = function() {
		/**
		 * 绑定事件：明细 - 新建
		 */
		$("#ShadowPriceScheme001_btn_add").click(function(event){	
			if(getSchemeId().schId!=null){
				Joyin.dialog(JoyinUtil.getLabel("新建影子方案明细（ "+ "<span style='color:red'> * </span>" + "必填）"),
			               basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/init",
			               "90%", "75%", {pageModel:PAGE_MODEL_NEW, operateType: "add",schemeId:schId,schName:schName},function(){
			            	   shadowPriceDetialTable.reload();
			       });
			}else{
				Joyin.alert("请选择左侧的方案名称",2);
			}
					
		});
			
		/**
		 * 绑定事件：明细 - 编辑
		 */
		shadowPriceDetialTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑影子定价方案明细（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/init",
		               "90%", "75%", {pageModel:PAGE_MODEL_EDIT, schemeDetailId: data.schemeDetailId, operateType: "edit",schName:schName},function(){
		            	   shadowPriceDetialTable.reload();
		       });
	   });
		
		
		/**
		 * 明细 -复制
		 */
		shadowPriceDetialTable.bind('click', '.manual', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制影子定价方案明细（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme003/init",
		               "90%", "75%", {pageModel:PAGE_MODEL_NEW, schemeDetailId: data.schemeDetailId, schemeId:schId, operateType: "copy",schName:schName},function(){
		            	   shadowPriceDetialTable.reload();
	       });
	   });
		
		/**
		 * 绑定事件：明细 - 删除
		 */
		shadowPriceDetialTable.bind('click', '.delete', function(data, index) {
			var object = {};
	    	object.schemeDetailId = data.schemeDetailId;
	    	object.operateType = "delete";
		   	Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteCheck', object, function(result) {
		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
		         /*  	Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteShadowPriceDetail', object, function(result) {
		           		shadowPriceDetialTable.reload();
		           	});*/
		            WorkFlow.doDeleteList(
		            		basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteShadowPriceDetailBatch', 
                    		object, 
                    		"ShadowPriceScheme001_btn_delete", 
                    		"ShadowPriceScheme001",
                    		function() {
                    			shadowPriceDetialTable.reload();
                    		}
                    	);
		           });
		       });
	   });
		
		/**删除走流程**/
        $('#ShadowPriceScheme001_btn_delete').click(function() {
        	var object = {};
        	var rowArr = shadowPriceDetialTable.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0){
        		Joyin.error("请选择一条记录！");   		
        	}else if(rowArr != null && rowArr.length>1){
    			Joyin.error("请选择一条记录！");
    		}else{
    			object=rowArr[0];
    	    	object.operateType = "delete";
    		   	Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteCheck', object, function(result) {
    		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	    		            WorkFlow.doDeleteList(
	    		            		basePath + 'valuation/valuationparametermanage/shadowpricescheme/ShadowPriceScheme001/deleteShadowPriceDetailBatch', 
		                    		object, 
		                    		"ShadowPriceScheme001_btn_delete", 
		                    		"ShadowPriceScheme001",
		                    		function() {
		                    			shadowPriceDetialTable.reload();
		                    		}
		                    	);
    		           });
    	       });
        	}
        });
        
        /**
         * 导出
         */
        $('#ShadowPriceScheme001_btn_export').click(function() {
            exportExcel();
        });
		
	}
		
	/**
	 * 加载右侧所有表格
	 */
	var allLoadTable = function(){
    	shadowPriceDetialTable.load();//加载表格 -
    	allLoadFalg = true;
	}
	
	/**
	 * 右侧表格重载
	 */
	var reloadTable = function(schId){
		shadowPriceDetialTable.reload();//重载表格 	
		shadowPriceDetialTable.layOption.where.schemeId = schId;		
	}
	
    /**
     * 页面加载完后执行
     */
    $(function() {
    	// 选项卡点击事件
        layui.element.on('tab(tabContainer)', function(obj) {
        	loadTable(obj.index);  // 加载当前表格数据
        });         
         
          var workflowId = $('#workflowId').val();
          if($('#workflowId').val()!=''){
          	 $('.layui-col-md3').hide();
          	// $('.layui-col-md9').requestFullScreen();
      	  }	else{
      		 shadowPriceTable.load();//加载左侧影子定价方案列表  
             indexBind();
             typeAssetsBind();
      	  }
        
  
         // shadowPriceDetialTable.load();
        
    });
});


