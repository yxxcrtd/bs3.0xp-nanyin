$(function () {
//	左侧列表主键：方案Id -- 单击时使用
	var s_schId = null;
	var allLoadFalg = false;// 右侧表格是否加载过
	
	// ------------------------ 对于多tab表格的权限控制  --------------------------------------
    // 管理员权限
    var admin_purviewElements = ["_edit", "_export", "_delete", "_search"];
    var isAdmin = false;
    if (admin_purviewElements.sort().toString() === purviewElements.sort().toString()){
        isAdmin = true;
    }
    var allBtn = ['edit', 'copy', 'delt'];


	// 自定义数据表格按钮
	// 框架 bug：权限控制时 只能处理 string类型的按钮。
	// 已知问题：系统管理员无法看到 表格按钮（后台固定了purviewElements）
	$.extend(true, $.fn.joyintable.defaults, {
        // 按钮设置
        buttons: {
            copy: {
            	clas: 'iconfont icon-copy',
                title: '复制'
            }
        }
	});
	
	// --------------------------------------------------------------
	
    /**
     * 左侧 - 获取查询参数
     */
    var getSearchKey = function() {
        return {
        	searchKey: $('#searchKey').val()
        }
    };
    
    /**
     *  查询条件 ：增值税则表
     */
    var getVatTaxParams = function() {
    	return {
            schId: s_schId,
            assetClassify: $.trim($('#vatTax_assetClassify').val()),
        	capiIncomeFeature: $.trim($('#vatTax_capiIncomeFeature').val()),
        	incomeType: $.trim($('#vatTax_incomeType').val()),
        	feeType: $.trim($('#vatTax_feeType').val()),
        	effDateBegin: $.trim($('#vatTax_effDate').startDate()),
        	effDateEnd: $.trim($('#vatTax_effDate').endDate()),
        }
    }
    
    /**
     *  查询条件 ：附加税则表
     */
    var getAdtTaxParams = function() {
    	return {
            schId: s_schId,
            addTaxType: $.trim($('#adtTax_addTaxType').val()),
        	effDateBegin: $.trim($('#adtTax_effDate').startDate()),
        	effDateEnd: $.trim($('#adtTax_effDate').endDate()),
        }
    }
    
    /**
     *  查询条件 ：资产应税信息
     */
    var getTaxableParams = function() {
    	return {
            schId: s_schId,
            assetClassify: $.trim($('#taxable_assetClassify').val()),
            assetId: $.trim($('#taxable_assetId').val()),
            capiIncomeFeature: $.trim($('#taxable_capiIncomeFeature').val()),
            incomeType: $.trim($('#taxable_incomeType').val()),
            taxableSign: $.trim($('#taxable_taxableSign').val()),
        	effDateBegin: $.trim($('#taxable_effDate').startDate()),
        	effDateEnd: $.trim($('#taxable_effDate').endDate()),
        	maintenanceMode: $.trim($('#taxable_maintenanceMode').val()),
        }
    }
    
    /**
     *  查询条件 ：参数设置
     */
    var getConfigParams = function() {
    	return {
            schemeId: s_schId,
            configId: $.trim($('#config_configId').val()),
            schemeType2: $.trim($('#config_schemeType2').val()),
        	effDateBegin: $.trim($('#config_effDate').startDate()),
        	effDateEnd: $.trim($('#config_effDate').endDate()),
        }
    }

    /**
     *  查询条件 ：会计场景计税配置
     */
    var getTaxSchaBokConfigParams = function() {
    	return {
            schemeId: s_schId,
            tableId: $.trim($('#taxSchaBokConfig_tableId').val()),
            vatType: $.trim($('#taxSchaBokConfig_vatType').val())
        }
    };


    
//    =========================  税务方案设置（左侧列表）   =======================
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
		even: false,
		click:function(table, data){
//			右侧Tab刷新
			if(!allLoadFalg){
				s_schId = data.schId;
				allLoadTable();
			}else{
				s_schId = data.schId;
				reloadTable(data.schId);
			}
			
			tabButtionEnable();
//			----------------  添加操作图标和绑定事件  ------------------
	        var index=data.LAY_TABLE_INDEX;
//		    var code=data.schId;
		    var tt=$('#schemeTable').next().children(".layui-table-box").find(".layui-table-body").eq(0);
	         tt.find("tr").each(function(i){
	      	    $(this).find('td[data-field="schName"]').each(function(){  // 遍历 tr 的各个 td
		        	     var _text=$(this).text();
	      	    	if(index==i){
			        	   $(this).children("div").nextAll().remove();
			        	   var html = "<div style='float:right'>";
			        	   if(purviewElements.indexOf("_edit")!=-1){
			        		   html +="<i class='iconfont icon-edit  edit scheme' ></i> ";
			        	   }
			        	   if(purviewElements.indexOf("_manual")!=-1){
			        		   html +="<i class='iconfont icon-copy manual scheme' ></i> ";
			        	   }
			        	   if(purviewElements.indexOf("_delete")!=-1){
			        		   html +="<i class='iconfont icon-delete delete scheme' ></i> ";
			        	   }
//							var html = "<div style='float:right'><i class='iconfont icon-edit scheme' ></i>" +
//			        			   "  <i class='iconfont icon-copy scheme' ></i>" +
//			        			   "  <i class='iconfont icon-delete scheme' ></i>";
							// 若不是默认方案，则在后面添加设为默认按钮
							if(data.isDefault != 'Y' &&purviewElements.indexOf("_batch")!=-1){
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
		    	Joyin.dialog(JoyinUtil.getLabel("编辑税务方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
		                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme002/init",
		                "65%", "85%", {pageModel:PAGE_MODEL_EDIT, schId: data.schId, operateType: "edit"},function(){
		                	schemeTable.reload();//加载左侧方案列表
		                	s_schId = null;// 重置选择记录
		                	// allClearTable();// 清空右侧表格
		                	$("#vatTax-title").click();//右侧 tab 还原
		        });
		    });
		    $(".scheme.manual.icon-copy").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("复制税务方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
        	            basePath + "valuation/taxparametermanage/taxscheme/TaxScheme002/init",
        	            "65%", "85%", {pageModel:PAGE_MODEL_NEW, schId: data.schId , operateType: "copy"},function(){
        	            	schemeTable.reload();//加载左侧方案列表
        	            	s_schId = null;// 重置选择记录
        	            	// allClearTable();// 清空右侧表格
        	            	$("#vatTax-title").click();//右侧 tab 还原
        	    });
		    });
		    $(".scheme.delete.icon-delete").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	var object = {};
		    	object.schId = data.schId;
		    	object.operateType = "deleteScheme";
        	    Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
        	    	object.operateType = "deleteScheme";
        	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	        	WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteScheme', object, "TaxScheme001_btn_deleteScheme", "TaxScheme002",function() {
        	        			schemeTable.reload();//加载左侧方案列表
        	            		s_schId = null;// 重置选择记录
        	            		// allClearTable();// 清空右侧表格
        	            		$("#vatTax-title").click();//右侧 tab 还原	
        	              });
        	        });
        	    });
		    });
			$(".scheme.batch.icon-location").click(function (event){
				event.stopPropagation();// 阻止事件冒泡
				var object = {};
				object.schId = data.schId;
				object.schName = data.schName;
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.default")), function(){
					WorkFlow.doSubmitList(
						basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/setDefaultScheme?operType=modify',
						object,
						"TaxScheme002_btn_submit",
						"TaxScheme002",
						function() {
							schemeTable.reload();//加载左侧方案列表
							s_schId = null;// 重置选择记录
							$("#vatTax-title").click();//右侧 tab 还原
						}
					);
				});
			});
		},
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看税务方案"),
	                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme002/init",
	                "65%", "85%", {pageModel:PAGE_MODEL_REFERENCE, schId: data.schId, operateType: "view"},function(){
	        });
        },
        checkbox:false,
		cols: [
			{field:'schId', title: '方案代码', sortable: false, visible:false},
			{field:'schName', title: '方案名称', sortable: false}
		],
		done:function(){
			$('#schemeTable').next().find("thead").remove();// 删除表头
			
			var schArr = $('#schemeTable').next().find("td");
			if( schArr != undefined && schArr.length > 0 ){
				schArr[0].click();// 默认选中第一个方案
			}
			
		}
	  });
	
	/**
	 * 事件绑定：税务方案
	 */
	var schemeBind = function() {
		
    	/** 事件绑定: 税务方案-新建 */
    	$("#TaxScheme001_btn_add_scheme").click(function(event){
    		event.stopPropagation();// 阻止事件冒泡
            Joyin.dialog(JoyinUtil.getLabel("新建税务方案（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme002/init",
                "65%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                	schemeTable.reload();//加载左侧方案列表
                	s_schId = null;// 重置选择记录
                	allClearTable();// 右侧 tab 还原
                	$("#vatTax-title").click();//右侧 tab 还原
                })
        });
    	
    	/** 带图标的输入框绑定事件:searchKey */
    	$(".iconinput").click(function(){
    		schemeTable.reload();//加载左侧方案列表
    		s_schId = null;// 重置选择记录
    		allClearTable();// 右侧 tab 还原
    		$("#vatTax-title").click();//右侧 tab 还原
    	});
		
	}
	
