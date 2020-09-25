/**
 * 条件新建
 */
$(function() {
	var digitFlag = false;
	/**
     * 定义数据表格
     */
    //var addbtn = (pageModel == PAGE_MODEL_NEW) || ($("#wfl_operType").val() == 'NEW');
	var options = {
		//page: true,
        addbtn:true,
        height:400,
        checkbox:false,
        editCell : function(value, data, field, cell, column){
        	var index = data.LAY_TABLE_INDEX;
        	if(field == 'alarmOper' && index>0){
        		var selArr = table.getAllRows();
        		for(var i=0;i<index;i++){
        			if(selArr[i].alarmOper==value){
        				Joyin.warn(JoyinUtil.getMessage('RSKRDS_W_0034',JoyinUtil.getLabel("RskSupervisionIndividuation003.alarmOper")));
        				table.deleteRow(data.LAY_TABLE_INDEX);
        				return false;
        			}
        		}
        		return true;
        	}
        	if(field == 'depreciation'){
        		var unit = $("#unit").val();
        		value = RskQuotaCommon.formatDepreciation(value, data, cell, unit);
        		var rowData = {alarmOper:data.alarmOper?data.alarmOper:"",depreciation:value};
        		setTimeout(function(){table.renderRow(data.LAY_TABLE_INDEX, rowData);},500);
        		return;
        	}
           	return true;
           	},
    	cols:[
             {field:'alarmOper' ,title: 'RskSupervisionIndividuation003.alarmOper', edit:true
            	 ,editOption:{type:'select',id:"alarmOper",formatoption:true,result:ALARM_OPER}, sortable:false
             }//触警操作
             
             ,{field:'depreciation' , title: 'RskSupervisionIndividuation003.Depreciation', edit:true, type:"custom",sortable:false, width: 400
             }//阙值
             ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['delt']}
         ]
         
    };
	function dataDisplay(){
		 
		/* var detail=jqModal.find('[name="detail"]').val();	
		 var detailJson = JSON.parse(detail);*/
		var warnDetail=$("#warnCond").val();	
		var warnDetailJson = warnDetail==''?'':JSON.parse(warnDetail);
		var foulDetail=$("#foulCond").val();	
		var foulDetailJson = foulDetail==''?'':JSON.parse(foulDetail);
		var approveDetail=$("#approveCond").val();	
		var approveDetailJson = approveDetail==''?'':JSON.parse(approveDetail);
		 // 限制型 
		 if($("#indType").val().indexOf("LIMIT_")!=-1){
			 if(warnDetailJson.threshold_1.value!=null&&warnDetailJson.threshold_1.value!=""){
				 //预警
					var row1 = {alarmOper:cjcz_yj,depreciation:warnDetailJson.threshold_1.value};
					setTimeout(function(){$('#dataTable').data("joyintable").addRow(row1);},1000);
			 }
			 if(approveDetailJson.threshold_1.value!=null&&approveDetailJson.threshold_1.value!=""){
				//审批
					var row2 = {alarmOper:cjcz_sp,depreciation:approveDetailJson.threshold_1.value};
					setTimeout(function(){$('#dataTable').data("joyintable").addRow(row2);},1000);
			 }
			 if(foulDetailJson.threshold_1.value!=null&&foulDetailJson.threshold_1.value!=""){
					//禁止
					var row3 = {alarmOper:cjcz_jz,depreciation:foulDetailJson.threshold_1.value};
					setTimeout(function(){$('#dataTable').data("joyintable").addRow(row3);},1000);
			 }
			
			setTimeout(function(){$('#dataTable').data("joyintable").addRow(selArr);},1000);
			$("#comparison").val(warnDetailJson.threshold_1.op).trigger('change');
			$("#unit").val(warnDetailJson.threshold_1.unit).trigger('change');
			//jqModal.find('[name="rskSupervisionIndicatorDto.isModel"]').val(warnDetailJson.threshold_1.is_model).trigger('change');
			
		 }else{
				var arrObj = foulDetailJson.threshold_1.value.split(",");
				var jzx = jqModal.find('[name="jzx"]');
				for(var i=0;i<arrObj.length;i++){
					for(var j=0; j<jzx.length; j++){
						if(jzx[j].value==arrObj[i]){
							jzx[j].checked = true;
						}
					}
				}
				layui.form.render('checkbox')
				
		 }
	}
	//预警条件，违规条件，禁止条件封装成json串
	function abJson(){
		var jsonResult={};
		 //限制型 
		if($("#indType").val().indexOf("LIMIT_")!=-1){
			var unit = $("#unit").val();
			var comparison = $("#comparison").val();
			var selArr = table.getAllRows();
			var alarmOper1;
			var alarmOper2;
			var alarmOper3;
			//预警值
			var value1;
			//审批值
			var value2;
			//禁止值
			var value3;
			
			//取出表格中的预警，违规，审批数据
			for(var i=0;i<selArr.length;i++){
				if(selArr[i].alarmOper == cjcz_yj){
					value1 = selArr[i].depreciation;
					
				}
				if(selArr[i].alarmOper == cjcz_sp){
					value2 = selArr[i].depreciation;
					
				}
				if(selArr[i].alarmOper == cjcz_jz){
					value3 = selArr[i].depreciation;
				}
			}
			
			jsonResult = {
							//type:jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val(),
							//预警
							warning: {
								threshold_1:{
									op:comparison,
									value:value1,
									unit:unit
									}
								},
							//禁止
							violation: {
							    threshold_1: {
							    	op:comparison,
									value:value3,
									unit:unit
							    }
							},
							//审批
							approve: {
							    threshold_1: {
							    	op:comparison,
									value:value2,
									unit:unit
							    }
							}
			}
			//jsonResult = JSON.stringify(jsonResult);
			$("#warnCond").val(JSON.stringify(jsonResult.warning));
			$("#foulCond").val(JSON.stringify(jsonResult.violation));
			$("#approveCond").val(JSON.stringify(jsonResult.approve));
		}else{
			var jzx = jqModal.find('[name="jzx"]');
			var s='';
			for(var i=0; i<jzx.length; i++){
				if(jzx[i].checked) s+=jzx[i].value+','; //如果选中，将value添加到变量s中
			} 
			
			jsonResult = {
				violationForbid: {
					threshold_1: {
					      value: s
					}
				}
			}
			//jsonResult = JSON.stringify(jsonResult);
			//-----add by 裴家俊 2019/03/25------start-----
			$("#warnCond").val('');
			$("#approveCond").val('');
			//-----add by 裴家俊 2019/03/25------start-----
			$("#foulCond").val(JSON.stringify(jsonResult.violationForbid));
		}
		
	};
	
	//必填项 
	function requiredW(){
		var pointType = $("#indType").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(!startDate){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"开始时间"));
			return false;
		}
		if(!endDate){
			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"结束时间"));
			return false;
		}
		if(startDate>endDate){
			Joyin.warn("开始时间不允许大于结束时间!");
			return false;
		}
		if(pointType.indexOf("LIMIT_")!=-1){
			if(table!=null && table!=''){
                var selArr = table.getAllRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.warn("表格不能为空!");
                return;
            }else{
            	var comparison = $("#comparison").val();
    			var unit = $("#unit").val();
    			if(!comparison){
    				Joyin.error(JoyinUtil.getMessage('FW_E_0006',"比较符"));
    				return false;
    			}
    			if(!unit){
    				Joyin.error(JoyinUtil.getMessage('FW_E_0006',"单位"));
    				return false;
    			}
            	var alarmOper = "";
            	for(var i=0;i<selArr.length;i++){
            		for(var j=i+1;j<selArr.length;j++){
            			if(selArr[i].alarmOper==selArr[j].alarmOper){
            				Joyin.warn(JoyinUtil.getMessage('RSKRDS_W_0034',JoyinUtil.getLabel("RskSupervisionIndividuation003.alarmOper")));
            				return false;
            			}
            		}
            		if(!selArr[i].alarmOper||selArr[i].alarmOper==''){
            			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"触警操作"));
            			return false;
            		}
            		if(!selArr[i].depreciation||selArr[i].depreciation==''){
            			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"阙值"));
            			return false;
            		}
            		alarmOper = alarmOper+selArr[i].alarmOper;
            	}
            	
            }
			
		}else{
			var jzx = jqModal.find('input[name="jzx"]');
			var s='';var flag=true;
			for(var i=0; i<jzx.length; i++){
				if(jzx[i].checked) {
					flag=false; //如果选中，将value添加到变量s中
				}
			}
			if(flag){
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0011"));
				return false;
			}
		}
		
		return true;
	};
	/*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
	/**
     * 
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
   	 table.deleteRow(index);
    });
	function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
        object.pageId = "RskSupervisionIndividuation003";
        object.pageModel = pageModel;
        top.returnData=object;
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
	function init(pointType){
	     //----初始化单位与阈值方向----start---
	     initUnitOrDirection();
	     //----初始化单位与阈值方向----end----
		if(entity!=null&&pageModel!=PAGE_MODEL_NEW){
			dataDisplay();
		}
	}
	/*
	  初始化单位与阈值方向
	*/
	var initUnitOrDirection=function(){
	    var warnDetail=$('#submitForm').find('[id="oldWarnCond"]').val();
        var warnDetailJson = warnDetail==''?'':JSON.parse(warnDetail);
        var foulDetail=$('#submitForm').find('[id="oldFoulCond"]').val();
        var foulDetailJson = foulDetail==''?'':JSON.parse(foulDetail);
        var approveDetail=$('#submitForm').find('[id="oldApproveCond"]').val();
        var approveDetailJson = approveDetail==''?'':JSON.parse(approveDetail);
	     var comparison = '';
         var unit = '';
         //预警
         if(warnDetailJson.threshold_1.value!=null&&warnDetailJson.threshold_1.value!=''){
             comparison = warnDetailJson.threshold_1.op;
             unit = warnDetailJson.threshold_1.unit;
         }
        //审批
        if(approveDetailJson.threshold_1.value!=null&&approveDetailJson.threshold_1.value!=''){
            comparison = approveDetailJson.threshold_1.op;
            unit =  approveDetailJson.threshold_1.unit;
        }
        //禁止
        if(foulDetailJson.threshold_1.value!=null&&foulDetailJson.threshold_1.value!=''){
            comparison = foulDetailJson.threshold_1.op;
            unit =  foulDetailJson.threshold_1.unit;
        }
        $("#comparison").val(comparison).trigger('change').attr("disabled",true);
        $("#unit").val(unit).trigger('change').attr("disabled",true);
	}

    $(function() {
    	digitFlag = entity.unit=="YEAR";
    	init();
        // 按钮事件
        $('#RskSupervisionIndividuation003_btn_save').click(function() {
        	if(!requiredW()){
    			return;
		 	}else{
		 		abJson();
		 	}
            saveOrSubmit('save');
        });
        /*$("#conditionItem").change(function(){
        	setUnit();
        })*/
    });
}());