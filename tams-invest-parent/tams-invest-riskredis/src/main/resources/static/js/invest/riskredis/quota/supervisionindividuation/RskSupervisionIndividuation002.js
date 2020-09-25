/**
 * 条件新建
 */
$(function() {
	 
    //var editable = pageModel != PAGE_MODEL_REFERENCE;
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
        object.pageId = "RskSupervisionIndividuation002";
        object.pageModel = pageModel;
        var controlType = $("#controlType").val();
        var conditionItem = $("#conditionItem").val();
        if(conditionItem==CONDITION_ITEM_03){
        	var eStr = new Date($("#valueStr3").val());
        	object.valueStr = eStr.getTime();
        	var eEnd = new Date($("#valueEnd3").val())
        	object.valueEnd = eEnd.getTime();
        }else{
        	if(controlType!=CONTROL_TYPE_05){
        		object.valueStr = $("#valueStr2").val();
        		object.valueEnd = null;
        	}
        }
    	
    	object.conditionItemStr = $("#conditionItem").select2('data')[0].text;
    	object.controlTypeStr = $("#controlType").select2('data')[0].text;
    	top.returnData=object;
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
      /*//判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation002/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation002/insert',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation002/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation002/update',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                })
            })
        }*/
    }
    
    var init = function(){
	var wfl_operType = $("#wfl_operType").val();
   	if(pageModel==PAGE_MODEL_EDIT){
   		/*if(wfl_operType == null || wfl_operType != 'NEW'){
   			$('#rolePos').attr("disabled",true);
   			$('#userName').attr("disabled",true);
   		}*/
   	}
   	var controlType = $("#controlType").val();
	if(controlType!=CONTROL_TYPE_05){
		$("#valueSingleDiv").show();
		$("#valueRangeDiv").hide();
	}else{
		$("#valueSingleDiv").hide();
		$("#valueRangeDiv").show();
	}
	setUnit();
	var conditionItem = $("#conditionItem").val();
	if(conditionItem==CONDITION_ITEM_03&&entity!=null&&entity.valueStr!=null&&entity.valueEnd!=null){
		var dStr = new Date(parseInt(entity.valueStr));
		var dEnd = new Date(parseInt(entity.valueEnd));
		$("#valueStr3").val(dStr.format("yyyy-MM-dd")).trigger('change');
		$("#valueEnd3").val(dEnd.format("yyyy-MM-dd")).trigger('change');
	}
   };
   var setUnit = function(){
	   var conditionItem = $("#conditionItem").val();
	   var controlType = $("#controlType").val();
	   if(conditionItem==CONDITION_ITEM_03){
		   $("#controlType").val('');
		   $("#controlType").attr("disabled",true).trigger('change');
		   $("#valueSingleDiv").hide();
     		$("#valueRangeDiv").hide();
		   $("#dateRangeDiv").show();
		   if(VDATE){
			   $("#valueStr3").val(VDATE).trigger('change');
		   }
	   }else{
		   $("#dateRangeDiv").hide();
		   $("#controlType").attr("disabled",false).trigger('change');
		   if(controlType!=CONTROL_TYPE_05){
          		$("#valueSingleDiv").show();
          		$("#valueRangeDiv").hide();
          	}else{
          		$("#valueSingleDiv").hide();
          		$("#valueRangeDiv").show();
          	}
		   if(conditionItem==CONDITION_ITEM_02){
			   $("#unit1").val("元").trigger('change');
			   $("#unit2").val("元").trigger('change');
			   $("#unit3").val("元").trigger('change');
		   }else{
			   $("#unit1").val("万元").trigger('change');
			   $("#unit2").val("万元").trigger('change');
			   $("#unit3").val("万元").trigger('change');
		   }
	   }
   }
   function requiredW(){
		if(!$("#conditionItem").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"条件项"));
			return false;
		}
		if($("#conditionItem").val()!=CONDITION_ITEM_03&&!$("#controlType").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"控制类型"));
			return false;
		}
		if($("#conditionItem").val()==CONDITION_ITEM_03&&!$("#valueStr3").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"开始时间"));
			return false;
		}
		if($("#conditionItem").val()==CONDITION_ITEM_03&&!$("#valueEnd3").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"结束时间"));
			return false;
		}
		if($("#conditionItem").val()==CONDITION_ITEM_03&&$("#valueEnd3").val()<$("#valueStr3").val()){
			Joyin.warn("开始时间不允许大于结束时间!");
			return false;
		}
		if($("#conditionItem").val()!=CONDITION_ITEM_03&&$("#controlType").val()!=CONTROL_TYPE_05&&!$("#valueStr2").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"数值"));
			return false;
		}
		if($("#conditionItem").val()!=CONDITION_ITEM_03&&$("#controlType").val()==CONTROL_TYPE_05&&!$("#valueStr").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"最小数值"));
			return false;
		}
		if($("#conditionItem").val()!=CONDITION_ITEM_03&&$("#controlType").val()==CONTROL_TYPE_05&&!$("#valueEnd").val()){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"最大数值"));
			return false;
		}
		if($("#conditionItem").val()!=CONDITION_ITEM_03&&$("#controlType").val()==CONTROL_TYPE_05&&JoyinMath.doMinus($("#valueStr").val(),$("#valueEnd").val())>0){
            Joyin.warn("前面数值不能大于后面数值");
            return false;
        }
		return true;
	};
    $(function() {
    	init();
    	$("select[name='controlType']").closest("div").next().html("");//删除控制类型后面的小星星
        // 按钮事件
        $('#RskSupervisionIndividuation002_btn_save').click(function() {
        	if(!requiredW()){
    			return;
		 	}
            saveOrSubmit('save');
        });
        $("#controlType").change(function(){
        	var controlType = $("#controlType").val();
        	var conditionItem = $("#conditionItem").val();
        	if(conditionItem!=CONDITION_ITEM_03){
        		if(controlType!==CONTROL_TYPE_05){
            		$("#valueSingleDiv").show();
            		$("#valueRangeDiv").hide();
            	}else{
            		$("#valueSingleDiv").hide();
            		$("#valueRangeDiv").show();
            	}
        	}
        	
        })
        $("#conditionItem").change(function(){
        	setUnit();
        })
    });
}());