//  =========================  增值税则表（右侧Tab）   =======================	
	
	var btns_vatTax = [];
	if (!isAdmin){
        if(purviewElements.indexOf('_edit') != -1) {
            btns_vatTax.push('edit');
        }
        if(purviewElements.indexOf('_manual') != -1) {
            btns_vatTax.push('copy');
        }
        if(purviewElements.indexOf('_delete') != -1) {
            btns_vatTax.push('delt');
        }
    } else {
        btns_vatTax = allBtn
    }
	
	/**
	 * 增值税则表
	 */
	var vatTaxTable=$("#vatTax-table").joyintable({
	      ajax: {
	    	url: 'findVatTax',
	        type: 'POST',
	        data: function() {
	            return getVatTaxParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'seqNo', width:200, title: '流水号' , visible:false},
			{field:'schId', width:200, title: '税务方案代码', visible:false},
			{field:'isDefault', width:200, title: '是否默认', visible:false},
			{field:'assetClassify', width:200,sortField:'assetClassifyCode', title: '资产分类'},
			{field:'capiIncomeFeature', width:120, sortField:'capiIncomeFeatureCode',title: '本金收益特征'},
			{field:'incomeType', width:120,sortField:'incomeTypeCode', title: '收入类型'},
			{field:'taxableSign', width:120, sortField:'taxableSignCode',title: '应税标志'},
			{field:'feeType', width:200, sortField:'feeTypeCode',title: '免税收入类型'},
			{field:'taxRate', width:200, type: 'money', title: '应税税率(%)'},
			{field:'effDate', width:120, title: '生效日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	        	buttons: btns_vatTax
	        }
		],
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看增值税则表"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, seqNo: data.seqNo, operateType: "view"},function(){
		            	   //vatTaxTable.reload();
		       });
		}
	  });
	
	/**
	 * 事件绑定：增值税则表
	 */
	var vatTaxBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#TaxScheme001_btn_vatTax_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_vatTaxForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#TaxScheme001_btn_vatTax_reset").click(function(event){
			reset( '#vatTaxForm', vatTaxTable);
		});
		
		/** 事件绑定: 资产估值配置-查询 */
    	$("#TaxScheme001_btn_vatTax_search").click(function(event){
    		vatTaxTable.load();//重载表格 - 增值税则表
        });
		
		/**
		 * 绑定事件：增值税则表 - 新建
		 */
		$("#TaxScheme001_btn_vatTax_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建增值税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, operateType: "add", schId: s_schId},function(){
		            	   vatTaxTable.reload();
		       });
		});
		
    	/** 事件绑定: 增值税则表配置-导出 */
        $('#TaxScheme001_btn_vatTax_export').click(function() {
			var paramObj = {};
			var rowArr = vatTaxTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getVatTaxParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].seqNo + ",";
				}
				paramObj.seqNo = keyStr;
			}
	        Joyin.exportExcel('exportVatTax', paramObj);
        });
		
		/**
		 * 绑定事件：增值税则表 - 编辑
		 */
		vatTaxTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑增值税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType: "edit"},function(){
		            	   vatTaxTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：增值税则表 - 复制
		 */
		vatTaxTable.bind('click', '.copy', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制增值税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme003/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, seqNo: data.seqNo, operateType: "copy", schId: s_schId},function(){
		            	   vatTaxTable.reload();
		       });
	   });
	   
		/**
		 * 绑定事件：增值税则表 - 删除
		 */
		vatTaxTable.bind('click', '.delt', function(data, index) {
			var object = {};
	    	object.seqNo = data.seqNo;
	    	var isDefault= data.isDefault
	    	if(isDefault==='Y'){
	    		Joyin.alert("默认税务方案增值税则表中内容不可删除",2);
	    	}else{
	    	 	object.operateType = "delete";
			   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
			           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
			        	   WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteVatTax', object, "TaxScheme001_btn_vatTax_delete", "TaxScheme003",function() {
			        		   vatTaxTable.reload();
	       	              });
			           });
			       });
	    	}
	   });
		
		/** 事件绑定: 增值税则表-批量删除 */
        $('#TaxScheme001_btn_vatTax_delete').click(function() {
        	var object = {};
        	var rowArr = vatTaxTable.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
        		Joyin.error("请选择1条记录进行删除。");
        	}else{
        		object.seqNo = rowArr[0].seqNo;
          		object.effDate = rowArr[0].effDate;
          		object.isDefault = rowArr[0].isDefault;
    	    	object.operateType = "deleteVatTax";
    		   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
    		   		   object.operateType = "delete";
    		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    		        	   WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteVatTax', object, "TaxScheme001_btn_vatTax_delete", "TaxScheme003",function() {
    		        		   vatTaxTable.reload();
           	              });
    		           });
    	       });
        	}
        });
		
	}
	

