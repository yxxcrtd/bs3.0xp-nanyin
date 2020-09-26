$(function () {
//	左侧列表主键：方案Id -- 单击时使用
	var s_schemeId = null;
	var allLoadFalg = false;// 右侧表格是否加载过
	
	/**
	 * 左侧 - 动态获取：方案Id -- 单击时使用
	 */
	var getSchemeId = function() {
        return {
            schemeId: s_schemeId
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
    
    /**
     *  查询条件 ：一般资产估值
     */
    var getTypeAssetsParams = function() {
    	return {
            schemeId: s_schemeId,
        	secType: $.trim($('#typeAsstes_secType').val()),
        	tradeMarket: $.trim($('#typeAsstes_tradeMarket').val()),
        	secDetailType: $.trim($('#typeAsstes_secDetailType').val()),
        	invAim: $.trim($('#typeAsstes_invAim').val()),
        }
    }
    
    /**
     *  查询条件 ：特殊资产估值
     */
    var getSpecialAssetsParams = function() {
    	return {
            schemeId: s_schemeId,
        	finprodId: $.trim($('#specialAsstes_finprodId').val()),
        	tradeMarket: $.trim($('#specialAsstes_tradeMarket').val()),
        	invAim: $.trim($('#specialAsstes_invAim').val())
        }
    }
    
//    =========================  估值方案设置（左侧列表）   =======================
    /**
     * 设置表格：左侧方案列表
     */
	var schemeTable=$("#schemeTable").joyintable({
        ajax: {
            url: 'findScheme',
            type: 'POST',
            data: function() {
                return getSearchKey();
            }
        },
		searchOnInit: false,
		cellMinWidth:100 ,
		click:function(table, data){
//			TODO
//			$("#specialAsstes_finprodId").attr("data-url", "findFinpord?schemeId="+data.schemeId);
//			右侧Tab刷新
			if(!allLoadFalg){
				s_schemeId = data.schemeId;
				allLoadTable();
			}else{
				s_schemeId = data.schemeId;
				reloadTable(data.schemeId);
			}
			
			tabButtionEnable();
//			----------------  添加操作图标和绑定事件  ------------------
		      var index=data.LAY_TABLE_INDEX;
		      var code=data.schemeId;
			    var tt=$('#schemeTable').next().children(".layui-table-box").find(".layui-table-body").eq(0);
		         tt.find("tr").each(function(i){
		      	    $(this).find('td[data-field="scheme"]').each(function(){  // 遍历 tr 的各个 td
			        	     var _text=$(this).text();
		      	    	if(index==i){
				        	   $(this).children("div").nextAll().remove();
//				        	   var html = "<div style='float:right'><i class='iconfont icon-edit scheme' ></i>" +
//		        			   "  <i class='iconfont icon-copy scheme' ></i>" +
//		        			   "  <i class='iconfont icon-delete scheme' ></i>" ;
				        	   var html = "<div style='float:right'>";
				        		if(purviewElements.indexOf('_edit')!=-1){
				        			html += '<i class="iconfont icon-edit edit scheme"></i>';
				        		}
				        		if(purviewElements.indexOf('_manual')!=-1){
			                        html += '<i class="iconfont icon-copy manual scheme"></i>';
				        		}
				        		if(purviewElements.indexOf('_delete')!=-1){
			                        html += '<i class="iconfont icon-delete delete scheme"></i>';
				        		}
								// 若不是默认方案，则在后面添加设为默认按钮
								if(purviewElements.indexOf('_batch')!=-1 && data.isdefault != 'Y'){
									html +="  <i class='iconfont icon-location batch scheme' title='设为默认'></i>";
								}
								$(this).children("div").after(html);
			        	     }else{
					           $(this).children("div").nextAll().remove();
		      	         }
		      	    });
	        }); 
		    $(".scheme.edit.icon-edit").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("编辑估值方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
		                basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/init",
		                "65%", "85%", {pageModel:PAGE_MODEL_EDIT, schemeId: data.schemeId, operateType: "edit"},function(){
		                	$("#typeAssets-title").click();//右侧 tab 还原
		                	schemeTable.reload();//加载左侧方案列表
		                	s_schemeId = null;// 重置选择记录
		                	allClearTable();// 清空右侧表格
		        });
		    });
		    $(".scheme.manual.icon-copy").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("复制估值方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
        	            basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/init",
        	            "65%", "85%", {pageModel:PAGE_MODEL_NEW, schemeId: data.schemeId , operateType: "copy"},function(){
        	            	$("#typeAssets-title").click();//右侧 tab 还原
        	            	schemeTable.reload();//加载左侧方案列表
        	            	s_schemeId = null;// 重置选择记录
        	            	allClearTable();// 清空右侧表格
        	    });
		    });
		    $(".scheme.delete.icon-delete").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	var object = {};
		    	object.schemeId = data.schemeId;
		    	object.operateType = "deleteScheme";
        	    Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/check', object, function(result) {
        	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	        	WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/deleteScheme', object, "ValuationMethodScheme001_btn_deleteScheme", "ValuationMethodScheme002",function() {
        	        		$("#typeAssets-title").click();//右侧 tab 还原	
        	        		schemeTable.reload();//加载左侧方案列表
        	            		s_schemeId = null;// 重置选择记录
        	            		allClearTable();// 清空右侧表格
        	              });
        	        });
        	    });
		    });
			$(".scheme.batch.icon-location").click(function (event){
				event.stopPropagation();// 阻止事件冒泡
				var object = {};
				object.schemeId = data.schemeId;
				object.schemeName = data.schemeName;
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.default")), function(){
					WorkFlow.doSubmitList(
						basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/setDefaultScheme?operType=modify',
						object,
						"ValuationMethodScheme002_btn_submit",
						"ValuationMethodScheme002",
						function() {
							schemeTable.reload();//加载左侧方案列表
							s_schemeId = null;// 重置选择记录
							allClearTable();// 清空右侧表格
						}
					);
				});
			});
		},
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看估值方案"),
	                basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/init",
	                "65%", "85%", {pageModel:PAGE_MODEL_REFERENCE, schemeId: data.schemeId, operateType: "view"},function(){
	        });
        },
        checkbox:false,
		cols: [
			{field:'schemeId', title: '方案代码', sortable: false, visible:false},
			{field:'scheme', title: '方案', sortable: false}
		],
		done:function(){
			$('#schemeTable').next().find("thead").remove();// 删除表头
			
			var schArr = $('#schemeTable').next().find("td");
//			if( schArr != undefined && schArr.length > 0 ){
//				schArr[0].click();// 默认选中第一个方案
//			}
		}
	  });
	
	/**
	 * 事件绑定：估值方案
	 */
	var schemeBind = function() {
		
    	/** 事件绑定: 估值方案-新建 */
    	$("#ValuationMethodScheme001_btn_add_scheme").click(function(event){
    		event.stopPropagation();// 阻止事件冒泡
            Joyin.dialog(JoyinUtil.getLabel("新建估值方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme002/init",
                "65%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                	$("#typeAssets-title").click();//右侧 tab 还原
                	schemeTable.reload();//加载左侧方案列表
                	s_schemeId = null;// 重置选择记录
                	allClearTable();// 右侧 tab 还原
                })
        });
    	
    	/** 带图标的输入框绑定事件:searchKey */
    	$(".iconinput").click(function(){
    		$("#typeAssets-title").click();//右侧 tab 还原
    		schemeTable.reload();//加载左侧方案列表
    		s_schemeId = null;// 重置选择记录
    		allClearTable();// 右侧 tab 还原
    	});
		
	}
	
