$(function () {
	
	var _tradeId = null;
	   

	var node=$('#divlist');
	
	var setDivList = function(id){
	    _tradeId = id;
		if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
			typeDivTable.load(); 
			typeDivTable.reload();
		}else{
			typeDivTable.reload();
		}
		node.show();　
	    //var tt=$('#typeDiv-table').next();		    
	    //tt.css({"height":"130px"});
	    //tt.children(".layui-table-box").children(".layui-table-main").css({"height":"130px"});			    
	};
    /**
     *  查询条件 ：质押式正回购
     */
    var getTypeRsParams = function() {
    	return {
    		counterId: $.trim($('#counterIdRs').val()),
    		tradeMarket: $.trim($('#tradeMarketRs').val()),
    		portfolioId: $.trim($('#portfolioIdRs').val()),
    		releaseStatus: $.trim($('#releaseStatusRs').val()),
    		secId: $.trim($('#secIdRs').val()),
    		registOrg: $.trim($('#registOrgRs').val()),
    		acctId: $.trim($('#acctIdRs').val()),
            dataDateStart:$('#tradeDateRs').startDate(),
            dataDateEnd:$('#tradeDateRs').endDate()
        }
    }
    
    var getTradeIdParams = function() {
    	return {
    		tradeId:_tradeId
        }
    }
    
    /**
     *  查询条件 ：质押式逆回购
     */
    var getTypeRbParams = function() {
    	return {
    		counterId: $.trim($('#counterIdRb').val()),
    		tradeMarket: $.trim($('#tradeMarketRb').val()),
    		portfolioId: $.trim($('#portfolioIdRb').val()),
    		releaseStatus: $.trim($('#releaseStatusRb').val()),
    		registOrg: $.trim($('#registOrgRb').val()),
    		acctId: $.trim($('#acctIdRb').val()),
            dataDateStart:$('#tradeDateRb').startDate(),
            dataDateEnd:$('#tradeDateRb').endDate()
        }
    }
 
	
//  =========================  质押式正回购  =======================	
	
	/**
	 * 质押式正回购
	 */
	var typeRsTable=$("#typeRs-table").joyintable({
	      ajax: {
	    	url: 'findPageRs',
	        type: 'POST',
	        data: function() {
	            return getTypeRsParams();
	        }
	    },
        dblclick:function(table, data) {
       	    setDivList(data.tradeId);
        },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
            {field:'releaseStatusText', width:80, title: '有效状态', sortField:'fp.release_status'},
            {field:'releaseStatus', width:10, title: '有效状态' , visible:false},
			{field:'tradeId', width:160, title: '业务编号',sortField:'td.trade_id'},
            {
                field: 'portfolioId', width:120, sortField:'p.portfolio_id', title: '组合代码',
                render: function (data, type, row, meta) {
                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
                }
            },	
			{field:'portfolioName', width:120, title: '组合名称', sortField:'p.portfolio_name'},
			{field:'tradeMarket', width:120,title:'交易场所',sortField:'dict.item_value'},
			{field:'repoTypeText', width:120, title: '回购类型',sortField:'td.FINPROD_TYPE2'},
			{field:'tradeDate', width:120, title: '交易日期', type: 'date',sortField:'td.trade_date'},
			{field:'termDays', width:120, title: '回购期限（天）',sortField:'td.mdate-td.vdate'},
			{field:'prinAmt', width:120,  title: '首期结算金额（元）',type: 'money',sortField:'td.prin_amt'},
			{field:'mtradeAmt', width:120,  title: '到期结算金额（元）',type: 'money',sortField:'tp.m_trade_amt'},		
/*			{field:'secId', width:120, title: '债券代码',sortField:'fp.pledge_finprod_id'},
			{field:'secName', width:120, title: '债券名称',sortField:'f.finprod_abbr'},
			{field:'allPrice', width:130, title: '券面总额（万元）',type: 'money',sortField:'fp.pledge_amt'},
			{field:'rate', width:120,title: '折算比例（%）',type: 'money',sortField:'fp.pledge_ratio'},*/
			{field:'counterId', width:140, title: '交易对手',sortField:'mcp.counter_name'},
			{field:'counterTrader', width:120, title: '对手方交易员',sortField:'td.COUNTER_TRADER'},
			{field:'acctId', width:10, title: '证券托管户', visible:false},
			{field:'acctName', width:120, title: '证券托管户',sortField:'tsai.sec_acct_name'},
			{field:'registOrg', width:160, title: '登记托管机构',sortField:'dict2.item_value'}

		]
	  });
	
	/**
	 * 事件绑定：质押式正回购
	 */
	var typeRsBind = function() {
		
		/** 事件绑定: 质押式正回购-查询 */
    	$("#PtlManualRelease001_Rs_search").click(function(event){
    		typeRsTable.load();//重载表格 
    		node.hide();　
        });
		
		
    	/** 事件绑定: 质押式正回购配置-导出 */
        $('#PtlManualRelease001_Rs_export').click(function() {
			var paramObj = {};
			var rowArr = typeRsTable.getAllCheckedRows();// 所有选中行数据
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
		
    	/** 事件绑定: 置空按钮 */
		$("#PtlManualRelease001_Rs_reset").click(function(event){
			reset( '#typeRSForm', typeRsTable);
		});
    	/** 事件绑定: 筛选按钮 */
		$("#PtlManualRelease001_Rs_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#div_typeRSForm").toggle();
		});
		/**
		 * 绑定事件：质押式正回购 
		 */
        $('#PtlManualRelease001_Rs_revoke').click(function() {
            if(typeRsTable!=null && typeRsTable!=''){
                var selArr = typeRsTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行操作。");
                return;
            }else{          	
    			var rowArr = typeRsTable.getAllCheckedRows();// 所有选中行数据
    			if(rowArr.length>1){
    				Joyin.error("请选择一条记录进行操作！");
	                return;
    			}
				for(var i=0; i<rowArr.length; i++){ 
					if("1"==rowArr[i].releaseStatus){
		                Joyin.error("请选择未释放记录进行操作！");
		                return;
					}
				}
            
      
            var object = {};
            object.selectedItem = selArr;
            object.data = datas;
            object.tradeId=rowArr[0].tradeId;
            object.prinAmt=rowArr[0].prinAmt;
            object.mtradeAmt=rowArr[0].mtradeAmt;
            object.operateType = "revoke";
            object.pageModel = PAGE_MODEL_EDIT;
/*            Joyin.dialog(JoyinUtil.getLabel("手工释放"),
                basePath + "invest/portfolio/ptlmanualrelease/PtlManualRelease002/init",
                "70%", "80%", object, function () {
            	   typeRsTable.reload();
                });*/
            
            var checkObj=selArr[0];
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/check', checkObj, function(result) {//数据验证
		            Joyin.dialog(JoyinUtil.getLabel("手工释放"),
		                basePath + "invest/portfolio/ptlmanualrelease/PtlManualRelease002/init",
		                "70%", "80%", object, function () {
		            	    typeRsTable.reload();
		                });
            });
            
            /*Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001","是否确认释放当前交易的质押券头寸"), function(){
                	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/revokeRs', object,function() {
		        		   typeRsTable.reload();
                    });
                });
            });*/
            
            }
        });
		
	}
	

