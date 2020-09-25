(function() {
	var jqModal = $('#rskSupervisionIndicatorForm');	
	var count=0;
	var divPzInfo=$('#dt-pz-info-add');
	var varSt='$'+'{';
	var varEt='}';
	jqModal.find('#btn-pz-add').click(function(){
		 if(pageModel == PAGE_MODEL_REFERENCE){
			 return;
		 }
    	count++;
    	var divStr=navHtmlBag();
    	divPzInfo.append(divStr);
    	$(".manager_fh").attr('data-result', SUP_CONNECTOR).textSelect();
    	$(".manager_rr").attr('data-result', JSON.stringify(INDICATOR_PARAM)).textSelect();
    	$(".btn-detail-del").layButton();

    });
	
	jqModal.on('click','.btn-detail-del',function() {
		var jqBtn = $(this);
		var jqDivId = jqBtn.closest('div').attr('id');
		$("#"+jqDivId).remove();
		count--;
	});
	
	//新增时
	function  navHtmlBag(){
		var divStr='';
		divStr+='<div class="layui-col-md6" style="margin-bottom:10px;overflow:hidden;height:36px;" id="dt-pz-info-'+count+'">';
        divStr+='<div class="input-inline select-ind-fh manager_fh_fh">';
        divStr+='<select type="text" id="manager_fh" name="manager_fh" formatoption="false" class="input-select  manager_fh"  placeholder="请输入内容"></select>'; 
        divStr+='</div>';
    	divStr+='<div class="input-inline select-ind-rr">';
        divStr+='<select type="text" id="manager_rr" name="manager_rr" formatoption="false" class="input-select   manager_rr" placeholder="请输入内容" ></select>';  
        divStr+='</div>';
    	divStr+='<a class="layui-btn btn-delete btn-detail-del" style="padding:0px"></a>';
    	divStr+='</div>';
    	return divStr;
	}	
	//修改或查看时
	function  navHtmlBagEditOrQuery(obj,objj){
		var divStr='';
		divStr+='<div class="layui-col-md6" style="margin-bottom:10px;overflow:hidden;height:36px;" id="dt-pz-info-'+count+'">';
		divStr+='<div class="input-inline select-ind-fh manager_fh_fh">';
        divStr+='<select type="text" id="manager_fh" name="manager_fh" formatoption="false"  class="input-select manager_fh"  placeholder="请输入内容"  allowClear="true" initValue="'+obj+'"></select>'; 
        divStr+='</div>';
    	divStr+='<div class="input-inline select-ind-rr">';
        divStr+='<select type="text" id="manager_rr" name="manager_rr"  formatoption="false" class="input-select manager_rr"  placeholder="请输入内容"  allowClear="true" initValue="'+objj+'"></select>'; 
        divStr+='</div>';
    	divStr+='<a class="layui-btn btn-delete btn-detail-del" style="padding:0px"></a>';
    	/*divStr+='<a class="layui-btn layui-btn-sm btn-create btn-detail-add"></a>';*/
    	divStr+='</div>';
    	return divStr;
	}	

	//校验画面指标表达式是否配置或是否合理
	function checkFunction() {
		
		var exprStr='';
		var expeStrChs='';
		var flag=true;
		jqModal.find("[name=manager_fh]").each(function(index){
			if(index!=0){
				if($(this).val()==""){
					flag=false;
					return false;
				}
			}
			exprStr+= $(this).val()+varSt+jqModal.find("[name=manager_rr]:eq("+index+")").val()+varEt;
			expeStrChs+= $(this).val()+jqModal.find("[name=manager_rr]:eq("+index+")").find("option:selected").text();
		 });
		if(!flag){
			 Joyin.error(JoyinUtil.getMessage("RSK_W_0004"));
			return false;
		}
		if(exprStr==""||exprStr=="${null}"||exprStr=="${}"){
			   Joyin.error(JoyinUtil.getMessage("RSK_W_0003"));
			   return false;
			}
			else{
				if(exprStr.indexOf("}($")!=-1){
					  Joyin.error(JoyinUtil.getMessage("RSK_W_0032"));
					  return false;
				}
				
				if(exprStr.indexOf("+${}")!=-1||exprStr.indexOf("+(${}")!=-1||exprStr.indexOf("-${}")!=-1||exprStr.indexOf("-(${}")!=-1||exprStr.indexOf("*${}")!=-1||exprStr.indexOf("*(${}")!=-1||exprStr.indexOf("/${}")!=-1||exprStr.indexOf("/(${}")!=-1){
					 Joyin.error(JoyinUtil.getMessage("RSK_W_0032"));
					 return false;
				}
				if(exprStr.indexOf("})$")==exprStr.length-5){
					exprStr=exprStr.replace("${}","");
				}
				else{
					if(exprStr.indexOf("})$")!=-1||exprStr.indexOf("${}")!=-1){
					 Joyin.error(JoyinUtil.getMessage("RSK_W_0032"));
					 return false;
					}
				}
			}
		if((exprStr.split('(')).length-1!=(exprStr.split(')')).length-1){
			  Joyin.error(JoyinUtil.getMessage("RSK_W_0030"));
			  return false;
		}
		if(exprStr.substring(0,1)!='('&&exprStr.substring(0,1)!='$'){
			  Joyin.error(JoyinUtil.getMessage("RSK_W_0036"));
			  return false;
		}
		if(getByteLen(exprStr)>600){
			Joyin.error(JoyinUtil.getMessage('RSK_W_0033',[JoyinUtil.getLabel("RskSupervisionIndicator002.expr"),"600"]));
			return false;
		}
		
		 jqModal.find('input[name="rskSupervisionIndicatorDto.expr"]').val(exprStr);
		// jqModal.find('input[name="exprChs"]').val(expeStrChs);
		 return true;
	};
	
	function getByteLen(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
           var length = val.charCodeAt(i);
           if(length>=0&&length<=128)
            {
                len += 1;
            }
            else
            {
                len += 2;
            }
        }
        return len;
    }
	//修改时，反显
	function changeFunction(obj){
		divPzInfo.empty();
		count=0;
		var expr=obj.expr;
		var expArr=expr.split(varEt);
		var incount=0;
		var divStr='';
		for(var i=0;i<expArr.length;i++){
			if(expArr[i]!=''){
				count++;
				var mxArr=expArr[i].split(varSt);
				if(mxArr==')'){
					var divStr=navHtmlBagEditOrQuery(mxArr[0],"");
				}
				else{
					if(mxArr.length>1){
						var divStr=navHtmlBagEditOrQuery(mxArr[0],mxArr[1]);
					}
				}
				divPzInfo.append(divStr);
				$(".manager_fh").attr('data-result', SUP_CONNECTOR).textSelect();
		    	$(".manager_rr").attr('data-result', JSON.stringify(INDICATOR_PARAM)).textSelect();
		    	$(".btn-detail-del").layButton();
				incount++; 
			}
		}
	}
	
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#rskSupervisionIndicatorForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.rskSupervisionIndicatorDto.warnCond=$('input[name="rskSupervisionIndicatorDto.warnCond"]').val();
        object.rskSupervisionIndicatorDto.foulCond=$('input[name="rskSupervisionIndicatorDto.foulCond"]').val();
        //object.rskSupervisionIndicatorDto.prohibitProj=$('input[name="rskSupervisionIndicatorDto.prohibitProj"]').val();
        //组合或组合包信息
    	if($("#startWfType").val()!='modify'){
    		object.selectedPortfIndItem = RskSupervisionIndicator004.packageSave();
    		//当没有选择组合时，清空有效日期
    		if(object.selectedPortfIndItem.length==0){
    			object.rskPortfolioIndicatorDto.startDate=null;
    			object.rskPortfolioIndicatorDto.endDate=null;
    		}
    		if(typeof $("#sortType") !== 'undefined'&& $("#sortType")){
    			object.singlePortfCond=$("#sortType").val();
    		}
    	}
    	 Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/superviseindicator/RskSupervisionIndicator002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList('/invest/risk/limitmanagement/superviseindicator/RskSupervisionIndicator002/save', object, "RskSupervisionIndicator002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList('/invest/risk/limitmanagement/superviseindicator/RskSupervisionIndicator002/save', object, "RskSupervisionIndicator002_btn_submit");
		        }
     		});
    	 });   
    };	
	
    //新建或编辑初始化
    function init(){
    	//修改  指标名称置灰
        if(pageModel!=PAGE_MODEL_NEW&&!workflowId){
        	//----add by jjpei 20190727----修复BSTWO-1088----start------
            //$("input[name='rskSupervisionIndicatorDto.indName']").attr('disabled',true);
			//----add by jjpei 20190727----修复BSTWO-1088----end------
        }
        else{
        	 if("modify"==$("#startWfType").val()){
        		 $("input[name='rskSupervisionIndicatorDto.indName']").attr('disabled',true);
        	 }
        }
    	if(entity!=null&&entity.rskSupervisionIndicatorDto!=null){
			changeFunction(entity.rskSupervisionIndicatorDto);
			//---add by jjpei 20190812---触发禁投参数联动带出枚举项----------
			if($('#' + pageId + '_indType').val().indexOf("FORBID_")!=-1) {
				var selData = jqModal.find('.manager_rr').eq(0).select2('data')[0].object;
				if (null != selData && selData.isForbid == 'Y') {
					displayForbidEnumInfo(selData.forbidEnum);//调用查询禁投枚举逻辑
				}
			}
		}else{
			divPzInfo.empty();
			jqModal.find('#btn-pz-add').click();//初始化先出来第一个
		}
    }
	/**
	 * 根据指标参数查询禁投项枚举--add by jjpei 20190812----
	 */
    var displayForbidEnumInfo=function(forbidEnum){
		Joyin.ajaxSync(basePath + 'invest/risk/limitmanagement/superviseindicator/RskSupervisionIndicator002/getForbidEnumInfo', {'forbidEnum':forbidEnum}, function(result) {
			var divJzd=$("#jztz_zclx");
			divJzd.empty();
			$("input[lay-filter='forbid_SelChk']").prop('checked',false);//清空全选框
			layui.form.render('checkbox');
			var reData=eval(result);
			var inputStr='<div class="element"><ul class="manage-info-item">'
			for (var i = 0; i < reData.length; i++){
				inputStr+='<li><div>'
				inputStr+='<input type="checkbox"  name="jzx" data-text="'+reData[i].text+'" value="'+reData[i].id+'">' + reData[i].text
				inputStr+='</div></li>'
			}
			inputStr+='</ul></div>';
			divJzd.append(inputStr);
			//进行js中拼接的html代码页面渲染（因为不是在html写的，是js动态构造的，不能自动渲染）
			layui.form.render('checkbox')

		});
	}
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
    	$('div#oneStepDiv').find('a.nextStep').on('click',function(event){
    		$(".errors").removeClass('errors')
    		if(!oneValidate()){
    			 event.stopPropagation();
        		 return false;
    		}
        	
        })
         $('div#secStepDiv').find('a.nextStep').on('click',function(event){
        	 $(".errors").removeClass('errors')
        	if(!twoValidate()){
        		event.stopPropagation();
        	}
        })
        function oneValidate(){
    		var messages="";
    		var mesFlag=true;
    		if($('#' + pageId + '_indName').val()==''){
    			 $('#' + pageId + '_indName').addClass('errors');
    			 messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.indName")) + '</span>'+'<br>';
       		     //Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.indName")));
        		// return false;
        		 mesFlag=false;
	       	}
	       	if($('#' + pageId + '_indType').val()==''){
	       		$('#' + pageId + '_indType').addClass('errors');
	       		messages += '<span>&nbsp' + JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.indType")) + '</span>'+'<br>';
	      		 /*Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.indType")));*/
		     	//return false;
		     	mesFlag=false;
	       	}
	       	if(!mesFlag){
	       		Joyin.error(messages.substring(0,messages.lastIndexOf('<br>')));
	       		return false;
	       	}
	       	if(checkFunction()){
		        $('#' + pageId + '_indNameTwo').val( $('#' + pageId + '_indName').val());
		        $('#' + pageId + '_indNameThree').val($('#' + pageId + '_indName').val());
	       	}
	       	else{
	       		return false;
	       	}
	       	return true;
    	}
    	function twoValidate(){
    		if($('#' + pageId + '_indName').val()==''){
       		Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RskSupervisionIndicator002.indName")));
        		 return false;
	       	}
	       	if(!RskSupervisionIndicator003.getTimePoint()){
	       		return false;
	       	};
	       	if(!RskSupervisionIndicator003.requiredW()){
	       		return false;
	   		}
	       	else{
	       		RskSupervisionIndicator003.abJson();
	       	}
	       	return true;
    	  }
        
    	var liList=$('div.pro-module-step').find('li');
        for(var i=0;i<liList.length;i++){
        	 (function(i){
        		 liList[i].addEventListener('click',function(){
        			if(i==1){
        				$(".errors").removeClass('errors')
        				if(!oneValidate()){
        			       event.stopPropagation();
        		 		}
        			}
        			if(i==2){
        				$(".errors").removeClass('errors')
        				if(!twoValidate()){
         			       event.stopPropagation();
         		 		}
        			}
        		 },true)//true默认先执行
        	 })(i);
        }
        //----add by jjpei 20190811---添加参数选择事件----start------
		jqModal.on('change','.manager_rr',function(){
			var selData=$(this).select2('data')[0].object;
			if(null==selData||selData.isForbid!='Y'){
				return;
			}
			if(null!=selData&&selData.isForbid=='Y'&&$("select[name='manager_rr']").length>=2){
				return;
			}
			displayForbidEnumInfo(selData.forbidEnum);
		})
		//----add by jjpei 20190811---添加参数选择事件----end------
        // 保存按钮执行事件
        $('#RskSupervisionIndicator002_btn_save').click(function(){
        	if(!oneValidate()){
    			return;
		 	}
        	if(!twoValidate()){
    			return;
		 	}
        	if($("#startWfType").val()!='modify'){
	        	if(!RskSupervisionIndicator004.check004Func()){
	    			return;
	    		}
        	}
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#RskSupervisionIndicator002_btn_submit').click(function(){
        	if(!oneValidate()){
    			return;
		 	}
        	if(!twoValidate()){
    			return;
		 	}
        	if($("#startWfType").val()!='modify'){
	        	if(!RskSupervisionIndicator004.check004Func()){
	    			return;
	    		}
        	}
    		onSaveOrSubmitAction('submit');
        });
    });
}());