//  =========================  一般资产估值（右侧Tab）   =======================	
	
	/**
	 * 一般资产估值
	 */
	var typeAssetsTable=$("#typeAssets-table").joyintable({
	      ajax: {
	    	url: 'findSchemeDetail',
	        type: 'POST',
	        data: function() {
	            return getTypeAssetsParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'schemeDetailId', width:120, title: '方案详情代码' , visible:false},
			{field:'schemeId',  width:120, title: '方案代码' , visible:false},
			{field:'secTypeCode', width:120, title: '证券品种Code', visible:false},
			{field:'secType', width:120,sortField:'secTypeCode', title: '证券品种'},
			{field:'tradeMarketCode', width:120, title: '交易场所Code', visible:false},
			{field:'tradeMarket', width:120, sortField:'tradeMarketCode',title: '交易场所'},
			{field:'secDetailTypeCode', width:120, title: '证券细分类Code', visible:false},
			{field:'secDetailType', width:120, sortField:'secDetailTypeCode', title: '证券细分类'},
			{field:'invAimCode', width:120, title: '投资目的Code', visible:false},
			{field:'invAim', width:120, sortField:'invAimCode',title: '投资目的'},
			{field:'valuationMethodCode', width:120, title: '估值方法Code', visible:false},
			{field:'valuationMethod', width:120,sortField:'valuationMethodCode', title: '估值方法'},
			{field:'valuationPriceCode', width:120, title: '估值市价Code', visible:false},
			{field:'valuationPrice', width:120, sortField:'valuationPriceCode', title: '估值市价'},
			{field:'quotaDigit', width:120, title: '行情位数'},
			{field:'priceTailMode', width:160, title: '行情尾数处理方式'},
	        {field:'priceClass', width:160, title: '价格类型'},
	        {field:'valuationDate', width:120, sortField:'valuationDateCode',title: '行情日期'},
	        {field:'vdate', width:120, title: '开始日'},
//	        {field:'mdate', width:120, title: '结束日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	        	render:function (data, type, row, meta){
	        		var buttons = "";
	        		if(purviewElements.indexOf('_edit')!=-1){
                         buttons += '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
	        		}
	        		if(purviewElements.indexOf('_manual')!=-1){
                        buttons += '<i class="iconfont icon-copy manual" title="复制"></i>';
	        		}
	        		if(purviewElements.indexOf('_delete')!=-1){
                        buttons += '<i class="btn layui-icon delete" title="删除">&#xe640;</i>';
	        		}
	        		return buttons;
	        	}
	        }
		],
		done: function(data){
//			if(data.length<1){
//                layer.alert('没有符合条件的记录！');
//			}
			if(data.length > 0 && data[0].schemeDescription != undefined){
				$("#typeAssets-title-desc").text("方案描述：" + data[0].schemeDescription); // 方案描述
			}else{
				$("#typeAssets-title-desc").text("方案描述："); // 方案描述还原
			}
		},
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看一般资产估值"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, schemeDetailId: data.schemeDetailId, operateType: "view"},function(){
		            	   //typeAssetsTable.reload();
		       });
		}
	  });
	
	/**
	 * 事件绑定：一般资产估值
	 */
	var typeAssetsBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#ValuationMethodScheme003_btn_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_typeAsstesForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#ValuationMethodScheme003_btn_reset").click(function(event){
			reset( '#typeAsstesForm', specialAssetsTable);
		});
		
		/** 事件绑定: 资产估值配置-查询 */
    	$("#ValuationMethodScheme003_btn_search").click(function(event){
    		typeAssetsTable.load();//重载表格 - 资产估值配置
			if(data.length<1){
                layer.alert('没有符合条件的记录！');
			}
        });
		
		/**
		 * 绑定事件：一般资产估值 - 新建
		 */
		$("#ValuationMethodScheme003_btn_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建一般资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, operateType: "add", schemeId: s_schemeId},function(){
		            	   typeAssetsTable.reload();
		       });
		});
		
    	/** 事件绑定: 一般资产估值配置-导出 */
        $('#ValuationMethodScheme003_btn_export').click(function() {
			var paramObj = {};
			var rowArr = typeAssetsTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getTypeAssetsParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].schemeDetailId + ",";
				}
				paramObj.schemeDetailId = keyStr;
			}
	        Joyin.exportExcel('exportTypeAssets', paramObj);
        });
		
		/**
		 * 绑定事件：一般资产估值 - 编辑
		 */
		typeAssetsTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑一般资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, schemeDetailId: data.schemeDetailId, operateType: "edit"},function(){
		            	   typeAssetsTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：一般资产估值 - 复制
		 */
		typeAssetsTable.bind('click', '.manual', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制一般资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, schemeDetailId: data.schemeDetailId, operateType: "copy", schemeId: s_schemeId},function(){
		            	   typeAssetsTable.reload();
		       });
	   });
	   
		/**
		 * 绑定事件：一般资产估值 - 删除
		 */
		typeAssetsTable.bind('click', '.delete', function(data, index) {
			var object = {};
			var listdto=[data];
            object.listValuationMethodSchemeDto = listdto;
//			object.schemeId = data.schemeId;
//	    	object.schemeDetailId = data.schemeDetailId;
	    	object.operateType = "delete";
		   	Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/check', object, function(result) {
		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
		        	   WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/deleteTypeAssets', object, "ValuationMethodScheme003_btn_delete", "ValuationMethodScheme003",function() {
		        		   typeAssetsTable.reload();
       	              });
		           });
		       });
	   });
		
		/**
		 * 绑定事件：一般资产估值 - 批量删除
		 */
        $('#ValuationMethodScheme003_btn_delete').click(function() {
            if(typeAssetsTable!=null && typeAssetsTable!=''){
                var selArr = typeAssetsTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行删除。");
                return;
            }
            
            var object = {};
            object.listValuationMethodSchemeDto = selArr;
            object.data = datas;
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/deleteTypeAssets', object, "ValuationMethodScheme003_btn_delete", "ValuationMethodScheme003",function() {
		        		   typeAssetsTable.reload();
                    });
                });
        });
        });
		
	}
	

