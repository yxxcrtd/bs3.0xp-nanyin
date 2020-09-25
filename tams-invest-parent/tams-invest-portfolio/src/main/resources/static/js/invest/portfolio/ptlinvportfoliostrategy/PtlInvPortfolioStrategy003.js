$(function () {
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.floatList = dataTable.getAllRows();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy003/check', object, function(result) {
        	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
    			if (result.data){
    				top.returnData = result.data;
    				var index = parent.layer.getFrameIndex(window.name);
    				parent.layer.close(index);
    				return;
    			}
    	});
    });
    };
    
    
    /**
     * 定义数据表格
     *
     */
    var dataTable=$("#dataTable").joyintable({
    	data:floatList ==null?[] : floatList,
        searchOnInit:  true,
        height : 200,
        checkbox:false,
        addbtn:true,
        loadonce : true,
        cols:[
             {field:'baseRuleId', title: 'PtlInvPortfolioStrategy001.baseRuleId',width:300,sortable:false, edit:true, editOption:{type:'select',id:"baseRuleId",formatoption:true,result: SECRATE_DATA}}
            ,{field:'coefficient',  title: 'PtlInvPortfolioStrategy001.coefficient',width:300, sortable: false,edit:true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });
    
    /**
     * 绑定删除事件
     */
    dataTable.bind('click', '.delt', function(data, index) {
    	dataTable.deleteRow(index);
    });
    
    var linkBind = function (){
        //  绑定联动：收益目标类型
        $("#incomeType").change(function(){
    		  var s_incomeType =  $("#incomeType").val();
    		  //收益目标类型为固定
    		  if(s_incomeType == '01'){
    			  $("#div_incomeTarget").show();
    			  $("#div_minIncomeTarget").show();
    			  $("#div_maxIncomeTarget").show();
    			  $("#spreadRate").val('');
    			  $("#div_spreadRate").hide();
    			  $("#div_dataTable").hide();
    		  //收益目标类型为浮动
    		  }else if(s_incomeType == '02'){
    			  $("#incomeTarget").val('');
    			  $("#minIncomeTarget").val('');
    			  $("#maxIncomeTarget").val('');
    			  $("#div_incomeTarget").hide();
    			  $("#div_minIncomeTarget").hide();
    			  $("#div_maxIncomeTarget").hide();
    			  $("#div_spreadRate").show();
    			  $("#div_dataTable").show();
    		  }else{
    			  $("#div_incomeTarget").hide();
    			  $("#div_minIncomeTarget").hide();
    			  $("#div_maxIncomeTarget").hide();
    			  $("#spreadRate").val('');
    			  $("#div_spreadRate").hide();
    			  $("#div_dataTable").hide();
    		  }
        });
    }
    
    
    $(function() {
    	if($("#incomeType").val() == '01'){
    		  $("#div_incomeTarget").show();
			  $("#div_minIncomeTarget").show();
			  $("#div_maxIncomeTarget").show();
			  $("#div_spreadRate").hide();
			  $("#div_dataTable").hide();
    	}else if($("#incomeType").val() == '02'){
    		  $("#div_incomeTarget").hide();
			  $("#div_minIncomeTarget").hide();
			  $("#div_maxIncomeTarget").hide();
			  $("#div_spreadRate").show();
			  $("#div_dataTable").show();
    	}
    	linkBind(); // 联动绑定
    	 // 绑定按钮事件
        $('#PtlInvPortfolioStrategy003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlInvPortfolioStrategy003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        				
    });
    
});