//  =========================  质押式逆回购（Tab）   =======================	
	
	/**
	 * 质押式逆回购
	 */
	var typeRbTable=$("#typeRb-table").joyintable({
	      ajax: {
	    	url: 'findPageRb',
	        type: 'POST',
	        data: function() {
	            return getTypeRbParams();
	        }
	    },
        dblclick:function(table, data) {
        },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [				
		            {field:'releaseStatusText', width:80, title: '有效状态' , sortField:'td.release_status'},  
		            {field:'releaseStatus', width:10, title: '有效状态' , visible:false},
					{field:'tradeId', width:160, title: '业务编号',sortField:'td.trade_id'},
		            {
		                field: 'portfolioId', width:120,sortField:'p.portfolio_id', title: '组合代码',
		                render: function (data, type, row, meta) {
		                    return "<a class='portfolioId-link' style='cursor: pointer;'>" + row.portfolioId + "</a>";
		                }
		            },					
					{field:'portfolioName', width:120, title: '组合名称', sortField:'p.portfolio_name'},
					{field:'tradeMarket', width:120,title:'交易场所',sortField:'dict.item_value'},
					{field:'repoTypeText', width:120, title: '回购类型',sortField:'td.FINPROD_TYPE2'},
					{field:'tradeDate', width:120, title: '交易日期' , type: 'date',sortField:'td.trade_date'},
					{field:'termDays', width:120, title: '回购期限（天）',sortField:'td.mdate-td.vdate'},
/*					{field:'tradeAmt', width:130,  title: '交易金额（元）',type: 'money',sortField:'td.trade_amt'},
*/					{field:'prinAmt', width:120,  title: '首期结算金额（元）',type: 'money',sortField:'td.prin_amt'},
					{field:'mtradeAmt', width:120,  title: '到期结算金额（元）',type: 'money',sortField:'tp.m_trade_amt'},					
					{field:'counterId', width:140, title: '交易对手',sortField:'mcp.counter_name'},
					{field:'counterTrader', width:120, title: '对手方交易员',sortField:'td.COUNTER_TRADER'},
					{field:'acctId', width:10, title: '证券托管户',visible:false},
					{field:'acctName', width:120, title: '证券托管户',sortField:'dict2.item_value'}

		],
//		done: function(data){
//			if(data.length<1){
//                layer.alert('没有符合条件的记录！');
//			}
//		},
		
	  });
	
	/**
	 * 事件绑定：质押式逆回购
	 */
	var typeRbBind = function() {
		
		
    	/** 事件绑定: 质押式逆回购-查询 */
    	$("#PtlManualRelease001_Rb_search").click(function(event){
    		typeRbTable.load();//重载表格 - 质押式逆回购
        });
    	
        /** 事件绑定: 质押式逆回购-导出 */
        $('#PtlManualRelease001_Rb_export').click(function() {
        	var paramObj = {};
			var rowArr = typeRbTable.getAllCheckedRows();// 所有选中行数据
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
        
      	/** 事件绑定: 置空按钮 */
		$("#PtlManualRelease001_Rb_reset").click(function(event){
			reset( '#typeRBForm', typeRbTable);
		});

    	/** 事件绑定: 筛选按钮 */
		$("#PtlManualRelease001_Rb_filter").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
			$("#div_typeRBForm").toggle();
		});
        /** 事件绑定: 质押式逆回购 */
        $('#PtlManualRelease001_Rb_revoke').click(function() {
            if(typeRbTable!=null && typeRbTable!=''){
                var selArr = typeRbTable.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行操作。");
                return;
            }else{         	
    			var rowArr = typeRbTable.getAllCheckedRows();// 所有选中行数据
    			if(rowArr.length>1){
    				Joyin.error("请选择一条记录进行操作！");
	                return;
    			}
    				for(var i=0; i<rowArr.length; i++){ 
    					if("1"==rowArr[i].releaseStatus){
    		                Joyin.error("请选择未释放记录进行操作！");
    		                return;
    					}
    				}
    				
				  var object = {};
		            object.selectedItem = selArr;
		            object.data = datas;
		            object.tradeId=rowArr[0].tradeId;
		            object.prinAmt=rowArr[0].prinAmt;
		            object.mtradeAmt=rowArr[0].mtradeAmt;
		            object.operateType = "revoke";
		            object.pageModel = PAGE_MODEL_EDIT;
		            var checkObj=selArr[0];
		            checkObj.operateType = "revoke";
		            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/checkB', checkObj, function(result) {//数据验证
				            Joyin.dialog(JoyinUtil.getLabel("手工释放"),
				                basePath + "invest/portfolio/ptlmanualrelease/PtlManualRelease003/init",
				                "70%", "80%", object, function () {
				            	    typeRbTable.reload();
				                });
		            });

            }           
/*            var object = {};
            object.selectedItem = selArr;
            object.data = datas;
            object.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001","是否确认释放当前交易的质押券头寸"), function(){
                	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanualrelease/PtlManualRelease001/revokeRb', object,function() {
                    	typeRbTable.reload();
                    });
                });
           });*/
        });

	}
	
	/**
	 * 重置 Tab
	 * conditionArea : 条件区
	 * table : 关联的表格
	 */
	var reset = function(conditionArea, table){
		$(conditionArea).find("input").val("");
		$(conditionArea).find("select").val("").trigger("change");// select清空
		   //typeDivTable.clear();//清空表格 -
    	   node.hide();
	}

	/**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
	typeRsTable.bind('click', '.portfolioId-link', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.see"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",
            {
                pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName
            });
    });	
	typeRbTable.bind('click', '.portfolioId-link', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.see"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",
            {
                pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName
            });
    });	



	var typeDivTable=$("#typeDiv-table").joyintable({
	      ajax: {
	    	url: 'findPageRepInfo',
	        type: 'POST',
	        data: function() {
	            return getTradeIdParams();
	        }
	    },
		searchOnInit: false,
        page : false,
        height: 120,
        checkbox:false,
		cols: [						   
				{field:'secId', width:120, title: '债券代码',sortField:'fp.pledge_finprod_id'},
				{field:'secName', width:120, title: '债券名称',sortField:'f.finprod_abbr'},
				{field:'allPrice', width:130, title: '券面总额（万元）',type: 'money',sortField:'fp.pledge_amt'},
				{field:'rate', width:120,title: '折算比例（%）',type: 'money',sortField:'fp.pledge_ratio'},
		],	
	  });

   var typeDivBind = function() {
   	   
       $('.layui-tab-title').on('click', function(title) {
    	   //typeDivTable.clear();//清空表格 -
    	   node.hide();
           if(title.toElement.id=="RS-title"){
        	  
           }else if(title.toElement.textContent=="Rb-title"){

           }
       });
		
	};

    /**
     * 页面加载完后执行
     */
	$(function () {
        var autoSize = function(){
            var height = $(window).height();
            $(".layui-fluid").css({
                "overflow" : "auto",
                "height": height + "px",
            });
        };
        autoSize();
        $(window).resize(function() {
            autoSize();
        });
        
    	typeRsBind(); //事件绑定 - 质押式正回购
    	typeRbBind();//事件绑定 - 质押式逆回购
    	typeDivBind();


	});


	
});