//  =========================  附加税则表（右侧Tab）   =======================	
	var btns_adtTax = [];
    if (!isAdmin){
        if(purviewElements.indexOf('_edit') != -1) {
            btns_adtTax.push('edit');
        }
        if(purviewElements.indexOf('_manual') != -1) {
            btns_adtTax.push('copy');
        }
        if(purviewElements.indexOf('_delete') != -1) {
            btns_adtTax.push('delt');
        }
    } else {
        btns_adtTax = allBtn
    }

	/**
	 * 附加税则表
	 */
	var adtTaxTable=$("#adtTax-table").joyintable({
	      ajax: {
	    	url: 'findAdtTax',
	        type: 'POST',
	        data: function() {
	            return getAdtTaxParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'taxId',  title: '税则代码' , visible:false},
			{field:'schId',  title: '税务方案代码' , visible:false},
			{field:'isDefault', width:200, title: '是否默认', visible:false},
			{field:'addTaxType', width:400, sortField:'addTaxTypeCode',title: '附加税类型'},
			{field:'taxRate', width:400, type: 'money', title: '应税税率(%)'},
			{field:'effDate', width:400, title: '生效日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	        	buttons: btns_adtTax
	        }
		],
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看附加税则表"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme004/init",
		               "65%", "85%", {pageModel:PAGE_MODEL_REFERENCE, taxId: data.taxId, operateType: "view"},function(){
		            	   //adtTaxTable.reload();
		       });
		}
	  });
	
	/**
	 * 事件绑定：附加税则表
	 */
	var adtTaxBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#TaxScheme001_btn_adtTax_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_adtTaxForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#TaxScheme001_btn_adtTax_reset").click(function(event){
			reset( '#adtTaxForm', adtTaxTable);
		});
		
    	/** 事件绑定: 附加税则表-查询 */
    	$("#TaxScheme001_btn_adtTax_search").click(function(event){
    		adtTaxTable.load();//重载表格 - 附加税则表
        });
    	
        /** 事件绑定: 附加税则表-导出 */
        $('#TaxScheme001_btn_adtTax_export').click(function() {
        	var paramObj = {};
			var rowArr = adtTaxTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getAdtTaxParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].taxId + ",";
				}
				paramObj.taxId = keyStr;
			}
	        Joyin.exportExcel('exportAdtTax', paramObj);
        });
        
        
		
		/**
		 * 绑定事件：附加税则表 - 新建
		 */
		$("#TaxScheme001_btn_adtTax_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建附加税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme004/init",
		               "65%", "85%", {pageModel:PAGE_MODEL_NEW,  schId: s_schId, operateType: "add"},function(){
		            	   adtTaxTable.reload();
		       });
		});
		
		/**
		 * 绑定事件：附加税则表 - 编辑
		 */
		adtTaxTable.bind('click', '.edit', function(data, index) {
            if(isEJFRflag ){
            // 1.默认方案 当新建附加税税则生效日小于或等于平台日期时，不允许编辑
            // 2.默认方案  当新建附加税税则大于平台日期时，允许编辑
            // 3.公共附加税税则，不允许编辑
               if(data.updateFlg == '02'){
               // 公共数据不让删除
                    Joyin.alert("公共附加税税则不允许编辑",2);
                    return false;
               }
               if(data.isDefault==='Y'){
                    var effDate = data.effDate;
                    // 生效日
                    if(new Date(platDate).DateDiff('d',effDate)<=0){
                        Joyin.alert("默认方案下非公共附加税税则生效日小于或等于平台日期时，不允许编辑",2);
                        return false;
                    }
               }
            }

		   	Joyin.dialog(JoyinUtil.getLabel("编辑附加税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme004/init",
		               "65%", "85%", {pageModel:PAGE_MODEL_EDIT, taxId: data.taxId, operateType: "edit"},function(){
		            	   adtTaxTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：附加税则表 - 复制
		 */
		adtTaxTable.bind('click', '.copy', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制附加税则表（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme004/init",
		               "65%", "85%", {pageModel:PAGE_MODEL_NEW, taxId: data.taxId, schId: s_schId, operateType: "copy"},function(){
		            	   adtTaxTable.reload();
	       });
	   });
	   
		/**
		 * 绑定事件：附加税则表 - 删除
		 */
		adtTaxTable.bind('click', '.delt', function(data, index) {
			var object = {};
	    	object.taxId = data.taxId;
	    	var isDefault =data.isDefault;

	    	if(isEJFRflag ){
	    	// 1.删除规则修改  默认方案 当新建附加税税则生效日小于或等于平台日期时，不允许删除；
	    	// 2.默认方案  当新建附加税税则大于平台日期时，允许删除
	    	// 3.公共附加税税则，不允许删除
	    	   if(data.updateFlg == '02'){
	    	   // 公共数据不让删除
	    	        Joyin.alert("公共附加税税则不允许删除",2);
	    	        return false;
	    	   }
	    	   if(isDefault==='Y'){
	    	        var effDate = data.effDate;
	    	        // 生效日
                    if(new Date(platDate).DateDiff('d',effDate)<=0){
                        Joyin.alert("默认方案下非公共附加税税则生效日小于或等于平台日期时，不允许删除",2);
                        return false;
                    }
	    	   }
	    	}else{
                if(isDefault==='Y'){
                    Joyin.alert("默认税务方案附加税则表中内容不可删除",2);
                    return false;
                }
	    	}

            object.operateType = "deleteAdtTax";
            Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
                   Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                       WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteAdtTax', object, "TaxScheme001_btn_adtTax_delete", "TaxScheme004",function() {
                           adtTaxTable.reload();
                      });
                   });
           });

	   });
		
      /** 事件绑定: 附加税则表-批量删除 */
      $('#TaxScheme001_btn_adtTax_delete').click(function() {
      	var object = {};
      	var rowArr = adtTaxTable.getAllCheckedRows();// 所有选中行数据
      	if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
      		Joyin.error("请选择1条记录进行删除。");
      	}else{
      		object.taxId = rowArr[0].taxId;
      		object.effDate = rowArr[0].effDate;
      		object.isDefault = rowArr[0].isDefault;

      		if(isEJFRflag ){
            // 1.删除规则修改  默认方案 当新建附加税税则生效日小于或等于平台日期时，不允许删除；
            // 2.默认方案  当新建附加税税则大于平台日期时，允许删除
            // 3.公共附加税税则，不允许删除
               if(rowArr[0].updateFlg == '02'){
               // 公共数据不让删除
                    Joyin.alert("公共附加税税则不允许删除",2);
                    return false;
               }
               if(rowArr[0].isDefault==='Y'){
                    var effDate = rowArr[0].effDate;
                    // 生效日
                    if(new Date(platDate).DateDiff('d',effDate)<=0){
                        Joyin.alert("默认方案下非公共附加税税则生效日小于或等于平台日期时，不允许删除",2);
                        return false;
                    }
               }
            }else{
                if(rowArr[0].isDefault==='Y'){
                    Joyin.alert("默认税务方案附加税则表中内容不可删除",2);
                    return false;
                }
            }

  	    	object.operateType = "deleteAdtTax";
  		   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
  		   		   object.operateType = "delete";
  		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
  		        	 WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteAdtTax', object, "TaxScheme001_btn_adtTax_delete", "TaxScheme004",function() {
  		        		 	adtTaxTable.reload();
     	              });
  		           });
  	       });
      	}
      });
      
      
	}
	
	
