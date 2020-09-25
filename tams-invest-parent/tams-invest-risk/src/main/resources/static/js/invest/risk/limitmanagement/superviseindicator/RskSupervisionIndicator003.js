(function() {
	var jqModal = $('#rskSupervisionIndicatorForm');	
	var lnkNo = "";
	/*var relateIndType={
			'FORBID_INDUSTRY':'SW_INDUSTRY_ONE_TYPE',
			'FORBID_ASSETTYPE':'FINPROD_TYPE_TG',
			'FORBID_BONDTYPE':'WINDTYPEONE',
			'FORBID_FUNDTYPE':'FUND_ASSET_SECOND_TYPE',
			'FORBID_DEBTRATE':'CHB_BODY_GRADE',
			'FORBID_BODYRATE':'CHB_BODY_GRADE'
		};*/
	  function data003Display(){
		/* var detail=jqModal.find('[name="detail"]').val();	
		 var detailJson = JSON.parse(detail);*/
		var warnDetail=jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val();	
		var warnDetailJson = warnDetail==''?'':JSON.parse(warnDetail);
		var foulDetail=jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val();	
		var foulDetailJson = foulDetail==''?'':JSON.parse(foulDetail);
		/*var probitDetail=jqModal.find('[name="rskSupervisionIndicatorDto.prohibitProj"]').val();	
		var probitDetailJson = probitDetail==''?'':JSON.parse(probitDetail);*/
		 // 限制型 
		 if(entity.rskSupervisionIndicatorDto.indType.indexOf("LIMIT_")!=-1){
			jqModal.find('[name="lnk1"]').val(warnDetailJson.threshold_2.lnk).trigger('change');
			jqModal.find('[name="lnk2"]').val(foulDetailJson.threshold_2.lnk).trigger('change');
			jqModal.find('[name="op1"]').val(warnDetailJson.threshold_1.op).trigger('change');
			jqModal.find('[name="op2"]').val(warnDetailJson.threshold_2.op).trigger('change');
			jqModal.find('[name="op3"]').val(foulDetailJson.threshold_1.op).trigger('change');
			jqModal.find('[name="op4"]').val(foulDetailJson.threshold_2.op).trigger('change');
			jqModal.find('[name="value1"]').val(warnDetailJson.threshold_1.value).trigger('change');
			jqModal.find('[name="value2"]').val(warnDetailJson.threshold_2.value).trigger('change');
			jqModal.find('[name="value3"]').val(foulDetailJson.threshold_1.value).trigger('change');
			jqModal.find('[name="value4"]').val(foulDetailJson.threshold_2.value).trigger('change');
		    jqModal.find('[name="unit1"]').val(warnDetailJson.threshold_1.unit).trigger('change');
			jqModal.find('[name="unit2"]').val(warnDetailJson.threshold_2.unit).trigger('change');
			jqModal.find('[name="unit3"]').val(foulDetailJson.threshold_1.unit).trigger('change');
			jqModal.find('[name="unit4"]').val(foulDetailJson.threshold_2.unit).trigger('change'); 
			show_lnk1(warnDetailJson.threshold_2.lnk,1);
			show_lnk2(foulDetailJson.threshold_2.lnk,1);
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
				//---add by jjpei 20190812---反显禁投模式-----start----
			 	var isModel = foulDetailJson.threshold_1.is_model;
				 jqModal.find('[name="forbidOrAgreeRadio"][value='+isModel+']').attr("checked",'checked');
				 layui.form.render('radio');
			   //---add by jjpei 20190812---反显禁投模式-----end----
		 }
	}
	
	//展现违规条件和预警条件
    function show_cond_add(v){
		if(v.indexOf("LIMIT_")>=0){
			$("#xzx_yj_div").show();
			$("#xzx_wg_div").show();
			$("#jzx_wg_div").hide();
		}else{

			$("#xzx_yj_div").hide();
			$("#xzx_wg_div").hide();
			$("#jzx_wg_div").show();
			//var arrFobid=JSON.parse(relateIndType).filter(item => item.id===v);
            //----update by jjpei 20190811---注销原有的，在指标参数中生成动态的---start-----
		/*	var arrFobid=_.filter(JSON.parse(relateIndType),function(item){
				return item.id===v
			});
			 Joyin.ajaxSync(basePath + 'invest/risk/limitmanagement/superviseindicator/RskSupervisionIndicator002/getIndTypeRelateItems', {'dictCode':arrFobid[0].text}, function(result) {
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
			var reg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");  
			var lnk1 = jqModal.find('[name="lnk1"]').val();
			var lnk2 = jqModal.find('[name="lnk2"]').val();
			var val1 = jqModal.find('[name="value1"]').val();
			var val2 = jqModal.find('[name="value2"]').val();
			var val3 = jqModal.find('[name="value3"]').val();
			var val4 = jqModal.find('[name="value4"]').val();
			if($.trim(val1)!=""){
				if(!reg.test(Math.abs(val1))){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0006',JoyinUtil.getLabel("RskSupervisionPortfPoint002.warnCond")));
					return false;
				}
			}
			if($.trim(val2)==""){
				if(lnk1!=lnkNo){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0007',JoyinUtil.getLabel("RskSupervisionPortfPoint002.warnCond")));
					return false;
				}
			}else{
				if($.trim(val1)==""){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0009',JoyinUtil.getLabel("RskSupervisionPortfPoint002.warnCond")));
					return false;
				}
				if(!reg.test(val2)){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0008',JoyinUtil.getLabel("RskSupervisionPortfPoint002.warnCond")));
					return false;
				}
			}
			if(val3==""){
				Joyin.error(JoyinUtil.getMessage('RSK_W_0005',JoyinUtil.getLabel("RskSupervisionPortfPoint002.foulCond")));
				return false;
			}else{
				if(!reg.test(Math.abs(val3))){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0006',JoyinUtil.getLabel("RskSupervisionPortfPoint002.foulCond")));
					return false;
				}
			}
			if(val4==""){
				if(lnk2!=lnkNo){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0007',JoyinUtil.getLabel("RskSupervisionPortfPoint002.foulCond")));
					return false;
				}
			}else{
				if(!reg.test(val4)){
					Joyin.error(JoyinUtil.getMessage('RSK_W_0008',JoyinUtil.getLabel("RskSupervisionPortfPoint002.foulCond")));
					return false;
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
				Joyin.error(JoyinUtil.getMessage("RSK_W_0011"));
				return false;
			}
		}
		
		return true;
	};
	//预警条件和违规条件封装成json串
	function abJson(){
		var jsonResult={};
		 //限制型 
		if(jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val().indexOf("LIMIT_")!=-1){
			var lnk1 = jqModal.find('[name="lnk1"]').val();
			var lnk2 = jqModal.find('[name="lnk2"]').val();
			var op1 = jqModal.find('[name="op1"]').val();
			var op2 = jqModal.find('[name="op2"]').val();
			var op3 = jqModal.find('[name="op3"]').val();
			var op4 = jqModal.find('[name="op4"]').val();
			var val1 = jqModal.find('[name="value1"]').val();
			var val2 = jqModal.find('[name="value2"]').val();
			var val3 = jqModal.find('[name="value3"]').val();
			var val4 = jqModal.find('[name="value4"]').val();
			if(val1==""){
				op1="";
			}
			if(val2==""){
				op2="";
			}
			if(val3==""){
				op3="";
			}
			if(val4==""){
				op4="";
			}
			//---------add-------------裴家俊--------------2018/10/29---------
			var unit1 = jqModal.find('[name="unit1"]').val();
			var unit2 = jqModal.find('[name="unit2"]').val();
			var unit3 = jqModal.find('[name="unit3"]').val();
			var unit4 = jqModal.find('[name="unit4"]').val();
			jsonResult = {
							//type:jqModal.find('[name="rskSupervisionIndicatorDto.indType"]').val(),
							warning: {
								threshold_1:{
									op:op1,
									value:val1,
									unit:unit1
									},
								threshold_2:{
									lnk:lnk1,
									op:op2,
									value:val2,
									unit:unit2
									}
								},
							violation: {
							    threshold_1: {
							      op: op3,
							      value: val3,
							      unit:unit3
							    },
							    threshold_2: {
							    	  lnk:lnk2,
								      op: op4,
								      value: val4,
								      unit:unit4
								}
							}
			}
			//jsonResult = JSON.stringify(jsonResult);
			jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val(JSON.stringify(jsonResult.warning));
			jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val(JSON.stringify(jsonResult.violation));
		}else{
			var jzx = jqModal.find('[name="jzx"]');
			var s='';
			//--add by jjpei 20190812---中文及禁投模式---start----
			var s_cn='';
			var is_model = jqModal.find('[name="forbidOrAgreeRadio"]:checked').val();
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
						  is_model:is_model
					}
				}
			}
			//jsonResult = JSON.stringify(jsonResult);
			//-----add by 裴家俊 2019/03/25------start-----
			jqModal.find('[name="rskSupervisionIndicatorDto.warnCond"]').val('');
			//-----add by 裴家俊 2019/03/25------start-----
			jqModal.find('[name="rskSupervisionIndicatorDto.foulCond"]').val(JSON.stringify(jsonResult.violationForbid));
		}
		
	};
	
 	$('#' + pageId + '_indType').change(function(){
		var pointType=$(this).val();
		if(pointType!=''){
			show_cond_add(pointType);
		}
	})
	
	//连接符change控制
	$("#lnk1").change(function(){
		show_lnk1($(this).val(),1);
	});
	//连接符change控制
	$("#lnk2").change(function(){
		show_lnk2($(this).val(),1);
	});
	
	layui.form.on('checkbox(03_timePoint)', function(data){
            if(data.elem.checked){
            	$("#frequencyDiv").show();
            }
            else {
            	$("select[name='rskSupervisionIndicatorDto.supFrequency']").val("").trigger('change');
            	$("#frequencyDiv").hide();
            }
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
			Joyin.error(JoyinUtil.getMessage("RSK_W_0012"));
			return false;
		}	
		if(s.indexOf('03')!=-1){
			if(jqModal.find('[name="rskSupervisionIndicatorDto.supFrequency"]').val()==''){
				Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.supFrequency")));
       		 	return false;
			}
		}
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