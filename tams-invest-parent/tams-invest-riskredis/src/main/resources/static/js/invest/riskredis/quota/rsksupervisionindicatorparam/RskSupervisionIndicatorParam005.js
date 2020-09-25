//参数模板 初始化为复合模板
	 var parameterTemplate = PARA_TEMPLATE_FHMB;
/**
 *自定义参数新建
 */
$(function() {
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm',true);
    	object.paraDisplayType = $("#paraDisplayType").val();
    	object.redisField = $("#redisField").val();
    	object.workflowId = $("#workflowId").val();
        //-----add by 裴家俊  2019/03/20-----end---------
        object.pageModel = pageModel;
        object.paraCode = $("#paraCode").val();
        object.industry = $("#industry").val();
        object.assetDetailType = $("#assetDetailType").val();
        var subRateType2 = $("#subRateType2").val();   
        if(subRateType2){
        	object.subRateType2 = subRateType2.join(',');
        }
		 var debRateType2 = $("#debRateType2").val();
		 if(debRateType2){
	        	object.debRateType2 = debRateType2.join(',');
	        }
        object.pageId="RskSupervisionIndicatorParam005";
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005/save', object, "RskSupervisionIndicatorParam005_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005/save', object, "RskSupervisionIndicatorParam005_btn_submit");
                }
            });
        });
    }

	 function abJson(){
		 $("#paraDisplayType").val(PARA_DISPLAY_TYPE_CUSTOM);
		 var jsonResult = {};
		 var assetType = $("#assetType").val();
		 var assetDetailType = $("#assetDetailType").val();
		 var category = $("#category").val();
		 var industry = $("#industry").val();
		 var subRateType = $("#subRateType").val();
		 var subRateType2 = $("#subRateType2").val();
		 var debRateType = $("#debRateType").val();
		 var debRateType2 = $("#debRateType2").val();
		 if(subRateType2&&subRateType2!=""){
     		var subRateType2ArrayN=[];
			 subRateType2.map(function(y){
     			var i = y.indexOf('.');
	        		if(i != -1) {
	        			subRateType2ArrayN.push(y.substring(i + 1));
	        		}
     		})
     		//subRateType2 = subRateType2ArrayN.join(',');
		 };
		 if(debRateType2&&debRateType2!=""){
     		var debRateType2ArrayN=[];
			 debRateType2.map(function(y){
     			var i = y.indexOf('.');
	        		if(i != -1) {
	        			debRateType2ArrayN.push(y.substring(i + 1));
	        		}
     		})
     		//debRateType2 = debRateType2ArrayN.join(',');
		 };
		 var str = "AUTH_ASSET_FIRST_TYPE";
		 var assetDetailType = $("#assetDetailType").val();
		 if(assetDetailType){
			 var text = JSON.parse( assetDetailType);
			 var asset = text.id;
			 var str = [];
			 var strDetail = [];
			 if(asset&&asset.length>0){
				 var j=0;
				 var detail = "";
				 for(var i=0;i<=asset.length;i++){
					 if(i==asset.length||asset[i].indexOf("AUTH_ASSET_FIRST_TYPE")!=-1||asset[i].indexOf("CLASS_MANAGE_FIRST_TYPE")!=-1
							 ||asset[i].indexOf("SUPERVISE_ASSET_TYPE")!=-1||(asset[i].indexOf("WINDTYPEONE")!=-1&&asset[i].indexOf("CHB_WINDTYPEONE")==-1)){
						 if(j!=0){
							 detail = detail.substring(0,detail.length-1);
							 strDetail[j-1] = detail;
							 detail = "";
						 }
						 if(i!=asset.length){
							 var index = asset[i].indexOf('.');
							 if(i != -1) {
								 str[j] = asset[i].substring(index + 1);
				        		}
							 j++; 
						 }
					 }else{
						 var index = asset[i].indexOf('.');
						 if(index != -1) {
							 detail = detail+asset[i].substring(index + 1)+",";
			        		}
					 }
				 }
			 }
			 var value = [];
			 if(str.length>0&&strDetail.length>0){
				 for(var i=0;i<strDetail.length;i++){
					 value[i] = {parent:str[i],id:strDetail[i]};
				 }
			 }
			 assetDetailType = {text,value};
		 }
		 var industry = $("#industry").val();
		 if(industry){
			 var text = JSON.parse(industry);
			 var asset2 = text.id;
			 var str2 = [];
			 var strDetail2 = [];
			 if(asset2&&asset2.length>0){
				 var j=0;
				 var detail2 = "";
				 for(var i=0;i<=asset2.length;i++){
					 var n2 = "0102030405060708091011121314151617181920";
					 if(i==asset2.length||n2.indexOf(asset2[i])!=-1){
						 if(j!=0){
							 detail2 = detail2.substring(0,detail2.length-1)
							 strDetail2[j-1] = detail2;
							 detail2 = "";
						 }
						 if(i!=asset2.length){
							 str2[j] = asset2[i];
							 j++;
						 }
					 }else{
						 detail2 = detail2+asset2[i]+",";
					 }
				 }
			 }
			 var value = [];
			 if(str2.length>0&&strDetail2.length>0){
				 for(var i=0;i<strDetail2.length;i++){
					 value[i] = {parent:str2[i],id:strDetail2[i]};
				 }
			 }
			 industry = {text,value};
		 }
		 if(parameterTemplate == PARA_TEMPLATE_FHMB){
			 jsonResult = {
					//模板代码
					 	modelType:PARA_TEMPLATE_FHMB ,
					 	value:$("#value").val(),
                 		assetType:$('#assetType').val(),
                 		category:$('#category').val(),
                 		assetDetailType:assetDetailType,
			 			industryType:industry,
			 			bodyGradeType:subRateType,
			 			bodyGrade:subRateType2ArrayN,
			 			bondGradeType:debRateType,
			 			bondGrade:debRateType2ArrayN,
			 			mdateType:$("#dateType").val(),
			 			mdateRange:$("#dateRange").val(),
			 			customDate:$("#definDate").val()
			 };
			 if(subRateType){
				 jsonResult.bodyGradeType = subRateType;
				 jsonResult.bodyGrade = subRateType2ArrayN;
			 }
			 if(debRateType){
				 jsonResult.bondGradeType = debRateType;
				 jsonResult.bondGrade = debRateType2ArrayN;
			 }
		 }else if(parameterTemplate == PARA_TEMPLATE_ZCSZ){
			 jsonResult = {
						//type:jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val(),
						//模板代码
					 	modelType:PARA_TEMPLATE_ZCSZ ,
						//变量值
					 	valueRange:$("#variable").val(),
						customDate:$("#variableDefinDate").val(),
						//sql
						sql: ''
			 }
		 }else{
			 jsonResult = {
						//type:jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val(),
						//模板代码
					 	modelType:PARA_TEMPLATE_ZCCB ,
						
						//变量值
					 	//变量值
					 	valueRange:$("#variable").val(),
						customDate:$("#variableDefinDate").val(),
						//sql
						sql: ''
			 }
		 }
		 var redisField = "portfolioCode";
		 $("#redisField").val(redisField);
		 $("#content").val(JSON.stringify(jsonResult));
		 //设置模板值
		 $("#modelCode").val(parameterTemplate);
		 //设置模板变量值
		 if(parameterTemplate != PARA_TEMPLATE_FHMB){
			 if($("#variable").val()!=DATE_RANGE_TYPE_CUSTOM){
				 $("#modelValue").val($("#variable").select2('data')[0].text);
			 }else{
				 $("#modelValue").val($("#variableDefinDate").val());
			 }
		 }
	 }
	 function data003Display(){
	    	var contentDetail=$('#content').val();	
			var contentDetailJson = contentDetail==''?'':JSON.parse(contentDetail);
			if(contentDetailJson!=null){
				if(contentDetailJson.modelType==PARA_TEMPLATE_FHMB){
					if(contentDetailJson.value!=null&&contentDetailJson.value!=''){
						$("#value").val(contentDetailJson.value).trigger("change");
					}
					if(contentDetailJson.mdateType!=null&&contentDetailJson.mdateType!=''){
						$("#dateType").val(contentDetailJson.mdateType).trigger("change");
					}
					if(contentDetailJson.mdateRange!=null&&contentDetailJson.mdateRange!=''){
						$("#dateRange").val(contentDetailJson.mdateRange).trigger("change");
					}
					if(contentDetailJson.customDate!=null&&contentDetailJson.customDate!=''){
						$("#definDate").val(contentDetailJson.customDate).trigger("change");
					}
					if(contentDetailJson.bodyGradeType!=null&&contentDetailJson.bodyGradeType!=''){
						$("#subRateType").val(contentDetailJson.bodyGradeType).trigger("change");
					}
					if(contentDetailJson.bodyGrade!=null&&contentDetailJson.bodyGrade!=''){
						if(contentDetailJson.bodyGradeType=="01"){
							for(var i=0;i<contentDetailJson.bodyGrade.length;i++){
								contentDetailJson.bodyGrade[i] = "CHB_BODY_GRADE."+contentDetailJson.bodyGrade[i];
							}
						}
						$("#subRateType2").val(contentDetailJson.bodyGrade).trigger("change");
					}
					if(contentDetailJson.bondGradeType!=null&&contentDetailJson.bondGradeType!=''){
						$("#debRateType").val(contentDetailJson.bondGradeType).trigger("change");
					}
					if(contentDetailJson.bondGrade!=null&&contentDetailJson.bondGrade!=''){
						if(contentDetailJson.bondGradeType=="01"){
							for(var i=0;i<contentDetailJson.bondGrade.length;i++){
								contentDetailJson.bondGrade[i] = "CHB_BODY_GRADE."+contentDetailJson.bondGrade[i];
							}
						}
						$("#debRateType2").val(contentDetailJson.bondGrade).trigger("change");
					}
				}else{
					if(contentDetailJson.valueRange!=null&&contentDetailJson.valueRange!=''){
						$("#variable").val(contentDetailJson.valueRange).trigger("change");
					}
					if(contentDetailJson.customDate!=null&&contentDetailJson.customDate!=''){
						$("#variableDefinDate").val(contentDetailJson.customDate).trigger("change");
					}
				}
			}
			
	    }
    function validate(){
    	var messages="";
		var mesFlag=true;
		if($('#paraName').val()==''){
			 $('#paraName').addClass('errors');
			 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.paraName")) + '</span>'+'<br>';
			 mesFlag=false;
      	}else{
      		$('#paraName').removeClass('errors');
      	}
		if(parameterTemplate == PARA_TEMPLATE_FHMB){
			if(!$('#assetType').val()||$('#assetType').val()==''){
				$('#assetType').next().next().children().children().addClass("errors");
				//$('#assetType').addClass('errors');
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.assetType")) + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#assetType').next().next().children().children().removeClass("errors");
			}
			if(!$('#assetDetailType').val()||$('#assetDetailType').val()==""){
				$('#assetDetailType').children().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"资产二级类型") + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				var assetDetailType = JSON.parse($('#assetDetailType').val());
				if(assetDetailType&&assetDetailType.id.length==0){
					$('#assetDetailType').children().children().children().addClass("errors");
					 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"资产二级类型") + '</span>'+'<br>';
					 mesFlag=false;
				}else{
					$('#assetDetailType').next().children().children().removeClass("errors");
				}
			}/*
			if($('#assetSecondDetailType').val()==''){
				$('#assetSecondDetailType').addClass('errors');
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.assetType")) + '</span>'+'<br>';
				 mesFlag=false;
			}*/
			if($("#category").val()!=null&&$("#category").val()!=""){
				if(!$("#industry").val()||$("#industry").val()==""){
					$('#industry').next().children().children().addClass("errors");
					 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"所属行业二级类型") + '</span>'+'<br>';
					 mesFlag=false;
				}else{
					var industry = JSON.parse($("#industry").val());
					if(industry&&industry.id.length==0){
						$('#industry').next().children().children().addClass("errors");
						 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"所属行业二级类型") + '</span>'+'<br>';
						 mesFlag=false;
					}else{
						$('#industry').next().children().children().removeClass("errors");
					}
				}
			}
			if($("#industry").val()!=null&&$("#industry").val()!=''&&!$("#category").val()){
				var industry = JSON.parse($("#industry").val());
				if(industry&&industry.id.length!=0){
					$('#category').next().next().children().children().addClass("errors");
					 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"所属行业") + '</span>'+'<br>';
					 mesFlag=false;
				}
			}else{
				$('#category').next().next().children().children().removeClass("errors");
			}
			
			
			if($("#subRateType").val()!=null&&$("#subRateType").val()!=''&&!$("#subRateType2").val()){
				//$('#subRateType2').addClass('errors');
				$('#subRateType2').next().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"主体评级二级类型") + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#subRateType2').next().children().children().removeClass("errors");
			}
			if($("#subRateType2").val()!=null&&$("#subRateType2").val()!=''&&!$("#subRateType").val()){
				//$('#subRateType').addClass('errors');
				$('#subRateType').next().next().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"主体评级一级类型") + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#subRateType').next().next().children().children().removeClass("errors");
			}
			if($("#debRateType").val()!=null&&$("#debRateType").val()!=''&&!$("#debRateType2").val()){
				//$('#debRateType2').addClass('errors');
				$('#debRateType2').next().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"债项评级二级类型") + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#debRateType2').next().children().children().removeClass("errors");
			}
			if($("#debRateType2").val()!=null&&$("#debRateType2").val()!=''&&!$("#debRateType").val()){
				//$('#debRateType').addClass('errors');
				$('#debRateType').next().next().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',"债项评级一级类型") + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#debRateType').next().next().children().children().removeClass("errors");
			}
			if($('#value').val()==''){
				//$('#value').addClass('errors');
				$('#value').next().next().children().children().addClass("errors");
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.value")) + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#value').next().next().children().children().removeClass("errors");
			}
			if($('#dateRange').val()==DATE_RANGE_TYPE_CUSTOM && !$("#definDate").val()){
				$('#definDate').addClass('errors');
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.definDate")) + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#definDate').removeClass('errors');
			}
		}else{
			if($('#variable').val()==''){
				$('#variable').next().next().children().children().addClass("errors")
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.variable")) + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#variable').next().next().children().children().removeClass("errors")
			}
			if($('#variable').val()==DATE_RANGE_TYPE_CUSTOM && $("#variableDefinDate").val()==''){
				 $('#variableDefinDate').addClass('errors');
				 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicatorParam005.definDate")) + '</span>'+'<br>';
				 mesFlag=false;
			}else{
				$('#variableDefinDate').removeClass('errors');
			}
			
		}
		if(!mesFlag){
       		Joyin.error(messages.substring(0,messages.lastIndexOf('<br>')));
       		return false;
       	}
		return mesFlag
    }
    
    var init = function(){
    	var contentDetail=$('#content').val();
		var contentDetailJson = contentDetail==''?'':JSON.parse(contentDetail);
		if(contentDetailJson!=null){
			if(contentDetailJson.modelType==PARA_TEMPLATE_FHMB){
				$("#fhmbDiv").show();
	    		$("#jzzcDiv").hide();
	    		parameterTemplate = PARA_TEMPLATE_FHMB
			}else{
				$("#jzzcDiv").show();
	    		$("#fhmbDiv").hide();
	    		if(contentDetailJson.modelType==PARA_TEMPLATE_ZCSZ){
	    			$("#parameter").html("前N日净资产市值");
	    			parameterTemplate = PARA_TEMPLATE_ZCSZ;
	    		}else{
	    			$("#parameter").html("前N日净资产成本");
	    			parameterTemplate = PARA_TEMPLATE_ZCCB
	    		}
	    		$.ajax({
	                type: "POST",
	                url: basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005/getModelValue',
	                async: false,
	                data: {modelCode:parameterTemplate},
	                success: function (result) {
	                	$("#modelValueText").children().text(result);
	                }
	            });
			}
			if (contentDetailJson.assetDetailType) {
                $("#assetDetailType").selectTreeVal(contentDetailJson.assetDetailType.text);
            }
			if (contentDetailJson.industryType) {
                $("#industry").selectTreeVal(contentDetailJson.industryType.text);
            }
			if (contentDetailJson.assetType){
				$('#assetType').val(contentDetailJson.assetType).trigger("change");
			}
			if (contentDetailJson.category){
				$('#category').val(contentDetailJson.category).trigger("change");
			}
		}
    	if(entity!=null&&($("#workflowId").val()==null||$("#workflowId").val()=='')){
    		data003Display();
    	}
    	if($("#variable").val()==DATE_RANGE_TYPE_CUSTOM){
    		$("#variableDefinDate").attr("disabled",false);
    	}
    	if($("#dateRange").val()==DATE_RANGE_TYPE_CUSTOM){
    		$("#dateType").attr("disabled",false);
    		$("#definDate").attr("disabled",false);
    	}
    	
   }
    var dateRangeChange = function(value){
    	if(value == DATE_RANGE_TYPE_CUSTOM){
    		$("#dateType").attr("disabled",false);
    		$("#definDate").attr("disabled",false);
    		$("#variableDefinDate").attr("disabled",false);
    		
    	}else{
    		$("#dateType").attr("disabled",true);
    		$("#definDate").attr("disabled",true);
    		$("#variableDefinDate").attr("disabled",true);
    		$("#dateType").val('');
    		$("#definDate").val('');
    		$("#variableDefinDate").val('');
    	}
    	/*var paraName = $("#paraName").val();
    	if(!paraName){
    		if(parameterTemplate == PARA_TEMPLATE_ZCSZ){
    			if($("#variable").val()!=DATE_RANGE_TYPE_CUSTOM){
    				paraName = $("#variable").select2('data')[0].text+"净资产市值";
    			}
    		}
    		if(parameterTemplate == PARA_TEMPLATE_ZCCB){
    			if($("#variable").val()!=DATE_RANGE_TYPE_CUSTOM){
    				paraName = $("#variable").select2('data')[0].text+"净资产成本";
    			}
    		}
    		$("#paraName").val(paraName);
    	}*/
    };
    /*var variableDefinDateChange = function(value){
    	var paraName = $("#paraName").val();
    	if(!paraName&&value&&value!=''){
    		if(parameterTemplate == PARA_TEMPLATE_ZCSZ){
    			paraName = "前"+value+"日净资产市值";
    		}
    		if(parameterTemplate == PARA_TEMPLATE_ZCCB){
    			paraName = "前"+value+"日净资产成本";
    		}
    		$("#paraName").val(paraName);
    	}
    }*/
    var dateChange = function(id){
    	debugger;
    	var value = $("#"+id).val();
    	if(value.indexOf(".")!=-1){
    		value = value.substring(0,value.indexOf("."));
    	}
    	if(value.length>6){
    		value = value.substring(0,6);
    	}
    	if(value<1){
    		value = '';
    	}
    	$("#"+id).val(value);
    }
    $(function() {
    	init();
        // 按钮事件
        $('#RskSupervisionIndicatorParam005_btn_save').click(function() {
        	if(!validate()){
        		return;
        	};
        	abJson();
            saveOrSubmit('save');
        });
        $('#RskSupervisionIndicatorParam005_btn_submit').click(function() {
        	if(!validate()){
        		return;
        	};
        	abJson();
            saveOrSubmit('submit');
        });
        $("#dateRange").change(function(){
        	dateRangeChange($("#dateRange").val());
        });
        $("#variable").change(function(){
        	dateRangeChange($("#variable").val());
        });
        $("#variableDefinDate").change(function(){
        	dateChange("variableDefinDate");
        	//variableDefinDateChange($("#variableDefinDate").val());
        });
        $("#definDate").change(function(){
        	dateChange("definDate");
        });
        $("#assetType").change(function(){
        	$("#assetDetailTypeShow").attr("value","");
        	treeNode = false;
        });
        $("#category").change(function(){
        	$("#industryShow").attr("value","");
        	treeNode = false;
        })
    });
}());

function changeContent(id){
	if(pageModel!=PAGE_MODEL_REFERENCE&&pageModel!=PAGE_MODEL_EDIT){
		if(id.textContent == '复合模板'){
			$("#fhmbDiv").show();
			$("#jzzcDiv").hide();
			parameterTemplate = PARA_TEMPLATE_FHMB
			
		}else{
			$("#jzzcDiv").show();
			$("#fhmbDiv").hide();
			$("#parameter").html(id.textContent);
			if(id.textContent == '前N日净资产市值'){
				parameterTemplate = PARA_TEMPLATE_ZCSZ;
			}else{
				parameterTemplate = PARA_TEMPLATE_ZCCB;
			}
			$.ajax({
                type: "POST",
                url: basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005/getModelValue',
                async: false,
                data: {modelCode:parameterTemplate},
                success: function (result) {
                	$("#modelValueText").children().text(result);
                }
            });
		}
	}
	
}