//  =========================  资产应税信息（右侧Tab）   =======================	
	var btns_taxable = [];
    if (!isAdmin){
        if(purviewElements.indexOf('_edit') != -1) {
            btns_taxable.push('edit');
        }
        if(purviewElements.indexOf('_manual') != -1) {
            btns_taxable.push('copy');
        }
        if(purviewElements.indexOf('_delete') != -1) {
            btns_taxable.push('delt');
        }
    } else {
        btns_taxable = allBtn
    }

	/**
	 * 资产应税信息
	 */
	var taxableTable=$("#taxable-table").joyintable({
	      ajax: {
	    	url: 'findTaxable',
	        type: 'POST',
	        data: function() {
	            return getTaxableParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'seqNo', width:120, title: '流水号' , visible:false},
			{field:'schId', width:120, title: '税务方案代码' , visible:false},
			{field:'isDefault', width:200, title: '是否默认', visible:false},
			{field:'assetId', width:120, title: '资产代码'},
			{field:'assetName', width:200, title: '资产名称'},
			{field:'assetClassify', width:200, sortField:'assetClassifyCode',title: '资产分类'},
			{field:'capiIncomeFeature', width:120, sortField:'capiIncomeFeatureCode',title: '本金收益特征'},
			{field:'incomeType', width:120, sortField:'incomeTypeCode',title: '收入类型'},
			{field:'taxableSign', width:120, sortField:'taxableSignCode',title: '应税标识'},
			{field:'taxRate', width:120, type: 'money', title: '应税税率(%)'},
			{field:'effDate', width:120, title: '生效日'},
			{field:'maintenanceMode', width:120, sortField:'maintenanceModeCode',title: '维护方式'},
			{field:'feeType', width:150, sortField:'feeTypeCode',title: '免税收入类型'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	        	buttons: btns_taxable
	        }
		],
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看资产应税信息"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme005/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, seqNo: data.seqNo, operateType: "view"},function(){
		            	   //taxableTable.reload();
		       });
		}
	  });
	
	/**
	 * 事件绑定：资产应税信息
	 */
	var taxableBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#TaxScheme001_btn_taxable_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_taxableForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#TaxScheme001_btn_taxable_reset").click(function(event){
			reset( '#taxableForm', taxableTable);
		});
		
    	/** 事件绑定: 资产应税信息-查询 */
    	$("#TaxScheme001_btn_taxable_search").click(function(event){
    		taxableTable.load();//重载表格 - 资产应税信息
        });
    	
        /** 事件绑定: 资产应税信息-导出 */
        $('#TaxScheme001_btn_taxable_export').click(function() {
        	var paramObj = {};
			var rowArr = taxableTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getTaxableParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].seqNo + ",";
				}
				paramObj.seqNo = keyStr;
			}
	        Joyin.exportExcel('exportTaxable', paramObj);
        });
		
		/**
		 * 绑定事件：资产应税信息 - 新建
		 */
		$("#TaxScheme001_btn_taxable_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建资产应税信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme005/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW,  schId: s_schId, operateType: "add"},function(){
		            	   taxableTable.reload();
		       });
		});
		
		/**
		 * 绑定事件：资产应税信息 - 编辑
		 */
		taxableTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑资产应税信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme005/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType: "edit"},function(){
		            	   taxableTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：资产应税信息 - 复制
		 */
		taxableTable.bind('click', '.copy', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制资产应税信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme005/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, seqNo: data.seqNo, schId: s_schId, operateType: "copy"},function(){
		            	   taxableTable.reload();
	       });
	   });
	   
		/**
		 * 绑定事件：资产应税信息 - 删除
		 */
		taxableTable.bind('click', '.delt', function(data, index) {
			var object = {};
	    	object.seqNo = data.seqNo;
	    	var isDefault =data.isDefault;
//	    	if(isDefault==='Y'){
//	    		Joyin.alert("默认税务方案资产应税信息中内容不可删除",2);
//	    	}else{
	    		object.operateType = "delete";
			   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
			           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
			        	   WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteTaxable', object, "TaxScheme001_btn_taxable_delete", "TaxScheme005",function() {
			        		   taxableTable.reload();
	    	              });
			           });
		       });
