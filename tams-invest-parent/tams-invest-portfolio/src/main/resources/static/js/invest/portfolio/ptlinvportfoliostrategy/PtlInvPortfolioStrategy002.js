$(function () {
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.ptlIncomeIndexList = dataTable.getAllRows();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/save', object, "PtlInvPortfolioStrategy002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/save', object, "PtlInvPortfolioStrategy002_btn_submit");
                }
            });
        });
    };
    
    
    /**
     * 定义数据表格
     *
     */
    var dataTable=$("#dataTable").joyintable({
    	data:ptlIncomeIndexList ==null?[] : ptlIncomeIndexList,
        searchOnInit:  true,
        height : 200,
        checkbox:false,
        page:false,
        addbtn:false,
        loadonce : true,
        cols:[
        	 {field:'evalIndex',  title: 'PtlInvestStrategyPool002.evalIndex',width:200,sortable: false,edit:true, editOption:{type:'select',id:"evalIndex",formatoption:true,result: EVAL_INDEX}}
        	,{field:'incomeType',  title: 'PtlInvPortfolioStrategy001.incomeType',width:200,sortable: false,edit:true, editOption:{type:'select',id:"incomeType",formatoption:true,result: EVAL_INCOME_TYPE}}
        	,{field:'incomeTarget',  title: 'PtlInvPortfolioStrategy001.incomeTarget',width:200,sortable: false,edit:false}
        	,{field:'incomeTargetCode',  title: '目标值code',sortable: false,edit:false,visible:false}
        	,{field:'maxIncomeTarget',  title: 'PtlInvPortfolioStrategy001.maxIncomeTarget',width:200,sortable: false,edit:false}
        	,{field:'minIncomeTarget',  title: 'PtlInvPortfolioStrategy001.minIncomeTarget',width:200,sortable: false,edit:false}
        	,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit']}
        ],
        done : function(table, allData){
        	//设置都不可编辑
        	 var allrowData =  dataTable.options.cols;
             for(var i in allrowData){
            	   allrowData[i].edit = false;
             }
        }
    });
    
    /**
     * 绑定编辑事件
     */
    dataTable.bind('click', '.edit', function(data,index) {
    	Joyin.dialog(JoyinUtil.getLabel("评价指标编辑"),
                basePath + "invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy003/init","98%", "95%",{data:JSON.stringify(data)},function(result){
	        	if(!result){
	        		return;
	        	} else{
	        		dataTable.removeRow(data);
	        		var row = {
	        					evalIndex: data.evalIndex,
	        					incomeType: result.incomeType,
	        					incomeTarget: result.incomeTarget,
	        					incomeTargetCode: result.incomeTargetCode,
	        					maxIncomeTarget: result.maxIncomeTarget,
	        					minIncomeTarget: result.minIncomeTarget,
	                        };
	        		dataTable.addRow(row);
	        	}
        	});
    });
    
    var linkBind = function (){
    //  绑定联动：组合代码
        $("#portfolioId").change(function(){
    		  var s_portfolioId =  $("#portfolioId").val();
    		  Joyin.ajax(
    				basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/getPortfolioName', 
    				{portfolioId : s_portfolioId},
    				function (data) {
    					  //组合名称
    					  $("#portfolioName").val(data.portfolioName);
    		  });
        });
        
        //  绑定联动：策略代码
        $("#strategyId").change(function(){
    		  var s_strategyId =  $("#strategyId").val();
    		  Joyin.ajax(
    				basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/getStrategy', 
    				{strategyId : s_strategyId},
    				function (result) {
    					  //策略名称
    					  $("#strategyName").val(result.strategyName);
    					  //策略描述
    					  $("#strategyDesc").val(result.strategyDesc);
    					  //策略类型
    					  $("#strategyType").val(result.strategyType).trigger('change');
    					  //隐藏域：表格显示标志
    					  $("#tableShowFlag").val(result.tableShowFlag);
    					  var flag = result.tableShowFlag;
    		    		  if(flag == '02'){
    		    			  $("#div_dataTable").hide();
    		    		  }else if(flag == '01'){
    		    			  $("#div_dataTable").show();
    		    			  dataTable.reload({data : result.ptlIncomeIndexList});
    		    		  }else{
    		    			  $("#div_dataTable").hide();
    		    		  }
    		  });
        });
        
        //  绑定联动：收益目标类型
        $("#incomeType").change(function(){
    		  var s_incomeType =  $("#incomeType").val();
    		  //收益目标类型为固定
    		  if(s_incomeType == '01'){
    			  $("#div_incomeTarget").show();
    			  $("#div_minIncomeTarget").show();
    			  $("#div_maxIncomeTarget").show();
    			  $("#div_dataTable").hide();
    		  //收益目标类型为浮动
    		  }else if(s_incomeType == '02'){
    			  $("#div_incomeTarget").hide();
    			  $("#div_minIncomeTarget").hide();
    			  $("#div_maxIncomeTarget").hide();
    			  $("#div_dataTable").show();
    		  }else{
    			  $("#div_incomeTarget").hide();
    			  $("#div_minIncomeTarget").hide();
    			  $("#div_maxIncomeTarget").hide();
    			  $("#div_dataTable").hide();
    		  }
        });
    }
    
    
    $(function() {
    	if($("#tableShowFlag").val() == '01'){
			$("#div_dataTable").show();
			if($("#tableShowFlag").val() != ''){
  			  dataTable.reload({data : ptlIncomeIndexList,url:null});
			}
	  	}else if($("#tableShowFlag").val() == '02'){
	  		$("#div_dataTable").hide();
	  	}
    	linkBind(); // 联动绑定
    	 // 绑定按钮事件
        $('#PtlInvPortfolioStrategy002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlInvPortfolioStrategy002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
    });
    
});