(function() {
	/**
     * 定义数据表格
     */
    var addbtn = true;
	var options = {
		page: false,
		data:[],
        addbtn:addbtn,
        addToLast:true,
        searchOnInit: true,
        checkbox:false,
        height:260,
        editCell : function(value, data, field, cell, column){
        	var index = data.LAY_TABLE_INDEX;
        	if(field == 'alarmOper' && index>0){
        		var selArr = table.getAllRows();
        		for(var i=0;i<index;i++){
        			if(selArr[i].alarmOper==value){
        				Joyin.warn(JoyinUtil.getMessage('RSKRDS_W_0034',JoyinUtil.getLabel("RskSupervisionIndicator002.alarmOper")));
        				return false;
        			}
        		}
        		return true;
        	}
        	
        	if(field == 'depreciation'){
        		var unit = $("#RskSupervisionIndicator002_unit").val();
        		value = RskQuotaCommon.formatDepreciation(value, data, cell, unit);
        		var rowData = {alarmOper:data.alarmOper,depreciation:value};
        		setTimeout(function(){table.renderRow(data.LAY_TABLE_INDEX, rowData);},100);
//        		return;
        	}
           	return true;
           	},
    	cols:[
             {field:'alarmOper' ,title: 'RskSupervisionIndicator002.alarmOper', edit:true,width:470
            	 ,editOption:{type:'select',id:"alarmOper",formatoption:true,result:ALARM_OPER}, sortable:false
             }//触警操作
             ,{field:'depreciation' , width:315,title: 'RskSupervisionIndicator002.Depreciation', edit:true, sortable:false
             }//阙值
             ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['delt']}
         ],
         
    };
	
	/*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
	var jqModal = $('#rskSupervisionIndicatorForm');	
	var lnkNo = "";
	  function data003Display(){
		/* var detail=jqModal.find('[name="detail"]').val();	
		 var detailJson = JSON.parse(detail);*/
		var warnDetail=jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val();	
		var warnDetailJson = warnDetail==''?'':JSON.parse(warnDetail);
		var foulDetail=jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val();	
		var foulDetailJson = foulDetail==''?'':JSON.parse(foulDetail);
		var approveDetail=jqModal.find('[name="rskSupervisionIndicatorDto.approveCond"]').val();	
		var approveDetailJson = approveDetail==''?'':JSON.parse(approveDetail);
		 // 限制型 
		 if(entity.rskSupervisionIndicatorDto.indType.indexOf("LIMIT_")!=-1){
			 var comparison = '';
			 var unit = '';
			 var is_model = '';
			 //预警
			 if(warnDetailJson.threshold_1.value!=null&&warnDetailJson.threshold_1.value!=''){
				 var row1 = {alarmOper:cjcz_yj,depreciation:warnDetailJson.threshold_1.value};
				 comparison = warnDetailJson.threshold_1.op;
				 unit = warnDetailJson.threshold_1.unit;
				 is_model = warnDetailJson.threshold_1.is_model;
				 setTimeout(function(){$('#dataTable').data("joyintable").addRow(row1);},1000);
			 }
			//审批
			if(approveDetailJson.threshold_1.value!=null&&approveDetailJson.threshold_1.value!=''){
				var row2 = {alarmOper:cjcz_sp,depreciation:approveDetailJson.threshold_1.value};
				comparison = approveDetailJson.threshold_1.op;
				unit =  approveDetailJson.threshold_1.unit;
				is_model = approveDetailJson.threshold_1.is_model;
				setTimeout(function(){$('#dataTable').data("joyintable").addRow(row2);},1000);
			}
			//禁止
			if(foulDetailJson.threshold_1.value!=null&&foulDetailJson.threshold_1.value!=''){
				var row3 = {alarmOper:cjcz_jz,depreciation:foulDetailJson.threshold_1.value};
				comparison = foulDetailJson.threshold_1.op;
				unit =  foulDetailJson.threshold_1.unit;
				is_model = foulDetailJson.threshold_1.is_model;
				setTimeout(function(){$('#dataTable').data("joyintable").addRow(row3);},1000);
			}
			//var selArr = [row1,row2,row3];
			
			$("#RskSupervisionIndicator002_comparison").val(comparison).trigger('change');
			$("#RskSupervisionIndicator002_unit").val(unit).trigger('change');
			if(is_model=="01"){
				jqModal.find('[name="rskSupervisionIndicatorDto.isModel"]').prop("checked","checked");
				layui.form.render('checkbox');
			}
			//jqModal.find('[name="rskSupervisionIndicatorDto.isModel"]').val(warnDetailJson.threshold_1.is_model).trigger('change');
			
		 }else if(entity.rskSupervisionIndicatorDto.indType.indexOf("FORBID_")!=-1){
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
				//---add by jjpei 20190812---反显禁投模式-----start----
			 	var fb_model = foulDetailJson.threshold_1.fb_model;
				 jqModal.find('[name="forbidOrAgreeRadio"][value='+fb_model+']').attr("checked",'checked');
				 layui.form.render('radio');
			   //---add by jjpei 20190812---反显禁投模式-----end----
		 }
	}
	var supDirectionShow = function(){
	    $("#RskSupervisionIndicator002_supDirection").val('');
    	$("#supDirectionDiv").hide();
		/*//指标类型
		var intType = $("#RskSupervisionIndicator002_indType").val();
		//监控时点
		var timePoint = jqModal.find('[name="rskSupervisionIndicatorDto.timePoint"]');
		if((intType==jyekz||intType==zxjgkz||intType==drjyl)&&
				(timePoint[0].checked||timePoint[1].checked)){
			$("#supDirectionDiv").show();
		}else{
			$("#RskSupervisionIndicator002_supDirection").val('');
			$("#supDirectionDiv").hide();
		}*/
	}
	//展现违规条件和预警条件
    function show_cond_add(v){
		if(v.indexOf("LIMIT_")>=0){
			$("#xzx_qzsz_div").show();
			$("#jzx_wg_div").hide();
			$("#inner_wg_div").hide();
		}else if(v.indexOf("INNER_")>=0){
			$("#inner_wg_div").show();
			$("#xzx_qzsz_div").hide();
			$("#jzx_wg_div").hide()
		}else{
			$("#xzx_qzsz_div").hide();
			$("#inner_wg_div").hide();
			$("#jzx_wg_div").show();
			//var arrFobid=JSON.parse(relateIndType).filter(item => item.id===v);
            //----update by jjpei 20190811---注销原有的，在指标参数中生成动态的---start-----
		/*	var arrFobid=_.filter(JSON.parse(relateIndType),function(item){
				return item.id===v
			});
			 Joyin.ajaxSync(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator002/getIndTypeRelateItems', {'dictCode':arrFobid[0].text}, function(result) {
					var divJzd=$("#jztz_zclx");
					divJzd.empty();
					var reData=eval(result);
					var inputStr='<div class="element"><ul class="manage-info-item">'
					for (var i = 0; i < reData.length; i++){
						inputStr+='<li><div>'
						inputStr+='<input type="checkbox"  name="jzx" value="'+reData[i].id+'">' + reData[i].text
						inputStr+='</div></li>'
					}	
					inputStr+='</ul></div>';
					divJzd.append(inputStr);
					//进行js中拼接的html代码页面渲染（因为不是在html写的，是js动态构造的，不能自动渲染）
			    	layui.form.render('checkbox')
					
			});*/
            //----update by jjpei 20190811---注销原有的，在指标参数中生成动态的---end-----
		}
	}
	function show_lnk1(v,type){
		if(type==0)//重置
		{
			jqModal.find('[name="value1"]').val("").trigger('change');
			jqModal.find('[name="op1"]').val(">").trigger('change');
			jqModal.find('[name="op2"]').val("").trigger('change');
			jqModal.find('[name="op2"]').attr("disabled",true);
			jqModal.find('[name="value2"]').val("").trigger('change');
			jqModal.find('[name="value2"]').attr("disabled",true);
			jqModal.find('[name="lnk1"]').val("-").trigger('change');
			jqModal.find('[name="unit1"]').val("-").trigger('change');
			jqModal.find('[name="unit2"]').val("-").trigger('change');
			jqModal.find('[name="unit2"]').attr("disabled",true);
		}
		else{
			if(v==lnkNo||v==""){
				jqModal.find('[name="op2"]').val("").trigger('change');
				jqModal.find('[name="op2"]').attr("disabled",true);
				jqModal.find('[name="value2"]').val("").trigger('change');
				jqModal.find('[name="value2"]').attr("disabled",true);
				jqModal.find('[name="unit2"]').val("").trigger('change');
				jqModal.find('[name="unit2"]').attr("disabled",true);
			}else{
				jqModal.find('[name="op2"]').attr("disabled",false);
				jqModal.find('[name="value2"]').attr("disabled",false);
				jqModal.find('[name="unit2"]').attr("disabled",false);
				if(type!=1){
					jqModal.find('[name="op2"]').val(">").trigger('change');
				}
			}
		}
	}

	function show_lnk2(v,type){
		if(type==0){//重置
			jqModal.find('[name="value3"]').val("").trigger('change');
			jqModal.find('[name="op3"]').val(">").trigger('change');
			jqModal.find('[name="op4"]').val("").trigger('change');
			jqModal.find('[name="op4"]').attr("disabled",true);
			jqModal.find('[name="value4"]').val("").trigger('change');
			jqModal.find('[name="value4"]').attr("disabled",true);
			jqModal.find('[name="lnk2"]').val("-").trigger('change');
			jqModal.find('[name="unit3"]').val("-").trigger('change');
			jqModal.find('[name="unit4"]').val("-").trigger('change');
			jqModal.find('[name="unit4"]').attr("disabled",true);
		}
		else{
			if(v==lnkNo||v==""){
				jqModal.find('[name="op4"]').val("").trigger('change');
				jqModal.find('[name="op4"]').attr("disabled",true);
				jqModal.find('[name="value4"]').val("").trigger('change');
				jqModal.find('[name="value4"]').attr("disabled",true);
				jqModal.find('[name="unit4"]').val("").trigger('change');
				jqModal.find('[name="unit4"]').attr("disabled",true);
			}else{
				jqModal.find('[name="op4"]').attr("disabled",false);
				jqModal.find('[name="value4"]').attr("disabled",false);
				jqModal.find('[name="unit4"]').attr("disabled",false);
				if(type!=1){
					jqModal.find('[name="op2"]').val(">").trigger('change');
				}
			}
		}
	}
	
	 //必填项 
	function requiredW(){
		var pointType = jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val();
		if(pointType.indexOf("LIMIT_")!=-1){
			if(table!=null && table!=''){
                var selArr = table.getAllRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.warn("表格不能为空!");
                return;
            }else{
            	var alarmOper = "";
            	for(var i=0;i<selArr.length;i++){
            		for(var j=i+1;j<selArr.length;j++){
            			if(selArr[i].alarmOper==selArr[j].alarmOper){
            				Joyin.warn(JoyinUtil.getMessage('RSKRDS_W_0034',JoyinUtil.getLabel("RskSupervisionIndicator002.alarmOper")));
            				return false;
            			}
            		}
            		if(!selArr[i].depreciation||selArr[i].depreciation==''){
            			Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"阙值"));
            			return false;
            		}
            		alarmOper = alarmOper+selArr[i].alarmOper;
            	}
            	if(alarmOper.indexOf('01')==-1&&alarmOper.indexOf('02')==-1&&alarmOper.indexOf('03')==-1){
            		Joyin.warn(JoyinUtil.getMessage('FW_E_0006',"预警操作"));
    				return false;
            	}
            }
			var comparison = $("#RskSupervisionIndicator002_comparison").val();
			var unit = $("#RskSupervisionIndicator002_unit").val();
			if(!comparison){
				Joyin.error(JoyinUtil.getMessage('FW_E_0006',"比较符"));
				return false;
			}
			if(!unit){
				Joyin.error(JoyinUtil.getMessage('FW_E_0006',"单位"));
				return false;
			}

		}else if (pointType.indexOf("INNER_")!=-1){
			if(!$("#RskSupervisionIndicator002_compareDays").val()){
				Joyin.error(JoyinUtil.getMessage('FW_E_0006',"比较天数"));
				return false;
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
	//预警条件，违规条件，禁止条件封装成json串
	function abJson(){
		var jsonResult={};
		 //限制型 
		if(jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val().indexOf("LIMIT_")!=-1){
			debugger;
			var comparison = $("#RskSupervisionIndicator002_comparison").val();
			var unit = $("#RskSupervisionIndicator002_unit").val();
			var isModel = "";
			var isModel_c=jqModal.find('[name="rskSupervisionIndicatorDto.isModel"]');
			if(isModel_c[0].checked){
				//01 处理为绝对值
				isModel = "01";
			}else{
				isModel = "02";
			}
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
									unit:unit,
									is_model:isModel
									}
								},
							
							//禁止
							violation: {
							    threshold_1: {
							    	op:comparison,
									value:value3,
									unit:unit,
									is_model:isModel
							    }
							},
							//审批
							approve: {
							    threshold_1: {
							    	op:comparison,
									value:value2,
									unit:unit,
									is_model:isModel
							    }
							}
			}
			//jsonResult = JSON.stringify(jsonResult);
			jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val(JSON.stringify(jsonResult.warning));
			jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val(JSON.stringify(jsonResult.violation));
			jqModal.find('[name="rskSupervisionIndicatorDto.approveCond"]').val(JSON.stringify(jsonResult.approve));
		}else if(jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val().indexOf("FORBID_")!=-1){
			var jzx = jqModal.find('[name="jzx"]');
			var s='';
			//--add by jjpei 20190812---中文及禁投模式---start----
			var s_cn='';
			var fb_model = jqModal.find('[name="forbidOrAgreeRadio"]:checked').val();
			//--add by jjpei 20190812---中文及禁投模式----end----
			for(var i=0; i<jzx.length; i++){
				if(jzx[i].checked) {
					s+=jzx[i].value+','
					s_cn+=jzx.eq(i).attr('data-text')+','
				}; //如果选中，将value添加到变量s中
			} 
			
			jsonResult = {
				violationForbid: {
					threshold_1: {
					      value: s,
						  value_cn: s_cn,
						fb_model:fb_model
					}
				}
			}
			//jsonResult = JSON.stringify(jsonResult);
			//-----add by 裴家俊 2019/03/25------start-----
			jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val('');
			jqModal.find('[name="rskSupervisionIndicatorDto.approveCond"]').val('');
			//-----add by 裴家俊 2019/03/25------start-----
			jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val(JSON.stringify(jsonResult.violationForbid));
		}
		
	};
	
 	$('#' + pageId + '_indType').change(function(){
		var pointType=$(this).val();
		if(pointType!=''){
			show_cond_add(pointType);
		}
		//监控方向显示事件
		supDirectionShow();
	})
	
	
	
	layui.form.on('checkbox(03_timePoint)', function(data){
            if(data.elem.checked){
            	$("#frequencyDiv").show();
            }
            else {
            	$("select[name='rskSupervisionIndicatorDto.supFrequency']").val("").trigger('change');
            	$("#frequencyDiv").hide();
            }
        });
	layui.form.on('checkbox(01_timePoint)', function(data){
		//监控方向显示事件
		supDirectionShow();
    });
	layui.form.on('checkbox(02_timePoint)', function(data){
		//监控方向显示事件
		supDirectionShow();
    });
    
    function getTimePoint(){
    	var timePoint_c=jqModal.find('[name="rskSupervisionIndicatorDto.timePoint"]');
    	var s='';	
		for(var i=0; i<timePoint_c.length; i++){
			if(timePoint_c[i].checked) 
				s+=timePoint_c[i].value+','; //如果选中，将value添加到变量s中
         }
		s =s.substring(0,s.length-1);
		if(s==''){
			Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0012"));
			return false;
		}	
		if(s.indexOf('03')!=-1){
			if(!jqModal.find('[name="rskSupervisionIndicatorDto.supFrequency"]').val()){
				Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.supFrequency")));
       		 	return false;
			}
		}
		//监督类型
		if(null==jqModal.find('[name="rskSupervisionIndicatorDto.supType"]').val()||jqModal.find('[name="rskSupervisionIndicatorDto.supType"]').val()==''){
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.supType")));
			return false;
		}
		//指标类型
		var intType = $("#RskSupervisionIndicator002_indType").val();
		//----------update by 裴家俊 2019 --------------------
		/*if((intType==jyekz||intType==zxjgkz||intType==drjyl)&&(s.indexOf('01')!=-1||s.indexOf('02')!=-1)){
			if(jqModal.find('[name="rskSupervisionIndicatorDto.supDirection"]').val()==''){
				Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.supDirection")));
       		 	return false;
			}
		}*/
		return true;
    }
    
 // 开放计划公开接口
    window.RskSupervisionIndicator003 = {
    	requiredW:requiredW,
    	abJson: abJson,
    	getTimePoint:getTimePoint
    };
	
    function init(pointType){
    	if($("input[name='rskSupervisionIndicatorDto.timePoint'][value='03']").prop('checked')) {
            $('#frequencyDiv').show();
        }
        else {
            $('#frequencyDiv').hide();
        }
    	supDirectionShow();
    	if(entity!=null){		
			//----add by jjpei 20190812--start-保存之后显示禁投的预警还是阈值的预警画面
			show_cond_add($('#' + pageId + '_indType').val());
			//----add by jjpei 20190812--end--保存之后显示禁投的预警还是阈值的预警画面
    		data003Display();   //修改操作时，页面信息反显
    	}else{
    		jqModal.find('[name="rskSupervisionIndicatorDto.supType"][value="SUPERVISION"]').attr("checked",'checked');
        	layui.form.render('radio');
        	//---add by jjpei 20190812---默认选择禁投项--start----
			jqModal.find('[name="forbidOrAgreeRadio"][value="Y"]').attr("checked",'checked');
			layui.form.render('radio');
			//---add by jjpei 20190812---默认选择禁投项--end----
			//---add by jjpei 20190812---默认选择禁投项--start----
    		show_lnk1(pointType,0);//新增时，对操作符，连接符的控制
    		show_lnk2(pointType,0)
    	}
    }
    /**
     * 画面加载完成
     */
    $(function() { 
    	
    	var pointType =jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val();
        
        init(pointType);//初始化操作
        
        if(pageModel != PAGE_MODEL_REFERENCE){
        	 $("#jiduLabel").append('<i class="must">*</i>');
             $("#jikongLabel").append('<i class="must">*</i>');
		 }
        $("#RskSupervisionIndicator002_unit").change(function(){
        	table.clear();
        })
        //---add by jjpei 20190811---全选禁投项-----
        layui.form.on('checkbox(forbid_SelChk)', function(data){
            var checkFlag=true;
            if(!data.elem.checked){
                checkFlag=false;
            }
            var jzx = jqModal.find('[name="jzx"]');
            for(var j=0; j<jzx.length; j++){
                jzx[j].checked = checkFlag;
            }
            layui.form.render('checkbox')
        });
        //---add by jjpei 20190811---全选禁投项-----
    });
}());