//	    	}
	   });
		
      /** 事件绑定: 资产应税信息-批量删除 */
      $('#TaxScheme001_btn_taxable_delete').click(function() {
      	var object = {};
      	var rowArr = taxableTable.getAllCheckedRows();// 所有选中行数据
      	if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
      		Joyin.error("请选择1条记录进行删除。");
      	}else{
      		object.seqNo = rowArr[0].seqNo;
      		object.effDate = rowArr[0].effDate;
      		object.isDefault = rowArr[0].isDefault;
  	    	object.operateType = "deletetaxable";
  		   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
  		   		   object.operateType = "delete";
  		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
  		        	  WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteTaxable', object, "TaxScheme001_btn_taxable_delete", "TaxScheme005",function() {
  		        		taxableTable.reload();
  		        	  });
  		           });
  	       });
      	}
      });
      
	}
	
	//========================  参数配置 (右侧 Tab)  =====================
	var btns_config = [];
    if (!isAdmin){
        if(purviewElements.indexOf('_edit') != -1) {
            btns_config.push('edit');
        }
        if(purviewElements.indexOf('_manual') != -1) {
            btns_config.push('copy');
        }
        if(purviewElements.indexOf('_delete') != -1) {
            btns_config.push('delt');
        }
    }  else {
        btns_config = allBtn
    }

	/**
	 * 参数设置
	 */
	var configTable=$("#config-table").joyintable({
	      ajax: {
	    	url: 'findConfig',
	        type: 'POST',
	        data: function() {
	            return getConfigParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
			{field:'seq_id', width:120, title: '代码', visible:false},
			{field:'schemeId',  width:120, title: '方案代码', visible:false},
			{field:'isDefault', width:200, title: '是否默认', visible:false},
			{field:'schemeType2',  sortField:'schemeType2Code',title: '参数类型'},
			{field:'configId',  width:200, title: '项目代码', visible:false},
			{field:'configName',  width:240, title: '项目'},
			{field:'remark',  width:240, title: '项目说明'},
			{field:'configValue',  width:120, title: '值'},
			{field:'effDate',  width:120, title: '生效日'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	        	buttons: btns_config
	        }
		],
		dblclick:function(table, data) {
			Joyin.dialog(JoyinUtil.getLabel("查看参数设置"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme006/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, seqId: data.seqId, operateType: "view"},function(){
		            	   
		       });
		}
	  });
	
	/**
	 * 事件绑定：参数设置
	 */
	var configBind = function() {
		
		/** 事件绑定: 筛选按钮 */
		$("#TaxScheme001_btn_config_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#c_configForm").toggle();
		});
		
		/** 事件绑定: 置空按钮 */
		$("#TaxScheme001_btn_config_reset").click(function(event){
			reset( '#configForm', configTable);
		});
		
    	/** 事件绑定: 参数设置配置-查询 */
    	$("#TaxScheme001_btn_config_search").click(function(event){
    		configTable.load();//重载表格 - 参数设置配置
        });
    	
        /** 事件绑定: 参数设置配置-导出 */
        $('#TaxScheme001_btn_config_export').click(function() {
        	var paramObj = {};
			var rowArr = configTable.getAllCheckedRows();// 所有选中行数据
			if(rowArr == null || rowArr.length == 0){ // 按条件导出
				paramObj = getConfigParams();
			}else{// 按选中导出
				var keyStr = "";
				for(var i=0; i<rowArr.length; i++){ 
					keyStr = keyStr + rowArr[i].seqId + ",";
				}
				paramObj.seqId = keyStr;
			}
	        Joyin.exportExcel('exportConfig', paramObj);
        });
        
        
        /** 事件绑定: 参数设置配置-批量删除 */
        $('#TaxScheme001_btn_config_delete').click(function() {
        	var object = {};
        	var rowArr = configTable.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
          		Joyin.error("请选择1条记录进行删除。");
        	}else{
        		object.seqId = rowArr[0].seqId;
        		object.isDefault = rowArr[0].isDefault;
    	    	object.operateType = "delete";
    		   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
    		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    		        	   WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteConfig', object, "TaxScheme001_btn_config_delete", "TaxScheme006",function() {
    		        		   configTable.reload();
    	  		        	  });
    		           });
    	       });
        	}
        });
		
		/**
		 * 绑定事件：参数设置 - 新建
		 */
		$("#TaxScheme001_btn_config_add").click(function(event){
			Joyin.dialog(JoyinUtil.getLabel("新建参数设置（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme006/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW,  schemeId: s_schId, operateType: "add"},function(){
		            	   configTable.reload();
		       });
		});
		
		/**
		 * 绑定事件：参数设置 - 编辑
		 */
		configTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑参数设置（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme006/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqId: data.seqId, operateType: "edit"},function(){
		            	   configTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：参数设置 - 复制
		 */
		configTable.bind('click', '.copy', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制参数设置（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/taxparametermanage/taxscheme/TaxScheme006/init",
		               "85%", "85%", {pageModel:PAGE_MODEL_NEW, schemeId: s_schId, seqId: data.seqId, operateType: "copy"},function(){
		            	   configTable.reload();
	       });
	   });
	   
		/**
		 * 绑定事件：参数设置 - 删除
		 */
		configTable.bind('click', '.delt', function(data, index) {
			var object = {};
	    	object.seqId = data.seqId;
	    	var isDefault =data.isDefault;
	    	if(isDefault==='Y'){
	    		Joyin.alert("默认税务方案参数设置中内容不可删除",2);
	    	}else{
		    	object.operateType = "delete";
			   	Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
			           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
			        	   WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteConfig', object, "TaxScheme001_btn_config_delete", "TaxScheme006",function() {
			        		   configTable.reload();
	  		        	  });
			           });
		       });
	    	}
	   });
	};


    //========================  会计场景计税配置 (右侧 Tab)  =====================
    var btns_taxSchaBokConfig = [];
    if (!isAdmin){
        if(purviewElements.indexOf('_edit') != -1) {
            btns_taxSchaBokConfig.push('edit');
        }
        if(purviewElements.indexOf('_manual') != -1) {
            btns_taxSchaBokConfig.push('copy');
        }
        if(purviewElements.indexOf('_delete') != -1) {
            btns_taxSchaBokConfig.push('delt');
        }
    }  else {
        btns_taxSchaBokConfig = allBtn
    }

    /**
     * 会计场景计税配置
     */
    var taxSchaBokConfigTable=$("#taxSchaBokConfig-table").joyintable({
        ajax: {
            url: 'findTaxSchaBokConfig',
            type: 'POST',
            data: function() {
                return getTaxSchaBokConfigParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        checkbox:true,
        cols: [
            {field:'seqNo',  width:200, title: '流水号', visible: false},
            {field:'configId',  width:200, title: '配置代码'},
            {field:'tableIdCode', width:180, sortField:'tableId', title: '记账场景(会计事件编码)', },
            {field:'subjectNature', width:200, title: '发生额科目性质'},
            {field:'vatType',  width:140, title: '增值税类型', },
            {field:'taxPType',  width:140, title: '确认类型'},
            {field:'incomeSource',  width:140, title: '发生场景'},
            {field:'inOut',  width:140, title: '增项/减项'},
            {field:'calcType',  width:140, title: '计算方式'},
            {field:'cdFlag',  width:140, title: '发生科目借贷方向'},
            {field:'calcScale',  width:140, title: '占比比例(预留)'},
            {field:'calcSnature',  width:140, title: '占比科目性质'},
            {field:'calcBase',  width:140, title: '占比基数'},
            {field:'isCarrt',  width:140, title: '金额计入方式'},
            {field:'intRepayHandle',  width:140, title: '处理条件'},
            {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
                buttons: btns_taxSchaBokConfig
            }
        ],
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看会计场景计税配置"),
                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme007/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE, schemeId: s_schId, seqNo: data.seqNo, operateType: "view"},function(){

                });
        }
    });

    /**
     * 事件绑定：会计场景计税配置
     */
    var taxSchaBokConfigBind = function() {

        /** 事件绑定: 筛选按钮 */
        $("#TaxScheme001_btn_taxSchaBokConfig_filter").click(function(event){
            event.stopPropagation();// 阻止事件冒泡
            $("#c_taxSchaBokConfigForm").toggle();
        });

        /** 事件绑定: 置空按钮 */
        $("#TaxScheme001_btn_taxSchaBokConfig_reset").click(function(event){
            reset( '#taxSchaBokConfigForm', taxSchaBokConfigTable);
        });

        /** 事件绑定: 会计场景计税配置-查询 */
        $("#TaxScheme001_btn_taxSchaBokConfig_search").click(function(event){
            taxSchaBokConfigTable.load();//重载表格 - 参数设置配置
        });

        /** 事件绑定: 会计场景计税配置-导出 */
        $('#TaxScheme001_btn_taxSchaBokConfig_export').click(function() {
            var paramObj = {};
            var rowArr = taxSchaBokConfigTable.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length == 0){ // 按条件导出
                paramObj = getTaxSchaBokConfigParams();
            }else{// 按选中导出
                var keyStr = "";
                for(var i=0; i<rowArr.length; i++){
                    keyStr = keyStr + rowArr[i].seqNo + ",";
                }
                paramObj.schemeId = s_schId;
                paramObj.seqNo = keyStr;
            }
            Joyin.exportExcel('exportTaxSchaBokConfig', paramObj);
        });


        /** 事件绑定: 会计场景计税配置-删除 */
        $('#TaxScheme001_btn_taxSchaBokConfig_delete').click(function() {
            var object = {};
            var rowArr = taxSchaBokConfigTable.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
				Joyin.error("请选择1条记录进行删除。");
            }else{
                object.seqNo = rowArr[0].seqNo;
                object.isDefault = rowArr[0].isDefault;
                object.schemeId = s_schId;
                object.configId = rowArr[0].configId;
                object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteTaxSchaBokConfig', object, "TaxScheme001_btn_taxSchaBokConfig_delete", "TaxScheme007",function() {
                            taxSchaBokConfigTable.reload();
                        });
                    });
                });
            }
        });

        /**
         * 绑定事件：会计场景计税配置- 新建
         */
        $("#TaxScheme001_btn_taxSchaBokConfig_add").click(function(event){
            Joyin.dialog(JoyinUtil.getLabel("新建会计场景计税配置（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme007/init",
                "85%", "85%", {pageModel:PAGE_MODEL_NEW,  schemeId: s_schId, operateType: "add"},function(){
                    taxSchaBokConfigTable.reload();
                });
        });

        /**
         * 绑定事件：会计场景计税配置 - 编辑
         */
        taxSchaBokConfigTable.bind('click', '.edit', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("编辑会计场景计税配置（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme007/init",
                "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, schemeId: s_schId, operateType: "edit"},function(){
                    taxSchaBokConfigTable.reload();
                });
        });

        /**
         * 绑定事件：会计场景计税配置 - 复制
         */
        taxSchaBokConfigTable.bind('click', '.copy', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("复制会计场景计税配置（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxscheme/TaxScheme007/init",
                "85%", "85%", {pageModel:PAGE_MODEL_NEW, seqNo: data.seqNo, operateType: "copy"},function(){
                    taxSchaBokConfigTable.reload();
                });
        });

        /**
         * 绑定事件：会计场景计税配置 - 删除
         */
        taxSchaBokConfigTable.bind('click', '.delt', function(data, index) {
            var object = {};
            object.seqNo = data.seqNo;
            object.schemeId = s_schId;
            object.configId = data.configId;
            var isDefault =data.isDefault;
            if(isDefault==='Y'){
                Joyin.alert("默认税务方案中会计场景计税配置内容不可删除");
            }else{
                object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxscheme/TaxScheme001/deleteTaxSchaBokConfig', object, "TaxScheme001_btn_taxSchaBokConfig_delete", "TaxScheme007",function() {
                            taxSchaBokConfigTable.reload();
                        });
                    });
                });
            }
        });
    };

	
	