//  =========================  特殊资产估值（右侧Tab）   =======================	
	
	/**
	 * 特殊资产估值
	 */
	var specialAssetsTable=$("#specialAssets-table").joyintable({
	      ajax: {
	    	url: 'findSpecialScheme',
	        type: 'POST',
	        data: function() {
	            return getSpecialAssetsParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'schemeDetailId', width:120, title: '方案详情代码', visible:false},
			{field:'schemeId',  width:120, title: '方案代码', visible:false},
			{field:'finprodId',  width:120, title: '资产代码'},
			{field:'finprodName',  width:120, title: '资产名称'},
			{field:'tradeMarketCode', width:120, title: '交易场所Code', visible:false},
			{field:'tradeMarket', width:120, sortField:'tradeMarketCode',title: '交易场所'},
			{field:'invAimCode', width:120, title: '投资目的Code', visible:false},
			{field:'invAim', width:120, sortField:'invAimCode',title: '投资目的'},
			{field:'valuationMethodCode', width:120, title: '估值方法Code', visible:false},
			{field:'valuationMethod', width:120, sortField:'valuationMethodCode',title: '估值方法'},
			{field:'valuationPriceCode', width:120, title: '估值第一市价Code', visible:false},
			{field:'valuationPrice', width:120, sortField:'valuationPriceCode',title: '估值第一市价'},
			{field:'valuationPrice2Code', width:120, title: '估值第二市价Code', visible:false},
			{field:'valuationPrice2', width:120, sortField:'valuationPrice2Code',title: '估值第二市价'},
			{field:'valuationDate', sortField:'valuationDateCode',width:120, title: '行情日期'},
			{field:'quotaDigit', width:120, title: '行情位数'},
			{field:'priceTailMode', width:160, title: '行情尾数处理方式'},
            {field:'priceClass', width:160, title: '价格类型'},
	        {field:'vdate', width:120, title: '开始日'},
	        {field:'mdate', width:120, title: '结束日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
//	        	buttons: [
//	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
//	                {clas: 'iconfont icon-copy',title: '复制'},
//	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
	        	render:function (data, type, row, meta){
	        		var buttons = "";
	        		if(purviewElements.indexOf('_edit')!=-1){
	        			buttons += '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
	        		}
	        		if(purviewElements.indexOf('_manual')!=-1){
                        buttons += '<i class="iconfont icon-copy manual" title="复制"></i>';
	        		}
	        		if(purviewElements.indexOf('_delete')!=-1){
                        buttons += '<i class="btn layui-icon delete" title="删除">&#xe640;</i>';
	        		}
	        		return buttons;
	        	}
	        }
		],
//		done: function(data){
//			if(data.length<1){
//                layer.alert('没有符合条件的记录！');
//			}
//		},
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看特殊资产估值"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme004/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, schemeDetailId: data.schemeDetailId, operateType: "view"},function(){
		            	   //specialAssetsTable.reload();
		       });
		}
	  });
	
	/**
	 * 事件绑定：特殊资产估值
	 */
	var specialAssetsBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#ValuationMethodScheme004_btn_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_specialAsstesForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#ValuationMethodScheme004_btn_reset").click(function(event){
			reset( '#specialAsstesForm', specialAssetsTable);
		});
		
    	/** 事件绑定: 特殊资产估值配置-查询 */
    	$("#ValuationMethodScheme004_btn_search").click(function(event){
    		specialAssetsTable.load();//重载表格 - 特殊资产估值配置
        });
    	
        /** 事件绑定: 特殊资产估值配置-导出 */
        $('#ValuationMethodScheme004_btn_export').click(function() {
        	var paramObj = {};
			var rowArr = specialAssetsTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getSpecialAssetsParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].schemeDetailId + ",";
				}
				paramObj.schemeDetailId = keyStr;
			}
	        Joyin.exportExcel('exportSpecialAssets', paramObj);
        });
        
        
        /** 事件绑定: 特殊资产估值配置-批量删除 */
        $('#ValuationMethodScheme004_btn_delete').click(function() {
            if(specialAssetsTable!=null && specialAssetsTable!=''){
                var selArr = specialAssetsTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行删除。");
                return;
            }
            
            var object = {};
            object.listValuationMethodSchemeDto = selArr;
            object.data = datas;
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/deleteSpecialAssets', object, "ValuationMethodScheme004_btn_delete", "ValuationMethodScheme004",function() {
                    	specialAssetsTable.reload();
                    });
                });
        });
        });
		
		/**
		 * 绑定事件：特殊资产估值 - 新建
		 */
		$("#ValuationMethodScheme004_btn_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建特殊资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme004/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW,  schemeId: s_schemeId, operateType: "add"},function(){
		            	   specialAssetsTable.reload();
		       });
		});
		
		/**
		 * 绑定事件：特殊资产估值 - 编辑
		 */
		specialAssetsTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑特殊资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme004/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, schemeDetailId: data.schemeDetailId, operateType: "edit"},function(){
		            	   specialAssetsTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：特殊资产估值 - 复制
		 */
		specialAssetsTable.bind('click', '.manual', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制特殊资产估值（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme004/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, schemeDetailId: data.schemeDetailId, schemeId: data.schemeId, operateType: "copy"},function(){
		            	   specialAssetsTable.reload();
	       });
	   });
	   
		/**
		 * 绑定事件：特殊资产估值 - 删除
		 */
		specialAssetsTable.bind('click', '.delete', function(data, index) {
			var object = {};
			var listdto=[data];
            object.listValuationMethodSchemeDto = listdto;
//			object.schemeId = data.schemeId;
//	    	object.schemeDetailId = data.schemeDetailId;
	    	object.operateType = "delete";
		   	Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/check', object, function(result) {
	           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	        	   WorkFlow.doDeleteList(basePath + 'valuation/valuationparametermanage/valuationmethodscheme/ValuationMethodScheme001/deleteSpecialAssets', object, "ValuationMethodScheme004_btn_delete", "ValuationMethodScheme004",function() {
	        		   specialAssetsTable.reload();
   	              });
              });
	       });
	   });
	}
	
	
	
