$(function () {
	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.prodId = $.trim($('#prodId').val());
		object.pageModel = pageModel;
		object.operateType = operateType;		
		Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
		    		object.operateType = "save";		    			    		
				    WorkFlow.doSaveList(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum002/save', 
								object, "ReportContentEnum002_btn_save");
				}else{// submit
					WorkFlow.doSaveList(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum002/save', 
							object, "ReportContentEnum002_btn_submit");
				}
		    });
		});
	};
	
	/**
	 * 根据操作类型：显示按钮
	 */
    function btnShow() {
        var operateType = $('#operateType').val();
        if(operateType === 'edit') {
        	 $("#ReportContentEnum002_btn_save").show();
        	 $("#ReportContentEnum002_btn_submit").hide();
        	 $('#id').attr("disabled",true);
        }else {
        	 $("#ReportContentEnum002_btn_save").hide();
        	 $("#ReportContentEnum002_btn_submit").show();
        }
        if(operateType == 'view'){
        	$('input').attr("readonly","readonly");
        }
    };
    /*
    *//**
     * 获取查询参数
     *//*
    var getSearchParams = function() {
        return {
        	id: $.trim($('#id').val())
        }
    };
    var settedRule=$("#settedRule").joyintable({
    	ajax: {
            url: basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum002/findRulePage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }    
        },
        checkbox : false,
	    searchOnInit: true,
	    loadonce:true,
	    page : false,
    	cellMinWidth:100,
        cols:[{field:'ruleType1Name',title:'字段值',width:100,fixed:true}
            ,{field:'ruleType2Name',title: '比较条件',width:100,fixed:true}
            ,{field:'ruleType3Name',title: '值类型',width:100,fixed:true}
            ,{field:'ruleType4Name',title: '值',width:100,fixed:true}
	        ,{field:'operation',  width:30, title: 'SysCommon.operation', type:'action', buttons: ['delt']}
	        ,{field:'ruleType1', visible:false}
	        ,{field:'ruleType2', visible:false}
	        ,{field:'ruleType3', visible:false}
	        ,{field:'ruleType4', visible:false}
	        ]
    });
    settedRule.bind('click', '.delt', function (data, index) {
    	settedRule.removeRow(data);
    });
   	*//**
      * 规则增加事件
      * @returns
      *//*
     function addrule(){
    	 var rule = new Object;
    	 rule.ruleType1=$('#ruleType1').select2('data')[0].id;
    	 rule.ruleType1Name=$('#ruleType1').select2('data')[0].text;
    	 
    	 rule.ruleType2=$('#ruleType2').select2('data')[0].id;
    	 rule.ruleType2Name=$('#ruleType2').select2('data')[0].text;
    	 
    	 rule.ruleType3=$('#ruleType3').select2('data')[0].id;
    	 rule.ruleType3Name=$('#ruleType3').select2('data')[0].text;
    	 if(rule.ruleType1=='PROFIT_TYPE'){
    		 rule.ruleType4=$('#profitType').select2('data')[0].id;
        	 rule.ruleType4Name=$('#profitType').select2('data')[0].text;
    	 }
    	 else if(rule.ruleType1=='RAISE_TYPE'){
    		 rule.ruleType4=$('#raiseType').select2('data')[0].id;
        	 rule.ruleType4Name=$('#raiseType').select2('data')[0].text;
    	 }
    	 else{
    		 rule.ruleType4=$('#chooseDate').val();
        	 rule.ruleType4Name=$('#chooseDate').val();
    	 }
    	 
    	 var obj=settedRule.getAllRows();
    	 var index=0;
    	 for(var i in obj){
             if(obj[i].ruleType1==rule.ruleType1&&obj[i].ruleType2==rule.ruleType2
            		 &&obj[i].ruleType3==rule.ruleType3&&obj[i].ruleType4==rule.ruleType4){
               index++;
             }  
         }
         if(index==0){
        	 settedRule.addRow(rule); 
         }
     }
     function ruleType1Change(){
    	 var ruleType1=$('#ruleType1').select2('data')[0].id;
    	 if(ruleType1=='PROFIT_TYPE'){
    		 $('#raiseTypeSelect').hide();
    		 $('#chooseDate').hide();
    		 $('#profitTypeSelect').show();
    	 }
    	 else if(ruleType1=='RAISE_TYPE'){
    		 $('#raiseTypeSelect').show();
    		 $('#chooseDate').hide();
    		 $('#profitTypeSelect').hide();
    	 }
    	 else{
    		 $('#raiseTypeSelect').hide();
    		 $('#chooseDate').show();
    		 $('#profitTypeSelect').hide();
    	 }
     }
	*/
	  $(function() {
		   /* $('#raiseTypeSelect').hide();
			$('#chooseDate').show();
			$('#profitTypeSelect').hide();
			//规则增加事件
	    	$('#ReportContentEnum002_btn_add').click(addrule);
	    	//规则1改变事件
	    	$('#ruleType1').change(ruleType1Change);*/
	        // 绑定按钮事件
	        $('#ReportContentEnum002_btn_save').click(function() {
	            saveOrSubmit('save');	           
	        });
	        $('#ReportContentEnum002_btn_submit').click(function() {
	            saveOrSubmit('submit');
	        });
	        
	        btnShow();
	        	       
	    });
});