//  =========================  右侧Tab 相关   =======================
	
	var loaded = {};// 已加载状态记录
	/**
	 * 根据选项卡索引，动态加载表格
	 */
	var loadTable = function(index){
		 // if(s_schId == null){
			// Joyin.error('请在左侧选择一个方案，再进行配置操作！');
		 // }
		 if(s_schId != null ){// 右侧方案已选择 
			 if(!loaded[index]){ // 当前没加载
				 switch(index)
				 {
				 case 0:
					 vatTaxTable.load();break;//加载表格 - 增值税则表
				 case 1:
					 adtTaxTable.load();break;//加载表格 - 附加税则表
				 case 2:
					 taxableTable.load();break;//加载表格 - 资产应税信息
				 case 3:
					 configTable.load();break;//加载表格 - 参数设置
                 case 4:
                     taxSchaBokConfigTable.load();break;//加载表格 - 会计场景计税配置
				 }
				 loaded[index] = true;
			 }else { // 当前已加载，避免表格错位（架构组要求重载）
				 switch(index)
				 {
				 case 0:
					 vatTaxTable.reload();break;//重载表格 - 增值税则表
				 case 1:
					 adtTaxTable.reload();break;//重载表格 - 附加税则表
				 case 2:
					 taxableTable.reload();break;//重载表格 - 资产应税信息
				 case 3:
					 configTable.reload();break;//重载表格 - 参数设置
                 case 4:
                     taxSchaBokConfigTable.load();break;//加载表格 - 会计场景计税配置
				 }
			 }
		 }
	}
	

	
	/**
	 * 加载右侧所有表格
	 */
	var allLoadTable = function(){
    	vatTaxTable.load();//加载表格 - 增值税则表
    	adtTaxTable.load();//加载表格 - 附加税则表
    	taxableTable.load();//加载表格 - 资产应税信息
    	configTable.load();//加载表格 - 参数设置
    	taxSchaBokConfigTable.load();//加载表格 - 会计场景计税配置
    	allLoadFalg = true;
	}
	
	/**
	 * 右侧表格重载
	 */
	var reloadTable = function(schId){
		vatTaxTable.reload();//重载表格 - 增值税则表
		adtTaxTable.reload();//重载表格 - 附加税则表
		taxableTable.reload();//重载表格 - 资产应税信息
		configTable.reload();//重载表格 - 参数设置
		taxSchaBokConfigTable.reload();//重载表格 - 会计场景计税配置

		vatTaxTable.layOption.where.schId = schId;
		adtTaxTable.layOption.where.schId = schId;
		taxableTable.layOption.where.schId = schId;
		configTable.layOption.where.schId = schId;
		taxSchaBokConfigTable.layOption.where.schId = schId;
	}
	
	/**
	 * 清空右侧表格
	 */
	var allClearTable = function(){
		vatTaxTable.clear();//清空表格 - 增值税则表
		adtTaxTable.clear();//清空表格 - 附加税则表
		taxableTable.clear();//清空表格 - 资产应税信息
		configTable.clear();//清空表格 - 参数设置
		taxSchaBokConfigTable.clear();//清空表格 - 会计场景计税配置

		tabButtionDisable();// 禁用右侧按钮
	}
	
	/**
	 * 重置 Tab
	 * conditionArea : 条件区
	 * table : 关联的表格
	 */
	var reset = function(conditionArea, table){
		$(conditionArea).find("input").val("");
		$(conditionArea).find("select").val("").trigger("change");// select清空
	}

	/**
	 * 禁用 右侧 tab 按钮
	 */
	var tabButtionDisable = function(){
    	$(".layui-tab-item").each(function(index){
			$(this).find("button").addClass("layui-btn-disabled");
			$(this).find("button").attr("disabled", "disabled");
    	});
	}
	
	/**
	 *  启用 右侧 tab 按钮
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
    	
    	schemeBind(); // 事件绑定 - 税务方案
    	vatTaxBind(); //事件绑定 - 增值税则表
    	adtTaxBind();//事件绑定 - 附加税则表
    	taxableBind();//事件绑定 - 附加税则表
    	configBind();//事件绑定 - 参数设置
        taxSchaBokConfigBind();//事件绑定 - 会计场景计税配置

    	tabButtionDisable();// 禁用右侧按钮
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