//  =========================  右侧Tab 相关   =======================
	
	var loaded = {};// 已加载状态记录
	/**
	 * 根据选项卡索引，动态加载表格
	 */
	var loadTable = function(index){
		 // if(s_schemeId == null){
			// Joyin.error('请在左侧选择一个方案，再进行配置操作！');
		 // }
		 if(s_schemeId != null ){// 右侧方案已选择 且 当前没加载
			 if(!loaded[index]){ // 当前没加载
				 switch(index)
				 {
				 case 0:
					 typeAssetsTable.load();break;//加载表格 - 一般资产估值
				 case 1:
					 specialAssetsTable.load();break;//加载表格 - 特殊资产估值配置
				 }
				 loaded[index] = true;
			 }else{// 当前已加载，避免表格错位（架构组要求重载）
				 switch(index)
				 {
				 case 0:
					 typeAssetsTable.reload();break;//加载表格 - 一般资产估值
				 case 1:
					 specialAssetsTable.reload();break;//加载表格 - 特殊资产估值配置
				 }
			 }
		 }
	}
	

	
	/**
	 * 加载右侧所有表格
	 */
	var allLoadTable = function(){
    	typeAssetsTable.load();//加载表格 - 一般资产估值
    	specialAssetsTable.load();//加载表格 - 特殊资产估值配置
    	allLoadFalg = true;
	}
	
	/**
	 * 右侧表格重载
	 */
	var reloadTable = function(schemeId){
		typeAssetsTable.reload();//重载表格 - 一般资产估值
		specialAssetsTable.reload();//重载表格 - 特殊资产估值配置
		
		typeAssetsTable.layOption.where.schemeId = schemeId;
		specialAssetsTable.layOption.where.schemeId = schemeId;
	}
	
	/**
	 * 清空右侧表格
	 */
	var allClearTable = function(){
		typeAssetsTable.clear();//清空表格 - 一般资产估值
		specialAssetsTable.clear();//清空表格 - 特殊资产估值配置
		
		tabButtionDisable();
//		$("#typeAssets-title-desc").text("方案描述："); // 方案描述还原
	}
	
	/**
	 * 重置 Tab
	 * conditionArea : 条件区
	 * table : 关联的表格
	 */
	var reset = function(conditionArea, table){
		$(conditionArea).find("input").val("");
		$(conditionArea).find("select").val("").trigger("change");// select清空
//		specialAssetsTable.clear();
	}

	/**
	 * 右侧 tab 按钮不可编辑
	 */
	var tabButtionDisable = function(){
    	$(".layui-tab-item").each(function(index){
    		if(index != 0){// 右侧不选择的情况下，除了一般资产估值外，都不可编辑
    			$(this).find("button").addClass("layui-btn-disabled");
    			$(this).find("button").attr("disabled", "disabled");
    		}
    	});
	}
	
	/**
	 * 右侧 tab 按钮可编辑
	 */
	var tabButtionEnable = function(){
		$(".layui-tab-item").find("button").removeClass("layui-btn-disabled");
		$(".layui-tab-item").find("button").removeAttr("disabled");
	}
	
    /**
     * 页面加载完后执行
     */
    $(function() {
    	// 选项卡点击事件
        layui.element.on('tab(tabContainer)', function(obj) {
        	loadTable(obj.index);  // 加载当前表格数据
        });
    	

    	schemeTable.load();//加载左侧方案列表
    	
    	schemeBind(); // 事件绑定 - 估值方案
    	typeAssetsBind(); //事件绑定 - 一般资产估值
    	specialAssetsBind();//事件绑定 - 特殊资产估值
    	
    	tabButtionDisable();
    });
});

/**
 * 不使用此方式：不同域，无法调用
 * 带图标的输入框绑定事件
 * data-eventfunc="searchKey"
 * @returns
 */
//function searchKey(){
//	schemeTable.load();//加载左侧方案列表
//}

